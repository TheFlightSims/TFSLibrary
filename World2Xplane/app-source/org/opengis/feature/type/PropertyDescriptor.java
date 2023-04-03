package org.opengis.feature.type;

import java.util.Map;

public interface PropertyDescriptor {
  PropertyType getType();
  
  Name getName();
  
  int getMinOccurs();
  
  int getMaxOccurs();
  
  boolean isNillable();
  
  Map<Object, Object> getUserData();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\PropertyDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */