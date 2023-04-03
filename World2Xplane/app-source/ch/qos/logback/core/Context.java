package ch.qos.logback.core;

import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.spi.PropertyContainer;
import ch.qos.logback.core.status.StatusManager;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public interface Context extends PropertyContainer {
  StatusManager getStatusManager();
  
  Object getObject(String paramString);
  
  void putObject(String paramString, Object paramObject);
  
  String getProperty(String paramString);
  
  void putProperty(String paramString1, String paramString2);
  
  Map<String, String> getCopyOfPropertyMap();
  
  String getName();
  
  void setName(String paramString);
  
  long getBirthTime();
  
  Object getConfigurationLock();
  
  ExecutorService getExecutorService();
  
  void register(LifeCycle paramLifeCycle);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\Context.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */