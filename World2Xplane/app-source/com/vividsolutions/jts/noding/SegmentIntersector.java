package com.vividsolutions.jts.noding;

public interface SegmentIntersector {
  void processIntersections(SegmentString paramSegmentString1, int paramInt1, SegmentString paramSegmentString2, int paramInt2);
  
  boolean isDone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */