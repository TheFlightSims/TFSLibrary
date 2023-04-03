/*     */ package org.jdom.output;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.jdom.Attribute;
/*     */ import org.jdom.CDATA;
/*     */ import org.jdom.Comment;
/*     */ import org.jdom.DocType;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.EntityRef;
/*     */ import org.jdom.JDOMException;
/*     */ import org.jdom.Namespace;
/*     */ import org.jdom.ProcessingInstruction;
/*     */ import org.jdom.Text;
/*     */ import org.jdom.adapters.DOMAdapter;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ public class DOMOutputter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: DOMOutputter.java,v $ $Revision: 1.43 $ $Date: 2007/11/10 05:29:01 $ $Name:  $";
/*     */   
/*     */   private static final String DEFAULT_ADAPTER_CLASS = "org.jdom.adapters.XercesDOMAdapter";
/*     */   
/*     */   private String adapterClass;
/*     */   
/*     */   private boolean forceNamespaceAware;
/*     */   
/*     */   public DOMOutputter() {}
/*     */   
/*     */   public DOMOutputter(String adapterClass) {
/* 110 */     this.adapterClass = adapterClass;
/*     */   }
/*     */   
/*     */   public void setForceNamespaceAware(boolean flag) {
/* 119 */     this.forceNamespaceAware = flag;
/*     */   }
/*     */   
/*     */   public boolean getForceNamespaceAware() {
/* 128 */     return this.forceNamespaceAware;
/*     */   }
/*     */   
/*     */   public Document output(Document document) throws JDOMException {
/* 141 */     NamespaceStack namespaces = new NamespaceStack();
/* 143 */     Document domDoc = null;
/*     */     try {
/* 146 */       DocType dt = document.getDocType();
/* 147 */       domDoc = createDOMDocument(dt);
/* 152 */       Element autoroot = domDoc.getDocumentElement();
/* 153 */       if (autoroot != null)
/* 157 */         domDoc.removeChild(autoroot); 
/* 161 */       Iterator itr = document.getContent().iterator();
/* 162 */       while (itr.hasNext()) {
/* 163 */         Object node = itr.next();
/* 165 */         if (node instanceof Element) {
/* 166 */           Element domElement = output((Element)node, domDoc, namespaces);
/* 168 */           domDoc.appendChild(domElement);
/*     */           continue;
/*     */         } 
/* 170 */         if (node instanceof Comment) {
/* 171 */           Comment comment = (Comment)node;
/* 172 */           Comment domComment = domDoc.createComment(comment.getText());
/* 174 */           domDoc.appendChild(domComment);
/*     */           continue;
/*     */         } 
/* 176 */         if (node instanceof ProcessingInstruction) {
/* 177 */           ProcessingInstruction pi = (ProcessingInstruction)node;
/* 179 */           ProcessingInstruction domPI = domDoc.createProcessingInstruction(pi.getTarget(), pi.getData());
/* 182 */           domDoc.appendChild(domPI);
/*     */           continue;
/*     */         } 
/* 184 */         if (node instanceof DocType)
/*     */           continue; 
/* 188 */         throw new JDOMException("Document contained top-level content with type:" + node.getClass().getName());
/*     */       } 
/* 194 */     } catch (Throwable e) {
/* 195 */       throw new JDOMException("Exception outputting Document", e);
/*     */     } 
/* 198 */     return domDoc;
/*     */   }
/*     */   
/*     */   private Document createDOMDocument(DocType dt) throws JDOMException {
/* 203 */     if (this.adapterClass != null) {
/*     */       try {
/* 206 */         DOMAdapter adapter = (DOMAdapter)Class.forName(this.adapterClass).newInstance();
/* 209 */         return adapter.createDocument(dt);
/* 211 */       } catch (ClassNotFoundException e) {
/*     */       
/* 214 */       } catch (IllegalAccessException e) {
/*     */       
/* 217 */       } catch (InstantiationException e) {}
/*     */     } else {
/*     */       try {
/* 224 */         DOMAdapter adapter = (DOMAdapter)Class.forName("org.jdom.adapters.JAXPDOMAdapter").newInstance();
/* 228 */         return adapter.createDocument(dt);
/* 230 */       } catch (ClassNotFoundException e) {
/*     */       
/* 233 */       } catch (IllegalAccessException e) {
/*     */       
/* 236 */       } catch (InstantiationException e) {}
/*     */     } 
/*     */     try {
/* 243 */       DOMAdapter adapter = (DOMAdapter)Class.forName("org.jdom.adapters.XercesDOMAdapter").newInstance();
/* 245 */       return adapter.createDocument(dt);
/* 249 */     } catch (ClassNotFoundException e) {
/*     */     
/* 252 */     } catch (IllegalAccessException e) {
/*     */     
/* 255 */     } catch (InstantiationException e) {}
/* 259 */     throw new JDOMException("No JAXP or default parser available");
/*     */   }
/*     */   
/*     */   private Element output(Element element, Document domDoc, NamespaceStack namespaces) throws JDOMException {
/*     */     try {
/* 268 */       int previouslyDeclaredNamespaces = namespaces.size();
/* 270 */       Element domElement = null;
/* 271 */       if (element.getNamespace() == Namespace.NO_NAMESPACE) {
/* 273 */         domElement = this.forceNamespaceAware ? domDoc.createElementNS((String)null, element.getQualifiedName()) : domDoc.createElement(element.getQualifiedName());
/*     */       } else {
/* 277 */         domElement = domDoc.createElementNS(element.getNamespaceURI(), element.getQualifiedName());
/*     */       } 
/* 287 */       Namespace ns = element.getNamespace();
/* 288 */       if (ns != Namespace.XML_NAMESPACE && (ns != Namespace.NO_NAMESPACE || namespaces.getURI("") != null)) {
/* 291 */         String prefix = ns.getPrefix();
/* 292 */         String uri = namespaces.getURI(prefix);
/* 293 */         if (!ns.getURI().equals(uri)) {
/* 294 */           namespaces.push(ns);
/* 295 */           String attrName = getXmlnsTagFor(ns);
/* 296 */           domElement.setAttribute(attrName, ns.getURI());
/*     */         } 
/*     */       } 
/* 301 */       Iterator itr = element.getAdditionalNamespaces().iterator();
/* 302 */       while (itr.hasNext()) {
/* 303 */         Namespace additional = itr.next();
/* 304 */         String prefix = additional.getPrefix();
/* 305 */         String uri = namespaces.getURI(prefix);
/* 306 */         if (!additional.getURI().equals(uri)) {
/* 307 */           String attrName = getXmlnsTagFor(additional);
/* 308 */           domElement.setAttribute(attrName, additional.getURI());
/* 309 */           namespaces.push(additional);
/*     */         } 
/*     */       } 
/* 314 */       itr = element.getAttributes().iterator();
/* 315 */       while (itr.hasNext()) {
/* 316 */         Attribute attribute = (Attribute)itr.next();
/* 317 */         domElement.setAttributeNode(output(attribute, domDoc));
/* 318 */         Namespace ns1 = attribute.getNamespace();
/* 319 */         if (ns1 != Namespace.NO_NAMESPACE && ns1 != Namespace.XML_NAMESPACE) {
/* 321 */           String prefix = ns1.getPrefix();
/* 322 */           String uri = namespaces.getURI(prefix);
/* 323 */           if (!ns1.getURI().equals(uri)) {
/* 324 */             String attrName = getXmlnsTagFor(ns1);
/* 325 */             domElement.setAttribute(attrName, ns1.getURI());
/* 326 */             namespaces.push(ns1);
/*     */           } 
/*     */         } 
/* 330 */         if (attribute.getNamespace() == Namespace.NO_NAMESPACE) {
/* 332 */           if (this.forceNamespaceAware) {
/* 333 */             domElement.setAttributeNS((String)null, attribute.getQualifiedName(), attribute.getValue());
/*     */             continue;
/*     */           } 
/* 337 */           domElement.setAttribute(attribute.getQualifiedName(), attribute.getValue());
/*     */           continue;
/*     */         } 
/* 342 */         domElement.setAttributeNS(attribute.getNamespaceURI(), attribute.getQualifiedName(), attribute.getValue());
/*     */       } 
/* 349 */       itr = element.getContent().iterator();
/* 350 */       while (itr.hasNext()) {
/* 351 */         Object node = itr.next();
/* 353 */         if (node instanceof Element) {
/* 354 */           Element e = (Element)node;
/* 355 */           Element domElt = output(e, domDoc, namespaces);
/* 356 */           domElement.appendChild(domElt);
/*     */           continue;
/*     */         } 
/* 358 */         if (node instanceof String) {
/* 359 */           String str = (String)node;
/* 360 */           Text domText = domDoc.createTextNode(str);
/* 361 */           domElement.appendChild(domText);
/*     */           continue;
/*     */         } 
/* 363 */         if (node instanceof CDATA) {
/* 364 */           CDATA cdata = (CDATA)node;
/* 365 */           CDATASection domCdata = domDoc.createCDATASection(cdata.getText());
/* 367 */           domElement.appendChild(domCdata);
/*     */           continue;
/*     */         } 
/* 369 */         if (node instanceof Text) {
/* 370 */           Text text = (Text)node;
/* 371 */           Text domText = domDoc.createTextNode(text.getText());
/* 373 */           domElement.appendChild(domText);
/*     */           continue;
/*     */         } 
/* 375 */         if (node instanceof Comment) {
/* 376 */           Comment comment = (Comment)node;
/* 377 */           Comment domComment = domDoc.createComment(comment.getText());
/* 379 */           domElement.appendChild(domComment);
/*     */           continue;
/*     */         } 
/* 381 */         if (node instanceof ProcessingInstruction) {
/* 382 */           ProcessingInstruction pi = (ProcessingInstruction)node;
/* 384 */           ProcessingInstruction domPI = domDoc.createProcessingInstruction(pi.getTarget(), pi.getData());
/* 387 */           domElement.appendChild(domPI);
/*     */           continue;
/*     */         } 
/* 389 */         if (node instanceof EntityRef) {
/* 390 */           EntityRef entity = (EntityRef)node;
/* 391 */           EntityReference domEntity = domDoc.createEntityReference(entity.getName());
/* 393 */           domElement.appendChild(domEntity);
/*     */           continue;
/*     */         } 
/* 396 */         throw new JDOMException("Element contained content with type:" + node.getClass().getName());
/*     */       } 
/* 403 */       while (namespaces.size() > previouslyDeclaredNamespaces)
/* 404 */         namespaces.pop(); 
/* 407 */       return domElement;
/* 409 */     } catch (Exception e) {
/* 410 */       throw new JDOMException("Exception outputting Element " + element.getQualifiedName(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Attr output(Attribute attribute, Document domDoc) throws JDOMException {
/* 418 */     Attr domAttr = null;
/*     */     try {
/* 420 */       if (attribute.getNamespace() == Namespace.NO_NAMESPACE) {
/* 422 */         if (this.forceNamespaceAware) {
/* 423 */           domAttr = domDoc.createAttributeNS((String)null, attribute.getQualifiedName());
/*     */         } else {
/* 425 */           domAttr = domDoc.createAttribute(attribute.getQualifiedName());
/*     */         } 
/*     */       } else {
/* 429 */         domAttr = domDoc.createAttributeNS(attribute.getNamespaceURI(), attribute.getQualifiedName());
/*     */       } 
/* 432 */       domAttr.setValue(attribute.getValue());
/* 433 */     } catch (Exception e) {
/* 434 */       throw new JDOMException("Exception outputting Attribute " + attribute.getQualifiedName(), e);
/*     */     } 
/* 437 */     return domAttr;
/*     */   }
/*     */   
/*     */   private static String getXmlnsTagFor(Namespace ns) {
/* 447 */     String attrName = "xmlns";
/* 448 */     if (!ns.getPrefix().equals("")) {
/* 449 */       attrName = attrName + ":";
/* 450 */       attrName = attrName + ns.getPrefix();
/*     */     } 
/* 452 */     return attrName;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\output\DOMOutputter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */