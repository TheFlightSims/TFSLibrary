package org.opengis.parameter;

import org.opengis.util.InternationalString;

public interface Parameter<T> {
  String getName();
  
  InternationalString getTitle();
  
  InternationalString getDescription();
  
  Class<T> getType();
  
  Boolean isRequired();
  
  int getMinOccurs();
  
  int getMaxOccurs();
  
  T getDefaultValue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\Parameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */