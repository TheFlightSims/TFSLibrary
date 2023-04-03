/*    */ package org.apache.commons.digester.parser;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import javax.xml.parsers.ParserConfigurationException;
/*    */ import javax.xml.parsers.SAXParser;
/*    */ import javax.xml.parsers.SAXParserFactory;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.SAXNotRecognizedException;
/*    */ 
/*    */ public class GenericParser {
/* 44 */   protected static Log log = LogFactory.getLog("org.apache.commons.digester.Digester.sax");
/*    */   
/*    */   private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
/*    */   
/* 56 */   protected static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
/*    */   
/*    */   public static SAXParser newSAXParser(Properties properties) throws ParserConfigurationException, SAXException, SAXNotRecognizedException {
/* 69 */     SAXParserFactory factory = (SAXParserFactory)properties.get("SAXParserFactory");
/* 71 */     SAXParser parser = factory.newSAXParser();
/* 72 */     String schemaLocation = (String)properties.get("schemaLocation");
/* 73 */     String schemaLanguage = (String)properties.get("schemaLanguage");
/*    */     try {
/* 76 */       if (schemaLocation != null) {
/* 77 */         parser.setProperty(JAXP_SCHEMA_LANGUAGE, schemaLanguage);
/* 78 */         parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", schemaLocation);
/*    */       } 
/* 80 */     } catch (SAXNotRecognizedException e) {
/* 81 */       log.info(parser.getClass().getName() + ": " + e.getMessage() + " not supported.");
/*    */     } 
/* 84 */     return parser;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\parser\GenericParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */