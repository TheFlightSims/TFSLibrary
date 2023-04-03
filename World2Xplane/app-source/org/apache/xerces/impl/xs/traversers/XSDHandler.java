package org.apache.xerces.impl.xs.traversers;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.impl.xs.XSAttributeDecl;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSDDescription;
import org.apache.xerces.impl.xs.XSDeclarationPool;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSGrammarBucket;
import org.apache.xerces.impl.xs.XSGroupDecl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSNotationDecl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.opti.ElementImpl;
import org.apache.xerces.impl.xs.opti.SchemaDOM;
import org.apache.xerces.impl.xs.opti.SchemaDOMParser;
import org.apache.xerces.impl.xs.opti.SchemaParsingConfig;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import org.apache.xerces.parsers.SAXParser;
import org.apache.xerces.parsers.XML11Configuration;
import org.apache.xerces.util.DOMInputSource;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.util.SAXInputSource;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.URI;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.grammars.XMLSchemaDescription;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSParticle;
import org.apache.xerces.xs.XSTypeDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XSDHandler {
  protected static final String VALIDATION = "http://xml.org/sax/features/validation";
  
  protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
  
  protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
  
  protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
  
  protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
  
  protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
  
  protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
  
  protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
  
  protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
  
  private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
  
  protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
  
  protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
  
  public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
  
  public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
  
  public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
  
  protected static final boolean DEBUG_NODE_POOL = false;
  
  static final int ATTRIBUTE_TYPE = 1;
  
  static final int ATTRIBUTEGROUP_TYPE = 2;
  
  static final int ELEMENT_TYPE = 3;
  
  static final int GROUP_TYPE = 4;
  
  static final int IDENTITYCONSTRAINT_TYPE = 5;
  
  static final int NOTATION_TYPE = 6;
  
  static final int TYPEDECL_TYPE = 7;
  
  public static final String REDEF_IDENTIFIER = "_fn3dktizrknc9pi";
  
  protected Hashtable fNotationRegistry = new Hashtable();
  
  protected XSDeclarationPool fDeclPool = null;
  
  private Hashtable fUnparsedAttributeRegistry = new Hashtable();
  
  private Hashtable fUnparsedAttributeGroupRegistry = new Hashtable();
  
  private Hashtable fUnparsedElementRegistry = new Hashtable();
  
  private Hashtable fUnparsedGroupRegistry = new Hashtable();
  
  private Hashtable fUnparsedIdentityConstraintRegistry = new Hashtable();
  
  private Hashtable fUnparsedNotationRegistry = new Hashtable();
  
  private Hashtable fUnparsedTypeRegistry = new Hashtable();
  
  private Hashtable fUnparsedAttributeRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedAttributeGroupRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedElementRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedGroupRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedIdentityConstraintRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedNotationRegistrySub = new Hashtable();
  
  private Hashtable fUnparsedTypeRegistrySub = new Hashtable();
  
  private Hashtable fXSDocumentInfoRegistry = new Hashtable();
  
  private Hashtable fDependencyMap = new Hashtable();
  
  private Hashtable fImportMap = new Hashtable();
  
  private Vector fAllTNSs = new Vector();
  
  private Hashtable fLocationPairs = null;
  
  private static final Hashtable EMPTY_TABLE = new Hashtable();
  
  Hashtable fHiddenNodes = null;
  
  private Hashtable fTraversed = new Hashtable();
  
  private Hashtable fDoc2SystemId = new Hashtable();
  
  private XSDocumentInfo fRoot = null;
  
  private Hashtable fDoc2XSDocumentMap = new Hashtable();
  
  private Hashtable fRedefine2XSDMap = new Hashtable();
  
  private Hashtable fRedefine2NSSupport = new Hashtable();
  
  private Hashtable fRedefinedRestrictedAttributeGroupRegistry = new Hashtable();
  
  private Hashtable fRedefinedRestrictedGroupRegistry = new Hashtable();
  
  private boolean fLastSchemaWasDuplicate;
  
  private boolean fValidateAnnotations = false;
  
  private boolean fHonourAllSchemaLocations = false;
  
  private XMLErrorReporter fErrorReporter;
  
  private XMLEntityResolver fEntityResolver;
  
  private XSAttributeChecker fAttributeChecker;
  
  private SymbolTable fSymbolTable;
  
  private XSGrammarBucket fGrammarBucket;
  
  private XSDDescription fSchemaGrammarDescription;
  
  private XMLGrammarPool fGrammarPool;
  
  XSDAttributeGroupTraverser fAttributeGroupTraverser;
  
  XSDAttributeTraverser fAttributeTraverser;
  
  XSDComplexTypeTraverser fComplexTypeTraverser;
  
  XSDElementTraverser fElementTraverser;
  
  XSDGroupTraverser fGroupTraverser;
  
  XSDKeyrefTraverser fKeyrefTraverser;
  
  XSDNotationTraverser fNotationTraverser;
  
  XSDSimpleTypeTraverser fSimpleTypeTraverser;
  
  XSDUniqueOrKeyTraverser fUniqueOrKeyTraverser;
  
  XSDWildcardTraverser fWildCardTraverser;
  
  SchemaDOMParser fSchemaParser;
  
  SchemaContentHandler fXSContentHandler;
  
  XML11Configuration fAnnotationValidator;
  
  XSAnnotationGrammarPool fGrammarBucketAdapter;
  
  private static final int INIT_STACK_SIZE = 30;
  
  private static final int INC_STACK_SIZE = 10;
  
  private int fLocalElemStackPos = 0;
  
  private XSParticleDecl[] fParticle = new XSParticleDecl[30];
  
  private Element[] fLocalElementDecl = new Element[30];
  
  private XSDocumentInfo[] fLocalElementDecl_schema = new XSDocumentInfo[30];
  
  private int[] fAllContext = new int[30];
  
  private XSObject[] fParent = new XSObject[30];
  
  private String[][] fLocalElemNamespaceContext = new String[30][1];
  
  private static final int INIT_KEYREF_STACK = 2;
  
  private static final int INC_KEYREF_STACK_AMOUNT = 2;
  
  private int fKeyrefStackPos = 0;
  
  private Element[] fKeyrefs = new Element[2];
  
  private XSDocumentInfo[] fKeyrefsMapXSDocumentInfo = new XSDocumentInfo[2];
  
  private XSElementDecl[] fKeyrefElems = new XSElementDecl[2];
  
  private String[][] fKeyrefNamespaceContext = new String[2][1];
  
  private static final String[][] NS_ERROR_CODES = new String[][] { { "src-include.2.1", "src-include.2.1" }, { "src-redefine.3.1", "src-redefine.3.1" }, { "src-import.3.1", "src-import.3.2" }, null, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" } };
  
  private static final String[] ELE_ERROR_CODES = new String[] { "src-include.1", "src-redefine.2", "src-import.2", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4" };
  
  private Vector fReportedTNS = null;
  
  private static final String[] COMP_TYPE = new String[] { null, "attribute declaration", "attribute group", "element declaration", "group", "identity constraint", "notation", "type definition" };
  
  private static final String[] CIRCULAR_CODES = new String[] { "Internal-Error", "Internal-Error", "src-attribute_group.3", "e-props-correct.6", "mg-props-correct.2", "Internal-Error", "Internal-Error", "st-props-correct.2" };
  
  private SimpleLocator xl = new SimpleLocator();
  
  private String null2EmptyString(String paramString) {
    return (paramString == null) ? XMLSymbols.EMPTY_STRING : paramString;
  }
  
  private String emptyString2Null(String paramString) {
    return (paramString == XMLSymbols.EMPTY_STRING) ? null : paramString;
  }
  
  private String doc2SystemId(Element paramElement) {
    String str = null;
    if (paramElement.getOwnerDocument() instanceof SchemaDOM)
      str = ((SchemaDOM)paramElement.getOwnerDocument()).getDocumentURI(); 
    return (str != null) ? str : (String)this.fDoc2SystemId.get(paramElement);
  }
  
  public XSDHandler() {
    this.fHiddenNodes = new Hashtable();
    this.fSchemaParser = new SchemaDOMParser((XMLParserConfiguration)new SchemaParsingConfig());
  }
  
  public XSDHandler(XSGrammarBucket paramXSGrammarBucket) {
    this();
    this.fGrammarBucket = paramXSGrammarBucket;
    this.fSchemaGrammarDescription = new XSDDescription();
  }
  
  public SchemaGrammar parseSchema(XMLInputSource paramXMLInputSource, XSDDescription paramXSDDescription, Hashtable paramHashtable) throws IOException {
    this.fLocationPairs = paramHashtable;
    this.fSchemaParser.resetNodePool();
    SchemaGrammar schemaGrammar = null;
    String str = null;
    short s = paramXSDDescription.getContextType();
    if (s != 3) {
      if (this.fHonourAllSchemaLocations && s == 2 && isExistingGrammar(paramXSDDescription)) {
        schemaGrammar = this.fGrammarBucket.getGrammar(paramXSDDescription.getTargetNamespace());
      } else {
        schemaGrammar = findGrammar(paramXSDDescription);
      } 
      if (schemaGrammar != null)
        return schemaGrammar; 
      str = paramXSDDescription.getTargetNamespace();
      if (str != null)
        str = this.fSymbolTable.addSymbol(str); 
    } 
    prepareForParse();
    Document document = null;
    Element element = null;
    if (paramXMLInputSource instanceof DOMInputSource) {
      this.fHiddenNodes.clear();
      Node node = ((DOMInputSource)paramXMLInputSource).getNode();
      if (node instanceof Document) {
        document = (Document)node;
        element = DOMUtil.getRoot(document);
      } else if (node instanceof Element) {
        element = (Element)node;
      } else {
        return null;
      } 
    } else if (paramXMLInputSource instanceof SAXInputSource) {
      SAXParser sAXParser;
      XMLReader xMLReader = ((SAXInputSource)paramXMLInputSource).getXMLReader();
      InputSource inputSource = ((SAXInputSource)paramXMLInputSource).getInputSource();
      boolean bool1 = false;
      if (xMLReader != null) {
        try {
          bool1 = xMLReader.getFeature("http://xml.org/sax/features/namespace-prefixes");
        } catch (SAXException sAXException) {}
      } else {
        try {
          xMLReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException sAXException) {
          sAXParser = new SAXParser();
        } 
        try {
          sAXParser.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
          bool1 = true;
        } catch (SAXException sAXException) {}
      } 
      boolean bool2 = false;
      try {
        bool2 = sAXParser.getFeature("http://xml.org/sax/features/string-interning");
      } catch (SAXException sAXException) {}
      if (this.fXSContentHandler == null)
        this.fXSContentHandler = new SchemaContentHandler(); 
      this.fXSContentHandler.reset(this.fSchemaParser, this.fSymbolTable, bool1, bool2);
      sAXParser.setContentHandler(this.fXSContentHandler);
      sAXParser.setErrorHandler(this.fErrorReporter.getSAXErrorHandler());
      try {
        sAXParser.parse(inputSource);
      } catch (SAXException sAXException) {
        return null;
      } 
      document = this.fXSContentHandler.getDocument();
      if (document == null)
        return null; 
      element = DOMUtil.getRoot(document);
    } else {
      element = getSchemaDocument(str, paramXMLInputSource, (s == 3), s, null);
    } 
    if (element == null)
      return null; 
    if (s == 3) {
      Element element1 = element;
      str = DOMUtil.getAttrValue(element1, SchemaSymbols.ATT_TARGETNAMESPACE);
      if (str != null && str.length() > 0) {
        str = this.fSymbolTable.addSymbol(str);
        paramXSDDescription.setTargetNamespace(str);
      } else {
        str = null;
      } 
      schemaGrammar = findGrammar(paramXSDDescription);
      if (schemaGrammar != null)
        return schemaGrammar; 
      String str1 = XMLEntityManager.expandSystemId(paramXMLInputSource.getSystemId(), paramXMLInputSource.getBaseSystemId(), false);
      XSDKey xSDKey = new XSDKey(str1, s, str);
      this.fTraversed.put(xSDKey, element);
      if (str1 != null)
        this.fDoc2SystemId.put(element, str1); 
    } 
    prepareForTraverse();
    this.fRoot = constructTrees(element, paramXMLInputSource.getSystemId(), paramXSDDescription);
    if (this.fRoot == null)
      return null; 
    buildGlobalNameRegistries();
    ArrayList arrayList = this.fValidateAnnotations ? new ArrayList() : null;
    traverseSchemas(arrayList);
    traverseLocalElements();
    resolveKeyRefs();
    for (int i = this.fAllTNSs.size() - 1; i >= 0; i--) {
      String str1 = this.fAllTNSs.elementAt(i);
      Vector vector = (Vector)this.fImportMap.get(str1);
      SchemaGrammar schemaGrammar1 = this.fGrammarBucket.getGrammar(emptyString2Null(str1));
      if (schemaGrammar1 != null) {
        byte b1 = 0;
        for (byte b2 = 0; b2 < vector.size(); b2++) {
          SchemaGrammar schemaGrammar2 = this.fGrammarBucket.getGrammar(vector.elementAt(b2));
          if (schemaGrammar2 != null)
            vector.setElementAt(schemaGrammar2, b1++); 
        } 
        vector.setSize(b1);
        schemaGrammar1.setImportedGrammars(vector);
      } 
    } 
    if (this.fValidateAnnotations && arrayList.size() > 0)
      validateAnnotations(arrayList); 
    return this.fGrammarBucket.getGrammar(this.fRoot.fTargetNamespace);
  }
  
  private void validateAnnotations(ArrayList paramArrayList) {
    if (this.fAnnotationValidator == null)
      createAnnotationValidator(); 
    int i = paramArrayList.size();
    XMLInputSource xMLInputSource = new XMLInputSource(null, null, null);
    this.fGrammarBucketAdapter.refreshGrammars(this.fGrammarBucket);
    for (byte b = 0; b < i; b += 2) {
      xMLInputSource.setSystemId(paramArrayList.get(b));
      for (XSAnnotationInfo xSAnnotationInfo = (XSAnnotationInfo)paramArrayList.get(b + 1); xSAnnotationInfo != null; xSAnnotationInfo = xSAnnotationInfo.next) {
        xMLInputSource.setCharacterStream(new StringReader(xSAnnotationInfo.fAnnotation));
        try {
          this.fAnnotationValidator.parse(xMLInputSource);
        } catch (IOException iOException) {}
      } 
    } 
  }
  
  private void createAnnotationValidator() {
    this.fAnnotationValidator = new XML11Configuration();
    this.fGrammarBucketAdapter = new XSAnnotationGrammarPool();
    this.fAnnotationValidator.setFeature("http://xml.org/sax/features/validation", true);
    this.fAnnotationValidator.setFeature("http://apache.org/xml/features/validation/schema", true);
    this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarBucketAdapter);
    XMLErrorHandler xMLErrorHandler = this.fErrorReporter.getErrorHandler();
    this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/error-handler", (xMLErrorHandler != null) ? xMLErrorHandler : new DefaultErrorHandler());
  }
  
  SchemaGrammar getGrammar(String paramString) {
    return this.fGrammarBucket.getGrammar(paramString);
  }
  
  protected SchemaGrammar findGrammar(XSDDescription paramXSDDescription) {
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramXSDDescription.getTargetNamespace());
    if (schemaGrammar == null && this.fGrammarPool != null) {
      schemaGrammar = (SchemaGrammar)this.fGrammarPool.retrieveGrammar((XMLGrammarDescription)paramXSDDescription);
      if (schemaGrammar != null && !this.fGrammarBucket.putGrammar(schemaGrammar, true)) {
        reportSchemaWarning("GrammarConflict", null, null);
        schemaGrammar = null;
      } 
    } 
    return schemaGrammar;
  }
  
  protected XSDocumentInfo constructTrees(Element paramElement, String paramString, XSDDescription paramXSDDescription) {
    if (paramElement == null)
      return null; 
    String str = paramXSDDescription.getTargetNamespace();
    short s = paramXSDDescription.getContextType();
    XSDocumentInfo xSDocumentInfo = null;
    try {
      xSDocumentInfo = new XSDocumentInfo(paramElement, this.fAttributeChecker, this.fSymbolTable);
    } catch (XMLSchemaException xMLSchemaException) {
      reportSchemaError(ELE_ERROR_CODES[s], new Object[] { paramString }, paramElement);
      return null;
    } 
    if (xSDocumentInfo.fTargetNamespace != null && xSDocumentInfo.fTargetNamespace.length() == 0) {
      reportSchemaWarning("EmptyTargetNamespace", new Object[] { paramString }, paramElement);
      xSDocumentInfo.fTargetNamespace = null;
    } 
    if (str != null) {
      boolean bool = false;
      if (s == 0 || s == 1) {
        if (xSDocumentInfo.fTargetNamespace == null) {
          xSDocumentInfo.fTargetNamespace = str;
          xSDocumentInfo.fIsChameleonSchema = true;
        } else if (str != xSDocumentInfo.fTargetNamespace) {
          reportSchemaError(NS_ERROR_CODES[s][bool], new Object[] { str, xSDocumentInfo.fTargetNamespace }, paramElement);
          return null;
        } 
      } else if (s != 3 && str != xSDocumentInfo.fTargetNamespace) {
        reportSchemaError(NS_ERROR_CODES[s][bool], new Object[] { str, xSDocumentInfo.fTargetNamespace }, paramElement);
        return null;
      } 
    } else if (xSDocumentInfo.fTargetNamespace != null) {
      if (s == 3) {
        paramXSDDescription.setTargetNamespace(xSDocumentInfo.fTargetNamespace);
        str = xSDocumentInfo.fTargetNamespace;
      } else {
        boolean bool = true;
        reportSchemaError(NS_ERROR_CODES[s][bool], new Object[] { str, xSDocumentInfo.fTargetNamespace }, paramElement);
        return null;
      } 
    } 
    xSDocumentInfo.addAllowedNS(xSDocumentInfo.fTargetNamespace);
    SchemaGrammar schemaGrammar = null;
    if (s == 0 || s == 1) {
      schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
    } else if (this.fHonourAllSchemaLocations && s == 2) {
      schemaGrammar = findGrammar(paramXSDDescription);
      if (schemaGrammar == null) {
        schemaGrammar = new SchemaGrammar(xSDocumentInfo.fTargetNamespace, paramXSDDescription.makeClone(), this.fSymbolTable);
        this.fGrammarBucket.putGrammar(schemaGrammar);
      } 
    } else {
      schemaGrammar = new SchemaGrammar(xSDocumentInfo.fTargetNamespace, paramXSDDescription.makeClone(), this.fSymbolTable);
      this.fGrammarBucket.putGrammar(schemaGrammar);
    } 
    schemaGrammar.addDocument(null, (String)this.fDoc2SystemId.get(xSDocumentInfo.fSchemaElement));
    this.fDoc2XSDocumentMap.put(paramElement, xSDocumentInfo);
    Vector vector = new Vector();
    Element element1 = paramElement;
    Element element2 = null;
    for (Element element3 = DOMUtil.getFirstChildElement(element1); element3 != null; element3 = DOMUtil.getNextSiblingElement(element3)) {
      String str1 = null;
      String str2 = null;
      String str3 = DOMUtil.getLocalName(element3);
      byte b = -1;
      if (str3.equals(SchemaSymbols.ELT_ANNOTATION))
        continue; 
      if (str3.equals(SchemaSymbols.ELT_IMPORT)) {
        b = 2;
        Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element3, true, xSDocumentInfo);
        str2 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
        str1 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAMESPACE];
        if (str1 != null)
          str1 = this.fSymbolTable.addSymbol(str1); 
        if (str1 == xSDocumentInfo.fTargetNamespace)
          reportSchemaError((str1 != null) ? "src-import.1.1" : "src-import.1.2", new Object[] { str1 }, element3); 
        Element element = DOMUtil.getFirstChildElement(element3);
        if (element != null) {
          String str5 = DOMUtil.getLocalName(element);
          if (str5.equals(SchemaSymbols.ELT_ANNOTATION)) {
            schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element, arrayOfObject, true, xSDocumentInfo));
          } else {
            reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", str5 }, element3);
          } 
          if (DOMUtil.getNextSiblingElement(element) != null)
            reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element)) }, element3); 
        } else {
          String str5 = DOMUtil.getSyntheticAnnotation(element3);
          if (str5 != null)
            schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element3, str5, arrayOfObject, true, xSDocumentInfo)); 
        } 
        this.fAttributeChecker.returnAttrArray(arrayOfObject, xSDocumentInfo);
        if (xSDocumentInfo.isAllowedNS(str1)) {
          if (!this.fHonourAllSchemaLocations)
            continue; 
        } else {
          xSDocumentInfo.addAllowedNS(str1);
        } 
        String str4 = null2EmptyString(xSDocumentInfo.fTargetNamespace);
        Vector vector1 = (Vector)this.fImportMap.get(str4);
        if (vector1 == null) {
          this.fAllTNSs.addElement(str4);
          vector1 = new Vector();
          this.fImportMap.put(str4, vector1);
          vector1.addElement(str1);
        } else if (!vector1.contains(str1)) {
          vector1.addElement(str1);
        } 
        this.fSchemaGrammarDescription.reset();
        this.fSchemaGrammarDescription.setContextType((short)2);
        this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(paramElement));
        this.fSchemaGrammarDescription.setLocationHints(new String[] { str2 });
        this.fSchemaGrammarDescription.setTargetNamespace(str1);
        if ((!this.fHonourAllSchemaLocations && findGrammar(this.fSchemaGrammarDescription) != null) || isExistingGrammar(this.fSchemaGrammarDescription))
          continue; 
        element2 = resolveSchema(this.fSchemaGrammarDescription, false, element3, (findGrammar(this.fSchemaGrammarDescription) == null));
      } else if (str3.equals(SchemaSymbols.ELT_INCLUDE) || str3.equals(SchemaSymbols.ELT_REDEFINE)) {
        Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element3, true, xSDocumentInfo);
        str2 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
        if (str3.equals(SchemaSymbols.ELT_REDEFINE))
          this.fRedefine2NSSupport.put(element3, new SchemaNamespaceSupport(xSDocumentInfo.fNamespaceSupport)); 
        if (str3.equals(SchemaSymbols.ELT_INCLUDE)) {
          Element element = DOMUtil.getFirstChildElement(element3);
          if (element != null) {
            String str4 = DOMUtil.getLocalName(element);
            if (str4.equals(SchemaSymbols.ELT_ANNOTATION)) {
              schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element, arrayOfObject, true, xSDocumentInfo));
            } else {
              reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", str4 }, element3);
            } 
            if (DOMUtil.getNextSiblingElement(element) != null)
              reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element)) }, element3); 
          } else {
            String str4 = DOMUtil.getSyntheticAnnotation(element3);
            if (str4 != null)
              schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element3, str4, arrayOfObject, true, xSDocumentInfo)); 
          } 
        } else {
          for (Element element = DOMUtil.getFirstChildElement(element3); element != null; element = DOMUtil.getNextSiblingElement(element)) {
            String str4 = DOMUtil.getLocalName(element);
            if (str4.equals(SchemaSymbols.ELT_ANNOTATION)) {
              schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element, arrayOfObject, true, xSDocumentInfo));
              DOMUtil.setHidden(element, this.fHiddenNodes);
            } else {
              String str5 = DOMUtil.getSyntheticAnnotation(element3);
              if (str5 != null)
                schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element3, str5, arrayOfObject, true, xSDocumentInfo)); 
            } 
          } 
        } 
        this.fAttributeChecker.returnAttrArray(arrayOfObject, xSDocumentInfo);
        if (str2 == null)
          reportSchemaError("s4s-att-must-appear", new Object[] { "<include> or <redefine>", "schemaLocation" }, element3); 
        boolean bool = false;
        b = 0;
        if (str3.equals(SchemaSymbols.ELT_REDEFINE)) {
          bool = nonAnnotationContent(element3);
          b = 1;
        } 
        this.fSchemaGrammarDescription.reset();
        this.fSchemaGrammarDescription.setContextType(b);
        this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(paramElement));
        this.fSchemaGrammarDescription.setLocationHints(new String[] { str2 });
        this.fSchemaGrammarDescription.setTargetNamespace(str);
        element2 = resolveSchema(this.fSchemaGrammarDescription, bool, element3, true);
        str1 = xSDocumentInfo.fTargetNamespace;
      } else {
        break;
      } 
      XSDocumentInfo xSDocumentInfo1 = null;
      if (this.fLastSchemaWasDuplicate) {
        xSDocumentInfo1 = (element2 == null) ? null : (XSDocumentInfo)this.fDoc2XSDocumentMap.get(element2);
      } else {
        xSDocumentInfo1 = constructTrees(element2, str2, this.fSchemaGrammarDescription);
      } 
      if (str3.equals(SchemaSymbols.ELT_REDEFINE) && xSDocumentInfo1 != null)
        this.fRedefine2XSDMap.put(element3, xSDocumentInfo1); 
      if (element2 != null) {
        if (xSDocumentInfo1 != null)
          vector.addElement(xSDocumentInfo1); 
        element2 = null;
      } 
      continue;
    } 
    this.fDependencyMap.put(xSDocumentInfo, vector);
    return xSDocumentInfo;
  }
  
  private boolean isExistingGrammar(XSDDescription paramXSDDescription) {
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramXSDDescription.getTargetNamespace());
    if (schemaGrammar == null)
      return (findGrammar(paramXSDDescription) != null); 
    try {
      return schemaGrammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(paramXSDDescription.getLiteralSystemId(), paramXSDDescription.getBaseSystemId(), false));
    } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
      return false;
    } 
  }
  
  protected void buildGlobalNameRegistries() {
    Stack stack = new Stack();
    stack.push(this.fRoot);
    while (!stack.empty()) {
      XSDocumentInfo xSDocumentInfo = stack.pop();
      Element element1 = xSDocumentInfo.fSchemaElement;
      if (DOMUtil.isHidden(element1, this.fHiddenNodes))
        continue; 
      Element element2 = element1;
      boolean bool = true;
      for (Element element3 = DOMUtil.getFirstChildElement(element2); element3 != null; element3 = DOMUtil.getNextSiblingElement(element3)) {
        if (!DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION))
          if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_INCLUDE) || DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_IMPORT)) {
            if (!bool)
              reportSchemaError("s4s-elt-invalid-content.3", new Object[] { DOMUtil.getLocalName(element3) }, element3); 
            DOMUtil.setHidden(element3, this.fHiddenNodes);
          } else if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_REDEFINE)) {
            if (!bool)
              reportSchemaError("s4s-elt-invalid-content.3", new Object[] { DOMUtil.getLocalName(element3) }, element3); 
            for (Element element = DOMUtil.getFirstChildElement(element3); element != null; element = DOMUtil.getNextSiblingElement(element)) {
              String str = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME);
              if (str.length() != 0) {
                String str1 = (xSDocumentInfo.fTargetNamespace == null) ? ("," + str) : (xSDocumentInfo.fTargetNamespace + "," + str);
                String str2 = DOMUtil.getLocalName(element);
                if (str2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                  checkForDuplicateNames(str1, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistrySub, element, xSDocumentInfo);
                  String str3 = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                  renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_ATTRIBUTEGROUP, str, str3);
                } else if (str2.equals(SchemaSymbols.ELT_COMPLEXTYPE) || str2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                  checkForDuplicateNames(str1, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistrySub, element, xSDocumentInfo);
                  String str3 = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                  if (str2.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_COMPLEXTYPE, str, str3);
                  } else {
                    renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_SIMPLETYPE, str, str3);
                  } 
                } else if (str2.equals(SchemaSymbols.ELT_GROUP)) {
                  checkForDuplicateNames(str1, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistrySub, element, xSDocumentInfo);
                  String str3 = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                  renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_GROUP, str, str3);
                } 
              } 
            } 
          } else {
            bool = false;
            String str = DOMUtil.getAttrValue(element3, SchemaSymbols.ATT_NAME);
            if (str.length() != 0) {
              String str1 = (xSDocumentInfo.fTargetNamespace == null) ? ("," + str) : (xSDocumentInfo.fTargetNamespace + "," + str);
              String str2 = DOMUtil.getLocalName(element3);
              if (str2.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                checkForDuplicateNames(str1, this.fUnparsedAttributeRegistry, this.fUnparsedAttributeRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                checkForDuplicateNames(str1, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_COMPLEXTYPE) || str2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                checkForDuplicateNames(str1, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_ELEMENT)) {
                checkForDuplicateNames(str1, this.fUnparsedElementRegistry, this.fUnparsedElementRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_GROUP)) {
                checkForDuplicateNames(str1, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistrySub, element3, xSDocumentInfo);
              } else if (str2.equals(SchemaSymbols.ELT_NOTATION)) {
                checkForDuplicateNames(str1, this.fUnparsedNotationRegistry, this.fUnparsedNotationRegistrySub, element3, xSDocumentInfo);
              } 
            } 
          }  
      } 
      DOMUtil.setHidden(element1, this.fHiddenNodes);
      Vector vector = (Vector)this.fDependencyMap.get(xSDocumentInfo);
      for (byte b = 0; b < vector.size(); b++)
        stack.push(vector.elementAt(b)); 
    } 
  }
  
  protected void traverseSchemas(ArrayList paramArrayList) {
    setSchemasVisible(this.fRoot);
    Stack stack = new Stack();
    stack.push(this.fRoot);
    while (!stack.empty()) {
      XSDocumentInfo xSDocumentInfo = stack.pop();
      Element element1 = xSDocumentInfo.fSchemaElement;
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
      if (DOMUtil.isHidden(element1, this.fHiddenNodes))
        continue; 
      Element element2 = element1;
      boolean bool = false;
      for (Element element3 = DOMUtil.getFirstVisibleChildElement(element2, this.fHiddenNodes); element3 != null; element3 = DOMUtil.getNextVisibleSiblingElement(element3, this.fHiddenNodes)) {
        DOMUtil.setHidden(element3, this.fHiddenNodes);
        String str = DOMUtil.getLocalName(element3);
        if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_REDEFINE)) {
          xSDocumentInfo.backupNSSupport((SchemaNamespaceSupport)this.fRedefine2NSSupport.get(element3));
          for (Element element = DOMUtil.getFirstVisibleChildElement(element3, this.fHiddenNodes); element != null; element = DOMUtil.getNextVisibleSiblingElement(element, this.fHiddenNodes)) {
            String str1 = DOMUtil.getLocalName(element);
            DOMUtil.setHidden(element, this.fHiddenNodes);
            if (str1.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
              this.fAttributeGroupTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else if (str1.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
              this.fComplexTypeTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else if (str1.equals(SchemaSymbols.ELT_GROUP)) {
              this.fGroupTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else if (str1.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
              this.fSimpleTypeTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
            } else {
              reportSchemaError("s4s-elt-must-match.1", new Object[] { DOMUtil.getLocalName(element3), "(annotation | (simpleType | complexType | group | attributeGroup))*", str1 }, element);
            } 
          } 
          xSDocumentInfo.restoreNSSupport();
        } else if (str.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
          this.fAttributeTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
          this.fAttributeGroupTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
          this.fComplexTypeTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_ELEMENT)) {
          this.fElementTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_GROUP)) {
          this.fGroupTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_NOTATION)) {
          this.fNotationTraverser.traverse(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
          this.fSimpleTypeTraverser.traverseGlobal(element3, xSDocumentInfo, schemaGrammar);
        } else if (str.equals(SchemaSymbols.ELT_ANNOTATION)) {
          schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element3, xSDocumentInfo.getSchemaAttrs(), true, xSDocumentInfo));
          bool = true;
        } else {
          reportSchemaError("s4s-elt-invalid-content.1", new Object[] { SchemaSymbols.ELT_SCHEMA, DOMUtil.getLocalName(element3) }, element3);
        } 
      } 
      if (!bool) {
        String str = DOMUtil.getSyntheticAnnotation(element2);
        if (str != null)
          schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element2, str, xSDocumentInfo.getSchemaAttrs(), true, xSDocumentInfo)); 
      } 
      if (paramArrayList != null) {
        XSAnnotationInfo xSAnnotationInfo = xSDocumentInfo.getAnnotations();
        if (xSAnnotationInfo != null) {
          paramArrayList.add(doc2SystemId(element1));
          paramArrayList.add(xSAnnotationInfo);
        } 
      } 
      xSDocumentInfo.returnSchemaAttrs();
      DOMUtil.setHidden(element1, this.fHiddenNodes);
      Vector vector = (Vector)this.fDependencyMap.get(xSDocumentInfo);
      for (byte b = 0; b < vector.size(); b++)
        stack.push(vector.elementAt(b)); 
    } 
  }
  
  private final boolean needReportTNSError(String paramString) {
    if (this.fReportedTNS == null) {
      this.fReportedTNS = new Vector();
    } else if (this.fReportedTNS.contains(paramString)) {
      return false;
    } 
    this.fReportedTNS.addElement(paramString);
    return true;
  }
  
  protected Object getGlobalDecl(XSDocumentInfo paramXSDocumentInfo, int paramInt, QName paramQName, Element paramElement) {
    XSAttributeGroupDecl xSAttributeGroupDecl2;
    XSElementDecl xSElementDecl2;
    XSGroupDecl xSGroupDecl2;
    IdentityConstraint identityConstraint;
    XSNotationDecl xSNotationDecl2;
    XSTypeDefinition xSTypeDefinition;
    XSAttributeDecl xSAttributeDecl1;
    XSAttributeGroupDecl xSAttributeGroupDecl1;
    XSElementDecl xSElementDecl1;
    XSGroupDecl xSGroupDecl1;
    XSNotationDecl xSNotationDecl1;
    XSSimpleType xSSimpleType;
    if (paramQName.uri != null && paramQName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && paramInt == 7) {
      XSTypeDefinition xSTypeDefinition1 = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(paramQName.localpart);
      if (xSTypeDefinition1 != null)
        return xSTypeDefinition1; 
    } 
    if (!paramXSDocumentInfo.isAllowedNS(paramQName.uri)) {
      if (paramXSDocumentInfo.needReportTNSError(paramQName.uri)) {
        String str1 = (paramQName.uri == null) ? "src-resolve.4.1" : "src-resolve.4.2";
        reportSchemaError(str1, new Object[] { this.fDoc2SystemId.get(paramXSDocumentInfo.fSchemaElement), paramQName.uri, paramQName.rawname }, paramElement);
      } 
      return null;
    } 
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramQName.uri);
    if (schemaGrammar == null) {
      if (needReportTNSError(paramQName.uri))
        reportSchemaError("src-resolve", new Object[] { paramQName.rawname, COMP_TYPE[paramInt] }, paramElement); 
      return null;
    } 
    XSAttributeDecl xSAttributeDecl2 = null;
    switch (paramInt) {
      case 1:
        xSAttributeDecl2 = schemaGrammar.getGlobalAttributeDecl(paramQName.localpart);
        break;
      case 2:
        xSAttributeGroupDecl2 = schemaGrammar.getGlobalAttributeGroupDecl(paramQName.localpart);
        break;
      case 3:
        xSElementDecl2 = schemaGrammar.getGlobalElementDecl(paramQName.localpart);
        break;
      case 4:
        xSGroupDecl2 = schemaGrammar.getGlobalGroupDecl(paramQName.localpart);
        break;
      case 5:
        identityConstraint = schemaGrammar.getIDConstraintDecl(paramQName.localpart);
        break;
      case 6:
        xSNotationDecl2 = schemaGrammar.getGlobalNotationDecl(paramQName.localpart);
        break;
      case 7:
        xSTypeDefinition = schemaGrammar.getGlobalTypeDecl(paramQName.localpart);
        break;
    } 
    if (xSTypeDefinition != null)
      return xSTypeDefinition; 
    XSDocumentInfo xSDocumentInfo1 = null;
    Element element1 = null;
    XSDocumentInfo xSDocumentInfo2 = null;
    String str = (paramQName.uri == null) ? ("," + paramQName.localpart) : (paramQName.uri + "," + paramQName.localpart);
    switch (paramInt) {
      case 1:
        element1 = (Element)this.fUnparsedAttributeRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedAttributeRegistrySub.get(str);
        break;
      case 2:
        element1 = (Element)this.fUnparsedAttributeGroupRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedAttributeGroupRegistrySub.get(str);
        break;
      case 3:
        element1 = (Element)this.fUnparsedElementRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedElementRegistrySub.get(str);
        break;
      case 4:
        element1 = (Element)this.fUnparsedGroupRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedGroupRegistrySub.get(str);
        break;
      case 5:
        element1 = (Element)this.fUnparsedIdentityConstraintRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedIdentityConstraintRegistrySub.get(str);
        break;
      case 6:
        element1 = (Element)this.fUnparsedNotationRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedNotationRegistrySub.get(str);
        break;
      case 7:
        element1 = (Element)this.fUnparsedTypeRegistry.get(str);
        xSDocumentInfo2 = (XSDocumentInfo)this.fUnparsedTypeRegistrySub.get(str);
        break;
      default:
        reportSchemaError("Internal-Error", new Object[] { "XSDHandler asked to locate component of type " + paramInt + "; it does not recognize this type!" }, paramElement);
        break;
    } 
    if (element1 == null) {
      reportSchemaError("src-resolve", new Object[] { paramQName.rawname, COMP_TYPE[paramInt] }, paramElement);
      return null;
    } 
    xSDocumentInfo1 = findXSDocumentForDecl(paramXSDocumentInfo, element1, xSDocumentInfo2);
    if (xSDocumentInfo1 == null) {
      String str1 = (paramQName.uri == null) ? "src-resolve.4.1" : "src-resolve.4.2";
      reportSchemaError(str1, new Object[] { this.fDoc2SystemId.get(paramXSDocumentInfo.fSchemaElement), paramQName.uri, paramQName.rawname }, paramElement);
      return null;
    } 
    if (DOMUtil.isHidden(element1, this.fHiddenNodes)) {
      String str1 = CIRCULAR_CODES[paramInt];
      if (paramInt == 7 && SchemaSymbols.ELT_COMPLEXTYPE.equals(DOMUtil.getLocalName(element1)))
        str1 = "ct-props-correct.3"; 
      reportSchemaError(str1, new Object[] { paramQName.prefix + ":" + paramQName.localpart }, paramElement);
      return null;
    } 
    DOMUtil.setHidden(element1, this.fHiddenNodes);
    SchemaNamespaceSupport schemaNamespaceSupport = null;
    Element element2 = DOMUtil.getParent(element1);
    if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_REDEFINE))
      schemaNamespaceSupport = (SchemaNamespaceSupport)this.fRedefine2NSSupport.get(element2); 
    xSDocumentInfo1.backupNSSupport(schemaNamespaceSupport);
    switch (paramInt) {
      case 1:
        xSAttributeDecl1 = this.fAttributeTraverser.traverseGlobal(element1, xSDocumentInfo1, schemaGrammar);
        break;
      case 2:
        xSAttributeGroupDecl1 = this.fAttributeGroupTraverser.traverseGlobal(element1, xSDocumentInfo1, schemaGrammar);
        break;
      case 3:
        xSElementDecl1 = this.fElementTraverser.traverseGlobal(element1, xSDocumentInfo1, schemaGrammar);
        break;
      case 4:
        xSGroupDecl1 = this.fGroupTraverser.traverseGlobal(element1, xSDocumentInfo1, schemaGrammar);
        break;
      case 5:
        xSGroupDecl1 = null;
        break;
      case 6:
        xSNotationDecl1 = this.fNotationTraverser.traverse(element1, xSDocumentInfo1, schemaGrammar);
        break;
      case 7:
        if (DOMUtil.getLocalName(element1).equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
          XSComplexTypeDecl xSComplexTypeDecl = this.fComplexTypeTraverser.traverseGlobal(element1, xSDocumentInfo1, schemaGrammar);
          break;
        } 
        xSSimpleType = this.fSimpleTypeTraverser.traverseGlobal(element1, xSDocumentInfo1, schemaGrammar);
        break;
    } 
    xSDocumentInfo1.restoreNSSupport();
    return xSSimpleType;
  }
  
  Object getGrpOrAttrGrpRedefinedByRestriction(int paramInt, QName paramQName, XSDocumentInfo paramXSDocumentInfo, Element paramElement) {
    String str1 = (paramQName.uri != null) ? (paramQName.uri + "," + paramQName.localpart) : ("," + paramQName.localpart);
    String str2 = null;
    switch (paramInt) {
      case 2:
        str2 = (String)this.fRedefinedRestrictedAttributeGroupRegistry.get(str1);
        break;
      case 4:
        str2 = (String)this.fRedefinedRestrictedGroupRegistry.get(str1);
        break;
      default:
        return null;
    } 
    if (str2 == null)
      return null; 
    int i = str2.indexOf(",");
    QName qName = new QName(XMLSymbols.EMPTY_STRING, str2.substring(i + 1), str2.substring(i), (i == 0) ? null : str2.substring(0, i));
    Object object = getGlobalDecl(paramXSDocumentInfo, paramInt, qName, paramElement);
    if (object == null) {
      switch (paramInt) {
        case 2:
          reportSchemaError("src-redefine.7.2.1", new Object[] { paramQName.localpart }, paramElement);
          break;
        case 4:
          reportSchemaError("src-redefine.6.2.1", new Object[] { paramQName.localpart }, paramElement);
          break;
      } 
      return null;
    } 
    return object;
  }
  
  protected void resolveKeyRefs() {
    for (byte b = 0; b < this.fKeyrefStackPos; b++) {
      XSDocumentInfo xSDocumentInfo = this.fKeyrefsMapXSDocumentInfo[b];
      xSDocumentInfo.fNamespaceSupport.makeGlobal();
      xSDocumentInfo.fNamespaceSupport.setEffectiveContext(this.fKeyrefNamespaceContext[b]);
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
      DOMUtil.setHidden(this.fKeyrefs[b], this.fHiddenNodes);
      this.fKeyrefTraverser.traverse(this.fKeyrefs[b], this.fKeyrefElems[b], xSDocumentInfo, schemaGrammar);
    } 
  }
  
  protected Hashtable getIDRegistry() {
    return this.fUnparsedIdentityConstraintRegistry;
  }
  
  protected Hashtable getIDRegistry_sub() {
    return this.fUnparsedIdentityConstraintRegistrySub;
  }
  
  protected void storeKeyRef(Element paramElement, XSDocumentInfo paramXSDocumentInfo, XSElementDecl paramXSElementDecl) {
    String str = DOMUtil.getAttrValue(paramElement, SchemaSymbols.ATT_NAME);
    if (str.length() != 0) {
      String str1 = (paramXSDocumentInfo.fTargetNamespace == null) ? ("," + str) : (paramXSDocumentInfo.fTargetNamespace + "," + str);
      checkForDuplicateNames(str1, this.fUnparsedIdentityConstraintRegistry, this.fUnparsedIdentityConstraintRegistrySub, paramElement, paramXSDocumentInfo);
    } 
    if (this.fKeyrefStackPos == this.fKeyrefs.length) {
      Element[] arrayOfElement = new Element[this.fKeyrefStackPos + 2];
      System.arraycopy(this.fKeyrefs, 0, arrayOfElement, 0, this.fKeyrefStackPos);
      this.fKeyrefs = arrayOfElement;
      XSElementDecl[] arrayOfXSElementDecl = new XSElementDecl[this.fKeyrefStackPos + 2];
      System.arraycopy(this.fKeyrefElems, 0, arrayOfXSElementDecl, 0, this.fKeyrefStackPos);
      this.fKeyrefElems = arrayOfXSElementDecl;
      String[][] arrayOfString = new String[this.fKeyrefStackPos + 2][];
      System.arraycopy(this.fKeyrefNamespaceContext, 0, arrayOfString, 0, this.fKeyrefStackPos);
      this.fKeyrefNamespaceContext = arrayOfString;
      XSDocumentInfo[] arrayOfXSDocumentInfo = new XSDocumentInfo[this.fKeyrefStackPos + 2];
      System.arraycopy(this.fKeyrefsMapXSDocumentInfo, 0, arrayOfXSDocumentInfo, 0, this.fKeyrefStackPos);
      this.fKeyrefsMapXSDocumentInfo = arrayOfXSDocumentInfo;
    } 
    this.fKeyrefs[this.fKeyrefStackPos] = paramElement;
    this.fKeyrefElems[this.fKeyrefStackPos] = paramXSElementDecl;
    this.fKeyrefNamespaceContext[this.fKeyrefStackPos] = paramXSDocumentInfo.fNamespaceSupport.getEffectiveLocalContext();
    this.fKeyrefsMapXSDocumentInfo[this.fKeyrefStackPos++] = paramXSDocumentInfo;
  }
  
  private Element resolveSchema(XSDDescription paramXSDDescription, boolean paramBoolean1, Element paramElement, boolean paramBoolean2) {
    XMLInputSource xMLInputSource = null;
    try {
      Hashtable hashtable = paramBoolean2 ? this.fLocationPairs : EMPTY_TABLE;
      xMLInputSource = XMLSchemaLoader.resolveDocument(paramXSDDescription, hashtable, this.fEntityResolver);
    } catch (IOException iOException) {
      if (paramBoolean1) {
        reportSchemaError("schema_reference.4", new Object[] { paramXSDDescription.getLocationHints()[0] }, paramElement);
      } else {
        reportSchemaWarning("schema_reference.4", new Object[] { paramXSDDescription.getLocationHints()[0] }, paramElement);
      } 
    } 
    if (xMLInputSource instanceof DOMInputSource) {
      this.fHiddenNodes.clear();
      Node node = ((DOMInputSource)xMLInputSource).getNode();
      return (node instanceof Document) ? DOMUtil.getRoot((Document)node) : ((node instanceof Element) ? (Element)node : null);
    } 
    if (xMLInputSource instanceof SAXInputSource) {
      SAXParser sAXParser;
      XMLReader xMLReader = ((SAXInputSource)xMLInputSource).getXMLReader();
      InputSource inputSource = ((SAXInputSource)xMLInputSource).getInputSource();
      boolean bool1 = false;
      if (xMLReader != null) {
        try {
          bool1 = xMLReader.getFeature("http://xml.org/sax/features/namespace-prefixes");
        } catch (SAXException sAXException) {}
      } else {
        try {
          xMLReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException sAXException) {
          sAXParser = new SAXParser();
        } 
        try {
          sAXParser.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
          bool1 = true;
        } catch (SAXException sAXException) {}
      } 
      boolean bool2 = false;
      try {
        bool2 = sAXParser.getFeature("http://xml.org/sax/features/string-interning");
      } catch (SAXException sAXException) {}
      if (this.fXSContentHandler == null)
        this.fXSContentHandler = new SchemaContentHandler(); 
      this.fXSContentHandler.reset(this.fSchemaParser, this.fSymbolTable, bool1, bool2);
      sAXParser.setContentHandler(this.fXSContentHandler);
      sAXParser.setErrorHandler(this.fErrorReporter.getSAXErrorHandler());
      try {
        sAXParser.parse(inputSource);
      } catch (SAXException sAXException) {
        return null;
      } catch (IOException iOException) {
        return null;
      } 
      Document document = this.fXSContentHandler.getDocument();
      return (document == null) ? null : DOMUtil.getRoot(document);
    } 
    return getSchemaDocument(paramXSDDescription.getTargetNamespace(), xMLInputSource, paramBoolean1, paramXSDDescription.getContextType(), paramElement);
  }
  
  private Element getSchemaDocument(String paramString, XMLInputSource paramXMLInputSource, boolean paramBoolean, short paramShort, Element paramElement) {
    boolean bool = true;
    Element element = null;
    try {
      if (paramXMLInputSource != null && (paramXMLInputSource.getSystemId() != null || paramXMLInputSource.getByteStream() != null || paramXMLInputSource.getCharacterStream() != null)) {
        XSDKey xSDKey = null;
        String str = null;
        str = XMLEntityManager.expandSystemId(paramXMLInputSource.getSystemId(), paramXMLInputSource.getBaseSystemId(), false);
        xSDKey = new XSDKey(str, paramShort, paramString);
        if (paramShort != 3 && (element = (Element)this.fTraversed.get(xSDKey)) != null) {
          this.fLastSchemaWasDuplicate = true;
          return element;
        } 
        this.fSchemaParser.parse(paramXMLInputSource);
        element = (this.fSchemaParser.getDocument2() == null) ? null : DOMUtil.getRoot(this.fSchemaParser.getDocument2());
        if (xSDKey != null)
          this.fTraversed.put(xSDKey, element); 
        if (str != null)
          this.fDoc2SystemId.put(element, str); 
        this.fLastSchemaWasDuplicate = false;
        return element;
      } 
      bool = false;
    } catch (IOException iOException) {}
    if (paramBoolean) {
      if (bool) {
        reportSchemaError("schema_reference.4", new Object[] { paramXMLInputSource.getSystemId() }, paramElement);
      } else {
        reportSchemaError("schema_reference.4", new Object[] { (paramXMLInputSource == null) ? "" : paramXMLInputSource.getSystemId() }, paramElement);
      } 
    } else if (bool) {
      reportSchemaWarning("schema_reference.4", new Object[] { paramXMLInputSource.getSystemId() }, paramElement);
    } 
    this.fLastSchemaWasDuplicate = false;
    return null;
  }
  
  private void createTraversers() {
    this.fAttributeChecker = new XSAttributeChecker(this);
    this.fAttributeGroupTraverser = new XSDAttributeGroupTraverser(this, this.fAttributeChecker);
    this.fAttributeTraverser = new XSDAttributeTraverser(this, this.fAttributeChecker);
    this.fComplexTypeTraverser = new XSDComplexTypeTraverser(this, this.fAttributeChecker);
    this.fElementTraverser = new XSDElementTraverser(this, this.fAttributeChecker);
    this.fGroupTraverser = new XSDGroupTraverser(this, this.fAttributeChecker);
    this.fKeyrefTraverser = new XSDKeyrefTraverser(this, this.fAttributeChecker);
    this.fNotationTraverser = new XSDNotationTraverser(this, this.fAttributeChecker);
    this.fSimpleTypeTraverser = new XSDSimpleTypeTraverser(this, this.fAttributeChecker);
    this.fUniqueOrKeyTraverser = new XSDUniqueOrKeyTraverser(this, this.fAttributeChecker);
    this.fWildCardTraverser = new XSDWildcardTraverser(this, this.fAttributeChecker);
  }
  
  void prepareForParse() {
    this.fTraversed.clear();
    this.fDoc2SystemId.clear();
    this.fHiddenNodes.clear();
    this.fLastSchemaWasDuplicate = false;
  }
  
  void prepareForTraverse() {
    this.fUnparsedAttributeRegistry.clear();
    this.fUnparsedAttributeGroupRegistry.clear();
    this.fUnparsedElementRegistry.clear();
    this.fUnparsedGroupRegistry.clear();
    this.fUnparsedIdentityConstraintRegistry.clear();
    this.fUnparsedNotationRegistry.clear();
    this.fUnparsedTypeRegistry.clear();
    this.fUnparsedAttributeRegistrySub.clear();
    this.fUnparsedAttributeGroupRegistrySub.clear();
    this.fUnparsedElementRegistrySub.clear();
    this.fUnparsedGroupRegistrySub.clear();
    this.fUnparsedIdentityConstraintRegistrySub.clear();
    this.fUnparsedNotationRegistrySub.clear();
    this.fUnparsedTypeRegistrySub.clear();
    this.fXSDocumentInfoRegistry.clear();
    this.fDependencyMap.clear();
    this.fDoc2XSDocumentMap.clear();
    this.fRedefine2XSDMap.clear();
    this.fRedefine2NSSupport.clear();
    this.fAllTNSs.removeAllElements();
    this.fImportMap.clear();
    this.fRoot = null;
    for (byte b1 = 0; b1 < this.fLocalElemStackPos; b1++) {
      this.fParticle[b1] = null;
      this.fLocalElementDecl[b1] = null;
      this.fLocalElementDecl_schema[b1] = null;
      this.fLocalElemNamespaceContext[b1] = null;
    } 
    this.fLocalElemStackPos = 0;
    for (byte b2 = 0; b2 < this.fKeyrefStackPos; b2++) {
      this.fKeyrefs[b2] = null;
      this.fKeyrefElems[b2] = null;
      this.fKeyrefNamespaceContext[b2] = null;
      this.fKeyrefsMapXSDocumentInfo[b2] = null;
    } 
    this.fKeyrefStackPos = 0;
    if (this.fAttributeChecker == null)
      createTraversers(); 
    this.fAttributeChecker.reset(this.fSymbolTable);
    this.fAttributeGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fAttributeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fComplexTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fElementTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fKeyrefTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fNotationTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fSimpleTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fUniqueOrKeyTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fWildCardTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
    this.fRedefinedRestrictedAttributeGroupRegistry.clear();
    this.fRedefinedRestrictedGroupRegistry.clear();
  }
  
  public void setDeclPool(XSDeclarationPool paramXSDeclarationPool) {
    this.fDeclPool = paramXSDeclarationPool;
  }
  
  public void reset(XMLComponentManager paramXMLComponentManager) {
    this.fSymbolTable = (SymbolTable)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    this.fEntityResolver = (XMLEntityResolver)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
    XMLEntityResolver xMLEntityResolver = (XMLEntityResolver)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
    if (xMLEntityResolver != null)
      this.fSchemaParser.setEntityResolver(xMLEntityResolver); 
    this.fErrorReporter = (XMLErrorReporter)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    try {
      XMLErrorHandler xMLErrorHandler = this.fErrorReporter.getErrorHandler();
      if (xMLErrorHandler != this.fSchemaParser.getProperty("http://apache.org/xml/properties/internal/error-handler")) {
        this.fSchemaParser.setProperty("http://apache.org/xml/properties/internal/error-handler", (xMLErrorHandler != null) ? xMLErrorHandler : new DefaultErrorHandler());
        if (this.fAnnotationValidator != null)
          this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/error-handler", (xMLErrorHandler != null) ? xMLErrorHandler : new DefaultErrorHandler()); 
      } 
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fValidateAnnotations = paramXMLComponentManager.getFeature("http://apache.org/xml/features/validate-annotations");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fValidateAnnotations = false;
    } 
    try {
      this.fHonourAllSchemaLocations = paramXMLComponentManager.getFeature("http://apache.org/xml/features/honour-all-schemaLocations");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fHonourAllSchemaLocations = false;
    } 
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", this.fErrorReporter.getFeature("http://apache.org/xml/features/continue-after-fatal-error"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/allow-java-encodings", paramXMLComponentManager.getFeature("http://apache.org/xml/features/allow-java-encodings"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/standard-uri-conformant", paramXMLComponentManager.getFeature("http://apache.org/xml/features/standard-uri-conformant"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      this.fGrammarPool = (XMLGrammarPool)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
    } catch (XMLConfigurationException xMLConfigurationException) {
      this.fGrammarPool = null;
    } 
    try {
      this.fSchemaParser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", paramXMLComponentManager.getFeature("http://apache.org/xml/features/disallow-doctype-decl"));
    } catch (XMLConfigurationException xMLConfigurationException) {}
    try {
      Object object = paramXMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
      if (object != null)
        this.fSchemaParser.setProperty("http://apache.org/xml/properties/security-manager", object); 
    } catch (XMLConfigurationException xMLConfigurationException) {}
  }
  
  void traverseLocalElements() {
    this.fElementTraverser.fDeferTraversingLocalElements = false;
    for (byte b = 0; b < this.fLocalElemStackPos; b++) {
      Element element = this.fLocalElementDecl[b];
      XSDocumentInfo xSDocumentInfo = this.fLocalElementDecl_schema[b];
      SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
      this.fElementTraverser.traverseLocal(this.fParticle[b], element, xSDocumentInfo, schemaGrammar, this.fAllContext[b], this.fParent[b], this.fLocalElemNamespaceContext[b]);
      if ((this.fParticle[b]).fType == 0) {
        XSModelGroupImpl xSModelGroupImpl = null;
        if (this.fParent[b] instanceof XSComplexTypeDecl) {
          XSParticle xSParticle = ((XSComplexTypeDecl)this.fParent[b]).getParticle();
          if (xSParticle != null)
            xSModelGroupImpl = (XSModelGroupImpl)xSParticle.getTerm(); 
        } else {
          xSModelGroupImpl = ((XSGroupDecl)this.fParent[b]).fModelGroup;
        } 
        if (xSModelGroupImpl != null)
          removeParticle(xSModelGroupImpl, this.fParticle[b]); 
      } 
    } 
  }
  
  private boolean removeParticle(XSModelGroupImpl paramXSModelGroupImpl, XSParticleDecl paramXSParticleDecl) {
    for (byte b = 0; b < paramXSModelGroupImpl.fParticleCount; b++) {
      XSParticleDecl xSParticleDecl = paramXSModelGroupImpl.fParticles[b];
      if (xSParticleDecl == paramXSParticleDecl) {
        for (byte b1 = b; b1 < paramXSModelGroupImpl.fParticleCount - 1; b1++)
          paramXSModelGroupImpl.fParticles[b1] = paramXSModelGroupImpl.fParticles[b1 + 1]; 
        paramXSModelGroupImpl.fParticleCount--;
        return true;
      } 
      if (xSParticleDecl.fType == 3 && removeParticle((XSModelGroupImpl)xSParticleDecl.fValue, paramXSParticleDecl))
        return true; 
    } 
    return false;
  }
  
  void fillInLocalElemInfo(Element paramElement, XSDocumentInfo paramXSDocumentInfo, int paramInt, XSObject paramXSObject, XSParticleDecl paramXSParticleDecl) {
    if (this.fParticle.length == this.fLocalElemStackPos) {
      XSParticleDecl[] arrayOfXSParticleDecl = new XSParticleDecl[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fParticle, 0, arrayOfXSParticleDecl, 0, this.fLocalElemStackPos);
      this.fParticle = arrayOfXSParticleDecl;
      Element[] arrayOfElement = new Element[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fLocalElementDecl, 0, arrayOfElement, 0, this.fLocalElemStackPos);
      this.fLocalElementDecl = arrayOfElement;
      XSDocumentInfo[] arrayOfXSDocumentInfo = new XSDocumentInfo[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fLocalElementDecl_schema, 0, arrayOfXSDocumentInfo, 0, this.fLocalElemStackPos);
      this.fLocalElementDecl_schema = arrayOfXSDocumentInfo;
      int[] arrayOfInt = new int[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fAllContext, 0, arrayOfInt, 0, this.fLocalElemStackPos);
      this.fAllContext = arrayOfInt;
      XSObject[] arrayOfXSObject = new XSObject[this.fLocalElemStackPos + 10];
      System.arraycopy(this.fParent, 0, arrayOfXSObject, 0, this.fLocalElemStackPos);
      this.fParent = arrayOfXSObject;
      String[][] arrayOfString = new String[this.fLocalElemStackPos + 10][];
      System.arraycopy(this.fLocalElemNamespaceContext, 0, arrayOfString, 0, this.fLocalElemStackPos);
      this.fLocalElemNamespaceContext = arrayOfString;
    } 
    this.fParticle[this.fLocalElemStackPos] = paramXSParticleDecl;
    this.fLocalElementDecl[this.fLocalElemStackPos] = paramElement;
    this.fLocalElementDecl_schema[this.fLocalElemStackPos] = paramXSDocumentInfo;
    this.fAllContext[this.fLocalElemStackPos] = paramInt;
    this.fParent[this.fLocalElemStackPos] = paramXSObject;
    this.fLocalElemNamespaceContext[this.fLocalElemStackPos++] = paramXSDocumentInfo.fNamespaceSupport.getEffectiveLocalContext();
  }
  
  void checkForDuplicateNames(String paramString, Hashtable paramHashtable1, Hashtable paramHashtable2, Element paramElement, XSDocumentInfo paramXSDocumentInfo) {
    Object object = null;
    if ((object = paramHashtable1.get(paramString)) == null) {
      paramHashtable1.put(paramString, paramElement);
      paramHashtable2.put(paramString, paramXSDocumentInfo);
    } else {
      Element element1 = (Element)object;
      XSDocumentInfo xSDocumentInfo1 = paramHashtable2.get(paramString);
      if (element1 == paramElement)
        return; 
      Element element2 = null;
      XSDocumentInfo xSDocumentInfo2 = null;
      boolean bool = true;
      if (DOMUtil.getLocalName(element2 = DOMUtil.getParent(element1)).equals(SchemaSymbols.ELT_REDEFINE)) {
        xSDocumentInfo2 = (XSDocumentInfo)this.fRedefine2XSDMap.get(element2);
      } else if (DOMUtil.getLocalName(DOMUtil.getParent(paramElement)).equals(SchemaSymbols.ELT_REDEFINE)) {
        xSDocumentInfo2 = xSDocumentInfo1;
        bool = false;
      } 
      if (xSDocumentInfo2 != null) {
        if (xSDocumentInfo1 == paramXSDocumentInfo) {
          reportSchemaError("sch-props-correct.2", new Object[] { paramString }, paramElement);
          return;
        } 
        String str = paramString.substring(paramString.lastIndexOf(',') + 1) + "_fn3dktizrknc9pi";
        if (xSDocumentInfo2 == paramXSDocumentInfo) {
          paramElement.setAttribute(SchemaSymbols.ATT_NAME, str);
          if (paramXSDocumentInfo.fTargetNamespace == null) {
            paramHashtable1.put("," + str, paramElement);
            paramHashtable2.put("," + str, paramXSDocumentInfo);
          } else {
            paramHashtable1.put(paramXSDocumentInfo.fTargetNamespace + "," + str, paramElement);
            paramHashtable2.put(paramXSDocumentInfo.fTargetNamespace + "," + str, paramXSDocumentInfo);
          } 
          if (paramXSDocumentInfo.fTargetNamespace == null) {
            checkForDuplicateNames("," + str, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } else {
            checkForDuplicateNames(paramXSDocumentInfo.fTargetNamespace + "," + str, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } 
        } else if (bool) {
          if (paramXSDocumentInfo.fTargetNamespace == null) {
            checkForDuplicateNames("," + str, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } else {
            checkForDuplicateNames(paramXSDocumentInfo.fTargetNamespace + "," + str, paramHashtable1, paramHashtable2, paramElement, paramXSDocumentInfo);
          } 
        } else {
          reportSchemaError("sch-props-correct.2", new Object[] { paramString }, paramElement);
        } 
      } else {
        reportSchemaError("sch-props-correct.2", new Object[] { paramString }, paramElement);
      } 
    } 
  }
  
  private void renameRedefiningComponents(XSDocumentInfo paramXSDocumentInfo, Element paramElement, String paramString1, String paramString2, String paramString3) {
    if (paramString1.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
      Element element = DOMUtil.getFirstChildElement(paramElement);
      if (element == null) {
        reportSchemaError("src-redefine.5.a.a", null, paramElement);
      } else {
        String str = DOMUtil.getLocalName(element);
        if (str.equals(SchemaSymbols.ELT_ANNOTATION))
          element = DOMUtil.getNextSiblingElement(element); 
        if (element == null) {
          reportSchemaError("src-redefine.5.a.a", null, paramElement);
        } else {
          str = DOMUtil.getLocalName(element);
          if (!str.equals(SchemaSymbols.ELT_RESTRICTION)) {
            reportSchemaError("src-redefine.5.a.b", new Object[] { str }, paramElement);
          } else {
            Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element, false, paramXSDocumentInfo);
            QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_BASE];
            if (qName == null || qName.uri != paramXSDocumentInfo.fTargetNamespace || !qName.localpart.equals(paramString2)) {
              reportSchemaError("src-redefine.5.a.c", new Object[] { str, ((paramXSDocumentInfo.fTargetNamespace == null) ? "" : paramXSDocumentInfo.fTargetNamespace) + "," + paramString2 }, paramElement);
            } else if (qName.prefix != null && qName.prefix.length() > 0) {
              element.setAttribute(SchemaSymbols.ATT_BASE, qName.prefix + ":" + paramString3);
            } else {
              element.setAttribute(SchemaSymbols.ATT_BASE, paramString3);
            } 
            this.fAttributeChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
          } 
        } 
      } 
    } else if (paramString1.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
      Element element = DOMUtil.getFirstChildElement(paramElement);
      if (element == null) {
        reportSchemaError("src-redefine.5.b.a", null, paramElement);
      } else {
        if (DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION))
          element = DOMUtil.getNextSiblingElement(element); 
        if (element == null) {
          reportSchemaError("src-redefine.5.b.a", null, paramElement);
        } else {
          Element element1 = DOMUtil.getFirstChildElement(element);
          if (element1 == null) {
            reportSchemaError("src-redefine.5.b.b", null, element);
          } else {
            String str = DOMUtil.getLocalName(element1);
            if (str.equals(SchemaSymbols.ELT_ANNOTATION))
              element1 = DOMUtil.getNextSiblingElement(element1); 
            if (element1 == null) {
              reportSchemaError("src-redefine.5.b.b", null, element);
            } else {
              str = DOMUtil.getLocalName(element1);
              if (!str.equals(SchemaSymbols.ELT_RESTRICTION) && !str.equals(SchemaSymbols.ELT_EXTENSION)) {
                reportSchemaError("src-redefine.5.b.c", new Object[] { str }, element1);
              } else {
                Object[] arrayOfObject = this.fAttributeChecker.checkAttributes(element1, false, paramXSDocumentInfo);
                QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_BASE];
                if (qName == null || qName.uri != paramXSDocumentInfo.fTargetNamespace || !qName.localpart.equals(paramString2)) {
                  reportSchemaError("src-redefine.5.b.d", new Object[] { str, ((paramXSDocumentInfo.fTargetNamespace == null) ? "" : paramXSDocumentInfo.fTargetNamespace) + "," + paramString2 }, element1);
                } else if (qName.prefix != null && qName.prefix.length() > 0) {
                  element1.setAttribute(SchemaSymbols.ATT_BASE, qName.prefix + ":" + paramString3);
                } else {
                  element1.setAttribute(SchemaSymbols.ATT_BASE, paramString3);
                } 
              } 
            } 
          } 
        } 
      } 
    } else if (paramString1.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
      String str = (paramXSDocumentInfo.fTargetNamespace == null) ? ("," + paramString2) : (paramXSDocumentInfo.fTargetNamespace + "," + paramString2);
      int i = changeRedefineGroup(str, paramString1, paramString3, paramElement, paramXSDocumentInfo);
      if (i > 1) {
        reportSchemaError("src-redefine.7.1", new Object[] { new Integer(i) }, paramElement);
      } else if (i != 1) {
        if (paramXSDocumentInfo.fTargetNamespace == null) {
          this.fRedefinedRestrictedAttributeGroupRegistry.put(str, "," + paramString3);
        } else {
          this.fRedefinedRestrictedAttributeGroupRegistry.put(str, paramXSDocumentInfo.fTargetNamespace + "," + paramString3);
        } 
      } 
    } else if (paramString1.equals(SchemaSymbols.ELT_GROUP)) {
      String str = (paramXSDocumentInfo.fTargetNamespace == null) ? ("," + paramString2) : (paramXSDocumentInfo.fTargetNamespace + "," + paramString2);
      int i = changeRedefineGroup(str, paramString1, paramString3, paramElement, paramXSDocumentInfo);
      if (i > 1) {
        reportSchemaError("src-redefine.6.1.1", new Object[] { new Integer(i) }, paramElement);
      } else if (i != 1) {
        if (paramXSDocumentInfo.fTargetNamespace == null) {
          this.fRedefinedRestrictedGroupRegistry.put(str, "," + paramString3);
        } else {
          this.fRedefinedRestrictedGroupRegistry.put(str, paramXSDocumentInfo.fTargetNamespace + "," + paramString3);
        } 
      } 
    } else {
      reportSchemaError("Internal-Error", new Object[] { "could not handle this particular <redefine>; please submit your schemas and instance document in a bug report!" }, paramElement);
    } 
  }
  
  private String findQName(String paramString, XSDocumentInfo paramXSDocumentInfo) {
    SchemaNamespaceSupport schemaNamespaceSupport = paramXSDocumentInfo.fNamespaceSupport;
    int i = paramString.indexOf(':');
    String str1 = XMLSymbols.EMPTY_STRING;
    if (i > 0)
      str1 = paramString.substring(0, i); 
    String str2 = schemaNamespaceSupport.getURI(this.fSymbolTable.addSymbol(str1));
    String str3 = (i == 0) ? paramString : paramString.substring(i + 1);
    if (str1 == XMLSymbols.EMPTY_STRING && str2 == null && paramXSDocumentInfo.fIsChameleonSchema)
      str2 = paramXSDocumentInfo.fTargetNamespace; 
    return (str2 == null) ? ("," + str3) : (str2 + "," + str3);
  }
  
  private int changeRedefineGroup(String paramString1, String paramString2, String paramString3, Element paramElement, XSDocumentInfo paramXSDocumentInfo) {
    int i = 0;
    for (Element element = DOMUtil.getFirstChildElement(paramElement); element != null; element = DOMUtil.getNextSiblingElement(element)) {
      String str = DOMUtil.getLocalName(element);
      if (!str.equals(paramString2)) {
        i += changeRedefineGroup(paramString1, paramString2, paramString3, element, paramXSDocumentInfo);
      } else {
        String str1 = element.getAttribute(SchemaSymbols.ATT_REF);
        if (str1.length() != 0) {
          String str2 = findQName(str1, paramXSDocumentInfo);
          if (paramString1.equals(str2)) {
            String str3 = XMLSymbols.EMPTY_STRING;
            int j = str1.indexOf(":");
            if (j > 0) {
              str3 = str1.substring(0, j);
              element.setAttribute(SchemaSymbols.ATT_REF, str3 + ":" + paramString3);
            } else {
              element.setAttribute(SchemaSymbols.ATT_REF, paramString3);
            } 
            i++;
            if (paramString2.equals(SchemaSymbols.ELT_GROUP)) {
              String str4 = element.getAttribute(SchemaSymbols.ATT_MINOCCURS);
              String str5 = element.getAttribute(SchemaSymbols.ATT_MAXOCCURS);
              if ((str5.length() != 0 && !str5.equals("1")) || (str4.length() != 0 && !str4.equals("1")))
                reportSchemaError("src-redefine.6.1.2", new Object[] { str1 }, element); 
            } 
          } 
        } 
      } 
    } 
    return i;
  }
  
  private XSDocumentInfo findXSDocumentForDecl(XSDocumentInfo paramXSDocumentInfo1, Element paramElement, XSDocumentInfo paramXSDocumentInfo2) {
    XSDocumentInfo xSDocumentInfo = paramXSDocumentInfo2;
    return (xSDocumentInfo == null) ? null : xSDocumentInfo;
  }
  
  private boolean nonAnnotationContent(Element paramElement) {
    for (Element element = DOMUtil.getFirstChildElement(paramElement); element != null; element = DOMUtil.getNextSiblingElement(element)) {
      if (!DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION))
        return true; 
    } 
    return false;
  }
  
  private void setSchemasVisible(XSDocumentInfo paramXSDocumentInfo) {
    if (DOMUtil.isHidden(paramXSDocumentInfo.fSchemaElement, this.fHiddenNodes)) {
      DOMUtil.setVisible(paramXSDocumentInfo.fSchemaElement, this.fHiddenNodes);
      Vector vector = (Vector)this.fDependencyMap.get(paramXSDocumentInfo);
      for (byte b = 0; b < vector.size(); b++)
        setSchemasVisible(vector.elementAt(b)); 
    } 
  }
  
  public SimpleLocator element2Locator(Element paramElement) {
    if (!(paramElement instanceof ElementImpl))
      return null; 
    SimpleLocator simpleLocator = new SimpleLocator();
    return element2Locator(paramElement, simpleLocator) ? simpleLocator : null;
  }
  
  public boolean element2Locator(Element paramElement, SimpleLocator paramSimpleLocator) {
    if (paramSimpleLocator == null)
      return false; 
    if (paramElement instanceof ElementImpl) {
      ElementImpl elementImpl = (ElementImpl)paramElement;
      Document document = elementImpl.getOwnerDocument();
      String str = (String)this.fDoc2SystemId.get(DOMUtil.getRoot(document));
      int i = elementImpl.getLineNumber();
      int j = elementImpl.getColumnNumber();
      paramSimpleLocator.setValues(str, str, i, j, elementImpl.getCharacterOffset());
      return true;
    } 
    return false;
  }
  
  void reportSchemaError(String paramString, Object[] paramArrayOfObject, Element paramElement) {
    if (element2Locator(paramElement, this.xl)) {
      this.fErrorReporter.reportError((XMLLocator)this.xl, "http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)1);
    } else {
      this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)1);
    } 
  }
  
  void reportSchemaWarning(String paramString, Object[] paramArrayOfObject, Element paramElement) {
    if (element2Locator(paramElement, this.xl)) {
      this.fErrorReporter.reportError((XMLLocator)this.xl, "http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)0);
    } else {
      this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", paramString, paramArrayOfObject, (short)0);
    } 
  }
  
  public void setGenerateSyntheticAnnotations(boolean paramBoolean) {
    this.fSchemaParser.setFeature("http://apache.org/xml/features/generate-synthetic-annotations", paramBoolean);
  }
  
  private static class XSDKey {
    String systemId;
    
    short referType;
    
    String referNS;
    
    XSDKey(String param1String1, short param1Short, String param1String2) {
      this.systemId = param1String1;
      this.referType = param1Short;
      this.referNS = param1String2;
    }
    
    public int hashCode() {
      return (this.referNS == null) ? 0 : this.referNS.hashCode();
    }
    
    public boolean equals(Object param1Object) {
      if (!(param1Object instanceof XSDKey))
        return false; 
      XSDKey xSDKey = (XSDKey)param1Object;
      return (this.referNS != xSDKey.referNS) ? false : (!(this.systemId == null || !this.systemId.equals(xSDKey.systemId)));
    }
  }
  
  private static class XSAnnotationGrammarPool implements XMLGrammarPool {
    private XSGrammarBucket fGrammarBucket;
    
    private Grammar[] fInitialGrammarSet;
    
    private XSAnnotationGrammarPool() {}
    
    public Grammar[] retrieveInitialGrammarSet(String param1String) {
      if (param1String == "http://www.w3.org/2001/XMLSchema") {
        if (this.fInitialGrammarSet == null)
          if (this.fGrammarBucket == null) {
            this.fInitialGrammarSet = new Grammar[] { (Grammar)SchemaGrammar.SG_Schema4Annotations };
          } else {
            SchemaGrammar[] arrayOfSchemaGrammar = this.fGrammarBucket.getGrammars();
            for (byte b = 0; b < arrayOfSchemaGrammar.length; b++) {
              if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(arrayOfSchemaGrammar[b].getTargetNamespace())) {
                this.fInitialGrammarSet = (Grammar[])arrayOfSchemaGrammar;
                return this.fInitialGrammarSet;
              } 
            } 
            Grammar[] arrayOfGrammar = new Grammar[arrayOfSchemaGrammar.length + 1];
            System.arraycopy(arrayOfSchemaGrammar, 0, arrayOfGrammar, 0, arrayOfSchemaGrammar.length);
            arrayOfGrammar[arrayOfGrammar.length - 1] = (Grammar)SchemaGrammar.SG_Schema4Annotations;
            this.fInitialGrammarSet = arrayOfGrammar;
          }  
        return this.fInitialGrammarSet;
      } 
      return new Grammar[0];
    }
    
    public void cacheGrammars(String param1String, Grammar[] param1ArrayOfGrammar) {}
    
    public Grammar retrieveGrammar(XMLGrammarDescription param1XMLGrammarDescription) {
      if (param1XMLGrammarDescription.getGrammarType() == "http://www.w3.org/2001/XMLSchema") {
        String str = ((XMLSchemaDescription)param1XMLGrammarDescription).getTargetNamespace();
        if (this.fGrammarBucket != null) {
          SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(str);
          if (schemaGrammar != null)
            return (Grammar)schemaGrammar; 
        } 
        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(str))
          return (Grammar)SchemaGrammar.SG_Schema4Annotations; 
      } 
      return null;
    }
    
    public void refreshGrammars(XSGrammarBucket param1XSGrammarBucket) {
      this.fGrammarBucket = param1XSGrammarBucket;
      this.fInitialGrammarSet = null;
    }
    
    public void lockPool() {}
    
    public void unlockPool() {}
    
    public void clear() {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */