package com.vividsolutions.jts.geom;

public interface CoordinateSequence extends Cloneable {
  public static final int X = 0;
  
  public static final int Y = 1;
  
  public static final int Z = 2;
  
  public static final int M = 3;
  
  int getDimension();
  
  Coordinate getCoordinate(int paramInt);
  
  Coordinate getCoordinateCopy(int paramInt);
  
  void getCoordinate(int paramInt, Coordinate paramCoordinate);
  
  double getX(int paramInt);
  
  double getY(int paramInt);
  
  double getOrdinate(int paramInt1, int paramInt2);
  
  int size();
  
  void setOrdinate(int paramInt1, int paramInt2, double paramDouble);
  
  Coordinate[] toCoordinateArray();
  
  Envelope expandEnvelope(Envelope paramEnvelope);
  
  Object clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\CoordinateSequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */