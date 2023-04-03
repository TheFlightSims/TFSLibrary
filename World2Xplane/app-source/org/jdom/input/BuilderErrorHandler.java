/*     */ package org.jdom.input;
/*     */ 
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ public class BuilderErrorHandler implements ErrorHandler {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: BuilderErrorHandler.java,v $ $Revision: 1.13 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*     */   
/*     */   public void warning(SAXParseException exception) throws SAXException {}
/*     */   
/*     */   public void error(SAXParseException exception) throws SAXException {
/*  96 */     throw exception;
/*     */   }
/*     */   
/*     */   public void fatalError(SAXParseException exception) throws SAXException {
/* 109 */     throw exception;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\input\BuilderErrorHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */