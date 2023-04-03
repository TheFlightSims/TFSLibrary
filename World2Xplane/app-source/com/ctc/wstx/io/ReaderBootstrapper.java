/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.exc.WstxEOFException;
/*     */ import com.ctc.wstx.exc.WstxException;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.util.StringUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.text.MessageFormat;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLReporter;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*     */ 
/*     */ public final class ReaderBootstrapper extends InputBootstrapper {
/*     */   static final char CHAR_BOM_MARKER = '﻿';
/*     */   
/*     */   final Reader mIn;
/*     */   
/*     */   final String mInputEncoding;
/*     */   
/*     */   private char[] mCharBuffer;
/*     */   
/*     */   private int mInputPtr;
/*     */   
/*     */   private int mInputEnd;
/*     */   
/*     */   private ReaderBootstrapper(String pubId, String sysId, Reader r, String appEncoding) {
/*  84 */     super(pubId, sysId);
/*  85 */     this.mIn = r;
/*  86 */     if (appEncoding == null && 
/*  87 */       r instanceof InputStreamReader)
/*  88 */       appEncoding = ((InputStreamReader)r).getEncoding(); 
/*  91 */     this.mInputEncoding = appEncoding;
/*     */   }
/*     */   
/*     */   public static ReaderBootstrapper getInstance(String pubId, String sysId, Reader r, String appEncoding) {
/* 111 */     return new ReaderBootstrapper(pubId, sysId, r, appEncoding);
/*     */   }
/*     */   
/*     */   public Reader bootstrapInput(ReaderConfig cfg, boolean mainDoc, int xmlVersion) throws IOException, XMLStreamException {
/* 131 */     this.mCharBuffer = (cfg == null) ? new char[128] : cfg.allocSmallCBuffer(128);
/* 133 */     initialLoad(7);
/* 140 */     if (this.mInputEnd >= 7) {
/* 141 */       char c = this.mCharBuffer[this.mInputPtr];
/* 144 */       if (c == '﻿')
/* 145 */         c = this.mCharBuffer[++this.mInputPtr]; 
/* 147 */       if (c == '<') {
/* 148 */         if (this.mCharBuffer[this.mInputPtr + 1] == '?' && this.mCharBuffer[this.mInputPtr + 2] == 'x' && this.mCharBuffer[this.mInputPtr + 3] == 'm' && this.mCharBuffer[this.mInputPtr + 4] == 'l' && this.mCharBuffer[this.mInputPtr + 5] <= ' ') {
/* 154 */           this.mInputPtr += 6;
/* 155 */           readXmlDecl(mainDoc, xmlVersion);
/* 157 */           if (this.mFoundEncoding != null && this.mInputEncoding != null)
/* 158 */             verifyXmlEncoding(cfg); 
/*     */         } 
/* 173 */       } else if (c == 'ï') {
/* 174 */         throw new WstxIOException("Unexpected first character (char code 0xEF), not valid in xml document: could be mangled UTF-8 BOM marker. Make sure that the Reader uses correct encoding or pass an InputStream instead");
/*     */       } 
/*     */     } 
/* 182 */     if (this.mInputPtr < this.mInputEnd)
/* 183 */       return new MergedReader(cfg, this.mIn, this.mCharBuffer, this.mInputPtr, this.mInputEnd); 
/* 186 */     return this.mIn;
/*     */   }
/*     */   
/*     */   public String getInputEncoding() {
/* 190 */     return this.mInputEncoding;
/*     */   }
/*     */   
/*     */   public int getInputTotal() {
/* 194 */     return this.mInputProcessed + this.mInputPtr;
/*     */   }
/*     */   
/*     */   public int getInputColumn() {
/* 198 */     return this.mInputPtr - this.mInputRowStart;
/*     */   }
/*     */   
/*     */   protected void verifyXmlEncoding(ReaderConfig cfg) throws XMLStreamException {
/* 210 */     String inputEnc = this.mInputEncoding;
/* 213 */     if (StringUtil.equalEncodings(inputEnc, this.mFoundEncoding))
/*     */       return; 
/* 222 */     XMLReporter rep = cfg.getXMLReporter();
/* 223 */     if (rep != null) {
/* 224 */       Location loc = getLocation();
/* 225 */       String msg = MessageFormat.format(ErrorConsts.W_MIXED_ENCODINGS, new Object[] { this.mFoundEncoding, inputEnc });
/* 228 */       String type = ErrorConsts.WT_XML_DECL;
/* 232 */       XMLValidationProblem prob = new XMLValidationProblem(loc, msg, 1, type);
/* 233 */       rep.report(msg, type, prob, loc);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean initialLoad(int minimum) throws IOException {
/* 246 */     this.mInputPtr = 0;
/* 247 */     this.mInputEnd = 0;
/* 249 */     while (this.mInputEnd < minimum) {
/* 250 */       int count = this.mIn.read(this.mCharBuffer, this.mInputEnd, this.mCharBuffer.length - this.mInputEnd);
/* 252 */       if (count < 1)
/* 253 */         return false; 
/* 255 */       this.mInputEnd += count;
/*     */     } 
/* 257 */     return true;
/*     */   }
/*     */   
/*     */   protected void loadMore() throws IOException, WstxException {
/* 267 */     this.mInputProcessed += this.mInputEnd;
/* 268 */     this.mInputRowStart -= this.mInputEnd;
/* 270 */     this.mInputPtr = 0;
/* 271 */     this.mInputEnd = this.mIn.read(this.mCharBuffer, 0, this.mCharBuffer.length);
/* 272 */     if (this.mInputEnd < 1)
/* 273 */       throw new WstxEOFException(" in xml declaration", getLocation()); 
/*     */   }
/*     */   
/*     */   protected void pushback() {
/* 285 */     this.mInputPtr--;
/*     */   }
/*     */   
/*     */   protected int getNext() throws IOException, WstxException {
/* 291 */     return (this.mInputPtr < this.mInputEnd) ? this.mCharBuffer[this.mInputPtr++] : nextChar();
/*     */   }
/*     */   
/*     */   protected int getNextAfterWs(boolean reqWs) throws IOException, WstxException {
/* 299 */     int count = 0;
/*     */     while (true) {
/* 302 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mCharBuffer[this.mInputPtr++] : nextChar();
/* 305 */       if (c > ' ') {
/* 306 */         if (reqWs && count == 0)
/* 307 */           reportUnexpectedChar(c, "; expected a white space"); 
/* 309 */         return c;
/*     */       } 
/* 311 */       if (c == '\r' || c == '\n') {
/* 312 */         skipCRLF(c);
/* 313 */       } else if (c == '\000') {
/* 314 */         reportNull();
/*     */       } 
/* 316 */       count++;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int checkKeyword(String exp) throws IOException, WstxException {
/* 327 */     int len = exp.length();
/* 329 */     for (int ptr = 1; ptr < len; ptr++) {
/* 330 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mCharBuffer[this.mInputPtr++] : nextChar();
/* 333 */       if (c != exp.charAt(ptr))
/* 334 */         return c; 
/* 336 */       if (c == '\000')
/* 337 */         reportNull(); 
/*     */     } 
/* 341 */     return 0;
/*     */   }
/*     */   
/*     */   protected int readQuotedValue(char[] kw, int quoteChar) throws IOException, WstxException {
/* 347 */     int i = 0;
/* 348 */     int len = kw.length;
/*     */     while (true) {
/* 351 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mCharBuffer[this.mInputPtr++] : nextChar();
/* 353 */       if (c == '\r' || c == '\n') {
/* 354 */         skipCRLF(c);
/* 355 */       } else if (c == '\000') {
/* 356 */         reportNull();
/*     */       } 
/* 358 */       if (c == quoteChar)
/* 359 */         return (i < len) ? i : -1; 
/* 362 */       if (i < len)
/* 363 */         kw[i++] = c; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Location getLocation() {
/* 370 */     return (Location)new WstxInputLocation(null, this.mPublicId, this.mSystemId, this.mInputProcessed + this.mInputPtr - 1, this.mInputRow, this.mInputPtr - this.mInputRowStart);
/*     */   }
/*     */   
/*     */   protected char nextChar() throws IOException, WstxException {
/* 384 */     if (this.mInputPtr >= this.mInputEnd)
/* 385 */       loadMore(); 
/* 387 */     return this.mCharBuffer[this.mInputPtr++];
/*     */   }
/*     */   
/*     */   protected void skipCRLF(char lf) throws IOException, WstxException {
/* 393 */     if (lf == '\r') {
/* 394 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mCharBuffer[this.mInputPtr++] : nextChar();
/* 396 */       if (c != '\n')
/* 397 */         this.mInputPtr--; 
/*     */     } 
/* 400 */     this.mInputRow++;
/* 401 */     this.mInputRowStart = this.mInputPtr;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\ReaderBootstrapper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */