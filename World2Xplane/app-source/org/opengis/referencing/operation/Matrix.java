package org.opengis.referencing.operation;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.Cloneable;

@UML(identifier = "PT_Matrix", specification = Specification.OGC_01009)
public interface Matrix extends Cloneable {
  int getNumRow();
  
  int getNumCol();
  
  double getElement(int paramInt1, int paramInt2);
  
  void setElement(int paramInt1, int paramInt2, double paramDouble);
  
  boolean isIdentity();
  
  Matrix clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\Matrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */