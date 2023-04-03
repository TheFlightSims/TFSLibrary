/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.sr.AttributeCollector;
/*     */ import com.ctc.wstx.sr.InputElementStack;
/*     */ import com.ctc.wstx.util.EmptyNamespaceContext;
/*     */ import com.ctc.wstx.util.StringVector;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeSet;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import org.codehaus.stax2.ri.typed.AsciiValueEncoder;
/*     */ 
/*     */ public class NonNsStreamWriter extends TypedStreamWriter {
/*     */   final StringVector mElements;
/*     */   
/*     */   TreeSet mAttrNames;
/*     */   
/*     */   public NonNsStreamWriter(XmlWriter xw, String enc, WriterConfig cfg) {
/*  78 */     super(xw, enc, cfg);
/*  79 */     this.mElements = new StringVector(32);
/*     */   }
/*     */   
/*     */   public NamespaceContext getNamespaceContext() {
/*  89 */     return (NamespaceContext)EmptyNamespaceContext.getInstance();
/*     */   }
/*     */   
/*     */   public String getPrefix(String uri) {
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   public void setDefaultNamespace(String uri) throws XMLStreamException {
/*  99 */     reportIllegalArg("Can not set default namespace for non-namespace writer.");
/*     */   }
/*     */   
/*     */   public void setNamespaceContext(NamespaceContext context) {
/* 104 */     reportIllegalArg("Can not set NamespaceContext for non-namespace writer.");
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix, String uri) throws XMLStreamException {
/* 110 */     reportIllegalArg("Can not set namespace prefix for non-namespace writer.");
/*     */   }
/*     */   
/*     */   public void writeAttribute(String localName, String value) throws XMLStreamException {
/* 117 */     if (!this.mStartElementOpen && this.mCheckStructure)
/* 118 */       reportNwfStructure(ErrorConsts.WERR_ATTR_NO_ELEM); 
/* 120 */     if (this.mCheckAttrs) {
/* 124 */       if (this.mAttrNames == null)
/* 125 */         this.mAttrNames = new TreeSet(); 
/* 127 */       if (!this.mAttrNames.add(localName))
/* 128 */         reportNwfAttr("Trying to write attribute '" + localName + "' twice"); 
/*     */     } 
/* 131 */     if (this.mValidator != null)
/* 135 */       this.mValidator.validateAttribute(localName, "", "", value); 
/*     */     try {
/* 139 */       this.mWriter.writeAttribute(localName, value);
/* 140 */     } catch (IOException ioe) {
/* 141 */       throwFromIOE(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeAttribute(String nsURI, String localName, String value) throws XMLStreamException {
/* 148 */     writeAttribute(localName, value);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String prefix, String nsURI, String localName, String value) throws XMLStreamException {
/* 155 */     writeAttribute(localName, value);
/*     */   }
/*     */   
/*     */   public void writeDefaultNamespace(String nsURI) throws XMLStreamException {
/* 161 */     reportIllegalMethod("Can not call writeDefaultNamespace namespaces with non-namespace writer.");
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String localName) throws XMLStreamException {
/* 167 */     doWriteStartElement(localName);
/* 168 */     this.mEmptyElement = true;
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String nsURI, String localName) throws XMLStreamException {
/* 174 */     writeEmptyElement(localName);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String prefix, String localName, String nsURI) throws XMLStreamException {
/* 180 */     writeEmptyElement(localName);
/*     */   }
/*     */   
/*     */   public void writeEndElement() throws XMLStreamException {
/* 186 */     doWriteEndTag((String)null, this.mCfgAutomaticEmptyElems);
/*     */   }
/*     */   
/*     */   public void writeNamespace(String prefix, String nsURI) throws XMLStreamException {
/* 192 */     reportIllegalMethod("Can not set write namespaces with non-namespace writer.");
/*     */   }
/*     */   
/*     */   public void writeStartElement(String localName) throws XMLStreamException {
/* 198 */     doWriteStartElement(localName);
/* 199 */     this.mEmptyElement = false;
/*     */   }
/*     */   
/*     */   public void writeStartElement(String nsURI, String localName) throws XMLStreamException {
/* 205 */     writeStartElement(localName);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String prefix, String localName, String nsURI) throws XMLStreamException {
/* 211 */     writeStartElement(localName);
/*     */   }
/*     */   
/*     */   public void writeFullEndElement() throws XMLStreamException {
/* 227 */     doWriteEndTag((String)null, false);
/*     */   }
/*     */   
/*     */   public QName getCurrentElementName() {
/* 237 */     if (this.mElements.isEmpty())
/* 238 */       return null; 
/* 240 */     return new QName(this.mElements.getLastString());
/*     */   }
/*     */   
/*     */   public String getNamespaceURI(String prefix) {
/* 244 */     return null;
/*     */   }
/*     */   
/*     */   public void writeStartElement(StartElement elem) throws XMLStreamException {
/* 256 */     QName name = elem.getName();
/* 257 */     writeStartElement(name.getLocalPart());
/* 258 */     Iterator it = elem.getAttributes();
/* 259 */     while (it.hasNext()) {
/* 260 */       Attribute attr = it.next();
/* 261 */       name = attr.getName();
/* 262 */       writeAttribute(name.getLocalPart(), attr.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeEndElement(QName name) throws XMLStreamException {
/* 275 */     doWriteEndTag(this.mCheckStructure ? name.getLocalPart() : null, this.mCfgAutomaticEmptyElems);
/*     */   }
/*     */   
/*     */   protected void writeTypedAttribute(String prefix, String nsURI, String localName, AsciiValueEncoder enc) throws XMLStreamException {
/* 284 */     if (!this.mStartElementOpen && this.mCheckStructure)
/* 285 */       reportNwfStructure(ErrorConsts.WERR_ATTR_NO_ELEM); 
/* 287 */     if (this.mCheckAttrs) {
/* 288 */       if (this.mAttrNames == null)
/* 289 */         this.mAttrNames = new TreeSet(); 
/* 291 */       if (!this.mAttrNames.add(localName))
/* 292 */         reportNwfAttr("Trying to write attribute '" + localName + "' twice"); 
/*     */     } 
/*     */     try {
/* 297 */       if (this.mValidator == null) {
/* 298 */         this.mWriter.writeTypedAttribute(localName, enc);
/*     */       } else {
/* 300 */         this.mWriter.writeTypedAttribute(null, localName, null, enc, this.mValidator, getCopyBuffer());
/*     */       } 
/* 302 */     } catch (IOException ioe) {
/* 303 */       throwFromIOE(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void closeStartElement(boolean emptyElem) throws XMLStreamException {
/* 316 */     this.mStartElementOpen = false;
/* 317 */     if (this.mAttrNames != null)
/* 318 */       this.mAttrNames.clear(); 
/*     */     try {
/* 322 */       if (emptyElem) {
/* 323 */         this.mWriter.writeStartTagEmptyEnd();
/*     */       } else {
/* 325 */         this.mWriter.writeStartTagEnd();
/*     */       } 
/* 327 */     } catch (IOException ioe) {
/* 328 */       throwFromIOE(ioe);
/*     */     } 
/* 331 */     if (this.mValidator != null)
/* 332 */       this.mVldContent = this.mValidator.validateElementAndAttributes(); 
/* 336 */     if (emptyElem) {
/* 337 */       String localName = this.mElements.removeLast();
/* 338 */       if (this.mElements.isEmpty())
/* 339 */         this.mState = 3; 
/* 341 */       if (this.mValidator != null)
/* 342 */         this.mVldContent = this.mValidator.validateElementEnd(localName, "", ""); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void copyStartElement(InputElementStack elemStack, AttributeCollector attrCollector) throws IOException, XMLStreamException {
/* 357 */     String ln = elemStack.getLocalName();
/* 358 */     boolean nsAware = elemStack.isNamespaceAware();
/* 363 */     if (nsAware) {
/* 364 */       String prefix = elemStack.getPrefix();
/* 365 */       if (prefix != null && prefix.length() > 0)
/* 366 */         ln = prefix + ":" + ln; 
/*     */     } 
/* 369 */     writeStartElement(ln);
/* 374 */     if (nsAware) {
/* 375 */       int nsCount = elemStack.getCurrentNsCount();
/* 376 */       if (nsCount > 0)
/* 377 */         for (int i = 0; i < nsCount; i++) {
/* 378 */           String prefix = elemStack.getLocalNsPrefix(i);
/* 379 */           if (prefix == null || prefix.length() == 0) {
/* 380 */             prefix = "xml";
/*     */           } else {
/* 382 */             prefix = "xmlns:" + prefix;
/*     */           } 
/* 384 */           writeAttribute(prefix, elemStack.getLocalNsURI(i));
/*     */         }  
/*     */     } 
/* 392 */     int attrCount = this.mCfgCopyDefaultAttrs ? attrCollector.getCount() : attrCollector.getSpecifiedCount();
/* 396 */     if (attrCount > 0)
/* 397 */       for (int i = 0; i < attrCount; i++)
/* 398 */         attrCollector.writeAttribute(i, this.mWriter);  
/*     */   }
/*     */   
/*     */   protected String getTopElementDesc() {
/* 405 */     return this.mElements.isEmpty() ? "#root" : this.mElements.getLastString();
/*     */   }
/*     */   
/*     */   public String validateQNamePrefix(QName name) {
/* 411 */     return name.getPrefix();
/*     */   }
/*     */   
/*     */   private void doWriteStartElement(String localName) throws XMLStreamException {
/* 423 */     this.mAnyOutput = true;
/* 425 */     if (this.mStartElementOpen) {
/* 426 */       closeStartElement(this.mEmptyElement);
/* 427 */     } else if (this.mState == 1) {
/* 429 */       verifyRootElement(localName, null);
/* 430 */     } else if (this.mState == 3) {
/* 431 */       if (this.mCheckStructure)
/* 432 */         reportNwfStructure(ErrorConsts.WERR_PROLOG_SECOND_ROOT, localName); 
/* 435 */       this.mState = 2;
/*     */     } 
/* 444 */     if (this.mValidator != null)
/* 445 */       this.mValidator.validateElementStart(localName, "", ""); 
/* 448 */     this.mStartElementOpen = true;
/* 449 */     this.mElements.addString(localName);
/*     */     try {
/* 451 */       this.mWriter.writeStartTagStart(localName);
/* 452 */     } catch (IOException ioe) {
/* 453 */       throwFromIOE(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doWriteEndTag(String expName, boolean allowEmpty) throws XMLStreamException {
/* 475 */     if (this.mStartElementOpen && this.mEmptyElement) {
/* 476 */       this.mEmptyElement = false;
/* 478 */       closeStartElement(true);
/*     */     } 
/* 482 */     if (this.mState != 2)
/* 484 */       reportNwfStructure("No open start element, when trying to write end element"); 
/* 490 */     String localName = this.mElements.removeLast();
/* 491 */     if (this.mCheckStructure && 
/* 492 */       expName != null && !localName.equals(expName))
/* 496 */       reportNwfStructure("Mismatching close element name, '" + localName + "'; expected '" + expName + "'."); 
/* 505 */     if (this.mStartElementOpen) {
/* 509 */       if (this.mValidator != null)
/* 513 */         this.mVldContent = this.mValidator.validateElementAndAttributes(); 
/* 515 */       this.mStartElementOpen = false;
/* 516 */       if (this.mAttrNames != null)
/* 517 */         this.mAttrNames.clear(); 
/*     */       try {
/* 521 */         if (allowEmpty) {
/* 522 */           this.mWriter.writeStartTagEmptyEnd();
/* 523 */           if (this.mElements.isEmpty())
/* 524 */             this.mState = 3; 
/* 526 */           if (this.mValidator != null)
/* 527 */             this.mVldContent = this.mValidator.validateElementEnd(localName, "", ""); 
/*     */           return;
/*     */         } 
/* 532 */         this.mWriter.writeStartTagEnd();
/* 533 */       } catch (IOException ioe) {
/* 534 */         throwFromIOE(ioe);
/*     */       } 
/*     */     } 
/*     */     try {
/* 539 */       this.mWriter.writeEndTag(localName);
/* 540 */     } catch (IOException ioe) {
/* 541 */       throwFromIOE(ioe);
/*     */     } 
/* 544 */     if (this.mElements.isEmpty())
/* 545 */       this.mState = 3; 
/* 549 */     if (this.mValidator != null)
/* 550 */       this.mVldContent = this.mValidator.validateElementEnd(localName, "", ""); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\NonNsStreamWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */