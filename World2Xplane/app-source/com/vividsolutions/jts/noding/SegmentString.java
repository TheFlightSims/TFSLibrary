package com.vividsolutions.jts.noding;

import com.vividsolutions.jts.geom.Coordinate;

public interface SegmentString {
  Object getData();
  
  void setData(Object paramObject);
  
  int size();
  
  Coordinate getCoordinate(int paramInt);
  
  Coordinate[] getCoordinates();
  
  boolean isClosed();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */