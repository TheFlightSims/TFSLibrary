/*     */ package org.jdom.adapters;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.jdom.JDOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ public class CrimsonDOMAdapter extends AbstractDOMAdapter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: CrimsonDOMAdapter.java,v $ $Revision: 1.17 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   public Document getDocument(InputStream in, boolean validate) throws IOException, JDOMException {
/*     */     try {
/*  92 */       Class[] parameterTypes = new Class[2];
/*  93 */       parameterTypes[0] = Class.forName("java.io.InputStream");
/*  94 */       parameterTypes[1] = boolean.class;
/*  96 */       Object[] args = new Object[2];
/*  97 */       args[0] = in;
/*  98 */       args[1] = new Boolean(false);
/* 101 */       Class parserClass = Class.forName("org.apache.crimson.tree.XmlDocument");
/* 102 */       Method createXmlDocument = parserClass.getMethod("createXmlDocument", parameterTypes);
/* 104 */       Document doc = (Document)createXmlDocument.invoke(null, args);
/* 107 */       return doc;
/* 109 */     } catch (InvocationTargetException e) {
/* 110 */       Throwable targetException = e.getTargetException();
/* 111 */       if (targetException instanceof SAXParseException) {
/* 112 */         SAXParseException parseException = (SAXParseException)targetException;
/* 113 */         throw new JDOMException("Error on line " + parseException.getLineNumber() + " of XML document: " + parseException.getMessage(), parseException);
/*     */       } 
/* 115 */       if (targetException instanceof IOException) {
/* 116 */         IOException ioException = (IOException)targetException;
/* 117 */         throw ioException;
/*     */       } 
/* 119 */       throw new JDOMException(targetException.getMessage(), targetException);
/* 121 */     } catch (Exception e) {
/* 122 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Document createDocument() throws JDOMException {
/*     */     try {
/* 136 */       return (Document)Class.forName("org.apache.crimson.tree.XmlDocument").newInstance();
/* 141 */     } catch (Exception e) {
/* 142 */       throw new JDOMException(e.getClass().getName() + ": " + e.getMessage() + " when creating document", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\adapters\CrimsonDOMAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */