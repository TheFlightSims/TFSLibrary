package org.java.plugin.registry;

import java.util.Collection;

public interface PluginAttribute extends PluginElement<PluginAttribute> {
  String getValue();
  
  Collection<PluginAttribute> getSubAttributes();
  
  PluginAttribute getSubAttribute(String paramString);
  
  Collection<PluginAttribute> getSubAttributes(String paramString);
  
  PluginAttribute getSuperAttribute();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\PluginAttribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */