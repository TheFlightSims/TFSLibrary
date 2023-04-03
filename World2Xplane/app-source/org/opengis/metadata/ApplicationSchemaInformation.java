package org.opengis.metadata;

import java.net.URI;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;

@UML(identifier = "MD_ApplicationSchemaInformation", specification = Specification.ISO_19115)
public interface ApplicationSchemaInformation {
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Citation getName();
  
  @UML(identifier = "schemaLanguage", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  String getSchemaLanguage();
  
  @UML(identifier = "constraintLanguage", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  String getConstraintLanguage();
  
  @UML(identifier = "schemaAscii", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  URI getSchemaAscii();
  
  @UML(identifier = "graphicsFile", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  URI getGraphicsFile();
  
  @UML(identifier = "softwareDevelopmentFile", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  URI getSoftwareDevelopmentFile();
  
  @UML(identifier = "softwareDevelopmentFileFormat", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getSoftwareDevelopmentFileFormat();
  
  @Deprecated
  @UML(identifier = "featureCatalogueSupplement", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  SpatialAttributeSupplement getFeatureCatalogueSupplement();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\ApplicationSchemaInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */