package org.opengis.metadata;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_FeatureTypeList", specification = Specification.ISO_19115)
public interface FeatureTypeList {
  @UML(identifier = "spatialObject", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  String getSpatialObject();
  
  @UML(identifier = "spatialSchemaName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  String getSpatialSchemaName();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\FeatureTypeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */