package org.java.plugin.registry;

public interface PluginElement<T extends PluginElement<T>> extends Identity, Documentable<T> {
  PluginDescriptor getDeclaringPluginDescriptor();
  
  PluginFragment getDeclaringPluginFragment();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\PluginElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */