package com.seisw.util.geom;

public interface Poly {
  void clear();
  
  void add(double paramDouble1, double paramDouble2);
  
  void add(Point2D paramPoint2D);
  
  void add(Poly paramPoly);
  
  boolean isEmpty();
  
  Rectangle2D getBounds();
  
  Poly getInnerPoly(int paramInt);
  
  int getNumInnerPoly();
  
  int getNumPoints();
  
  double getX(int paramInt);
  
  double getY(int paramInt);
  
  boolean isHole();
  
  void setIsHole(boolean paramBoolean);
  
  boolean isContributing(int paramInt);
  
  void setContributing(int paramInt, boolean paramBoolean);
  
  Poly intersection(Poly paramPoly);
  
  Poly union(Poly paramPoly);
  
  Poly xor(Poly paramPoly);
  
  Poly difference(Poly paramPoly);
  
  double getArea();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\seis\\util\geom\Poly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */