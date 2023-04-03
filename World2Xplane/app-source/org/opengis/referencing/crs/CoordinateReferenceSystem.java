package org.opengis.referencing.crs;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;

@UML(identifier = "SC_CRS", specification = Specification.ISO_19111)
public interface CoordinateReferenceSystem extends ReferenceSystem {
  CoordinateSystem getCoordinateSystem();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\CoordinateReferenceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */