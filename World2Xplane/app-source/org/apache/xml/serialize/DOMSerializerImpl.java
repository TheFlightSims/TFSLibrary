package org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Vector;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.DOMErrorImpl;
import org.apache.xerces.dom.DOMLocatorImpl;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.dom.DOMNormalizer;
import org.apache.xerces.dom.DOMStringListImpl;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XML11Char;
import org.apache.xerces.util.XMLChar;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.ls.LSSerializerFilter;

public class DOMSerializerImpl implements LSSerializer, DOMConfiguration {
  private XMLSerializer serializer;
  
  private XML11Serializer xml11Serializer;
  
  private DOMStringList fRecognizedParameters;
  
  protected short features = 0;
  
  protected static final short NAMESPACES = 1;
  
  protected static final short WELLFORMED = 2;
  
  protected static final short ENTITIES = 4;
  
  protected static final short CDATA = 8;
  
  protected static final short SPLITCDATA = 16;
  
  protected static final short COMMENTS = 32;
  
  protected static final short DISCARDDEFAULT = 64;
  
  protected static final short INFOSET = 128;
  
  protected static final short XMLDECL = 256;
  
  protected static final short NSDECL = 512;
  
  protected static final short DOM_ELEMENT_CONTENT_WHITESPACE = 1024;
  
  protected static final short PRETTY_PRINT = 2048;
  
  private DOMErrorHandler fErrorHandler = null;
  
  private final DOMErrorImpl fError = new DOMErrorImpl();
  
  private final DOMLocatorImpl fLocator = new DOMLocatorImpl();
  
  public DOMSerializerImpl() {
    this.features = (short)(this.features | 0x1);
    this.features = (short)(this.features | 0x4);
    this.features = (short)(this.features | 0x20);
    this.features = (short)(this.features | 0x8);
    this.features = (short)(this.features | 0x10);
    this.features = (short)(this.features | 0x2);
    this.features = (short)(this.features | 0x200);
    this.features = (short)(this.features | 0x400);
    this.features = (short)(this.features | 0x40);
    this.features = (short)(this.features | 0x100);
    this.serializer = new XMLSerializer();
    initSerializer(this.serializer);
  }
  
  public DOMConfiguration getDomConfig() {
    return this;
  }
  
  public void setParameter(String paramString, Object paramObject) throws DOMException {
    if (paramObject instanceof Boolean) {
      boolean bool = ((Boolean)paramObject).booleanValue();
      if (paramString.equalsIgnoreCase("infoset")) {
        if (bool) {
          this.features = (short)(this.features & 0xFFFFFFFB);
          this.features = (short)(this.features & 0xFFFFFFF7);
          this.features = (short)(this.features | 0x1);
          this.features = (short)(this.features | 0x200);
          this.features = (short)(this.features | 0x2);
          this.features = (short)(this.features | 0x20);
        } 
      } else if (paramString.equalsIgnoreCase("xml-declaration")) {
        this.features = bool ? (short)(this.features | 0x100) : (short)(this.features & 0xFFFFFEFF);
      } else if (paramString.equalsIgnoreCase("namespaces")) {
        this.features = bool ? (short)(this.features | 0x1) : (short)(this.features & 0xFFFFFFFE);
        this.serializer.fNamespaces = bool;
      } else if (paramString.equalsIgnoreCase("split-cdata-sections")) {
        this.features = bool ? (short)(this.features | 0x10) : (short)(this.features & 0xFFFFFFEF);
      } else if (paramString.equalsIgnoreCase("discard-default-content")) {
        this.features = bool ? (short)(this.features | 0x40) : (short)(this.features & 0xFFFFFFBF);
      } else if (paramString.equalsIgnoreCase("well-formed")) {
        this.features = bool ? (short)(this.features | 0x2) : (short)(this.features & 0xFFFFFFFD);
      } else if (paramString.equalsIgnoreCase("entities")) {
        this.features = bool ? (short)(this.features | 0x4) : (short)(this.features & 0xFFFFFFFB);
      } else if (paramString.equalsIgnoreCase("cdata-sections")) {
        this.features = bool ? (short)(this.features | 0x8) : (short)(this.features & 0xFFFFFFF7);
      } else if (paramString.equalsIgnoreCase("comments")) {
        this.features = bool ? (short)(this.features | 0x20) : (short)(this.features & 0xFFFFFFDF);
      } else if (paramString.equalsIgnoreCase("format-pretty-print")) {
        this.features = bool ? (short)(this.features | 0x800) : (short)(this.features & 0xFFFFF7FF);
      } else if (paramString.equalsIgnoreCase("canonical-form") || paramString.equalsIgnoreCase("validate-if-schema") || paramString.equalsIgnoreCase("validate") || paramString.equalsIgnoreCase("check-character-normalization") || paramString.equalsIgnoreCase("datatype-normalization") || paramString.equalsIgnoreCase("normalize-characters")) {
        if (bool) {
          String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
          throw new DOMException((short)9, str);
        } 
      } else if (paramString.equalsIgnoreCase("namespace-declarations")) {
        this.features = bool ? (short)(this.features | 0x200) : (short)(this.features & 0xFFFFFDFF);
        this.serializer.fNamespacePrefixes = bool;
      } else if (paramString.equalsIgnoreCase("element-content-whitespace") || paramString.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
        if (!bool) {
          String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
          throw new DOMException((short)9, str);
        } 
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { paramString });
        throw new DOMException((short)9, str);
      } 
    } else if (paramString.equalsIgnoreCase("error-handler")) {
      if (paramObject == null || paramObject instanceof DOMErrorHandler) {
        this.fErrorHandler = (DOMErrorHandler)paramObject;
      } else {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { paramString });
        throw new DOMException((short)17, str);
      } 
    } else {
      if (paramString.equalsIgnoreCase("resource-resolver") || paramString.equalsIgnoreCase("schema-location") || (paramString.equalsIgnoreCase("schema-type") && paramObject != null)) {
        String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
        throw new DOMException((short)9, str1);
      } 
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { paramString });
      throw new DOMException((short)8, str);
    } 
  }
  
  public boolean canSetParameter(String paramString, Object paramObject) {
    if (paramObject == null)
      return true; 
    if (paramObject instanceof Boolean) {
      boolean bool = ((Boolean)paramObject).booleanValue();
      if (paramString.equalsIgnoreCase("namespaces") || paramString.equalsIgnoreCase("split-cdata-sections") || paramString.equalsIgnoreCase("discard-default-content") || paramString.equalsIgnoreCase("xml-declaration") || paramString.equalsIgnoreCase("well-formed") || paramString.equalsIgnoreCase("infoset") || paramString.equalsIgnoreCase("entities") || paramString.equalsIgnoreCase("cdata-sections") || paramString.equalsIgnoreCase("comments") || paramString.equalsIgnoreCase("format-pretty-print") || paramString.equalsIgnoreCase("namespace-declarations"))
        return true; 
      if (paramString.equalsIgnoreCase("canonical-form") || paramString.equalsIgnoreCase("validate-if-schema") || paramString.equalsIgnoreCase("validate") || paramString.equalsIgnoreCase("check-character-normalization") || paramString.equalsIgnoreCase("datatype-normalization") || paramString.equalsIgnoreCase("normalize-characters"))
        return !bool; 
      if (paramString.equalsIgnoreCase("element-content-whitespace") || paramString.equalsIgnoreCase("ignore-unknown-character-denormalizations"))
        return bool; 
    } else if ((paramString.equalsIgnoreCase("error-handler") && paramObject == null) || paramObject instanceof DOMErrorHandler) {
      return true;
    } 
    return false;
  }
  
  public DOMStringList getParameterNames() {
    if (this.fRecognizedParameters == null) {
      Vector vector = new Vector();
      vector.add("namespaces");
      vector.add("split-cdata-sections");
      vector.add("discard-default-content");
      vector.add("xml-declaration");
      vector.add("canonical-form");
      vector.add("validate-if-schema");
      vector.add("validate");
      vector.add("check-character-normalization");
      vector.add("datatype-normalization");
      vector.add("format-pretty-print");
      vector.add("normalize-characters");
      vector.add("well-formed");
      vector.add("infoset");
      vector.add("namespace-declarations");
      vector.add("element-content-whitespace");
      vector.add("entities");
      vector.add("cdata-sections");
      vector.add("comments");
      vector.add("ignore-unknown-character-denormalizations");
      vector.add("error-handler");
      this.fRecognizedParameters = (DOMStringList)new DOMStringListImpl(vector);
    } 
    return this.fRecognizedParameters;
  }
  
  public Object getParameter(String paramString) throws DOMException {
    if (paramString.equalsIgnoreCase("comments"))
      return ((this.features & 0x20) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("namespaces"))
      return ((this.features & 0x1) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("xml-declaration"))
      return ((this.features & 0x100) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("cdata-sections"))
      return ((this.features & 0x8) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("entities"))
      return ((this.features & 0x4) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("split-cdata-sections"))
      return ((this.features & 0x10) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("well-formed"))
      return ((this.features & 0x2) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("namespace-declarations"))
      return ((this.features & 0x200) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("element-content-whitespace") || paramString.equalsIgnoreCase("ignore-unknown-character-denormalizations"))
      return Boolean.TRUE; 
    if (paramString.equalsIgnoreCase("discard-default-content"))
      return ((this.features & 0x40) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("format-pretty-print"))
      return ((this.features & 0x800) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("infoset"))
      return ((this.features & 0x4) == 0 && (this.features & 0x8) == 0 && (this.features & 0x1) != 0 && (this.features & 0x200) != 0 && (this.features & 0x2) != 0 && (this.features & 0x20) != 0) ? Boolean.TRUE : Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("normalize-characters") || paramString.equalsIgnoreCase("canonical-form") || paramString.equalsIgnoreCase("validate-if-schema") || paramString.equalsIgnoreCase("check-character-normalization") || paramString.equalsIgnoreCase("validate") || paramString.equalsIgnoreCase("validate-if-schema") || paramString.equalsIgnoreCase("datatype-normalization"))
      return Boolean.FALSE; 
    if (paramString.equalsIgnoreCase("error-handler"))
      return this.fErrorHandler; 
    if (paramString.equalsIgnoreCase("resource-resolver") || paramString.equalsIgnoreCase("schema-location") || paramString.equalsIgnoreCase("schema-type")) {
      String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramString });
      throw new DOMException((short)9, str1);
    } 
    String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { paramString });
    throw new DOMException((short)8, str);
  }
  
  public String writeToString(Node paramNode) throws DOMException, LSException {
    XMLSerializer xMLSerializer = null;
    String str = _getXmlVersion(paramNode);
    if (str != null && str.equals("1.1")) {
      if (this.xml11Serializer == null) {
        this.xml11Serializer = new XML11Serializer();
        initSerializer(this.xml11Serializer);
      } 
      copySettings(this.serializer, this.xml11Serializer);
      xMLSerializer = this.xml11Serializer;
    } else {
      xMLSerializer = this.serializer;
    } 
    StringWriter stringWriter = new StringWriter();
    try {
      prepareForSerialization(xMLSerializer, paramNode);
      xMLSerializer._format.setEncoding("UTF-16");
      xMLSerializer.setOutputCharStream(stringWriter);
      if (paramNode.getNodeType() == 9) {
        xMLSerializer.serialize((Document)paramNode);
      } else if (paramNode.getNodeType() == 11) {
        xMLSerializer.serialize((DocumentFragment)paramNode);
      } else if (paramNode.getNodeType() == 1) {
        xMLSerializer.serialize((Element)paramNode);
      } else {
        String str1 = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "unable-to-serialize-node", null);
        if (xMLSerializer.fDOMErrorHandler != null) {
          DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
          dOMErrorImpl.fType = "unable-to-serialize-node";
          dOMErrorImpl.fMessage = str1;
          dOMErrorImpl.fSeverity = 3;
          xMLSerializer.fDOMErrorHandler.handleError((DOMError)dOMErrorImpl);
        } 
        throw new LSException((short)82, str1);
      } 
    } catch (LSException lSException) {
      throw lSException;
    } catch (RuntimeException runtimeException) {
      if (runtimeException == DOMNormalizer.abort)
        return null; 
      throw (LSException)DOMUtil.createLSException((short)82, runtimeException).fillInStackTrace();
    } catch (IOException iOException) {
      String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "STRING_TOO_LONG", new Object[] { iOException.getMessage() });
      throw new DOMException((short)2, str1);
    } finally {
      xMLSerializer.clearDocumentState();
    } 
    return stringWriter.toString();
  }
  
  public void setNewLine(String paramString) {
    this.serializer._format.setLineSeparator(paramString);
  }
  
  public String getNewLine() {
    return this.serializer._format.getLineSeparator();
  }
  
  public LSSerializerFilter getFilter() {
    return this.serializer.fDOMFilter;
  }
  
  public void setFilter(LSSerializerFilter paramLSSerializerFilter) {
    this.serializer.fDOMFilter = paramLSSerializerFilter;
  }
  
  private void initSerializer(XMLSerializer paramXMLSerializer) {
    paramXMLSerializer.fNSBinder = new NamespaceSupport();
    paramXMLSerializer.fLocalNSBinder = new NamespaceSupport();
    paramXMLSerializer.fSymbolTable = new SymbolTable();
  }
  
  private void copySettings(XMLSerializer paramXMLSerializer1, XMLSerializer paramXMLSerializer2) {
    paramXMLSerializer2.fDOMErrorHandler = this.fErrorHandler;
    paramXMLSerializer2._format.setEncoding(paramXMLSerializer1._format.getEncoding());
    paramXMLSerializer2._format.setLineSeparator(paramXMLSerializer1._format.getLineSeparator());
    paramXMLSerializer2.fDOMFilter = paramXMLSerializer1.fDOMFilter;
  }
  
  public boolean write(Node paramNode, LSOutput paramLSOutput) throws LSException {
    if (paramNode == null)
      return false; 
    XMLSerializer xMLSerializer = null;
    String str1 = _getXmlVersion(paramNode);
    if (str1 != null && str1.equals("1.1")) {
      if (this.xml11Serializer == null) {
        this.xml11Serializer = new XML11Serializer();
        initSerializer(this.xml11Serializer);
      } 
      copySettings(this.serializer, this.xml11Serializer);
      xMLSerializer = this.xml11Serializer;
    } else {
      xMLSerializer = this.serializer;
    } 
    String str2 = null;
    if ((str2 = paramLSOutput.getEncoding()) == null) {
      str2 = _getInputEncoding(paramNode);
      if (str2 == null) {
        str2 = _getXmlEncoding(paramNode);
        if (str2 == null)
          str2 = "UTF-8"; 
      } 
    } 
    try {
      prepareForSerialization(xMLSerializer, paramNode);
      xMLSerializer._format.setEncoding(str2);
      OutputStream outputStream = paramLSOutput.getByteStream();
      Writer writer = paramLSOutput.getCharacterStream();
      String str = paramLSOutput.getSystemId();
      if (writer == null) {
        if (outputStream == null) {
          if (str == null) {
            String str3 = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "no-output-specified", null);
            if (xMLSerializer.fDOMErrorHandler != null) {
              DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
              dOMErrorImpl.fType = "no-output-specified";
              dOMErrorImpl.fMessage = str3;
              dOMErrorImpl.fSeverity = 3;
              xMLSerializer.fDOMErrorHandler.handleError((DOMError)dOMErrorImpl);
            } 
            throw new LSException((short)82, str3);
          } 
          xMLSerializer.setOutputByteStream(XMLEntityManager.createOutputStream(str));
        } else {
          xMLSerializer.setOutputByteStream(outputStream);
        } 
      } else {
        xMLSerializer.setOutputCharStream(writer);
      } 
      if (paramNode.getNodeType() == 9) {
        xMLSerializer.serialize((Document)paramNode);
      } else if (paramNode.getNodeType() == 11) {
        xMLSerializer.serialize((DocumentFragment)paramNode);
      } else if (paramNode.getNodeType() == 1) {
        xMLSerializer.serialize((Element)paramNode);
      } else {
        return false;
      } 
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      if (xMLSerializer.fDOMErrorHandler != null) {
        DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
        dOMErrorImpl.fException = unsupportedEncodingException;
        dOMErrorImpl.fType = "unsupported-encoding";
        dOMErrorImpl.fMessage = unsupportedEncodingException.getMessage();
        dOMErrorImpl.fSeverity = 3;
        xMLSerializer.fDOMErrorHandler.handleError((DOMError)dOMErrorImpl);
      } 
      throw new LSException((short)82, DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "unsupported-encoding", null));
    } catch (LSException lSException) {
      throw lSException;
    } catch (RuntimeException runtimeException) {
      if (runtimeException == DOMNormalizer.abort)
        return false; 
      throw (LSException)DOMUtil.createLSException((short)82, runtimeException).fillInStackTrace();
    } catch (Exception exception) {
      if (xMLSerializer.fDOMErrorHandler != null) {
        DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
        dOMErrorImpl.fException = exception;
        dOMErrorImpl.fMessage = exception.getMessage();
        dOMErrorImpl.fSeverity = 2;
        xMLSerializer.fDOMErrorHandler.handleError((DOMError)dOMErrorImpl);
      } 
      throw (LSException)DOMUtil.createLSException((short)82, exception).fillInStackTrace();
    } finally {
      xMLSerializer.clearDocumentState();
    } 
    return true;
  }
  
  public boolean writeToURI(Node paramNode, String paramString) throws LSException {
    if (paramNode == null)
      return false; 
    XMLSerializer xMLSerializer = null;
    String str1 = _getXmlVersion(paramNode);
    if (str1 != null && str1.equals("1.1")) {
      if (this.xml11Serializer == null) {
        this.xml11Serializer = new XML11Serializer();
        initSerializer(this.xml11Serializer);
      } 
      copySettings(this.serializer, this.xml11Serializer);
      xMLSerializer = this.xml11Serializer;
    } else {
      xMLSerializer = this.serializer;
    } 
    String str2 = _getInputEncoding(paramNode);
    if (str2 == null) {
      str2 = _getXmlEncoding(paramNode);
      if (str2 == null)
        str2 = "UTF-8"; 
    } 
    try {
      prepareForSerialization(xMLSerializer, paramNode);
      xMLSerializer._format.setEncoding(str2);
      xMLSerializer.setOutputByteStream(XMLEntityManager.createOutputStream(paramString));
      if (paramNode.getNodeType() == 9) {
        xMLSerializer.serialize((Document)paramNode);
      } else if (paramNode.getNodeType() == 11) {
        xMLSerializer.serialize((DocumentFragment)paramNode);
      } else if (paramNode.getNodeType() == 1) {
        xMLSerializer.serialize((Element)paramNode);
      } else {
        return false;
      } 
    } catch (LSException lSException) {
      throw lSException;
    } catch (RuntimeException runtimeException) {
      if (runtimeException == DOMNormalizer.abort)
        return false; 
      throw (LSException)DOMUtil.createLSException((short)82, runtimeException).fillInStackTrace();
    } catch (Exception exception) {
      if (xMLSerializer.fDOMErrorHandler != null) {
        DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
        dOMErrorImpl.fException = exception;
        dOMErrorImpl.fMessage = exception.getMessage();
        dOMErrorImpl.fSeverity = 2;
        xMLSerializer.fDOMErrorHandler.handleError((DOMError)dOMErrorImpl);
      } 
      throw (LSException)DOMUtil.createLSException((short)82, exception).fillInStackTrace();
    } finally {
      xMLSerializer.clearDocumentState();
    } 
    return true;
  }
  
  private void prepareForSerialization(XMLSerializer paramXMLSerializer, Node paramNode) {
    paramXMLSerializer.reset();
    paramXMLSerializer.features = this.features;
    paramXMLSerializer.fDOMErrorHandler = this.fErrorHandler;
    paramXMLSerializer.fNamespaces = ((this.features & 0x1) != 0);
    paramXMLSerializer.fNamespacePrefixes = ((this.features & 0x200) != 0);
    paramXMLSerializer._format.setIndenting(((this.features & 0x800) != 0));
    paramXMLSerializer._format.setOmitComments(((this.features & 0x20) == 0));
    paramXMLSerializer._format.setOmitXMLDeclaration(((this.features & 0x100) == 0));
    if ((this.features & 0x2) != 0) {
      Node node = paramNode;
      boolean bool = true;
      Document document = (paramNode.getNodeType() == 9) ? (Document)paramNode : paramNode.getOwnerDocument();
      try {
        Method method = document.getClass().getMethod("isXMLVersionChanged()", new Class[0]);
        if (method != null)
          bool = ((Boolean)method.invoke(document, (Object[])null)).booleanValue(); 
      } catch (Exception exception) {}
      if (paramNode.getFirstChild() != null) {
        while (paramNode != null) {
          verify(paramNode, bool, false);
          Node node1 = paramNode.getFirstChild();
          while (node1 == null) {
            node1 = paramNode.getNextSibling();
            if (node1 == null) {
              paramNode = paramNode.getParentNode();
              if (node == paramNode) {
                node1 = null;
                break;
              } 
              node1 = paramNode.getNextSibling();
            } 
          } 
          paramNode = node1;
        } 
      } else {
        verify(paramNode, bool, false);
      } 
    } 
  }
  
  private void verify(Node paramNode, boolean paramBoolean1, boolean paramBoolean2) {
    NamedNodeMap namedNodeMap;
    ProcessingInstruction processingInstruction;
    String str;
    short s = paramNode.getNodeType();
    this.fLocator.fRelatedNode = paramNode;
    switch (s) {
      case 1:
        if (paramBoolean1) {
          boolean bool;
          if ((this.features & 0x1) != 0) {
            bool = CoreDocumentImpl.isValidQName(paramNode.getPrefix(), paramNode.getLocalName(), paramBoolean2);
          } else {
            bool = CoreDocumentImpl.isXMLName(paramNode.getNodeName(), paramBoolean2);
          } 
          if (!bool && !bool && this.fErrorHandler != null) {
            String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "wf-invalid-character-in-node-name", new Object[] { "Element", paramNode.getNodeName() });
            DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, str1, (short)3, "wf-invalid-character-in-node-name");
          } 
        } 
        namedNodeMap = paramNode.hasAttributes() ? paramNode.getAttributes() : null;
        if (namedNodeMap != null)
          for (byte b = 0; b < namedNodeMap.getLength(); b++) {
            Attr attr = (Attr)namedNodeMap.item(b);
            this.fLocator.fRelatedNode = attr;
            DOMNormalizer.isAttrValueWF(this.fErrorHandler, this.fError, this.fLocator, namedNodeMap, attr, attr.getValue(), paramBoolean2);
            if (paramBoolean1) {
              boolean bool = CoreDocumentImpl.isXMLName(attr.getNodeName(), paramBoolean2);
              if (!bool) {
                String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "wf-invalid-character-in-node-name", new Object[] { "Attr", paramNode.getNodeName() });
                DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, str1, (short)3, "wf-invalid-character-in-node-name");
              } 
            } 
          }  
        break;
      case 8:
        if ((this.features & 0x20) != 0)
          DOMNormalizer.isCommentWF(this.fErrorHandler, this.fError, this.fLocator, ((Comment)paramNode).getData(), paramBoolean2); 
        break;
      case 5:
        if (paramBoolean1 && (this.features & 0x4) != 0)
          CoreDocumentImpl.isXMLName(paramNode.getNodeName(), paramBoolean2); 
        break;
      case 4:
        DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, paramNode.getNodeValue(), paramBoolean2);
        break;
      case 3:
        DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, paramNode.getNodeValue(), paramBoolean2);
        break;
      case 7:
        processingInstruction = (ProcessingInstruction)paramNode;
        str = processingInstruction.getTarget();
        if (paramBoolean1) {
          boolean bool;
          if (paramBoolean2) {
            bool = XML11Char.isXML11ValidName(str);
          } else {
            bool = XMLChar.isValidName(str);
          } 
          if (!bool) {
            String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "wf-invalid-character-in-node-name", new Object[] { "Element", paramNode.getNodeName() });
            DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, str1, (short)3, "wf-invalid-character-in-node-name");
          } 
        } 
        DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, processingInstruction.getData(), paramBoolean2);
        break;
    } 
  }
  
  private String _getXmlVersion(Node paramNode) {
    Document document = (paramNode.getNodeType() == 9) ? (Document)paramNode : paramNode.getOwnerDocument();
    if (document != null && DocumentMethods.fgDocumentMethodsAvailable)
      try {
        return (String)DocumentMethods.fgDocumentGetXmlVersionMethod.invoke(document, (Object[])null);
      } catch (VirtualMachineError virtualMachineError) {
        throw virtualMachineError;
      } catch (ThreadDeath threadDeath) {
        throw threadDeath;
      } catch (Throwable throwable) {} 
    return null;
  }
  
  private String _getInputEncoding(Node paramNode) {
    Document document = (paramNode.getNodeType() == 9) ? (Document)paramNode : paramNode.getOwnerDocument();
    if (document != null && DocumentMethods.fgDocumentMethodsAvailable)
      try {
        return (String)DocumentMethods.fgDocumentGetInputEncodingMethod.invoke(document, (Object[])null);
      } catch (VirtualMachineError virtualMachineError) {
        throw virtualMachineError;
      } catch (ThreadDeath threadDeath) {
        throw threadDeath;
      } catch (Throwable throwable) {} 
    return null;
  }
  
  private String _getXmlEncoding(Node paramNode) {
    Document document = (paramNode.getNodeType() == 9) ? (Document)paramNode : paramNode.getOwnerDocument();
    if (document != null && DocumentMethods.fgDocumentMethodsAvailable)
      try {
        return (String)DocumentMethods.fgDocumentGetXmlEncodingMethod.invoke(document, (Object[])null);
      } catch (VirtualMachineError virtualMachineError) {
        throw virtualMachineError;
      } catch (ThreadDeath threadDeath) {
        throw threadDeath;
      } catch (Throwable throwable) {} 
    return null;
  }
  
  static class DocumentMethods {
    private static Method fgDocumentGetXmlVersionMethod = null;
    
    private static Method fgDocumentGetInputEncodingMethod = null;
    
    private static Method fgDocumentGetXmlEncodingMethod = null;
    
    private static boolean fgDocumentMethodsAvailable = false;
    
    static {
      try {
        fgDocumentGetXmlVersionMethod = Document.class.getMethod("getXmlVersion", new Class[0]);
        fgDocumentGetInputEncodingMethod = Document.class.getMethod("getInputEncoding", new Class[0]);
        fgDocumentGetXmlEncodingMethod = Document.class.getMethod("getXmlEncoding", new Class[0]);
        fgDocumentMethodsAvailable = true;
      } catch (Exception exception) {
        fgDocumentGetXmlVersionMethod = null;
        fgDocumentGetInputEncodingMethod = null;
        fgDocumentGetXmlEncodingMethod = null;
        fgDocumentMethodsAvailable = false;
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\DOMSerializerImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */