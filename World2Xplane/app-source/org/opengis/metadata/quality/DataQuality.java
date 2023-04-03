package org.opengis.metadata.quality;

import java.util.Collection;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.lineage.Lineage;

@UML(identifier = "DQ_DataQuality", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface DataQuality {
  @UML(identifier = "scope", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Scope getScope();
  
  @UML(identifier = "report", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends Element> getReports();
  
  @UML(identifier = "lineage", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Lineage getLineage();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\quality\DataQuality.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */