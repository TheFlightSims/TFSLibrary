/*      */ package org.geotools.xml.transform;
/*      */ 
/*      */ import java.io.OutputStream;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.TransformerFactory;
/*      */ import javax.xml.transform.sax.SAXSource;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ import org.xml.sax.helpers.AttributesImpl;
/*      */ import org.xml.sax.helpers.NamespaceSupport;
/*      */ import org.xml.sax.helpers.XMLFilterImpl;
/*      */ 
/*      */ public abstract class TransformerBase {
/*   60 */   private int indentation = -1;
/*      */   
/*      */   private boolean xmlDecl = false;
/*      */   
/*      */   private boolean nsDecl = true;
/*      */   
/*   63 */   private Charset charset = Charset.forName("UTF-8");
/*      */   
/*      */   public static final String XMLNS_NAMESPACE = "http://www.w3.org/2000/xmlns/";
/*      */   
/*      */   public abstract Translator createTranslator(ContentHandler paramContentHandler);
/*      */   
/*      */   public Transformer createTransformer() throws TransformerException {
/*   77 */     TransformerFactory tFactory = TransformerFactory.newInstance();
/*   78 */     if (this.indentation > -1)
/*      */       try {
/*   80 */         tFactory.setAttribute("indent-number", new Integer(this.indentation));
/*   81 */       } catch (IllegalArgumentException e) {} 
/*   86 */     Transformer transformer = tFactory.newTransformer();
/*   88 */     if (this.indentation > -1) {
/*   89 */       transformer.setOutputProperty("indent", "yes");
/*   90 */       transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(this.indentation));
/*      */     } else {
/*   92 */       transformer.setOutputProperty("indent", "no");
/*      */     } 
/*   95 */     if (this.xmlDecl) {
/*   96 */       transformer.setOutputProperty("omit-xml-declaration", "yes");
/*      */     } else {
/*   98 */       transformer.setOutputProperty("omit-xml-declaration", "no");
/*      */     } 
/*  101 */     transformer.setOutputProperty("encoding", this.charset.name());
/*  103 */     return transformer;
/*      */   }
/*      */   
/*      */   public void transform(Object object, OutputStream out) throws TransformerException {
/*  112 */     transform(object, new StreamResult(out));
/*      */   }
/*      */   
/*      */   public void transform(Object object, Writer out) throws TransformerException {
/*  121 */     transform(object, new StreamResult(out));
/*      */   }
/*      */   
/*      */   public void transform(Object object, StreamResult result) throws TransformerException {
/*  132 */     Task t = createTransformTask(object, result);
/*  133 */     t.run();
/*  134 */     if (t.checkError()) {
/*  135 */       Exception e = t.getError();
/*  136 */       if (!TransformerException.class.isAssignableFrom(e.getClass()))
/*  137 */         e = new TransformerException("Translator error", e); 
/*  139 */       throw (TransformerException)e;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Task createTransformTask(Object object, StreamResult result) throws TransformerException {
/*  151 */     return new Task(object, result);
/*      */   }
/*      */   
/*      */   public String transform(Object object) throws TransformerException {
/*  161 */     StringWriter sw = new StringWriter();
/*  162 */     transform(object, sw);
/*  163 */     return sw.getBuffer().toString();
/*      */   }
/*      */   
/*      */   public XMLReaderSupport createXMLReader(Object object) {
/*  170 */     return new XMLReaderSupport(this, object);
/*      */   }
/*      */   
/*      */   public int getIndentation() {
/*  179 */     return this.indentation;
/*      */   }
/*      */   
/*      */   public void setIndentation(int amt) {
/*  188 */     this.indentation = amt;
/*      */   }
/*      */   
/*      */   public Charset getEncoding() {
/*  196 */     return this.charset;
/*      */   }
/*      */   
/*      */   public void setEncoding(Charset charset) {
/*  205 */     this.charset = charset;
/*      */   }
/*      */   
/*      */   public boolean isOmitXMLDeclaration() {
/*  215 */     return this.xmlDecl;
/*      */   }
/*      */   
/*      */   public void setOmitXMLDeclaration(boolean xmlDecl) {
/*  225 */     this.xmlDecl = xmlDecl;
/*      */   }
/*      */   
/*      */   public boolean isNamespaceDeclartionEnabled() {
/*  235 */     return this.nsDecl;
/*      */   }
/*      */   
/*      */   public void setNamespaceDeclarationEnabled(boolean enabled) {
/*  245 */     this.nsDecl = enabled;
/*      */   }
/*      */   
/*      */   public class Task implements Runnable {
/*      */     private final Transformer transformer;
/*      */     
/*      */     private final Source xmlSource;
/*      */     
/*      */     private final StreamResult result;
/*      */     
/*      */     private final TransformerBase.XMLReaderSupport reader;
/*      */     
/*      */     private Exception error;
/*      */     
/*      */     public Task(Object object, StreamResult result) throws TransformerException {
/*  262 */       this.transformer = TransformerBase.this.createTransformer();
/*  263 */       this.reader = TransformerBase.this.createXMLReader(object);
/*  265 */       this.xmlSource = new SAXSource(TransformerBase.this.createXMLReader(object), new InputSource());
/*  268 */       this.result = result;
/*      */     }
/*      */     
/*      */     public boolean checkError() {
/*  275 */       return (this.error != null);
/*      */     }
/*      */     
/*      */     public Exception getError() {
/*  282 */       return this.error;
/*      */     }
/*      */     
/*      */     public void abort() {
/*  289 */       Translator t = this.reader.getTranslator();
/*  290 */       if (t != null)
/*  291 */         t.abort(); 
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       try {
/*  300 */         this.transformer.transform(this.xmlSource, this.result);
/*  301 */       } catch (Exception re) {
/*  302 */         this.error = re;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ContentHandlerFilter implements ContentHandler, LexicalHandler {
/*      */     private final ContentHandler original;
/*      */     
/*      */     private AttributesImpl namespaceDecls;
/*      */     
/*      */     public ContentHandlerFilter(ContentHandler original, AttributesImpl nsDecls) {
/*  319 */       this.original = original;
/*  320 */       this.namespaceDecls = nsDecls;
/*      */     }
/*      */     
/*      */     public void characters(char[] ch, int start, int length) throws SAXException {
/*  325 */       this.original.characters(ch, start, length);
/*      */     }
/*      */     
/*      */     public void endDocument() throws SAXException {
/*  329 */       this.original.endDocument();
/*      */     }
/*      */     
/*      */     public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/*  334 */       this.original.endElement(namespaceURI, localName, qName);
/*      */     }
/*      */     
/*      */     public void endPrefixMapping(String prefix) throws SAXException {
/*  338 */       this.original.endPrefixMapping(prefix);
/*      */     }
/*      */     
/*      */     public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*  343 */       this.original.ignorableWhitespace(ch, start, length);
/*      */     }
/*      */     
/*      */     public void processingInstruction(String target, String data) throws SAXException {
/*  348 */       this.original.processingInstruction(target, data);
/*      */     }
/*      */     
/*      */     public void setDocumentLocator(Locator locator) {
/*  352 */       this.original.setDocumentLocator(locator);
/*      */     }
/*      */     
/*      */     public void skippedEntity(String name) throws SAXException {
/*  356 */       this.original.skippedEntity(name);
/*      */     }
/*      */     
/*      */     public void startDocument() throws SAXException {
/*  360 */       this.original.startDocument();
/*      */     }
/*      */     
/*      */     public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/*  365 */       if (this.namespaceDecls != null) {
/*  366 */         for (int i = 0, ii = atts.getLength(); i < ii; i++)
/*  367 */           this.namespaceDecls.addAttribute(null, null, atts.getQName(i), atts.getType(i), atts.getValue(i)); 
/*  372 */         atts = this.namespaceDecls;
/*  373 */         this.namespaceDecls = null;
/*      */       } 
/*  375 */       if (namespaceURI == null)
/*  376 */         namespaceURI = ""; 
/*  377 */       if (localName == null)
/*  378 */         localName = ""; 
/*  379 */       this.original.startElement(namespaceURI, localName, qName, atts);
/*      */     }
/*      */     
/*      */     public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  384 */       this.original.startPrefixMapping(prefix, uri);
/*      */     }
/*      */     
/*      */     public void comment(char[] ch, int start, int length) throws SAXException {
/*  388 */       if (this.original instanceof LexicalHandler)
/*  389 */         ((LexicalHandler)this.original).comment(ch, start, length); 
/*      */     }
/*      */     
/*      */     public void startCDATA() throws SAXException {
/*  394 */       if (this.original instanceof LexicalHandler)
/*  395 */         ((LexicalHandler)this.original).startCDATA(); 
/*      */     }
/*      */     
/*      */     public void endCDATA() throws SAXException {
/*  400 */       if (this.original instanceof LexicalHandler)
/*  401 */         ((LexicalHandler)this.original).endCDATA(); 
/*      */     }
/*      */     
/*      */     public void startDTD(String name, String publicId, String systemId) throws SAXException {
/*  406 */       if (this.original instanceof LexicalHandler)
/*  407 */         ((LexicalHandler)this.original).startDTD(name, publicId, systemId); 
/*      */     }
/*      */     
/*      */     public void endDTD() throws SAXException {
/*  412 */       if (this.original instanceof LexicalHandler)
/*  413 */         ((LexicalHandler)this.original).endDTD(); 
/*      */     }
/*      */     
/*      */     public void startEntity(String name) throws SAXException {
/*  418 */       if (this.original instanceof LexicalHandler)
/*  419 */         ((LexicalHandler)this.original).startEntity(name); 
/*      */     }
/*      */     
/*      */     public void endEntity(String name) throws SAXException {
/*  424 */       if (this.original instanceof LexicalHandler)
/*  425 */         ((LexicalHandler)this.original).endEntity(name); 
/*      */     }
/*      */   }
/*      */   
/*      */   protected static abstract class TranslatorSupport implements Translator {
/*      */     protected final ContentHandler contentHandler;
/*      */     
/*      */     private String prefix;
/*      */     
/*      */     private String namespace;
/*      */     
/*  437 */     protected final Attributes NULL_ATTS = new AttributesImpl();
/*      */     
/*  438 */     protected NamespaceSupport nsSupport = new NamespaceSupport();
/*      */     
/*      */     protected TransformerBase.SchemaLocationSupport schemaLocation;
/*      */     
/*  445 */     private List<Action> pending = new ArrayList<Action>();
/*      */     
/*      */     private static interface Action {
/*      */       void commit();
/*      */     }
/*      */     
/*      */     private class Start implements Action {
/*      */       private final String element;
/*      */       
/*      */       private final Attributes attributes;
/*      */       
/*      */       public Start(String element, Attributes attributes) {
/*  464 */         this.element = element;
/*  465 */         this.attributes = new AttributesImpl(attributes);
/*      */       }
/*      */       
/*      */       public void commit() {
/*  469 */         TransformerBase.TranslatorSupport.this._start(this.element, this.attributes);
/*      */       }
/*      */       
/*      */       public String toString() {
/*  473 */         return "Start(" + this.element + ", " + this.attributes + ")";
/*      */       }
/*      */     }
/*      */     
/*      */     private class Chars implements Action {
/*      */       private final String text;
/*      */       
/*      */       public Chars(String text) {
/*  485 */         this.text = text;
/*      */       }
/*      */       
/*      */       public void commit() {
/*  489 */         TransformerBase.TranslatorSupport.this._chars(this.text);
/*      */       }
/*      */       
/*      */       public String toString() {
/*  493 */         return "Chars(" + this.text + ")";
/*      */       }
/*      */     }
/*      */     
/*      */     private class CData implements Action {
/*      */       private final String text;
/*      */       
/*      */       public CData(String text) {
/*  505 */         this.text = text;
/*      */       }
/*      */       
/*      */       public void commit() {
/*  509 */         TransformerBase.TranslatorSupport.this._cdata(this.text);
/*      */       }
/*      */       
/*      */       public String toString() {
/*  513 */         return "CData(" + this.text + ")";
/*      */       }
/*      */     }
/*      */     
/*      */     private class Comment implements Action {
/*      */       private final String text;
/*      */       
/*      */       public Comment(String text) {
/*  525 */         this.text = text;
/*      */       }
/*      */       
/*      */       public void commit() {
/*  529 */         TransformerBase.TranslatorSupport.this._comment(this.text);
/*      */       }
/*      */       
/*      */       public String toString() {
/*  533 */         return "Comment(" + this.text + ")";
/*      */       }
/*      */     }
/*      */     
/*      */     private class End implements Action {
/*      */       private final String element;
/*      */       
/*      */       public End(String element) {
/*  545 */         this.element = element;
/*      */       }
/*      */       
/*      */       public void commit() {
/*  549 */         TransformerBase.TranslatorSupport.this._end(this.element);
/*      */       }
/*      */       
/*      */       public String toString() {
/*  553 */         return "End(" + this.element + ")";
/*      */       }
/*      */     }
/*      */     
/*      */     private static interface Backend {
/*      */       void start(String param2String, Attributes param2Attributes);
/*      */       
/*      */       void chars(String param2String);
/*      */       
/*      */       void cdata(String param2String);
/*      */       
/*      */       void comment(String param2String);
/*      */       
/*      */       void end(String param2String);
/*      */     }
/*      */     
/*      */     private class BufferedBackend implements Backend {
/*      */       private BufferedBackend() {}
/*      */       
/*      */       public void start(String element, Attributes attributes) {
/*  573 */         if (element == null)
/*  574 */           throw new NullPointerException("Attempted to start XML tag with null element"); 
/*  576 */         if (attributes == null)
/*  577 */           throw new NullPointerException("Attempted to start XML tag with null attributes"); 
/*  579 */         TransformerBase.TranslatorSupport.this.pending.add(new TransformerBase.TranslatorSupport.Start(element, attributes));
/*      */       }
/*      */       
/*      */       public void chars(String text) {
/*  583 */         if (text == null)
/*  584 */           throw new NullPointerException("Attempted to start text block with null text"); 
/*  586 */         TransformerBase.TranslatorSupport.this.pending.add(new TransformerBase.TranslatorSupport.Chars(text));
/*      */       }
/*      */       
/*      */       public void cdata(String text) {
/*  590 */         if (text == null)
/*  591 */           throw new NullPointerException("Attempted to start CDATA block with null text"); 
/*  593 */         TransformerBase.TranslatorSupport.this.pending.add(new TransformerBase.TranslatorSupport.CData(text));
/*      */       }
/*      */       
/*      */       public void end(String element) {
/*  597 */         if (element == null)
/*  598 */           throw new NullPointerException("Attempted to close tag with null element"); 
/*  600 */         TransformerBase.TranslatorSupport.this.pending.add(new TransformerBase.TranslatorSupport.End(element));
/*      */       }
/*      */       
/*      */       public void comment(String text) {
/*  604 */         if (text == null)
/*  605 */           throw new NullPointerException("Attempted to add comment with null text"); 
/*  607 */         TransformerBase.TranslatorSupport.this.pending.add(new TransformerBase.TranslatorSupport.Comment(text));
/*      */       }
/*      */     }
/*      */     
/*      */     private class DirectBackend implements Backend {
/*      */       private DirectBackend() {}
/*      */       
/*      */       public void start(String element, Attributes attributes) {
/*  616 */         TransformerBase.TranslatorSupport.this._start(element, attributes);
/*      */       }
/*      */       
/*      */       public void chars(String text) {
/*  620 */         TransformerBase.TranslatorSupport.this._chars(text);
/*      */       }
/*      */       
/*      */       public void cdata(String text) {
/*  624 */         TransformerBase.TranslatorSupport.this._cdata(text);
/*      */       }
/*      */       
/*      */       public void end(String element) {
/*  628 */         TransformerBase.TranslatorSupport.this._end(element);
/*      */       }
/*      */       
/*      */       public void comment(String text) {
/*  632 */         TransformerBase.TranslatorSupport.this._comment(text);
/*      */       }
/*      */     }
/*      */     
/*  639 */     private final Backend directBackend = new DirectBackend();
/*      */     
/*  644 */     private final Backend bufferedBackend = new BufferedBackend();
/*      */     
/*  649 */     private Backend backend = this.directBackend;
/*      */     
/*      */     protected volatile boolean running = true;
/*      */     
/*      */     public TranslatorSupport(ContentHandler contentHandler, String prefix, String nsURI) {
/*  659 */       this.contentHandler = contentHandler;
/*  660 */       this.prefix = prefix;
/*  661 */       this.namespace = nsURI;
/*  662 */       if (prefix != null && nsURI != null)
/*  663 */         this.nsSupport.declarePrefix(prefix, nsURI); 
/*      */     }
/*      */     
/*      */     public TranslatorSupport(ContentHandler contentHandler, String prefix, String nsURI, TransformerBase.SchemaLocationSupport schemaLocation) {
/*  668 */       this(contentHandler, prefix, nsURI);
/*  669 */       this.schemaLocation = schemaLocation;
/*      */     }
/*      */     
/*      */     public void abort() {
/*  673 */       this.running = false;
/*      */     }
/*      */     
/*      */     protected void mark() {
/*  701 */       if (this.backend == this.bufferedBackend)
/*  701 */         throw new IllegalStateException("Mark already set"); 
/*  702 */       this.backend = this.bufferedBackend;
/*      */     }
/*      */     
/*      */     protected void reset() {
/*  714 */       this.pending.clear();
/*  715 */       this.backend = this.directBackend;
/*      */     }
/*      */     
/*      */     protected void commit() {
/*  726 */       if (this.backend != this.bufferedBackend)
/*  726 */         throw new IllegalStateException("Can't commit without a mark"); 
/*  727 */       for (Action a : this.pending) {
/*      */         try {
/*  729 */           a.commit();
/*  730 */         } catch (Exception e) {
/*  731 */           String message = "Error while committing XML elements; specific element was: " + a;
/*  733 */           throw new RuntimeException(message, e);
/*      */         } 
/*      */       } 
/*  736 */       this.pending.clear();
/*  737 */       this.backend = this.directBackend;
/*      */     }
/*      */     
/*      */     protected void addNamespaceDeclarations(TranslatorSupport trans) {
/*  745 */       NamespaceSupport additional = trans.getNamespaceSupport();
/*  746 */       Enumeration<String> declared = additional.getDeclaredPrefixes();
/*  747 */       while (declared.hasMoreElements()) {
/*  748 */         String prefix1 = declared.nextElement().toString();
/*  749 */         this.nsSupport.declarePrefix(prefix1, additional.getURI(prefix1));
/*      */       } 
/*      */     }
/*      */     
/*      */     protected AttributesImpl createAttributes(String[] nameValuePairs) {
/*  764 */       AttributesImpl attributes = new AttributesImpl();
/*  766 */       for (int i = 0; i < nameValuePairs.length; i += 2) {
/*  767 */         String name = nameValuePairs[i];
/*  768 */         String value = nameValuePairs[i + 1];
/*  770 */         attributes.addAttribute("", name, name, "", value);
/*      */       } 
/*  773 */       return attributes;
/*      */     }
/*      */     
/*      */     protected void element(String element, String content) {
/*  777 */       element(element, content, this.NULL_ATTS);
/*      */     }
/*      */     
/*      */     protected void elementSafe(String element, String content) {
/*  786 */       if (content != null && content.length() != 0)
/*  787 */         element(element, content, this.NULL_ATTS); 
/*      */     }
/*      */     
/*      */     protected void element(String element, String content, Attributes atts) {
/*  792 */       start(element, atts);
/*  794 */       if (content != null)
/*  795 */         chars(content); 
/*  798 */       end(element);
/*      */     }
/*      */     
/*      */     protected void start(String element) {
/*  802 */       start(element, this.NULL_ATTS);
/*      */     }
/*      */     
/*      */     protected void start(String element, Attributes atts) {
/*  806 */       this.backend.start(element, atts);
/*      */     }
/*      */     
/*      */     private void _start(String element, Attributes atts) {
/*      */       try {
/*  811 */         String el = (this.prefix == null) ? element : (this.prefix + ":" + element);
/*  812 */         this.contentHandler.startElement("", "", el, atts);
/*  813 */       } catch (SAXException se) {
/*  814 */         throw new RuntimeException(se);
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void chars(String text) {
/*  819 */       this.backend.chars(text);
/*      */     }
/*      */     
/*      */     private void _chars(String text) {
/*      */       try {
/*  824 */         char[] ch = text.toCharArray();
/*  825 */         this.contentHandler.characters(ch, 0, ch.length);
/*  826 */       } catch (SAXException se) {
/*  827 */         throw new RuntimeException(se);
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void end(String element) {
/*  832 */       this.backend.end(element);
/*      */     }
/*      */     
/*      */     private void _end(String element) {
/*      */       try {
/*  837 */         String el = (this.prefix == null) ? element : (this.prefix + ":" + element);
/*  839 */         this.contentHandler.endElement("", "", el);
/*  840 */       } catch (SAXException se) {
/*  841 */         throw new RuntimeException(se);
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void cdata(String cdata) {
/*  846 */       this.backend.cdata(cdata);
/*      */     }
/*      */     
/*      */     private void _cdata(String cdata) {
/*  850 */       if (this.contentHandler instanceof LexicalHandler) {
/*  851 */         LexicalHandler lexicalHandler = (LexicalHandler)this.contentHandler;
/*      */         try {
/*  853 */           lexicalHandler.startCDATA();
/*  854 */           char[] carray = cdata.toCharArray();
/*  855 */           this.contentHandler.characters(carray, 0, carray.length);
/*  856 */           lexicalHandler.endCDATA();
/*  858 */         } catch (SAXException e) {
/*  859 */           throw new RuntimeException(e);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void comment(String comment) {
/*  865 */       this.backend.comment(comment);
/*      */     }
/*      */     
/*      */     private void _comment(String comment) {
/*  869 */       if (this.contentHandler instanceof LexicalHandler) {
/*  870 */         LexicalHandler lexicalHandler = (LexicalHandler)this.contentHandler;
/*      */         try {
/*  872 */           char[] carray = comment.toCharArray();
/*  873 */           lexicalHandler.comment(carray, 0, carray.length);
/*  875 */         } catch (SAXException e) {
/*  876 */           throw new RuntimeException(e);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public String getDefaultNamespace() {
/*  882 */       return this.namespace;
/*      */     }
/*      */     
/*      */     public String getDefaultPrefix() {
/*  886 */       return this.prefix;
/*      */     }
/*      */     
/*      */     public NamespaceSupport getNamespaceSupport() {
/*  890 */       return this.nsSupport;
/*      */     }
/*      */     
/*      */     public TransformerBase.SchemaLocationSupport getSchemaLocationSupport() {
/*  894 */       return this.schemaLocation;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class SchemaLocationSupport {
/*  907 */     private Map locations = new HashMap<Object, Object>();
/*      */     
/*      */     public void setLocation(String nsURI, String uri) {
/*  910 */       this.locations.put(nsURI, uri);
/*      */     }
/*      */     
/*      */     public String getSchemaLocation() {
/*  914 */       return getSchemaLocation(this.locations.keySet());
/*      */     }
/*      */     
/*      */     public String getSchemaLocation(String nsURI) {
/*  918 */       String uri = (String)this.locations.get(nsURI);
/*  920 */       if (uri == null)
/*  921 */         return ""; 
/*  923 */       return nsURI + " " + uri;
/*      */     }
/*      */     
/*      */     public String getSchemaLocation(Set namespaces) {
/*  927 */       StringBuffer location = new StringBuffer();
/*  929 */       for (Iterator<String> it = namespaces.iterator(); it.hasNext(); ) {
/*  930 */         location.append(getSchemaLocation(it.next()));
/*  932 */         if (it.hasNext())
/*  933 */           location.append(" "); 
/*      */       } 
/*  937 */       return location.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class XMLReaderSupport extends XMLFilterImpl {
/*      */     TransformerBase base;
/*      */     
/*      */     Object object;
/*      */     
/*      */     Translator translator;
/*      */     
/*      */     public XMLReaderSupport(TransformerBase transfomerBase, Object object) {
/*  950 */       this.base = transfomerBase;
/*  951 */       this.object = object;
/*      */     }
/*      */     
/*      */     public final Translator getTranslator() {
/*  955 */       return this.translator;
/*      */     }
/*      */     
/*      */     public void parse(InputSource in) throws SAXException {
/*  959 */       ContentHandler handler = getContentHandler();
/*  961 */       if (this.base.isNamespaceDeclartionEnabled()) {
/*  962 */         AttributesImpl atts = new AttributesImpl();
/*  963 */         TransformerBase.ContentHandlerFilter filter = new TransformerBase.ContentHandlerFilter(handler, atts);
/*  965 */         this.translator = this.base.createTranslator(filter);
/*  967 */         if (this.translator.getDefaultNamespace() != null) {
/*  969 */           atts.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns", "CDATA", this.translator.getDefaultNamespace());
/*  973 */           if (this.translator.getDefaultPrefix() != null)
/*  974 */             atts.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:" + this.translator.getDefaultPrefix(), "CDATA", this.translator.getDefaultNamespace()); 
/*      */         } 
/*  980 */         NamespaceSupport ns = this.translator.getNamespaceSupport();
/*  981 */         Enumeration<String> e = ns.getPrefixes();
/*  986 */         while (e.hasMoreElements()) {
/*  987 */           String prefix = e.nextElement().toString();
/*  989 */           if (prefix.equals("xml"))
/*      */             continue; 
/*  993 */           String xmlns = "xmlns:" + prefix;
/*  995 */           if (atts.getValue(xmlns) == null)
/*  996 */             atts.addAttribute("http://www.w3.org/2000/xmlns/", null, xmlns, "CDATA", ns.getURI(prefix)); 
/*      */         } 
/* 1003 */         String defaultNS = ns.getURI("");
/* 1005 */         if (defaultNS != null && atts.getValue("xmlns:") == null)
/* 1006 */           atts.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:", "CDATA", defaultNS); 
/* 1011 */         TransformerBase.SchemaLocationSupport schemaLocSup = this.translator.getSchemaLocationSupport();
/* 1014 */         if (schemaLocSup != null && !schemaLocSup.getSchemaLocation().equals("")) {
/* 1016 */           atts.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xsi", "CDATA", "http://www.w3.org/2001/XMLSchema-instance");
/* 1018 */           atts.addAttribute(null, null, "xsi:schemaLocation", null, schemaLocSup.getSchemaLocation());
/*      */         } 
/*      */       } else {
/* 1022 */         this.translator = this.base.createTranslator(handler);
/*      */       } 
/* 1025 */       handler.startDocument();
/* 1026 */       this.translator.encode(this.object);
/* 1027 */       handler.endDocument();
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\xml\transform\TransformerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */