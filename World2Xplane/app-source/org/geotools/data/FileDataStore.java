package org.geotools.data;

import java.io.IOException;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

public interface FileDataStore extends DataStore {
  SimpleFeatureType getSchema() throws IOException;
  
  void updateSchema(SimpleFeatureType paramSimpleFeatureType) throws IOException;
  
  SimpleFeatureSource getFeatureSource() throws IOException;
  
  FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader() throws IOException;
  
  FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(Filter paramFilter, Transaction paramTransaction) throws IOException;
  
  FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(Transaction paramTransaction) throws IOException;
  
  FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(Transaction paramTransaction) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FileDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */