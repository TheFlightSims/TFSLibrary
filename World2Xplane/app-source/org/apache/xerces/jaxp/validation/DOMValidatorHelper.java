package org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.validation.EntityState;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLParseException;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

final class DOMValidatorHelper implements ValidatorHelper, EntityState {
  private static final int CHUNK_SIZE = 1024;
  
  private static final int CHUNK_MASK = 1023;
  
  private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
  
  private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
  
  private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
  
  private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
  
  private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
  
  private final XMLErrorReporter fErrorReporter;
  
  private final NamespaceSupport fNamespaceContext;
  
  private final DOMNamespaceContext fDOMNamespaceContext = new DOMNamespaceContext(this);
  
  private final XMLSchemaValidator fSchemaValidator;
  
  private final SymbolTable fSymbolTable;
  
  private final ValidationManager fValidationManager;
  
  private final XMLSchemaValidatorComponentManager fComponentManager;
  
  private final SimpleLocator fXMLLocator = new SimpleLocator(null, null, -1, -1, -1);
  
  private DOMDocumentHandler fDOMValidatorHandler;
  
  private final DOMResultAugmentor fDOMResultAugmentor = new DOMResultAugmentor(this);
  
  private final DOMResultBuilder fDOMResultBuilder = new DOMResultBuilder();
  
  private NamedNodeMap fEntities = null;
  
  private final char[] fCharBuffer = new char[1024];
  
  private Node fRoot;
  
  private Node fCurrentElement;
  
  final QName fElementQName = new QName();
  
  final QName fAttributeQName = new QName();
  
  final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
  
  final XMLString fTempString = new XMLString();
  
  public DOMValidatorHelper(XMLSchemaValidatorComponentManager paramXMLSchemaValidatorComponentManager) {
    this.fComponentManager = paramXMLSchemaValidatorComponentManager;
    this.fErrorReporter = (XMLErrorReporter)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    this.fNamespaceContext = (NamespaceSupport)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/namespace-context");
    this.fSchemaValidator = (XMLSchemaValidator)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validator/schema");
    this.fSymbolTable = (SymbolTable)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
    this.fValidationManager = (ValidationManager)this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
  }
  
  public void validate(Source paramSource, Result paramResult) throws SAXException, IOException {
    if (paramResult instanceof DOMResult || paramResult == null) {
      DOMSource dOMSource = (DOMSource)paramSource;
      DOMResult dOMResult = (DOMResult)paramResult;
      Node node = dOMSource.getNode();
      this.fRoot = node;
      if (node != null) {
        this.fComponentManager.reset();
        this.fValidationManager.setEntityState(this);
        this.fDOMNamespaceContext.reset();
        String str = dOMSource.getSystemId();
        this.fXMLLocator.setLiteralSystemId(str);
        this.fXMLLocator.setExpandedSystemId(str);
        this.fErrorReporter.setDocumentLocator((XMLLocator)this.fXMLLocator);
        try {
          setupEntityMap((node.getNodeType() == 9) ? (Document)node : node.getOwnerDocument());
          setupDOMResultHandler(dOMSource, dOMResult);
          this.fSchemaValidator.startDocument((XMLLocator)this.fXMLLocator, null, this.fDOMNamespaceContext, null);
          validate(node);
          this.fSchemaValidator.endDocument(null);
        } catch (XMLParseException xMLParseException) {
          throw Util.toSAXParseException(xMLParseException);
        } catch (XNIException xNIException) {
          throw Util.toSAXException(xNIException);
        } finally {
          this.fRoot = null;
          this.fCurrentElement = null;
          this.fEntities = null;
          if (this.fDOMValidatorHandler != null)
            this.fDOMValidatorHandler.setDOMResult(null); 
        } 
      } 
      return;
    } 
    throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SourceResultMismatch", new Object[] { paramSource.getClass().getName(), paramResult.getClass().getName() }));
  }
  
  public boolean isEntityDeclared(String paramString) {
    return false;
  }
  
  public boolean isEntityUnparsed(String paramString) {
    if (this.fEntities != null) {
      Entity entity = (Entity)this.fEntities.getNamedItem(paramString);
      if (entity != null)
        return (entity.getNotationName() != null); 
    } 
    return false;
  }
  
  private void validate(Node paramNode) {
    // Byte code:
    //   0: aload_1
    //   1: astore_2
    //   2: aload_0
    //   3: aload_2
    //   4: invokespecial useIsSameNode : (Lorg/w3c/dom/Node;)Z
    //   7: istore_3
    //   8: goto -> 114
    //   11: aload_0
    //   12: aload_1
    //   13: invokespecial beginNode : (Lorg/w3c/dom/Node;)V
    //   16: aload_1
    //   17: invokeinterface getFirstChild : ()Lorg/w3c/dom/Node;
    //   22: astore #4
    //   24: goto -> 106
    //   27: aload_0
    //   28: aload_1
    //   29: invokespecial finishNode : (Lorg/w3c/dom/Node;)V
    //   32: aload_2
    //   33: aload_1
    //   34: if_acmpne -> 40
    //   37: goto -> 111
    //   40: aload_1
    //   41: invokeinterface getNextSibling : ()Lorg/w3c/dom/Node;
    //   46: astore #4
    //   48: aload #4
    //   50: ifnonnull -> 106
    //   53: aload_1
    //   54: invokeinterface getParentNode : ()Lorg/w3c/dom/Node;
    //   59: astore_1
    //   60: aload_1
    //   61: ifnull -> 91
    //   64: iload_3
    //   65: ifeq -> 78
    //   68: aload_2
    //   69: aload_1
    //   70: invokeinterface isSameNode : (Lorg/w3c/dom/Node;)Z
    //   75: goto -> 88
    //   78: aload_2
    //   79: aload_1
    //   80: if_acmpne -> 87
    //   83: iconst_1
    //   84: goto -> 88
    //   87: iconst_0
    //   88: ifeq -> 106
    //   91: aload_1
    //   92: ifnull -> 100
    //   95: aload_0
    //   96: aload_1
    //   97: invokespecial finishNode : (Lorg/w3c/dom/Node;)V
    //   100: aconst_null
    //   101: astore #4
    //   103: goto -> 111
    //   106: aload #4
    //   108: ifnull -> 27
    //   111: aload #4
    //   113: astore_1
    //   114: aload_1
    //   115: ifnonnull -> 11
    //   118: return
  }
  
  private void beginNode(Node paramNode) {
    switch (paramNode.getNodeType()) {
      case 1:
        this.fCurrentElement = paramNode;
        this.fNamespaceContext.pushContext();
        fillQName(this.fElementQName, paramNode);
        processAttributes(paramNode.getAttributes());
        this.fSchemaValidator.startElement(this.fElementQName, (XMLAttributes)this.fAttributes, null);
        break;
      case 3:
        if (this.fDOMValidatorHandler != null) {
          this.fDOMValidatorHandler.setIgnoringCharacters(true);
          sendCharactersToValidator(paramNode.getNodeValue());
          this.fDOMValidatorHandler.setIgnoringCharacters(false);
          this.fDOMValidatorHandler.characters((Text)paramNode);
          break;
        } 
        sendCharactersToValidator(paramNode.getNodeValue());
        break;
      case 4:
        if (this.fDOMValidatorHandler != null) {
          this.fDOMValidatorHandler.setIgnoringCharacters(true);
          this.fSchemaValidator.startCDATA(null);
          sendCharactersToValidator(paramNode.getNodeValue());
          this.fSchemaValidator.endCDATA(null);
          this.fDOMValidatorHandler.setIgnoringCharacters(false);
          this.fDOMValidatorHandler.cdata((CDATASection)paramNode);
          break;
        } 
        this.fSchemaValidator.startCDATA(null);
        sendCharactersToValidator(paramNode.getNodeValue());
        this.fSchemaValidator.endCDATA(null);
        break;
      case 7:
        if (this.fDOMValidatorHandler != null)
          this.fDOMValidatorHandler.processingInstruction((ProcessingInstruction)paramNode); 
        break;
      case 8:
        if (this.fDOMValidatorHandler != null)
          this.fDOMValidatorHandler.comment((Comment)paramNode); 
        break;
      case 10:
        if (this.fDOMValidatorHandler != null)
          this.fDOMValidatorHandler.doctypeDecl((DocumentType)paramNode); 
        break;
    } 
  }
  
  private void finishNode(Node paramNode) {
    if (paramNode.getNodeType() == 1) {
      this.fCurrentElement = paramNode;
      fillQName(this.fElementQName, paramNode);
      this.fSchemaValidator.endElement(this.fElementQName, null);
      this.fNamespaceContext.popContext();
    } 
  }
  
  private void setupEntityMap(Document paramDocument) {
    if (paramDocument != null) {
      DocumentType documentType = paramDocument.getDoctype();
      if (documentType != null) {
        this.fEntities = documentType.getEntities();
        return;
      } 
    } 
    this.fEntities = null;
  }
  
  private void setupDOMResultHandler(DOMSource paramDOMSource, DOMResult paramDOMResult) throws SAXException {
    if (paramDOMResult == null) {
      this.fDOMValidatorHandler = null;
      this.fSchemaValidator.setDocumentHandler(null);
      return;
    } 
    Node node = paramDOMResult.getNode();
    if (paramDOMSource.getNode() == node) {
      this.fDOMValidatorHandler = this.fDOMResultAugmentor;
      this.fDOMResultAugmentor.setDOMResult(paramDOMResult);
      this.fSchemaValidator.setDocumentHandler(this.fDOMResultAugmentor);
      return;
    } 
    if (paramDOMResult.getNode() == null)
      try {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        paramDOMResult.setNode(documentBuilder.newDocument());
      } catch (ParserConfigurationException parserConfigurationException) {
        throw new SAXException(parserConfigurationException);
      }  
    this.fDOMValidatorHandler = this.fDOMResultBuilder;
    this.fDOMResultBuilder.setDOMResult(paramDOMResult);
    this.fSchemaValidator.setDocumentHandler(this.fDOMResultBuilder);
  }
  
  private void fillQName(QName paramQName, Node paramNode) {
    String str1 = paramNode.getPrefix();
    String str2 = paramNode.getLocalName();
    String str3 = paramNode.getNodeName();
    String str4 = paramNode.getNamespaceURI();
    paramQName.prefix = (str1 != null) ? this.fSymbolTable.addSymbol(str1) : XMLSymbols.EMPTY_STRING;
    paramQName.localpart = (str2 != null) ? this.fSymbolTable.addSymbol(str2) : XMLSymbols.EMPTY_STRING;
    paramQName.rawname = (str3 != null) ? this.fSymbolTable.addSymbol(str3) : XMLSymbols.EMPTY_STRING;
    paramQName.uri = (str4 != null && str4.length() > 0) ? this.fSymbolTable.addSymbol(str4) : null;
  }
  
  private void processAttributes(NamedNodeMap paramNamedNodeMap) {
    int i = paramNamedNodeMap.getLength();
    this.fAttributes.removeAllAttributes();
    for (byte b = 0; b < i; b++) {
      Attr attr = (Attr)paramNamedNodeMap.item(b);
      String str = attr.getValue();
      if (str == null)
        str = XMLSymbols.EMPTY_STRING; 
      fillQName(this.fAttributeQName, attr);
      this.fAttributes.addAttributeNS(this.fAttributeQName, XMLSymbols.fCDATASymbol, str);
      this.fAttributes.setSpecified(b, attr.getSpecified());
      if (this.fAttributeQName.uri == NamespaceContext.XMLNS_URI)
        if (this.fAttributeQName.prefix == XMLSymbols.PREFIX_XMLNS) {
          this.fNamespaceContext.declarePrefix(this.fAttributeQName.localpart, (str.length() != 0) ? this.fSymbolTable.addSymbol(str) : null);
        } else {
          this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, (str.length() != 0) ? this.fSymbolTable.addSymbol(str) : null);
        }  
    } 
  }
  
  private void sendCharactersToValidator(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 113
    //   4: aload_1
    //   5: invokevirtual length : ()I
    //   8: istore_2
    //   9: iload_2
    //   10: sipush #1023
    //   13: iand
    //   14: istore_3
    //   15: iload_3
    //   16: ifle -> 55
    //   19: aload_1
    //   20: iconst_0
    //   21: iload_3
    //   22: aload_0
    //   23: getfield fCharBuffer : [C
    //   26: iconst_0
    //   27: invokevirtual getChars : (II[CI)V
    //   30: aload_0
    //   31: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   34: aload_0
    //   35: getfield fCharBuffer : [C
    //   38: iconst_0
    //   39: iload_3
    //   40: invokevirtual setValues : ([CII)V
    //   43: aload_0
    //   44: getfield fSchemaValidator : Lorg/apache/xerces/impl/xs/XMLSchemaValidator;
    //   47: aload_0
    //   48: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   51: aconst_null
    //   52: invokevirtual characters : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
    //   55: iload_3
    //   56: istore #4
    //   58: goto -> 107
    //   61: aload_1
    //   62: iload #4
    //   64: wide iinc #4 1024
    //   70: iload #4
    //   72: aload_0
    //   73: getfield fCharBuffer : [C
    //   76: iconst_0
    //   77: invokevirtual getChars : (II[CI)V
    //   80: aload_0
    //   81: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   84: aload_0
    //   85: getfield fCharBuffer : [C
    //   88: iconst_0
    //   89: sipush #1024
    //   92: invokevirtual setValues : ([CII)V
    //   95: aload_0
    //   96: getfield fSchemaValidator : Lorg/apache/xerces/impl/xs/XMLSchemaValidator;
    //   99: aload_0
    //   100: getfield fTempString : Lorg/apache/xerces/xni/XMLString;
    //   103: aconst_null
    //   104: invokevirtual characters : (Lorg/apache/xerces/xni/XMLString;Lorg/apache/xerces/xni/Augmentations;)V
    //   107: iload #4
    //   109: iload_2
    //   110: if_icmplt -> 61
    //   113: return
  }
  
  private boolean useIsSameNode(Node paramNode) {
    if (paramNode instanceof org.apache.xerces.dom.NodeImpl)
      return false; 
    Document document = (paramNode.getNodeType() == 9) ? (Document)paramNode : paramNode.getOwnerDocument();
    return (document != null && document.getImplementation().hasFeature("Core", "3.0"));
  }
  
  Node getCurrentElement() {
    return this.fCurrentElement;
  }
  
  final class DOMNamespaceContext implements NamespaceContext {
    protected String[] fNamespace;
    
    protected int fNamespaceSize;
    
    protected boolean fDOMContextBuilt;
    
    private final DOMValidatorHelper this$0;
    
    DOMNamespaceContext(DOMValidatorHelper this$0) {
      this.this$0 = this$0;
      this.fNamespace = new String[32];
      this.fNamespaceSize = 0;
      this.fDOMContextBuilt = false;
    }
    
    public void pushContext() {
      this.this$0.fNamespaceContext.pushContext();
    }
    
    public void popContext() {
      this.this$0.fNamespaceContext.popContext();
    }
    
    public boolean declarePrefix(String param1String1, String param1String2) {
      return this.this$0.fNamespaceContext.declarePrefix(param1String1, param1String2);
    }
    
    public String getURI(String param1String) {
      String str = this.this$0.fNamespaceContext.getURI(param1String);
      if (str == null) {
        if (!this.fDOMContextBuilt) {
          fillNamespaceContext();
          this.fDOMContextBuilt = true;
        } 
        if (this.fNamespaceSize > 0 && !this.this$0.fNamespaceContext.containsPrefix(param1String))
          str = getURI0(param1String); 
      } 
      return str;
    }
    
    public String getPrefix(String param1String) {
      return this.this$0.fNamespaceContext.getPrefix(param1String);
    }
    
    public int getDeclaredPrefixCount() {
      return this.this$0.fNamespaceContext.getDeclaredPrefixCount();
    }
    
    public String getDeclaredPrefixAt(int param1Int) {
      return this.this$0.fNamespaceContext.getDeclaredPrefixAt(param1Int);
    }
    
    public Enumeration getAllPrefixes() {
      return this.this$0.fNamespaceContext.getAllPrefixes();
    }
    
    public void reset() {
      this.fDOMContextBuilt = false;
      this.fNamespaceSize = 0;
    }
    
    private void fillNamespaceContext() {
      if (this.this$0.fRoot != null)
        for (Node node = this.this$0.fRoot.getParentNode(); node != null; node = node.getParentNode()) {
          if (1 == node.getNodeType()) {
            NamedNodeMap namedNodeMap = node.getAttributes();
            int i = namedNodeMap.getLength();
            for (byte b = 0; b < i; b++) {
              Attr attr = (Attr)namedNodeMap.item(b);
              String str = attr.getValue();
              if (str == null)
                str = XMLSymbols.EMPTY_STRING; 
              this.this$0.fillQName(this.this$0.fAttributeQName, attr);
              if (this.this$0.fAttributeQName.uri == NamespaceContext.XMLNS_URI)
                if (this.this$0.fAttributeQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                  declarePrefix0(this.this$0.fAttributeQName.localpart, (str.length() != 0) ? this.this$0.fSymbolTable.addSymbol(str) : null);
                } else {
                  declarePrefix0(XMLSymbols.EMPTY_STRING, (str.length() != 0) ? this.this$0.fSymbolTable.addSymbol(str) : null);
                }  
            } 
          } 
        }  
    }
    
    private void declarePrefix0(String param1String1, String param1String2) {
      if (this.fNamespaceSize == this.fNamespace.length) {
        String[] arrayOfString = new String[this.fNamespaceSize * 2];
        System.arraycopy(this.fNamespace, 0, arrayOfString, 0, this.fNamespaceSize);
        this.fNamespace = arrayOfString;
      } 
      this.fNamespace[this.fNamespaceSize++] = param1String1;
      this.fNamespace[this.fNamespaceSize++] = param1String2;
    }
    
    private String getURI0(String param1String) {
      for (byte b = 0; b < this.fNamespaceSize; b += 2) {
        if (this.fNamespace[b] == param1String)
          return this.fNamespace[b + 1]; 
      } 
      return null;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\DOMValidatorHelper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */