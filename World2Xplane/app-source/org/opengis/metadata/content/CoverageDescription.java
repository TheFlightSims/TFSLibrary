package org.opengis.metadata.content;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.RecordType;

@UML(identifier = "MD_CoverageDescription", specification = Specification.ISO_19115)
public interface CoverageDescription extends ContentInformation {
  @UML(identifier = "attributeDescription", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  RecordType getAttributeDescription();
  
  @UML(identifier = "contentType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  CoverageContentType getContentType();
  
  @UML(identifier = "dimension", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends RangeDimension> getDimensions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\content\CoverageDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */