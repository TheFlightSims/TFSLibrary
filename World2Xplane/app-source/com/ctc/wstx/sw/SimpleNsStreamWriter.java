/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.sr.AttributeCollector;
/*     */ import com.ctc.wstx.sr.InputElementStack;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ 
/*     */ public class SimpleNsStreamWriter extends BaseNsStreamWriter {
/*     */   public SimpleNsStreamWriter(XmlWriter xw, String enc, WriterConfig cfg) {
/*  51 */     super(xw, enc, cfg, false);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String nsURI, String localName, String value) throws XMLStreamException {
/*  71 */     if (!this.mStartElementOpen)
/*  72 */       throwOutputError(ErrorConsts.WERR_ATTR_NO_ELEM); 
/*  74 */     String prefix = this.mCurrElem.getExplicitPrefix(nsURI);
/*  75 */     if (!this.mReturnNullForDefaultNamespace && prefix == null)
/*  76 */       throwOutputError("Unbound namespace URI '" + nsURI + "'"); 
/*  78 */     doWriteAttr(localName, nsURI, prefix, value);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String prefix, String nsURI, String localName, String value) throws XMLStreamException {
/*  85 */     if (!this.mStartElementOpen)
/*  86 */       throwOutputError(ErrorConsts.WERR_ATTR_NO_ELEM); 
/*  88 */     doWriteAttr(localName, nsURI, prefix, value);
/*     */   }
/*     */   
/*     */   public void writeDefaultNamespace(String nsURI) throws XMLStreamException {
/* 100 */     if (!this.mStartElementOpen)
/* 101 */       throwOutputError("Trying to write a namespace declaration when there is no open start element."); 
/* 104 */     setDefaultNamespace(nsURI);
/* 105 */     doWriteDefaultNs(nsURI);
/*     */   }
/*     */   
/*     */   public void writeNamespace(String prefix, String nsURI) throws XMLStreamException {
/* 111 */     if (prefix == null || prefix.length() == 0 || prefix.equals("xmlns")) {
/* 113 */       writeDefaultNamespace(nsURI);
/*     */       return;
/*     */     } 
/* 119 */     if (!this.mStartElementOpen)
/* 120 */       throwOutputError("Trying to write a namespace declaration when there is no open start element."); 
/* 129 */     if (!this.mXml11 && 
/* 130 */       nsURI.length() == 0)
/* 131 */       throwOutputError(ErrorConsts.ERR_NS_EMPTY); 
/* 136 */     setPrefix(prefix, nsURI);
/* 137 */     doWriteNamespace(prefix, nsURI);
/*     */   }
/*     */   
/*     */   public void setDefaultNamespace(String uri) throws XMLStreamException {
/* 149 */     this.mCurrElem.setDefaultNsUri(uri);
/*     */   }
/*     */   
/*     */   public void doSetPrefix(String prefix, String uri) throws XMLStreamException {
/* 155 */     this.mCurrElem.addPrefix(prefix, uri);
/*     */   }
/*     */   
/*     */   public void writeStartElement(StartElement elem) throws XMLStreamException {
/* 161 */     QName name = elem.getName();
/* 162 */     Iterator it = elem.getNamespaces();
/* 164 */     while (it.hasNext()) {
/* 165 */       Namespace ns = it.next();
/* 167 */       String prefix = ns.getPrefix();
/* 168 */       if (prefix == null || prefix.length() == 0) {
/* 169 */         setDefaultNamespace(ns.getNamespaceURI());
/*     */         continue;
/*     */       } 
/* 171 */       setPrefix(prefix, ns.getNamespaceURI());
/*     */     } 
/* 185 */     String nsURI = name.getNamespaceURI();
/* 186 */     if (nsURI == null) {
/* 187 */       writeStartElement(name.getLocalPart());
/*     */     } else {
/* 189 */       String prefix = name.getPrefix();
/* 190 */       writeStartElement(prefix, name.getLocalPart(), nsURI);
/*     */     } 
/* 194 */     it = elem.getNamespaces();
/* 195 */     while (it.hasNext()) {
/* 196 */       Namespace ns = it.next();
/* 197 */       String prefix = ns.getPrefix();
/* 198 */       if (prefix == null || prefix.length() == 0) {
/* 199 */         writeDefaultNamespace(ns.getNamespaceURI());
/*     */         continue;
/*     */       } 
/* 201 */       writeNamespace(prefix, ns.getNamespaceURI());
/*     */     } 
/* 208 */     it = (Iterator)elem.getAttributes();
/* 209 */     while (it.hasNext()) {
/* 210 */       Attribute attr = it.next();
/* 211 */       name = attr.getName();
/* 212 */       nsURI = name.getNamespaceURI();
/* 214 */       if (nsURI != null && nsURI.length() > 0) {
/* 215 */         writeAttribute(name.getPrefix(), nsURI, name.getLocalPart(), attr.getValue());
/*     */         continue;
/*     */       } 
/* 218 */       writeAttribute(name.getLocalPart(), attr.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void writeStartOrEmpty(String localName, String nsURI) throws XMLStreamException {
/* 229 */     String prefix = this.mCurrElem.getPrefix(nsURI);
/* 230 */     if (prefix == null)
/* 231 */       throw new XMLStreamException("Unbound namespace URI '" + nsURI + "'"); 
/* 233 */     checkStartElement(localName, prefix);
/* 234 */     if (this.mValidator != null)
/* 235 */       this.mValidator.validateElementStart(localName, nsURI, prefix); 
/* 238 */     if (this.mOutputElemPool != null) {
/* 239 */       SimpleOutputElement newCurr = this.mOutputElemPool;
/* 240 */       this.mOutputElemPool = newCurr.reuseAsChild(this.mCurrElem, prefix, localName, nsURI);
/* 241 */       this.mPoolSize--;
/* 242 */       this.mCurrElem = newCurr;
/*     */     } else {
/* 244 */       this.mCurrElem = this.mCurrElem.createChild(prefix, localName, nsURI);
/*     */     } 
/* 246 */     doWriteStartTag(prefix, localName);
/*     */   }
/*     */   
/*     */   protected void writeStartOrEmpty(String prefix, String localName, String nsURI) throws XMLStreamException {
/* 252 */     checkStartElement(localName, prefix);
/* 253 */     if (this.mValidator != null)
/* 254 */       this.mValidator.validateElementStart(localName, nsURI, prefix); 
/* 257 */     if (this.mOutputElemPool != null) {
/* 258 */       SimpleOutputElement newCurr = this.mOutputElemPool;
/* 259 */       this.mOutputElemPool = newCurr.reuseAsChild(this.mCurrElem, prefix, localName, nsURI);
/* 260 */       this.mPoolSize--;
/* 261 */       this.mCurrElem = newCurr;
/*     */     } else {
/* 263 */       this.mCurrElem = this.mCurrElem.createChild(prefix, localName, nsURI);
/*     */     } 
/* 265 */     doWriteStartTag(prefix, localName);
/*     */   }
/*     */   
/*     */   public final void copyStartElement(InputElementStack elemStack, AttributeCollector attrCollector) throws IOException, XMLStreamException {
/* 282 */     int nsCount = elemStack.getCurrentNsCount();
/* 283 */     if (nsCount > 0)
/* 288 */       for (int i = 0; i < nsCount; i++) {
/* 289 */         String prefix = elemStack.getLocalNsPrefix(i);
/* 290 */         String uri = elemStack.getLocalNsURI(i);
/* 291 */         if (prefix == null || prefix.length() == 0) {
/* 292 */           setDefaultNamespace(uri);
/*     */         } else {
/* 294 */           setPrefix(prefix, uri);
/*     */         } 
/*     */       }  
/* 299 */     writeStartElement(elemStack.getPrefix(), elemStack.getLocalName(), elemStack.getNsURI());
/* 303 */     if (nsCount > 0)
/* 305 */       for (int i = 0; i < nsCount; i++) {
/* 306 */         String prefix = elemStack.getLocalNsPrefix(i);
/* 307 */         String uri = elemStack.getLocalNsURI(i);
/* 309 */         if (prefix == null || prefix.length() == 0) {
/* 310 */           writeDefaultNamespace(uri);
/*     */         } else {
/* 312 */           writeNamespace(prefix, uri);
/*     */         } 
/*     */       }  
/* 320 */     int attrCount = this.mCfgCopyDefaultAttrs ? attrCollector.getCount() : attrCollector.getSpecifiedCount();
/* 324 */     if (attrCount > 0)
/* 325 */       for (int i = 0; i < attrCount; i++)
/* 326 */         attrCollector.writeAttribute(i, this.mWriter);  
/*     */   }
/*     */   
/*     */   public String validateQNamePrefix(QName name) {
/* 334 */     return name.getPrefix();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\SimpleNsStreamWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */