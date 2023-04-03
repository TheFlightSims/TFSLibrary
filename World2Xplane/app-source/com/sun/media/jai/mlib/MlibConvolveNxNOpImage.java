/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.KernelJAI;
/*     */ 
/*     */ final class MlibConvolveNxNOpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel;
/*     */   
/*     */   private int kw;
/*     */   
/*     */   private int kh;
/*     */   
/*     */   float[] kData;
/*     */   
/*     */   double[] doublekData;
/*     */   
/*     */   int[] intkData;
/*     */   
/*  80 */   int shift = -1;
/*     */   
/*     */   public MlibConvolveNxNOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 103 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 113 */     this.kernel = kernel;
/* 114 */     this.kw = kernel.getWidth();
/* 115 */     this.kh = kernel.getHeight();
/* 119 */     this.kData = kernel.getKernelData();
/* 121 */     int count = this.kw * this.kh;
/* 125 */     this.intkData = new int[count];
/* 126 */     this.doublekData = new double[count];
/* 127 */     for (int i = 0; i < count; i++)
/* 128 */       this.doublekData[i] = this.kData[i]; 
/*     */   }
/*     */   
/*     */   private synchronized void setShift(int formatTag) {
/* 133 */     if (this.shift == -1) {
/* 134 */       int mediaLibDataType = MediaLibAccessor.getMediaLibDataType(formatTag);
/* 136 */       this.shift = Image.ConvKernelConvert(this.intkData, this.doublekData, this.kw, this.kh, mediaLibDataType);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 156 */     Raster source = sources[0];
/* 157 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 159 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 161 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag, true);
/* 163 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag, true);
/* 165 */     int numBands = getSampleModel().getNumBands();
/* 168 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 169 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 170 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 171 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 176 */           if (this.shift == -1)
/* 177 */             setShift(formatTag); 
/* 180 */           if (this.kw == 2) {
/* 181 */             Image.Conv2x2(dstML[i], srcML[i], this.intkData, this.shift, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 185 */           if (this.kw == 3) {
/* 186 */             Image.Conv3x3(dstML[i], srcML[i], this.intkData, this.shift, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 190 */           if (this.kw == 4) {
/* 191 */             Image.Conv4x4(dstML[i], srcML[i], this.intkData, this.shift, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 195 */           if (this.kw == 5) {
/* 196 */             Image.Conv5x5(dstML[i], srcML[i], this.intkData, this.shift, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 200 */           if (this.kw == 7)
/* 201 */             Image.Conv7x7(dstML[i], srcML[i], this.intkData, this.shift, (1 << numBands) - 1, 0); 
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 209 */           if (this.kw == 2) {
/* 210 */             Image.Conv2x2_Fp(dstML[i], srcML[i], this.doublekData, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 214 */           if (this.kw == 3) {
/* 215 */             Image.Conv3x3_Fp(dstML[i], srcML[i], this.doublekData, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 219 */           if (this.kw == 4) {
/* 220 */             Image.Conv4x4_Fp(dstML[i], srcML[i], this.doublekData, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 224 */           if (this.kw == 5) {
/* 225 */             Image.Conv5x5_Fp(dstML[i], srcML[i], this.doublekData, (1 << numBands) - 1, 0);
/*     */             break;
/*     */           } 
/* 229 */           if (this.kw == 7)
/* 230 */             Image.Conv7x7_Fp(dstML[i], srcML[i], this.doublekData, (1 << numBands) - 1, 0); 
/*     */           break;
/*     */         default:
/* 237 */           className = getClass().getName();
/* 238 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 242 */     if (dstAccessor.isDataCopy())
/* 243 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibConvolveNxNOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */