/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ 
/*     */ public class XYBarDataset extends AbstractIntervalXYDataset implements IntervalXYDataset, DatasetChangeListener {
/*     */   private XYDataset underlying;
/*     */   
/*     */   private double barWidth;
/*     */   
/*     */   public XYBarDataset(XYDataset underlying, double barWidth) {
/*  71 */     this.underlying = underlying;
/*  72 */     this.underlying.addChangeListener(this);
/*  73 */     this.barWidth = barWidth;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/*  82 */     return this.underlying.getSeriesCount();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/*  93 */     return this.underlying.getSeriesKey(series);
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 104 */     return this.underlying.getItemCount(series);
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 118 */     return this.underlying.getX(series, item);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 130 */     return this.underlying.getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 142 */     Number result = null;
/* 143 */     Number xnum = this.underlying.getX(series, item);
/* 144 */     if (xnum != null)
/* 145 */       result = new Double(xnum.doubleValue() - this.barWidth / 2.0D); 
/* 147 */     return result;
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 159 */     Number result = null;
/* 160 */     Number xnum = this.underlying.getX(series, item);
/* 161 */     if (xnum != null)
/* 162 */       result = new Double(xnum.doubleValue() + this.barWidth / 2.0D); 
/* 164 */     return result;
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 176 */     return this.underlying.getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 188 */     return this.underlying.getY(series, item);
/*     */   }
/*     */   
/*     */   public void datasetChanged(DatasetChangeEvent event) {
/* 197 */     notifyListeners(event);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\XYBarDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */