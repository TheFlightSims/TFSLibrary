package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "TM_PeriodDuration", specification = Specification.ISO_19108)
public interface PeriodDuration extends Duration {
  @UML(identifier = "designator", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  InternationalString getDesignator();
  
  @UML(identifier = "years", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getYears();
  
  @UML(identifier = "months", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getMonths();
  
  @UML(identifier = "days", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getDays();
  
  @UML(identifier = "timeIndicator", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getTimeIndicator();
  
  @UML(identifier = "hours", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getHours();
  
  @UML(identifier = "minutes", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getMinutes();
  
  @UML(identifier = "seconds", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getSeconds();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\PeriodDuration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */