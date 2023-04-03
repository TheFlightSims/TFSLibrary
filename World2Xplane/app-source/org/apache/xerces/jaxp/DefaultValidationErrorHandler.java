package org.apache.xerces.jaxp;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class DefaultValidationErrorHandler extends DefaultHandler {
  private static int ERROR_COUNT_LIMIT = 10;
  
  private int errorCount = 0;
  
  public void error(SAXParseException paramSAXParseException) throws SAXException {
    if (this.errorCount >= ERROR_COUNT_LIMIT)
      return; 
    if (this.errorCount == 0) {
      System.err.println("Warning: validation was turned on but an org.xml.sax.ErrorHandler was not");
      System.err.println("set, which is probably not what is desired.  Parser will use a default");
      System.err.println("ErrorHandler to print the first " + ERROR_COUNT_LIMIT + " errors.  Please call");
      System.err.println("the 'setErrorHandler' method to fix this.");
    } 
    String str1 = paramSAXParseException.getSystemId();
    if (str1 == null)
      str1 = "null"; 
    String str2 = "Error: URI=" + str1 + " Line=" + paramSAXParseException.getLineNumber() + ": " + paramSAXParseException.getMessage();
    System.err.println(str2);
    this.errorCount++;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\DefaultValidationErrorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */