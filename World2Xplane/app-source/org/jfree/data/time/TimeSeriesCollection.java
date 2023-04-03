/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.Dataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.SeriesChangeListener;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class TimeSeriesCollection extends AbstractIntervalXYDataset implements XYDataset, IntervalXYDataset, DomainInfo, Serializable {
/*     */   private static final long serialVersionUID = 834149929022371137L;
/*     */   
/*     */   private List data;
/*     */   
/*     */   private Calendar workingCalendar;
/*     */   
/*     */   private TimePeriodAnchor xPosition;
/*     */   
/*     */   private boolean domainIsPointsInTime;
/*     */   
/*     */   public TimeSeriesCollection() {
/* 135 */     this((TimeSeries)null, TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public TimeSeriesCollection(TimeZone zone) {
/* 145 */     this((TimeSeries)null, zone);
/*     */   }
/*     */   
/*     */   public TimeSeriesCollection(TimeSeries series) {
/* 155 */     this(series, TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public TimeSeriesCollection(TimeSeries series, TimeZone zone) {
/* 169 */     if (zone == null)
/* 170 */       zone = TimeZone.getDefault(); 
/* 172 */     this.workingCalendar = Calendar.getInstance(zone);
/* 173 */     this.data = new ArrayList();
/* 174 */     if (series != null) {
/* 175 */       this.data.add(series);
/* 176 */       series.addChangeListener((SeriesChangeListener)this);
/*     */     } 
/* 178 */     this.xPosition = TimePeriodAnchor.START;
/* 179 */     this.domainIsPointsInTime = true;
/*     */   }
/*     */   
/*     */   public boolean getDomainIsPointsInTime() {
/* 193 */     return this.domainIsPointsInTime;
/*     */   }
/*     */   
/*     */   public void setDomainIsPointsInTime(boolean flag) {
/* 203 */     this.domainIsPointsInTime = flag;
/* 204 */     notifyListeners(new DatasetChangeEvent(this, (Dataset)this));
/*     */   }
/*     */   
/*     */   public TimePeriodAnchor getXPosition() {
/* 215 */     return this.xPosition;
/*     */   }
/*     */   
/*     */   public void setXPosition(TimePeriodAnchor anchor) {
/* 226 */     if (anchor == null)
/* 227 */       throw new IllegalArgumentException("Null 'anchor' argument."); 
/* 229 */     this.xPosition = anchor;
/* 230 */     notifyListeners(new DatasetChangeEvent(this, (Dataset)this));
/*     */   }
/*     */   
/*     */   public List getSeries() {
/* 239 */     return Collections.unmodifiableList(this.data);
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 248 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public TimeSeries getSeries(int series) {
/* 259 */     if (series < 0 || series >= getSeriesCount())
/* 260 */       throw new IllegalArgumentException("The 'series' argument is out of bounds (" + series + ")."); 
/* 264 */     return this.data.get(series);
/*     */   }
/*     */   
/*     */   public TimeSeries getSeries(String key) {
/* 276 */     TimeSeries result = null;
/* 277 */     Iterator iterator = this.data.iterator();
/* 278 */     while (iterator.hasNext()) {
/* 279 */       TimeSeries series = iterator.next();
/* 280 */       Comparable k = series.getKey();
/* 281 */       if (k != null && k.equals(key))
/* 282 */         result = series; 
/*     */     } 
/* 285 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 298 */     return getSeries(series).getKey();
/*     */   }
/*     */   
/*     */   public void addSeries(TimeSeries series) {
/* 308 */     if (series == null)
/* 309 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 311 */     this.data.add(series);
/* 312 */     series.addChangeListener((SeriesChangeListener)this);
/* 313 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(TimeSeries series) {
/* 323 */     if (series == null)
/* 324 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 326 */     this.data.remove(series);
/* 327 */     series.removeChangeListener((SeriesChangeListener)this);
/* 328 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(int index) {
/* 337 */     TimeSeries series = getSeries(index);
/* 338 */     if (series != null)
/* 339 */       removeSeries(series); 
/*     */   }
/*     */   
/*     */   public void removeAllSeries() {
/* 351 */     for (int i = 0; i < this.data.size(); i++) {
/* 352 */       TimeSeries series = this.data.get(i);
/* 353 */       series.removeChangeListener((SeriesChangeListener)this);
/*     */     } 
/* 357 */     this.data.clear();
/* 358 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 371 */     return getSeries(series).getItemCount();
/*     */   }
/*     */   
/*     */   public double getXValue(int series, int item) {
/* 383 */     TimeSeries s = this.data.get(series);
/* 384 */     TimeSeriesDataItem i = s.getDataItem(item);
/* 385 */     RegularTimePeriod period = i.getPeriod();
/* 386 */     return getX(period);
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 398 */     TimeSeries ts = this.data.get(series);
/* 399 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 400 */     RegularTimePeriod period = dp.getPeriod();
/* 401 */     return new Long(getX(period));
/*     */   }
/*     */   
/*     */   protected synchronized long getX(RegularTimePeriod period) {
/* 413 */     long result = 0L;
/* 414 */     if (this.xPosition == TimePeriodAnchor.START) {
/* 415 */       result = period.getFirstMillisecond(this.workingCalendar);
/* 417 */     } else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 418 */       result = period.getMiddleMillisecond(this.workingCalendar);
/* 420 */     } else if (this.xPosition == TimePeriodAnchor.END) {
/* 421 */       result = period.getLastMillisecond(this.workingCalendar);
/*     */     } 
/* 423 */     return result;
/*     */   }
/*     */   
/*     */   public synchronized Number getStartX(int series, int item) {
/* 436 */     TimeSeries ts = this.data.get(series);
/* 437 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 438 */     return new Long(dp.getPeriod().getFirstMillisecond(this.workingCalendar));
/*     */   }
/*     */   
/*     */   public synchronized Number getEndX(int series, int item) {
/* 452 */     TimeSeries ts = this.data.get(series);
/* 453 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 454 */     return new Long(dp.getPeriod().getLastMillisecond(this.workingCalendar));
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 468 */     TimeSeries ts = this.data.get(series);
/* 469 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 470 */     return dp.getValue();
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 482 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 494 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public int[] getSurroundingItems(int series, long milliseconds) {
/* 509 */     int[] result = { -1, -1 };
/* 510 */     TimeSeries timeSeries = getSeries(series);
/* 511 */     for (int i = 0; i < timeSeries.getItemCount(); i++) {
/* 512 */       Number x = getX(series, i);
/* 513 */       long m = x.longValue();
/* 514 */       if (m <= milliseconds)
/* 515 */         result[0] = i; 
/* 517 */       if (m >= milliseconds) {
/* 518 */         result[1] = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 522 */     return result;
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 534 */     double result = Double.NaN;
/* 535 */     Range r = getDomainBounds(includeInterval);
/* 536 */     if (r != null)
/* 537 */       result = r.getLowerBound(); 
/* 539 */     return result;
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 551 */     double result = Double.NaN;
/* 552 */     Range r = getDomainBounds(includeInterval);
/* 553 */     if (r != null)
/* 554 */       result = r.getUpperBound(); 
/* 556 */     return result;
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 568 */     Range result = null;
/* 569 */     Iterator iterator = this.data.iterator();
/* 570 */     while (iterator.hasNext()) {
/* 571 */       TimeSeries series = iterator.next();
/* 572 */       int count = series.getItemCount();
/* 573 */       if (count > 0) {
/*     */         Range temp;
/* 574 */         RegularTimePeriod start = series.getTimePeriod(0);
/* 575 */         RegularTimePeriod end = series.getTimePeriod(count - 1);
/* 577 */         if (!includeInterval || this.domainIsPointsInTime) {
/* 578 */           temp = new Range(getX(start), getX(end));
/*     */         } else {
/* 581 */           temp = new Range(start.getFirstMillisecond(this.workingCalendar), end.getLastMillisecond(this.workingCalendar));
/*     */         } 
/* 586 */         result = Range.combine(result, temp);
/*     */       } 
/*     */     } 
/* 589 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 600 */     if (obj == this)
/* 601 */       return true; 
/* 603 */     if (!(obj instanceof TimeSeriesCollection))
/* 604 */       return false; 
/* 606 */     TimeSeriesCollection that = (TimeSeriesCollection)obj;
/* 607 */     if (this.xPosition != that.xPosition)
/* 608 */       return false; 
/* 610 */     if (this.domainIsPointsInTime != that.domainIsPointsInTime)
/* 611 */       return false; 
/* 613 */     if (!ObjectUtilities.equal(this.data, that.data))
/* 614 */       return false; 
/* 616 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 626 */     int result = this.data.hashCode();
/* 627 */     result = 29 * result + ((this.workingCalendar != null) ? this.workingCalendar.hashCode() : 0);
/* 629 */     result = 29 * result + ((this.xPosition != null) ? this.xPosition.hashCode() : 0);
/* 631 */     result = 29 * result + (this.domainIsPointsInTime ? 1 : 0);
/* 632 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimeSeriesCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */