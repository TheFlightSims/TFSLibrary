/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ public abstract class ConfigurationXMLReader implements XMLReader {
/*     */   protected static final String NS_URI = "";
/*     */   
/*     */   private static final String DEFAULT_ROOT_NAME = "config";
/*     */   
/*  52 */   private static final Attributes EMPTY_ATTRS = new AttributesImpl();
/*     */   
/*     */   private ContentHandler contentHandler;
/*     */   
/*     */   private SAXException exception;
/*     */   
/*     */   private String rootName;
/*     */   
/*     */   protected ConfigurationXMLReader() {
/*  69 */     setRootName("config");
/*     */   }
/*     */   
/*     */   public void parse(String systemId) throws IOException, SAXException {
/*  82 */     parseConfiguration();
/*     */   }
/*     */   
/*     */   public void parse(InputSource input) throws IOException, SAXException {
/*  95 */     parseConfiguration();
/*     */   }
/*     */   
/*     */   public boolean getFeature(String name) {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   public void setFeature(String name, boolean value) {}
/*     */   
/*     */   public ContentHandler getContentHandler() {
/* 126 */     return this.contentHandler;
/*     */   }
/*     */   
/*     */   public void setContentHandler(ContentHandler handler) {
/* 137 */     this.contentHandler = handler;
/*     */   }
/*     */   
/*     */   public DTDHandler getDTDHandler() {
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public void setDTDHandler(DTDHandler handler) {}
/*     */   
/*     */   public EntityResolver getEntityResolver() {
/* 168 */     return null;
/*     */   }
/*     */   
/*     */   public void setEntityResolver(EntityResolver resolver) {}
/*     */   
/*     */   public ErrorHandler getErrorHandler() {
/* 188 */     return null;
/*     */   }
/*     */   
/*     */   public void setErrorHandler(ErrorHandler handler) {}
/*     */   
/*     */   public Object getProperty(String name) {
/* 209 */     return null;
/*     */   }
/*     */   
/*     */   public void setProperty(String name, Object value) {}
/*     */   
/*     */   public String getRootName() {
/* 230 */     return this.rootName;
/*     */   }
/*     */   
/*     */   public void setRootName(String string) {
/* 240 */     this.rootName = string;
/*     */   }
/*     */   
/*     */   protected void fireElementStart(String name, Attributes attribs) {
/* 251 */     if (getException() == null)
/*     */       try {
/* 255 */         Attributes at = (attribs == null) ? EMPTY_ATTRS : attribs;
/* 256 */         getContentHandler().startElement("", name, name, at);
/* 258 */       } catch (SAXException ex) {
/* 260 */         this.exception = ex;
/*     */       }  
/*     */   }
/*     */   
/*     */   protected void fireElementEnd(String name) {
/* 272 */     if (getException() == null)
/*     */       try {
/* 276 */         getContentHandler().endElement("", name, name);
/* 278 */       } catch (SAXException ex) {
/* 280 */         this.exception = ex;
/*     */       }  
/*     */   }
/*     */   
/*     */   protected void fireCharacters(String text) {
/* 292 */     if (getException() == null)
/*     */       try {
/* 296 */         char[] ch = text.toCharArray();
/* 297 */         getContentHandler().characters(ch, 0, ch.length);
/* 299 */       } catch (SAXException ex) {
/* 301 */         this.exception = ex;
/*     */       }  
/*     */   }
/*     */   
/*     */   public SAXException getException() {
/* 313 */     return this.exception;
/*     */   }
/*     */   
/*     */   protected void parseConfiguration() throws IOException, SAXException {
/* 325 */     if (getParsedConfiguration() == null)
/* 327 */       throw new IOException("No configuration specified!"); 
/* 330 */     if (getContentHandler() != null) {
/* 332 */       this.exception = null;
/* 333 */       getContentHandler().startDocument();
/* 334 */       processKeys();
/* 335 */       if (getException() != null)
/* 337 */         throw getException(); 
/* 339 */       getContentHandler().endDocument();
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract Configuration getParsedConfiguration();
/*     */   
/*     */   protected abstract void processKeys() throws IOException, SAXException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationXMLReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */