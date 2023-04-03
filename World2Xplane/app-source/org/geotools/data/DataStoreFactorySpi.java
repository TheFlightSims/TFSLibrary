package org.geotools.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public interface DataStoreFactorySpi extends DataAccessFactory {
  DataStore createDataStore(Map<String, Serializable> paramMap) throws IOException;
  
  DataStore createNewDataStore(Map<String, Serializable> paramMap) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataStoreFactorySpi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */