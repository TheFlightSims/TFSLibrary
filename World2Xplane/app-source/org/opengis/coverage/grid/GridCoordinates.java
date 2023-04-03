package org.opengis.coverage.grid;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.Cloneable;

@UML(identifier = "CV_GridCoordinates", specification = Specification.ISO_19123)
public interface GridCoordinates extends Cloneable {
  int getDimension();
  
  @UML(identifier = "coordValues", obligation = Obligation.MANDATORY, specification = Specification.ISO_19123)
  int[] getCoordinateValues();
  
  int getCoordinateValue(int paramInt) throws IndexOutOfBoundsException;
  
  void setCoordinateValue(int paramInt1, int paramInt2) throws IndexOutOfBoundsException, UnsupportedOperationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\GridCoordinates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */