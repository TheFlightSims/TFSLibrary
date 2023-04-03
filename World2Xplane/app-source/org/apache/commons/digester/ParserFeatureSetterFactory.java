/*    */ package org.apache.commons.digester;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import javax.xml.parsers.ParserConfigurationException;
/*    */ import javax.xml.parsers.SAXParser;
/*    */ import javax.xml.parsers.SAXParserFactory;
/*    */ import org.apache.commons.digester.parser.GenericParser;
/*    */ import org.apache.commons.digester.parser.XercesParser;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.SAXNotRecognizedException;
/*    */ import org.xml.sax.SAXNotSupportedException;
/*    */ 
/*    */ public class ParserFeatureSetterFactory {
/*    */   private static boolean isXercesUsed;
/*    */   
/*    */   static {
/*    */     try {
/* 55 */       SAXParserFactory factory = SAXParserFactory.newInstance();
/* 56 */       if (factory.getClass().getName().startsWith("org.apache.xerces"))
/* 57 */         isXercesUsed = true; 
/* 59 */     } catch (Exception ex) {
/* 60 */       isXercesUsed = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SAXParser newSAXParser(Properties properties) throws ParserConfigurationException, SAXException, SAXNotRecognizedException, SAXNotSupportedException {
/* 76 */     if (isXercesUsed)
/* 77 */       return XercesParser.newSAXParser(properties); 
/* 79 */     return GenericParser.newSAXParser(properties);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\ParserFeatureSetterFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */