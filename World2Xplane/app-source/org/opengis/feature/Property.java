package org.opengis.feature;

import java.util.Map;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyDescriptor;
import org.opengis.feature.type.PropertyType;

public interface Property {
  Object getValue();
  
  void setValue(Object paramObject);
  
  PropertyType getType();
  
  PropertyDescriptor getDescriptor();
  
  Name getName();
  
  boolean isNillable();
  
  Map<Object, Object> getUserData();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\Property.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */