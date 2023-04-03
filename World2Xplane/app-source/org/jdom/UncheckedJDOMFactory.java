/*     */ package org.jdom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class UncheckedJDOMFactory implements JDOMFactory {
/*     */   public Element element(String name, Namespace namespace) {
/*  75 */     Element e = new Element();
/*  76 */     e.name = name;
/*  77 */     if (namespace == null)
/*  78 */       namespace = Namespace.NO_NAMESPACE; 
/*  80 */     e.namespace = namespace;
/*  81 */     return e;
/*     */   }
/*     */   
/*     */   public Element element(String name) {
/*  85 */     Element e = new Element();
/*  86 */     e.name = name;
/*  87 */     e.namespace = Namespace.NO_NAMESPACE;
/*  88 */     return e;
/*     */   }
/*     */   
/*     */   public Element element(String name, String uri) {
/*  92 */     return element(name, Namespace.getNamespace("", uri));
/*     */   }
/*     */   
/*     */   public Element element(String name, String prefix, String uri) {
/*  96 */     return element(name, Namespace.getNamespace(prefix, uri));
/*     */   }
/*     */   
/*     */   public Attribute attribute(String name, String value, Namespace namespace) {
/* 104 */     Attribute a = new Attribute();
/* 105 */     a.name = name;
/* 106 */     a.value = value;
/* 107 */     if (namespace == null)
/* 108 */       namespace = Namespace.NO_NAMESPACE; 
/* 110 */     a.namespace = namespace;
/* 111 */     return a;
/*     */   }
/*     */   
/*     */   public Attribute attribute(String name, String value, int type, Namespace namespace) {
/* 115 */     Attribute a = new Attribute();
/* 116 */     a.name = name;
/* 117 */     a.type = type;
/* 118 */     a.value = value;
/* 119 */     if (namespace == null)
/* 120 */       namespace = Namespace.NO_NAMESPACE; 
/* 122 */     a.namespace = namespace;
/* 123 */     return a;
/*     */   }
/*     */   
/*     */   public Attribute attribute(String name, String value) {
/* 127 */     Attribute a = new Attribute();
/* 128 */     a.name = name;
/* 129 */     a.value = value;
/* 130 */     a.namespace = Namespace.NO_NAMESPACE;
/* 131 */     return a;
/*     */   }
/*     */   
/*     */   public Attribute attribute(String name, String value, int type) {
/* 135 */     Attribute a = new Attribute();
/* 136 */     a.name = name;
/* 137 */     a.type = type;
/* 138 */     a.value = value;
/* 139 */     a.namespace = Namespace.NO_NAMESPACE;
/* 140 */     return a;
/*     */   }
/*     */   
/*     */   public Text text(String str) {
/* 148 */     Text t = new Text();
/* 149 */     t.value = str;
/* 150 */     return t;
/*     */   }
/*     */   
/*     */   public CDATA cdata(String str) {
/* 158 */     CDATA c = new CDATA();
/* 159 */     c.value = str;
/* 160 */     return c;
/*     */   }
/*     */   
/*     */   public Comment comment(String str) {
/* 168 */     Comment c = new Comment();
/* 169 */     c.text = str;
/* 170 */     return c;
/*     */   }
/*     */   
/*     */   public ProcessingInstruction processingInstruction(String target, Map data) {
/* 178 */     ProcessingInstruction p = new ProcessingInstruction();
/* 179 */     p.target = target;
/* 180 */     p.setData(data);
/* 181 */     return p;
/*     */   }
/*     */   
/*     */   public ProcessingInstruction processingInstruction(String target, String data) {
/* 185 */     ProcessingInstruction p = new ProcessingInstruction();
/* 186 */     p.target = target;
/* 187 */     p.setData(data);
/* 188 */     return p;
/*     */   }
/*     */   
/*     */   public EntityRef entityRef(String name) {
/* 196 */     EntityRef e = new EntityRef();
/* 197 */     e.name = name;
/* 198 */     return e;
/*     */   }
/*     */   
/*     */   public EntityRef entityRef(String name, String systemID) {
/* 202 */     EntityRef e = new EntityRef();
/* 203 */     e.name = name;
/* 204 */     e.systemID = systemID;
/* 205 */     return e;
/*     */   }
/*     */   
/*     */   public EntityRef entityRef(String name, String publicID, String systemID) {
/* 209 */     EntityRef e = new EntityRef();
/* 210 */     e.name = name;
/* 211 */     e.publicID = publicID;
/* 212 */     e.systemID = systemID;
/* 213 */     return e;
/*     */   }
/*     */   
/*     */   public DocType docType(String elementName, String publicID, String systemID) {
/* 221 */     DocType d = new DocType();
/* 222 */     d.elementName = elementName;
/* 223 */     d.publicID = publicID;
/* 224 */     d.systemID = systemID;
/* 225 */     return d;
/*     */   }
/*     */   
/*     */   public DocType docType(String elementName, String systemID) {
/* 229 */     return docType(elementName, null, systemID);
/*     */   }
/*     */   
/*     */   public DocType docType(String elementName) {
/* 233 */     return docType(elementName, null, null);
/*     */   }
/*     */   
/*     */   public Document document(Element rootElement, DocType docType, String baseURI) {
/* 241 */     Document d = new Document();
/* 242 */     if (docType != null)
/* 243 */       addContent(d, docType); 
/* 245 */     if (rootElement != null)
/* 246 */       addContent(d, rootElement); 
/* 248 */     if (baseURI != null)
/* 249 */       d.baseURI = baseURI; 
/* 251 */     return d;
/*     */   }
/*     */   
/*     */   public Document document(Element rootElement, DocType docType) {
/* 255 */     return document(rootElement, docType, null);
/*     */   }
/*     */   
/*     */   public Document document(Element rootElement) {
/* 259 */     return document(rootElement, null, null);
/*     */   }
/*     */   
/*     */   public void addContent(Parent parent, Content child) {
/* 267 */     if (parent instanceof Element) {
/* 268 */       Element elt = (Element)parent;
/* 269 */       elt.content.uncheckedAddContent(child);
/*     */     } else {
/* 272 */       Document doc = (Document)parent;
/* 273 */       doc.content.uncheckedAddContent(child);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAttribute(Element parent, Attribute a) {
/* 278 */     parent.attributes.uncheckedAddAttribute(a);
/*     */   }
/*     */   
/*     */   public void addNamespaceDeclaration(Element parent, Namespace additional) {
/* 282 */     if (parent.additionalNamespaces == null)
/* 283 */       parent.additionalNamespaces = new ArrayList(5); 
/* 285 */     parent.additionalNamespaces.add(additional);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\UncheckedJDOMFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */