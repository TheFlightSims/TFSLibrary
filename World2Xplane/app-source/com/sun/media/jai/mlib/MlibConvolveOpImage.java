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
/*     */ final class MlibConvolveOpImage extends AreaOpImage {
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
/*     */   float[] kData;
/*     */   
/*     */   double[] doublekData;
/*     */   
/*     */   int[] intkData;
/*     */   
/*  81 */   int shift = -1;
/*     */   
/*     */   public MlibConvolveOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 103 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 113 */     this.kernel = kernel;
/* 114 */     this.kw = kernel.getWidth();
/* 115 */     this.kh = kernel.getHeight();
/* 121 */     this.kx = this.kw / 2;
/* 122 */     this.ky = this.kh / 2;
/* 125 */     this.kData = kernel.getKernelData();
/* 127 */     int count = this.kw * this.kh;
/* 131 */     this.intkData = new int[count];
/* 132 */     this.doublekData = new double[count];
/* 133 */     for (int i = 0; i < count; i++)
/* 134 */       this.doublekData[i] = this.kData[i]; 
/*     */   }
/*     */   
/*     */   private synchronized void setShift(int formatTag) {
/* 139 */     if (this.shift == -1) {
/* 140 */       int mediaLibDataType = MediaLibAccessor.getMediaLibDataType(formatTag);
/* 142 */       this.shift = Image.ConvKernelConvert(this.intkData, this.doublekData, this.kw, this.kh, mediaLibDataType);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 162 */     Raster source = sources[0];
/* 163 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 165 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 167 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 169 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 171 */     int numBands = getSampleModel().getNumBands();
/* 174 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 175 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 176 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 177 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 182 */           if (this.shift == -1)
/* 183 */             setShift(formatTag); 
/* 185 */           Image.ConvMxN(dstML[i], srcML[i], this.intkData, this.kw, this.kh, this.kx, this.ky, this.shift, (1 << numBands) - 1, 0);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 192 */           Image.ConvMxN_Fp(dstML[i], srcML[i], this.doublekData, this.kw, this.kh, this.kx, this.ky, (1 << numBands) - 1, 0);
/*     */           break;
/*     */         default:
/* 198 */           className = getClass().getName();
/* 199 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 203 */     if (dstAccessor.isDataCopy())
/* 204 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibConvolveOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */