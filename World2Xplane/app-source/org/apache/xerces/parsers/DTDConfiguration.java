package org.apache.xerces.parsers;

import java.io.IOException;
import java.util.Locale;
import org.apache.xerces.impl.XMLDTDScannerImpl;
import org.apache.xerces.impl.XMLDocumentScannerImpl;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.XMLNamespaceBinder;
import org.apache.xerces.impl.dtd.XMLDTDProcessor;
import org.apache.xerces.impl.dtd.XMLDTDValidator;
import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.util.MessageFormatter;
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
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLPullParserConfiguration;

public class DTDConfiguration extends BasicParserConfiguration implements XMLPullParserConfiguration {
  protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
  
  protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
  
  protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
  
  protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
  
  protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
  
  protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
  
  protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
  
  protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
  
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
  
  protected XMLGrammarPool fGrammarPool;
  
  protected DTDDVFactory fDatatypeValidatorFactory;
  
  protected XMLErrorReporter fErrorReporter;
  
  protected XMLEntityManager fEntityManager;
  
  protected XMLDocumentScanner fScanner;
  
  protected XMLInputSource fInputSource;
  
  protected XMLDTDScanner fDTDScanner;
  
  protected XMLDTDProcessor fDTDProcessor;
  
  protected XMLDTDValidator fDTDValidator;
  
  protected XMLNamespaceBinder fNamespaceBinder;
  
  protected ValidationManager fValidationManager;
  
  protected XMLLocator fLocator;
  
  protected boolean fParseInProgress = false;
  
  public DTDConfiguration() {
    this(null, null, null);
  }
  
  public DTDConfiguration(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, null, null);
  }
  
  public DTDConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this(paramSymbolTable, paramXMLGrammarPool, null);
  }
  
  public DTDConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool, XMLComponentManager paramXMLComponentManager) {
    super(paramSymbolTable, paramXMLComponentManager);
    String[] arrayOfString1 = { "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/nonvalidating/load-external-dtd" };
    addRecognizedFeatures(arrayOfString1);
    setFeature("http://apache.org/xml/features/continue-after-fatal-error", false);
    setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);
    String[] arrayOfString2 = { 
        "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/document-scanner", "http://apache.org/xml/properties/internal/dtd-scanner", "http://apache.org/xml/properties/internal/dtd-processor", "http://apache.org/xml/properties/internal/validator/dtd", "http://apache.org/xml/properties/internal/namespace-binder", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/datatype-validator-factory", "http://apache.org/xml/properties/internal/validation-manager", 
        "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage" };
    addRecognizedProperties(arrayOfString2);
    this.fGrammarPool = paramXMLGrammarPool;
    if (this.fGrammarPool != null)
      setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool); 
    this.fEntityManager = createEntityManager();
    setProperty("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager);
    addComponent((XMLComponent)this.fEntityManager);
    this.fErrorReporter = createErrorReporter();
    this.fErrorReporter.setDocumentLocator((XMLLocator)this.fEntityManager.getEntityScanner());
    setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
    addComponent((XMLComponent)this.fErrorReporter);
    this.fScanner = createDocumentScanner();
    setProperty("http://apache.org/xml/properties/internal/document-scanner", this.fScanner);
    if (this.fScanner instanceof XMLComponent)
      addComponent((XMLComponent)this.fScanner); 
    this.fDTDScanner = createDTDScanner();
    if (this.fDTDScanner != null) {
      setProperty("http://apache.org/xml/properties/internal/dtd-scanner", this.fDTDScanner);
      if (this.fDTDScanner instanceof XMLComponent)
        addComponent((XMLComponent)this.fDTDScanner); 
    } 
    this.fDTDProcessor = createDTDProcessor();
    if (this.fDTDProcessor != null) {
      setProperty("http://apache.org/xml/properties/internal/dtd-processor", this.fDTDProcessor);
      addComponent((XMLComponent)this.fDTDProcessor);
    } 
    this.fDTDValidator = createDTDValidator();
    if (this.fDTDValidator != null) {
      setProperty("http://apache.org/xml/properties/internal/validator/dtd", this.fDTDValidator);
      addComponent((XMLComponent)this.fDTDValidator);
    } 
    this.fNamespaceBinder = createNamespaceBinder();
    if (this.fNamespaceBinder != null) {
      setProperty("http://apache.org/xml/properties/internal/namespace-binder", this.fNamespaceBinder);
      addComponent((XMLComponent)this.fNamespaceBinder);
    } 
    this.fDatatypeValidatorFactory = createDatatypeValidatorFactory();
    if (this.fDatatypeValidatorFactory != null)
      setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fDatatypeValidatorFactory); 
    this.fValidationManager = createValidationManager();
    if (this.fValidationManager != null)
      setProperty("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager); 
    if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
      XMLMessageFormatter xMLMessageFormatter = new XMLMessageFormatter();
      this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", (MessageFormatter)xMLMessageFormatter);
      this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", (MessageFormatter)xMLMessageFormatter);
    } 
    try {
      setLocale(Locale.getDefault());
    } catch (XNIException xNIException) {}
  }
  
  public void setLocale(Locale paramLocale) throws XNIException {
    super.setLocale(paramLocale);
    this.fErrorReporter.setLocale(paramLocale);
  }
  
  public void setInputSource(XMLInputSource paramXMLInputSource) throws XMLConfigurationException, IOException {
    this.fInputSource = paramXMLInputSource;
  }
  
  public boolean parse(boolean paramBoolean) throws XNIException, IOException {
    if (this.fInputSource != null)
      try {
        reset();
        this.fScanner.setInputSource(this.fInputSource);
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
      return this.fScanner.scanDocument(paramBoolean);
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
  
  protected void reset() throws XNIException {
    if (this.fValidationManager != null)
      this.fValidationManager.reset(); 
    configurePipeline();
    super.reset();
  }
  
  protected void configurePipeline() {
    if (this.fDTDValidator != null) {
      this.fScanner.setDocumentHandler((XMLDocumentHandler)this.fDTDValidator);
      if (this.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
        this.fDTDValidator.setDocumentHandler((XMLDocumentHandler)this.fNamespaceBinder);
        this.fDTDValidator.setDocumentSource((XMLDocumentSource)this.fScanner);
        this.fNamespaceBinder.setDocumentHandler(this.fDocumentHandler);
        this.fNamespaceBinder.setDocumentSource((XMLDocumentSource)this.fDTDValidator);
        this.fLastComponent = (XMLDocumentSource)this.fNamespaceBinder;
      } else {
        this.fDTDValidator.setDocumentHandler(this.fDocumentHandler);
        this.fDTDValidator.setDocumentSource((XMLDocumentSource)this.fScanner);
        this.fLastComponent = (XMLDocumentSource)this.fDTDValidator;
      } 
    } else if (this.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
      this.fScanner.setDocumentHandler((XMLDocumentHandler)this.fNamespaceBinder);
      this.fNamespaceBinder.setDocumentHandler(this.fDocumentHandler);
      this.fNamespaceBinder.setDocumentSource((XMLDocumentSource)this.fScanner);
      this.fLastComponent = (XMLDocumentSource)this.fNamespaceBinder;
    } else {
      this.fScanner.setDocumentHandler(this.fDocumentHandler);
      this.fLastComponent = (XMLDocumentSource)this.fScanner;
    } 
    configureDTDPipeline();
  }
  
  protected void configureDTDPipeline() {
    if (this.fDTDScanner != null) {
      this.fProperties.put("http://apache.org/xml/properties/internal/dtd-scanner", this.fDTDScanner);
      if (this.fDTDProcessor != null) {
        this.fProperties.put("http://apache.org/xml/properties/internal/dtd-processor", this.fDTDProcessor);
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
      } else {
        this.fDTDScanner.setDTDHandler(this.fDTDHandler);
        if (this.fDTDHandler != null)
          this.fDTDHandler.setDTDSource((XMLDTDSource)this.fDTDScanner); 
        this.fDTDScanner.setDTDContentModelHandler(this.fDTDContentModelHandler);
        if (this.fDTDContentModelHandler != null)
          this.fDTDContentModelHandler.setDTDContentModelSource((XMLDTDContentModelSource)this.fDTDScanner); 
      } 
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
    } 
    super.checkFeature(paramString);
  }
  
  protected void checkProperty(String paramString) throws XMLConfigurationException {
    if (paramString.startsWith("http://apache.org/xml/properties/")) {
      int i = paramString.length() - "http://apache.org/xml/properties/".length();
      if (i == "internal/dtd-scanner".length() && paramString.endsWith("internal/dtd-scanner"))
        return; 
    } 
    super.checkProperty(paramString);
  }
  
  protected XMLEntityManager createEntityManager() {
    return new XMLEntityManager();
  }
  
  protected XMLErrorReporter createErrorReporter() {
    return new XMLErrorReporter();
  }
  
  protected XMLDocumentScanner createDocumentScanner() {
    return (XMLDocumentScanner)new XMLDocumentScannerImpl();
  }
  
  protected XMLDTDScanner createDTDScanner() {
    return (XMLDTDScanner)new XMLDTDScannerImpl();
  }
  
  protected XMLDTDProcessor createDTDProcessor() {
    return new XMLDTDProcessor();
  }
  
  protected XMLDTDValidator createDTDValidator() {
    return new XMLDTDValidator();
  }
  
  protected XMLNamespaceBinder createNamespaceBinder() {
    return new XMLNamespaceBinder();
  }
  
  protected DTDDVFactory createDatatypeValidatorFactory() {
    return DTDDVFactory.getInstance();
  }
  
  protected ValidationManager createValidationManager() {
    return new ValidationManager();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\DTDConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */