/*      */ package com.ctc.wstx.dtd;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.cfg.ErrorConsts;
/*      */ import com.ctc.wstx.ent.EntityDecl;
/*      */ import com.ctc.wstx.ent.IntEntity;
/*      */ import com.ctc.wstx.ent.ParsedExtEntity;
/*      */ import com.ctc.wstx.ent.UnparsedExtEntity;
/*      */ import com.ctc.wstx.evt.WNotationDeclaration;
/*      */ import com.ctc.wstx.io.WstxInputData;
/*      */ import com.ctc.wstx.io.WstxInputLocation;
/*      */ import com.ctc.wstx.io.WstxInputSource;
/*      */ import com.ctc.wstx.sr.InputProblemReporter;
/*      */ import com.ctc.wstx.util.InternCache;
/*      */ import com.ctc.wstx.util.PrefixedName;
/*      */ import com.ctc.wstx.util.SymbolTable;
/*      */ import com.ctc.wstx.util.TextBuffer;
/*      */ import com.ctc.wstx.util.WordResolver;
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.net.URL;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.Set;
/*      */ import java.util.TreeSet;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLReporter;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import javax.xml.stream.events.NotationDeclaration;
/*      */ import org.codehaus.stax2.XMLStreamLocation2;
/*      */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*      */ 
/*      */ public class FullDTDReader extends MinimalDTDReader {
/*      */   static final boolean INTERN_SHARED_NAMES = false;
/*      */   
/*   72 */   static final Boolean ENTITY_EXP_GE = Boolean.FALSE;
/*      */   
/*   74 */   static final Boolean ENTITY_EXP_PE = Boolean.TRUE;
/*      */   
/*      */   final int mConfigFlags;
/*      */   
/*      */   final boolean mCfgSupportDTDPP;
/*      */   
/*      */   final boolean mCfgFullyValidating;
/*      */   
/*      */   HashMap mParamEntities;
/*      */   
/*      */   final HashMap mPredefdPEs;
/*      */   
/*      */   Set mRefdPEs;
/*      */   
/*      */   HashMap mGeneralEntities;
/*      */   
/*      */   final HashMap mPredefdGEs;
/*      */   
/*      */   Set mRefdGEs;
/*      */   
/*      */   boolean mUsesPredefdEntities = false;
/*      */   
/*      */   HashMap mNotations;
/*      */   
/*      */   final HashMap mPredefdNotations;
/*      */   
/*      */   boolean mUsesPredefdNotations = false;
/*      */   
/*      */   HashMap mNotationForwardRefs;
/*      */   
/*  219 */   HashMap mSharedNames = null;
/*      */   
/*      */   HashMap mElements;
/*      */   
/*  233 */   HashMap mSharedEnumValues = null;
/*      */   
/*  247 */   DefaultAttrValue mCurrAttrDefault = null;
/*      */   
/*      */   boolean mExpandingPE = false;
/*      */   
/*  260 */   TextBuffer mValueBuffer = null;
/*      */   
/*  273 */   int mIncludeCount = 0;
/*      */   
/*      */   boolean mCheckForbiddenPEs = false;
/*      */   
/*      */   String mCurrDeclaration;
/*      */   
/*      */   boolean mAnyDTDppFeatures = false;
/*      */   
/*  302 */   String mDefaultNsURI = "";
/*      */   
/*  308 */   HashMap mNamespaces = null;
/*      */   
/*  317 */   DTDWriter mFlattenWriter = null;
/*      */   
/*      */   final DTDEventListener mEventListener;
/*      */   
/*  327 */   transient TextBuffer mTextBuffer = null;
/*      */   
/*      */   final PrefixedName mAccessKey;
/*      */   
/*      */   private FullDTDReader(WstxInputSource input, ReaderConfig cfg, boolean constructFully, int xmlVersion) {
/*  341 */     this(input, cfg, false, (DTDSubset)null, constructFully, xmlVersion);
/*      */   }
/*      */   
/*      */   private FullDTDReader(WstxInputSource input, ReaderConfig cfg, DTDSubset intSubset, boolean constructFully, int xmlVersion) {
/*  351 */     this(input, cfg, true, intSubset, constructFully, xmlVersion);
/*  354 */     input.initInputLocation((WstxInputData)this, this.mCurrDepth);
/*      */   }
/*      */   
/*      */   private FullDTDReader(WstxInputSource input, ReaderConfig cfg, boolean isExt, DTDSubset intSubset, boolean constructFully, int xmlVersion) {
/*  364 */     super(input, cfg, isExt);
/* 3276 */     this.mAccessKey = new PrefixedName(null, null);
/*      */     this.mDocXmlVersion = xmlVersion;
/*      */     this.mXml11 = cfg.isXml11();
/*      */     int cfgFlags = cfg.getConfigFlags();
/*      */     this.mConfigFlags = cfgFlags;
/*      */     this.mCfgSupportDTDPP = ((cfgFlags & 0x80000) != 0);
/*      */     this.mCfgFullyValidating = constructFully;
/*      */     this.mUsesPredefdEntities = false;
/*      */     this.mParamEntities = null;
/*      */     this.mRefdPEs = null;
/*      */     this.mRefdGEs = null;
/*      */     this.mGeneralEntities = null;
/*      */     HashMap pes = (intSubset == null) ? null : intSubset.getParameterEntityMap();
/*      */     if (pes == null || pes.isEmpty()) {
/*      */       this.mPredefdPEs = null;
/*      */     } else {
/*      */       this.mPredefdPEs = pes;
/*      */     } 
/*      */     HashMap ges = (intSubset == null) ? null : intSubset.getGeneralEntityMap();
/*      */     if (ges == null || ges.isEmpty()) {
/*      */       this.mPredefdGEs = null;
/*      */     } else {
/*      */       this.mPredefdGEs = ges;
/*      */     } 
/*      */     HashMap not = (intSubset == null) ? null : intSubset.getNotationMap();
/*      */     if (not == null || not.isEmpty()) {
/*      */       this.mPredefdNotations = null;
/*      */     } else {
/*      */       this.mPredefdNotations = not;
/*      */     } 
/*      */     this.mEventListener = this.mConfig.getDTDEventListener();
/*      */   }
/*      */   
/*      */   public static DTDSubset readInternalSubset(WstxInputData srcData, WstxInputSource input, ReaderConfig cfg, boolean constructFully, int xmlVersion) throws XMLStreamException {
/*      */     DTDSubset ss;
/*      */     FullDTDReader r = new FullDTDReader(input, cfg, constructFully, xmlVersion);
/*      */     r.copyBufferStateFrom(srcData);
/*      */     try {
/*      */       ss = r.parseDTD();
/*      */     } finally {
/*      */       srcData.copyBufferStateFrom((WstxInputData)r);
/*      */     } 
/*      */     return ss;
/*      */   }
/*      */   
/*      */   public static DTDSubset readExternalSubset(WstxInputSource src, ReaderConfig cfg, DTDSubset intSubset, boolean constructFully, int xmlVersion) throws XMLStreamException {
/*      */     FullDTDReader r = new FullDTDReader(src, cfg, intSubset, constructFully, xmlVersion);
/*      */     return r.parseDTD();
/*      */   }
/*      */   
/*      */   public static DTDSubset flattenExternalSubset(WstxInputSource src, Writer flattenWriter, boolean inclComments, boolean inclConditionals, boolean inclPEs) throws IOException, XMLStreamException {
/*      */     ReaderConfig cfg = ReaderConfig.createFullDefaults();
/*      */     cfg = cfg.createNonShared(new SymbolTable());
/*      */     FullDTDReader r = new FullDTDReader(src, cfg, null, true, 0);
/*      */     r.setFlattenWriter(flattenWriter, inclComments, inclConditionals, inclPEs);
/*      */     DTDSubset ss = r.parseDTD();
/*      */     r.flushFlattenWriter();
/*      */     flattenWriter.flush();
/*      */     return ss;
/*      */   }
/*      */   
/*      */   private TextBuffer getTextBuffer() {
/*      */     if (this.mTextBuffer == null) {
/*      */       this.mTextBuffer = TextBuffer.createTemporaryBuffer();
/*      */       this.mTextBuffer.resetInitialized();
/*      */     } else {
/*      */       this.mTextBuffer.resetWithEmpty();
/*      */     } 
/*      */     return this.mTextBuffer;
/*      */   }
/*      */   
/*      */   public void setFlattenWriter(Writer w, boolean inclComments, boolean inclConditionals, boolean inclPEs) {
/*      */     this.mFlattenWriter = new DTDWriter(w, inclComments, inclConditionals, inclPEs);
/*      */   }
/*      */   
/*      */   private void flushFlattenWriter() throws XMLStreamException {
/*      */     this.mFlattenWriter.flush(this.mInputBuffer, this.mInputPtr);
/*      */   }
/*      */   
/*      */   public EntityDecl findEntity(String entName) {
/*      */     if (this.mPredefdGEs != null) {
/*      */       EntityDecl decl = (EntityDecl)this.mPredefdGEs.get(entName);
/*      */       if (decl != null)
/*      */         return decl; 
/*      */     } 
/*      */     return (EntityDecl)this.mGeneralEntities.get(entName);
/*      */   }
/*      */   
/*      */   protected DTDSubset parseDTD() throws XMLStreamException {
/*      */     DTDSubset ss;
/*      */     while (true) {
/*      */       this.mCheckForbiddenPEs = false;
/*      */       int i = getNextAfterWS();
/*      */       if (i < 0) {
/*      */         if (this.mIsExternal)
/*      */           break; 
/*      */         throwUnexpectedEOF(" in internal DTD subset");
/*      */       } 
/*      */       if (i == 37) {
/*      */         expandPE();
/*      */         continue;
/*      */       } 
/*      */       this.mTokenInputTotal = this.mCurrInputProcessed + this.mInputPtr;
/*      */       this.mTokenInputRow = this.mCurrInputRow;
/*      */       this.mTokenInputCol = this.mInputPtr - this.mCurrInputRowStart;
/*      */       if (i == 60) {
/*      */         this.mCheckForbiddenPEs = (!this.mIsExternal && this.mInput == this.mRootInput);
/*      */         if (this.mFlattenWriter == null) {
/*      */           parseDirective();
/*      */           continue;
/*      */         } 
/*      */         parseDirectiveFlattened();
/*      */         continue;
/*      */       } 
/*      */       if (i == 93) {
/*      */         if (this.mIncludeCount == 0 && !this.mIsExternal)
/*      */           break; 
/*      */         if (this.mIncludeCount > 0) {
/*      */           boolean suppress = (this.mFlattenWriter != null && !this.mFlattenWriter.includeConditionals());
/*      */           if (suppress) {
/*      */             this.mFlattenWriter.flush(this.mInputBuffer, this.mInputPtr - 1);
/*      */             this.mFlattenWriter.disableOutput();
/*      */           } 
/*      */           try {
/*      */             char c = dtdNextFromCurr();
/*      */             if (c == ']') {
/*      */               c = dtdNextFromCurr();
/*      */               if (c == '>') {
/*      */                 this.mIncludeCount--;
/*      */                 if (suppress)
/*      */                   this.mFlattenWriter.enableOutput(this.mInputPtr); 
/*      */                 continue;
/*      */               } 
/*      */             } 
/*      */             throwDTDUnexpectedChar(c, "; expected ']]>' to close conditional include section");
/*      */           } finally {
/*      */             if (suppress)
/*      */               this.mFlattenWriter.enableOutput(this.mInputPtr); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       if (this.mIsExternal)
/*      */         throwDTDUnexpectedChar(i, "; expected a '<' to start a directive"); 
/*      */       throwDTDUnexpectedChar(i, "; expected a '<' to start a directive, or \"]>\" to end internal subset");
/*      */     } 
/*      */     if (this.mIncludeCount > 0) {
/*      */       String suffix = (this.mIncludeCount == 1) ? "an INCLUDE block" : ("" + this.mIncludeCount + " INCLUDE blocks");
/*      */       throwUnexpectedEOF(getErrorMsg() + "; expected closing marker for " + suffix);
/*      */     } 
/*      */     if (this.mNotationForwardRefs != null && this.mNotationForwardRefs.size() > 0)
/*      */       _reportUndefinedNotationRefs(); 
/*      */     if (this.mIsExternal) {
/*      */       boolean cachable = (!this.mUsesPredefdEntities && !this.mUsesPredefdNotations);
/*      */       ss = DTDSubsetImpl.constructInstance(cachable, this.mGeneralEntities, this.mRefdGEs, null, this.mRefdPEs, this.mNotations, this.mElements, this.mCfgFullyValidating);
/*      */     } else {
/*      */       ss = DTDSubsetImpl.constructInstance(false, this.mGeneralEntities, null, this.mParamEntities, null, this.mNotations, this.mElements, this.mCfgFullyValidating);
/*      */     } 
/*      */     return ss;
/*      */   }
/*      */   
/*      */   protected void parseDirective() throws XMLStreamException {
/*      */     char c = dtdNextFromCurr();
/*      */     if (c == '?') {
/*      */       readPI();
/*      */       return;
/*      */     } 
/*      */     if (c != '!')
/*      */       throwDTDUnexpectedChar(c, "; expected '!' to start a directive"); 
/*      */     c = dtdNextFromCurr();
/*      */     if (c == '-') {
/*      */       c = dtdNextFromCurr();
/*      */       if (c != '-')
/*      */         throwDTDUnexpectedChar(c, "; expected '-' for a comment"); 
/*      */       if (this.mEventListener != null && this.mEventListener.dtdReportComments()) {
/*      */         readComment(this.mEventListener);
/*      */       } else {
/*      */         skipComment();
/*      */       } 
/*      */     } else if (c == '[') {
/*      */       checkInclusion();
/*      */     } else if (c >= 'A' && c <= 'Z') {
/*      */       handleDeclaration(c);
/*      */     } else {
/*      */       throwDTDUnexpectedChar(c, ErrorConsts.ERR_DTD_MAINLEVEL_KEYWORD);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void parseDirectiveFlattened() throws XMLStreamException {
/*      */     this.mFlattenWriter.flush(this.mInputBuffer, this.mInputPtr - 1);
/*      */     this.mFlattenWriter.disableOutput();
/*      */     char c = dtdNextFromCurr();
/*      */     if (c == '?') {
/*      */       this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */       this.mFlattenWriter.output("<?");
/*      */       readPI();
/*      */       return;
/*      */     } 
/*      */     if (c != '!')
/*      */       throwDTDUnexpectedChar(c, ErrorConsts.ERR_DTD_MAINLEVEL_KEYWORD); 
/*      */     c = dtdNextFromCurr();
/*      */     if (c == '-') {
/*      */       c = dtdNextFromCurr();
/*      */       if (c != '-')
/*      */         throwDTDUnexpectedChar(c, "; expected '-' for a comment"); 
/*      */       boolean comm = this.mFlattenWriter.includeComments();
/*      */       if (comm) {
/*      */         this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */         this.mFlattenWriter.output("<!--");
/*      */       } 
/*      */       try {
/*      */         skipComment();
/*      */       } finally {
/*      */         if (!comm)
/*      */           this.mFlattenWriter.enableOutput(this.mInputPtr); 
/*      */       } 
/*      */     } else if (c == '[') {
/*      */       boolean cond = this.mFlattenWriter.includeConditionals();
/*      */       if (cond) {
/*      */         this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */         this.mFlattenWriter.output("<![");
/*      */       } 
/*      */       try {
/*      */         checkInclusion();
/*      */       } finally {
/*      */         if (!cond)
/*      */           this.mFlattenWriter.enableOutput(this.mInputPtr); 
/*      */       } 
/*      */     } else {
/*      */       boolean filterPEs = (c == 'E' && !this.mFlattenWriter.includeParamEntities());
/*      */       if (filterPEs) {
/*      */         handleSuppressedDeclaration();
/*      */       } else if (c >= 'A' && c <= 'Z') {
/*      */         this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */         this.mFlattenWriter.output("<!");
/*      */         this.mFlattenWriter.output(c);
/*      */         handleDeclaration(c);
/*      */       } else {
/*      */         throwDTDUnexpectedChar(c, ErrorConsts.ERR_DTD_MAINLEVEL_KEYWORD);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void initInputSource(WstxInputSource newInput, boolean isExt, String entityId) throws XMLStreamException {
/*      */     if (this.mFlattenWriter != null) {
/*      */       this.mFlattenWriter.flush(this.mInputBuffer, this.mInputPtr);
/*      */       this.mFlattenWriter.disableOutput();
/*      */       try {
/*      */         super.initInputSource(newInput, isExt, entityId);
/*      */       } finally {
/*      */         this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */       } 
/*      */     } else {
/*      */       super.initInputSource(newInput, isExt, entityId);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean loadMore() throws XMLStreamException {
/*      */     WstxInputSource input = this.mInput;
/*      */     if (this.mFlattenWriter != null)
/*      */       this.mFlattenWriter.flush(this.mInputBuffer, this.mInputEnd); 
/*      */     do {
/*      */       this.mCurrInputProcessed += this.mInputEnd;
/*      */       this.mCurrInputRowStart -= this.mInputEnd;
/*      */       try {
/*      */         int count = input.readInto((WstxInputData)this);
/*      */         if (count > 0) {
/*      */           if (this.mFlattenWriter != null)
/*      */             this.mFlattenWriter.setFlattenStart(this.mInputPtr); 
/*      */           return true;
/*      */         } 
/*      */         input.close();
/*      */       } catch (IOException ioe) {
/*      */         throw constructFromIOE(ioe);
/*      */       } 
/*      */       if (input == this.mRootInput)
/*      */         return false; 
/*      */       WstxInputSource parent = input.getParent();
/*      */       if (parent == null)
/*      */         throwNullParent(input); 
/*      */       if (this.mCurrDepth != input.getScopeId())
/*      */         handleIncompleteEntityProblem(input); 
/*      */       this.mInput = input = parent;
/*      */       input.restoreContext((WstxInputData)this);
/*      */       if (this.mFlattenWriter != null)
/*      */         this.mFlattenWriter.setFlattenStart(this.mInputPtr); 
/*      */       this.mInputTopDepth = input.getScopeId();
/*      */       if (this.mNormalizeLFs)
/*      */         continue; 
/*      */       this.mNormalizeLFs = !input.fromInternalEntity();
/*      */     } while (this.mInputPtr >= this.mInputEnd);
/*      */     return true;
/*      */   }
/*      */   
/*      */   protected boolean loadMoreFromCurrent() throws XMLStreamException {
/*      */     if (this.mFlattenWriter != null)
/*      */       this.mFlattenWriter.flush(this.mInputBuffer, this.mInputEnd); 
/*      */     this.mCurrInputProcessed += this.mInputEnd;
/*      */     this.mCurrInputRowStart -= this.mInputEnd;
/*      */     try {
/*      */       int count = this.mInput.readInto((WstxInputData)this);
/*      */       if (count > 0) {
/*      */         if (this.mFlattenWriter != null)
/*      */           this.mFlattenWriter.setFlattenStart(this.mInputPtr); 
/*      */         return true;
/*      */       } 
/*      */     } catch (IOException ie) {
/*      */       throwFromIOE(ie);
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   protected boolean ensureInput(int minAmount) throws XMLStreamException {
/*      */     int currAmount = this.mInputEnd - this.mInputPtr;
/*      */     if (currAmount >= minAmount)
/*      */       return true; 
/*      */     if (this.mFlattenWriter != null)
/*      */       this.mFlattenWriter.flush(this.mInputBuffer, this.mInputEnd); 
/*      */     try {
/*      */       if (this.mInput.readMore((WstxInputData)this, minAmount)) {
/*      */         if (this.mFlattenWriter != null)
/*      */           this.mFlattenWriter.setFlattenStart(currAmount); 
/*      */         return true;
/*      */       } 
/*      */     } catch (IOException ie) {
/*      */       throwFromIOE(ie);
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   private void loadMoreScoped(WstxInputSource currScope, String entityName, Location loc) throws XMLStreamException {
/*      */     boolean check = (this.mInput == currScope);
/*      */     loadMore(getErrorMsg());
/*      */     if (check && this.mInput != currScope)
/*      */       _reportWFCViolation("Unterminated entity value for entity '" + entityName + "' (definition started at " + loc + ")"); 
/*      */   }
/*      */   
/*      */   private char dtdNextIfAvailable() throws XMLStreamException {
/*      */     char c;
/*      */     if (this.mInputPtr < this.mInputEnd) {
/*      */       c = this.mInputBuffer[this.mInputPtr++];
/*      */     } else {
/*      */       int i = peekNext();
/*      */       if (i < 0)
/*      */         return Character.MIN_VALUE; 
/*      */       this.mInputPtr++;
/*      */       c = (char)i;
/*      */     } 
/*      */     if (c == '\000')
/*      */       throwNullChar(); 
/*      */     return c;
/*      */   }
/*      */   
/*      */   private char getNextExpanded() throws XMLStreamException {
/*      */     while (true) {
/*      */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(getErrorMsg());
/*      */       if (c != '%')
/*      */         return c; 
/*      */       expandPE();
/*      */     } 
/*      */   }
/*      */   
/*      */   private char skipDtdWs(boolean handlePEs) throws XMLStreamException {
/*      */     while (true) {
/*      */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(getErrorMsg());
/*      */       if (c > ' ') {
/*      */         if (c == '%' && handlePEs) {
/*      */           expandPE();
/*      */           continue;
/*      */         } 
/*      */         return c;
/*      */       } 
/*      */       if (c == '\n' || c == '\r') {
/*      */         skipCRLF(c);
/*      */         continue;
/*      */       } 
/*      */       if (c != ' ' && c != '\t')
/*      */         throwInvalidSpace(c); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private char skipObligatoryDtdWs() throws XMLStreamException {
/*      */     char c;
/*      */     int i = peekNext();
/*      */     if (i == -1) {
/*      */       c = getNextChar(getErrorMsg());
/*      */       if (c > ' ' && c != '%')
/*      */         return c; 
/*      */     } else {
/*      */       c = this.mInputBuffer[this.mInputPtr++];
/*      */       if (c > ' ' && c != '%')
/*      */         throwDTDUnexpectedChar(c, "; expected a separating white space"); 
/*      */     } 
/*      */     while (true) {
/*      */       if (c == '%') {
/*      */         expandPE();
/*      */       } else {
/*      */         if (c > ' ')
/*      */           break; 
/*      */         if (c == '\n' || c == '\r') {
/*      */           skipCRLF(c);
/*      */         } else if (c != ' ' && c != '\t') {
/*      */           throwInvalidSpace(c);
/*      */         } 
/*      */       } 
/*      */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(getErrorMsg());
/*      */     } 
/*      */     return c;
/*      */   }
/*      */   
/*      */   private void expandPE() throws XMLStreamException {
/*      */     String id;
/*      */     char c;
/*      */     if (this.mCheckForbiddenPEs)
/*      */       throwForbiddenPE(); 
/*      */     if (this.mFlattenWriter != null) {
/*      */       this.mFlattenWriter.flush(this.mInputBuffer, this.mInputPtr - 1);
/*      */       this.mFlattenWriter.disableOutput();
/*      */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */       id = readDTDName(c);
/*      */       try {
/*      */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */       } finally {
/*      */         this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */       } 
/*      */     } else {
/*      */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */       id = readDTDName(c);
/*      */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */     } 
/*      */     if (c != ';')
/*      */       throwDTDUnexpectedChar(c, "; expected ';' to end parameter entity name"); 
/*      */     this.mExpandingPE = true;
/*      */     expandEntity(id, true, ENTITY_EXP_PE);
/*      */   }
/*      */   
/*      */   protected String checkDTDKeyword(String exp) throws XMLStreamException {
/*      */     int i = 0;
/*      */     int len = exp.length();
/*      */     char c = ' ';
/*      */     for (; i < len; i++) {
/*      */       if (this.mInputPtr < this.mInputEnd) {
/*      */         c = this.mInputBuffer[this.mInputPtr++];
/*      */       } else {
/*      */         c = dtdNextIfAvailable();
/*      */         if (c == '\000')
/*      */           return exp.substring(0, i); 
/*      */       } 
/*      */       if (c != exp.charAt(i))
/*      */         break; 
/*      */     } 
/*      */     if (i == len) {
/*      */       c = dtdNextIfAvailable();
/*      */       if (c == '\000')
/*      */         return null; 
/*      */       if (!isNameChar(c)) {
/*      */         this.mInputPtr--;
/*      */         return null;
/*      */       } 
/*      */     } 
/*      */     StringBuffer sb = new StringBuffer(exp.substring(0, i));
/*      */     sb.append(c);
/*      */     while (true) {
/*      */       c = dtdNextIfAvailable();
/*      */       if (c == '\000')
/*      */         break; 
/*      */       if (!isNameChar(c) && c != ':') {
/*      */         this.mInputPtr--;
/*      */         break;
/*      */       } 
/*      */       sb.append(c);
/*      */     } 
/*      */     return sb.toString();
/*      */   }
/*      */   
/*      */   protected String readDTDKeyword(String prefix) throws XMLStreamException {
/*      */     StringBuffer sb = new StringBuffer(prefix);
/*      */     while (true) {
/*      */       char c;
/*      */       if (this.mInputPtr < this.mInputEnd) {
/*      */         c = this.mInputBuffer[this.mInputPtr++];
/*      */       } else {
/*      */         c = dtdNextIfAvailable();
/*      */         if (c == '\000')
/*      */           break; 
/*      */       } 
/*      */       if (!isNameChar(c) && c != ':') {
/*      */         this.mInputPtr--;
/*      */         break;
/*      */       } 
/*      */       sb.append(c);
/*      */     } 
/*      */     return sb.toString();
/*      */   }
/*      */   
/*      */   private boolean checkPublicSystemKeyword(char c) throws XMLStreamException {
/*      */     String errId;
/*      */     if (c == 'P') {
/*      */       errId = checkDTDKeyword("UBLIC");
/*      */       if (errId == null)
/*      */         return true; 
/*      */       errId = "P" + errId;
/*      */     } else if (c == 'S') {
/*      */       errId = checkDTDKeyword("YSTEM");
/*      */       if (errId == null)
/*      */         return false; 
/*      */       errId = "S" + errId;
/*      */     } else {
/*      */       if (!isNameStartChar(c))
/*      */         throwDTDUnexpectedChar(c, "; expected 'PUBLIC' or 'SYSTEM' keyword"); 
/*      */       errId = readDTDKeyword(String.valueOf(c));
/*      */     } 
/*      */     _reportWFCViolation("Unrecognized keyword '" + errId + "'; expected 'PUBLIC' or 'SYSTEM'");
/*      */     return false;
/*      */   }
/*      */   
/*      */   private String readDTDName(char c) throws XMLStreamException {
/*      */     if (!isNameStartChar(c))
/*      */       throwDTDUnexpectedChar(c, "; expected an identifier"); 
/*      */     return parseFullName(c);
/*      */   }
/*      */   
/*      */   private String readDTDLocalName(char c, boolean checkChar) throws XMLStreamException {
/*      */     if (checkChar && !isNameStartChar(c))
/*      */       throwDTDUnexpectedChar(c, "; expected an identifier"); 
/*      */     return parseLocalName(c);
/*      */   }
/*      */   
/*      */   private String readDTDNmtoken(char c) throws XMLStreamException {
/*      */     char[] outBuf = getNameBuffer(64);
/*      */     int outLen = outBuf.length;
/*      */     int outPtr = 0;
/*      */     while (true) {
/*      */       if (!isNameChar(c) && c != ':') {
/*      */         if (outPtr == 0)
/*      */           throwDTDUnexpectedChar(c, "; expected a NMTOKEN character to start a NMTOKEN"); 
/*      */         this.mInputPtr--;
/*      */         break;
/*      */       } 
/*      */       if (outPtr >= outLen) {
/*      */         outBuf = expandBy50Pct(outBuf);
/*      */         outLen = outBuf.length;
/*      */       } 
/*      */       outBuf[outPtr++] = c;
/*      */       if (this.mInputPtr < this.mInputEnd) {
/*      */         c = this.mInputBuffer[this.mInputPtr++];
/*      */         continue;
/*      */       } 
/*      */       c = dtdNextIfAvailable();
/*      */       if (c == '\000')
/*      */         break; 
/*      */     } 
/*      */     return new String(outBuf, 0, outPtr);
/*      */   }
/*      */   
/*      */   private PrefixedName readDTDQName(char firstChar) throws XMLStreamException {
/*      */     String prefix;
/*      */     String localName;
/*      */     if (!this.mCfgNsEnabled) {
/*      */       prefix = null;
/*      */       localName = parseFullName(firstChar);
/*      */     } else {
/*      */       localName = parseLocalName(firstChar);
/*      */       char c = dtdNextIfAvailable();
/*      */       if (c == '\000') {
/*      */         prefix = null;
/*      */       } else if (c == ':') {
/*      */         prefix = localName;
/*      */         c = dtdNextFromCurr();
/*      */         localName = parseLocalName(c);
/*      */       } else {
/*      */         prefix = null;
/*      */         this.mInputPtr--;
/*      */       } 
/*      */     } 
/*      */     return findSharedName(prefix, localName);
/*      */   }
/*      */   
/*      */   private char readArity() throws XMLStreamException {
/*      */     char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(getErrorMsg());
/*      */     if (c == '?' || c == '*' || c == '+')
/*      */       return c; 
/*      */     this.mInputPtr--;
/*      */     return ' ';
/*      */   }
/*      */   
/*      */   private char[] parseEntityValue(String id, Location loc, char quoteChar) throws XMLStreamException {
/*      */     WstxInputSource currScope = this.mInput;
/*      */     TextBuffer tb = this.mValueBuffer;
/*      */     if (tb == null)
/*      */       tb = TextBuffer.createTemporaryBuffer(); 
/*      */     tb.resetInitialized();
/*      */     char[] outBuf = tb.getCurrentSegment();
/*      */     int outPtr = tb.getCurrentSegmentSize();
/*      */     while (true) {
/*      */       if (this.mInputPtr >= this.mInputEnd)
/*      */         loadMoreScoped(currScope, id, loc); 
/*      */       char c1 = this.mInputBuffer[this.mInputPtr++];
/*      */       if (c1 < '?')
/*      */         if (c1 == quoteChar) {
/*      */           if (this.mInput == currScope)
/*      */             break; 
/*      */         } else if (c1 == '&') {
/*      */           int d = resolveCharOnlyEntity(false);
/*      */           if (d != 0) {
/*      */             if (d <= 65535) {
/*      */               c1 = (char)d;
/*      */             } else {
/*      */               if (outPtr >= outBuf.length) {
/*      */                 outBuf = tb.finishCurrentSegment();
/*      */                 outPtr = 0;
/*      */               } 
/*      */               d -= 65536;
/*      */               outBuf[outPtr++] = (char)((d >> 10) + 55296);
/*      */               c1 = (char)((d & 0x3FF) + 56320);
/*      */             } 
/*      */           } else {
/*      */             boolean first = true;
/*      */             while (true) {
/*      */               if (outPtr >= outBuf.length) {
/*      */                 outBuf = tb.finishCurrentSegment();
/*      */                 outPtr = 0;
/*      */               } 
/*      */               outBuf[outPtr++] = c1;
/*      */               if (this.mInputPtr >= this.mInputEnd)
/*      */                 loadMoreScoped(currScope, id, loc); 
/*      */               c1 = this.mInputBuffer[this.mInputPtr++];
/*      */               if (c1 == ';')
/*      */                 break; 
/*      */               if (first) {
/*      */                 first = false;
/*      */                 if (isNameStartChar(c1))
/*      */                   continue; 
/*      */               } else if (isNameChar(c1)) {
/*      */                 continue;
/*      */               } 
/*      */               if (c1 == ':' && !this.mCfgNsEnabled)
/*      */                 continue; 
/*      */               if (first)
/*      */                 throwDTDUnexpectedChar(c1, "; expected entity name after '&'"); 
/*      */               throwDTDUnexpectedChar(c1, "; expected semi-colon after entity name");
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           if (c1 == '%') {
/*      */             expandPE();
/*      */             continue;
/*      */           } 
/*      */           if (c1 < ' ')
/*      */             if (c1 == '\n') {
/*      */               markLF();
/*      */             } else if (c1 == '\r') {
/*      */               if (skipCRLF(c1)) {
/*      */                 if (!this.mNormalizeLFs) {
/*      */                   if (outPtr >= outBuf.length) {
/*      */                     outBuf = tb.finishCurrentSegment();
/*      */                     outPtr = 0;
/*      */                   } 
/*      */                   outBuf[outPtr++] = c1;
/*      */                 } 
/*      */                 c1 = '\n';
/*      */               } else if (this.mNormalizeLFs) {
/*      */                 c1 = '\n';
/*      */               } 
/*      */             } else if (c1 != '\t') {
/*      */               throwInvalidSpace(c1);
/*      */             }  
/*      */         }  
/*      */       if (outPtr >= outBuf.length) {
/*      */         outBuf = tb.finishCurrentSegment();
/*      */         outPtr = 0;
/*      */       } 
/*      */       outBuf[outPtr++] = c1;
/*      */     } 
/*      */     tb.setCurrentLength(outPtr);
/*      */     char c = skipDtdWs(true);
/*      */     if (c != '>')
/*      */       throwDTDUnexpectedChar(c, "; expected closing '>' after ENTITY declaration"); 
/*      */     char[] result = tb.contentsAsArray();
/*      */     this.mValueBuffer = tb;
/*      */     return result;
/*      */   }
/*      */   
/*      */   private void parseAttrDefaultValue(DefaultAttrValue defVal, char quoteChar, PrefixedName attrName, Location loc, boolean gotFixed) throws XMLStreamException {
/*      */     if (quoteChar != '"' && quoteChar != '\'') {
/*      */       String msg = "; expected a single or double quote to enclose the default value";
/*      */       if (!gotFixed)
/*      */         msg = msg + ", or one of keywords (#REQUIRED, #IMPLIED, #FIXED)"; 
/*      */       msg = msg + " (for attribute '" + attrName + "')";
/*      */       throwDTDUnexpectedChar(quoteChar, msg);
/*      */     } 
/*      */     WstxInputSource currScope = this.mInput;
/*      */     TextBuffer tb = this.mValueBuffer;
/*      */     if (tb == null)
/*      */       tb = TextBuffer.createTemporaryBuffer(); 
/*      */     tb.resetInitialized();
/*      */     int outPtr = 0;
/*      */     char[] outBuf = tb.getCurrentSegment();
/*      */     int outLen = outBuf.length;
/*      */     while (true) {
/*      */       if (this.mInputPtr >= this.mInputEnd) {
/*      */         boolean check = (this.mInput == currScope);
/*      */         loadMore(getErrorMsg());
/*      */         if (check && this.mInput != currScope)
/*      */           _reportWFCViolation("Unterminated attribute default value for attribute '" + attrName + "' (definition started at " + loc + ")"); 
/*      */       } 
/*      */       char c = this.mInputBuffer[this.mInputPtr++];
/*      */       if (c < '?')
/*      */         if (c <= ' ') {
/*      */           if (c == '\n') {
/*      */             markLF();
/*      */           } else if (c == '\r') {
/*      */             c = getNextChar(" in attribute default value");
/*      */             if (c != '\n') {
/*      */               this.mInputPtr--;
/*      */               c = this.mNormalizeLFs ? '\n' : '\r';
/*      */             } 
/*      */             markLF();
/*      */           } else if (c != ' ' && c != '\t') {
/*      */             throwInvalidSpace(c);
/*      */           } 
/*      */           c = ' ';
/*      */         } else if (c == quoteChar) {
/*      */           if (this.mInput == currScope)
/*      */             break; 
/*      */         } else if (c == '&') {
/*      */           int d;
/*      */           if (inputInBuffer() >= 3) {
/*      */             d = resolveSimpleEntity(true);
/*      */           } else {
/*      */             d = resolveCharOnlyEntity(true);
/*      */           } 
/*      */           if (d == 0) {
/*      */             c = getNextChar(" in entity reference");
/*      */             String id = parseEntityName(c);
/*      */             try {
/*      */               this.mCurrAttrDefault = defVal;
/*      */               this.mExpandingPE = false;
/*      */               expandEntity(id, false, ENTITY_EXP_GE);
/*      */             } finally {
/*      */               this.mCurrAttrDefault = null;
/*      */             } 
/*      */             continue;
/*      */           } 
/*      */           if (c > Character.MAX_VALUE)
/*      */             if (d <= 65535) {
/*      */               c = (char)d;
/*      */             } else {
/*      */               if (outPtr >= outBuf.length) {
/*      */                 outBuf = tb.finishCurrentSegment();
/*      */                 outPtr = 0;
/*      */               } 
/*      */               d -= 65536;
/*      */               outBuf[outPtr++] = (char)((d >> 10) + 55296);
/*      */               c = (char)((d & 0x3FF) + 56320);
/*      */             }  
/*      */         } else if (c == '<') {
/*      */           throwDTDUnexpectedChar(c, " in attribute default value");
/*      */         }  
/*      */       if (outPtr >= outLen) {
/*      */         outBuf = tb.finishCurrentSegment();
/*      */         outPtr = 0;
/*      */         outLen = outBuf.length;
/*      */       } 
/*      */       outBuf[outPtr++] = c;
/*      */     } 
/*      */     tb.setCurrentLength(outPtr);
/*      */     defVal.setValue(tb.contentsAsString());
/*      */     this.mValueBuffer = tb;
/*      */   }
/*      */   
/*      */   protected void readPI() throws XMLStreamException {
/*      */     String target = parseFullName();
/*      */     if (target.length() == 0)
/*      */       _reportWFCViolation(ErrorConsts.ERR_WF_PI_MISSING_TARGET); 
/*      */     if (target.equalsIgnoreCase("xml"))
/*      */       _reportWFCViolation(ErrorConsts.ERR_WF_PI_XML_TARGET, target); 
/*      */     char c = dtdNextFromCurr();
/*      */     if (!isSpaceChar(c)) {
/*      */       if (c != '?' || dtdNextFromCurr() != '>')
/*      */         throwUnexpectedChar(c, ErrorConsts.ERR_WF_PI_XML_MISSING_SPACE); 
/*      */       if (this.mEventListener != null)
/*      */         this.mEventListener.dtdProcessingInstruction(target, ""); 
/*      */     } else if (this.mEventListener == null) {
/*      */       while (true) {
/*      */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */         if (c == '?')
/*      */           while (true) {
/*      */             c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */             if (c != '?') {
/*      */               if (c == '>')
/*      */                 return; 
/*      */               break;
/*      */             } 
/*      */           }  
/*      */         if (c < ' ') {
/*      */           if (c == '\n' || c == '\r') {
/*      */             skipCRLF(c);
/*      */             continue;
/*      */           } 
/*      */           if (c != '\t')
/*      */             throwInvalidSpace(c); 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       while (c <= ' ') {
/*      */         if (c == '\n' || c == '\r') {
/*      */           skipCRLF(c);
/*      */         } else if (c != '\t' && c != ' ') {
/*      */           throwInvalidSpace(c);
/*      */         } 
/*      */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */       } 
/*      */       TextBuffer tb = getTextBuffer();
/*      */       char[] outBuf = tb.getCurrentSegment();
/*      */       int outPtr = 0;
/*      */       while (true) {
/*      */         if (c == '?') {
/*      */           while (true) {
/*      */             c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */             if (c != '?')
/*      */               break; 
/*      */             if (outPtr >= outBuf.length) {
/*      */               outBuf = tb.finishCurrentSegment();
/*      */               outPtr = 0;
/*      */             } 
/*      */             outBuf[outPtr++] = c;
/*      */           } 
/*      */           if (c == '>')
/*      */             break; 
/*      */           this.mInputPtr--;
/*      */           c = '?';
/*      */         } else if (c < ' ') {
/*      */           if (c == '\n' || c == '\r') {
/*      */             skipCRLF(c);
/*      */             c = '\n';
/*      */           } else if (c != '\t') {
/*      */             throwInvalidSpace(c);
/*      */           } 
/*      */         } 
/*      */         if (outPtr >= outBuf.length) {
/*      */           outBuf = tb.finishCurrentSegment();
/*      */           outPtr = 0;
/*      */         } 
/*      */         outBuf[outPtr++] = c;
/*      */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */       } 
/*      */       tb.setCurrentLength(outPtr);
/*      */       String data = tb.contentsAsString();
/*      */       this.mEventListener.dtdProcessingInstruction(target, data);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void readComment(DTDEventListener l) throws XMLStreamException {
/*      */     TextBuffer tb = getTextBuffer();
/*      */     char[] outBuf = tb.getCurrentSegment();
/*      */     int outPtr = 0;
/*      */     while (true) {
/*      */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/*      */       if (c < ' ') {
/*      */         if (c == '\n' || c == '\r') {
/*      */           skipCRLF(c);
/*      */           c = '\n';
/*      */         } else if (c != '\t') {
/*      */           throwInvalidSpace(c);
/*      */         } 
/*      */       } else if (c == '-') {
/*      */         c = dtdNextFromCurr();
/*      */         if (c == '-') {
/*      */           c = dtdNextFromCurr();
/*      */           if (c != '>')
/*      */             throwParseError(ErrorConsts.ERR_HYPHENS_IN_COMMENT); 
/*      */           break;
/*      */         } 
/*      */         c = '-';
/*      */         this.mInputPtr--;
/*      */       } 
/*      */       if (outPtr >= outBuf.length) {
/*      */         outBuf = tb.finishCurrentSegment();
/*      */         outPtr = 0;
/*      */       } 
/*      */       outBuf[outPtr++] = c;
/*      */     } 
/*      */     tb.setCurrentLength(outPtr);
/*      */     tb.fireDtdCommentEvent(l);
/*      */   }
/*      */   
/*      */   private void checkInclusion() throws XMLStreamException {
/*      */     String keyword;
/*      */     if (!this.mIsExternal && this.mInput == this.mRootInput)
/*      */       _reportWFCViolation("Internal DTD subset can not use (INCLUDE/IGNORE) directives (except via external entities)"); 
/*      */     char c = skipDtdWs(true);
/*      */     if (c != 'I') {
/*      */       keyword = readDTDKeyword(String.valueOf(c));
/*      */     } else {
/*      */       c = dtdNextFromCurr();
/*      */       if (c == 'G') {
/*      */         keyword = checkDTDKeyword("NORE");
/*      */         if (keyword == null) {
/*      */           handleIgnored();
/*      */           return;
/*      */         } 
/*      */         keyword = "IG" + keyword;
/*      */       } else if (c == 'N') {
/*      */         keyword = checkDTDKeyword("CLUDE");
/*      */         if (keyword == null) {
/*      */           handleIncluded();
/*      */           return;
/*      */         } 
/*      */         keyword = "IN" + keyword;
/*      */       } else {
/*      */         this.mInputPtr--;
/*      */         keyword = readDTDKeyword("I");
/*      */       } 
/*      */     } 
/*      */     _reportWFCViolation("Unrecognized directive '" + keyword + "'; expected either 'IGNORE' or 'INCLUDE'");
/*      */   }
/*      */   
/*      */   private void handleIncluded() throws XMLStreamException {
/*      */     char c = skipDtdWs(false);
/*      */     if (c != '[')
/*      */       throwDTDUnexpectedChar(c, "; expected '[' to follow 'INCLUDE' directive"); 
/*      */     this.mIncludeCount++;
/*      */   }
/*      */   
/*      */   private void handleIgnored() throws XMLStreamException {
/*      */     char c = skipDtdWs(false);
/*      */     int count = 1;
/*      */     if (c != '[')
/*      */       throwDTDUnexpectedChar(c, "; expected '[' to follow 'IGNORE' directive"); 
/*      */     String errorMsg = getErrorMsg();
/*      */     while (true) {
/*      */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(errorMsg);
/*      */       if (c < ' ') {
/*      */         if (c == '\n' || c == '\r') {
/*      */           skipCRLF(c);
/*      */           continue;
/*      */         } 
/*      */         if (c != '\t')
/*      */           throwInvalidSpace(c); 
/*      */         continue;
/*      */       } 
/*      */       if (c == ']') {
/*      */         if (getNextChar(errorMsg) == ']' && getNextChar(errorMsg) == '>') {
/*      */           if (--count < 1)
/*      */             return; 
/*      */           continue;
/*      */         } 
/*      */         this.mInputPtr--;
/*      */         continue;
/*      */       } 
/*      */       if (c == '<') {
/*      */         if (getNextChar(errorMsg) == '!' && getNextChar(errorMsg) == '[') {
/*      */           count++;
/*      */           continue;
/*      */         } 
/*      */         this.mInputPtr--;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void _reportUndefinedNotationRefs() throws XMLStreamException {
/*      */     int count = this.mNotationForwardRefs.size();
/*      */     String id = this.mNotationForwardRefs.keySet().iterator().next();
/*      */     String msg = "" + count + " referenced notation" + ((count == 1) ? "" : "s") + " undefined: first one '" + id + "'";
/*      */     _reportVCViolation(msg);
/*      */   }
/*      */   
/*      */   private void _reportBadDirective(String dir) throws XMLStreamException {
/*      */     String msg = "Unrecognized DTD directive '<!" + dir + " >'; expected ATTLIST, ELEMENT, ENTITY or NOTATION";
/*      */     if (this.mCfgSupportDTDPP)
/*      */       msg = msg + " (or, for DTD++, TARGETNS)"; 
/*      */     _reportWFCViolation(msg);
/*      */   }
/*      */   
/*      */   private void _reportVCViolation(String msg) throws XMLStreamException {
/*      */     if (this.mCfgFullyValidating) {
/*      */       reportValidationProblem(msg, 2);
/*      */     } else {
/*      */       reportValidationProblem(msg, 1);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void _reportWFCViolation(String msg) throws XMLStreamException {
/*      */     throwParseError(msg);
/*      */   }
/*      */   
/*      */   private void _reportWFCViolation(String format, Object arg) throws XMLStreamException {
/*      */     throwParseError(format, arg, null);
/*      */   }
/*      */   
/*      */   private void throwDTDElemError(String msg, Object elem) throws XMLStreamException {
/*      */     _reportWFCViolation(elemDesc(elem) + ": " + msg);
/*      */   }
/*      */   
/*      */   private void throwDTDAttrError(String msg, DTDElement elem, PrefixedName attrName) throws XMLStreamException {
/*      */     _reportWFCViolation(attrDesc(elem, attrName) + ": " + msg);
/*      */   }
/*      */   
/*      */   private void throwDTDUnexpectedChar(int i, String extraMsg) throws XMLStreamException {
/*      */     if (extraMsg == null)
/*      */       throwUnexpectedChar(i, getErrorMsg()); 
/*      */     throwUnexpectedChar(i, getErrorMsg() + extraMsg);
/*      */   }
/*      */   
/*      */   private void throwForbiddenPE() throws XMLStreamException {
/*      */     _reportWFCViolation("Can not have parameter entities in the internal subset, except for defining complete declarations (XML 1.0, #2.8, WFC 'PEs In Internal Subset')");
/*      */   }
/*      */   
/*      */   private String elemDesc(Object elem) {
/*      */     return "Element <" + elem + ">)";
/*      */   }
/*      */   
/*      */   private String attrDesc(Object elem, PrefixedName attrName) {
/*      */     return "Attribute '" + attrName + "' (of element <" + elem + ">)";
/*      */   }
/*      */   
/*      */   private String entityDesc(WstxInputSource input) {
/*      */     return "Entity &" + input.getEntityId() + ";";
/*      */   }
/*      */   
/*      */   private void handleDeclaration(char c) throws XMLStreamException {
/*      */     String keyw = null;
/*      */     this.mCurrDepth = 1;
/*      */     try {
/*      */       if (c == 'A') {
/*      */         keyw = checkDTDKeyword("TTLIST");
/*      */         if (keyw == null) {
/*      */           this.mCurrDeclaration = "ATTLIST";
/*      */           handleAttlistDecl();
/*      */         } else {
/*      */           keyw = "A" + keyw;
/*      */           _reportBadDirective(keyw);
/*      */         } 
/*      */       } else if (c == 'E') {
/*      */         c = dtdNextFromCurr();
/*      */         if (c == 'N') {
/*      */           keyw = checkDTDKeyword("TITY");
/*      */           if (keyw == null) {
/*      */             this.mCurrDeclaration = "ENTITY";
/*      */             handleEntityDecl(false);
/*      */           } else {
/*      */             keyw = "EN" + keyw;
/*      */             _reportBadDirective(keyw);
/*      */           } 
/*      */         } else if (c == 'L') {
/*      */           keyw = checkDTDKeyword("EMENT");
/*      */           if (keyw == null) {
/*      */             this.mCurrDeclaration = "ELEMENT";
/*      */             handleElementDecl();
/*      */           } else {
/*      */             keyw = "EL" + keyw;
/*      */             _reportBadDirective(keyw);
/*      */           } 
/*      */         } else {
/*      */           keyw = readDTDKeyword("E");
/*      */           _reportBadDirective(keyw);
/*      */         } 
/*      */       } else if (c == 'N') {
/*      */         keyw = checkDTDKeyword("OTATION");
/*      */         if (keyw == null) {
/*      */           this.mCurrDeclaration = "NOTATION";
/*      */           handleNotationDecl();
/*      */         } else {
/*      */           keyw = "N" + keyw;
/*      */           _reportBadDirective(keyw);
/*      */         } 
/*      */       } else if (c == 'T' && this.mCfgSupportDTDPP) {
/*      */         keyw = checkDTDKeyword("ARGETNS");
/*      */         if (keyw == null) {
/*      */           this.mCurrDeclaration = "TARGETNS";
/*      */           handleTargetNsDecl();
/*      */         } else {
/*      */           keyw = "T" + keyw;
/*      */           _reportBadDirective(keyw);
/*      */         } 
/*      */       } else {
/*      */         keyw = readDTDKeyword(String.valueOf(c));
/*      */         _reportBadDirective(keyw);
/*      */       } 
/*      */       if (this.mInput.getScopeId() > 0)
/*      */         handleGreedyEntityProblem(this.mInput); 
/*      */     } finally {
/*      */       this.mCurrDepth = 0;
/*      */       this.mCurrDeclaration = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void handleSuppressedDeclaration() throws XMLStreamException {
/*      */     String keyw;
/*      */     char c = dtdNextFromCurr();
/*      */     if (c == 'N') {
/*      */       keyw = checkDTDKeyword("TITY");
/*      */       if (keyw == null) {
/*      */         handleEntityDecl(true);
/*      */         return;
/*      */       } 
/*      */       keyw = "EN" + keyw;
/*      */       this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */     } else {
/*      */       this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */       this.mFlattenWriter.output("<!E");
/*      */       this.mFlattenWriter.output(c);
/*      */       if (c == 'L') {
/*      */         keyw = checkDTDKeyword("EMENT");
/*      */         if (keyw == null) {
/*      */           handleElementDecl();
/*      */           return;
/*      */         } 
/*      */         keyw = "EL" + keyw;
/*      */       } else {
/*      */         keyw = readDTDKeyword("E");
/*      */       } 
/*      */     } 
/*      */     _reportBadDirective(keyw);
/*      */   }
/*      */   
/*      */   private void handleAttlistDecl() throws XMLStreamException {
/*      */     char c = skipObligatoryDtdWs();
/*      */     PrefixedName elemName = readDTDQName(c);
/*      */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/*      */     HashMap m = getElementMap();
/*      */     DTDElement elem = (DTDElement)m.get(elemName);
/*      */     if (elem == null) {
/*      */       elem = DTDElement.createPlaceholder(this.mConfig, (Location)xMLStreamLocation2, elemName);
/*      */       m.put(elemName, elem);
/*      */     } 
/*      */     int index = 0;
/*      */     while (true) {
/*      */       c = getNextExpanded();
/*      */       if (isSpaceChar(c)) {
/*      */         this.mInputPtr--;
/*      */         c = skipDtdWs(true);
/*      */       } 
/*      */       if (c == '>')
/*      */         break; 
/*      */       handleAttrDecl(elem, c, index, (Location)xMLStreamLocation2);
/*      */       index++;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void handleElementDecl() throws XMLStreamException {
/*      */     char c = skipObligatoryDtdWs();
/*      */     PrefixedName elemName = readDTDQName(c);
/*      */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/*      */     c = skipObligatoryDtdWs();
/*      */     StructValidator val = null;
/*      */     int vldContent = 4;
/*      */     if (c == '(') {
/*      */       c = skipDtdWs(true);
/*      */       if (c == '#') {
/*      */         val = readMixedSpec(elemName, this.mCfgFullyValidating);
/*      */         vldContent = 4;
/*      */       } else {
/*      */         this.mInputPtr--;
/*      */         ContentSpec spec = readContentSpec(elemName, true, this.mCfgFullyValidating);
/*      */         val = spec.getSimpleValidator();
/*      */         if (val == null)
/*      */           val = new DFAValidator(DFAState.constructDFA(spec)); 
/*      */         vldContent = 1;
/*      */       } 
/*      */     } else if (isNameStartChar(c)) {
/*      */       String keyw = null;
/*      */       if (c == 'A') {
/*      */         keyw = checkDTDKeyword("NY");
/*      */         if (keyw == null) {
/*      */           val = null;
/*      */           vldContent = 4;
/*      */         } else {
/*      */           keyw = "A" + keyw;
/*      */           _reportWFCViolation("Unrecognized DTD content spec keyword '" + keyw + "' (for element <" + elemName + ">); expected ANY or EMPTY");
/*      */         } 
/*      */       } else if (c == 'E') {
/*      */         keyw = checkDTDKeyword("MPTY");
/*      */         if (keyw == null) {
/*      */           val = EmptyValidator.getPcdataInstance();
/*      */           vldContent = 0;
/*      */         } else {
/*      */           keyw = "E" + keyw;
/*      */           _reportWFCViolation("Unrecognized DTD content spec keyword '" + keyw + "' (for element <" + elemName + ">); expected ANY or EMPTY");
/*      */         } 
/*      */       } else {
/*      */         this.mInputPtr--;
/*      */         keyw = readDTDKeyword(String.valueOf(c));
/*      */         _reportWFCViolation("Unrecognized DTD content spec keyword '" + keyw + "' (for element <" + elemName + ">); expected ANY or EMPTY");
/*      */       } 
/*      */     } else {
/*      */       throwDTDUnexpectedChar(c, ": excepted '(' to start content specification for element <" + elemName + ">");
/*      */     } 
/*      */     c = skipDtdWs(true);
/*      */     if (c != '>')
/*      */       throwDTDUnexpectedChar(c, "; expected '>' to finish the element declaration for <" + elemName + ">"); 
/*      */     HashMap m = getElementMap();
/*      */     DTDElement oldElem = (DTDElement)m.get(elemName);
/*      */     if (oldElem != null) {
/*      */       if (oldElem.isDefined())
/*      */         if (this.mCfgFullyValidating) {
/*      */           DTDSubsetImpl.throwElementException(oldElem, (Location)xMLStreamLocation2);
/*      */         } else {
/*      */           return;
/*      */         }  
/*      */       oldElem = oldElem.define((Location)xMLStreamLocation2, val, vldContent);
/*      */     } else {
/*      */       oldElem = DTDElement.createDefined(this.mConfig, (Location)xMLStreamLocation2, elemName, val, vldContent);
/*      */     } 
/*      */     m.put(elemName, oldElem);
/*      */   }
/*      */   
/*      */   private void handleEntityDecl(boolean suppressPEDecl) throws XMLStreamException {
/*      */     EntityDecl ent;
/*      */     HashMap m;
/*      */     char c = dtdNextFromCurr();
/*      */     boolean gotSeparator = false;
/*      */     boolean isParam = false;
/*      */     label76: while (true) {
/*      */       while (c == '%') {
/*      */         char d = dtdNextIfAvailable();
/*      */         if (d == '\000' || isSpaceChar(d)) {
/*      */           isParam = true;
/*      */           if (d == '\n' || c == '\r') {
/*      */             skipCRLF(d);
/*      */             break label76;
/*      */           } 
/*      */           break label76;
/*      */         } 
/*      */         if (!isNameStartChar(d))
/*      */           throwDTDUnexpectedChar(d, "; expected a space (for PE declaration) or PE reference name"); 
/*      */         this.mInputPtr--;
/*      */         gotSeparator = true;
/*      */         expandPE();
/*      */         c = dtdNextChar();
/*      */       } 
/*      */       if (!isSpaceChar(c))
/*      */         break; 
/*      */       gotSeparator = true;
/*      */       c = dtdNextFromCurr();
/*      */     } 
/*      */     if (!gotSeparator)
/*      */       throwDTDUnexpectedChar(c, "; expected a space separating ENTITY keyword and entity name"); 
/*      */     if (isParam)
/*      */       c = skipDtdWs(true); 
/*      */     if (suppressPEDecl && !isParam) {
/*      */       this.mFlattenWriter.enableOutput(this.mInputPtr);
/*      */       this.mFlattenWriter.output("<!ENTITY ");
/*      */       this.mFlattenWriter.output(c);
/*      */     } 
/*      */     String id = readDTDName(c);
/*      */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/*      */     try {
/*      */       c = skipObligatoryDtdWs();
/*      */       if (c == '\'' || c == '"') {
/*      */         dtdNextFromCurr();
/*      */         WstxInputLocation wstxInputLocation = getLastCharLocation();
/*      */         this.mInputPtr--;
/*      */         char[] contents = parseEntityValue(id, (Location)wstxInputLocation, c);
/*      */         IntEntity intEntity = new IntEntity((Location)xMLStreamLocation2, id, getSource(), contents, (Location)wstxInputLocation);
/*      */       } else {
/*      */         if (!isNameStartChar(c))
/*      */           throwDTDUnexpectedChar(c, "; expected either quoted value, or keyword 'PUBLIC' or 'SYSTEM'"); 
/*      */         ent = handleExternalEntityDecl(this.mInput, isParam, id, c, (Location)xMLStreamLocation2);
/*      */       } 
/*      */       if (this.mIsExternal)
/*      */         ent.markAsExternallyDeclared(); 
/*      */     } finally {
/*      */       if (suppressPEDecl && isParam)
/*      */         this.mFlattenWriter.enableOutput(this.mInputPtr); 
/*      */     } 
/*      */     if (isParam) {
/*      */       m = this.mParamEntities;
/*      */       if (m == null)
/*      */         this.mParamEntities = m = new HashMap(); 
/*      */     } else {
/*      */       m = this.mGeneralEntities;
/*      */       if (m == null)
/*      */         this.mGeneralEntities = m = new LinkedHashMap(); 
/*      */     } 
/*      */     Object old;
/*      */     if (m.size() > 0 && (old = m.get(id)) != null) {
/*      */       XMLReporter rep = this.mConfig.getXMLReporter();
/*      */       if (rep != null) {
/*      */         EntityDecl oldED = (EntityDecl)old;
/*      */         String str = " entity '" + id + "' defined more than once: first declaration at " + oldED.getLocation();
/*      */         if (isParam) {
/*      */           str = "Parameter" + str;
/*      */         } else {
/*      */           str = "General" + str;
/*      */         } 
/*      */         _reportWarning(rep, ErrorConsts.WT_ENT_DECL, str, (Location)xMLStreamLocation2);
/*      */       } 
/*      */     } else {
/*      */       m.put(id, ent);
/*      */     } 
/*      */     if (this.mEventListener != null && !ent.isParsed())
/*      */       this.mEventListener.dtdUnparsedEntityDecl(id, ent.getPublicId(), ent.getSystemId(), ent.getNotationName(), this.mInput.getSource()); 
/*      */   }
/*      */   
/*      */   private void handleNotationDecl() throws XMLStreamException {
/*      */     String pubId, sysId;
/*      */     char c = skipObligatoryDtdWs();
/*      */     String id = readDTDName(c);
/*      */     c = skipObligatoryDtdWs();
/*      */     boolean isPublic = checkPublicSystemKeyword(c);
/*      */     c = skipObligatoryDtdWs();
/*      */     if (isPublic) {
/*      */       if (c != '"' && c != '\'')
/*      */         throwDTDUnexpectedChar(c, "; expected a quote to start the public identifier"); 
/*      */       pubId = parsePublicId(c, getErrorMsg());
/*      */       c = skipDtdWs(true);
/*      */     } else {
/*      */       pubId = null;
/*      */     } 
/*      */     if (c == '"' || c == '\'') {
/*      */       sysId = parseSystemId(c, this.mNormalizeLFs, getErrorMsg());
/*      */       c = skipDtdWs(true);
/*      */     } else {
/*      */       if (!isPublic)
/*      */         throwDTDUnexpectedChar(c, "; expected a quote to start the system identifier"); 
/*      */       sysId = null;
/*      */     } 
/*      */     if (c != '>')
/*      */       throwDTDUnexpectedChar(c, "; expected closing '>' after NOTATION declaration"); 
/*      */     URL baseURL = this.mInput.getSource();
/*      */     if (this.mEventListener != null)
/*      */       this.mEventListener.dtdNotationDecl(id, pubId, sysId, baseURL); 
/*      */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/*      */     WNotationDeclaration wNotationDeclaration = new WNotationDeclaration((Location)xMLStreamLocation2, id, pubId, sysId, baseURL);
/*      */     if (this.mPredefdNotations != null) {
/*      */       NotationDeclaration oldDecl = (NotationDeclaration)this.mPredefdNotations.get(id);
/*      */       if (oldDecl != null)
/*      */         DTDSubsetImpl.throwNotationException(oldDecl, (NotationDeclaration)wNotationDeclaration); 
/*      */     } 
/*      */     HashMap m = this.mNotations;
/*      */     if (m == null) {
/*      */       this.mNotations = m = new LinkedHashMap();
/*      */     } else {
/*      */       NotationDeclaration oldDecl = (NotationDeclaration)m.get(id);
/*      */       if (oldDecl != null)
/*      */         DTDSubsetImpl.throwNotationException(oldDecl, (NotationDeclaration)wNotationDeclaration); 
/*      */     } 
/*      */     if (this.mNotationForwardRefs != null)
/*      */       this.mNotationForwardRefs.remove(id); 
/*      */     m.put(id, wNotationDeclaration);
/*      */   }
/*      */   
/*      */   private void handleTargetNsDecl() throws XMLStreamException {
/*      */     String name;
/*      */     this.mAnyDTDppFeatures = true;
/*      */     char c = skipObligatoryDtdWs();
/*      */     if (isNameStartChar(c)) {
/*      */       name = readDTDLocalName(c, false);
/*      */       c = skipObligatoryDtdWs();
/*      */     } else {
/*      */       name = null;
/*      */     } 
/*      */     if (c != '"' && c != '\'') {
/*      */       if (c == '>')
/*      */         _reportWFCViolation("Missing namespace URI for TARGETNS directive"); 
/*      */       throwDTDUnexpectedChar(c, "; expected a single or double quote to enclose the namespace URI");
/*      */     } 
/*      */     String uri = parseSystemId(c, false, "in namespace URI");
/*      */     if ((this.mConfigFlags & 0x800) != 0)
/*      */       uri = InternCache.getInstance().intern(uri); 
/*      */     c = skipDtdWs(true);
/*      */     if (c != '>')
/*      */       throwDTDUnexpectedChar(c, "; expected '>' to end TARGETNS directive"); 
/*      */     if (name == null) {
/*      */       this.mDefaultNsURI = uri;
/*      */     } else {
/*      */       if (this.mNamespaces == null)
/*      */         this.mNamespaces = new HashMap(); 
/*      */       this.mNamespaces.put(name, uri);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void handleAttrDecl(DTDElement elem, char c, int index, Location loc) throws XMLStreamException {
/*      */     DefaultAttrValue defVal;
/*      */     DTDAttribute attr;
/*      */     PrefixedName attrName = readDTDQName(c);
/*      */     c = skipObligatoryDtdWs();
/*      */     int type = 0;
/*      */     WordResolver enumValues = null;
/*      */     if (c == '(') {
/*      */       enumValues = parseEnumerated(elem, attrName, false);
/*      */       type = 1;
/*      */     } else {
/*      */       String typeStr = readDTDName(c);
/*      */       switch (typeStr.charAt(0)) {
/*      */         case 'C':
/*      */           if (typeStr == "CDATA") {
/*      */             type = 0;
/*      */             break;
/*      */           } 
/*      */         case 'I':
/*      */           if (typeStr == "ID") {
/*      */             type = 2;
/*      */             break;
/*      */           } 
/*      */           if (typeStr == "IDREF") {
/*      */             type = 3;
/*      */             break;
/*      */           } 
/*      */           if (typeStr == "IDREFS") {
/*      */             type = 4;
/*      */             break;
/*      */           } 
/*      */         case 'E':
/*      */           if (typeStr == "ENTITY") {
/*      */             type = 5;
/*      */             break;
/*      */           } 
/*      */           if (typeStr == "ENTITIES") {
/*      */             type = 6;
/*      */             break;
/*      */           } 
/*      */         case 'N':
/*      */           if (typeStr == "NOTATION") {
/*      */             type = 7;
/*      */             c = skipObligatoryDtdWs();
/*      */             if (c != '(')
/*      */               throwDTDUnexpectedChar(c, "Excepted '(' to start the list of NOTATION ids"); 
/*      */             enumValues = parseEnumerated(elem, attrName, true);
/*      */             break;
/*      */           } 
/*      */           if (typeStr == "NMTOKEN") {
/*      */             type = 8;
/*      */             break;
/*      */           } 
/*      */           if (typeStr == "NMTOKENS") {
/*      */             type = 9;
/*      */             break;
/*      */           } 
/*      */         default:
/*      */           throwDTDAttrError("Unrecognized attribute type '" + typeStr + "'" + ErrorConsts.ERR_DTD_ATTR_TYPE, elem, attrName);
/*      */           break;
/*      */       } 
/*      */     } 
/*      */     c = skipObligatoryDtdWs();
/*      */     if (c == '#') {
/*      */       String defTypeStr = readDTDName(getNextExpanded());
/*      */       if (defTypeStr == "REQUIRED") {
/*      */         defVal = DefaultAttrValue.constructRequired();
/*      */       } else if (defTypeStr == "IMPLIED") {
/*      */         defVal = DefaultAttrValue.constructImplied();
/*      */       } else if (defTypeStr == "FIXED") {
/*      */         defVal = DefaultAttrValue.constructFixed();
/*      */         c = skipObligatoryDtdWs();
/*      */         parseAttrDefaultValue(defVal, c, attrName, loc, true);
/*      */       } else {
/*      */         throwDTDAttrError("Unrecognized attribute default value directive #" + defTypeStr + ErrorConsts.ERR_DTD_DEFAULT_TYPE, elem, attrName);
/*      */         defVal = null;
/*      */       } 
/*      */     } else {
/*      */       defVal = DefaultAttrValue.constructOptional();
/*      */       parseAttrDefaultValue(defVal, c, attrName, loc, false);
/*      */     } 
/*      */     if (type == 2 && defVal.hasDefaultValue()) {
/*      */       if (this.mCfgFullyValidating)
/*      */         throwDTDAttrError("has type ID; can not have a default (or #FIXED) value (XML 1.0/#3.3.1)", elem, attrName); 
/*      */     } else if (this.mConfig.willDoXmlIdTyping() && attrName.isXmlReservedAttr(this.mCfgNsEnabled, "id")) {
/*      */       checkXmlIdAttr(type);
/*      */     } 
/*      */     if (attrName.isXmlReservedAttr(this.mCfgNsEnabled, "space"))
/*      */       checkXmlSpaceAttr(type, enumValues); 
/*      */     if (this.mCfgNsEnabled && attrName.isaNsDeclaration()) {
/*      */       if (!defVal.hasDefaultValue())
/*      */         return; 
/*      */       attr = elem.addNsDefault((InputProblemReporter)this, attrName, type, defVal, this.mCfgFullyValidating);
/*      */     } else {
/*      */       attr = elem.addAttribute((InputProblemReporter)this, attrName, type, defVal, enumValues, this.mCfgFullyValidating);
/*      */     } 
/*      */     if (attr == null) {
/*      */       XMLReporter rep = this.mConfig.getXMLReporter();
/*      */       if (rep != null) {
/*      */         String msg = MessageFormat.format(ErrorConsts.W_DTD_ATTR_REDECL, new Object[] { attrName, elem });
/*      */         _reportWarning(rep, ErrorConsts.WT_ATTR_DECL, msg, loc);
/*      */       } 
/*      */     } else if (defVal.hasDefaultValue()) {
/*      */       attr.normalizeDefault();
/*      */       if (this.mCfgFullyValidating)
/*      */         attr.validateDefault((InputProblemReporter)this, true); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private WordResolver parseEnumerated(DTDElement elem, PrefixedName attrName, boolean isNotation) throws XMLStreamException {
/*      */     HashMap sharedEnums;
/*      */     TreeSet set = new TreeSet();
/*      */     char c = skipDtdWs(true);
/*      */     if (c == ')')
/*      */       throwDTDUnexpectedChar(c, " (empty list; missing identifier(s))?"); 
/*      */     if (isNotation) {
/*      */       sharedEnums = null;
/*      */     } else {
/*      */       sharedEnums = this.mSharedEnumValues;
/*      */       if (sharedEnums == null && !isNotation)
/*      */         this.mSharedEnumValues = sharedEnums = new HashMap(); 
/*      */     } 
/*      */     String id = isNotation ? readNotationEntry(c, attrName, elem.getLocation()) : readEnumEntry(c, sharedEnums);
/*      */     set.add(id);
/*      */     while (true) {
/*      */       c = skipDtdWs(true);
/*      */       if (c == ')')
/*      */         break; 
/*      */       if (c != '|')
/*      */         throwDTDUnexpectedChar(c, "; missing '|' separator?"); 
/*      */       c = skipDtdWs(true);
/*      */       id = isNotation ? readNotationEntry(c, attrName, elem.getLocation()) : readEnumEntry(c, sharedEnums);
/*      */       if (!set.add(id))
/*      */         if (this.mCfgFullyValidating)
/*      */           throwDTDAttrError("Duplicate enumeration value '" + id + "'", elem, attrName);  
/*      */     } 
/*      */     return WordResolver.constructInstance(set);
/*      */   }
/*      */   
/*      */   private String readNotationEntry(char c, PrefixedName attrName, Location refLoc) throws XMLStreamException {
/*      */     String id = readDTDName(c);
/*      */     if (this.mPredefdNotations != null) {
/*      */       NotationDeclaration notationDeclaration = (NotationDeclaration)this.mPredefdNotations.get(id);
/*      */       if (notationDeclaration != null) {
/*      */         this.mUsesPredefdNotations = true;
/*      */         return notationDeclaration.getName();
/*      */       } 
/*      */     } 
/*      */     NotationDeclaration decl = (this.mNotations == null) ? null : (NotationDeclaration)this.mNotations.get(id);
/*      */     if (decl == null) {
/*      */       if (this.mCfgFullyValidating) {
/*      */         if (this.mNotationForwardRefs == null)
/*      */           this.mNotationForwardRefs = new LinkedHashMap(); 
/*      */         this.mNotationForwardRefs.put(id, refLoc);
/*      */       } 
/*      */       return id;
/*      */     } 
/*      */     return decl.getName();
/*      */   }
/*      */   
/*      */   private String readEnumEntry(char c, HashMap sharedEnums) throws XMLStreamException {
/*      */     String id = readDTDNmtoken(c);
/*      */     String sid = (String)sharedEnums.get(id);
/*      */     if (sid == null) {
/*      */       sid = id;
/*      */       sharedEnums.put(sid, sid);
/*      */     } 
/*      */     return sid;
/*      */   }
/*      */   
/*      */   private StructValidator readMixedSpec(PrefixedName elemName, boolean construct) throws XMLStreamException {
/*      */     String keyw = checkDTDKeyword("PCDATA");
/*      */     if (keyw != null)
/*      */       _reportWFCViolation("Unrecognized directive #" + keyw + "'; expected #PCDATA (or element name)"); 
/*      */     HashMap m = new LinkedHashMap();
/*      */     while (true) {
/*      */       char c1 = skipDtdWs(true);
/*      */       if (c1 == ')')
/*      */         break; 
/*      */       if (c1 == '|') {
/*      */         c1 = skipDtdWs(true);
/*      */       } else if (c1 == ',') {
/*      */         throwDTDUnexpectedChar(c1, " (sequences not allowed within mixed content)");
/*      */       } else if (c1 == '(') {
/*      */         throwDTDUnexpectedChar(c1, " (sub-content specs not allowed within mixed content)");
/*      */       } else {
/*      */         throwDTDUnexpectedChar(c1, "; expected either '|' to separate elements, or ')' to close the list");
/*      */       } 
/*      */       PrefixedName n = readDTDQName(c1);
/*      */       Object old = m.put(n, TokenContentSpec.construct(' ', n));
/*      */       if (old != null)
/*      */         if (this.mCfgFullyValidating)
/*      */           throwDTDElemError("duplicate child element <" + n + "> in mixed content model", elemName);  
/*      */     } 
/*      */     char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(getErrorMsg());
/*      */     if (c != '*') {
/*      */       if (m.size() > 0)
/*      */         _reportWFCViolation("Missing trailing '*' after a non-empty mixed content specification"); 
/*      */       this.mInputPtr--;
/*      */     } 
/*      */     if (!construct)
/*      */       return null; 
/*      */     if (m.isEmpty())
/*      */       return EmptyValidator.getPcdataInstance(); 
/*      */     ContentSpec spec = ChoiceContentSpec.constructMixed(this.mCfgNsEnabled, m.values());
/*      */     StructValidator val = spec.getSimpleValidator();
/*      */     if (val == null) {
/*      */       DFAState dfa = DFAState.constructDFA(spec);
/*      */       val = new DFAValidator(dfa);
/*      */     } 
/*      */     return val;
/*      */   }
/*      */   
/*      */   private ContentSpec readContentSpec(PrefixedName elemName, boolean mainLevel, boolean construct) throws XMLStreamException {
/*      */     ArrayList subSpecs = new ArrayList();
/*      */     boolean isChoice = false;
/*      */     boolean choiceSet = false;
/*      */     while (true) {
/*      */       char c = skipDtdWs(true);
/*      */       if (c == ')') {
/*      */         if (subSpecs.isEmpty())
/*      */           _reportWFCViolation("Empty content specification for '" + elemName + "' (need at least one entry)"); 
/*      */         break;
/*      */       } 
/*      */       if (c == '|' || c == ',') {
/*      */         boolean newChoice = (c == '|');
/*      */         if (!choiceSet) {
/*      */           isChoice = newChoice;
/*      */           choiceSet = true;
/*      */         } else if (isChoice != newChoice) {
/*      */           _reportWFCViolation("Can not mix content spec separators ('|' and ','); need to use parenthesis groups");
/*      */         } 
/*      */         c = skipDtdWs(true);
/*      */       } else if (!subSpecs.isEmpty()) {
/*      */         throwDTDUnexpectedChar(c, " (missing separator '|' or ','?)");
/*      */       } 
/*      */       if (c == '(') {
/*      */         ContentSpec contentSpec = readContentSpec(elemName, false, construct);
/*      */         subSpecs.add(contentSpec);
/*      */         continue;
/*      */       } 
/*      */       if (c == '|' || c == ',')
/*      */         throwDTDUnexpectedChar(c, " (missing element name?)"); 
/*      */       PrefixedName thisName = readDTDQName(c);
/*      */       char c1 = readArity();
/*      */       ContentSpec cs = construct ? TokenContentSpec.construct(c1, thisName) : TokenContentSpec.getDummySpec();
/*      */       subSpecs.add(cs);
/*      */     } 
/*      */     char arity = readArity();
/*      */     if (!construct)
/*      */       return TokenContentSpec.getDummySpec(); 
/*      */     if (subSpecs.size() == 1) {
/*      */       ContentSpec cs = subSpecs.get(0);
/*      */       char otherArity = cs.getArity();
/*      */       if (arity != otherArity)
/*      */         cs.setArity(combineArities(arity, otherArity)); 
/*      */       return cs;
/*      */     } 
/*      */     if (isChoice)
/*      */       return ChoiceContentSpec.constructChoice(this.mCfgNsEnabled, arity, subSpecs); 
/*      */     return SeqContentSpec.construct(this.mCfgNsEnabled, arity, subSpecs);
/*      */   }
/*      */   
/*      */   private static char combineArities(char arity1, char arity2) {
/*      */     if (arity1 == arity2)
/*      */       return arity1; 
/*      */     if (arity1 == ' ')
/*      */       return arity2; 
/*      */     if (arity2 == ' ')
/*      */       return arity1; 
/*      */     if (arity1 == '*' || arity2 == '*')
/*      */       return '*'; 
/*      */     return '*';
/*      */   }
/*      */   
/*      */   private EntityDecl handleExternalEntityDecl(WstxInputSource inputSource, boolean isParam, String id, char c, Location evtLoc) throws XMLStreamException {
/*      */     boolean isPublic = checkPublicSystemKeyword(c);
/*      */     String pubId = null;
/*      */     if (isPublic) {
/*      */       c = skipObligatoryDtdWs();
/*      */       if (c != '"' && c != '\'')
/*      */         throwDTDUnexpectedChar(c, "; expected a quote to start the public identifier"); 
/*      */       pubId = parsePublicId(c, getErrorMsg());
/*      */       c = getNextExpanded();
/*      */       if (c <= ' ') {
/*      */         c = skipDtdWs(true);
/*      */       } else if (c != '>') {
/*      */         this.mInputPtr--;
/*      */         c = skipObligatoryDtdWs();
/*      */       } 
/*      */       if (c == '>')
/*      */         _reportWFCViolation("Unexpected end of ENTITY declaration (expected a system id after public id): trying to use an SGML DTD instead of XML one?"); 
/*      */     } else {
/*      */       c = skipObligatoryDtdWs();
/*      */     } 
/*      */     if (c != '"' && c != '\'')
/*      */       throwDTDUnexpectedChar(c, "; expected a quote to start the system identifier"); 
/*      */     String sysId = parseSystemId(c, this.mNormalizeLFs, getErrorMsg());
/*      */     String notationId = null;
/*      */     if (isParam) {
/*      */       c = skipDtdWs(true);
/*      */     } else {
/*      */       int i = peekNext();
/*      */       if (i == 62) {
/*      */         c = '>';
/*      */         this.mInputPtr++;
/*      */       } else if (i < 0) {
/*      */         c = skipDtdWs(true);
/*      */       } else if (i == 37) {
/*      */         c = getNextExpanded();
/*      */       } else {
/*      */         this.mInputPtr++;
/*      */         c = (char)i;
/*      */         if (!isSpaceChar(c))
/*      */           throwDTDUnexpectedChar(c, "; expected a separating space or closing '>'"); 
/*      */         c = skipDtdWs(true);
/*      */       } 
/*      */       if (c != '>') {
/*      */         if (!isNameStartChar(c))
/*      */           throwDTDUnexpectedChar(c, "; expected either NDATA keyword, or closing '>'"); 
/*      */         String keyw = checkDTDKeyword("DATA");
/*      */         if (keyw != null)
/*      */           _reportWFCViolation("Unrecognized keyword '" + keyw + "'; expected NOTATION (or closing '>')"); 
/*      */         c = skipObligatoryDtdWs();
/*      */         notationId = readNotationEntry(c, (PrefixedName)null, evtLoc);
/*      */         c = skipDtdWs(true);
/*      */       } 
/*      */     } 
/*      */     if (c != '>')
/*      */       throwDTDUnexpectedChar(c, "; expected closing '>'"); 
/*      */     URL ctxt = inputSource.getSource();
/*      */     if (notationId == null)
/*      */       return (EntityDecl)new ParsedExtEntity(evtLoc, id, ctxt, pubId, sysId); 
/*      */     return (EntityDecl)new UnparsedExtEntity(evtLoc, id, ctxt, pubId, sysId, notationId);
/*      */   }
/*      */   
/*      */   private HashMap getElementMap() {
/*      */     HashMap m = this.mElements;
/*      */     if (m == null)
/*      */       this.mElements = m = new LinkedHashMap(); 
/*      */     return m;
/*      */   }
/*      */   
/*      */   private PrefixedName findSharedName(String prefix, String localName) {
/* 3291 */     HashMap m = this.mSharedNames;
/* 3293 */     if (this.mSharedNames == null) {
/* 3294 */       this.mSharedNames = m = new HashMap();
/*      */     } else {
/* 3297 */       PrefixedName key = this.mAccessKey;
/* 3298 */       key.reset(prefix, localName);
/* 3299 */       key = (PrefixedName)m.get(key);
/* 3300 */       if (key != null)
/* 3301 */         return key; 
/*      */     } 
/* 3306 */     PrefixedName result = new PrefixedName(prefix, localName);
/* 3307 */     m.put(result, result);
/* 3308 */     return result;
/*      */   }
/*      */   
/*      */   protected EntityDecl findEntity(String id, Object arg) {
/* 3325 */     if (arg == ENTITY_EXP_PE) {
/* 3326 */       EntityDecl ed = (this.mPredefdPEs == null) ? null : (EntityDecl)this.mPredefdPEs.get(id);
/* 3327 */       if (ed != null) {
/* 3328 */         this.mUsesPredefdEntities = true;
/* 3333 */         this.mRefdPEs = null;
/* 3334 */       } else if (this.mParamEntities != null) {
/* 3335 */         ed = (EntityDecl)this.mParamEntities.get(id);
/* 3336 */         if (ed != null && 
/* 3337 */           !this.mUsesPredefdEntities) {
/* 3339 */           Set used = this.mRefdPEs;
/* 3340 */           if (used == null)
/* 3341 */             this.mRefdPEs = used = new HashSet(); 
/* 3343 */           used.add(id);
/*      */         } 
/*      */       } 
/* 3347 */       return ed;
/*      */     } 
/* 3351 */     if (arg == ENTITY_EXP_GE) {
/* 3356 */       EntityDecl ed = (this.mPredefdGEs == null) ? null : (EntityDecl)this.mPredefdGEs.get(id);
/* 3357 */       if (ed != null) {
/* 3358 */         this.mUsesPredefdEntities = true;
/* 3363 */         this.mRefdGEs = null;
/* 3364 */       } else if (this.mGeneralEntities != null) {
/* 3365 */         ed = (EntityDecl)this.mGeneralEntities.get(id);
/* 3366 */         if (ed != null)
/* 3368 */           if (!this.mUsesPredefdEntities) {
/* 3370 */             if (this.mRefdGEs == null)
/* 3371 */               this.mRefdGEs = new HashSet(); 
/* 3373 */             this.mRefdGEs.add(id);
/*      */           }  
/*      */       } 
/* 3377 */       return ed;
/*      */     } 
/* 3380 */     throw new IllegalStateException(ErrorConsts.ERR_INTERNAL);
/*      */   }
/*      */   
/*      */   protected void handleUndeclaredEntity(String id) throws XMLStreamException {
/* 3390 */     _reportVCViolation("Undeclared parameter entity '" + id + "'.");
/* 3391 */     if (this.mCurrAttrDefault != null) {
/* 3392 */       WstxInputLocation wstxInputLocation = getLastCharLocation();
/* 3393 */       if (this.mExpandingPE) {
/* 3394 */         this.mCurrAttrDefault.addUndeclaredPE(id, (Location)wstxInputLocation);
/*      */       } else {
/* 3396 */         this.mCurrAttrDefault.addUndeclaredGE(id, (Location)wstxInputLocation);
/*      */       } 
/*      */     } 
/* 3399 */     if (this.mEventListener != null)
/* 3401 */       if (this.mExpandingPE)
/* 3402 */         this.mEventListener.dtdSkippedEntity("%" + id);  
/*      */   }
/*      */   
/*      */   protected void handleIncompleteEntityProblem(WstxInputSource closing) throws XMLStreamException {
/* 3419 */     if (closing.getScopeId() == 0) {
/* 3421 */       _reportWFCViolation(entityDesc(closing) + ": " + "Incomplete PE: has to fully contain a declaration (as per xml 1.0.3, section 2.8, WFC 'PE Between Declarations')");
/* 3425 */     } else if (this.mCfgFullyValidating) {
/* 3426 */       _reportVCViolation(entityDesc(closing) + ": " + "Incomplete PE: has to be fully contained in a declaration (as per xml 1.0.3, section 2.8, VC 'Proper Declaration/PE Nesting')");
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void handleGreedyEntityProblem(WstxInputSource input) throws XMLStreamException {
/* 3436 */     if (this.mCfgFullyValidating)
/* 3437 */       _reportWFCViolation(entityDesc(input) + ": " + "Unbalanced PE: has to be fully contained in a declaration (as per xml 1.0.3, section 2.8, VC 'Proper Declaration/PE Nesting')"); 
/*      */   }
/*      */   
/*      */   protected void checkXmlSpaceAttr(int type, WordResolver enumValues) throws XMLStreamException {
/* 3451 */     boolean ok = (type == 1);
/* 3452 */     if (ok)
/* 3453 */       switch (enumValues.size()) {
/*      */         case 1:
/* 3455 */           ok = (enumValues.find("preserve") != null || enumValues.find("default") != null);
/*      */           break;
/*      */         case 2:
/* 3459 */           ok = (enumValues.find("preserve") != null && enumValues.find("default") != null);
/*      */           break;
/*      */         default:
/* 3463 */           ok = false;
/*      */           break;
/*      */       }  
/* 3467 */     if (!ok)
/* 3468 */       _reportVCViolation(ErrorConsts.ERR_DTD_XML_SPACE); 
/*      */   }
/*      */   
/*      */   protected void checkXmlIdAttr(int type) throws XMLStreamException {
/* 3475 */     if (type != 2)
/* 3476 */       _reportVCViolation(ErrorConsts.ERR_DTD_XML_ID); 
/*      */   }
/*      */   
/*      */   private void _reportWarning(XMLReporter rep, String probType, String msg, Location loc) throws XMLStreamException {
/* 3490 */     if (rep != null) {
/* 3494 */       XMLValidationProblem prob = new XMLValidationProblem(loc, msg, 1, probType);
/* 3496 */       rep.report(msg, probType, prob, loc);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\FullDTDReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */