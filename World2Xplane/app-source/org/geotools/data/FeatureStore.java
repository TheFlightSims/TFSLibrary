package org.geotools.data;

import java.io.IOException;
import java.util.List;
import org.geotools.feature.FeatureCollection;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;
import org.opengis.filter.identity.FeatureId;

public interface FeatureStore<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> extends FeatureSource<T, F> {
  List<FeatureId> addFeatures(FeatureCollection<T, F> paramFeatureCollection) throws IOException;
  
  void removeFeatures(Filter paramFilter) throws IOException;
  
  void modifyFeatures(Name[] paramArrayOfName, Object[] paramArrayOfObject, Filter paramFilter) throws IOException;
  
  void modifyFeatures(AttributeDescriptor[] paramArrayOfAttributeDescriptor, Object[] paramArrayOfObject, Filter paramFilter) throws IOException;
  
  void modifyFeatures(Name paramName, Object paramObject, Filter paramFilter) throws IOException;
  
  void modifyFeatures(AttributeDescriptor paramAttributeDescriptor, Object paramObject, Filter paramFilter) throws IOException;
  
  void setFeatures(FeatureReader<T, F> paramFeatureReader) throws IOException;
  
  void setTransaction(Transaction paramTransaction);
  
  Transaction getTransaction();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */