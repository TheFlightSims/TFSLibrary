/*     */ package com.ctc.wstx.sr;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.dtd.DTDId;
/*     */ import com.ctc.wstx.dtd.DTDSubset;
/*     */ import com.ctc.wstx.dtd.DTDValidatorBase;
/*     */ import com.ctc.wstx.dtd.FullDTDReader;
/*     */ import com.ctc.wstx.io.BranchingReaderSource;
/*     */ import com.ctc.wstx.io.DefaultInputResolver;
/*     */ import com.ctc.wstx.io.InputBootstrapper;
/*     */ import com.ctc.wstx.io.WstxInputSource;
/*     */ import com.ctc.wstx.util.URLUtil;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.DTDValidationSchema;
/*     */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*     */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public class ValidatingStreamReader extends TypedStreamReader {
/*     */   static final String STAX_PROP_ENTITIES = "javax.xml.stream.entities";
/*     */   
/*     */   static final String STAX_PROP_NOTATIONS = "javax.xml.stream.notations";
/*     */   
/*  74 */   DTDValidationSchema mDTD = null;
/*     */   
/*  81 */   XMLValidator mAutoDtdValidator = null;
/*     */   
/*     */   boolean mDtdValidatorSet = false;
/*     */   
/*  92 */   protected ValidationProblemHandler mVldProbHandler = null;
/*     */   
/*     */   private ValidatingStreamReader(InputBootstrapper bs, BranchingReaderSource input, ReaderCreator owner, ReaderConfig cfg, InputElementStack elemStack, boolean forER) throws XMLStreamException {
/* 106 */     super(bs, input, owner, cfg, elemStack, forER);
/*     */   }
/*     */   
/*     */   public static ValidatingStreamReader createValidatingStreamReader(BranchingReaderSource input, ReaderCreator owner, ReaderConfig cfg, InputBootstrapper bs, boolean forER) throws XMLStreamException {
/* 127 */     ValidatingStreamReader sr = new ValidatingStreamReader(bs, input, owner, cfg, createElementStack(cfg), forER);
/* 129 */     return sr;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 141 */     if (name.equals("javax.xml.stream.entities")) {
/* 142 */       safeEnsureFinishToken();
/* 143 */       if (this.mDTD == null || !(this.mDTD instanceof DTDSubset))
/* 144 */         return null; 
/* 146 */       List l = ((DTDSubset)this.mDTD).getGeneralEntityList();
/* 150 */       return new ArrayList(l);
/*     */     } 
/* 152 */     if (name.equals("javax.xml.stream.notations")) {
/* 153 */       safeEnsureFinishToken();
/* 154 */       if (this.mDTD == null || !(this.mDTD instanceof DTDSubset))
/* 155 */         return null; 
/* 160 */       List l = ((DTDSubset)this.mDTD).getNotationList();
/* 161 */       return new ArrayList(l);
/*     */     } 
/* 163 */     return super.getProperty(name);
/*     */   }
/*     */   
/*     */   public void setFeature(String name, Object value) {
/* 180 */     if (name.equals("org.codehaus.stax2.propDtdOverride")) {
/* 186 */       if (value != null && !(value instanceof DTDValidationSchema))
/* 187 */         throw new IllegalArgumentException("Value to set for feature " + name + " not of type DTDValidationSchema"); 
/* 189 */       this.mConfig.setProperty("org.codehaus.stax2.propDtdOverride", value);
/*     */     } else {
/* 191 */       super.setFeature(name, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getProcessedDTD() {
/* 202 */     return getProcessedDTDSchema();
/*     */   }
/*     */   
/*     */   public DTDValidationSchema getProcessedDTDSchema() {
/* 206 */     DTDValidationSchema dtd = this.mConfig.getDTDOverride();
/* 207 */     if (dtd == null)
/* 208 */       dtd = this.mDTD; 
/* 210 */     return this.mDTD;
/*     */   }
/*     */   
/*     */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 223 */     return this.mElementStack.validateAgainst(schema);
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 230 */     return this.mElementStack.stopValidatingAgainst(schema);
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 237 */     return this.mElementStack.stopValidatingAgainst(validator);
/*     */   }
/*     */   
/*     */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 243 */     ValidationProblemHandler oldH = this.mVldProbHandler;
/* 244 */     this.mVldProbHandler = h;
/* 245 */     return oldH;
/*     */   }
/*     */   
/*     */   protected void finishDTD(boolean copyContents) throws XMLStreamException {
/* 271 */     if (!hasConfigFlags(16)) {
/* 272 */       super.finishDTD(copyContents);
/*     */       return;
/*     */     } 
/* 279 */     char c = getNextChar(" in DOCTYPE declaration");
/* 280 */     DTDSubset intSubset = null;
/* 285 */     if (c == '[') {
/* 287 */       if (copyContents)
/* 288 */         ((BranchingReaderSource)this.mInput).startBranch(this.mTextBuffer, this.mInputPtr, this.mNormalizeLFs); 
/*     */       try {
/* 292 */         intSubset = FullDTDReader.readInternalSubset(this, this.mInput, this.mConfig, hasConfigFlags(32), this.mDocXmlVersion);
/*     */       } finally {
/* 299 */         if (copyContents)
/* 304 */           ((BranchingReaderSource)this.mInput).endBranch(this.mInputPtr - 1); 
/*     */       } 
/* 309 */       c = getNextCharAfterWS(" in internal DTD subset");
/*     */     } 
/* 312 */     if (c != '>')
/* 313 */       throwUnexpectedChar(c, "; expected '>' to finish DOCTYPE declaration."); 
/* 323 */     this.mDTD = this.mConfig.getDTDOverride();
/* 324 */     if (this.mDTD == null) {
/* 327 */       DTDSubset extSubset = null;
/* 340 */       if (this.mDtdPublicId != null || this.mDtdSystemId != null)
/* 341 */         extSubset = findDtdExtSubset(this.mDtdPublicId, this.mDtdSystemId, intSubset); 
/* 344 */       if (intSubset == null) {
/* 345 */         this.mDTD = (DTDValidationSchema)extSubset;
/* 346 */       } else if (extSubset == null) {
/* 347 */         this.mDTD = (DTDValidationSchema)intSubset;
/*     */       } else {
/* 349 */         this.mDTD = (DTDValidationSchema)intSubset.combineWithExternalSubset(this, extSubset);
/*     */       } 
/*     */     } 
/* 354 */     if (this.mDTD == null) {
/* 355 */       this.mGeneralEntities = null;
/*     */     } else {
/*     */       DTDValidatorBase dTDValidatorBase;
/* 357 */       if (this.mDTD instanceof DTDSubset) {
/* 358 */         this.mGeneralEntities = ((DTDSubset)this.mDTD).getGeneralEntityMap();
/*     */       } else {
/* 363 */         _reportProblem(this.mConfig.getXMLReporter(), ErrorConsts.WT_DT_DECL, "Value to set for feature org.codehaus.stax2.propDtdOverride not a native Woodstox DTD implementation (but " + this.mDTD.getClass() + "): can not access full entity or notation information", (Location)null);
/*     */       } 
/* 371 */       this.mAutoDtdValidator = this.mDTD.createValidator(this.mElementStack);
/* 372 */       this.mDtdValidatorSet = true;
/* 373 */       NsDefaultProvider nsDefs = null;
/* 374 */       if (this.mAutoDtdValidator instanceof DTDValidatorBase) {
/* 375 */         DTDValidatorBase dtdv = (DTDValidatorBase)this.mAutoDtdValidator;
/* 376 */         dtdv.setAttrValueNormalization(true);
/* 378 */         if (dtdv.hasNsDefaults())
/* 379 */           dTDValidatorBase = dtdv; 
/*     */       } 
/* 382 */       this.mElementStack.setAutomaticDTDValidator(this.mAutoDtdValidator, (NsDefaultProvider)dTDValidatorBase);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reportValidationProblem(XMLValidationProblem prob) throws XMLStreamException {
/* 393 */     if (this.mVldProbHandler != null) {
/* 395 */       this.mVldProbHandler.reportProblem(prob);
/*     */     } else {
/* 397 */       super.reportValidationProblem(prob);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void initValidation() throws XMLStreamException {
/* 409 */     if (hasConfigFlags(32) && !this.mDtdValidatorSet)
/* 414 */       reportProblem((Location)null, ErrorConsts.WT_DT_DECL, ErrorConsts.W_MISSING_DTD, (Object)null, (Object)null); 
/*     */   }
/*     */   
/*     */   private DTDSubset findDtdExtSubset(String pubId, String sysId, DTDSubset intSubset) throws XMLStreamException {
/*     */     DTDId dtdId;
/* 434 */     boolean cache = hasConfigFlags(65536);
/*     */     try {
/* 437 */       dtdId = constructDtdId(pubId, sysId);
/* 438 */     } catch (IOException ioe) {
/* 439 */       throw constructFromIOE(ioe);
/*     */     } 
/* 442 */     if (cache) {
/* 443 */       DTDSubset dTDSubset = findCachedSubset(dtdId, intSubset);
/* 444 */       if (dTDSubset != null)
/* 445 */         return dTDSubset; 
/*     */     } 
/* 454 */     if (sysId == null)
/* 455 */       throwParseError("Can not resolve DTD with public id \"{0}\"; missing system identifier", this.mDtdPublicId, (Object)null); 
/* 457 */     WstxInputSource src = null;
/*     */     try {
/* 460 */       int xmlVersion = this.mDocXmlVersion;
/* 462 */       if (xmlVersion == 0)
/* 463 */         xmlVersion = 256; 
/* 471 */       src = DefaultInputResolver.resolveEntity(this.mInput, null, null, pubId, sysId, this.mConfig.getDtdResolver(), this.mConfig, xmlVersion);
/* 474 */     } catch (FileNotFoundException fex) {
/* 478 */       throwParseError("(was {0}) {1}", fex.getClass().getName(), fex.getMessage());
/* 479 */     } catch (IOException ioe) {
/* 480 */       throwFromIOE(ioe);
/*     */     } 
/* 483 */     DTDSubset extSubset = FullDTDReader.readExternalSubset(src, this.mConfig, intSubset, hasConfigFlags(32), this.mDocXmlVersion);
/* 487 */     if (cache)
/* 493 */       if (extSubset.isCachable())
/* 494 */         this.mOwner.addCachedDTD(dtdId, extSubset);  
/* 498 */     return extSubset;
/*     */   }
/*     */   
/*     */   private DTDSubset findCachedSubset(DTDId id, DTDSubset intSubset) throws XMLStreamException {
/* 504 */     DTDSubset extSubset = this.mOwner.findCachedDTD(id);
/* 509 */     if (extSubset != null && (
/* 510 */       intSubset == null || extSubset.isReusableWith(intSubset)))
/* 511 */       return extSubset; 
/* 514 */     return null;
/*     */   }
/*     */   
/*     */   private URI resolveExtSubsetPath(String systemId) throws IOException {
/* 524 */     URL ctxt = (this.mInput == null) ? null : this.mInput.getSource();
/* 529 */     if (ctxt == null)
/* 534 */       return URLUtil.uriFromSystemId(systemId); 
/* 536 */     URL url = URLUtil.urlFromSystemId(systemId, ctxt);
/*     */     try {
/* 538 */       return new URI(url.toExternalForm());
/* 539 */     } catch (URISyntaxException e) {
/* 540 */       throw new IOException("Failed to construct URI for external subset, URL = " + url.toExternalForm() + ": " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected DTDId constructDtdId(String pubId, String sysId) throws IOException {
/* 550 */     int significantFlags = this.mConfigFlags & 0x280021;
/* 566 */     URI sysRef = (sysId == null || sysId.length() == 0) ? null : resolveExtSubsetPath(sysId);
/* 574 */     boolean usePublicId = ((this.mConfigFlags & 0x20000) != 0);
/* 575 */     if (usePublicId && pubId != null && pubId.length() > 0)
/* 576 */       return DTDId.construct(pubId, sysRef, significantFlags, this.mXml11); 
/* 578 */     if (sysRef == null)
/* 579 */       return null; 
/* 581 */     return DTDId.constructFromSystemId(sysRef, significantFlags, this.mXml11);
/*     */   }
/*     */   
/*     */   protected DTDId constructDtdId(URI sysId) throws IOException {
/* 587 */     int significantFlags = this.mConfigFlags & 0x80021;
/* 598 */     return DTDId.constructFromSystemId(sysId, significantFlags, this.mXml11);
/*     */   }
/*     */   
/*     */   protected void reportInvalidContent(int evtType) throws XMLStreamException {
/* 617 */     switch (this.mVldContent) {
/*     */       case 0:
/* 619 */         reportValidationProblem(ErrorConsts.ERR_VLD_EMPTY, this.mElementStack.getTopElementDesc(), ErrorConsts.tokenTypeDesc(evtType));
/*     */         return;
/*     */       case 1:
/*     */       case 2:
/* 625 */         reportValidationProblem(ErrorConsts.ERR_VLD_NON_MIXED, this.mElementStack.getTopElementDesc(), (Object)null);
/*     */         return;
/*     */       case 3:
/*     */       case 4:
/* 633 */         reportValidationProblem(ErrorConsts.ERR_VLD_ANY, this.mElementStack.getTopElementDesc(), ErrorConsts.tokenTypeDesc(evtType));
/*     */         return;
/*     */     } 
/* 638 */     throwParseError("Internal error: trying to report invalid content for " + evtType);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\ValidatingStreamReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */