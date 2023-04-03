package org.opengis.filter.capability;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

public interface ScalarCapabilities {
  boolean hasLogicalOperators();
  
  @UML(identifier = "comparisonOperators", specification = Specification.UNSPECIFIED)
  ComparisonOperators getComparisonOperators();
  
  @UML(identifier = "arithmeticOperators", specification = Specification.UNSPECIFIED)
  ArithmeticOperators getArithmeticOperators();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\ScalarCapabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */