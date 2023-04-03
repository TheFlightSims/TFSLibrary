package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.datum.VerticalDatum;

@UML(identifier = "SC_VerticalCRS", specification = Specification.ISO_19111)
public interface VerticalCRS extends SingleCRS {
  @UML(identifier = "usesCS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  VerticalCS getCoordinateSystem();
  
  @UML(identifier = "usesDatum", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  VerticalDatum getDatum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\VerticalCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */