package org.apache.xerces.util;

import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ErrorHandlerWrapper implements XMLErrorHandler {
  protected ErrorHandler fErrorHandler;
  
  public ErrorHandlerWrapper() {}
  
  public ErrorHandlerWrapper(ErrorHandler paramErrorHandler) {
    setErrorHandler(paramErrorHandler);
  }
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.fErrorHandler = paramErrorHandler;
  }
  
  public ErrorHandler getErrorHandler() {
    return this.fErrorHandler;
  }
  
  public void warning(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException {
    if (this.fErrorHandler != null) {
      SAXParseException sAXParseException = createSAXParseException(paramXMLParseException);
      try {
        this.fErrorHandler.warning(sAXParseException);
      } catch (SAXParseException sAXParseException1) {
        throw createXMLParseException(sAXParseException1);
      } catch (SAXException sAXException) {
        throw createXNIException(sAXException);
      } 
    } 
  }
  
  public void error(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException {
    if (this.fErrorHandler != null) {
      SAXParseException sAXParseException = createSAXParseException(paramXMLParseException);
      try {
        this.fErrorHandler.error(sAXParseException);
      } catch (SAXParseException sAXParseException1) {
        throw createXMLParseException(sAXParseException1);
      } catch (SAXException sAXException) {
        throw createXNIException(sAXException);
      } 
    } 
  }
  
  public void fatalError(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException {
    if (this.fErrorHandler != null) {
      SAXParseException sAXParseException = createSAXParseException(paramXMLParseException);
      try {
        this.fErrorHandler.fatalError(sAXParseException);
      } catch (SAXParseException sAXParseException1) {
        throw createXMLParseException(sAXParseException1);
      } catch (SAXException sAXException) {
        throw createXNIException(sAXException);
      } 
    } 
  }
  
  protected static SAXParseException createSAXParseException(XMLParseException paramXMLParseException) {
    return new SAXParseException(paramXMLParseException.getMessage(), paramXMLParseException.getPublicId(), paramXMLParseException.getExpandedSystemId(), paramXMLParseException.getLineNumber(), paramXMLParseException.getColumnNumber(), paramXMLParseException.getException());
  }
  
  protected static XMLParseException createXMLParseException(SAXParseException paramSAXParseException) {
    String str1 = paramSAXParseException.getPublicId();
    String str2 = paramSAXParseException.getSystemId();
    int i = paramSAXParseException.getLineNumber();
    int j = paramSAXParseException.getColumnNumber();
    XMLLocator xMLLocator = new XMLLocator(str1, str2, j, i) {
        private final String val$fPublicId;
        
        private final String val$fExpandedSystemId;
        
        private final int val$fColumnNumber;
        
        private final int val$fLineNumber;
        
        public String getPublicId() {
          return this.val$fPublicId;
        }
        
        public String getExpandedSystemId() {
          return this.val$fExpandedSystemId;
        }
        
        public String getBaseSystemId() {
          return null;
        }
        
        public String getLiteralSystemId() {
          return null;
        }
        
        public int getColumnNumber() {
          return this.val$fColumnNumber;
        }
        
        public int getLineNumber() {
          return this.val$fLineNumber;
        }
        
        public int getCharacterOffset() {
          return -1;
        }
        
        public String getEncoding() {
          return null;
        }
        
        public String getXMLVersion() {
          return null;
        }
      };
    return new XMLParseException(xMLLocator, paramSAXParseException.getMessage(), paramSAXParseException);
  }
  
  protected static XNIException createXNIException(SAXException paramSAXException) {
    return new XNIException(paramSAXException.getMessage(), paramSAXException);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\ErrorHandlerWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */