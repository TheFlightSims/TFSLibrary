/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.sr.InputElementStack;
/*     */ import com.ctc.wstx.sr.NsDefaultProvider;
/*     */ import com.ctc.wstx.util.DataUtil;
/*     */ import com.ctc.wstx.util.ElementIdMap;
/*     */ import com.ctc.wstx.util.ExceptionUtil;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public abstract class DTDValidatorBase extends XMLValidator implements NsDefaultProvider {
/*     */   static final int DEFAULT_STACK_SIZE = 16;
/*     */   
/*     */   static final int EXP_MAX_ATTRS = 16;
/*     */   
/*  66 */   protected static final HashMap EMPTY_MAP = new HashMap();
/*     */   
/*     */   final boolean mHasNsDefaults;
/*     */   
/*     */   final DTDSubset mSchema;
/*     */   
/*     */   final ValidationContext mContext;
/*     */   
/*     */   final Map mElemSpecs;
/*     */   
/*     */   final Map mGeneralEntities;
/*     */   
/*     */   protected boolean mNormAttrs;
/*     */   
/* 124 */   protected DTDElement mCurrElem = null;
/*     */   
/* 130 */   protected DTDElement[] mElems = null;
/*     */   
/* 135 */   protected int mElemCount = 0;
/*     */   
/* 140 */   protected HashMap mCurrAttrDefs = null;
/*     */   
/* 147 */   protected DTDAttribute[] mAttrSpecs = new DTDAttribute[16];
/*     */   
/* 154 */   protected int mAttrCount = 0;
/*     */   
/* 162 */   protected int mIdAttrIndex = -1;
/*     */   
/* 170 */   protected final transient PrefixedName mTmpKey = new PrefixedName(null, null);
/*     */   
/* 176 */   char[] mTmpAttrValueBuffer = null;
/*     */   
/*     */   public DTDValidatorBase(DTDSubset schema, ValidationContext ctxt, boolean hasNsDefaults, Map elemSpecs, Map genEntities) {
/* 187 */     this.mSchema = schema;
/* 188 */     this.mContext = ctxt;
/* 189 */     this.mHasNsDefaults = hasNsDefaults;
/* 190 */     this.mElemSpecs = (elemSpecs == null || elemSpecs.size() == 0) ? Collections.EMPTY_MAP : elemSpecs;
/* 192 */     this.mGeneralEntities = genEntities;
/* 194 */     this.mNormAttrs = true;
/* 195 */     this.mElems = new DTDElement[16];
/*     */   }
/*     */   
/*     */   public void setAttrValueNormalization(boolean state) {
/* 213 */     this.mNormAttrs = state;
/*     */   }
/*     */   
/*     */   public abstract boolean reallyValidating();
/*     */   
/*     */   public final XMLValidationSchema getSchema() {
/* 229 */     return (XMLValidationSchema)this.mSchema;
/*     */   }
/*     */   
/*     */   public abstract void validateElementStart(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public abstract String validateAttribute(String paramString1, String paramString2, String paramString3, String paramString4) throws XMLStreamException;
/*     */   
/*     */   public abstract String validateAttribute(String paramString1, String paramString2, String paramString3, char[] paramArrayOfchar, int paramInt1, int paramInt2) throws XMLStreamException;
/*     */   
/*     */   public abstract int validateElementAndAttributes() throws XMLStreamException;
/*     */   
/*     */   public abstract int validateElementEnd(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public void validateText(String text, boolean lastTextSegment) throws XMLStreamException {}
/*     */   
/*     */   public void validateText(char[] cbuf, int textStart, int textEnd, boolean lastTextSegment) throws XMLStreamException {}
/*     */   
/*     */   public abstract void validationCompleted(boolean paramBoolean) throws XMLStreamException;
/*     */   
/*     */   public String getAttributeType(int index) {
/* 291 */     DTDAttribute attr = this.mAttrSpecs[index];
/* 292 */     return (attr == null) ? "CDATA" : attr.getValueTypeString();
/*     */   }
/*     */   
/*     */   public int getIdAttrIndex() {
/* 308 */     int ix = this.mIdAttrIndex;
/* 309 */     if (ix == -2) {
/* 310 */       ix = -1;
/* 311 */       if (this.mCurrElem != null) {
/* 312 */         DTDAttribute idAttr = this.mCurrElem.getIdAttribute();
/* 313 */         if (idAttr != null) {
/* 314 */           DTDAttribute[] attrs = this.mAttrSpecs;
/* 315 */           for (int i = 0, len = attrs.length; i < len; i++) {
/* 316 */             if (attrs[i] == idAttr) {
/* 317 */               ix = i;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 323 */       this.mIdAttrIndex = ix;
/*     */     } 
/* 325 */     return ix;
/*     */   }
/*     */   
/*     */   public int getNotationAttrIndex() {
/* 343 */     for (int i = 0, len = this.mAttrCount; i < len; i++) {
/* 344 */       if (this.mAttrSpecs[i].typeIsNotation())
/* 345 */         return i; 
/*     */     } 
/* 348 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean mayHaveNsDefaults(String elemPrefix, String elemLN) {
/* 365 */     this.mTmpKey.reset(elemPrefix, elemLN);
/* 366 */     DTDElement elem = (DTDElement)this.mElemSpecs.get(this.mTmpKey);
/* 367 */     this.mCurrElem = elem;
/* 368 */     return (elem != null && elem.hasNsDefaults());
/*     */   }
/*     */   
/*     */   public void checkNsDefaults(InputElementStack nsStack) throws XMLStreamException {
/* 375 */     HashMap m = this.mCurrElem.getNsDefaults();
/* 376 */     if (m != null) {
/* 377 */       Iterator it = m.entrySet().iterator();
/* 378 */       while (it.hasNext()) {
/* 379 */         Map.Entry me = it.next();
/* 380 */         String prefix = (String)me.getKey();
/* 381 */         if (!nsStack.isPrefixLocallyDeclared(prefix)) {
/* 382 */           DTDAttribute attr = (DTDAttribute)me.getValue();
/* 383 */           String uri = attr.getDefaultValue(this.mContext, this);
/* 384 */           nsStack.addNsBinding(prefix, uri);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   PrefixedName getElemName() {
/* 400 */     DTDElement elem = this.mElems[this.mElemCount - 1];
/* 401 */     return elem.getName();
/*     */   }
/*     */   
/*     */   Location getLocation() {
/* 405 */     return this.mContext.getValidationLocation();
/*     */   }
/*     */   
/*     */   protected abstract ElementIdMap getIdMap();
/*     */   
/*     */   Map getEntityMap() {
/* 411 */     return this.mGeneralEntities;
/*     */   }
/*     */   
/*     */   char[] getTempAttrValueBuffer(int neededLength) {
/* 416 */     if (this.mTmpAttrValueBuffer == null || this.mTmpAttrValueBuffer.length < neededLength) {
/* 418 */       int size = (neededLength < 100) ? 100 : neededLength;
/* 419 */       this.mTmpAttrValueBuffer = new char[size];
/*     */     } 
/* 421 */     return this.mTmpAttrValueBuffer;
/*     */   }
/*     */   
/*     */   public boolean hasNsDefaults() {
/* 425 */     return this.mHasNsDefaults;
/*     */   }
/*     */   
/*     */   void reportValidationProblem(String msg) throws XMLStreamException {
/* 442 */     doReportValidationProblem(msg, null);
/*     */   }
/*     */   
/*     */   void reportValidationProblem(String msg, Location loc) throws XMLStreamException {
/* 448 */     doReportValidationProblem(msg, loc);
/*     */   }
/*     */   
/*     */   void reportValidationProblem(String format, Object arg) throws XMLStreamException {
/* 454 */     doReportValidationProblem(MessageFormat.format(format, new Object[] { arg }), null);
/*     */   }
/*     */   
/*     */   void reportValidationProblem(String format, Object arg1, Object arg2) throws XMLStreamException {
/* 461 */     doReportValidationProblem(MessageFormat.format(format, new Object[] { arg1, arg2 }), null);
/*     */   }
/*     */   
/*     */   protected void doReportValidationProblem(String msg, Location loc) throws XMLStreamException {
/* 474 */     if (loc == null)
/* 475 */       loc = getLocation(); 
/* 477 */     XMLValidationProblem prob = new XMLValidationProblem(loc, msg, 2);
/* 478 */     prob.setReporter(this);
/* 479 */     this.mContext.reportProblem(prob);
/*     */   }
/*     */   
/*     */   protected void doAddDefaultValue(DTDAttribute attr) throws XMLStreamException {
/* 488 */     String def = attr.getDefaultValue(this.mContext, this);
/* 489 */     if (def == null)
/* 490 */       ExceptionUtil.throwInternal("null default attribute value"); 
/* 492 */     PrefixedName an = attr.getName();
/* 494 */     String prefix = an.getPrefix();
/* 495 */     String uri = "";
/* 496 */     if (prefix != null && prefix.length() > 0) {
/* 497 */       uri = this.mContext.getNamespaceURI(prefix);
/* 499 */       if (uri == null || uri.length() == 0) {
/* 504 */         reportValidationProblem("Unbound namespace prefix \"{0}\" for default attribute \"{1}\"", prefix, attr);
/* 506 */         uri = "";
/*     */       } 
/*     */     } 
/* 509 */     int defIx = this.mContext.addDefaultAttribute(an.getLocalName(), uri, prefix, def);
/* 510 */     if (defIx >= 0) {
/* 518 */       while (defIx >= this.mAttrSpecs.length)
/* 519 */         this.mAttrSpecs = (DTDAttribute[])DataUtil.growArrayBy50Pct(this.mAttrSpecs); 
/* 524 */       while (this.mAttrCount < defIx)
/* 525 */         this.mAttrSpecs[this.mAttrCount++] = null; 
/* 527 */       this.mAttrSpecs[defIx] = attr;
/* 528 */       this.mAttrCount = defIx + 1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDValidatorBase.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */