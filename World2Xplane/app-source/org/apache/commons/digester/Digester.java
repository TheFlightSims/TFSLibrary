/*      */ package org.apache.commons.digester;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.util.ArrayList;
/*      */ import java.util.EmptyStackException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.parsers.SAXParser;
/*      */ import javax.xml.parsers.SAXParserFactory;
/*      */ import org.apache.commons.collections.ArrayStack;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXNotRecognizedException;
/*      */ import org.xml.sax.SAXNotSupportedException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.XMLReader;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ 
/*      */ public class Digester extends DefaultHandler {
/*      */   public Digester() {}
/*      */   
/*      */   public Digester(SAXParser parser) {
/*  120 */     this.parser = parser;
/*      */   }
/*      */   
/*      */   public Digester(XMLReader reader) {
/*  136 */     this.reader = reader;
/*      */   }
/*      */   
/*  147 */   protected StringBuffer bodyText = new StringBuffer();
/*      */   
/*  153 */   protected ArrayStack bodyTexts = new ArrayStack();
/*      */   
/*  166 */   protected ArrayStack matches = new ArrayStack(10);
/*      */   
/*  174 */   protected ClassLoader classLoader = null;
/*      */   
/*      */   protected boolean configured = false;
/*      */   
/*      */   protected EntityResolver entityResolver;
/*      */   
/*  192 */   protected HashMap entityValidator = new HashMap();
/*      */   
/*  199 */   protected ErrorHandler errorHandler = null;
/*      */   
/*  205 */   protected SAXParserFactory factory = null;
/*      */   
/*  210 */   protected String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
/*      */   
/*  217 */   protected Locator locator = null;
/*      */   
/*  223 */   protected String match = "";
/*      */   
/*      */   protected boolean namespaceAware = false;
/*      */   
/*  240 */   protected HashMap namespaces = new HashMap();
/*      */   
/*  247 */   protected ArrayStack params = new ArrayStack();
/*      */   
/*  253 */   protected SAXParser parser = null;
/*      */   
/*  260 */   protected String publicId = null;
/*      */   
/*  266 */   protected XMLReader reader = null;
/*      */   
/*  273 */   protected Object root = null;
/*      */   
/*  282 */   protected Rules rules = null;
/*      */   
/*  288 */   protected String schemaLanguage = "http://www.w3.org/2001/XMLSchema";
/*      */   
/*  294 */   protected String schemaLocation = null;
/*      */   
/*  300 */   protected ArrayStack stack = new ArrayStack();
/*      */   
/*      */   protected boolean useContextClassLoader = false;
/*      */   
/*      */   protected boolean validating = false;
/*      */   
/*  319 */   protected Log log = LogFactory.getLog("org.apache.commons.digester.Digester");
/*      */   
/*  326 */   protected Log saxLog = LogFactory.getLog("org.apache.commons.digester.Digester.sax");
/*      */   
/*      */   protected static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
/*      */   
/*      */   protected Substitutor substitutor;
/*      */   
/*  343 */   private HashMap stacksByName = new HashMap();
/*      */   
/*  354 */   private ContentHandler customContentHandler = null;
/*      */   
/*  360 */   private StackAction stackAction = null;
/*      */   
/*      */   public String findNamespaceURI(String prefix) {
/*  373 */     ArrayStack nsStack = (ArrayStack)this.namespaces.get(prefix);
/*  374 */     if (nsStack == null)
/*  375 */       return null; 
/*      */     try {
/*  378 */       return (String)nsStack.peek();
/*  379 */     } catch (EmptyStackException e) {
/*  380 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public ClassLoader getClassLoader() {
/*  398 */     if (this.classLoader != null)
/*  399 */       return this.classLoader; 
/*  401 */     if (this.useContextClassLoader) {
/*  402 */       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
/*  404 */       if (classLoader != null)
/*  405 */         return classLoader; 
/*      */     } 
/*  408 */     return getClass().getClassLoader();
/*      */   }
/*      */   
/*      */   public void setClassLoader(ClassLoader classLoader) {
/*  422 */     this.classLoader = classLoader;
/*      */   }
/*      */   
/*      */   public int getCount() {
/*  432 */     return this.stack.size();
/*      */   }
/*      */   
/*      */   public String getCurrentElementName() {
/*  442 */     String elementName = this.match;
/*  443 */     int lastSlash = elementName.lastIndexOf('/');
/*  444 */     if (lastSlash >= 0)
/*  445 */       elementName = elementName.substring(lastSlash + 1); 
/*  447 */     return elementName;
/*      */   }
/*      */   
/*      */   public int getDebug() {
/*  461 */     return 0;
/*      */   }
/*      */   
/*      */   public void setDebug(int debug) {}
/*      */   
/*      */   public ErrorHandler getErrorHandler() {
/*  488 */     return this.errorHandler;
/*      */   }
/*      */   
/*      */   public void setErrorHandler(ErrorHandler errorHandler) {
/*  500 */     this.errorHandler = errorHandler;
/*      */   }
/*      */   
/*      */   public SAXParserFactory getFactory() {
/*  510 */     if (this.factory == null) {
/*  511 */       this.factory = SAXParserFactory.newInstance();
/*  512 */       this.factory.setNamespaceAware(this.namespaceAware);
/*  513 */       this.factory.setValidating(this.validating);
/*      */     } 
/*  515 */     return this.factory;
/*      */   }
/*      */   
/*      */   public boolean getFeature(String feature) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
/*  539 */     return getFactory().getFeature(feature);
/*      */   }
/*      */   
/*      */   public void setFeature(String feature, boolean value) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
/*  567 */     getFactory().setFeature(feature, value);
/*      */   }
/*      */   
/*      */   public Log getLogger() {
/*  577 */     return this.log;
/*      */   }
/*      */   
/*      */   public void setLogger(Log log) {
/*  587 */     this.log = log;
/*      */   }
/*      */   
/*      */   public Log getSAXLogger() {
/*  599 */     return this.saxLog;
/*      */   }
/*      */   
/*      */   public void setSAXLogger(Log saxLog) {
/*  612 */     this.saxLog = saxLog;
/*      */   }
/*      */   
/*      */   public String getMatch() {
/*  620 */     return this.match;
/*      */   }
/*      */   
/*      */   public boolean getNamespaceAware() {
/*  630 */     return this.namespaceAware;
/*      */   }
/*      */   
/*      */   public void setNamespaceAware(boolean namespaceAware) {
/*  642 */     this.namespaceAware = namespaceAware;
/*      */   }
/*      */   
/*      */   public void setPublicId(String publicId) {
/*  652 */     this.publicId = publicId;
/*      */   }
/*      */   
/*      */   public String getPublicId() {
/*  662 */     return this.publicId;
/*      */   }
/*      */   
/*      */   public String getRuleNamespaceURI() {
/*  673 */     return getRules().getNamespaceURI();
/*      */   }
/*      */   
/*      */   public void setRuleNamespaceURI(String ruleNamespaceURI) {
/*  688 */     getRules().setNamespaceURI(ruleNamespaceURI);
/*      */   }
/*      */   
/*      */   public SAXParser getParser() {
/*  700 */     if (this.parser != null)
/*  701 */       return this.parser; 
/*      */     try {
/*  706 */       if (this.validating && this.schemaLocation != null) {
/*  712 */         Properties properties = new Properties();
/*  713 */         properties.put("SAXParserFactory", getFactory());
/*  714 */         if (this.schemaLocation != null) {
/*  715 */           properties.put("schemaLocation", this.schemaLocation);
/*  716 */           properties.put("schemaLanguage", this.schemaLanguage);
/*      */         } 
/*  718 */         this.parser = ParserFeatureSetterFactory.newSAXParser(properties);
/*      */       } else {
/*  725 */         this.parser = getFactory().newSAXParser();
/*      */       } 
/*  727 */     } catch (Exception e) {
/*  728 */       this.log.error("Digester.getParser: ", e);
/*  729 */       return null;
/*      */     } 
/*  732 */     return this.parser;
/*      */   }
/*      */   
/*      */   public Object getProperty(String property) throws SAXNotRecognizedException, SAXNotSupportedException {
/*  753 */     return getParser().getProperty(property);
/*      */   }
/*      */   
/*      */   public void setProperty(String property, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
/*  775 */     getParser().setProperty(property, value);
/*      */   }
/*      */   
/*      */   public XMLReader getReader() {
/*      */     try {
/*  790 */       return getXMLReader();
/*  791 */     } catch (SAXException e) {
/*  792 */       this.log.error("Cannot get XMLReader", e);
/*  793 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Rules getRules() {
/*  806 */     if (this.rules == null) {
/*  807 */       this.rules = new RulesBase();
/*  808 */       this.rules.setDigester(this);
/*      */     } 
/*  810 */     return this.rules;
/*      */   }
/*      */   
/*      */   public void setRules(Rules rules) {
/*  823 */     this.rules = rules;
/*  824 */     this.rules.setDigester(this);
/*      */   }
/*      */   
/*      */   public String getSchema() {
/*  834 */     return this.schemaLocation;
/*      */   }
/*      */   
/*      */   public void setSchema(String schemaLocation) {
/*  870 */     this.schemaLocation = schemaLocation;
/*      */   }
/*      */   
/*      */   public String getSchemaLanguage() {
/*  880 */     return this.schemaLanguage;
/*      */   }
/*      */   
/*      */   public void setSchemaLanguage(String schemaLanguage) {
/*  892 */     this.schemaLanguage = schemaLanguage;
/*      */   }
/*      */   
/*      */   public boolean getUseContextClassLoader() {
/*  902 */     return this.useContextClassLoader;
/*      */   }
/*      */   
/*      */   public void setUseContextClassLoader(boolean use) {
/*  918 */     this.useContextClassLoader = use;
/*      */   }
/*      */   
/*      */   public boolean getValidating() {
/*  928 */     return this.validating;
/*      */   }
/*      */   
/*      */   public void setValidating(boolean validating) {
/*  941 */     this.validating = validating;
/*      */   }
/*      */   
/*      */   public XMLReader getXMLReader() throws SAXException {
/*  954 */     if (this.reader == null)
/*  955 */       this.reader = getParser().getXMLReader(); 
/*  958 */     this.reader.setDTDHandler(this);
/*  959 */     this.reader.setContentHandler(this);
/*  961 */     if (this.entityResolver == null) {
/*  962 */       this.reader.setEntityResolver(this);
/*      */     } else {
/*  964 */       this.reader.setEntityResolver(this.entityResolver);
/*      */     } 
/*  967 */     this.reader.setErrorHandler(this);
/*  968 */     return this.reader;
/*      */   }
/*      */   
/*      */   public Substitutor getSubstitutor() {
/*  976 */     return this.substitutor;
/*      */   }
/*      */   
/*      */   public void setSubstitutor(Substitutor substitutor) {
/*  985 */     this.substitutor = substitutor;
/*      */   }
/*      */   
/*      */   public ContentHandler getCustomContentHandler() {
/*  994 */     return this.customContentHandler;
/*      */   }
/*      */   
/*      */   public void setCustomContentHandler(ContentHandler handler) {
/* 1032 */     this.customContentHandler = handler;
/*      */   }
/*      */   
/*      */   public void setStackAction(StackAction stackAction) {
/* 1042 */     this.stackAction = stackAction;
/*      */   }
/*      */   
/*      */   public StackAction getStackAction() {
/* 1051 */     return this.stackAction;
/*      */   }
/*      */   
/*      */   public Map getCurrentNamespaces() {
/* 1063 */     if (!this.namespaceAware)
/* 1064 */       this.log.warn("Digester is not namespace aware"); 
/* 1066 */     Map currentNamespaces = new HashMap();
/* 1067 */     Iterator nsIterator = this.namespaces.entrySet().iterator();
/* 1068 */     while (nsIterator.hasNext()) {
/* 1069 */       Map.Entry nsEntry = nsIterator.next();
/*      */       try {
/* 1071 */         currentNamespaces.put(nsEntry.getKey(), ((ArrayStack)nsEntry.getValue()).peek());
/* 1073 */       } catch (RuntimeException e) {
/* 1075 */         this.log.error(e.getMessage(), e);
/* 1076 */         throw e;
/*      */       } 
/*      */     } 
/* 1079 */     return currentNamespaces;
/*      */   }
/*      */   
/*      */   public void characters(char[] buffer, int start, int length) throws SAXException {
/* 1098 */     if (this.customContentHandler != null) {
/* 1100 */       this.customContentHandler.characters(buffer, start, length);
/*      */       return;
/*      */     } 
/* 1104 */     if (this.saxLog.isDebugEnabled())
/* 1105 */       this.saxLog.debug("characters(" + new String(buffer, start, length) + ")"); 
/* 1108 */     this.bodyText.append(buffer, start, length);
/*      */   }
/*      */   
/*      */   public void endDocument() throws SAXException {
/* 1120 */     if (this.saxLog.isDebugEnabled())
/* 1121 */       if (getCount() > 1) {
/* 1122 */         this.saxLog.debug("endDocument():  " + getCount() + " elements left");
/*      */       } else {
/* 1125 */         this.saxLog.debug("endDocument()");
/*      */       }  
/* 1130 */     Iterator rules = getRules().rules().iterator();
/* 1131 */     while (rules.hasNext()) {
/* 1132 */       Rule rule = rules.next();
/*      */       try {
/* 1134 */         rule.finish();
/* 1135 */       } catch (Exception e) {
/* 1136 */         this.log.error("Finish event threw exception", e);
/* 1137 */         throw createSAXException(e);
/* 1138 */       } catch (Error e) {
/* 1139 */         this.log.error("Finish event threw error", e);
/* 1140 */         throw e;
/*      */       } 
/*      */     } 
/* 1145 */     clear();
/*      */   }
/*      */   
/*      */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 1165 */     if (this.customContentHandler != null) {
/* 1167 */       this.customContentHandler.endElement(namespaceURI, localName, qName);
/*      */       return;
/*      */     } 
/* 1171 */     boolean debug = this.log.isDebugEnabled();
/* 1173 */     if (debug) {
/* 1174 */       if (this.saxLog.isDebugEnabled())
/* 1175 */         this.saxLog.debug("endElement(" + namespaceURI + "," + localName + "," + qName + ")"); 
/* 1178 */       this.log.debug("  match='" + this.match + "'");
/* 1179 */       this.log.debug("  bodyText='" + this.bodyText + "'");
/*      */     } 
/* 1184 */     String name = localName;
/* 1185 */     if (name == null || name.length() < 1)
/* 1186 */       name = qName; 
/* 1190 */     List rules = (List)this.matches.pop();
/* 1191 */     if (rules != null && rules.size() > 0) {
/* 1192 */       String bodyText = this.bodyText.toString();
/* 1193 */       Substitutor substitutor = getSubstitutor();
/* 1194 */       if (substitutor != null)
/* 1195 */         bodyText = substitutor.substitute(bodyText); 
/* 1197 */       for (int i = 0; i < rules.size(); i++) {
/*      */         try {
/* 1199 */           Rule rule = rules.get(i);
/* 1200 */           if (debug)
/* 1201 */             this.log.debug("  Fire body() for " + rule); 
/* 1203 */           rule.body(namespaceURI, name, bodyText);
/* 1204 */         } catch (Exception e) {
/* 1205 */           this.log.error("Body event threw exception", e);
/* 1206 */           throw createSAXException(e);
/* 1207 */         } catch (Error e) {
/* 1208 */           this.log.error("Body event threw error", e);
/* 1209 */           throw e;
/*      */         } 
/*      */       } 
/* 1213 */     } else if (debug) {
/* 1214 */       this.log.debug("  No rules found matching '" + this.match + "'.");
/*      */     } 
/* 1219 */     this.bodyText = (StringBuffer)this.bodyTexts.pop();
/* 1220 */     if (debug)
/* 1221 */       this.log.debug("  Popping body text '" + this.bodyText.toString() + "'"); 
/* 1225 */     if (rules != null)
/* 1226 */       for (int i = 0; i < rules.size(); i++) {
/* 1227 */         int j = rules.size() - i - 1;
/*      */         try {
/* 1229 */           Rule rule = rules.get(j);
/* 1230 */           if (debug)
/* 1231 */             this.log.debug("  Fire end() for " + rule); 
/* 1233 */           rule.end(namespaceURI, name);
/* 1234 */         } catch (Exception e) {
/* 1235 */           this.log.error("End event threw exception", e);
/* 1236 */           throw createSAXException(e);
/* 1237 */         } catch (Error e) {
/* 1238 */           this.log.error("End event threw error", e);
/* 1239 */           throw e;
/*      */         } 
/*      */       }  
/* 1245 */     int slash = this.match.lastIndexOf('/');
/* 1246 */     if (slash >= 0) {
/* 1247 */       this.match = this.match.substring(0, slash);
/*      */     } else {
/* 1249 */       this.match = "";
/*      */     } 
/*      */   }
/*      */   
/*      */   public void endPrefixMapping(String prefix) throws SAXException {
/* 1264 */     if (this.saxLog.isDebugEnabled())
/* 1265 */       this.saxLog.debug("endPrefixMapping(" + prefix + ")"); 
/* 1269 */     ArrayStack stack = (ArrayStack)this.namespaces.get(prefix);
/* 1270 */     if (stack == null)
/*      */       return; 
/*      */     try {
/* 1274 */       stack.pop();
/* 1275 */       if (stack.empty())
/* 1276 */         this.namespaces.remove(prefix); 
/* 1277 */     } catch (EmptyStackException e) {
/* 1278 */       throw createSAXException("endPrefixMapping popped too many times");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void ignorableWhitespace(char[] buffer, int start, int len) throws SAXException {
/* 1297 */     if (this.saxLog.isDebugEnabled())
/* 1298 */       this.saxLog.debug("ignorableWhitespace(" + new String(buffer, start, len) + ")"); 
/*      */   }
/*      */   
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/* 1318 */     if (this.customContentHandler != null) {
/* 1320 */       this.customContentHandler.processingInstruction(target, data);
/*      */       return;
/*      */     } 
/* 1324 */     if (this.saxLog.isDebugEnabled())
/* 1325 */       this.saxLog.debug("processingInstruction('" + target + "','" + data + "')"); 
/*      */   }
/*      */   
/*      */   public Locator getDocumentLocator() {
/* 1340 */     return this.locator;
/*      */   }
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {
/* 1351 */     if (this.saxLog.isDebugEnabled())
/* 1352 */       this.saxLog.debug("setDocumentLocator(" + locator + ")"); 
/* 1355 */     this.locator = locator;
/*      */   }
/*      */   
/*      */   public void skippedEntity(String name) throws SAXException {
/* 1369 */     if (this.saxLog.isDebugEnabled())
/* 1370 */       this.saxLog.debug("skippedEntity(" + name + ")"); 
/*      */   }
/*      */   
/*      */   public void startDocument() throws SAXException {
/* 1385 */     if (this.saxLog.isDebugEnabled())
/* 1386 */       this.saxLog.debug("startDocument()"); 
/* 1392 */     configure();
/*      */   }
/*      */   
/*      */   public void startElement(String namespaceURI, String localName, String qName, Attributes list) throws SAXException {
/* 1412 */     boolean debug = this.log.isDebugEnabled();
/* 1414 */     if (this.customContentHandler != null) {
/* 1416 */       this.customContentHandler.startElement(namespaceURI, localName, qName, list);
/*      */       return;
/*      */     } 
/* 1420 */     if (this.saxLog.isDebugEnabled())
/* 1421 */       this.saxLog.debug("startElement(" + namespaceURI + "," + localName + "," + qName + ")"); 
/* 1426 */     this.bodyTexts.push(this.bodyText);
/* 1427 */     if (debug)
/* 1428 */       this.log.debug("  Pushing body text '" + this.bodyText.toString() + "'"); 
/* 1430 */     this.bodyText = new StringBuffer();
/* 1434 */     String name = localName;
/* 1435 */     if (name == null || name.length() < 1)
/* 1436 */       name = qName; 
/* 1440 */     StringBuffer sb = new StringBuffer(this.match);
/* 1441 */     if (this.match.length() > 0)
/* 1442 */       sb.append('/'); 
/* 1444 */     sb.append(name);
/* 1445 */     this.match = sb.toString();
/* 1446 */     if (debug)
/* 1447 */       this.log.debug("  New match='" + this.match + "'"); 
/* 1451 */     List rules = getRules().match(namespaceURI, this.match);
/* 1452 */     this.matches.push(rules);
/* 1453 */     if (rules != null && rules.size() > 0) {
/* 1454 */       Substitutor substitutor = getSubstitutor();
/* 1455 */       if (substitutor != null)
/* 1456 */         list = substitutor.substitute(list); 
/* 1458 */       for (int i = 0; i < rules.size(); i++) {
/*      */         try {
/* 1460 */           Rule rule = rules.get(i);
/* 1461 */           if (debug)
/* 1462 */             this.log.debug("  Fire begin() for " + rule); 
/* 1464 */           rule.begin(namespaceURI, name, list);
/* 1465 */         } catch (Exception e) {
/* 1466 */           this.log.error("Begin event threw exception", e);
/* 1467 */           throw createSAXException(e);
/* 1468 */         } catch (Error e) {
/* 1469 */           this.log.error("Begin event threw error", e);
/* 1470 */           throw e;
/*      */         } 
/*      */       } 
/* 1474 */     } else if (debug) {
/* 1475 */       this.log.debug("  No rules found matching '" + this.match + "'.");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void startPrefixMapping(String prefix, String namespaceURI) throws SAXException {
/* 1493 */     if (this.saxLog.isDebugEnabled())
/* 1494 */       this.saxLog.debug("startPrefixMapping(" + prefix + "," + namespaceURI + ")"); 
/* 1498 */     ArrayStack stack = (ArrayStack)this.namespaces.get(prefix);
/* 1499 */     if (stack == null) {
/* 1500 */       stack = new ArrayStack();
/* 1501 */       this.namespaces.put(prefix, stack);
/*      */     } 
/* 1503 */     stack.push(namespaceURI);
/*      */   }
/*      */   
/*      */   public void notationDecl(String name, String publicId, String systemId) {
/* 1520 */     if (this.saxLog.isDebugEnabled())
/* 1521 */       this.saxLog.debug("notationDecl(" + name + "," + publicId + "," + systemId + ")"); 
/*      */   }
/*      */   
/*      */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notation) {
/* 1539 */     if (this.saxLog.isDebugEnabled())
/* 1540 */       this.saxLog.debug("unparsedEntityDecl(" + name + "," + publicId + "," + systemId + "," + notation + ")"); 
/*      */   }
/*      */   
/*      */   public void setEntityResolver(EntityResolver entityResolver) {
/* 1556 */     this.entityResolver = entityResolver;
/*      */   }
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/* 1565 */     return this.entityResolver;
/*      */   }
/*      */   
/*      */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/* 1580 */     if (this.saxLog.isDebugEnabled())
/* 1581 */       this.saxLog.debug("resolveEntity('" + publicId + "', '" + systemId + "')"); 
/* 1584 */     if (publicId != null)
/* 1585 */       this.publicId = publicId; 
/* 1588 */     URL entityURL = null;
/* 1589 */     if (publicId != null)
/* 1590 */       entityURL = (URL)this.entityValidator.get(publicId); 
/* 1594 */     if (this.schemaLocation != null && entityURL == null && systemId != null)
/* 1595 */       entityURL = (URL)this.entityValidator.get(systemId); 
/* 1598 */     if (entityURL == null) {
/* 1599 */       if (systemId == null) {
/* 1601 */         if (this.log.isDebugEnabled())
/* 1602 */           this.log.debug(" Cannot resolve null entity, returning null InputSource"); 
/* 1604 */         return null;
/*      */       } 
/* 1608 */       if (this.log.isDebugEnabled())
/* 1609 */         this.log.debug(" Trying to resolve using system ID '" + systemId + "'"); 
/*      */       try {
/* 1612 */         entityURL = new URL(systemId);
/* 1613 */       } catch (MalformedURLException e) {
/* 1614 */         throw new IllegalArgumentException("Malformed URL '" + systemId + "' : " + e.getMessage());
/*      */       } 
/*      */     } 
/* 1621 */     if (this.log.isDebugEnabled())
/* 1622 */       this.log.debug(" Resolving to alternate DTD '" + entityURL + "'"); 
/*      */     try {
/* 1626 */       return createInputSourceFromURL(entityURL);
/* 1627 */     } catch (Exception e) {
/* 1628 */       throw createSAXException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void error(SAXParseException exception) throws SAXException {
/* 1646 */     this.log.error("Parse Error at line " + exception.getLineNumber() + " column " + exception.getColumnNumber() + ": " + exception.getMessage(), exception);
/* 1649 */     if (this.errorHandler != null)
/* 1650 */       this.errorHandler.error(exception); 
/*      */   }
/*      */   
/*      */   public void fatalError(SAXParseException exception) throws SAXException {
/* 1666 */     this.log.error("Parse Fatal Error at line " + exception.getLineNumber() + " column " + exception.getColumnNumber() + ": " + exception.getMessage(), exception);
/* 1669 */     if (this.errorHandler != null)
/* 1670 */       this.errorHandler.fatalError(exception); 
/*      */   }
/*      */   
/*      */   public void warning(SAXParseException exception) throws SAXException {
/* 1685 */     if (this.errorHandler != null) {
/* 1686 */       this.log.warn("Parse Warning Error at line " + exception.getLineNumber() + " column " + exception.getColumnNumber() + ": " + exception.getMessage(), exception);
/* 1690 */       this.errorHandler.warning(exception);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void log(String message) {
/* 1707 */     this.log.info(message);
/*      */   }
/*      */   
/*      */   public void log(String message, Throwable exception) {
/* 1720 */     this.log.error(message, exception);
/*      */   }
/*      */   
/*      */   public Object parse(File file) throws IOException, SAXException {
/* 1736 */     if (file == null)
/* 1737 */       throw new IllegalArgumentException("File to parse is null"); 
/* 1740 */     configure();
/* 1741 */     InputSource input = new InputSource(new FileInputStream(file));
/* 1742 */     input.setSystemId(file.toURL().toString());
/* 1743 */     getXMLReader().parse(input);
/* 1744 */     cleanup();
/* 1745 */     return this.root;
/*      */   }
/*      */   
/*      */   public Object parse(InputSource input) throws IOException, SAXException {
/* 1759 */     if (input == null)
/* 1760 */       throw new IllegalArgumentException("InputSource to parse is null"); 
/* 1763 */     configure();
/* 1764 */     getXMLReader().parse(input);
/* 1765 */     cleanup();
/* 1766 */     return this.root;
/*      */   }
/*      */   
/*      */   public Object parse(InputStream input) throws IOException, SAXException {
/* 1782 */     if (input == null)
/* 1783 */       throw new IllegalArgumentException("InputStream to parse is null"); 
/* 1786 */     configure();
/* 1787 */     InputSource is = new InputSource(input);
/* 1788 */     getXMLReader().parse(is);
/* 1789 */     cleanup();
/* 1790 */     return this.root;
/*      */   }
/*      */   
/*      */   public Object parse(Reader reader) throws IOException, SAXException {
/* 1806 */     if (reader == null)
/* 1807 */       throw new IllegalArgumentException("Reader to parse is null"); 
/* 1810 */     configure();
/* 1811 */     InputSource is = new InputSource(reader);
/* 1812 */     getXMLReader().parse(is);
/* 1813 */     cleanup();
/* 1814 */     return this.root;
/*      */   }
/*      */   
/*      */   public Object parse(String uri) throws IOException, SAXException {
/* 1830 */     if (uri == null)
/* 1831 */       throw new IllegalArgumentException("String URI to parse is null"); 
/* 1834 */     configure();
/* 1835 */     InputSource is = createInputSourceFromURL(uri);
/* 1836 */     getXMLReader().parse(is);
/* 1837 */     cleanup();
/* 1838 */     return this.root;
/*      */   }
/*      */   
/*      */   public Object parse(URL url) throws IOException, SAXException {
/* 1856 */     if (url == null)
/* 1857 */       throw new IllegalArgumentException("URL to parse is null"); 
/* 1860 */     configure();
/* 1861 */     InputSource is = createInputSourceFromURL(url);
/* 1862 */     getXMLReader().parse(is);
/* 1863 */     cleanup();
/* 1864 */     return this.root;
/*      */   }
/*      */   
/*      */   public void register(String publicId, URL entityURL) {
/* 1894 */     if (this.log.isDebugEnabled())
/* 1895 */       this.log.debug("register('" + publicId + "', '" + entityURL + "'"); 
/* 1897 */     this.entityValidator.put(publicId, entityURL);
/*      */   }
/*      */   
/*      */   public void register(String publicId, String entityURL) {
/* 1911 */     if (this.log.isDebugEnabled())
/* 1912 */       this.log.debug("register('" + publicId + "', '" + entityURL + "'"); 
/*      */     try {
/* 1915 */       this.entityValidator.put(publicId, new URL(entityURL));
/* 1916 */     } catch (MalformedURLException e) {
/* 1917 */       throw new IllegalArgumentException("Malformed URL '" + entityURL + "' : " + e.getMessage());
/*      */     } 
/*      */   }
/*      */   
/* 1931 */   protected List inputSources = new ArrayList(5);
/*      */   
/*      */   public InputSource createInputSourceFromURL(URL url) throws MalformedURLException, IOException {
/* 1971 */     URLConnection connection = url.openConnection();
/* 1972 */     connection.setUseCaches(false);
/* 1973 */     InputStream stream = connection.getInputStream();
/* 1974 */     InputSource source = new InputSource(stream);
/* 1975 */     source.setSystemId(url.toExternalForm());
/* 1976 */     this.inputSources.add(source);
/* 1977 */     return source;
/*      */   }
/*      */   
/*      */   public InputSource createInputSourceFromURL(String url) throws MalformedURLException, IOException {
/* 1993 */     return createInputSourceFromURL(new URL(url));
/*      */   }
/*      */   
/*      */   public void addRule(String pattern, Rule rule) {
/* 2010 */     rule.setDigester(this);
/* 2011 */     getRules().add(pattern, rule);
/*      */   }
/*      */   
/*      */   public void addRuleSet(RuleSet ruleSet) {
/* 2023 */     String oldNamespaceURI = getRuleNamespaceURI();
/* 2024 */     String newNamespaceURI = ruleSet.getNamespaceURI();
/* 2025 */     if (this.log.isDebugEnabled())
/* 2026 */       if (newNamespaceURI == null) {
/* 2027 */         this.log.debug("addRuleSet() with no namespace URI");
/*      */       } else {
/* 2029 */         this.log.debug("addRuleSet() with namespace URI " + newNamespaceURI);
/*      */       }  
/* 2032 */     setRuleNamespaceURI(newNamespaceURI);
/* 2033 */     ruleSet.addRuleInstances(this);
/* 2034 */     setRuleNamespaceURI(oldNamespaceURI);
/*      */   }
/*      */   
/*      */   public void addBeanPropertySetter(String pattern) {
/* 2047 */     addRule(pattern, new BeanPropertySetterRule());
/*      */   }
/*      */   
/*      */   public void addBeanPropertySetter(String pattern, String propertyName) {
/* 2063 */     addRule(pattern, new BeanPropertySetterRule(propertyName));
/*      */   }
/*      */   
/*      */   public void addCallMethod(String pattern, String methodName) {
/* 2077 */     addRule(pattern, new CallMethodRule(methodName));
/*      */   }
/*      */   
/*      */   public void addCallMethod(String pattern, String methodName, int paramCount) {
/* 2095 */     addRule(pattern, new CallMethodRule(methodName, paramCount));
/*      */   }
/*      */   
/*      */   public void addCallMethod(String pattern, String methodName, int paramCount, String[] paramTypes) {
/* 2122 */     addRule(pattern, new CallMethodRule(methodName, paramCount, paramTypes));
/*      */   }
/*      */   
/*      */   public void addCallMethod(String pattern, String methodName, int paramCount, Class[] paramTypes) {
/* 2151 */     addRule(pattern, new CallMethodRule(methodName, paramCount, paramTypes));
/*      */   }
/*      */   
/*      */   public void addCallParam(String pattern, int paramIndex) {
/* 2170 */     addRule(pattern, new CallParamRule(paramIndex));
/*      */   }
/*      */   
/*      */   public void addCallParam(String pattern, int paramIndex, String attributeName) {
/* 2189 */     addRule(pattern, new CallParamRule(paramIndex, attributeName));
/*      */   }
/*      */   
/*      */   public void addCallParam(String pattern, int paramIndex, boolean fromStack) {
/* 2206 */     addRule(pattern, new CallParamRule(paramIndex, fromStack));
/*      */   }
/*      */   
/*      */   public void addCallParam(String pattern, int paramIndex, int stackIndex) {
/* 2222 */     addRule(pattern, new CallParamRule(paramIndex, stackIndex));
/*      */   }
/*      */   
/*      */   public void addCallParamPath(String pattern, int paramIndex) {
/* 2237 */     addRule(pattern, new PathCallParamRule(paramIndex));
/*      */   }
/*      */   
/*      */   public void addObjectParam(String pattern, int paramIndex, Object paramObj) {
/* 2263 */     addRule(pattern, new ObjectParamRule(paramIndex, paramObj));
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, String className) {
/* 2278 */     addFactoryCreate(pattern, className, false);
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, Class clazz) {
/* 2293 */     addFactoryCreate(pattern, clazz, false);
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, String className, String attributeName) {
/* 2311 */     addFactoryCreate(pattern, className, attributeName, false);
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, Class clazz, String attributeName) {
/* 2329 */     addFactoryCreate(pattern, clazz, attributeName, false);
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, ObjectCreationFactory creationFactory) {
/* 2346 */     addFactoryCreate(pattern, creationFactory, false);
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, String className, boolean ignoreCreateExceptions) {
/* 2364 */     addRule(pattern, new FactoryCreateRule(className, ignoreCreateExceptions));
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, Class clazz, boolean ignoreCreateExceptions) {
/* 2385 */     addRule(pattern, new FactoryCreateRule(clazz, ignoreCreateExceptions));
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, String className, String attributeName, boolean ignoreCreateExceptions) {
/* 2409 */     addRule(pattern, new FactoryCreateRule(className, attributeName, ignoreCreateExceptions));
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, Class clazz, String attributeName, boolean ignoreCreateExceptions) {
/* 2433 */     addRule(pattern, new FactoryCreateRule(clazz, attributeName, ignoreCreateExceptions));
/*      */   }
/*      */   
/*      */   public void addFactoryCreate(String pattern, ObjectCreationFactory creationFactory, boolean ignoreCreateExceptions) {
/* 2454 */     creationFactory.setDigester(this);
/* 2455 */     addRule(pattern, new FactoryCreateRule(creationFactory, ignoreCreateExceptions));
/*      */   }
/*      */   
/*      */   public void addObjectCreate(String pattern, String className) {
/* 2469 */     addRule(pattern, new ObjectCreateRule(className));
/*      */   }
/*      */   
/*      */   public void addObjectCreate(String pattern, Class clazz) {
/* 2484 */     addRule(pattern, new ObjectCreateRule(clazz));
/*      */   }
/*      */   
/*      */   public void addObjectCreate(String pattern, String className, String attributeName) {
/* 2502 */     addRule(pattern, new ObjectCreateRule(className, attributeName));
/*      */   }
/*      */   
/*      */   public void addObjectCreate(String pattern, String attributeName, Class clazz) {
/* 2521 */     addRule(pattern, new ObjectCreateRule(attributeName, clazz));
/*      */   }
/*      */   
/*      */   public void addSetNestedProperties(String pattern) {
/* 2535 */     addRule(pattern, new SetNestedPropertiesRule());
/*      */   }
/*      */   
/*      */   public void addSetNestedProperties(String pattern, String elementName, String propertyName) {
/* 2549 */     addRule(pattern, new SetNestedPropertiesRule(elementName, propertyName));
/*      */   }
/*      */   
/*      */   public void addSetNestedProperties(String pattern, String[] elementNames, String[] propertyNames) {
/* 2563 */     addRule(pattern, new SetNestedPropertiesRule(elementNames, propertyNames));
/*      */   }
/*      */   
/*      */   public void addSetNext(String pattern, String methodName) {
/* 2576 */     addRule(pattern, new SetNextRule(methodName));
/*      */   }
/*      */   
/*      */   public void addSetNext(String pattern, String methodName, String paramType) {
/* 2596 */     addRule(pattern, new SetNextRule(methodName, paramType));
/*      */   }
/*      */   
/*      */   public void addSetRoot(String pattern, String methodName) {
/* 2611 */     addRule(pattern, new SetRootRule(methodName));
/*      */   }
/*      */   
/*      */   public void addSetRoot(String pattern, String methodName, String paramType) {
/* 2628 */     addRule(pattern, new SetRootRule(methodName, paramType));
/*      */   }
/*      */   
/*      */   public void addSetProperties(String pattern) {
/* 2641 */     addRule(pattern, new SetPropertiesRule());
/*      */   }
/*      */   
/*      */   public void addSetProperties(String pattern, String attributeName, String propertyName) {
/* 2660 */     addRule(pattern, new SetPropertiesRule(attributeName, propertyName));
/*      */   }
/*      */   
/*      */   public void addSetProperties(String pattern, String[] attributeNames, String[] propertyNames) {
/* 2679 */     addRule(pattern, new SetPropertiesRule(attributeNames, propertyNames));
/*      */   }
/*      */   
/*      */   public void addSetProperty(String pattern, String name, String value) {
/* 2695 */     addRule(pattern, new SetPropertyRule(name, value));
/*      */   }
/*      */   
/*      */   public void addSetTop(String pattern, String methodName) {
/* 2710 */     addRule(pattern, new SetTopRule(methodName));
/*      */   }
/*      */   
/*      */   public void addSetTop(String pattern, String methodName, String paramType) {
/* 2730 */     addRule(pattern, new SetTopRule(methodName, paramType));
/*      */   }
/*      */   
/*      */   public void clear() {
/* 2756 */     this.match = "";
/* 2757 */     this.bodyTexts.clear();
/* 2758 */     this.params.clear();
/* 2759 */     this.publicId = null;
/* 2760 */     this.stack.clear();
/* 2761 */     this.stacksByName.clear();
/* 2762 */     this.customContentHandler = null;
/*      */   }
/*      */   
/*      */   public Object peek() {
/*      */     try {
/* 2773 */       return this.stack.peek();
/* 2774 */     } catch (EmptyStackException e) {
/* 2775 */       this.log.warn("Empty stack (returning null)");
/* 2776 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object peek(int n) {
/*      */     try {
/* 2793 */       return this.stack.peek(n);
/* 2794 */     } catch (EmptyStackException e) {
/* 2795 */       this.log.warn("Empty stack (returning null)");
/* 2796 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object pop() {
/*      */     try {
/* 2809 */       Object popped = this.stack.pop();
/* 2810 */       if (this.stackAction != null)
/* 2811 */         popped = this.stackAction.onPop(this, null, popped); 
/* 2813 */       return popped;
/* 2814 */     } catch (EmptyStackException e) {
/* 2815 */       this.log.warn("Empty stack (returning null)");
/* 2816 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void push(Object object) {
/* 2829 */     if (this.stackAction != null)
/* 2830 */       object = this.stackAction.onPush(this, null, object); 
/* 2833 */     if (this.stack.size() == 0)
/* 2834 */       this.root = object; 
/* 2836 */     this.stack.push(object);
/*      */   }
/*      */   
/*      */   public void push(String stackName, Object value) {
/* 2849 */     if (this.stackAction != null)
/* 2850 */       value = this.stackAction.onPush(this, stackName, value); 
/* 2853 */     ArrayStack namedStack = (ArrayStack)this.stacksByName.get(stackName);
/* 2854 */     if (namedStack == null) {
/* 2855 */       namedStack = new ArrayStack();
/* 2856 */       this.stacksByName.put(stackName, namedStack);
/*      */     } 
/* 2858 */     namedStack.push(value);
/*      */   }
/*      */   
/*      */   public Object pop(String stackName) {
/* 2875 */     Object result = null;
/* 2876 */     ArrayStack namedStack = (ArrayStack)this.stacksByName.get(stackName);
/* 2877 */     if (namedStack == null) {
/* 2878 */       if (this.log.isDebugEnabled())
/* 2879 */         this.log.debug("Stack '" + stackName + "' is empty"); 
/* 2881 */       throw new EmptyStackException();
/*      */     } 
/* 2884 */     result = namedStack.pop();
/* 2886 */     if (this.stackAction != null)
/* 2887 */       result = this.stackAction.onPop(this, stackName, result); 
/* 2890 */     return result;
/*      */   }
/*      */   
/*      */   public Object peek(String stackName) {
/* 2908 */     return peek(stackName, 0);
/*      */   }
/*      */   
/*      */   public Object peek(String stackName, int n) {
/* 2927 */     Object result = null;
/* 2928 */     ArrayStack namedStack = (ArrayStack)this.stacksByName.get(stackName);
/* 2929 */     if (namedStack == null) {
/* 2930 */       if (this.log.isDebugEnabled())
/* 2931 */         this.log.debug("Stack '" + stackName + "' is empty"); 
/* 2933 */       throw new EmptyStackException();
/*      */     } 
/* 2937 */     result = namedStack.peek(n);
/* 2939 */     return result;
/*      */   }
/*      */   
/*      */   public boolean isEmpty(String stackName) {
/* 2953 */     boolean result = true;
/* 2954 */     ArrayStack namedStack = (ArrayStack)this.stacksByName.get(stackName);
/* 2955 */     if (namedStack != null)
/* 2956 */       result = namedStack.isEmpty(); 
/* 2958 */     return result;
/*      */   }
/*      */   
/*      */   public Object getRoot() {
/* 2988 */     return this.root;
/*      */   }
/*      */   
/*      */   public void resetRoot() {
/* 3002 */     this.root = null;
/*      */   }
/*      */   
/*      */   protected void cleanup() {
/* 3023 */     Iterator sources = this.inputSources.iterator();
/* 3024 */     while (sources.hasNext()) {
/* 3025 */       InputSource source = sources.next();
/*      */       try {
/* 3027 */         source.getByteStream().close();
/* 3028 */       } catch (IOException e) {}
/*      */     } 
/* 3032 */     this.inputSources.clear();
/*      */   }
/*      */   
/*      */   protected void configure() {
/* 3054 */     if (this.configured)
/*      */       return; 
/* 3059 */     initialize();
/* 3063 */     this.configured = true;
/*      */   }
/*      */   
/*      */   protected void initialize() {}
/*      */   
/*      */   Map getRegistrations() {
/* 3100 */     return this.entityValidator;
/*      */   }
/*      */   
/*      */   List getRules(String match) {
/* 3120 */     return getRules().match(match);
/*      */   }
/*      */   
/*      */   public Object peekParams() {
/*      */     try {
/* 3135 */       return this.params.peek();
/* 3136 */     } catch (EmptyStackException e) {
/* 3137 */       this.log.warn("Empty stack (returning null)");
/* 3138 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object peekParams(int n) {
/*      */     try {
/* 3158 */       return this.params.peek(n);
/* 3159 */     } catch (EmptyStackException e) {
/* 3160 */       this.log.warn("Empty stack (returning null)");
/* 3161 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object popParams() {
/*      */     try {
/* 3177 */       if (this.log.isTraceEnabled())
/* 3178 */         this.log.trace("Popping params"); 
/* 3180 */       return this.params.pop();
/* 3181 */     } catch (EmptyStackException e) {
/* 3182 */       this.log.warn("Empty stack (returning null)");
/* 3183 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void pushParams(Object object) {
/* 3198 */     if (this.log.isTraceEnabled())
/* 3199 */       this.log.trace("Pushing params"); 
/* 3201 */     this.params.push(object);
/*      */   }
/*      */   
/*      */   public SAXException createSAXException(String message, Exception e) {
/* 3212 */     if (e != null && e instanceof InvocationTargetException) {
/* 3214 */       Throwable t = ((InvocationTargetException)e).getTargetException();
/* 3215 */       if (t != null && t instanceof Exception)
/* 3216 */         e = (Exception)t; 
/*      */     } 
/* 3219 */     if (this.locator != null) {
/* 3220 */       String error = "Error at line " + this.locator.getLineNumber() + " char " + this.locator.getColumnNumber() + ": " + message;
/* 3222 */       if (e != null)
/* 3223 */         return new SAXParseException(error, this.locator, e); 
/* 3225 */       return new SAXParseException(error, this.locator);
/*      */     } 
/* 3228 */     this.log.error("No Locator!");
/* 3229 */     if (e != null)
/* 3230 */       return new SAXException(message, e); 
/* 3232 */     return new SAXException(message);
/*      */   }
/*      */   
/*      */   public SAXException createSAXException(Exception e) {
/* 3243 */     if (e instanceof InvocationTargetException) {
/* 3244 */       Throwable t = ((InvocationTargetException)e).getTargetException();
/* 3245 */       if (t != null && t instanceof Exception)
/* 3246 */         e = (Exception)t; 
/*      */     } 
/* 3249 */     return createSAXException(e.getMessage(), e);
/*      */   }
/*      */   
/*      */   public SAXException createSAXException(String message) {
/* 3259 */     return createSAXException(message, null);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\Digester.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */