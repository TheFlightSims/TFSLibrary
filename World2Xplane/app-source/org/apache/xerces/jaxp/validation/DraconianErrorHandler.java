package org.apache.xerces.jaxp.validation;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

final class DraconianErrorHandler implements ErrorHandler {
  private static final DraconianErrorHandler ERROR_HANDLER_INSTANCE = new DraconianErrorHandler();
  
  public static DraconianErrorHandler getInstance() {
    return ERROR_HANDLER_INSTANCE;
  }
  
  public void warning(SAXParseException paramSAXParseException) throws SAXException {}
  
  public void error(SAXParseException paramSAXParseException) throws SAXException {
    throw paramSAXParseException;
  }
  
  public void fatalError(SAXParseException paramSAXParseException) throws SAXException {
    throw paramSAXParseException;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\DraconianErrorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */