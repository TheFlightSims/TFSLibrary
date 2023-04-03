package org.apache.commons.configuration.reloading;

import org.apache.commons.configuration.FileConfiguration;

public interface ReloadingStrategy {
  void setConfiguration(FileConfiguration paramFileConfiguration);
  
  void init();
  
  boolean reloadingRequired();
  
  void reloadingPerformed();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\reloading\ReloadingStrategy.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */