/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.Series;
/*     */ import org.jfree.data.general.SeriesException;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class TimeSeries extends Series implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -5032960206869675528L;
/*     */   
/*     */   protected static final String DEFAULT_DOMAIN_DESCRIPTION = "Time";
/*     */   
/*     */   protected static final String DEFAULT_RANGE_DESCRIPTION = "Value";
/*     */   
/*     */   private String domain;
/*     */   
/*     */   private String range;
/*     */   
/*     */   protected Class timePeriodClass;
/*     */   
/*     */   protected List data;
/*     */   
/*     */   private int maximumItemCount;
/*     */   
/*     */   private long maximumItemAge;
/*     */   
/*     */   public TimeSeries(String name) {
/* 123 */     this(name, "Time", "Value", Day.class);
/*     */   }
/*     */   
/*     */   public TimeSeries(String name, Class timePeriodClass) {
/* 137 */     this(name, "Time", "Value", timePeriodClass);
/*     */   }
/*     */   
/*     */   public TimeSeries(String name, String domain, String range, Class timePeriodClass) {
/* 159 */     super(name);
/* 160 */     this.domain = domain;
/* 161 */     this.range = range;
/* 162 */     this.timePeriodClass = timePeriodClass;
/* 163 */     this.data = new ArrayList();
/* 164 */     this.maximumItemCount = Integer.MAX_VALUE;
/* 165 */     this.maximumItemAge = Long.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public String getDomainDescription() {
/* 175 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomainDescription(String description) {
/* 186 */     String old = this.domain;
/* 187 */     this.domain = description;
/* 188 */     firePropertyChange("Domain", old, description);
/*     */   }
/*     */   
/*     */   public String getRangeDescription() {
/* 197 */     return this.range;
/*     */   }
/*     */   
/*     */   public void setRangeDescription(String description) {
/* 207 */     String old = this.range;
/* 208 */     this.range = description;
/* 209 */     firePropertyChange("Range", old, description);
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 218 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public List getItems() {
/* 228 */     return Collections.unmodifiableList(this.data);
/*     */   }
/*     */   
/*     */   public int getMaximumItemCount() {
/* 240 */     return this.maximumItemCount;
/*     */   }
/*     */   
/*     */   public void setMaximumItemCount(int maximum) {
/* 255 */     if (maximum < 0)
/* 256 */       throw new IllegalArgumentException("Negative 'maximum' argument."); 
/* 258 */     this.maximumItemCount = maximum;
/* 259 */     int count = this.data.size();
/* 260 */     if (count > maximum)
/* 261 */       delete(0, count - maximum - 1); 
/*     */   }
/*     */   
/*     */   public long getMaximumItemAge() {
/* 273 */     return this.maximumItemAge;
/*     */   }
/*     */   
/*     */   public void setMaximumItemAge(long periods) {
/* 289 */     if (periods < 0L)
/* 290 */       throw new IllegalArgumentException("Negative 'periods' argument."); 
/* 292 */     this.maximumItemAge = periods;
/* 293 */     removeAgedItems(true);
/*     */   }
/*     */   
/*     */   public Class getTimePeriodClass() {
/* 306 */     return this.timePeriodClass;
/*     */   }
/*     */   
/*     */   public TimeSeriesDataItem getDataItem(int index) {
/* 317 */     return this.data.get(index);
/*     */   }
/*     */   
/*     */   public TimeSeriesDataItem getDataItem(RegularTimePeriod period) {
/* 332 */     if (period == null)
/* 333 */       throw new IllegalArgumentException("Null 'period' argument"); 
/* 337 */     TimeSeriesDataItem dummy = new TimeSeriesDataItem(period, -2.147483648E9D);
/* 340 */     int index = Collections.binarySearch(this.data, dummy);
/* 341 */     if (index >= 0)
/* 342 */       return this.data.get(index); 
/* 345 */     return null;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod getTimePeriod(int index) {
/* 358 */     return getDataItem(index).getPeriod();
/*     */   }
/*     */   
/*     */   public RegularTimePeriod getNextTimePeriod() {
/* 368 */     RegularTimePeriod last = getTimePeriod(getItemCount() - 1);
/* 369 */     return last.next();
/*     */   }
/*     */   
/*     */   public Collection getTimePeriods() {
/* 378 */     Collection result = new ArrayList();
/* 379 */     for (int i = 0; i < getItemCount(); i++)
/* 380 */       result.add(getTimePeriod(i)); 
/* 382 */     return result;
/*     */   }
/*     */   
/*     */   public Collection getTimePeriodsUniqueToOtherSeries(TimeSeries series) {
/* 395 */     Collection result = new ArrayList();
/* 397 */     for (int i = 0; i < series.getItemCount(); i++) {
/* 398 */       RegularTimePeriod period = series.getTimePeriod(i);
/* 399 */       int index = getIndex(period);
/* 400 */       if (index < 0)
/* 401 */         result.add(period); 
/*     */     } 
/* 406 */     return result;
/*     */   }
/*     */   
/*     */   public int getIndex(RegularTimePeriod period) {
/* 421 */     if (period == null)
/* 422 */       throw new IllegalArgumentException("Null 'period' argument."); 
/* 426 */     TimeSeriesDataItem dummy = new TimeSeriesDataItem(period, -2.147483648E9D);
/* 429 */     int index = Collections.binarySearch(this.data, dummy);
/* 430 */     return index;
/*     */   }
/*     */   
/*     */   public Number getValue(int index) {
/* 442 */     return getDataItem(index).getValue();
/*     */   }
/*     */   
/*     */   public Number getValue(RegularTimePeriod period) {
/* 455 */     int index = getIndex(period);
/* 456 */     if (index >= 0)
/* 457 */       return getValue(index); 
/* 460 */     return null;
/*     */   }
/*     */   
/*     */   public void add(TimeSeriesDataItem item) {
/* 474 */     add(item, true);
/*     */   }
/*     */   
/*     */   public void add(TimeSeriesDataItem item, boolean notify) {
/* 487 */     if (item == null)
/* 488 */       throw new IllegalArgumentException("Null 'item' argument."); 
/* 490 */     if (!item.getPeriod().getClass().equals(this.timePeriodClass)) {
/* 491 */       StringBuffer b = new StringBuffer();
/* 492 */       b.append("You are trying to add data where the time period class ");
/* 493 */       b.append("is ");
/* 494 */       b.append(item.getPeriod().getClass().getName());
/* 495 */       b.append(", but the TimeSeries is expecting an instance of ");
/* 496 */       b.append(this.timePeriodClass.getName());
/* 497 */       b.append(".");
/* 498 */       throw new SeriesException(b.toString());
/*     */     } 
/* 502 */     boolean added = false;
/* 503 */     int count = getItemCount();
/* 504 */     if (count == 0) {
/* 505 */       this.data.add(item);
/* 506 */       added = true;
/*     */     } else {
/* 509 */       RegularTimePeriod last = getTimePeriod(getItemCount() - 1);
/* 510 */       if (item.getPeriod().compareTo((T)last) > 0) {
/* 511 */         this.data.add(item);
/* 512 */         added = true;
/*     */       } else {
/* 515 */         int index = Collections.binarySearch(this.data, item);
/* 516 */         if (index < 0) {
/* 517 */           this.data.add(-index - 1, item);
/* 518 */           added = true;
/*     */         } else {
/* 521 */           StringBuffer b = new StringBuffer();
/* 522 */           b.append("You are attempting to add an observation for ");
/* 523 */           b.append("the time period ");
/* 524 */           b.append(item.getPeriod().toString());
/* 525 */           b.append(" but the series already contains an observation");
/* 526 */           b.append(" for that time period. Duplicates are not ");
/* 527 */           b.append("permitted.  Try using the addOrUpdate() method.");
/* 528 */           throw new SeriesException(b.toString());
/*     */         } 
/*     */       } 
/*     */     } 
/* 532 */     if (added) {
/* 534 */       if (getItemCount() > this.maximumItemCount)
/* 535 */         this.data.remove(0); 
/* 538 */       removeAgedItems(false);
/* 541 */       if (notify)
/* 542 */         fireSeriesChanged(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(RegularTimePeriod period, double value) {
/* 558 */     add(period, value, true);
/*     */   }
/*     */   
/*     */   public void add(RegularTimePeriod period, double value, boolean notify) {
/* 572 */     TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
/* 573 */     add(item, notify);
/*     */   }
/*     */   
/*     */   public void add(RegularTimePeriod period, Number value) {
/* 586 */     add(period, value, true);
/*     */   }
/*     */   
/*     */   public void add(RegularTimePeriod period, Number value, boolean notify) {
/* 600 */     TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
/* 601 */     add(item, notify);
/*     */   }
/*     */   
/*     */   public void update(RegularTimePeriod period, Number value) {
/* 612 */     TimeSeriesDataItem temp = new TimeSeriesDataItem(period, value);
/* 613 */     int index = Collections.binarySearch(this.data, temp);
/* 614 */     if (index >= 0) {
/* 615 */       TimeSeriesDataItem pair = this.data.get(index);
/* 616 */       pair.setValue(value);
/* 617 */       fireSeriesChanged();
/*     */     } else {
/* 620 */       throw new SeriesException("TimeSeries.update(TimePeriod, Number):  period does not exist.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update(int index, Number value) {
/* 634 */     TimeSeriesDataItem item = getDataItem(index);
/* 635 */     item.setValue(value);
/* 636 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public TimeSeries addAndOrUpdate(TimeSeries series) {
/* 648 */     TimeSeries overwritten = new TimeSeries("Overwritten values from: " + getKey(), series.getTimePeriodClass());
/* 651 */     for (int i = 0; i < series.getItemCount(); i++) {
/* 652 */       TimeSeriesDataItem item = series.getDataItem(i);
/* 653 */       TimeSeriesDataItem oldItem = addOrUpdate(item.getPeriod(), item.getValue());
/* 656 */       if (oldItem != null)
/* 657 */         overwritten.add(oldItem); 
/*     */     } 
/* 660 */     return overwritten;
/*     */   }
/*     */   
/*     */   public TimeSeriesDataItem addOrUpdate(RegularTimePeriod period, double value) {
/* 677 */     return addOrUpdate(period, new Double(value));
/*     */   }
/*     */   
/*     */   public TimeSeriesDataItem addOrUpdate(RegularTimePeriod period, Number value) {
/* 695 */     if (period == null)
/* 696 */       throw new IllegalArgumentException("Null 'period' argument."); 
/* 698 */     TimeSeriesDataItem overwritten = null;
/* 700 */     TimeSeriesDataItem key = new TimeSeriesDataItem(period, value);
/* 701 */     int index = Collections.binarySearch(this.data, key);
/* 702 */     if (index >= 0) {
/* 703 */       TimeSeriesDataItem existing = this.data.get(index);
/* 705 */       overwritten = (TimeSeriesDataItem)existing.clone();
/* 706 */       existing.setValue(value);
/* 707 */       removeAgedItems(false);
/* 710 */       fireSeriesChanged();
/*     */     } else {
/* 713 */       this.data.add(-index - 1, new TimeSeriesDataItem(period, value));
/* 716 */       if (getItemCount() > this.maximumItemCount)
/* 717 */         this.data.remove(0); 
/* 720 */       removeAgedItems(false);
/* 723 */       fireSeriesChanged();
/*     */     } 
/* 725 */     return overwritten;
/*     */   }
/*     */   
/*     */   public void removeAgedItems(boolean notify) {
/* 740 */     if (getItemCount() > 1) {
/* 741 */       long latest = getTimePeriod(getItemCount() - 1).getSerialIndex();
/* 742 */       boolean removed = false;
/* 744 */       while (latest - getTimePeriod(0).getSerialIndex() >= this.maximumItemAge) {
/* 745 */         this.data.remove(0);
/* 746 */         removed = true;
/*     */       } 
/* 748 */       if (removed && notify)
/* 749 */         fireSeriesChanged(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeAgedItems(long latest, boolean notify) {
/* 766 */     if (getItemCount() > 1)
/* 768 */       while (latest - getTimePeriod(0).getSerialIndex() >= this.maximumItemAge)
/* 769 */         this.data.remove(0);  
/*     */   }
/*     */   
/*     */   public void clear() {
/* 780 */     if (this.data.size() > 0) {
/* 781 */       this.data.clear();
/* 782 */       fireSeriesChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void delete(RegularTimePeriod period) {
/* 795 */     int index = getIndex(period);
/* 796 */     this.data.remove(index);
/* 797 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void delete(int start, int end) {
/* 807 */     for (int i = 0; i <= end - start; i++)
/* 808 */       this.data.remove(start); 
/* 810 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 829 */     Object clone = createCopy(0, getItemCount() - 1);
/* 830 */     return clone;
/*     */   }
/*     */   
/*     */   public TimeSeries createCopy(int start, int end) throws CloneNotSupportedException {
/* 848 */     TimeSeries copy = (TimeSeries)super.clone();
/* 850 */     copy.data = new ArrayList();
/* 851 */     if (this.data.size() > 0)
/* 852 */       for (int index = start; index <= end; index++) {
/* 853 */         TimeSeriesDataItem item = this.data.get(index);
/* 855 */         TimeSeriesDataItem clone = (TimeSeriesDataItem)item.clone();
/*     */         try {
/* 857 */           copy.add(clone);
/* 859 */         } catch (SeriesException e) {
/* 860 */           System.err.println("Unable to add cloned data item.");
/*     */         } 
/*     */       }  
/* 865 */     return copy;
/*     */   }
/*     */   
/*     */   public TimeSeries createCopy(RegularTimePeriod start, RegularTimePeriod end) throws CloneNotSupportedException {
/* 884 */     int startIndex = getIndex(start);
/* 885 */     if (startIndex < 0)
/* 886 */       startIndex = -(startIndex + 1); 
/* 888 */     int endIndex = getIndex(end);
/* 889 */     if (endIndex < 0) {
/* 890 */       endIndex = -(endIndex + 1);
/* 891 */       endIndex--;
/*     */     } 
/* 894 */     TimeSeries result = createCopy(startIndex, endIndex);
/* 896 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 908 */     if (object == this)
/* 909 */       return true; 
/* 911 */     if (!(object instanceof TimeSeries) || !super.equals(object))
/* 912 */       return false; 
/* 914 */     TimeSeries s = (TimeSeries)object;
/* 915 */     if (!ObjectUtilities.equal(getDomainDescription(), s.getDomainDescription()))
/* 918 */       return false; 
/* 921 */     if (!ObjectUtilities.equal(getRangeDescription(), s.getRangeDescription()))
/* 924 */       return false; 
/* 927 */     if (!getClass().equals(s.getClass()))
/* 928 */       return false; 
/* 931 */     if (getMaximumItemAge() != s.getMaximumItemAge())
/* 932 */       return false; 
/* 935 */     if (getMaximumItemCount() != s.getMaximumItemCount())
/* 936 */       return false; 
/* 939 */     int count = getItemCount();
/* 940 */     if (count != s.getItemCount())
/* 941 */       return false; 
/* 943 */     for (int i = 0; i < count; i++) {
/* 944 */       if (!getDataItem(i).equals(s.getDataItem(i)))
/* 945 */         return false; 
/*     */     } 
/* 948 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 958 */     int result = (this.domain != null) ? this.domain.hashCode() : 0;
/* 959 */     result = 29 * result + ((this.range != null) ? this.range.hashCode() : 0);
/* 960 */     result = 29 * result + ((this.timePeriodClass != null) ? this.timePeriodClass.hashCode() : 0);
/* 962 */     result = 29 * result + this.data.hashCode();
/* 963 */     result = 29 * result + this.maximumItemCount;
/* 964 */     result = 29 * result + (int)this.maximumItemAge;
/* 965 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimeSeries.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */