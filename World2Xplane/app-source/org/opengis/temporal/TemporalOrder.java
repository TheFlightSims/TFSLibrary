package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Order", specification = Specification.ISO_19108)
public interface TemporalOrder {
  @UML(identifier = "relativePosition", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  RelativePosition relativePosition(TemporalPrimitive paramTemporalPrimitive);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */