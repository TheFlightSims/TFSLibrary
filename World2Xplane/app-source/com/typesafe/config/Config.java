package com.typesafe.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface Config extends ConfigMergeable {
  ConfigObject root();
  
  ConfigOrigin origin();
  
  Config withFallback(ConfigMergeable paramConfigMergeable);
  
  Config resolve();
  
  Config resolve(ConfigResolveOptions paramConfigResolveOptions);
  
  boolean isResolved();
  
  Config resolveWith(Config paramConfig);
  
  Config resolveWith(Config paramConfig, ConfigResolveOptions paramConfigResolveOptions);
  
  void checkValid(Config paramConfig, String... paramVarArgs);
  
  boolean hasPath(String paramString);
  
  boolean isEmpty();
  
  Set<Map.Entry<String, ConfigValue>> entrySet();
  
  boolean getBoolean(String paramString);
  
  Number getNumber(String paramString);
  
  int getInt(String paramString);
  
  long getLong(String paramString);
  
  double getDouble(String paramString);
  
  String getString(String paramString);
  
  ConfigObject getObject(String paramString);
  
  Config getConfig(String paramString);
  
  Object getAnyRef(String paramString);
  
  ConfigValue getValue(String paramString);
  
  Long getBytes(String paramString);
  
  @Deprecated
  Long getMilliseconds(String paramString);
  
  @Deprecated
  Long getNanoseconds(String paramString);
  
  long getDuration(String paramString, TimeUnit paramTimeUnit);
  
  ConfigList getList(String paramString);
  
  List<Boolean> getBooleanList(String paramString);
  
  List<Number> getNumberList(String paramString);
  
  List<Integer> getIntList(String paramString);
  
  List<Long> getLongList(String paramString);
  
  List<Double> getDoubleList(String paramString);
  
  List<String> getStringList(String paramString);
  
  List<? extends ConfigObject> getObjectList(String paramString);
  
  List<? extends Config> getConfigList(String paramString);
  
  List<? extends Object> getAnyRefList(String paramString);
  
  List<Long> getBytesList(String paramString);
  
  @Deprecated
  List<Long> getMillisecondsList(String paramString);
  
  @Deprecated
  List<Long> getNanosecondsList(String paramString);
  
  List<Long> getDurationList(String paramString, TimeUnit paramTimeUnit);
  
  Config withOnlyPath(String paramString);
  
  Config withoutPath(String paramString);
  
  Config atPath(String paramString);
  
  Config atKey(String paramString);
  
  Config withValue(String paramString, ConfigValue paramConfigValue);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */