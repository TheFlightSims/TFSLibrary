/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import org.jfree.data.DefaultKeyedValues2D;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ 
/*     */ public class CategoryTableXYDataset extends AbstractIntervalXYDataset implements TableXYDataset, IntervalXYDataset, DomainInfo {
/*     */   private DefaultKeyedValues2D values;
/*     */   
/*     */   private IntervalXYDelegate intervalDelegate;
/*     */   
/*     */   public CategoryTableXYDataset() {
/*  89 */     this.values = new DefaultKeyedValues2D(true);
/*  90 */     this.intervalDelegate = new IntervalXYDelegate(this);
/*  91 */     addChangeListener(this.intervalDelegate);
/*     */   }
/*     */   
/*     */   public void add(double x, double y, String seriesName) {
/* 103 */     add(new Double(x), new Double(y), seriesName, true);
/*     */   }
/*     */   
/*     */   public void add(Number x, Number y, String seriesName, boolean notify) {
/* 116 */     this.values.addValue(y, (Comparable)x, seriesName);
/* 117 */     if (notify)
/* 118 */       fireDatasetChanged(); 
/*     */   }
/*     */   
/*     */   public void remove(double x, String seriesName) {
/* 129 */     remove(new Double(x), seriesName, true);
/*     */   }
/*     */   
/*     */   public void remove(Number x, String seriesName, boolean notify) {
/* 140 */     this.values.removeValue((Comparable)x, seriesName);
/* 141 */     if (notify)
/* 142 */       fireDatasetChanged(); 
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 153 */     return this.values.getColumnCount();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 164 */     return this.values.getColumnKey(series);
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 173 */     return this.values.getRowCount();
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 185 */     return getItemCount();
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 198 */     return (Number)this.values.getRowKey(item);
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 210 */     return this.intervalDelegate.getStartX(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 222 */     return this.intervalDelegate.getEndX(series, item);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 234 */     return this.values.getValue(item, series);
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 246 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 258 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 270 */     return this.intervalDelegate.getDomainLowerBound(includeInterval);
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 282 */     return this.intervalDelegate.getDomainUpperBound(includeInterval);
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 294 */     if (includeInterval)
/* 295 */       return this.intervalDelegate.getDomainBounds(includeInterval); 
/* 298 */     return DatasetUtilities.iterateDomainBounds(this, includeInterval);
/*     */   }
/*     */   
/*     */   public double getIntervalPositionFactor() {
/* 308 */     return this.intervalDelegate.getIntervalPositionFactor();
/*     */   }
/*     */   
/*     */   public void setIntervalPositionFactor(double d) {
/* 320 */     this.intervalDelegate.setIntervalPositionFactor(d);
/* 321 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public double getIntervalWidth() {
/* 330 */     return this.intervalDelegate.getIntervalWidth();
/*     */   }
/*     */   
/*     */   public void setIntervalWidth(double d) {
/* 340 */     this.intervalDelegate.setFixedIntervalWidth(d);
/* 341 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public boolean isAutoWidth() {
/* 350 */     return this.intervalDelegate.isAutoWidth();
/*     */   }
/*     */   
/*     */   public void setAutoWidth(boolean b) {
/* 360 */     this.intervalDelegate.setAutoWidth(b);
/* 361 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 372 */     if (!(obj instanceof CategoryTableXYDataset))
/* 373 */       return false; 
/* 375 */     CategoryTableXYDataset that = (CategoryTableXYDataset)obj;
/* 376 */     if (!this.intervalDelegate.equals(that.intervalDelegate))
/* 377 */       return false; 
/* 379 */     if (!this.values.equals(that.values))
/* 380 */       return false; 
/* 382 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\CategoryTableXYDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */