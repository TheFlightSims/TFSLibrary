package org.opengis.feature.type;

import java.util.List;
import org.opengis.filter.Filter;

public interface OperationType extends PropertyType {
  OperationType getSuper();
  
  boolean isAbstract();
  
  AttributeType getTarget();
  
  AttributeType getResult();
  
  List<AttributeType> getParameters();
  
  List<Filter> getRestrictions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\OperationType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */