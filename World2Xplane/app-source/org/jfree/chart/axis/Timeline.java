package org.jfree.chart.axis;

import java.util.Date;

public interface Timeline {
  long toTimelineValue(long paramLong);
  
  long toTimelineValue(Date paramDate);
  
  long toMillisecond(long paramLong);
  
  boolean containsDomainValue(long paramLong);
  
  boolean containsDomainValue(Date paramDate);
  
  boolean containsDomainRange(long paramLong1, long paramLong2);
  
  boolean containsDomainRange(Date paramDate1, Date paramDate2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\Timeline.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */