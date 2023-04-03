package org.opengis.temporal;

import java.util.Collection;
import java.util.Date;
import javax.measure.unit.Unit;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.util.InternationalString;

public interface TemporalFactory {
  Calendar createCalendar(ReferenceIdentifier paramReferenceIdentifier, Extent paramExtent);
  
  CalendarDate createCalendarDate(TemporalReferenceSystem paramTemporalReferenceSystem, IndeterminateValue paramIndeterminateValue, InternationalString paramInternationalString, int[] paramArrayOfint);
  
  CalendarEra createCalendarEra(InternationalString paramInternationalString1, InternationalString paramInternationalString2, CalendarDate paramCalendarDate, JulianDate paramJulianDate, Period paramPeriod);
  
  Clock createClock(ReferenceIdentifier paramReferenceIdentifier, Extent paramExtent, InternationalString paramInternationalString, ClockTime paramClockTime1, ClockTime paramClockTime2);
  
  ClockTime createClockTime(TemporalReferenceSystem paramTemporalReferenceSystem, IndeterminateValue paramIndeterminateValue, Number[] paramArrayOfNumber);
  
  DateAndTime createDateAndTime(TemporalReferenceSystem paramTemporalReferenceSystem, IndeterminateValue paramIndeterminateValue, InternationalString paramInternationalString, int[] paramArrayOfint, Number[] paramArrayOfNumber);
  
  Instant createInstant(Position paramPosition);
  
  IntervalLength createIntervalLenght(Unit paramUnit, int paramInt1, int paramInt2, int paramInt3);
  
  JulianDate createJulianDate(TemporalReferenceSystem paramTemporalReferenceSystem, IndeterminateValue paramIndeterminateValue, Number paramNumber);
  
  OrdinalEra createOrdinalEra(InternationalString paramInternationalString, Date paramDate1, Date paramDate2, Collection<OrdinalEra> paramCollection);
  
  OrdinalPosition createOrdinalPosition(TemporalReferenceSystem paramTemporalReferenceSystem, IndeterminateValue paramIndeterminateValue, OrdinalEra paramOrdinalEra);
  
  OrdinalReferenceSystem createOrdinalReferenceSystem(ReferenceIdentifier paramReferenceIdentifier, Extent paramExtent, Collection<OrdinalEra> paramCollection);
  
  Period createPeriod(Instant paramInstant1, Instant paramInstant2);
  
  PeriodDuration createPeriodDuration(InternationalString paramInternationalString1, InternationalString paramInternationalString2, InternationalString paramInternationalString3, InternationalString paramInternationalString4, InternationalString paramInternationalString5, InternationalString paramInternationalString6, InternationalString paramInternationalString7);
  
  Position createPosition(Date paramDate);
  
  TemporalCoordinate createTemporalCoordinate(TemporalReferenceSystem paramTemporalReferenceSystem, IndeterminateValue paramIndeterminateValue, Number paramNumber);
  
  TemporalCoordinateSystem createTemporalCoordinateSystem(ReferenceIdentifier paramReferenceIdentifier, Extent paramExtent, Date paramDate, InternationalString paramInternationalString);
  
  TemporalPosition createTemporalPosition(TemporalReferenceSystem paramTemporalReferenceSystem, IndeterminateValue paramIndeterminateValue);
  
  TemporalReferenceSystem createTemporalReferenceSystem(ReferenceIdentifier paramReferenceIdentifier, Extent paramExtent);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */