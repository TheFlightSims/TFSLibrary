/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import java.text.DateFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.TimeZone;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.time.DateRange;
/*      */ import org.jfree.data.time.Month;
/*      */ import org.jfree.data.time.RegularTimePeriod;
/*      */ import org.jfree.data.time.Year;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ 
/*      */ public class DateAxis extends ValueAxis implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = -1013460999649007604L;
/*      */   
/*  159 */   public static final DateRange DEFAULT_DATE_RANGE = new DateRange();
/*      */   
/*      */   public static final double DEFAULT_AUTO_RANGE_MINIMUM_SIZE_IN_MILLISECONDS = 2.0D;
/*      */   
/*  166 */   public static final DateTickUnit DEFAULT_DATE_TICK_UNIT = new DateTickUnit(2, 1, new SimpleDateFormat());
/*      */   
/*  170 */   public static final Date DEFAULT_ANCHOR_DATE = new Date();
/*      */   
/*      */   private DateTickUnit tickUnit;
/*      */   
/*      */   private DateFormat dateFormatOverride;
/*      */   
/*  182 */   private DateTickMarkPosition tickMarkPosition = DateTickMarkPosition.START;
/*      */   
/*      */   private static class DefaultTimeline implements Timeline, Serializable {
/*      */     private DefaultTimeline() {}
/*      */     
/*      */     public long toTimelineValue(long millisecond) {
/*  198 */       return millisecond;
/*      */     }
/*      */     
/*      */     public long toTimelineValue(Date date) {
/*  209 */       return date.getTime();
/*      */     }
/*      */     
/*      */     public long toMillisecond(long value) {
/*  221 */       return value;
/*      */     }
/*      */     
/*      */     public boolean containsDomainValue(long millisecond) {
/*  233 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsDomainValue(Date date) {
/*  245 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsDomainRange(long from, long to) {
/*  258 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsDomainRange(Date from, Date to) {
/*  271 */       return true;
/*      */     }
/*      */     
/*      */     public boolean equals(Object object) {
/*  283 */       if (object == null)
/*  284 */         return false; 
/*  287 */       if (object == this)
/*  288 */         return true; 
/*  291 */       if (object instanceof DefaultTimeline)
/*  292 */         return true; 
/*  295 */       return false;
/*      */     }
/*      */   }
/*      */   
/*  301 */   private static final Timeline DEFAULT_TIMELINE = new DefaultTimeline();
/*      */   
/*      */   private TimeZone timeZone;
/*      */   
/*      */   private Timeline timeline;
/*      */   
/*      */   public DateAxis() {
/*  313 */     this((String)null);
/*      */   }
/*      */   
/*      */   public DateAxis(String label) {
/*  322 */     this(label, TimeZone.getDefault());
/*      */   }
/*      */   
/*      */   public DateAxis(String label, TimeZone zone) {
/*  336 */     super(label, createStandardDateTickUnits(zone));
/*  337 */     setTickUnit(DEFAULT_DATE_TICK_UNIT, false, false);
/*  338 */     setAutoRangeMinimumSize(2.0D);
/*  341 */     setRange((Range)DEFAULT_DATE_RANGE, false, false);
/*  342 */     this.dateFormatOverride = null;
/*  343 */     this.timeZone = zone;
/*  344 */     this.timeline = DEFAULT_TIMELINE;
/*      */   }
/*      */   
/*      */   public Timeline getTimeline() {
/*  353 */     return this.timeline;
/*      */   }
/*      */   
/*      */   public void setTimeline(Timeline timeline) {
/*  365 */     if (this.timeline != timeline) {
/*  366 */       this.timeline = timeline;
/*  367 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public DateTickUnit getTickUnit() {
/*  377 */     return this.tickUnit;
/*      */   }
/*      */   
/*      */   public void setTickUnit(DateTickUnit unit) {
/*  388 */     setTickUnit(unit, true, true);
/*      */   }
/*      */   
/*      */   public void setTickUnit(DateTickUnit unit, boolean notify, boolean turnOffAutoSelection) {
/*  401 */     this.tickUnit = unit;
/*  402 */     if (turnOffAutoSelection)
/*  403 */       setAutoTickUnitSelection(false, false); 
/*  405 */     if (notify)
/*  406 */       notifyListeners(new AxisChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public DateFormat getDateFormatOverride() {
/*  418 */     return this.dateFormatOverride;
/*      */   }
/*      */   
/*      */   public void setDateFormatOverride(DateFormat formatter) {
/*  428 */     this.dateFormatOverride = formatter;
/*  429 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setRange(Range range) {
/*  440 */     setRange(range, true, true);
/*      */   }
/*      */   
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify) {
/*      */     DateRange dateRange;
/*  456 */     if (range == null)
/*  457 */       throw new IllegalArgumentException("Null 'range' argument."); 
/*  461 */     if (!(range instanceof DateRange))
/*  462 */       dateRange = new DateRange(range); 
/*  464 */     super.setRange((Range)dateRange, turnOffAutoRange, notify);
/*      */   }
/*      */   
/*      */   public void setRange(Date lower, Date upper) {
/*  475 */     if (lower.getTime() >= upper.getTime())
/*  476 */       throw new IllegalArgumentException("Requires 'lower' < 'upper'."); 
/*  478 */     setRange((Range)new DateRange(lower, upper));
/*      */   }
/*      */   
/*      */   public void setRange(double lower, double upper) {
/*  489 */     if (lower >= upper)
/*  490 */       throw new IllegalArgumentException("Requires 'lower' < 'upper'."); 
/*  492 */     setRange((Range)new DateRange(lower, upper));
/*      */   }
/*      */   
/*      */   public Date getMinimumDate() {
/*  502 */     Date result = null;
/*  504 */     Range range = getRange();
/*  505 */     if (range instanceof DateRange) {
/*  506 */       DateRange r = (DateRange)range;
/*  507 */       result = r.getLowerDate();
/*      */     } else {
/*  510 */       result = new Date((long)range.getLowerBound());
/*      */     } 
/*  513 */     return result;
/*      */   }
/*      */   
/*      */   public void setMinimumDate(Date date) {
/*  524 */     setRange((Range)new DateRange(date, getMaximumDate()), true, false);
/*  525 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Date getMaximumDate() {
/*  535 */     Date result = null;
/*  536 */     Range range = getRange();
/*  537 */     if (range instanceof DateRange) {
/*  538 */       DateRange r = (DateRange)range;
/*  539 */       result = r.getUpperDate();
/*      */     } else {
/*  542 */       result = new Date((long)range.getUpperBound());
/*      */     } 
/*  544 */     return result;
/*      */   }
/*      */   
/*      */   public void setMaximumDate(Date maximumDate) {
/*  555 */     setRange((Range)new DateRange(getMinimumDate(), maximumDate), true, false);
/*  556 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public DateTickMarkPosition getTickMarkPosition() {
/*  565 */     return this.tickMarkPosition;
/*      */   }
/*      */   
/*      */   public void setTickMarkPosition(DateTickMarkPosition position) {
/*  575 */     if (position == null)
/*  576 */       throw new IllegalArgumentException("Null 'position' argument."); 
/*  578 */     this.tickMarkPosition = position;
/*  579 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void configure() {
/*  587 */     if (isAutoRange())
/*  588 */       autoAdjustRange(); 
/*      */   }
/*      */   
/*      */   public boolean isHiddenValue(long millis) {
/*  601 */     return !this.timeline.containsDomainValue(new Date(millis));
/*      */   }
/*      */   
/*      */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge) {
/*  618 */     value = this.timeline.toTimelineValue((long)value);
/*  620 */     DateRange range = (DateRange)getRange();
/*  621 */     double axisMin = this.timeline.toTimelineValue(range.getLowerDate());
/*  622 */     double axisMax = this.timeline.toTimelineValue(range.getUpperDate());
/*  623 */     double result = 0.0D;
/*  624 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  625 */       double minX = area.getX();
/*  626 */       double maxX = area.getMaxX();
/*  627 */       if (isInverted()) {
/*  628 */         result = maxX + (value - axisMin) / (axisMax - axisMin) * (minX - maxX);
/*      */       } else {
/*  632 */         result = minX + (value - axisMin) / (axisMax - axisMin) * (maxX - minX);
/*      */       } 
/*  636 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  637 */       double minY = area.getMinY();
/*  638 */       double maxY = area.getMaxY();
/*  639 */       if (isInverted()) {
/*  640 */         result = minY + (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       } else {
/*  644 */         result = maxY - (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       } 
/*      */     } 
/*  648 */     return result;
/*      */   }
/*      */   
/*      */   public double dateToJava2D(Date date, Rectangle2D area, RectangleEdge edge) {
/*  665 */     double value = date.getTime();
/*  666 */     return valueToJava2D(value, area, edge);
/*      */   }
/*      */   
/*      */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge) {
/*      */     double result;
/*  684 */     DateRange range = (DateRange)getRange();
/*  685 */     double axisMin = this.timeline.toTimelineValue(range.getLowerDate());
/*  686 */     double axisMax = this.timeline.toTimelineValue(range.getUpperDate());
/*  688 */     double min = 0.0D;
/*  689 */     double max = 0.0D;
/*  690 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  691 */       min = area.getX();
/*  692 */       max = area.getMaxX();
/*  694 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  695 */       min = area.getMaxY();
/*  696 */       max = area.getY();
/*      */     } 
/*  700 */     if (isInverted()) {
/*  701 */       result = axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     } else {
/*  705 */       result = axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     } 
/*  709 */     return this.timeline.toMillisecond((long)result);
/*      */   }
/*      */   
/*      */   public Date calculateLowestVisibleTickValue(DateTickUnit unit) {
/*  720 */     return nextStandardDate(getMinimumDate(), unit);
/*      */   }
/*      */   
/*      */   public Date calculateHighestVisibleTickValue(DateTickUnit unit) {
/*  731 */     return previousStandardDate(getMaximumDate(), unit);
/*      */   }
/*      */   
/*      */   protected Date previousStandardDate(Date date, DateTickUnit unit) {
/*      */     int milliseconds, seconds, minutes, hours, days, months, years;
/*      */     long result;
/*      */     Month month;
/*      */     Date standardDate;
/*      */     long millis;
/*  752 */     Calendar calendar = Calendar.getInstance(this.timeZone);
/*  753 */     calendar.setTime(date);
/*  754 */     int count = unit.getCount();
/*  755 */     int current = calendar.get(unit.getCalendarField());
/*  756 */     int value = count * current / count;
/*  758 */     switch (unit.getUnit()) {
/*      */       case 6:
/*  761 */         years = calendar.get(1);
/*  762 */         months = calendar.get(2);
/*  763 */         days = calendar.get(5);
/*  764 */         hours = calendar.get(11);
/*  765 */         minutes = calendar.get(12);
/*  766 */         seconds = calendar.get(13);
/*  767 */         calendar.set(years, months, days, hours, minutes, seconds);
/*  768 */         calendar.set(14, value);
/*  769 */         return calendar.getTime();
/*      */       case 5:
/*  772 */         years = calendar.get(1);
/*  773 */         months = calendar.get(2);
/*  774 */         days = calendar.get(5);
/*  775 */         hours = calendar.get(11);
/*  776 */         minutes = calendar.get(12);
/*  777 */         if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  778 */           milliseconds = 0;
/*  780 */         } else if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  781 */           milliseconds = 500;
/*      */         } else {
/*  784 */           milliseconds = 999;
/*      */         } 
/*  786 */         calendar.set(14, milliseconds);
/*  787 */         calendar.set(years, months, days, hours, minutes, value);
/*  788 */         return calendar.getTime();
/*      */       case 4:
/*  791 */         years = calendar.get(1);
/*  792 */         months = calendar.get(2);
/*  793 */         days = calendar.get(5);
/*  794 */         hours = calendar.get(11);
/*  795 */         if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  796 */           seconds = 0;
/*  798 */         } else if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  799 */           seconds = 30;
/*      */         } else {
/*  802 */           seconds = 59;
/*      */         } 
/*  804 */         calendar.clear(14);
/*  805 */         calendar.set(years, months, days, hours, value, seconds);
/*  806 */         return calendar.getTime();
/*      */       case 3:
/*  809 */         years = calendar.get(1);
/*  810 */         months = calendar.get(2);
/*  811 */         days = calendar.get(5);
/*  812 */         if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  813 */           minutes = 0;
/*  814 */           seconds = 0;
/*  816 */         } else if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  817 */           minutes = 30;
/*  818 */           seconds = 0;
/*      */         } else {
/*  821 */           minutes = 59;
/*  822 */           seconds = 59;
/*      */         } 
/*  824 */         calendar.clear(14);
/*  825 */         calendar.set(years, months, days, value, minutes, seconds);
/*  826 */         return calendar.getTime();
/*      */       case 2:
/*  829 */         years = calendar.get(1);
/*  830 */         months = calendar.get(2);
/*  831 */         if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  832 */           hours = 0;
/*  833 */           minutes = 0;
/*  834 */           seconds = 0;
/*  836 */         } else if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  837 */           hours = 12;
/*  838 */           minutes = 0;
/*  839 */           seconds = 0;
/*      */         } else {
/*  842 */           hours = 23;
/*  843 */           minutes = 59;
/*  844 */           seconds = 59;
/*      */         } 
/*  846 */         calendar.clear(14);
/*  847 */         calendar.set(years, months, value, hours, 0, 0);
/*  850 */         result = calendar.getTime().getTime();
/*  851 */         if (result > date.getTime())
/*  852 */           calendar.set(years, months, value - 1, hours, 0, 0); 
/*  854 */         return calendar.getTime();
/*      */       case 1:
/*  857 */         years = calendar.get(1);
/*  858 */         calendar.clear(14);
/*  859 */         calendar.set(years, value, 1, 0, 0, 0);
/*  860 */         month = new Month(calendar.getTime());
/*  861 */         standardDate = calculateDateForPosition((RegularTimePeriod)month, this.tickMarkPosition);
/*  864 */         millis = standardDate.getTime();
/*  865 */         if (millis > date.getTime()) {
/*  866 */           month = (Month)month.previous();
/*  867 */           standardDate = calculateDateForPosition((RegularTimePeriod)month, this.tickMarkPosition);
/*      */         } 
/*  871 */         return standardDate;
/*      */       case 0:
/*  874 */         if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  875 */           months = 0;
/*  876 */           days = 1;
/*  878 */         } else if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  879 */           months = 6;
/*  880 */           days = 1;
/*      */         } else {
/*  883 */           months = 11;
/*  884 */           days = 31;
/*      */         } 
/*  886 */         calendar.clear(14);
/*  887 */         calendar.set(value, months, days, 0, 0, 0);
/*  888 */         return calendar.getTime();
/*      */     } 
/*  890 */     return null;
/*      */   }
/*      */   
/*      */   private Date calculateDateForPosition(RegularTimePeriod period, DateTickMarkPosition position) {
/*  908 */     if (position == null)
/*  909 */       throw new IllegalArgumentException("Null 'position' argument."); 
/*  911 */     Date result = null;
/*  912 */     if (position == DateTickMarkPosition.START) {
/*  913 */       result = new Date(period.getFirstMillisecond());
/*  915 */     } else if (position == DateTickMarkPosition.MIDDLE) {
/*  916 */       result = new Date(period.getMiddleMillisecond());
/*  918 */     } else if (position == DateTickMarkPosition.END) {
/*  919 */       result = new Date(period.getLastMillisecond());
/*      */     } 
/*  921 */     return result;
/*      */   }
/*      */   
/*      */   protected Date nextStandardDate(Date date, DateTickUnit unit) {
/*  936 */     Date previous = previousStandardDate(date, unit);
/*  937 */     Calendar calendar = Calendar.getInstance();
/*  938 */     calendar.setTime(previous);
/*  939 */     calendar.add(unit.getCalendarField(), unit.getCount());
/*  940 */     return calendar.getTime();
/*      */   }
/*      */   
/*      */   public static TickUnitSource createStandardDateTickUnits() {
/*  954 */     return createStandardDateTickUnits(TimeZone.getDefault());
/*      */   }
/*      */   
/*      */   public static TickUnitSource createStandardDateTickUnits(TimeZone zone) {
/*  970 */     if (zone == null)
/*  971 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/*  973 */     TickUnits units = new TickUnits();
/*  976 */     DateFormat f1 = new SimpleDateFormat("HH:mm:ss.SSS");
/*  977 */     DateFormat f2 = new SimpleDateFormat("HH:mm:ss");
/*  978 */     DateFormat f3 = new SimpleDateFormat("HH:mm");
/*  979 */     DateFormat f4 = new SimpleDateFormat("d-MMM, HH:mm");
/*  980 */     DateFormat f5 = new SimpleDateFormat("d-MMM");
/*  981 */     DateFormat f6 = new SimpleDateFormat("MMM-yyyy");
/*  982 */     DateFormat f7 = new SimpleDateFormat("yyyy");
/*  984 */     f1.setTimeZone(zone);
/*  985 */     f2.setTimeZone(zone);
/*  986 */     f3.setTimeZone(zone);
/*  987 */     f4.setTimeZone(zone);
/*  988 */     f5.setTimeZone(zone);
/*  989 */     f6.setTimeZone(zone);
/*  990 */     f7.setTimeZone(zone);
/*  993 */     units.add(new DateTickUnit(6, 1, f1));
/*  994 */     units.add(new DateTickUnit(6, 5, 6, 1, f1));
/*  999 */     units.add(new DateTickUnit(6, 10, 6, 1, f1));
/* 1004 */     units.add(new DateTickUnit(6, 25, 6, 5, f1));
/* 1009 */     units.add(new DateTickUnit(6, 50, 6, 10, f1));
/* 1014 */     units.add(new DateTickUnit(6, 100, 6, 10, f1));
/* 1019 */     units.add(new DateTickUnit(6, 250, 6, 10, f1));
/* 1024 */     units.add(new DateTickUnit(6, 500, 6, 50, f1));
/* 1031 */     units.add(new DateTickUnit(5, 1, 6, 50, f2));
/* 1036 */     units.add(new DateTickUnit(5, 5, 5, 1, f2));
/* 1041 */     units.add(new DateTickUnit(5, 10, 5, 1, f2));
/* 1046 */     units.add(new DateTickUnit(5, 30, 5, 5, f2));
/* 1053 */     units.add(new DateTickUnit(4, 1, 5, 5, f3));
/* 1056 */     units.add(new DateTickUnit(4, 2, 5, 10, f3));
/* 1061 */     units.add(new DateTickUnit(4, 5, 4, 1, f3));
/* 1064 */     units.add(new DateTickUnit(4, 10, 4, 1, f3));
/* 1069 */     units.add(new DateTickUnit(4, 15, 4, 5, f3));
/* 1074 */     units.add(new DateTickUnit(4, 20, 4, 5, f3));
/* 1079 */     units.add(new DateTickUnit(4, 30, 4, 5, f3));
/* 1086 */     units.add(new DateTickUnit(3, 1, 4, 5, f3));
/* 1089 */     units.add(new DateTickUnit(3, 2, 4, 10, f3));
/* 1092 */     units.add(new DateTickUnit(3, 4, 4, 30, f3));
/* 1095 */     units.add(new DateTickUnit(3, 6, 3, 1, f3));
/* 1098 */     units.add(new DateTickUnit(3, 12, 3, 1, f4));
/* 1103 */     units.add(new DateTickUnit(2, 1, 3, 1, f5));
/* 1106 */     units.add(new DateTickUnit(2, 2, 3, 1, f5));
/* 1109 */     units.add(new DateTickUnit(2, 7, 2, 1, f5));
/* 1112 */     units.add(new DateTickUnit(2, 15, 2, 1, f5));
/* 1117 */     units.add(new DateTickUnit(1, 1, 2, 1, f6));
/* 1120 */     units.add(new DateTickUnit(1, 2, 2, 1, f6));
/* 1123 */     units.add(new DateTickUnit(1, 3, 1, 1, f6));
/* 1126 */     units.add(new DateTickUnit(1, 4, 1, 1, f6));
/* 1129 */     units.add(new DateTickUnit(1, 6, 1, 1, f6));
/* 1134 */     units.add(new DateTickUnit(0, 1, 1, 1, f7));
/* 1137 */     units.add(new DateTickUnit(0, 2, 1, 3, f7));
/* 1140 */     units.add(new DateTickUnit(0, 5, 0, 1, f7));
/* 1143 */     units.add(new DateTickUnit(0, 10, 0, 1, f7));
/* 1146 */     units.add(new DateTickUnit(0, 25, 0, 5, f7));
/* 1149 */     units.add(new DateTickUnit(0, 50, 0, 10, f7));
/* 1152 */     units.add(new DateTickUnit(0, 100, 0, 20, f7));
/* 1156 */     return units;
/*      */   }
/*      */   
/*      */   protected void autoAdjustRange() {
/* 1165 */     Plot plot = getPlot();
/* 1167 */     if (plot == null)
/*      */       return; 
/* 1171 */     if (plot instanceof ValueAxisPlot) {
/*      */       DateRange dateRange1;
/* 1172 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/* 1174 */       Range r = vap.getDataRange(this);
/* 1175 */       if (r == null)
/* 1176 */         if (this.timeline instanceof SegmentedTimeline) {
/* 1178 */           dateRange1 = new DateRange(((SegmentedTimeline)this.timeline).getStartTime(), (((SegmentedTimeline)this.timeline).getStartTime() + 1L));
/*      */         } else {
/* 1184 */           dateRange1 = new DateRange();
/*      */         }  
/* 1188 */       long upper = this.timeline.toTimelineValue((long)dateRange1.getUpperBound());
/* 1192 */       long fixedAutoRange = (long)getFixedAutoRange();
/* 1193 */       if (fixedAutoRange > 0.0D) {
/* 1194 */         lower = upper - fixedAutoRange;
/*      */       } else {
/* 1197 */         lower = this.timeline.toTimelineValue((long)dateRange1.getLowerBound());
/* 1198 */         double range = (upper - lower);
/* 1199 */         long minRange = (long)getAutoRangeMinimumSize();
/* 1200 */         if (range < minRange) {
/* 1201 */           long expand = (long)(minRange - range) / 2L;
/* 1202 */           upper += expand;
/* 1203 */           lower -= expand;
/*      */         } 
/* 1205 */         upper += (long)(range * getUpperMargin());
/* 1206 */         lower -= (long)(range * getLowerMargin());
/*      */       } 
/* 1209 */       upper = this.timeline.toMillisecond(upper);
/* 1210 */       long lower = this.timeline.toMillisecond(lower);
/* 1211 */       DateRange dr = new DateRange(new Date(lower), new Date(upper));
/* 1212 */       setRange((Range)dr, false, false);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 1230 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1231 */       selectHorizontalAutoTickUnit(g2, dataArea, edge);
/* 1233 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1234 */       selectVerticalAutoTickUnit(g2, dataArea, edge);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 1252 */     long shift = 0L;
/* 1253 */     if (this.timeline instanceof SegmentedTimeline)
/* 1254 */       shift = ((SegmentedTimeline)this.timeline).getStartTime(); 
/* 1256 */     double zero = valueToJava2D(shift + 0.0D, dataArea, edge);
/* 1257 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/* 1261 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1262 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 1263 */     double x1 = valueToJava2D(shift + unit1.getSize(), dataArea, edge);
/* 1264 */     double unit1Width = Math.abs(x1 - zero);
/* 1267 */     double guess = tickLabelWidth / unit1Width * unit1.getSize();
/* 1268 */     DateTickUnit unit2 = (DateTickUnit)tickUnits.getCeilingTickUnit(guess);
/* 1269 */     double x2 = valueToJava2D(shift + unit2.getSize(), dataArea, edge);
/* 1270 */     double unit2Width = Math.abs(x2 - zero);
/* 1271 */     tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
/* 1272 */     if (tickLabelWidth > unit2Width)
/* 1273 */       unit2 = (DateTickUnit)tickUnits.getLargerTickUnit(unit2); 
/* 1275 */     setTickUnit(unit2, false, false);
/*      */   }
/*      */   
/*      */   protected void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/*      */     DateTickUnit finalUnit;
/* 1292 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1293 */     double zero = valueToJava2D(0.0D, dataArea, edge);
/* 1296 */     double estimate1 = getRange().getLength() / 10.0D;
/* 1297 */     DateTickUnit candidate1 = (DateTickUnit)tickUnits.getCeilingTickUnit(estimate1);
/* 1299 */     double labelHeight1 = estimateMaximumTickLabelHeight(g2, candidate1);
/* 1300 */     double y1 = valueToJava2D(candidate1.getSize(), dataArea, edge);
/* 1301 */     double candidate1UnitHeight = Math.abs(y1 - zero);
/* 1304 */     double estimate2 = labelHeight1 / candidate1UnitHeight * candidate1.getSize();
/* 1306 */     DateTickUnit candidate2 = (DateTickUnit)tickUnits.getCeilingTickUnit(estimate2);
/* 1308 */     double labelHeight2 = estimateMaximumTickLabelHeight(g2, candidate2);
/* 1309 */     double y2 = valueToJava2D(candidate2.getSize(), dataArea, edge);
/* 1310 */     double unit2Height = Math.abs(y2 - zero);
/* 1314 */     if (labelHeight2 < unit2Height) {
/* 1315 */       finalUnit = candidate2;
/*      */     } else {
/* 1318 */       finalUnit = (DateTickUnit)tickUnits.getLargerTickUnit(candidate2);
/*      */     } 
/* 1320 */     setTickUnit(finalUnit, false, false);
/*      */   }
/*      */   
/*      */   private double estimateMaximumTickLabelWidth(Graphics2D g2, DateTickUnit unit) {
/* 1340 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/* 1341 */     double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
/* 1343 */     Font tickLabelFont = getTickLabelFont();
/* 1344 */     FontRenderContext frc = g2.getFontRenderContext();
/* 1345 */     LineMetrics lm = tickLabelFont.getLineMetrics("ABCxyz", frc);
/* 1346 */     if (isVerticalTickLabels()) {
/* 1349 */       result += lm.getHeight();
/*      */     } else {
/* 1353 */       DateRange range = (DateRange)getRange();
/* 1354 */       Date lower = range.getLowerDate();
/* 1355 */       Date upper = range.getUpperDate();
/* 1356 */       String lowerStr = null;
/* 1357 */       String upperStr = null;
/* 1358 */       DateFormat formatter = getDateFormatOverride();
/* 1359 */       if (formatter != null) {
/* 1360 */         lowerStr = formatter.format(lower);
/* 1361 */         upperStr = formatter.format(upper);
/*      */       } else {
/* 1364 */         lowerStr = unit.dateToString(lower);
/* 1365 */         upperStr = unit.dateToString(upper);
/*      */       } 
/* 1367 */       FontMetrics fm = g2.getFontMetrics(tickLabelFont);
/* 1368 */       double w1 = fm.stringWidth(lowerStr);
/* 1369 */       double w2 = fm.stringWidth(upperStr);
/* 1370 */       result += Math.max(w1, w2);
/*      */     } 
/* 1373 */     return result;
/*      */   }
/*      */   
/*      */   private double estimateMaximumTickLabelHeight(Graphics2D g2, DateTickUnit unit) {
/* 1393 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/* 1394 */     double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
/* 1396 */     Font tickLabelFont = getTickLabelFont();
/* 1397 */     FontRenderContext frc = g2.getFontRenderContext();
/* 1398 */     LineMetrics lm = tickLabelFont.getLineMetrics("ABCxyz", frc);
/* 1399 */     if (!isVerticalTickLabels()) {
/* 1402 */       result += lm.getHeight();
/*      */     } else {
/* 1406 */       DateRange range = (DateRange)getRange();
/* 1407 */       Date lower = range.getLowerDate();
/* 1408 */       Date upper = range.getUpperDate();
/* 1409 */       String lowerStr = null;
/* 1410 */       String upperStr = null;
/* 1411 */       DateFormat formatter = getDateFormatOverride();
/* 1412 */       if (formatter != null) {
/* 1413 */         lowerStr = formatter.format(lower);
/* 1414 */         upperStr = formatter.format(upper);
/*      */       } else {
/* 1417 */         lowerStr = unit.dateToString(lower);
/* 1418 */         upperStr = unit.dateToString(upper);
/*      */       } 
/* 1420 */       FontMetrics fm = g2.getFontMetrics(tickLabelFont);
/* 1421 */       double w1 = fm.stringWidth(lowerStr);
/* 1422 */       double w2 = fm.stringWidth(upperStr);
/* 1423 */       result += Math.max(w1, w2);
/*      */     } 
/* 1426 */     return result;
/*      */   }
/*      */   
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/* 1446 */     List result = null;
/* 1447 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1448 */       result = refreshTicksHorizontal(g2, dataArea, edge);
/* 1450 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1451 */       result = refreshTicksVertical(g2, dataArea, edge);
/*      */     } 
/* 1453 */     return result;
/*      */   }
/*      */   
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 1470 */     List result = new ArrayList();
/* 1472 */     Font tickLabelFont = getTickLabelFont();
/* 1473 */     g2.setFont(tickLabelFont);
/* 1475 */     if (isAutoTickUnitSelection())
/* 1476 */       selectAutoTickUnit(g2, dataArea, edge); 
/* 1479 */     DateTickUnit unit = getTickUnit();
/* 1480 */     Date tickDate = calculateLowestVisibleTickValue(unit);
/* 1481 */     Date upperDate = getMaximumDate();
/* 1483 */     while (tickDate.before(upperDate)) {
/* 1485 */       if (!isHiddenValue(tickDate.getTime())) {
/*      */         String tickLabel;
/* 1488 */         DateFormat formatter = getDateFormatOverride();
/* 1489 */         if (formatter != null) {
/* 1490 */           tickLabel = formatter.format(tickDate);
/*      */         } else {
/* 1493 */           tickLabel = this.tickUnit.dateToString(tickDate);
/*      */         } 
/* 1495 */         TextAnchor anchor = null;
/* 1496 */         TextAnchor rotationAnchor = null;
/* 1497 */         double angle = 0.0D;
/* 1498 */         if (isVerticalTickLabels()) {
/* 1499 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1500 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/* 1501 */           if (edge == RectangleEdge.TOP) {
/* 1502 */             angle = 1.5707963267948966D;
/*      */           } else {
/* 1505 */             angle = -1.5707963267948966D;
/*      */           } 
/* 1509 */         } else if (edge == RectangleEdge.TOP) {
/* 1510 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 1511 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*      */         } else {
/* 1514 */           anchor = TextAnchor.TOP_CENTER;
/* 1515 */           rotationAnchor = TextAnchor.TOP_CENTER;
/*      */         } 
/* 1519 */         Tick tick = new DateTick(tickDate, tickLabel, anchor, rotationAnchor, angle);
/* 1522 */         result.add(tick);
/* 1523 */         tickDate = unit.addToDate(tickDate);
/*      */       } else {
/* 1526 */         tickDate = unit.rollDate(tickDate);
/*      */         continue;
/*      */       } 
/* 1531 */       switch (unit.getUnit()) {
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 6:
/*      */           break;
/*      */         case 1:
/* 1540 */           tickDate = calculateDateForPosition((RegularTimePeriod)new Month(tickDate), this.tickMarkPosition);
/*      */           break;
/*      */         case 0:
/* 1545 */           tickDate = calculateDateForPosition((RegularTimePeriod)new Year(tickDate), this.tickMarkPosition);
/*      */           break;
/*      */       } 
/*      */     } 
/* 1555 */     return result;
/*      */   }
/*      */   
/*      */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 1572 */     List result = new ArrayList();
/* 1574 */     Font tickLabelFont = getTickLabelFont();
/* 1575 */     g2.setFont(tickLabelFont);
/* 1577 */     if (isAutoTickUnitSelection())
/* 1578 */       selectAutoTickUnit(g2, dataArea, edge); 
/* 1580 */     DateTickUnit unit = getTickUnit();
/* 1581 */     Date tickDate = calculateLowestVisibleTickValue(unit);
/* 1583 */     Date upperDate = getMaximumDate();
/* 1584 */     while (tickDate.before(upperDate)) {
/* 1586 */       if (!isHiddenValue(tickDate.getTime())) {
/*      */         String tickLabel;
/* 1589 */         DateFormat formatter = getDateFormatOverride();
/* 1590 */         if (formatter != null) {
/* 1591 */           tickLabel = formatter.format(tickDate);
/*      */         } else {
/* 1594 */           tickLabel = this.tickUnit.dateToString(tickDate);
/*      */         } 
/* 1596 */         TextAnchor anchor = null;
/* 1597 */         TextAnchor rotationAnchor = null;
/* 1598 */         double angle = 0.0D;
/* 1599 */         if (isVerticalTickLabels()) {
/* 1600 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 1601 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1602 */           if (edge == RectangleEdge.LEFT) {
/* 1603 */             angle = -1.5707963267948966D;
/*      */           } else {
/* 1606 */             angle = 1.5707963267948966D;
/*      */           } 
/* 1610 */         } else if (edge == RectangleEdge.LEFT) {
/* 1611 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1612 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/*      */         } else {
/* 1615 */           anchor = TextAnchor.CENTER_LEFT;
/* 1616 */           rotationAnchor = TextAnchor.CENTER_LEFT;
/*      */         } 
/* 1620 */         Tick tick = new DateTick(tickDate, tickLabel, anchor, rotationAnchor, angle);
/* 1623 */         result.add(tick);
/* 1624 */         tickDate = unit.addToDate(tickDate);
/*      */         continue;
/*      */       } 
/* 1627 */       tickDate = unit.rollDate(tickDate);
/*      */     } 
/* 1630 */     return result;
/*      */   }
/*      */   
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/* 1657 */     if (!isVisible()) {
/* 1658 */       AxisState axisState = new AxisState(cursor);
/* 1661 */       List ticks = refreshTicks(g2, axisState, dataArea, edge);
/* 1662 */       axisState.setTicks(ticks);
/* 1663 */       return axisState;
/*      */     } 
/* 1667 */     AxisState state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
/* 1673 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/* 1675 */     return state;
/*      */   }
/*      */   
/*      */   public void zoomRange(double lowerPercent, double upperPercent) {
/*      */     DateRange dateRange;
/* 1686 */     double start = this.timeline.toTimelineValue((long)getRange().getLowerBound());
/* 1689 */     double length = (this.timeline.toTimelineValue((long)getRange().getUpperBound()) - this.timeline.toTimelineValue((long)getRange().getLowerBound()));
/* 1694 */     Range adjusted = null;
/* 1695 */     if (isInverted()) {
/* 1696 */       dateRange = new DateRange(this.timeline.toMillisecond((long)(start + length * (1.0D - upperPercent))), this.timeline.toMillisecond((long)(start + length * (1.0D - lowerPercent))));
/*      */     } else {
/* 1706 */       dateRange = new DateRange(this.timeline.toMillisecond((long)(start + length * lowerPercent)), this.timeline.toMillisecond((long)(start + length * upperPercent)));
/*      */     } 
/* 1713 */     setRange((Range)dateRange);
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1724 */     if (obj == this)
/* 1725 */       return true; 
/* 1727 */     if (!(obj instanceof DateAxis))
/* 1728 */       return false; 
/* 1730 */     DateAxis that = (DateAxis)obj;
/* 1731 */     if (!ObjectUtilities.equal(this.tickUnit, that.tickUnit))
/* 1732 */       return false; 
/* 1734 */     if (!ObjectUtilities.equal(this.dateFormatOverride, that.dateFormatOverride))
/* 1737 */       return false; 
/* 1739 */     if (!ObjectUtilities.equal(this.tickMarkPosition, that.tickMarkPosition))
/* 1742 */       return false; 
/* 1744 */     if (!ObjectUtilities.equal(this.timeline, that.timeline))
/* 1745 */       return false; 
/* 1747 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1756 */     if (getLabel() != null)
/* 1757 */       return getLabel().hashCode(); 
/* 1760 */     return 0;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1774 */     DateAxis clone = (DateAxis)super.clone();
/* 1777 */     if (this.dateFormatOverride != null)
/* 1778 */       clone.dateFormatOverride = (DateFormat)this.dateFormatOverride.clone(); 
/* 1783 */     return clone;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\DateAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */