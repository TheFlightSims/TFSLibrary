package org.java.plugin.registry;

public interface ManifestInfo {
  String getId();
  
  Version getVersion();
  
  String getVendor();
  
  String getPluginId();
  
  Version getPluginVersion();
  
  MatchingRule getMatchingRule();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\ManifestInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */