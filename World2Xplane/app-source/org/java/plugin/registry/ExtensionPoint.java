package org.java.plugin.registry;

import java.util.Collection;

public interface ExtensionPoint extends UniqueIdentity, PluginElement<ExtensionPoint> {
  ExtensionMultiplicity getMultiplicity();
  
  Collection<ParameterDefinition> getParameterDefinitions();
  
  ParameterDefinition getParameterDefinition(String paramString);
  
  Collection<Extension> getAvailableExtensions();
  
  Extension getAvailableExtension(String paramString);
  
  boolean isExtensionAvailable(String paramString);
  
  Collection<Extension> getConnectedExtensions();
  
  Extension getConnectedExtension(String paramString);
  
  boolean isExtensionConnected(String paramString);
  
  boolean isValid();
  
  String getParentPluginId();
  
  String getParentExtensionPointId();
  
  boolean isSuccessorOf(ExtensionPoint paramExtensionPoint);
  
  Collection<ExtensionPoint> getDescendants();
  
  public static interface ParameterDefinition extends PluginElement<ParameterDefinition> {
    ParameterMultiplicity getMultiplicity();
    
    ParameterType getType();
    
    String getCustomData();
    
    Collection<ParameterDefinition> getSubDefinitions();
    
    ParameterDefinition getSubDefinition(String param1String);
    
    ExtensionPoint getDeclaringExtensionPoint();
    
    ParameterDefinition getSuperDefinition();
    
    String getDefaultValue();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\ExtensionPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */