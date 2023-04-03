package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.cs.CoordinateSystem;

@UML(identifier = "SC_GeocentricCRS", specification = Specification.ISO_19111)
public interface GeocentricCRS extends GeodeticCRS {
  @UML(identifier = "usesCartesianCS, usesSphericalCS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  CoordinateSystem getCoordinateSystem();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\GeocentricCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */