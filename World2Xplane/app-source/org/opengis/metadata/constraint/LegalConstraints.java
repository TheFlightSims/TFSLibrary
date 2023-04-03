package org.opengis.metadata.constraint;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_LegalConstraints", specification = Specification.ISO_19115)
public interface LegalConstraints extends Constraints {
  @UML(identifier = "accessConstraints", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<Restriction> getAccessConstraints();
  
  @UML(identifier = "useConstraints", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<Restriction> getUseConstraints();
  
  @UML(identifier = "otherConstraints", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getOtherConstraints();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\constraint\LegalConstraints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */