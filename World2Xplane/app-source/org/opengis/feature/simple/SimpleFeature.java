package org.opengis.feature.simple;

import java.util.List;
import org.opengis.feature.Feature;
import org.opengis.feature.type.Name;

public interface SimpleFeature extends Feature {
  String getID();
  
  SimpleFeatureType getType();
  
  SimpleFeatureType getFeatureType();
  
  List<Object> getAttributes();
  
  void setAttributes(List<Object> paramList);
  
  void setAttributes(Object[] paramArrayOfObject);
  
  Object getAttribute(String paramString);
  
  void setAttribute(String paramString, Object paramObject);
  
  Object getAttribute(Name paramName);
  
  void setAttribute(Name paramName, Object paramObject);
  
  Object getAttribute(int paramInt) throws IndexOutOfBoundsException;
  
  void setAttribute(int paramInt, Object paramObject) throws IndexOutOfBoundsException;
  
  int getAttributeCount();
  
  Object getDefaultGeometry();
  
  void setDefaultGeometry(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\simple\SimpleFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */