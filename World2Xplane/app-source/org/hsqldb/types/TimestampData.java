package org.hsqldb.types;

public class TimestampData {
  final long seconds;
  
  int nanos;
  
  final int zone;
  
  public TimestampData(long paramLong) {
    this.seconds = paramLong;
    this.nanos = 0;
    this.zone = 0;
  }
  
  public TimestampData(long paramLong, int paramInt) {
    this.seconds = paramLong;
    this.nanos = paramInt;
    this.zone = 0;
  }
  
  public TimestampData(long paramLong, int paramInt1, int paramInt2) {
    this.seconds = paramLong;
    this.nanos = paramInt1;
    this.zone = paramInt2;
  }
  
  public long getSeconds() {
    return this.seconds;
  }
  
  public int getNanos() {
    return this.nanos;
  }
  
  public int getZone() {
    return this.zone;
  }
  
  public void clearNanos() {
    this.nanos = 0;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof TimestampData) ? ((this.seconds == ((TimestampData)paramObject).seconds && this.nanos == ((TimestampData)paramObject).nanos && this.zone == ((TimestampData)paramObject).zone)) : false;
  }
  
  public int hashCode() {
    return (int)this.seconds ^ this.nanos;
  }
  
  public int compareTo(TimestampData paramTimestampData) {
    long l = this.seconds - paramTimestampData.seconds;
    if (l == 0L) {
      l = (this.nanos - paramTimestampData.nanos);
      if (l == 0L)
        return 0; 
    } 
    return (l > 0L) ? 1 : -1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\TimestampData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */