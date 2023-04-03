package org.geotools.referencing.operation;

import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.Matrix;

public interface LinearTransform extends MathTransform {
  Matrix getMatrix();
  
  boolean isIdentity(double paramDouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\LinearTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */