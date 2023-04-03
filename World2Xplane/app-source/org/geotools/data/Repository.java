package org.geotools.data;

import java.util.List;
import org.opengis.feature.type.Name;

public interface Repository {
  DataAccess<?, ?> access(Name paramName);
  
  DataStore dataStore(Name paramName);
  
  List<DataStore> getDataStores();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\Repository.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */