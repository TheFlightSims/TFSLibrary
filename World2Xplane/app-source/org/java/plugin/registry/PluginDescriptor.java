package org.java.plugin.registry;

import java.net.URL;
import java.util.Collection;

public interface PluginDescriptor extends UniqueIdentity, Documentable<PluginDescriptor> {
  String getVendor();
  
  Version getVersion();
  
  Collection<PluginAttribute> getAttributes();
  
  PluginAttribute getAttribute(String paramString);
  
  Collection<PluginAttribute> getAttributes(String paramString);
  
  Collection<PluginPrerequisite> getPrerequisites();
  
  PluginPrerequisite getPrerequisite(String paramString);
  
  Collection<ExtensionPoint> getExtensionPoints();
  
  ExtensionPoint getExtensionPoint(String paramString);
  
  Collection<Extension> getExtensions();
  
  Extension getExtension(String paramString);
  
  Collection<Library> getLibraries();
  
  Library getLibrary(String paramString);
  
  PluginRegistry getRegistry();
  
  String getPluginClassName();
  
  Collection<PluginFragment> getFragments();
  
  URL getLocation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\PluginDescriptor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */