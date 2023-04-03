package org.apache.xerces.util;

import org.apache.xerces.xni.XMLLocator;

public final class XMLLocatorWrapper implements XMLLocator {
  private XMLLocator fLocator = null;
  
  public void setLocator(XMLLocator paramXMLLocator) {
    this.fLocator = paramXMLLocator;
  }
  
  public XMLLocator getLocator() {
    return this.fLocator;
  }
  
  public String getPublicId() {
    return (this.fLocator != null) ? this.fLocator.getPublicId() : null;
  }
  
  public String getLiteralSystemId() {
    return (this.fLocator != null) ? this.fLocator.getLiteralSystemId() : null;
  }
  
  public String getBaseSystemId() {
    return (this.fLocator != null) ? this.fLocator.getBaseSystemId() : null;
  }
  
  public String getExpandedSystemId() {
    return (this.fLocator != null) ? this.fLocator.getExpandedSystemId() : null;
  }
  
  public int getLineNumber() {
    return (this.fLocator != null) ? this.fLocator.getLineNumber() : -1;
  }
  
  public int getColumnNumber() {
    return (this.fLocator != null) ? this.fLocator.getColumnNumber() : -1;
  }
  
  public int getCharacterOffset() {
    return (this.fLocator != null) ? this.fLocator.getCharacterOffset() : -1;
  }
  
  public String getEncoding() {
    return (this.fLocator != null) ? this.fLocator.getEncoding() : null;
  }
  
  public String getXMLVersion() {
    return (this.fLocator != null) ? this.fLocator.getXMLVersion() : null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\XMLLocatorWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */