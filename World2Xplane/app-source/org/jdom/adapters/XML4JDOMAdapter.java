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
/*     */ public class XML4JDOMAdapter extends AbstractDOMAdapter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: XML4JDOMAdapter.java,v $ $Revision: 1.18 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   public Document getDocument(InputStream in, boolean validate) throws IOException, JDOMException {
/*     */     try {
/* 100 */       Class parserClass = Class.forName("org.apache.xerces.parsers.DOMParser");
/* 101 */       Object parser = parserClass.newInstance();
/* 104 */       Method setFeature = parserClass.getMethod("setFeature", new Class[] { String.class, boolean.class });
/* 108 */       setFeature.invoke(parser, new Object[] { "http://xml.org/sax/features/validation", new Boolean(validate) });
/* 112 */       setFeature.invoke(parser, new Object[] { "http://xml.org/sax/features/namespaces", new Boolean(false) });
/* 116 */       if (validate) {
/* 117 */         Method setErrorHandler = parserClass.getMethod("setErrorHandler", new Class[] { ErrorHandler.class });
/* 120 */         setErrorHandler.invoke(parser, new Object[] { new BuilderErrorHandler() });
/*     */       } 
/* 124 */       Method parse = parserClass.getMethod("parse", new Class[] { InputSource.class });
/* 127 */       parse.invoke(parser, new Object[] { new InputSource(in) });
/* 130 */       Method getDocument = parserClass.getMethod("getDocument", null);
/* 131 */       Document doc = (Document)getDocument.invoke(parser, null);
/* 133 */       return doc;
/* 134 */     } catch (InvocationTargetException e) {
/* 135 */       Throwable targetException = e.getTargetException();
/* 136 */       if (targetException instanceof SAXParseException) {
/* 137 */         SAXParseException parseException = (SAXParseException)targetException;
/* 138 */         throw new JDOMException("Error on line " + parseException.getLineNumber() + " of XML document: " + parseException.getMessage(), parseException);
/*     */       } 
/* 140 */       if (targetException instanceof IOException) {
/* 141 */         IOException ioException = (IOException)targetException;
/* 142 */         throw ioException;
/*     */       } 
/* 144 */       throw new JDOMException(targetException.getMessage(), targetException);
/* 146 */     } catch (Exception e) {
/* 147 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Document createDocument() throws JDOMException {
/*     */     try {
/* 161 */       return (Document)Class.forName("org.apache.xerces.dom.DocumentImpl").newInstance();
/* 166 */     } catch (Exception e) {
/* 167 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage() + " while creating document", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\adapters\XML4JDOMAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */