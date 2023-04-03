/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.SeriesChangeListener;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class XYSeriesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, DomainInfo, Serializable {
/*     */   private static final long serialVersionUID = -7590013825931496766L;
/*     */   
/*     */   private List data;
/*     */   
/*     */   private IntervalXYDelegate intervalDelegate;
/*     */   
/*     */   public XYSeriesCollection() {
/*  88 */     this((XYSeries)null);
/*     */   }
/*     */   
/*     */   public XYSeriesCollection(XYSeries series) {
/*  97 */     this.data = new ArrayList();
/*  98 */     this.intervalDelegate = new IntervalXYDelegate(this, false);
/*  99 */     addChangeListener(this.intervalDelegate);
/* 100 */     if (series != null) {
/* 101 */       this.data.add(series);
/* 102 */       series.addChangeListener((SeriesChangeListener)this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addSeries(XYSeries series) {
/* 114 */     if (series == null)
/* 115 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 117 */     this.data.add(series);
/* 118 */     series.addChangeListener((SeriesChangeListener)this);
/* 119 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(int series) {
/* 131 */     if (series < 0 || series > getSeriesCount())
/* 132 */       throw new IllegalArgumentException("Series index out of bounds."); 
/* 136 */     XYSeries ts = this.data.get(series);
/* 137 */     ts.removeChangeListener((SeriesChangeListener)this);
/* 138 */     this.data.remove(series);
/* 139 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(XYSeries series) {
/* 151 */     if (series == null)
/* 152 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 154 */     if (this.data.contains(series)) {
/* 155 */       series.removeChangeListener((SeriesChangeListener)this);
/* 156 */       this.data.remove(series);
/* 157 */       fireDatasetChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeAllSeries() {
/* 169 */     for (int i = 0; i < this.data.size(); i++) {
/* 170 */       XYSeries series = this.data.get(i);
/* 171 */       series.removeChangeListener((SeriesChangeListener)this);
/*     */     } 
/* 175 */     this.data.clear();
/* 176 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 185 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public List getSeries() {
/* 194 */     return Collections.unmodifiableList(this.data);
/*     */   }
/*     */   
/*     */   public XYSeries getSeries(int series) {
/* 205 */     if (series < 0 || series >= getSeriesCount())
/* 206 */       throw new IllegalArgumentException("Series index out of bounds"); 
/* 208 */     return this.data.get(series);
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 220 */     return getSeries(series).getKey();
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 232 */     return getSeries(series).getItemCount();
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 244 */     XYSeries ts = this.data.get(series);
/* 245 */     XYDataItem xyItem = ts.getDataItem(item);
/* 246 */     return xyItem.getX();
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 258 */     return this.intervalDelegate.getStartX(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 270 */     return this.intervalDelegate.getEndX(series, item);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int index) {
/* 283 */     XYSeries ts = this.data.get(series);
/* 284 */     XYDataItem xyItem = ts.getDataItem(index);
/* 285 */     return xyItem.getY();
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 298 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 310 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 330 */     if (obj == this)
/* 331 */       return true; 
/* 333 */     if (!(obj instanceof XYSeriesCollection))
/* 334 */       return false; 
/* 336 */     XYSeriesCollection that = (XYSeriesCollection)obj;
/* 337 */     return ObjectUtilities.equal(this.data, that.data);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 347 */     return (this.data != null) ? this.data.hashCode() : 0;
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 359 */     return this.intervalDelegate.getDomainLowerBound(includeInterval);
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 371 */     return this.intervalDelegate.getDomainUpperBound(includeInterval);
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 383 */     if (includeInterval)
/* 384 */       return this.intervalDelegate.getDomainBounds(includeInterval); 
/* 387 */     return DatasetUtilities.iterateDomainBounds(this, includeInterval);
/*     */   }
/*     */   
/*     */   public double getIntervalWidth() {
/* 399 */     return this.intervalDelegate.getIntervalWidth();
/*     */   }
/*     */   
/*     */   public void setIntervalWidth(double width) {
/* 409 */     if (width < 0.0D)
/* 410 */       throw new IllegalArgumentException("Negative 'width' argument."); 
/* 412 */     this.intervalDelegate.setFixedIntervalWidth(width);
/* 413 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public double getIntervalPositionFactor() {
/* 422 */     return this.intervalDelegate.getIntervalPositionFactor();
/*     */   }
/*     */   
/*     */   public void setIntervalPositionFactor(double factor) {
/* 433 */     this.intervalDelegate.setIntervalPositionFactor(factor);
/* 434 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public boolean isAutoWidth() {
/* 443 */     return this.intervalDelegate.isAutoWidth();
/*     */   }
/*     */   
/*     */   public void setAutoWidth(boolean b) {
/* 453 */     this.intervalDelegate.setAutoWidth(b);
/* 454 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\XYSeriesCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */