package org.geotools.feature.collection;

import java.util.NoSuchElementException;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.opengis.feature.simple.SimpleFeature;

public interface RandomFeatureAccess extends SimpleFeatureCollection {
  SimpleFeature getFeatureMember(String paramString) throws NoSuchElementException;
  
  SimpleFeature removeFeatureMember(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\RandomFeatureAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */