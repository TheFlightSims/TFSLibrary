package com.vividsolutions.jts.geom;

public interface CoordinateSequenceFactory {
  CoordinateSequence create(Coordinate[] paramArrayOfCoordinate);
  
  CoordinateSequence create(CoordinateSequence paramCoordinateSequence);
  
  CoordinateSequence create(int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\CoordinateSequenceFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */