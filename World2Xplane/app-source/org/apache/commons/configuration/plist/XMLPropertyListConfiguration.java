/*     */ package org.apache.commons.configuration.plist;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URL;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.commons.configuration.AbstractHierarchicalFileConfiguration;
/*     */ import org.apache.commons.configuration.Configuration;
/*     */ import org.apache.commons.configuration.ConfigurationException;
/*     */ import org.apache.commons.configuration.HierarchicalConfiguration;
/*     */ import org.apache.commons.configuration.MapConfiguration;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.lang.StringEscapeUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class XMLPropertyListConfiguration extends AbstractHierarchicalFileConfiguration {
/*     */   private static final long serialVersionUID = -3162063751042475985L;
/*     */   
/*     */   private static final int INDENT_SIZE = 4;
/*     */   
/*     */   public XMLPropertyListConfiguration() {
/* 139 */     initRoot();
/*     */   }
/*     */   
/*     */   public XMLPropertyListConfiguration(HierarchicalConfiguration configuration) {
/* 151 */     super(configuration);
/*     */   }
/*     */   
/*     */   public XMLPropertyListConfiguration(String fileName) throws ConfigurationException {
/* 163 */     super(fileName);
/*     */   }
/*     */   
/*     */   public XMLPropertyListConfiguration(File file) throws ConfigurationException {
/* 174 */     super(file);
/*     */   }
/*     */   
/*     */   public XMLPropertyListConfiguration(URL url) throws ConfigurationException {
/* 185 */     super(url);
/*     */   }
/*     */   
/*     */   public void setProperty(String key, Object value) {
/* 191 */     if (value instanceof byte[]) {
/* 193 */       fireEvent(3, key, value, true);
/* 194 */       setDetailEvents(false);
/*     */       try {
/* 197 */         clearProperty(key);
/* 198 */         addPropertyDirect(key, value);
/*     */       } finally {
/* 202 */         setDetailEvents(true);
/*     */       } 
/* 204 */       fireEvent(3, key, value, false);
/*     */     } else {
/* 208 */       super.setProperty(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addProperty(String key, Object value) {
/* 214 */     if (value instanceof byte[]) {
/* 216 */       fireEvent(1, key, value, true);
/* 217 */       addPropertyDirect(key, value);
/* 218 */       fireEvent(1, key, value, false);
/*     */     } else {
/* 222 */       super.addProperty(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void load(Reader in) throws ConfigurationException {
/* 231 */     if (!(getRootNode() instanceof PListNode))
/* 233 */       initRoot(); 
/* 237 */     EntityResolver resolver = new EntityResolver() {
/*     */         private final XMLPropertyListConfiguration this$0;
/*     */         
/*     */         public InputSource resolveEntity(String publicId, String systemId) {
/* 241 */           return new InputSource(getClass().getClassLoader().getResourceAsStream("PropertyList-1.0.dtd"));
/*     */         }
/*     */       };
/* 246 */     XMLPropertyListHandler handler = new XMLPropertyListHandler(getRoot());
/*     */     try {
/* 249 */       SAXParserFactory factory = SAXParserFactory.newInstance();
/* 250 */       factory.setValidating(true);
/* 252 */       SAXParser parser = factory.newSAXParser();
/* 253 */       parser.getXMLReader().setEntityResolver(resolver);
/* 254 */       parser.getXMLReader().setContentHandler(handler);
/* 255 */       parser.getXMLReader().parse(new InputSource(in));
/* 257 */     } catch (Exception e) {
/* 259 */       throw new ConfigurationException("Unable to parse the configuration file", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void save(Writer out) throws ConfigurationException {
/* 265 */     PrintWriter writer = new PrintWriter(out);
/* 267 */     if (getEncoding() != null) {
/* 269 */       writer.println("<?xml version=\"1.0\" encoding=\"" + getEncoding() + "\"?>");
/*     */     } else {
/* 273 */       writer.println("<?xml version=\"1.0\"?>");
/*     */     } 
/* 276 */     writer.println("<!DOCTYPE plist SYSTEM \"file://localhost/System/Library/DTDs/PropertyList.dtd\">");
/* 277 */     writer.println("<plist version=\"1.0\">");
/* 279 */     printNode(writer, 1, getRoot());
/* 281 */     writer.println("</plist>");
/* 282 */     writer.flush();
/*     */   }
/*     */   
/*     */   private void printNode(PrintWriter out, int indentLevel, HierarchicalConfiguration.Node node) {
/* 290 */     String padding = StringUtils.repeat(" ", indentLevel * 4);
/* 292 */     if (node.getName() != null)
/* 294 */       out.println(padding + "<key>" + StringEscapeUtils.escapeXml(node.getName()) + "</key>"); 
/* 297 */     List children = node.getChildren();
/* 298 */     if (!children.isEmpty()) {
/* 300 */       out.println(padding + "<dict>");
/* 302 */       Iterator it = children.iterator();
/* 303 */       while (it.hasNext()) {
/* 305 */         HierarchicalConfiguration.Node child = it.next();
/* 306 */         printNode(out, indentLevel + 1, child);
/* 308 */         if (it.hasNext())
/* 310 */           out.println(); 
/*     */       } 
/* 314 */       out.println(padding + "</dict>");
/* 316 */     } else if (node.getValue() == null) {
/* 318 */       out.println(padding + "<dict/>");
/*     */     } else {
/* 322 */       Object value = node.getValue();
/* 323 */       printValue(out, indentLevel, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void printValue(PrintWriter out, int indentLevel, Object value) {
/* 332 */     String padding = StringUtils.repeat(" ", indentLevel * 4);
/* 334 */     if (value instanceof Date) {
/* 584 */       synchronized (PListNode.format) {
/* 584 */         out.println(padding + "<date>" + PListNode.format.format((Date)value) + "</date>");
/*     */       } 
/*     */     } else if (value instanceof Calendar) {
/*     */       printValue(out, indentLevel, ((Calendar)value).getTime());
/*     */     } else if (value instanceof Number) {
/*     */       if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
/*     */         out.println(padding + "<real>" + value.toString() + "</real>");
/*     */       } else {
/*     */         out.println(padding + "<integer>" + value.toString() + "</integer>");
/*     */       } 
/*     */     } else if (value instanceof Boolean) {
/*     */       if (((Boolean)value).booleanValue()) {
/*     */         out.println(padding + "<true/>");
/*     */       } else {
/*     */         out.println(padding + "<false/>");
/*     */       } 
/*     */     } else if (value instanceof List) {
/*     */       out.println(padding + "<array>");
/*     */       Iterator it = ((List)value).iterator();
/*     */       while (it.hasNext())
/*     */         printValue(out, indentLevel + 1, it.next()); 
/*     */       out.println(padding + "</array>");
/*     */     } else if (value instanceof HierarchicalConfiguration) {
/*     */       printNode(out, indentLevel, ((HierarchicalConfiguration)value).getRoot());
/*     */     } else if (value instanceof Configuration) {
/*     */       out.println(padding + "<dict>");
/*     */       Configuration config = (Configuration)value;
/*     */       Iterator it = config.getKeys();
/*     */       while (it.hasNext()) {
/*     */         String key = it.next();
/*     */         HierarchicalConfiguration.Node node = new HierarchicalConfiguration.Node(key);
/*     */         node.setValue(config.getProperty(key));
/*     */         printNode(out, indentLevel + 1, node);
/*     */         if (it.hasNext())
/*     */           out.println(); 
/*     */       } 
/*     */       out.println(padding + "</dict>");
/*     */     } else if (value instanceof Map) {
/*     */       Map map = (Map)value;
/*     */       printValue(out, indentLevel, new MapConfiguration(map));
/*     */     } else if (value instanceof byte[]) {
/*     */       String base64 = new String(Base64.encodeBase64((byte[])value));
/*     */       out.println(padding + "<data>" + StringEscapeUtils.escapeXml(base64) + "</data>");
/*     */     } else if (value != null) {
/*     */       out.println(padding + "<string>" + StringEscapeUtils.escapeXml(String.valueOf(value)) + "</string>");
/*     */     } else {
/*     */       out.println(padding + "<string/>");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initRoot() {
/*     */     setRootNode((ConfigurationNode)new PListNode());
/*     */   }
/*     */   
/*     */   private static class XMLPropertyListHandler extends DefaultHandler {
/*     */     private StringBuffer buffer = new StringBuffer();
/*     */     
/*     */     private List stack = new ArrayList();
/*     */     
/*     */     public XMLPropertyListHandler(HierarchicalConfiguration.Node root) {
/*     */       push(root);
/*     */     }
/*     */     
/*     */     private HierarchicalConfiguration.Node peek() {
/*     */       if (!this.stack.isEmpty())
/*     */         return this.stack.get(this.stack.size() - 1); 
/*     */       return null;
/*     */     }
/*     */     
/*     */     private HierarchicalConfiguration.Node pop() {
/*     */       if (!this.stack.isEmpty())
/*     */         return this.stack.remove(this.stack.size() - 1); 
/*     */       return null;
/*     */     }
/*     */     
/*     */     private void push(HierarchicalConfiguration.Node node) {
/*     */       this.stack.add(node);
/*     */     }
/*     */     
/*     */     public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*     */       if ("array".equals(qName)) {
/*     */         push(new XMLPropertyListConfiguration.ArrayNode());
/*     */       } else if ("dict".equals(qName)) {
/*     */         if (peek() instanceof XMLPropertyListConfiguration.ArrayNode) {
/*     */           XMLPropertyListConfiguration config = new XMLPropertyListConfiguration();
/*     */           XMLPropertyListConfiguration.ArrayNode node = (XMLPropertyListConfiguration.ArrayNode)peek();
/*     */           node.addValue(config);
/*     */           push(config.getRoot());
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void endElement(String uri, String localName, String qName) throws SAXException {
/*     */       if ("key".equals(qName)) {
/*     */         XMLPropertyListConfiguration.PListNode node = new XMLPropertyListConfiguration.PListNode();
/*     */         node.setName(this.buffer.toString());
/*     */         peek().addChild(node);
/*     */         push(node);
/*     */       } else if ("dict".equals(qName)) {
/*     */         pop();
/*     */       } else {
/*     */         if ("string".equals(qName)) {
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addValue(this.buffer.toString());
/*     */         } else if ("integer".equals(qName)) {
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addIntegerValue(this.buffer.toString());
/*     */         } else if ("real".equals(qName)) {
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addRealValue(this.buffer.toString());
/*     */         } else if ("true".equals(qName)) {
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addTrueValue();
/*     */         } else if ("false".equals(qName)) {
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addFalseValue();
/*     */         } else if ("data".equals(qName)) {
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addDataValue(this.buffer.toString());
/*     */         } else if ("date".equals(qName)) {
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addDateValue(this.buffer.toString());
/*     */         } else if ("array".equals(qName)) {
/*     */           XMLPropertyListConfiguration.ArrayNode array = (XMLPropertyListConfiguration.ArrayNode)pop();
/*     */           ((XMLPropertyListConfiguration.PListNode)peek()).addList(array);
/*     */         } 
/*     */         if (!(peek() instanceof XMLPropertyListConfiguration.ArrayNode))
/*     */           pop(); 
/*     */       } 
/*     */       this.buffer.setLength(0);
/*     */     }
/*     */     
/*     */     public void characters(char[] ch, int start, int length) throws SAXException {
/*     */       this.buffer.append(ch, start, length);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PListNode extends HierarchicalConfiguration.Node {
/*     */     private static final long serialVersionUID = -7614060264754798317L;
/*     */     
/* 592 */     private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
/*     */     
/*     */     static {
/* 595 */       format.setTimeZone(TimeZone.getTimeZone("UTC"));
/*     */     }
/*     */     
/* 599 */     private static DateFormat gnustepFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*     */     
/*     */     public void addValue(Object value) {
/* 611 */       if (getValue() == null) {
/* 613 */         setValue(value);
/* 615 */       } else if (getValue() instanceof Collection) {
/* 617 */         Collection collection = (Collection)getValue();
/* 618 */         collection.add(value);
/*     */       } else {
/* 622 */         List list = new ArrayList();
/* 623 */         list.add(getValue());
/* 624 */         list.add(value);
/* 625 */         setValue(list);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void addDateValue(String value) {
/*     */       try {
/* 638 */         if (value.indexOf(' ') != -1) {
/* 641 */           synchronized (gnustepFormat) {
/* 643 */             addValue(gnustepFormat.parse(value));
/*     */           } 
/*     */         } else {
/* 649 */           synchronized (format) {
/* 651 */             addValue(format.parse(value));
/*     */           } 
/*     */         } 
/* 655 */       } catch (ParseException e) {}
/*     */     }
/*     */     
/*     */     public void addDataValue(String value) {
/* 670 */       addValue(Base64.decodeBase64(value.getBytes()));
/*     */     }
/*     */     
/*     */     public void addIntegerValue(String value) {
/* 680 */       addValue(new BigInteger(value));
/*     */     }
/*     */     
/*     */     public void addRealValue(String value) {
/* 690 */       addValue(new BigDecimal(value));
/*     */     }
/*     */     
/*     */     public void addTrueValue() {
/* 698 */       addValue(Boolean.TRUE);
/*     */     }
/*     */     
/*     */     public void addFalseValue() {
/* 706 */       addValue(Boolean.FALSE);
/*     */     }
/*     */     
/*     */     public void addList(XMLPropertyListConfiguration.ArrayNode node) {
/* 716 */       addValue(node.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayNode extends PListNode {
/*     */     private static final long serialVersionUID = 5586544306664205835L;
/*     */     
/* 733 */     private List list = new ArrayList();
/*     */     
/*     */     public void addValue(Object value) {
/* 742 */       this.list.add(value);
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 752 */       return this.list;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\plist\XMLPropertyListConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */