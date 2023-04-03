package org.opengis.metadata.spatial;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_GeometricObjects", specification = Specification.ISO_19115)
public interface GeometricObjects {
  @UML(identifier = "geometricObjectType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  GeometricObjectType getGeometricObjectType();
  
  @UML(identifier = "geometricObjectCount", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Integer getGeometricObjectCount();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\GeometricObjects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */