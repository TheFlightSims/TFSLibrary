package org.apache.xerces.impl.xpath;

public class XPathException extends Exception {
  static final long serialVersionUID = -948482312169512085L;
  
  private final String fKey = "c-general-xpath";
  
  public XPathException() {}
  
  public XPathException(String paramString) {}
  
  public String getKey() {
    return this.fKey;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xpath\XPathException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */