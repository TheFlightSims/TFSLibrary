package org.apache.xerces.xs;

public interface XSNamedMap {
  int getLength();
  
  XSObject item(int paramInt);
  
  XSObject itemByName(String paramString1, String paramString2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSNamedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */