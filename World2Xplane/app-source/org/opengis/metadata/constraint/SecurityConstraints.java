package org.opengis.metadata.constraint;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_SecurityConstraints", specification = Specification.ISO_19115)
public interface SecurityConstraints extends Constraints {
  @UML(identifier = "classification", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Classification getClassification();
  
  @UML(identifier = "userNote", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getUserNote();
  
  @UML(identifier = "classificationSystem", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getClassificationSystem();
  
  @UML(identifier = "handlingDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getHandlingDescription();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\constraint\SecurityConstraints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */