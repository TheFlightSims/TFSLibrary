/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.commons.lang.StringEscapeUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class XMLPropertiesConfiguration extends PropertiesConfiguration {
/*     */   private static final String DEFAULT_ENCODING = "UTF-8";
/*     */   
/*     */   public XMLPropertiesConfiguration() {
/*  78 */     setEncoding("UTF-8");
/*     */   }
/*     */   
/*     */   public XMLPropertiesConfiguration(String fileName) throws ConfigurationException {
/* 103 */     super(fileName);
/*     */     setEncoding("UTF-8");
/*     */   }
/*     */   
/*     */   public XMLPropertiesConfiguration(File file) throws ConfigurationException {
/* 116 */     super(file);
/*     */     setEncoding("UTF-8");
/*     */   }
/*     */   
/*     */   public XMLPropertiesConfiguration(URL url) throws ConfigurationException {
/* 129 */     super(url);
/*     */     setEncoding("UTF-8");
/*     */   }
/*     */   
/*     */   public void load(Reader in) throws ConfigurationException {
/* 134 */     SAXParserFactory factory = SAXParserFactory.newInstance();
/* 135 */     factory.setNamespaceAware(false);
/* 136 */     factory.setValidating(true);
/*     */     try {
/* 140 */       SAXParser parser = factory.newSAXParser();
/* 142 */       XMLReader xmlReader = parser.getXMLReader();
/* 143 */       xmlReader.setEntityResolver(new EntityResolver() {
/*     */             private final XMLPropertiesConfiguration this$0;
/*     */             
/*     */             public InputSource resolveEntity(String publicId, String systemId) {
/* 147 */               return new InputSource(getClass().getClassLoader().getResourceAsStream("properties.dtd"));
/*     */             }
/*     */           });
/* 150 */       xmlReader.setContentHandler(new XMLPropertiesHandler());
/* 151 */       xmlReader.parse(new InputSource(in));
/* 153 */     } catch (Exception e) {
/* 155 */       throw new ConfigurationException("Unable to parse the configuration file", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void save(Writer out) throws ConfigurationException {
/* 163 */     PrintWriter writer = new PrintWriter(out);
/* 165 */     String encoding = (getEncoding() != null) ? getEncoding() : "UTF-8";
/* 166 */     writer.println("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
/* 167 */     writer.println("<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
/* 168 */     writer.println("<properties>");
/* 170 */     if (getHeader() != null)
/* 172 */       writer.println("  <comment>" + StringEscapeUtils.escapeXml(getHeader()) + "</comment>"); 
/* 175 */     Iterator keys = getKeys();
/* 176 */     while (keys.hasNext()) {
/* 178 */       String key = keys.next();
/* 179 */       Object value = getProperty(key);
/* 181 */       if (value instanceof List) {
/* 183 */         writeProperty(writer, key, (List)value);
/*     */         continue;
/*     */       } 
/* 187 */       writeProperty(writer, key, value);
/*     */     } 
/* 191 */     writer.println("</properties>");
/* 192 */     writer.flush();
/*     */   }
/*     */   
/*     */   private void writeProperty(PrintWriter out, String key, Object value) {
/* 205 */     String k = StringEscapeUtils.escapeXml(key);
/* 207 */     if (value != null) {
/* 210 */       String v = StringEscapeUtils.escapeXml(String.valueOf(value));
/* 211 */       v = StringUtils.replace(v, String.valueOf(getListDelimiter()), "\\" + getListDelimiter());
/* 213 */       out.println("  <entry key=\"" + k + "\">" + v + "</entry>");
/*     */     } else {
/* 217 */       out.println("  <entry key=\"" + k + "\"/>");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeProperty(PrintWriter out, String key, List values) {
/* 230 */     for (int i = 0; i < values.size(); i++)
/* 232 */       writeProperty(out, key, values.get(i)); 
/*     */   }
/*     */   
/*     */   private class XMLPropertiesHandler extends DefaultHandler {
/*     */     private String key;
/*     */     
/* 248 */     private StringBuffer value = new StringBuffer();
/*     */     
/*     */     private boolean inCommentElement;
/*     */     
/*     */     private boolean inEntryElement;
/*     */     
/*     */     private final XMLPropertiesConfiguration this$0;
/*     */     
/*     */     public void startElement(String uri, String localName, String qName, Attributes attrs) {
/* 258 */       if ("comment".equals(qName))
/* 260 */         this.inCommentElement = true; 
/* 263 */       if ("entry".equals(qName)) {
/* 265 */         this.key = attrs.getValue("key");
/* 266 */         this.inEntryElement = true;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void endElement(String uri, String localName, String qName) {
/* 272 */       if (this.inCommentElement) {
/* 275 */         XMLPropertiesConfiguration.this.setHeader(this.value.toString());
/* 276 */         this.inCommentElement = false;
/*     */       } 
/* 279 */       if (this.inEntryElement) {
/* 282 */         XMLPropertiesConfiguration.this.addProperty(this.key, this.value.toString());
/* 283 */         this.inEntryElement = false;
/*     */       } 
/* 287 */       this.value = new StringBuffer();
/*     */     }
/*     */     
/*     */     public void characters(char[] chars, int start, int length) {
/* 296 */       this.value.append(chars, start, length);
/*     */     }
/*     */     
/*     */     private XMLPropertiesHandler() {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\XMLPropertiesConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */