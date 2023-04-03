package org.geotools.data;

import java.io.IOException;
import java.util.List;
import org.opengis.feature.type.Name;

public interface DataAccess<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> {
  ServiceInfo getInfo();
  
  void createSchema(T paramT) throws IOException;
  
  void updateSchema(Name paramName, T paramT) throws IOException;
  
  void removeSchema(Name paramName) throws IOException;
  
  List<Name> getNames() throws IOException;
  
  T getSchema(Name paramName) throws IOException;
  
  FeatureSource<T, F> getFeatureSource(Name paramName) throws IOException;
  
  void dispose();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */