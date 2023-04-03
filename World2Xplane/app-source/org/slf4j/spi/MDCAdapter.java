package org.slf4j.spi;

import java.util.Map;

public interface MDCAdapter {
  void put(String paramString1, String paramString2);
  
  String get(String paramString);
  
  void remove(String paramString);
  
  void clear();
  
  Map getCopyOfContextMap();
  
  void setContextMap(Map paramMap);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\spi\MDCAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */