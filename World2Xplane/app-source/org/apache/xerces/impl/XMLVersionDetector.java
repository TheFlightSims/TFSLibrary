package org.apache.xerces.impl;

import java.io.CharConversionException;
import java.io.EOFException;
import java.io.IOException;
import org.apache.xerces.impl.io.MalformedByteSequenceException;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XMLVersionDetector {
  private static final char[] XML11_VERSION = new char[] { '1', '.', '1' };
  
  protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  protected static final String fVersionSymbol = "version".intern();
  
  protected static final String fXMLSymbol = "[xml]".intern();
  
  protected SymbolTable fSymbolTable;
  
  protected XMLErrorReporter fErrorReporter;
  
  protected XMLEntityManager fEntityManager;
  
  protected String fEncoding = null;
  
  private final char[] fExpectedVersionString = new char[] { 
      '<', '?', 'x', 'm', 'l', ' ', 'v', 'e', 'r', 's', 
      'i', 'o', 'n', '=', ' ', ' ', ' ', ' ', ' ' };
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    this.fSymbolTable = (SymbolTable)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    this.fErrorReporter = (XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    this.fEntityManager = (XMLEntityManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
    for (byte b = 14; b < this.fExpectedVersionString.length; b++)
      this.fExpectedVersionString[b] = ' '; 
  }
  
  public void startDocumentParsing(XMLEntityHandler paramXMLEntityHandler, short paramShort) {
    if (paramShort == 1) {
      this.fEntityManager.setScannerVersion((short)1);
    } else {
      this.fEntityManager.setScannerVersion((short)2);
    } 
    this.fErrorReporter.setDocumentLocator(this.fEntityManager.getEntityScanner());
    this.fEntityManager.setEntityHandler(paramXMLEntityHandler);
    paramXMLEntityHandler.startEntity(fXMLSymbol, this.fEntityManager.getCurrentResourceIdentifier(), this.fEncoding, null);
  }
  
  public short determineDocVersion(XMLInputSource paramXMLInputSource) throws IOException {
    this.fEncoding = this.fEntityManager.setupCurrentEntity(fXMLSymbol, paramXMLInputSource, false, true);
    this.fEntityManager.setScannerVersion((short)1);
    XMLEntityScanner xMLEntityScanner = this.fEntityManager.getEntityScanner();
    try {
      if (!xMLEntityScanner.skipString("<?xml"))
        return 1; 
      if (!xMLEntityScanner.skipDeclSpaces()) {
        fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 5);
        return 1;
      } 
      if (!xMLEntityScanner.skipString("version")) {
        fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 6);
        return 1;
      } 
      xMLEntityScanner.skipDeclSpaces();
      if (xMLEntityScanner.peekChar() != 61) {
        fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 13);
        return 1;
      } 
      xMLEntityScanner.scanChar();
      xMLEntityScanner.skipDeclSpaces();
      int i = xMLEntityScanner.scanChar();
      this.fExpectedVersionString[14] = (char)i;
      for (byte b1 = 0; b1 < XML11_VERSION.length; b1++)
        this.fExpectedVersionString[15 + b1] = (char)xMLEntityScanner.scanChar(); 
      this.fExpectedVersionString[18] = (char)xMLEntityScanner.scanChar();
      fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 19);
      byte b2;
      for (b2 = 0; b2 < XML11_VERSION.length && this.fExpectedVersionString[15 + b2] == XML11_VERSION[b2]; b2++);
      return (b2 == XML11_VERSION.length) ? 2 : 1;
    } catch (MalformedByteSequenceException malformedByteSequenceException) {
      this.fErrorReporter.reportError(malformedByteSequenceException.getDomain(), malformedByteSequenceException.getKey(), malformedByteSequenceException.getArguments(), (short)2, (Exception)malformedByteSequenceException);
      return -1;
    } catch (CharConversionException charConversionException) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[])null, (short)2, charConversionException);
      return -1;
    } catch (EOFException eOFException) {
      this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "PrematureEOF", null, (short)2);
      return -1;
    } 
  }
  
  private void fixupCurrentEntity(XMLEntityManager paramXMLEntityManager, char[] paramArrayOfchar, int paramInt) {
    XMLEntityManager.ScannedEntity scannedEntity = paramXMLEntityManager.getCurrentEntity();
    if (scannedEntity.count - scannedEntity.position + paramInt > scannedEntity.ch.length) {
      char[] arrayOfChar = scannedEntity.ch;
      scannedEntity.ch = new char[paramInt + scannedEntity.count - scannedEntity.position + 1];
      System.arraycopy(arrayOfChar, 0, scannedEntity.ch, 0, arrayOfChar.length);
    } 
    if (scannedEntity.position < paramInt) {
      System.arraycopy(scannedEntity.ch, scannedEntity.position, scannedEntity.ch, paramInt, scannedEntity.count - scannedEntity.position);
      scannedEntity.count += paramInt - scannedEntity.position;
    } else {
      for (int i = paramInt; i < scannedEntity.position; i++)
        scannedEntity.ch[i] = ' '; 
    } 
    System.arraycopy(paramArrayOfchar, 0, scannedEntity.ch, 0, paramInt);
    scannedEntity.position = 0;
    scannedEntity.baseCharOffset = 0;
    scannedEntity.startPosition = 0;
    scannedEntity.columnNumber = scannedEntity.lineNumber = 1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLVersionDetector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */