/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.SeriesChangeListener;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class TimePeriodValuesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, DomainInfo, Serializable {
/*     */   private static final long serialVersionUID = -3077934065236454199L;
/*     */   
/*     */   public TimePeriodValuesCollection() {
/*  96 */     this((TimePeriodValues)null);
/*     */   }
/*     */   
/* 106 */   private List data = new ArrayList();
/*     */   
/* 107 */   private TimePeriodAnchor xPosition = TimePeriodAnchor.MIDDLE;
/*     */   
/*     */   private boolean domainIsPointsInTime = true;
/*     */   
/*     */   public TimePeriodValuesCollection(TimePeriodValues series) {
/* 109 */     if (series != null) {
/* 110 */       this.data.add(series);
/* 111 */       series.addChangeListener((SeriesChangeListener)this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TimePeriodAnchor getXPosition() {
/* 121 */     return this.xPosition;
/*     */   }
/*     */   
/*     */   public void setXPosition(TimePeriodAnchor position) {
/* 130 */     if (position == null)
/* 131 */       throw new IllegalArgumentException("Null 'position' argument."); 
/* 133 */     this.xPosition = position;
/*     */   }
/*     */   
/*     */   public boolean getDomainIsPointsInTime() {
/* 146 */     return this.domainIsPointsInTime;
/*     */   }
/*     */   
/*     */   public void setDomainIsPointsInTime(boolean flag) {
/* 156 */     this.domainIsPointsInTime = flag;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 165 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public TimePeriodValues getSeries(int series) {
/* 177 */     if (series < 0 || series > getSeriesCount())
/* 178 */       throw new IllegalArgumentException("Index 'series' out of range."); 
/* 181 */     return this.data.get(series);
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 194 */     return getSeries(series).getKey();
/*     */   }
/*     */   
/*     */   public void addSeries(TimePeriodValues series) {
/* 206 */     if (series == null)
/* 207 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 210 */     this.data.add(series);
/* 211 */     series.addChangeListener((SeriesChangeListener)this);
/* 212 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(TimePeriodValues series) {
/* 223 */     if (series == null)
/* 224 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 226 */     this.data.remove(series);
/* 227 */     series.removeChangeListener((SeriesChangeListener)this);
/* 228 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(int index) {
/* 238 */     TimePeriodValues series = getSeries(index);
/* 239 */     if (series != null)
/* 240 */       removeSeries(series); 
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 254 */     return getSeries(series).getItemCount();
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 266 */     TimePeriodValues ts = this.data.get(series);
/* 267 */     TimePeriodValue dp = ts.getDataItem(item);
/* 268 */     TimePeriod period = dp.getPeriod();
/* 269 */     return new Long(getX(period));
/*     */   }
/*     */   
/*     */   private long getX(TimePeriod period) {
/* 281 */     if (this.xPosition == TimePeriodAnchor.START)
/* 282 */       return period.getStart().getTime(); 
/* 284 */     if (this.xPosition == TimePeriodAnchor.MIDDLE)
/* 285 */       return period.getStart().getTime() / 2L + period.getEnd().getTime() / 2L; 
/* 288 */     if (this.xPosition == TimePeriodAnchor.END)
/* 289 */       return period.getEnd().getTime(); 
/* 292 */     throw new IllegalStateException("TimePeriodAnchor unknown.");
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 306 */     TimePeriodValues ts = this.data.get(series);
/* 307 */     TimePeriodValue dp = ts.getDataItem(item);
/* 308 */     return new Long(dp.getPeriod().getStart().getTime());
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 320 */     TimePeriodValues ts = this.data.get(series);
/* 321 */     TimePeriodValue dp = ts.getDataItem(item);
/* 322 */     return new Long(dp.getPeriod().getEnd().getTime());
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 334 */     TimePeriodValues ts = this.data.get(series);
/* 335 */     TimePeriodValue dp = ts.getDataItem(item);
/* 336 */     return dp.getValue();
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 348 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 360 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 372 */     double result = Double.NaN;
/* 373 */     Range r = getDomainBounds(includeInterval);
/* 374 */     if (r != null)
/* 375 */       result = r.getLowerBound(); 
/* 377 */     return result;
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 389 */     double result = Double.NaN;
/* 390 */     Range r = getDomainBounds(includeInterval);
/* 391 */     if (r != null)
/* 392 */       result = r.getUpperBound(); 
/* 394 */     return result;
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 406 */     Range result = null;
/* 407 */     Range temp = null;
/* 408 */     Iterator iterator = this.data.iterator();
/* 409 */     while (iterator.hasNext()) {
/* 410 */       TimePeriodValues series = iterator.next();
/* 411 */       int count = series.getItemCount();
/* 412 */       if (count > 0) {
/* 413 */         TimePeriod start = series.getTimePeriod(series.getMinStartIndex());
/* 416 */         TimePeriod end = series.getTimePeriod(series.getMaxEndIndex());
/* 417 */         if (this.domainIsPointsInTime) {
/* 418 */           if (this.xPosition == TimePeriodAnchor.START) {
/* 419 */             TimePeriod maxStart = series.getTimePeriod(series.getMaxStartIndex());
/* 422 */             temp = new Range(start.getStart().getTime(), maxStart.getStart().getTime());
/* 427 */           } else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 428 */             TimePeriod minMiddle = series.getTimePeriod(series.getMinMiddleIndex());
/* 431 */             long s1 = minMiddle.getStart().getTime();
/* 432 */             long e1 = minMiddle.getEnd().getTime();
/* 433 */             TimePeriod maxMiddle = series.getTimePeriod(series.getMaxMiddleIndex());
/* 436 */             long s2 = maxMiddle.getStart().getTime();
/* 437 */             long e2 = maxMiddle.getEnd().getTime();
/* 438 */             temp = new Range((s1 + (e1 - s1) / 2L), (s2 + (e2 - s2) / 2L));
/* 442 */           } else if (this.xPosition == TimePeriodAnchor.END) {
/* 443 */             TimePeriod minEnd = series.getTimePeriod(series.getMinEndIndex());
/* 446 */             temp = new Range(minEnd.getEnd().getTime(), end.getEnd().getTime());
/*     */           } 
/*     */         } else {
/* 452 */           temp = new Range(start.getStart().getTime(), end.getEnd().getTime());
/*     */         } 
/* 456 */         result = Range.combine(result, temp);
/*     */       } 
/*     */     } 
/* 459 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 470 */     if (obj == this)
/* 471 */       return true; 
/* 473 */     if (!(obj instanceof TimePeriodValuesCollection))
/* 474 */       return false; 
/* 476 */     TimePeriodValuesCollection that = (TimePeriodValuesCollection)obj;
/* 477 */     if (this.domainIsPointsInTime != that.domainIsPointsInTime)
/* 478 */       return false; 
/* 480 */     if (this.xPosition != that.xPosition)
/* 481 */       return false; 
/* 483 */     if (!ObjectUtilities.equal(this.data, that.data))
/* 484 */       return false; 
/* 486 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimePeriodValuesCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */