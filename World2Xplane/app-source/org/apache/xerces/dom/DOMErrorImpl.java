package org.apache.xerces.dom;

import org.apache.xerces.xni.parser.XMLParseException;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMLocator;

public class DOMErrorImpl implements DOMError {
  public short fSeverity = 1;
  
  public String fMessage = null;
  
  public DOMLocatorImpl fLocator = new DOMLocatorImpl();
  
  public Exception fException = null;
  
  public String fType;
  
  public Object fRelatedData;
  
  public DOMErrorImpl() {}
  
  public DOMErrorImpl(short paramShort, XMLParseException paramXMLParseException) {
    this.fSeverity = paramShort;
    this.fException = (Exception)paramXMLParseException;
    this.fLocator = createDOMLocator(paramXMLParseException);
  }
  
  public short getSeverity() {
    return this.fSeverity;
  }
  
  public String getMessage() {
    return this.fMessage;
  }
  
  public DOMLocator getLocation() {
    return this.fLocator;
  }
  
  private DOMLocatorImpl createDOMLocator(XMLParseException paramXMLParseException) {
    return new DOMLocatorImpl(paramXMLParseException.getLineNumber(), paramXMLParseException.getColumnNumber(), paramXMLParseException.getCharacterOffset(), paramXMLParseException.getExpandedSystemId());
  }
  
  public Object getRelatedException() {
    return this.fException;
  }
  
  public void reset() {
    this.fSeverity = 1;
    this.fException = null;
  }
  
  public String getType() {
    return this.fType;
  }
  
  public Object getRelatedData() {
    return this.fRelatedData;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DOMErrorImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */