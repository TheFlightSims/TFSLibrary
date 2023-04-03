package org.apache.commons.math3.geometry;

import java.io.Serializable;
import java.text.NumberFormat;

public interface Vector<S extends Space> extends Serializable {
  Space getSpace();
  
  Vector<S> getZero();
  
  double getNorm1();
  
  double getNorm();
  
  double getNormSq();
  
  double getNormInf();
  
  Vector<S> add(Vector<S> paramVector);
  
  Vector<S> add(double paramDouble, Vector<S> paramVector);
  
  Vector<S> subtract(Vector<S> paramVector);
  
  Vector<S> subtract(double paramDouble, Vector<S> paramVector);
  
  Vector<S> negate();
  
  Vector<S> normalize();
  
  Vector<S> scalarMultiply(double paramDouble);
  
  boolean isNaN();
  
  boolean isInfinite();
  
  double distance1(Vector<S> paramVector);
  
  double distance(Vector<S> paramVector);
  
  double distanceInf(Vector<S> paramVector);
  
  double distanceSq(Vector<S> paramVector);
  
  double dotProduct(Vector<S> paramVector);
  
  String toString(NumberFormat paramNumberFormat);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\Vector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */