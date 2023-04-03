package org.apache.xerces.util;

import org.apache.xerces.xni.XMLLocator;
import org.xml.sax.ext.Locator2;

public class LocatorProxy implements Locator2 {
  private final XMLLocator fLocator;
  
  public LocatorProxy(XMLLocator paramXMLLocator) {
    this.fLocator = paramXMLLocator;
  }
  
  public String getPublicId() {
    return this.fLocator.getPublicId();
  }
  
  public String getSystemId() {
    return this.fLocator.getExpandedSystemId();
  }
  
  public int getLineNumber() {
    return this.fLocator.getLineNumber();
  }
  
  public int getColumnNumber() {
    return this.fLocator.getColumnNumber();
  }
  
  public String getXMLVersion() {
    return this.fLocator.getXMLVersion();
  }
  
  public String getEncoding() {
    return this.fLocator.getEncoding();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\LocatorProxy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */