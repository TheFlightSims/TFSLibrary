/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.DefaultKeyedValues2D;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.Dataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class TimeTableXYDataset extends AbstractIntervalXYDataset implements Cloneable, PublicCloneable, IntervalXYDataset, DomainInfo, TableXYDataset {
/*     */   private DefaultKeyedValues2D values;
/*     */   
/*     */   private boolean domainIsPointsInTime;
/*     */   
/*     */   private TimePeriodAnchor xPosition;
/*     */   
/*     */   private Calendar workingCalendar;
/*     */   
/*     */   public TimeTableXYDataset() {
/* 113 */     this(TimeZone.getDefault(), Locale.getDefault());
/*     */   }
/*     */   
/*     */   public TimeTableXYDataset(TimeZone zone) {
/* 123 */     this(zone, Locale.getDefault());
/*     */   }
/*     */   
/*     */   public TimeTableXYDataset(TimeZone zone, Locale locale) {
/* 133 */     if (zone == null)
/* 134 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/* 136 */     if (locale == null)
/* 137 */       throw new IllegalArgumentException("Null 'locale' argument."); 
/* 139 */     this.values = new DefaultKeyedValues2D(true);
/* 140 */     this.workingCalendar = Calendar.getInstance(zone, locale);
/* 141 */     this.xPosition = TimePeriodAnchor.START;
/*     */   }
/*     */   
/*     */   public boolean getDomainIsPointsInTime() {
/* 156 */     return this.domainIsPointsInTime;
/*     */   }
/*     */   
/*     */   public void setDomainIsPointsInTime(boolean flag) {
/* 167 */     this.domainIsPointsInTime = flag;
/* 168 */     notifyListeners(new DatasetChangeEvent(this, (Dataset)this));
/*     */   }
/*     */   
/*     */   public TimePeriodAnchor getXPosition() {
/* 178 */     return this.xPosition;
/*     */   }
/*     */   
/*     */   public void setXPosition(TimePeriodAnchor anchor) {
/* 188 */     if (anchor == null)
/* 189 */       throw new IllegalArgumentException("Null 'anchor' argument."); 
/* 191 */     this.xPosition = anchor;
/* 192 */     notifyListeners(new DatasetChangeEvent(this, (Dataset)this));
/*     */   }
/*     */   
/*     */   public void add(TimePeriod period, double y, String seriesName) {
/* 205 */     add(period, new Double(y), seriesName, true);
/*     */   }
/*     */   
/*     */   public void add(TimePeriod period, Number y, String seriesName, boolean notify) {
/* 219 */     this.values.addValue(y, period, seriesName);
/* 220 */     if (notify)
/* 221 */       fireDatasetChanged(); 
/*     */   }
/*     */   
/*     */   public void remove(TimePeriod period, String seriesName) {
/* 234 */     remove(period, seriesName, true);
/*     */   }
/*     */   
/*     */   public void remove(TimePeriod period, String seriesName, boolean notify) {
/* 247 */     this.values.removeValue(period, seriesName);
/* 248 */     if (notify)
/* 249 */       fireDatasetChanged(); 
/*     */   }
/*     */   
/*     */   public TimePeriod getTimePeriod(int item) {
/* 262 */     return (TimePeriod)this.values.getRowKey(item);
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 271 */     return this.values.getRowCount();
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 284 */     return getItemCount();
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 293 */     return this.values.getColumnCount();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 304 */     return this.values.getColumnKey(series);
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 318 */     return new Double(getXValue(series, item));
/*     */   }
/*     */   
/*     */   public double getXValue(int series, int item) {
/* 330 */     TimePeriod period = (TimePeriod)this.values.getRowKey(item);
/* 331 */     return getXValue(period);
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 343 */     return new Double(getStartXValue(series, item));
/*     */   }
/*     */   
/*     */   public double getStartXValue(int series, int item) {
/* 356 */     TimePeriod period = (TimePeriod)this.values.getRowKey(item);
/* 357 */     return period.getStart().getTime();
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 369 */     return new Double(getEndXValue(series, item));
/*     */   }
/*     */   
/*     */   public double getEndXValue(int series, int item) {
/* 382 */     TimePeriod period = (TimePeriod)this.values.getRowKey(item);
/* 383 */     return period.getEnd().getTime();
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 395 */     return this.values.getValue(item, series);
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 407 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 419 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   private long getXValue(TimePeriod period) {
/* 430 */     long result = 0L;
/* 431 */     if (this.xPosition == TimePeriodAnchor.START) {
/* 432 */       result = period.getStart().getTime();
/* 434 */     } else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 435 */       long t0 = period.getStart().getTime();
/* 436 */       long t1 = period.getEnd().getTime();
/* 437 */       result = t0 + (t1 - t0) / 2L;
/* 439 */     } else if (this.xPosition == TimePeriodAnchor.END) {
/* 440 */       result = period.getEnd().getTime();
/*     */     } 
/* 442 */     return result;
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 454 */     double result = Double.NaN;
/* 455 */     Range r = getDomainBounds(includeInterval);
/* 456 */     if (r != null)
/* 457 */       result = r.getLowerBound(); 
/* 459 */     return result;
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 471 */     double result = Double.NaN;
/* 472 */     Range r = getDomainBounds(includeInterval);
/* 473 */     if (r != null)
/* 474 */       result = r.getUpperBound(); 
/* 476 */     return result;
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 488 */     List keys = this.values.getRowKeys();
/* 489 */     if (keys.isEmpty())
/* 490 */       return null; 
/* 493 */     TimePeriod first = keys.get(0);
/* 494 */     TimePeriod last = keys.get(keys.size() - 1);
/* 496 */     if (!includeInterval || this.domainIsPointsInTime)
/* 497 */       return new Range(getXValue(first), getXValue(last)); 
/* 500 */     return new Range(first.getStart().getTime(), last.getEnd().getTime());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 514 */     if (obj == this)
/* 515 */       return true; 
/* 517 */     if (!(obj instanceof TimeTableXYDataset))
/* 518 */       return false; 
/* 520 */     TimeTableXYDataset that = (TimeTableXYDataset)obj;
/* 521 */     if (this.domainIsPointsInTime != that.domainIsPointsInTime)
/* 522 */       return false; 
/* 524 */     if (this.xPosition != that.xPosition)
/* 525 */       return false; 
/* 527 */     if (!this.workingCalendar.getTimeZone().equals(that.workingCalendar.getTimeZone()))
/* 530 */       return false; 
/* 532 */     if (!this.values.equals(that.values))
/* 533 */       return false; 
/* 535 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 546 */     TimeTableXYDataset clone = (TimeTableXYDataset)super.clone();
/* 547 */     clone.values = (DefaultKeyedValues2D)this.values.clone();
/* 548 */     clone.workingCalendar = (Calendar)this.workingCalendar.clone();
/* 549 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimeTableXYDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */