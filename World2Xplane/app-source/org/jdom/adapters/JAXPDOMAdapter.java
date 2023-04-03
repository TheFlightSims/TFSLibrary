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
/*     */ 
/*     */ public class JAXPDOMAdapter extends AbstractDOMAdapter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: JAXPDOMAdapter.java,v $ $Revision: 1.13 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   public Document getDocument(InputStream in, boolean validate) throws IOException, JDOMException {
/*     */     try {
/*  95 */       Class.forName("javax.xml.transform.Transformer");
/*  98 */       Class factoryClass = Class.forName("javax.xml.parsers.DocumentBuilderFactory");
/* 102 */       Method newParserInstance = factoryClass.getMethod("newInstance", null);
/* 104 */       Object factory = newParserInstance.invoke(null, null);
/* 107 */       Method setValidating = factoryClass.getMethod("setValidating", new Class[] { boolean.class });
/* 110 */       setValidating.invoke(factory, new Object[] { new Boolean(validate) });
/* 114 */       Method setNamespaceAware = factoryClass.getMethod("setNamespaceAware", new Class[] { boolean.class });
/* 117 */       setNamespaceAware.invoke(factory, new Object[] { Boolean.TRUE });
/* 121 */       Method newDocBuilder = factoryClass.getMethod("newDocumentBuilder", null);
/* 123 */       Object jaxpParser = newDocBuilder.invoke(factory, null);
/* 126 */       Class parserClass = jaxpParser.getClass();
/* 127 */       Method setErrorHandler = parserClass.getMethod("setErrorHandler", new Class[] { ErrorHandler.class });
/* 130 */       setErrorHandler.invoke(jaxpParser, new Object[] { new BuilderErrorHandler() });
/* 134 */       Method parse = parserClass.getMethod("parse", new Class[] { InputStream.class });
/* 136 */       Document domDoc = (Document)parse.invoke(jaxpParser, new Object[] { in });
/* 139 */       return domDoc;
/* 140 */     } catch (InvocationTargetException e) {
/* 141 */       Throwable targetException = e.getTargetException();
/* 142 */       if (targetException instanceof IOException)
/* 143 */         throw (IOException)targetException; 
/* 145 */       throw new JDOMException(targetException.getMessage(), targetException);
/* 147 */     } catch (Exception e) {
/* 148 */       throw new JDOMException("Reflection failed while parsing a document with JAXP", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Document createDocument() throws JDOMException {
/*     */     try {
/* 167 */       Class.forName("javax.xml.transform.Transformer");
/* 170 */       Class factoryClass = Class.forName("javax.xml.parsers.DocumentBuilderFactory");
/* 174 */       Method newParserInstance = factoryClass.getMethod("newInstance", null);
/* 176 */       Object factory = newParserInstance.invoke(null, null);
/* 179 */       Method newDocBuilder = factoryClass.getMethod("newDocumentBuilder", null);
/* 181 */       Object jaxpParser = newDocBuilder.invoke(factory, null);
/* 184 */       Class parserClass = jaxpParser.getClass();
/* 185 */       Method newDoc = parserClass.getMethod("newDocument", null);
/* 186 */       Document domDoc = (Document)newDoc.invoke(jaxpParser, null);
/* 189 */       return domDoc;
/* 190 */     } catch (Exception e) {
/* 191 */       throw new JDOMException("Reflection failed while creating new JAXP document", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\adapters\JAXPDOMAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */