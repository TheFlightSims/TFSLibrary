package org.geotools.data.simple;

import java.io.IOException;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

public interface SimpleFeatureSource extends FeatureSource<SimpleFeatureType, SimpleFeature> {
  SimpleFeatureCollection getFeatures() throws IOException;
  
  SimpleFeatureCollection getFeatures(Filter paramFilter) throws IOException;
  
  SimpleFeatureCollection getFeatures(Query paramQuery) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\simple\SimpleFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */