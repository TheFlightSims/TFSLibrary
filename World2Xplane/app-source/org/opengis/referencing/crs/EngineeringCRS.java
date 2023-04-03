package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.datum.EngineeringDatum;

@UML(identifier = "SC_EngineeringCRS", specification = Specification.ISO_19111)
public interface EngineeringCRS extends SingleCRS {
  @UML(identifier = "usesDatum", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  EngineeringDatum getDatum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\EngineeringCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */