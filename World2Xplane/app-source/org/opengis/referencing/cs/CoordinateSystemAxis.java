package org.opengis.referencing.cs;

import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.IdentifiedObject;

@UML(identifier = "CS_CoordinateSystemAxis", specification = Specification.ISO_19111)
public interface CoordinateSystemAxis extends IdentifiedObject {
  @UML(identifier = "axisAbbrev", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  String getAbbreviation();
  
  @UML(identifier = "axisDirection", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  AxisDirection getDirection();
  
  @UML(identifier = "minimumValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  double getMinimumValue();
  
  @UML(identifier = "maximumValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  double getMaximumValue();
  
  @UML(identifier = "rangeMeaning", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  RangeMeaning getRangeMeaning();
  
  @UML(identifier = "axisUnitID", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  Unit<?> getUnit();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\cs\CoordinateSystemAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */