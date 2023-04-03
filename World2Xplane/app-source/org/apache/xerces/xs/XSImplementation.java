package org.apache.xerces.xs;

public interface XSImplementation {
  StringList getRecognizedVersions();
  
  XSLoader createXSLoader(StringList paramStringList) throws XSException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSImplementation.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */