package org.opengis.referencing.datum;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CD_VerticalDatum", specification = Specification.ISO_19111)
public interface VerticalDatum extends Datum {
  @UML(identifier = "vertDatumType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  VerticalDatumType getVerticalDatumType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\VerticalDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */