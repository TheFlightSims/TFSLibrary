/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ 
/*     */ final class MlibThresholdOpImage extends PointOpImage {
/*     */   private double[] low;
/*     */   
/*     */   private int[] lowInt;
/*     */   
/*     */   private double[] high;
/*     */   
/*     */   private int[] highInt;
/*     */   
/*     */   private double[] constants;
/*     */   
/*     */   private int[] constantsInt;
/*     */   
/*     */   public MlibThresholdOpImage(RenderedImage source, Map config, ImageLayout layout, double[] low, double[] high, double[] constants) {
/*  61 */     super(source, layout, config, true);
/*  63 */     int numBands = getSampleModel().getNumBands();
/*  64 */     this.low = new double[numBands];
/*  65 */     this.lowInt = new int[numBands];
/*  66 */     this.high = new double[numBands];
/*  67 */     this.highInt = new int[numBands];
/*  68 */     this.constants = new double[numBands];
/*  69 */     this.constantsInt = new int[numBands];
/*  71 */     for (int i = 0; i < numBands; i++) {
/*  72 */       if (low.length < numBands) {
/*  73 */         this.low[i] = low[0];
/*     */       } else {
/*  75 */         this.low[i] = low[i];
/*     */       } 
/*  77 */       this.lowInt[i] = ImageUtil.clampInt((int)Math.ceil(this.low[i]));
/*  79 */       if (high.length < numBands) {
/*  80 */         this.high[i] = high[0];
/*     */       } else {
/*  82 */         this.high[i] = high[i];
/*     */       } 
/*  84 */       this.highInt[i] = ImageUtil.clampInt((int)Math.floor(this.high[i]));
/*  86 */       if (constants.length < numBands) {
/*  87 */         this.constants[i] = constants[0];
/*     */       } else {
/*  89 */         this.constants[i] = constants[i];
/*     */       } 
/*  91 */       this.constantsInt[i] = ImageUtil.clampRoundInt(this.constants[i]);
/*     */     } 
/*  94 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 104 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 106 */     MediaLibAccessor srcMA = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 108 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 111 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 112 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 114 */     switch (dstMA.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 119 */         for (i = 0; i < dstMLI.length; i++) {
/* 120 */           int[] mlLow = dstMA.getIntParameters(i, this.lowInt);
/* 121 */           int[] mlHigh = dstMA.getIntParameters(i, this.highInt);
/* 122 */           int[] mlConstants = dstMA.getIntParameters(i, this.constantsInt);
/* 123 */           Image.Thresh5(dstMLI[i], srcMLI[i], mlHigh, mlLow, mlConstants);
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 130 */         for (i = 0; i < dstMLI.length; i++) {
/* 131 */           double[] mlLow = dstMA.getDoubleParameters(i, this.low);
/* 132 */           double[] mlHigh = dstMA.getDoubleParameters(i, this.high);
/* 133 */           double[] mlConstants = dstMA.getDoubleParameters(i, this.constants);
/* 134 */           Image.Thresh5_Fp(dstMLI[i], srcMLI[i], mlHigh, mlLow, mlConstants);
/*     */         } 
/*     */         break;
/*     */       default:
/* 140 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 143 */     if (dstMA.isDataCopy()) {
/* 144 */       dstMA.clampDataArrays();
/* 145 */       dstMA.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibThresholdOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */