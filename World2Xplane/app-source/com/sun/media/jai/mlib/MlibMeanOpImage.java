/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.MeanOpImage;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ 
/*     */ final class MlibMeanOpImage extends MeanOpImage {
/*     */   public MlibMeanOpImage(RenderedImage source, ROI roi, int xStart, int yStart, int xPeriod, int yPeriod) {
/*  49 */     super(source, roi, xStart, yStart, xPeriod, yPeriod);
/*     */   }
/*     */   
/*     */   protected void accumulateStatistics(String name, Raster source, Object stats) {
/*     */     int i;
/*  56 */     PlanarImage sourceImage = getSourceImage(0);
/*  57 */     int numBands = sourceImage.getSampleModel().getNumBands();
/*  60 */     int formatTag = MediaLibAccessor.findCompatibleTag(null, source);
/*  61 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, source.getBounds(), formatTag);
/*  66 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/*  70 */     double[] dmean = new double[numBands];
/*  72 */     switch (srcAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  77 */         for (i = 0; i < srcML.length; i++)
/*  78 */           Image.Mean(dmean, srcML[i]); 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*  84 */         for (i = 0; i < srcML.length; i++)
/*  85 */           Image.Mean_Fp(dmean, srcML[i]); 
/*     */         break;
/*     */       default:
/*  90 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/*  93 */     dmean = srcAccessor.getDoubleParameters(0, dmean);
/*  96 */     double[] mean = (double[])stats;
/*  97 */     double weight = (source.getWidth() * source.getHeight()) / (this.width * this.height);
/* 100 */     for (int j = 0; j < numBands; j++)
/* 101 */       mean[j] = mean[j] + dmean[j] * weight; 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMeanOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */