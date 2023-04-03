/*     */ package org.jdom.input;
/*     */ 
/*     */ import org.jdom.Document;
/*     */ import org.jdom.JDOMException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ public class JDOMParseException extends JDOMException {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: JDOMParseException.java,v $ $Revision: 1.8 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*     */   
/*     */   private final Document partialDocument;
/*     */   
/*     */   public JDOMParseException(String message, Throwable cause) {
/*  91 */     this(message, cause, null);
/*     */   }
/*     */   
/*     */   public JDOMParseException(String message, Throwable cause, Document partialDocument) {
/* 109 */     super(message, cause);
/* 110 */     this.partialDocument = partialDocument;
/*     */   }
/*     */   
/*     */   public Document getPartialDocument() {
/* 120 */     return this.partialDocument;
/*     */   }
/*     */   
/*     */   public String getPublicId() {
/* 131 */     return (getCause() instanceof SAXParseException) ? ((SAXParseException)getCause()).getPublicId() : null;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/* 143 */     return (getCause() instanceof SAXParseException) ? ((SAXParseException)getCause()).getSystemId() : null;
/*     */   }
/*     */   
/*     */   public int getLineNumber() {
/* 157 */     return (getCause() instanceof SAXParseException) ? ((SAXParseException)getCause()).getLineNumber() : -1;
/*     */   }
/*     */   
/*     */   public int getColumnNumber() {
/* 171 */     return (getCause() instanceof SAXParseException) ? ((SAXParseException)getCause()).getColumnNumber() : -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\input\JDOMParseException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */