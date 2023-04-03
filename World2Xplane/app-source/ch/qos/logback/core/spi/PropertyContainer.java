package ch.qos.logback.core.spi;

import java.util.Map;

public interface PropertyContainer {
  String getProperty(String paramString);
  
  Map<String, String> getCopyOfPropertyMap();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\spi\PropertyContainer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */