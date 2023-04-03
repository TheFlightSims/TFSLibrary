package com.typesafe.config;

public interface ConfigParseable {
  ConfigObject parse(ConfigParseOptions paramConfigParseOptions);
  
  ConfigOrigin origin();
  
  ConfigParseOptions options();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigParseable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */