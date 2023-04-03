/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.SimpleTimeZone;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ public class SegmentedTimeline implements Timeline, Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 1093779862539903110L;
/*      */   
/*      */   public static final long DAY_SEGMENT_SIZE = 86400000L;
/*      */   
/*      */   public static final long HOUR_SEGMENT_SIZE = 3600000L;
/*      */   
/*      */   public static final long FIFTEEN_MINUTE_SEGMENT_SIZE = 900000L;
/*      */   
/*      */   public static final long MINUTE_SEGMENT_SIZE = 60000L;
/*      */   
/*      */   public static long FIRST_MONDAY_AFTER_1900;
/*      */   
/*      */   public static TimeZone NO_DST_TIME_ZONE;
/*      */   
/*  211 */   public static TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();
/*      */   
/*  217 */   private Calendar workingCalendarNoDST = new GregorianCalendar(NO_DST_TIME_ZONE);
/*      */   
/*  223 */   private Calendar workingCalendar = Calendar.getInstance();
/*      */   
/*      */   private long segmentSize;
/*      */   
/*      */   private int segmentsIncluded;
/*      */   
/*      */   private int segmentsExcluded;
/*      */   
/*      */   private int groupSegmentCount;
/*      */   
/*      */   private long startTime;
/*      */   
/*      */   private long segmentsIncludedSize;
/*      */   
/*      */   private long segmentsExcludedSize;
/*      */   
/*      */   private long segmentsGroupSize;
/*      */   
/*  260 */   private List exceptionSegments = new ArrayList();
/*      */   
/*      */   private SegmentedTimeline baseTimeline;
/*      */   
/*      */   private boolean adjustForDaylightSaving = false;
/*      */   
/*      */   static {
/*  283 */     int offset = TimeZone.getDefault().getRawOffset();
/*  284 */     NO_DST_TIME_ZONE = new SimpleTimeZone(offset, "UTC-" + offset);
/*  288 */     Calendar cal = new GregorianCalendar(NO_DST_TIME_ZONE);
/*  289 */     cal.set(1900, 0, 1, 0, 0, 0);
/*  290 */     cal.set(14, 0);
/*  291 */     while (cal.get(7) != 2)
/*  292 */       cal.add(5, 1); 
/*  296 */     FIRST_MONDAY_AFTER_1900 = cal.getTime().getTime();
/*      */   }
/*      */   
/*      */   public SegmentedTimeline(long segmentSize, int segmentsIncluded, int segmentsExcluded) {
/*  320 */     this.segmentSize = segmentSize;
/*  321 */     this.segmentsIncluded = segmentsIncluded;
/*  322 */     this.segmentsExcluded = segmentsExcluded;
/*  324 */     this.groupSegmentCount = this.segmentsIncluded + this.segmentsExcluded;
/*  325 */     this.segmentsIncludedSize = this.segmentsIncluded * this.segmentSize;
/*  326 */     this.segmentsExcludedSize = this.segmentsExcluded * this.segmentSize;
/*  327 */     this.segmentsGroupSize = this.segmentsIncludedSize + this.segmentsExcludedSize;
/*      */   }
/*      */   
/*      */   public static SegmentedTimeline newMondayThroughFridayTimeline() {
/*  341 */     SegmentedTimeline timeline = new SegmentedTimeline(86400000L, 5, 2);
/*  343 */     timeline.setStartTime(FIRST_MONDAY_AFTER_1900);
/*  344 */     return timeline;
/*      */   }
/*      */   
/*      */   public static SegmentedTimeline newFifteenMinuteTimeline() {
/*  365 */     SegmentedTimeline timeline = new SegmentedTimeline(900000L, 28, 68);
/*  367 */     timeline.setStartTime(FIRST_MONDAY_AFTER_1900 + 36L * timeline.getSegmentSize());
/*  370 */     timeline.setBaseTimeline(newMondayThroughFridayTimeline());
/*  371 */     return timeline;
/*      */   }
/*      */   
/*      */   public boolean getAdjustForDaylightSaving() {
/*  381 */     return this.adjustForDaylightSaving;
/*      */   }
/*      */   
/*      */   public void setAdjustForDaylightSaving(boolean adjust) {
/*  391 */     this.adjustForDaylightSaving = adjust;
/*      */   }
/*      */   
/*      */   public void setStartTime(long millisecond) {
/*  405 */     this.startTime = millisecond;
/*      */   }
/*      */   
/*      */   public long getStartTime() {
/*  415 */     return this.startTime;
/*      */   }
/*      */   
/*      */   public int getSegmentsExcluded() {
/*  424 */     return this.segmentsExcluded;
/*      */   }
/*      */   
/*      */   public long getSegmentsExcludedSize() {
/*  434 */     return this.segmentsExcludedSize;
/*      */   }
/*      */   
/*      */   public int getGroupSegmentCount() {
/*  444 */     return this.groupSegmentCount;
/*      */   }
/*      */   
/*      */   public long getSegmentsGroupSize() {
/*  454 */     return this.segmentsGroupSize;
/*      */   }
/*      */   
/*      */   public int getSegmentsIncluded() {
/*  463 */     return this.segmentsIncluded;
/*      */   }
/*      */   
/*      */   public long getSegmentsIncludedSize() {
/*  472 */     return this.segmentsIncludedSize;
/*      */   }
/*      */   
/*      */   public long getSegmentSize() {
/*  481 */     return this.segmentSize;
/*      */   }
/*      */   
/*      */   public List getExceptionSegments() {
/*  491 */     return Collections.unmodifiableList(this.exceptionSegments);
/*      */   }
/*      */   
/*      */   public void setExceptionSegments(List exceptionSegments) {
/*  500 */     this.exceptionSegments = exceptionSegments;
/*      */   }
/*      */   
/*      */   public SegmentedTimeline getBaseTimeline() {
/*  509 */     return this.baseTimeline;
/*      */   }
/*      */   
/*      */   public void setBaseTimeline(SegmentedTimeline baseTimeline) {
/*  520 */     if (baseTimeline != null) {
/*  521 */       if (baseTimeline.getSegmentSize() < this.segmentSize)
/*  522 */         throw new IllegalArgumentException("baseTimeline.getSegmentSize() is smaller than segmentSize"); 
/*  526 */       if (baseTimeline.getStartTime() > this.startTime)
/*  527 */         throw new IllegalArgumentException("baseTimeline.getStartTime() is after startTime"); 
/*  531 */       if (baseTimeline.getSegmentSize() % this.segmentSize != 0L)
/*  532 */         throw new IllegalArgumentException("baseTimeline.getSegmentSize() is not multiple of segmentSize"); 
/*  537 */       if ((this.startTime - baseTimeline.getStartTime()) % this.segmentSize != 0L)
/*  539 */         throw new IllegalArgumentException("baseTimeline is not aligned"); 
/*      */     } 
/*  545 */     this.baseTimeline = baseTimeline;
/*      */   }
/*      */   
/*      */   public long toTimelineValue(long millisecond) {
/*  560 */     long result, rawMilliseconds = millisecond - this.startTime;
/*  561 */     long groupMilliseconds = rawMilliseconds % this.segmentsGroupSize;
/*  562 */     long groupIndex = rawMilliseconds / this.segmentsGroupSize;
/*  564 */     if (groupMilliseconds >= this.segmentsIncludedSize) {
/*  565 */       result = toTimelineValue(this.startTime + this.segmentsGroupSize * (groupIndex + 1L));
/*      */     } else {
/*  570 */       Segment segment = getSegment(millisecond);
/*  571 */       if (segment.inExceptionSegments()) {
/*  572 */         result = toTimelineValue(segment.getSegmentEnd() + 1L);
/*      */       } else {
/*  575 */         long shiftedSegmentedValue = millisecond - this.startTime;
/*  576 */         long x = shiftedSegmentedValue % this.segmentsGroupSize;
/*  577 */         long y = shiftedSegmentedValue / this.segmentsGroupSize;
/*  579 */         long wholeExceptionsBeforeDomainValue = getExceptionSegmentCount(this.startTime, millisecond - 1L);
/*  589 */         if (x < this.segmentsIncludedSize) {
/*  590 */           result = this.segmentsIncludedSize * y + x - wholeExceptionsBeforeDomainValue * this.segmentSize;
/*      */         } else {
/*  596 */           result = this.segmentsIncludedSize * (y + 1L) - wholeExceptionsBeforeDomainValue * this.segmentSize;
/*      */         } 
/*      */       } 
/*      */     } 
/*  604 */     return result;
/*      */   }
/*      */   
/*      */   public long toTimelineValue(Date date) {
/*  617 */     return toTimelineValue(getTime(date));
/*      */   }
/*      */   
/*      */   public long toMillisecond(long timelineValue) {
/*  631 */     Segment result = new Segment(this, this.startTime + timelineValue + timelineValue / this.segmentsIncludedSize * this.segmentsExcludedSize);
/*  635 */     long lastIndex = this.startTime;
/*  638 */     while (lastIndex <= result.segmentStart) {
/*      */       long exceptionSegmentCount;
/*  644 */       while ((exceptionSegmentCount = getExceptionSegmentCount(lastIndex, result.millisecond / this.segmentSize * this.segmentSize - 1L)) > 0L) {
/*  646 */         lastIndex = result.segmentStart;
/*  649 */         for (int i = 0; i < exceptionSegmentCount;) {
/*      */           while (true) {
/*  651 */             result.inc();
/*  653 */             if (!result.inExcludeSegments())
/*      */               i++; 
/*      */           } 
/*      */         } 
/*      */       } 
/*  656 */       lastIndex = result.segmentStart;
/*  659 */       while (result.inExceptionSegments() || result.inExcludeSegments()) {
/*  660 */         result.inc();
/*  661 */         lastIndex += this.segmentSize;
/*      */       } 
/*  664 */       lastIndex++;
/*      */     } 
/*  667 */     return getTimeFromLong(result.millisecond);
/*      */   }
/*      */   
/*      */   public long getTimeFromLong(long date) {
/*  678 */     long result = date;
/*  679 */     if (this.adjustForDaylightSaving) {
/*  680 */       this.workingCalendarNoDST.setTime(new Date(date));
/*  681 */       this.workingCalendar.set(this.workingCalendarNoDST.get(1), this.workingCalendarNoDST.get(2), this.workingCalendarNoDST.get(5), this.workingCalendarNoDST.get(11), this.workingCalendarNoDST.get(12), this.workingCalendarNoDST.get(13));
/*  689 */       this.workingCalendar.set(14, this.workingCalendarNoDST.get(14));
/*  695 */       result = this.workingCalendar.getTime().getTime();
/*      */     } 
/*  697 */     return result;
/*      */   }
/*      */   
/*      */   public boolean containsDomainValue(long millisecond) {
/*  708 */     Segment segment = getSegment(millisecond);
/*  709 */     return segment.inIncludeSegments();
/*      */   }
/*      */   
/*      */   public boolean containsDomainValue(Date date) {
/*  720 */     return containsDomainValue(getTime(date));
/*      */   }
/*      */   
/*      */   public boolean containsDomainRange(long domainValueStart, long domainValueEnd) {
/*  735 */     if (domainValueEnd < domainValueStart)
/*  736 */       throw new IllegalArgumentException("domainValueEnd (" + domainValueEnd + ") < domainValueStart (" + domainValueStart + ")"); 
/*  741 */     Segment segment = getSegment(domainValueStart);
/*  742 */     boolean contains = true;
/*      */     do {
/*  744 */       contains = segment.inIncludeSegments();
/*  745 */       if (segment.contains(domainValueEnd))
/*      */         break; 
/*  749 */       segment.inc();
/*  752 */     } while (contains);
/*  753 */     return contains;
/*      */   }
/*      */   
/*      */   public boolean containsDomainRange(Date dateDomainValueStart, Date dateDomainValueEnd) {
/*  768 */     return containsDomainRange(getTime(dateDomainValueStart), getTime(dateDomainValueEnd));
/*      */   }
/*      */   
/*      */   public void addException(long millisecond) {
/*  786 */     addException(new Segment(this, millisecond));
/*      */   }
/*      */   
/*      */   public void addException(long fromDomainValue, long toDomainValue) {
/*  805 */     addException(new SegmentRange(this, fromDomainValue, toDomainValue));
/*      */   }
/*      */   
/*      */   public void addException(Date exceptionDate) {
/*  820 */     addException(getTime(exceptionDate));
/*      */   }
/*      */   
/*      */   public void addExceptions(List exceptionList) {
/*  837 */     for (Iterator iter = exceptionList.iterator(); iter.hasNext();)
/*  838 */       addException(iter.next()); 
/*      */   }
/*      */   
/*      */   private void addException(Segment segment) {
/*  853 */     if (segment.inIncludeSegments()) {
/*  854 */       int p = binarySearchExceptionSegments(segment);
/*  855 */       this.exceptionSegments.add(-(p + 1), segment);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addBaseTimelineException(long domainValue) {
/*  877 */     Segment baseSegment = this.baseTimeline.getSegment(domainValue);
/*  878 */     if (baseSegment.inIncludeSegments()) {
/*  882 */       Segment segment = getSegment(baseSegment.getSegmentStart());
/*  883 */       while (segment.getSegmentStart() <= baseSegment.getSegmentEnd()) {
/*  884 */         if (segment.inIncludeSegments()) {
/*  887 */           long toDomainValue, fromDomainValue = segment.getSegmentStart();
/*      */           do {
/*  890 */             toDomainValue = segment.getSegmentEnd();
/*  891 */             segment.inc();
/*  893 */           } while (segment.inIncludeSegments());
/*  896 */           addException(fromDomainValue, toDomainValue);
/*      */           continue;
/*      */         } 
/*  901 */         segment.inc();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addBaseTimelineException(Date date) {
/*  920 */     addBaseTimelineException(getTime(date));
/*      */   }
/*      */   
/*      */   public void addBaseTimelineExclusions(long fromBaseDomainValue, long toBaseDomainValue) {
/*  936 */     Segment baseSegment = this.baseTimeline.getSegment(fromBaseDomainValue);
/*  938 */     while (baseSegment.getSegmentStart() <= toBaseDomainValue && !baseSegment.inExcludeSegments())
/*  940 */       baseSegment.inc(); 
/*  945 */     while (baseSegment.getSegmentStart() <= toBaseDomainValue) {
/*  947 */       long baseExclusionRangeEnd = baseSegment.getSegmentStart() + this.baseTimeline.getSegmentsExcluded() * this.baseTimeline.getSegmentSize() - 1L;
/*  953 */       Segment segment = getSegment(baseSegment.getSegmentStart());
/*  954 */       while (segment.getSegmentStart() <= baseExclusionRangeEnd) {
/*  955 */         if (segment.inIncludeSegments()) {
/*  958 */           long toDomainValue, fromDomainValue = segment.getSegmentStart();
/*      */           do {
/*  961 */             toDomainValue = segment.getSegmentEnd();
/*  962 */             segment.inc();
/*  964 */           } while (segment.inIncludeSegments());
/*  967 */           addException(new BaseTimelineSegmentRange(this, fromDomainValue, toDomainValue));
/*      */           continue;
/*      */         } 
/*  973 */         segment.inc();
/*      */       } 
/*  978 */       baseSegment.inc(this.baseTimeline.getGroupSegmentCount());
/*      */     } 
/*      */   }
/*      */   
/*      */   public long getExceptionSegmentCount(long fromMillisecond, long toMillisecond) {
/*  993 */     if (toMillisecond < fromMillisecond)
/*  994 */       return 0L; 
/*  997 */     int n = 0;
/*  998 */     Iterator iter = this.exceptionSegments.iterator();
/*  999 */     while (iter.hasNext()) {
/* 1000 */       Segment segment = iter.next();
/* 1001 */       Segment intersection = segment.intersect(fromMillisecond, toMillisecond);
/* 1003 */       if (intersection != null)
/* 1004 */         n = (int)(n + intersection.getSegmentCount()); 
/*      */     } 
/* 1008 */     return n;
/*      */   }
/*      */   
/*      */   public Segment getSegment(long millisecond) {
/* 1023 */     return new Segment(this, millisecond);
/*      */   }
/*      */   
/*      */   public Segment getSegment(Date date) {
/* 1041 */     return getSegment(getTime(date));
/*      */   }
/*      */   
/*      */   private boolean equals(Object o, Object p) {
/* 1055 */     return (o == p || (o != null && o.equals(p)));
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/* 1066 */     if (o instanceof SegmentedTimeline) {
/* 1067 */       SegmentedTimeline other = (SegmentedTimeline)o;
/* 1069 */       boolean b0 = (this.segmentSize == other.getSegmentSize());
/* 1070 */       boolean b1 = (this.segmentsIncluded == other.getSegmentsIncluded());
/* 1071 */       boolean b2 = (this.segmentsExcluded == other.getSegmentsExcluded());
/* 1072 */       boolean b3 = (this.startTime == other.getStartTime());
/* 1073 */       boolean b4 = equals(this.exceptionSegments, other.getExceptionSegments());
/* 1076 */       return (b0 && b1 && b2 && b3 && b4);
/*      */     } 
/* 1079 */     return false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1089 */     int result = 19;
/* 1090 */     result = 37 * result + (int)(this.segmentSize ^ this.segmentSize >>> 32L);
/* 1092 */     result = 37 * result + (int)(this.startTime ^ this.startTime >>> 32L);
/* 1093 */     return result;
/*      */   }
/*      */   
/*      */   private int binarySearchExceptionSegments(Segment segment) {
/* 1112 */     int low = 0;
/* 1113 */     int high = this.exceptionSegments.size() - 1;
/* 1115 */     while (low <= high) {
/* 1116 */       int mid = (low + high) / 2;
/* 1117 */       Segment midSegment = this.exceptionSegments.get(mid);
/* 1120 */       if (segment.contains(midSegment) || midSegment.contains(segment))
/* 1121 */         return mid; 
/* 1124 */       if (midSegment.before(segment)) {
/* 1125 */         low = mid + 1;
/*      */         continue;
/*      */       } 
/* 1127 */       if (midSegment.after(segment)) {
/* 1128 */         high = mid - 1;
/*      */         continue;
/*      */       } 
/* 1131 */       throw new IllegalStateException("Invalid condition.");
/*      */     } 
/* 1134 */     return -(low + 1);
/*      */   }
/*      */   
/*      */   public long getTime(Date date) {
/* 1148 */     long result = date.getTime();
/* 1149 */     if (this.adjustForDaylightSaving) {
/* 1150 */       this.workingCalendar.setTime(date);
/* 1151 */       this.workingCalendarNoDST.set(this.workingCalendar.get(1), this.workingCalendar.get(2), this.workingCalendar.get(5), this.workingCalendar.get(11), this.workingCalendar.get(12), this.workingCalendar.get(13));
/* 1159 */       this.workingCalendarNoDST.set(14, this.workingCalendar.get(14));
/* 1163 */       Date revisedDate = this.workingCalendarNoDST.getTime();
/* 1164 */       result = revisedDate.getTime();
/*      */     } 
/* 1167 */     return result;
/*      */   }
/*      */   
/*      */   public Date getDate(long value) {
/* 1178 */     this.workingCalendarNoDST.setTime(new Date(value));
/* 1179 */     return this.workingCalendarNoDST.getTime();
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1190 */     SegmentedTimeline clone = (SegmentedTimeline)super.clone();
/* 1191 */     return clone;
/*      */   }
/*      */   
/*      */   public class Segment implements Comparable, Cloneable, Serializable {
/*      */     protected long segmentNumber;
/*      */     
/*      */     protected long segmentStart;
/*      */     
/*      */     protected long segmentEnd;
/*      */     
/*      */     protected long millisecond;
/*      */     
/*      */     private final SegmentedTimeline this$0;
/*      */     
/*      */     protected Segment(SegmentedTimeline this$0) {
/* 1219 */       this.this$0 = this$0;
/*      */     }
/*      */     
/*      */     protected Segment(SegmentedTimeline this$0, long millisecond) {
/* 1228 */       this.this$0 = this$0;
/* 1229 */       this.segmentNumber = calculateSegmentNumber(millisecond);
/* 1230 */       this.segmentStart = this$0.startTime + this.segmentNumber * this$0.segmentSize;
/* 1232 */       this.segmentEnd = this.segmentStart + this$0.segmentSize - 1L;
/* 1234 */       this.millisecond = millisecond;
/*      */     }
/*      */     
/*      */     public long calculateSegmentNumber(long millis) {
/* 1245 */       if (millis >= this.this$0.startTime)
/* 1246 */         return (millis - this.this$0.startTime) / this.this$0.segmentSize; 
/* 1250 */       return (millis - this.this$0.startTime) / this.this$0.segmentSize - 1L;
/*      */     }
/*      */     
/*      */     public long getSegmentNumber() {
/* 1261 */       return this.segmentNumber;
/*      */     }
/*      */     
/*      */     public long getSegmentCount() {
/* 1271 */       return 1L;
/*      */     }
/*      */     
/*      */     public long getSegmentStart() {
/* 1280 */       return this.segmentStart;
/*      */     }
/*      */     
/*      */     public long getSegmentEnd() {
/* 1289 */       return this.segmentEnd;
/*      */     }
/*      */     
/*      */     public long getMillisecond() {
/* 1299 */       return this.millisecond;
/*      */     }
/*      */     
/*      */     public Date getDate() {
/* 1309 */       return this.this$0.getDate(this.millisecond);
/*      */     }
/*      */     
/*      */     public boolean contains(long millis) {
/* 1322 */       return (this.segmentStart <= millis && millis <= this.segmentEnd);
/*      */     }
/*      */     
/*      */     public boolean contains(long from, long to) {
/* 1336 */       return (this.segmentStart <= from && to <= this.segmentEnd);
/*      */     }
/*      */     
/*      */     public boolean contains(Segment segment) {
/* 1348 */       return contains(segment.getSegmentStart(), segment.getSegmentEnd());
/*      */     }
/*      */     
/*      */     public boolean contained(long from, long to) {
/* 1362 */       return (from <= this.segmentStart && this.segmentEnd <= to);
/*      */     }
/*      */     
/*      */     public Segment intersect(long from, long to) {
/* 1375 */       if (from <= this.segmentStart && this.segmentEnd <= to)
/* 1376 */         return this; 
/* 1379 */       return null;
/*      */     }
/*      */     
/*      */     public boolean before(Segment other) {
/* 1392 */       return (this.segmentEnd < other.getSegmentStart());
/*      */     }
/*      */     
/*      */     public boolean after(Segment other) {
/* 1404 */       return (this.segmentStart > other.getSegmentEnd());
/*      */     }
/*      */     
/*      */     public boolean equals(Object object) {
/* 1416 */       if (object instanceof Segment) {
/* 1417 */         Segment other = (Segment)object;
/* 1418 */         return (this.segmentNumber == other.getSegmentNumber() && this.segmentStart == other.getSegmentStart() && this.segmentEnd == other.getSegmentEnd() && this.millisecond == other.getMillisecond());
/*      */       } 
/* 1424 */       return false;
/*      */     }
/*      */     
/*      */     public Segment copy() {
/*      */       try {
/* 1436 */         return (Segment)clone();
/* 1438 */       } catch (CloneNotSupportedException e) {
/* 1439 */         return null;
/*      */       } 
/*      */     }
/*      */     
/*      */     public int compareTo(Object object) {
/* 1453 */       Segment other = (Segment)object;
/* 1454 */       if (before(other))
/* 1455 */         return -1; 
/* 1457 */       if (after(other))
/* 1458 */         return 1; 
/* 1461 */       return 0;
/*      */     }
/*      */     
/*      */     public boolean inIncludeSegments() {
/* 1472 */       if (getSegmentNumberRelativeToGroup() < this.this$0.segmentsIncluded)
/* 1474 */         return !inExceptionSegments(); 
/* 1477 */       return false;
/*      */     }
/*      */     
/*      */     public boolean inExcludeSegments() {
/* 1487 */       return (getSegmentNumberRelativeToGroup() >= this.this$0.segmentsIncluded);
/*      */     }
/*      */     
/*      */     private long getSegmentNumberRelativeToGroup() {
/* 1500 */       long p = this.segmentNumber % this.this$0.groupSegmentCount;
/* 1502 */       if (p < 0L)
/* 1503 */         p += this.this$0.groupSegmentCount; 
/* 1505 */       return p;
/*      */     }
/*      */     
/*      */     public boolean inExceptionSegments() {
/* 1520 */       return (this.this$0.binarySearchExceptionSegments(this) >= 0);
/*      */     }
/*      */     
/*      */     public void inc(long n) {
/* 1530 */       this.segmentNumber += n;
/* 1531 */       long m = n * this.this$0.segmentSize;
/* 1532 */       this.segmentStart += m;
/* 1533 */       this.segmentEnd += m;
/* 1534 */       this.millisecond += m;
/*      */     }
/*      */     
/*      */     public void inc() {
/* 1542 */       inc(1L);
/*      */     }
/*      */     
/*      */     public void dec(long n) {
/* 1552 */       this.segmentNumber -= n;
/* 1553 */       long m = n * this.this$0.segmentSize;
/* 1554 */       this.segmentStart -= m;
/* 1555 */       this.segmentEnd -= m;
/* 1556 */       this.millisecond -= m;
/*      */     }
/*      */     
/*      */     public void dec() {
/* 1564 */       dec(1L);
/*      */     }
/*      */     
/*      */     public void moveIndexToStart() {
/* 1571 */       this.millisecond = this.segmentStart;
/*      */     }
/*      */     
/*      */     public void moveIndexToEnd() {
/* 1578 */       this.millisecond = this.segmentEnd;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class SegmentRange extends Segment {
/*      */     private long segmentCount;
/*      */     
/*      */     private final SegmentedTimeline this$0;
/*      */     
/*      */     public SegmentRange(SegmentedTimeline this$0, long fromMillisecond, long toMillisecond) {
/* 1601 */       super(this$0);
/* 1601 */       this.this$0 = this$0;
/* 1603 */       SegmentedTimeline.Segment start = this$0.getSegment(fromMillisecond);
/* 1604 */       SegmentedTimeline.Segment end = this$0.getSegment(toMillisecond);
/* 1611 */       this.millisecond = fromMillisecond;
/* 1612 */       this.segmentNumber = calculateSegmentNumber(fromMillisecond);
/* 1613 */       this.segmentStart = start.segmentStart;
/* 1614 */       this.segmentEnd = end.segmentEnd;
/* 1615 */       this.segmentCount = end.getSegmentNumber() - start.getSegmentNumber() + 1L;
/*      */     }
/*      */     
/*      */     public long getSegmentCount() {
/* 1625 */       return this.segmentCount;
/*      */     }
/*      */     
/*      */     public SegmentedTimeline.Segment intersect(long from, long to) {
/* 1643 */       long start = Math.max(from, this.segmentStart);
/* 1644 */       long end = Math.min(to, this.segmentEnd);
/* 1649 */       if (start <= end)
/* 1650 */         return new SegmentRange(this.this$0, start, end); 
/* 1653 */       return null;
/*      */     }
/*      */     
/*      */     public boolean inIncludeSegments() {
/* 1664 */       SegmentedTimeline.Segment segment = this.this$0.getSegment(this.segmentStart);
/* 1665 */       for (; segment.getSegmentStart() < this.segmentEnd; 
/* 1666 */         segment.inc()) {
/* 1667 */         if (!segment.inIncludeSegments())
/* 1668 */           return false; 
/*      */       } 
/* 1671 */       return true;
/*      */     }
/*      */     
/*      */     public boolean inExcludeSegments() {
/* 1680 */       SegmentedTimeline.Segment segment = this.this$0.getSegment(this.segmentStart);
/* 1681 */       for (; segment.getSegmentStart() < this.segmentEnd; 
/* 1682 */         segment.inc()) {
/* 1683 */         if (!segment.inExceptionSegments())
/* 1684 */           return false; 
/*      */       } 
/* 1687 */       return true;
/*      */     }
/*      */     
/*      */     public void inc(long n) {
/* 1697 */       throw new IllegalArgumentException("Not implemented in SegmentRange");
/*      */     }
/*      */   }
/*      */   
/*      */   protected class BaseTimelineSegmentRange extends SegmentRange {
/*      */     private final SegmentedTimeline this$0;
/*      */     
/*      */     public BaseTimelineSegmentRange(SegmentedTimeline this$0, long fromDomainValue, long toDomainValue) {
/* 1717 */       super(this$0, fromDomainValue, toDomainValue);
/*      */       this.this$0 = this$0;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\SegmentedTimeline.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */