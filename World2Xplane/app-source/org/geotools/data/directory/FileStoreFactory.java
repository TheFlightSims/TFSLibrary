package org.geotools.data.directory;

import java.io.File;
import java.io.IOException;
import org.geotools.data.DataStore;

public interface FileStoreFactory {
  DataStore getDataStore(File paramFile) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\FileStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */