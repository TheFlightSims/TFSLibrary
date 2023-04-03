package org.apache.commons.beanutils;

public interface MutableDynaClass extends DynaClass {
  void add(String paramString);
  
  void add(String paramString, Class paramClass);
  
  void add(String paramString, Class paramClass, boolean paramBoolean1, boolean paramBoolean2);
  
  boolean isRestricted();
  
  void remove(String paramString);
  
  void setRestricted(boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\MutableDynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */