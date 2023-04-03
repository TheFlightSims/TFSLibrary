/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class MaxOpImage extends PointOpImage {
/*  53 */   private static long negativeZeroFloatBits = Float.floatToIntBits(-0.0F);
/*     */   
/*  54 */   private static long negativeZeroDoubleBits = Double.doubleToLongBits(-0.0D);
/*     */   
/*  55 */   private static byte[] byteTable = null;
/*     */   
/*  56 */   private static SoftReference softRef = null;
/*     */   
/*     */   private synchronized void allocByteTable() {
/*  59 */     if (softRef == null || softRef.get() == null) {
/*  65 */       byteTable = new byte[65536];
/*  66 */       softRef = new SoftReference(byteTable);
/*  69 */       int idx = 0;
/*  70 */       for (int i1 = 0; i1 < 256; i1++) {
/*  71 */         int base = i1 << 8;
/*     */         int i2;
/*  72 */         for (i2 = 0; i2 < i1; i2++)
/*  73 */           byteTable[base + i2] = (byte)i1; 
/*  75 */         for (i2 = i1; i2 < 256; i2++)
/*  76 */           byteTable[base + i2] = (byte)i2; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public MaxOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  93 */     super(source1, source2, layout, config, true);
/*  95 */     if (this.sampleModel.getTransferType() == 0)
/*  96 */       allocByteTable(); 
/* 100 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 116 */     RasterFormatTag[] formatTags = getFormatTags();
/* 119 */     RasterAccessor s1 = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 122 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, formatTags[1], getSourceImage(1).getColorModel());
/* 125 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 128 */     switch (d.getDataType()) {
/*     */       case 0:
/* 130 */         computeRectByte(s1, s2, d);
/*     */         break;
/*     */       case 1:
/* 133 */         computeRectUShort(s1, s2, d);
/*     */         break;
/*     */       case 2:
/* 136 */         computeRectShort(s1, s2, d);
/*     */         break;
/*     */       case 3:
/* 139 */         computeRectInt(s1, s2, d);
/*     */         break;
/*     */       case 4:
/* 142 */         computeRectFloat(s1, s2, d);
/*     */         break;
/*     */       case 5:
/* 145 */         computeRectDouble(s1, s2, d);
/*     */         break;
/*     */     } 
/* 149 */     if (d.isDataCopy()) {
/* 150 */       d.clampDataArrays();
/* 151 */       d.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 158 */     int s1LineStride = src1.getScanlineStride();
/* 159 */     int s1PixelStride = src1.getPixelStride();
/* 160 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 161 */     byte[][] s1Data = src1.getByteDataArrays();
/* 163 */     int s2LineStride = src2.getScanlineStride();
/* 164 */     int s2PixelStride = src2.getPixelStride();
/* 165 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 166 */     byte[][] s2Data = src2.getByteDataArrays();
/* 168 */     int dwidth = dst.getWidth();
/* 169 */     int dheight = dst.getHeight();
/* 170 */     int bands = dst.getNumBands();
/* 171 */     int dLineStride = dst.getScanlineStride();
/* 172 */     int dPixelStride = dst.getPixelStride();
/* 173 */     int[] dBandOffsets = dst.getBandOffsets();
/* 174 */     byte[][] dData = dst.getByteDataArrays();
/* 176 */     for (int b = 0; b < bands; b++) {
/* 177 */       byte[] s1 = s1Data[b];
/* 178 */       byte[] s2 = s2Data[b];
/* 179 */       byte[] d = dData[b];
/* 181 */       int s1LineOffset = s1BandOffsets[b];
/* 182 */       int s2LineOffset = s2BandOffsets[b];
/* 183 */       int dLineOffset = dBandOffsets[b];
/* 185 */       for (int h = 0; h < dheight; h++) {
/* 186 */         int s1PixelOffset = s1LineOffset;
/* 187 */         int s2PixelOffset = s2LineOffset;
/* 188 */         int dPixelOffset = dLineOffset;
/* 190 */         s1LineOffset += s1LineStride;
/* 191 */         s2LineOffset += s2LineStride;
/* 192 */         dLineOffset += dLineStride;
/* 194 */         int dstEnd = dPixelOffset + dwidth * dPixelStride;
/* 195 */         while (dPixelOffset < dstEnd) {
/* 196 */           int i1 = s1[s1PixelOffset] & 0xFF;
/* 197 */           int i2 = s2[s2PixelOffset] & 0xFF;
/* 198 */           d[dPixelOffset] = byteTable[(i1 << 8) + i2];
/* 200 */           s1PixelOffset += s1PixelStride;
/* 201 */           s2PixelOffset += s2PixelStride;
/* 202 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 211 */     int s1LineStride = src1.getScanlineStride();
/* 212 */     int s1PixelStride = src1.getPixelStride();
/* 213 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 214 */     short[][] s1Data = src1.getShortDataArrays();
/* 216 */     int s2LineStride = src2.getScanlineStride();
/* 217 */     int s2PixelStride = src2.getPixelStride();
/* 218 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 219 */     short[][] s2Data = src2.getShortDataArrays();
/* 221 */     int dwidth = dst.getWidth();
/* 222 */     int dheight = dst.getHeight();
/* 223 */     int bands = dst.getNumBands();
/* 224 */     int dLineStride = dst.getScanlineStride();
/* 225 */     int dPixelStride = dst.getPixelStride();
/* 226 */     int[] dBandOffsets = dst.getBandOffsets();
/* 227 */     short[][] dData = dst.getShortDataArrays();
/* 229 */     for (int b = 0; b < bands; b++) {
/* 230 */       short[] s1 = s1Data[b];
/* 231 */       short[] s2 = s2Data[b];
/* 232 */       short[] d = dData[b];
/* 234 */       int s1LineOffset = s1BandOffsets[b];
/* 235 */       int s2LineOffset = s2BandOffsets[b];
/* 236 */       int dLineOffset = dBandOffsets[b];
/* 238 */       for (int h = 0; h < dheight; h++) {
/* 239 */         int s1PixelOffset = s1LineOffset;
/* 240 */         int s2PixelOffset = s2LineOffset;
/* 241 */         int dPixelOffset = dLineOffset;
/* 243 */         s1LineOffset += s1LineStride;
/* 244 */         s2LineOffset += s2LineStride;
/* 245 */         dLineOffset += dLineStride;
/* 247 */         for (int w = 0; w < dwidth; w++) {
/* 248 */           d[dPixelOffset] = maxUShort(s1[s1PixelOffset], s2[s2PixelOffset]);
/* 251 */           s1PixelOffset += s1PixelStride;
/* 252 */           s2PixelOffset += s2PixelStride;
/* 253 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 262 */     int s1LineStride = src1.getScanlineStride();
/* 263 */     int s1PixelStride = src1.getPixelStride();
/* 264 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 265 */     short[][] s1Data = src1.getShortDataArrays();
/* 267 */     int s2LineStride = src2.getScanlineStride();
/* 268 */     int s2PixelStride = src2.getPixelStride();
/* 269 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 270 */     short[][] s2Data = src2.getShortDataArrays();
/* 272 */     int dwidth = dst.getWidth();
/* 273 */     int dheight = dst.getHeight();
/* 274 */     int bands = dst.getNumBands();
/* 275 */     int dLineStride = dst.getScanlineStride();
/* 276 */     int dPixelStride = dst.getPixelStride();
/* 277 */     int[] dBandOffsets = dst.getBandOffsets();
/* 278 */     short[][] dData = dst.getShortDataArrays();
/* 280 */     for (int b = 0; b < bands; b++) {
/* 281 */       short[] s1 = s1Data[b];
/* 282 */       short[] s2 = s2Data[b];
/* 283 */       short[] d = dData[b];
/* 285 */       int s1LineOffset = s1BandOffsets[b];
/* 286 */       int s2LineOffset = s2BandOffsets[b];
/* 287 */       int dLineOffset = dBandOffsets[b];
/* 289 */       for (int h = 0; h < dheight; h++) {
/* 290 */         int s1PixelOffset = s1LineOffset;
/* 291 */         int s2PixelOffset = s2LineOffset;
/* 292 */         int dPixelOffset = dLineOffset;
/* 294 */         s1LineOffset += s1LineStride;
/* 295 */         s2LineOffset += s2LineStride;
/* 296 */         dLineOffset += dLineStride;
/* 298 */         for (int w = 0; w < dwidth; w++) {
/* 299 */           d[dPixelOffset] = maxShort(s1[s1PixelOffset], s2[s2PixelOffset]);
/* 302 */           s1PixelOffset += s1PixelStride;
/* 303 */           s2PixelOffset += s2PixelStride;
/* 304 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 313 */     int s1LineStride = src1.getScanlineStride();
/* 314 */     int s1PixelStride = src1.getPixelStride();
/* 315 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 316 */     int[][] s1Data = src1.getIntDataArrays();
/* 318 */     int s2LineStride = src2.getScanlineStride();
/* 319 */     int s2PixelStride = src2.getPixelStride();
/* 320 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 321 */     int[][] s2Data = src2.getIntDataArrays();
/* 323 */     int dwidth = dst.getWidth();
/* 324 */     int dheight = dst.getHeight();
/* 325 */     int bands = dst.getNumBands();
/* 326 */     int dLineStride = dst.getScanlineStride();
/* 327 */     int dPixelStride = dst.getPixelStride();
/* 328 */     int[] dBandOffsets = dst.getBandOffsets();
/* 329 */     int[][] dData = dst.getIntDataArrays();
/* 331 */     for (int b = 0; b < bands; b++) {
/* 332 */       int[] s1 = s1Data[b];
/* 333 */       int[] s2 = s2Data[b];
/* 334 */       int[] d = dData[b];
/* 336 */       int s1LineOffset = s1BandOffsets[b];
/* 337 */       int s2LineOffset = s2BandOffsets[b];
/* 338 */       int dLineOffset = dBandOffsets[b];
/* 340 */       for (int h = 0; h < dheight; h++) {
/* 341 */         int s1PixelOffset = s1LineOffset;
/* 342 */         int s2PixelOffset = s2LineOffset;
/* 343 */         int dPixelOffset = dLineOffset;
/* 345 */         s1LineOffset += s1LineStride;
/* 346 */         s2LineOffset += s2LineStride;
/* 347 */         dLineOffset += dLineStride;
/* 349 */         for (int w = 0; w < dwidth; w++) {
/* 350 */           d[dPixelOffset] = maxInt(s1[s1PixelOffset], s2[s2PixelOffset]);
/* 353 */           s1PixelOffset += s1PixelStride;
/* 354 */           s2PixelOffset += s2PixelStride;
/* 355 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 364 */     int s1LineStride = src1.getScanlineStride();
/* 365 */     int s1PixelStride = src1.getPixelStride();
/* 366 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 367 */     float[][] s1Data = src1.getFloatDataArrays();
/* 369 */     int s2LineStride = src2.getScanlineStride();
/* 370 */     int s2PixelStride = src2.getPixelStride();
/* 371 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 372 */     float[][] s2Data = src2.getFloatDataArrays();
/* 374 */     int dwidth = dst.getWidth();
/* 375 */     int dheight = dst.getHeight();
/* 376 */     int bands = dst.getNumBands();
/* 377 */     int dLineStride = dst.getScanlineStride();
/* 378 */     int dPixelStride = dst.getPixelStride();
/* 379 */     int[] dBandOffsets = dst.getBandOffsets();
/* 380 */     float[][] dData = dst.getFloatDataArrays();
/* 382 */     for (int b = 0; b < bands; b++) {
/* 383 */       float[] s1 = s1Data[b];
/* 384 */       float[] s2 = s2Data[b];
/* 385 */       float[] d = dData[b];
/* 387 */       int s1LineOffset = s1BandOffsets[b];
/* 388 */       int s2LineOffset = s2BandOffsets[b];
/* 389 */       int dLineOffset = dBandOffsets[b];
/* 391 */       for (int h = 0; h < dheight; h++) {
/* 392 */         int s1PixelOffset = s1LineOffset;
/* 393 */         int s2PixelOffset = s2LineOffset;
/* 394 */         int dPixelOffset = dLineOffset;
/* 396 */         s1LineOffset += s1LineStride;
/* 397 */         s2LineOffset += s2LineStride;
/* 398 */         dLineOffset += dLineStride;
/* 400 */         for (int w = 0; w < dwidth; w++) {
/* 401 */           d[dPixelOffset] = maxFloat(s1[s1PixelOffset], s2[s2PixelOffset]);
/* 404 */           s1PixelOffset += s1PixelStride;
/* 405 */           s2PixelOffset += s2PixelStride;
/* 406 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 415 */     int s1LineStride = src1.getScanlineStride();
/* 416 */     int s1PixelStride = src1.getPixelStride();
/* 417 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 418 */     double[][] s1Data = src1.getDoubleDataArrays();
/* 420 */     int s2LineStride = src2.getScanlineStride();
/* 421 */     int s2PixelStride = src2.getPixelStride();
/* 422 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 423 */     double[][] s2Data = src2.getDoubleDataArrays();
/* 425 */     int dwidth = dst.getWidth();
/* 426 */     int dheight = dst.getHeight();
/* 427 */     int bands = dst.getNumBands();
/* 428 */     int dLineStride = dst.getScanlineStride();
/* 429 */     int dPixelStride = dst.getPixelStride();
/* 430 */     int[] dBandOffsets = dst.getBandOffsets();
/* 431 */     double[][] dData = dst.getDoubleDataArrays();
/* 433 */     for (int b = 0; b < bands; b++) {
/* 434 */       double[] s1 = s1Data[b];
/* 435 */       double[] s2 = s2Data[b];
/* 436 */       double[] d = dData[b];
/* 438 */       int s1LineOffset = s1BandOffsets[b];
/* 439 */       int s2LineOffset = s2BandOffsets[b];
/* 440 */       int dLineOffset = dBandOffsets[b];
/* 442 */       for (int h = 0; h < dheight; h++) {
/* 443 */         int s1PixelOffset = s1LineOffset;
/* 444 */         int s2PixelOffset = s2LineOffset;
/* 445 */         int dPixelOffset = dLineOffset;
/* 447 */         s1LineOffset += s1LineStride;
/* 448 */         s2LineOffset += s2LineStride;
/* 449 */         dLineOffset += dLineStride;
/* 451 */         for (int w = 0; w < dwidth; w++) {
/* 452 */           d[dPixelOffset] = maxDouble(s1[s1PixelOffset], s2[s2PixelOffset]);
/* 455 */           s1PixelOffset += s1PixelStride;
/* 456 */           s2PixelOffset += s2PixelStride;
/* 457 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final short maxUShort(short a, short b) {
/* 464 */     return ((a & 0xFFFF) > (b & 0xFFFF)) ? a : b;
/*     */   }
/*     */   
/*     */   private final short maxShort(short a, short b) {
/* 468 */     return (a > b) ? a : b;
/*     */   }
/*     */   
/*     */   private final int maxInt(int a, int b) {
/* 472 */     return (a > b) ? a : b;
/*     */   }
/*     */   
/*     */   private final float maxFloat(float a, float b) {
/* 476 */     if (a != a)
/* 476 */       return a; 
/* 477 */     if (a == 0.0F && b == 0.0F && Float.floatToIntBits(a) == negativeZeroFloatBits)
/* 479 */       return b; 
/* 481 */     return (a >= b) ? a : b;
/*     */   }
/*     */   
/*     */   private final double maxDouble(double a, double b) {
/* 485 */     if (a != a)
/* 485 */       return a; 
/* 486 */     if (a == 0.0D && b == 0.0D && Double.doubleToLongBits(a) == negativeZeroDoubleBits)
/* 488 */       return b; 
/* 490 */     return (a >= b) ? a : b;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MaxOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */