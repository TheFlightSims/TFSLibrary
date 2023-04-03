package org.geotools.data;

import java.net.URI;
import java.util.Set;

public interface ServiceInfo {
  String getTitle();
  
  Set<String> getKeywords();
  
  String getDescription();
  
  URI getPublisher();
  
  URI getSchema();
  
  URI getSource();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ServiceInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */