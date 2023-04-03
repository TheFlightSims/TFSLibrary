package org.apache.xerces.jaxp;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.validation.Schema;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import org.apache.xerces.parsers.SAXParser;
import org.apache.xerces.parsers.XMLParser;
import org.apache.xerces.util.SAXMessageFormatter;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.PSVIProvider;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserImpl extends SAXParser implements JAXPConstants, PSVIProvider {
  private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
  
  private static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
  
  private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
  
  private static final String XMLSCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
  
  private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
  
  private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private JAXPSAXParser xmlReader = new JAXPSAXParser(this);
  
  private String schemaLanguage = null;
  
  private final Schema grammar;
  
  private XMLComponent fSchemaValidator;
  
  private XMLComponentManager fSchemaValidatorComponentManager;
  
  private ValidationManager fSchemaValidationManager;
  
  private UnparsedEntityHandler fUnparsedEntityHandler;
  
  private final ErrorHandler fInitErrorHandler;
  
  private final EntityResolver fInitEntityResolver;
  
  SAXParserImpl(SAXParserFactoryImpl paramSAXParserFactoryImpl, Hashtable paramHashtable) throws SAXException {
    this(paramSAXParserFactoryImpl, paramHashtable, false);
  }
  
  SAXParserImpl(SAXParserFactoryImpl paramSAXParserFactoryImpl, Hashtable paramHashtable, boolean paramBoolean) throws SAXException {
    this.xmlReader.setFeature0("http://xml.org/sax/features/namespaces", paramSAXParserFactoryImpl.isNamespaceAware());
    this.xmlReader.setFeature0("http://xml.org/sax/features/namespace-prefixes", !paramSAXParserFactoryImpl.isNamespaceAware());
    if (paramSAXParserFactoryImpl.isXIncludeAware())
      this.xmlReader.setFeature0("http://apache.org/xml/features/xinclude", true); 
    if (paramBoolean)
      this.xmlReader.setProperty0("http://apache.org/xml/properties/security-manager", new SecurityManager()); 
    setFeatures(paramHashtable);
    if (paramSAXParserFactoryImpl.isValidating()) {
      this.fInitErrorHandler = new DefaultValidationErrorHandler();
      this.xmlReader.setErrorHandler(this.fInitErrorHandler);
    } else {
      this.fInitErrorHandler = this.xmlReader.getErrorHandler();
    } 
    this.xmlReader.setFeature0("http://xml.org/sax/features/validation", paramSAXParserFactoryImpl.isValidating());
    this.grammar = paramSAXParserFactoryImpl.getSchema();
    if (this.grammar != null) {
      JAXPValidatorComponent jAXPValidatorComponent;
      XMLParserConfiguration xMLParserConfiguration = this.xmlReader.getXMLParserConfiguration();
      XMLSchemaValidator xMLSchemaValidator = null;
      if (this.grammar instanceof XSGrammarPoolContainer) {
        xMLSchemaValidator = new XMLSchemaValidator();
        this.fSchemaValidationManager = new ValidationManager();
        this.fUnparsedEntityHandler = new UnparsedEntityHandler(this.fSchemaValidationManager);
        xMLParserConfiguration.setDTDHandler((XMLDTDHandler)this.fUnparsedEntityHandler);
        this.fUnparsedEntityHandler.setDTDHandler((XMLDTDHandler)this.xmlReader);
        this.xmlReader.setDTDSource((XMLDTDSource)this.fUnparsedEntityHandler);
        this.fSchemaValidatorComponentManager = new SchemaValidatorConfiguration((XMLComponentManager)xMLParserConfiguration, (XSGrammarPoolContainer)this.grammar, this.fSchemaValidationManager);
      } else {
        jAXPValidatorComponent = new JAXPValidatorComponent(this.grammar.newValidatorHandler());
        this.fSchemaValidatorComponentManager = (XMLComponentManager)xMLParserConfiguration;
      } 
      xMLParserConfiguration.addRecognizedFeatures(jAXPValidatorComponent.getRecognizedFeatures());
      xMLParserConfiguration.addRecognizedProperties(jAXPValidatorComponent.getRecognizedProperties());
      xMLParserConfiguration.setDocumentHandler((XMLDocumentHandler)jAXPValidatorComponent);
      ((XMLDocumentSource)jAXPValidatorComponent).setDocumentHandler((XMLDocumentHandler)this.xmlReader);
      this.xmlReader.setDocumentSource((XMLDocumentSource)jAXPValidatorComponent);
      this.fSchemaValidator = jAXPValidatorComponent;
    } 
    this.fInitEntityResolver = this.xmlReader.getEntityResolver();
  }
  
  private void setFeatures(Hashtable paramHashtable) throws SAXNotSupportedException, SAXNotRecognizedException {
    if (paramHashtable != null) {
      Enumeration enumeration = paramHashtable.keys();
      while (enumeration.hasMoreElements()) {
        String str = enumeration.nextElement();
        boolean bool = ((Boolean)paramHashtable.get(str)).booleanValue();
        this.xmlReader.setFeature0(str, bool);
      } 
    } 
  }
  
  public Parser getParser() throws SAXException {
    return (Parser)this.xmlReader;
  }
  
  public XMLReader getXMLReader() {
    return (XMLReader)this.xmlReader;
  }
  
  public boolean isNamespaceAware() {
    try {
      return this.xmlReader.getFeature("http://xml.org/sax/features/namespaces");
    } catch (SAXException sAXException) {
      throw new IllegalStateException(sAXException.getMessage());
    } 
  }
  
  public boolean isValidating() {
    try {
      return this.xmlReader.getFeature("http://xml.org/sax/features/validation");
    } catch (SAXException sAXException) {
      throw new IllegalStateException(sAXException.getMessage());
    } 
  }
  
  public boolean isXIncludeAware() {
    try {
      return this.xmlReader.getFeature("http://apache.org/xml/features/xinclude");
    } catch (SAXException sAXException) {
      return false;
    } 
  }
  
  public void setProperty(String paramString, Object paramObject) throws SAXNotRecognizedException, SAXNotSupportedException {
    this.xmlReader.setProperty(paramString, paramObject);
  }
  
  public Object getProperty(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    return this.xmlReader.getProperty(paramString);
  }
  
  public void parse(InputSource paramInputSource, DefaultHandler paramDefaultHandler) throws SAXException, IOException {
    if (paramInputSource == null)
      throw new IllegalArgumentException(); 
    if (paramDefaultHandler != null) {
      this.xmlReader.setContentHandler(paramDefaultHandler);
      this.xmlReader.setEntityResolver(paramDefaultHandler);
      this.xmlReader.setErrorHandler(paramDefaultHandler);
      this.xmlReader.setDTDHandler(paramDefaultHandler);
      this.xmlReader.setDocumentHandler(null);
    } 
    this.xmlReader.parse(paramInputSource);
  }
  
  public void parse(InputSource paramInputSource, HandlerBase paramHandlerBase) throws SAXException, IOException {
    if (paramInputSource == null)
      throw new IllegalArgumentException(); 
    if (paramHandlerBase != null) {
      this.xmlReader.setDocumentHandler(paramHandlerBase);
      this.xmlReader.setEntityResolver(paramHandlerBase);
      this.xmlReader.setErrorHandler(paramHandlerBase);
      this.xmlReader.setDTDHandler(paramHandlerBase);
      this.xmlReader.setContentHandler(null);
    } 
    this.xmlReader.parse(paramInputSource);
  }
  
  public Schema getSchema() {
    return this.grammar;
  }
  
  public void reset() {
    try {
      this.xmlReader.restoreInitState();
    } catch (SAXException sAXException) {}
    this.xmlReader.setContentHandler(null);
    this.xmlReader.setDTDHandler(null);
    if (this.xmlReader.getErrorHandler() != this.fInitErrorHandler)
      this.xmlReader.setErrorHandler(this.fInitErrorHandler); 
    if (this.xmlReader.getEntityResolver() != this.fInitEntityResolver)
      this.xmlReader.setEntityResolver(this.fInitEntityResolver); 
  }
  
  public ElementPSVI getElementPSVI() {
    return this.xmlReader.getElementPSVI();
  }
  
  public AttributePSVI getAttributePSVI(int paramInt) {
    return this.xmlReader.getAttributePSVI(paramInt);
  }
  
  public AttributePSVI getAttributePSVIByName(String paramString1, String paramString2) {
    return this.xmlReader.getAttributePSVIByName(paramString1, paramString2);
  }
  
  public static class JAXPSAXParser extends SAXParser {
    private HashMap fInitFeatures = new HashMap();
    
    private HashMap fInitProperties = new HashMap();
    
    private SAXParserImpl fSAXParser;
    
    public JAXPSAXParser() {}
    
    JAXPSAXParser(SAXParserImpl param1SAXParserImpl) {
      this.fSAXParser = param1SAXParserImpl;
    }
    
    public synchronized void setFeature(String param1String, boolean param1Boolean) throws SAXNotRecognizedException, SAXNotSupportedException {
      if (param1String == null)
        throw new NullPointerException(); 
      if (param1String.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
        try {
          setProperty("http://apache.org/xml/properties/security-manager", param1Boolean ? new SecurityManager() : null);
        } catch (SAXNotRecognizedException sAXNotRecognizedException) {
          if (param1Boolean)
            throw sAXNotRecognizedException; 
        } catch (SAXNotSupportedException sAXNotSupportedException) {
          if (param1Boolean)
            throw sAXNotSupportedException; 
        } 
        return;
      } 
      if (!this.fInitFeatures.containsKey(param1String)) {
        boolean bool = super.getFeature(param1String);
        this.fInitFeatures.put(param1String, bool ? Boolean.TRUE : Boolean.FALSE);
      } 
      if (this.fSAXParser != null && this.fSAXParser.fSchemaValidator != null)
        setSchemaValidatorFeature(param1String, param1Boolean); 
      super.setFeature(param1String, param1Boolean);
    }
    
    public synchronized boolean getFeature(String param1String) throws SAXNotRecognizedException, SAXNotSupportedException {
      if (param1String == null)
        throw new NullPointerException(); 
      if (param1String.equals("http://javax.xml.XMLConstants/feature/secure-processing"))
        try {
          return (super.getProperty("http://apache.org/xml/properties/security-manager") != null);
        } catch (SAXException sAXException) {
          return false;
        }  
      return super.getFeature(param1String);
    }
    
    public synchronized void setProperty(String param1String, Object param1Object) throws SAXNotRecognizedException, SAXNotSupportedException {
      if (param1String == null)
        throw new NullPointerException(); 
      if (this.fSAXParser != null) {
        if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(param1String)) {
          if (this.fSAXParser.grammar != null)
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "schema-already-specified", new Object[] { param1String })); 
          if ("http://www.w3.org/2001/XMLSchema".equals(param1Object)) {
            if (this.fSAXParser.isValidating()) {
              this.fSAXParser.schemaLanguage = "http://www.w3.org/2001/XMLSchema";
              setFeature("http://apache.org/xml/features/validation/schema", true);
              if (!this.fInitProperties.containsKey("http://java.sun.com/xml/jaxp/properties/schemaLanguage"))
                this.fInitProperties.put("http://java.sun.com/xml/jaxp/properties/schemaLanguage", super.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage")); 
              super.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            } 
          } else if (param1Object == null) {
            this.fSAXParser.schemaLanguage = null;
            setFeature("http://apache.org/xml/features/validation/schema", false);
          } else {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "schema-not-supported", null));
          } 
          return;
        } 
        if ("http://java.sun.com/xml/jaxp/properties/schemaSource".equals(param1String)) {
          if (this.fSAXParser.grammar != null)
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "schema-already-specified", new Object[] { param1String })); 
          String str = (String)getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
          if (str != null && "http://www.w3.org/2001/XMLSchema".equals(str)) {
            if (!this.fInitProperties.containsKey("http://java.sun.com/xml/jaxp/properties/schemaSource"))
              this.fInitProperties.put("http://java.sun.com/xml/jaxp/properties/schemaSource", super.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource")); 
            super.setProperty(param1String, param1Object);
          } else {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "jaxp-order-not-supported", new Object[] { "http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://java.sun.com/xml/jaxp/properties/schemaSource" }));
          } 
          return;
        } 
      } 
      if (!this.fInitProperties.containsKey(param1String))
        this.fInitProperties.put(param1String, super.getProperty(param1String)); 
      if (this.fSAXParser != null && this.fSAXParser.fSchemaValidator != null)
        setSchemaValidatorProperty(param1String, param1Object); 
      super.setProperty(param1String, param1Object);
    }
    
    public synchronized Object getProperty(String param1String) throws SAXNotRecognizedException, SAXNotSupportedException {
      if (param1String == null)
        throw new NullPointerException(); 
      return (this.fSAXParser != null && "http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(param1String)) ? this.fSAXParser.schemaLanguage : super.getProperty(param1String);
    }
    
    synchronized void restoreInitState() throws SAXNotRecognizedException, SAXNotSupportedException {
      if (!this.fInitFeatures.isEmpty()) {
        for (Map.Entry entry : this.fInitFeatures.entrySet()) {
          String str = (String)entry.getKey();
          boolean bool = ((Boolean)entry.getValue()).booleanValue();
          super.setFeature(str, bool);
        } 
        this.fInitFeatures.clear();
      } 
      if (!this.fInitProperties.isEmpty()) {
        for (Map.Entry entry : this.fInitProperties.entrySet()) {
          String str = (String)entry.getKey();
          Object object = entry.getValue();
          super.setProperty(str, object);
        } 
        this.fInitProperties.clear();
      } 
    }
    
    public void parse(InputSource param1InputSource) throws SAXException, IOException {
      if (this.fSAXParser != null && this.fSAXParser.fSchemaValidator != null) {
        if (this.fSAXParser.fSchemaValidationManager != null) {
          this.fSAXParser.fSchemaValidationManager.reset();
          this.fSAXParser.fUnparsedEntityHandler.reset();
        } 
        resetSchemaValidator();
      } 
      super.parse(param1InputSource);
    }
    
    public void parse(String param1String) throws SAXException, IOException {
      if (this.fSAXParser != null && this.fSAXParser.fSchemaValidator != null) {
        if (this.fSAXParser.fSchemaValidationManager != null) {
          this.fSAXParser.fSchemaValidationManager.reset();
          this.fSAXParser.fUnparsedEntityHandler.reset();
        } 
        resetSchemaValidator();
      } 
      super.parse(param1String);
    }
    
    XMLParserConfiguration getXMLParserConfiguration() {
      return ((XMLParser)this).fConfiguration;
    }
    
    void setFeature0(String param1String, boolean param1Boolean) throws SAXNotRecognizedException, SAXNotSupportedException {
      super.setFeature(param1String, param1Boolean);
    }
    
    boolean getFeature0(String param1String) throws SAXNotRecognizedException, SAXNotSupportedException {
      return super.getFeature(param1String);
    }
    
    void setProperty0(String param1String, Object param1Object) throws SAXNotRecognizedException, SAXNotSupportedException {
      super.setProperty(param1String, param1Object);
    }
    
    Object getProperty0(String param1String) throws SAXNotRecognizedException, SAXNotSupportedException {
      return super.getProperty(param1String);
    }
    
    private void setSchemaValidatorFeature(String param1String, boolean param1Boolean) throws SAXNotRecognizedException, SAXNotSupportedException {
      try {
        this.fSAXParser.fSchemaValidator.setFeature(param1String, param1Boolean);
      } catch (XMLConfigurationException xMLConfigurationException) {
        String str = xMLConfigurationException.getIdentifier();
        if (xMLConfigurationException.getType() == 0)
          throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[] { str })); 
        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[] { str }));
      } 
    }
    
    private void setSchemaValidatorProperty(String param1String, Object param1Object) throws SAXNotRecognizedException, SAXNotSupportedException {
      try {
        this.fSAXParser.fSchemaValidator.setProperty(param1String, param1Object);
      } catch (XMLConfigurationException xMLConfigurationException) {
        String str = xMLConfigurationException.getIdentifier();
        if (xMLConfigurationException.getType() == 0)
          throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[] { str })); 
        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[] { str }));
      } 
    }
    
    private void resetSchemaValidator() throws SAXException {
      try {
        this.fSAXParser.fSchemaValidator.reset(this.fSAXParser.fSchemaValidatorComponentManager);
      } catch (XMLConfigurationException xMLConfigurationException) {
        throw new SAXException(xMLConfigurationException);
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\SAXParserImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */