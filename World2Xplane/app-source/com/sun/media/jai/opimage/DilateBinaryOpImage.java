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
/*     */ final class DilateBinaryOpImage extends AreaOpImage {
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
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/* 158 */     if (configuration == null) {
/* 159 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/* 163 */       config = configuration;
/* 165 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/* 166 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/* 167 */         RenderingHints hints = (RenderingHints)configuration;
/* 168 */         config = (RenderingHints)hints.clone();
/*     */       } 
/*     */     } 
/* 172 */     return config;
/*     */   }
/*     */   
/*     */   public DilateBinaryOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 192 */     super(source, layout, configHelper(config), true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 202 */     this.kernel = kernel;
/* 203 */     this.kw = kernel.getWidth();
/* 204 */     this.kh = kernel.getHeight();
/* 205 */     this.kx = kernel.getXOrigin();
/* 206 */     this.ky = kernel.getYOrigin();
/* 208 */     this.kwPack = (this.kw + 31) / 32;
/* 209 */     this.kdataPack = packKernel(kernel);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 225 */     Raster source = sources[0];
/* 227 */     PixelAccessor pa = new PixelAccessor(source.getSampleModel(), null);
/* 228 */     PackedImageData srcIm = pa.getPackedPixels(source, source.getBounds(), false, false);
/* 231 */     pa = new PixelAccessor(dest.getSampleModel(), null);
/* 232 */     PackedImageData dstIm = pa.getPackedPixels(dest, destRect, true, false);
/* 236 */     int[] srcUK = new int[this.kwPack * this.kh];
/* 240 */     int dheight = destRect.height;
/* 241 */     int dwidth = destRect.width;
/* 243 */     int sOffset = srcIm.offset;
/* 244 */     int dOffset = dstIm.offset;
/* 245 */     for (int j = 0; j < dheight; j++) {
/* 251 */       for (int m = 0; m < srcUK.length; m++)
/* 252 */         srcUK[m] = 0; 
/*     */       int i;
/* 258 */       for (i = 0; i < this.kw - 1; i++) {
/* 259 */         bitShiftMatrixLeft(srcUK, this.kh, this.kwPack);
/* 260 */         int lastCol = this.kwPack - 1;
/* 261 */         int bitLoc = srcIm.bitOffset + i;
/* 262 */         int byteLoc = bitLoc >> 3;
/* 263 */         bitLoc = 7 - (bitLoc & 0x7);
/* 264 */         int k = 0, sOffsetB = sOffset;
/* 265 */         for (; k < this.kh; 
/* 266 */           k++, sOffsetB += srcIm.lineStride) {
/* 268 */           int selement = srcIm.data[sOffsetB + byteLoc];
/* 269 */           int val = selement >> bitLoc & 0x1;
/* 270 */           srcUK[lastCol] = srcUK[lastCol] | val;
/* 271 */           lastCol += this.kwPack;
/*     */         } 
/*     */       } 
/* 277 */       for (i = 0; i < dwidth; i++) {
/* 279 */         bitShiftMatrixLeft(srcUK, this.kh, this.kwPack);
/* 280 */         int lastCol = this.kwPack - 1;
/* 281 */         int bitLoc = srcIm.bitOffset + i + this.kw - 1;
/* 282 */         int byteLoc = bitLoc >> 3;
/* 283 */         bitLoc = 7 - (bitLoc & 0x7);
/* 284 */         int k = 0, sOffsetB = sOffset;
/* 285 */         for (; k < this.kh; 
/* 286 */           k++, sOffsetB += srcIm.lineStride) {
/* 288 */           int selement = srcIm.data[sOffsetB + byteLoc];
/* 289 */           int val = selement >> bitLoc & 0x1;
/* 290 */           srcUK[lastCol] = srcUK[lastCol] | val;
/* 291 */           lastCol += this.kwPack;
/*     */         } 
/* 295 */         for (k = 0; k < srcUK.length; k++) {
/* 296 */           if ((srcUK[k] & this.kdataPack[k]) != 0) {
/* 297 */             int dBitLoc = dstIm.bitOffset + i;
/* 298 */             int dshift = 7 - (dBitLoc & 0x7);
/* 299 */             int dByteLoc = (dBitLoc >> 3) + dOffset;
/* 300 */             int delement = dstIm.data[dByteLoc];
/* 301 */             delement |= 1 << dshift;
/* 302 */             dstIm.data[dByteLoc] = (byte)delement;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 308 */       sOffset += srcIm.lineStride;
/* 309 */       dOffset += dstIm.lineStride;
/*     */     } 
/* 311 */     pa.setPackedPixels(dstIm);
/*     */   }
/*     */   
/*     */   private final int[] packKernel(KernelJAI kernel) {
/* 321 */     int kw = kernel.getWidth();
/* 322 */     int kh = kernel.getHeight();
/* 323 */     int kwPack = (31 + kw) / 32;
/* 324 */     int[] kerPacked = new int[kwPack * kh];
/* 325 */     float[] kdata = kernel.getKernelData();
/* 326 */     for (int j = 0; j < kw; j++) {
/* 327 */       int m = j;
/* 328 */       int lastCol = kwPack - 1;
/* 329 */       bitShiftMatrixLeft(kerPacked, kh, kwPack);
/* 330 */       for (int i = 0; i < kh; i++, lastCol += kwPack, m += kw) {
/* 331 */         if (kdata[m] > 0.9F)
/* 332 */           kerPacked[lastCol] = kerPacked[lastCol] | 0x1; 
/*     */       } 
/*     */     } 
/* 336 */     return kerPacked;
/*     */   }
/*     */   
/*     */   private static final void bitShiftMatrixLeft(int[] mat, int rows, int cols) {
/* 344 */     int m = 0;
/* 345 */     for (int i = 0; i < rows; i++) {
/* 346 */       for (int j = 0; j < cols - 1; j++) {
/* 347 */         mat[m] = mat[m] << 1 | mat[m + 1] >>> 31;
/* 348 */         m++;
/*     */       } 
/* 350 */       mat[m] = mat[m] << 1;
/* 351 */       m++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DilateBinaryOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */