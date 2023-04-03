package org.geotools.data;

import java.io.IOException;
import java.net.URL;

public interface FileDataStoreFactorySpi extends DataStoreFactorySpi {
  String[] getFileExtensions();
  
  boolean canProcess(URL paramURL);
  
  FileDataStore createDataStore(URL paramURL) throws IOException;
  
  String getTypeName(URL paramURL) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FileDataStoreFactorySpi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */