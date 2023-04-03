package org.geotools.data;

import java.io.IOException;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;

public interface DataStore extends DataAccess<SimpleFeatureType, SimpleFeature> {
  void updateSchema(String paramString, SimpleFeatureType paramSimpleFeatureType) throws IOException;
  
  void removeSchema(String paramString) throws IOException;
  
  String[] getTypeNames() throws IOException;
  
  SimpleFeatureType getSchema(String paramString) throws IOException;
  
  SimpleFeatureSource getFeatureSource(String paramString) throws IOException;
  
  SimpleFeatureSource getFeatureSource(Name paramName) throws IOException;
  
  FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(Query paramQuery, Transaction paramTransaction) throws IOException;
  
  FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String paramString, Filter paramFilter, Transaction paramTransaction) throws IOException;
  
  FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String paramString, Transaction paramTransaction) throws IOException;
  
  FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(String paramString, Transaction paramTransaction) throws IOException;
  
  LockingManager getLockingManager();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */