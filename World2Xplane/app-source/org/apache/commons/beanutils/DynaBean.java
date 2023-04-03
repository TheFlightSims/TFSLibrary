package org.apache.commons.beanutils;

public interface DynaBean {
  boolean contains(String paramString1, String paramString2);
  
  Object get(String paramString);
  
  Object get(String paramString, int paramInt);
  
  Object get(String paramString1, String paramString2);
  
  DynaClass getDynaClass();
  
  void remove(String paramString1, String paramString2);
  
  void set(String paramString, Object paramObject);
  
  void set(String paramString, int paramInt, Object paramObject);
  
  void set(String paramString1, String paramString2, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\DynaBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */