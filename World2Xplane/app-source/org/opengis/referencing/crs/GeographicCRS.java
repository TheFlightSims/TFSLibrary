package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.cs.EllipsoidalCS;

@UML(identifier = "SC_GeographicCRS", specification = Specification.ISO_19111)
public interface GeographicCRS extends GeodeticCRS {
  @UML(identifier = "usesCS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  EllipsoidalCS getCoordinateSystem();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\GeographicCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */