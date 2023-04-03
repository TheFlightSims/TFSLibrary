package org.opengis.referencing.datum;

import javax.measure.quantity.Length;
import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.IdentifiedObject;

@UML(identifier = "CD_Ellipsoid", specification = Specification.ISO_19111)
public interface Ellipsoid extends IdentifiedObject {
  @UML(identifier = "getAxisUnit", specification = Specification.OGC_01009)
  Unit<Length> getAxisUnit();
  
  @UML(identifier = "semiMajorAxis", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  double getSemiMajorAxis();
  
  @UML(identifier = "secondDefiningParameter.semiMinorAxis", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  double getSemiMinorAxis();
  
  @UML(identifier = "secondDefiningParameter.inverseFlattening", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  double getInverseFlattening();
  
  @UML(identifier = "CS_Ellipsoid.isIvfDefinitive", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01009)
  boolean isIvfDefinitive();
  
  @UML(identifier = "secondDefiningParameter.isSphere", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  boolean isSphere();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\Ellipsoid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */