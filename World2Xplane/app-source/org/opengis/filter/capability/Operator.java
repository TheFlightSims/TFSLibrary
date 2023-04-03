package org.opengis.filter.capability;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

public interface Operator {
  @UML(identifier = "name", specification = Specification.UNSPECIFIED)
  String getName();
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\Operator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */