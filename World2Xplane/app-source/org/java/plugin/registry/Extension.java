package org.java.plugin.registry;

import java.net.URL;
import java.util.Collection;
import java.util.Date;
import org.java.plugin.PathResolver;

public interface Extension extends UniqueIdentity, PluginElement<Extension> {
  Collection<Parameter> getParameters();
  
  Parameter getParameter(String paramString);
  
  Collection<Parameter> getParameters(String paramString);
  
  String getExtendedPluginId();
  
  String getExtendedPointId();
  
  boolean isValid();
  
  public static interface Parameter extends PluginElement<Parameter> {
    String rawValue();
    
    Collection<Parameter> getSubParameters();
    
    Parameter getSubParameter(String param1String);
    
    Collection<Parameter> getSubParameters(String param1String);
    
    Extension getDeclaringExtension();
    
    ExtensionPoint.ParameterDefinition getDefinition();
    
    Parameter getSuperParameter();
    
    String valueAsString();
    
    Boolean valueAsBoolean();
    
    Number valueAsNumber();
    
    Date valueAsDate();
    
    PluginDescriptor valueAsPluginDescriptor();
    
    ExtensionPoint valueAsExtensionPoint();
    
    Extension valueAsExtension();
    
    URL valueAsUrl();
    
    URL valueAsUrl(PathResolver param1PathResolver);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\Extension.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */