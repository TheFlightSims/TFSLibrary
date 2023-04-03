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
/*     */ final class MlibClampOpImage extends PointOpImage {
/*     */   private double[] low;
/*     */   
/*     */   private int[] lowInt;
/*     */   
/*     */   private double[] high;
/*     */   
/*     */   private int[] highInt;
/*     */   
/*     */   public MlibClampOpImage(RenderedImage source, Map config, ImageLayout layout, double[] low, double[] high) {
/*  54 */     super(source, layout, config, true);
/*  56 */     int numBands = getSampleModel().getNumBands();
/*  57 */     this.low = new double[numBands];
/*  58 */     this.lowInt = new int[numBands];
/*  59 */     this.high = new double[numBands];
/*  60 */     this.highInt = new int[numBands];
/*  62 */     for (int i = 0; i < numBands; i++) {
/*  63 */       if (low.length < numBands) {
/*  64 */         this.low[i] = low[0];
/*     */       } else {
/*  66 */         this.low[i] = low[i];
/*     */       } 
/*  68 */       this.lowInt[i] = ImageUtil.clampRoundInt(this.low[i]);
/*  70 */       if (high.length < numBands) {
/*  71 */         this.high[i] = high[0];
/*     */       } else {
/*  73 */         this.high[i] = high[i];
/*     */       } 
/*  75 */       this.highInt[i] = ImageUtil.clampRoundInt(this.high[i]);
/*     */     } 
/*  78 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  88 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  90 */     MediaLibAccessor srcMA = new MediaLibAccessor(sources[0], destRect, formatTag);
/*  92 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/*  95 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/*  96 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/*  98 */     switch (dstMA.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 103 */         for (i = 0; i < dstMLI.length; i++) {
/* 104 */           int[] mlLow = dstMA.getIntParameters(i, this.lowInt);
/* 105 */           int[] mlHigh = dstMA.getIntParameters(i, this.highInt);
/* 106 */           Image.Thresh4(dstMLI[i], srcMLI[i], mlHigh, mlLow, mlHigh, mlLow);
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 113 */         for (i = 0; i < dstMLI.length; i++) {
/* 114 */           double[] mlLow = dstMA.getDoubleParameters(i, this.low);
/* 115 */           double[] mlHigh = dstMA.getDoubleParameters(i, this.high);
/* 116 */           Image.Thresh4_Fp(dstMLI[i], srcMLI[i], mlHigh, mlLow, mlHigh, mlLow);
/*     */         } 
/*     */         break;
/*     */       default:
/* 122 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 125 */     if (dstMA.isDataCopy()) {
/* 126 */       dstMA.clampDataArrays();
/* 127 */       dstMA.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibClampOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */