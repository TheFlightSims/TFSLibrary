/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class DefaultHighLowDataset extends AbstractXYDataset implements OHLCDataset {
/*     */   private Comparable seriesKey;
/*     */   
/*     */   private Date[] date;
/*     */   
/*     */   private Number[] high;
/*     */   
/*     */   private Number[] low;
/*     */   
/*     */   private Number[] open;
/*     */   
/*     */   private Number[] close;
/*     */   
/*     */   private Number[] volume;
/*     */   
/*     */   public DefaultHighLowDataset(Comparable seriesKey, Date[] date, double[] high, double[] low, double[] open, double[] close, double[] volume) {
/*  99 */     this.seriesKey = seriesKey;
/* 100 */     this.date = date;
/* 101 */     this.high = createNumberArray(high);
/* 102 */     this.low = createNumberArray(low);
/* 103 */     this.open = createNumberArray(open);
/* 104 */     this.close = createNumberArray(close);
/* 105 */     this.volume = createNumberArray(volume);
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int i) {
/* 117 */     return this.seriesKey;
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 131 */     return new Long(this.date[item].getTime());
/*     */   }
/*     */   
/*     */   public Date getXDate(int series, int item) {
/* 145 */     return this.date[item];
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 160 */     return getClose(series, item);
/*     */   }
/*     */   
/*     */   public Number getHigh(int series, int item) {
/* 172 */     return this.high[item];
/*     */   }
/*     */   
/*     */   public double getHighValue(int series, int item) {
/* 185 */     double result = Double.NaN;
/* 186 */     Number high = getHigh(series, item);
/* 187 */     if (high != null)
/* 188 */       result = high.doubleValue(); 
/* 190 */     return result;
/*     */   }
/*     */   
/*     */   public Number getLow(int series, int item) {
/* 202 */     return this.low[item];
/*     */   }
/*     */   
/*     */   public double getLowValue(int series, int item) {
/* 215 */     double result = Double.NaN;
/* 216 */     Number low = getLow(series, item);
/* 217 */     if (low != null)
/* 218 */       result = low.doubleValue(); 
/* 220 */     return result;
/*     */   }
/*     */   
/*     */   public Number getOpen(int series, int item) {
/* 232 */     return this.open[item];
/*     */   }
/*     */   
/*     */   public double getOpenValue(int series, int item) {
/* 245 */     double result = Double.NaN;
/* 246 */     Number open = getOpen(series, item);
/* 247 */     if (open != null)
/* 248 */       result = open.doubleValue(); 
/* 250 */     return result;
/*     */   }
/*     */   
/*     */   public Number getClose(int series, int item) {
/* 262 */     return this.close[item];
/*     */   }
/*     */   
/*     */   public double getCloseValue(int series, int item) {
/* 275 */     double result = Double.NaN;
/* 276 */     Number close = getClose(series, item);
/* 277 */     if (close != null)
/* 278 */       result = close.doubleValue(); 
/* 280 */     return result;
/*     */   }
/*     */   
/*     */   public Number getVolume(int series, int item) {
/* 292 */     return this.volume[item];
/*     */   }
/*     */   
/*     */   public double getVolumeValue(int series, int item) {
/* 305 */     double result = Double.NaN;
/* 306 */     Number volume = getVolume(series, item);
/* 307 */     if (volume != null)
/* 308 */       result = volume.doubleValue(); 
/* 310 */     return result;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 321 */     return 1;
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 332 */     return this.date.length;
/*     */   }
/*     */   
/*     */   public static Number[] createNumberArray(double[] data) {
/* 343 */     Number[] result = new Number[data.length];
/* 344 */     for (int i = 0; i < data.length; i++)
/* 345 */       result[i] = new Double(data[i]); 
/* 347 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\DefaultHighLowDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */