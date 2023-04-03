package com.typesafe.config;

import java.net.URL;
import java.util.List;

public interface ConfigOrigin {
  String description();
  
  String filename();
  
  URL url();
  
  String resource();
  
  int lineNumber();
  
  List<String> comments();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigOrigin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */