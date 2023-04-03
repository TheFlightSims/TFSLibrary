/*     */ package org.jdom.transform;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.util.List;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.JDOMFactory;
/*     */ import org.xml.sax.EntityResolver;
/*     */ 
/*     */ public class XSLTransformer {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: XSLTransformer.java,v $ $Revision: 1.5 $ $Date: 2007/11/14 04:36:54 $ $Name:  $";
/*     */   
/*     */   private Templates templates;
/*     */   
/* 128 */   private JDOMFactory factory = null;
/*     */   
/*     */   private XSLTransformer(Source stylesheet) throws XSLTransformException {
/*     */     try {
/* 133 */       this.templates = TransformerFactory.newInstance().newTemplates(stylesheet);
/* 136 */     } catch (TransformerException e) {
/* 137 */       throw new XSLTransformException("Could not construct XSLTransformer", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public XSLTransformer(String stylesheetSystemId) throws XSLTransformException {
/* 148 */     this(new StreamSource(stylesheetSystemId));
/*     */   }
/*     */   
/*     */   public XSLTransformer(InputStream stylesheet) throws XSLTransformException {
/* 163 */     this(new StreamSource(stylesheet));
/*     */   }
/*     */   
/*     */   public XSLTransformer(Reader stylesheet) throws XSLTransformException {
/* 178 */     this(new StreamSource(stylesheet));
/*     */   }
/*     */   
/*     */   public XSLTransformer(File stylesheet) throws XSLTransformException {
/* 193 */     this(new StreamSource(stylesheet));
/*     */   }
/*     */   
/*     */   public XSLTransformer(Document stylesheet) throws XSLTransformException {
/* 208 */     this(new JDOMSource(stylesheet));
/*     */   }
/*     */   
/*     */   public List transform(List inputNodes) throws XSLTransformException {
/* 219 */     JDOMSource source = new JDOMSource(inputNodes);
/* 220 */     JDOMResult result = new JDOMResult();
/* 221 */     result.setFactory(this.factory);
/*     */     try {
/* 223 */       this.templates.newTransformer().transform(source, result);
/* 224 */       return result.getResult();
/* 226 */     } catch (TransformerException e) {
/* 227 */       throw new XSLTransformException("Could not perform transformation", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Document transform(Document inputDoc) throws XSLTransformException {
/* 239 */     return transform(inputDoc, null);
/*     */   }
/*     */   
/*     */   public Document transform(Document inputDoc, EntityResolver resolver) throws XSLTransformException {
/* 251 */     JDOMSource source = new JDOMSource(inputDoc, resolver);
/* 252 */     JDOMResult result = new JDOMResult();
/* 253 */     result.setFactory(this.factory);
/*     */     try {
/* 255 */       this.templates.newTransformer().transform(source, result);
/* 256 */       return result.getDocument();
/* 258 */     } catch (TransformerException e) {
/* 259 */       throw new XSLTransformException("Could not perform transformation", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFactory(JDOMFactory factory) {
/* 275 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public JDOMFactory getFactory() {
/* 289 */     return this.factory;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\transform\XSLTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */