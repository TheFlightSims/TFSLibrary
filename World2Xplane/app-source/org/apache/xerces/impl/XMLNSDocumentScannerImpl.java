package org.apache.xerces.impl;

import java.io.IOException;
import org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDocumentSource;

public class XMLNSDocumentScannerImpl extends XMLDocumentScannerImpl {
  protected boolean fBindNamespaces;
  
  protected boolean fPerformValidation;
  
  private XMLDTDValidatorFilter fDTDValidator;
  
  private boolean fSawSpace;
  
  public void setDTDValidator(XMLDTDValidatorFilter paramXMLDTDValidatorFilter) {
    this.fDTDValidator = paramXMLDTDValidatorFilter;
  }
  
  protected boolean scanStartElement() throws IOException, XNIException {
    this.fEntityScanner.scanQName(this.fElementQName);
    String str = this.fElementQName.rawname;
    if (this.fBindNamespaces) {
      this.fNamespaceContext.pushContext();
      if (this.fScannerState == 6 && this.fPerformValidation) {
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_GRAMMAR_NOT_FOUND", new Object[] { str }, (short)1);
        if (this.fDoctypeName == null || !this.fDoctypeName.equals(str))
          this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RootElementTypeMustMatchDoctypedecl", new Object[] { this.fDoctypeName, str }, (short)1); 
      } 
    } 
    this.fCurrentElement = this.fElementStack.pushElement(this.fElementQName);
    boolean bool = false;
    this.fAttributes.removeAllAttributes();
    while (true) {
      boolean bool1 = this.fEntityScanner.skipSpaces();
      int i = this.fEntityScanner.peekChar();
      if (i == 62) {
        this.fEntityScanner.scanChar();
        break;
      } 
      if (i == 47) {
        this.fEntityScanner.scanChar();
        if (!this.fEntityScanner.skipChar(62))
          reportFatalError("ElementUnterminated", new Object[] { str }); 
        bool = true;
        break;
      } 
      if (!isValidNameStartChar(i) || !bool1)
        reportFatalError("ElementUnterminated", new Object[] { str }); 
      scanAttribute(this.fAttributes);
    } 
    if (this.fBindNamespaces) {
      if (this.fElementQName.prefix == XMLSymbols.PREFIX_XMLNS)
        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[] { this.fElementQName.rawname }, (short)2); 
      String str1 = (this.fElementQName.prefix != null) ? this.fElementQName.prefix : XMLSymbols.EMPTY_STRING;
      this.fElementQName.uri = this.fNamespaceContext.getURI(str1);
      this.fCurrentElement.uri = this.fElementQName.uri;
      if (this.fElementQName.prefix == null && this.fElementQName.uri != null) {
        this.fElementQName.prefix = XMLSymbols.EMPTY_STRING;
        this.fCurrentElement.prefix = XMLSymbols.EMPTY_STRING;
      } 
      if (this.fElementQName.prefix != null && this.fElementQName.uri == null)
        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[] { this.fElementQName.prefix, this.fElementQName.rawname }, (short)2); 
      int i = this.fAttributes.getLength();
      for (byte b = 0; b < i; b++) {
        this.fAttributes.getName(b, this.fAttributeQName);
        String str2 = (this.fAttributeQName.prefix != null) ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
        String str3 = this.fNamespaceContext.getURI(str2);
        if ((this.fAttributeQName.uri == null || this.fAttributeQName.uri != str3) && str2 != XMLSymbols.EMPTY_STRING) {
          this.fAttributeQName.uri = str3;
          if (str3 == null)
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[] { this.fElementQName.rawname, this.fAttributeQName.rawname, str2 }, (short)2); 
          this.fAttributes.setURI(b, str3);
        } 
      } 
      if (i > 1) {
        QName qName = this.fAttributes.checkDuplicatesNS();
        if (qName != null)
          if (qName.uri != null) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[] { this.fElementQName.rawname, qName.localpart, qName.uri }, (short)2);
          } else {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNotUnique", new Object[] { this.fElementQName.rawname, qName.rawname }, (short)2);
          }  
      } 
    } 
    if (this.fDocumentHandler != null)
      if (bool) {
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1])
          reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname }); 
        this.fDocumentHandler.emptyElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
        if (this.fBindNamespaces)
          this.fNamespaceContext.popContext(); 
        this.fElementStack.popElement(this.fElementQName);
      } else {
        this.fDocumentHandler.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
      }  
    return bool;
  }
  
  protected void scanStartElementName() throws IOException, XNIException {
    this.fEntityScanner.scanQName(this.fElementQName);
    this.fSawSpace = this.fEntityScanner.skipSpaces();
  }
  
  protected boolean scanStartElementAfterName() throws IOException, XNIException {
    String str = this.fElementQName.rawname;
    if (this.fBindNamespaces) {
      this.fNamespaceContext.pushContext();
      if (this.fScannerState == 6 && this.fPerformValidation) {
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_GRAMMAR_NOT_FOUND", new Object[] { str }, (short)1);
        if (this.fDoctypeName == null || !this.fDoctypeName.equals(str))
          this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RootElementTypeMustMatchDoctypedecl", new Object[] { this.fDoctypeName, str }, (short)1); 
      } 
    } 
    this.fCurrentElement = this.fElementStack.pushElement(this.fElementQName);
    boolean bool = false;
    this.fAttributes.removeAllAttributes();
    while (true) {
      int i = this.fEntityScanner.peekChar();
      if (i == 62) {
        this.fEntityScanner.scanChar();
        break;
      } 
      if (i == 47) {
        this.fEntityScanner.scanChar();
        if (!this.fEntityScanner.skipChar(62))
          reportFatalError("ElementUnterminated", new Object[] { str }); 
        bool = true;
        break;
      } 
      if (!isValidNameStartChar(i) || !this.fSawSpace)
        reportFatalError("ElementUnterminated", new Object[] { str }); 
      scanAttribute(this.fAttributes);
      this.fSawSpace = this.fEntityScanner.skipSpaces();
    } 
    if (this.fBindNamespaces) {
      if (this.fElementQName.prefix == XMLSymbols.PREFIX_XMLNS)
        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[] { this.fElementQName.rawname }, (short)2); 
      String str1 = (this.fElementQName.prefix != null) ? this.fElementQName.prefix : XMLSymbols.EMPTY_STRING;
      this.fElementQName.uri = this.fNamespaceContext.getURI(str1);
      this.fCurrentElement.uri = this.fElementQName.uri;
      if (this.fElementQName.prefix == null && this.fElementQName.uri != null) {
        this.fElementQName.prefix = XMLSymbols.EMPTY_STRING;
        this.fCurrentElement.prefix = XMLSymbols.EMPTY_STRING;
      } 
      if (this.fElementQName.prefix != null && this.fElementQName.uri == null)
        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[] { this.fElementQName.prefix, this.fElementQName.rawname }, (short)2); 
      int i = this.fAttributes.getLength();
      for (byte b = 0; b < i; b++) {
        this.fAttributes.getName(b, this.fAttributeQName);
        String str2 = (this.fAttributeQName.prefix != null) ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
        String str3 = this.fNamespaceContext.getURI(str2);
        if ((this.fAttributeQName.uri == null || this.fAttributeQName.uri != str3) && str2 != XMLSymbols.EMPTY_STRING) {
          this.fAttributeQName.uri = str3;
          if (str3 == null)
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[] { this.fElementQName.rawname, this.fAttributeQName.rawname, str2 }, (short)2); 
          this.fAttributes.setURI(b, str3);
        } 
      } 
      if (i > 1) {
        QName qName = this.fAttributes.checkDuplicatesNS();
        if (qName != null)
          if (qName.uri != null) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[] { this.fElementQName.rawname, qName.localpart, qName.uri }, (short)2);
          } else {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNotUnique", new Object[] { this.fElementQName.rawname, qName.rawname }, (short)2);
          }  
      } 
    } 
    if (this.fDocumentHandler != null)
      if (bool) {
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1])
          reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname }); 
        this.fDocumentHandler.emptyElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
        if (this.fBindNamespaces)
          this.fNamespaceContext.popContext(); 
        this.fElementStack.popElement(this.fElementQName);
      } else {
        this.fDocumentHandler.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
      }  
    return bool;
  }
  
  protected void scanAttribute(XMLAttributesImpl paramXMLAttributesImpl) throws IOException, XNIException {
    int i;
    this.fEntityScanner.scanQName(this.fAttributeQName);
    this.fEntityScanner.skipSpaces();
    if (!this.fEntityScanner.skipChar(61))
      reportFatalError("EqRequiredInAttribute", new Object[] { this.fCurrentElement.rawname, this.fAttributeQName.rawname }); 
    this.fEntityScanner.skipSpaces();
    if (this.fBindNamespaces) {
      i = paramXMLAttributesImpl.getLength();
      paramXMLAttributesImpl.addAttributeNS(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
    } else {
      int j = paramXMLAttributesImpl.getLength();
      i = paramXMLAttributesImpl.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
      if (j == paramXMLAttributesImpl.getLength())
        reportFatalError("AttributeNotUnique", new Object[] { this.fCurrentElement.rawname, this.fAttributeQName.rawname }); 
    } 
    boolean bool = scanAttributeValue(this.fTempString, this.fTempString2, this.fAttributeQName.rawname, this.fIsEntityDeclaredVC, this.fCurrentElement.rawname);
    String str = this.fTempString.toString();
    paramXMLAttributesImpl.setValue(i, str);
    if (!bool)
      paramXMLAttributesImpl.setNonNormalizedValue(i, this.fTempString2.toString()); 
    paramXMLAttributesImpl.setSpecified(i, true);
    if (this.fBindNamespaces) {
      String str1 = this.fAttributeQName.localpart;
      String str2 = (this.fAttributeQName.prefix != null) ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
      if (str2 == XMLSymbols.PREFIX_XMLNS || (str2 == XMLSymbols.EMPTY_STRING && str1 == XMLSymbols.PREFIX_XMLNS)) {
        String str3 = this.fSymbolTable.addSymbol(str);
        if (str2 == XMLSymbols.PREFIX_XMLNS && str1 == XMLSymbols.PREFIX_XMLNS)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { this.fAttributeQName }, (short)2); 
        if (str3 == NamespaceContext.XMLNS_URI)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { this.fAttributeQName }, (short)2); 
        if (str1 == XMLSymbols.PREFIX_XML) {
          if (str3 != NamespaceContext.XML_URI)
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { this.fAttributeQName }, (short)2); 
        } else if (str3 == NamespaceContext.XML_URI) {
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { this.fAttributeQName }, (short)2);
        } 
        str2 = (str1 != XMLSymbols.PREFIX_XMLNS) ? str1 : XMLSymbols.EMPTY_STRING;
        if (str3 == XMLSymbols.EMPTY_STRING && str1 != XMLSymbols.PREFIX_XMLNS)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "EmptyPrefixedAttName", new Object[] { this.fAttributeQName }, (short)2); 
        this.fNamespaceContext.declarePrefix(str2, (str3.length() != 0) ? str3 : null);
        paramXMLAttributesImpl.setURI(i, this.fNamespaceContext.getURI(XMLSymbols.PREFIX_XMLNS));
      } else if (this.fAttributeQName.prefix != null) {
        paramXMLAttributesImpl.setURI(i, this.fNamespaceContext.getURI(this.fAttributeQName.prefix));
      } 
    } 
  }
  
  protected int scanEndElement() throws IOException, XNIException {
    this.fElementStack.popElement(this.fElementQName);
    if (!this.fEntityScanner.skipString(this.fElementQName.rawname))
      reportFatalError("ETagRequired", new Object[] { this.fElementQName.rawname }); 
    this.fEntityScanner.skipSpaces();
    if (!this.fEntityScanner.skipChar(62))
      reportFatalError("ETagUnterminated", new Object[] { this.fElementQName.rawname }); 
    this.fMarkupDepth--;
    this.fMarkupDepth--;
    if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1])
      reportFatalError("ElementEntityMismatch", new Object[] { this.fCurrentElement.rawname }); 
    if (this.fDocumentHandler != null) {
      this.fDocumentHandler.endElement(this.fElementQName, null);
      if (this.fBindNamespaces)
        this.fNamespaceContext.popContext(); 
    } 
    return this.fMarkupDepth;
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    super.reset(paramXMLComponentManager);
    this.fPerformValidation = false;
    this.fBindNamespaces = false;
  }
  
  protected XMLDocumentFragmentScannerImpl.Dispatcher createContentDispatcher() {
    return new NSContentDispatcher(this);
  }
  
  protected final class NSContentDispatcher extends XMLDocumentScannerImpl.ContentDispatcher {
    private final XMLNSDocumentScannerImpl this$0;
    
    protected NSContentDispatcher(XMLNSDocumentScannerImpl this$0) {
      super(this$0);
      this.this$0 = this$0;
    }
    
    protected boolean scanRootElementHook() throws IOException, XNIException {
      if (this.this$0.fExternalSubsetResolver != null && !this.this$0.fSeenDoctypeDecl && !this.this$0.fDisallowDoctype && (this.this$0.fValidation || this.this$0.fLoadExternalDTD)) {
        this.this$0.scanStartElementName();
        resolveExternalSubsetAndRead();
        reconfigurePipeline();
        if (this.this$0.scanStartElementAfterName()) {
          this.this$0.setScannerState(12);
          this.this$0.setDispatcher(this.this$0.fTrailingMiscDispatcher);
          return true;
        } 
      } else {
        reconfigurePipeline();
        if (this.this$0.scanStartElement()) {
          this.this$0.setScannerState(12);
          this.this$0.setDispatcher(this.this$0.fTrailingMiscDispatcher);
          return true;
        } 
      } 
      return false;
    }
    
    private void reconfigurePipeline() {
      if (this.this$0.fDTDValidator == null) {
        this.this$0.fBindNamespaces = true;
      } else if (!this.this$0.fDTDValidator.hasGrammar()) {
        this.this$0.fBindNamespaces = true;
        this.this$0.fPerformValidation = this.this$0.fDTDValidator.validate();
        XMLDocumentSource xMLDocumentSource = this.this$0.fDTDValidator.getDocumentSource();
        XMLDocumentHandler xMLDocumentHandler = this.this$0.fDTDValidator.getDocumentHandler();
        xMLDocumentSource.setDocumentHandler(xMLDocumentHandler);
        if (xMLDocumentHandler != null)
          xMLDocumentHandler.setDocumentSource(xMLDocumentSource); 
        this.this$0.fDTDValidator.setDocumentSource(null);
        this.this$0.fDTDValidator.setDocumentHandler(null);
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLNSDocumentScannerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */