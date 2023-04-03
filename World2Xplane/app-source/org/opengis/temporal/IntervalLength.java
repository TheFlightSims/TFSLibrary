package org.opengis.temporal;

import javax.measure.unit.Unit;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_IntervalLength", specification = Specification.ISO_19108)
public interface IntervalLength extends Duration {
  @UML(identifier = "unit", specification = Specification.ISO_19108)
  Unit getUnit();
  
  @UML(identifier = "radix", specification = Specification.ISO_19108)
  int getRadix();
  
  @UML(identifier = "factor", specification = Specification.ISO_19108)
  int getFactor();
  
  @UML(identifier = "value", specification = Specification.ISO_19108)
  int getValue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\IntervalLength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */