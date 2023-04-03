/*     */ package com.ctc.wstx.dom;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import java.util.HashMap;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.transform.dom.DOMResult;
/*     */ import org.codehaus.stax2.ri.EmptyNamespaceContext;
/*     */ import org.codehaus.stax2.ri.dom.DOMWrappingWriter;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public class WstxDOMWrappingWriter extends DOMWrappingWriter {
/*     */   protected static final String ERR_NSDECL_WRONG_STATE = "Trying to write a namespace declaration when there is no open start element.";
/*     */   
/*     */   protected final WriterConfig mConfig;
/*     */   
/*     */   protected DOMOutputElement mCurrElem;
/*     */   
/*     */   protected DOMOutputElement mOpenElement;
/*     */   
/*     */   protected int[] mAutoNsSeq;
/*     */   
/*     */   protected String mSuggestedDefNs;
/*     */   
/*     */   protected String mAutomaticNsPrefix;
/*     */   
/*     */   HashMap mSuggestedPrefixes;
/*     */   
/*     */   private WstxDOMWrappingWriter(WriterConfig cfg, Node treeRoot) throws XMLStreamException {
/* 118 */     super(treeRoot, cfg.willSupportNamespaces(), cfg.automaticNamespacesEnabled());
/*     */     DOMOutputElement root;
/*     */     Element elem;
/*     */     this.mSuggestedDefNs = null;
/*     */     this.mSuggestedPrefixes = null;
/* 119 */     this.mConfig = cfg;
/* 120 */     this.mAutoNsSeq = null;
/* 121 */     this.mAutomaticNsPrefix = this.mNsRepairing ? this.mConfig.getAutomaticNsPrefix() : null;
/* 126 */     switch (treeRoot.getNodeType()) {
/*     */       case 9:
/*     */       case 11:
/* 130 */         this.mCurrElem = DOMOutputElement.createRoot(treeRoot);
/* 131 */         this.mOpenElement = null;
/*     */         return;
/*     */       case 1:
/* 137 */         root = DOMOutputElement.createRoot(treeRoot);
/* 138 */         elem = (Element)treeRoot;
/* 139 */         this.mOpenElement = this.mCurrElem = root.createChild(elem);
/*     */         return;
/*     */     } 
/* 144 */     throw new XMLStreamException("Can not create an XMLStreamWriter for a DOM node of type " + treeRoot.getClass());
/*     */   }
/*     */   
/*     */   public static WstxDOMWrappingWriter createFrom(WriterConfig cfg, DOMResult dst) throws XMLStreamException {
/* 151 */     Node rootNode = dst.getNode();
/* 152 */     return new WstxDOMWrappingWriter(cfg, rootNode);
/*     */   }
/*     */   
/*     */   public NamespaceContext getNamespaceContext() {
/* 167 */     if (!this.mNsAware)
/* 168 */       return (NamespaceContext)EmptyNamespaceContext.getInstance(); 
/* 170 */     return (NamespaceContext)this.mCurrElem;
/*     */   }
/*     */   
/*     */   public String getPrefix(String uri) {
/* 175 */     if (!this.mNsAware)
/* 176 */       return null; 
/* 178 */     if (this.mNsContext != null) {
/* 179 */       String prefix = this.mNsContext.getPrefix(uri);
/* 180 */       if (prefix != null)
/* 181 */         return prefix; 
/*     */     } 
/* 184 */     return this.mCurrElem.getPrefix(uri);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 188 */     return this.mConfig.getProperty(name);
/*     */   }
/*     */   
/*     */   public void setDefaultNamespace(String uri) {
/* 192 */     this.mSuggestedDefNs = (uri == null || uri.length() == 0) ? null : uri;
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix, String uri) throws XMLStreamException {
/* 200 */     if (prefix == null)
/* 201 */       throw new NullPointerException("Can not pass null 'prefix' value"); 
/* 204 */     if (prefix.length() == 0) {
/* 205 */       setDefaultNamespace(uri);
/*     */       return;
/*     */     } 
/* 208 */     if (uri == null)
/* 209 */       throw new NullPointerException("Can not pass null 'uri' value"); 
/* 216 */     if (prefix.equals("xml")) {
/* 217 */       if (!uri.equals("http://www.w3.org/XML/1998/namespace"))
/* 218 */         throwOutputError(ErrorConsts.ERR_NS_REDECL_XML, uri); 
/*     */     } else {
/* 220 */       if (prefix.equals("xmlns")) {
/* 221 */         if (!uri.equals("http://www.w3.org/2000/xmlns/"))
/* 222 */           throwOutputError(ErrorConsts.ERR_NS_REDECL_XMLNS, uri); 
/*     */         return;
/*     */       } 
/* 228 */       if (uri.equals("http://www.w3.org/XML/1998/namespace")) {
/* 229 */         throwOutputError(ErrorConsts.ERR_NS_REDECL_XML_URI, prefix);
/* 230 */       } else if (uri.equals("http://www.w3.org/2000/xmlns/")) {
/* 231 */         throwOutputError(ErrorConsts.ERR_NS_REDECL_XMLNS_URI, prefix);
/*     */       } 
/*     */     } 
/* 236 */     if (this.mSuggestedPrefixes == null)
/* 237 */       this.mSuggestedPrefixes = new HashMap(16); 
/* 239 */     this.mSuggestedPrefixes.put(uri, prefix);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String localName, String value) throws XMLStreamException {
/* 246 */     outputAttribute((String)null, (String)null, localName, value);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String nsURI, String localName, String value) throws XMLStreamException {
/* 252 */     outputAttribute(nsURI, (String)null, localName, value);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String prefix, String nsURI, String localName, String value) throws XMLStreamException {
/* 258 */     outputAttribute(nsURI, prefix, localName, value);
/*     */   }
/*     */   
/*     */   public void writeDefaultNamespace(String nsURI) {
/* 268 */     if (this.mOpenElement == null)
/* 269 */       throw new IllegalStateException("No currently open START_ELEMENT, cannot write attribute"); 
/* 271 */     setDefaultNamespace(nsURI);
/* 272 */     this.mOpenElement.addAttribute("http://www.w3.org/2000/xmlns/", "xmlns", nsURI);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String localName) throws XMLStreamException {
/* 280 */     writeEmptyElement((String)null, localName);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String nsURI, String localName) throws XMLStreamException {
/* 291 */     createStartElem(nsURI, (String)null, localName, true);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String prefix, String localName, String nsURI) throws XMLStreamException {
/* 297 */     if (prefix == null)
/* 298 */       prefix = ""; 
/* 300 */     createStartElem(nsURI, prefix, localName, true);
/*     */   }
/*     */   
/*     */   public void writeEndDocument() {
/* 305 */     this.mCurrElem = this.mOpenElement = null;
/*     */   }
/*     */   
/*     */   public void writeEndElement() {
/* 311 */     if (this.mCurrElem == null || this.mCurrElem.isRoot())
/* 312 */       throw new IllegalStateException("No open start element to close"); 
/* 314 */     this.mOpenElement = null;
/* 315 */     this.mCurrElem = this.mCurrElem.getParent();
/*     */   }
/*     */   
/*     */   public void writeNamespace(String prefix, String nsURI) throws XMLStreamException {
/* 322 */     if (prefix == null || prefix.length() == 0) {
/* 323 */       writeDefaultNamespace(nsURI);
/*     */       return;
/*     */     } 
/* 326 */     if (!this.mNsAware)
/* 327 */       throwOutputError("Can not write namespaces with non-namespace writer."); 
/* 329 */     outputAttribute("http://www.w3.org/2000/xmlns/", "xmlns", prefix, nsURI);
/* 330 */     this.mCurrElem.addPrefix(prefix, nsURI);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String localName) throws XMLStreamException {
/* 343 */     writeStartElement((String)null, localName);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String nsURI, String localName) throws XMLStreamException {
/* 349 */     createStartElem(nsURI, (String)null, localName, false);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String prefix, String localName, String nsURI) throws XMLStreamException {
/* 355 */     createStartElem(nsURI, prefix, localName, false);
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/* 371 */     return this.mConfig.isPropertySupported(name);
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/* 379 */     return this.mConfig.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws XMLStreamException {
/* 398 */     if (this.mCurrElem != null)
/* 399 */       throw new IllegalStateException("Operation only allowed to the document before adding root element"); 
/* 401 */     reportUnsupported("writeDTD()");
/*     */   }
/*     */   
/*     */   protected void appendLeaf(Node n) throws IllegalStateException {
/* 432 */     this.mCurrElem.appendNode(n);
/* 433 */     this.mOpenElement = null;
/*     */   }
/*     */   
/*     */   protected void createStartElem(String nsURI, String prefix, String localName, boolean isEmpty) throws XMLStreamException {
/*     */     DOMOutputElement elem;
/* 456 */     if (!this.mNsAware) {
/* 457 */       if (nsURI != null && nsURI.length() > 0)
/* 458 */         throwOutputError("Can not specify non-empty uri/prefix in non-namespace mode"); 
/* 460 */       elem = this.mCurrElem.createAndAttachChild(this.mDocument.createElement(localName));
/* 462 */     } else if (this.mNsRepairing) {
/* 463 */       String actPrefix = validateElemPrefix(prefix, nsURI, this.mCurrElem);
/* 464 */       if (actPrefix != null) {
/* 465 */         if (actPrefix.length() != 0) {
/* 466 */           elem = this.mCurrElem.createAndAttachChild(this.mDocument.createElementNS(nsURI, actPrefix + ":" + localName));
/*     */         } else {
/* 468 */           elem = this.mCurrElem.createAndAttachChild(this.mDocument.createElementNS(nsURI, localName));
/*     */         } 
/*     */       } else {
/* 475 */         if (prefix == null)
/* 476 */           prefix = ""; 
/* 478 */         actPrefix = generateElemPrefix(prefix, nsURI, this.mCurrElem);
/* 479 */         boolean hasPrefix = (actPrefix.length() != 0);
/* 480 */         if (hasPrefix)
/* 481 */           localName = actPrefix + ":" + localName; 
/* 483 */         elem = this.mCurrElem.createAndAttachChild(this.mDocument.createElementNS(nsURI, localName));
/* 490 */         this.mOpenElement = elem;
/* 492 */         if (hasPrefix) {
/* 493 */           writeNamespace(actPrefix, nsURI);
/* 494 */           elem.addPrefix(actPrefix, nsURI);
/*     */         } else {
/* 496 */           writeDefaultNamespace(nsURI);
/* 497 */           elem.setDefaultNsUri(nsURI);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 505 */       if (prefix == null && nsURI != null && nsURI.length() > 0) {
/* 506 */         if (nsURI == null)
/* 507 */           nsURI = ""; 
/* 509 */         prefix = (this.mSuggestedPrefixes == null) ? null : (String)this.mSuggestedPrefixes.get(nsURI);
/* 510 */         if (prefix == null)
/* 511 */           throwOutputError("Can not find prefix for namespace \"" + nsURI + "\""); 
/*     */       } 
/* 514 */       if (prefix != null && prefix.length() != 0)
/* 515 */         localName = prefix + ":" + localName; 
/* 517 */       elem = this.mCurrElem.createAndAttachChild(this.mDocument.createElementNS(nsURI, localName));
/*     */     } 
/* 523 */     this.mOpenElement = elem;
/* 524 */     if (!isEmpty)
/* 525 */       this.mCurrElem = elem; 
/*     */   }
/*     */   
/*     */   protected void outputAttribute(String nsURI, String prefix, String localName, String value) throws XMLStreamException {
/* 532 */     if (this.mOpenElement == null)
/* 533 */       throw new IllegalStateException("No currently open START_ELEMENT, cannot write attribute"); 
/* 536 */     if (this.mNsAware) {
/* 537 */       if (this.mNsRepairing)
/* 538 */         prefix = findOrCreateAttrPrefix(prefix, nsURI, this.mOpenElement); 
/* 540 */       if (prefix != null && prefix.length() > 0)
/* 541 */         localName = prefix + ":" + localName; 
/* 543 */       this.mOpenElement.addAttribute(nsURI, localName, value);
/*     */     } else {
/* 545 */       if (prefix != null && prefix.length() > 0)
/* 546 */         localName = prefix + ":" + localName; 
/* 548 */       this.mOpenElement.addAttribute(localName, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String validateElemPrefix(String prefix, String nsURI, DOMOutputElement elem) throws XMLStreamException {
/* 560 */     if (nsURI == null || nsURI.length() == 0) {
/* 561 */       String currURL = elem.getDefaultNsUri();
/* 562 */       if (currURL == null || currURL.length() == 0)
/* 564 */         return ""; 
/* 567 */       return null;
/*     */     } 
/* 570 */     int status = elem.isPrefixValid(prefix, nsURI, true);
/* 571 */     if (status == 1)
/* 572 */       return prefix; 
/* 574 */     return null;
/*     */   }
/*     */   
/*     */   protected final String findElemPrefix(String nsURI, DOMOutputElement elem) throws XMLStreamException {
/* 596 */     if (nsURI == null || nsURI.length() == 0) {
/* 597 */       String currDefNsURI = elem.getDefaultNsUri();
/* 598 */       if (currDefNsURI != null && currDefNsURI.length() > 0)
/* 600 */         return null; 
/* 602 */       return "";
/*     */     } 
/* 604 */     return this.mCurrElem.getPrefix(nsURI);
/*     */   }
/*     */   
/*     */   protected final String generateElemPrefix(String suggPrefix, String nsURI, DOMOutputElement elem) throws XMLStreamException {
/* 622 */     if (nsURI == null || nsURI.length() == 0)
/* 623 */       return ""; 
/* 629 */     if (suggPrefix == null)
/* 631 */       if (this.mSuggestedDefNs != null && this.mSuggestedDefNs.equals(nsURI)) {
/* 632 */         suggPrefix = "";
/*     */       } else {
/* 634 */         suggPrefix = (this.mSuggestedPrefixes == null) ? null : (String)this.mSuggestedPrefixes.get(nsURI);
/* 636 */         if (suggPrefix == null) {
/* 642 */           if (this.mAutoNsSeq == null) {
/* 643 */             this.mAutoNsSeq = new int[1];
/* 644 */             this.mAutoNsSeq[0] = 1;
/*     */           } 
/* 646 */           suggPrefix = elem.generateMapping(this.mAutomaticNsPrefix, nsURI, this.mAutoNsSeq);
/*     */         } 
/*     */       }  
/* 653 */     return suggPrefix;
/*     */   }
/*     */   
/*     */   protected final String findOrCreateAttrPrefix(String suggPrefix, String nsURI, DOMOutputElement elem) throws XMLStreamException {
/* 674 */     if (nsURI == null || nsURI.length() == 0)
/* 679 */       return null; 
/* 682 */     if (suggPrefix != null) {
/* 683 */       int status = elem.isPrefixValid(suggPrefix, nsURI, false);
/* 684 */       if (status == 1)
/* 685 */         return suggPrefix; 
/* 694 */       if (status == 0) {
/* 695 */         elem.addPrefix(suggPrefix, nsURI);
/* 696 */         writeNamespace(suggPrefix, nsURI);
/* 697 */         return suggPrefix;
/*     */       } 
/*     */     } 
/* 702 */     String prefix = elem.getExplicitPrefix(nsURI);
/* 703 */     if (prefix != null)
/* 704 */       return prefix; 
/* 710 */     if (suggPrefix != null) {
/* 711 */       prefix = suggPrefix;
/* 712 */     } else if (this.mSuggestedPrefixes != null) {
/* 713 */       prefix = (String)this.mSuggestedPrefixes.get(nsURI);
/*     */     } 
/* 717 */     if (prefix != null)
/* 724 */       if (prefix.length() == 0 || elem.getNamespaceURI(prefix) != null)
/* 726 */         prefix = null;  
/* 730 */     if (prefix == null) {
/* 731 */       if (this.mAutoNsSeq == null) {
/* 732 */         this.mAutoNsSeq = new int[1];
/* 733 */         this.mAutoNsSeq[0] = 1;
/*     */       } 
/* 735 */       prefix = this.mCurrElem.generateMapping(this.mAutomaticNsPrefix, nsURI, this.mAutoNsSeq);
/*     */     } 
/* 740 */     elem.addPrefix(prefix, nsURI);
/* 741 */     writeNamespace(prefix, nsURI);
/* 742 */     return prefix;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dom\WstxDOMWrappingWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */