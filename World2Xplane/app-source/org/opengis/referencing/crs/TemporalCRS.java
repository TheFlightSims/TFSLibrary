package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.datum.TemporalDatum;

@UML(identifier = "SC_TemporalCRS", specification = Specification.ISO_19111)
public interface TemporalCRS extends SingleCRS {
  @UML(identifier = "usesCS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  TimeCS getCoordinateSystem();
  
  @UML(identifier = "usesDatum", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  TemporalDatum getDatum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\TemporalCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */