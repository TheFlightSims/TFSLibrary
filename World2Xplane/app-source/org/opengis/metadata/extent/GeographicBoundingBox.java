package org.opengis.metadata.extent;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "EX_GeographicBoundingBox", specification = Specification.ISO_19115)
public interface GeographicBoundingBox extends GeographicExtent {
  @UML(identifier = "westBoundLongitude", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  double getWestBoundLongitude();
  
  @UML(identifier = "eastBoundLongitude", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  double getEastBoundLongitude();
  
  @UML(identifier = "southBoundLatitude", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  double getSouthBoundLatitude();
  
  @UML(identifier = "northBoundLatitude", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  double getNorthBoundLatitude();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\GeographicBoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */