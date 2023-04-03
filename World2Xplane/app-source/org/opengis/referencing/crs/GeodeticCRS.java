package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.datum.GeodeticDatum;

@UML(identifier = "SC_GeodeticCRS", specification = Specification.ISO_19111)
public interface GeodeticCRS extends SingleCRS {
  @UML(identifier = "usesDatum", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  GeodeticDatum getDatum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\GeodeticCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */