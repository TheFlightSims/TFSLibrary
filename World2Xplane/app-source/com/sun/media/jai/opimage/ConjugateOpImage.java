/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class ConjugateOpImage extends PointOpImage {
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source) {
/*  53 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  57 */     SampleModel sm = il.getSampleModel(source);
/*  60 */     int dataType = sm.getTransferType();
/*  64 */     boolean createNewSampleModel = false;
/*  65 */     if (dataType == 0) {
/*  66 */       dataType = 2;
/*  67 */       createNewSampleModel = true;
/*  68 */     } else if (dataType == 1) {
/*  69 */       dataType = 3;
/*  70 */       createNewSampleModel = true;
/*     */     } 
/*  74 */     if (createNewSampleModel) {
/*  75 */       sm = RasterFactory.createComponentSampleModel(sm, dataType, sm.getWidth(), sm.getHeight(), sm.getNumBands());
/*  80 */       il.setSampleModel(sm);
/*  83 */       ColorModel cm = il.getColorModel(null);
/*  84 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/*  87 */         il.unsetValid(512); 
/*     */     } 
/*  91 */     return il;
/*     */   }
/*     */   
/*     */   public ConjugateOpImage(RenderedImage source, Map config, ImageLayout layout) {
/* 108 */     super(source, layoutHelper(layout, source), config, true);
/* 111 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 127 */     RasterFormatTag[] formatTags = getFormatTags();
/* 129 */     Raster source = sources[0];
/* 130 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 132 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 137 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 141 */     switch (dstAccessor.getDataType()) {
/*     */       case 2:
/* 143 */         shortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 146 */         intLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 4:
/* 149 */         floatLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 5:
/* 152 */         doubleLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       default:
/* 157 */         throw new RuntimeException(JaiI18N.getString("ConjugateOpImage0"));
/*     */     } 
/* 163 */     if (dstAccessor.isDataCopy()) {
/* 164 */       dstAccessor.clampDataArrays();
/* 165 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 170 */     int dwidth = dst.getWidth();
/* 171 */     int dheight = dst.getHeight();
/* 172 */     int dnumBands = dst.getNumBands();
/* 174 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 175 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 176 */     int dstPixelStride = dst.getPixelStride();
/* 177 */     int dstScanlineStride = dst.getScanlineStride();
/* 179 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 180 */     int[] srcBandOffsets = src.getBandOffsets();
/* 181 */     int srcPixelStride = src.getPixelStride();
/* 182 */     int srcScanlineStride = src.getScanlineStride();
/* 184 */     for (int k = 0; k < dnumBands; k++) {
/* 185 */       boolean isRealPart = (k % 2 == 0);
/* 186 */       short[] dstData = dstDataArrays[k];
/* 187 */       short[] srcData = srcDataArrays[k];
/* 188 */       int srcScanlineOffset = srcBandOffsets[k];
/* 189 */       int dstScanlineOffset = dstBandOffsets[k];
/* 190 */       if (isRealPart) {
/* 191 */         for (int j = 0; j < dheight; j++) {
/* 192 */           int srcPixelOffset = srcScanlineOffset;
/* 193 */           int dstPixelOffset = dstScanlineOffset;
/* 194 */           for (int i = 0; i < dwidth; i++) {
/* 195 */             dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 196 */             srcPixelOffset += srcPixelStride;
/* 197 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 199 */           srcScanlineOffset += srcScanlineStride;
/* 200 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } else {
/* 203 */         for (int j = 0; j < dheight; j++) {
/* 204 */           int srcPixelOffset = srcScanlineOffset;
/* 205 */           int dstPixelOffset = dstScanlineOffset;
/* 206 */           for (int i = 0; i < dwidth; i++) {
/* 207 */             dstData[dstPixelOffset] = ImageUtil.clampShort(-srcData[srcPixelOffset]);
/* 209 */             srcPixelOffset += srcPixelStride;
/* 210 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 212 */           srcScanlineOffset += srcScanlineStride;
/* 213 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 222 */     int dwidth = dst.getWidth();
/* 223 */     int dheight = dst.getHeight();
/* 224 */     int dnumBands = dst.getNumBands();
/* 226 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 227 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 228 */     int dstPixelStride = dst.getPixelStride();
/* 229 */     int dstScanlineStride = dst.getScanlineStride();
/* 231 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 232 */     int[] srcBandOffsets = src.getBandOffsets();
/* 233 */     int srcPixelStride = src.getPixelStride();
/* 234 */     int srcScanlineStride = src.getScanlineStride();
/* 236 */     for (int k = 0; k < dnumBands; k++) {
/* 237 */       boolean isRealPart = (k % 2 == 0);
/* 238 */       int[] dstData = dstDataArrays[k];
/* 239 */       int[] srcData = srcDataArrays[k];
/* 240 */       int srcScanlineOffset = srcBandOffsets[k];
/* 241 */       int dstScanlineOffset = dstBandOffsets[k];
/* 242 */       if (isRealPart) {
/* 243 */         for (int j = 0; j < dheight; j++) {
/* 244 */           int srcPixelOffset = srcScanlineOffset;
/* 245 */           int dstPixelOffset = dstScanlineOffset;
/* 246 */           for (int i = 0; i < dwidth; i++) {
/* 247 */             dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 248 */             srcPixelOffset += srcPixelStride;
/* 249 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 251 */           srcScanlineOffset += srcScanlineStride;
/* 252 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } else {
/* 255 */         for (int j = 0; j < dheight; j++) {
/* 256 */           int srcPixelOffset = srcScanlineOffset;
/* 257 */           int dstPixelOffset = dstScanlineOffset;
/* 258 */           for (int i = 0; i < dwidth; i++) {
/* 259 */             dstData[dstPixelOffset] = -srcData[srcPixelOffset];
/* 260 */             srcPixelOffset += srcPixelStride;
/* 261 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 263 */           srcScanlineOffset += srcScanlineStride;
/* 264 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, RasterAccessor dst) {
/* 271 */     int dwidth = dst.getWidth();
/* 272 */     int dheight = dst.getHeight();
/* 273 */     int dnumBands = dst.getNumBands();
/* 275 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 276 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 277 */     int dstPixelStride = dst.getPixelStride();
/* 278 */     int dstScanlineStride = dst.getScanlineStride();
/* 280 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 281 */     int[] srcBandOffsets = src.getBandOffsets();
/* 282 */     int srcPixelStride = src.getPixelStride();
/* 283 */     int srcScanlineStride = src.getScanlineStride();
/* 285 */     for (int k = 0; k < dnumBands; k++) {
/* 286 */       boolean isRealPart = (k % 2 == 0);
/* 287 */       float[] dstData = dstDataArrays[k];
/* 288 */       float[] srcData = srcDataArrays[k];
/* 289 */       int srcScanlineOffset = srcBandOffsets[k];
/* 290 */       int dstScanlineOffset = dstBandOffsets[k];
/* 291 */       if (isRealPart) {
/* 292 */         for (int j = 0; j < dheight; j++) {
/* 293 */           int srcPixelOffset = srcScanlineOffset;
/* 294 */           int dstPixelOffset = dstScanlineOffset;
/* 295 */           for (int i = 0; i < dwidth; i++) {
/* 296 */             dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 297 */             srcPixelOffset += srcPixelStride;
/* 298 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 300 */           srcScanlineOffset += srcScanlineStride;
/* 301 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } else {
/* 304 */         for (int j = 0; j < dheight; j++) {
/* 305 */           int srcPixelOffset = srcScanlineOffset;
/* 306 */           int dstPixelOffset = dstScanlineOffset;
/* 307 */           for (int i = 0; i < dwidth; i++) {
/* 308 */             dstData[dstPixelOffset] = -srcData[srcPixelOffset];
/* 309 */             srcPixelOffset += srcPixelStride;
/* 310 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 312 */           srcScanlineOffset += srcScanlineStride;
/* 313 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, RasterAccessor dst) {
/* 320 */     int dwidth = dst.getWidth();
/* 321 */     int dheight = dst.getHeight();
/* 322 */     int dnumBands = dst.getNumBands();
/* 324 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 325 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 326 */     int dstPixelStride = dst.getPixelStride();
/* 327 */     int dstScanlineStride = dst.getScanlineStride();
/* 329 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 330 */     int[] srcBandOffsets = src.getBandOffsets();
/* 331 */     int srcPixelStride = src.getPixelStride();
/* 332 */     int srcScanlineStride = src.getScanlineStride();
/* 334 */     for (int k = 0; k < dnumBands; k++) {
/* 335 */       boolean isRealPart = (k % 2 == 0);
/* 336 */       double[] dstData = dstDataArrays[k];
/* 337 */       double[] srcData = srcDataArrays[k];
/* 338 */       int srcScanlineOffset = srcBandOffsets[k];
/* 339 */       int dstScanlineOffset = dstBandOffsets[k];
/* 340 */       if (isRealPart) {
/* 341 */         for (int j = 0; j < dheight; j++) {
/* 342 */           int srcPixelOffset = srcScanlineOffset;
/* 343 */           int dstPixelOffset = dstScanlineOffset;
/* 344 */           for (int i = 0; i < dwidth; i++) {
/* 345 */             dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 346 */             srcPixelOffset += srcPixelStride;
/* 347 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 349 */           srcScanlineOffset += srcScanlineStride;
/* 350 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } else {
/* 353 */         for (int j = 0; j < dheight; j++) {
/* 354 */           int srcPixelOffset = srcScanlineOffset;
/* 355 */           int dstPixelOffset = dstScanlineOffset;
/* 356 */           for (int i = 0; i < dwidth; i++) {
/* 357 */             dstData[dstPixelOffset] = -srcData[srcPixelOffset];
/* 358 */             srcPixelOffset += srcPixelStride;
/* 359 */             dstPixelOffset += dstPixelStride;
/*     */           } 
/* 361 */           srcScanlineOffset += srcScanlineStride;
/* 362 */           dstScanlineOffset += dstScanlineStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ConjugateOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */