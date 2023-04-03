package org.java.plugin.registry;

import java.util.Collection;

public interface Library extends UniqueIdentity, PluginElement<Library> {
  String getPath();
  
  boolean isCodeLibrary();
  
  Collection<String> getExports();
  
  Version getVersion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\Library.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */