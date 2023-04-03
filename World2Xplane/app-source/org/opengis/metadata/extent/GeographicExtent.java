package org.opengis.metadata.extent;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "EX_GeographicExtent", specification = Specification.ISO_19115)
public interface GeographicExtent {
  @UML(identifier = "extentTypeCode", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Boolean getInclusion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\GeographicExtent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */