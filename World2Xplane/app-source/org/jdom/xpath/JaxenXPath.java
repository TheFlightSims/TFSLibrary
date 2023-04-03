/*     */ package org.jdom.xpath;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.NamespaceContext;
/*     */ import org.jaxen.SimpleNamespaceContext;
/*     */ import org.jaxen.SimpleVariableContext;
/*     */ import org.jaxen.jdom.JDOMXPath;
/*     */ import org.jdom.Attribute;
/*     */ import org.jdom.Content;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.JDOMException;
/*     */ import org.jdom.Namespace;
/*     */ 
/*     */ class JaxenXPath extends XPath {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: JaxenXPath.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:02 $ $Name:  $";
/*     */   
/*     */   private transient JDOMXPath xPath;
/*     */   
/*     */   private Object currentContext;
/*     */   
/*     */   public JaxenXPath(String expr) throws JDOMException {
/*  99 */     setXPath(expr);
/*     */   }
/*     */   
/*     */   public List selectNodes(Object context) throws JDOMException {
/*     */     try {
/* 120 */       this.currentContext = context;
/* 122 */       return this.xPath.selectNodes(context);
/* 124 */     } catch (JaxenException ex1) {
/* 125 */       throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), ex1);
/*     */     } finally {
/* 129 */       this.currentContext = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object selectSingleNode(Object context) throws JDOMException {
/*     */     try {
/* 151 */       this.currentContext = context;
/* 153 */       return this.xPath.selectSingleNode(context);
/* 155 */     } catch (JaxenException ex1) {
/* 156 */       throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), ex1);
/*     */     } finally {
/* 160 */       this.currentContext = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String valueOf(Object context) throws JDOMException {
/*     */     try {
/* 180 */       this.currentContext = context;
/* 182 */       return this.xPath.stringValueOf(context);
/* 184 */     } catch (JaxenException ex1) {
/* 185 */       throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), ex1);
/*     */     } finally {
/* 189 */       this.currentContext = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number numberValueOf(Object context) throws JDOMException {
/*     */     try {
/* 213 */       this.currentContext = context;
/* 215 */       return this.xPath.numberValueOf(context);
/* 217 */     } catch (JaxenException ex1) {
/* 218 */       throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), ex1);
/*     */     } finally {
/* 222 */       this.currentContext = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setVariable(String name, Object value) throws IllegalArgumentException {
/* 240 */     Object o = this.xPath.getVariableContext();
/* 241 */     if (o instanceof SimpleVariableContext)
/* 242 */       ((SimpleVariableContext)o).setVariableValue(null, name, value); 
/*     */   }
/*     */   
/*     */   public void addNamespace(Namespace namespace) {
/*     */     try {
/* 258 */       this.xPath.addNamespace(namespace.getPrefix(), namespace.getURI());
/* 260 */     } catch (JaxenException ex1) {}
/*     */   }
/*     */   
/*     */   public String getXPath() {
/* 269 */     return this.xPath.toString();
/*     */   }
/*     */   
/*     */   private void setXPath(String expr) throws JDOMException {
/*     */     try {
/* 281 */       this.xPath = new JDOMXPath(expr);
/* 282 */       this.xPath.setNamespaceContext((NamespaceContext)new NSContext(this));
/* 284 */     } catch (Exception ex1) {
/* 285 */       throw new JDOMException("Invalid XPath expression: \"" + expr + "\"", ex1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 291 */     return this.xPath.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 295 */     if (o instanceof JaxenXPath) {
/* 296 */       JaxenXPath x = (JaxenXPath)o;
/* 298 */       return (super.equals(o) && this.xPath.toString().equals(x.xPath.toString()));
/*     */     } 
/* 301 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 305 */     return this.xPath.hashCode();
/*     */   }
/*     */   
/*     */   private class NSContext extends SimpleNamespaceContext {
/*     */     private final JaxenXPath this$0;
/*     */     
/*     */     public NSContext(JaxenXPath this$0) {
/* 309 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public String translateNamespacePrefixToUri(String prefix) {
/* 323 */       if (prefix == null || prefix.length() == 0)
/* 324 */         return null; 
/* 327 */       String uri = super.translateNamespacePrefixToUri(prefix);
/* 328 */       if (uri == null) {
/* 329 */         Object ctx = this.this$0.currentContext;
/* 330 */         if (ctx != null) {
/* 331 */           Element elt = null;
/* 334 */           if (ctx instanceof Element) {
/* 335 */             elt = (Element)ctx;
/* 336 */           } else if (ctx instanceof Attribute) {
/* 337 */             elt = ((Attribute)ctx).getParent();
/* 338 */           } else if (ctx instanceof Content) {
/* 339 */             elt = ((Content)ctx).getParentElement();
/* 340 */           } else if (ctx instanceof Document) {
/* 341 */             elt = ((Document)ctx).getRootElement();
/*     */           } 
/* 344 */           if (elt != null) {
/* 345 */             Namespace ns = elt.getNamespace(prefix);
/* 346 */             if (ns != null)
/* 347 */               uri = ns.getURI(); 
/*     */           } 
/*     */         } 
/*     */       } 
/* 352 */       return uri;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\xpath\JaxenXPath.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */