/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ 
/*     */ public class DynamicTimeSeriesCollection extends AbstractIntervalXYDataset implements IntervalXYDataset, DomainInfo, RangeInfo {
/*     */   public static final int START = 0;
/*     */   
/*     */   public static final int MIDDLE = 1;
/*     */   
/*     */   public static final int END = 2;
/*     */   
/* 109 */   private int maximumItemCount = 2000;
/*     */   
/*     */   protected int historyCount;
/*     */   
/*     */   private Comparable[] seriesKeys;
/*     */   
/* 118 */   private Class timePeriodClass = Minute.class;
/*     */   
/*     */   protected RegularTimePeriod[] pointsInTime;
/*     */   
/*     */   private int seriesCount;
/*     */   
/*     */   protected ValueSequence[] valueHistory;
/*     */   
/*     */   protected Calendar workingCalendar;
/*     */   
/*     */   private int position;
/*     */   
/*     */   private boolean domainIsPointsInTime;
/*     */   
/*     */   private int oldestAt;
/*     */   
/*     */   private int newestAt;
/*     */   
/*     */   private long deltaTime;
/*     */   
/*     */   private Long domainStart;
/*     */   
/*     */   private Long domainEnd;
/*     */   
/*     */   private Range domainRange;
/*     */   
/*     */   protected class ValueSequence {
/*     */     float[] dataPoints;
/*     */     
/*     */     private final DynamicTimeSeriesCollection this$0;
/*     */     
/*     */     public ValueSequence() {
/* 138 */       this(DynamicTimeSeriesCollection.this.maximumItemCount);
/*     */     }
/*     */     
/*     */     public ValueSequence(int length) {
/* 146 */       DynamicTimeSeriesCollection.this = DynamicTimeSeriesCollection.this;
/* 147 */       this.dataPoints = new float[length];
/* 148 */       for (int i = 0; i < length; i++)
/* 149 */         this.dataPoints[i] = 0.0F; 
/*     */     }
/*     */     
/*     */     public void enterData(int index, float value) {
/* 160 */       this.dataPoints[index] = value;
/*     */     }
/*     */     
/*     */     public float getData(int index) {
/* 171 */       return this.dataPoints[index];
/*     */     }
/*     */   }
/*     */   
/* 218 */   private Float minValue = new Float(0.0F);
/*     */   
/* 221 */   private Float maxValue = null;
/*     */   
/*     */   private Range valueRange;
/*     */   
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments) {
/* 235 */     this(nSeries, nMoments, new Millisecond(), TimeZone.getDefault());
/* 236 */     this.newestAt = nMoments - 1;
/*     */   }
/*     */   
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments, TimeZone zone) {
/* 249 */     this(nSeries, nMoments, new Millisecond(), zone);
/* 250 */     this.newestAt = nMoments - 1;
/*     */   }
/*     */   
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments, RegularTimePeriod timeSample) {
/* 263 */     this(nSeries, nMoments, timeSample, TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments, RegularTimePeriod timeSample, TimeZone zone) {
/* 280 */     this.maximumItemCount = nMoments;
/* 281 */     this.historyCount = nMoments;
/* 282 */     this.seriesKeys = new Comparable[nSeries];
/* 284 */     for (int i = 0; i < nSeries; i++)
/* 285 */       this.seriesKeys[i] = ""; 
/* 287 */     this.newestAt = nMoments - 1;
/* 288 */     this.valueHistory = new ValueSequence[nSeries];
/* 289 */     this.timePeriodClass = timeSample.getClass();
/* 292 */     if (this.timePeriodClass == Second.class) {
/* 293 */       this.pointsInTime = (RegularTimePeriod[])new Second[nMoments];
/* 295 */     } else if (this.timePeriodClass == Minute.class) {
/* 296 */       this.pointsInTime = (RegularTimePeriod[])new Minute[nMoments];
/* 298 */     } else if (this.timePeriodClass == Hour.class) {
/* 299 */       this.pointsInTime = (RegularTimePeriod[])new Hour[nMoments];
/*     */     } 
/* 302 */     this.workingCalendar = Calendar.getInstance(zone);
/* 303 */     this.position = 0;
/* 304 */     this.domainIsPointsInTime = true;
/*     */   }
/*     */   
/*     */   public synchronized long setTimeBase(RegularTimePeriod start) {
/* 320 */     if (this.pointsInTime[0] == null) {
/* 321 */       this.pointsInTime[0] = start;
/* 322 */       for (int i = 1; i < this.historyCount; i++)
/* 323 */         this.pointsInTime[i] = this.pointsInTime[i - 1].next(); 
/*     */     } 
/* 326 */     long oldestL = this.pointsInTime[0].getFirstMillisecond(this.workingCalendar);
/* 329 */     long nextL = this.pointsInTime[1].getFirstMillisecond(this.workingCalendar);
/* 332 */     this.deltaTime = nextL - oldestL;
/* 333 */     this.oldestAt = 0;
/* 334 */     this.newestAt = this.historyCount - 1;
/* 335 */     findDomainLimits();
/* 336 */     return this.deltaTime;
/*     */   }
/*     */   
/*     */   protected void findDomainLimits() {
/* 346 */     long endL, startL = getOldestTime().getFirstMillisecond(this.workingCalendar);
/* 348 */     if (this.domainIsPointsInTime) {
/* 349 */       endL = getNewestTime().getFirstMillisecond(this.workingCalendar);
/*     */     } else {
/* 352 */       endL = getNewestTime().getLastMillisecond(this.workingCalendar);
/*     */     } 
/* 354 */     this.domainStart = new Long(startL);
/* 355 */     this.domainEnd = new Long(endL);
/* 356 */     this.domainRange = new Range(startL, endL);
/*     */   }
/*     */   
/*     */   public int getPosition() {
/* 366 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(int position) {
/* 375 */     this.position = position;
/*     */   }
/*     */   
/*     */   public void addSeries(float[] values, int seriesNumber, Comparable seriesKey) {
/* 392 */     invalidateRangeInfo();
/* 394 */     if (values == null)
/* 395 */       throw new IllegalArgumentException("TimeSeriesDataset.addSeries(): cannot add null array of values."); 
/* 398 */     if (seriesNumber >= this.valueHistory.length)
/* 399 */       throw new IllegalArgumentException("TimeSeriesDataset.addSeries(): cannot add more series than specified in c'tor"); 
/* 402 */     if (this.valueHistory[seriesNumber] == null) {
/* 403 */       this.valueHistory[seriesNumber] = new ValueSequence(this.historyCount);
/* 405 */       this.seriesCount++;
/*     */     } 
/* 410 */     int srcLength = values.length;
/* 411 */     int copyLength = this.historyCount;
/* 412 */     boolean fillNeeded = false;
/* 413 */     if (srcLength < this.historyCount) {
/* 414 */       fillNeeded = true;
/* 415 */       copyLength = srcLength;
/*     */     } 
/*     */     int i;
/* 418 */     for (i = 0; i < copyLength; i++)
/* 420 */       this.valueHistory[seriesNumber].enterData(i, values[i]); 
/* 422 */     if (fillNeeded)
/* 423 */       for (i = copyLength; i < this.historyCount; i++)
/* 424 */         this.valueHistory[seriesNumber].enterData(i, 0.0F);  
/* 428 */     if (seriesKey != null)
/* 429 */       this.seriesKeys[seriesNumber] = seriesKey; 
/* 431 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void setSeriesKey(int seriesNumber, Comparable key) {
/* 442 */     this.seriesKeys[seriesNumber] = key;
/*     */   }
/*     */   
/*     */   public void addValue(int seriesNumber, int index, float value) {
/* 454 */     invalidateRangeInfo();
/* 455 */     if (seriesNumber >= this.valueHistory.length)
/* 456 */       throw new IllegalArgumentException("TimeSeriesDataset.addValue(): series #" + seriesNumber + "unspecified in c'tor"); 
/* 461 */     if (this.valueHistory[seriesNumber] == null) {
/* 462 */       this.valueHistory[seriesNumber] = new ValueSequence(this.historyCount);
/* 464 */       this.seriesCount++;
/*     */     } 
/* 469 */     this.valueHistory[seriesNumber].enterData(index, value);
/* 471 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 480 */     return this.seriesCount;
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 494 */     return this.historyCount;
/*     */   }
/*     */   
/*     */   protected int translateGet(int toFetch) {
/* 507 */     if (this.oldestAt == 0)
/* 508 */       return toFetch; 
/* 511 */     int newIndex = toFetch + this.oldestAt;
/* 512 */     if (newIndex >= this.historyCount)
/* 513 */       newIndex -= this.historyCount; 
/* 515 */     return newIndex;
/*     */   }
/*     */   
/*     */   public int offsetFromNewest(int delta) {
/* 526 */     return wrapOffset(this.newestAt + delta);
/*     */   }
/*     */   
/*     */   public int offsetFromOldest(int delta) {
/* 537 */     return wrapOffset(this.oldestAt + delta);
/*     */   }
/*     */   
/*     */   protected int wrapOffset(int protoIndex) {
/* 548 */     int tmp = protoIndex;
/* 549 */     if (tmp >= this.historyCount) {
/* 550 */       tmp -= this.historyCount;
/* 552 */     } else if (tmp < 0) {
/* 553 */       tmp += this.historyCount;
/*     */     } 
/* 555 */     return tmp;
/*     */   }
/*     */   
/*     */   public synchronized RegularTimePeriod advanceTime() {
/* 566 */     RegularTimePeriod nextInstant = this.pointsInTime[this.newestAt].next();
/* 567 */     this.newestAt = this.oldestAt;
/* 574 */     boolean extremaChanged = false;
/* 575 */     float oldMax = 0.0F;
/* 576 */     if (this.maxValue != null)
/* 577 */       oldMax = this.maxValue.floatValue(); 
/* 579 */     for (int s = 0; s < getSeriesCount(); s++) {
/* 580 */       if (this.valueHistory[s].getData(this.oldestAt) == oldMax)
/* 581 */         extremaChanged = true; 
/* 583 */       if (extremaChanged)
/*     */         break; 
/*     */     } 
/* 587 */     if (extremaChanged)
/* 588 */       invalidateRangeInfo(); 
/* 591 */     float wiper = 0.0F;
/* 592 */     for (int i = 0; i < getSeriesCount(); i++)
/* 593 */       this.valueHistory[i].enterData(this.newestAt, wiper); 
/* 596 */     this.pointsInTime[this.newestAt] = nextInstant;
/* 598 */     this.oldestAt++;
/* 599 */     if (this.oldestAt >= this.historyCount)
/* 600 */       this.oldestAt = 0; 
/* 603 */     long startL = this.domainStart.longValue();
/* 604 */     this.domainStart = new Long(startL + this.deltaTime);
/* 605 */     long endL = this.domainEnd.longValue();
/* 606 */     this.domainEnd = new Long(endL + this.deltaTime);
/* 607 */     this.domainRange = new Range(startL, endL);
/* 608 */     fireSeriesChanged();
/* 609 */     return nextInstant;
/*     */   }
/*     */   
/*     */   public void invalidateRangeInfo() {
/* 618 */     this.maxValue = null;
/* 619 */     this.valueRange = null;
/*     */   }
/*     */   
/*     */   protected double findMaxValue() {
/* 628 */     double max = 0.0D;
/* 629 */     for (int s = 0; s < getSeriesCount(); s++) {
/* 630 */       for (int i = 0; i < this.historyCount; i++) {
/* 631 */         double tmp = getYValue(s, i);
/* 632 */         if (tmp > max)
/* 633 */           max = tmp; 
/*     */       } 
/*     */     } 
/* 637 */     return max;
/*     */   }
/*     */   
/*     */   public int getOldestIndex() {
/* 648 */     return this.oldestAt;
/*     */   }
/*     */   
/*     */   public int getNewestIndex() {
/* 657 */     return this.newestAt;
/*     */   }
/*     */   
/*     */   public void appendData(float[] newData) {
/* 668 */     int nDataPoints = newData.length;
/* 669 */     if (nDataPoints > this.valueHistory.length)
/* 670 */       throw new IllegalArgumentException("More data than series to put them in"); 
/* 675 */     for (int s = 0; s < nDataPoints; s++) {
/* 678 */       if (this.valueHistory[s] == null)
/* 679 */         this.valueHistory[s] = new ValueSequence(this.historyCount); 
/* 681 */       this.valueHistory[s].enterData(this.newestAt, newData[s]);
/*     */     } 
/* 683 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void appendData(float[] newData, int insertionIndex, int refresh) {
/* 695 */     int nDataPoints = newData.length;
/* 696 */     if (nDataPoints > this.valueHistory.length)
/* 697 */       throw new IllegalArgumentException("More data than series to put them in"); 
/* 701 */     for (int s = 0; s < nDataPoints; s++) {
/* 702 */       if (this.valueHistory[s] == null)
/* 703 */         this.valueHistory[s] = new ValueSequence(this.historyCount); 
/* 705 */       this.valueHistory[s].enterData(insertionIndex, newData[s]);
/*     */     } 
/* 708 */     insertionIndex++;
/* 709 */     if (refresh > 0 && insertionIndex % refresh == 0)
/* 710 */       fireSeriesChanged(); 
/*     */   }
/*     */   
/*     */   public RegularTimePeriod getNewestTime() {
/* 721 */     return this.pointsInTime[this.newestAt];
/*     */   }
/*     */   
/*     */   public RegularTimePeriod getOldestTime() {
/* 730 */     return this.pointsInTime[this.oldestAt];
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 744 */     RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
/* 745 */     return new Long(getX(tp));
/*     */   }
/*     */   
/*     */   public double getYValue(int series, int item) {
/* 759 */     ValueSequence values = this.valueHistory[series];
/* 760 */     return values.getData(translateGet(item));
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 772 */     return new Float(getYValue(series, item));
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 784 */     RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
/* 785 */     return new Long(tp.getFirstMillisecond(this.workingCalendar));
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 797 */     RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
/* 798 */     return new Long(tp.getLastMillisecond(this.workingCalendar));
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 810 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 822 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 844 */     return this.seriesKeys[series];
/*     */   }
/*     */   
/*     */   protected void fireSeriesChanged() {
/* 851 */     seriesChanged(new SeriesChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 868 */     return this.domainStart.doubleValue();
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 881 */     return this.domainEnd.doubleValue();
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 894 */     if (this.domainRange == null)
/* 895 */       findDomainLimits(); 
/* 897 */     return this.domainRange;
/*     */   }
/*     */   
/*     */   private long getX(RegularTimePeriod period) {
/* 908 */     switch (this.position) {
/*     */       case 0:
/* 910 */         return period.getFirstMillisecond(this.workingCalendar);
/*     */       case 1:
/* 912 */         return period.getMiddleMillisecond(this.workingCalendar);
/*     */       case 2:
/* 914 */         return period.getLastMillisecond(this.workingCalendar);
/*     */     } 
/* 916 */     return period.getMiddleMillisecond(this.workingCalendar);
/*     */   }
/*     */   
/*     */   public double getRangeLowerBound(boolean includeInterval) {
/* 935 */     double result = Double.NaN;
/* 936 */     if (this.minValue != null)
/* 937 */       result = this.minValue.doubleValue(); 
/* 939 */     return result;
/*     */   }
/*     */   
/*     */   public double getRangeUpperBound(boolean includeInterval) {
/* 951 */     double result = Double.NaN;
/* 952 */     if (this.maxValue != null)
/* 953 */       result = this.maxValue.doubleValue(); 
/* 955 */     return result;
/*     */   }
/*     */   
/*     */   public Range getRangeBounds(boolean includeInterval) {
/* 967 */     if (this.valueRange == null) {
/* 968 */       double max = getRangeUpperBound(includeInterval);
/* 969 */       this.valueRange = new Range(0.0D, max);
/*     */     } 
/* 971 */     return this.valueRange;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\DynamicTimeSeriesCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */