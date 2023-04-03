package org.opengis.metadata.spatial;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;

@UML(identifier = "MD_Georeferenceable", specification = Specification.ISO_19115)
public interface Georeferenceable extends GridSpatialRepresentation {
  @UML(identifier = "controlPointAvailability", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  boolean isControlPointAvailable();
  
  @UML(identifier = "orientationParameterAvailability", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  boolean isOrientationParameterAvailable();
  
  @UML(identifier = "orientationParameterDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getOrientationParameterDescription();
  
  @UML(identifier = "georeferencedParameters", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Record getGeoreferencedParameters();
  
  @UML(identifier = "parameterCitation", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Citation> getParameterCitation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\Georeferenceable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */