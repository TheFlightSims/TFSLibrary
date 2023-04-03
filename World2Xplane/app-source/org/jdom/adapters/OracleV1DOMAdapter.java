/*     */ package org.jdom.adapters;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.jdom.JDOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ public class OracleV1DOMAdapter extends AbstractDOMAdapter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: OracleV1DOMAdapter.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   public Document getDocument(InputStream in, boolean validate) throws IOException, JDOMException {
/*     */     try {
/*  94 */       Class parserClass = Class.forName("oracle.xml.parser.XMLParser");
/*  95 */       Object parser = parserClass.newInstance();
/*  98 */       Method parse = parserClass.getMethod("parse", new Class[] { InputSource.class });
/* 101 */       parse.invoke(parser, new Object[] { new InputSource(in) });
/* 104 */       Method getDocument = parserClass.getMethod("getDocument", null);
/* 105 */       Document doc = (Document)getDocument.invoke(parser, null);
/* 107 */       return doc;
/* 108 */     } catch (InvocationTargetException e) {
/* 109 */       Throwable targetException = e.getTargetException();
/* 110 */       if (targetException instanceof SAXParseException) {
/* 111 */         SAXParseException parseException = (SAXParseException)targetException;
/* 112 */         throw new JDOMException("Error on line " + parseException.getLineNumber() + " of XML document: " + parseException.getMessage(), parseException);
/*     */       } 
/* 114 */       if (targetException instanceof IOException) {
/* 115 */         IOException ioException = (IOException)targetException;
/* 116 */         throw ioException;
/*     */       } 
/* 118 */       throw new JDOMException(targetException.getMessage(), targetException);
/* 120 */     } catch (Exception e) {
/* 121 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Document createDocument() throws JDOMException {
/*     */     try {
/* 135 */       return (Document)Class.forName("oracle.xml.parser.XMLDocument").newInstance();
/* 140 */     } catch (Exception e) {
/* 141 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage() + " when creating document", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\adapters\OracleV1DOMAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */