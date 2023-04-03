package org.opengis.referencing.datum;

import javax.measure.quantity.Angle;
import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.IdentifiedObject;

@UML(identifier = "CD_PrimeMeridian", specification = Specification.ISO_19111)
public interface PrimeMeridian extends IdentifiedObject {
  @UML(identifier = "greenwichLongitude", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  double getGreenwichLongitude();
  
  @UML(identifier = "getAngularUnit", specification = Specification.OGC_01009)
  Unit<Angle> getAngularUnit();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\PrimeMeridian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */