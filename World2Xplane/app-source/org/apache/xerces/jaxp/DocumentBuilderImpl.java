package org.apache.xerces.jaxp;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.validation.Schema;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class DocumentBuilderImpl extends DocumentBuilder implements JAXPConstants {
  private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
  
  private static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
  
  private static final String CREATE_ENTITY_REF_NODES_FEATURE = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
  
  private static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
  
  private static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
  
  private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
  
  private static final String XMLSCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
  
  private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
  
  private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private DOMParser domParser = null;
  
  private final Schema grammar;
  
  private XMLComponent fSchemaValidator;
  
  private XMLComponentManager fSchemaValidatorComponentManager;
  
  private ValidationManager fSchemaValidationManager;
  
  private UnparsedEntityHandler fUnparsedEntityHandler;
  
  private final ErrorHandler fInitErrorHandler;
  
  private final EntityResolver fInitEntityResolver;
  
  DocumentBuilderImpl(DocumentBuilderFactoryImpl paramDocumentBuilderFactoryImpl, Hashtable paramHashtable1, Hashtable paramHashtable2) throws SAXNotRecognizedException, SAXNotSupportedException {
    this(paramDocumentBuilderFactoryImpl, paramHashtable1, paramHashtable2, false);
  }
  
  DocumentBuilderImpl(DocumentBuilderFactoryImpl paramDocumentBuilderFactoryImpl, Hashtable paramHashtable1, Hashtable paramHashtable2, boolean paramBoolean) throws SAXNotRecognizedException, SAXNotSupportedException {
    this.domParser = new DOMParser();
    if (paramDocumentBuilderFactoryImpl.isValidating()) {
      this.fInitErrorHandler = new DefaultValidationErrorHandler();
      setErrorHandler(this.fInitErrorHandler);
    } else {
      this.fInitErrorHandler = this.domParser.getErrorHandler();
    } 
    this.domParser.setFeature("http://xml.org/sax/features/validation", paramDocumentBuilderFactoryImpl.isValidating());
    this.domParser.setFeature("http://xml.org/sax/features/namespaces", paramDocumentBuilderFactoryImpl.isNamespaceAware());
    this.domParser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", !paramDocumentBuilderFactoryImpl.isIgnoringElementContentWhitespace());
    this.domParser.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", !paramDocumentBuilderFactoryImpl.isExpandEntityReferences());
    this.domParser.setFeature("http://apache.org/xml/features/include-comments", !paramDocumentBuilderFactoryImpl.isIgnoringComments());
    this.domParser.setFeature("http://apache.org/xml/features/create-cdata-nodes", !paramDocumentBuilderFactoryImpl.isCoalescing());
    if (paramDocumentBuilderFactoryImpl.isXIncludeAware())
      this.domParser.setFeature("http://apache.org/xml/features/xinclude", true); 
    if (paramBoolean)
      this.domParser.setProperty("http://apache.org/xml/properties/security-manager", new SecurityManager()); 
    this.grammar = paramDocumentBuilderFactoryImpl.getSchema();
    if (this.grammar != null) {
      JAXPValidatorComponent jAXPValidatorComponent;
      XMLParserConfiguration xMLParserConfiguration = this.domParser.getXMLParserConfiguration();
      XMLSchemaValidator xMLSchemaValidator = null;
      if (this.grammar instanceof XSGrammarPoolContainer) {
        xMLSchemaValidator = new XMLSchemaValidator();
        this.fSchemaValidationManager = new ValidationManager();
        this.fUnparsedEntityHandler = new UnparsedEntityHandler(this.fSchemaValidationManager);
        xMLParserConfiguration.setDTDHandler((XMLDTDHandler)this.fUnparsedEntityHandler);
        this.fUnparsedEntityHandler.setDTDHandler((XMLDTDHandler)this.domParser);
        this.domParser.setDTDSource((XMLDTDSource)this.fUnparsedEntityHandler);
        this.fSchemaValidatorComponentManager = new SchemaValidatorConfiguration((XMLComponentManager)xMLParserConfiguration, (XSGrammarPoolContainer)this.grammar, this.fSchemaValidationManager);
      } else {
        jAXPValidatorComponent = new JAXPValidatorComponent(this.grammar.newValidatorHandler());
        this.fSchemaValidatorComponentManager = (XMLComponentManager)xMLParserConfiguration;
      } 
      xMLParserConfiguration.addRecognizedFeatures(jAXPValidatorComponent.getRecognizedFeatures());
      xMLParserConfiguration.addRecognizedProperties(jAXPValidatorComponent.getRecognizedProperties());
      xMLParserConfiguration.setDocumentHandler((XMLDocumentHandler)jAXPValidatorComponent);
      ((XMLDocumentSource)jAXPValidatorComponent).setDocumentHandler((XMLDocumentHandler)this.domParser);
      this.domParser.setDocumentSource((XMLDocumentSource)jAXPValidatorComponent);
      this.fSchemaValidator = jAXPValidatorComponent;
    } 
    setFeatures(paramHashtable2);
    setDocumentBuilderFactoryAttributes(paramHashtable1);
    this.fInitEntityResolver = this.domParser.getEntityResolver();
  }
  
  private void setFeatures(Hashtable paramHashtable) throws SAXNotSupportedException, SAXNotRecognizedException {
    if (paramHashtable != null) {
      Enumeration enumeration = paramHashtable.keys();
      while (enumeration.hasMoreElements()) {
        String str = enumeration.nextElement();
        boolean bool = ((Boolean)paramHashtable.get(str)).booleanValue();
        this.domParser.setFeature(str, bool);
      } 
    } 
  }
  
  private void setDocumentBuilderFactoryAttributes(Hashtable paramHashtable) throws SAXNotSupportedException, SAXNotRecognizedException {
    if (paramHashtable == null)
      return; 
    Enumeration enumeration = paramHashtable.keys();
    while (enumeration.hasMoreElements()) {
      String str = enumeration.nextElement();
      Object object = paramHashtable.get(str);
      if (object instanceof Boolean) {
        this.domParser.setFeature(str, ((Boolean)object).booleanValue());
        continue;
      } 
      if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(str)) {
        if ("http://www.w3.org/2001/XMLSchema".equals(object) && isValidating()) {
          this.domParser.setFeature("http://apache.org/xml/features/validation/schema", true);
          this.domParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        } 
        continue;
      } 
      if ("http://java.sun.com/xml/jaxp/properties/schemaSource".equals(str)) {
        if (isValidating()) {
          String str1 = (String)paramHashtable.get("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
          if (str1 != null && "http://www.w3.org/2001/XMLSchema".equals(str1)) {
            this.domParser.setProperty(str, object);
            continue;
          } 
          throw new IllegalArgumentException(DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "jaxp-order-not-supported", new Object[] { "http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://java.sun.com/xml/jaxp/properties/schemaSource" }));
        } 
        continue;
      } 
      this.domParser.setProperty(str, object);
    } 
  }
  
  public Document newDocument() {
    return (Document)new DocumentImpl();
  }
  
  public DOMImplementation getDOMImplementation() {
    return DOMImplementationImpl.getDOMImplementation();
  }
  
  public Document parse(InputSource paramInputSource) throws SAXException, IOException {
    if (paramInputSource == null)
      throw new IllegalArgumentException(DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "jaxp-null-input-source", null)); 
    if (this.fSchemaValidator != null) {
      if (this.fSchemaValidationManager != null) {
        this.fSchemaValidationManager.reset();
        this.fUnparsedEntityHandler.reset();
      } 
      resetSchemaValidator();
    } 
    this.domParser.parse(paramInputSource);
    Document document = this.domParser.getDocument();
    this.domParser.dropDocumentReferences();
    return document;
  }
  
  public boolean isNamespaceAware() {
    try {
      return this.domParser.getFeature("http://xml.org/sax/features/namespaces");
    } catch (SAXException sAXException) {
      throw new IllegalStateException(sAXException.getMessage());
    } 
  }
  
  public boolean isValidating() {
    try {
      return this.domParser.getFeature("http://xml.org/sax/features/validation");
    } catch (SAXException sAXException) {
      throw new IllegalStateException(sAXException.getMessage());
    } 
  }
  
  public boolean isXIncludeAware() {
    try {
      return this.domParser.getFeature("http://apache.org/xml/features/xinclude");
    } catch (SAXException sAXException) {
      return false;
    } 
  }
  
  public void setEntityResolver(EntityResolver paramEntityResolver) {
    this.domParser.setEntityResolver(paramEntityResolver);
  }
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.domParser.setErrorHandler(paramErrorHandler);
  }
  
  public Schema getSchema() {
    return this.grammar;
  }
  
  public void reset() {
    if (this.domParser.getErrorHandler() != this.fInitErrorHandler)
      this.domParser.setErrorHandler(this.fInitErrorHandler); 
    if (this.domParser.getEntityResolver() != this.fInitEntityResolver)
      this.domParser.setEntityResolver(this.fInitEntityResolver); 
  }
  
  DOMParser getDOMParser() {
    return this.domParser;
  }
  
  private void resetSchemaValidator() throws SAXException {
    try {
      this.fSchemaValidator.reset(this.fSchemaValidatorComponentManager);
    } catch (XMLConfigurationException xMLConfigurationException) {
      throw new SAXException(xMLConfigurationException);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\DocumentBuilderImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */