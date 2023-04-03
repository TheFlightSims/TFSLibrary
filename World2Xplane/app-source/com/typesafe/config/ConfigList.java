package com.typesafe.config;

import java.util.List;

public interface ConfigList extends List<ConfigValue>, ConfigValue {
  List<Object> unwrapped();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */