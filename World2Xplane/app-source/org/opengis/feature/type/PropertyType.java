package org.opengis.feature.type;

import java.util.List;
import java.util.Map;
import org.opengis.filter.Filter;
import org.opengis.util.InternationalString;

public interface PropertyType {
  Name getName();
  
  Class<?> getBinding();
  
  PropertyType getSuper();
  
  boolean isAbstract();
  
  List<Filter> getRestrictions();
  
  InternationalString getDescription();
  
  Map<Object, Object> getUserData();
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\PropertyType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */