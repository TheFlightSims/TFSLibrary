/*      */ package org.jdom.input;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import org.jdom.DefaultJDOMFactory;
/*      */ import org.jdom.Document;
/*      */ import org.jdom.JDOMException;
/*      */ import org.jdom.JDOMFactory;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXNotRecognizedException;
/*      */ import org.xml.sax.SAXNotSupportedException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.XMLFilter;
/*      */ import org.xml.sax.XMLReader;
/*      */ import org.xml.sax.helpers.XMLReaderFactory;
/*      */ 
/*      */ public class SAXBuilder {
/*      */   private static final String CVS_ID = "@(#) $RCSfile: SAXBuilder.java,v $ $Revision: 1.93 $ $Date: 2009/07/23 06:26:26 $ $Name:  $";
/*      */   
/*      */   private static final String DEFAULT_SAX_DRIVER = "org.apache.xerces.parsers.SAXParser";
/*      */   
/*      */   private boolean validate;
/*      */   
/*      */   private boolean expand = true;
/*      */   
/*      */   private String saxDriverClass;
/*      */   
/*  111 */   private ErrorHandler saxErrorHandler = null;
/*      */   
/*  114 */   private EntityResolver saxEntityResolver = null;
/*      */   
/*  117 */   private DTDHandler saxDTDHandler = null;
/*      */   
/*  120 */   private XMLFilter saxXMLFilter = null;
/*      */   
/*  123 */   private JDOMFactory factory = (JDOMFactory)new DefaultJDOMFactory();
/*      */   
/*      */   private boolean ignoringWhite = false;
/*      */   
/*      */   private boolean ignoringBoundaryWhite = false;
/*      */   
/*  132 */   private HashMap features = new HashMap(5);
/*      */   
/*  135 */   private HashMap properties = new HashMap(5);
/*      */   
/*      */   private boolean fastReconfigure = false;
/*      */   
/*      */   private boolean skipNextLexicalReportingConfig = false;
/*      */   
/*      */   private boolean skipNextEntityExpandConfig = false;
/*      */   
/*      */   private boolean reuseParser = true;
/*      */   
/*  153 */   private XMLReader saxParser = null;
/*      */   
/*      */   public SAXBuilder() {
/*  161 */     this(false);
/*      */   }
/*      */   
/*      */   public SAXBuilder(boolean validate) {
/*  174 */     this.validate = validate;
/*      */   }
/*      */   
/*      */   public SAXBuilder(String saxDriverClass) {
/*  185 */     this(saxDriverClass, false);
/*      */   }
/*      */   
/*      */   public SAXBuilder(String saxDriverClass, boolean validate) {
/*  199 */     this.saxDriverClass = saxDriverClass;
/*  200 */     this.validate = validate;
/*      */   }
/*      */   
/*      */   public String getDriverClass() {
/*  209 */     return this.saxDriverClass;
/*      */   }
/*      */   
/*      */   public JDOMFactory getFactory() {
/*  217 */     return this.factory;
/*      */   }
/*      */   
/*      */   public void setFactory(JDOMFactory factory) {
/*  227 */     this.factory = factory;
/*      */   }
/*      */   
/*      */   public boolean getValidation() {
/*  236 */     return this.validate;
/*      */   }
/*      */   
/*      */   public void setValidation(boolean validate) {
/*  246 */     this.validate = validate;
/*      */   }
/*      */   
/*      */   public ErrorHandler getErrorHandler() {
/*  254 */     return this.saxErrorHandler;
/*      */   }
/*      */   
/*      */   public void setErrorHandler(ErrorHandler errorHandler) {
/*  263 */     this.saxErrorHandler = errorHandler;
/*      */   }
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/*  272 */     return this.saxEntityResolver;
/*      */   }
/*      */   
/*      */   public void setEntityResolver(EntityResolver entityResolver) {
/*  281 */     this.saxEntityResolver = entityResolver;
/*      */   }
/*      */   
/*      */   public DTDHandler getDTDHandler() {
/*  290 */     return this.saxDTDHandler;
/*      */   }
/*      */   
/*      */   public void setDTDHandler(DTDHandler dtdHandler) {
/*  299 */     this.saxDTDHandler = dtdHandler;
/*      */   }
/*      */   
/*      */   public XMLFilter getXMLFilter() {
/*  308 */     return this.saxXMLFilter;
/*      */   }
/*      */   
/*      */   public void setXMLFilter(XMLFilter xmlFilter) {
/*  317 */     this.saxXMLFilter = xmlFilter;
/*      */   }
/*      */   
/*      */   public boolean getIgnoringElementContentWhitespace() {
/*  328 */     return this.ignoringWhite;
/*      */   }
/*      */   
/*      */   public void setIgnoringElementContentWhitespace(boolean ignoringWhite) {
/*  343 */     this.ignoringWhite = ignoringWhite;
/*      */   }
/*      */   
/*      */   public boolean getIgnoringBoundaryWhitespace() {
/*  356 */     return this.ignoringBoundaryWhite;
/*      */   }
/*      */   
/*      */   public void setIgnoringBoundaryWhitespace(boolean ignoringBoundaryWhite) {
/*  376 */     this.ignoringBoundaryWhite = ignoringBoundaryWhite;
/*      */   }
/*      */   
/*      */   public boolean getReuseParser() {
/*  387 */     return this.reuseParser;
/*      */   }
/*      */   
/*      */   public void setReuseParser(boolean reuseParser) {
/*  403 */     this.reuseParser = reuseParser;
/*  404 */     this.saxParser = null;
/*      */   }
/*      */   
/*      */   public void setFastReconfigure(boolean fastReconfigure) {
/*  421 */     if (this.reuseParser)
/*  422 */       this.fastReconfigure = fastReconfigure; 
/*      */   }
/*      */   
/*      */   public void setFeature(String name, boolean value) {
/*  444 */     this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
/*      */   }
/*      */   
/*      */   public void setProperty(String name, Object value) {
/*  464 */     this.properties.put(name, value);
/*      */   }
/*      */   
/*      */   public Document build(InputSource in) throws JDOMException, IOException {
/*  479 */     SAXHandler contentHandler = null;
/*      */     try {
/*  483 */       contentHandler = createContentHandler();
/*  484 */       configureContentHandler(contentHandler);
/*  486 */       XMLReader parser = this.saxParser;
/*  487 */       if (parser == null) {
/*  489 */         parser = createParser();
/*  492 */         if (this.saxXMLFilter != null) {
/*  494 */           XMLFilter root = this.saxXMLFilter;
/*  495 */           while (root.getParent() instanceof XMLFilter)
/*  496 */             root = (XMLFilter)root.getParent(); 
/*  498 */           root.setParent(parser);
/*  501 */           parser = this.saxXMLFilter;
/*      */         } 
/*  505 */         configureParser(parser, contentHandler);
/*  507 */         if (this.reuseParser)
/*  508 */           this.saxParser = parser; 
/*      */       } else {
/*  514 */         configureParser(parser, contentHandler);
/*      */       } 
/*  518 */       parser.parse(in);
/*  520 */       return contentHandler.getDocument();
/*  522 */     } catch (SAXParseException e) {
/*  523 */       Document doc = contentHandler.getDocument();
/*  524 */       if (!doc.hasRootElement())
/*  525 */         doc = null; 
/*  528 */       String systemId = e.getSystemId();
/*  529 */       if (systemId != null)
/*  530 */         throw new JDOMParseException("Error on line " + e.getLineNumber() + " of document " + systemId, e, doc); 
/*  533 */       throw new JDOMParseException("Error on line " + e.getLineNumber(), e, doc);
/*  537 */     } catch (SAXException e) {
/*  538 */       throw new JDOMParseException("Error in building: " + e.getMessage(), e, contentHandler.getDocument());
/*      */     } finally {
/*  545 */       contentHandler = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected SAXHandler createContentHandler() {
/*  555 */     SAXHandler contentHandler = new SAXHandler(this.factory);
/*  556 */     return contentHandler;
/*      */   }
/*      */   
/*      */   protected void configureContentHandler(SAXHandler contentHandler) {
/*  570 */     contentHandler.setExpandEntities(this.expand);
/*  571 */     contentHandler.setIgnoringElementContentWhitespace(this.ignoringWhite);
/*  572 */     contentHandler.setIgnoringBoundaryWhitespace(this.ignoringBoundaryWhite);
/*      */   }
/*      */   
/*      */   protected XMLReader createParser() throws JDOMException {
/*  589 */     XMLReader parser = null;
/*  590 */     if (this.saxDriverClass != null) {
/*      */       try {
/*  593 */         parser = XMLReaderFactory.createXMLReader(this.saxDriverClass);
/*  596 */         setFeaturesAndProperties(parser, true);
/*  598 */       } catch (SAXException e) {
/*  599 */         throw new JDOMException("Could not load " + this.saxDriverClass, e);
/*      */       } 
/*      */     } else {
/*      */       try {
/*  608 */         Class factoryClass = Class.forName("org.jdom.input.JAXPParserFactory");
/*  611 */         Method createParser = factoryClass.getMethod("createParser", new Class[] { boolean.class, Map.class, Map.class });
/*  616 */         parser = (XMLReader)createParser.invoke(null, new Object[] { this.validate ? Boolean.TRUE : Boolean.FALSE, this.features, this.properties });
/*  621 */         setFeaturesAndProperties(parser, false);
/*  623 */       } catch (JDOMException e) {
/*  624 */         throw e;
/*  626 */       } catch (NoClassDefFoundError e) {
/*      */       
/*  632 */       } catch (Exception e) {}
/*      */     } 
/*  639 */     if (parser == null)
/*      */       try {
/*  641 */         parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
/*  643 */         this.saxDriverClass = parser.getClass().getName();
/*  646 */         setFeaturesAndProperties(parser, true);
/*  648 */       } catch (SAXException e) {
/*  649 */         throw new JDOMException("Could not load default SAX parser: org.apache.xerces.parsers.SAXParser", e);
/*      */       }  
/*  654 */     return parser;
/*      */   }
/*      */   
/*      */   protected void configureParser(XMLReader parser, SAXHandler contentHandler) throws JDOMException {
/*  676 */     parser.setContentHandler(contentHandler);
/*  678 */     if (this.saxEntityResolver != null)
/*  679 */       parser.setEntityResolver(this.saxEntityResolver); 
/*  682 */     if (this.saxDTDHandler != null) {
/*  683 */       parser.setDTDHandler(this.saxDTDHandler);
/*      */     } else {
/*  685 */       parser.setDTDHandler(contentHandler);
/*      */     } 
/*  688 */     if (this.saxErrorHandler != null) {
/*  689 */       parser.setErrorHandler(this.saxErrorHandler);
/*      */     } else {
/*  691 */       parser.setErrorHandler(new BuilderErrorHandler());
/*      */     } 
/*  697 */     if (!this.skipNextLexicalReportingConfig) {
/*  698 */       boolean success = false;
/*      */       try {
/*  701 */         parser.setProperty("http://xml.org/sax/handlers/LexicalHandler", contentHandler);
/*  703 */         success = true;
/*  704 */       } catch (SAXNotSupportedException e) {
/*      */       
/*  706 */       } catch (SAXNotRecognizedException e) {}
/*  711 */       if (!success)
/*      */         try {
/*  713 */           parser.setProperty("http://xml.org/sax/properties/lexical-handler", contentHandler);
/*  715 */           success = true;
/*  716 */         } catch (SAXNotSupportedException e) {
/*      */         
/*  718 */         } catch (SAXNotRecognizedException e) {} 
/*  725 */       if (!success && this.fastReconfigure)
/*  726 */         this.skipNextLexicalReportingConfig = true; 
/*      */     } 
/*  733 */     if (!this.skipNextEntityExpandConfig) {
/*  734 */       boolean success = false;
/*  737 */       if (!this.expand)
/*      */         try {
/*  739 */           parser.setProperty("http://xml.org/sax/properties/declaration-handler", contentHandler);
/*  741 */           success = true;
/*  742 */         } catch (SAXNotSupportedException e) {
/*      */         
/*  744 */         } catch (SAXNotRecognizedException e) {} 
/*  752 */       if (!success && this.fastReconfigure)
/*  753 */         this.skipNextEntityExpandConfig = true; 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setFeaturesAndProperties(XMLReader parser, boolean coreFeatures) throws JDOMException {
/*  762 */     Iterator iter = this.features.keySet().iterator();
/*  763 */     while (iter.hasNext()) {
/*  764 */       String name = iter.next();
/*  765 */       Boolean value = (Boolean)this.features.get(name);
/*  766 */       internalSetFeature(parser, name, value.booleanValue(), name);
/*      */     } 
/*  770 */     iter = this.properties.keySet().iterator();
/*  771 */     while (iter.hasNext()) {
/*  772 */       String name = iter.next();
/*  773 */       internalSetProperty(parser, name, this.properties.get(name), name);
/*      */     } 
/*  776 */     if (coreFeatures) {
/*      */       try {
/*  779 */         internalSetFeature(parser, "http://xml.org/sax/features/validation", this.validate, "Validation");
/*  782 */       } catch (JDOMException e) {
/*  786 */         if (this.validate)
/*  787 */           throw e; 
/*      */       } 
/*  791 */       internalSetFeature(parser, "http://xml.org/sax/features/namespaces", true, "Namespaces");
/*  794 */       internalSetFeature(parser, "http://xml.org/sax/features/namespace-prefixes", true, "Namespace prefixes");
/*      */     } 
/*      */     try {
/*  807 */       if (parser.getFeature("http://xml.org/sax/features/external-general-entities") != this.expand)
/*  808 */         parser.setFeature("http://xml.org/sax/features/external-general-entities", this.expand); 
/*  811 */     } catch (SAXNotRecognizedException e) {
/*      */     
/*  812 */     } catch (SAXNotSupportedException e) {}
/*      */   }
/*      */   
/*      */   private void internalSetFeature(XMLReader parser, String feature, boolean value, String displayName) throws JDOMException {
/*      */     try {
/*  822 */       parser.setFeature(feature, value);
/*  823 */     } catch (SAXNotSupportedException e) {
/*  824 */       throw new JDOMException(displayName + " feature not supported for SAX driver " + parser.getClass().getName());
/*  826 */     } catch (SAXNotRecognizedException e) {
/*  827 */       throw new JDOMException(displayName + " feature not recognized for SAX driver " + parser.getClass().getName());
/*      */     } 
/*      */   }
/*      */   
/*      */   private void internalSetProperty(XMLReader parser, String property, Object value, String displayName) throws JDOMException {
/*      */     try {
/*  841 */       parser.setProperty(property, value);
/*  842 */     } catch (SAXNotSupportedException e) {
/*  843 */       throw new JDOMException(displayName + " property not supported for SAX driver " + parser.getClass().getName());
/*  845 */     } catch (SAXNotRecognizedException e) {
/*  846 */       throw new JDOMException(displayName + " property not recognized for SAX driver " + parser.getClass().getName());
/*      */     } 
/*      */   }
/*      */   
/*      */   public Document build(InputStream in) throws JDOMException, IOException {
/*  865 */     return build(new InputSource(in));
/*      */   }
/*      */   
/*      */   public Document build(File file) throws JDOMException, IOException {
/*      */     try {
/*  883 */       URL url = fileToURL(file);
/*  884 */       return build(url);
/*  885 */     } catch (MalformedURLException e) {
/*  886 */       throw new JDOMException("Error in building", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Document build(URL url) throws JDOMException, IOException {
/*  904 */     String systemID = url.toExternalForm();
/*  905 */     return build(new InputSource(systemID));
/*      */   }
/*      */   
/*      */   public Document build(InputStream in, String systemId) throws JDOMException, IOException {
/*  924 */     InputSource src = new InputSource(in);
/*  925 */     src.setSystemId(systemId);
/*  926 */     return build(src);
/*      */   }
/*      */   
/*      */   public Document build(Reader characterStream) throws JDOMException, IOException {
/*  946 */     return build(new InputSource(characterStream));
/*      */   }
/*      */   
/*      */   public Document build(Reader characterStream, String systemId) throws JDOMException, IOException {
/*  968 */     InputSource src = new InputSource(characterStream);
/*  969 */     src.setSystemId(systemId);
/*  970 */     return build(src);
/*      */   }
/*      */   
/*      */   public Document build(String systemId) throws JDOMException, IOException {
/*  986 */     return build(new InputSource(systemId));
/*      */   }
/*      */   
/*      */   private static URL fileToURL(File file) throws MalformedURLException {
/* 1019 */     StringBuffer buffer = new StringBuffer();
/* 1020 */     String path = file.getAbsolutePath();
/* 1023 */     if (File.separatorChar != '/')
/* 1024 */       path = path.replace(File.separatorChar, '/'); 
/* 1028 */     if (!path.startsWith("/"))
/* 1029 */       buffer.append('/'); 
/* 1033 */     int len = path.length();
/* 1034 */     for (int i = 0; i < len; i++) {
/* 1035 */       char c = path.charAt(i);
/* 1036 */       if (c == ' ') {
/* 1037 */         buffer.append("%20");
/* 1038 */       } else if (c == '#') {
/* 1039 */         buffer.append("%23");
/* 1040 */       } else if (c == '%') {
/* 1041 */         buffer.append("%25");
/* 1042 */       } else if (c == '&') {
/* 1043 */         buffer.append("%26");
/* 1044 */       } else if (c == ';') {
/* 1045 */         buffer.append("%3B");
/* 1046 */       } else if (c == '<') {
/* 1047 */         buffer.append("%3C");
/* 1048 */       } else if (c == '=') {
/* 1049 */         buffer.append("%3D");
/* 1050 */       } else if (c == '>') {
/* 1051 */         buffer.append("%3E");
/* 1052 */       } else if (c == '?') {
/* 1053 */         buffer.append("%3F");
/* 1054 */       } else if (c == '~') {
/* 1055 */         buffer.append("%7E");
/*      */       } else {
/* 1057 */         buffer.append(c);
/*      */       } 
/*      */     } 
/* 1061 */     if (!path.endsWith("/") && file.isDirectory())
/* 1062 */       buffer.append('/'); 
/* 1066 */     return new URL("file", "", buffer.toString());
/*      */   }
/*      */   
/*      */   public boolean getExpandEntities() {
/* 1076 */     return this.expand;
/*      */   }
/*      */   
/*      */   public void setExpandEntities(boolean expand) {
/* 1103 */     this.expand = expand;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\input\SAXBuilder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */