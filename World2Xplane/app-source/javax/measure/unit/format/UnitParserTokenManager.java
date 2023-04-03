/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ class UnitParserTokenManager implements UnitParserConstants {
/*   6 */   public PrintStream debugStream = System.out;
/*     */   
/*     */   public void setDebugStream(PrintStream ds) {
/*   7 */     this.debugStream = ds;
/*     */   }
/*     */   
/*     */   private final int jjStopStringLiteralDfa_0(int pos, long active0) {
/*  10 */     switch (pos) {
/*     */     
/*     */     } 
/*  13 */     return -1;
/*     */   }
/*     */   
/*     */   private final int jjStartNfa_0(int pos, long active0) {
/*  18 */     return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
/*     */   }
/*     */   
/*     */   private final int jjStopAtPos(int pos, int kind) {
/*  22 */     this.jjmatchedKind = kind;
/*  23 */     this.jjmatchedPos = pos;
/*  24 */     return pos + 1;
/*     */   }
/*     */   
/*     */   private final int jjStartNfaWithStates_0(int pos, int kind, int state) {
/*  28 */     this.jjmatchedKind = kind;
/*  29 */     this.jjmatchedPos = pos;
/*     */     try {
/*  30 */       this.curChar = this.input_stream.readChar();
/*     */     } catch (IOException e) {
/*  31 */       return pos + 1;
/*     */     } 
/*  32 */     return jjMoveNfa_0(state, pos + 1);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa0_0() {
/*  36 */     switch (this.curChar) {
/*     */       case '(':
/*  39 */         return jjStopAtPos(0, 12);
/*     */       case ')':
/*  41 */         return jjStopAtPos(0, 13);
/*     */       case '*':
/*  43 */         return jjStopAtPos(0, 7);
/*     */       case '+':
/*  45 */         return jjStopAtPos(0, 5);
/*     */       case '-':
/*  47 */         return jjStopAtPos(0, 6);
/*     */       case '/':
/*  49 */         return jjStopAtPos(0, 9);
/*     */       case ':':
/*  51 */         return jjStopAtPos(0, 11);
/*     */       case '^':
/*  53 */         return jjStopAtPos(0, 10);
/*     */       case 'e':
/*  55 */         return jjStartNfaWithStates_0(0, 19, 7);
/*     */       case '·':
/*  57 */         return jjStopAtPos(0, 8);
/*     */     } 
/*  59 */     return jjMoveNfa_0(6, 0);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAdd(int state) {
/*  64 */     if (this.jjrounds[state] != this.jjround) {
/*  66 */       this.jjstateSet[this.jjnewStateCnt++] = state;
/*  67 */       this.jjrounds[state] = this.jjround;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void jjAddStates(int start, int end) {
/*     */     do {
/*  73 */       this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
/*  74 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddTwoStates(int state1, int state2) {
/*  78 */     jjCheckNAdd(state1);
/*  79 */     jjCheckNAdd(state2);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start, int end) {
/*     */     do {
/*  84 */       jjCheckNAdd(jjnextStates[start]);
/*  85 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start) {
/*  89 */     jjCheckNAdd(jjnextStates[start]);
/*  90 */     jjCheckNAdd(jjnextStates[start + 1]);
/*     */   }
/*     */   
/*  92 */   static final long[] jjbitVec0 = new long[] { 0L, 0L, 147492887796383744L, 0L };
/*     */   
/*  95 */   static final long[] jjbitVec1 = new long[] { 0L, 284008251501051904L, 0L, 0L };
/*     */   
/*  98 */   static final long[] jjbitVec2 = new long[] { -4294967298L, -1L, -1L, -1L };
/*     */   
/* 101 */   static final long[] jjbitVec4 = new long[] { 0L, 0L, -183521684815347713L, -1L };
/*     */   
/* 104 */   static final long[] jjbitVec5 = new long[] { -1L, -284008251501051905L, -1L, -1L };
/*     */   
/*     */   private final int jjMoveNfa_0(int startState, int curPos) {
/* 110 */     int startsAt = 0;
/* 111 */     this.jjnewStateCnt = 15;
/* 112 */     int i = 1;
/* 113 */     this.jjstateSet[0] = startState;
/* 114 */     int kind = Integer.MAX_VALUE;
/*     */     while (true) {
/* 117 */       if (++this.jjround == Integer.MAX_VALUE)
/* 118 */         ReInitRounds(); 
/* 119 */       if (this.curChar < '@') {
/* 121 */         long l = 1L << this.curChar;
/*     */         do {
/* 124 */           switch (this.jjstateSet[--i]) {
/*     */             case 6:
/* 127 */               if ((0xF80010FE00000000L & l) != 0L) {
/* 129 */                 if (kind > 20)
/* 130 */                   kind = 20; 
/* 131 */                 jjCheckNAdd(7);
/*     */                 break;
/*     */               } 
/* 133 */               if ((0x3FF000000000000L & l) != 0L) {
/* 135 */                 if (kind > 14)
/* 136 */                   kind = 14; 
/* 137 */                 jjCheckNAddStates(0, 4);
/*     */                 break;
/*     */               } 
/* 139 */               if (this.curChar == '.')
/* 140 */                 jjCheckNAdd(2); 
/*     */               break;
/*     */             case 1:
/* 143 */               if (this.curChar == '.')
/* 144 */                 jjCheckNAdd(2); 
/*     */               break;
/*     */             case 2:
/* 147 */               if ((0x3FF000000000000L & l) == 0L)
/*     */                 break; 
/* 149 */               if (kind > 16)
/* 150 */                 kind = 16; 
/* 151 */               jjCheckNAddTwoStates(2, 3);
/*     */               break;
/*     */             case 4:
/* 154 */               if ((0x280000000000L & l) != 0L)
/* 155 */                 jjCheckNAdd(5); 
/*     */               break;
/*     */             case 5:
/* 158 */               if ((0x3FF000000000000L & l) == 0L)
/*     */                 break; 
/* 160 */               if (kind > 16)
/* 161 */                 kind = 16; 
/* 162 */               jjCheckNAdd(5);
/*     */               break;
/*     */             case 7:
/* 165 */               if ((0xFBFF10FE00000000L & l) == 0L)
/*     */                 break; 
/* 167 */               if (kind > 20)
/* 168 */                 kind = 20; 
/* 169 */               jjCheckNAdd(7);
/*     */               break;
/*     */             case 8:
/* 172 */               if ((0x3FF000000000000L & l) == 0L)
/*     */                 break; 
/* 174 */               if (kind > 14)
/* 175 */                 kind = 14; 
/* 176 */               jjCheckNAddStates(0, 4);
/*     */               break;
/*     */             case 9:
/* 179 */               if ((0x3FF000000000000L & l) == 0L)
/*     */                 break; 
/* 181 */               if (kind > 14)
/* 182 */                 kind = 14; 
/* 183 */               jjCheckNAdd(9);
/*     */               break;
/*     */             case 10:
/* 186 */               if ((0x3FF000000000000L & l) == 0L)
/*     */                 break; 
/* 188 */               if (kind > 16)
/* 189 */                 kind = 16; 
/* 190 */               jjCheckNAddStates(5, 8);
/*     */               break;
/*     */           } 
/* 194 */         } while (i != startsAt);
/* 196 */       } else if (this.curChar < '') {
/* 198 */         long l = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 201 */           switch (this.jjstateSet[--i]) {
/*     */             case 6:
/* 204 */               if ((0xFFFFFFFFBFFFFFFFL & l) != 0L) {
/* 206 */                 if (kind > 20)
/* 207 */                   kind = 20; 
/* 208 */                 jjCheckNAdd(7);
/*     */               } 
/* 210 */               if (this.curChar == 'l')
/* 211 */                 jjAddStates(9, 10); 
/*     */               break;
/*     */             case 3:
/* 214 */               if ((0x2000000020L & l) != 0L)
/* 215 */                 jjAddStates(11, 12); 
/*     */               break;
/*     */             case 7:
/* 218 */               if ((0xFFFFFFFFBFFFFFFFL & l) == 0L)
/*     */                 break; 
/* 220 */               if (kind > 20)
/* 221 */                 kind = 20; 
/* 222 */               jjCheckNAdd(7);
/*     */               break;
/*     */             case 11:
/* 225 */               if (this.curChar == 'l')
/* 226 */                 jjAddStates(9, 10); 
/*     */               break;
/*     */             case 12:
/* 229 */               if (this.curChar == 'o')
/* 230 */                 this.jjstateSet[this.jjnewStateCnt++] = 13; 
/*     */               break;
/*     */             case 13:
/* 233 */               if (this.curChar == 'g' && kind > 17)
/* 234 */                 kind = 17; 
/*     */               break;
/*     */             case 14:
/* 237 */               if (this.curChar == 'n' && kind > 18)
/* 238 */                 kind = 18; 
/*     */               break;
/*     */           } 
/* 242 */         } while (i != startsAt);
/*     */       } else {
/* 246 */         int hiByte = this.curChar >> 8;
/* 247 */         int i1 = hiByte >> 6;
/* 248 */         long l1 = 1L << (hiByte & 0x3F);
/* 249 */         int i2 = (this.curChar & 0xFF) >> 6;
/* 250 */         long l2 = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 253 */           switch (this.jjstateSet[--i]) {
/*     */             case 6:
/* 256 */               if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
/* 258 */                 if (kind > 15)
/* 259 */                   kind = 15; 
/* 260 */                 jjCheckNAdd(0);
/*     */               } 
/* 262 */               if (jjCanMove_1(hiByte, i1, i2, l1, l2)) {
/* 264 */                 if (kind > 20)
/* 265 */                   kind = 20; 
/* 266 */                 jjCheckNAdd(7);
/*     */               } 
/*     */               break;
/*     */             case 0:
/* 270 */               if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
/*     */                 break; 
/* 272 */               if (kind > 15)
/* 273 */                 kind = 15; 
/* 274 */               jjCheckNAdd(0);
/*     */               break;
/*     */             case 7:
/* 277 */               if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
/*     */                 break; 
/* 279 */               if (kind > 20)
/* 280 */                 kind = 20; 
/* 281 */               jjCheckNAdd(7);
/*     */               break;
/*     */           } 
/* 285 */         } while (i != startsAt);
/*     */       } 
/* 287 */       if (kind != Integer.MAX_VALUE) {
/* 289 */         this.jjmatchedKind = kind;
/* 290 */         this.jjmatchedPos = curPos;
/* 291 */         kind = Integer.MAX_VALUE;
/*     */       } 
/* 293 */       curPos++;
/* 294 */       if ((i = this.jjnewStateCnt) == (startsAt = 15 - (this.jjnewStateCnt = startsAt)))
/* 295 */         return curPos; 
/*     */       try {
/* 296 */         this.curChar = this.input_stream.readChar();
/*     */       } catch (IOException e) {
/* 297 */         return curPos;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/* 300 */   static final int[] jjnextStates = new int[] { 
/* 300 */       9, 1, 2, 3, 10, 1, 2, 3, 10, 12, 
/* 300 */       14, 4, 5 };
/*     */   
/*     */   private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
/* 305 */     switch (hiByte) {
/*     */       case 0:
/* 308 */         return ((jjbitVec0[i2] & l2) != 0L);
/*     */       case 32:
/* 310 */         return ((jjbitVec1[i2] & l2) != 0L);
/*     */     } 
/* 312 */     return false;
/*     */   }
/*     */   
/*     */   private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2) {
/* 317 */     switch (hiByte) {
/*     */       case 0:
/* 320 */         return ((jjbitVec4[i2] & l2) != 0L);
/*     */       case 32:
/* 322 */         return ((jjbitVec5[i2] & l2) != 0L);
/*     */     } 
/* 324 */     if ((jjbitVec2[i1] & l1) != 0L)
/* 325 */       return true; 
/* 326 */     return false;
/*     */   }
/*     */   
/* 329 */   public static final String[] jjstrLiteralImages = new String[] { 
/* 329 */       "", null, null, null, null, "+", "-", "*", "·", "/", 
/* 329 */       "^", ":", "(", ")", null, null, null, null, null, "e", 
/* 329 */       null };
/*     */   
/* 332 */   public static final String[] lexStateNames = new String[] { "DEFAULT" };
/*     */   
/*     */   protected SimpleCharStream input_stream;
/*     */   
/* 336 */   private final int[] jjrounds = new int[15];
/*     */   
/* 337 */   private final int[] jjstateSet = new int[30];
/*     */   
/*     */   protected char curChar;
/*     */   
/*     */   int curLexState;
/*     */   
/*     */   int defaultLexState;
/*     */   
/*     */   int jjnewStateCnt;
/*     */   
/*     */   int jjround;
/*     */   
/*     */   int jjmatchedPos;
/*     */   
/*     */   int jjmatchedKind;
/*     */   
/*     */   public UnitParserTokenManager(SimpleCharStream stream, int lexState) {
/* 345 */     this(stream);
/* 346 */     SwitchTo(lexState);
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream) {
/* 350 */     this.jjmatchedPos = this.jjnewStateCnt = 0;
/* 351 */     this.curLexState = this.defaultLexState;
/* 352 */     this.input_stream = stream;
/* 353 */     ReInitRounds();
/*     */   }
/*     */   
/*     */   private final void ReInitRounds() {
/* 358 */     this.jjround = -2147483647;
/* 359 */     for (int i = 15; i-- > 0;)
/* 360 */       this.jjrounds[i] = Integer.MIN_VALUE; 
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream, int lexState) {
/* 364 */     ReInit(stream);
/* 365 */     SwitchTo(lexState);
/*     */   }
/*     */   
/*     */   public void SwitchTo(int lexState) {
/* 369 */     if (lexState >= 1 || lexState < 0)
/* 370 */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2); 
/* 372 */     this.curLexState = lexState;
/*     */   }
/*     */   
/*     */   protected Token jjFillToken() {
/* 377 */     Token t = Token.newToken(this.jjmatchedKind);
/* 378 */     t.kind = this.jjmatchedKind;
/* 379 */     String im = jjstrLiteralImages[this.jjmatchedKind];
/* 380 */     t.image = (im == null) ? this.input_stream.GetImage() : im;
/* 381 */     t.beginLine = this.input_stream.getBeginLine();
/* 382 */     t.beginColumn = this.input_stream.getBeginColumn();
/* 383 */     t.endLine = this.input_stream.getEndLine();
/* 384 */     t.endColumn = this.input_stream.getEndColumn();
/* 385 */     return t;
/*     */   }
/*     */   
/*     */   public UnitParserTokenManager(SimpleCharStream stream) {
/* 388 */     this.curLexState = 0;
/* 389 */     this.defaultLexState = 0;
/*     */     this.input_stream = stream;
/*     */   }
/*     */   
/*     */   public Token getNextToken() {
/* 398 */     Token specialToken = null;
/* 400 */     int curPos = 0;
/*     */     try {
/* 407 */       this.curChar = this.input_stream.BeginToken();
/* 409 */     } catch (IOException e) {
/* 411 */       this.jjmatchedKind = 0;
/* 412 */       Token matchedToken = jjFillToken();
/* 413 */       return matchedToken;
/*     */     } 
/* 416 */     this.jjmatchedKind = Integer.MAX_VALUE;
/* 417 */     this.jjmatchedPos = 0;
/* 418 */     curPos = jjMoveStringLiteralDfa0_0();
/* 419 */     if (this.jjmatchedKind != Integer.MAX_VALUE) {
/* 421 */       if (this.jjmatchedPos + 1 < curPos)
/* 422 */         this.input_stream.backup(curPos - this.jjmatchedPos - 1); 
/* 423 */       Token matchedToken = jjFillToken();
/* 424 */       return matchedToken;
/*     */     } 
/* 426 */     int error_line = this.input_stream.getEndLine();
/* 427 */     int error_column = this.input_stream.getEndColumn();
/* 428 */     String error_after = null;
/* 429 */     boolean EOFSeen = false;
/*     */     try {
/* 430 */       this.input_stream.readChar();
/* 430 */       this.input_stream.backup(1);
/* 431 */     } catch (IOException e1) {
/* 432 */       EOFSeen = true;
/* 433 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/* 434 */       if (this.curChar == '\n' || this.curChar == '\r') {
/* 435 */         error_line++;
/* 436 */         error_column = 0;
/*     */       } else {
/* 439 */         error_column++;
/*     */       } 
/*     */     } 
/* 441 */     if (!EOFSeen) {
/* 442 */       this.input_stream.backup(1);
/* 443 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/*     */     } 
/* 445 */     throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UnitParserTokenManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */