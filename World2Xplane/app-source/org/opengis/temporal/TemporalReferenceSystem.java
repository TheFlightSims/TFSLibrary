package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.ReferenceSystem;

@UML(identifier = "TM_ReferenceSystem", specification = Specification.ISO_19108)
public interface TemporalReferenceSystem extends ReferenceSystem {
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  ReferenceIdentifier getName();
  
  @UML(identifier = "DomainOfValidity", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Extent getDomainOfValidity();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalReferenceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */