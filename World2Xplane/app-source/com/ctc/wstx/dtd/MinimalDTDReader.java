/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.ent.EntityDecl;
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.io.WstxInputSource;
/*     */ import com.ctc.wstx.sr.StreamScanner;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public class MinimalDTDReader extends StreamScanner {
/*     */   final boolean mIsExternal;
/*     */   
/*     */   private MinimalDTDReader(WstxInputSource input, ReaderConfig cfg) {
/*  57 */     this(input, cfg, false);
/*     */   }
/*     */   
/*     */   protected MinimalDTDReader(WstxInputSource input, ReaderConfig cfg, boolean isExt) {
/*  66 */     super(input, cfg, cfg.getDtdResolver());
/*  67 */     this.mIsExternal = isExt;
/*  71 */     this.mCfgReplaceEntities = true;
/*     */   }
/*     */   
/*     */   public static void skipInternalSubset(WstxInputData srcData, WstxInputSource input, ReaderConfig cfg) throws XMLStreamException {
/*  87 */     MinimalDTDReader r = new MinimalDTDReader(input, cfg);
/*  89 */     r.copyBufferStateFrom(srcData);
/*     */     try {
/*  91 */       r.skipInternalSubset();
/*     */     } finally {
/*  97 */       srcData.copyBufferStateFrom((WstxInputData)r);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Location getLocation() {
/* 114 */     return (Location)getStartLocation();
/*     */   }
/*     */   
/*     */   protected EntityDecl findEntity(String id, Object arg) {
/* 119 */     throwIllegalCall();
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   protected void handleUndeclaredEntity(String id) throws XMLStreamException {}
/*     */   
/*     */   protected void handleIncompleteEntityProblem(WstxInputSource closing) throws XMLStreamException {}
/*     */   
/*     */   protected char handleExpandedSurrogate(char first, char second) {
/* 146 */     return first;
/*     */   }
/*     */   
/*     */   public EntityDecl findEntity(String entName) {
/* 164 */     return null;
/*     */   }
/*     */   
/*     */   protected void skipInternalSubset() throws XMLStreamException {
/*     */     while (true) {
/* 181 */       int i = getNextAfterWS();
/* 182 */       if (i < 0)
/* 184 */         throwUnexpectedEOF(" in internal DTD subset"); 
/* 186 */       if (i == 37) {
/* 187 */         skipPE();
/*     */         continue;
/*     */       } 
/* 190 */       if (i == 60) {
/* 194 */         char c = getNextSkippingPEs();
/* 195 */         if (c == '?') {
/* 200 */           skipPI();
/*     */           continue;
/*     */         } 
/* 201 */         if (c == '!') {
/* 202 */           c = getNextSkippingPEs();
/* 203 */           if (c == '[')
/*     */             continue; 
/* 209 */           if (c == '-') {
/* 210 */             skipComment();
/*     */             continue;
/*     */           } 
/* 211 */           if (c >= 'A' && c <= 'Z') {
/* 212 */             skipDeclaration(c);
/*     */             continue;
/*     */           } 
/* 217 */           skipDeclaration(c);
/*     */           continue;
/*     */         } 
/* 223 */         this.mInputPtr--;
/*     */         continue;
/*     */       } 
/* 228 */       if (i == 93) {
/* 233 */         if (this.mInput != this.mRootInput)
/* 234 */           throwParseError("Encountered int. subset end marker ']]>' in an expanded entity; has to be at main level."); 
/*     */         break;
/*     */       } 
/* 239 */       throwUnexpectedChar(i, " in internal DTD subset; expected a '<' to start a directive, or \"]>\" to end internal subset.");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected char dtdNextFromCurr() throws XMLStreamException {
/* 252 */     return (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(getErrorMsg());
/*     */   }
/*     */   
/*     */   protected char dtdNextChar() throws XMLStreamException {
/* 259 */     return (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(getErrorMsg());
/*     */   }
/*     */   
/*     */   protected char getNextSkippingPEs() throws XMLStreamException {
/*     */     while (true) {
/* 267 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(getErrorMsg());
/* 269 */       if (c != '%')
/* 270 */         return c; 
/* 272 */       skipPE();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void skipPE() throws XMLStreamException {
/* 285 */     skipDTDName();
/* 290 */     char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 292 */     if (c != ';')
/* 293 */       this.mInputPtr--; 
/*     */   }
/*     */   
/*     */   protected void skipComment() throws XMLStreamException {
/* 300 */     skipCommentContent();
/* 302 */     char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 304 */     if (c != '>')
/* 305 */       throwParseError("String '--' not allowed in comment (missing '>'?)"); 
/*     */   }
/*     */   
/*     */   protected void skipCommentContent() throws XMLStreamException {
/*     */     while (true) {
/* 313 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 315 */       if (c == '-') {
/* 316 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 318 */         if (c == '-')
/*     */           return; 
/*     */         continue;
/*     */       } 
/* 321 */       if (c == '\n' || c == '\r')
/* 322 */         skipCRLF(c); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void skipPI() throws XMLStreamException {
/*     */     while (true) {
/* 331 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 333 */       if (c == '?')
/*     */         while (true) {
/* 335 */           c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 337 */           if (c != '?') {
/* 338 */             if (c == '>')
/*     */               return; 
/*     */             break;
/*     */           } 
/*     */         }  
/* 342 */       if (c == '\n' || c == '\r')
/* 343 */         skipCRLF(c); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void skipDeclaration(char c) throws XMLStreamException {
/* 351 */     while (c != '>') {
/* 352 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 354 */       if (c == '\n' || c == '\r') {
/* 355 */         skipCRLF(c);
/*     */         continue;
/*     */       } 
/* 362 */       if (c == '\'' || c == '"')
/* 363 */         skipLiteral(c); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void skipLiteral(char quoteChar) throws XMLStreamException {
/*     */     while (true) {
/* 372 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : dtdNextFromCurr();
/* 374 */       if (c == '\n' || c == '\r') {
/* 375 */         skipCRLF(c);
/*     */         continue;
/*     */       } 
/* 376 */       if (c == quoteChar)
/*     */         break; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void skipDTDName() throws XMLStreamException {
/* 388 */     skipFullName(getNextChar(getErrorMsg()));
/*     */   }
/*     */   
/*     */   protected String getErrorMsg() {
/* 401 */     return this.mIsExternal ? " in external DTD subset" : " in internal DTD subset";
/*     */   }
/*     */   
/*     */   protected void throwIllegalCall() throws Error {
/* 408 */     throw new IllegalStateException("Internal error: this method should never be called");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\MinimalDTDReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */