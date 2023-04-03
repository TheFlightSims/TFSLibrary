/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.exc.WstxParsingException;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.DataUtil;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.NotationDeclaration;
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public final class DTDSubsetImpl extends DTDSubset {
/*     */   final boolean mIsCachable;
/*     */   
/*     */   final boolean mFullyValidating;
/*     */   
/*     */   final boolean mHasNsDefaults;
/*     */   
/*     */   final HashMap mGeneralEntities;
/*     */   
/*  77 */   volatile transient List mGeneralEntityList = null;
/*     */   
/*     */   final Set mRefdGEs;
/*     */   
/*     */   final HashMap mDefinedPEs;
/*     */   
/*     */   final Set mRefdPEs;
/*     */   
/*     */   final HashMap mNotations;
/*     */   
/* 135 */   transient List mNotationList = null;
/*     */   
/*     */   final HashMap mElements;
/*     */   
/*     */   private DTDSubsetImpl(boolean cachable, HashMap genEnt, Set refdGEs, HashMap paramEnt, Set peRefs, HashMap notations, HashMap elements, boolean fullyValidating) {
/* 158 */     this.mIsCachable = cachable;
/* 159 */     this.mGeneralEntities = genEnt;
/* 160 */     this.mRefdGEs = refdGEs;
/* 161 */     this.mDefinedPEs = paramEnt;
/* 162 */     this.mRefdPEs = peRefs;
/* 163 */     this.mNotations = notations;
/* 164 */     this.mElements = elements;
/* 165 */     this.mFullyValidating = fullyValidating;
/* 167 */     boolean anyNsDefs = false;
/* 168 */     if (elements != null) {
/* 169 */       Iterator it = elements.values().iterator();
/* 170 */       while (it.hasNext()) {
/* 171 */         DTDElement elem = it.next();
/* 172 */         if (elem.hasNsDefaults()) {
/* 173 */           anyNsDefs = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 178 */     this.mHasNsDefaults = anyNsDefs;
/*     */   }
/*     */   
/*     */   public static DTDSubsetImpl constructInstance(boolean cachable, HashMap genEnt, Set refdGEs, HashMap paramEnt, Set refdPEs, HashMap notations, HashMap elements, boolean fullyValidating) {
/* 187 */     return new DTDSubsetImpl(cachable, genEnt, refdGEs, paramEnt, refdPEs, notations, elements, fullyValidating);
/*     */   }
/*     */   
/*     */   public DTDSubset combineWithExternalSubset(InputProblemReporter rep, DTDSubset extSubset) throws XMLStreamException {
/* 203 */     HashMap ge1 = getGeneralEntityMap();
/* 204 */     HashMap ge2 = extSubset.getGeneralEntityMap();
/* 205 */     if (ge1 == null || ge1.isEmpty()) {
/* 206 */       ge1 = ge2;
/* 208 */     } else if (ge2 != null && !ge2.isEmpty()) {
/* 213 */       combineMaps(ge1, ge2);
/*     */     } 
/* 218 */     HashMap n1 = getNotationMap();
/* 219 */     HashMap n2 = extSubset.getNotationMap();
/* 220 */     if (n1 == null || n1.isEmpty()) {
/* 221 */       n1 = n2;
/* 223 */     } else if (n2 != null && !n2.isEmpty()) {
/* 227 */       checkNotations(n1, n2);
/* 233 */       combineMaps(n1, n2);
/*     */     } 
/* 239 */     HashMap e1 = getElementMap();
/* 240 */     HashMap e2 = extSubset.getElementMap();
/* 241 */     if (e1 == null || e1.isEmpty()) {
/* 242 */       e1 = e2;
/* 244 */     } else if (e2 != null && !e2.isEmpty()) {
/* 249 */       combineElements(rep, e1, e2);
/*     */     } 
/* 256 */     return constructInstance(false, ge1, null, null, null, n1, e1, this.mFullyValidating);
/*     */   }
/*     */   
/*     */   public XMLValidator createValidator(ValidationContext ctxt) throws XMLStreamException {
/* 269 */     if (this.mFullyValidating)
/* 270 */       return new DTDValidator(this, ctxt, this.mHasNsDefaults, getElementMap(), getGeneralEntityMap()); 
/* 273 */     return new DTDTypingNonValidator(this, ctxt, this.mHasNsDefaults, getElementMap(), getGeneralEntityMap());
/*     */   }
/*     */   
/*     */   public int getEntityCount() {
/* 285 */     return (this.mGeneralEntities == null) ? 0 : this.mGeneralEntities.size();
/*     */   }
/*     */   
/*     */   public int getNotationCount() {
/* 289 */     return (this.mNotations == null) ? 0 : this.mNotations.size();
/*     */   }
/*     */   
/*     */   public boolean isCachable() {
/* 299 */     return this.mIsCachable;
/*     */   }
/*     */   
/*     */   public HashMap getGeneralEntityMap() {
/* 303 */     return this.mGeneralEntities;
/*     */   }
/*     */   
/*     */   public List getGeneralEntityList() {
/* 308 */     List l = this.mGeneralEntityList;
/* 309 */     if (l == null) {
/* 310 */       if (this.mGeneralEntities == null || this.mGeneralEntities.size() == 0) {
/* 311 */         l = Collections.EMPTY_LIST;
/*     */       } else {
/* 313 */         l = Collections.unmodifiableList(new ArrayList(this.mGeneralEntities.values()));
/*     */       } 
/* 315 */       this.mGeneralEntityList = l;
/*     */     } 
/* 318 */     return l;
/*     */   }
/*     */   
/*     */   public HashMap getParameterEntityMap() {
/* 322 */     return this.mDefinedPEs;
/*     */   }
/*     */   
/*     */   public HashMap getNotationMap() {
/* 326 */     return this.mNotations;
/*     */   }
/*     */   
/*     */   public synchronized List getNotationList() {
/* 331 */     List l = this.mNotationList;
/* 332 */     if (l == null) {
/* 333 */       if (this.mNotations == null || this.mNotations.size() == 0) {
/* 334 */         l = Collections.EMPTY_LIST;
/*     */       } else {
/* 336 */         l = Collections.unmodifiableList(new ArrayList(this.mNotations.values()));
/*     */       } 
/* 338 */       this.mNotationList = l;
/*     */     } 
/* 341 */     return l;
/*     */   }
/*     */   
/*     */   public HashMap getElementMap() {
/* 345 */     return this.mElements;
/*     */   }
/*     */   
/*     */   public boolean isReusableWith(DTDSubset intSubset) {
/* 359 */     Set refdPEs = this.mRefdPEs;
/* 361 */     if (refdPEs != null && refdPEs.size() > 0) {
/* 362 */       HashMap intPEs = intSubset.getParameterEntityMap();
/* 363 */       if (intPEs != null && intPEs.size() > 0 && 
/* 364 */         DataUtil.anyValuesInCommon(refdPEs, intPEs.keySet()))
/* 365 */         return false; 
/*     */     } 
/* 369 */     Set refdGEs = this.mRefdGEs;
/* 371 */     if (refdGEs != null && refdGEs.size() > 0) {
/* 372 */       HashMap intGEs = intSubset.getGeneralEntityMap();
/* 373 */       if (intGEs != null && intGEs.size() > 0 && 
/* 374 */         DataUtil.anyValuesInCommon(refdGEs, intGEs.keySet()))
/* 375 */         return false; 
/*     */     } 
/* 379 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 389 */     StringBuffer sb = new StringBuffer();
/* 390 */     sb.append("[DTDSubset: ");
/* 391 */     int count = getEntityCount();
/* 392 */     sb.append(count);
/* 393 */     sb.append(" general entities");
/* 394 */     sb.append(']');
/* 395 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static void throwNotationException(NotationDeclaration oldDecl, NotationDeclaration newDecl) throws XMLStreamException {
/* 407 */     throw new WstxParsingException(MessageFormat.format(ErrorConsts.ERR_DTD_NOTATION_REDEFD, new Object[] { newDecl.getName(), oldDecl.getLocation().toString() }), newDecl.getLocation());
/*     */   }
/*     */   
/*     */   public static void throwElementException(DTDElement oldElem, Location loc) throws XMLStreamException {
/* 418 */     throw new WstxParsingException(MessageFormat.format(ErrorConsts.ERR_DTD_ELEM_REDEFD, new Object[] { oldElem.getDisplayName(), oldElem.getLocation().toString() }), loc);
/*     */   }
/*     */   
/*     */   private static void combineMaps(HashMap m1, HashMap m2) {
/* 439 */     Iterator it = m2.entrySet().iterator();
/* 440 */     while (it.hasNext()) {
/* 441 */       Map.Entry me = it.next();
/* 442 */       Object key = me.getKey();
/* 446 */       Object old = m1.put(key, me.getValue());
/* 448 */       if (old != null)
/* 449 */         m1.put(key, old); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void combineElements(InputProblemReporter rep, HashMap intElems, HashMap extElems) throws XMLStreamException {
/* 464 */     Iterator it = extElems.entrySet().iterator();
/* 465 */     while (it.hasNext()) {
/* 466 */       Map.Entry me = it.next();
/* 467 */       Object key = me.getKey();
/* 468 */       Object extVal = me.getValue();
/* 469 */       Object oldVal = intElems.get(key);
/* 472 */       if (oldVal == null) {
/* 473 */         intElems.put(key, extVal);
/*     */         continue;
/*     */       } 
/* 477 */       DTDElement extElem = (DTDElement)extVal;
/* 478 */       DTDElement intElem = (DTDElement)oldVal;
/* 481 */       if (extElem.isDefined()) {
/* 482 */         if (intElem.isDefined()) {
/* 483 */           throwElementException(intElem, extElem.getLocation());
/*     */           continue;
/*     */         } 
/* 490 */         intElem.defineFrom(rep, extElem, this.mFullyValidating);
/*     */         continue;
/*     */       } 
/* 493 */       if (!intElem.isDefined()) {
/* 497 */         rep.reportProblem(intElem.getLocation(), ErrorConsts.WT_ENT_DECL, ErrorConsts.W_UNDEFINED_ELEM, extElem.getDisplayName(), null);
/*     */         continue;
/*     */       } 
/* 503 */       intElem.mergeMissingAttributesFrom(rep, extElem, this.mFullyValidating);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkNotations(HashMap fromInt, HashMap fromExt) throws XMLStreamException {
/* 518 */     Iterator it = fromExt.entrySet().iterator();
/* 519 */     while (it.hasNext()) {
/* 520 */       Map.Entry en = it.next();
/* 521 */       if (fromInt.containsKey(en.getKey()))
/* 522 */         throwNotationException((NotationDeclaration)fromInt.get(en.getKey()), (NotationDeclaration)en.getValue()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDSubsetImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */