package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.datum.Datum;

@UML(identifier = "SC_SingleCRS", specification = Specification.ISO_19111)
public interface SingleCRS extends CoordinateReferenceSystem {
  @UML(identifier = "usesCS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  CoordinateSystem getCoordinateSystem();
  
  @UML(identifier = "usesDatum", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  Datum getDatum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\SingleCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */