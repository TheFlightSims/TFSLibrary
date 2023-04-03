package org.apache.xerces.impl.xs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.WeakHashMap;
import org.apache.xerces.dom.DOMErrorImpl;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.dom.DOMStringListImpl;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.models.CMNodeFactory;
import org.apache.xerces.impl.xs.traversers.XSDHandler;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.apache.xerces.util.DOMErrorHandlerWrapper;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.util.ParserConfigurationSettings;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarLoader;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.grammars.XSGrammar;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xs.LSInputList;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSLoader;
import org.apache.xerces.xs.XSModel;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;

public class XMLSchemaLoader implements XMLGrammarLoader, XMLComponent, XSLoader, DOMConfiguration {
  protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
  
  protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
  
  protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
  
  protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
  
  protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
  
  protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
  
  protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
  
  protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
  
  protected static final String AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
  
  protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
  
  private static final String[] RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/validation/schema-full-checking", "http://apache.org/xml/features/validation/schema/augment-psvi", "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/standard-uri-conformant", "http://apache.org/xml/features/disallow-doctype-decl", "http://apache.org/xml/features/generate-synthetic-annotations", "http://apache.org/xml/features/validate-annotations", "http://apache.org/xml/features/honour-all-schemaLocations" };
  
  public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
  
  protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
  
  protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
  
  protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://apache.org/xml/properties/security-manager" };
  
  private ParserConfigurationSettings fLoaderConfig = new ParserConfigurationSettings();
  
  private XMLErrorReporter fErrorReporter = new XMLErrorReporter();
  
  private XMLEntityManager fEntityManager = null;
  
  private XMLEntityResolver fUserEntityResolver = null;
  
  private XMLGrammarPool fGrammarPool = null;
  
  private String fExternalSchemas = null;
  
  private String fExternalNoNSSchema = null;
  
  private Object fJAXPSource = null;
  
  private boolean fIsCheckedFully = false;
  
  private boolean fJAXPProcessed = false;
  
  private boolean fSettingsChanged = true;
  
  private XSDHandler fSchemaHandler;
  
  private XSGrammarBucket fGrammarBucket;
  
  private XSDeclarationPool fDeclPool = null;
  
  private SubstitutionGroupHandler fSubGroupHandler;
  
  private CMBuilder fCMBuilder;
  
  private XSDDescription fXSDDescription = new XSDDescription();
  
  private WeakHashMap fJAXPCache;
  
  private Locale fLocale = Locale.getDefault();
  
  private DOMStringList fRecognizedParameters = null;
  
  private DOMErrorHandlerWrapper fErrorHandler = null;
  
  private DOMEntityResolverWrapper fResourceResolver = null;
  
  public XMLSchemaLoader() {
    this(new SymbolTable(), null, new XMLEntityManager(), null, null, null);
  }
  
  public XMLSchemaLoader(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, null, new XMLEntityManager(), null, null, null);
  }
  
  XMLSchemaLoader(XMLErrorReporter paramXMLErrorReporter, XSGrammarBucket paramXSGrammarBucket, SubstitutionGroupHandler paramSubstitutionGroupHandler, CMBuilder paramCMBuilder) {
    this(null, paramXMLErrorReporter, null, paramXSGrammarBucket, paramSubstitutionGroupHandler, paramCMBuilder);
  }
  
  XMLSchemaLoader(SymbolTable paramSymbolTable, XMLErrorReporter paramXMLErrorReporter, XMLEntityManager paramXMLEntityManager, XSGrammarBucket paramXSGrammarBucket, SubstitutionGroupHandler paramSubstitutionGroupHandler, CMBuilder paramCMBuilder) {
    this.fLoaderConfig.addRecognizedFeatures(RECOGNIZED_FEATURES);
    this.fLoaderConfig.addRecognizedProperties(RECOGNIZED_PROPERTIES);
    if (paramSymbolTable != null)
      this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", paramSymbolTable); 
    if (paramXMLErrorReporter == null) {
      paramXMLErrorReporter = new XMLErrorReporter();
      paramXMLErrorReporter.setLocale(this.fLocale);
      paramXMLErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", new DefaultErrorHandler());
    } 
    this.fErrorReporter = paramXMLErrorReporter;
    if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null)
      this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter()); 
    this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
    this.fEntityManager = paramXMLEntityManager;
    if (this.fEntityManager != null)
      this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager); 
    this.fLoaderConfig.setFeature("http://apache.org/xml/features/validation/schema/augment-psvi", true);
    if (paramXSGrammarBucket == null)
      paramXSGrammarBucket = new XSGrammarBucket(); 
    this.fGrammarBucket = paramXSGrammarBucket;
    if (paramSubstitutionGroupHandler == null)
      paramSubstitutionGroupHandler = new SubstitutionGroupHandler(this.fGrammarBucket); 
    this.fSubGroupHandler = paramSubstitutionGroupHandler;
    CMNodeFactory cMNodeFactory = new CMNodeFactory();
    if (paramCMBuilder == null)
      paramCMBuilder = new CMBuilder(cMNodeFactory); 
    this.fCMBuilder = paramCMBuilder;
    this.fSchemaHandler = new XSDHandler(this.fGrammarBucket);
    this.fDeclPool = new XSDeclarationPool();
    this.fJAXPCache = new WeakHashMap();
    this.fSettingsChanged = true;
  }
  
  public String[] getRecognizedFeatures() {
    return (String[])RECOGNIZED_FEATURES.clone();
  }
  
  public boolean getFeature(String paramString) throws XMLConfigurationException {
    return this.fLoaderConfig.getFeature(paramString);
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    this.fSettingsChanged = true;
    if (paramString.equals("http://apache.org/xml/features/continue-after-fatal-error")) {
      this.fErrorReporter.setFeature("http://apache.org/xml/features/continue-after-fatal-error", paramBoolean);
    } else if (paramString.equals("http://apache.org/xml/features/generate-synthetic-annotations")) {
      this.fSchemaHandler.setGenerateSyntheticAnnotations(paramBoolean);
    } 
    this.fLoaderConfig.setFeature(paramString, paramBoolean);
  }
  
  public String[] getRecognizedProperties() {
    return (String[])RECOGNIZED_PROPERTIES.clone();
  }
  
  public Object getProperty(String paramString) throws XMLConfigurationException {
    return this.fLoaderConfig.getProperty(paramString);
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    this.fSettingsChanged = true;
    this.fLoaderConfig.setProperty(paramString, paramObject);
    if (paramString.equals("http://java.sun.com/xml/jaxp/properties/schemaSource")) {
      this.fJAXPSource = paramObject;
      this.fJAXPProcessed = false;
    } else if (paramString.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
      this.fGrammarPool = (XMLGrammarPool)paramObject;
    } else if (paramString.equals("http://apache.org/xml/properties/schema/external-schemaLocation")) {
      this.fExternalSchemas = (String)paramObject;
    } else if (paramString.equals("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation")) {
      this.fExternalNoNSSchema = (String)paramObject;
    } else if (paramString.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
      this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", paramObject);
    } else if (paramString.equals("http://apache.org/xml/properties/internal/error-reporter")) {
      this.fErrorReporter = (XMLErrorReporter)paramObject;
      if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null)
        this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter()); 
    } 
  }
  
  public void setLocale(Locale paramLocale) {
    this.fLocale = paramLocale;
    this.fErrorReporter.setLocale(paramLocale);
  }
  
  public Locale getLocale() {
    return this.fLocale;
  }
  
  public void setErrorHandler(XMLErrorHandler paramXMLErrorHandler) {
    this.fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", paramXMLErrorHandler);
  }
  
  public XMLErrorHandler getErrorHandler() {
    return this.fErrorReporter.getErrorHandler();
  }
  
  public void setEntityResolver(XMLEntityResolver paramXMLEntityResolver) {
    this.fUserEntityResolver = paramXMLEntityResolver;
    this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", paramXMLEntityResolver);
    this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", paramXMLEntityResolver);
  }
  
  public XMLEntityResolver getEntityResolver() {
    return this.fUserEntityResolver;
  }
  
  public void loadGrammar(XMLInputSource[] paramArrayOfXMLInputSource) throws IOException, XNIException {
    int i = paramArrayOfXMLInputSource.length;
    for (byte b = 0; b < i; b++)
      loadGrammar(paramArrayOfXMLInputSource[b]); 
  }
  
  public Grammar loadGrammar(XMLInputSource paramXMLInputSource) throws IOException, XNIException {
    reset((XMLComponentManager)this.fLoaderConfig);
    this.fSettingsChanged = false;
    XSDDescription xSDDescription = new XSDDescription();
    xSDDescription.fContextType = 3;
    xSDDescription.setBaseSystemId(paramXMLInputSource.getBaseSystemId());
    xSDDescription.setLiteralSystemId(paramXMLInputSource.getSystemId());
    Hashtable hashtable = new Hashtable();
    processExternalHints(this.fExternalSchemas, this.fExternalNoNSSchema, hashtable, this.fErrorReporter);
    SchemaGrammar schemaGrammar = loadSchema(xSDDescription, paramXMLInputSource, hashtable);
    if (schemaGrammar != null && this.fGrammarPool != null) {
      this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", (Grammar[])this.fGrammarBucket.getGrammars());
      if (this.fIsCheckedFully && this.fJAXPCache.get(schemaGrammar) != schemaGrammar)
        XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter); 
    } 
    return (Grammar)schemaGrammar;
  }
  
  SchemaGrammar loadSchema(XSDDescription paramXSDDescription, XMLInputSource paramXMLInputSource, Hashtable paramHashtable) throws IOException, XNIException {
    if (!this.fJAXPProcessed)
      processJAXPSchemaSource(paramHashtable); 
    return this.fSchemaHandler.parseSchema(paramXMLInputSource, paramXSDDescription, paramHashtable);
  }
  
  public static XMLInputSource resolveDocument(XSDDescription paramXSDDescription, Hashtable paramHashtable, XMLEntityResolver paramXMLEntityResolver) throws IOException {
    String str1 = null;
    if (paramXSDDescription.getContextType() == 2 || paramXSDDescription.fromInstance()) {
      String str3 = paramXSDDescription.getTargetNamespace();
      String str4 = (str3 == null) ? XMLSymbols.EMPTY_STRING : str3;
      LocationArray locationArray = (LocationArray)paramHashtable.get(str4);
      if (locationArray != null)
        str1 = locationArray.getFirstLocation(); 
    } 
    if (str1 == null) {
      String[] arrayOfString = paramXSDDescription.getLocationHints();
      if (arrayOfString != null && arrayOfString.length > 0)
        str1 = arrayOfString[0]; 
    } 
    String str2 = XMLEntityManager.expandSystemId(str1, paramXSDDescription.getBaseSystemId(), false);
    paramXSDDescription.setLiteralSystemId(str1);
    paramXSDDescription.setExpandedSystemId(str2);
    return paramXMLEntityResolver.resolveEntity((XMLResourceIdentifier)paramXSDDescription);
  }
  
  public static void processExternalHints(String paramString1, String paramString2, Hashtable paramHashtable, XMLErrorReporter paramXMLErrorReporter) {
    if (paramString1 != null)
      try {
        XSAttributeDecl xSAttributeDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION);
        xSAttributeDecl.fType.validate(paramString1, null, null);
        if (!tokenizeSchemaLocationStr(paramString1, paramHashtable))
          paramXMLErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "SchemaLocation", new Object[] { paramString1 }, (short)0); 
      } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
        paramXMLErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs(), (short)0);
      }  
    if (paramString2 != null)
      try {
        XSAttributeDecl xSAttributeDecl = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
        xSAttributeDecl.fType.validate(paramString2, null, null);
        LocationArray locationArray = (LocationArray)paramHashtable.get(XMLSymbols.EMPTY_STRING);
        if (locationArray == null) {
          locationArray = new LocationArray();
          paramHashtable.put(XMLSymbols.EMPTY_STRING, locationArray);
        } 
        locationArray.addLocation(paramString2);
      } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
        paramXMLErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs(), (short)0);
      }  
  }
  
  public static boolean tokenizeSchemaLocationStr(String paramString, Hashtable paramHashtable) {
    if (paramString != null) {
      StringTokenizer stringTokenizer = new StringTokenizer(paramString, " \n\t\r");
      while (stringTokenizer.hasMoreTokens()) {
        String str1 = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens())
          return false; 
        String str2 = stringTokenizer.nextToken();
        LocationArray locationArray = (LocationArray)paramHashtable.get(str1);
        if (locationArray == null) {
          locationArray = new LocationArray();
          paramHashtable.put(str1, locationArray);
        } 
        locationArray.addLocation(str2);
      } 
    } 
    return true;
  }
  
  private void processJAXPSchemaSource(Hashtable paramHashtable) throws IOException {
    this.fJAXPProcessed = true;
    if (this.fJAXPSource == null)
      return; 
    Class clazz = this.fJAXPSource.getClass().getComponentType();
    XMLInputSource xMLInputSource = null;
    String str = null;
    if (clazz == null) {
      if (this.fJAXPSource instanceof InputStream || this.fJAXPSource instanceof InputSource) {
        SchemaGrammar schemaGrammar1 = (SchemaGrammar)this.fJAXPCache.get(this.fJAXPSource);
        if (schemaGrammar1 != null) {
          this.fGrammarBucket.putGrammar(schemaGrammar1);
          return;
        } 
      } 
      this.fXSDDescription.reset();
      xMLInputSource = xsdToXMLInputSource(this.fJAXPSource);
      str = xMLInputSource.getSystemId();
      this.fXSDDescription.fContextType = 3;
      if (str != null) {
        this.fXSDDescription.setBaseSystemId(xMLInputSource.getBaseSystemId());
        this.fXSDDescription.setLiteralSystemId(str);
        this.fXSDDescription.setExpandedSystemId(str);
        this.fXSDDescription.fLocationHints = new String[] { str };
      } 
      SchemaGrammar schemaGrammar = loadSchema(this.fXSDDescription, xMLInputSource, paramHashtable);
      if (schemaGrammar != null) {
        if (this.fJAXPSource instanceof InputStream || this.fJAXPSource instanceof InputSource) {
          this.fJAXPCache.put(this.fJAXPSource, schemaGrammar);
          if (this.fIsCheckedFully)
            XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter); 
        } 
        this.fGrammarBucket.putGrammar(schemaGrammar);
      } 
      return;
    } 
    if (clazz != Object.class && clazz != String.class && clazz != File.class && clazz != InputStream.class && clazz != InputSource.class && !File.class.isAssignableFrom(clazz) && !InputStream.class.isAssignableFrom(clazz) && !InputSource.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
      MessageFormatter messageFormatter = this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1");
      throw new XMLConfigurationException((short)1, messageFormatter.formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-type.2", new Object[] { clazz.getName() }));
    } 
    Object[] arrayOfObject = (Object[])this.fJAXPSource;
    ArrayList arrayList = new ArrayList();
    for (byte b = 0; b < arrayOfObject.length; b++) {
      if (arrayOfObject[b] instanceof InputStream || arrayOfObject[b] instanceof InputSource) {
        SchemaGrammar schemaGrammar1 = (SchemaGrammar)this.fJAXPCache.get(arrayOfObject[b]);
        if (schemaGrammar1 != null) {
          this.fGrammarBucket.putGrammar(schemaGrammar1);
          continue;
        } 
      } 
      this.fXSDDescription.reset();
      xMLInputSource = xsdToXMLInputSource(arrayOfObject[b]);
      str = xMLInputSource.getSystemId();
      this.fXSDDescription.fContextType = 3;
      if (str != null) {
        this.fXSDDescription.setBaseSystemId(xMLInputSource.getBaseSystemId());
        this.fXSDDescription.setLiteralSystemId(str);
        this.fXSDDescription.setExpandedSystemId(str);
        this.fXSDDescription.fLocationHints = new String[] { str };
      } 
      String str1 = null;
      SchemaGrammar schemaGrammar = this.fSchemaHandler.parseSchema(xMLInputSource, this.fXSDDescription, paramHashtable);
      if (this.fIsCheckedFully)
        XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter); 
      if (schemaGrammar != null) {
        str1 = schemaGrammar.getTargetNamespace();
        if (arrayList.contains(str1)) {
          MessageFormatter messageFormatter = this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1");
          throw new IllegalArgumentException(messageFormatter.formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-ns", null));
        } 
        arrayList.add(str1);
        if (arrayOfObject[b] instanceof InputStream || arrayOfObject[b] instanceof InputSource)
          this.fJAXPCache.put(arrayOfObject[b], schemaGrammar); 
        this.fGrammarBucket.putGrammar(schemaGrammar);
      } 
      continue;
    } 
  }
  
  private XMLInputSource xsdToXMLInputSource(Object paramObject) {
    if (paramObject instanceof String) {
      String str = (String)paramObject;
      this.fXSDDescription.reset();
      this.fXSDDescription.setValues(null, str, null, null);
      XMLInputSource xMLInputSource = null;
      try {
        xMLInputSource = this.fEntityManager.resolveEntity((XMLResourceIdentifier)this.fXSDDescription);
      } catch (IOException iOException) {
        this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { str }, (short)1);
      } 
      return (xMLInputSource == null) ? new XMLInputSource(null, str, null) : xMLInputSource;
    } 
    if (paramObject instanceof InputSource)
      return saxToXMLInputSource((InputSource)paramObject); 
    if (paramObject instanceof InputStream)
      return new XMLInputSource(null, null, null, (InputStream)paramObject, null); 
    if (paramObject instanceof File) {
      File file = (File)paramObject;
      BufferedInputStream bufferedInputStream = null;
      try {
        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
      } catch (FileNotFoundException fileNotFoundException) {
        this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { file.toString() }, (short)1);
      } 
      return new XMLInputSource(null, null, null, bufferedInputStream, null);
    } 
    MessageFormatter messageFormatter = this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1");
    throw new XMLConfigurationException((short)1, messageFormatter.formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-type.1", new Object[] { (paramObject != null) ? paramObject.getClass().getName() : "null" }));
  }
  
  private static XMLInputSource saxToXMLInputSource(InputSource paramInputSource) {
    String str1 = paramInputSource.getPublicId();
    String str2 = paramInputSource.getSystemId();
    Reader reader = paramInputSource.getCharacterStream();
    if (reader != null)
      return new XMLInputSource(str1, str2, null, reader, null); 
    InputStream inputStream = paramInputSource.getByteStream();
    return (inputStream != null) ? new XMLInputSource(str1, str2, null, inputStream, paramInputSource.getEncoding()) : new XMLInputSource(str1, str2, null);
  }
  
  public Boolean getFeatureDefault(String paramString) {
    return paramString.equals("http://apache.org/xml/features/validation/schema/augment-psvi") ? Boolean.TRUE : null;
  }
  
  public Object getPropertyDefault(String paramString) {
    return null;
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException {
    this.fGrammarBucket.reset();
    this.fSubGroupHandler.reset();
    if (!this.fSettingsChanged || !parserSettingsUpdated(paramXMLComponentManager)) {
      this.fJAXPProcessed = false;
      initGrammarBucket();
      return;
    } 
    this.fEntityManager = (XMLEntityManager)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
    this.fErrorReporter = (XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    boolean bool = true;
    try {
      bool = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema/augment-psvi");
    } catch (XMLConfigurationException xMLConfigurationException) {
      bool = false;
    } 
    if (!bool) {
      this.fDeclPool.reset();
      this.fCMBuilder.setDeclPool(this.fDeclPool);
      this.fSchemaHandler.setDeclPool(this.fDeclPool);
    } else {
      this.fCMBuilder.setDeclPool(null);
      this.fSchemaHandler.setDeclPool(null);
    } 
    try {
      this.fExternalSchemas = (String)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
      this.fExternalNoNSSchema = (String)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fExternalSchemas = null;
      this.fExternalNoNSSchema = null;
    } 
    try {
      this.fJAXPSource = paramXMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
      this.fJAXPProcessed = false;
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fJAXPSource = null;
      this.fJAXPProcessed = false;
    } 
    try {
      this.fGrammarPool = (XMLGrammarPool)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fGrammarPool = null;
    } 
    initGrammarBucket();
    try {
      boolean bool1 = paramXMLComponentManager.getFeature("http://apache.org/xml/features/continue-after-fatal-error");
      this.fErrorReporter.setFeature("http://apache.org/xml/features/continue-after-fatal-error", bool1);
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fIsCheckedFully = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema-full-checking");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fIsCheckedFully = false;
    } 
    try {
      this.fSchemaHandler.setGenerateSyntheticAnnotations(paramXMLComponentManager.getFeature("http://apache.org/xml/features/generate-synthetic-annotations"));
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fSchemaHandler.setGenerateSyntheticAnnotations(false);
    } 
    this.fSchemaHandler.reset(paramXMLComponentManager);
  }
  
  private boolean parserSettingsUpdated(XMLComponentManager paramXMLComponentManager) {
    try {
      return paramXMLComponentManager.getFeature("http://apache.org/xml/features/internal/parser-settings");
    } catch (XMLConfigurationException xMLConfigurationException) {
      return true;
    } 
  }
  
  private void initGrammarBucket() {
    if (this.fGrammarPool != null) {
      Grammar[] arrayOfGrammar = this.fGrammarPool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema");
      for (byte b = 0; b < arrayOfGrammar.length; b++) {
        if (!this.fGrammarBucket.putGrammar((SchemaGrammar)arrayOfGrammar[b], true))
          this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "GrammarConflict", null, (short)0); 
      } 
    } 
  }
  
  public DOMConfiguration getConfig() {
    return this;
  }
  
  public XSModel load(LSInput paramLSInput) {
    try {
      Grammar grammar = loadGrammar(dom2xmlInputSource(paramLSInput));
      return ((XSGrammar)grammar).toXSModel();
    } catch (Exception exception) {
      reportDOMFatalError(exception);
      return null;
    } 
  }
  
  public XSModel loadInputList(LSInputList paramLSInputList) {
    int i = paramLSInputList.getLength();
    if (i == 0)
      return null; 
    SchemaGrammar[] arrayOfSchemaGrammar = new SchemaGrammar[i];
    for (byte b = 0; b < i; b++) {
      try {
        arrayOfSchemaGrammar[b] = (SchemaGrammar)loadGrammar(dom2xmlInputSource(paramLSInputList.item(b)));
      } catch (Exception exception) {
        reportDOMFatalError(exception);
        return null;
      } 
    } 
    return new XSModelImpl(arrayOfSchemaGrammar);
  }
  
  public XSModel loadURI(String paramString) {
    try {
      Grammar grammar = loadGrammar(new XMLInputSource(null, paramString, null));
      return ((XSGrammar)grammar).toXSModel();
    } catch (Exception exception) {
      reportDOMFatalError(exception);
      return null;
    } 
  }
  
  public XSModel loadURIList(StringList paramStringList) {
    int i = paramStringList.getLength();
    if (i == 0)
      return null; 
    SchemaGrammar[] arrayOfSchemaGrammar = new SchemaGrammar[i];
    for (byte b = 0; b < i; b++) {
      try {
        arrayOfSchemaGrammar[b] = (SchemaGrammar)loadGrammar(new XMLInputSource(null, paramStringList.item(b), null));
      } catch (Exception exception) {
        reportDOMFatalError(exception);
        return null;
      } 
    } 
    return new XSModelImpl(arrayOfSchemaGrammar);
  }
  
  void reportDOMFatalError(Exception paramException) {
    if (this.fErrorHandler != null) {
      DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
      dOMErrorImpl.fException = paramException;
      dOMErrorImpl.fMessage = paramException.getMessage();
      dOMErrorImpl.fSeverity = 3;
      this.fErrorHandler.getErrorHandler().handleError((DOMError)dOMErrorImpl);
    } 
  }
  
  public boolean canSetParameter(String paramString, Object paramObject) {
    return (paramObject instanceof Boolean) ? ((paramString.equals("validate") || paramString.equals("http://apache.org/xml/features/validation/schema-full-checking") || paramString.equals("http://apache.org/xml/features/validate-annotations") || paramString.equals("http://apache.org/xml/features/continue-after-fatal-error") || paramString.equals("http://apache.org/xml/features/allow-java-encodings") || paramString.equals("http://apache.org/xml/features/standard-uri-conformant") || paramString.equals("http://apache.org/xml/features/generate-synthetic-annotations") || paramString.equals("http://apache.org/xml/features/honour-all-schemaLocations"))) : ((paramString.equals("error-handler") || paramString.equals("resource-resolver") || paramString.equals("http://apache.org/xml/properties/internal/symbol-table") || paramString.equals("http://apache.org/xml/properties/internal/error-reporter") || paramString.equals("http://apache.org/xml/properties/internal/error-handler") || paramString.equals("http://apache.org/xml/properties/internal/entity-resolver") || paramString.equals("http://apache.org/xml/properties/internal/grammar-pool") || paramString.equals("http://apache.org/xml/properties/schema/external-schemaLocation") || paramString.equals("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation") || paramString.equals("http://java.sun.com/xml/jaxp/properties/schemaSource")));
  }
  
  public Object getParameter(String paramString) throws DOMException {
    if (paramString.equals("error-handler"))
      return (this.fErrorHandler != null) ? this.fErrorHandler.getErrorHandler() : null; 
    if (paramString.equals("resource-resolver"))
      return (this.fResourceResolver != null) ? this.fResourceResolver.getEntityResolver() : null; 
    try {
      boolean bool = getFeature(paramString);
      return bool ? Boolean.TRUE : Boolean.FALSE;
    } catch (Exception exception) {
      try {
        return getProperty(paramString);
      } catch (Exception exception1) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
        throw new DOMException((short)9, str);
      } 
    } 
  }
  
  public DOMStringList getParameterNames() {
    if (this.fRecognizedParameters == null) {
      Vector vector = new Vector();
      vector.add("validate");
      vector.add("error-handler");
      vector.add("resource-resolver");
      vector.add("http://apache.org/xml/properties/internal/symbol-table");
      vector.add("http://apache.org/xml/properties/internal/error-reporter");
      vector.add("http://apache.org/xml/properties/internal/error-handler");
      vector.add("http://apache.org/xml/properties/internal/entity-resolver");
      vector.add("http://apache.org/xml/properties/internal/grammar-pool");
      vector.add("http://apache.org/xml/properties/schema/external-schemaLocation");
      vector.add("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation");
      vector.add("http://java.sun.com/xml/jaxp/properties/schemaSource");
      vector.add("http://apache.org/xml/features/validation/schema-full-checking");
      vector.add("http://apache.org/xml/features/continue-after-fatal-error");
      vector.add("http://apache.org/xml/features/allow-java-encodings");
      vector.add("http://apache.org/xml/features/standard-uri-conformant");
      vector.add("http://apache.org/xml/features/validate-annotations");
      vector.add("http://apache.org/xml/features/generate-synthetic-annotations");
      vector.add("http://apache.org/xml/features/honour-all-schemaLocations");
      this.fRecognizedParameters = (DOMStringList)new DOMStringListImpl(vector);
    } 
    return this.fRecognizedParameters;
  }
  
  public void setParameter(String paramString, Object paramObject) throws DOMException {
    if (paramObject instanceof Boolean) {
      boolean bool = ((Boolean)paramObject).booleanValue();
      if (paramString.equals("validate") && bool)
        return; 
      try {
        setFeature(paramString, bool);
      } catch (Exception exception) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
        throw new DOMException((short)9, str);
      } 
      return;
    } 
    if (paramString.equals("error-handler")) {
      if (paramObject instanceof DOMErrorHandler) {
        try {
          this.fErrorHandler = new DOMErrorHandlerWrapper((DOMErrorHandler)paramObject);
          setErrorHandler((XMLErrorHandler)this.fErrorHandler);
        } catch (XMLConfigurationException xMLConfigurationException) {}
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
        throw new DOMException((short)9, str);
      } 
      return;
    } 
    if (paramString.equals("resource-resolver")) {
      if (paramObject instanceof LSResourceResolver) {
        try {
          this.fResourceResolver = new DOMEntityResolverWrapper((LSResourceResolver)paramObject);
          setEntityResolver((XMLEntityResolver)this.fResourceResolver);
        } catch (XMLConfigurationException xMLConfigurationException) {}
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
        throw new DOMException((short)9, str);
      } 
      return;
    } 
    try {
      setProperty(paramString, paramObject);
    } catch (Exception exception) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
      throw new DOMException((short)9, str);
    } 
  }
  
  XMLInputSource dom2xmlInputSource(LSInput paramLSInput) {
    XMLInputSource xMLInputSource = null;
    if (paramLSInput.getCharacterStream() != null) {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI(), paramLSInput.getCharacterStream(), "UTF-16");
    } else if (paramLSInput.getByteStream() != null) {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI(), paramLSInput.getByteStream(), paramLSInput.getEncoding());
    } else if (paramLSInput.getStringData() != null && paramLSInput.getStringData().length() != 0) {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI(), new StringReader(paramLSInput.getStringData()), "UTF-16");
    } else {
      xMLInputSource = new XMLInputSource(paramLSInput.getPublicId(), paramLSInput.getSystemId(), paramLSInput.getBaseURI());
    } 
    return xMLInputSource;
  }
  
  static class LocationArray {
    int length;
    
    String[] locations = new String[2];
    
    public void resize(int param1Int1, int param1Int2) {
      String[] arrayOfString = new String[param1Int2];
      System.arraycopy(this.locations, 0, arrayOfString, 0, Math.min(param1Int1, param1Int2));
      this.locations = arrayOfString;
      this.length = Math.min(param1Int1, param1Int2);
    }
    
    public void addLocation(String param1String) {
      if (this.length >= this.locations.length)
        resize(this.length, Math.max(1, this.length * 2)); 
      this.locations[this.length++] = param1String;
    }
    
    public String[] getLocationArray() {
      if (this.length < this.locations.length)
        resize(this.locations.length, this.length); 
      return this.locations;
    }
    
    public String getFirstLocation() {
      return (this.length > 0) ? this.locations[0] : null;
    }
    
    public int getLength() {
      return this.length;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XMLSchemaLoader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */