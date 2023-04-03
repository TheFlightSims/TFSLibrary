package org.opengis.feature;

import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AssociationType;
import org.opengis.feature.type.AttributeType;

public interface Association extends Property {
  AssociationDescriptor getDescriptor();
  
  AssociationType getType();
  
  Attribute getValue();
  
  void setValue(Object paramObject) throws IllegalArgumentException;
  
  AttributeType getRelatedType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\Association.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */