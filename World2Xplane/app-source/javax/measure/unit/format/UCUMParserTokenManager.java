/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ class UCUMParserTokenManager implements UCUMParserConstants {
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
/*  39 */         return jjStopAtPos(0, 14);
/*     */       case ')':
/*  41 */         return jjStopAtPos(0, 15);
/*     */       case '.':
/*  43 */         return jjStopAtPos(0, 11);
/*     */       case '/':
/*  45 */         return jjStopAtPos(0, 12);
/*     */     } 
/*  47 */     return jjMoveNfa_0(0, 0);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAdd(int state) {
/*  52 */     if (this.jjrounds[state] != this.jjround) {
/*  54 */       this.jjstateSet[this.jjnewStateCnt++] = state;
/*  55 */       this.jjrounds[state] = this.jjround;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void jjAddStates(int start, int end) {
/*     */     do {
/*  61 */       this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
/*  62 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddTwoStates(int state1, int state2) {
/*  66 */     jjCheckNAdd(state1);
/*  67 */     jjCheckNAdd(state2);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start, int end) {
/*     */     do {
/*  72 */       jjCheckNAdd(jjnextStates[start]);
/*  73 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start) {
/*  77 */     jjCheckNAdd(jjnextStates[start]);
/*  78 */     jjCheckNAdd(jjnextStates[start + 1]);
/*     */   }
/*     */   
/*     */   private final int jjMoveNfa_0(int startState, int curPos) {
/*  83 */     int startsAt = 0;
/*  84 */     this.jjnewStateCnt = 14;
/*  85 */     int i = 1;
/*  86 */     this.jjstateSet[0] = startState;
/*  87 */     int kind = Integer.MAX_VALUE;
/*     */     while (true) {
/*  90 */       if (++this.jjround == Integer.MAX_VALUE)
/*  91 */         ReInitRounds(); 
/*  92 */       if (this.curChar < '@') {
/*  94 */         long l = 1L << this.curChar;
/*     */         do {
/*  97 */           switch (this.jjstateSet[--i]) {
/*     */             case 0:
/* 100 */               if ((0xFFFF14FA00000000L & l) != 0L) {
/* 101 */                 jjCheckNAddStates(0, 3);
/* 102 */               } else if ((0x280000000000L & l) != 0L) {
/* 104 */                 if (kind > 10)
/* 105 */                   kind = 10; 
/*     */               } 
/* 107 */               if ((0xFC0014FA00000000L & l) != 0L) {
/* 109 */                 if (kind > 13)
/* 110 */                   kind = 13; 
/* 111 */                 jjCheckNAdd(5);
/*     */                 break;
/*     */               } 
/* 113 */               if ((0x3FF000000000000L & l) != 0L) {
/* 115 */                 if (kind > 9)
/* 116 */                   kind = 9; 
/* 117 */                 jjCheckNAdd(3);
/*     */               } 
/*     */               break;
/*     */             case 1:
/* 121 */               if ((0xFFFFFFFE00000000L & l) != 0L)
/* 122 */                 jjAddStates(4, 5); 
/*     */               break;
/*     */             case 3:
/* 125 */               if ((0x3FF000000000000L & l) == 0L)
/*     */                 break; 
/* 127 */               if (kind > 9)
/* 128 */                 kind = 9; 
/* 129 */               jjCheckNAdd(3);
/*     */               break;
/*     */             case 4:
/* 132 */               if ((0x280000000000L & l) != 0L && kind > 10)
/* 133 */                 kind = 10; 
/*     */               break;
/*     */             case 5:
/* 136 */               if ((0xFC0014FA00000000L & l) == 0L)
/*     */                 break; 
/* 138 */               if (kind > 13)
/* 139 */                 kind = 13; 
/* 140 */               jjCheckNAdd(5);
/*     */               break;
/*     */             case 7:
/* 143 */               if ((0xFFFFFFFE00000000L & l) != 0L)
/* 144 */                 jjAddStates(6, 7); 
/*     */               break;
/*     */             case 9:
/* 147 */               if ((0xFFFF14FA00000000L & l) != 0L)
/* 148 */                 jjCheckNAddTwoStates(9, 10); 
/*     */               break;
/*     */             case 10:
/* 151 */               if ((0xFC0014FA00000000L & l) == 0L)
/*     */                 break; 
/* 153 */               if (kind > 13)
/* 154 */                 kind = 13; 
/* 155 */               jjCheckNAdd(10);
/*     */               break;
/*     */             case 11:
/* 158 */               if ((0xFFFF14FA00000000L & l) != 0L)
/* 159 */                 jjCheckNAddStates(0, 3); 
/*     */               break;
/*     */             case 12:
/* 162 */               if ((0xFFFF14FA00000000L & l) != 0L)
/* 163 */                 jjCheckNAddTwoStates(12, 5); 
/*     */               break;
/*     */             case 13:
/* 166 */               if ((0xFFFF14FA00000000L & l) != 0L)
/* 167 */                 jjCheckNAddTwoStates(13, 6); 
/*     */               break;
/*     */           } 
/* 171 */         } while (i != startsAt);
/* 173 */       } else if (this.curChar < '') {
/* 175 */         long l = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 178 */           switch (this.jjstateSet[--i]) {
/*     */             case 0:
/* 181 */               if ((0x57FFFFFFD7FFFFFFL & l) != 0L) {
/* 182 */                 jjCheckNAddStates(0, 3);
/* 183 */               } else if (this.curChar == '[') {
/* 184 */                 jjCheckNAdd(7);
/* 185 */               } else if (this.curChar == '{') {
/* 186 */                 jjCheckNAddTwoStates(1, 2);
/*     */               } 
/* 187 */               if ((0x57FFFFFFD7FFFFFFL & l) != 0L) {
/* 189 */                 if (kind > 13)
/* 190 */                   kind = 13; 
/* 191 */                 jjCheckNAdd(5);
/*     */               } 
/*     */               break;
/*     */             case 1:
/* 195 */               if ((0x57FFFFFFFFFFFFFFL & l) != 0L)
/* 196 */                 jjCheckNAddTwoStates(1, 2); 
/*     */               break;
/*     */             case 2:
/* 199 */               if (this.curChar == '}' && kind > 8)
/* 200 */                 kind = 8; 
/*     */               break;
/*     */             case 5:
/* 203 */               if ((0x57FFFFFFD7FFFFFFL & l) == 0L)
/*     */                 break; 
/* 205 */               if (kind > 13)
/* 206 */                 kind = 13; 
/* 207 */               jjCheckNAdd(5);
/*     */               break;
/*     */             case 6:
/* 210 */               if (this.curChar == '[')
/* 211 */                 jjCheckNAdd(7); 
/*     */               break;
/*     */             case 7:
/* 214 */               if ((0x7FFFFFFFD7FFFFFFL & l) != 0L)
/* 215 */                 jjCheckNAddTwoStates(7, 8); 
/*     */               break;
/*     */             case 8:
/* 218 */               if (this.curChar != ']')
/*     */                 break; 
/* 220 */               if (kind > 13)
/* 221 */                 kind = 13; 
/* 222 */               jjCheckNAddTwoStates(9, 10);
/*     */               break;
/*     */             case 9:
/* 225 */               if ((0x57FFFFFFD7FFFFFFL & l) != 0L)
/* 226 */                 jjCheckNAddTwoStates(9, 10); 
/*     */               break;
/*     */             case 10:
/* 229 */               if ((0x57FFFFFFD7FFFFFFL & l) == 0L)
/*     */                 break; 
/* 231 */               if (kind > 13)
/* 232 */                 kind = 13; 
/* 233 */               jjCheckNAdd(10);
/*     */               break;
/*     */             case 11:
/* 236 */               if ((0x57FFFFFFD7FFFFFFL & l) != 0L)
/* 237 */                 jjCheckNAddStates(0, 3); 
/*     */               break;
/*     */             case 12:
/* 240 */               if ((0x57FFFFFFD7FFFFFFL & l) != 0L)
/* 241 */                 jjCheckNAddTwoStates(12, 5); 
/*     */               break;
/*     */             case 13:
/* 244 */               if ((0x57FFFFFFD7FFFFFFL & l) != 0L)
/* 245 */                 jjCheckNAddTwoStates(13, 6); 
/*     */               break;
/*     */           } 
/* 249 */         } while (i != startsAt);
/*     */       } else {
/* 253 */         int i2 = (this.curChar & 0xFF) >> 6;
/* 254 */         long l2 = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 257 */           switch (this.jjstateSet[--i]) {
/*     */           
/*     */           } 
/* 261 */         } while (i != startsAt);
/*     */       } 
/* 263 */       if (kind != Integer.MAX_VALUE) {
/* 265 */         this.jjmatchedKind = kind;
/* 266 */         this.jjmatchedPos = curPos;
/* 267 */         kind = Integer.MAX_VALUE;
/*     */       } 
/* 269 */       curPos++;
/* 270 */       if ((i = this.jjnewStateCnt) == (startsAt = 14 - (this.jjnewStateCnt = startsAt)))
/* 271 */         return curPos; 
/*     */       try {
/* 272 */         this.curChar = this.input_stream.readChar();
/*     */       } catch (IOException e) {
/* 273 */         return curPos;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/* 276 */   static final int[] jjnextStates = new int[] { 12, 5, 13, 6, 1, 2, 7, 8 };
/*     */   
/* 279 */   public static final String[] jjstrLiteralImages = new String[] { 
/* 279 */       "", null, null, null, null, null, null, null, null, null, 
/* 279 */       null, ".", "/", null, "(", ")" };
/*     */   
/* 282 */   public static final String[] lexStateNames = new String[] { "DEFAULT" };
/*     */   
/*     */   protected SimpleCharStream input_stream;
/*     */   
/* 286 */   private final int[] jjrounds = new int[14];
/*     */   
/* 287 */   private final int[] jjstateSet = new int[28];
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
/*     */   public UCUMParserTokenManager(SimpleCharStream stream, int lexState) {
/* 295 */     this(stream);
/* 296 */     SwitchTo(lexState);
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream) {
/* 300 */     this.jjmatchedPos = this.jjnewStateCnt = 0;
/* 301 */     this.curLexState = this.defaultLexState;
/* 302 */     this.input_stream = stream;
/* 303 */     ReInitRounds();
/*     */   }
/*     */   
/*     */   private final void ReInitRounds() {
/* 308 */     this.jjround = -2147483647;
/* 309 */     for (int i = 14; i-- > 0;)
/* 310 */       this.jjrounds[i] = Integer.MIN_VALUE; 
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream, int lexState) {
/* 314 */     ReInit(stream);
/* 315 */     SwitchTo(lexState);
/*     */   }
/*     */   
/*     */   public void SwitchTo(int lexState) {
/* 319 */     if (lexState >= 1 || lexState < 0)
/* 320 */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2); 
/* 322 */     this.curLexState = lexState;
/*     */   }
/*     */   
/*     */   protected Token jjFillToken() {
/* 327 */     Token t = Token.newToken(this.jjmatchedKind);
/* 328 */     t.kind = this.jjmatchedKind;
/* 329 */     String im = jjstrLiteralImages[this.jjmatchedKind];
/* 330 */     t.image = (im == null) ? this.input_stream.GetImage() : im;
/* 331 */     t.beginLine = this.input_stream.getBeginLine();
/* 332 */     t.beginColumn = this.input_stream.getBeginColumn();
/* 333 */     t.endLine = this.input_stream.getEndLine();
/* 334 */     t.endColumn = this.input_stream.getEndColumn();
/* 335 */     return t;
/*     */   }
/*     */   
/*     */   public UCUMParserTokenManager(SimpleCharStream stream) {
/* 338 */     this.curLexState = 0;
/* 339 */     this.defaultLexState = 0;
/*     */     this.input_stream = stream;
/*     */   }
/*     */   
/*     */   public Token getNextToken() {
/* 348 */     Token specialToken = null;
/* 350 */     int curPos = 0;
/*     */     try {
/* 357 */       this.curChar = this.input_stream.BeginToken();
/* 359 */     } catch (IOException e) {
/* 361 */       this.jjmatchedKind = 0;
/* 362 */       Token matchedToken = jjFillToken();
/* 363 */       return matchedToken;
/*     */     } 
/* 366 */     this.jjmatchedKind = Integer.MAX_VALUE;
/* 367 */     this.jjmatchedPos = 0;
/* 368 */     curPos = jjMoveStringLiteralDfa0_0();
/* 369 */     if (this.jjmatchedKind != Integer.MAX_VALUE) {
/* 371 */       if (this.jjmatchedPos + 1 < curPos)
/* 372 */         this.input_stream.backup(curPos - this.jjmatchedPos - 1); 
/* 373 */       Token matchedToken = jjFillToken();
/* 374 */       return matchedToken;
/*     */     } 
/* 376 */     int error_line = this.input_stream.getEndLine();
/* 377 */     int error_column = this.input_stream.getEndColumn();
/* 378 */     String error_after = null;
/* 379 */     boolean EOFSeen = false;
/*     */     try {
/* 380 */       this.input_stream.readChar();
/* 380 */       this.input_stream.backup(1);
/* 381 */     } catch (IOException e1) {
/* 382 */       EOFSeen = true;
/* 383 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/* 384 */       if (this.curChar == '\n' || this.curChar == '\r') {
/* 385 */         error_line++;
/* 386 */         error_column = 0;
/*     */       } else {
/* 389 */         error_column++;
/*     */       } 
/*     */     } 
/* 391 */     if (!EOFSeen) {
/* 392 */       this.input_stream.backup(1);
/* 393 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/*     */     } 
/* 395 */     throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UCUMParserTokenManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */