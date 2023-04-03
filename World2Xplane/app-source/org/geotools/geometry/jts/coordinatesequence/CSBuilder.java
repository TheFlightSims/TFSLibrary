package org.geotools.geometry.jts.coordinatesequence;

import com.vividsolutions.jts.geom.CoordinateSequence;

public interface CSBuilder {
  void start(int paramInt1, int paramInt2);
  
  CoordinateSequence end();
  
  void setOrdinate(double paramDouble, int paramInt1, int paramInt2);
  
  void setOrdinate(CoordinateSequence paramCoordinateSequence, double paramDouble, int paramInt1, int paramInt2);
  
  double getOrdinate(int paramInt1, int paramInt2);
  
  int getSize();
  
  int getDimension();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\coordinatesequence\CSBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */