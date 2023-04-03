package org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.apache.xerces.util.DOMInputSource;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.apache.xerces.util.SAXInputSource;
import org.apache.xerces.util.SAXMessageFormatter;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

public final class XMLSchemaFactory extends SchemaFactory {
  private static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
  
  private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
  
  private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  private final XMLSchemaLoader fXMLSchemaLoader = new XMLSchemaLoader();
  
  private ErrorHandler fErrorHandler;
  
  private LSResourceResolver fLSResourceResolver;
  
  private final DOMEntityResolverWrapper fDOMEntityResolverWrapper = new DOMEntityResolverWrapper();
  
  private final ErrorHandlerWrapper fErrorHandlerWrapper = new ErrorHandlerWrapper(DraconianErrorHandler.getInstance());
  
  private SecurityManager fSecurityManager;
  
  private final XMLGrammarPoolWrapper fXMLGrammarPoolWrapper = new XMLGrammarPoolWrapper();
  
  private boolean fUseGrammarPoolOnly;
  
  public XMLSchemaFactory() {
    this.fXMLSchemaLoader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
    this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fXMLGrammarPoolWrapper);
    this.fXMLSchemaLoader.setEntityResolver((XMLEntityResolver)this.fDOMEntityResolverWrapper);
    this.fXMLSchemaLoader.setErrorHandler((XMLErrorHandler)this.fErrorHandlerWrapper);
    this.fUseGrammarPoolOnly = true;
  }
  
  public boolean isSchemaLanguageSupported(String paramString) {
    if (paramString == null)
      throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaLanguageNull", null)); 
    if (paramString.length() == 0)
      throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaLanguageLengthZero", null)); 
    return paramString.equals("http://www.w3.org/2001/XMLSchema");
  }
  
  public LSResourceResolver getResourceResolver() {
    return this.fLSResourceResolver;
  }
  
  public void setResourceResolver(LSResourceResolver paramLSResourceResolver) {
    this.fLSResourceResolver = paramLSResourceResolver;
    this.fDOMEntityResolverWrapper.setEntityResolver(paramLSResourceResolver);
    this.fXMLSchemaLoader.setEntityResolver((XMLEntityResolver)this.fDOMEntityResolverWrapper);
  }
  
  public ErrorHandler getErrorHandler() {
    return this.fErrorHandler;
  }
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.fErrorHandler = paramErrorHandler;
    this.fErrorHandlerWrapper.setErrorHandler((paramErrorHandler != null) ? paramErrorHandler : DraconianErrorHandler.getInstance());
    this.fXMLSchemaLoader.setErrorHandler((XMLErrorHandler)this.fErrorHandlerWrapper);
  }
  
  public Schema newSchema(Source[] paramArrayOfSource) throws SAXException {
    XMLGrammarPoolImplExtension xMLGrammarPoolImplExtension = new XMLGrammarPoolImplExtension();
    this.fXMLGrammarPoolWrapper.setGrammarPool((XMLGrammarPool)xMLGrammarPoolImplExtension);
    XMLInputSource[] arrayOfXMLInputSource = new XMLInputSource[paramArrayOfSource.length];
    for (byte b = 0; b < paramArrayOfSource.length; b++) {
      Source source = paramArrayOfSource[b];
      if (source instanceof StreamSource) {
        StreamSource streamSource = (StreamSource)source;
        String str1 = streamSource.getPublicId();
        String str2 = streamSource.getSystemId();
        InputStream inputStream = streamSource.getInputStream();
        Reader reader = streamSource.getReader();
        arrayOfXMLInputSource[b] = new XMLInputSource(str1, str2, null);
        arrayOfXMLInputSource[b].setByteStream(inputStream);
        arrayOfXMLInputSource[b].setCharacterStream(reader);
      } else if (source instanceof SAXSource) {
        SAXSource sAXSource = (SAXSource)source;
        InputSource inputSource = sAXSource.getInputSource();
        if (inputSource == null)
          throw new SAXException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SAXSourceNullInputSource", null)); 
        arrayOfXMLInputSource[b] = (XMLInputSource)new SAXInputSource(sAXSource.getXMLReader(), inputSource);
      } else if (source instanceof DOMSource) {
        DOMSource dOMSource = (DOMSource)source;
        Node node = dOMSource.getNode();
        String str = dOMSource.getSystemId();
        arrayOfXMLInputSource[b] = (XMLInputSource)new DOMInputSource(node, str);
      } else {
        if (source == null)
          throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaSourceArrayMemberNull", null)); 
        throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaFactorySourceUnrecognized", new Object[] { source.getClass().getName() }));
      } 
    } 
    try {
      this.fXMLSchemaLoader.loadGrammar(arrayOfXMLInputSource);
    } catch (XNIException xNIException) {
      throw Util.toSAXException(xNIException);
    } catch (IOException iOException) {
      SAXParseException sAXParseException = new SAXParseException(iOException.getMessage(), null, iOException);
      if (this.fErrorHandler != null)
        this.fErrorHandler.error(sAXParseException); 
      throw sAXParseException;
    } 
    this.fXMLGrammarPoolWrapper.setGrammarPool(null);
    int i = xMLGrammarPoolImplExtension.getGrammarCount();
    XMLSchema xMLSchema = null;
    if (this.fUseGrammarPoolOnly) {
      if (i > 1) {
        xMLSchema = new XMLSchema(new ReadOnlyGrammarPool((XMLGrammarPool)xMLGrammarPoolImplExtension));
      } else if (i == 1) {
        Grammar[] arrayOfGrammar = xMLGrammarPoolImplExtension.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema");
        SimpleXMLSchema simpleXMLSchema = new SimpleXMLSchema(arrayOfGrammar[0]);
      } else {
        EmptyXMLSchema emptyXMLSchema = new EmptyXMLSchema();
      } 
    } else {
      xMLSchema = new XMLSchema(new ReadOnlyGrammarPool((XMLGrammarPool)xMLGrammarPoolImplExtension), false);
    } 
    propagateFeatures(xMLSchema);
    return xMLSchema;
  }
  
  public Schema newSchema() throws SAXException {
    WeakReferenceXMLSchema weakReferenceXMLSchema = new WeakReferenceXMLSchema();
    propagateFeatures(weakReferenceXMLSchema);
    return weakReferenceXMLSchema;
  }
  
  public boolean getFeature(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "FeatureNameNull", null)); 
    if (paramString.equals("http://javax.xml.XMLConstants/feature/secure-processing"))
      return (this.fSecurityManager != null); 
    if (paramString.equals("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only"))
      return this.fUseGrammarPoolOnly; 
    try {
      return this.fXMLSchemaLoader.getFeature(paramString);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-supported", new Object[] { str }));
    } 
  }
  
  public Object getProperty(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "ProperyNameNull", null)); 
    if (paramString.equals("http://apache.org/xml/properties/security-manager"))
      return this.fSecurityManager; 
    if (paramString.equals("http://apache.org/xml/properties/internal/grammar-pool"))
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { paramString })); 
    try {
      return this.fXMLSchemaLoader.getProperty(paramString);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { str }));
    } 
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "FeatureNameNull", null)); 
    if (paramString.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
      this.fSecurityManager = paramBoolean ? new SecurityManager() : null;
      this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
      return;
    } 
    if (paramString.equals("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only")) {
      this.fUseGrammarPoolOnly = paramBoolean;
      return;
    } 
    try {
      this.fXMLSchemaLoader.setFeature(paramString, paramBoolean);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-supported", new Object[] { str }));
    } 
  }
  
  public void setProperty(String paramString, Object paramObject) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "ProperyNameNull", null)); 
    if (paramString.equals("http://apache.org/xml/properties/security-manager")) {
      this.fSecurityManager = (SecurityManager)paramObject;
      this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
      return;
    } 
    if (paramString.equals("http://apache.org/xml/properties/internal/grammar-pool"))
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { paramString })); 
    try {
      this.fXMLSchemaLoader.setProperty(paramString, paramObject);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { str }));
    } 
  }
  
  private void propagateFeatures(AbstractXMLSchema paramAbstractXMLSchema) {
    paramAbstractXMLSchema.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", (this.fSecurityManager != null));
    String[] arrayOfString = this.fXMLSchemaLoader.getRecognizedFeatures();
    for (byte b = 0; b < arrayOfString.length; b++) {
      boolean bool = this.fXMLSchemaLoader.getFeature(arrayOfString[b]);
      paramAbstractXMLSchema.setFeature(arrayOfString[b], bool);
    } 
  }
  
  static class XMLGrammarPoolWrapper implements XMLGrammarPool {
    private XMLGrammarPool fGrammarPool;
    
    public Grammar[] retrieveInitialGrammarSet(String param1String) {
      return this.fGrammarPool.retrieveInitialGrammarSet(param1String);
    }
    
    public void cacheGrammars(String param1String, Grammar[] param1ArrayOfGrammar) {
      this.fGrammarPool.cacheGrammars(param1String, param1ArrayOfGrammar);
    }
    
    public Grammar retrieveGrammar(XMLGrammarDescription param1XMLGrammarDescription) {
      return this.fGrammarPool.retrieveGrammar(param1XMLGrammarDescription);
    }
    
    public void lockPool() {
      this.fGrammarPool.lockPool();
    }
    
    public void unlockPool() {
      this.fGrammarPool.unlockPool();
    }
    
    public void clear() {
      this.fGrammarPool.clear();
    }
    
    void setGrammarPool(XMLGrammarPool param1XMLGrammarPool) {
      this.fGrammarPool = param1XMLGrammarPool;
    }
    
    XMLGrammarPool getGrammarPool() {
      return this.fGrammarPool;
    }
  }
  
  static class XMLGrammarPoolImplExtension extends XMLGrammarPoolImpl {
    public XMLGrammarPoolImplExtension() {}
    
    public XMLGrammarPoolImplExtension(int param1Int) {
      super(param1Int);
    }
    
    int getGrammarCount() {
      return this.fGrammarCount;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\XMLSchemaFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */