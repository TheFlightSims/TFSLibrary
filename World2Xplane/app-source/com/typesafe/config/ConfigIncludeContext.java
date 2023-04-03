package com.typesafe.config;

public interface ConfigIncludeContext {
  ConfigParseable relativeTo(String paramString);
  
  ConfigParseOptions parseOptions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigIncludeContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */