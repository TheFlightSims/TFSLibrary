package org.opengis.referencing.datum;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CD_GeodeticDatum", specification = Specification.ISO_19111)
public interface GeodeticDatum extends Datum {
  @UML(identifier = "usesEllipsoid", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  Ellipsoid getEllipsoid();
  
  @UML(identifier = "usesPrimeMeridian", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  PrimeMeridian getPrimeMeridian();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\GeodeticDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */