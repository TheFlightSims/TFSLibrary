package org.java.plugin.registry;

import java.net.URL;

public interface PluginFragment extends UniqueIdentity, Documentable<PluginFragment> {
  String getVendor();
  
  Version getVersion();
  
  String getPluginId();
  
  Version getPluginVersion();
  
  PluginRegistry getRegistry();
  
  boolean matches(PluginDescriptor paramPluginDescriptor);
  
  MatchingRule getMatchingRule();
  
  URL getLocation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\PluginFragment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */