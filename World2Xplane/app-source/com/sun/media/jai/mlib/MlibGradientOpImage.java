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
/*     */ final class MlibGradientOpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel_h;
/*     */   
/*     */   protected KernelJAI kernel_v;
/*     */   
/*     */   private int kh;
/*     */   
/*     */   private int kw;
/*     */   
/*     */   private int kx;
/*     */   
/*     */   private int ky;
/*     */   
/*     */   float[] kernel_h_data;
/*     */   
/*     */   float[] kernel_v_data;
/*     */   
/*     */   double[] dbl_kh_data;
/*     */   
/*     */   double[] dbl_kv_data;
/*     */   
/*     */   public MlibGradientOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel_h, KernelJAI kernel_v) {
/*  73 */     super(source, layout, config, true, extender, kernel_h.getLeftPadding(), kernel_h.getRightPadding(), kernel_h.getTopPadding(), kernel_h.getBottomPadding());
/*  83 */     this.kernel_h = kernel_h;
/*  84 */     this.kernel_v = kernel_v;
/*  90 */     this.kw = kernel_h.getWidth();
/*  91 */     this.kh = kernel_h.getHeight();
/*  96 */     this.kx = this.kw / 2;
/*  97 */     this.ky = this.kh / 2;
/* 102 */     this.kernel_h_data = kernel_h.getKernelData();
/* 103 */     this.kernel_v_data = kernel_v.getKernelData();
/* 104 */     int count = this.kw * this.kh;
/* 105 */     this.dbl_kh_data = new double[count];
/* 106 */     this.dbl_kv_data = new double[count];
/* 107 */     for (int i = 0; i < count; i++) {
/* 108 */       this.dbl_kh_data[i] = this.kernel_h_data[i];
/* 109 */       this.dbl_kv_data[i] = this.kernel_v_data[i];
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 124 */     Raster source = sources[0];
/* 125 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 127 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 129 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 131 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 133 */     int numBands = getSampleModel().getNumBands();
/* 135 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 136 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 137 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 138 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 143 */           Image.GradientMxN(dstML[i], srcML[i], this.dbl_kh_data, this.dbl_kv_data, this.kw, this.kh, this.kx, this.ky, (1 << numBands) - 1, 0);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 157 */           Image.GradientMxN_Fp(dstML[i], srcML[i], this.dbl_kh_data, this.dbl_kv_data, this.kw, this.kh, this.kx, this.ky, (1 << numBands) - 1, 0);
/*     */           break;
/*     */         default:
/* 170 */           className = getClass().getName();
/* 171 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 175 */     if (dstAccessor.isDataCopy())
/* 176 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibGradientOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */