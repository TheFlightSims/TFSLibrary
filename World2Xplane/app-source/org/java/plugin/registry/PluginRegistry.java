package org.java.plugin.registry;

import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.java.plugin.PathResolver;
import org.java.plugin.util.ExtendedProperties;

public interface PluginRegistry {
  Map<String, Identity> register(URL[] paramArrayOfURL) throws ManifestProcessingException;
  
  ManifestInfo readManifestInfo(URL paramURL) throws ManifestProcessingException;
  
  Collection<String> unregister(String[] paramArrayOfString);
  
  PluginDescriptor getPluginDescriptor(String paramString);
  
  boolean isPluginDescriptorAvailable(String paramString);
  
  Collection<PluginDescriptor> getPluginDescriptors();
  
  ExtensionPoint getExtensionPoint(String paramString1, String paramString2);
  
  ExtensionPoint getExtensionPoint(String paramString);
  
  boolean isExtensionPointAvailable(String paramString1, String paramString2);
  
  boolean isExtensionPointAvailable(String paramString);
  
  Collection<PluginFragment> getPluginFragments();
  
  Collection<PluginDescriptor> getDependingPlugins(PluginDescriptor paramPluginDescriptor);
  
  IntegrityCheckReport checkIntegrity(PathResolver paramPathResolver);
  
  IntegrityCheckReport checkIntegrity(PathResolver paramPathResolver, boolean paramBoolean);
  
  IntegrityCheckReport getRegistrationReport();
  
  String makeUniqueId(String paramString1, String paramString2);
  
  String makeUniqueId(String paramString, Version paramVersion);
  
  String extractPluginId(String paramString);
  
  String extractId(String paramString);
  
  Version extractVersion(String paramString);
  
  void registerListener(RegistryChangeListener paramRegistryChangeListener);
  
  void unregisterListener(RegistryChangeListener paramRegistryChangeListener);
  
  void configure(ExtendedProperties paramExtendedProperties);
  
  public static interface RegistryChangeData {
    Set<String> addedPlugins();
    
    Set<String> removedPlugins();
    
    Set<String> modifiedPlugins();
    
    Set<String> addedExtensions();
    
    Set<String> addedExtensions(String param1String);
    
    Set<String> removedExtensions();
    
    Set<String> removedExtensions(String param1String);
    
    Set<String> modifiedExtensions();
    
    Set<String> modifiedExtensions(String param1String);
  }
  
  public static interface RegistryChangeListener {
    void registryChanged(PluginRegistry.RegistryChangeData param1RegistryChangeData);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\PluginRegistry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */