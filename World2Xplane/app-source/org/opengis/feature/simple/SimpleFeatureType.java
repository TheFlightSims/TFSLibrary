package org.opengis.feature.simple;

import java.util.List;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;

public interface SimpleFeatureType extends FeatureType {
  String getTypeName();
  
  List<AttributeDescriptor> getAttributeDescriptors();
  
  AttributeDescriptor getDescriptor(String paramString);
  
  AttributeDescriptor getDescriptor(Name paramName);
  
  AttributeDescriptor getDescriptor(int paramInt) throws IndexOutOfBoundsException;
  
  int getAttributeCount();
  
  List<AttributeType> getTypes();
  
  AttributeType getType(String paramString);
  
  AttributeType getType(Name paramName);
  
  AttributeType getType(int paramInt) throws IndexOutOfBoundsException;
  
  int indexOf(String paramString);
  
  int indexOf(Name paramName);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\simple\SimpleFeatureType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */