package org.apache.xerces.parsers;

import java.io.StringReader;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.xerces.dom.DOMErrorImpl;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.dom.DOMStringListImpl;
import org.apache.xerces.impl.Constants;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.apache.xerces.util.DOMErrorHandlerWrapper;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSParserFilter;
import org.w3c.dom.ls.LSResourceResolver;

public class DOMParserImpl extends AbstractDOMParser implements LSParser, DOMConfiguration {
  protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
  
  protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
  
  protected static final String XMLSCHEMA = "http://apache.org/xml/features/validation/schema";
  
  protected static final String XMLSCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
  
  protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
  
  protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
  
  protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
  
  protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
  
  protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String PSVI_AUGMENT = "http://apache.org/xml/features/validation/schema/augment-psvi";
  
  protected boolean fNamespaceDeclarations = true;
  
  protected String fSchemaType = null;
  
  protected boolean fBusy = false;
  
  private boolean abortNow = false;
  
  private Thread currentThread;
  
  protected static final boolean DEBUG = false;
  
  private Vector fSchemaLocations = new Vector();
  
  private String fSchemaLocation = null;
  
  private DOMStringList fRecognizedParameters;
  
  private AbortHandler abortHandler = new AbortHandler();
  
  public DOMParserImpl(String paramString1, String paramString2) {
    this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", paramString1));
    if (paramString2 != null)
      if (paramString2.equals(Constants.NS_DTD)) {
        this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_DTD);
        this.fSchemaType = Constants.NS_DTD;
      } else if (paramString2.equals(Constants.NS_XMLSCHEMA)) {
        this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_XMLSCHEMA);
      }  
  }
  
  public DOMParserImpl(XMLParserConfiguration paramXMLParserConfiguration) {
    super(paramXMLParserConfiguration);
    String[] arrayOfString = { "canonical-form", "cdata-sections", "charset-overrides-xml-encoding", "infoset", "namespace-declarations", "split-cdata-sections", "supported-media-types-only", "certified", "well-formed", "ignore-unknown-character-denormalizations" };
    this.fConfiguration.addRecognizedFeatures(arrayOfString);
    this.fConfiguration.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
    this.fConfiguration.setFeature("namespace-declarations", true);
    this.fConfiguration.setFeature("well-formed", true);
    this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
    this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
    this.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", true);
    this.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", false);
    this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
    this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
    this.fConfiguration.setFeature("canonical-form", false);
    this.fConfiguration.setFeature("charset-overrides-xml-encoding", true);
    this.fConfiguration.setFeature("split-cdata-sections", true);
    this.fConfiguration.setFeature("supported-media-types-only", false);
    this.fConfiguration.setFeature("ignore-unknown-character-denormalizations", true);
    this.fConfiguration.setFeature("certified", true);
    try {
      this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
    } catch (XMLConfigurationException xMLConfigurationException) {}
  }
  
  public DOMParserImpl(SymbolTable paramSymbolTable) {
    this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", paramSymbolTable);
  }
  
  public DOMParserImpl(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", paramSymbolTable);
    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", paramXMLGrammarPool);
  }
  
  public void reset() {
    super.reset();
    this.fNamespaceDeclarations = this.fConfiguration.getFeature("namespace-declarations");
    if (this.fSkippedElemStack != null)
      this.fSkippedElemStack.removeAllElements(); 
    this.fSchemaLocations.clear();
    this.fRejectedElement.clear();
    this.fFilterReject = false;
    this.fSchemaType = null;
  }
  
  public DOMConfiguration getDomConfig() {
    return this;
  }
  
  public LSParserFilter getFilter() {
    return this.fDOMFilter;
  }
  
  public void setFilter(LSParserFilter paramLSParserFilter) {
    this.fDOMFilter = paramLSParserFilter;
    if (this.fSkippedElemStack == null)
      this.fSkippedElemStack = new Stack(); 
  }
  
  public void setParameter(String paramString, Object paramObject) throws DOMException {
    if (paramObject instanceof Boolean) {
      boolean bool = ((Boolean)paramObject).booleanValue();
      try {
        if (paramString.equalsIgnoreCase("comments")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", bool);
        } else if (paramString.equalsIgnoreCase("datatype-normalization")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", bool);
        } else if (paramString.equalsIgnoreCase("entities")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", bool);
        } else if (paramString.equalsIgnoreCase("disallow-doctype")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/disallow-doctype-decl", bool);
        } else if (paramString.equalsIgnoreCase("supported-media-types-only") || paramString.equalsIgnoreCase("normalize-characters") || paramString.equalsIgnoreCase("check-character-normalization") || paramString.equalsIgnoreCase("canonical-form")) {
          if (bool) {
            String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
            throw new DOMException((short)9, str);
          } 
        } else if (paramString.equalsIgnoreCase("namespaces")) {
          this.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", bool);
        } else if (paramString.equalsIgnoreCase("infoset")) {
          if (bool) {
            this.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", true);
            this.fConfiguration.setFeature("namespace-declarations", true);
            this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
            this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", false);
            this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
            this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
          } 
        } else if (paramString.equalsIgnoreCase("cdata-sections")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", bool);
        } else if (paramString.equalsIgnoreCase("namespace-declarations")) {
          this.fConfiguration.setFeature("namespace-declarations", bool);
        } else if (paramString.equalsIgnoreCase("well-formed") || paramString.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
          if (!bool) {
            String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
            throw new DOMException((short)9, str);
          } 
        } else if (paramString.equalsIgnoreCase("validate")) {
          this.fConfiguration.setFeature("http://xml.org/sax/features/validation", bool);
          if (this.fSchemaType != Constants.NS_DTD) {
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", bool);
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", bool);
          } 
          if (bool)
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", false); 
        } else if (paramString.equalsIgnoreCase("validate-if-schema")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", bool);
          if (bool)
            this.fConfiguration.setFeature("http://xml.org/sax/features/validation", false); 
        } else if (paramString.equalsIgnoreCase("element-content-whitespace")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", bool);
        } else if (paramString.equalsIgnoreCase("psvi")) {
          this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/augment-psvi", true);
          this.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", "org.apache.xerces.dom.PSVIDocumentImpl");
        } else {
          String str;
          if (paramString.equalsIgnoreCase("http://apache.org/xml/features/honour-all-schemaLocations")) {
            str = "http://apache.org/xml/features/honour-all-schemaLocations";
          } else {
            str = paramString.toLowerCase(Locale.ENGLISH);
          } 
          this.fConfiguration.setFeature(str, bool);
        } 
      } catch (XMLConfigurationException xMLConfigurationException) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { paramString });
        throw new DOMException((short)8, str);
      } 
    } else if (paramString.equalsIgnoreCase("error-handler")) {
      if (paramObject instanceof DOMErrorHandler || paramObject == null) {
        try {
          this.fErrorHandler = new DOMErrorHandlerWrapper((DOMErrorHandler)paramObject);
          this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", this.fErrorHandler);
        } catch (XMLConfigurationException xMLConfigurationException) {}
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { paramString });
        throw new DOMException((short)17, str);
      } 
    } else if (paramString.equalsIgnoreCase("resource-resolver")) {
      if (paramObject instanceof LSResourceResolver || paramObject == null) {
        try {
          this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper((LSResourceResolver)paramObject));
        } catch (XMLConfigurationException xMLConfigurationException) {}
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { paramString });
        throw new DOMException((short)17, str);
      } 
    } else if (paramString.equalsIgnoreCase("schema-location")) {
      if (paramObject instanceof String || paramObject == null) {
        try {
          if (paramObject == null) {
            this.fSchemaLocation = null;
            this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", null);
          } else {
            this.fSchemaLocation = (String)paramObject;
            StringTokenizer stringTokenizer = new StringTokenizer(this.fSchemaLocation, " \n\t\r");
            if (stringTokenizer.hasMoreTokens()) {
              this.fSchemaLocations.clear();
              this.fSchemaLocations.add(stringTokenizer.nextToken());
              while (stringTokenizer.hasMoreTokens())
                this.fSchemaLocations.add(stringTokenizer.nextToken()); 
              this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", this.fSchemaLocations.toArray());
            } else {
              this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", paramObject);
            } 
          } 
        } catch (XMLConfigurationException xMLConfigurationException) {}
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { paramString });
        throw new DOMException((short)17, str);
      } 
    } else if (paramString.equalsIgnoreCase("schema-type")) {
      if (paramObject instanceof String || paramObject == null) {
        try {
          if (paramObject == null) {
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", false);
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", false);
            this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", null);
            this.fSchemaType = null;
          } else if (paramObject.equals(Constants.NS_XMLSCHEMA)) {
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", true);
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
            this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_XMLSCHEMA);
            this.fSchemaType = Constants.NS_XMLSCHEMA;
          } else if (paramObject.equals(Constants.NS_DTD)) {
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", false);
            this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", false);
            this.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_DTD);
            this.fSchemaType = Constants.NS_DTD;
          } 
        } catch (XMLConfigurationException xMLConfigurationException) {}
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { paramString });
        throw new DOMException((short)17, str);
      } 
    } else if (paramString.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
      this.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", paramObject);
    } else {
      String str = paramString.toLowerCase(Locale.ENGLISH);
      try {
        this.fConfiguration.setProperty(str, paramObject);
        return;
      } catch (XMLConfigurationException xMLConfigurationException) {
        try {
          if (paramString.equalsIgnoreCase("http://apache.org/xml/features/honour-all-schemaLocations"))
            str = "http://apache.org/xml/features/honour-all-schemaLocations"; 
          this.fConfiguration.getFeature(str);
          String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { paramString });
          throw new DOMException((short)17, str1);
        } catch (XMLConfigurationException xMLConfigurationException1) {
          String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { paramString });
          throw new DOMException((short)8, str1);
        } 
      } 
    } 
  }
  
  public Object getParameter(String paramString) throws DOMException {
    String str;
    if (paramString.equalsIgnoreCase("comments"))
      return this.fConfiguration.getFeature("http://apache.org/xml/features/include-comments") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("datatype-normalization"))
      return this.fConfiguration.getFeature("http://apache.org/xml/features/validation/schema/normalized-value") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("entities"))
      return this.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("namespaces"))
      return this.fConfiguration.getFeature("http://xml.org/sax/features/namespaces") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("validate"))
      return this.fConfiguration.getFeature("http://xml.org/sax/features/validation") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("validate-if-schema"))
      return this.fConfiguration.getFeature("http://apache.org/xml/features/validation/dynamic") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("element-content-whitespace"))
      return this.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("disallow-doctype"))
      return this.fConfiguration.getFeature("http://apache.org/xml/features/disallow-doctype-decl") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("infoset")) {
      boolean bool = (this.fConfiguration.getFeature("http://xml.org/sax/features/namespaces") && this.fConfiguration.getFeature("namespace-declarations") && this.fConfiguration.getFeature("http://apache.org/xml/features/include-comments") && this.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace") && !this.fConfiguration.getFeature("http://apache.org/xml/features/validation/dynamic") && !this.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes") && !this.fConfiguration.getFeature("http://apache.org/xml/features/validation/schema/normalized-value") && !this.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes")) ? true : false;
      return bool ? Boolean.TRUE : Boolean.FALSE;
    } 
    if (paramString.equalsIgnoreCase("cdata-sections"))
      return this.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes") ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("check-character-normalization") || paramString.equalsIgnoreCase("normalize-characters"))
      return Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("namespace-declarations") || paramString.equalsIgnoreCase("well-formed") || paramString.equalsIgnoreCase("ignore-unknown-character-denormalizations") || paramString.equalsIgnoreCase("canonical-form") || paramString.equalsIgnoreCase("supported-media-types-only") || paramString.equalsIgnoreCase("split-cdata-sections") || paramString.equalsIgnoreCase("charset-overrides-xml-encoding"))
      return this.fConfiguration.getFeature(paramString.toLowerCase(Locale.ENGLISH)) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("error-handler"))
      return (this.fErrorHandler != null) ? this.fErrorHandler.getErrorHandler() : null; 
    if (paramString.equalsIgnoreCase("resource-resolver")) {
      try {
        XMLEntityResolver xMLEntityResolver = (XMLEntityResolver)this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
        if (xMLEntityResolver != null && xMLEntityResolver instanceof DOMEntityResolverWrapper)
          return ((DOMEntityResolverWrapper)xMLEntityResolver).getEntityResolver(); 
      } catch (XMLConfigurationException xMLConfigurationException) {}
      return null;
    } 
    if (paramString.equalsIgnoreCase("schema-type"))
      return this.fConfiguration.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage"); 
    if (paramString.equalsIgnoreCase("schema-location"))
      return this.fSchemaLocation; 
    if (paramString.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table"))
      return this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/symbol-table"); 
    if (paramString.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name"))
      return this.fConfiguration.getProperty("http://apache.org/xml/properties/dom/document-class-name"); 
    if (paramString.equalsIgnoreCase("http://apache.org/xml/features/honour-all-schemaLocations")) {
      str = "http://apache.org/xml/features/honour-all-schemaLocations";
    } else {
      str = paramString.toLowerCase(Locale.ENGLISH);
    } 
    try {
      return this.fConfiguration.getFeature(str) ? Boolean.TRUE : Boolean.FALSE;
    } catch (XMLConfigurationException xMLConfigurationException) {
      try {
        return this.fConfiguration.getProperty(str);
      } catch (XMLConfigurationException xMLConfigurationException1) {
        String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { paramString });
        throw new DOMException((short)8, str1);
      } 
    } 
  }
  
  public boolean canSetParameter(String paramString, Object paramObject) {
    if (paramObject == null)
      return true; 
    if (paramObject instanceof Boolean) {
      boolean bool = ((Boolean)paramObject).booleanValue();
      if (paramString.equalsIgnoreCase("supported-media-types-only") || paramString.equalsIgnoreCase("normalize-characters") || paramString.equalsIgnoreCase("check-character-normalization") || paramString.equalsIgnoreCase("canonical-form"))
        return !bool; 
      if (paramString.equalsIgnoreCase("well-formed") || paramString.equalsIgnoreCase("ignore-unknown-character-denormalizations"))
        return bool; 
      if (paramString.equalsIgnoreCase("cdata-sections") || paramString.equalsIgnoreCase("charset-overrides-xml-encoding") || paramString.equalsIgnoreCase("comments") || paramString.equalsIgnoreCase("datatype-normalization") || paramString.equalsIgnoreCase("disallow-doctype") || paramString.equalsIgnoreCase("entities") || paramString.equalsIgnoreCase("infoset") || paramString.equalsIgnoreCase("namespaces") || paramString.equalsIgnoreCase("namespace-declarations") || paramString.equalsIgnoreCase("validate") || paramString.equalsIgnoreCase("validate-if-schema") || paramString.equalsIgnoreCase("element-content-whitespace") || paramString.equalsIgnoreCase("xml-declaration"))
        return true; 
      try {
        String str;
        if (paramString.equalsIgnoreCase("http://apache.org/xml/features/honour-all-schemaLocations")) {
          str = "http://apache.org/xml/features/honour-all-schemaLocations";
        } else {
          str = paramString.toLowerCase(Locale.ENGLISH);
        } 
        this.fConfiguration.getFeature(str);
        return true;
      } catch (XMLConfigurationException xMLConfigurationException) {
        return false;
      } 
    } 
    if (paramString.equalsIgnoreCase("error-handler"))
      return (paramObject instanceof DOMErrorHandler || paramObject == null); 
    if (paramString.equalsIgnoreCase("resource-resolver"))
      return (paramObject instanceof LSResourceResolver || paramObject == null); 
    if (paramString.equalsIgnoreCase("schema-type"))
      return ((paramObject instanceof String && (paramObject.equals(Constants.NS_XMLSCHEMA) || paramObject.equals(Constants.NS_DTD))) || paramObject == null); 
    if (paramString.equalsIgnoreCase("schema-location"))
      return (paramObject instanceof String || paramObject == null); 
    if (paramString.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name"))
      return true; 
    try {
      this.fConfiguration.getProperty(paramString.toLowerCase(Locale.ENGLISH));
      return true;
    } catch (XMLConfigurationException xMLConfigurationException) {
      return false;
    } 
  }
  
  public DOMStringList getParameterNames() {
    if (this.fRecognizedParameters == null) {
      Vector vector = new Vector();
      vector.add("namespaces");
      vector.add("cdata-sections");
      vector.add("canonical-form");
      vector.add("namespace-declarations");
      vector.add("split-cdata-sections");
      vector.add("entities");
      vector.add("validate-if-schema");
      vector.add("validate");
      vector.add("datatype-normalization");
      vector.add("charset-overrides-xml-encoding");
      vector.add("check-character-normalization");
      vector.add("supported-media-types-only");
      vector.add("ignore-unknown-character-denormalizations");
      vector.add("normalize-characters");
      vector.add("well-formed");
      vector.add("infoset");
      vector.add("disallow-doctype");
      vector.add("element-content-whitespace");
      vector.add("comments");
      vector.add("error-handler");
      vector.add("resource-resolver");
      vector.add("schema-location");
      vector.add("schema-type");
      this.fRecognizedParameters = (DOMStringList)new DOMStringListImpl(vector);
    } 
    return this.fRecognizedParameters;
  }
  
  public Document parseURI(String paramString) throws LSException {
    if (this.fBusy) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_STATE_ERR", null);
      throw new DOMException((short)11, str);
    } 
    XMLInputSource xMLInputSource = new XMLInputSource(null, paramString, null);
    try {
      this.currentThread = Thread.currentThread();
      this.fBusy = true;
      parse(xMLInputSource);
      this.fBusy = false;
      if (this.abortNow && this.currentThread.isInterrupted()) {
        this.abortNow = false;
        Thread.interrupted();
      } 
    } catch (Exception exception) {
      this.fBusy = false;
      if (this.abortNow && this.currentThread.isInterrupted())
        Thread.interrupted(); 
      if (this.abortNow) {
        this.abortNow = false;
        restoreHandlers();
        return null;
      } 
      if (exception != AbstractDOMParser.ABORT) {
        if (!(exception instanceof org.apache.xerces.xni.parser.XMLParseException) && this.fErrorHandler != null) {
          DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
          dOMErrorImpl.fException = exception;
          dOMErrorImpl.fMessage = exception.getMessage();
          dOMErrorImpl.fSeverity = 3;
          this.fErrorHandler.getErrorHandler().handleError((DOMError)dOMErrorImpl);
        } 
        throw (LSException)DOMUtil.createLSException((short)81, exception).fillInStackTrace();
      } 
    } 
    Document document = getDocument();
    dropDocumentReferences();
    return document;
  }
  
  public Document parse(LSInput paramLSInput) throws LSException {
    XMLInputSource xMLInputSource = dom2xmlInputSource(paramLSInput);
    if (this.fBusy) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_STATE_ERR", null);
      throw new DOMException((short)11, str);
    } 
    try {
      this.currentThread = Thread.currentThread();
      this.fBusy = true;
      parse(xMLInputSource);
      this.fBusy = false;
      if (this.abortNow && this.currentThread.isInterrupted()) {
        this.abortNow = false;
        Thread.interrupted();
      } 
    } catch (Exception exception) {
      this.fBusy = false;
      if (this.abortNow && this.currentThread.isInterrupted())
        Thread.interrupted(); 
      if (this.abortNow) {
        this.abortNow = false;
        restoreHandlers();
        return null;
      } 
      if (exception != AbstractDOMParser.ABORT) {
        if (!(exception instanceof org.apache.xerces.xni.parser.XMLParseException) && this.fErrorHandler != null) {
          DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
          dOMErrorImpl.fException = exception;
          dOMErrorImpl.fMessage = exception.getMessage();
          dOMErrorImpl.fSeverity = 3;
          this.fErrorHandler.getErrorHandler().handleError((DOMError)dOMErrorImpl);
        } 
        throw (LSException)DOMUtil.createLSException((short)81, exception).fillInStackTrace();
      } 
    } 
    Document document = getDocument();
    dropDocumentReferences();
    return document;
  }
  
  private void restoreHandlers() {
    this.fConfiguration.setDocumentHandler(this);
    this.fConfiguration.setDTDHandler(this);
    this.fConfiguration.setDTDContentModelHandler(this);
  }
  
  public Node parseWithContext(LSInput paramLSInput, Node paramNode, short paramShort) throws DOMException, LSException {
    throw new DOMException((short)9, "Not supported");
  }
  
  XMLInputSource dom2xmlInputSource(LSInput paramLSInput) {
    XMLInputSource xMLInputSource = null;
    if (paramLSInput.getCharacterStream() != null) {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI(), paramLSInput.getCharacterStream(), "UTF-16");
    } else if (paramLSInput.getByteStream() != null) {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI(), paramLSInput.getByteStream(), paramLSInput.getEncoding());
    } else if (paramLSInput.getStringData() != null && paramLSInput.getStringData().length() > 0) {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI(), new StringReader(paramLSInput.getStringData()), "UTF-16");
    } else if ((paramLSInput.getSystemId() != null && paramLSInput.getSystemId().length() > 0) || (paramLSInput.getPublicId() != null && paramLSInput.getPublicId().length() > 0)) {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI());
    } else {
      if (this.fErrorHandler != null) {
        DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
        dOMErrorImpl.fType = "no-input-specified";
        dOMErrorImpl.fMessage = "no-input-specified";
        dOMErrorImpl.fSeverity = 3;
        this.fErrorHandler.getErrorHandler().handleError((DOMError)dOMErrorImpl);
      } 
      throw new LSException((short)81, "no-input-specified");
    } 
    return xMLInputSource;
  }
  
  public boolean getAsync() {
    return false;
  }
  
  public boolean getBusy() {
    return this.fBusy;
  }
  
  public void abort() {
    if (this.fBusy) {
      this.fBusy = false;
      if (this.currentThread != null) {
        this.abortNow = true;
        this.fConfiguration.setDocumentHandler(this.abortHandler);
        this.fConfiguration.setDTDHandler(this.abortHandler);
        this.fConfiguration.setDTDContentModelHandler(this.abortHandler);
        if (this.currentThread == Thread.currentThread())
          throw AbstractDOMParser.ABORT; 
        this.currentThread.interrupt();
      } 
    } 
  }
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) {
    if (!this.fNamespaceDeclarations && this.fNamespaceAware) {
      int i = paramXMLAttributes.getLength();
      for (int j = i - 1; j >= 0; j--) {
        if (XMLSymbols.PREFIX_XMLNS == paramXMLAttributes.getPrefix(j) || XMLSymbols.PREFIX_XMLNS == paramXMLAttributes.getQName(j))
          paramXMLAttributes.removeAttributeAt(j); 
      } 
    } 
    super.startElement(paramQName, paramXMLAttributes, paramAugmentations);
  }
  
  private static final class AbortHandler implements XMLDocumentHandler, XMLDTDHandler, XMLDTDContentModelHandler {
    private XMLDocumentSource documentSource;
    
    private XMLDTDContentModelSource dtdContentSource;
    
    private XMLDTDSource dtdSource;
    
    private AbortHandler() {}
    
    public void startDocument(XMLLocator param1XMLLocator, String param1String, NamespaceContext param1NamespaceContext, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void xmlDecl(String param1String1, String param1String2, String param1String3, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void doctypeDecl(String param1String1, String param1String2, String param1String3, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void comment(XMLString param1XMLString, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void processingInstruction(String param1String, XMLString param1XMLString, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startElement(QName param1QName, XMLAttributes param1XMLAttributes, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void emptyElement(QName param1QName, XMLAttributes param1XMLAttributes, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startGeneralEntity(String param1String1, XMLResourceIdentifier param1XMLResourceIdentifier, String param1String2, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void textDecl(String param1String1, String param1String2, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endGeneralEntity(String param1String, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void characters(XMLString param1XMLString, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void ignorableWhitespace(XMLString param1XMLString, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endElement(QName param1QName, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startCDATA(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endCDATA(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endDocument(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void setDocumentSource(XMLDocumentSource param1XMLDocumentSource) {
      this.documentSource = param1XMLDocumentSource;
    }
    
    public XMLDocumentSource getDocumentSource() {
      return this.documentSource;
    }
    
    public void startDTD(XMLLocator param1XMLLocator, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startParameterEntity(String param1String1, XMLResourceIdentifier param1XMLResourceIdentifier, String param1String2, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endParameterEntity(String param1String, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startExternalSubset(XMLResourceIdentifier param1XMLResourceIdentifier, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endExternalSubset(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void elementDecl(String param1String1, String param1String2, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startAttlist(String param1String, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void attributeDecl(String param1String1, String param1String2, String param1String3, String[] param1ArrayOfString, String param1String4, XMLString param1XMLString1, XMLString param1XMLString2, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endAttlist(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void internalEntityDecl(String param1String, XMLString param1XMLString1, XMLString param1XMLString2, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void externalEntityDecl(String param1String, XMLResourceIdentifier param1XMLResourceIdentifier, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void unparsedEntityDecl(String param1String1, XMLResourceIdentifier param1XMLResourceIdentifier, String param1String2, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void notationDecl(String param1String, XMLResourceIdentifier param1XMLResourceIdentifier, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startConditional(short param1Short, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void ignoredCharacters(XMLString param1XMLString, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endConditional(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endDTD(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void setDTDSource(XMLDTDSource param1XMLDTDSource) {
      this.dtdSource = param1XMLDTDSource;
    }
    
    public XMLDTDSource getDTDSource() {
      return this.dtdSource;
    }
    
    public void startContentModel(String param1String, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void any(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void empty(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void startGroup(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void pcdata(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void element(String param1String, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void separator(short param1Short, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void occurrence(short param1Short, Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endGroup(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void endContentModel(Augmentations param1Augmentations) throws XNIException {
      throw AbstractDOMParser.ABORT;
    }
    
    public void setDTDContentModelSource(XMLDTDContentModelSource param1XMLDTDContentModelSource) {
      this.dtdContentSource = param1XMLDTDContentModelSource;
    }
    
    public XMLDTDContentModelSource getDTDContentModelSource() {
      return this.dtdContentSource;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\DOMParserImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */