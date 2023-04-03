/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.EmptyElementHandler;
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.util.DefaultXmlSymbolTable;
/*     */ import java.io.IOException;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import org.codehaus.stax2.ri.typed.AsciiValueEncoder;
/*     */ 
/*     */ public abstract class BaseNsStreamWriter extends TypedStreamWriter {
/*  48 */   protected static final String sPrefixXml = DefaultXmlSymbolTable.getXmlSymbol();
/*     */   
/*  50 */   protected static final String sPrefixXmlns = DefaultXmlSymbolTable.getXmlnsSymbol();
/*     */   
/*     */   protected static final String ERR_NSDECL_WRONG_STATE = "Trying to write a namespace declaration when there is no open start element.";
/*     */   
/*     */   protected final boolean mAutomaticNS;
/*     */   
/*     */   protected final EmptyElementHandler mEmptyElementHandler;
/*     */   
/*  78 */   protected SimpleOutputElement mCurrElem = SimpleOutputElement.createRoot();
/*     */   
/*  84 */   protected NamespaceContext mRootNsContext = null;
/*     */   
/*  98 */   protected SimpleOutputElement mOutputElemPool = null;
/*     */   
/*     */   static final int MAX_POOL_SIZE = 8;
/*     */   
/* 108 */   protected int mPoolSize = 0;
/*     */   
/*     */   public BaseNsStreamWriter(XmlWriter xw, String enc, WriterConfig cfg, boolean repairing) {
/* 119 */     super(xw, enc, cfg);
/* 120 */     this.mAutomaticNS = repairing;
/* 121 */     this.mEmptyElementHandler = cfg.getEmptyElementHandler();
/*     */   }
/*     */   
/*     */   public NamespaceContext getNamespaceContext() {
/* 131 */     return this.mCurrElem;
/*     */   }
/*     */   
/*     */   public String getPrefix(String uri) {
/* 135 */     return this.mCurrElem.getPrefix(uri);
/*     */   }
/*     */   
/*     */   public abstract void setDefaultNamespace(String paramString) throws XMLStreamException;
/*     */   
/*     */   public void setNamespaceContext(NamespaceContext ctxt) throws XMLStreamException {
/* 150 */     if (this.mState != 1)
/* 151 */       throwOutputError("Called setNamespaceContext() after having already output root element."); 
/* 154 */     this.mRootNsContext = ctxt;
/* 155 */     this.mCurrElem.setRootNsContext(ctxt);
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix, String uri) throws XMLStreamException {
/* 161 */     if (prefix == null)
/* 162 */       throw new NullPointerException("Can not pass null 'prefix' value"); 
/* 165 */     if (prefix.length() == 0) {
/* 166 */       setDefaultNamespace(uri);
/*     */       return;
/*     */     } 
/* 169 */     if (uri == null)
/* 170 */       throw new NullPointerException("Can not pass null 'uri' value"); 
/* 182 */     if (prefix.equals(sPrefixXml)) {
/* 183 */       if (!uri.equals("http://www.w3.org/XML/1998/namespace"))
/* 184 */         throwOutputError(ErrorConsts.ERR_NS_REDECL_XML, uri); 
/* 186 */     } else if (prefix.equals(sPrefixXmlns)) {
/* 187 */       if (!uri.equals("http://www.w3.org/2000/xmlns/"))
/* 188 */         throwOutputError(ErrorConsts.ERR_NS_REDECL_XMLNS, uri); 
/* 192 */     } else if (uri.equals("http://www.w3.org/XML/1998/namespace")) {
/* 193 */       throwOutputError(ErrorConsts.ERR_NS_REDECL_XML_URI, prefix);
/* 194 */     } else if (uri.equals("http://www.w3.org/2000/xmlns/")) {
/* 195 */       throwOutputError(ErrorConsts.ERR_NS_REDECL_XMLNS_URI, prefix);
/*     */     } 
/* 206 */     if (!this.mXml11 && 
/* 207 */       uri.length() == 0)
/* 208 */       throwOutputError(ErrorConsts.ERR_NS_EMPTY); 
/* 213 */     doSetPrefix(prefix, uri);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String localName, String value) throws XMLStreamException {
/* 225 */     if (!this.mStartElementOpen && this.mCheckStructure)
/* 226 */       reportNwfStructure(ErrorConsts.WERR_ATTR_NO_ELEM); 
/* 228 */     doWriteAttr(localName, (String)null, (String)null, value);
/*     */   }
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2, String paramString3, String paramString4) throws XMLStreamException;
/*     */   
/*     */   public void writeEmptyElement(String localName) throws XMLStreamException {
/* 246 */     checkStartElement(localName, (String)null);
/* 247 */     if (this.mValidator != null)
/* 248 */       this.mValidator.validateElementStart(localName, "", ""); 
/* 250 */     this.mEmptyElement = true;
/* 251 */     if (this.mOutputElemPool != null) {
/* 252 */       SimpleOutputElement newCurr = this.mOutputElemPool;
/* 253 */       this.mOutputElemPool = newCurr.reuseAsChild(this.mCurrElem, localName);
/* 254 */       this.mPoolSize--;
/* 255 */       this.mCurrElem = newCurr;
/*     */     } else {
/* 257 */       this.mCurrElem = this.mCurrElem.createChild(localName);
/*     */     } 
/* 259 */     doWriteStartTag(localName);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String nsURI, String localName) throws XMLStreamException {
/* 266 */     writeStartOrEmpty(localName, nsURI);
/* 267 */     this.mEmptyElement = true;
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String prefix, String localName, String nsURI) throws XMLStreamException {
/* 273 */     writeStartOrEmpty(prefix, localName, nsURI);
/* 274 */     this.mEmptyElement = true;
/*     */   }
/*     */   
/*     */   public void writeEndElement() throws XMLStreamException {
/* 280 */     doWriteEndTag((QName)null, this.mCfgAutomaticEmptyElems);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String localName) throws XMLStreamException {
/* 290 */     checkStartElement(localName, (String)null);
/* 291 */     if (this.mValidator != null)
/* 292 */       this.mValidator.validateElementStart(localName, "", ""); 
/* 294 */     this.mEmptyElement = false;
/* 295 */     if (this.mOutputElemPool != null) {
/* 296 */       SimpleOutputElement newCurr = this.mOutputElemPool;
/* 297 */       this.mOutputElemPool = newCurr.reuseAsChild(this.mCurrElem, localName);
/* 298 */       this.mPoolSize--;
/* 299 */       this.mCurrElem = newCurr;
/*     */     } else {
/* 301 */       this.mCurrElem = this.mCurrElem.createChild(localName);
/*     */     } 
/* 304 */     doWriteStartTag(localName);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String nsURI, String localName) throws XMLStreamException {
/* 310 */     writeStartOrEmpty(localName, nsURI);
/* 311 */     this.mEmptyElement = false;
/*     */   }
/*     */   
/*     */   public void writeStartElement(String prefix, String localName, String nsURI) throws XMLStreamException {
/* 317 */     writeStartOrEmpty(prefix, localName, nsURI);
/* 318 */     this.mEmptyElement = false;
/*     */   }
/*     */   
/*     */   protected void writeTypedAttribute(String prefix, String nsURI, String localName, AsciiValueEncoder enc) throws XMLStreamException {
/* 325 */     if (!this.mStartElementOpen)
/* 326 */       throwOutputError(ErrorConsts.WERR_ATTR_NO_ELEM); 
/* 328 */     if (this.mCheckAttrs)
/* 329 */       this.mCurrElem.checkAttrWrite(nsURI, localName); 
/*     */     try {
/* 332 */       if (this.mValidator == null) {
/* 333 */         if (prefix == null || prefix.length() == 0) {
/* 334 */           this.mWriter.writeTypedAttribute(localName, enc);
/*     */         } else {
/* 336 */           this.mWriter.writeTypedAttribute(prefix, localName, enc);
/*     */         } 
/*     */       } else {
/* 339 */         this.mWriter.writeTypedAttribute(prefix, localName, nsURI, enc, this.mValidator, getCopyBuffer());
/*     */       } 
/* 342 */     } catch (IOException ioe) {
/* 343 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeFullEndElement() throws XMLStreamException {
/* 360 */     doWriteEndTag((QName)null, false);
/*     */   }
/*     */   
/*     */   public QName getCurrentElementName() {
/* 371 */     return this.mCurrElem.getName();
/*     */   }
/*     */   
/*     */   public String getNamespaceURI(String prefix) {
/* 375 */     return this.mCurrElem.getNamespaceURI(prefix);
/*     */   }
/*     */   
/*     */   public void writeEndElement(QName name) throws XMLStreamException {
/* 393 */     doWriteEndTag(this.mCheckStructure ? name : null, this.mCfgAutomaticEmptyElems);
/*     */   }
/*     */   
/*     */   protected void closeStartElement(boolean emptyElem) throws XMLStreamException {
/* 407 */     this.mStartElementOpen = false;
/*     */     try {
/* 410 */       if (emptyElem) {
/* 411 */         this.mWriter.writeStartTagEmptyEnd();
/*     */       } else {
/* 413 */         this.mWriter.writeStartTagEnd();
/*     */       } 
/* 415 */     } catch (IOException ioe) {
/* 416 */       throw new WstxIOException(ioe);
/*     */     } 
/* 419 */     if (this.mValidator != null)
/* 420 */       this.mVldContent = this.mValidator.validateElementAndAttributes(); 
/* 424 */     if (emptyElem) {
/* 425 */       SimpleOutputElement curr = this.mCurrElem;
/* 426 */       this.mCurrElem = curr.getParent();
/* 427 */       if (this.mCurrElem.isRoot())
/* 428 */         this.mState = 3; 
/* 430 */       if (this.mValidator != null)
/* 431 */         this.mVldContent = this.mValidator.validateElementEnd(curr.getLocalName(), curr.getNamespaceURI(), curr.getPrefix()); 
/* 434 */       if (this.mPoolSize < 8) {
/* 435 */         curr.addToPool(this.mOutputElemPool);
/* 436 */         this.mOutputElemPool = curr;
/* 437 */         this.mPoolSize++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getTopElementDesc() {
/* 443 */     return this.mCurrElem.getNameDesc();
/*     */   }
/*     */   
/*     */   protected void checkStartElement(String localName, String prefix) throws XMLStreamException {
/* 461 */     if (this.mStartElementOpen) {
/* 462 */       closeStartElement(this.mEmptyElement);
/* 463 */     } else if (this.mState == 1) {
/* 464 */       verifyRootElement(localName, prefix);
/* 465 */     } else if (this.mState == 3) {
/* 466 */       if (this.mCheckStructure) {
/* 467 */         String name = (prefix == null || prefix.length() == 0) ? localName : (prefix + ":" + localName);
/* 469 */         reportNwfStructure(ErrorConsts.WERR_PROLOG_SECOND_ROOT, name);
/*     */       } 
/* 474 */       this.mState = 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void doWriteAttr(String localName, String nsURI, String prefix, String value) throws XMLStreamException {
/* 482 */     if (this.mCheckAttrs)
/* 483 */       this.mCurrElem.checkAttrWrite(nsURI, localName); 
/* 486 */     if (this.mValidator != null)
/* 490 */       this.mValidator.validateAttribute(localName, nsURI, prefix, value); 
/*     */     try {
/* 493 */       int vlen = value.length();
/* 495 */       if (vlen >= 12) {
/* 496 */         char[] buf = this.mCopyBuffer;
/* 497 */         if (buf == null)
/* 498 */           this.mCopyBuffer = buf = this.mConfig.allocMediumCBuffer(512); 
/* 503 */         if (vlen <= buf.length) {
/* 504 */           value.getChars(0, vlen, buf, 0);
/* 505 */           if (prefix != null && prefix.length() > 0) {
/* 506 */             this.mWriter.writeAttribute(prefix, localName, buf, 0, vlen);
/*     */           } else {
/* 508 */             this.mWriter.writeAttribute(localName, buf, 0, vlen);
/*     */           } 
/*     */           return;
/*     */         } 
/*     */       } 
/* 513 */       if (prefix != null && prefix.length() > 0) {
/* 514 */         this.mWriter.writeAttribute(prefix, localName, value);
/*     */       } else {
/* 516 */         this.mWriter.writeAttribute(localName, value);
/*     */       } 
/* 518 */     } catch (IOException ioe) {
/* 519 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void doWriteAttr(String localName, String nsURI, String prefix, char[] buf, int start, int len) throws XMLStreamException {
/* 527 */     if (this.mCheckAttrs)
/* 528 */       this.mCurrElem.checkAttrWrite(nsURI, localName); 
/* 531 */     if (this.mValidator != null)
/* 535 */       this.mValidator.validateAttribute(localName, nsURI, prefix, buf, start, len); 
/*     */     try {
/* 538 */       if (prefix != null && prefix.length() > 0) {
/* 539 */         this.mWriter.writeAttribute(prefix, localName, buf, start, len);
/*     */       } else {
/* 541 */         this.mWriter.writeAttribute(localName, buf, start, len);
/*     */       } 
/* 543 */     } catch (IOException ioe) {
/* 544 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doWriteNamespace(String prefix, String nsURI) throws XMLStreamException {
/*     */     try {
/* 552 */       int vlen = nsURI.length();
/* 554 */       if (vlen >= 12) {
/* 555 */         char[] buf = this.mCopyBuffer;
/* 556 */         if (buf == null)
/* 557 */           this.mCopyBuffer = buf = this.mConfig.allocMediumCBuffer(512); 
/* 560 */         if (vlen <= buf.length) {
/* 561 */           nsURI.getChars(0, vlen, buf, 0);
/* 562 */           this.mWriter.writeAttribute("xmlns", prefix, buf, 0, vlen);
/*     */           return;
/*     */         } 
/*     */       } 
/* 566 */       this.mWriter.writeAttribute("xmlns", prefix, nsURI);
/* 567 */     } catch (IOException ioe) {
/* 568 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doWriteDefaultNs(String nsURI) throws XMLStreamException {
/*     */     try {
/* 576 */       int vlen = (nsURI == null) ? 0 : nsURI.length();
/* 578 */       if (vlen >= 12) {
/* 579 */         char[] buf = this.mCopyBuffer;
/* 580 */         if (buf == null)
/* 581 */           this.mCopyBuffer = buf = this.mConfig.allocMediumCBuffer(512); 
/* 584 */         if (vlen <= buf.length) {
/* 585 */           nsURI.getChars(0, vlen, buf, 0);
/* 586 */           this.mWriter.writeAttribute("xmlns", buf, 0, vlen);
/*     */           return;
/*     */         } 
/*     */       } 
/* 590 */       this.mWriter.writeAttribute("xmlns", nsURI);
/* 591 */     } catch (IOException ioe) {
/* 592 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void doWriteStartTag(String localName) throws XMLStreamException {
/* 599 */     this.mAnyOutput = true;
/* 600 */     this.mStartElementOpen = true;
/*     */     try {
/* 602 */       this.mWriter.writeStartTagStart(localName);
/* 603 */     } catch (IOException ioe) {
/* 604 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void doWriteStartTag(String prefix, String localName) throws XMLStreamException {
/* 611 */     this.mAnyOutput = true;
/* 612 */     this.mStartElementOpen = true;
/*     */     try {
/* 614 */       boolean hasPrefix = (prefix != null && prefix.length() > 0);
/* 615 */       if (hasPrefix) {
/* 616 */         this.mWriter.writeStartTagStart(prefix, localName);
/*     */       } else {
/* 618 */         this.mWriter.writeStartTagStart(localName);
/*     */       } 
/* 620 */     } catch (IOException ioe) {
/* 621 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doWriteEndTag(QName expName, boolean allowEmpty) throws XMLStreamException {
/* 640 */     if (this.mStartElementOpen && this.mEmptyElement) {
/* 641 */       this.mEmptyElement = false;
/* 642 */       closeStartElement(true);
/*     */     } 
/* 646 */     if (this.mState != 2)
/* 648 */       reportNwfStructure("No open start element, when trying to write end element"); 
/* 651 */     SimpleOutputElement thisElem = this.mCurrElem;
/* 652 */     String prefix = thisElem.getPrefix();
/* 653 */     String localName = thisElem.getLocalName();
/* 654 */     String nsURI = thisElem.getNamespaceURI();
/* 657 */     this.mCurrElem = thisElem.getParent();
/* 659 */     if (this.mPoolSize < 8) {
/* 660 */       thisElem.addToPool(this.mOutputElemPool);
/* 661 */       this.mOutputElemPool = thisElem;
/* 662 */       this.mPoolSize++;
/*     */     } 
/* 665 */     if (this.mCheckStructure && 
/* 666 */       expName != null)
/* 668 */       if (!localName.equals(expName.getLocalPart()))
/* 672 */         reportNwfStructure("Mismatching close element local name, '" + localName + "'; expected '" + expName.getLocalPart() + "'.");  
/* 680 */     if (this.mStartElementOpen) {
/* 684 */       if (this.mValidator != null)
/* 688 */         this.mVldContent = this.mValidator.validateElementAndAttributes(); 
/* 690 */       this.mStartElementOpen = false;
/*     */       try {
/* 693 */         if (this.mEmptyElementHandler != null)
/* 694 */           allowEmpty = this.mEmptyElementHandler.allowEmptyElement(prefix, localName, nsURI, allowEmpty); 
/* 697 */         if (allowEmpty) {
/* 698 */           this.mWriter.writeStartTagEmptyEnd();
/* 699 */           if (this.mCurrElem.isRoot())
/* 700 */             this.mState = 3; 
/* 702 */           if (this.mValidator != null)
/* 703 */             this.mVldContent = this.mValidator.validateElementEnd(localName, nsURI, prefix); 
/*     */           return;
/*     */         } 
/* 708 */         this.mWriter.writeStartTagEnd();
/* 709 */       } catch (IOException ioe) {
/* 710 */         throw new WstxIOException(ioe);
/*     */       } 
/*     */     } 
/*     */     try {
/* 715 */       this.mWriter.writeEndTag(prefix, localName);
/* 716 */     } catch (IOException ioe) {
/* 717 */       throw new WstxIOException(ioe);
/*     */     } 
/* 720 */     if (this.mCurrElem.isRoot())
/* 721 */       this.mState = 3; 
/* 725 */     if (this.mValidator != null)
/* 726 */       this.mVldContent = this.mValidator.validateElementEnd(localName, nsURI, prefix); 
/*     */   }
/*     */   
/*     */   public abstract void doSetPrefix(String paramString1, String paramString2) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeDefaultNamespace(String paramString) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeNamespace(String paramString1, String paramString2) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeStartElement(StartElement paramStartElement) throws XMLStreamException;
/*     */   
/*     */   protected abstract void writeStartOrEmpty(String paramString1, String paramString2) throws XMLStreamException;
/*     */   
/*     */   protected abstract void writeStartOrEmpty(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\BaseNsStreamWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */