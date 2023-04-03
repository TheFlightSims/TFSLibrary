/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColorCube;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.UntiledOpImage;
/*     */ 
/*     */ final class ErrorDiffusionOpImage extends UntiledOpImage {
/*     */   private static final float FLOAT_EPSILON = 1.1920929E-7F;
/*     */   
/*     */   private static final int NBANDS = 3;
/*     */   
/*     */   private static final int NGRAYS = 256;
/*     */   
/*     */   private static final int OVERSHOOT = 256;
/*     */   
/*     */   private static final int UNDERSHOOT = 256;
/*     */   
/*     */   private static final int TOTALGRAYS = 768;
/*     */   
/*     */   private static final int ERR_SHIFT = 8;
/*     */   
/*     */   protected LookupTableJAI colorMap;
/*     */   
/*     */   protected KernelJAI errorKernel;
/*     */   
/*     */   private int numBandsSource;
/*     */   
/*     */   private boolean isOptimizedCase = false;
/*     */   
/*     */   private float minPixelValue;
/*     */   
/*     */   private float maxPixelValue;
/*     */   
/*     */   private static boolean isFloydSteinbergKernel(KernelJAI kernel) {
/* 114 */     int ky = kernel.getYOrigin();
/* 116 */     return (kernel.getWidth() == 3 && kernel.getXOrigin() == 1 && kernel.getHeight() - ky == 2 && Math.abs(kernel.getElement(2, ky) - 0.4375F) < 1.1920929E-7F && Math.abs(kernel.getElement(0, ky + 1) - 0.1875F) < 1.1920929E-7F && Math.abs(kernel.getElement(1, ky + 1) - 0.3125F) < 1.1920929E-7F && Math.abs(kernel.getElement(2, ky + 1) - 0.0625F) < 1.1920929E-7F);
/*     */   }
/*     */   
/*     */   private static int[] initFloydSteinberg24To8(ColorCube colorCube) {
/* 137 */     int[] ditherTable = new int[2304];
/* 139 */     float[] thresh = new float[256];
/* 144 */     int[] multipliers = colorCube.getMultipliers();
/* 145 */     int[] dimsLessOne = colorCube.getDimsLessOne();
/* 146 */     int offset = colorCube.getAdjustedOffset();
/* 151 */     for (int band = 0; band < 3; band++) {
/* 152 */       int j = band * 768;
/* 159 */       float binWidth = 255.0F / dimsLessOne[band];
/* 168 */       for (int i = 0; i < dimsLessOne[band]; i++)
/* 169 */         thresh[i] = (i + 0.5F) * binWidth; 
/* 171 */       thresh[dimsLessOne[band]] = 256.0F;
/* 178 */       int tableInc = 256;
/* 179 */       int tableValue = -65536;
/* 180 */       for (int gray = -256; gray < 0; gray++) {
/* 181 */         ditherTable[j++] = tableValue;
/* 182 */         tableValue += tableInc;
/*     */       } 
/* 188 */       int indexContrib = 0;
/* 189 */       float frepValue = 0.0F;
/* 191 */       int binNum = 0;
/* 192 */       float threshold = thresh[0];
/* 193 */       int k = 0;
/* 194 */       while (k < 256) {
/* 201 */         int tableBase = indexContrib;
/* 202 */         int m = (int)(frepValue + 0.5F);
/* 203 */         while (k < threshold) {
/* 204 */           ditherTable[j++] = (k - m << 8) + tableBase;
/* 206 */           k++;
/*     */         } 
/* 215 */         threshold = thresh[++binNum];
/* 216 */         indexContrib += multipliers[band];
/* 217 */         frepValue += binWidth;
/*     */       } 
/* 225 */       indexContrib -= multipliers[band];
/* 226 */       int repValue = 255;
/* 227 */       tableValue = 256 - repValue << 8 | indexContrib;
/* 229 */       for (k = 256; k < 512; k++) {
/* 230 */         ditherTable[j++] = tableValue;
/* 231 */         tableValue += tableInc;
/*     */       } 
/*     */     } 
/* 241 */     int pTab = 0;
/* 242 */     for (int count = 768; count != 0; count--) {
/* 243 */       ditherTable[pTab] = ditherTable[pTab] + offset;
/* 244 */       pTab++;
/*     */     } 
/* 247 */     return ditherTable;
/*     */   }
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, LookupTableJAI colorMap) {
/* 257 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/* 261 */     il.setMinX(source.getMinX());
/* 262 */     il.setMinY(source.getMinY());
/* 263 */     il.setWidth(source.getWidth());
/* 264 */     il.setHeight(source.getHeight());
/* 267 */     SampleModel sm = il.getSampleModel(source);
/* 270 */     if (colorMap.getNumBands() == 1 && colorMap.getNumEntries() == 2 && !ImageUtil.isBinary(il.getSampleModel(source))) {
/* 273 */       sm = new MultiPixelPackedSampleModel(0, il.getTileWidth(source), il.getTileHeight(source), 1);
/* 277 */       il.setSampleModel(sm);
/*     */     } 
/* 281 */     if (sm.getNumBands() != 1) {
/* 282 */       sm = RasterFactory.createComponentSampleModel(sm, sm.getTransferType(), sm.getWidth(), sm.getHeight(), 1);
/* 288 */       il.setSampleModel(sm);
/* 291 */       ColorModel cm = il.getColorModel(null);
/* 292 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 295 */         il.unsetValid(512); 
/*     */     } 
/* 300 */     int numColorMapBands = colorMap.getNumBands();
/* 301 */     int maxIndex = 0;
/* 302 */     for (int i = 0; i < numColorMapBands; i++)
/* 303 */       maxIndex = Math.max(colorMap.getOffset(i) + colorMap.getNumEntries() - 1, maxIndex); 
/* 309 */     if ((maxIndex > 255 && sm.getDataType() == 0) || (maxIndex > 65535 && sm.getDataType() != 3)) {
/* 311 */       int dataType = (maxIndex > 65535) ? 3 : 1;
/* 313 */       sm = RasterFactory.createComponentSampleModel(sm, dataType, sm.getWidth(), sm.getHeight(), 1);
/* 319 */       il.setSampleModel(sm);
/* 322 */       ColorModel cm = il.getColorModel(null);
/* 323 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 326 */         il.unsetValid(512); 
/*     */     } 
/* 335 */     if ((layout == null || !il.isValid(512)) && source.getSampleModel().getDataType() == 0 && (sm.getDataType() == 0 || sm.getDataType() == 1) && colorMap.getDataType() == 0 && colorMap.getNumBands() == 3) {
/* 341 */       ColorModel cm = source.getColorModel();
/* 342 */       if (cm == null || (cm != null && cm.getColorSpace().isCS_sRGB())) {
/* 344 */         int size = colorMap.getNumEntries();
/* 345 */         byte[][] cmap = new byte[3][maxIndex + 1];
/* 346 */         for (int j = 0; j < 3; j++) {
/* 347 */           byte[] band = cmap[j];
/* 348 */           byte[] data = colorMap.getByteData(j);
/* 349 */           int offset = colorMap.getOffset(j);
/* 350 */           int end = offset + size;
/* 351 */           for (int k = offset; k < end; k++)
/* 352 */             band[k] = data[k - offset]; 
/*     */         } 
/* 356 */         int numBits = (sm.getDataType() == 0) ? 8 : 16;
/* 358 */         il.setColorModel(new IndexColorModel(numBits, maxIndex + 1, cmap[0], cmap[1], cmap[2]));
/*     */       } 
/*     */     } 
/* 364 */     return il;
/*     */   }
/*     */   
/*     */   public ErrorDiffusionOpImage(RenderedImage source, Map config, ImageLayout layout, LookupTableJAI colorMap, KernelJAI errorKernel) {
/* 396 */     super(source, config, layoutHelper(layout, source, colorMap));
/* 399 */     SampleModel srcSampleModel = source.getSampleModel();
/* 402 */     this.numBandsSource = srcSampleModel.getNumBands();
/* 405 */     this.colorMap = colorMap;
/* 408 */     this.errorKernel = errorKernel;
/* 411 */     this.isOptimizedCase = (this.sampleModel.getTransferType() == 0 && srcSampleModel.getTransferType() == 0 && this.numBandsSource == 3 && colorMap instanceof ColorCube && isFloydSteinbergKernel(errorKernel));
/* 419 */     switch (colorMap.getDataType()) {
/*     */       case 0:
/* 422 */         this.minPixelValue = 0.0F;
/* 423 */         this.maxPixelValue = 255.0F;
/*     */         return;
/*     */       case 2:
/* 426 */         this.minPixelValue = -32768.0F;
/* 427 */         this.maxPixelValue = 32767.0F;
/*     */         return;
/*     */       case 1:
/* 430 */         this.minPixelValue = 0.0F;
/* 431 */         this.maxPixelValue = 65535.0F;
/*     */         return;
/*     */       case 3:
/* 434 */         this.minPixelValue = -2.1474836E9F;
/* 435 */         this.maxPixelValue = 2.1474836E9F;
/*     */         return;
/*     */       case 4:
/* 438 */         this.minPixelValue = 0.0F;
/* 439 */         this.maxPixelValue = Float.MAX_VALUE;
/*     */         return;
/*     */       case 5:
/* 442 */         this.minPixelValue = 0.0F;
/* 443 */         this.maxPixelValue = Float.MAX_VALUE;
/*     */         return;
/*     */     } 
/* 446 */     throw new RuntimeException(JaiI18N.getString("ErrorDiffusionOpImage0"));
/*     */   }
/*     */   
/*     */   protected void computeImage(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 466 */     Raster source = sources[0];
/* 468 */     if (this.isOptimizedCase) {
/* 469 */       computeImageOptimized(source, dest, destRect);
/*     */     } else {
/* 471 */       computeImageDefault(source, dest, destRect);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeImageDefault(Raster source, WritableRaster dest, Rectangle destRect) {
/* 479 */     int startX = this.minX;
/* 480 */     int endX = startX + this.width - 1;
/* 483 */     int startY = this.minY;
/* 484 */     int endY = startY + this.height - 1;
/* 487 */     int numLinesBuffer = this.errorKernel.getHeight() - this.errorKernel.getYOrigin();
/* 491 */     float[][] bufMem = new float[numLinesBuffer][this.width * this.numBandsSource];
/* 494 */     int[] bufIdx = new int[numLinesBuffer];
/* 497 */     for (int idx = 0; idx < numLinesBuffer; idx++) {
/* 498 */       bufIdx[idx] = idx;
/* 499 */       source.getPixels(startX, startY + idx, this.width, 1, bufMem[idx]);
/*     */     } 
/* 503 */     int lastLineBuffer = numLinesBuffer - 1;
/* 506 */     int kernelWidth = this.errorKernel.getWidth();
/* 507 */     float[] kernelData = this.errorKernel.getKernelData();
/* 508 */     int diffuseRight = kernelWidth - this.errorKernel.getXOrigin() - 1;
/* 509 */     int diffuseBelow = this.errorKernel.getHeight() - this.errorKernel.getYOrigin() - 1;
/* 511 */     int kernelOffsetRight = this.errorKernel.getYOrigin() * kernelWidth + this.errorKernel.getXOrigin() + 1;
/* 514 */     int kernelOffsetBelow = (this.errorKernel.getYOrigin() + 1) * kernelWidth;
/* 517 */     float[] currentPixel = new float[this.numBandsSource];
/* 518 */     int offset = this.colorMap.getOffset();
/* 519 */     float[] qError = new float[this.numBandsSource];
/* 522 */     int[] dstData = new int[this.width];
/* 523 */     for (int y = startY; y <= endY; y++) {
/* 524 */       int currentIndex = bufIdx[0];
/* 525 */       float[] currentLine = bufMem[currentIndex];
/* 528 */       int dstOffset = 0;
/* 529 */       for (int x = startX, z = 0; x <= endX; x++) {
/* 531 */         for (int b = 0; b < this.numBandsSource; b++) {
/* 532 */           currentPixel[b] = currentLine[z++];
/* 535 */           if (currentPixel[b] < this.minPixelValue || currentPixel[b] > this.maxPixelValue) {
/* 537 */             currentPixel[b] = Math.max(currentPixel[b], this.minPixelValue);
/* 539 */             currentPixel[b] = Math.min(currentPixel[b], this.maxPixelValue);
/*     */           } 
/*     */         } 
/* 545 */         int nearestIndex = this.colorMap.findNearestEntry(currentPixel);
/* 548 */         dstData[dstOffset++] = nearestIndex;
/* 551 */         boolean isQuantizationError = false;
/* 552 */         for (int i = 0; i < this.numBandsSource; i++) {
/* 553 */           qError[i] = currentPixel[i] - this.colorMap.lookupFloat(i, nearestIndex);
/* 556 */           if (qError[i] != 0.0F)
/* 557 */             isQuantizationError = true; 
/*     */         } 
/* 562 */         if (isQuantizationError) {
/* 564 */           int rightCount = Math.min(diffuseRight, endX - x);
/* 565 */           int kernelOffset = kernelOffsetRight;
/* 566 */           int sampleOffset = z;
/* 567 */           for (int u = 1; u <= rightCount; u++) {
/* 568 */             for (int j = 0; j < this.numBandsSource; j++)
/* 569 */               currentLine[sampleOffset++] = currentLine[sampleOffset++] + qError[j] * kernelData[kernelOffset]; 
/* 572 */             kernelOffset++;
/*     */           } 
/* 576 */           int offsetLeft = Math.min(x - startX, diffuseRight);
/* 577 */           int count = Math.min(x + diffuseRight, endX) - Math.max(x - diffuseRight, startX) + 1;
/* 580 */           for (int v = 1; v <= diffuseBelow; v++) {
/* 581 */             float[] line = bufMem[bufIdx[v]];
/* 582 */             kernelOffset = kernelOffsetBelow;
/* 583 */             sampleOffset = z - (offsetLeft + 1) * this.numBandsSource;
/* 584 */             for (int j = 1; j <= count; j++) {
/* 585 */               for (int m = 0; m < this.numBandsSource; m++)
/* 586 */                 line[sampleOffset++] = line[sampleOffset++] + qError[m] * kernelData[kernelOffset]; 
/* 589 */               kernelOffset++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 598 */       dest.setSamples(startX, y, destRect.width, 1, 0, dstData);
/* 601 */       for (int k = 0; k < lastLineBuffer; k++)
/* 602 */         bufIdx[k] = bufIdx[k + 1]; 
/* 604 */       bufIdx[lastLineBuffer] = currentIndex;
/* 607 */       if (y + numLinesBuffer < getMaxY())
/* 608 */         source.getPixels(startX, y + numLinesBuffer, this.width, 1, bufMem[bufIdx[lastLineBuffer]]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeImageOptimized(Raster source, WritableRaster dest, Rectangle destRect) {
/* 618 */     int startX = this.minX;
/* 619 */     int endX = startX + this.width - 1;
/* 622 */     int startY = this.minY;
/* 623 */     int endY = startY + this.height - 1;
/* 626 */     int[] ditherTable = initFloydSteinberg24To8((ColorCube)this.colorMap);
/* 629 */     int sourceWidthPadded = source.getWidth() + 2;
/* 632 */     int[] errBuf = new int[sourceWidthPadded * 3];
/* 635 */     RasterFormatTag[] formatTags = getFormatTags();
/* 637 */     RasterAccessor srcAccessor = new RasterAccessor(source, new Rectangle(startX, startY, source.getWidth(), source.getHeight()), formatTags[0], getSourceImage(0).getColorModel());
/* 643 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 647 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 648 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 649 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 650 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 653 */     byte[] srcData0 = srcAccessor.getByteDataArray(0);
/* 654 */     byte[] srcData1 = srcAccessor.getByteDataArray(1);
/* 655 */     byte[] srcData2 = srcAccessor.getByteDataArray(2);
/* 656 */     byte[] dstData = dstAccessor.getByteDataArray(0);
/* 659 */     int srcLine0 = srcAccessor.getBandOffset(0);
/* 660 */     int srcLine1 = srcAccessor.getBandOffset(1);
/* 661 */     int srcLine2 = srcAccessor.getBandOffset(2);
/* 662 */     int dstLine = dstAccessor.getBandOffset(0);
/* 673 */     for (int y = startY; y <= endY; y++) {
/* 675 */       int srcPixel0 = srcLine0;
/* 676 */       int srcPixel1 = srcLine1;
/* 677 */       int srcPixel2 = srcLine2;
/* 678 */       int dstPixel = dstLine;
/* 727 */       int errRedA = 0;
/* 728 */       int errRedC = 0;
/* 729 */       int errRedD = 0;
/* 730 */       int errGrnA = 0;
/* 731 */       int errGrnC = 0;
/* 732 */       int errGrnD = 0;
/* 733 */       int errBluA = 0;
/* 734 */       int errBluC = 0;
/* 735 */       int errBluD = 0;
/* 737 */       int pErr = 0;
/* 738 */       int dstOffset = 0;
/* 739 */       for (int x = startX; x <= endX; x++) {
/* 745 */         int pTab = 256;
/* 747 */         int adjVal = (errRedA + errBuf[pErr + 3] + 8 >> 4) + (srcData0[srcPixel0] & 0xFF);
/* 750 */         srcPixel0 += srcPixelStride;
/* 751 */         int tabval = ditherTable[pTab + adjVal];
/* 752 */         int err = tabval >> 8;
/* 753 */         int err1 = err;
/* 754 */         int index = tabval & 0xFF;
/* 755 */         int err2 = err + err;
/* 756 */         errBuf[pErr] = errRedC + (err += err2);
/* 757 */         errRedC = errRedD + (err += err2);
/* 758 */         errRedD = err1;
/* 759 */         errRedA = err += err2;
/* 766 */         pTab += 768;
/* 768 */         adjVal = (errGrnA + errBuf[pErr + 4] + 8 >> 4) + (srcData1[srcPixel1] & 0xFF);
/* 771 */         srcPixel1 += srcPixelStride;
/* 772 */         tabval = ditherTable[pTab + adjVal];
/* 773 */         err = tabval >> 8;
/* 774 */         err1 = err;
/* 775 */         index += tabval & 0xFF;
/* 776 */         err2 = err + err;
/* 777 */         errBuf[pErr + 1] = errGrnC + (err += err2);
/* 778 */         errGrnC = errGrnD + (err += err2);
/* 779 */         errGrnD = err1;
/* 780 */         errGrnA = err += err2;
/* 782 */         pTab += 768;
/* 789 */         adjVal = (errBluA + errBuf[pErr + 5] + 8 >> 4) + (srcData2[srcPixel2] & 0xFF);
/* 792 */         srcPixel2 += srcPixelStride;
/* 793 */         tabval = ditherTable[pTab + adjVal];
/* 794 */         err = tabval >> 8;
/* 795 */         err1 = err;
/* 796 */         index += tabval & 0xFF;
/* 797 */         err2 = err + err;
/* 798 */         errBuf[pErr + 2] = errBluC + (err += err2);
/* 799 */         errBluC = errBluD + (err += err2);
/* 800 */         errBluD = err1;
/* 801 */         errBluA = err += err2;
/* 804 */         dstData[dstPixel] = (byte)(index & 0xFF);
/* 805 */         dstPixel += dstPixelStride;
/* 807 */         pErr += 3;
/*     */       } 
/* 814 */       int last = 3 * (sourceWidthPadded - 2);
/* 815 */       errBuf[last] = errRedC;
/* 816 */       errBuf[last + 1] = errGrnC;
/* 817 */       errBuf[last + 2] = errBluC;
/* 820 */       srcLine0 += srcScanlineStride;
/* 821 */       srcLine1 += srcScanlineStride;
/* 822 */       srcLine2 += srcScanlineStride;
/* 823 */       dstLine += dstScanlineStride;
/*     */     } 
/* 828 */     dstAccessor.copyDataToRaster();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ErrorDiffusionOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */