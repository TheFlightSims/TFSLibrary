package org.java.plugin.registry;

public interface PluginPrerequisite extends UniqueIdentity, PluginElement<PluginPrerequisite> {
  String getPluginId();
  
  Version getPluginVersion();
  
  boolean isExported();
  
  boolean isOptional();
  
  boolean isReverseLookup();
  
  boolean matches();
  
  MatchingRule getMatchingRule();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\PluginPrerequisite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */