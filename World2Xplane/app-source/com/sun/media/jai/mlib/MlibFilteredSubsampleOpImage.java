/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.FilteredSubsampleOpImage;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ 
/*     */ final class MlibFilteredSubsampleOpImage extends FilteredSubsampleOpImage {
/*     */   protected double[] m_hKernel;
/*     */   
/*     */   protected double[] m_vKernel;
/*     */   
/*     */   private static final boolean DEBUG = false;
/*     */   
/*     */   public MlibFilteredSubsampleOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int scaleX, int scaleY, float[] qsFilter, Interpolation interp) {
/*  65 */     super(source, extender, config, layout, scaleX, scaleY, qsFilter, interp);
/*  78 */     this.m_hKernel = new double[this.hKernel.length];
/*  79 */     this.m_vKernel = new double[this.vKernel.length];
/*  81 */     for (int i = 0; i < this.hKernel.length; i++)
/*  82 */       this.m_hKernel[i] = this.hKernel[i]; 
/*  86 */     for (int j = 0; j < this.vKernel.length; j++)
/*  87 */       this.m_vKernel[j] = this.vKernel[j]; 
/*     */   }
/*     */   
/*     */   public void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/* 108 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 111 */     MediaLibAccessor dst = new MediaLibAccessor(dest, destRect, formatTag);
/* 115 */     MediaLibAccessor src = new MediaLibAccessor(sources[0], mapDestRect(destRect, 0), formatTag);
/* 120 */     int transX = this.m_hKernel.length - (this.scaleX + 1) / 2 - this.hParity * (1 + this.scaleX) % 2;
/* 121 */     int transY = this.m_vKernel.length - (this.scaleY + 1) / 2 - this.vParity * (1 + this.scaleY) % 2;
/* 123 */     switch (dst.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 128 */         srcML = src.getMediaLibImages();
/* 129 */         dstML = dst.getMediaLibImages();
/* 130 */         for (i = 0; i < dstML.length; i++)
/* 131 */           Image.FilteredSubsample(dstML[i], srcML[i], this.scaleX, this.scaleY, transX, transY, this.m_hKernel, this.m_vKernel, this.hParity, this.vParity, 0); 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 141 */         srcML = src.getMediaLibImages();
/* 142 */         dstML = dst.getMediaLibImages();
/* 143 */         for (i = 0; i < dstML.length; i++)
/* 144 */           Image.FilteredSubsample_Fp(dstML[i], srcML[i], this.scaleX, this.scaleY, transX, transY, this.m_hKernel, this.m_vKernel, this.hParity, this.vParity, 0); 
/*     */         break;
/*     */       default:
/* 153 */         throw new IllegalArgumentException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 159 */     if (dst.isDataCopy()) {
/* 160 */       dst.clampDataArrays();
/* 161 */       dst.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibFilteredSubsampleOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */