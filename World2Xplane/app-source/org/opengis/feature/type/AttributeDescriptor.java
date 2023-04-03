package org.opengis.feature.type;

public interface AttributeDescriptor extends PropertyDescriptor {
  AttributeType getType();
  
  String getLocalName();
  
  Object getDefaultValue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\AttributeDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */