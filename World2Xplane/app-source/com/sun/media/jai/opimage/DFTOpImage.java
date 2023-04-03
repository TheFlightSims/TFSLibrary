/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.media.jai.util.MathJAI;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.UntiledOpImage;
/*     */ import javax.media.jai.operator.DFTDescriptor;
/*     */ 
/*     */ public class DFTOpImage extends UntiledOpImage {
/*     */   FFT fft;
/*     */   
/*     */   protected boolean complexSrc;
/*     */   
/*     */   protected boolean complexDst;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, EnumeratedParameter dataNature) {
/*     */     int newWidth, newHeight;
/*  76 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  80 */     il.setMinX(source.getMinX());
/*  81 */     il.setMinY(source.getMinY());
/*  86 */     int currentWidth = il.getWidth(source);
/*  87 */     int currentHeight = il.getHeight(source);
/*  90 */     if (currentWidth == 1 && currentHeight == 1) {
/*  91 */       newWidth = newHeight = 1;
/*  92 */     } else if (currentWidth == 1 && currentHeight > 1) {
/*  93 */       newWidth = 1;
/*  94 */       newHeight = MathJAI.nextPositivePowerOf2(currentHeight);
/*  95 */     } else if (currentWidth > 1 && currentHeight == 1) {
/*  96 */       newWidth = MathJAI.nextPositivePowerOf2(currentWidth);
/*  97 */       newHeight = 1;
/*     */     } else {
/*  99 */       newWidth = MathJAI.nextPositivePowerOf2(currentWidth);
/* 100 */       newHeight = MathJAI.nextPositivePowerOf2(currentHeight);
/*     */     } 
/* 102 */     il.setWidth(newWidth);
/* 103 */     il.setHeight(newHeight);
/* 106 */     boolean isComplexSource = !dataNature.equals(DFTDescriptor.REAL_TO_COMPLEX);
/* 108 */     boolean isComplexDest = !dataNature.equals(DFTDescriptor.COMPLEX_TO_REAL);
/* 112 */     boolean createNewSampleModel = false;
/* 115 */     SampleModel srcSampleModel = source.getSampleModel();
/* 116 */     int requiredNumBands = srcSampleModel.getNumBands();
/* 117 */     if (isComplexSource && !isComplexDest) {
/* 118 */       requiredNumBands /= 2;
/* 119 */     } else if (!isComplexSource && isComplexDest) {
/* 120 */       requiredNumBands *= 2;
/*     */     } 
/* 124 */     SampleModel sm = il.getSampleModel(source);
/* 125 */     int numBands = sm.getNumBands();
/* 126 */     if (numBands != requiredNumBands) {
/* 127 */       numBands = requiredNumBands;
/* 128 */       createNewSampleModel = true;
/*     */     } 
/* 132 */     int dataType = sm.getTransferType();
/* 133 */     if (dataType != 4 && dataType != 5) {
/* 135 */       dataType = 4;
/* 136 */       createNewSampleModel = true;
/*     */     } 
/* 140 */     if (createNewSampleModel) {
/* 141 */       sm = RasterFactory.createComponentSampleModel(sm, dataType, newWidth, newHeight, numBands);
/* 146 */       il.setSampleModel(sm);
/* 149 */       ColorModel cm = il.getColorModel(null);
/* 150 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 153 */         il.unsetValid(512); 
/*     */     } 
/* 157 */     return il;
/*     */   }
/*     */   
/*     */   public DFTOpImage(RenderedImage source, Map config, ImageLayout layout, EnumeratedParameter dataNature, FFT fft) {
/* 180 */     super(source, config, layoutHelper(layout, source, dataNature));
/* 183 */     this.fft = fft;
/* 186 */     this.complexSrc = !dataNature.equals(DFTDescriptor.REAL_TO_COMPLEX);
/* 187 */     this.complexDst = !dataNature.equals(DFTDescriptor.COMPLEX_TO_REAL);
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 204 */     if (destPt == null)
/* 205 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 208 */     return null;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 222 */     if (sourcePt == null)
/* 223 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 226 */     return null;
/*     */   }
/*     */   
/*     */   protected void computeImage(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 239 */     Raster source = sources[0];
/* 242 */     if (destRect.width == 1 && destRect.height == 1) {
/* 243 */       int nDstBands = this.sampleModel.getNumBands();
/* 244 */       double[] srcPixel = new double[source.getSampleModel().getNumBands()];
/* 246 */       source.getPixel(destRect.x, destRect.y, srcPixel);
/* 247 */       if (this.complexSrc && this.complexDst) {
/* 248 */         dest.setPixel(destRect.x, destRect.y, srcPixel);
/* 249 */       } else if (this.complexSrc) {
/* 250 */         for (int i = 0; i < nDstBands; i++)
/* 252 */           dest.setSample(destRect.x, destRect.y, i, srcPixel[2 * i]); 
/* 254 */       } else if (this.complexDst) {
/* 255 */         for (int i = 0; i < nDstBands; i++)
/* 257 */           dest.setSample(destRect.x, destRect.y, i, (i % 2 == 0) ? srcPixel[i / 2] : 0.0D); 
/*     */       } else {
/* 262 */         throw new RuntimeException(JaiI18N.getString("DFTOpImage1"));
/*     */       } 
/*     */       return;
/*     */     } 
/* 268 */     this.fft.setLength((destRect.width > 1) ? getWidth() : getHeight());
/* 271 */     int srcWidth = source.getWidth();
/* 272 */     int srcHeight = source.getHeight();
/* 273 */     int srcX = source.getMinX();
/* 274 */     int srcY = source.getMinY();
/* 277 */     RasterFormatTag[] formatTags = getFormatTags();
/* 279 */     RasterAccessor srcAccessor = new RasterAccessor(source, new Rectangle(srcX, srcY, srcWidth, srcHeight), formatTags[0], getSourceImage(0).getColorModel());
/* 284 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 288 */     int srcDataType = srcAccessor.getDataType();
/* 289 */     int dstDataType = dstAccessor.getDataType();
/* 292 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 293 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 294 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 295 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 296 */     int dstPixelStrideImag = 1;
/* 297 */     int dstLineStrideImag = destRect.width;
/* 298 */     if (this.complexDst) {
/* 299 */       dstPixelStrideImag = dstPixelStride;
/* 300 */       dstLineStrideImag = dstScanlineStride;
/*     */     } 
/* 304 */     int srcBandIndex = 0;
/* 305 */     int srcBandStride = this.complexSrc ? 2 : 1;
/* 306 */     int dstBandIndex = 0;
/* 307 */     int dstBandStride = this.complexDst ? 2 : 1;
/* 310 */     int numComponents = this.complexDst ? (dest.getSampleModel().getNumBands() / 2) : dest.getSampleModel().getNumBands();
/* 315 */     for (int comp = 0; comp < numComponents; comp++) {
/* 317 */       Object srcReal = srcAccessor.getDataArray(srcBandIndex);
/* 320 */       Object srcImag = null;
/* 321 */       if (this.complexSrc)
/* 322 */         srcImag = srcAccessor.getDataArray(srcBandIndex + 1); 
/* 326 */       Object dstReal = dstAccessor.getDataArray(dstBandIndex);
/* 327 */       Object dstImag = null;
/* 328 */       if (this.complexDst) {
/* 329 */         dstImag = dstAccessor.getDataArray(dstBandIndex + 1);
/* 334 */       } else if (dstDataType == 4) {
/* 335 */         dstImag = new float[destRect.width * destRect.height];
/*     */       } else {
/* 337 */         dstImag = new double[destRect.width * destRect.height];
/*     */       } 
/* 341 */       if (destRect.width > 1) {
/* 343 */         this.fft.setLength(getWidth());
/* 346 */         int srcOffsetReal = srcAccessor.getBandOffset(srcBandIndex);
/* 348 */         int srcOffsetImag = 0;
/* 349 */         if (this.complexSrc)
/* 350 */           srcOffsetImag = srcAccessor.getBandOffset(srcBandIndex + 1); 
/* 355 */         int dstOffsetReal = dstAccessor.getBandOffset(dstBandIndex);
/* 357 */         int dstOffsetImag = 0;
/* 358 */         if (this.complexDst)
/* 359 */           dstOffsetImag = dstAccessor.getBandOffset(dstBandIndex + 1); 
/* 364 */         for (int row = 0; row < srcHeight; row++) {
/* 366 */           this.fft.setData(srcDataType, srcReal, srcOffsetReal, srcPixelStride, srcImag, srcOffsetImag, srcPixelStride, srcWidth);
/* 372 */           this.fft.transform();
/* 375 */           this.fft.getData(dstDataType, dstReal, dstOffsetReal, dstPixelStride, dstImag, dstOffsetImag, dstPixelStrideImag);
/* 380 */           srcOffsetReal += srcScanlineStride;
/* 381 */           srcOffsetImag += srcScanlineStride;
/* 382 */           dstOffsetReal += dstScanlineStride;
/* 383 */           dstOffsetImag += dstLineStrideImag;
/*     */         } 
/*     */       } 
/* 387 */       if (destRect.width == 1) {
/* 396 */         int srcOffsetReal = srcAccessor.getBandOffset(srcBandIndex);
/* 398 */         int srcOffsetImag = 0;
/* 399 */         if (this.complexSrc)
/* 400 */           srcOffsetImag = srcAccessor.getBandOffset(srcBandIndex + 1); 
/* 405 */         int dstOffsetReal = dstAccessor.getBandOffset(dstBandIndex);
/* 407 */         int dstOffsetImag = 0;
/* 408 */         if (this.complexDst)
/* 409 */           dstOffsetImag = dstAccessor.getBandOffset(dstBandIndex + 1); 
/* 414 */         this.fft.setData(srcDataType, srcReal, srcOffsetReal, srcScanlineStride, srcImag, srcOffsetImag, srcScanlineStride, srcHeight);
/* 420 */         this.fft.transform();
/* 423 */         this.fft.getData(dstDataType, dstReal, dstOffsetReal, dstScanlineStride, dstImag, dstOffsetImag, dstLineStrideImag);
/* 426 */       } else if (destRect.height > 1) {
/* 428 */         this.fft.setLength(getHeight());
/* 431 */         int dstOffsetReal = dstAccessor.getBandOffset(dstBandIndex);
/* 433 */         int dstOffsetImag = 0;
/* 434 */         if (this.complexDst)
/* 435 */           dstOffsetImag = dstAccessor.getBandOffset(dstBandIndex + 1); 
/* 440 */         for (int col = 0; col < destRect.width; col++) {
/* 442 */           this.fft.setData(dstDataType, dstReal, dstOffsetReal, dstScanlineStride, dstImag, dstOffsetImag, dstLineStrideImag, destRect.height);
/* 448 */           this.fft.transform();
/* 451 */           this.fft.getData(dstDataType, dstReal, dstOffsetReal, dstScanlineStride, this.complexDst ? dstImag : null, dstOffsetImag, dstLineStrideImag);
/* 457 */           dstOffsetReal += dstPixelStride;
/* 458 */           dstOffsetImag += dstPixelStrideImag;
/*     */         } 
/*     */       } 
/* 463 */       srcBandIndex += srcBandStride;
/* 464 */       dstBandIndex += dstBandStride;
/*     */     } 
/* 467 */     if (dstAccessor.needsClamping())
/* 468 */       dstAccessor.clampDataArrays(); 
/* 472 */     dstAccessor.copyDataToRaster();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DFTOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */