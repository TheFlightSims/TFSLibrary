package org.opengis.metadata.quality;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.maintenance.ScopeDescription;

@UML(identifier = "DQ_Scope", specification = Specification.ISO_19115)
public interface Scope {
  @UML(identifier = "level", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  ScopeCode getLevel();
  
  @UML(identifier = "levelDescription", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends ScopeDescription> getLevelDescription();
  
  @UML(identifier = "extent", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Extent getExtent();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\quality\Scope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */