package org.opengis.referencing.crs;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.cs.AffineCS;
import org.opengis.referencing.datum.ImageDatum;

@UML(identifier = "SC_ImageCRS", specification = Specification.ISO_19111)
public interface ImageCRS extends SingleCRS {
  @UML(identifier = "usesObliqueCartesianCS, usesCartesianCS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  AffineCS getCoordinateSystem();
  
  @UML(identifier = "usesDatum", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  ImageDatum getDatum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\ImageCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */