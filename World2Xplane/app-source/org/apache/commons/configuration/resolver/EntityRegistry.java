package org.apache.commons.configuration.resolver;

import java.net.URL;
import java.util.Map;

public interface EntityRegistry {
  void registerEntityId(String paramString, URL paramURL);
  
  Map getRegisteredEntities();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\resolver\EntityRegistry.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */