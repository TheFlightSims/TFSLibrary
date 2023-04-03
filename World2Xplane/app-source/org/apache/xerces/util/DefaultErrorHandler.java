package org.apache.xerces.util;

import java.io.PrintWriter;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLParseException;

public class DefaultErrorHandler implements XMLErrorHandler {
  protected PrintWriter fOut;
  
  public DefaultErrorHandler() {
    this(new PrintWriter(System.err));
  }
  
  public DefaultErrorHandler(PrintWriter paramPrintWriter) {
    this.fOut = paramPrintWriter;
  }
  
  public void warning(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException {
    printError("Warning", paramXMLParseException);
  }
  
  public void error(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException {
    printError("Error", paramXMLParseException);
  }
  
  public void fatalError(String paramString1, String paramString2, XMLParseException paramXMLParseException) throws XNIException {
    printError("Fatal Error", paramXMLParseException);
    throw paramXMLParseException;
  }
  
  private void printError(String paramString, XMLParseException paramXMLParseException) {
    this.fOut.print("[");
    this.fOut.print(paramString);
    this.fOut.print("] ");
    String str = paramXMLParseException.getExpandedSystemId();
    if (str != null) {
      int i = str.lastIndexOf('/');
      if (i != -1)
        str = str.substring(i + 1); 
      this.fOut.print(str);
    } 
    this.fOut.print(':');
    this.fOut.print(paramXMLParseException.getLineNumber());
    this.fOut.print(':');
    this.fOut.print(paramXMLParseException.getColumnNumber());
    this.fOut.print(": ");
    this.fOut.print(paramXMLParseException.getMessage());
    this.fOut.println();
    this.fOut.flush();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\DefaultErrorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */