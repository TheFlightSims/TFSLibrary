package com.vividsolutions.jts.geom;

public interface CoordinateSequenceFilter {
  void filter(CoordinateSequence paramCoordinateSequence, int paramInt);
  
  boolean isDone();
  
  boolean isGeometryChanged();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\CoordinateSequenceFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */