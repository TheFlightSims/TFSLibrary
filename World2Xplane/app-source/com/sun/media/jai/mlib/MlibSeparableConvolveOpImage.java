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
/*     */ final class MlibSeparableConvolveOpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel;
/*     */   
/*     */   private int kw;
/*     */   
/*     */   private int kh;
/*     */   
/*     */   float[] hValues;
/*     */   
/*     */   float[] vValues;
/*     */   
/*     */   double[] hDoubleData;
/*     */   
/*     */   double[] vDoubleData;
/*     */   
/*     */   int[] hIntData;
/*     */   
/*     */   int[] vIntData;
/*     */   
/*  82 */   int shift = -1;
/*     */   
/*     */   public MlibSeparableConvolveOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 105 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 115 */     this.kernel = kernel;
/* 116 */     this.kw = kernel.getWidth();
/* 117 */     this.kh = kernel.getHeight();
/* 121 */     this.hValues = kernel.getHorizontalKernelData();
/* 122 */     this.vValues = kernel.getVerticalKernelData();
/* 127 */     this.hDoubleData = new double[this.hValues.length];
/*     */     int i;
/* 128 */     for (i = 0; i < this.hValues.length; i++)
/* 129 */       this.hDoubleData[i] = this.hValues[i]; 
/* 132 */     this.vDoubleData = new double[this.vValues.length];
/* 133 */     for (i = 0; i < this.vValues.length; i++)
/* 134 */       this.vDoubleData[i] = this.vValues[i]; 
/* 137 */     this.hIntData = new int[this.hValues.length];
/* 138 */     this.vIntData = new int[this.vValues.length];
/*     */   }
/*     */   
/*     */   private synchronized void setShift(int formatTag) {
/* 143 */     if (this.shift == -1) {
/* 144 */       int mediaLibDataType = MediaLibAccessor.getMediaLibDataType(formatTag);
/* 146 */       this.shift = Image.SConvKernelConvert(this.hIntData, this.vIntData, this.hDoubleData, this.vDoubleData, this.kw, this.kh, mediaLibDataType);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 168 */     Raster source = sources[0];
/* 169 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 171 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 174 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 176 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 178 */     int numBands = getSampleModel().getNumBands();
/* 180 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 181 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 182 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 183 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 188 */           if (this.shift == -1)
/* 189 */             setShift(formatTag); 
/* 191 */           switch (this.kw) {
/*     */             case 3:
/* 193 */               Image.SConv3x3(dstML[i], srcML[i], this.hIntData, this.vIntData, this.shift, (1 << numBands) - 1, 0);
/*     */               break;
/*     */             case 5:
/* 199 */               Image.SConv5x5(dstML[i], srcML[i], this.hIntData, this.vIntData, this.shift, (1 << numBands) - 1, 0);
/*     */               break;
/*     */             case 7:
/* 205 */               Image.SConv7x7(dstML[i], srcML[i], this.hIntData, this.vIntData, this.shift, (1 << numBands) - 1, 0);
/*     */               break;
/*     */           } 
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 214 */           switch (this.kw) {
/*     */             case 3:
/* 216 */               Image.SConv3x3_Fp(dstML[i], srcML[i], this.hDoubleData, this.vDoubleData, (1 << numBands) - 1, 0);
/*     */               break;
/*     */             case 5:
/* 222 */               Image.SConv5x5_Fp(dstML[i], srcML[i], this.hDoubleData, this.vDoubleData, (1 << numBands) - 1, 0);
/*     */               break;
/*     */             case 7:
/* 228 */               Image.SConv7x7_Fp(dstML[i], srcML[i], this.hDoubleData, this.vDoubleData, (1 << numBands) - 1, 0);
/*     */               break;
/*     */           } 
/*     */           break;
/*     */         default:
/* 236 */           className = getClass().getName();
/* 237 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 241 */     if (dstAccessor.isDataCopy())
/* 242 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSeparableConvolveOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */