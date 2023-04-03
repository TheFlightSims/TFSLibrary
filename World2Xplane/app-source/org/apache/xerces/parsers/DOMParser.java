package org.apache.xerces.parsers;

import java.io.IOException;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.util.EntityResolver2Wrapper;
import org.apache.xerces.util.EntityResolverWrapper;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.apache.xerces.util.SAXMessageFormatter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.EntityResolver2;
import org.xml.sax.helpers.LocatorImpl;

public class DOMParser extends AbstractDOMParser {
  protected static final String USE_ENTITY_RESOLVER2 = "http://xml.org/sax/features/use-entity-resolver2";
  
  protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/grammar-pool" };
  
  protected boolean fUseEntityResolver2 = true;
  
  public DOMParser(XMLParserConfiguration paramXMLParserConfiguration) {
    super(paramXMLParserConfiguration);
  }
  
  public DOMParser() {
    this(null, null);
  }
  
  public DOMParser(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, null);
  }
  
  public DOMParser(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    this.fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
    if (paramSymbolTable != null)
      this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", paramSymbolTable); 
    if (paramXMLGrammarPool != null)
      this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", paramXMLGrammarPool); 
  }
  
  public void parse(String paramString) throws SAXException, IOException {
    XMLInputSource xMLInputSource = new XMLInputSource(null, paramString, null);
    try {
      parse(xMLInputSource);
    } catch (XMLParseException xMLParseException) {
      Exception exception = xMLParseException.getException();
      if (exception == null || exception instanceof java.io.CharConversionException) {
        LocatorImpl locatorImpl = new LocatorImpl();
        locatorImpl.setPublicId(xMLParseException.getPublicId());
        locatorImpl.setSystemId(xMLParseException.getExpandedSystemId());
        locatorImpl.setLineNumber(xMLParseException.getLineNumber());
        locatorImpl.setColumnNumber(xMLParseException.getColumnNumber());
        throw (exception == null) ? new SAXParseException(xMLParseException.getMessage(), locatorImpl) : new SAXParseException(xMLParseException.getMessage(), locatorImpl, exception);
      } 
      if (exception instanceof SAXException)
        throw (SAXException)exception; 
      if (exception instanceof IOException)
        throw (IOException)exception; 
      throw new SAXException(exception);
    } catch (XNIException xNIException) {
      xNIException.printStackTrace();
      Exception exception = xNIException.getException();
      if (exception == null)
        throw new SAXException(xNIException.getMessage()); 
      if (exception instanceof SAXException)
        throw (SAXException)exception; 
      if (exception instanceof IOException)
        throw (IOException)exception; 
      throw new SAXException(exception);
    } 
  }
  
  public void parse(InputSource paramInputSource) throws SAXException, IOException {
    try {
      XMLInputSource xMLInputSource = new XMLInputSource(paramInputSource.getPublicId(), paramInputSource.getSystemId(), null);
      xMLInputSource.setByteStream(paramInputSource.getByteStream());
      xMLInputSource.setCharacterStream(paramInputSource.getCharacterStream());
      xMLInputSource.setEncoding(paramInputSource.getEncoding());
      parse(xMLInputSource);
    } catch (XMLParseException xMLParseException) {
      Exception exception = xMLParseException.getException();
      if (exception == null || exception instanceof java.io.CharConversionException) {
        LocatorImpl locatorImpl = new LocatorImpl();
        locatorImpl.setPublicId(xMLParseException.getPublicId());
        locatorImpl.setSystemId(xMLParseException.getExpandedSystemId());
        locatorImpl.setLineNumber(xMLParseException.getLineNumber());
        locatorImpl.setColumnNumber(xMLParseException.getColumnNumber());
        throw (exception == null) ? new SAXParseException(xMLParseException.getMessage(), locatorImpl) : new SAXParseException(xMLParseException.getMessage(), locatorImpl, exception);
      } 
      if (exception instanceof SAXException)
        throw (SAXException)exception; 
      if (exception instanceof IOException)
        throw (IOException)exception; 
      throw new SAXException(exception);
    } catch (XNIException xNIException) {
      Exception exception = xNIException.getException();
      if (exception == null)
        throw new SAXException(xNIException.getMessage()); 
      if (exception instanceof SAXException)
        throw (SAXException)exception; 
      if (exception instanceof IOException)
        throw (IOException)exception; 
      throw new SAXException(exception);
    } 
  }
  
  public void setEntityResolver(EntityResolver paramEntityResolver) {
    try {
      XMLEntityResolver xMLEntityResolver = (XMLEntityResolver)this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
      if (this.fUseEntityResolver2 && paramEntityResolver instanceof EntityResolver2) {
        if (xMLEntityResolver instanceof EntityResolver2Wrapper) {
          EntityResolver2Wrapper entityResolver2Wrapper = (EntityResolver2Wrapper)xMLEntityResolver;
          entityResolver2Wrapper.setEntityResolver((EntityResolver2)paramEntityResolver);
        } else {
          this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolver2Wrapper((EntityResolver2)paramEntityResolver));
        } 
      } else if (xMLEntityResolver instanceof EntityResolverWrapper) {
        EntityResolverWrapper entityResolverWrapper = (EntityResolverWrapper)xMLEntityResolver;
        entityResolverWrapper.setEntityResolver(paramEntityResolver);
      } else {
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolverWrapper(paramEntityResolver));
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {}
  }
  
  public EntityResolver getEntityResolver() {
    EntityResolver entityResolver = null;
    try {
      XMLEntityResolver xMLEntityResolver = (XMLEntityResolver)this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
      if (xMLEntityResolver != null)
        if (xMLEntityResolver instanceof EntityResolverWrapper) {
          entityResolver = ((EntityResolverWrapper)xMLEntityResolver).getEntityResolver();
        } else if (xMLEntityResolver instanceof EntityResolver2Wrapper) {
          entityResolver = ((EntityResolver2Wrapper)xMLEntityResolver).getEntityResolver();
        }  
    } catch (XMLConfigurationException xMLConfigurationException) {}
    return entityResolver;
  }
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    try {
      XMLErrorHandler xMLErrorHandler = (XMLErrorHandler)this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/error-handler");
      if (xMLErrorHandler instanceof ErrorHandlerWrapper) {
        ErrorHandlerWrapper errorHandlerWrapper = (ErrorHandlerWrapper)xMLErrorHandler;
        errorHandlerWrapper.setErrorHandler(paramErrorHandler);
      } else {
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", new ErrorHandlerWrapper(paramErrorHandler));
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {}
  }
  
  public ErrorHandler getErrorHandler() {
    ErrorHandler errorHandler = null;
    try {
      XMLErrorHandler xMLErrorHandler = (XMLErrorHandler)this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/error-handler");
      if (xMLErrorHandler != null && xMLErrorHandler instanceof ErrorHandlerWrapper)
        errorHandler = ((ErrorHandlerWrapper)xMLErrorHandler).getErrorHandler(); 
    } catch (XMLConfigurationException xMLConfigurationException) {}
    return errorHandler;
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws SAXNotRecognizedException, SAXNotSupportedException {
    try {
      if (paramString.equals("http://xml.org/sax/features/use-entity-resolver2")) {
        if (paramBoolean != this.fUseEntityResolver2) {
          this.fUseEntityResolver2 = paramBoolean;
          setEntityResolver(getEntityResolver());
        } 
        return;
      } 
      this.fConfiguration.setFeature(paramString, paramBoolean);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[] { str }));
    } 
  }
  
  public boolean getFeature(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    try {
      return paramString.equals("http://xml.org/sax/features/use-entity-resolver2") ? this.fUseEntityResolver2 : this.fConfiguration.getFeature(paramString);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[] { str }));
    } 
  }
  
  public void setProperty(String paramString, Object paramObject) throws SAXNotRecognizedException, SAXNotSupportedException {
    try {
      this.fConfiguration.setProperty(paramString, paramObject);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[] { str }));
    } 
  }
  
  public Object getProperty(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString.equals("http://apache.org/xml/properties/dom/current-element-node")) {
      boolean bool = false;
      try {
        bool = getFeature("http://apache.org/xml/features/dom/defer-node-expansion");
      } catch (XMLConfigurationException xMLConfigurationException) {}
      if (bool)
        throw new SAXNotSupportedException(DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "CannotQueryDeferredNode", null)); 
      return (this.fCurrentNode != null && this.fCurrentNode.getNodeType() == 1) ? this.fCurrentNode : null;
    } 
    try {
      return this.fConfiguration.getProperty(paramString);
    } catch (XMLConfigurationException xMLConfigurationException) {
      String str = xMLConfigurationException.getIdentifier();
      if (xMLConfigurationException.getType() == 0)
        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[] { str })); 
      throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[] { str }));
    } 
  }
  
  public XMLParserConfiguration getXMLParserConfiguration() {
    return this.fConfiguration;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\DOMParser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */