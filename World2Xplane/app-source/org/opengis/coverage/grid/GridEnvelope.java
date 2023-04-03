package org.opengis.coverage.grid;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CV_GridEnvelope", specification = Specification.ISO_19123)
public interface GridEnvelope {
  int getDimension();
  
  @UML(identifier = "low", obligation = Obligation.MANDATORY, specification = Specification.ISO_19123)
  GridCoordinates getLow();
  
  @UML(identifier = "high", obligation = Obligation.MANDATORY, specification = Specification.ISO_19123)
  GridCoordinates getHigh();
  
  int getLow(int paramInt) throws IndexOutOfBoundsException;
  
  int getHigh(int paramInt) throws IndexOutOfBoundsException;
  
  int getSpan(int paramInt) throws IndexOutOfBoundsException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\GridEnvelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */