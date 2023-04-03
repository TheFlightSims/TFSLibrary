package org.opengis.metadata.extent;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.crs.VerticalCRS;

@UML(identifier = "EX_VerticalExtent", specification = Specification.ISO_19115)
public interface VerticalExtent {
  @UML(identifier = "minimumValue", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Double getMinimumValue();
  
  @UML(identifier = "maximumValue", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Double getMaximumValue();
  
  @UML(identifier = "verticalCRS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  VerticalCRS getVerticalCRS();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\VerticalExtent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */