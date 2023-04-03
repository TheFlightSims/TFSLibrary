/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.util.XmlChars;
/*     */ 
/*     */ public class WstxInputData {
/*     */   public static final char CHAR_NULL = '\000';
/*     */   
/*     */   public static final char INT_NULL = '\000';
/*     */   
/*     */   public static final char CHAR_SPACE = ' ';
/*     */   
/*     */   public static final char INT_SPACE = ' ';
/*     */   
/*     */   public static final int MAX_UNICODE_CHAR = 1114111;
/*     */   
/*     */   private static final int VALID_CHAR_COUNT = 256;
/*     */   
/*     */   private static final byte NAME_CHAR_INVALID_B = 0;
/*     */   
/*     */   private static final byte NAME_CHAR_ALL_VALID_B = 1;
/*     */   
/*     */   private static final byte NAME_CHAR_VALID_NONFIRST_B = -1;
/*     */   
/*  74 */   private static final byte[] sCharValidity = new byte[256];
/*     */   
/*     */   private static final int VALID_PUBID_CHAR_COUNT = 128;
/*     */   
/*     */   static {
/*  80 */     sCharValidity[95] = 1;
/*     */     int i, last;
/*  81 */     for (i = 0, last = 25; i <= last; i++) {
/*  82 */       sCharValidity[65 + i] = 1;
/*  83 */       sCharValidity[97 + i] = 1;
/*     */     } 
/*  86 */     for (i = 192; i < 256; i++)
/*  87 */       sCharValidity[i] = 1; 
/*  90 */     sCharValidity[215] = 0;
/*  91 */     sCharValidity[247] = 0;
/*  95 */     sCharValidity[45] = -1;
/*  96 */     sCharValidity[46] = -1;
/*  97 */     sCharValidity[183] = -1;
/*  98 */     for (i = 48; i <= 57; i++)
/*  99 */       sCharValidity[i] = -1; 
/*     */   }
/*     */   
/* 107 */   private static final byte[] sPubidValidity = new byte[128];
/*     */   
/*     */   private static final byte PUBID_CHAR_VALID_B = 1;
/*     */   
/*     */   static {
/* 111 */     for (i = 0, last = 25; i <= last; i++) {
/* 112 */       sPubidValidity[65 + i] = 1;
/* 113 */       sPubidValidity[97 + i] = 1;
/*     */     } 
/* 115 */     for (i = 48; i <= 57; i++)
/* 116 */       sPubidValidity[i] = 1; 
/* 120 */     sPubidValidity[10] = 1;
/* 121 */     sPubidValidity[13] = 1;
/* 122 */     sPubidValidity[32] = 1;
/* 125 */     sPubidValidity[45] = 1;
/* 126 */     sPubidValidity[39] = 1;
/* 127 */     sPubidValidity[40] = 1;
/* 128 */     sPubidValidity[41] = 1;
/* 129 */     sPubidValidity[43] = 1;
/* 130 */     sPubidValidity[44] = 1;
/* 131 */     sPubidValidity[46] = 1;
/* 132 */     sPubidValidity[47] = 1;
/* 133 */     sPubidValidity[58] = 1;
/* 134 */     sPubidValidity[61] = 1;
/* 135 */     sPubidValidity[63] = 1;
/* 136 */     sPubidValidity[59] = 1;
/* 137 */     sPubidValidity[33] = 1;
/* 138 */     sPubidValidity[42] = 1;
/* 139 */     sPubidValidity[35] = 1;
/* 140 */     sPubidValidity[64] = 1;
/* 141 */     sPubidValidity[36] = 1;
/* 142 */     sPubidValidity[95] = 1;
/* 143 */     sPubidValidity[37] = 1;
/*     */   }
/*     */   
/*     */   protected boolean mXml11 = false;
/*     */   
/*     */   protected char[] mInputBuffer;
/*     */   
/* 174 */   protected int mInputPtr = 0;
/*     */   
/* 179 */   protected int mInputEnd = 0;
/*     */   
/* 191 */   protected long mCurrInputProcessed = 0L;
/*     */   
/* 197 */   protected int mCurrInputRow = 1;
/*     */   
/* 205 */   protected int mCurrInputRowStart = 0;
/*     */   
/*     */   public void copyBufferStateFrom(WstxInputData src) {
/* 222 */     this.mInputBuffer = src.mInputBuffer;
/* 223 */     this.mInputPtr = src.mInputPtr;
/* 224 */     this.mInputEnd = src.mInputEnd;
/* 226 */     this.mCurrInputProcessed = src.mCurrInputProcessed;
/* 227 */     this.mCurrInputRow = src.mCurrInputRow;
/* 228 */     this.mCurrInputRowStart = src.mCurrInputRowStart;
/*     */   }
/*     */   
/*     */   protected final boolean isNameStartChar(char c) {
/* 249 */     if (c <= 'z') {
/* 250 */       if (c >= 'a')
/* 251 */         return true; 
/* 253 */       if (c < 'A')
/* 254 */         return false; 
/* 256 */       return (c <= 'Z' || c == '_');
/*     */     } 
/* 261 */     return this.mXml11 ? XmlChars.is11NameStartChar(c) : XmlChars.is10NameStartChar(c);
/*     */   }
/*     */   
/*     */   protected final boolean isNameChar(char c) {
/* 274 */     if (c <= 'z') {
/* 275 */       if (c >= 'a')
/* 276 */         return true; 
/* 278 */       if (c <= 'Z') {
/* 279 */         if (c >= 'A')
/* 280 */           return true; 
/* 283 */         return ((c >= '0' && c <= '9') || c == '.' || c == '-');
/*     */       } 
/* 285 */       return (c == '_');
/*     */     } 
/* 287 */     return this.mXml11 ? XmlChars.is11NameChar(c) : XmlChars.is10NameChar(c);
/*     */   }
/*     */   
/*     */   public static final boolean isNameStartChar(char c, boolean nsAware, boolean xml11) {
/* 295 */     if (c <= 'z') {
/* 296 */       if (c >= 'a')
/* 297 */         return true; 
/* 299 */       if (c < 'A') {
/* 300 */         if (c == ':' && !nsAware)
/* 301 */           return true; 
/* 303 */         return false;
/*     */       } 
/* 305 */       return (c <= 'Z' || c == '_');
/*     */     } 
/* 310 */     return xml11 ? XmlChars.is11NameStartChar(c) : XmlChars.is10NameStartChar(c);
/*     */   }
/*     */   
/*     */   public static final boolean isNameChar(char c, boolean nsAware, boolean xml11) {
/* 316 */     if (c <= 'z') {
/* 317 */       if (c >= 'a')
/* 318 */         return true; 
/* 320 */       if (c <= 'Z') {
/* 321 */         if (c >= 'A')
/* 322 */           return true; 
/* 325 */         return ((c >= '0' && c <= '9') || c == '.' || c == '-' || (c == ':' && !nsAware));
/*     */       } 
/* 328 */       return (c == '_');
/*     */     } 
/* 330 */     return xml11 ? XmlChars.is11NameChar(c) : XmlChars.is10NameChar(c);
/*     */   }
/*     */   
/*     */   public static final int findIllegalNameChar(String name, boolean nsAware, boolean xml11) {
/* 342 */     int len = name.length();
/* 343 */     if (len < 1)
/* 344 */       return -1; 
/* 347 */     char c = name.charAt(0);
/* 350 */     if (c <= 'z') {
/* 351 */       if (c < 'a')
/* 352 */         if (c < 'A') {
/* 353 */           if (c != ':' || nsAware)
/* 354 */             return 0; 
/* 356 */         } else if (c > 'Z' && c != '_') {
/* 358 */           return 0;
/*     */         }  
/* 362 */     } else if (xml11) {
/* 363 */       if (!XmlChars.is11NameStartChar(c))
/* 364 */         return 0; 
/* 367 */     } else if (!XmlChars.is10NameStartChar(c)) {
/* 368 */       return 0;
/*     */     } 
/* 373 */     for (int i = 1; i < len; i++) {
/* 374 */       c = name.charAt(i);
/* 375 */       if (c <= 'z') {
/* 376 */         if (c >= 'a')
/*     */           continue; 
/* 379 */         if (c <= 'Z') {
/* 380 */           if (c >= 'A')
/*     */             continue; 
/* 384 */           if ((c >= '0' && c <= '9') || c == '.' || c == '-')
/*     */             continue; 
/* 388 */           if (c == ':' && !nsAware)
/*     */             continue; 
/* 391 */         } else if (c == '_') {
/*     */           continue;
/*     */         } 
/* 395 */       } else if (xml11 ? 
/* 396 */         XmlChars.is11NameChar(c) : 
/*     */         
/* 400 */         XmlChars.is10NameChar(c)) {
/*     */         continue;
/*     */       } 
/* 405 */       return i;
/*     */     } 
/* 408 */     return -1;
/*     */   }
/*     */   
/*     */   public static final int findIllegalNmtokenChar(String nmtoken, boolean nsAware, boolean xml11) {
/* 413 */     int len = nmtoken.length();
/* 415 */     for (int i = 1; i < len; i++) {
/* 416 */       char c = nmtoken.charAt(i);
/* 417 */       if (c <= 'z') {
/* 418 */         if (c >= 'a')
/*     */           continue; 
/* 421 */         if (c <= 'Z') {
/* 422 */           if (c >= 'A')
/*     */             continue; 
/* 426 */           if ((c >= '0' && c <= '9') || c == '.' || c == '-')
/*     */             continue; 
/* 430 */           if (c == ':' && !nsAware)
/*     */             continue; 
/* 433 */         } else if (c == '_') {
/*     */           continue;
/*     */         } 
/* 437 */       } else if (xml11 ? 
/* 438 */         XmlChars.is11NameChar(c) : 
/*     */         
/* 442 */         XmlChars.is10NameChar(c)) {
/*     */         continue;
/*     */       } 
/* 447 */       return i;
/*     */     } 
/* 449 */     return -1;
/*     */   }
/*     */   
/*     */   public static final boolean isSpaceChar(char c) {
/* 454 */     return (c <= ' ');
/*     */   }
/*     */   
/*     */   public static String getCharDesc(char c) {
/* 459 */     int i = c;
/* 460 */     if (Character.isISOControl(c))
/* 461 */       return "(CTRL-CHAR, code " + i + ")"; 
/* 463 */     if (i > 255)
/* 464 */       return "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")"; 
/* 466 */     return "'" + c + "' (code " + i + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\WstxInputData.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */