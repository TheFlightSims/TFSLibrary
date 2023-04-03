/*     */ package org.jdom.adapters;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import org.jdom.DocType;
/*     */ import org.jdom.JDOMException;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ 
/*     */ public abstract class AbstractDOMAdapter implements DOMAdapter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: AbstractDOMAdapter.java,v $ $Revision: 1.21 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   public Document getDocument(File filename, boolean validate) throws IOException, JDOMException {
/*  92 */     return getDocument(new FileInputStream(filename), validate);
/*     */   }
/*     */   
/*     */   public abstract Document getDocument(InputStream paramInputStream, boolean paramBoolean) throws IOException, JDOMException;
/*     */   
/*     */   public abstract Document createDocument() throws JDOMException;
/*     */   
/*     */   public Document createDocument(DocType doctype) throws JDOMException {
/* 129 */     if (doctype == null)
/* 130 */       return createDocument(); 
/* 133 */     DOMImplementation domImpl = createDocument().getImplementation();
/* 134 */     DocumentType domDocType = domImpl.createDocumentType(doctype.getElementName(), doctype.getPublicID(), doctype.getSystemID());
/* 140 */     setInternalSubset(domDocType, doctype.getInternalSubset());
/* 142 */     return domImpl.createDocument("http://temporary", doctype.getElementName(), domDocType);
/*     */   }
/*     */   
/*     */   protected void setInternalSubset(DocumentType dt, String s) {
/* 157 */     if (dt == null || s == null)
/*     */       return; 
/*     */     try {
/* 163 */       Class dtclass = dt.getClass();
/* 164 */       Method setInternalSubset = dtclass.getMethod("setInternalSubset", new Class[] { String.class });
/* 166 */       setInternalSubset.invoke(dt, new Object[] { s });
/* 168 */     } catch (Exception e) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\adapters\AbstractDOMAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */