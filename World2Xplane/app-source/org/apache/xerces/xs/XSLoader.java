package org.apache.xerces.xs;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.ls.LSInput;

public interface XSLoader {
  DOMConfiguration getConfig();
  
  XSModel loadURIList(StringList paramStringList);
  
  XSModel loadInputList(LSInputList paramLSInputList);
  
  XSModel loadURI(String paramString);
  
  XSModel load(LSInput paramLSInput);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSLoader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */