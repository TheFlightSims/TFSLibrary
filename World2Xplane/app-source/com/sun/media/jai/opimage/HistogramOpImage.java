/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import javax.media.jai.Histogram;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.StatisticsOpImage;
/*     */ 
/*     */ final class HistogramOpImage extends StatisticsOpImage {
/*     */   private int[] numBins;
/*     */   
/*     */   private double[] lowValue;
/*     */   
/*     */   private double[] highValue;
/*     */   
/*     */   private int numBands;
/*     */   
/*     */   private final boolean tileIntersectsROI(int tileX, int tileY) {
/*  48 */     if (this.roi == null)
/*  49 */       return true; 
/*  51 */     return this.roi.intersects(tileXToX(tileX), tileYToY(tileY), this.tileWidth, this.tileHeight);
/*     */   }
/*     */   
/*     */   public HistogramOpImage(RenderedImage source, ROI roi, int xStart, int yStart, int xPeriod, int yPeriod, int[] numBins, double[] lowValue, double[] highValue) {
/*  70 */     super(source, roi, xStart, yStart, xPeriod, yPeriod);
/*  72 */     this.numBands = source.getSampleModel().getNumBands();
/*  74 */     this.numBins = new int[this.numBands];
/*  75 */     this.lowValue = new double[this.numBands];
/*  76 */     this.highValue = new double[this.numBands];
/*  78 */     for (int b = 0; b < this.numBands; b++) {
/*  79 */       this.numBins[b] = (numBins.length == 1) ? numBins[0] : numBins[b];
/*  81 */       this.lowValue[b] = (lowValue.length == 1) ? lowValue[0] : lowValue[b];
/*  83 */       this.highValue[b] = (highValue.length == 1) ? highValue[0] : highValue[b];
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String[] getStatisticsNames() {
/*  89 */     String[] names = new String[1];
/*  90 */     names[0] = "histogram";
/*  91 */     return names;
/*     */   }
/*     */   
/*     */   protected Object createStatistics(String name) {
/*  95 */     if (name.equalsIgnoreCase("histogram"))
/*  96 */       return new Histogram(this.numBins, this.lowValue, this.highValue); 
/*  98 */     return Image.UndefinedProperty;
/*     */   }
/*     */   
/*     */   protected void accumulateStatistics(String name, Raster source, Object stats) {
/* 105 */     Histogram histogram = (Histogram)stats;
/* 106 */     histogram.countPixels(source, this.roi, this.xStart, this.yStart, this.xPeriod, this.yPeriod);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\HistogramOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */