package org.opengis.metadata.constraint;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_Constraints", specification = Specification.ISO_19115)
public interface Constraints {
  @UML(identifier = "useLimitation", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getUseLimitation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\constraint\Constraints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */