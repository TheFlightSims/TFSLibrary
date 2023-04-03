package org.geotools.data.simple;

import java.io.IOException;
import org.geotools.data.FeatureStore;
import org.geotools.data.Query;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

public interface SimpleFeatureStore extends FeatureStore<SimpleFeatureType, SimpleFeature>, SimpleFeatureSource {
  void modifyFeatures(String paramString, Object paramObject, Filter paramFilter) throws IOException;
  
  void modifyFeatures(String[] paramArrayOfString, Object[] paramArrayOfObject, Filter paramFilter) throws IOException;
  
  SimpleFeatureCollection getFeatures() throws IOException;
  
  SimpleFeatureCollection getFeatures(Filter paramFilter) throws IOException;
  
  SimpleFeatureCollection getFeatures(Query paramQuery) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\simple\SimpleFeatureStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */