package org.opengis.metadata.identification;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_RepresentativeFraction", specification = Specification.ISO_19115)
public interface RepresentativeFraction {
  @Deprecated
  double toScale();
  
  double doubleValue();
  
  @UML(identifier = "denominator", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  long getDenominator();
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\RepresentativeFraction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */