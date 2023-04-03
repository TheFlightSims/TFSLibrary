package org.apache.xerces.impl;

import java.io.IOException;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XML11Char;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;

public class XML11DTDScannerImpl extends XMLDTDScannerImpl {
  private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
  
  public XML11DTDScannerImpl() {}
  
  public XML11DTDScannerImpl(SymbolTable paramSymbolTable, XMLErrorReporter paramXMLErrorReporter, XMLEntityManager paramXMLEntityManager) {
    super(paramSymbolTable, paramXMLErrorReporter, paramXMLEntityManager);
  }
  
  protected boolean scanPubidLiteral(XMLString paramXMLString) throws IOException, XNIException {
    int i = this.fEntityScanner.scanChar();
    if (i != 39 && i != 34) {
      reportFatalError("QuoteRequiredInPublicID", null);
      return false;
    } 
    this.fStringBuffer.clear();
    boolean bool1 = true;
    boolean bool2 = true;
    while (true) {
      int j = this.fEntityScanner.scanChar();
      if (j == 32 || j == 10 || j == 13 || j == 133 || j == 8232) {
        if (!bool1) {
          this.fStringBuffer.append(' ');
          bool1 = true;
        } 
        continue;
      } 
      if (j == i) {
        if (bool1)
          ((XMLString)this.fStringBuffer).length--; 
        paramXMLString.setValues((XMLString)this.fStringBuffer);
      } else {
        if (XMLChar.isPubid(j)) {
          this.fStringBuffer.append((char)j);
          bool1 = false;
          continue;
        } 
        if (j == -1) {
          reportFatalError("PublicIDUnterminated", null);
          return false;
        } 
        bool2 = false;
        reportFatalError("InvalidCharInPublicID", new Object[] { Integer.toHexString(j) });
        continue;
      } 
      return bool2;
    } 
  }
  
  protected void normalizeWhitespace(XMLString paramXMLString) {
    int i = paramXMLString.offset + paramXMLString.length;
    for (int j = paramXMLString.offset; j < i; j++) {
      char c = paramXMLString.ch[j];
      if (XMLChar.isSpace(c))
        paramXMLString.ch[j] = ' '; 
    } 
  }
  
  protected void normalizeWhitespace(XMLString paramXMLString, int paramInt) {
    int i = paramXMLString.offset + paramXMLString.length;
    for (int j = paramXMLString.offset + paramInt; j < i; j++) {
      char c = paramXMLString.ch[j];
      if (XMLChar.isSpace(c))
        paramXMLString.ch[j] = ' '; 
    } 
  }
  
  protected int isUnchangedByNormalization(XMLString paramXMLString) {
    int i = paramXMLString.offset + paramXMLString.length;
    for (int j = paramXMLString.offset; j < i; j++) {
      char c = paramXMLString.ch[j];
      if (XMLChar.isSpace(c))
        return j - paramXMLString.offset; 
    } 
    return -1;
  }
  
  protected boolean isInvalid(int paramInt) {
    return !XML11Char.isXML11Valid(paramInt);
  }
  
  protected boolean isInvalidLiteral(int paramInt) {
    return !XML11Char.isXML11ValidLiteral(paramInt);
  }
  
  protected boolean isValidNameChar(int paramInt) {
    return XML11Char.isXML11Name(paramInt);
  }
  
  protected boolean isValidNameStartChar(int paramInt) {
    return XML11Char.isXML11NameStart(paramInt);
  }
  
  protected boolean isValidNCName(int paramInt) {
    return XML11Char.isXML11NCName(paramInt);
  }
  
  protected boolean isValidNameStartHighSurrogate(int paramInt) {
    return XML11Char.isXML11NameHighSurrogate(paramInt);
  }
  
  protected boolean versionSupported(String paramString) {
    return (paramString.equals("1.1") || paramString.equals("1.0"));
  }
  
  protected String getVersionNotSupportedKey() {
    return "VersionNotSupported11";
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XML11DTDScannerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */