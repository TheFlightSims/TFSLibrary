/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.util.DataUtil;
/*     */ import com.ctc.wstx.util.ElementIdMap;
/*     */ import com.ctc.wstx.util.ExceptionUtil;
/*     */ import java.util.BitSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ 
/*     */ public class DTDTypingNonValidator extends DTDValidatorBase {
/*     */   protected boolean mHasAttrDefaults = false;
/*     */   
/*  55 */   protected BitSet mCurrDefaultAttrs = null;
/*     */   
/*     */   protected boolean mHasNormalizableAttrs = false;
/*     */   
/*     */   BitSet mTmpDefaultAttrs;
/*     */   
/*     */   public DTDTypingNonValidator(DTDSubset schema, ValidationContext ctxt, boolean hasNsDefaults, Map elemSpecs, Map genEntities) {
/*  84 */     super(schema, ctxt, hasNsDefaults, elemSpecs, genEntities);
/*     */   }
/*     */   
/*     */   public final boolean reallyValidating() {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public void setAttrValueNormalization(boolean state) {}
/*     */   
/*     */   public void validateElementStart(String localName, String uri, String prefix) throws XMLStreamException {
/* 118 */     this.mTmpKey.reset(prefix, localName);
/* 119 */     DTDElement elem = (DTDElement)this.mElemSpecs.get(this.mTmpKey);
/* 121 */     int elemCount = this.mElemCount++;
/* 122 */     if (elemCount >= this.mElems.length)
/* 123 */       this.mElems = (DTDElement[])DataUtil.growArrayBy50Pct(this.mElems); 
/* 126 */     this.mElems[elemCount] = this.mCurrElem = elem;
/* 127 */     this.mAttrCount = 0;
/* 128 */     this.mIdAttrIndex = -2;
/* 135 */     if (elem == null) {
/* 136 */       this.mCurrAttrDefs = EMPTY_MAP;
/* 137 */       this.mHasAttrDefaults = false;
/* 138 */       this.mCurrDefaultAttrs = null;
/* 139 */       this.mHasNormalizableAttrs = false;
/*     */       return;
/*     */     } 
/* 144 */     this.mCurrAttrDefs = elem.getAttributes();
/* 145 */     if (this.mCurrAttrDefs == null) {
/* 146 */       this.mCurrAttrDefs = EMPTY_MAP;
/* 147 */       this.mHasAttrDefaults = false;
/* 148 */       this.mCurrDefaultAttrs = null;
/* 149 */       this.mHasNormalizableAttrs = false;
/*     */       return;
/*     */     } 
/* 154 */     this.mHasNormalizableAttrs = (this.mNormAttrs || elem.attrsNeedValidation());
/* 157 */     this.mHasAttrDefaults = elem.hasAttrDefaultValues();
/* 158 */     if (this.mHasAttrDefaults) {
/* 162 */       int specCount = elem.getSpecialCount();
/* 163 */       BitSet bs = this.mTmpDefaultAttrs;
/* 164 */       if (bs == null) {
/* 165 */         this.mTmpDefaultAttrs = bs = new BitSet(specCount);
/*     */       } else {
/* 167 */         bs.clear();
/*     */       } 
/* 169 */       this.mCurrDefaultAttrs = bs;
/*     */     } else {
/* 171 */       this.mCurrDefaultAttrs = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, String value) throws XMLStreamException {
/* 187 */     DTDAttribute attr = (DTDAttribute)this.mCurrAttrDefs.get(this.mTmpKey.reset(prefix, localName));
/* 188 */     int index = this.mAttrCount++;
/* 189 */     if (index >= this.mAttrSpecs.length)
/* 190 */       this.mAttrSpecs = (DTDAttribute[])DataUtil.growArrayBy50Pct(this.mAttrSpecs); 
/* 192 */     this.mAttrSpecs[index] = attr;
/* 197 */     if (attr != null) {
/* 198 */       if (this.mHasAttrDefaults) {
/* 202 */         int specIndex = attr.getSpecialIndex();
/* 203 */         if (specIndex >= 0)
/* 204 */           this.mCurrDefaultAttrs.set(specIndex); 
/*     */       } 
/* 207 */       if (this.mHasNormalizableAttrs);
/*     */     } 
/* 211 */     return null;
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, char[] valueChars, int valueStart, int valueEnd) throws XMLStreamException {
/* 221 */     DTDAttribute attr = (DTDAttribute)this.mCurrAttrDefs.get(this.mTmpKey.reset(prefix, localName));
/* 222 */     int index = this.mAttrCount++;
/* 223 */     if (index >= this.mAttrSpecs.length)
/* 224 */       this.mAttrSpecs = (DTDAttribute[])DataUtil.growArrayBy50Pct(this.mAttrSpecs); 
/* 226 */     this.mAttrSpecs[index] = attr;
/* 227 */     if (attr != null) {
/* 228 */       if (this.mHasAttrDefaults) {
/* 229 */         int specIndex = attr.getSpecialIndex();
/* 230 */         if (specIndex >= 0)
/* 231 */           this.mCurrDefaultAttrs.set(specIndex); 
/*     */       } 
/* 234 */       if (this.mHasNormalizableAttrs)
/* 235 */         return attr.normalize(this, valueChars, valueStart, valueEnd); 
/*     */     } 
/* 238 */     return null;
/*     */   }
/*     */   
/*     */   public int validateElementAndAttributes() throws XMLStreamException {
/* 248 */     DTDElement elem = this.mCurrElem;
/* 249 */     if (this.mHasAttrDefaults) {
/* 250 */       BitSet specBits = this.mCurrDefaultAttrs;
/* 251 */       int specCount = elem.getSpecialCount();
/* 252 */       int ix = specBits.nextClearBit(0);
/* 253 */       while (ix < specCount) {
/* 254 */         List specAttrs = elem.getSpecialAttrs();
/* 255 */         DTDAttribute attr = specAttrs.get(ix);
/* 256 */         if (attr.hasDefaultValue())
/* 257 */           doAddDefaultValue(attr); 
/* 259 */         ix = specBits.nextClearBit(ix + 1);
/*     */       } 
/*     */     } 
/* 269 */     return (elem == null) ? 4 : elem.getAllowedContentIfSpace();
/*     */   }
/*     */   
/*     */   public int validateElementEnd(String localName, String uri, String prefix) throws XMLStreamException {
/* 279 */     int ix = --this.mElemCount;
/* 280 */     this.mElems[ix] = null;
/* 281 */     if (ix < 1)
/* 282 */       return 4; 
/* 284 */     DTDElement elem = this.mElems[ix - 1];
/* 285 */     return (elem == null) ? 4 : this.mElems[ix - 1].getAllowedContentIfSpace();
/*     */   }
/*     */   
/*     */   public void validationCompleted(boolean eod) {}
/*     */   
/*     */   protected ElementIdMap getIdMap() {
/* 311 */     ExceptionUtil.throwGenericInternal();
/* 312 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDTypingNonValidator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */