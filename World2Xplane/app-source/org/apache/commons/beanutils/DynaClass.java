package org.apache.commons.beanutils;

public interface DynaClass {
  String getName();
  
  DynaProperty getDynaProperty(String paramString);
  
  DynaProperty[] getDynaProperties();
  
  DynaBean newInstance() throws IllegalAccessException, InstantiationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\DynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */