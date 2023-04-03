package org.geotools.feature;

import org.opengis.feature.simple.SimpleFeature;

public interface FeatureFactory {
  SimpleFeature create(Object[] paramArrayOfObject) throws IllegalAttributeException;
  
  SimpleFeature create(Object[] paramArrayOfObject, String paramString) throws IllegalAttributeException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */