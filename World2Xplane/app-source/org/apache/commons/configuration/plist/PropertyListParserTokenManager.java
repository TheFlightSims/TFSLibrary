/*     */ package org.apache.commons.configuration.plist;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ class PropertyListParserTokenManager implements PropertyListParserConstants {
/*   6 */   public PrintStream debugStream = System.out;
/*     */   
/*     */   public void setDebugStream(PrintStream ds) {
/*  10 */     this.debugStream = ds;
/*     */   }
/*     */   
/*     */   private final int jjStopStringLiteralDfa_0(int pos, long active0) {
/*  15 */     switch (pos) {
/*     */       case 0:
/*  18 */         if ((active0 & 0x2000L) != 0L)
/*  20 */           return 8; 
/*  22 */         if ((active0 & 0x8000L) != 0L)
/*  24 */           return 14; 
/*  26 */         if ((active0 & 0x800000L) != 0L) {
/*  28 */           this.jjmatchedKind = 21;
/*  29 */           return 8;
/*     */         } 
/*  31 */         if ((active0 & 0x5000L) != 0L)
/*  33 */           return 6; 
/*  35 */         return -1;
/*     */       case 1:
/*  37 */         if ((active0 & 0x4000L) != 0L) {
/*  39 */           this.jjmatchedKind = 21;
/*  40 */           this.jjmatchedPos = 1;
/*  41 */           return 3;
/*     */         } 
/*  43 */         return -1;
/*     */     } 
/*  45 */     return -1;
/*     */   }
/*     */   
/*     */   private final int jjStartNfa_0(int pos, long active0) {
/*  51 */     return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
/*     */   }
/*     */   
/*     */   private final int jjStopAtPos(int pos, int kind) {
/*  56 */     this.jjmatchedKind = kind;
/*  57 */     this.jjmatchedPos = pos;
/*  58 */     return pos + 1;
/*     */   }
/*     */   
/*     */   private final int jjStartNfaWithStates_0(int pos, int kind, int state) {
/*  63 */     this.jjmatchedKind = kind;
/*  64 */     this.jjmatchedPos = pos;
/*     */     try {
/*  67 */       this.curChar = this.input_stream.readChar();
/*  69 */     } catch (IOException e) {
/*  71 */       return pos + 1;
/*     */     } 
/*  73 */     return jjMoveNfa_0(state, pos + 1);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa0_0() {
/*  78 */     switch (this.curChar) {
/*     */       case '"':
/*  81 */         return jjStartNfaWithStates_0(0, 15, 14);
/*     */       case '(':
/*  83 */         return jjStopAtPos(0, 5);
/*     */       case ')':
/*  85 */         return jjStopAtPos(0, 6);
/*     */       case ',':
/*  87 */         return jjStopAtPos(0, 7);
/*     */       case ';':
/*  89 */         return jjStopAtPos(0, 10);
/*     */       case '<':
/*  91 */         this.jjmatchedKind = 12;
/*  92 */         return jjMoveStringLiteralDfa1_0(16384L);
/*     */       case '=':
/*  94 */         return jjStopAtPos(0, 11);
/*     */       case '>':
/*  96 */         return jjStartNfaWithStates_0(0, 13, 8);
/*     */       case '\\':
/*  98 */         return jjMoveStringLiteralDfa1_0(8388608L);
/*     */       case '{':
/* 100 */         return jjStopAtPos(0, 8);
/*     */       case '}':
/* 102 */         return jjStopAtPos(0, 9);
/*     */     } 
/* 104 */     return jjMoveNfa_0(0, 0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa1_0(long active0) {
/*     */     try {
/* 112 */       this.curChar = this.input_stream.readChar();
/* 114 */     } catch (IOException e) {
/* 116 */       jjStopStringLiteralDfa_0(0, active0);
/* 117 */       return 1;
/*     */     } 
/* 119 */     switch (this.curChar) {
/*     */       case '"':
/* 122 */         if ((active0 & 0x800000L) != 0L)
/* 124 */           return jjStopAtPos(1, 23); 
/*     */         break;
/*     */       case '*':
/* 128 */         return jjMoveStringLiteralDfa2_0(active0, 16384L);
/*     */     } 
/* 132 */     return jjStartNfa_0(0, active0);
/*     */   }
/*     */   
/*     */   private final int jjMoveStringLiteralDfa2_0(long old0, long active0) {
/* 137 */     if ((active0 &= old0) == 0L)
/* 139 */       return jjStartNfa_0(0, old0); 
/*     */     try {
/* 143 */       this.curChar = this.input_stream.readChar();
/* 145 */     } catch (IOException e) {
/* 147 */       jjStopStringLiteralDfa_0(1, active0);
/* 148 */       return 2;
/*     */     } 
/* 150 */     switch (this.curChar) {
/*     */       case 'D':
/* 153 */         if ((active0 & 0x4000L) != 0L)
/* 155 */           return jjStartNfaWithStates_0(2, 14, 15); 
/*     */         break;
/*     */     } 
/* 161 */     return jjStartNfa_0(1, active0);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAdd(int state) {
/* 166 */     if (this.jjrounds[state] != this.jjround) {
/* 168 */       this.jjstateSet[this.jjnewStateCnt++] = state;
/* 169 */       this.jjrounds[state] = this.jjround;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void jjAddStates(int start, int end) {
/*     */     do {
/* 177 */       this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
/* 179 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddTwoStates(int state1, int state2) {
/* 184 */     jjCheckNAdd(state1);
/* 185 */     jjCheckNAdd(state2);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start, int end) {
/*     */     do {
/* 192 */       jjCheckNAdd(jjnextStates[start]);
/* 194 */     } while (start++ != end);
/*     */   }
/*     */   
/*     */   private final void jjCheckNAddStates(int start) {
/* 199 */     jjCheckNAdd(jjnextStates[start]);
/* 200 */     jjCheckNAdd(jjnextStates[start + 1]);
/*     */   }
/*     */   
/* 203 */   static final long[] jjbitVec0 = new long[] { 0L, 0L, -1L, -1L };
/*     */   
/*     */   private final int jjMoveNfa_0(int startState, int curPos) {
/* 210 */     int startsAt = 0;
/* 211 */     this.jjnewStateCnt = 14;
/* 212 */     int i = 1;
/* 213 */     this.jjstateSet[0] = startState;
/* 214 */     int kind = Integer.MAX_VALUE;
/*     */     while (true) {
/* 217 */       if (++this.jjround == Integer.MAX_VALUE)
/* 219 */         ReInitRounds(); 
/* 221 */       if (this.curChar < '@') {
/* 223 */         long l = 1L << this.curChar;
/*     */         do {
/* 227 */           switch (this.jjstateSet[--i]) {
/*     */             case 15:
/* 230 */               if ((0xD7FFECFAFFFFD9FFL & l) != 0L) {
/* 232 */                 if (kind > 21)
/* 234 */                   kind = 21; 
/* 236 */                 jjCheckNAdd(8);
/*     */               } 
/* 238 */               if ((0x7FF280100000000L & l) != 0L) {
/* 240 */                 jjCheckNAddTwoStates(4, 5);
/*     */                 break;
/*     */               } 
/* 242 */               if (this.curChar == '>')
/* 244 */                 if (kind > 20)
/* 246 */                   kind = 20;  
/*     */               break;
/*     */             case 6:
/* 251 */               if ((0xD7FFECFAFFFFD9FFL & l) != 0L) {
/* 253 */                 if (kind > 21)
/* 255 */                   kind = 21; 
/* 257 */                 jjCheckNAdd(8);
/*     */               } 
/* 259 */               if ((0x3FF000100002600L & l) != 0L) {
/* 261 */                 jjCheckNAddTwoStates(1, 2);
/*     */                 break;
/*     */               } 
/* 263 */               if (this.curChar == '*') {
/* 265 */                 this.jjstateSet[this.jjnewStateCnt++] = 3;
/*     */                 break;
/*     */               } 
/* 267 */               if (this.curChar == '>')
/* 269 */                 if (kind > 19)
/* 271 */                   kind = 19;  
/*     */               break;
/*     */             case 14:
/* 276 */               if ((0xFFFFFFFBFFFFFFFFL & l) != 0L) {
/* 278 */                 jjCheckNAddStates(0, 2);
/*     */                 break;
/*     */               } 
/* 280 */               if (this.curChar == '"')
/* 282 */                 if (kind > 22)
/* 284 */                   kind = 22;  
/*     */               break;
/*     */             case 3:
/*     */             case 8:
/* 290 */               if ((0xD7FFECFAFFFFD9FFL & l) == 0L)
/*     */                 break; 
/* 294 */               if (kind > 21)
/* 296 */                 kind = 21; 
/* 298 */               jjCheckNAdd(8);
/*     */               break;
/*     */             case 0:
/* 301 */               if ((0xD7FFECFAFFFFD9FFL & l) != 0L) {
/* 303 */                 if (kind > 21)
/* 305 */                   kind = 21; 
/* 307 */                 jjCheckNAdd(8);
/* 309 */               } else if (this.curChar == '"') {
/* 311 */                 jjCheckNAddStates(0, 2);
/*     */               } 
/* 313 */               if (this.curChar == '<')
/* 315 */                 this.jjstateSet[this.jjnewStateCnt++] = 6; 
/* 317 */               if (this.curChar == '<')
/* 319 */                 jjCheckNAddTwoStates(1, 2); 
/*     */               break;
/*     */             case 1:
/* 323 */               if ((0x3FF000100002600L & l) != 0L)
/* 325 */                 jjCheckNAddTwoStates(1, 2); 
/*     */               break;
/*     */             case 2:
/* 329 */               if (this.curChar == '>' && kind > 19)
/* 331 */                 kind = 19; 
/*     */               break;
/*     */             case 4:
/* 335 */               if ((0x7FF280100000000L & l) != 0L)
/* 337 */                 jjCheckNAddTwoStates(4, 5); 
/*     */               break;
/*     */             case 5:
/* 341 */               if (this.curChar == '>' && kind > 20)
/* 343 */                 kind = 20; 
/*     */               break;
/*     */             case 7:
/* 347 */               if (this.curChar == '<')
/* 349 */                 this.jjstateSet[this.jjnewStateCnt++] = 6; 
/*     */               break;
/*     */             case 9:
/*     */             case 11:
/* 354 */               if (this.curChar == '"')
/* 356 */                 jjCheckNAddStates(0, 2); 
/*     */               break;
/*     */             case 10:
/* 360 */               if ((0xFFFFFFFBFFFFFFFFL & l) != 0L)
/* 362 */                 jjCheckNAddStates(0, 2); 
/*     */               break;
/*     */             case 13:
/* 366 */               if (this.curChar == '"' && kind > 22)
/* 368 */                 kind = 22; 
/*     */               break;
/*     */           } 
/* 375 */         } while (i != startsAt);
/* 377 */       } else if (this.curChar < '') {
/* 379 */         long l = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 383 */           switch (this.jjstateSet[--i]) {
/*     */             case 15:
/* 386 */               if ((0xD7FFFFFFFFFFFFFFL & l) != 0L) {
/* 388 */                 if (kind > 21)
/* 390 */                   kind = 21; 
/* 392 */                 jjCheckNAdd(8);
/*     */               } 
/* 394 */               if (this.curChar == 'Z')
/* 396 */                 jjCheckNAddTwoStates(4, 5); 
/*     */               break;
/*     */             case 6:
/* 400 */               if ((0xD7FFFFFFFFFFFFFFL & l) != 0L) {
/* 402 */                 if (kind > 21)
/* 404 */                   kind = 21; 
/* 406 */                 jjCheckNAdd(8);
/*     */               } 
/* 408 */               if ((0x7E0000007EL & l) != 0L)
/* 410 */                 jjCheckNAddTwoStates(1, 2); 
/*     */               break;
/*     */             case 14:
/* 414 */               jjCheckNAddStates(0, 2);
/* 415 */               if (this.curChar == '\\')
/* 417 */                 this.jjstateSet[this.jjnewStateCnt++] = 11; 
/*     */               break;
/*     */             case 3:
/* 421 */               if ((0xD7FFFFFFFFFFFFFFL & l) != 0L) {
/* 423 */                 if (kind > 21)
/* 425 */                   kind = 21; 
/* 427 */                 jjCheckNAdd(8);
/*     */               } 
/* 429 */               if (this.curChar == 'D')
/* 431 */                 jjCheckNAddTwoStates(4, 5); 
/*     */               break;
/*     */             case 0:
/*     */             case 8:
/* 436 */               if ((0xD7FFFFFFFFFFFFFFL & l) == 0L)
/*     */                 break; 
/* 440 */               if (kind > 21)
/* 442 */                 kind = 21; 
/* 444 */               jjCheckNAdd(8);
/*     */               break;
/*     */             case 1:
/* 447 */               if ((0x7E0000007EL & l) != 0L)
/* 449 */                 jjCheckNAddTwoStates(1, 2); 
/*     */               break;
/*     */             case 4:
/* 453 */               if (this.curChar == 'Z')
/* 455 */                 jjCheckNAddTwoStates(4, 5); 
/*     */               break;
/*     */             case 10:
/* 459 */               jjCheckNAddStates(0, 2);
/*     */               break;
/*     */             case 12:
/* 462 */               if (this.curChar == '\\')
/* 464 */                 this.jjstateSet[this.jjnewStateCnt++] = 11; 
/*     */               break;
/*     */           } 
/* 471 */         } while (i != startsAt);
/*     */       } else {
/* 475 */         int i2 = (this.curChar & 0xFF) >> 6;
/* 476 */         long l2 = 1L << (this.curChar & 0x3F);
/*     */         do {
/* 480 */           switch (this.jjstateSet[--i]) {
/*     */             case 8:
/*     */             case 15:
/* 484 */               if ((jjbitVec0[i2] & l2) == 0L)
/*     */                 break; 
/* 488 */               if (kind > 21)
/* 490 */                 kind = 21; 
/* 492 */               jjCheckNAdd(8);
/*     */               break;
/*     */             case 6:
/* 495 */               if ((jjbitVec0[i2] & l2) == 0L)
/*     */                 break; 
/* 499 */               if (kind > 21)
/* 501 */                 kind = 21; 
/* 503 */               jjCheckNAdd(8);
/*     */               break;
/*     */             case 10:
/*     */             case 14:
/* 507 */               if ((jjbitVec0[i2] & l2) != 0L)
/* 509 */                 jjCheckNAddStates(0, 2); 
/*     */               break;
/*     */             case 3:
/* 513 */               if ((jjbitVec0[i2] & l2) == 0L)
/*     */                 break; 
/* 517 */               if (kind > 21)
/* 519 */                 kind = 21; 
/* 521 */               jjCheckNAdd(8);
/*     */               break;
/*     */             case 0:
/* 524 */               if ((jjbitVec0[i2] & l2) == 0L)
/*     */                 break; 
/* 528 */               if (kind > 21)
/* 530 */                 kind = 21; 
/* 532 */               jjCheckNAdd(8);
/*     */               break;
/*     */           } 
/* 538 */         } while (i != startsAt);
/*     */       } 
/* 540 */       if (kind != Integer.MAX_VALUE) {
/* 542 */         this.jjmatchedKind = kind;
/* 543 */         this.jjmatchedPos = curPos;
/* 544 */         kind = Integer.MAX_VALUE;
/*     */       } 
/* 546 */       curPos++;
/* 547 */       if ((i = this.jjnewStateCnt) == (startsAt = 14 - (this.jjnewStateCnt = startsAt)))
/* 549 */         return curPos; 
/*     */       try {
/* 553 */         this.curChar = this.input_stream.readChar();
/* 555 */       } catch (IOException e) {
/* 557 */         return curPos;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/* 562 */   static final int[] jjnextStates = new int[] { 10, 12, 13 };
/*     */   
/* 565 */   public static final String[] jjstrLiteralImages = new String[] { 
/* 565 */       "", null, null, null, null, "(", ")", ",", "{", "}", 
/* 565 */       ";", "=", "<", ">", "<*D", "\"", null, null, null, null, 
/* 565 */       null, null, null, "\\\"" };
/*     */   
/* 569 */   public static final String[] lexStateNames = new String[] { "DEFAULT" };
/*     */   
/* 572 */   static final long[] jjtoToken = new long[] { 16318433L };
/*     */   
/* 575 */   static final long[] jjtoSkip = new long[] { 30L };
/*     */   
/*     */   protected SimpleCharStream input_stream;
/*     */   
/* 579 */   private final int[] jjrounds = new int[14];
/*     */   
/* 580 */   private final int[] jjstateSet = new int[28];
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
/*     */   public PropertyListParserTokenManager(SimpleCharStream stream, int lexState) {
/* 594 */     this(stream);
/* 595 */     SwitchTo(lexState);
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream) {
/* 600 */     this.jjmatchedPos = this.jjnewStateCnt = 0;
/* 601 */     this.curLexState = this.defaultLexState;
/* 602 */     this.input_stream = stream;
/* 603 */     ReInitRounds();
/*     */   }
/*     */   
/*     */   private final void ReInitRounds() {
/* 609 */     this.jjround = -2147483647;
/* 610 */     for (int i = 14; i-- > 0;)
/* 612 */       this.jjrounds[i] = Integer.MIN_VALUE; 
/*     */   }
/*     */   
/*     */   public void ReInit(SimpleCharStream stream, int lexState) {
/* 618 */     ReInit(stream);
/* 619 */     SwitchTo(lexState);
/*     */   }
/*     */   
/*     */   public void SwitchTo(int lexState) {
/* 624 */     if (lexState >= 1 || lexState < 0)
/* 626 */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2); 
/* 630 */     this.curLexState = lexState;
/*     */   }
/*     */   
/*     */   protected Token jjFillToken() {
/* 636 */     Token t = Token.newToken(this.jjmatchedKind);
/* 637 */     t.kind = this.jjmatchedKind;
/* 638 */     String im = jjstrLiteralImages[this.jjmatchedKind];
/* 639 */     t.image = (im == null) ? this.input_stream.GetImage() : im;
/* 640 */     t.beginLine = this.input_stream.getBeginLine();
/* 641 */     t.beginColumn = this.input_stream.getBeginColumn();
/* 642 */     t.endLine = this.input_stream.getEndLine();
/* 643 */     t.endColumn = this.input_stream.getEndColumn();
/* 644 */     return t;
/*     */   }
/*     */   
/*     */   public PropertyListParserTokenManager(SimpleCharStream stream) {
/* 647 */     this.curLexState = 0;
/* 648 */     this.defaultLexState = 0;
/*     */     this.input_stream = stream;
/*     */   }
/*     */   
/*     */   public Token getNextToken() {
/* 657 */     Token specialToken = null;
/* 659 */     int curPos = 0;
/*     */     while (true) {
/*     */       try {
/* 666 */         this.curChar = this.input_stream.BeginToken();
/* 668 */       } catch (IOException e) {
/* 670 */         this.jjmatchedKind = 0;
/* 671 */         Token matchedToken = jjFillToken();
/* 672 */         return matchedToken;
/*     */       } 
/*     */       try {
/* 677 */         this.input_stream.backup(0);
/* 678 */         while (this.curChar <= ' ' && (0x100002600L & 1L << this.curChar) != 0L)
/* 680 */           this.curChar = this.input_stream.BeginToken(); 
/* 683 */       } catch (IOException e1) {
/*     */         continue;
/*     */       } 
/* 687 */       this.jjmatchedKind = Integer.MAX_VALUE;
/* 688 */       this.jjmatchedPos = 0;
/* 689 */       curPos = jjMoveStringLiteralDfa0_0();
/* 690 */       if (this.jjmatchedKind != Integer.MAX_VALUE) {
/* 692 */         if (this.jjmatchedPos + 1 < curPos)
/* 694 */           this.input_stream.backup(curPos - this.jjmatchedPos - 1); 
/* 696 */         if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0L) {
/* 698 */           Token matchedToken = jjFillToken();
/* 699 */           return matchedToken;
/*     */         } 
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 706 */     int error_line = this.input_stream.getEndLine();
/* 707 */     int error_column = this.input_stream.getEndColumn();
/* 708 */     String error_after = null;
/* 709 */     boolean EOFSeen = false;
/*     */     try {
/* 712 */       this.input_stream.readChar();
/* 713 */       this.input_stream.backup(1);
/* 715 */     } catch (IOException e1) {
/* 717 */       EOFSeen = true;
/* 718 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/* 719 */       if (this.curChar == '\n' || this.curChar == '\r') {
/* 721 */         error_line++;
/* 722 */         error_column = 0;
/*     */       } else {
/* 726 */         error_column++;
/*     */       } 
/*     */     } 
/* 729 */     if (!EOFSeen) {
/* 731 */       this.input_stream.backup(1);
/* 732 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/*     */     } 
/* 734 */     throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\plist\PropertyListParserTokenManager.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */