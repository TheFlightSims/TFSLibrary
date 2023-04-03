package org.opengis.referencing.cs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.IdentifiedObject;

@UML(identifier = "CS_CoordinateSystem", specification = Specification.ISO_19111)
public interface CoordinateSystem extends IdentifiedObject {
  int getDimension();
  
  @UML(identifier = "usesAxis", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  CoordinateSystemAxis getAxis(int paramInt) throws IndexOutOfBoundsException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\cs\CoordinateSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */