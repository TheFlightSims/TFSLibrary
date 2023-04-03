package org.geotools.feature;

import java.util.Collection;
import java.util.Iterator;
import org.opengis.feature.simple.SimpleFeature;

public interface FeatureIndex extends CollectionListener {
  Iterator getFeatures();
  
  Collection find(Object paramObject) throws IllegalArgumentException;
  
  SimpleFeature findFirst(Object paramObject) throws IllegalArgumentException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureIndex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */