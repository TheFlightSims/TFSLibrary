package org.jfree.util;

public interface ExtendedConfiguration extends Configuration {
  boolean isPropertySet(String paramString);
  
  int getIntProperty(String paramString);
  
  int getIntProperty(String paramString, int paramInt);
  
  boolean getBoolProperty(String paramString);
  
  boolean getBoolProperty(String paramString, boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ExtendedConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */