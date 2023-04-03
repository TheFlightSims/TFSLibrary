package org.hsqldb.types;

import org.hsqldb.error.Error;

public class IntervalMonthData {
  public final int units;
  
  public static IntervalMonthData newInterval(double paramDouble, int paramInt) {
    int i = DTIType.intervalIndexMap.get(paramInt);
    paramDouble *= DTIType.yearToSecondFactors[i];
    return new IntervalMonthData((long)paramDouble);
  }
  
  public static IntervalMonthData newIntervalYear(long paramLong, IntervalType paramIntervalType) {
    return new IntervalMonthData(paramLong * 12L, paramIntervalType);
  }
  
  public static IntervalMonthData newIntervalMonth(long paramLong, IntervalType paramIntervalType) {
    return new IntervalMonthData(paramLong, paramIntervalType);
  }
  
  public IntervalMonthData(long paramLong, IntervalType paramIntervalType) {
    if (paramLong >= paramIntervalType.getIntervalValueLimit())
      throw Error.error(3406); 
    if (paramIntervalType.typeCode == 101)
      paramLong -= paramLong % 12L; 
    this.units = (int)paramLong;
  }
  
  public IntervalMonthData(long paramLong) {
    this.units = (int)paramLong;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof IntervalMonthData) ? ((this.units == ((IntervalMonthData)paramObject).units)) : false;
  }
  
  public int hashCode() {
    return this.units;
  }
  
  public int compareTo(IntervalMonthData paramIntervalMonthData) {
    return (this.units > paramIntervalMonthData.units) ? 1 : ((this.units < paramIntervalMonthData.units) ? -1 : 0);
  }
  
  public long getMonths() {
    return this.units;
  }
  
  public String toString() {
    return Type.SQL_INTERVAL_MONTH_MAX_PRECISION.convertToString(this);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\IntervalMonthData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */