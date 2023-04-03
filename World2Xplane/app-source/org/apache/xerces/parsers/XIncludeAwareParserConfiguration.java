package org.apache.xerces.parsers;

import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xinclude.XIncludeHandler;
import org.apache.xerces.xinclude.XIncludeNamespaceSupport;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentSource;

public class XIncludeAwareParserConfiguration extends XML11Configuration {
  protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
  
  protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
  
  protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
  
  protected static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
  
  protected static final String XINCLUDE_HANDLER = "http://apache.org/xml/properties/internal/xinclude-handler";
  
  protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
  
  protected XIncludeHandler fXIncludeHandler;
  
  protected NamespaceSupport fNonXIncludeNSContext;
  
  protected XIncludeNamespaceSupport fXIncludeNSContext;
  
  protected NamespaceContext fCurrentNSContext;
  
  protected boolean fXIncludeEnabled = false;
  
  public XIncludeAwareParserConfiguration() {
    this(null, null, null);
  }
  
  public XIncludeAwareParserConfiguration(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, null, null);
  }
  
  public XIncludeAwareParserConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this(paramSymbolTable, paramXMLGrammarPool, null);
  }
  
  public XIncludeAwareParserConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool, XMLComponentManager paramXMLComponentManager) {
    super(paramSymbolTable, paramXMLGrammarPool, paramXMLComponentManager);
    String[] arrayOfString1 = { "http://xml.org/sax/features/allow-dtd-events-after-endDTD", "http://apache.org/xml/features/xinclude/fixup-base-uris", "http://apache.org/xml/features/xinclude/fixup-language" };
    addRecognizedFeatures(arrayOfString1);
    String[] arrayOfString2 = { "http://apache.org/xml/properties/internal/xinclude-handler", "http://apache.org/xml/properties/internal/namespace-context" };
    addRecognizedProperties(arrayOfString2);
    setFeature("http://xml.org/sax/features/allow-dtd-events-after-endDTD", true);
    setFeature("http://apache.org/xml/features/xinclude/fixup-base-uris", true);
    setFeature("http://apache.org/xml/features/xinclude/fixup-language", true);
    this.fNonXIncludeNSContext = new NamespaceSupport();
    this.fCurrentNSContext = (NamespaceContext)this.fNonXIncludeNSContext;
    setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNonXIncludeNSContext);
  }
  
  protected void configurePipeline() {
    super.configurePipeline();
    if (this.fXIncludeEnabled) {
      if (this.fXIncludeHandler == null) {
        this.fXIncludeHandler = new XIncludeHandler();
        setProperty("http://apache.org/xml/properties/internal/xinclude-handler", this.fXIncludeHandler);
        addCommonComponent((XMLComponent)this.fXIncludeHandler);
        this.fXIncludeHandler.reset((XMLComponentManager)this);
      } 
      if (this.fCurrentNSContext != this.fXIncludeNSContext) {
        if (this.fXIncludeNSContext == null)
          this.fXIncludeNSContext = new XIncludeNamespaceSupport(); 
        this.fCurrentNSContext = (NamespaceContext)this.fXIncludeNSContext;
        setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fXIncludeNSContext);
      } 
      this.fDTDScanner.setDTDHandler((XMLDTDHandler)this.fDTDProcessor);
      this.fDTDProcessor.setDTDSource((XMLDTDSource)this.fDTDScanner);
      this.fDTDProcessor.setDTDHandler((XMLDTDHandler)this.fXIncludeHandler);
      this.fXIncludeHandler.setDTDSource((XMLDTDSource)this.fDTDProcessor);
      this.fXIncludeHandler.setDTDHandler(this.fDTDHandler);
      if (this.fDTDHandler != null)
        this.fDTDHandler.setDTDSource((XMLDTDSource)this.fXIncludeHandler); 
      XMLDocumentSource xMLDocumentSource = null;
      if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
        xMLDocumentSource = this.fSchemaValidator.getDocumentSource();
      } else {
        xMLDocumentSource = this.fLastComponent;
        this.fLastComponent = (XMLDocumentSource)this.fXIncludeHandler;
      } 
      XMLDocumentHandler xMLDocumentHandler = xMLDocumentSource.getDocumentHandler();
      xMLDocumentSource.setDocumentHandler((XMLDocumentHandler)this.fXIncludeHandler);
      this.fXIncludeHandler.setDocumentSource(xMLDocumentSource);
      if (xMLDocumentHandler != null) {
        this.fXIncludeHandler.setDocumentHandler(xMLDocumentHandler);
        xMLDocumentHandler.setDocumentSource((XMLDocumentSource)this.fXIncludeHandler);
      } 
    } else if (this.fCurrentNSContext != this.fNonXIncludeNSContext) {
      this.fCurrentNSContext = (NamespaceContext)this.fNonXIncludeNSContext;
      setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNonXIncludeNSContext);
    } 
  }
  
  protected void configureXML11Pipeline() {
    super.configureXML11Pipeline();
    if (this.fXIncludeEnabled) {
      if (this.fXIncludeHandler == null) {
        this.fXIncludeHandler = new XIncludeHandler();
        setProperty("http://apache.org/xml/properties/internal/xinclude-handler", this.fXIncludeHandler);
        addCommonComponent((XMLComponent)this.fXIncludeHandler);
        this.fXIncludeHandler.reset((XMLComponentManager)this);
      } 
      if (this.fCurrentNSContext != this.fXIncludeNSContext) {
        if (this.fXIncludeNSContext == null)
          this.fXIncludeNSContext = new XIncludeNamespaceSupport(); 
        this.fCurrentNSContext = (NamespaceContext)this.fXIncludeNSContext;
        setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fXIncludeNSContext);
      } 
      this.fXML11DTDScanner.setDTDHandler((XMLDTDHandler)this.fXML11DTDProcessor);
      this.fXML11DTDProcessor.setDTDSource((XMLDTDSource)this.fXML11DTDScanner);
      this.fXML11DTDProcessor.setDTDHandler((XMLDTDHandler)this.fXIncludeHandler);
      this.fXIncludeHandler.setDTDSource((XMLDTDSource)this.fXML11DTDProcessor);
      this.fXIncludeHandler.setDTDHandler(this.fDTDHandler);
      if (this.fDTDHandler != null)
        this.fDTDHandler.setDTDSource((XMLDTDSource)this.fXIncludeHandler); 
      XMLDocumentSource xMLDocumentSource = null;
      if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
        xMLDocumentSource = this.fSchemaValidator.getDocumentSource();
      } else {
        xMLDocumentSource = this.fLastComponent;
        this.fLastComponent = (XMLDocumentSource)this.fXIncludeHandler;
      } 
      XMLDocumentHandler xMLDocumentHandler = xMLDocumentSource.getDocumentHandler();
      xMLDocumentSource.setDocumentHandler((XMLDocumentHandler)this.fXIncludeHandler);
      this.fXIncludeHandler.setDocumentSource(xMLDocumentSource);
      if (xMLDocumentHandler != null) {
        this.fXIncludeHandler.setDocumentHandler(xMLDocumentHandler);
        xMLDocumentHandler.setDocumentSource((XMLDocumentSource)this.fXIncludeHandler);
      } 
    } else if (this.fCurrentNSContext != this.fNonXIncludeNSContext) {
      this.fCurrentNSContext = (NamespaceContext)this.fNonXIncludeNSContext;
      setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNonXIncludeNSContext);
    } 
  }
  
  public boolean getFeature(String paramString) throws XMLConfigurationException {
    return paramString.equals("http://apache.org/xml/features/internal/parser-settings") ? this.fConfigUpdated : (paramString.equals("http://apache.org/xml/features/xinclude") ? this.fXIncludeEnabled : getFeature0(paramString));
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    if (paramString.equals("http://apache.org/xml/features/xinclude")) {
      this.fXIncludeEnabled = paramBoolean;
      this.fConfigUpdated = true;
      return;
    } 
    super.setFeature(paramString, paramBoolean);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\XIncludeAwareParserConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */