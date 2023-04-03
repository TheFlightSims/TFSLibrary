/*     */ package org.apache.commons.digester.parser;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Properties;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ 
/*     */ public class XercesParser {
/*  49 */   protected static Log log = LogFactory.getLog("org.apache.commons.digester.Digester.sax");
/*     */   
/*     */   private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
/*     */   
/*  63 */   protected static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
/*     */   
/*  70 */   protected static String XERCES_DYNAMIC = "http://apache.org/xml/features/validation/dynamic";
/*     */   
/*  77 */   protected static String XERCES_SCHEMA = "http://apache.org/xml/features/validation/schema";
/*     */   
/*     */   protected static float version;
/*     */   
/*  90 */   protected static String versionNumber = null;
/*     */   
/*     */   private static String getXercesVersion() {
/*  99 */     String versionNumber = "1.0";
/*     */     try {
/* 102 */       Class versionClass = Class.forName("org.apache.xerces.impl.Version");
/* 105 */       Method method = versionClass.getMethod("getVersion", (Class[])null);
/* 107 */       String version = (String)method.invoke(null, (Object[])null);
/* 108 */       versionNumber = version.substring("Xerces-J".length(), version.lastIndexOf("."));
/* 110 */     } catch (Exception ex) {}
/* 113 */     return versionNumber;
/*     */   }
/*     */   
/*     */   public static SAXParser newSAXParser(Properties properties) throws ParserConfigurationException, SAXException, SAXNotSupportedException {
/* 128 */     SAXParserFactory factory = (SAXParserFactory)properties.get("SAXParserFactory");
/* 131 */     if (versionNumber == null) {
/* 132 */       versionNumber = getXercesVersion();
/* 133 */       version = (new Float(versionNumber)).floatValue();
/*     */     } 
/* 137 */     if (version > 2.1D) {
/* 139 */       configureXerces(factory);
/* 140 */       return factory.newSAXParser();
/*     */     } 
/* 142 */     SAXParser parser = factory.newSAXParser();
/* 143 */     configureOldXerces(parser, properties);
/* 144 */     return parser;
/*     */   }
/*     */   
/*     */   private static void configureOldXerces(SAXParser parser, Properties properties) throws ParserConfigurationException, SAXNotSupportedException {
/* 160 */     String schemaLocation = (String)properties.get("schemaLocation");
/* 161 */     String schemaLanguage = (String)properties.get("schemaLanguage");
/*     */     try {
/* 164 */       if (schemaLocation != null) {
/* 165 */         parser.setProperty(JAXP_SCHEMA_LANGUAGE, schemaLanguage);
/* 166 */         parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", schemaLocation);
/*     */       } 
/* 168 */     } catch (SAXNotRecognizedException e) {
/* 169 */       log.info(parser.getClass().getName() + ": " + e.getMessage() + " not supported.");
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void configureXerces(SAXParserFactory factory) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
/* 191 */     factory.setFeature(XERCES_DYNAMIC, true);
/* 192 */     factory.setFeature(XERCES_SCHEMA, true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\parser\XercesParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */