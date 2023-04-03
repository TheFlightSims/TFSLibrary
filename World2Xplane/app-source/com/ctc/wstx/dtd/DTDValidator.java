/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.util.DataUtil;
/*     */ import com.ctc.wstx.util.ElementId;
/*     */ import com.ctc.wstx.util.ElementIdMap;
/*     */ import com.ctc.wstx.util.StringUtil;
/*     */ import java.util.BitSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ 
/*     */ public class DTDValidator extends DTDValidatorBase {
/*     */   protected boolean mReportDuplicateErrors = false;
/*     */   
/*  61 */   protected ElementIdMap mIdMap = null;
/*     */   
/*  72 */   protected StructValidator[] mValidators = null;
/*     */   
/*  78 */   protected BitSet mCurrSpecialAttrs = null;
/*     */   
/*     */   boolean mCurrHasAnyFixed = false;
/*     */   
/*     */   BitSet mTmpSpecialAttrs;
/*     */   
/*     */   public DTDValidator(DTDSubset schema, ValidationContext ctxt, boolean hasNsDefaults, Map elemSpecs, Map genEntities) {
/* 104 */     super(schema, ctxt, hasNsDefaults, elemSpecs, genEntities);
/* 105 */     this.mValidators = new StructValidator[16];
/*     */   }
/*     */   
/*     */   public final boolean reallyValidating() {
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   public void validateElementStart(String localName, String uri, String prefix) throws XMLStreamException {
/* 130 */     this.mTmpKey.reset(prefix, localName);
/* 132 */     DTDElement elem = (DTDElement)this.mElemSpecs.get(this.mTmpKey);
/* 138 */     int elemCount = this.mElemCount++;
/* 139 */     if (elemCount >= this.mElems.length) {
/* 140 */       this.mElems = (DTDElement[])DataUtil.growArrayBy50Pct(this.mElems);
/* 141 */       this.mValidators = (StructValidator[])DataUtil.growArrayBy50Pct(this.mValidators);
/*     */     } 
/* 143 */     this.mElems[elemCount] = this.mCurrElem = elem;
/* 144 */     if (elem == null || !elem.isDefined())
/* 145 */       reportValidationProblem(ErrorConsts.ERR_VLD_UNKNOWN_ELEM, this.mTmpKey.toString()); 
/* 149 */     StructValidator pv = (elemCount > 0) ? this.mValidators[elemCount - 1] : null;
/* 151 */     if (pv != null && elem != null) {
/* 152 */       String msg = pv.tryToValidate(elem.getName());
/* 153 */       if (msg != null) {
/* 154 */         int ix = msg.indexOf("$END");
/* 155 */         String pname = this.mElems[elemCount - 1].toString();
/* 156 */         if (ix >= 0)
/* 157 */           msg = msg.substring(0, ix) + "</" + pname + ">" + msg.substring(ix + 4); 
/* 160 */         reportValidationProblem("Validation error, encountered element <" + elem.getName() + "> as a child of <" + pname + ">: " + msg);
/*     */       } 
/*     */     } 
/* 166 */     this.mAttrCount = 0;
/* 167 */     this.mIdAttrIndex = -2;
/* 170 */     if (elem == null) {
/* 171 */       this.mValidators[elemCount] = null;
/* 172 */       this.mCurrAttrDefs = EMPTY_MAP;
/* 173 */       this.mCurrHasAnyFixed = false;
/* 174 */       this.mCurrSpecialAttrs = null;
/*     */     } else {
/* 176 */       this.mValidators[elemCount] = elem.getValidator();
/* 177 */       this.mCurrAttrDefs = elem.getAttributes();
/* 178 */       if (this.mCurrAttrDefs == null)
/* 179 */         this.mCurrAttrDefs = EMPTY_MAP; 
/* 181 */       this.mCurrHasAnyFixed = elem.hasFixedAttrs();
/* 182 */       int specCount = elem.getSpecialCount();
/* 183 */       if (specCount == 0) {
/* 184 */         this.mCurrSpecialAttrs = null;
/*     */       } else {
/* 186 */         BitSet bs = this.mTmpSpecialAttrs;
/* 187 */         if (bs == null) {
/* 188 */           this.mTmpSpecialAttrs = bs = new BitSet(specCount);
/*     */         } else {
/* 190 */           bs.clear();
/*     */         } 
/* 192 */         this.mCurrSpecialAttrs = bs;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, String value) throws XMLStreamException {
/* 201 */     DTDAttribute attr = (DTDAttribute)this.mCurrAttrDefs.get(this.mTmpKey.reset(prefix, localName));
/* 202 */     if (attr == null) {
/* 204 */       if (this.mCurrElem != null)
/* 205 */         reportValidationProblem(ErrorConsts.ERR_VLD_UNKNOWN_ATTR, this.mCurrElem.toString(), this.mTmpKey.toString()); 
/* 211 */       return value;
/*     */     } 
/* 213 */     int index = this.mAttrCount++;
/* 214 */     if (index >= this.mAttrSpecs.length)
/* 215 */       this.mAttrSpecs = (DTDAttribute[])DataUtil.growArrayBy50Pct(this.mAttrSpecs); 
/* 217 */     this.mAttrSpecs[index] = attr;
/* 218 */     if (this.mCurrSpecialAttrs != null) {
/* 219 */       int specIndex = attr.getSpecialIndex();
/* 220 */       if (specIndex >= 0)
/* 221 */         this.mCurrSpecialAttrs.set(specIndex); 
/*     */     } 
/* 224 */     String result = attr.validate(this, value, this.mNormAttrs);
/* 225 */     if (this.mCurrHasAnyFixed && attr.isFixed()) {
/* 226 */       String act = (result == null) ? value : result;
/* 227 */       String exp = attr.getDefaultValue(this.mContext, this);
/* 228 */       if (!act.equals(exp))
/* 229 */         reportValidationProblem("Value of attribute \"" + attr + "\" (element <" + this.mCurrElem + ">) not \"" + exp + "\" as expected, but \"" + act + "\""); 
/*     */     } 
/* 232 */     return result;
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, char[] valueChars, int valueStart, int valueEnd) throws XMLStreamException {
/* 241 */     DTDAttribute attr = (DTDAttribute)this.mCurrAttrDefs.get(this.mTmpKey.reset(prefix, localName));
/* 242 */     if (attr == null) {
/* 244 */       if (this.mCurrElem != null)
/* 245 */         reportValidationProblem(ErrorConsts.ERR_VLD_UNKNOWN_ATTR, this.mCurrElem.toString(), this.mTmpKey.toString()); 
/* 251 */       return new String(valueChars, valueStart, valueEnd);
/*     */     } 
/* 253 */     int index = this.mAttrCount++;
/* 254 */     if (index >= this.mAttrSpecs.length)
/* 255 */       this.mAttrSpecs = (DTDAttribute[])DataUtil.growArrayBy50Pct(this.mAttrSpecs); 
/* 257 */     this.mAttrSpecs[index] = attr;
/* 258 */     if (this.mCurrSpecialAttrs != null) {
/* 259 */       int specIndex = attr.getSpecialIndex();
/* 260 */       if (specIndex >= 0)
/* 261 */         this.mCurrSpecialAttrs.set(specIndex); 
/*     */     } 
/* 264 */     String result = attr.validate(this, valueChars, valueStart, valueEnd, this.mNormAttrs);
/* 265 */     if (this.mCurrHasAnyFixed && attr.isFixed()) {
/*     */       boolean match;
/* 266 */       String exp = attr.getDefaultValue(this.mContext, this);
/* 268 */       if (result == null) {
/* 269 */         match = StringUtil.matches(exp, valueChars, valueStart, valueEnd - valueStart);
/*     */       } else {
/* 271 */         match = exp.equals(result);
/*     */       } 
/* 273 */       if (!match) {
/* 274 */         String act = (result == null) ? new String(valueChars, valueStart, valueEnd) : result;
/* 276 */         reportValidationProblem("Value of #FIXED attribute \"" + attr + "\" (element <" + this.mCurrElem + ">) not \"" + exp + "\" as expected, but \"" + act + "\"");
/*     */       } 
/*     */     } 
/* 279 */     return result;
/*     */   }
/*     */   
/*     */   public int validateElementAndAttributes() throws XMLStreamException {
/* 286 */     DTDElement elem = this.mCurrElem;
/* 287 */     if (elem == null)
/* 289 */       return 4; 
/* 293 */     if (this.mCurrSpecialAttrs != null) {
/* 294 */       BitSet specBits = this.mCurrSpecialAttrs;
/* 295 */       int specCount = elem.getSpecialCount();
/* 296 */       int ix = specBits.nextClearBit(0);
/* 297 */       while (ix < specCount) {
/* 298 */         List specAttrs = elem.getSpecialAttrs();
/* 299 */         DTDAttribute attr = specAttrs.get(ix);
/* 306 */         if (attr.isRequired()) {
/* 307 */           reportValidationProblem("Required attribute \"{0}\" missing from element <{1}>", attr, elem);
/*     */         } else {
/* 309 */           doAddDefaultValue(attr);
/*     */         } 
/* 311 */         ix = specBits.nextClearBit(ix + 1);
/*     */       } 
/*     */     } 
/* 315 */     return elem.getAllowedContent();
/*     */   }
/*     */   
/*     */   public int validateElementEnd(String localName, String uri, String prefix) throws XMLStreamException {
/* 326 */     int ix = this.mElemCount - 1;
/* 330 */     if (ix < 0)
/* 331 */       return 1; 
/* 333 */     this.mElemCount = ix;
/* 335 */     DTDElement closingElem = this.mElems[ix];
/* 336 */     this.mElems[ix] = null;
/* 337 */     StructValidator v = this.mValidators[ix];
/* 338 */     this.mValidators[ix] = null;
/* 341 */     if (v != null) {
/* 342 */       String msg = v.fullyValid();
/* 343 */       if (msg != null)
/* 344 */         reportValidationProblem("Validation error, element </" + closingElem + ">: " + msg); 
/*     */     } 
/* 350 */     if (ix < 1)
/* 352 */       return 1; 
/* 354 */     return this.mElems[ix - 1].getAllowedContent();
/*     */   }
/*     */   
/*     */   public void validationCompleted(boolean eod) throws XMLStreamException {
/* 366 */     checkIdRefs();
/*     */   }
/*     */   
/*     */   protected ElementIdMap getIdMap() {
/* 376 */     if (this.mIdMap == null)
/* 377 */       this.mIdMap = new ElementIdMap(); 
/* 379 */     return this.mIdMap;
/*     */   }
/*     */   
/*     */   protected void checkIdRefs() throws XMLStreamException {
/* 394 */     if (this.mIdMap != null) {
/* 395 */       ElementId ref = this.mIdMap.getFirstUndefined();
/* 396 */       if (ref != null)
/* 397 */         reportValidationProblem("Undefined id '" + ref.getId() + "': referenced from element <" + ref.getElemName() + ">, attribute '" + ref.getAttrName() + "'", ref.getLocation()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDValidator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */