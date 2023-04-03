/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.ExceptionUtil;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import com.ctc.wstx.util.WordResolver;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class DTDElement {
/*     */   final PrefixedName mName;
/*     */   
/*     */   final Location mLocation;
/*     */   
/*     */   StructValidator mValidator;
/*     */   
/*     */   int mAllowedContent;
/*     */   
/*     */   final boolean mNsAware;
/*     */   
/*     */   final boolean mXml11;
/*     */   
/*  85 */   HashMap mAttrMap = null;
/*     */   
/*  92 */   ArrayList mSpecAttrList = null;
/*     */   
/*     */   boolean mAnyFixed = false;
/*     */   
/*     */   boolean mAnyDefaults = false;
/*     */   
/*     */   boolean mValidateAttrs = false;
/*     */   
/*     */   DTDAttribute mIdAttr;
/*     */   
/*     */   DTDAttribute mNotationAttr;
/*     */   
/* 134 */   HashMap mNsDefaults = null;
/*     */   
/*     */   private DTDElement(Location loc, PrefixedName name, StructValidator val, int allowedContent, boolean nsAware, boolean xml11) {
/* 146 */     this.mName = name;
/* 147 */     this.mLocation = loc;
/* 148 */     this.mValidator = val;
/* 149 */     this.mAllowedContent = allowedContent;
/* 150 */     this.mNsAware = nsAware;
/* 151 */     this.mXml11 = xml11;
/*     */   }
/*     */   
/*     */   public static DTDElement createDefined(ReaderConfig cfg, Location loc, PrefixedName name, StructValidator val, int allowedContent) {
/* 161 */     if (allowedContent == 5)
/* 162 */       ExceptionUtil.throwInternal("trying to use XMLValidator.CONTENT_ALLOW_UNDEFINED via createDefined()"); 
/* 164 */     return new DTDElement(loc, name, val, allowedContent, cfg.willSupportNamespaces(), cfg.isXml11());
/*     */   }
/*     */   
/*     */   public static DTDElement createPlaceholder(ReaderConfig cfg, Location loc, PrefixedName name) {
/* 174 */     return new DTDElement(loc, name, null, 5, cfg.willSupportNamespaces(), cfg.isXml11());
/*     */   }
/*     */   
/*     */   public DTDElement define(Location loc, StructValidator val, int allowedContent) {
/* 186 */     verifyUndefined();
/* 187 */     if (allowedContent == 5)
/* 188 */       ExceptionUtil.throwInternal("trying to use CONTENT_ALLOW_UNDEFINED via define()"); 
/* 191 */     DTDElement elem = new DTDElement(loc, this.mName, val, allowedContent, this.mNsAware, this.mXml11);
/* 195 */     elem.mAttrMap = this.mAttrMap;
/* 196 */     elem.mSpecAttrList = this.mSpecAttrList;
/* 197 */     elem.mAnyFixed = this.mAnyFixed;
/* 198 */     elem.mValidateAttrs = this.mValidateAttrs;
/* 199 */     elem.mAnyDefaults = this.mAnyDefaults;
/* 200 */     elem.mIdAttr = this.mIdAttr;
/* 201 */     elem.mNotationAttr = this.mNotationAttr;
/* 202 */     elem.mNsDefaults = this.mNsDefaults;
/* 204 */     return elem;
/*     */   }
/*     */   
/*     */   public void defineFrom(InputProblemReporter rep, DTDElement definedElem, boolean fullyValidate) throws XMLStreamException {
/* 215 */     if (fullyValidate)
/* 216 */       verifyUndefined(); 
/* 218 */     this.mValidator = definedElem.mValidator;
/* 219 */     this.mAllowedContent = definedElem.mAllowedContent;
/* 220 */     mergeMissingAttributesFrom(rep, definedElem, fullyValidate);
/*     */   }
/*     */   
/*     */   private void verifyUndefined() {
/* 225 */     if (this.mAllowedContent != 5)
/* 226 */       ExceptionUtil.throwInternal("redefining defined element spec"); 
/*     */   }
/*     */   
/*     */   public DTDAttribute addAttribute(InputProblemReporter rep, PrefixedName attrName, int valueType, DefaultAttrValue defValue, WordResolver enumValues, boolean fullyValidate) throws XMLStreamException {
/*     */     DTDAttribute attr;
/* 244 */     HashMap m = this.mAttrMap;
/* 245 */     if (m == null)
/* 246 */       this.mAttrMap = m = new HashMap(); 
/* 249 */     List specList = defValue.isSpecial() ? getSpecialList() : null;
/* 252 */     int specIndex = (specList == null) ? -1 : specList.size();
/* 254 */     switch (valueType) {
/*     */       case 0:
/* 256 */         attr = new DTDCdataAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       case 1:
/* 260 */         attr = new DTDEnumAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11, enumValues);
/*     */         break;
/*     */       case 2:
/* 269 */         attr = new DTDIdAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       case 3:
/* 273 */         attr = new DTDIdRefAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       case 4:
/* 277 */         attr = new DTDIdRefsAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       case 5:
/* 281 */         attr = new DTDEntityAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       case 6:
/* 285 */         attr = new DTDEntitiesAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       case 7:
/* 289 */         attr = new DTDNotationAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11, enumValues);
/*     */         break;
/*     */       case 8:
/* 293 */         attr = new DTDNmTokenAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       case 9:
/* 297 */         attr = new DTDNmTokensAttr(attrName, defValue, specIndex, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       default:
/* 302 */         ExceptionUtil.throwGenericInternal();
/* 303 */         attr = null;
/*     */         break;
/*     */     } 
/* 306 */     DTDAttribute old = doAddAttribute(m, rep, attr, specList, fullyValidate);
/* 307 */     return (old == null) ? attr : null;
/*     */   }
/*     */   
/*     */   public DTDAttribute addNsDefault(InputProblemReporter rep, PrefixedName attrName, int valueType, DefaultAttrValue defValue, boolean fullyValidate) throws XMLStreamException {
/*     */     DTDAttribute nsAttr;
/* 329 */     switch (valueType) {
/*     */       case 0:
/* 331 */         nsAttr = new DTDCdataAttr(attrName, defValue, -1, this.mNsAware, this.mXml11);
/*     */         break;
/*     */       default:
/* 334 */         nsAttr = new DTDNmTokenAttr(attrName, defValue, -1, this.mNsAware, this.mXml11);
/*     */         break;
/*     */     } 
/* 339 */     String prefix = attrName.getPrefix();
/* 340 */     if (prefix == null || prefix.length() == 0) {
/* 341 */       prefix = "";
/*     */     } else {
/* 343 */       prefix = attrName.getLocalName();
/*     */     } 
/* 346 */     if (this.mNsDefaults == null) {
/* 347 */       this.mNsDefaults = new HashMap();
/* 349 */     } else if (this.mNsDefaults.containsKey(prefix)) {
/* 350 */       return null;
/*     */     } 
/* 353 */     this.mNsDefaults.put(prefix, nsAttr);
/* 354 */     return nsAttr;
/*     */   }
/*     */   
/*     */   public void mergeMissingAttributesFrom(InputProblemReporter rep, DTDElement other, boolean fullyValidate) throws XMLStreamException {
/* 361 */     Map otherMap = other.getAttributes();
/* 362 */     HashMap m = this.mAttrMap;
/* 363 */     if (m == null)
/* 364 */       this.mAttrMap = m = new HashMap(); 
/* 369 */     if (otherMap != null && otherMap.size() > 0) {
/* 370 */       Iterator it = otherMap.entrySet().iterator();
/* 371 */       while (it.hasNext()) {
/* 372 */         Map.Entry me = it.next();
/* 373 */         Object key = me.getKey();
/* 375 */         if (!m.containsKey(key)) {
/*     */           List specList;
/* 377 */           DTDAttribute newAttr = (DTDAttribute)me.getValue();
/* 380 */           if (newAttr.isSpecial()) {
/* 381 */             specList = getSpecialList();
/* 382 */             newAttr = newAttr.cloneWith(specList.size());
/*     */           } else {
/* 384 */             specList = null;
/*     */           } 
/* 386 */           doAddAttribute(m, rep, newAttr, specList, fullyValidate);
/*     */         } 
/*     */       } 
/*     */     } 
/* 391 */     HashMap otherNs = other.mNsDefaults;
/* 392 */     if (otherNs != null) {
/* 393 */       if (this.mNsDefaults == null)
/* 394 */         this.mNsDefaults = new HashMap(); 
/* 396 */       Iterator it = otherNs.entrySet().iterator();
/* 397 */       while (it.hasNext()) {
/* 398 */         Map.Entry me = it.next();
/* 399 */         Object key = me.getKey();
/* 401 */         if (!this.mNsDefaults.containsKey(key))
/* 402 */           this.mNsDefaults.put(key, me.getValue()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private DTDAttribute doAddAttribute(Map attrMap, InputProblemReporter rep, DTDAttribute attr, List specList, boolean fullyValidate) throws XMLStreamException {
/* 417 */     PrefixedName attrName = attr.getName();
/* 420 */     DTDAttribute old = (DTDAttribute)attrMap.get(attrName);
/* 421 */     if (old != null) {
/* 422 */       rep.reportProblem(null, ErrorConsts.WT_ATTR_DECL, ErrorConsts.W_DTD_DUP_ATTR, attrName, this.mName);
/* 424 */       return old;
/*     */     } 
/* 427 */     switch (attr.getValueType()) {
/*     */       case 2:
/* 430 */         if (fullyValidate && this.mIdAttr != null)
/* 431 */           rep.throwParseError("Invalid id attribute \"{0}\" for element <{1}>: already had id attribute \"" + this.mIdAttr.getName() + "\"", attrName, this.mName); 
/* 433 */         this.mIdAttr = attr;
/*     */         break;
/*     */       case 7:
/* 438 */         if (fullyValidate && this.mNotationAttr != null)
/* 439 */           rep.throwParseError("Invalid notation attribute '" + attrName + "' for element <" + this.mName + ">: already had notation attribute '" + this.mNotationAttr.getName() + "'"); 
/* 441 */         this.mNotationAttr = attr;
/*     */         break;
/*     */     } 
/* 445 */     attrMap.put(attrName, attr);
/* 446 */     if (specList != null)
/* 447 */       specList.add(attr); 
/* 449 */     if (!this.mAnyFixed)
/* 450 */       this.mAnyFixed = attr.isFixed(); 
/* 452 */     if (!this.mValidateAttrs)
/* 453 */       this.mValidateAttrs = attr.needsValidation(); 
/* 455 */     if (!this.mAnyDefaults)
/* 456 */       this.mAnyDefaults = attr.hasDefaultValue(); 
/* 459 */     return null;
/*     */   }
/*     */   
/*     */   public PrefixedName getName() {
/* 468 */     return this.mName;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 471 */     return this.mName.toString();
/*     */   }
/*     */   
/*     */   public String getDisplayName() {
/* 475 */     return this.mName.toString();
/*     */   }
/*     */   
/*     */   public Location getLocation() {
/* 478 */     return this.mLocation;
/*     */   }
/*     */   
/*     */   public boolean isDefined() {
/* 481 */     return (this.mAllowedContent != 5);
/*     */   }
/*     */   
/*     */   public int getAllowedContent() {
/* 489 */     return this.mAllowedContent;
/*     */   }
/*     */   
/*     */   public int getAllowedContentIfSpace() {
/* 501 */     int vld = this.mAllowedContent;
/* 502 */     return (vld <= 1) ? 2 : 4;
/*     */   }
/*     */   
/*     */   public HashMap getAttributes() {
/* 508 */     return this.mAttrMap;
/*     */   }
/*     */   
/*     */   public int getSpecialCount() {
/* 512 */     return (this.mSpecAttrList == null) ? 0 : this.mSpecAttrList.size();
/*     */   }
/*     */   
/*     */   public List getSpecialAttrs() {
/* 516 */     return this.mSpecAttrList;
/*     */   }
/*     */   
/*     */   public boolean attrsNeedValidation() {
/* 524 */     return this.mValidateAttrs;
/*     */   }
/*     */   
/*     */   public boolean hasFixedAttrs() {
/* 528 */     return this.mAnyFixed;
/*     */   }
/*     */   
/*     */   public boolean hasAttrDefaultValues() {
/* 532 */     return this.mAnyDefaults;
/*     */   }
/*     */   
/*     */   public DTDAttribute getIdAttribute() {
/* 536 */     return this.mIdAttr;
/*     */   }
/*     */   
/*     */   public DTDAttribute getNotationAttribute() {
/* 540 */     return this.mNotationAttr;
/*     */   }
/*     */   
/*     */   public boolean hasNsDefaults() {
/* 544 */     return (this.mNsDefaults != null);
/*     */   }
/*     */   
/*     */   public StructValidator getValidator() {
/* 555 */     return (this.mValidator == null) ? null : this.mValidator.newInstance();
/*     */   }
/*     */   
/*     */   protected HashMap getNsDefaults() {
/* 559 */     return this.mNsDefaults;
/*     */   }
/*     */   
/*     */   private List getSpecialList() {
/* 570 */     ArrayList l = this.mSpecAttrList;
/* 571 */     if (l == null)
/* 572 */       this.mSpecAttrList = l = new ArrayList(); 
/* 574 */     return l;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDElement.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */