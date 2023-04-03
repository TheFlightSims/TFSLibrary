/*     */ package org.jdom.input;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import org.jdom.Attribute;
/*     */ import org.jdom.Content;
/*     */ import org.jdom.DefaultJDOMFactory;
/*     */ import org.jdom.DocType;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.EntityRef;
/*     */ import org.jdom.JDOMFactory;
/*     */ import org.jdom.Namespace;
/*     */ import org.jdom.Parent;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMBuilder {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: DOMBuilder.java,v $ $Revision: 1.60 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*     */   
/*     */   private String adapterClass;
/*     */   
/*  90 */   private JDOMFactory factory = (JDOMFactory)new DefaultJDOMFactory();
/*     */   
/*     */   public DOMBuilder() {}
/*     */   
/*     */   public DOMBuilder(String adapterClass) {
/* 109 */     this.adapterClass = adapterClass;
/*     */   }
/*     */   
/*     */   public void setFactory(JDOMFactory factory) {
/* 119 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public JDOMFactory getFactory() {
/* 127 */     return this.factory;
/*     */   }
/*     */   
/*     */   public Document build(Document domDocument) {
/* 137 */     Document doc = this.factory.document(null);
/* 138 */     buildTree(domDocument, doc, null, true);
/* 139 */     return doc;
/*     */   }
/*     */   
/*     */   public Element build(Element domElement) {
/* 149 */     Document doc = this.factory.document(null);
/* 150 */     buildTree(domElement, doc, null, true);
/* 151 */     return doc.getRootElement();
/*     */   }
/*     */   
/*     */   private void buildTree(Node node, Document doc, Element current, boolean atRoot) {
/*     */     NodeList nodes;
/*     */     int i;
/*     */     String nodeName;
/*     */     int size;
/*     */     String prefix;
/*     */     String localName;
/*     */     int colon;
/*     */     Namespace ns;
/*     */     String uri;
/*     */     Element element;
/*     */     NamedNodeMap attributeList;
/*     */     int attsize;
/*     */     int j;
/*     */     NodeList children;
/*     */     String data;
/*     */     String cdata;
/*     */     EntityRef entity;
/*     */     DocumentType domDocType;
/*     */     String publicID;
/*     */     String systemID;
/*     */     String internalDTD;
/*     */     DocType docType;
/* 169 */     switch (node.getNodeType()) {
/*     */       case 9:
/* 171 */         nodes = node.getChildNodes();
/* 172 */         for (i = 0, size = nodes.getLength(); i < size; i++)
/* 173 */           buildTree(nodes.item(i), doc, current, true); 
/*     */         break;
/*     */       case 1:
/* 178 */         nodeName = node.getNodeName();
/* 179 */         prefix = "";
/* 180 */         localName = nodeName;
/* 181 */         colon = nodeName.indexOf(':');
/* 182 */         if (colon >= 0) {
/* 183 */           prefix = nodeName.substring(0, colon);
/* 184 */           localName = nodeName.substring(colon + 1);
/*     */         } 
/* 188 */         ns = null;
/* 189 */         uri = node.getNamespaceURI();
/* 190 */         if (uri == null) {
/* 191 */           ns = (current == null) ? Namespace.NO_NAMESPACE : current.getNamespace(prefix);
/*     */         } else {
/* 195 */           ns = Namespace.getNamespace(prefix, uri);
/*     */         } 
/* 198 */         element = this.factory.element(localName, ns);
/* 200 */         if (atRoot) {
/* 202 */           doc.setRootElement(element);
/*     */         } else {
/* 205 */           this.factory.addContent((Parent)current, (Content)element);
/*     */         } 
/* 209 */         attributeList = node.getAttributes();
/* 210 */         attsize = attributeList.getLength();
/* 212 */         for (j = 0; j < attsize; j++) {
/* 213 */           Attr att = (Attr)attributeList.item(j);
/* 215 */           String attname = att.getName();
/* 216 */           if (attname.startsWith("xmlns")) {
/* 217 */             String attPrefix = "";
/* 218 */             colon = attname.indexOf(':');
/* 219 */             if (colon >= 0)
/* 220 */               attPrefix = attname.substring(colon + 1); 
/* 223 */             String attvalue = att.getValue();
/* 225 */             Namespace declaredNS = Namespace.getNamespace(attPrefix, attvalue);
/* 233 */             if (prefix.equals(attPrefix)) {
/* 238 */               element.setNamespace(declaredNS);
/*     */             } else {
/* 241 */               this.factory.addNamespaceDeclaration(element, declaredNS);
/*     */             } 
/*     */           } 
/*     */         } 
/* 247 */         for (j = 0; j < attsize; j++) {
/* 248 */           Attr att = (Attr)attributeList.item(j);
/* 250 */           String attname = att.getName();
/* 252 */           if (!attname.startsWith("xmlns")) {
/* 253 */             String attPrefix = "";
/* 254 */             String attLocalName = attname;
/* 255 */             colon = attname.indexOf(':');
/* 256 */             if (colon >= 0) {
/* 257 */               attPrefix = attname.substring(0, colon);
/* 258 */               attLocalName = attname.substring(colon + 1);
/*     */             } 
/* 261 */             String attvalue = att.getValue();
/* 264 */             Namespace attNS = null;
/* 265 */             String attURI = att.getNamespaceURI();
/* 266 */             if (attURI == null || "".equals(attURI)) {
/* 267 */               attNS = Namespace.NO_NAMESPACE;
/* 278 */             } else if (attPrefix.length() > 0) {
/* 285 */               attNS = Namespace.getNamespace(attPrefix, attURI);
/*     */             } else {
/* 295 */               HashSet overrides = new HashSet();
/* 296 */               Element p = element;
/*     */               label114: do {
/* 300 */                 if (p.getNamespace().getURI().equals(attURI) && !overrides.contains(p.getNamespacePrefix()) && !"".equals(element.getNamespace().getPrefix())) {
/* 305 */                   attNS = p.getNamespace();
/*     */                   break;
/*     */                 } 
/* 308 */                 overrides.add(p.getNamespacePrefix());
/* 309 */                 Iterator it = p.getAdditionalNamespaces().iterator();
/* 310 */                 while (it.hasNext()) {
/* 311 */                   Namespace tns = it.next();
/* 312 */                   if (!overrides.contains(tns.getPrefix()) && attURI.equals(tns.getURI())) {
/* 314 */                     attNS = tns;
/*     */                     break label114;
/*     */                   } 
/* 317 */                   overrides.add(tns.getPrefix());
/*     */                 } 
/* 319 */                 p = p.getParentElement();
/* 320 */               } while (p != null);
/* 321 */               if (attNS == null) {
/* 333 */                 int cnt = 0;
/* 334 */                 String base = "attns";
/* 335 */                 String pfx = base + cnt;
/* 336 */                 while (overrides.contains(pfx)) {
/* 337 */                   cnt++;
/* 338 */                   pfx = base + cnt;
/*     */                 } 
/* 340 */                 attNS = Namespace.getNamespace(pfx, attURI);
/*     */               } 
/*     */             } 
/* 345 */             Attribute attribute = this.factory.attribute(attLocalName, attvalue, attNS);
/* 347 */             this.factory.setAttribute(element, attribute);
/*     */           } 
/*     */         } 
/* 354 */         children = node.getChildNodes();
/* 355 */         if (children != null) {
/* 356 */           int k = children.getLength();
/* 357 */           for (int m = 0; m < k; m++) {
/* 358 */             Node item = children.item(m);
/* 359 */             if (item != null)
/* 360 */               buildTree(item, doc, element, false); 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 367 */         data = node.getNodeValue();
/* 368 */         this.factory.addContent((Parent)current, (Content)this.factory.text(data));
/*     */         break;
/*     */       case 4:
/* 372 */         cdata = node.getNodeValue();
/* 373 */         this.factory.addContent((Parent)current, (Content)this.factory.cdata(cdata));
/*     */         break;
/*     */       case 7:
/* 378 */         if (atRoot) {
/* 379 */           this.factory.addContent((Parent)doc, (Content)this.factory.processingInstruction(node.getNodeName(), node.getNodeValue()));
/*     */           break;
/*     */         } 
/* 383 */         this.factory.addContent((Parent)current, (Content)this.factory.processingInstruction(node.getNodeName(), node.getNodeValue()));
/*     */         break;
/*     */       case 8:
/* 390 */         if (atRoot) {
/* 391 */           this.factory.addContent((Parent)doc, (Content)this.factory.comment(node.getNodeValue()));
/*     */           break;
/*     */         } 
/* 393 */         this.factory.addContent((Parent)current, (Content)this.factory.comment(node.getNodeValue()));
/*     */         break;
/*     */       case 5:
/* 398 */         entity = this.factory.entityRef(node.getNodeName());
/* 399 */         this.factory.addContent((Parent)current, (Content)entity);
/*     */         break;
/*     */       case 10:
/* 407 */         domDocType = (DocumentType)node;
/* 408 */         publicID = domDocType.getPublicId();
/* 409 */         systemID = domDocType.getSystemId();
/* 410 */         internalDTD = domDocType.getInternalSubset();
/* 412 */         docType = this.factory.docType(domDocType.getName());
/* 413 */         docType.setPublicID(publicID);
/* 414 */         docType.setSystemID(systemID);
/* 415 */         docType.setInternalSubset(internalDTD);
/* 417 */         this.factory.addContent((Parent)doc, (Content)docType);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\input\DOMBuilder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */