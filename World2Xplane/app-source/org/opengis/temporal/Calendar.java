package org.opengis.temporal;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Calendar", specification = Specification.ISO_19108)
public interface Calendar extends TemporalReferenceSystem {
  @UML(identifier = "dateTrans", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  JulianDate dateTrans(CalendarDate paramCalendarDate, ClockTime paramClockTime);
  
  @UML(identifier = "julTrans", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  CalendarDate julTrans(JulianDate paramJulianDate);
  
  @UML(identifier = "Basis", specification = Specification.ISO_19108)
  Collection<CalendarEra> getBasis();
  
  @UML(identifier = "Resolution", specification = Specification.ISO_19108)
  Clock getClock();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\Calendar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */