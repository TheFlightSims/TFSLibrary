/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.exc.WstxException;
/*     */ import com.ctc.wstx.exc.WstxParsingException;
/*     */ import com.ctc.wstx.exc.WstxUnexpectedCharException;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public abstract class InputBootstrapper {
/*     */   protected static final String ERR_XMLDECL_KW_VERSION = "; expected keyword 'version'";
/*     */   
/*     */   protected static final String ERR_XMLDECL_KW_ENCODING = "; expected keyword 'encoding'";
/*     */   
/*     */   protected static final String ERR_XMLDECL_KW_STANDALONE = "; expected keyword 'standalone'";
/*     */   
/*     */   protected static final String ERR_XMLDECL_END_MARKER = "; expected \"?>\" end marker";
/*     */   
/*     */   protected static final String ERR_XMLDECL_EXP_SPACE = "; expected a white space";
/*     */   
/*     */   protected static final String ERR_XMLDECL_EXP_EQ = "; expected '=' after ";
/*     */   
/*     */   protected static final String ERR_XMLDECL_EXP_ATTRVAL = "; expected a quote character enclosing value for ";
/*     */   
/*     */   public static final char CHAR_NULL = '\000';
/*     */   
/*     */   public static final char CHAR_SPACE = ' ';
/*     */   
/*     */   public static final char CHAR_NEL = '';
/*     */   
/*     */   public static final byte CHAR_CR = 13;
/*     */   
/*     */   public static final byte CHAR_LF = 10;
/*     */   
/*     */   public static final byte BYTE_NULL = 0;
/*     */   
/*     */   public static final byte BYTE_CR = 13;
/*     */   
/*     */   public static final byte BYTE_LF = 10;
/*     */   
/*     */   protected final String mPublicId;
/*     */   
/*     */   protected final String mSystemId;
/*     */   
/*  76 */   protected int mInputProcessed = 0;
/*     */   
/*  82 */   protected int mInputRow = 1;
/*     */   
/*  90 */   protected int mInputRowStart = 0;
/*     */   
/* 103 */   int mDeclaredXmlVersion = 0;
/*     */   
/*     */   String mFoundEncoding;
/*     */   
/*     */   String mStandalone;
/*     */   
/*     */   boolean mXml11Handling = false;
/*     */   
/* 133 */   final char[] mKeyword = new char[60];
/*     */   
/*     */   protected InputBootstrapper(String pubId, String sysId) {
/* 143 */     this.mPublicId = pubId;
/* 144 */     this.mSystemId = sysId;
/*     */   }
/*     */   
/*     */   protected void initFrom(InputBootstrapper src) {
/* 149 */     this.mInputProcessed = src.mInputProcessed;
/* 150 */     this.mInputRow = src.mInputRow;
/* 151 */     this.mInputRowStart = src.mInputRowStart;
/* 152 */     this.mDeclaredXmlVersion = src.mDeclaredXmlVersion;
/* 153 */     this.mFoundEncoding = src.mFoundEncoding;
/* 154 */     this.mStandalone = src.mStandalone;
/* 155 */     this.mXml11Handling = src.mXml11Handling;
/*     */   }
/*     */   
/*     */   public abstract Reader bootstrapInput(ReaderConfig paramReaderConfig, boolean paramBoolean, int paramInt) throws IOException, XMLStreamException;
/*     */   
/*     */   public String getPublicId() {
/* 178 */     return this.mPublicId;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/* 179 */     return this.mSystemId;
/*     */   }
/*     */   
/*     */   public int getDeclaredVersion() {
/* 185 */     return this.mDeclaredXmlVersion;
/*     */   }
/*     */   
/*     */   public boolean declaredXml11() {
/* 193 */     return (this.mDeclaredXmlVersion == 272);
/*     */   }
/*     */   
/*     */   public String getStandalone() {
/* 197 */     return this.mStandalone;
/*     */   }
/*     */   
/*     */   public String getDeclaredEncoding() {
/* 205 */     return this.mFoundEncoding;
/*     */   }
/*     */   
/*     */   public abstract int getInputTotal();
/*     */   
/*     */   public int getInputRow() {
/* 217 */     return this.mInputRow;
/*     */   }
/*     */   
/*     */   public abstract int getInputColumn();
/*     */   
/*     */   public abstract String getInputEncoding();
/*     */   
/*     */   protected void readXmlDecl(boolean isMainDoc, int xmlVersion) throws IOException, WstxException {
/* 253 */     int c = getNextAfterWs(false);
/* 257 */     if (c != 118) {
/* 258 */       if (isMainDoc)
/* 259 */         reportUnexpectedChar(c, "; expected keyword 'version'"); 
/*     */     } else {
/* 262 */       this.mDeclaredXmlVersion = readXmlVersion();
/* 263 */       c = getWsOrChar(63);
/*     */     } 
/* 271 */     boolean thisIs11 = (this.mDeclaredXmlVersion == 272);
/* 272 */     if (xmlVersion != 0) {
/* 273 */       this.mXml11Handling = (272 == xmlVersion);
/* 275 */       if (thisIs11 && !this.mXml11Handling)
/* 276 */         reportXmlProblem(ErrorConsts.ERR_XML_10_VS_11); 
/*     */     } else {
/* 279 */       this.mXml11Handling = thisIs11;
/*     */     } 
/* 283 */     if (c != 101) {
/* 284 */       if (!isMainDoc)
/* 285 */         reportUnexpectedChar(c, "; expected keyword 'encoding'"); 
/*     */     } else {
/* 288 */       this.mFoundEncoding = readXmlEncoding();
/* 289 */       c = getWsOrChar(63);
/*     */     } 
/* 293 */     if (isMainDoc && c == 115) {
/* 294 */       this.mStandalone = readXmlStandalone();
/* 295 */       c = getWsOrChar(63);
/*     */     } 
/* 300 */     if (c != 63)
/* 301 */       reportUnexpectedChar(c, "; expected \"?>\" end marker"); 
/* 303 */     c = getNext();
/* 304 */     if (c != 62)
/* 305 */       reportUnexpectedChar(c, "; expected \"?>\" end marker"); 
/*     */   }
/*     */   
/*     */   private final int readXmlVersion() throws IOException, WstxException {
/*     */     String got;
/* 315 */     int c = checkKeyword("version");
/* 316 */     if (c != 0)
/* 317 */       reportUnexpectedChar(c, "version"); 
/* 319 */     c = handleEq("version");
/* 320 */     int len = readQuotedValue(this.mKeyword, c);
/* 322 */     if (len == 3 && 
/* 323 */       this.mKeyword[0] == '1' && this.mKeyword[1] == '.') {
/* 324 */       c = this.mKeyword[2];
/* 325 */       if (c == 48)
/* 326 */         return 256; 
/* 328 */       if (c == 49)
/* 329 */         return 272; 
/*     */     } 
/* 337 */     if (len < 0) {
/* 338 */       got = "'" + new String(this.mKeyword) + "[..]'";
/* 339 */     } else if (len == 0) {
/* 340 */       got = "<empty>";
/*     */     } else {
/* 342 */       got = "'" + new String(this.mKeyword, 0, len) + "'";
/*     */     } 
/* 344 */     reportPseudoAttrProblem("version", got, "1.0", "1.1");
/* 346 */     return 0;
/*     */   }
/*     */   
/*     */   private final String readXmlEncoding() throws IOException, WstxException {
/* 352 */     int c = checkKeyword("encoding");
/* 353 */     if (c != 0)
/* 354 */       reportUnexpectedChar(c, "encoding"); 
/* 356 */     c = handleEq("encoding");
/* 358 */     int len = readQuotedValue(this.mKeyword, c);
/* 363 */     if (len == 0)
/* 364 */       reportPseudoAttrProblem("encoding", null, null, null); 
/* 368 */     if (len < 0)
/* 369 */       return new String(this.mKeyword); 
/* 371 */     return new String(this.mKeyword, 0, len);
/*     */   }
/*     */   
/*     */   private final String readXmlStandalone() throws IOException, WstxException {
/*     */     String got;
/* 377 */     int c = checkKeyword("standalone");
/* 378 */     if (c != 0)
/* 379 */       reportUnexpectedChar(c, "standalone"); 
/* 381 */     c = handleEq("standalone");
/* 382 */     int len = readQuotedValue(this.mKeyword, c);
/* 384 */     if (len == 2) {
/* 385 */       if (this.mKeyword[0] == 'n' && this.mKeyword[1] == 'o')
/* 386 */         return "no"; 
/* 388 */     } else if (len == 3 && 
/* 389 */       this.mKeyword[0] == 'y' && this.mKeyword[1] == 'e' && this.mKeyword[2] == 's') {
/* 391 */       return "yes";
/*     */     } 
/* 398 */     if (len < 0) {
/* 399 */       got = "'" + new String(this.mKeyword) + "[..]'";
/* 400 */     } else if (len == 0) {
/* 401 */       got = "<empty>";
/*     */     } else {
/* 403 */       got = "'" + new String(this.mKeyword, 0, len) + "'";
/*     */     } 
/* 406 */     reportPseudoAttrProblem("standalone", got, "yes", "no");
/* 408 */     return got;
/*     */   }
/*     */   
/*     */   private final int handleEq(String attr) throws IOException, WstxException {
/* 414 */     int c = getNextAfterWs(false);
/* 415 */     if (c != 61)
/* 416 */       reportUnexpectedChar(c, "; expected '=' after '" + attr + "'"); 
/* 419 */     c = getNextAfterWs(false);
/* 420 */     if (c != 34 && c != 39)
/* 421 */       reportUnexpectedChar(c, "; expected a quote character enclosing value for '" + attr + "'"); 
/* 423 */     return c;
/*     */   }
/*     */   
/*     */   private final int getWsOrChar(int ok) throws IOException, WstxException {
/* 434 */     int c = getNext();
/* 435 */     if (c == ok)
/* 436 */       return c; 
/* 438 */     if (c > 32)
/* 439 */       reportUnexpectedChar(c, "; expected either '" + (char)ok + "' or white space"); 
/* 441 */     if (c == 10 || c == 13)
/* 443 */       pushback(); 
/* 445 */     return getNextAfterWs(false);
/*     */   }
/*     */   
/*     */   protected abstract void pushback();
/*     */   
/*     */   protected abstract int getNext() throws IOException, WstxException;
/*     */   
/*     */   protected abstract int getNextAfterWs(boolean paramBoolean) throws IOException, WstxException;
/*     */   
/*     */   protected abstract int checkKeyword(String paramString) throws IOException, WstxException;
/*     */   
/*     */   protected abstract int readQuotedValue(char[] paramArrayOfchar, int paramInt) throws IOException, WstxException;
/*     */   
/*     */   protected abstract Location getLocation();
/*     */   
/*     */   protected void reportNull() throws WstxException {
/* 483 */     throw new WstxException("Illegal null byte in input stream", getLocation());
/*     */   }
/*     */   
/*     */   protected void reportXmlProblem(String msg) throws WstxException {
/* 490 */     throw new WstxParsingException(msg, getLocation());
/*     */   }
/*     */   
/*     */   protected void reportUnexpectedChar(int i, String msg) throws WstxException {
/*     */     String excMsg;
/* 496 */     char c = (char)i;
/* 500 */     if (Character.isISOControl(c)) {
/* 501 */       excMsg = "Unexpected character (CTRL-CHAR, code " + i + ")" + msg;
/*     */     } else {
/* 503 */       excMsg = "Unexpected character '" + c + "' (code " + i + ")" + msg;
/*     */     } 
/* 505 */     Location loc = getLocation();
/* 506 */     throw new WstxUnexpectedCharException(excMsg, loc, c);
/*     */   }
/*     */   
/*     */   private final void reportPseudoAttrProblem(String attrName, String got, String expVal1, String expVal2) throws WstxException {
/* 519 */     String expStr = (expVal1 == null) ? "" : ("; expected \"" + expVal1 + "\" or \"" + expVal2 + "\"");
/* 522 */     if (got == null || got.length() == 0)
/* 523 */       throw new WstxParsingException("Missing XML pseudo-attribute '" + attrName + "' value" + expStr, getLocation()); 
/* 526 */     throw new WstxParsingException("Invalid XML pseudo-attribute '" + attrName + "' value " + got + expStr, getLocation());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\InputBootstrapper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */