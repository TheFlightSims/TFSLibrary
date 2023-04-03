/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.sr.AttributeCollector;
/*     */ import com.ctc.wstx.sr.InputElementStack;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import org.codehaus.stax2.ri.typed.AsciiValueEncoder;
/*     */ 
/*     */ public final class RepairingNsStreamWriter extends BaseNsStreamWriter {
/*     */   protected final String mAutomaticNsPrefix;
/*     */   
/*  66 */   protected int[] mAutoNsSeq = null;
/*     */   
/*  68 */   protected String mSuggestedDefNs = null;
/*     */   
/*  77 */   protected HashMap mSuggestedPrefixes = null;
/*     */   
/*     */   public RepairingNsStreamWriter(XmlWriter xw, String enc, WriterConfig cfg) {
/*  87 */     super(xw, enc, cfg, true);
/*  88 */     this.mAutomaticNsPrefix = cfg.getAutomaticNsPrefix();
/*     */   }
/*     */   
/*     */   public void writeAttribute(String nsURI, String localName, String value) throws XMLStreamException {
/* 109 */     if (!this.mStartElementOpen)
/* 110 */       throwOutputError(ErrorConsts.WERR_ATTR_NO_ELEM); 
/* 112 */     doWriteAttr(localName, nsURI, findOrCreateAttrPrefix((String)null, nsURI, this.mCurrElem), value);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String prefix, String nsURI, String localName, String value) throws XMLStreamException {
/* 121 */     if (!this.mStartElementOpen)
/* 122 */       throwOutputError(ErrorConsts.WERR_ATTR_NO_ELEM); 
/* 125 */     doWriteAttr(localName, nsURI, findOrCreateAttrPrefix(prefix, nsURI, this.mCurrElem), value);
/*     */   }
/*     */   
/*     */   public void writeDefaultNamespace(String nsURI) throws XMLStreamException {
/* 139 */     if (!this.mStartElementOpen)
/* 140 */       throwOutputError("Trying to write a namespace declaration when there is no open start element."); 
/* 147 */     String prefix = this.mCurrElem.getPrefix();
/* 148 */     if (prefix != null && prefix.length() > 0) {
/* 149 */       this.mCurrElem.setDefaultNsUri(nsURI);
/* 150 */       doWriteDefaultNs(nsURI);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeNamespace(String prefix, String nsURI) throws XMLStreamException {
/* 162 */     if (prefix == null || prefix.length() == 0) {
/* 163 */       writeDefaultNamespace(nsURI);
/*     */       return;
/*     */     } 
/* 166 */     if (!this.mStartElementOpen)
/* 167 */       throwOutputError("Trying to write a namespace declaration when there is no open start element."); 
/* 175 */     int value = this.mCurrElem.isPrefixValid(prefix, nsURI, true);
/* 176 */     if (value == 0) {
/* 177 */       this.mCurrElem.addPrefix(prefix, nsURI);
/* 178 */       doWriteNamespace(prefix, nsURI);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDefaultNamespace(String uri) throws XMLStreamException {
/* 195 */     this.mSuggestedDefNs = (uri == null || uri.length() == 0) ? null : uri;
/*     */   }
/*     */   
/*     */   public void doSetPrefix(String prefix, String uri) throws XMLStreamException {
/* 205 */     if (uri == null || uri.length() == 0) {
/* 206 */       if (this.mSuggestedPrefixes != null) {
/* 207 */         Iterator it = this.mSuggestedPrefixes.entrySet().iterator();
/* 208 */         while (it.hasNext()) {
/* 209 */           Map.Entry en = it.next();
/* 210 */           String thisP = (String)en.getValue();
/* 211 */           if (thisP.equals(prefix))
/* 212 */             it.remove(); 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 217 */       if (this.mSuggestedPrefixes == null)
/* 218 */         this.mSuggestedPrefixes = new HashMap(16); 
/* 220 */       this.mSuggestedPrefixes.put(uri, prefix);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeStartElement(StartElement elem) throws XMLStreamException {
/* 230 */     QName name = elem.getName();
/* 231 */     writeStartElement(name.getPrefix(), name.getLocalPart(), name.getNamespaceURI());
/* 233 */     Iterator it = elem.getAttributes();
/* 234 */     while (it.hasNext()) {
/* 235 */       Attribute attr = it.next();
/* 236 */       name = attr.getName();
/* 237 */       writeAttribute(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart(), attr.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void writeTypedAttribute(String prefix, String nsURI, String localName, AsciiValueEncoder enc) throws XMLStreamException {
/* 248 */     super.writeTypedAttribute(findOrCreateAttrPrefix(prefix, nsURI, this.mCurrElem), nsURI, localName, enc);
/*     */   }
/*     */   
/*     */   protected void writeStartOrEmpty(String localName, String nsURI) throws XMLStreamException {
/* 255 */     checkStartElement(localName, "");
/* 258 */     String prefix = findElemPrefix(nsURI, this.mCurrElem);
/* 264 */     if (this.mOutputElemPool != null) {
/* 265 */       SimpleOutputElement newCurr = this.mOutputElemPool;
/* 266 */       this.mOutputElemPool = newCurr.reuseAsChild(this.mCurrElem, prefix, localName, nsURI);
/* 267 */       this.mPoolSize--;
/* 268 */       this.mCurrElem = newCurr;
/*     */     } else {
/* 270 */       this.mCurrElem = this.mCurrElem.createChild(prefix, localName, nsURI);
/*     */     } 
/* 273 */     if (prefix != null) {
/* 274 */       if (this.mValidator != null)
/* 275 */         this.mValidator.validateElementStart(localName, nsURI, prefix); 
/* 277 */       doWriteStartTag(prefix, localName);
/*     */     } else {
/* 279 */       prefix = generateElemPrefix((String)null, nsURI, this.mCurrElem);
/* 280 */       if (this.mValidator != null)
/* 281 */         this.mValidator.validateElementStart(localName, nsURI, prefix); 
/* 283 */       this.mCurrElem.setPrefix(prefix);
/* 284 */       doWriteStartTag(prefix, localName);
/* 285 */       if (prefix == null || prefix.length() == 0) {
/* 286 */         this.mCurrElem.setDefaultNsUri(nsURI);
/* 287 */         doWriteDefaultNs(nsURI);
/*     */       } else {
/* 289 */         this.mCurrElem.addPrefix(prefix, nsURI);
/* 290 */         doWriteNamespace(prefix, nsURI);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void writeStartOrEmpty(String suggPrefix, String localName, String nsURI) throws XMLStreamException {
/* 298 */     checkStartElement(localName, suggPrefix);
/* 301 */     String actPrefix = validateElemPrefix(suggPrefix, nsURI, this.mCurrElem);
/* 302 */     if (actPrefix != null) {
/* 303 */       if (this.mValidator != null)
/* 304 */         this.mValidator.validateElementStart(localName, nsURI, actPrefix); 
/* 306 */       if (this.mOutputElemPool != null) {
/* 307 */         SimpleOutputElement newCurr = this.mOutputElemPool;
/* 308 */         this.mOutputElemPool = newCurr.reuseAsChild(this.mCurrElem, actPrefix, localName, nsURI);
/* 309 */         this.mPoolSize--;
/* 310 */         this.mCurrElem = newCurr;
/*     */       } else {
/* 312 */         this.mCurrElem = this.mCurrElem.createChild(actPrefix, localName, nsURI);
/*     */       } 
/* 314 */       doWriteStartTag(actPrefix, localName);
/*     */     } else {
/* 320 */       if (suggPrefix == null)
/* 321 */         suggPrefix = ""; 
/* 323 */       actPrefix = generateElemPrefix(suggPrefix, nsURI, this.mCurrElem);
/* 324 */       if (this.mValidator != null)
/* 325 */         this.mValidator.validateElementStart(localName, nsURI, actPrefix); 
/* 327 */       if (this.mOutputElemPool != null) {
/* 328 */         SimpleOutputElement newCurr = this.mOutputElemPool;
/* 329 */         this.mOutputElemPool = newCurr.reuseAsChild(this.mCurrElem, actPrefix, localName, nsURI);
/* 330 */         this.mPoolSize--;
/* 331 */         this.mCurrElem = newCurr;
/*     */       } else {
/* 333 */         this.mCurrElem = this.mCurrElem.createChild(actPrefix, localName, nsURI);
/*     */       } 
/* 335 */       this.mCurrElem.setPrefix(actPrefix);
/* 336 */       doWriteStartTag(actPrefix, localName);
/* 337 */       if (actPrefix == null || actPrefix.length() == 0) {
/* 338 */         this.mCurrElem.setDefaultNsUri(nsURI);
/* 339 */         doWriteDefaultNs(nsURI);
/*     */       } else {
/* 341 */         this.mCurrElem.addPrefix(actPrefix, nsURI);
/* 342 */         doWriteNamespace(actPrefix, nsURI);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void copyStartElement(InputElementStack elemStack, AttributeCollector ac) throws IOException, XMLStreamException {
/* 364 */     String prefix = elemStack.getPrefix();
/* 365 */     String uri = elemStack.getNsURI();
/* 366 */     writeStartElement(prefix, elemStack.getLocalName(), uri);
/* 375 */     int nsCount = elemStack.getCurrentNsCount();
/* 376 */     if (nsCount > 0)
/* 377 */       for (int i = 0; i < nsCount; i++)
/* 378 */         writeNamespace(elemStack.getLocalNsPrefix(i), elemStack.getLocalNsURI(i));  
/* 385 */     int attrCount = this.mCfgCopyDefaultAttrs ? ac.getCount() : ac.getSpecifiedCount();
/* 391 */     if (attrCount > 0)
/* 392 */       for (int i = 0; i < attrCount; i++) {
/* 397 */         uri = ac.getURI(i);
/* 398 */         prefix = ac.getPrefix(i);
/* 402 */         if (prefix != null && prefix.length() != 0)
/* 407 */           prefix = findOrCreateAttrPrefix(prefix, uri, this.mCurrElem); 
/* 413 */         if (prefix == null || prefix.length() == 0) {
/* 414 */           this.mWriter.writeAttribute(ac.getLocalName(i), ac.getValue(i));
/*     */         } else {
/* 416 */           this.mWriter.writeAttribute(prefix, ac.getLocalName(i), ac.getValue(i));
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public String validateQNamePrefix(QName name) throws XMLStreamException {
/* 428 */     String uri = name.getNamespaceURI();
/* 429 */     String suggPrefix = name.getPrefix();
/* 430 */     String actPrefix = validateElemPrefix(suggPrefix, uri, this.mCurrElem);
/* 431 */     if (actPrefix == null) {
/* 436 */       if (suggPrefix == null)
/* 437 */         suggPrefix = ""; 
/* 439 */       actPrefix = generateElemPrefix(suggPrefix, uri, this.mCurrElem);
/* 440 */       if (actPrefix == null || actPrefix.length() == 0) {
/* 441 */         writeDefaultNamespace(uri);
/*     */       } else {
/* 443 */         writeNamespace(actPrefix, uri);
/*     */       } 
/*     */     } 
/* 446 */     return actPrefix;
/*     */   }
/*     */   
/*     */   protected final String findElemPrefix(String nsURI, SimpleOutputElement elem) throws XMLStreamException {
/* 468 */     if (nsURI == null || nsURI.length() == 0) {
/* 469 */       String currDefNsURI = elem.getDefaultNsUri();
/* 470 */       if (currDefNsURI != null && currDefNsURI.length() > 0)
/* 472 */         return null; 
/* 474 */       return "";
/*     */     } 
/* 476 */     return this.mCurrElem.getPrefix(nsURI);
/*     */   }
/*     */   
/*     */   protected final String generateElemPrefix(String suggPrefix, String nsURI, SimpleOutputElement elem) throws XMLStreamException {
/* 493 */     if (nsURI == null || nsURI.length() == 0)
/* 494 */       return ""; 
/* 500 */     if (suggPrefix == null)
/* 502 */       if (this.mSuggestedDefNs != null && this.mSuggestedDefNs.equals(nsURI)) {
/* 503 */         suggPrefix = "";
/*     */       } else {
/* 505 */         suggPrefix = (this.mSuggestedPrefixes == null) ? null : (String)this.mSuggestedPrefixes.get(nsURI);
/* 507 */         if (suggPrefix == null) {
/* 513 */           if (this.mAutoNsSeq == null) {
/* 514 */             this.mAutoNsSeq = new int[1];
/* 515 */             this.mAutoNsSeq[0] = 1;
/*     */           } 
/* 517 */           suggPrefix = elem.generateMapping(this.mAutomaticNsPrefix, nsURI, this.mAutoNsSeq);
/*     */         } 
/*     */       }  
/* 524 */     return suggPrefix;
/*     */   }
/*     */   
/*     */   protected final String findOrCreateAttrPrefix(String suggPrefix, String nsURI, SimpleOutputElement elem) throws XMLStreamException {
/* 544 */     if (nsURI == null || nsURI.length() == 0)
/* 549 */       return null; 
/* 552 */     if (suggPrefix != null) {
/* 553 */       int status = elem.isPrefixValid(suggPrefix, nsURI, false);
/* 554 */       if (status == 1)
/* 555 */         return suggPrefix; 
/* 564 */       if (status == 0) {
/* 565 */         elem.addPrefix(suggPrefix, nsURI);
/* 566 */         doWriteNamespace(suggPrefix, nsURI);
/* 567 */         return suggPrefix;
/*     */       } 
/*     */     } 
/* 572 */     String prefix = elem.getExplicitPrefix(nsURI);
/* 573 */     if (prefix != null)
/* 574 */       return prefix; 
/* 580 */     if (suggPrefix != null) {
/* 581 */       prefix = suggPrefix;
/* 582 */     } else if (this.mSuggestedPrefixes != null) {
/* 583 */       prefix = (String)this.mSuggestedPrefixes.get(nsURI);
/*     */     } 
/* 587 */     if (prefix != null)
/* 594 */       if (prefix.length() == 0 || elem.getNamespaceURI(prefix) != null)
/* 596 */         prefix = null;  
/* 600 */     if (prefix == null) {
/* 601 */       if (this.mAutoNsSeq == null) {
/* 602 */         this.mAutoNsSeq = new int[1];
/* 603 */         this.mAutoNsSeq[0] = 1;
/*     */       } 
/* 605 */       prefix = this.mCurrElem.generateMapping(this.mAutomaticNsPrefix, nsURI, this.mAutoNsSeq);
/*     */     } 
/* 610 */     elem.addPrefix(prefix, nsURI);
/* 611 */     doWriteNamespace(prefix, nsURI);
/* 612 */     return prefix;
/*     */   }
/*     */   
/*     */   private final String validateElemPrefix(String prefix, String nsURI, SimpleOutputElement elem) throws XMLStreamException {
/* 623 */     if (nsURI == null || nsURI.length() == 0) {
/* 624 */       String currURL = elem.getDefaultNsUri();
/* 625 */       if (currURL == null || currURL.length() == 0)
/* 627 */         return ""; 
/* 630 */       return null;
/*     */     } 
/* 633 */     int status = elem.isPrefixValid(prefix, nsURI, true);
/* 634 */     if (status == 1)
/* 635 */       return prefix; 
/* 644 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\RepairingNsStreamWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */