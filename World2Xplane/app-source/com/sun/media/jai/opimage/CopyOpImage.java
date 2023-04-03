/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ public final class CopyOpImage extends PointOpImage {
/*     */   public CopyOpImage(RenderedImage source, Map config, ImageLayout layout) {
/*  47 */     super(source, layout, config, true);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  63 */     RasterFormatTag[] formatTags = getFormatTags();
/*  65 */     Raster source = sources[0];
/*  66 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  68 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/*  73 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  77 */     if (dstAccessor.isBinary()) {
/*  78 */       byte[] srcBits = srcAccessor.getBinaryDataArray();
/*  79 */       byte[] dstBits = dstAccessor.getBinaryDataArray();
/*  81 */       System.arraycopy(srcBits, 0, dstBits, 0, dstBits.length);
/*  83 */       dstAccessor.copyBinaryDataToRaster();
/*     */     } else {
/*     */       String className;
/*  85 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*  87 */           byteLoop(srcAccessor, dstAccessor);
/*     */           break;
/*     */         case 1:
/*     */         case 2:
/*  91 */           shortLoop(srcAccessor, dstAccessor);
/*     */           break;
/*     */         case 3:
/*  94 */           intLoop(srcAccessor, dstAccessor);
/*     */           break;
/*     */         case 4:
/*  97 */           floatLoop(srcAccessor, dstAccessor);
/*     */           break;
/*     */         case 5:
/* 100 */           doubleLoop(srcAccessor, dstAccessor);
/*     */           break;
/*     */         default:
/* 103 */           className = getClass().getName();
/* 104 */           throw new RuntimeException(JaiI18N.getString("Convolve3x3OpImage1"));
/*     */       } 
/* 110 */       if (dstAccessor.isDataCopy()) {
/* 111 */         dstAccessor.clampDataArrays();
/* 112 */         dstAccessor.copyDataToRaster();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, RasterAccessor dst) {
/* 118 */     int dwidth = dst.getWidth();
/* 119 */     int dheight = dst.getHeight();
/* 120 */     int dnumBands = dst.getNumBands();
/* 122 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 123 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 124 */     int dstPixelStride = dst.getPixelStride();
/* 125 */     int dstScanlineStride = dst.getScanlineStride();
/* 127 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 128 */     int[] srcBandOffsets = src.getBandOffsets();
/* 129 */     int srcPixelStride = src.getPixelStride();
/* 130 */     int srcScanlineStride = src.getScanlineStride();
/* 132 */     for (int k = 0; k < dnumBands; k++) {
/* 133 */       byte[] dstData = dstDataArrays[k];
/* 134 */       byte[] srcData = srcDataArrays[k];
/* 135 */       int srcScanlineOffset = srcBandOffsets[k];
/* 136 */       int dstScanlineOffset = dstBandOffsets[k];
/* 137 */       for (int j = 0; j < dheight; j++) {
/* 138 */         int srcPixelOffset = srcScanlineOffset;
/* 139 */         int dstPixelOffset = dstScanlineOffset;
/* 140 */         for (int i = 0; i < dwidth; i++) {
/* 141 */           dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 142 */           srcPixelOffset += srcPixelStride;
/* 143 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 145 */         srcScanlineOffset += srcScanlineStride;
/* 146 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 152 */     int dwidth = dst.getWidth();
/* 153 */     int dheight = dst.getHeight();
/* 154 */     int dnumBands = dst.getNumBands();
/* 156 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 157 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 158 */     int dstPixelStride = dst.getPixelStride();
/* 159 */     int dstScanlineStride = dst.getScanlineStride();
/* 161 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 162 */     int[] srcBandOffsets = src.getBandOffsets();
/* 163 */     int srcPixelStride = src.getPixelStride();
/* 164 */     int srcScanlineStride = src.getScanlineStride();
/* 166 */     for (int k = 0; k < dnumBands; k++) {
/* 167 */       short[] dstData = dstDataArrays[k];
/* 168 */       short[] srcData = srcDataArrays[k];
/* 169 */       int srcScanlineOffset = srcBandOffsets[k];
/* 170 */       int dstScanlineOffset = dstBandOffsets[k];
/* 171 */       for (int j = 0; j < dheight; j++) {
/* 172 */         int srcPixelOffset = srcScanlineOffset;
/* 173 */         int dstPixelOffset = dstScanlineOffset;
/* 174 */         for (int i = 0; i < dwidth; i++) {
/* 175 */           dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 176 */           srcPixelOffset += srcPixelStride;
/* 177 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 179 */         srcScanlineOffset += srcScanlineStride;
/* 180 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 188 */     int dwidth = dst.getWidth();
/* 189 */     int dheight = dst.getHeight();
/* 190 */     int dnumBands = dst.getNumBands();
/* 192 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 193 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 194 */     int dstPixelStride = dst.getPixelStride();
/* 195 */     int dstScanlineStride = dst.getScanlineStride();
/* 197 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 198 */     int[] srcBandOffsets = src.getBandOffsets();
/* 199 */     int srcPixelStride = src.getPixelStride();
/* 200 */     int srcScanlineStride = src.getScanlineStride();
/* 202 */     for (int k = 0; k < dnumBands; k++) {
/* 203 */       int[] dstData = dstDataArrays[k];
/* 204 */       int[] srcData = srcDataArrays[k];
/* 205 */       int srcScanlineOffset = srcBandOffsets[k];
/* 206 */       int dstScanlineOffset = dstBandOffsets[k];
/* 207 */       for (int j = 0; j < dheight; j++) {
/* 208 */         int srcPixelOffset = srcScanlineOffset;
/* 209 */         int dstPixelOffset = dstScanlineOffset;
/* 210 */         for (int i = 0; i < dwidth; i++) {
/* 211 */           dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 212 */           srcPixelOffset += srcPixelStride;
/* 213 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 215 */         srcScanlineOffset += srcScanlineStride;
/* 216 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, RasterAccessor dst) {
/* 222 */     int dwidth = dst.getWidth();
/* 223 */     int dheight = dst.getHeight();
/* 224 */     int dnumBands = dst.getNumBands();
/* 226 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 227 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 228 */     int dstPixelStride = dst.getPixelStride();
/* 229 */     int dstScanlineStride = dst.getScanlineStride();
/* 231 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 232 */     int[] srcBandOffsets = src.getBandOffsets();
/* 233 */     int srcPixelStride = src.getPixelStride();
/* 234 */     int srcScanlineStride = src.getScanlineStride();
/* 236 */     for (int k = 0; k < dnumBands; k++) {
/* 237 */       float[] dstData = dstDataArrays[k];
/* 238 */       float[] srcData = srcDataArrays[k];
/* 239 */       int srcScanlineOffset = srcBandOffsets[k];
/* 240 */       int dstScanlineOffset = dstBandOffsets[k];
/* 241 */       for (int j = 0; j < dheight; j++) {
/* 242 */         int srcPixelOffset = srcScanlineOffset;
/* 243 */         int dstPixelOffset = dstScanlineOffset;
/* 244 */         for (int i = 0; i < dwidth; i++) {
/* 245 */           dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 246 */           srcPixelOffset += srcPixelStride;
/* 247 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 249 */         srcScanlineOffset += srcScanlineStride;
/* 250 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, RasterAccessor dst) {
/* 256 */     int dwidth = dst.getWidth();
/* 257 */     int dheight = dst.getHeight();
/* 258 */     int dnumBands = dst.getNumBands();
/* 260 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 261 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 262 */     int dstPixelStride = dst.getPixelStride();
/* 263 */     int dstScanlineStride = dst.getScanlineStride();
/* 265 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 266 */     int[] srcBandOffsets = src.getBandOffsets();
/* 267 */     int srcPixelStride = src.getPixelStride();
/* 268 */     int srcScanlineStride = src.getScanlineStride();
/* 270 */     for (int k = 0; k < dnumBands; k++) {
/* 271 */       double[] dstData = dstDataArrays[k];
/* 272 */       double[] srcData = srcDataArrays[k];
/* 273 */       int srcScanlineOffset = srcBandOffsets[k];
/* 274 */       int dstScanlineOffset = dstBandOffsets[k];
/* 275 */       for (int j = 0; j < dheight; j++) {
/* 276 */         int srcPixelOffset = srcScanlineOffset;
/* 277 */         int dstPixelOffset = dstScanlineOffset;
/* 278 */         for (int i = 0; i < dwidth; i++) {
/* 279 */           dstData[dstPixelOffset] = srcData[srcPixelOffset];
/* 280 */           srcPixelOffset += srcPixelStride;
/* 281 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 283 */         srcScanlineOffset += srcScanlineStride;
/* 284 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\CopyOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */