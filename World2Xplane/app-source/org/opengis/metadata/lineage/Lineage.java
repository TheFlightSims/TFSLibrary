package org.opengis.metadata.lineage;

import java.util.Collection;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "LI_Lineage", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface Lineage {
  @UML(identifier = "statement", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  InternationalString getStatement();
  
  @UML(identifier = "processStep", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends ProcessStep> getProcessSteps();
  
  @UML(identifier = "source", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends Source> getSources();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\lineage\Lineage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */