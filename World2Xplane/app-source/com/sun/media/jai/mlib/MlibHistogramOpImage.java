/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeMap;
/*     */ import javax.media.jai.Histogram;
/*     */ import javax.media.jai.StatisticsOpImage;
/*     */ 
/*     */ final class MlibHistogramOpImage extends StatisticsOpImage {
/*     */   private int[] numBins;
/*     */   
/*     */   private double[] lowValueFP;
/*     */   
/*     */   private double[] highValueFP;
/*     */   
/*     */   private int[] lowValue;
/*     */   
/*     */   private int[] highValue;
/*     */   
/*     */   private int numBands;
/*     */   
/*     */   private int[] bandIndexMap;
/*     */   
/*     */   private boolean reorderBands = false;
/*     */   
/*     */   public MlibHistogramOpImage(RenderedImage source, int xPeriod, int yPeriod, int[] numBins, double[] lowValueFP, double[] highValueFP) {
/*  66 */     super(source, null, source.getMinX(), source.getMinY(), xPeriod, yPeriod);
/*  73 */     this.numBands = this.sampleModel.getNumBands();
/*  76 */     this.numBins = new int[this.numBands];
/*  77 */     this.lowValueFP = new double[this.numBands];
/*  78 */     this.highValueFP = new double[this.numBands];
/*  81 */     for (int b = 0; b < this.numBands; b++) {
/*  82 */       this.numBins[b] = (numBins.length == 1) ? numBins[0] : numBins[b];
/*  84 */       this.lowValueFP[b] = (lowValueFP.length == 1) ? lowValueFP[0] : lowValueFP[b];
/*  86 */       this.highValueFP[b] = (highValueFP.length == 1) ? highValueFP[0] : highValueFP[b];
/*     */     } 
/*  94 */     this.lowValue = new int[this.lowValueFP.length];
/*     */     int i;
/*  95 */     for (i = 0; i < this.lowValueFP.length; i++)
/*  96 */       this.lowValue[i] = (int)Math.ceil(this.lowValueFP[i]); 
/* 103 */     this.highValue = new int[this.highValueFP.length];
/* 104 */     for (i = 0; i < this.highValueFP.length; i++)
/* 105 */       this.highValue[i] = (int)Math.ceil(this.highValueFP[i]); 
/* 109 */     if (this.numBands > 1) {
/* 110 */       ComponentSampleModel csm = (ComponentSampleModel)this.sampleModel;
/* 112 */       TreeMap indexMap = new TreeMap();
/* 115 */       int[] indices = csm.getBankIndices();
/* 116 */       boolean checkBanks = false;
/*     */       int j;
/* 117 */       for (j = 1; j < this.numBands; j++) {
/* 118 */         if (indices[j] != indices[j - 1]) {
/* 119 */           checkBanks = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 125 */       if (checkBanks) {
/* 126 */         for (j = 0; j < this.numBands; j++)
/* 127 */           indexMap.put(new Integer(indices[j]), new Integer(j)); 
/* 130 */         this.bandIndexMap = new int[this.numBands];
/* 131 */         Iterator bankIter = indexMap.keySet().iterator();
/* 132 */         int k = 0;
/* 133 */         while (bankIter.hasNext()) {
/* 134 */           int idx = ((Integer)indexMap.get(bankIter.next())).intValue();
/* 136 */           if (idx != k)
/* 137 */             this.reorderBands = true; 
/* 139 */           this.bandIndexMap[k++] = idx;
/*     */         } 
/*     */       } 
/* 145 */       if (!this.reorderBands) {
/* 146 */         indexMap.clear();
/* 148 */         if (this.bandIndexMap == null)
/* 149 */           this.bandIndexMap = new int[this.numBands]; 
/* 152 */         int[] offsets = csm.getBandOffsets();
/* 153 */         for (int m = 0; m < this.numBands; m++)
/* 154 */           indexMap.put(new Integer(offsets[m]), new Integer(m)); 
/* 157 */         Iterator offsetIter = indexMap.keySet().iterator();
/* 158 */         int k = 0;
/* 159 */         while (offsetIter.hasNext()) {
/* 160 */           int idx = ((Integer)indexMap.get(offsetIter.next())).intValue();
/* 162 */           if (idx != k)
/* 163 */             this.reorderBands = true; 
/* 165 */           this.bandIndexMap[k++] = idx;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String[] getStatisticsNames() {
/* 172 */     String[] names = new String[1];
/* 173 */     names[0] = "histogram";
/* 174 */     return names;
/*     */   }
/*     */   
/*     */   protected Object createStatistics(String name) {
/* 178 */     if (name.equalsIgnoreCase("histogram"))
/* 179 */       return new Histogram(this.numBins, this.lowValueFP, this.highValueFP); 
/* 181 */     return Image.UndefinedProperty;
/*     */   }
/*     */   
/*     */   protected void accumulateStatistics(String name, Raster source, Object stats) {
/*     */     int[][] histo;
/* 189 */     Histogram histogram = (Histogram)stats;
/* 190 */     int numBands = histogram.getNumBands();
/* 191 */     int[][] histJAI = histogram.getBins();
/* 194 */     Rectangle tileRect = source.getBounds();
/* 198 */     if (!this.reorderBands && tileRect.equals(getBounds())) {
/* 200 */       histo = histJAI;
/*     */     } else {
/* 203 */       histo = new int[numBands][];
/* 204 */       for (int i = 0; i < numBands; i++)
/* 205 */         histo[i] = new int[histogram.getNumBins(i)]; 
/*     */     } 
/* 210 */     int formatTag = MediaLibAccessor.findCompatibleTag(null, source);
/* 211 */     MediaLibAccessor accessor = new MediaLibAccessor(source, tileRect, formatTag);
/* 213 */     mediaLibImage[] img = accessor.getMediaLibImages();
/* 216 */     int offsetX = (this.xPeriod - (tileRect.x - this.xStart) % this.xPeriod) % this.xPeriod;
/* 217 */     int offsetY = (this.yPeriod - (tileRect.y - this.yStart) % this.yPeriod) % this.yPeriod;
/* 219 */     if (histo == histJAI) {
/* 220 */       synchronized (histogram) {
/* 222 */         Image.Histogram2(histo, img[0], this.lowValue, this.highValue, offsetX, offsetY, this.xPeriod, this.yPeriod);
/*     */       } 
/*     */     } else {
/* 227 */       Image.Histogram2(histo, img[0], this.lowValue, this.highValue, offsetX, offsetY, this.xPeriod, this.yPeriod);
/* 231 */       synchronized (histogram) {
/* 232 */         for (int i = 0; i < numBands; i++) {
/* 233 */           int numBins = (histo[i]).length;
/* 234 */           int[] binsBandJAI = this.reorderBands ? histJAI[this.bandIndexMap[i]] : histJAI[i];
/* 236 */           int[] binsBand = histo[i];
/* 237 */           for (int j = 0; j < numBins; j++)
/* 238 */             binsBandJAI[j] = binsBandJAI[j] + binsBand[j]; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibHistogramOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */