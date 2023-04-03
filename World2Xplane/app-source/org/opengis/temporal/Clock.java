package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "TM_Clock", specification = Specification.ISO_19108)
public interface Clock extends TemporalReferenceSystem {
  @UML(identifier = "referenceEvent", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  InternationalString getReferenceEvent();
  
  @UML(identifier = "ReferenceTime", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  ClockTime getReferenceTime();
  
  @UML(identifier = "utcReference", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  ClockTime getUTCReference();
  
  @UML(identifier = "clkTrans", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  ClockTime clkTrans(ClockTime paramClockTime);
  
  @UML(identifier = "utcTrans", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  ClockTime utcTrans(ClockTime paramClockTime);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\Clock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */