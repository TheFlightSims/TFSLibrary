package org.opengis.feature.type;

public interface AssociationType extends PropertyType {
  AssociationType getSuper();
  
  AttributeType getRelatedType();
  
  Class<?> getBinding();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\AssociationType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */