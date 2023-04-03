package org.apache.commons.configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public interface Configuration {
  Configuration subset(String paramString);
  
  boolean isEmpty();
  
  boolean containsKey(String paramString);
  
  void addProperty(String paramString, Object paramObject);
  
  void setProperty(String paramString, Object paramObject);
  
  void clearProperty(String paramString);
  
  void clear();
  
  Object getProperty(String paramString);
  
  Iterator getKeys(String paramString);
  
  Iterator getKeys();
  
  Properties getProperties(String paramString);
  
  boolean getBoolean(String paramString);
  
  boolean getBoolean(String paramString, boolean paramBoolean);
  
  Boolean getBoolean(String paramString, Boolean paramBoolean);
  
  byte getByte(String paramString);
  
  byte getByte(String paramString, byte paramByte);
  
  Byte getByte(String paramString, Byte paramByte);
  
  double getDouble(String paramString);
  
  double getDouble(String paramString, double paramDouble);
  
  Double getDouble(String paramString, Double paramDouble);
  
  float getFloat(String paramString);
  
  float getFloat(String paramString, float paramFloat);
  
  Float getFloat(String paramString, Float paramFloat);
  
  int getInt(String paramString);
  
  int getInt(String paramString, int paramInt);
  
  Integer getInteger(String paramString, Integer paramInteger);
  
  long getLong(String paramString);
  
  long getLong(String paramString, long paramLong);
  
  Long getLong(String paramString, Long paramLong);
  
  short getShort(String paramString);
  
  short getShort(String paramString, short paramShort);
  
  Short getShort(String paramString, Short paramShort);
  
  BigDecimal getBigDecimal(String paramString);
  
  BigDecimal getBigDecimal(String paramString, BigDecimal paramBigDecimal);
  
  BigInteger getBigInteger(String paramString);
  
  BigInteger getBigInteger(String paramString, BigInteger paramBigInteger);
  
  String getString(String paramString);
  
  String getString(String paramString1, String paramString2);
  
  String[] getStringArray(String paramString);
  
  List getList(String paramString);
  
  List getList(String paramString, List paramList);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\Configuration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */