/*     */ package org.jdom;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DefaultJDOMFactory implements JDOMFactory {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: DefaultJDOMFactory.java,v $ $Revision: 1.7 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   public Attribute attribute(String name, String value, Namespace namespace) {
/*  80 */     return new Attribute(name, value, namespace);
/*     */   }
/*     */   
/*     */   public Attribute attribute(String name, String value, int type, Namespace namespace) {
/*  85 */     return new Attribute(name, value, type, namespace);
/*     */   }
/*     */   
/*     */   public Attribute attribute(String name, String value) {
/*  89 */     return new Attribute(name, value);
/*     */   }
/*     */   
/*     */   public Attribute attribute(String name, String value, int type) {
/*  93 */     return new Attribute(name, value, type);
/*     */   }
/*     */   
/*     */   public CDATA cdata(String text) {
/*  97 */     return new CDATA(text);
/*     */   }
/*     */   
/*     */   public Text text(String text) {
/* 101 */     return new Text(text);
/*     */   }
/*     */   
/*     */   public Comment comment(String text) {
/* 105 */     return new Comment(text);
/*     */   }
/*     */   
/*     */   public DocType docType(String elementName, String publicID, String systemID) {
/* 110 */     return new DocType(elementName, publicID, systemID);
/*     */   }
/*     */   
/*     */   public DocType docType(String elementName, String systemID) {
/* 114 */     return new DocType(elementName, systemID);
/*     */   }
/*     */   
/*     */   public DocType docType(String elementName) {
/* 118 */     return new DocType(elementName);
/*     */   }
/*     */   
/*     */   public Document document(Element rootElement, DocType docType) {
/* 122 */     return new Document(rootElement, docType);
/*     */   }
/*     */   
/*     */   public Document document(Element rootElement, DocType docType, String baseURI) {
/* 126 */     return new Document(rootElement, docType, baseURI);
/*     */   }
/*     */   
/*     */   public Document document(Element rootElement) {
/* 130 */     return new Document(rootElement);
/*     */   }
/*     */   
/*     */   public Element element(String name, Namespace namespace) {
/* 134 */     return new Element(name, namespace);
/*     */   }
/*     */   
/*     */   public Element element(String name) {
/* 138 */     return new Element(name);
/*     */   }
/*     */   
/*     */   public Element element(String name, String uri) {
/* 142 */     return new Element(name, uri);
/*     */   }
/*     */   
/*     */   public Element element(String name, String prefix, String uri) {
/* 146 */     return new Element(name, prefix, uri);
/*     */   }
/*     */   
/*     */   public ProcessingInstruction processingInstruction(String target, Map data) {
/* 151 */     return new ProcessingInstruction(target, data);
/*     */   }
/*     */   
/*     */   public ProcessingInstruction processingInstruction(String target, String data) {
/* 156 */     return new ProcessingInstruction(target, data);
/*     */   }
/*     */   
/*     */   public EntityRef entityRef(String name) {
/* 160 */     return new EntityRef(name);
/*     */   }
/*     */   
/*     */   public EntityRef entityRef(String name, String publicID, String systemID) {
/* 164 */     return new EntityRef(name, publicID, systemID);
/*     */   }
/*     */   
/*     */   public EntityRef entityRef(String name, String systemID) {
/* 168 */     return new EntityRef(name, systemID);
/*     */   }
/*     */   
/*     */   public void addContent(Parent parent, Content child) {
/* 176 */     if (parent instanceof Document) {
/* 177 */       ((Document)parent).addContent(child);
/*     */     } else {
/* 180 */       ((Element)parent).addContent(child);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAttribute(Element parent, Attribute a) {
/* 185 */     parent.setAttribute(a);
/*     */   }
/*     */   
/*     */   public void addNamespaceDeclaration(Element parent, Namespace additional) {
/* 189 */     parent.addNamespaceDeclaration(additional);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\DefaultJDOMFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */