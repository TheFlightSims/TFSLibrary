package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Projection;

@UML(identifier = "SC_ProjectedCRS", specification = Specification.ISO_19111)
public interface ProjectedCRS extends GeneralDerivedCRS {
  GeographicCRS getBaseCRS();
  
  Projection getConversionFromBase();
  
  @UML(identifier = "usesCS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  CartesianCS getCoordinateSystem();
  
  GeodeticDatum getDatum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\ProjectedCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */