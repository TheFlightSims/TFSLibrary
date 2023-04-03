package org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Locale;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.TypeInfoProvider;
import javax.xml.validation.ValidatorHandler;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.validation.EntityState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.util.AttributesProxy;
import org.apache.xerces.util.SAXLocatorWrapper;
import org.apache.xerces.util.SAXMessageFormatter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.URI;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.ItemPSVI;
import org.apache.xerces.xs.PSVIProvider;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.Attributes2;
import org.xml.sax.ext.EntityResolver2;
import org.xml.sax.ext.LexicalHandler;

final class ValidatorHandlerImpl extends ValidatorHandler implements DTDHandler, EntityState, PSVIProvider, ValidatorHelper, XMLDocumentHandler {
  private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
  
  protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
  
  private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  private static final String LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
  
  private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
  
  private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
  
  private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  private final XMLErrorReporter fErrorReporter;
  
  private final NamespaceContext fNamespaceContext;
  
  private final XMLSchemaValidator fSchemaValidator;
  
  private final SymbolTable fSymbolTable;
  
  private final ValidationManager fValidationManager;
  
  private final XMLSchemaValidatorComponentManager fComponentManager;
  
  private final SAXLocatorWrapper fSAXLocatorWrapper = new SAXLocatorWrapper();
  
  private boolean fNeedPushNSContext = true;
  
  private HashMap fUnparsedEntities = null;
  
  private boolean fStringsInternalized = false;
  
  private final QName fElementQName = new QName();
  
  private final QName fAttributeQName = new QName();
  
  private final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
  
  private final AttributesProxy fAttrAdapter = new AttributesProxy((XMLAttributes)this.fAttributes);
  
  private final XMLString fTempString = new XMLString();
  
  private ContentHandler fContentHandler = null;
  
  private final XMLSchemaTypeInfoProvider fTypeInfoProvider = new XMLSchemaTypeInfoProvider();
  
  private final ResolutionForwarder fResolutionForwarder = new ResolutionForwarder(null);
  
  public ValidatorHandlerImpl(XSGrammarPoolContainer paramXSGrammarPoolContainer) {
    this(new XMLSchemaValidatorComponentManager(paramXSGrammarPoolContainer));
    this.fComponentManager.addRecognizedFeatures(new String[] { "http://xml.org/sax/features/namespace-prefixes" });
    this.fComponentManager.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
    setErrorHandler(null);
    setResourceResolver(null);
  }
  
  public ValidatorHandlerImpl(XMLSchemaValidatorComponentManager paramXMLSchemaValidatorComponentManager) {
    this.fComponentManager = paramXMLSchemaValidatorComponentManager;
    this.fErrorReporter = (XMLErrorReporter)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    this.fNamespaceContext = (NamespaceContext)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/namespace-context");
    this.fSchemaValidator = (XMLSchemaValidator)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validator/schema");
    this.fSymbolTable = (SymbolTable)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    this.fValidationManager = (ValidationManager)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
  }
  
  public void setContentHandler(ContentHandler paramContentHandler) {
    this.fContentHandler = paramContentHandler;
  }
  
  public ContentHandler getContentHandler() {
    return this.fContentHandler;
  }
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.fComponentManager.setErrorHandler(paramErrorHandler);
  }
  
  public ErrorHandler getErrorHandler() {
    return this.fComponentManager.getErrorHandler();
  }
  
  public void setResourceResolver(LSResourceResolver paramLSResourceResolver) {
    this.fComponentManager.setResourceResolver(paramLSResourceResolver);
  }
  
  public LSResourceResolver getResourceResolver() {
    return this.fComponentManager.getResourceResolver();
  }
  
  public TypeInfoProvider getTypeInfoProvider() {
    return this.fTypeInfoProvider;
  }
  
  public boolean getFeature(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    try {
      return this.fComponentManager.getFeature(paramString);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str1 = xMLConfigurationException.getIdentifier();
      String str2 = (xMLConfigurationException.getType() == 0) ? "feature-not-recognized" : "feature-not-supported";
      throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), str2, new Object[] { str1 }));
    } 
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    try {
      this.fComponentManager.setFeature(paramString, paramBoolean);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str1 = xMLConfigurationException.getIdentifier();
      String str2 = (xMLConfigurationException.getType() == 0) ? "feature-not-recognized" : "feature-not-supported";
      throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), str2, new Object[] { str1 }));
    } 
  }
  
  public Object getProperty(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    try {
      return this.fComponentManager.getProperty(paramString);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str1 = xMLConfigurationException.getIdentifier();
      String str2 = (xMLConfigurationException.getType() == 0) ? "property-not-recognized" : "property-not-supported";
      throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), str2, new Object[] { str1 }));
    } 
  }
  
  public void setProperty(String paramString, Object paramObject) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    try {
      this.fComponentManager.setProperty(paramString, paramObject);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str1 = xMLConfigurationException.getIdentifier();
      String str2 = (xMLConfigurationException.getType() == 0) ? "property-not-recognized" : "property-not-supported";
      throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), str2, new Object[] { str1 }));
    } 
  }
  
  public boolean isEntityDeclared(String paramString) {
    return false;
  }
  
  public boolean isEntityUnparsed(String paramString) {
    return (this.fUnparsedEntities != null) ? this.fUnparsedEntities.containsKey(paramString) : false;
  }
  
  public void startDocument(XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations) throws XNIException {
    if (this.fContentHandler != null)
      try {
        this.fContentHandler.startDocument();
      } catch (SAXException sAXException) {
        throw new XNIException(sAXException);
      }  
  }
  
  public void xmlDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void doctypeDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fContentHandler != null)
      try {
        this.fContentHandler.processingInstruction(paramString, paramXMLString.toString());
      } catch (SAXException sAXException) {
        throw new XNIException(sAXException);
      }  
  }
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    if (this.fContentHandler != null)
      try {
        this.fTypeInfoProvider.beginStartElement(paramAugmentations, paramXMLAttributes);
        this.fContentHandler.startElement((paramQName.uri != null) ? paramQName.uri : XMLSymbols.EMPTY_STRING, paramQName.localpart, paramQName.rawname, (Attributes)this.fAttrAdapter);
      } catch (SAXException sAXException) {
        throw new XNIException(sAXException);
      } finally {
        this.fTypeInfoProvider.finishStartElement();
      }  
  }
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    startElement(paramQName, paramXMLAttributes, paramAugmentations);
    endElement(paramQName, paramAugmentations);
  }
  
  public void startGeneralEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endGeneralEntity(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fContentHandler != null) {
      if (paramXMLString.length == 0)
        return; 
      try {
        this.fContentHandler.characters(paramXMLString.ch, paramXMLString.offset, paramXMLString.length);
      } catch (SAXException sAXException) {
        throw new XNIException(sAXException);
      } 
    } 
  }
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (this.fContentHandler != null)
      try {
        this.fContentHandler.ignorableWhitespace(paramXMLString.ch, paramXMLString.offset, paramXMLString.length);
      } catch (SAXException sAXException) {
        throw new XNIException(sAXException);
      }  
  }
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {
    if (this.fContentHandler != null)
      try {
        this.fTypeInfoProvider.beginEndElement(paramAugmentations);
        this.fContentHandler.endElement((paramQName.uri != null) ? paramQName.uri : XMLSymbols.EMPTY_STRING, paramQName.localpart, paramQName.rawname);
      } catch (SAXException sAXException) {
        throw new XNIException(sAXException);
      } finally {
        this.fTypeInfoProvider.finishEndElement();
      }  
  }
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endDocument(Augmentations paramAugmentations) throws XNIException {
    if (this.fContentHandler != null)
      try {
        this.fContentHandler.endDocument();
      } catch (SAXException sAXException) {
        throw new XNIException(sAXException);
      }  
  }
  
  public void setDocumentSource(XMLDocumentSource paramXMLDocumentSource) {}
  
  public XMLDocumentSource getDocumentSource() {
    return (XMLDocumentSource)this.fSchemaValidator;
  }
  
  public void setDocumentLocator(Locator paramLocator) {
    this.fSAXLocatorWrapper.setLocator(paramLocator);
    if (this.fContentHandler != null)
      this.fContentHandler.setDocumentLocator(paramLocator); 
  }
  
  public void startDocument() throws SAXException {
    this.fComponentManager.reset();
    this.fSchemaValidator.setDocumentHandler(this);
    this.fValidationManager.setEntityState(this);
    this.fTypeInfoProvider.finishStartElement();
    this.fNeedPushNSContext = true;
    if (this.fUnparsedEntities != null && !this.fUnparsedEntities.isEmpty())
      this.fUnparsedEntities.clear(); 
    this.fErrorReporter.setDocumentLocator((XMLLocator)this.fSAXLocatorWrapper);
    try {
      this.fSchemaValidator.startDocument((XMLLocator)this.fSAXLocatorWrapper, this.fSAXLocatorWrapper.getEncoding(), this.fNamespaceContext, null);
    } catch (XMLParseException xMLParseException) {
      throw Util.toSAXParseException(xMLParseException);
    } catch (XNIException xNIException) {
      throw Util.toSAXException(xNIException);
    } 
  }
  
  public void endDocument() throws SAXException {
    this.fSAXLocatorWrapper.setLocator(null);
    try {
      this.fSchemaValidator.endDocument(null);
    } catch (XMLParseException xMLParseException) {
      throw Util.toSAXParseException(xMLParseException);
    } catch (XNIException xNIException) {
      throw Util.toSAXException(xNIException);
    } 
  }
  
  public void startPrefixMapping(String paramString1, String paramString2) throws SAXException {
    String str1;
    String str2;
    if (!this.fStringsInternalized) {
      str1 = (paramString1 != null) ? this.fSymbolTable.addSymbol(paramString1) : XMLSymbols.EMPTY_STRING;
      str2 = (paramString2 != null && paramString2.length() > 0) ? this.fSymbolTable.addSymbol(paramString2) : null;
    } else {
      str1 = (paramString1 != null) ? paramString1 : XMLSymbols.EMPTY_STRING;
      str2 = (paramString2 != null && paramString2.length() > 0) ? paramString2 : null;
    } 
    if (this.fNeedPushNSContext) {
      this.fNeedPushNSContext = false;
      this.fNamespaceContext.pushContext();
    } 
    this.fNamespaceContext.declarePrefix(str1, str2);
    if (this.fContentHandler != null)
      this.fContentHandler.startPrefixMapping(paramString1, paramString2); 
  }
  
  public void endPrefixMapping(String paramString) throws SAXException {
    if (this.fContentHandler != null)
      this.fContentHandler.endPrefixMapping(paramString); 
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException {
    if (this.fNeedPushNSContext)
      this.fNamespaceContext.pushContext(); 
    this.fNeedPushNSContext = true;
    fillQName(this.fElementQName, paramString1, paramString2, paramString3);
    if (paramAttributes instanceof Attributes2) {
      fillXMLAttributes2((Attributes2)paramAttributes);
    } else {
      fillXMLAttributes(paramAttributes);
    } 
    try {
      this.fSchemaValidator.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
    } catch (XMLParseException xMLParseException) {
      throw Util.toSAXParseException(xMLParseException);
    } catch (XNIException xNIException) {
      throw Util.toSAXException(xNIException);
    } 
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException {
    fillQName(this.fElementQName, paramString1, paramString2, paramString3);
    try {
      this.fSchemaValidator.endElement(this.fElementQName, null);
    } catch (XMLParseException xMLParseException) {
      throw Util.toSAXParseException(xMLParseException);
    } catch (XNIException xNIException) {
      throw Util.toSAXException(xNIException);
    } finally {
      this.fNamespaceContext.popContext();
    } 
  }
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    try {
      this.fTempString.setValues(paramArrayOfchar, paramInt1, paramInt2);
      this.fSchemaValidator.characters(this.fTempString, null);
    } catch (XMLParseException xMLParseException) {
      throw Util.toSAXParseException(xMLParseException);
    } catch (XNIException xNIException) {
      throw Util.toSAXException(xNIException);
    } 
  }
  
  public void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    try {
      this.fTempString.setValues(paramArrayOfchar, paramInt1, paramInt2);
      this.fSchemaValidator.ignorableWhitespace(this.fTempString, null);
    } catch (XMLParseException xMLParseException) {
      throw Util.toSAXParseException(xMLParseException);
    } catch (XNIException xNIException) {
      throw Util.toSAXException(xNIException);
    } 
  }
  
  public void processingInstruction(String paramString1, String paramString2) throws SAXException {
    if (this.fContentHandler != null)
      this.fContentHandler.processingInstruction(paramString1, paramString2); 
  }
  
  public void skippedEntity(String paramString) throws SAXException {
    if (this.fContentHandler != null)
      this.fContentHandler.skippedEntity(paramString); 
  }
  
  public void notationDecl(String paramString1, String paramString2, String paramString3) throws SAXException {}
  
  public void unparsedEntityDecl(String paramString1, String paramString2, String paramString3, String paramString4) throws SAXException {
    if (this.fUnparsedEntities == null)
      this.fUnparsedEntities = new HashMap(); 
    this.fUnparsedEntities.put(paramString1, paramString1);
  }
  
  public void validate(Source paramSource, Result paramResult) throws SAXException, IOException {
    if (paramResult instanceof SAXResult || paramResult == null) {
      SAXSource sAXSource = (SAXSource)paramSource;
      SAXResult sAXResult = (SAXResult)paramResult;
      LexicalHandler lexicalHandler = null;
      if (paramResult != null) {
        ContentHandler contentHandler = sAXResult.getHandler();
        lexicalHandler = sAXResult.getLexicalHandler();
        if (lexicalHandler == null && contentHandler instanceof LexicalHandler)
          lexicalHandler = (LexicalHandler)contentHandler; 
        setContentHandler(contentHandler);
      } 
      try {
        XMLReader xMLReader = sAXSource.getXMLReader();
        if (xMLReader == null) {
          SAXParserFactory sAXParserFactory = SAXParserFactory.newInstance();
          sAXParserFactory.setNamespaceAware(true);
          try {
            xMLReader = sAXParserFactory.newSAXParser().getXMLReader();
            if (xMLReader instanceof org.apache.xerces.parsers.SAXParser) {
              SecurityManager securityManager = (SecurityManager)this.fComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
              if (securityManager != null)
                try {
                  xMLReader.setProperty("http://apache.org/xml/properties/security-manager", securityManager);
                } catch (SAXException sAXException) {} 
            } 
          } catch (Exception exception) {
            throw new FactoryConfigurationError(exception);
          } 
        } 
        try {
          this.fStringsInternalized = xMLReader.getFeature("http://xml.org/sax/features/string-interning");
        } catch (SAXException sAXException) {
          this.fStringsInternalized = false;
        } 
        ErrorHandler errorHandler = this.fComponentManager.getErrorHandler();
        xMLReader.setErrorHandler((errorHandler != null) ? errorHandler : DraconianErrorHandler.getInstance());
        xMLReader.setEntityResolver(this.fResolutionForwarder);
        this.fResolutionForwarder.setEntityResolver(this.fComponentManager.getResourceResolver());
        xMLReader.setContentHandler(this);
        xMLReader.setDTDHandler(this);
        try {
          xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", lexicalHandler);
        } catch (SAXException sAXException) {}
        InputSource inputSource = sAXSource.getInputSource();
        xMLReader.parse(inputSource);
      } finally {
        setContentHandler(null);
      } 
      return;
    } 
    throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SourceResultMismatch", new Object[] { paramSource.getClass().getName(), paramResult.getClass().getName() }));
  }
  
  public ElementPSVI getElementPSVI() {
    return this.fTypeInfoProvider.getElementPSVI();
  }
  
  public AttributePSVI getAttributePSVI(int paramInt) {
    return this.fTypeInfoProvider.getAttributePSVI(paramInt);
  }
  
  public AttributePSVI getAttributePSVIByName(String paramString1, String paramString2) {
    return this.fTypeInfoProvider.getAttributePSVIByName(paramString1, paramString2);
  }
  
  private void fillQName(QName paramQName, String paramString1, String paramString2, String paramString3) {
    if (!this.fStringsInternalized) {
      paramString1 = (paramString1 != null && paramString1.length() > 0) ? this.fSymbolTable.addSymbol(paramString1) : null;
      paramString2 = (paramString2 != null) ? this.fSymbolTable.addSymbol(paramString2) : XMLSymbols.EMPTY_STRING;
      paramString3 = (paramString3 != null) ? this.fSymbolTable.addSymbol(paramString3) : XMLSymbols.EMPTY_STRING;
    } else {
      if (paramString1 != null && paramString1.length() == 0)
        paramString1 = null; 
      if (paramString2 == null)
        paramString2 = XMLSymbols.EMPTY_STRING; 
      if (paramString3 == null)
        paramString3 = XMLSymbols.EMPTY_STRING; 
    } 
    String str = XMLSymbols.EMPTY_STRING;
    int i = paramString3.indexOf(':');
    if (i != -1)
      str = this.fSymbolTable.addSymbol(paramString3.substring(0, i)); 
    paramQName.setValues(str, paramString2, paramString3, paramString1);
  }
  
  private void fillXMLAttributes(Attributes paramAttributes) {
    this.fAttributes.removeAllAttributes();
    int i = paramAttributes.getLength();
    for (byte b = 0; b < i; b++) {
      fillXMLAttribute(paramAttributes, b);
      this.fAttributes.setSpecified(b, true);
    } 
  }
  
  private void fillXMLAttributes2(Attributes2 paramAttributes2) {
    this.fAttributes.removeAllAttributes();
    int i = paramAttributes2.getLength();
    for (byte b = 0; b < i; b++) {
      fillXMLAttribute(paramAttributes2, b);
      this.fAttributes.setSpecified(b, paramAttributes2.isSpecified(b));
      if (paramAttributes2.isDeclared(b))
        this.fAttributes.getAugmentations(b).putItem("ATTRIBUTE_DECLARED", Boolean.TRUE); 
    } 
  }
  
  private void fillXMLAttribute(Attributes paramAttributes, int paramInt) {
    fillQName(this.fAttributeQName, paramAttributes.getURI(paramInt), paramAttributes.getLocalName(paramInt), paramAttributes.getQName(paramInt));
    String str = paramAttributes.getType(paramInt);
    this.fAttributes.addAttributeNS(this.fAttributeQName, (str != null) ? str : XMLSymbols.fCDATASymbol, paramAttributes.getValue(paramInt));
  }
  
  static final class ResolutionForwarder implements EntityResolver2 {
    private static final String XML_TYPE = "http://www.w3.org/TR/REC-xml";
    
    protected LSResourceResolver fEntityResolver;
    
    public ResolutionForwarder() {}
    
    public ResolutionForwarder(LSResourceResolver param1LSResourceResolver) {
      setEntityResolver(param1LSResourceResolver);
    }
    
    public void setEntityResolver(LSResourceResolver param1LSResourceResolver) {
      this.fEntityResolver = param1LSResourceResolver;
    }
    
    public LSResourceResolver getEntityResolver() {
      return this.fEntityResolver;
    }
    
    public InputSource getExternalSubset(String param1String1, String param1String2) throws SAXException, IOException {
      return null;
    }
    
    public InputSource resolveEntity(String param1String1, String param1String2, String param1String3, String param1String4) throws SAXException, IOException {
      if (this.fEntityResolver != null) {
        LSInput lSInput = this.fEntityResolver.resolveResource("http://www.w3.org/TR/REC-xml", null, param1String2, param1String4, param1String3);
        if (lSInput != null) {
          String str1 = lSInput.getPublicId();
          String str2 = lSInput.getSystemId();
          String str3 = lSInput.getBaseURI();
          Reader reader = lSInput.getCharacterStream();
          InputStream inputStream = lSInput.getByteStream();
          String str4 = lSInput.getStringData();
          String str5 = lSInput.getEncoding();
          InputSource inputSource = new InputSource();
          inputSource.setPublicId(str1);
          inputSource.setSystemId((str3 != null) ? resolveSystemId(str2, str3) : str2);
          if (reader != null) {
            inputSource.setCharacterStream(reader);
          } else if (inputStream != null) {
            inputSource.setByteStream(inputStream);
          } else if (str4 != null && str4.length() != 0) {
            inputSource.setCharacterStream(new StringReader(str4));
          } 
          inputSource.setEncoding(str5);
          return inputSource;
        } 
      } 
      return null;
    }
    
    public InputSource resolveEntity(String param1String1, String param1String2) throws SAXException, IOException {
      return resolveEntity(null, param1String1, null, param1String2);
    }
    
    private String resolveSystemId(String param1String1, String param1String2) {
      try {
        return XMLEntityManager.expandSystemId(param1String1, param1String2, false);
      } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
        return param1String1;
      } 
    }
  }
  
  private static class XMLSchemaTypeInfoProvider extends TypeInfoProvider {
    private Augmentations fElementAugs;
    
    private XMLAttributes fAttributes;
    
    private boolean fInStartElement = false;
    
    private XMLSchemaTypeInfoProvider() {}
    
    void beginStartElement(Augmentations param1Augmentations, XMLAttributes param1XMLAttributes) {
      this.fInStartElement = true;
      this.fElementAugs = param1Augmentations;
      this.fAttributes = param1XMLAttributes;
    }
    
    void finishStartElement() {
      this.fInStartElement = false;
      this.fElementAugs = null;
      this.fAttributes = null;
    }
    
    void beginEndElement(Augmentations param1Augmentations) {
      this.fElementAugs = param1Augmentations;
    }
    
    void finishEndElement() {
      this.fElementAugs = null;
    }
    
    private void checkState() {
      if (!this.fInStartElement)
        throw new IllegalStateException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "TypeInfoProviderIllegalState", null)); 
    }
    
    public TypeInfo getAttributeTypeInfo(int param1Int) {
      checkState();
      return getAttributeType(param1Int);
    }
    
    private TypeInfo getAttributeType(int param1Int) {
      checkState();
      if (param1Int < 0 || this.fAttributes.getLength() <= param1Int)
        throw new IndexOutOfBoundsException(Integer.toString(param1Int)); 
      Augmentations augmentations = this.fAttributes.getAugmentations(param1Int);
      if (augmentations == null)
        return null; 
      AttributePSVI attributePSVI = (AttributePSVI)augmentations.getItem("ATTRIBUTE_PSVI");
      return getTypeInfoFromPSVI((ItemPSVI)attributePSVI);
    }
    
    public TypeInfo getAttributeTypeInfo(String param1String1, String param1String2) {
      checkState();
      return getAttributeTypeInfo(this.fAttributes.getIndex(param1String1, param1String2));
    }
    
    public TypeInfo getAttributeTypeInfo(String param1String) {
      checkState();
      return getAttributeTypeInfo(this.fAttributes.getIndex(param1String));
    }
    
    public TypeInfo getElementTypeInfo() {
      checkState();
      if (this.fElementAugs == null)
        return null; 
      ElementPSVI elementPSVI = (ElementPSVI)this.fElementAugs.getItem("ELEMENT_PSVI");
      return getTypeInfoFromPSVI((ItemPSVI)elementPSVI);
    }
    
    private TypeInfo getTypeInfoFromPSVI(ItemPSVI param1ItemPSVI) {
      if (param1ItemPSVI == null)
        return null; 
      if (param1ItemPSVI.getValidity() == 2) {
        XSSimpleTypeDefinition xSSimpleTypeDefinition = param1ItemPSVI.getMemberTypeDefinition();
        if (xSSimpleTypeDefinition != null)
          return (xSSimpleTypeDefinition instanceof TypeInfo) ? (TypeInfo)xSSimpleTypeDefinition : null; 
      } 
      XSTypeDefinition xSTypeDefinition = param1ItemPSVI.getTypeDefinition();
      return (xSTypeDefinition != null) ? ((xSTypeDefinition instanceof TypeInfo) ? (TypeInfo)xSTypeDefinition : null) : null;
    }
    
    public boolean isIdAttribute(int param1Int) {
      checkState();
      XSSimpleType xSSimpleType = (XSSimpleType)getAttributeType(param1Int);
      return (xSSimpleType == null) ? false : xSSimpleType.isIDType();
    }
    
    public boolean isSpecified(int param1Int) {
      checkState();
      return this.fAttributes.isSpecified(param1Int);
    }
    
    ElementPSVI getElementPSVI() {
      return (this.fElementAugs != null) ? (ElementPSVI)this.fElementAugs.getItem("ELEMENT_PSVI") : null;
    }
    
    AttributePSVI getAttributePSVI(int param1Int) {
      if (this.fAttributes != null) {
        Augmentations augmentations = this.fAttributes.getAugmentations(param1Int);
        if (augmentations != null)
          return (AttributePSVI)augmentations.getItem("ATTRIBUTE_PSVI"); 
      } 
      return null;
    }
    
    AttributePSVI getAttributePSVIByName(String param1String1, String param1String2) {
      if (this.fAttributes != null) {
        Augmentations augmentations = this.fAttributes.getAugmentations(param1String1, param1String2);
        if (augmentations != null)
          return (AttributePSVI)augmentations.getItem("ATTRIBUTE_PSVI"); 
      } 
      return null;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\ValidatorHandlerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */