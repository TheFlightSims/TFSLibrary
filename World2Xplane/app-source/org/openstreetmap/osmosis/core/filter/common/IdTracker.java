package org.openstreetmap.osmosis.core.filter.common;

public interface IdTracker extends Iterable<Long> {
  void set(long paramLong);
  
  boolean get(long paramLong);
  
  void setAll(IdTracker paramIdTracker);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\filter\common\IdTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */