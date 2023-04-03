/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MaxFilterDescriptor;
/*     */ 
/*     */ final class MaxFilterSquareOpImage extends MaxFilterOpImage {
/*     */   public MaxFilterSquareOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  54 */     super(source, extender, config, layout, MaxFilterDescriptor.MAX_MASK_SQUARE, maskSize);
/*     */   }
/*     */   
/*     */   protected void byteLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/*  65 */     int dwidth = dst.getWidth();
/*  66 */     int dheight = dst.getHeight();
/*  67 */     int dnumBands = dst.getNumBands();
/*  69 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  70 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  71 */     int dstPixelStride = dst.getPixelStride();
/*  72 */     int dstScanlineStride = dst.getScanlineStride();
/*  74 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  75 */     int[] srcBandOffsets = src.getBandOffsets();
/*  76 */     int srcPixelStride = src.getPixelStride();
/*  77 */     int srcScanlineStride = src.getScanlineStride();
/*  80 */     int wp = filterSize;
/*  82 */     for (int k = 0; k < dnumBands; k++) {
/*  83 */       byte[] dstData = dstDataArrays[k];
/*  84 */       byte[] srcData = srcDataArrays[k];
/*  85 */       int srcScanlineOffset = srcBandOffsets[k];
/*  86 */       int dstScanlineOffset = dstBandOffsets[k];
/*  87 */       for (int j = 0; j < dheight; j++) {
/*  88 */         int srcPixelOffset = srcScanlineOffset;
/*  89 */         int dstPixelOffset = dstScanlineOffset;
/*  91 */         for (int i = 0; i < dwidth; i++) {
/*  92 */           int imageVerticalOffset = srcPixelOffset;
/*  94 */           int maxval = Integer.MIN_VALUE;
/*  95 */           for (int u = 0; u < wp; u++) {
/*  96 */             int imageOffset = imageVerticalOffset;
/*  97 */             for (int v = 0; v < wp; v++) {
/*  98 */               int val = srcData[imageOffset] & 0xFF;
/*  99 */               imageOffset += srcPixelStride;
/* 100 */               maxval = (val > maxval) ? val : maxval;
/*     */             } 
/* 102 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 104 */           dstData[dstPixelOffset] = (byte)maxval;
/* 105 */           srcPixelOffset += srcPixelStride;
/* 106 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 108 */         srcScanlineOffset += srcScanlineStride;
/* 109 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 117 */     int dwidth = dst.getWidth();
/* 118 */     int dheight = dst.getHeight();
/* 119 */     int dnumBands = dst.getNumBands();
/* 121 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 122 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 123 */     int dstPixelStride = dst.getPixelStride();
/* 124 */     int dstScanlineStride = dst.getScanlineStride();
/* 126 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 127 */     int[] srcBandOffsets = src.getBandOffsets();
/* 128 */     int srcPixelStride = src.getPixelStride();
/* 129 */     int srcScanlineStride = src.getScanlineStride();
/* 132 */     int wp = filterSize;
/* 134 */     for (int k = 0; k < dnumBands; k++) {
/* 135 */       short[] dstData = dstDataArrays[k];
/* 136 */       short[] srcData = srcDataArrays[k];
/* 137 */       int srcScanlineOffset = srcBandOffsets[k];
/* 138 */       int dstScanlineOffset = dstBandOffsets[k];
/* 139 */       for (int j = 0; j < dheight; j++) {
/* 140 */         int srcPixelOffset = srcScanlineOffset;
/* 141 */         int dstPixelOffset = dstScanlineOffset;
/* 143 */         for (int i = 0; i < dwidth; i++) {
/* 144 */           int imageVerticalOffset = srcPixelOffset;
/* 146 */           int maxval = Integer.MIN_VALUE;
/* 147 */           for (int u = 0; u < wp; u++) {
/* 148 */             int imageOffset = imageVerticalOffset;
/* 149 */             for (int v = 0; v < wp; v++) {
/* 150 */               int val = srcData[imageOffset];
/* 151 */               imageOffset += srcPixelStride;
/* 152 */               maxval = (val > maxval) ? val : maxval;
/*     */             } 
/* 154 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 156 */           dstData[dstPixelOffset] = (short)maxval;
/* 157 */           srcPixelOffset += srcPixelStride;
/* 158 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 160 */         srcScanlineOffset += srcScanlineStride;
/* 161 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 169 */     int dwidth = dst.getWidth();
/* 170 */     int dheight = dst.getHeight();
/* 171 */     int dnumBands = dst.getNumBands();
/* 173 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 174 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 175 */     int dstPixelStride = dst.getPixelStride();
/* 176 */     int dstScanlineStride = dst.getScanlineStride();
/* 178 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 179 */     int[] srcBandOffsets = src.getBandOffsets();
/* 180 */     int srcPixelStride = src.getPixelStride();
/* 181 */     int srcScanlineStride = src.getScanlineStride();
/* 184 */     int wp = filterSize;
/* 186 */     for (int k = 0; k < dnumBands; k++) {
/* 187 */       short[] dstData = dstDataArrays[k];
/* 188 */       short[] srcData = srcDataArrays[k];
/* 189 */       int srcScanlineOffset = srcBandOffsets[k];
/* 190 */       int dstScanlineOffset = dstBandOffsets[k];
/* 191 */       for (int j = 0; j < dheight; j++) {
/* 192 */         int srcPixelOffset = srcScanlineOffset;
/* 193 */         int dstPixelOffset = dstScanlineOffset;
/* 195 */         for (int i = 0; i < dwidth; i++) {
/* 196 */           int imageVerticalOffset = srcPixelOffset;
/* 198 */           int maxval = Integer.MIN_VALUE;
/* 199 */           for (int u = 0; u < wp; u++) {
/* 200 */             int imageOffset = imageVerticalOffset;
/* 201 */             for (int v = 0; v < wp; v++) {
/* 202 */               int val = srcData[imageOffset] & 0xFFFF;
/* 203 */               imageOffset += srcPixelStride;
/* 204 */               maxval = (val > maxval) ? val : maxval;
/*     */             } 
/* 206 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 208 */           dstData[dstPixelOffset] = (short)maxval;
/* 209 */           srcPixelOffset += srcPixelStride;
/* 210 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 212 */         srcScanlineOffset += srcScanlineStride;
/* 213 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 221 */     int dwidth = dst.getWidth();
/* 222 */     int dheight = dst.getHeight();
/* 223 */     int dnumBands = dst.getNumBands();
/* 225 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 226 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 227 */     int dstPixelStride = dst.getPixelStride();
/* 228 */     int dstScanlineStride = dst.getScanlineStride();
/* 230 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 231 */     int[] srcBandOffsets = src.getBandOffsets();
/* 232 */     int srcPixelStride = src.getPixelStride();
/* 233 */     int srcScanlineStride = src.getScanlineStride();
/* 236 */     int wp = filterSize;
/* 238 */     for (int k = 0; k < dnumBands; k++) {
/* 239 */       int[] dstData = dstDataArrays[k];
/* 240 */       int[] srcData = srcDataArrays[k];
/* 241 */       int srcScanlineOffset = srcBandOffsets[k];
/* 242 */       int dstScanlineOffset = dstBandOffsets[k];
/* 243 */       for (int j = 0; j < dheight; j++) {
/* 244 */         int srcPixelOffset = srcScanlineOffset;
/* 245 */         int dstPixelOffset = dstScanlineOffset;
/* 247 */         for (int i = 0; i < dwidth; i++) {
/* 248 */           int imageVerticalOffset = srcPixelOffset;
/* 250 */           int maxval = Integer.MIN_VALUE;
/* 251 */           for (int u = 0; u < wp; u++) {
/* 252 */             int imageOffset = imageVerticalOffset;
/* 253 */             for (int v = 0; v < wp; v++) {
/* 254 */               int val = srcData[imageOffset];
/* 255 */               imageOffset += srcPixelStride;
/* 256 */               maxval = (val > maxval) ? val : maxval;
/*     */             } 
/* 258 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 260 */           dstData[dstPixelOffset] = maxval;
/* 261 */           srcPixelOffset += srcPixelStride;
/* 262 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 264 */         srcScanlineOffset += srcScanlineStride;
/* 265 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 273 */     int dwidth = dst.getWidth();
/* 274 */     int dheight = dst.getHeight();
/* 275 */     int dnumBands = dst.getNumBands();
/* 277 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 278 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 279 */     int dstPixelStride = dst.getPixelStride();
/* 280 */     int dstScanlineStride = dst.getScanlineStride();
/* 282 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 283 */     int[] srcBandOffsets = src.getBandOffsets();
/* 284 */     int srcPixelStride = src.getPixelStride();
/* 285 */     int srcScanlineStride = src.getScanlineStride();
/* 288 */     int wp = filterSize;
/* 290 */     for (int k = 0; k < dnumBands; k++) {
/* 291 */       float[] dstData = dstDataArrays[k];
/* 292 */       float[] srcData = srcDataArrays[k];
/* 293 */       int srcScanlineOffset = srcBandOffsets[k];
/* 294 */       int dstScanlineOffset = dstBandOffsets[k];
/* 295 */       for (int j = 0; j < dheight; j++) {
/* 296 */         int srcPixelOffset = srcScanlineOffset;
/* 297 */         int dstPixelOffset = dstScanlineOffset;
/* 299 */         for (int i = 0; i < dwidth; i++) {
/* 300 */           int imageVerticalOffset = srcPixelOffset;
/* 302 */           float maxval = -3.4028235E38F;
/* 303 */           for (int u = 0; u < wp; u++) {
/* 304 */             int imageOffset = imageVerticalOffset;
/* 305 */             for (int v = 0; v < wp; v++) {
/* 306 */               float val = srcData[imageOffset];
/* 307 */               imageOffset += srcPixelStride;
/* 308 */               maxval = (val > maxval) ? val : maxval;
/*     */             } 
/* 310 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 312 */           dstData[dstPixelOffset] = maxval;
/* 313 */           srcPixelOffset += srcPixelStride;
/* 314 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 316 */         srcScanlineOffset += srcScanlineStride;
/* 317 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 325 */     int dwidth = dst.getWidth();
/* 326 */     int dheight = dst.getHeight();
/* 327 */     int dnumBands = dst.getNumBands();
/* 329 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 330 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 331 */     int dstPixelStride = dst.getPixelStride();
/* 332 */     int dstScanlineStride = dst.getScanlineStride();
/* 334 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 335 */     int[] srcBandOffsets = src.getBandOffsets();
/* 336 */     int srcPixelStride = src.getPixelStride();
/* 337 */     int srcScanlineStride = src.getScanlineStride();
/* 340 */     int wp = filterSize;
/* 342 */     for (int k = 0; k < dnumBands; k++) {
/* 343 */       double[] dstData = dstDataArrays[k];
/* 344 */       double[] srcData = srcDataArrays[k];
/* 345 */       int srcScanlineOffset = srcBandOffsets[k];
/* 346 */       int dstScanlineOffset = dstBandOffsets[k];
/* 347 */       for (int j = 0; j < dheight; j++) {
/* 348 */         int srcPixelOffset = srcScanlineOffset;
/* 349 */         int dstPixelOffset = dstScanlineOffset;
/* 351 */         for (int i = 0; i < dwidth; i++) {
/* 352 */           int imageVerticalOffset = srcPixelOffset;
/* 354 */           double maxval = -1.7976931348623157E308D;
/* 355 */           for (int u = 0; u < wp; u++) {
/* 356 */             int imageOffset = imageVerticalOffset;
/* 357 */             for (int v = 0; v < wp; v++) {
/* 358 */               double val = srcData[imageOffset];
/* 359 */               imageOffset += srcPixelStride;
/* 360 */               maxval = (val > maxval) ? val : maxval;
/*     */             } 
/* 362 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 364 */           dstData[dstPixelOffset] = maxval;
/* 365 */           srcPixelOffset += srcPixelStride;
/* 366 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 368 */         srcScanlineOffset += srcScanlineStride;
/* 369 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MaxFilterSquareOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */