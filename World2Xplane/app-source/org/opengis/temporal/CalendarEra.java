package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "TM_CalendarEra", specification = Specification.ISO_19108)
public interface CalendarEra {
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  InternationalString getName();
  
  @UML(identifier = "referenceEvent", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getReferenceEvent();
  
  @UML(identifier = "referenceDate", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  CalendarDate getReferenceDate();
  
  @UML(identifier = "julianReference", specification = Specification.ISO_19108)
  JulianDate getJulianReference();
  
  @UML(identifier = "epochOfUse", specification = Specification.ISO_19108)
  Period getEpochOfUse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\CalendarEra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */