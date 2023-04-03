package org.opengis.metadata.extent;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "EX_Extent", specification = Specification.ISO_19115)
public interface Extent {
  @UML(identifier = "description", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  InternationalString getDescription();
  
  @UML(identifier = "geographicElement", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends GeographicExtent> getGeographicElements();
  
  @UML(identifier = "temporalElement", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends TemporalExtent> getTemporalElements();
  
  @UML(identifier = "verticalElement", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends VerticalExtent> getVerticalElements();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\Extent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */