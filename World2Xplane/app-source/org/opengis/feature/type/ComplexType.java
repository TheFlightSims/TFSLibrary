package org.opengis.feature.type;

import java.util.Collection;
import org.opengis.feature.Property;

public interface ComplexType extends AttributeType {
  Class<Collection<Property>> getBinding();
  
  Collection<PropertyDescriptor> getDescriptors();
  
  PropertyDescriptor getDescriptor(Name paramName);
  
  PropertyDescriptor getDescriptor(String paramString);
  
  boolean isInline();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\ComplexType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */