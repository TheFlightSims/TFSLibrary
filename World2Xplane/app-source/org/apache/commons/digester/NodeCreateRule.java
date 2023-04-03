/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class NodeCreateRule extends Rule {
/*     */   private DocumentBuilder documentBuilder;
/*     */   
/*     */   private int nodeType;
/*     */   
/*     */   private class NodeBuilder extends DefaultHandler {
/*     */     protected ContentHandler oldContentHandler;
/*     */     
/*     */     protected int depth;
/*     */     
/*     */     protected Document doc;
/*     */     
/*     */     protected Node root;
/*     */     
/*     */     protected Node top;
/*     */     
/*     */     protected StringBuffer topText;
/*     */     
/*     */     private final NodeCreateRule this$0;
/*     */     
/*     */     public NodeBuilder(NodeCreateRule this$0, Document doc, Node root) throws ParserConfigurationException, SAXException {
/* 108 */       this.this$0 = this$0;
/* 126 */       this.oldContentHandler = null;
/* 133 */       this.depth = 0;
/* 139 */       this.doc = null;
/* 145 */       this.root = null;
/* 151 */       this.top = null;
/* 156 */       this.topText = new StringBuffer();
/*     */       this.doc = doc;
/*     */       this.root = root;
/*     */       this.top = root;
/*     */       this.oldContentHandler = this$0.digester.getCustomContentHandler();
/*     */     }
/*     */     
/*     */     private void addTextIfPresent() throws SAXException {
/* 166 */       if (this.topText.length() > 0) {
/* 167 */         String str = this.topText.toString();
/* 168 */         this.topText.setLength(0);
/* 170 */         if (str.trim().length() > 0)
/*     */           try {
/* 175 */             this.top.appendChild(this.doc.createTextNode(str));
/* 176 */           } catch (DOMException e) {
/* 177 */             throw new SAXException(e.getMessage());
/*     */           }  
/*     */       } 
/*     */     }
/*     */     
/*     */     public void characters(char[] ch, int start, int length) throws SAXException {
/* 212 */       this.topText.append(ch, start, length);
/*     */     }
/*     */     
/*     */     public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 228 */       addTextIfPresent();
/*     */       try {
/* 231 */         if (this.depth == 0) {
/* 232 */           this.this$0.getDigester().setCustomContentHandler(this.oldContentHandler);
/* 233 */           this.this$0.getDigester().push(this.root);
/* 234 */           this.this$0.getDigester().endElement(namespaceURI, localName, qName);
/*     */         } 
/* 237 */         this.top = this.top.getParentNode();
/* 238 */         this.depth--;
/* 239 */       } catch (DOMException e) {
/* 240 */         throw new SAXException(e.getMessage());
/*     */       } 
/*     */     }
/*     */     
/*     */     public void processingInstruction(String target, String data) throws SAXException {
/*     */       try {
/* 260 */         this.top.appendChild(this.doc.createProcessingInstruction(target, data));
/* 261 */       } catch (DOMException e) {
/* 262 */         throw new SAXException(e.getMessage());
/*     */       } 
/*     */     }
/*     */     
/*     */     public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 282 */       addTextIfPresent();
/*     */       try {
/* 285 */         Node previousTop = this.top;
/* 286 */         if (localName == null || localName.length() == 0) {
/* 287 */           this.top = this.doc.createElement(qName);
/*     */         } else {
/* 289 */           this.top = this.doc.createElementNS(namespaceURI, localName);
/*     */         } 
/* 291 */         for (int i = 0; i < atts.getLength(); i++) {
/* 292 */           Attr attr = null;
/* 293 */           if (atts.getLocalName(i) == null || atts.getLocalName(i).length() == 0) {
/* 295 */             attr = this.doc.createAttribute(atts.getQName(i));
/* 296 */             attr.setNodeValue(atts.getValue(i));
/* 297 */             ((Element)this.top).setAttributeNode(attr);
/*     */           } else {
/* 299 */             attr = this.doc.createAttributeNS(atts.getURI(i), atts.getLocalName(i));
/* 301 */             attr.setNodeValue(atts.getValue(i));
/* 302 */             ((Element)this.top).setAttributeNodeNS(attr);
/*     */           } 
/*     */         } 
/* 305 */         previousTop.appendChild(this.top);
/* 306 */         this.depth++;
/* 307 */       } catch (DOMException e) {
/* 308 */         throw new SAXException(e.getMessage());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public NodeCreateRule() throws ParserConfigurationException {
/* 325 */     this(1);
/*     */   }
/*     */   
/*     */   public NodeCreateRule(DocumentBuilder documentBuilder) {
/* 340 */     this(1, documentBuilder);
/*     */   }
/*     */   
/*     */   public NodeCreateRule(int nodeType) throws ParserConfigurationException {
/* 358 */     this(nodeType, DocumentBuilderFactory.newInstance().newDocumentBuilder());
/*     */   }
/*     */   
/*     */   public NodeCreateRule(int nodeType, DocumentBuilder documentBuilder) {
/* 397 */     this.documentBuilder = null;
/* 407 */     this.nodeType = 1;
/*     */     if (nodeType != 11 && nodeType != 1)
/*     */       throw new IllegalArgumentException("Can only create nodes of type DocumentFragment and Element"); 
/*     */     this.nodeType = nodeType;
/*     */     this.documentBuilder = documentBuilder;
/*     */   }
/*     */   
/*     */   public void begin(String namespaceURI, String name, Attributes attributes) throws Exception {
/* 432 */     Document doc = this.documentBuilder.newDocument();
/* 433 */     NodeBuilder builder = null;
/* 434 */     if (this.nodeType == 1) {
/* 435 */       Element element = null;
/* 436 */       if (getDigester().getNamespaceAware()) {
/* 437 */         element = doc.createElementNS(namespaceURI, name);
/* 439 */         for (int i = 0; i < attributes.getLength(); i++)
/* 440 */           element.setAttributeNS(attributes.getURI(i), attributes.getQName(i), attributes.getValue(i)); 
/*     */       } else {
/* 445 */         element = doc.createElement(name);
/* 446 */         for (int i = 0; i < attributes.getLength(); i++)
/* 447 */           element.setAttribute(attributes.getQName(i), attributes.getValue(i)); 
/*     */       } 
/* 451 */       builder = new NodeBuilder(this, doc, element);
/*     */     } else {
/* 453 */       builder = new NodeBuilder(this, doc, doc.createDocumentFragment());
/*     */     } 
/* 459 */     getDigester().setCustomContentHandler(builder);
/*     */   }
/*     */   
/*     */   public void end() throws Exception {
/* 468 */     this.digester.pop();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\NodeCreateRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */