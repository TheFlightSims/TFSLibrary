package org.apache.xerces.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.apache.xerces.impl.XML11DTDScannerImpl;
import org.apache.xerces.impl.XML11DocumentScannerImpl;
import org.apache.xerces.impl.XML11NSDocumentScannerImpl;
import org.apache.xerces.impl.XMLDTDScannerImpl;
import org.apache.xerces.impl.XMLDocumentScannerImpl;
import org.apache.xerces.impl.XMLEntityHandler;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.XMLNSDocumentScannerImpl;
import org.apache.xerces.impl.XMLVersionDetector;
import org.apache.xerces.impl.dtd.XML11DTDProcessor;
import org.apache.xerces.impl.dtd.XML11DTDValidator;
import org.apache.xerces.impl.dtd.XML11NSDTDValidator;
import org.apache.xerces.impl.dtd.XMLDTDProcessor;
import org.apache.xerces.impl.dtd.XMLDTDValidator;
import org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;
import org.apache.xerces.impl.dtd.XMLNSDTDValidator;
import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.util.ParserConfigurationSettings;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLPullParserConfiguration;

public class XML11DTDConfiguration extends ParserConfigurationSettings implements XMLPullParserConfiguration, XML11Configurable {
  protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "org.apache.xerces.impl.dv.dtd.XML11DTDDVFactoryImpl";
  
  protected static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
  
  protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
  
  protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
  
  protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
  
  protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
  
  protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
  
  protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
  
  protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
  
  protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  protected static final String DTD_PROCESSOR = "http://apache.org/xml/properties/internal/dtd-processor";
  
  protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
  
  protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
  
  protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
  
  protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  
  protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
  
  protected static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
  
  protected SymbolTable fSymbolTable;
  
  protected XMLInputSource fInputSource;
  
  protected ValidationManager fValidationManager;
  
  protected XMLVersionDetector fVersionDetector;
  
  protected XMLLocator fLocator;
  
  protected Locale fLocale;
  
  protected ArrayList fComponents = new ArrayList();
  
  protected ArrayList fXML11Components = null;
  
  protected ArrayList fCommonComponents = null;
  
  protected XMLDocumentHandler fDocumentHandler;
  
  protected XMLDTDHandler fDTDHandler;
  
  protected XMLDTDContentModelHandler fDTDContentModelHandler;
  
  protected XMLDocumentSource fLastComponent;
  
  protected boolean fParseInProgress = false;
  
  protected boolean fConfigUpdated = false;
  
  protected DTDDVFactory fDatatypeValidatorFactory;
  
  protected XMLNSDocumentScannerImpl fNamespaceScanner;
  
  protected XMLDocumentScannerImpl fNonNSScanner;
  
  protected XMLDTDValidator fDTDValidator;
  
  protected XMLDTDValidator fNonNSDTDValidator;
  
  protected XMLDTDScanner fDTDScanner;
  
  protected XMLDTDProcessor fDTDProcessor;
  
  protected DTDDVFactory fXML11DatatypeFactory = null;
  
  protected XML11NSDocumentScannerImpl fXML11NSDocScanner = null;
  
  protected XML11DocumentScannerImpl fXML11DocScanner = null;
  
  protected XML11NSDTDValidator fXML11NSDTDValidator = null;
  
  protected XML11DTDValidator fXML11DTDValidator = null;
  
  protected XML11DTDScannerImpl fXML11DTDScanner = null;
  
  protected XML11DTDProcessor fXML11DTDProcessor = null;
  
  protected XMLGrammarPool fGrammarPool;
  
  protected XMLErrorReporter fErrorReporter;
  
  protected XMLEntityManager fEntityManager;
  
  protected XMLDocumentScanner fCurrentScanner;
  
  protected DTDDVFactory fCurrentDVFactory;
  
  protected XMLDTDScanner fCurrentDTDScanner;
  
  private boolean f11Initialized = false;
  
  public XML11DTDConfiguration() {
    this(null, null, null);
  }
  
  public XML11DTDConfiguration(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, null, null);
  }
  
  public XML11DTDConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this(paramSymbolTable, paramXMLGrammarPool, null);
  }
  
  public XML11DTDConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool, XMLComponentManager paramXMLComponentManager) {
    super(paramXMLComponentManager);
    this.fXML11Components = new ArrayList();
    this.fCommonComponents = new ArrayList();
    this.fRecognizedFeatures = new ArrayList();
    this.fRecognizedProperties = new ArrayList();
    this.fFeatures = new HashMap();
    this.fProperties = new HashMap();
    String[] arrayOfString1 = { "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/nonvalidating/load-external-dtd", "http://xml.org/sax/features/validation", "http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/external-general-entities", "http://xml.org/sax/features/external-parameter-entities", "http://apache.org/xml/features/internal/parser-settings" };
    addRecognizedFeatures(arrayOfString1);
    this.fFeatures.put("http://xml.org/sax/features/validation", Boolean.FALSE);
    this.fFeatures.put("http://xml.org/sax/features/namespaces", Boolean.TRUE);
    this.fFeatures.put("http://xml.org/sax/features/external-general-entities", Boolean.TRUE);
    this.fFeatures.put("http://xml.org/sax/features/external-parameter-entities", Boolean.TRUE);
    this.fFeatures.put("http://apache.org/xml/features/continue-after-fatal-error", Boolean.FALSE);
    this.fFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", Boolean.TRUE);
    this.fFeatures.put("http://apache.org/xml/features/internal/parser-settings", Boolean.TRUE);
    String[] arrayOfString2 = { 
        "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/document-scanner", "http://apache.org/xml/properties/internal/dtd-scanner", "http://apache.org/xml/properties/internal/dtd-processor", "http://apache.org/xml/properties/internal/validator/dtd", "http://apache.org/xml/properties/internal/datatype-validator-factory", 
        "http://apache.org/xml/properties/internal/validation-manager", "http://xml.org/sax/properties/xml-string", "http://apache.org/xml/properties/internal/grammar-pool", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage" };
    addRecognizedProperties(arrayOfString2);
    if (paramSymbolTable == null)
      paramSymbolTable = new SymbolTable(); 
    this.fSymbolTable = paramSymbolTable;
    this.fProperties.put("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
    this.fGrammarPool = paramXMLGrammarPool;
    if (this.fGrammarPool != null)
      this.fProperties.put("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool); 
    this.fEntityManager = new XMLEntityManager();
    this.fProperties.put("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager);
    addCommonComponent((XMLComponent)this.fEntityManager);
    this.fErrorReporter = new XMLErrorReporter();
    this.fErrorReporter.setDocumentLocator((XMLLocator)this.fEntityManager.getEntityScanner());
    this.fProperties.put("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
    addCommonComponent((XMLComponent)this.fErrorReporter);
    this.fNamespaceScanner = new XMLNSDocumentScannerImpl();
    this.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNamespaceScanner);
    addComponent((XMLComponent)this.fNamespaceScanner);
    this.fDTDScanner = (XMLDTDScanner)new XMLDTDScannerImpl();
    this.fProperties.put("http://apache.org/xml/properties/internal/dtd-scanner", this.fDTDScanner);
    addComponent((XMLComponent)this.fDTDScanner);
    this.fDTDProcessor = new XMLDTDProcessor();
    this.fProperties.put("http://apache.org/xml/properties/internal/dtd-processor", this.fDTDProcessor);
    addComponent((XMLComponent)this.fDTDProcessor);
    this.fDTDValidator = (XMLDTDValidator)new XMLNSDTDValidator();
    this.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", this.fDTDValidator);
    addComponent((XMLComponent)this.fDTDValidator);
    this.fDatatypeValidatorFactory = DTDDVFactory.getInstance();
    this.fProperties.put("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fDatatypeValidatorFactory);
    this.fValidationManager = new ValidationManager();
    this.fProperties.put("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager);
    this.fVersionDetector = new XMLVersionDetector();
    if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
      XMLMessageFormatter xMLMessageFormatter = new XMLMessageFormatter();
      this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", (MessageFormatter)xMLMessageFormatter);
      this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", (MessageFormatter)xMLMessageFormatter);
    } 
    try {
      setLocale(Locale.getDefault());
    } catch (XNIException xNIException) {}
    this.fConfigUpdated = false;
  }
  
  public void setInputSource(XMLInputSource paramXMLInputSource) throws XMLConfigurationException, IOException {
    this.fInputSource = paramXMLInputSource;
  }
  
  public void setLocale(Locale paramLocale) throws XNIException {
    this.fLocale = paramLocale;
    this.fErrorReporter.setLocale(paramLocale);
  }
  
  public void setDocumentHandler(XMLDocumentHandler paramXMLDocumentHandler) {
    this.fDocumentHandler = paramXMLDocumentHandler;
    if (this.fLastComponent != null) {
      this.fLastComponent.setDocumentHandler(this.fDocumentHandler);
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.setDocumentSource(this.fLastComponent); 
    } 
  }
  
  public XMLDocumentHandler getDocumentHandler() {
    return this.fDocumentHandler;
  }
  
  public void setDTDHandler(XMLDTDHandler paramXMLDTDHandler) {
    this.fDTDHandler = paramXMLDTDHandler;
  }
  
  public XMLDTDHandler getDTDHandler() {
    return this.fDTDHandler;
  }
  
  public void setDTDContentModelHandler(XMLDTDContentModelHandler paramXMLDTDContentModelHandler) {
    this.fDTDContentModelHandler = paramXMLDTDContentModelHandler;
  }
  
  public XMLDTDContentModelHandler getDTDContentModelHandler() {
    return this.fDTDContentModelHandler;
  }
  
  public void setEntityResolver(XMLEntityResolver paramXMLEntityResolver) {
    this.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", paramXMLEntityResolver);
  }
  
  public XMLEntityResolver getEntityResolver() {
    return (XMLEntityResolver)this.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
  }
  
  public void setErrorHandler(XMLErrorHandler paramXMLErrorHandler) {
    this.fProperties.put("http://apache.org/xml/properties/internal/error-handler", paramXMLErrorHandler);
  }
  
  public XMLErrorHandler getErrorHandler() {
    return (XMLErrorHandler)this.fProperties.get("http://apache.org/xml/properties/internal/error-handler");
  }
  
  public void cleanup() {
    this.fEntityManager.closeReaders();
  }
  
  public void parse(XMLInputSource paramXMLInputSource) throws XNIException, IOException {
    if (this.fParseInProgress)
      throw new XNIException("FWK005 parse may not be called while parsing."); 
    this.fParseInProgress = true;
    try {
      setInputSource(paramXMLInputSource);
      parse(true);
    } catch (XNIException xNIException) {
      throw xNIException;
    } catch (IOException iOException) {
      throw iOException;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (Exception exception) {
      throw new XNIException(exception);
    } finally {
      this.fParseInProgress = false;
      cleanup();
    } 
  }
  
  public boolean parse(boolean paramBoolean) throws XNIException, IOException {
    if (this.fInputSource != null)
      try {
        this.fValidationManager.reset();
        this.fVersionDetector.reset((XMLComponentManager)this);
        resetCommon();
        short s = this.fVersionDetector.determineDocVersion(this.fInputSource);
        if (s == 1) {
          configurePipeline();
          reset();
        } else if (s == 2) {
          initXML11Components();
          configureXML11Pipeline();
          resetXML11();
        } else {
          return false;
        } 
        this.fConfigUpdated = false;
        this.fVersionDetector.startDocumentParsing((XMLEntityHandler)this.fCurrentScanner, s);
        this.fInputSource = null;
      } catch (XNIException xNIException) {
        throw xNIException;
      } catch (IOException iOException) {
        throw iOException;
      } catch (RuntimeException runtimeException) {
        throw runtimeException;
      } catch (Exception exception) {
        throw new XNIException(exception);
      }  
    try {
      return this.fCurrentScanner.scanDocument(paramBoolean);
    } catch (XNIException xNIException) {
      throw xNIException;
    } catch (IOException iOException) {
      throw iOException;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (Exception exception) {
      throw new XNIException(exception);
    } 
  }
  
  public boolean getFeature(String paramString) throws XMLConfigurationException {
    return paramString.equals("http://apache.org/xml/features/internal/parser-settings") ? this.fConfigUpdated : super.getFeature(paramString);
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    this.fConfigUpdated = true;
    int i = this.fComponents.size();
    for (byte b1 = 0; b1 < i; b1++) {
      XMLComponent xMLComponent = this.fComponents.get(b1);
      xMLComponent.setFeature(paramString, paramBoolean);
    } 
    i = this.fCommonComponents.size();
    for (byte b2 = 0; b2 < i; b2++) {
      XMLComponent xMLComponent = this.fCommonComponents.get(b2);
      xMLComponent.setFeature(paramString, paramBoolean);
    } 
    i = this.fXML11Components.size();
    for (byte b3 = 0; b3 < i; b3++) {
      XMLComponent xMLComponent = this.fXML11Components.get(b3);
      try {
        xMLComponent.setFeature(paramString, paramBoolean);
      } catch (Exception exception) {}
    } 
    super.setFeature(paramString, paramBoolean);
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    this.fConfigUpdated = true;
    int i = this.fComponents.size();
    for (byte b1 = 0; b1 < i; b1++) {
      XMLComponent xMLComponent = this.fComponents.get(b1);
      xMLComponent.setProperty(paramString, paramObject);
    } 
    i = this.fCommonComponents.size();
    for (byte b2 = 0; b2 < i; b2++) {
      XMLComponent xMLComponent = this.fCommonComponents.get(b2);
      xMLComponent.setProperty(paramString, paramObject);
    } 
    i = this.fXML11Components.size();
    for (byte b3 = 0; b3 < i; b3++) {
      XMLComponent xMLComponent = this.fXML11Components.get(b3);
      try {
        xMLComponent.setProperty(paramString, paramObject);
      } catch (Exception exception) {}
    } 
    super.setProperty(paramString, paramObject);
  }
  
  public Locale getLocale() {
    return this.fLocale;
  }
  
  protected void reset() throws XNIException {
    int i = this.fComponents.size();
    for (byte b = 0; b < i; b++) {
      XMLComponent xMLComponent = this.fComponents.get(b);
      xMLComponent.reset((XMLComponentManager)this);
    } 
  }
  
  protected void resetCommon() throws XNIException {
    int i = this.fCommonComponents.size();
    for (byte b = 0; b < i; b++) {
      XMLComponent xMLComponent = this.fCommonComponents.get(b);
      xMLComponent.reset((XMLComponentManager)this);
    } 
  }
  
  protected void resetXML11() throws XNIException {
    int i = this.fXML11Components.size();
    for (byte b = 0; b < i; b++) {
      XMLComponent xMLComponent = this.fXML11Components.get(b);
      xMLComponent.reset((XMLComponentManager)this);
    } 
  }
  
  protected void configureXML11Pipeline() {
    if (this.fCurrentDVFactory != this.fXML11DatatypeFactory) {
      this.fCurrentDVFactory = this.fXML11DatatypeFactory;
      setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fCurrentDVFactory);
    } 
    if (this.fCurrentDTDScanner != this.fXML11DTDScanner) {
      this.fCurrentDTDScanner = (XMLDTDScanner)this.fXML11DTDScanner;
      setProperty("http://apache.org/xml/properties/internal/dtd-scanner", this.fCurrentDTDScanner);
      setProperty("http://apache.org/xml/properties/internal/dtd-processor", this.fXML11DTDProcessor);
    } 
    this.fXML11DTDScanner.setDTDHandler((XMLDTDHandler)this.fXML11DTDProcessor);
    this.fXML11DTDProcessor.setDTDSource((XMLDTDSource)this.fXML11DTDScanner);
    this.fXML11DTDProcessor.setDTDHandler(this.fDTDHandler);
    if (this.fDTDHandler != null)
      this.fDTDHandler.setDTDSource((XMLDTDSource)this.fXML11DTDProcessor); 
    this.fXML11DTDScanner.setDTDContentModelHandler((XMLDTDContentModelHandler)this.fXML11DTDProcessor);
    this.fXML11DTDProcessor.setDTDContentModelSource((XMLDTDContentModelSource)this.fXML11DTDScanner);
    this.fXML11DTDProcessor.setDTDContentModelHandler(this.fDTDContentModelHandler);
    if (this.fDTDContentModelHandler != null)
      this.fDTDContentModelHandler.setDTDContentModelSource((XMLDTDContentModelSource)this.fXML11DTDProcessor); 
    if (this.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
      if (this.fCurrentScanner != this.fXML11NSDocScanner) {
        this.fCurrentScanner = (XMLDocumentScanner)this.fXML11NSDocScanner;
        setProperty("http://apache.org/xml/properties/internal/document-scanner", this.fXML11NSDocScanner);
        setProperty("http://apache.org/xml/properties/internal/validator/dtd", this.fXML11NSDTDValidator);
      } 
      this.fXML11NSDocScanner.setDTDValidator((XMLDTDValidatorFilter)this.fXML11NSDTDValidator);
      this.fXML11NSDocScanner.setDocumentHandler((XMLDocumentHandler)this.fXML11NSDTDValidator);
      this.fXML11NSDTDValidator.setDocumentSource((XMLDocumentSource)this.fXML11NSDocScanner);
      this.fXML11NSDTDValidator.setDocumentHandler(this.fDocumentHandler);
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fXML11NSDTDValidator); 
      this.fLastComponent = (XMLDocumentSource)this.fXML11NSDTDValidator;
    } else {
      if (this.fXML11DocScanner == null) {
        this.fXML11DocScanner = new XML11DocumentScannerImpl();
        addXML11Component((XMLComponent)this.fXML11DocScanner);
        this.fXML11DTDValidator = new XML11DTDValidator();
        addXML11Component((XMLComponent)this.fXML11DTDValidator);
      } 
      if (this.fCurrentScanner != this.fXML11DocScanner) {
        this.fCurrentScanner = (XMLDocumentScanner)this.fXML11DocScanner;
        setProperty("http://apache.org/xml/properties/internal/document-scanner", this.fXML11DocScanner);
        setProperty("http://apache.org/xml/properties/internal/validator/dtd", this.fXML11DTDValidator);
      } 
      this.fXML11DocScanner.setDocumentHandler((XMLDocumentHandler)this.fXML11DTDValidator);
      this.fXML11DTDValidator.setDocumentSource((XMLDocumentSource)this.fXML11DocScanner);
      this.fXML11DTDValidator.setDocumentHandler(this.fDocumentHandler);
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fXML11DTDValidator); 
      this.fLastComponent = (XMLDocumentSource)this.fXML11DTDValidator;
    } 
  }
  
  protected void configurePipeline() {
    if (this.fCurrentDVFactory != this.fDatatypeValidatorFactory) {
      this.fCurrentDVFactory = this.fDatatypeValidatorFactory;
      setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fCurrentDVFactory);
    } 
    if (this.fCurrentDTDScanner != this.fDTDScanner) {
      this.fCurrentDTDScanner = this.fDTDScanner;
      setProperty("http://apache.org/xml/properties/internal/dtd-scanner", this.fCurrentDTDScanner);
      setProperty("http://apache.org/xml/properties/internal/dtd-processor", this.fDTDProcessor);
    } 
    this.fDTDScanner.setDTDHandler((XMLDTDHandler)this.fDTDProcessor);
    this.fDTDProcessor.setDTDSource((XMLDTDSource)this.fDTDScanner);
    this.fDTDProcessor.setDTDHandler(this.fDTDHandler);
    if (this.fDTDHandler != null)
      this.fDTDHandler.setDTDSource((XMLDTDSource)this.fDTDProcessor); 
    this.fDTDScanner.setDTDContentModelHandler((XMLDTDContentModelHandler)this.fDTDProcessor);
    this.fDTDProcessor.setDTDContentModelSource((XMLDTDContentModelSource)this.fDTDScanner);
    this.fDTDProcessor.setDTDContentModelHandler(this.fDTDContentModelHandler);
    if (this.fDTDContentModelHandler != null)
      this.fDTDContentModelHandler.setDTDContentModelSource((XMLDTDContentModelSource)this.fDTDProcessor); 
    if (this.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
      if (this.fCurrentScanner != this.fNamespaceScanner) {
        this.fCurrentScanner = (XMLDocumentScanner)this.fNamespaceScanner;
        setProperty("http://apache.org/xml/properties/internal/document-scanner", this.fNamespaceScanner);
        setProperty("http://apache.org/xml/properties/internal/validator/dtd", this.fDTDValidator);
      } 
      this.fNamespaceScanner.setDTDValidator((XMLDTDValidatorFilter)this.fDTDValidator);
      this.fNamespaceScanner.setDocumentHandler((XMLDocumentHandler)this.fDTDValidator);
      this.fDTDValidator.setDocumentSource((XMLDocumentSource)this.fNamespaceScanner);
      this.fDTDValidator.setDocumentHandler(this.fDocumentHandler);
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fDTDValidator); 
      this.fLastComponent = (XMLDocumentSource)this.fDTDValidator;
    } else {
      if (this.fNonNSScanner == null) {
        this.fNonNSScanner = new XMLDocumentScannerImpl();
        this.fNonNSDTDValidator = new XMLDTDValidator();
        addComponent((XMLComponent)this.fNonNSScanner);
        addComponent((XMLComponent)this.fNonNSDTDValidator);
      } 
      if (this.fCurrentScanner != this.fNonNSScanner) {
        this.fCurrentScanner = (XMLDocumentScanner)this.fNonNSScanner;
        setProperty("http://apache.org/xml/properties/internal/document-scanner", this.fNonNSScanner);
        setProperty("http://apache.org/xml/properties/internal/validator/dtd", this.fNonNSDTDValidator);
      } 
      this.fNonNSScanner.setDocumentHandler((XMLDocumentHandler)this.fNonNSDTDValidator);
      this.fNonNSDTDValidator.setDocumentSource((XMLDocumentSource)this.fNonNSScanner);
      this.fNonNSDTDValidator.setDocumentHandler(this.fDocumentHandler);
      if (this.fDocumentHandler != null)
        this.fDocumentHandler.setDocumentSource((XMLDocumentSource)this.fNonNSDTDValidator); 
      this.fLastComponent = (XMLDocumentSource)this.fNonNSDTDValidator;
    } 
  }
  
  protected void checkFeature(String paramString) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/features/")) {
      int i = paramString.length() - "http://apache.org/xml/features/".length();
      if (i == "validation/dynamic".length() && paramString.endsWith("validation/dynamic"))
        return; 
      if (i == "validation/default-attribute-values".length() && paramString.endsWith("validation/default-attribute-values")) {
        boolean bool = true;
        throw new XMLConfigurationException(bool, paramString);
      } 
      if (i == "validation/validate-content-models".length() && paramString.endsWith("validation/validate-content-models")) {
        boolean bool = true;
        throw new XMLConfigurationException(bool, paramString);
      } 
      if (i == "nonvalidating/load-dtd-grammar".length() && paramString.endsWith("nonvalidating/load-dtd-grammar"))
        return; 
      if (i == "nonvalidating/load-external-dtd".length() && paramString.endsWith("nonvalidating/load-external-dtd"))
        return; 
      if (i == "validation/validate-datatypes".length() && paramString.endsWith("validation/validate-datatypes")) {
        boolean bool = true;
        throw new XMLConfigurationException(bool, paramString);
      } 
      if (i == "internal/parser-settings".length() && paramString.endsWith("internal/parser-settings")) {
        boolean bool = true;
        throw new XMLConfigurationException(bool, paramString);
      } 
    } 
    super.checkFeature(paramString);
  }
  
  protected void checkProperty(String paramString) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/properties/")) {
      int i = paramString.length() - "http://apache.org/xml/properties/".length();
      if (i == "internal/dtd-scanner".length() && paramString.endsWith("internal/dtd-scanner"))
        return; 
    } 
    if (paramString.startsWith("http://xml.org/sax/properties/")) {
      int i = paramString.length() - "http://xml.org/sax/properties/".length();
      if (i == "xml-string".length() && paramString.endsWith("xml-string")) {
        boolean bool = true;
        throw new XMLConfigurationException(bool, paramString);
      } 
    } 
    super.checkProperty(paramString);
  }
  
  protected void addComponent(XMLComponent paramXMLComponent) {
    if (this.fComponents.contains(paramXMLComponent))
      return; 
    this.fComponents.add(paramXMLComponent);
    addRecognizedParamsAndSetDefaults(paramXMLComponent);
  }
  
  protected void addCommonComponent(XMLComponent paramXMLComponent) {
    if (this.fCommonComponents.contains(paramXMLComponent))
      return; 
    this.fCommonComponents.add(paramXMLComponent);
    addRecognizedParamsAndSetDefaults(paramXMLComponent);
  }
  
  protected void addXML11Component(XMLComponent paramXMLComponent) {
    if (this.fXML11Components.contains(paramXMLComponent))
      return; 
    this.fXML11Components.add(paramXMLComponent);
    addRecognizedParamsAndSetDefaults(paramXMLComponent);
  }
  
  protected void addRecognizedParamsAndSetDefaults(XMLComponent paramXMLComponent) {
    String[] arrayOfString1 = paramXMLComponent.getRecognizedFeatures();
    addRecognizedFeatures(arrayOfString1);
    String[] arrayOfString2 = paramXMLComponent.getRecognizedProperties();
    addRecognizedProperties(arrayOfString2);
    if (arrayOfString1 != null)
      for (byte b = 0; b < arrayOfString1.length; b++) {
        String str = arrayOfString1[b];
        Boolean bool = paramXMLComponent.getFeatureDefault(str);
        if (bool != null && !this.fFeatures.containsKey(str)) {
          this.fFeatures.put(str, bool);
          this.fConfigUpdated = true;
        } 
      }  
    if (arrayOfString2 != null)
      for (byte b = 0; b < arrayOfString2.length; b++) {
        String str = arrayOfString2[b];
        Object object = paramXMLComponent.getPropertyDefault(str);
        if (object != null && !this.fProperties.containsKey(str)) {
          this.fProperties.put(str, object);
          this.fConfigUpdated = true;
        } 
      }  
  }
  
  private void initXML11Components() {
    if (!this.f11Initialized) {
      this.fXML11DatatypeFactory = DTDDVFactory.getInstance("org.apache.xerces.impl.dv.dtd.XML11DTDDVFactoryImpl");
      this.fXML11DTDScanner = new XML11DTDScannerImpl();
      addXML11Component((XMLComponent)this.fXML11DTDScanner);
      this.fXML11DTDProcessor = new XML11DTDProcessor();
      addXML11Component((XMLComponent)this.fXML11DTDProcessor);
      this.fXML11NSDocScanner = new XML11NSDocumentScannerImpl();
      addXML11Component((XMLComponent)this.fXML11NSDocScanner);
      this.fXML11NSDTDValidator = new XML11NSDTDValidator();
      addXML11Component((XMLComponent)this.fXML11NSDTDValidator);
      this.f11Initialized = true;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\XML11DTDConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */