package org.opengis.feature;

import java.util.Collection;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.Name;

public interface ComplexAttribute extends Attribute {
  ComplexType getType();
  
  void setValue(Collection<Property> paramCollection);
  
  Collection<? extends Property> getValue();
  
  Collection<Property> getProperties(Name paramName);
  
  Property getProperty(Name paramName);
  
  Collection<Property> getProperties(String paramString);
  
  Collection<Property> getProperties();
  
  Property getProperty(String paramString);
  
  void validate() throws IllegalAttributeException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\ComplexAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */