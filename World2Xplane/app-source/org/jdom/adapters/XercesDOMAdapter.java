/*     */ package org.jdom.adapters;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.jdom.JDOMException;
/*     */ import org.jdom.input.BuilderErrorHandler;
/*     */ import org.w3c.dom.Document;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ public class XercesDOMAdapter extends AbstractDOMAdapter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: XercesDOMAdapter.java,v $ $Revision: 1.19 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   public Document getDocument(InputStream in, boolean validate) throws IOException, JDOMException {
/*     */     try {
/*  96 */       Class parserClass = Class.forName("org.apache.xerces.parsers.DOMParser");
/*  98 */       Object parser = parserClass.newInstance();
/* 101 */       Method setFeature = parserClass.getMethod("setFeature", new Class[] { String.class, boolean.class });
/* 104 */       setFeature.invoke(parser, new Object[] { "http://xml.org/sax/features/validation", new Boolean(validate) });
/* 109 */       setFeature.invoke(parser, new Object[] { "http://xml.org/sax/features/namespaces", new Boolean(true) });
/* 114 */       if (validate) {
/* 115 */         Method setErrorHandler = parserClass.getMethod("setErrorHandler", new Class[] { ErrorHandler.class });
/* 118 */         setErrorHandler.invoke(parser, new Object[] { new BuilderErrorHandler() });
/*     */       } 
/* 123 */       Method parse = parserClass.getMethod("parse", new Class[] { InputSource.class });
/* 126 */       parse.invoke(parser, new Object[] { new InputSource(in) });
/* 129 */       Method getDocument = parserClass.getMethod("getDocument", null);
/* 130 */       Document doc = (Document)getDocument.invoke(parser, null);
/* 132 */       return doc;
/* 133 */     } catch (InvocationTargetException e) {
/* 134 */       Throwable targetException = e.getTargetException();
/* 135 */       if (targetException instanceof SAXParseException) {
/* 136 */         SAXParseException parseException = (SAXParseException)targetException;
/* 138 */         throw new JDOMException("Error on line " + parseException.getLineNumber() + " of XML document: " + parseException.getMessage(), e);
/*     */       } 
/* 142 */       if (targetException instanceof IOException) {
/* 143 */         IOException ioException = (IOException)targetException;
/* 144 */         throw ioException;
/*     */       } 
/* 146 */       throw new JDOMException(targetException.getMessage(), e);
/* 148 */     } catch (Exception e) {
/* 149 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Document createDocument() throws JDOMException {
/*     */     try {
/* 163 */       return (Document)Class.forName("org.apache.xerces.dom.DocumentImpl").newInstance();
/* 165 */     } catch (Exception e) {
/* 166 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage() + " when creating document", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\adapters\XercesDOMAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */