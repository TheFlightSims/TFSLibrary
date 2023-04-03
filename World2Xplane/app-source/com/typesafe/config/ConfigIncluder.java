package com.typesafe.config;

public interface ConfigIncluder {
  ConfigIncluder withFallback(ConfigIncluder paramConfigIncluder);
  
  ConfigObject include(ConfigIncludeContext paramConfigIncludeContext, String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigIncluder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */