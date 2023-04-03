package org.apache.xerces.dom3.as;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

public interface DOMASBuilder extends LSParser {
  ASModel getAbstractSchema();
  
  void setAbstractSchema(ASModel paramASModel);
  
  ASModel parseASURI(String paramString) throws DOMASException, Exception;
  
  ASModel parseASInputSource(LSInput paramLSInput) throws DOMASException, Exception;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\DOMASBuilder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */