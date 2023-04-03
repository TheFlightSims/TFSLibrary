package com.typesafe.config;

import java.util.Map;

public interface ConfigObject extends ConfigValue, Map<String, ConfigValue> {
  Config toConfig();
  
  Map<String, Object> unwrapped();
  
  ConfigObject withFallback(ConfigMergeable paramConfigMergeable);
  
  ConfigValue get(Object paramObject);
  
  ConfigObject withOnlyKey(String paramString);
  
  ConfigObject withoutKey(String paramString);
  
  ConfigObject withValue(String paramString, ConfigValue paramConfigValue);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */