/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class DefaultOHLCDataset extends AbstractXYDataset implements OHLCDataset {
/*     */   private Comparable key;
/*     */   
/*     */   private OHLCDataItem[] data;
/*     */   
/*     */   public DefaultOHLCDataset(Comparable key, OHLCDataItem[] data) {
/*  72 */     this.key = key;
/*  73 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/*  84 */     return this.key;
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/*  96 */     return new Long(this.data[item].getDate().getTime());
/*     */   }
/*     */   
/*     */   public Date getXDate(int series, int item) {
/* 108 */     return this.data[item].getDate();
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 120 */     return getClose(series, item);
/*     */   }
/*     */   
/*     */   public Number getHigh(int series, int item) {
/* 132 */     return this.data[item].getHigh();
/*     */   }
/*     */   
/*     */   public double getHighValue(int series, int item) {
/* 145 */     double result = Double.NaN;
/* 146 */     Number high = getHigh(series, item);
/* 147 */     if (high != null)
/* 148 */       result = high.doubleValue(); 
/* 150 */     return result;
/*     */   }
/*     */   
/*     */   public Number getLow(int series, int item) {
/* 162 */     return this.data[item].getLow();
/*     */   }
/*     */   
/*     */   public double getLowValue(int series, int item) {
/* 175 */     double result = Double.NaN;
/* 176 */     Number low = getLow(series, item);
/* 177 */     if (low != null)
/* 178 */       result = low.doubleValue(); 
/* 180 */     return result;
/*     */   }
/*     */   
/*     */   public Number getOpen(int series, int item) {
/* 192 */     return this.data[item].getOpen();
/*     */   }
/*     */   
/*     */   public double getOpenValue(int series, int item) {
/* 205 */     double result = Double.NaN;
/* 206 */     Number open = getOpen(series, item);
/* 207 */     if (open != null)
/* 208 */       result = open.doubleValue(); 
/* 210 */     return result;
/*     */   }
/*     */   
/*     */   public Number getClose(int series, int item) {
/* 222 */     return this.data[item].getClose();
/*     */   }
/*     */   
/*     */   public double getCloseValue(int series, int item) {
/* 235 */     double result = Double.NaN;
/* 236 */     Number close = getClose(series, item);
/* 237 */     if (close != null)
/* 238 */       result = close.doubleValue(); 
/* 240 */     return result;
/*     */   }
/*     */   
/*     */   public Number getVolume(int series, int item) {
/* 252 */     return this.data[item].getVolume();
/*     */   }
/*     */   
/*     */   public double getVolumeValue(int series, int item) {
/* 265 */     double result = Double.NaN;
/* 266 */     Number volume = getVolume(series, item);
/* 267 */     if (volume != null)
/* 268 */       result = volume.doubleValue(); 
/* 270 */     return result;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 279 */     return 1;
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 290 */     return this.data.length;
/*     */   }
/*     */   
/*     */   public void sortDataByDate() {
/* 297 */     Arrays.sort((Object[])this.data);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 308 */     if (this == obj)
/* 309 */       return true; 
/* 311 */     if (!(obj instanceof DefaultOHLCDataset))
/* 312 */       return false; 
/* 314 */     DefaultOHLCDataset that = (DefaultOHLCDataset)obj;
/* 315 */     if (!this.key.equals(that.key))
/* 316 */       return false; 
/* 318 */     if (!Arrays.equals((Object[])this.data, (Object[])that.data))
/* 319 */       return false; 
/* 321 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\DefaultOHLCDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */