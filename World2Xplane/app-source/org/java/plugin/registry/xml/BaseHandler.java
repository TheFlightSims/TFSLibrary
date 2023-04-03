/*    */ package org.java.plugin.registry.xml;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.xml.sax.EntityResolver;
/*    */ import org.xml.sax.InputSource;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.SAXParseException;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ 
/*    */ abstract class BaseHandler extends DefaultHandler {
/* 37 */   protected final Log log = LogFactory.getLog(getClass());
/*    */   
/*    */   protected final EntityResolver entityResolver;
/*    */   
/*    */   BaseHandler(EntityResolver anEntityResolver) {
/* 41 */     this.entityResolver = anEntityResolver;
/*    */   }
/*    */   
/*    */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/* 51 */     if (this.entityResolver != null)
/*    */       try {
/* 53 */         return this.entityResolver.resolveEntity(publicId, systemId);
/* 54 */       } catch (SAXException se) {
/* 55 */         throw se;
/* 56 */       } catch (IOException ioe) {
/* 57 */         throw new SAXException("I/O error has occurred - " + ioe, ioe);
/*    */       }  
/* 60 */     this.log.warn("ignoring publicId=" + publicId + " and systemId=" + systemId);
/* 62 */     return null;
/*    */   }
/*    */   
/*    */   public void warning(SAXParseException e) {
/* 70 */     this.log.warn("non-fatal error while parsing XML document", e);
/*    */   }
/*    */   
/*    */   public void error(SAXParseException e) throws SAXException {
/* 78 */     if (this.entityResolver != null) {
/* 80 */       this.log.error("failed parsing XML resource in validating mode", e);
/* 81 */       throw e;
/*    */     } 
/* 83 */     this.log.warn("ignoring parse error", e);
/*    */   }
/*    */   
/*    */   public void fatalError(SAXParseException e) throws SAXException {
/* 91 */     this.log.fatal("failed parsing XML resource", e);
/* 92 */     throw e;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\BaseHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */