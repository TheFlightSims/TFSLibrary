package org.apache.xerces.xni.parser;

import org.apache.xerces.xni.XNIException;

public interface XMLErrorHandler {
  void warning(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException;
  
  void error(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException;
  
  void fatalError(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLErrorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */