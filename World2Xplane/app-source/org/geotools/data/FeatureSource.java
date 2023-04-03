package org.geotools.data;

import java.awt.RenderingHints;
import java.io.IOException;
import java.util.Set;
import org.geotools.feature.FeatureCollection;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;

public interface FeatureSource<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> {
  Name getName();
  
  ResourceInfo getInfo();
  
  DataAccess<T, F> getDataStore();
  
  QueryCapabilities getQueryCapabilities();
  
  void addFeatureListener(FeatureListener paramFeatureListener);
  
  void removeFeatureListener(FeatureListener paramFeatureListener);
  
  FeatureCollection<T, F> getFeatures(Filter paramFilter) throws IOException;
  
  FeatureCollection<T, F> getFeatures(Query paramQuery) throws IOException;
  
  FeatureCollection<T, F> getFeatures() throws IOException;
  
  T getSchema();
  
  ReferencedEnvelope getBounds() throws IOException;
  
  ReferencedEnvelope getBounds(Query paramQuery) throws IOException;
  
  int getCount(Query paramQuery) throws IOException;
  
  Set<RenderingHints.Key> getSupportedHints();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */