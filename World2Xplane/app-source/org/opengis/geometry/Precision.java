package org.opengis.geometry;

public interface Precision extends Comparable<Precision> {
  int compareTo(Precision paramPrecision);
  
  double getScale();
  
  PrecisionType getType();
  
  void round(DirectPosition paramDirectPosition);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\Precision.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */