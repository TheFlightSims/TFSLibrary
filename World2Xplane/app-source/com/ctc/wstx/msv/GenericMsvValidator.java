/*     */ package com.ctc.wstx.msv;
/*     */ 
/*     */ import com.ctc.wstx.util.ElementId;
/*     */ import com.ctc.wstx.util.ElementIdMap;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import com.ctc.wstx.util.TextAccumulator;
/*     */ import com.sun.msv.grammar.IDContextProvider2;
/*     */ import com.sun.msv.util.DatatypeRef;
/*     */ import com.sun.msv.util.StartTagInfo;
/*     */ import com.sun.msv.util.StringRef;
/*     */ import com.sun.msv.verifier.Acceptor;
/*     */ import com.sun.msv.verifier.DocumentDeclaration;
/*     */ import com.sun.msv.verifier.regexp.StringToken;
/*     */ import java.util.ArrayList;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ import org.relaxng.datatype.Datatype;
/*     */ 
/*     */ public final class GenericMsvValidator extends XMLValidator implements IDContextProvider2 {
/*     */   protected final XMLValidationSchema mParentSchema;
/*     */   
/*     */   protected final ValidationContext mContext;
/*     */   
/*     */   protected final DocumentDeclaration mVGM;
/*     */   
/*  72 */   protected final ArrayList mAcceptors = new ArrayList();
/*     */   
/*  74 */   protected Acceptor mCurrAcceptor = null;
/*     */   
/*  76 */   protected final TextAccumulator mTextAccumulator = new TextAccumulator();
/*     */   
/*     */   protected ElementIdMap mIdDefs;
/*     */   
/*     */   protected String mCurrAttrPrefix;
/*     */   
/*     */   protected String mCurrAttrLocalName;
/*     */   
/*     */   protected XMLValidationProblem mProblem;
/*     */   
/* 109 */   final StringRef mErrorRef = new StringRef();
/*     */   
/* 115 */   final StartTagInfo mStartTag = new StartTagInfo("", "", "", null, (IDContextProvider2)null);
/*     */   
/*     */   final AttributeProxy mAttributeProxy;
/*     */   
/*     */   public GenericMsvValidator(XMLValidationSchema parent, ValidationContext ctxt, DocumentDeclaration vgm) {
/* 132 */     this.mParentSchema = parent;
/* 133 */     this.mContext = ctxt;
/* 134 */     this.mVGM = vgm;
/* 136 */     this.mCurrAcceptor = this.mVGM.createAcceptor();
/* 137 */     this.mAttributeProxy = new AttributeProxy(ctxt);
/*     */   }
/*     */   
/*     */   public String getBaseUri() {
/* 152 */     return this.mContext.getBaseUri();
/*     */   }
/*     */   
/*     */   public boolean isNotation(String notationName) {
/* 157 */     return this.mContext.isNotationDeclared(notationName);
/*     */   }
/*     */   
/*     */   public boolean isUnparsedEntity(String entityName) {
/* 162 */     return this.mContext.isUnparsedEntityDeclared(entityName);
/*     */   }
/*     */   
/*     */   public String resolveNamespacePrefix(String prefix) {
/* 167 */     return this.mContext.getNamespaceURI(prefix);
/*     */   }
/*     */   
/*     */   public void onID(Datatype datatype, StringToken idToken) throws IllegalArgumentException {
/* 186 */     if (this.mIdDefs == null)
/* 187 */       this.mIdDefs = new ElementIdMap(); 
/* 190 */     int idType = datatype.getIdType();
/* 191 */     Location loc = this.mContext.getValidationLocation();
/* 192 */     PrefixedName elemPName = getElementPName();
/* 193 */     PrefixedName attrPName = getAttrPName();
/* 195 */     if (idType == 1) {
/* 196 */       String idStr = idToken.literal.trim();
/* 197 */       ElementId eid = this.mIdDefs.addDefined(idStr, loc, elemPName, attrPName);
/* 199 */       if (eid.getLocation() != loc) {
/* 200 */         this.mProblem = new XMLValidationProblem(loc, "Duplicate id '" + idStr + "', first declared at " + eid.getLocation());
/* 201 */         this.mProblem.setReporter(this);
/*     */       } 
/* 203 */     } else if (idType == 2) {
/* 204 */       String idStr = idToken.literal.trim();
/* 205 */       this.mIdDefs.addReferenced(idStr, loc, elemPName, attrPName);
/* 206 */     } else if (idType == 3) {
/* 207 */       StringTokenizer tokens = new StringTokenizer(idToken.literal);
/* 208 */       while (tokens.hasMoreTokens())
/* 209 */         this.mIdDefs.addReferenced(tokens.nextToken(), loc, elemPName, attrPName); 
/*     */     } else {
/* 212 */       throw new IllegalStateException("Internal error: unexpected ID datatype: " + datatype);
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLValidationSchema getSchema() {
/* 223 */     return this.mParentSchema;
/*     */   }
/*     */   
/*     */   public void validateElementStart(String localName, String uri, String prefix) throws XMLStreamException {
/* 240 */     if (this.mCurrAcceptor == null)
/*     */       return; 
/* 245 */     if (this.mTextAccumulator.hasText())
/* 246 */       doValidateText(this.mTextAccumulator); 
/* 252 */     if (uri == null)
/* 253 */       uri = ""; 
/* 261 */     String qname = localName;
/* 262 */     this.mStartTag.reinit(uri, localName, qname, this.mAttributeProxy, this);
/* 264 */     this.mCurrAcceptor = this.mCurrAcceptor.createChildAcceptor(this.mStartTag, this.mErrorRef);
/* 269 */     if (this.mErrorRef.str != null)
/* 270 */       reportError(this.mErrorRef); 
/* 272 */     if (this.mProblem != null) {
/* 273 */       XMLValidationProblem p = this.mProblem;
/* 274 */       this.mProblem = null;
/* 275 */       this.mContext.reportProblem(p);
/*     */     } 
/* 277 */     this.mAcceptors.add(this.mCurrAcceptor);
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, String value) throws XMLStreamException {
/* 284 */     this.mCurrAttrLocalName = localName;
/* 285 */     this.mCurrAttrPrefix = prefix;
/* 286 */     if (this.mCurrAcceptor != null) {
/* 288 */       String qname = localName;
/* 289 */       DatatypeRef typeRef = null;
/* 294 */       if (uri == null)
/* 295 */         uri = ""; 
/* 298 */       if (!this.mCurrAcceptor.onAttribute2(uri, localName, qname, value, this, this.mErrorRef, typeRef) || this.mErrorRef.str != null)
/* 300 */         reportError(this.mErrorRef); 
/* 302 */       if (this.mProblem != null) {
/* 303 */         XMLValidationProblem p = this.mProblem;
/* 304 */         this.mProblem = null;
/* 305 */         this.mContext.reportProblem(p);
/*     */       } 
/*     */     } 
/* 311 */     return null;
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, char[] valueChars, int valueStart, int valueEnd) throws XMLStreamException {
/* 320 */     int len = valueEnd - valueStart;
/* 324 */     return validateAttribute(localName, uri, prefix, new String(valueChars, valueStart, len));
/*     */   }
/*     */   
/*     */   public int validateElementAndAttributes() throws XMLStreamException {
/* 332 */     this.mCurrAttrLocalName = this.mCurrAttrPrefix = "";
/* 333 */     if (this.mCurrAcceptor != null) {
/* 337 */       if (!this.mCurrAcceptor.onEndAttributes(this.mStartTag, this.mErrorRef) || this.mErrorRef.str != null)
/* 339 */         reportError(this.mErrorRef); 
/* 342 */       int stringChecks = this.mCurrAcceptor.getStringCareLevel();
/* 343 */       switch (stringChecks) {
/*     */         case 0:
/* 345 */           return 1;
/*     */         case 1:
/* 347 */           return 4;
/*     */         case 2:
/* 349 */           return 3;
/*     */       } 
/* 351 */       throw new IllegalArgumentException("Internal error: unexpected string care level value return by MSV: " + stringChecks);
/*     */     } 
/* 356 */     return 4;
/*     */   }
/*     */   
/*     */   public int validateElementEnd(String localName, String uri, String prefix) throws XMLStreamException {
/* 371 */     doValidateText(this.mTextAccumulator);
/* 377 */     int lastIx = this.mAcceptors.size() - 1;
/* 378 */     if (lastIx < 0)
/* 379 */       return 1; 
/* 382 */     Acceptor acc = this.mAcceptors.remove(lastIx);
/* 383 */     if (acc != null && (
/* 384 */       !acc.isAcceptState(this.mErrorRef) || this.mErrorRef.str != null))
/* 385 */       reportError(this.mErrorRef); 
/* 388 */     if (lastIx == 0) {
/* 389 */       this.mCurrAcceptor = null;
/*     */     } else {
/* 391 */       this.mCurrAcceptor = this.mAcceptors.get(lastIx - 1);
/*     */     } 
/* 393 */     if (this.mCurrAcceptor != null && acc != null) {
/* 394 */       if (!this.mCurrAcceptor.stepForward(acc, this.mErrorRef) || this.mErrorRef.str != null)
/* 396 */         reportError(this.mErrorRef); 
/* 398 */       int stringChecks = this.mCurrAcceptor.getStringCareLevel();
/* 399 */       switch (stringChecks) {
/*     */         case 0:
/* 401 */           return 1;
/*     */         case 1:
/* 403 */           return 4;
/*     */         case 2:
/* 405 */           return 3;
/*     */       } 
/* 407 */       throw new IllegalArgumentException("Internal error: unexpected string care level value return by MSV: " + stringChecks);
/*     */     } 
/* 410 */     return 4;
/*     */   }
/*     */   
/*     */   public void validateText(String text, boolean lastTextSegment) throws XMLStreamException {
/* 420 */     this.mTextAccumulator.addText(text);
/* 421 */     if (lastTextSegment)
/* 422 */       doValidateText(this.mTextAccumulator); 
/*     */   }
/*     */   
/*     */   public void validateText(char[] cbuf, int textStart, int textEnd, boolean lastTextSegment) throws XMLStreamException {
/* 434 */     this.mTextAccumulator.addText(cbuf, textStart, textEnd);
/* 435 */     if (lastTextSegment)
/* 436 */       doValidateText(this.mTextAccumulator); 
/*     */   }
/*     */   
/*     */   public void validationCompleted(boolean eod) throws XMLStreamException {
/* 447 */     if (eod && 
/* 448 */       this.mIdDefs != null) {
/* 449 */       ElementId ref = this.mIdDefs.getFirstUndefined();
/* 450 */       if (ref != null) {
/* 451 */         String msg = "Undefined ID '" + ref.getId() + "': referenced from element <" + ref.getElemName() + ">, attribute '" + ref.getAttrName() + "'";
/* 455 */         reportError(msg, ref.getLocation());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getAttributeType(int index) {
/* 472 */     return null;
/*     */   }
/*     */   
/*     */   public int getIdAttrIndex() {
/* 478 */     return -1;
/*     */   }
/*     */   
/*     */   public int getNotationAttrIndex() {
/* 484 */     return -1;
/*     */   }
/*     */   
/*     */   PrefixedName getElementPName() {
/* 495 */     return PrefixedName.valueOf(this.mContext.getCurrentElementName());
/*     */   }
/*     */   
/*     */   PrefixedName getAttrPName() {
/* 500 */     return new PrefixedName(this.mCurrAttrPrefix, this.mCurrAttrLocalName);
/*     */   }
/*     */   
/*     */   void doValidateText(TextAccumulator textAcc) throws XMLStreamException {
/* 506 */     if (this.mCurrAcceptor != null) {
/* 507 */       String str = textAcc.getAndClear();
/* 508 */       DatatypeRef typeRef = null;
/* 509 */       if (!this.mCurrAcceptor.onText2(str, this, this.mErrorRef, typeRef) || this.mErrorRef.str != null)
/* 511 */         reportError(this.mErrorRef); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void reportError(StringRef errorRef) throws XMLStreamException {
/* 519 */     String msg = errorRef.str;
/* 520 */     errorRef.str = null;
/* 521 */     if (msg == null)
/* 522 */       msg = "Unknown reason"; 
/* 524 */     reportError(msg);
/*     */   }
/*     */   
/*     */   private void reportError(String msg) throws XMLStreamException {
/* 530 */     reportError(msg, this.mContext.getValidationLocation());
/*     */   }
/*     */   
/*     */   private void reportError(String msg, Location loc) throws XMLStreamException {
/* 536 */     XMLValidationProblem prob = new XMLValidationProblem(loc, msg, 2);
/* 537 */     prob.setReporter(this);
/* 538 */     this.mContext.reportProblem(prob);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\msv\GenericMsvValidator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */