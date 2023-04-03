package com.typesafe.config;

public interface ConfigValue extends ConfigMergeable {
  ConfigOrigin origin();
  
  ConfigValueType valueType();
  
  Object unwrapped();
  
  String render();
  
  String render(ConfigRenderOptions paramConfigRenderOptions);
  
  ConfigValue withFallback(ConfigMergeable paramConfigMergeable);
  
  Config atPath(String paramString);
  
  Config atKey(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */