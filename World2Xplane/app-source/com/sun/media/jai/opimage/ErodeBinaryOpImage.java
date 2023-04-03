/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.PackedImageData;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ 
/*     */ final class ErodeBinaryOpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel;
/*     */   
/*     */   private int kw;
/*     */   
/*     */   private int kh;
/*     */   
/*     */   private int kx;
/*     */   
/*     */   private int ky;
/*     */   
/*     */   private int[] kdataPack;
/*     */   
/*     */   private int kwPack;
/*     */   
/*     */   private int dwidth;
/*     */   
/*     */   private int dheight;
/*     */   
/*     */   private int dnumBands;
/*     */   
/*     */   private int bits;
/*     */   
/*     */   private int dstDBOffset;
/*     */   
/*     */   private int dstScanlineStride;
/*     */   
/*     */   private int dstScanlineStrideBits;
/*     */   
/*     */   private int dstMinX;
/*     */   
/*     */   private int dstMinY;
/*     */   
/*     */   private int dstTransX;
/*     */   
/*     */   private int dstTransY;
/*     */   
/*     */   private int dstDataBitOffset;
/*     */   
/*     */   private int srcDBOffset;
/*     */   
/*     */   private int srcScanlineStride;
/*     */   
/*     */   private int srcScanlineStrideBits;
/*     */   
/*     */   private int srcMinX;
/*     */   
/*     */   private int srcMinY;
/*     */   
/*     */   private int srcTransX;
/*     */   
/*     */   private int srcTransY;
/*     */   
/*     */   private int srcDataBitOffset;
/*     */   
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/* 177 */     if (configuration == null) {
/* 178 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/* 182 */       config = configuration;
/* 184 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/* 185 */         RenderingHints hints = (RenderingHints)configuration;
/* 186 */         config = (RenderingHints)hints.clone();
/* 187 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */       } 
/*     */     } 
/* 191 */     return config;
/*     */   }
/*     */   
/*     */   public ErodeBinaryOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 213 */     super(source, layout, configHelper(config), true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 223 */     this.kernel = kernel;
/* 224 */     this.kw = kernel.getWidth();
/* 225 */     this.kh = kernel.getHeight();
/* 226 */     this.kx = kernel.getXOrigin();
/* 227 */     this.ky = kernel.getYOrigin();
/* 229 */     this.kwPack = (this.kw + 31) / 32;
/* 230 */     this.kdataPack = packKernel(kernel);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 246 */     Raster source = sources[0];
/* 248 */     PixelAccessor pa = new PixelAccessor(source.getSampleModel(), null);
/* 249 */     PackedImageData srcIm = pa.getPackedPixels(source, source.getBounds(), false, false);
/* 252 */     pa = new PixelAccessor(dest.getSampleModel(), null);
/* 253 */     PackedImageData dstIm = pa.getPackedPixels(dest, destRect, true, false);
/* 257 */     int[] srcUK = new int[this.kwPack * this.kh];
/* 261 */     int dheight = destRect.height;
/* 262 */     int dwidth = destRect.width;
/* 264 */     int sOffset = srcIm.offset;
/* 265 */     int dOffset = dstIm.offset;
/* 266 */     for (int j = 0; j < dheight; j++) {
/* 272 */       for (int m = 0; m < srcUK.length; m++)
/* 273 */         srcUK[m] = 0; 
/*     */       int i;
/* 279 */       for (i = 0; i < this.kw - 1; i++) {
/* 280 */         bitShiftMatrixLeft(srcUK, this.kh, this.kwPack);
/* 281 */         int lastCol = this.kwPack - 1;
/* 282 */         int bitLoc = srcIm.bitOffset + i;
/* 283 */         int byteLoc = bitLoc >> 3;
/* 284 */         bitLoc = 7 - (bitLoc & 0x7);
/* 285 */         int k = 0, sOffsetB = sOffset;
/* 286 */         for (; k < this.kh; 
/* 287 */           k++, sOffsetB += srcIm.lineStride) {
/* 289 */           int selement = srcIm.data[sOffsetB + byteLoc];
/* 290 */           int val = selement >> bitLoc & 0x1;
/* 291 */           srcUK[lastCol] = srcUK[lastCol] | val;
/* 292 */           lastCol += this.kwPack;
/*     */         } 
/*     */       } 
/* 298 */       for (i = 0; i < dwidth; i++) {
/* 300 */         bitShiftMatrixLeft(srcUK, this.kh, this.kwPack);
/* 301 */         int lastCol = this.kwPack - 1;
/* 302 */         int bitLoc = srcIm.bitOffset + i + this.kw - 1;
/* 303 */         int byteLoc = bitLoc >> 3;
/* 304 */         bitLoc = 7 - (bitLoc & 0x7);
/* 305 */         int k = 0, sOffsetB = sOffset;
/* 306 */         for (; k < this.kh; 
/* 307 */           k++, sOffsetB += srcIm.lineStride) {
/* 309 */           int selement = srcIm.data[sOffsetB + byteLoc];
/* 310 */           int val = selement >> bitLoc & 0x1;
/* 311 */           srcUK[lastCol] = srcUK[lastCol] | val;
/* 312 */           lastCol += this.kwPack;
/*     */         } 
/* 316 */         int dBitLoc = dstIm.bitOffset + i;
/* 317 */         int dshift = 7 - (dBitLoc & 0x7);
/* 318 */         int dByteLoc = (dBitLoc >> 3) + dOffset;
/* 319 */         int delement = dstIm.data[dByteLoc];
/* 320 */         delement |= 1 << dshift;
/* 322 */         for (int n = 0; n < srcUK.length; n++) {
/* 323 */           if ((srcUK[n] & this.kdataPack[n]) != this.kdataPack[n]) {
/* 324 */             delement &= 1 << dshift ^ 0xFFFFFFFF;
/*     */             break;
/*     */           } 
/*     */         } 
/* 328 */         dstIm.data[dByteLoc] = (byte)delement;
/*     */       } 
/* 330 */       sOffset += srcIm.lineStride;
/* 331 */       dOffset += dstIm.lineStride;
/*     */     } 
/* 333 */     pa.setPackedPixels(dstIm);
/*     */   }
/*     */   
/*     */   private final int[] packKernel(KernelJAI kernel) {
/* 343 */     int kw = kernel.getWidth();
/* 344 */     int kh = kernel.getHeight();
/* 345 */     int kwPack = (31 + kw) / 32;
/* 346 */     int[] kerPacked = new int[kwPack * kh];
/* 347 */     float[] kdata = kernel.getKernelData();
/* 348 */     for (int j = 0; j < kw; j++) {
/* 349 */       int m = j;
/* 350 */       int lastCol = kwPack - 1;
/* 351 */       bitShiftMatrixLeft(kerPacked, kh, kwPack);
/* 352 */       for (int i = 0; i < kh; i++, lastCol += kwPack, m += kw) {
/* 353 */         if (kdata[m] > 0.9F)
/* 354 */           kerPacked[lastCol] = kerPacked[lastCol] | 0x1; 
/*     */       } 
/*     */     } 
/* 358 */     return kerPacked;
/*     */   }
/*     */   
/*     */   private static final void bitShiftMatrixLeft(int[] mat, int rows, int cols) {
/* 366 */     int m = 0;
/* 367 */     for (int i = 0; i < rows; i++) {
/* 368 */       for (int j = 0; j < cols - 1; j++) {
/* 369 */         mat[m] = mat[m] << 1 | mat[m + 1] >>> 31;
/* 370 */         m++;
/*     */       } 
/* 372 */       mat[m] = mat[m] << 1;
/* 373 */       m++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ErodeBinaryOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */