package org.opengis.metadata.extent;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

@UML(identifier = "EX_GeographicDescription", specification = Specification.ISO_19115)
public interface GeographicDescription extends GeographicExtent {
  @UML(identifier = "geographicIdentifier", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Identifier getGeographicIdentifier();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\GeographicDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */