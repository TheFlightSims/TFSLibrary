package org.opengis.referencing.datum;

import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "CD_TemporalDatum", specification = Specification.ISO_19111)
public interface TemporalDatum extends Datum {
  @UML(identifier = "origin", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  Date getOrigin();
  
  @UML(identifier = "anchorPoint", obligation = Obligation.FORBIDDEN, specification = Specification.ISO_19111)
  InternationalString getAnchorPoint();
  
  @UML(identifier = "realizationEpoch", obligation = Obligation.FORBIDDEN, specification = Specification.ISO_19111)
  Date getRealizationEpoch();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\TemporalDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */