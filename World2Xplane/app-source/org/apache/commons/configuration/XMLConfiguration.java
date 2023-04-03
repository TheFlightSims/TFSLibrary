/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.StringReader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.transform.Result;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.TransformerFactory;
/*      */ import javax.xml.transform.TransformerFactoryConfigurationError;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import org.apache.commons.configuration.resolver.DefaultEntityResolver;
/*      */ import org.apache.commons.configuration.resolver.EntityRegistry;
/*      */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.Text;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ 
/*      */ public class XMLConfiguration extends AbstractHierarchicalFileConfiguration implements EntityResolver, EntityRegistry {
/*      */   private static final long serialVersionUID = 2453781111653383552L;
/*      */   
/*      */   private static final String DEFAULT_ROOT_NAME = "configuration";
/*      */   
/*      */   private static final String ATTR_SPACE = "xml:space";
/*      */   
/*      */   private static final String VALUE_PRESERVE = "preserve";
/*      */   
/*      */   private static final char ATTR_VALUE_DELIMITER = '|';
/*      */   
/*      */   private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
/*      */   
/*      */   private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
/*      */   
/*      */   private Document document;
/*      */   
/*      */   private String rootElementName;
/*      */   
/*      */   private String publicID;
/*      */   
/*      */   private String systemID;
/*      */   
/*      */   private DocumentBuilder documentBuilder;
/*      */   
/*      */   private boolean validating;
/*      */   
/*      */   private boolean schemaValidation;
/*      */   
/*      */   private boolean attributeSplittingDisabled;
/*      */   
/*  222 */   private EntityResolver entityResolver = (EntityResolver)new DefaultEntityResolver();
/*      */   
/*      */   public XMLConfiguration() {
/*  230 */     setLogger(LogFactory.getLog(XMLConfiguration.class));
/*      */   }
/*      */   
/*      */   public XMLConfiguration(HierarchicalConfiguration c) {
/*  245 */     super(c);
/*  246 */     clearReferences(getRootNode());
/*  247 */     setRootElementName(getRootNode().getName());
/*  248 */     setLogger(LogFactory.getLog(XMLConfiguration.class));
/*      */   }
/*      */   
/*      */   public XMLConfiguration(String fileName) throws ConfigurationException {
/*  260 */     super(fileName);
/*  261 */     setLogger(LogFactory.getLog(XMLConfiguration.class));
/*      */   }
/*      */   
/*      */   public XMLConfiguration(File file) throws ConfigurationException {
/*  273 */     super(file);
/*  274 */     setLogger(LogFactory.getLog(XMLConfiguration.class));
/*      */   }
/*      */   
/*      */   public XMLConfiguration(URL url) throws ConfigurationException {
/*  286 */     super(url);
/*  287 */     setLogger(LogFactory.getLog(XMLConfiguration.class));
/*      */   }
/*      */   
/*      */   public String getRootElementName() {
/*  300 */     if (getDocument() == null)
/*  302 */       return (this.rootElementName == null) ? "configuration" : this.rootElementName; 
/*  306 */     return getDocument().getDocumentElement().getNodeName();
/*      */   }
/*      */   
/*      */   public void setRootElementName(String name) {
/*  324 */     if (getDocument() != null)
/*  326 */       throw new UnsupportedOperationException("The name of the root element cannot be changed when loaded from an XML document!"); 
/*  329 */     this.rootElementName = name;
/*  330 */     getRootNode().setName(name);
/*      */   }
/*      */   
/*      */   public DocumentBuilder getDocumentBuilder() {
/*  343 */     return this.documentBuilder;
/*      */   }
/*      */   
/*      */   public void setDocumentBuilder(DocumentBuilder documentBuilder) {
/*  358 */     this.documentBuilder = documentBuilder;
/*      */   }
/*      */   
/*      */   public String getPublicID() {
/*  371 */     return this.publicID;
/*      */   }
/*      */   
/*      */   public void setPublicID(String publicID) {
/*  384 */     this.publicID = publicID;
/*      */   }
/*      */   
/*      */   public String getSystemID() {
/*  397 */     return this.systemID;
/*      */   }
/*      */   
/*      */   public void setSystemID(String systemID) {
/*  410 */     this.systemID = systemID;
/*      */   }
/*      */   
/*      */   public boolean isValidating() {
/*  421 */     return this.validating;
/*      */   }
/*      */   
/*      */   public void setValidating(boolean validating) {
/*  434 */     if (!this.schemaValidation)
/*  436 */       this.validating = validating; 
/*      */   }
/*      */   
/*      */   public boolean isSchemaValidation() {
/*  449 */     return this.schemaValidation;
/*      */   }
/*      */   
/*      */   public void setSchemaValidation(boolean schemaValidation) {
/*  464 */     this.schemaValidation = schemaValidation;
/*  465 */     if (schemaValidation)
/*  467 */       this.validating = true; 
/*      */   }
/*      */   
/*      */   public void setEntityResolver(EntityResolver resolver) {
/*  479 */     this.entityResolver = resolver;
/*      */   }
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/*  489 */     return this.entityResolver;
/*      */   }
/*      */   
/*      */   public boolean isAttributeSplittingDisabled() {
/*  501 */     return this.attributeSplittingDisabled;
/*      */   }
/*      */   
/*      */   public void setAttributeSplittingDisabled(boolean attributeSplittingDisabled) {
/*  554 */     this.attributeSplittingDisabled = attributeSplittingDisabled;
/*      */   }
/*      */   
/*      */   public Document getDocument() {
/*  566 */     return this.document;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  575 */     super.clear();
/*  576 */     setRoot(new HierarchicalConfiguration.Node());
/*  577 */     this.document = null;
/*      */   }
/*      */   
/*      */   public void initProperties(Document document, boolean elemRefs) {
/*  588 */     if (document.getDoctype() != null) {
/*  590 */       setPublicID(document.getDoctype().getPublicId());
/*  591 */       setSystemID(document.getDoctype().getSystemId());
/*      */     } 
/*  594 */     constructHierarchy(getRoot(), document.getDocumentElement(), elemRefs, true);
/*  595 */     getRootNode().setName(document.getDocumentElement().getNodeName());
/*  596 */     if (elemRefs)
/*  598 */       getRoot().setReference(document.getDocumentElement()); 
/*      */   }
/*      */   
/*      */   private void constructHierarchy(HierarchicalConfiguration.Node node, Element element, boolean elemRefs, boolean trim) {
/*  614 */     boolean trimFlag = shouldTrim(element, trim);
/*  615 */     processAttributes(node, element, elemRefs);
/*  616 */     StringBuffer buffer = new StringBuffer();
/*  617 */     NodeList list = element.getChildNodes();
/*  618 */     for (int i = 0; i < list.getLength(); i++) {
/*  620 */       Node w3cNode = list.item(i);
/*  621 */       if (w3cNode instanceof Element) {
/*  623 */         Element child = (Element)w3cNode;
/*  624 */         HierarchicalConfiguration.Node childNode = new XMLNode(child.getTagName(), elemRefs ? child : null);
/*  626 */         constructHierarchy(childNode, child, elemRefs, trimFlag);
/*  627 */         node.addChild(childNode);
/*  628 */         handleDelimiters(node, childNode, trimFlag);
/*  630 */       } else if (w3cNode instanceof Text) {
/*  632 */         Text data = (Text)w3cNode;
/*  633 */         buffer.append(data.getData());
/*      */       } 
/*      */     } 
/*  637 */     String text = buffer.toString();
/*  638 */     if (trimFlag)
/*  640 */       text = text.trim(); 
/*  642 */     if (text.length() > 0 || (!node.hasChildren() && node != getRoot()))
/*  644 */       node.setValue(text); 
/*      */   }
/*      */   
/*      */   private void processAttributes(HierarchicalConfiguration.Node node, Element element, boolean elemRefs) {
/*  658 */     NamedNodeMap attributes = element.getAttributes();
/*  659 */     for (int i = 0; i < attributes.getLength(); i++) {
/*  661 */       Node w3cNode = attributes.item(i);
/*  662 */       if (w3cNode instanceof Attr) {
/*      */         List values;
/*  664 */         Attr attr = (Attr)w3cNode;
/*  666 */         if (isAttributeSplittingDisabled()) {
/*  668 */           values = Collections.singletonList(attr.getValue());
/*      */         } else {
/*  672 */           values = PropertyConverter.split(attr.getValue(), isDelimiterParsingDisabled() ? 124 : getListDelimiter());
/*      */         } 
/*  677 */         for (Iterator it = values.iterator(); it.hasNext(); ) {
/*  679 */           HierarchicalConfiguration.Node child = new XMLNode(attr.getName(), elemRefs ? element : null);
/*  681 */           child.setValue(it.next());
/*  682 */           node.addAttribute((ConfigurationNode)child);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void handleDelimiters(HierarchicalConfiguration.Node parent, HierarchicalConfiguration.Node child, boolean trim) {
/*  698 */     if (child.getValue() != null) {
/*      */       List values;
/*  701 */       if (isDelimiterParsingDisabled()) {
/*  703 */         values = new ArrayList();
/*  704 */         values.add(child.getValue().toString());
/*      */       } else {
/*  708 */         values = PropertyConverter.split(child.getValue().toString(), getListDelimiter(), trim);
/*      */       } 
/*  712 */       if (values.size() > 1) {
/*  714 */         Iterator it = values.iterator();
/*  716 */         HierarchicalConfiguration.Node c = createNode(child.getName());
/*  717 */         c.setValue(it.next());
/*  719 */         Iterator itAttrs = child.getAttributes().iterator();
/*  720 */         while (itAttrs.hasNext()) {
/*  722 */           HierarchicalConfiguration.Node ndAttr = itAttrs.next();
/*  723 */           ndAttr.setReference(null);
/*  724 */           c.addAttribute((ConfigurationNode)ndAttr);
/*      */         } 
/*  726 */         parent.remove(child);
/*  727 */         parent.addChild(c);
/*  730 */         while (it.hasNext()) {
/*  732 */           c = new XMLNode(child.getName(), null);
/*  733 */           c.setValue(it.next());
/*  734 */           parent.addChild(c);
/*      */         } 
/*  737 */       } else if (values.size() == 1) {
/*  741 */         child.setValue(values.get(0));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean shouldTrim(Element element, boolean currentTrim) {
/*  759 */     Attr attr = element.getAttributeNode("xml:space");
/*  761 */     if (attr == null)
/*  763 */       return currentTrim; 
/*  767 */     return !"preserve".equals(attr.getValue());
/*      */   }
/*      */   
/*      */   protected DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
/*  787 */     if (getDocumentBuilder() != null)
/*  789 */       return getDocumentBuilder(); 
/*  793 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  795 */     if (isValidating()) {
/*  797 */       factory.setValidating(true);
/*  798 */       if (isSchemaValidation()) {
/*  800 */         factory.setNamespaceAware(true);
/*  801 */         factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
/*      */       } 
/*      */     } 
/*  805 */     DocumentBuilder result = factory.newDocumentBuilder();
/*  806 */     result.setEntityResolver(this.entityResolver);
/*  808 */     if (isValidating())
/*  811 */       result.setErrorHandler(new DefaultHandler() {
/*      */             private final XMLConfiguration this$0;
/*      */             
/*      */             public void error(SAXParseException ex) throws SAXException {
/*  815 */               throw ex;
/*      */             }
/*      */           }); 
/*  819 */     return result;
/*      */   }
/*      */   
/*      */   protected Document createDocument() throws ConfigurationException {
/*      */     try {
/*  833 */       if (this.document == null) {
/*  835 */         DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/*  836 */         Document newDocument = documentBuilder.newDocument();
/*  837 */         Element rootElem = newDocument.createElement(getRootElementName());
/*  838 */         newDocument.appendChild(rootElem);
/*  839 */         this.document = newDocument;
/*      */       } 
/*  842 */       XMLBuilderVisitor builder = new XMLBuilderVisitor(this.document, isDelimiterParsingDisabled() ? Character.MIN_VALUE : getListDelimiter(), isAttributeSplittingDisabled());
/*  845 */       builder.processDocument(getRoot());
/*  846 */       initRootElementText(this.document, getRootNode().getValue());
/*  847 */       return this.document;
/*  849 */     } catch (DOMException domEx) {
/*  851 */       throw new ConfigurationException(domEx);
/*  853 */     } catch (ParserConfigurationException pex) {
/*  855 */       throw new ConfigurationException(pex);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void initRootElementText(Document doc, Object value) {
/*  867 */     Element elem = doc.getDocumentElement();
/*  868 */     NodeList children = elem.getChildNodes();
/*  871 */     for (int i = 0; i < children.getLength(); i++) {
/*  873 */       Node nd = children.item(i);
/*  874 */       if (nd.getNodeType() == 3)
/*  876 */         elem.removeChild(nd); 
/*      */     } 
/*  880 */     if (value != null)
/*  883 */       elem.appendChild(doc.createTextNode(String.valueOf(value))); 
/*      */   }
/*      */   
/*      */   protected HierarchicalConfiguration.Node createNode(String name) {
/*  896 */     return new XMLNode(name, null);
/*      */   }
/*      */   
/*      */   public void load(InputStream in) throws ConfigurationException {
/*  907 */     load(new InputSource(in));
/*      */   }
/*      */   
/*      */   public void load(Reader in) throws ConfigurationException {
/*  922 */     load(new InputSource(in));
/*      */   }
/*      */   
/*      */   private void load(InputSource source) throws ConfigurationException {
/*      */     try {
/*  934 */       URL sourceURL = getDelegate().getURL();
/*  935 */       if (sourceURL != null)
/*  937 */         source.setSystemId(sourceURL.toString()); 
/*  940 */       DocumentBuilder builder = createDocumentBuilder();
/*  941 */       Document newDocument = builder.parse(source);
/*  942 */       Document oldDocument = this.document;
/*  943 */       this.document = null;
/*  944 */       initProperties(newDocument, (oldDocument == null));
/*  945 */       this.document = (oldDocument == null) ? newDocument : oldDocument;
/*  947 */     } catch (SAXParseException spe) {
/*  949 */       throw new ConfigurationException("Error parsing " + source.getSystemId(), spe);
/*  951 */     } catch (Exception e) {
/*  953 */       getLogger().debug("Unable to load the configuraton", e);
/*  954 */       throw new ConfigurationException("Unable to load the configuration", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void save(Writer writer) throws ConfigurationException {
/*      */     try {
/*  968 */       Transformer transformer = createTransformer();
/*  969 */       Source source = new DOMSource(createDocument());
/*  970 */       Result result = new StreamResult(writer);
/*  971 */       transformer.transform(source, result);
/*  973 */     } catch (TransformerException e) {
/*  975 */       throw new ConfigurationException("Unable to save the configuration", e);
/*  977 */     } catch (TransformerFactoryConfigurationError e) {
/*  979 */       throw new ConfigurationException("Unable to save the configuration", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void validate() throws ConfigurationException {
/*      */     try {
/*  991 */       Transformer transformer = createTransformer();
/*  992 */       Source source = new DOMSource(createDocument());
/*  993 */       StringWriter writer = new StringWriter();
/*  994 */       Result result = new StreamResult(writer);
/*  995 */       transformer.transform(source, result);
/*  996 */       Reader reader = new StringReader(writer.getBuffer().toString());
/*  997 */       DocumentBuilder builder = createDocumentBuilder();
/*  998 */       builder.parse(new InputSource(reader));
/* 1000 */     } catch (SAXException e) {
/* 1002 */       throw new ConfigurationException("Validation failed", e);
/* 1004 */     } catch (IOException e) {
/* 1006 */       throw new ConfigurationException("Validation failed", e);
/* 1008 */     } catch (TransformerException e) {
/* 1010 */       throw new ConfigurationException("Validation failed", e);
/* 1012 */     } catch (ParserConfigurationException pce) {
/* 1014 */       throw new ConfigurationException("Validation failed", pce);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Transformer createTransformer() throws TransformerException {
/* 1030 */     Transformer transformer = TransformerFactory.newInstance().newTransformer();
/* 1033 */     transformer.setOutputProperty("indent", "yes");
/* 1034 */     if (getEncoding() != null)
/* 1036 */       transformer.setOutputProperty("encoding", getEncoding()); 
/* 1038 */     if (getPublicID() != null)
/* 1040 */       transformer.setOutputProperty("doctype-public", getPublicID()); 
/* 1043 */     if (getSystemID() != null)
/* 1045 */       transformer.setOutputProperty("doctype-system", getSystemID()); 
/* 1049 */     return transformer;
/*      */   }
/*      */   
/*      */   public Object clone() {
/* 1062 */     XMLConfiguration copy = (XMLConfiguration)super.clone();
/* 1065 */     copy.document = null;
/* 1066 */     copy.setDelegate(copy.createDelegate());
/* 1068 */     clearReferences(copy.getRootNode());
/* 1070 */     return copy;
/*      */   }
/*      */   
/*      */   protected AbstractHierarchicalFileConfiguration.FileConfigurationDelegate createDelegate() {
/* 1081 */     return new XMLFileConfigurationDelegate();
/*      */   }
/*      */   
/*      */   public void addNodes(String key, Collection nodes) {
/*      */     Collection xmlNodes;
/* 1097 */     if (nodes != null && !nodes.isEmpty()) {
/* 1099 */       xmlNodes = new ArrayList(nodes.size());
/* 1100 */       for (Iterator it = nodes.iterator(); it.hasNext();)
/* 1102 */         xmlNodes.add(convertToXMLNode(it.next())); 
/*      */     } else {
/* 1107 */       xmlNodes = nodes;
/*      */     } 
/* 1110 */     super.addNodes(key, xmlNodes);
/*      */   }
/*      */   
/*      */   private XMLNode convertToXMLNode(ConfigurationNode node) {
/* 1126 */     if (node instanceof XMLNode)
/* 1128 */       return (XMLNode)node; 
/* 1131 */     XMLNode nd = (XMLNode)createNode(node.getName());
/* 1132 */     nd.setValue(node.getValue());
/* 1133 */     nd.setAttribute(node.isAttribute());
/* 1134 */     for (Iterator iterator1 = node.getChildren().iterator(); iterator1.hasNext();)
/* 1136 */       nd.addChild(convertToXMLNode(iterator1.next())); 
/* 1138 */     for (Iterator it = node.getAttributes().iterator(); it.hasNext();)
/* 1140 */       nd.addAttribute((ConfigurationNode)convertToXMLNode(it.next())); 
/* 1142 */     return nd;
/*      */   }
/*      */   
/*      */   public void registerEntityId(String publicId, URL entityURL) {
/* 1183 */     if (this.entityResolver instanceof EntityRegistry)
/* 1185 */       ((EntityRegistry)this.entityResolver).registerEntityId(publicId, entityURL); 
/*      */   }
/*      */   
/*      */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/*      */     try {
/* 1207 */       return this.entityResolver.resolveEntity(publicId, systemId);
/* 1209 */     } catch (IOException e) {
/* 1211 */       throw new SAXException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Map getRegisteredEntities() {
/* 1223 */     if (this.entityResolver instanceof EntityRegistry)
/* 1225 */       return ((EntityRegistry)this.entityResolver).getRegisteredEntities(); 
/* 1227 */     return new HashMap();
/*      */   }
/*      */   
/*      */   class XMLNode extends HierarchicalConfiguration.Node {
/*      */     private static final long serialVersionUID = -4133988932174596562L;
/*      */     
/*      */     private final XMLConfiguration this$0;
/*      */     
/*      */     public XMLNode(String name, Element elem) {
/* 1250 */       super(name);
/* 1251 */       setReference(elem);
/*      */     }
/*      */     
/*      */     public void setValue(Object value) {
/* 1262 */       super.setValue(value);
/* 1264 */       if (getReference() != null && XMLConfiguration.this.document != null)
/* 1266 */         if (isAttribute()) {
/* 1268 */           updateAttribute();
/*      */         } else {
/* 1272 */           updateElement(value);
/*      */         }  
/*      */     }
/*      */     
/*      */     protected void removeReference() {
/* 1282 */       if (getReference() != null) {
/* 1284 */         Element element = (Element)getReference();
/* 1285 */         if (isAttribute()) {
/* 1287 */           updateAttribute();
/*      */         } else {
/* 1291 */           Node parentElem = element.getParentNode();
/* 1292 */           if (parentElem != null)
/* 1294 */             parentElem.removeChild(element); 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void updateElement(Object value) {
/* 1307 */       Text txtNode = findTextNodeForUpdate();
/* 1308 */       if (value == null) {
/* 1311 */         if (txtNode != null)
/* 1313 */           ((Element)getReference()).removeChild(txtNode); 
/* 1318 */       } else if (txtNode == null) {
/* 1320 */         String newValue = XMLConfiguration.this.isDelimiterParsingDisabled() ? value.toString() : PropertyConverter.escapeDelimiters(value.toString(), XMLConfiguration.this.getListDelimiter());
/* 1322 */         txtNode = XMLConfiguration.this.document.createTextNode(newValue);
/* 1323 */         if (((Element)getReference()).getFirstChild() != null) {
/* 1325 */           ((Element)getReference()).insertBefore(txtNode, ((Element)getReference()).getFirstChild());
/*      */         } else {
/* 1330 */           ((Element)getReference()).appendChild(txtNode);
/*      */         } 
/*      */       } else {
/* 1335 */         String newValue = XMLConfiguration.this.isDelimiterParsingDisabled() ? value.toString() : PropertyConverter.escapeDelimiters(value.toString(), XMLConfiguration.this.getListDelimiter());
/* 1337 */         txtNode.setNodeValue(newValue);
/*      */       } 
/*      */     }
/*      */     
/*      */     private void updateAttribute() {
/* 1348 */       XMLConfiguration.XMLBuilderVisitor.updateAttribute(getParent(), getName(), XMLConfiguration.this.getListDelimiter(), XMLConfiguration.this.isAttributeSplittingDisabled());
/*      */     }
/*      */     
/*      */     private Text findTextNodeForUpdate() {
/* 1362 */       Text result = null;
/* 1363 */       Element elem = (Element)getReference();
/* 1365 */       NodeList children = elem.getChildNodes();
/* 1366 */       Collection textNodes = new ArrayList();
/* 1367 */       for (int i = 0; i < children.getLength(); i++) {
/* 1369 */         Node nd = children.item(i);
/* 1370 */         if (nd instanceof Text)
/* 1372 */           if (result == null) {
/* 1374 */             result = (Text)nd;
/*      */           } else {
/* 1378 */             textNodes.add(nd);
/*      */           }  
/*      */       } 
/* 1384 */       if (result instanceof org.w3c.dom.CDATASection) {
/* 1386 */         textNodes.add(result);
/* 1387 */         result = null;
/*      */       } 
/* 1391 */       for (Iterator it = textNodes.iterator(); it.hasNext();)
/* 1393 */         elem.removeChild(it.next()); 
/* 1395 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   static class XMLBuilderVisitor extends HierarchicalConfiguration.BuilderVisitor {
/*      */     private Document document;
/*      */     
/* 1409 */     private char listDelimiter = AbstractConfiguration.getDefaultListDelimiter();
/*      */     
/*      */     private boolean isAttributeSplittingDisabled;
/*      */     
/*      */     public XMLBuilderVisitor(Document doc, char listDelimiter, boolean isAttributeSplittingDisabled) {
/* 1424 */       this.document = doc;
/* 1425 */       this.listDelimiter = listDelimiter;
/* 1426 */       this.isAttributeSplittingDisabled = isAttributeSplittingDisabled;
/*      */     }
/*      */     
/*      */     public void processDocument(HierarchicalConfiguration.Node rootNode) {
/* 1436 */       rootNode.visit(this, (ConfigurationKey)null);
/*      */     }
/*      */     
/*      */     protected Object insert(HierarchicalConfiguration.Node newNode, HierarchicalConfiguration.Node parent, HierarchicalConfiguration.Node sibling1, HierarchicalConfiguration.Node sibling2) {
/* 1451 */       if (newNode.isAttribute()) {
/* 1453 */         updateAttribute(parent, getElement(parent), newNode.getName(), this.listDelimiter, this.isAttributeSplittingDisabled);
/* 1455 */         return null;
/*      */       } 
/* 1460 */       Element elem = this.document.createElement(newNode.getName());
/* 1461 */       if (newNode.getValue() != null) {
/* 1463 */         String txt = newNode.getValue().toString();
/* 1464 */         if (this.listDelimiter != '\000')
/* 1466 */           txt = PropertyConverter.escapeListDelimiter(txt, this.listDelimiter); 
/* 1468 */         elem.appendChild(this.document.createTextNode(txt));
/*      */       } 
/* 1470 */       if (sibling2 == null) {
/* 1472 */         getElement(parent).appendChild(elem);
/* 1474 */       } else if (sibling1 != null) {
/* 1476 */         getElement(parent).insertBefore(elem, getElement(sibling1).getNextSibling());
/*      */       } else {
/* 1480 */         getElement(parent).insertBefore(elem, getElement(parent).getFirstChild());
/*      */       } 
/* 1482 */       return elem;
/*      */     }
/*      */     
/*      */     private static void updateAttribute(HierarchicalConfiguration.Node node, Element elem, String name, char listDelimiter, boolean isAttributeSplittingDisabled) {
/* 1499 */       if (node != null && elem != null) {
/* 1501 */         boolean hasAttribute = false;
/* 1502 */         List attrs = node.getAttributes(name);
/* 1503 */         StringBuffer buf = new StringBuffer();
/* 1504 */         char delimiter = (listDelimiter != '\000') ? listDelimiter : '|';
/* 1505 */         for (Iterator it = attrs.iterator(); it.hasNext(); ) {
/* 1507 */           HierarchicalConfiguration.Node attr = it.next();
/* 1508 */           if (attr.getValue() != null) {
/* 1510 */             hasAttribute = true;
/* 1511 */             if (buf.length() > 0)
/* 1513 */               buf.append(delimiter); 
/* 1515 */             String value = isAttributeSplittingDisabled ? attr.getValue().toString() : PropertyConverter.escapeDelimiters(attr.getValue().toString(), delimiter);
/* 1518 */             buf.append(value);
/*      */           } 
/* 1520 */           attr.setReference(elem);
/*      */         } 
/* 1523 */         if (!hasAttribute) {
/* 1525 */           elem.removeAttribute(name);
/*      */         } else {
/* 1529 */           elem.setAttribute(name, buf.toString());
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     static void updateAttribute(HierarchicalConfiguration.Node node, String name, char listDelimiter, boolean isAttributeSplittingDisabled) {
/* 1547 */       if (node != null)
/* 1549 */         updateAttribute(node, (Element)node.getReference(), name, listDelimiter, isAttributeSplittingDisabled); 
/*      */     }
/*      */     
/*      */     private Element getElement(HierarchicalConfiguration.Node node) {
/* 1563 */       return (node.getName() != null && node.getReference() != null) ? (Element)node.getReference() : this.document.getDocumentElement();
/*      */     }
/*      */   }
/*      */   
/*      */   private class XMLFileConfigurationDelegate extends AbstractHierarchicalFileConfiguration.FileConfigurationDelegate {
/*      */     private final XMLConfiguration this$0;
/*      */     
/*      */     private XMLFileConfigurationDelegate() {}
/*      */     
/*      */     public void load(InputStream in) throws ConfigurationException {
/* 1578 */       XMLConfiguration.this.load(in);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\XMLConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */