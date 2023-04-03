package org.opengis.feature.type;

import java.util.Map;
import java.util.Set;

public interface Schema extends Map<Name, AttributeType> {
  String getURI();
  
  void add(AttributeType paramAttributeType);
  
  Schema profile(Set<Name> paramSet);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\Schema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */