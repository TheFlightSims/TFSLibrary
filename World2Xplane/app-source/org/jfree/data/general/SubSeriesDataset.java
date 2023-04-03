/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.OHLCDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public class SubSeriesDataset extends AbstractIntervalXYDataset implements OHLCDataset, IntervalXYDataset, CombinationDataset {
/*  72 */   private SeriesDataset parent = null;
/*     */   
/*     */   private int[] map;
/*     */   
/*     */   public SubSeriesDataset(SeriesDataset parent, int[] map) {
/*  85 */     this.parent = parent;
/*  86 */     this.map = map;
/*     */   }
/*     */   
/*     */   public SubSeriesDataset(SeriesDataset parent, int series) {
/*  97 */     this(parent, new int[] { series });
/*     */   }
/*     */   
/*     */   public Number getHigh(int series, int item) {
/* 116 */     return ((OHLCDataset)this.parent).getHigh(this.map[series], item);
/*     */   }
/*     */   
/*     */   public double getHighValue(int series, int item) {
/* 129 */     double result = Double.NaN;
/* 130 */     Number high = getHigh(series, item);
/* 131 */     if (high != null)
/* 132 */       result = high.doubleValue(); 
/* 134 */     return result;
/*     */   }
/*     */   
/*     */   public Number getLow(int series, int item) {
/* 149 */     return ((OHLCDataset)this.parent).getLow(this.map[series], item);
/*     */   }
/*     */   
/*     */   public double getLowValue(int series, int item) {
/* 162 */     double result = Double.NaN;
/* 163 */     Number low = getLow(series, item);
/* 164 */     if (low != null)
/* 165 */       result = low.doubleValue(); 
/* 167 */     return result;
/*     */   }
/*     */   
/*     */   public Number getOpen(int series, int item) {
/* 182 */     return ((OHLCDataset)this.parent).getOpen(this.map[series], item);
/*     */   }
/*     */   
/*     */   public double getOpenValue(int series, int item) {
/* 195 */     double result = Double.NaN;
/* 196 */     Number open = getOpen(series, item);
/* 197 */     if (open != null)
/* 198 */       result = open.doubleValue(); 
/* 200 */     return result;
/*     */   }
/*     */   
/*     */   public Number getClose(int series, int item) {
/* 215 */     return ((OHLCDataset)this.parent).getClose(this.map[series], item);
/*     */   }
/*     */   
/*     */   public double getCloseValue(int series, int item) {
/* 228 */     double result = Double.NaN;
/* 229 */     Number close = getClose(series, item);
/* 230 */     if (close != null)
/* 231 */       result = close.doubleValue(); 
/* 233 */     return result;
/*     */   }
/*     */   
/*     */   public Number getVolume(int series, int item) {
/* 248 */     return ((OHLCDataset)this.parent).getVolume(this.map[series], item);
/*     */   }
/*     */   
/*     */   public double getVolumeValue(int series, int item) {
/* 261 */     double result = Double.NaN;
/* 262 */     Number volume = getVolume(series, item);
/* 263 */     if (volume != null)
/* 264 */       result = volume.doubleValue(); 
/* 266 */     return result;
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 285 */     return ((XYDataset)this.parent).getX(this.map[series], item);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 300 */     return ((XYDataset)this.parent).getY(this.map[series], item);
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 314 */     return ((XYDataset)this.parent).getItemCount(this.map[series]);
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 327 */     return this.map.length;
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 338 */     return this.parent.getSeriesKey(this.map[series]);
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 354 */     if (this.parent instanceof IntervalXYDataset)
/* 355 */       return ((IntervalXYDataset)this.parent).getStartX(this.map[series], item); 
/* 360 */     return getX(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 373 */     if (this.parent instanceof IntervalXYDataset)
/* 374 */       return ((IntervalXYDataset)this.parent).getEndX(this.map[series], item); 
/* 379 */     return getX(series, item);
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 392 */     if (this.parent instanceof IntervalXYDataset)
/* 393 */       return ((IntervalXYDataset)this.parent).getStartY(this.map[series], item); 
/* 398 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 411 */     if (this.parent instanceof IntervalXYDataset)
/* 412 */       return ((IntervalXYDataset)this.parent).getEndY(this.map[series], item); 
/* 417 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public SeriesDataset getParent() {
/* 431 */     return this.parent;
/*     */   }
/*     */   
/*     */   public int[] getMap() {
/* 440 */     return (int[])this.map.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\SubSeriesDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */