package org.hsqldb.lib;

public interface RefCapableRBInterface {
  String getString();
  
  String getString(String... paramVarArgs);
  
  String getExpandedString();
  
  String getExpandedString(String... paramVarArgs);
  
  String getString(int paramInt);
  
  String getString(int paramInt1, int paramInt2);
  
  String getString(int paramInt1, int paramInt2, int paramInt3);
  
  String getString(int paramInt, String paramString);
  
  String getString(String paramString, int paramInt);
  
  String getString(int paramInt1, int paramInt2, String paramString);
  
  String getString(int paramInt1, String paramString, int paramInt2);
  
  String getString(String paramString, int paramInt1, int paramInt2);
  
  String getString(int paramInt, String paramString1, String paramString2);
  
  String getString(String paramString1, String paramString2, int paramInt);
  
  String getString(String paramString1, int paramInt, String paramString2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\RefCapableRBInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */