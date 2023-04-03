package org.java.plugin;

import java.net.URL;
import org.java.plugin.registry.Identity;
import org.java.plugin.util.ExtendedProperties;

public interface PathResolver {
  void configure(ExtendedProperties paramExtendedProperties) throws Exception;
  
  void registerContext(Identity paramIdentity, URL paramURL);
  
  void unregisterContext(String paramString);
  
  URL getRegisteredContext(String paramString);
  
  boolean isContextRegistered(String paramString);
  
  URL resolvePath(Identity paramIdentity, String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\PathResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */