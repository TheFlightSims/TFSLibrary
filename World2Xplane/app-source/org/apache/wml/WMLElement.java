package org.apache.wml;

import org.w3c.dom.Element;

public interface WMLElement extends Element {
  void setId(String paramString);
  
  String getId();
  
  void setClassName(String paramString);
  
  String getClassName();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\WMLElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */