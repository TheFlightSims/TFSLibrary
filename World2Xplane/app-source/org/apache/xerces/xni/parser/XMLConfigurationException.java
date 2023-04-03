package org.apache.xerces.xni.parser;

import org.apache.xerces.xni.XNIException;

public class XMLConfigurationException extends XNIException {
  static final long serialVersionUID = -5437427404547669188L;
  
  public static final short NOT_RECOGNIZED = 0;
  
  public static final short NOT_SUPPORTED = 1;
  
  protected short fType;
  
  protected String fIdentifier;
  
  public XMLConfigurationException(short paramShort, String paramString) {
    super(paramString);
    this.fType = paramShort;
    this.fIdentifier = paramString;
  }
  
  public XMLConfigurationException(short paramShort, String paramString1, String paramString2) {
    super(paramString2);
    this.fType = paramShort;
    this.fIdentifier = paramString1;
  }
  
  public short getType() {
    return this.fType;
  }
  
  public String getIdentifier() {
    return this.fIdentifier;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLConfigurationException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */