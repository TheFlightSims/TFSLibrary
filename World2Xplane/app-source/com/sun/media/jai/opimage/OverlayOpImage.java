/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class OverlayOpImage extends PointOpImage {
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, Vector sources, Map config) {
/*  55 */     if (layout != null) {
/*  56 */       layout = (ImageLayout)layout.clone();
/*  57 */       layout.unsetImageBounds();
/*     */     } 
/*  60 */     return layout;
/*     */   }
/*     */   
/*     */   public OverlayOpImage(RenderedImage sourceUnder, RenderedImage sourceOver, Map config, ImageLayout layout) {
/*  75 */     super(sourceUnder, sourceOver, layoutHelper(layout, vectorize(sourceUnder, sourceOver), config), config, true);
/*  80 */     SampleModel srcSM = sourceUnder.getSampleModel();
/*  81 */     if (this.sampleModel.getTransferType() != srcSM.getTransferType() || this.sampleModel.getNumBands() != srcSM.getNumBands()) {
/*  83 */       this.sampleModel = srcSM.createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*  86 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  89 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/*  99 */     this.minX = sourceUnder.getMinX();
/* 100 */     this.minY = sourceUnder.getMinY();
/* 101 */     this.width = sourceUnder.getWidth();
/* 102 */     this.height = sourceUnder.getHeight();
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 113 */     WritableRaster dest = createTile(tileX, tileY);
/* 116 */     Rectangle destRect = dest.getBounds().intersection(getBounds());
/* 118 */     PlanarImage srcUnder = getSource(0);
/* 119 */     PlanarImage srcOver = getSource(1);
/* 121 */     Rectangle srcUnderBounds = srcUnder.getBounds();
/* 122 */     Rectangle srcOverBounds = srcOver.getBounds();
/* 126 */     Raster[] sources = new Raster[1];
/* 127 */     if (srcOverBounds.contains(destRect)) {
/* 129 */       sources[0] = srcOver.getData(destRect);
/* 130 */       computeRect(sources, dest, destRect);
/* 133 */       if (srcOver.overlapsMultipleTiles(destRect))
/* 134 */         recycleTile(sources[0]); 
/* 137 */       return dest;
/*     */     } 
/* 139 */     if (srcUnderBounds.contains(destRect) && !srcOverBounds.intersects(destRect)) {
/* 142 */       sources[0] = srcUnder.getData(destRect);
/* 143 */       computeRect(sources, dest, destRect);
/* 146 */       if (srcUnder.overlapsMultipleTiles(destRect))
/* 147 */         recycleTile(sources[0]); 
/* 150 */       return dest;
/*     */     } 
/* 154 */     Rectangle isectUnder = destRect.intersection(srcUnderBounds);
/* 155 */     sources[0] = srcUnder.getData(isectUnder);
/* 156 */     computeRect(sources, dest, isectUnder);
/* 159 */     if (srcUnder.overlapsMultipleTiles(isectUnder))
/* 160 */       recycleTile(sources[0]); 
/* 163 */     if (srcOverBounds.intersects(destRect)) {
/* 164 */       Rectangle isectOver = destRect.intersection(srcOverBounds);
/* 165 */       sources[0] = srcOver.getData(isectOver);
/* 166 */       computeRect(sources, dest, isectOver);
/* 169 */       if (srcOver.overlapsMultipleTiles(isectOver))
/* 170 */         recycleTile(sources[0]); 
/*     */     } 
/* 174 */     return dest;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 191 */     RasterFormatTag[] formatTags = getFormatTags();
/* 194 */     RasterAccessor src = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/* 197 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 200 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 202 */         computeRectByte(src, dst);
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 206 */         computeRectShort(src, dst);
/*     */         break;
/*     */       case 3:
/* 209 */         computeRectInt(src, dst);
/*     */         break;
/*     */       case 4:
/* 212 */         computeRectFloat(src, dst);
/*     */         break;
/*     */       case 5:
/* 215 */         computeRectDouble(src, dst);
/*     */         break;
/*     */     } 
/* 219 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 224 */     int dstWidth = dst.getWidth();
/* 225 */     int dstHeight = dst.getHeight();
/* 226 */     int dstBands = dst.getNumBands();
/* 228 */     int dstLineStride = dst.getScanlineStride();
/* 229 */     int dstPixelStride = dst.getPixelStride();
/* 230 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 231 */     byte[][] dstData = dst.getByteDataArrays();
/* 233 */     int srcLineStride = src.getScanlineStride();
/* 234 */     int srcPixelStride = src.getPixelStride();
/* 235 */     int[] srcBandOffsets = src.getBandOffsets();
/* 236 */     byte[][] srcData = src.getByteDataArrays();
/* 238 */     for (int b = 0; b < dstBands; b++) {
/* 239 */       byte[] d = dstData[b];
/* 240 */       byte[] s = srcData[b];
/* 242 */       int dstLineOffset = dstBandOffsets[b];
/* 243 */       int srcLineOffset = srcBandOffsets[b];
/* 245 */       for (int h = 0; h < dstHeight; h++) {
/* 246 */         int dstPixelOffset = dstLineOffset;
/* 247 */         int srcPixelOffset = srcLineOffset;
/* 249 */         dstLineOffset += dstLineStride;
/* 250 */         srcLineOffset += srcLineStride;
/* 252 */         for (int w = 0; w < dstWidth; w++) {
/* 253 */           d[dstPixelOffset] = s[srcPixelOffset];
/* 255 */           dstPixelOffset += dstPixelStride;
/* 256 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 264 */     int dstWidth = dst.getWidth();
/* 265 */     int dstHeight = dst.getHeight();
/* 266 */     int dstBands = dst.getNumBands();
/* 268 */     int dstLineStride = dst.getScanlineStride();
/* 269 */     int dstPixelStride = dst.getPixelStride();
/* 270 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 271 */     short[][] dstData = dst.getShortDataArrays();
/* 273 */     int srcLineStride = src.getScanlineStride();
/* 274 */     int srcPixelStride = src.getPixelStride();
/* 275 */     int[] srcBandOffsets = src.getBandOffsets();
/* 276 */     short[][] srcData = src.getShortDataArrays();
/* 278 */     for (int b = 0; b < dstBands; b++) {
/* 279 */       short[] d = dstData[b];
/* 280 */       short[] s = srcData[b];
/* 282 */       int dstLineOffset = dstBandOffsets[b];
/* 283 */       int srcLineOffset = srcBandOffsets[b];
/* 285 */       for (int h = 0; h < dstHeight; h++) {
/* 286 */         int dstPixelOffset = dstLineOffset;
/* 287 */         int srcPixelOffset = srcLineOffset;
/* 289 */         dstLineOffset += dstLineStride;
/* 290 */         srcLineOffset += srcLineStride;
/* 292 */         for (int w = 0; w < dstWidth; w++) {
/* 293 */           d[dstPixelOffset] = s[srcPixelOffset];
/* 295 */           dstPixelOffset += dstPixelStride;
/* 296 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 304 */     int dstWidth = dst.getWidth();
/* 305 */     int dstHeight = dst.getHeight();
/* 306 */     int dstBands = dst.getNumBands();
/* 308 */     int dstLineStride = dst.getScanlineStride();
/* 309 */     int dstPixelStride = dst.getPixelStride();
/* 310 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 311 */     int[][] dstData = dst.getIntDataArrays();
/* 313 */     int srcLineStride = src.getScanlineStride();
/* 314 */     int srcPixelStride = src.getPixelStride();
/* 315 */     int[] srcBandOffsets = src.getBandOffsets();
/* 316 */     int[][] srcData = src.getIntDataArrays();
/* 318 */     for (int b = 0; b < dstBands; b++) {
/* 319 */       int[] d = dstData[b];
/* 320 */       int[] s = srcData[b];
/* 322 */       int dstLineOffset = dstBandOffsets[b];
/* 323 */       int srcLineOffset = srcBandOffsets[b];
/* 325 */       for (int h = 0; h < dstHeight; h++) {
/* 326 */         int dstPixelOffset = dstLineOffset;
/* 327 */         int srcPixelOffset = srcLineOffset;
/* 329 */         dstLineOffset += dstLineStride;
/* 330 */         srcLineOffset += srcLineStride;
/* 332 */         for (int w = 0; w < dstWidth; w++) {
/* 333 */           d[dstPixelOffset] = s[srcPixelOffset];
/* 335 */           dstPixelOffset += dstPixelStride;
/* 336 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 344 */     int dstWidth = dst.getWidth();
/* 345 */     int dstHeight = dst.getHeight();
/* 346 */     int dstBands = dst.getNumBands();
/* 348 */     int dstLineStride = dst.getScanlineStride();
/* 349 */     int dstPixelStride = dst.getPixelStride();
/* 350 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 351 */     float[][] dstData = dst.getFloatDataArrays();
/* 353 */     int srcLineStride = src.getScanlineStride();
/* 354 */     int srcPixelStride = src.getPixelStride();
/* 355 */     int[] srcBandOffsets = src.getBandOffsets();
/* 356 */     float[][] srcData = src.getFloatDataArrays();
/* 358 */     for (int b = 0; b < dstBands; b++) {
/* 359 */       float[] d = dstData[b];
/* 360 */       float[] s = srcData[b];
/* 362 */       int dstLineOffset = dstBandOffsets[b];
/* 363 */       int srcLineOffset = srcBandOffsets[b];
/* 365 */       for (int h = 0; h < dstHeight; h++) {
/* 366 */         int dstPixelOffset = dstLineOffset;
/* 367 */         int srcPixelOffset = srcLineOffset;
/* 369 */         dstLineOffset += dstLineStride;
/* 370 */         srcLineOffset += srcLineStride;
/* 372 */         for (int w = 0; w < dstWidth; w++) {
/* 373 */           d[dstPixelOffset] = s[srcPixelOffset];
/* 375 */           dstPixelOffset += dstPixelStride;
/* 376 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 384 */     int dstWidth = dst.getWidth();
/* 385 */     int dstHeight = dst.getHeight();
/* 386 */     int dstBands = dst.getNumBands();
/* 388 */     int dstLineStride = dst.getScanlineStride();
/* 389 */     int dstPixelStride = dst.getPixelStride();
/* 390 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 391 */     double[][] dstData = dst.getDoubleDataArrays();
/* 393 */     int srcLineStride = src.getScanlineStride();
/* 394 */     int srcPixelStride = src.getPixelStride();
/* 395 */     int[] srcBandOffsets = src.getBandOffsets();
/* 396 */     double[][] srcData = src.getDoubleDataArrays();
/* 398 */     for (int b = 0; b < dstBands; b++) {
/* 399 */       double[] d = dstData[b];
/* 400 */       double[] s = srcData[b];
/* 402 */       int dstLineOffset = dstBandOffsets[b];
/* 403 */       int srcLineOffset = srcBandOffsets[b];
/* 405 */       for (int h = 0; h < dstHeight; h++) {
/* 406 */         int dstPixelOffset = dstLineOffset;
/* 407 */         int srcPixelOffset = srcLineOffset;
/* 409 */         dstLineOffset += dstLineStride;
/* 410 */         srcLineOffset += srcLineStride;
/* 412 */         for (int w = 0; w < dstWidth; w++) {
/* 413 */           d[dstPixelOffset] = s[srcPixelOffset];
/* 415 */           dstPixelOffset += dstPixelStride;
/* 416 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\OverlayOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */