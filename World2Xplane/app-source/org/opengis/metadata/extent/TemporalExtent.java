package org.opengis.metadata.extent;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.temporal.TemporalPrimitive;

@UML(identifier = "EX_TemporalExtent", specification = Specification.ISO_19115)
public interface TemporalExtent {
  @UML(identifier = "extent", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  TemporalPrimitive getExtent();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\TemporalExtent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */