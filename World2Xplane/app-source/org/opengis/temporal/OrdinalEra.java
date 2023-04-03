package org.opengis.temporal;

import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "TM_OrdinalEra", specification = Specification.ISO_19108)
public interface OrdinalEra {
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  InternationalString getName();
  
  @UML(identifier = "begin", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Date getBeginning();
  
  @UML(identifier = "end", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Date getEnd();
  
  @UML(identifier = "Composition", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Collection<OrdinalEra> getComposition();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\OrdinalEra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */