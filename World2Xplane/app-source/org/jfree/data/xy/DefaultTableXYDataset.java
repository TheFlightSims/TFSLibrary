/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
/*     */ import org.jfree.data.general.SeriesChangeListener;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class DefaultTableXYDataset extends AbstractIntervalXYDataset implements TableXYDataset, IntervalXYDataset, DomainInfo {
/*  90 */   private List data = null;
/*     */   
/*  93 */   private HashSet xPoints = null;
/*     */   
/*     */   private boolean propagateEvents = true;
/*     */   
/*     */   private boolean autoPrune = false;
/*     */   
/*     */   private IntervalXYDelegate intervalDelegate;
/*     */   
/*     */   public DefaultTableXYDataset() {
/* 108 */     this(false);
/*     */   }
/*     */   
/*     */   public DefaultTableXYDataset(boolean autoPrune) {
/* 119 */     this.autoPrune = autoPrune;
/* 120 */     this.data = new ArrayList();
/* 121 */     this.xPoints = new HashSet();
/* 122 */     this.intervalDelegate = new IntervalXYDelegate(this, false);
/* 123 */     addChangeListener(this.intervalDelegate);
/*     */   }
/*     */   
/*     */   public boolean isAutoPrune() {
/* 133 */     return this.autoPrune;
/*     */   }
/*     */   
/*     */   public void addSeries(XYSeries series) {
/* 144 */     if (series == null)
/* 145 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 147 */     if (series.getAllowDuplicateXValues())
/* 148 */       throw new IllegalArgumentException("Cannot accept XYSeries that allow duplicate values. Use XYSeries(seriesName, <sort>, false) constructor."); 
/* 153 */     updateXPoints(series);
/* 154 */     this.data.add(series);
/* 155 */     series.addChangeListener((SeriesChangeListener)this);
/* 156 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   private void updateXPoints(XYSeries series) {
/* 166 */     if (series == null)
/* 167 */       throw new IllegalArgumentException("Null 'series' not permitted."); 
/* 169 */     HashSet seriesXPoints = new HashSet();
/* 170 */     boolean savedState = this.propagateEvents;
/* 171 */     this.propagateEvents = false;
/* 172 */     for (int itemNo = 0; itemNo < series.getItemCount(); itemNo++) {
/* 173 */       Number xValue = series.getX(itemNo);
/* 174 */       seriesXPoints.add(xValue);
/* 175 */       if (!this.xPoints.contains(xValue)) {
/* 176 */         this.xPoints.add(xValue);
/* 177 */         int seriesCount = this.data.size();
/* 178 */         for (int seriesNo = 0; seriesNo < seriesCount; seriesNo++) {
/* 179 */           XYSeries dataSeries = this.data.get(seriesNo);
/* 180 */           if (!dataSeries.equals(series))
/* 181 */             dataSeries.add(xValue, (Number)null); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 186 */     Iterator iterator = this.xPoints.iterator();
/* 187 */     while (iterator.hasNext()) {
/* 188 */       Number xPoint = iterator.next();
/* 189 */       if (!seriesXPoints.contains(xPoint))
/* 190 */         series.add(xPoint, (Number)null); 
/*     */     } 
/* 193 */     this.propagateEvents = savedState;
/*     */   }
/*     */   
/*     */   public void updateXPoints() {
/* 200 */     this.propagateEvents = false;
/* 201 */     for (int s = 0; s < this.data.size(); s++)
/* 202 */       updateXPoints(this.data.get(s)); 
/* 204 */     if (this.autoPrune)
/* 205 */       prune(); 
/* 207 */     this.propagateEvents = true;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 216 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 225 */     if (this.xPoints == null)
/* 226 */       return 0; 
/* 229 */     return this.xPoints.size();
/*     */   }
/*     */   
/*     */   public XYSeries getSeries(int series) {
/* 241 */     if (series < 0 || series > getSeriesCount())
/* 242 */       throw new IllegalArgumentException("Index outside valid range."); 
/* 245 */     return this.data.get(series);
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 257 */     return getSeries(series).getKey();
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 269 */     return getSeries(series).getItemCount();
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 281 */     XYSeries s = this.data.get(series);
/* 282 */     XYDataItem dataItem = s.getDataItem(item);
/* 283 */     return dataItem.getX();
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 295 */     return this.intervalDelegate.getStartX(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 307 */     return this.intervalDelegate.getEndX(series, item);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int index) {
/* 320 */     XYSeries ts = this.data.get(series);
/* 321 */     XYDataItem dataItem = ts.getDataItem(index);
/* 322 */     return dataItem.getY();
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 334 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 346 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public void removeAllSeries() {
/* 357 */     for (int i = 0; i < this.data.size(); i++) {
/* 358 */       XYSeries series = this.data.get(i);
/* 359 */       series.removeChangeListener((SeriesChangeListener)this);
/*     */     } 
/* 363 */     this.data.clear();
/* 364 */     this.xPoints.clear();
/* 365 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(XYSeries series) {
/* 377 */     if (series == null)
/* 378 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 382 */     if (this.data.contains(series)) {
/* 383 */       series.removeChangeListener((SeriesChangeListener)this);
/* 384 */       this.data.remove(series);
/* 385 */       if (this.data.size() == 0)
/* 386 */         this.xPoints.clear(); 
/* 388 */       fireDatasetChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeSeries(int series) {
/* 402 */     if (series < 0 || series > getSeriesCount())
/* 403 */       throw new IllegalArgumentException("Index outside valid range."); 
/* 407 */     XYSeries s = this.data.get(series);
/* 408 */     s.removeChangeListener((SeriesChangeListener)this);
/* 409 */     this.data.remove(series);
/* 410 */     if (this.data.size() == 0) {
/* 411 */       this.xPoints.clear();
/* 413 */     } else if (this.autoPrune) {
/* 414 */       prune();
/*     */     } 
/* 416 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeAllValuesForX(Number x) {
/* 426 */     if (x == null)
/* 427 */       throw new IllegalArgumentException("Null 'x' argument."); 
/* 429 */     boolean savedState = this.propagateEvents;
/* 430 */     this.propagateEvents = false;
/* 431 */     for (int s = 0; s < this.data.size(); s++) {
/* 432 */       XYSeries series = this.data.get(s);
/* 433 */       series.remove(x);
/*     */     } 
/* 435 */     this.propagateEvents = savedState;
/* 436 */     this.xPoints.remove(x);
/* 437 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   protected boolean canPrune(Number x) {
/* 449 */     for (int s = 0; s < this.data.size(); s++) {
/* 450 */       XYSeries series = this.data.get(s);
/* 451 */       if (series.getY(series.indexOf(x)) != null)
/* 452 */         return false; 
/*     */     } 
/* 455 */     return true;
/*     */   }
/*     */   
/*     */   public void prune() {
/* 462 */     HashSet hs = (HashSet)this.xPoints.clone();
/* 463 */     Iterator iterator = hs.iterator();
/* 464 */     while (iterator.hasNext()) {
/* 465 */       Number x = iterator.next();
/* 466 */       if (canPrune(x))
/* 467 */         removeAllValuesForX(x); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void seriesChanged(SeriesChangeEvent event) {
/* 480 */     if (this.propagateEvents) {
/* 481 */       updateXPoints();
/* 482 */       fireDatasetChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 494 */     if (obj == this)
/* 495 */       return true; 
/* 497 */     if (!(obj instanceof DefaultTableXYDataset))
/* 498 */       return false; 
/* 500 */     DefaultTableXYDataset that = (DefaultTableXYDataset)obj;
/* 501 */     if (this.autoPrune != that.autoPrune)
/* 502 */       return false; 
/* 504 */     if (this.propagateEvents != that.propagateEvents)
/* 505 */       return false; 
/* 507 */     if (!this.intervalDelegate.equals(that.intervalDelegate))
/* 508 */       return false; 
/* 510 */     if (!ObjectUtilities.equal(this.data, that.data))
/* 511 */       return false; 
/* 513 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 523 */     int result = (this.data != null) ? this.data.hashCode() : 0;
/* 524 */     result = 29 * result + ((this.xPoints != null) ? this.xPoints.hashCode() : 0);
/* 526 */     result = 29 * result + (this.propagateEvents ? 1 : 0);
/* 527 */     result = 29 * result + (this.autoPrune ? 1 : 0);
/* 528 */     return result;
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 540 */     return this.intervalDelegate.getDomainLowerBound(includeInterval);
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 552 */     return this.intervalDelegate.getDomainUpperBound(includeInterval);
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 564 */     if (includeInterval)
/* 565 */       return this.intervalDelegate.getDomainBounds(includeInterval); 
/* 568 */     return DatasetUtilities.iterateDomainBounds(this, includeInterval);
/*     */   }
/*     */   
/*     */   public double getIntervalPositionFactor() {
/* 578 */     return this.intervalDelegate.getIntervalPositionFactor();
/*     */   }
/*     */   
/*     */   public void setIntervalPositionFactor(double d) {
/* 590 */     this.intervalDelegate.setIntervalPositionFactor(d);
/* 591 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public double getIntervalWidth() {
/* 600 */     return this.intervalDelegate.getIntervalWidth();
/*     */   }
/*     */   
/*     */   public void setIntervalWidth(double d) {
/* 610 */     this.intervalDelegate.setFixedIntervalWidth(d);
/* 611 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public boolean isAutoWidth() {
/* 621 */     return this.intervalDelegate.isAutoWidth();
/*     */   }
/*     */   
/*     */   public void setAutoWidth(boolean b) {
/* 631 */     this.intervalDelegate.setAutoWidth(b);
/* 632 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\DefaultTableXYDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */