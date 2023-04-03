package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "TM_CalDate", specification = Specification.ISO_19108)
public interface CalendarDate extends TemporalPosition {
  @UML(identifier = "calendarEraName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  InternationalString getCalendarEraName();
  
  @UML(identifier = "calDate", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  int[] getCalendarDate();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\CalendarDate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */