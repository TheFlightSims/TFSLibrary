/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class AsciiXmlWriter extends EncodingXmlWriter {
/*     */   public AsciiXmlWriter(OutputStream out, WriterConfig cfg, boolean autoclose) throws IOException {
/*  35 */     super(out, cfg, "US-ASCII", autoclose);
/*     */   }
/*     */   
/*     */   public void writeRaw(char[] cbuf, int offset, int len) throws IOException {
/*  41 */     if (this.mSurrogate != 0)
/*  42 */       throwUnpairedSurrogate(); 
/*  45 */     int ptr = this.mOutputPtr;
/*  46 */     while (len > 0) {
/*  47 */       int max = this.mOutputBuffer.length - ptr;
/*  48 */       if (max < 1) {
/*  49 */         this.mOutputPtr = ptr;
/*  50 */         flushBuffer();
/*  51 */         ptr = 0;
/*  52 */         max = this.mOutputBuffer.length;
/*     */       } 
/*  55 */       if (max > len)
/*  56 */         max = len; 
/*  58 */       if (this.mCheckContent) {
/*  59 */         for (int inEnd = offset + max; offset < inEnd; offset++) {
/*  60 */           int c = cbuf[offset];
/*  61 */           if (c < 32) {
/*  62 */             if (c != 10)
/*  64 */               if (c != 13)
/*  66 */                 if (c != 9) {
/*  67 */                   this.mOutputPtr = ptr;
/*  68 */                   c = handleInvalidChar(c);
/*     */                 }   
/*  70 */           } else if (c > 126) {
/*  71 */             this.mOutputPtr = ptr;
/*  72 */             if (c > 127) {
/*  73 */               handleInvalidAsciiChar(c);
/*  74 */             } else if (this.mXml11) {
/*  75 */               c = handleInvalidChar(c);
/*     */             } 
/*     */           } 
/*  78 */           this.mOutputBuffer[ptr++] = (byte)c;
/*     */         } 
/*     */       } else {
/*  81 */         for (int inEnd = offset + max; offset < inEnd; offset++)
/*  82 */           this.mOutputBuffer[ptr++] = (byte)cbuf[offset]; 
/*     */       } 
/*  85 */       len -= max;
/*     */     } 
/*  87 */     this.mOutputPtr = ptr;
/*     */   }
/*     */   
/*     */   public void writeRaw(String str, int offset, int len) throws IOException {
/*  93 */     if (this.mSurrogate != 0)
/*  94 */       throwUnpairedSurrogate(); 
/*  96 */     int ptr = this.mOutputPtr;
/*  97 */     while (len > 0) {
/*  98 */       int max = this.mOutputBuffer.length - ptr;
/*  99 */       if (max < 1) {
/* 100 */         this.mOutputPtr = ptr;
/* 101 */         flushBuffer();
/* 102 */         ptr = 0;
/* 103 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 106 */       if (max > len)
/* 107 */         max = len; 
/* 109 */       if (this.mCheckContent) {
/* 110 */         for (int inEnd = offset + max; offset < inEnd; offset++) {
/* 111 */           int c = str.charAt(offset);
/* 112 */           if (c < 32) {
/* 113 */             if (c != 10)
/* 115 */               if (c != 13)
/* 117 */                 if (c != 9) {
/* 118 */                   this.mOutputPtr = ptr;
/* 119 */                   c = handleInvalidChar(c);
/*     */                 }   
/* 121 */           } else if (c > 126) {
/* 122 */             this.mOutputPtr = ptr;
/* 123 */             if (c > 127) {
/* 124 */               handleInvalidAsciiChar(c);
/* 125 */             } else if (this.mXml11) {
/* 126 */               c = handleInvalidChar(c);
/*     */             } 
/*     */           } 
/* 129 */           this.mOutputBuffer[ptr++] = (byte)c;
/*     */         } 
/*     */       } else {
/* 132 */         for (int inEnd = offset + max; offset < inEnd; offset++)
/* 133 */           this.mOutputBuffer[ptr++] = (byte)str.charAt(offset); 
/*     */       } 
/* 136 */       len -= max;
/*     */     } 
/* 138 */     this.mOutputPtr = ptr;
/*     */   }
/*     */   
/*     */   protected void writeAttrValue(String data) throws IOException {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_2
/*     */     //   2: aload_1
/*     */     //   3: invokevirtual length : ()I
/*     */     //   6: istore_3
/*     */     //   7: aload_0
/*     */     //   8: getfield mOutputPtr : I
/*     */     //   11: istore #4
/*     */     //   13: iload_3
/*     */     //   14: ifle -> 342
/*     */     //   17: aload_0
/*     */     //   18: getfield mOutputBuffer : [B
/*     */     //   21: arraylength
/*     */     //   22: iload #4
/*     */     //   24: isub
/*     */     //   25: istore #5
/*     */     //   27: iload #5
/*     */     //   29: iconst_1
/*     */     //   30: if_icmpge -> 53
/*     */     //   33: aload_0
/*     */     //   34: iload #4
/*     */     //   36: putfield mOutputPtr : I
/*     */     //   39: aload_0
/*     */     //   40: invokevirtual flushBuffer : ()V
/*     */     //   43: iconst_0
/*     */     //   44: istore #4
/*     */     //   46: aload_0
/*     */     //   47: getfield mOutputBuffer : [B
/*     */     //   50: arraylength
/*     */     //   51: istore #5
/*     */     //   53: aload_0
/*     */     //   54: getfield mSurrogate : I
/*     */     //   57: ifeq -> 98
/*     */     //   60: aload_1
/*     */     //   61: iload_2
/*     */     //   62: iinc #2, 1
/*     */     //   65: invokevirtual charAt : (I)C
/*     */     //   68: istore #6
/*     */     //   70: aload_0
/*     */     //   71: iload #6
/*     */     //   73: invokevirtual calcSurrogate : (I)I
/*     */     //   76: istore #6
/*     */     //   78: aload_0
/*     */     //   79: iload #4
/*     */     //   81: putfield mOutputPtr : I
/*     */     //   84: aload_0
/*     */     //   85: iload #6
/*     */     //   87: invokevirtual writeAsEntity : (I)I
/*     */     //   90: istore #4
/*     */     //   92: iinc #3, -1
/*     */     //   95: goto -> 13
/*     */     //   98: iload #5
/*     */     //   100: iload_3
/*     */     //   101: if_icmple -> 107
/*     */     //   104: iload_3
/*     */     //   105: istore #5
/*     */     //   107: iload_2
/*     */     //   108: iload #5
/*     */     //   110: iadd
/*     */     //   111: istore #6
/*     */     //   113: iload_2
/*     */     //   114: iload #6
/*     */     //   116: if_icmpge -> 334
/*     */     //   119: aload_1
/*     */     //   120: iload_2
/*     */     //   121: iinc #2, 1
/*     */     //   124: invokevirtual charAt : (I)C
/*     */     //   127: istore #7
/*     */     //   129: iload #7
/*     */     //   131: bipush #32
/*     */     //   133: if_icmpge -> 223
/*     */     //   136: iload #7
/*     */     //   138: bipush #13
/*     */     //   140: if_icmpne -> 166
/*     */     //   143: aload_0
/*     */     //   144: getfield mEscapeCR : Z
/*     */     //   147: ifne -> 310
/*     */     //   150: aload_0
/*     */     //   151: getfield mOutputBuffer : [B
/*     */     //   154: iload #4
/*     */     //   156: iinc #4, 1
/*     */     //   159: iload #7
/*     */     //   161: i2b
/*     */     //   162: bastore
/*     */     //   163: goto -> 113
/*     */     //   166: iload #7
/*     */     //   168: bipush #10
/*     */     //   170: if_icmpeq -> 310
/*     */     //   173: iload #7
/*     */     //   175: bipush #9
/*     */     //   177: if_icmpeq -> 310
/*     */     //   180: aload_0
/*     */     //   181: getfield mCheckContent : Z
/*     */     //   184: ifeq -> 310
/*     */     //   187: aload_0
/*     */     //   188: getfield mXml11 : Z
/*     */     //   191: ifeq -> 199
/*     */     //   194: iload #7
/*     */     //   196: ifne -> 310
/*     */     //   199: aload_0
/*     */     //   200: iload #7
/*     */     //   202: invokevirtual handleInvalidChar : (I)C
/*     */     //   205: istore #7
/*     */     //   207: aload_0
/*     */     //   208: getfield mOutputBuffer : [B
/*     */     //   211: iload #4
/*     */     //   213: iinc #4, 1
/*     */     //   216: iload #7
/*     */     //   218: i2b
/*     */     //   219: bastore
/*     */     //   220: goto -> 113
/*     */     //   223: iload #7
/*     */     //   225: bipush #127
/*     */     //   227: if_icmpge -> 267
/*     */     //   230: iload #7
/*     */     //   232: bipush #60
/*     */     //   234: if_icmpeq -> 310
/*     */     //   237: iload #7
/*     */     //   239: bipush #38
/*     */     //   241: if_icmpeq -> 310
/*     */     //   244: iload #7
/*     */     //   246: bipush #34
/*     */     //   248: if_icmpeq -> 310
/*     */     //   251: aload_0
/*     */     //   252: getfield mOutputBuffer : [B
/*     */     //   255: iload #4
/*     */     //   257: iinc #4, 1
/*     */     //   260: iload #7
/*     */     //   262: i2b
/*     */     //   263: bastore
/*     */     //   264: goto -> 113
/*     */     //   267: iload #7
/*     */     //   269: ldc 55296
/*     */     //   271: if_icmplt -> 310
/*     */     //   274: iload #7
/*     */     //   276: ldc 57343
/*     */     //   278: if_icmpgt -> 310
/*     */     //   281: aload_0
/*     */     //   282: iload #7
/*     */     //   284: putfield mSurrogate : I
/*     */     //   287: iload_2
/*     */     //   288: iload #6
/*     */     //   290: if_icmpne -> 296
/*     */     //   293: goto -> 334
/*     */     //   296: aload_0
/*     */     //   297: aload_1
/*     */     //   298: iload_2
/*     */     //   299: iinc #2, 1
/*     */     //   302: invokevirtual charAt : (I)C
/*     */     //   305: invokevirtual calcSurrogate : (I)I
/*     */     //   308: istore #7
/*     */     //   310: aload_0
/*     */     //   311: iload #4
/*     */     //   313: putfield mOutputPtr : I
/*     */     //   316: aload_0
/*     */     //   317: iload #7
/*     */     //   319: invokevirtual writeAsEntity : (I)I
/*     */     //   322: istore #4
/*     */     //   324: aload_1
/*     */     //   325: invokevirtual length : ()I
/*     */     //   328: iload_2
/*     */     //   329: isub
/*     */     //   330: istore_3
/*     */     //   331: goto -> 13
/*     */     //   334: iload_3
/*     */     //   335: iload #5
/*     */     //   337: isub
/*     */     //   338: istore_3
/*     */     //   339: goto -> 13
/*     */     //   342: aload_0
/*     */     //   343: iload #4
/*     */     //   345: putfield mOutputPtr : I
/*     */     //   348: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #144	-> 0
/*     */     //   #145	-> 2
/*     */     //   #146	-> 7
/*     */     //   #149	-> 13
/*     */     //   #150	-> 17
/*     */     //   #151	-> 27
/*     */     //   #152	-> 33
/*     */     //   #153	-> 39
/*     */     //   #154	-> 43
/*     */     //   #155	-> 46
/*     */     //   #158	-> 53
/*     */     //   #159	-> 60
/*     */     //   #160	-> 70
/*     */     //   #161	-> 78
/*     */     //   #162	-> 84
/*     */     //   #163	-> 92
/*     */     //   #164	-> 95
/*     */     //   #167	-> 98
/*     */     //   #168	-> 104
/*     */     //   #171	-> 107
/*     */     //   #172	-> 119
/*     */     //   #173	-> 129
/*     */     //   #177	-> 136
/*     */     //   #178	-> 143
/*     */     //   #179	-> 150
/*     */     //   #180	-> 163
/*     */     //   #182	-> 166
/*     */     //   #183	-> 180
/*     */     //   #184	-> 187
/*     */     //   #185	-> 199
/*     */     //   #186	-> 207
/*     */     //   #187	-> 220
/*     */     //   #192	-> 223
/*     */     //   #193	-> 230
/*     */     //   #194	-> 251
/*     */     //   #195	-> 264
/*     */     //   #200	-> 267
/*     */     //   #201	-> 281
/*     */     //   #203	-> 287
/*     */     //   #204	-> 293
/*     */     //   #206	-> 296
/*     */     //   #213	-> 310
/*     */     //   #214	-> 316
/*     */     //   #215	-> 324
/*     */     //   #216	-> 331
/*     */     //   #218	-> 334
/*     */     //   #219	-> 339
/*     */     //   #220	-> 342
/*     */     //   #221	-> 348
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   70	28	6	sec	I
/*     */     //   129	205	7	c	I
/*     */     //   113	221	6	inEnd	I
/*     */     //   27	312	5	max	I
/*     */     //   0	349	0	this	Lcom/ctc/wstx/sw/AsciiXmlWriter;
/*     */     //   0	349	1	data	Ljava/lang/String;
/*     */     //   2	347	2	offset	I
/*     */     //   7	342	3	len	I
/*     */     //   13	336	4	ptr	I
/*     */   }
/*     */   
/*     */   protected void writeAttrValue(char[] data, int offset, int len) throws IOException {
/* 226 */     int ptr = this.mOutputPtr;
/* 229 */     while (len > 0) {
/* 230 */       int max = this.mOutputBuffer.length - ptr;
/* 231 */       if (max < 1) {
/* 232 */         this.mOutputPtr = ptr;
/* 233 */         flushBuffer();
/* 234 */         ptr = 0;
/* 235 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 238 */       if (this.mSurrogate != 0) {
/* 239 */         int sec = data[offset++];
/* 240 */         sec = calcSurrogate(sec);
/* 241 */         this.mOutputPtr = ptr;
/* 242 */         ptr = writeAsEntity(sec);
/* 243 */         len--;
/*     */         continue;
/*     */       } 
/* 247 */       if (max > len)
/* 248 */         max = len; 
/* 251 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 252 */         int c = data[offset++];
/* 253 */         if (c < 32) {
/* 257 */           if (c == 13) {
/* 258 */             if (!this.mEscapeCR) {
/* 259 */               this.mOutputBuffer[ptr++] = (byte)c;
/*     */               continue;
/*     */             } 
/* 262 */           } else if (c != 10 && c != 9 && 
/* 263 */             this.mCheckContent && (
/* 264 */             !this.mXml11 || c == 0)) {
/* 265 */             c = handleInvalidChar(c);
/* 266 */             this.mOutputBuffer[ptr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 272 */         } else if (c < 127) {
/* 273 */           if (c != 60 && c != 38 && c != 34) {
/* 274 */             this.mOutputBuffer[ptr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 280 */         } else if (c >= 55296 && c <= 57343) {
/* 281 */           this.mSurrogate = c;
/* 283 */           if (offset == inEnd)
/*     */             break; 
/* 286 */           c = calcSurrogate(data[offset++]);
/*     */         } 
/* 293 */         this.mOutputPtr = ptr;
/* 294 */         ptr = writeAsEntity(c);
/* 295 */         max -= inEnd - offset;
/*     */       } 
/* 298 */       len -= max;
/*     */     } 
/* 300 */     this.mOutputPtr = ptr;
/*     */   }
/*     */   
/*     */   protected int writeCDataContent(String data) throws IOException {
/* 308 */     int offset = 0;
/* 309 */     int len = data.length();
/* 310 */     if (!this.mCheckContent) {
/* 311 */       writeRaw(data, offset, len);
/* 312 */       return -1;
/*     */     } 
/* 314 */     int ptr = this.mOutputPtr;
/* 317 */     label45: while (len > 0) {
/* 318 */       int max = this.mOutputBuffer.length - ptr;
/* 319 */       if (max < 1) {
/* 320 */         this.mOutputPtr = ptr;
/* 321 */         flushBuffer();
/* 322 */         ptr = 0;
/* 323 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 326 */       if (max > len)
/* 327 */         max = len; 
/* 329 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 330 */         int c = data.charAt(offset++);
/* 331 */         if (c < 32) {
/* 332 */           if (c != 10)
/* 334 */             if (c != 13)
/* 336 */               if (c != 9) {
/* 337 */                 this.mOutputPtr = ptr;
/* 338 */                 c = handleInvalidChar(c);
/*     */               }   
/* 340 */         } else if (c > 126) {
/* 341 */           this.mOutputPtr = ptr;
/* 342 */           if (c > 127) {
/* 343 */             handleInvalidAsciiChar(c);
/* 344 */           } else if (this.mXml11) {
/* 345 */             c = handleInvalidChar(c);
/*     */           } 
/* 347 */         } else if (c == 62 && 
/* 348 */           offset > 2 && data.charAt(offset - 2) == ']' && data.charAt(offset - 3) == ']') {
/* 350 */           if (!this.mFixContent)
/* 351 */             return offset - 3; 
/* 356 */           this.mOutputPtr = ptr;
/* 357 */           writeCDataEnd();
/* 358 */           writeCDataStart();
/* 359 */           writeAscii((byte)62);
/* 360 */           ptr = this.mOutputPtr;
/* 364 */           len = data.length() - offset;
/*     */           continue label45;
/*     */         } 
/* 368 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 370 */       len -= max;
/*     */     } 
/* 372 */     this.mOutputPtr = ptr;
/* 373 */     return -1;
/*     */   }
/*     */   
/*     */   protected int writeCDataContent(char[] cbuf, int start, int len) throws IOException {
/* 381 */     if (!this.mCheckContent) {
/* 382 */       writeRaw(cbuf, start, len);
/* 383 */       return -1;
/*     */     } 
/* 386 */     int ptr = this.mOutputPtr;
/* 387 */     int offset = start;
/* 389 */     while (len > 0) {
/* 390 */       int max = this.mOutputBuffer.length - ptr;
/* 391 */       if (max < 1) {
/* 392 */         this.mOutputPtr = ptr;
/* 393 */         flushBuffer();
/* 394 */         ptr = 0;
/* 395 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 398 */       if (max > len)
/* 399 */         max = len; 
/* 402 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 403 */         int c = cbuf[offset++];
/* 404 */         if (c < 32) {
/* 405 */           if (c != 10)
/* 407 */             if (c != 13)
/* 409 */               if (c != 9) {
/* 410 */                 this.mOutputPtr = ptr;
/* 411 */                 c = handleInvalidChar(c);
/*     */               }   
/* 413 */         } else if (c > 126) {
/* 414 */           this.mOutputPtr = ptr;
/* 415 */           if (c > 127) {
/* 416 */             handleInvalidAsciiChar(c);
/* 417 */           } else if (this.mXml11) {
/* 418 */             c = handleInvalidChar(c);
/*     */           } 
/* 420 */         } else if (c == 62 && 
/* 421 */           offset >= start + 3 && cbuf[offset - 2] == ']' && cbuf[offset - 3] == ']') {
/* 423 */           if (!this.mFixContent)
/* 424 */             return offset - 3; 
/* 429 */           this.mOutputPtr = ptr;
/* 430 */           writeCDataEnd();
/* 431 */           writeCDataStart();
/* 432 */           writeAscii((byte)62);
/* 433 */           ptr = this.mOutputPtr;
/* 437 */           max -= inEnd - offset;
/*     */           break;
/*     */         } 
/* 441 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 443 */       len -= max;
/*     */     } 
/* 445 */     this.mOutputPtr = ptr;
/* 446 */     return -1;
/*     */   }
/*     */   
/*     */   protected int writeCommentContent(String data) throws IOException {
/* 454 */     int offset = 0;
/* 455 */     int len = data.length();
/* 456 */     if (!this.mCheckContent) {
/* 457 */       writeRaw(data, offset, len);
/* 458 */       return -1;
/*     */     } 
/* 461 */     int ptr = this.mOutputPtr;
/* 463 */     while (len > 0) {
/* 464 */       int max = this.mOutputBuffer.length - ptr;
/* 465 */       if (max < 1) {
/* 466 */         this.mOutputPtr = ptr;
/* 467 */         flushBuffer();
/* 468 */         ptr = 0;
/* 469 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 472 */       if (max > len)
/* 473 */         max = len; 
/* 476 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 477 */         int c = data.charAt(offset++);
/* 478 */         if (c < 32) {
/* 479 */           if (c != 10)
/* 481 */             if (c != 13)
/* 483 */               if (c != 9) {
/* 484 */                 this.mOutputPtr = ptr;
/* 485 */                 c = handleInvalidChar(c);
/*     */               }   
/* 487 */         } else if (c > 126) {
/* 488 */           this.mOutputPtr = ptr;
/* 489 */           if (c > 127) {
/* 490 */             handleInvalidAsciiChar(c);
/* 491 */           } else if (this.mXml11) {
/* 492 */             c = handleInvalidChar(c);
/*     */           } 
/* 494 */         } else if (c == 45 && 
/* 495 */           offset > 1 && data.charAt(offset - 2) == '-') {
/* 496 */           if (!this.mFixContent)
/* 497 */             return offset - 2; 
/* 504 */           this.mOutputBuffer[ptr++] = 32;
/* 505 */           if (ptr >= this.mOutputBuffer.length) {
/* 506 */             this.mOutputPtr = ptr;
/* 507 */             flushBuffer();
/* 508 */             ptr = 0;
/*     */           } 
/* 510 */           this.mOutputBuffer[ptr++] = 45;
/* 515 */           max -= inEnd - offset;
/*     */           break;
/*     */         } 
/* 519 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 521 */       len -= max;
/*     */     } 
/* 523 */     this.mOutputPtr = ptr;
/* 524 */     return -1;
/*     */   }
/*     */   
/*     */   protected int writePIData(String data) throws IOException, XMLStreamException {
/* 532 */     int offset = 0;
/* 533 */     int len = data.length();
/* 534 */     if (!this.mCheckContent) {
/* 535 */       writeRaw(data, offset, len);
/* 536 */       return -1;
/*     */     } 
/* 539 */     int ptr = this.mOutputPtr;
/* 540 */     while (len > 0) {
/* 541 */       int max = this.mOutputBuffer.length - ptr;
/* 542 */       if (max < 1) {
/* 543 */         this.mOutputPtr = ptr;
/* 544 */         flushBuffer();
/* 545 */         ptr = 0;
/* 546 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 549 */       if (max > len)
/* 550 */         max = len; 
/* 552 */       for (int inEnd = offset + max; offset < inEnd; offset++) {
/* 553 */         int c = data.charAt(offset);
/* 554 */         if (c < 32) {
/* 555 */           if (c != 10)
/* 557 */             if (c != 13)
/* 559 */               if (c != 9) {
/* 560 */                 this.mOutputPtr = ptr;
/* 561 */                 c = handleInvalidChar(c);
/*     */               }   
/* 563 */         } else if (c > 126) {
/* 564 */           this.mOutputPtr = ptr;
/* 565 */           if (c > 127) {
/* 566 */             handleInvalidAsciiChar(c);
/* 567 */           } else if (this.mXml11) {
/* 568 */             c = handleInvalidChar(c);
/*     */           } 
/* 570 */         } else if (c == 62 && 
/* 571 */           offset > 0 && data.charAt(offset - 1) == '?') {
/* 572 */           return offset - 2;
/*     */         } 
/* 575 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 577 */       len -= max;
/*     */     } 
/* 579 */     this.mOutputPtr = ptr;
/* 580 */     return -1;
/*     */   }
/*     */   
/*     */   protected void writeTextContent(String data) throws IOException {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_2
/*     */     //   2: aload_1
/*     */     //   3: invokevirtual length : ()I
/*     */     //   6: istore_3
/*     */     //   7: iload_3
/*     */     //   8: ifle -> 372
/*     */     //   11: aload_0
/*     */     //   12: getfield mOutputBuffer : [B
/*     */     //   15: arraylength
/*     */     //   16: aload_0
/*     */     //   17: getfield mOutputPtr : I
/*     */     //   20: isub
/*     */     //   21: istore #4
/*     */     //   23: iload #4
/*     */     //   25: iconst_1
/*     */     //   26: if_icmpge -> 40
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual flushBuffer : ()V
/*     */     //   33: aload_0
/*     */     //   34: getfield mOutputBuffer : [B
/*     */     //   37: arraylength
/*     */     //   38: istore #4
/*     */     //   40: aload_0
/*     */     //   41: getfield mSurrogate : I
/*     */     //   44: ifeq -> 78
/*     */     //   47: aload_1
/*     */     //   48: iload_2
/*     */     //   49: iinc #2, 1
/*     */     //   52: invokevirtual charAt : (I)C
/*     */     //   55: istore #5
/*     */     //   57: aload_0
/*     */     //   58: iload #5
/*     */     //   60: invokevirtual calcSurrogate : (I)I
/*     */     //   63: istore #5
/*     */     //   65: aload_0
/*     */     //   66: iload #5
/*     */     //   68: invokevirtual writeAsEntity : (I)I
/*     */     //   71: pop
/*     */     //   72: iinc #3, -1
/*     */     //   75: goto -> 7
/*     */     //   78: iload #4
/*     */     //   80: iload_3
/*     */     //   81: if_icmple -> 87
/*     */     //   84: iload_3
/*     */     //   85: istore #4
/*     */     //   87: iload_2
/*     */     //   88: iload #4
/*     */     //   90: iadd
/*     */     //   91: istore #5
/*     */     //   93: iload_2
/*     */     //   94: iload #5
/*     */     //   96: if_icmpge -> 364
/*     */     //   99: aload_1
/*     */     //   100: iload_2
/*     */     //   101: iinc #2, 1
/*     */     //   104: invokevirtual charAt : (I)C
/*     */     //   107: istore #6
/*     */     //   109: iload #6
/*     */     //   111: bipush #32
/*     */     //   113: if_icmpge -> 237
/*     */     //   116: iload #6
/*     */     //   118: bipush #10
/*     */     //   120: if_icmpeq -> 130
/*     */     //   123: iload #6
/*     */     //   125: bipush #9
/*     */     //   127: if_icmpne -> 152
/*     */     //   130: aload_0
/*     */     //   131: getfield mOutputBuffer : [B
/*     */     //   134: aload_0
/*     */     //   135: dup
/*     */     //   136: getfield mOutputPtr : I
/*     */     //   139: dup_x1
/*     */     //   140: iconst_1
/*     */     //   141: iadd
/*     */     //   142: putfield mOutputPtr : I
/*     */     //   145: iload #6
/*     */     //   147: i2b
/*     */     //   148: bastore
/*     */     //   149: goto -> 93
/*     */     //   152: iload #6
/*     */     //   154: bipush #13
/*     */     //   156: if_icmpne -> 188
/*     */     //   159: aload_0
/*     */     //   160: getfield mEscapeCR : Z
/*     */     //   163: ifne -> 347
/*     */     //   166: aload_0
/*     */     //   167: getfield mOutputBuffer : [B
/*     */     //   170: aload_0
/*     */     //   171: dup
/*     */     //   172: getfield mOutputPtr : I
/*     */     //   175: dup_x1
/*     */     //   176: iconst_1
/*     */     //   177: iadd
/*     */     //   178: putfield mOutputPtr : I
/*     */     //   181: iload #6
/*     */     //   183: i2b
/*     */     //   184: bastore
/*     */     //   185: goto -> 93
/*     */     //   188: aload_0
/*     */     //   189: getfield mXml11 : Z
/*     */     //   192: ifeq -> 200
/*     */     //   195: iload #6
/*     */     //   197: ifne -> 347
/*     */     //   200: aload_0
/*     */     //   201: getfield mCheckContent : Z
/*     */     //   204: ifeq -> 347
/*     */     //   207: aload_0
/*     */     //   208: iload #6
/*     */     //   210: invokevirtual handleInvalidChar : (I)C
/*     */     //   213: istore #6
/*     */     //   215: aload_0
/*     */     //   216: getfield mOutputBuffer : [B
/*     */     //   219: aload_0
/*     */     //   220: dup
/*     */     //   221: getfield mOutputPtr : I
/*     */     //   224: dup_x1
/*     */     //   225: iconst_1
/*     */     //   226: iadd
/*     */     //   227: putfield mOutputPtr : I
/*     */     //   230: iload #6
/*     */     //   232: i2b
/*     */     //   233: bastore
/*     */     //   234: goto -> 93
/*     */     //   237: iload #6
/*     */     //   239: bipush #127
/*     */     //   241: if_icmpge -> 304
/*     */     //   244: iload #6
/*     */     //   246: bipush #60
/*     */     //   248: if_icmpeq -> 347
/*     */     //   251: iload #6
/*     */     //   253: bipush #38
/*     */     //   255: if_icmpeq -> 347
/*     */     //   258: iload #6
/*     */     //   260: bipush #62
/*     */     //   262: if_icmpne -> 282
/*     */     //   265: iload_2
/*     */     //   266: iconst_1
/*     */     //   267: if_icmple -> 347
/*     */     //   270: aload_1
/*     */     //   271: iload_2
/*     */     //   272: iconst_2
/*     */     //   273: isub
/*     */     //   274: invokevirtual charAt : (I)C
/*     */     //   277: bipush #93
/*     */     //   279: if_icmpeq -> 347
/*     */     //   282: aload_0
/*     */     //   283: getfield mOutputBuffer : [B
/*     */     //   286: aload_0
/*     */     //   287: dup
/*     */     //   288: getfield mOutputPtr : I
/*     */     //   291: dup_x1
/*     */     //   292: iconst_1
/*     */     //   293: iadd
/*     */     //   294: putfield mOutputPtr : I
/*     */     //   297: iload #6
/*     */     //   299: i2b
/*     */     //   300: bastore
/*     */     //   301: goto -> 93
/*     */     //   304: iload #6
/*     */     //   306: ldc 55296
/*     */     //   308: if_icmplt -> 347
/*     */     //   311: iload #6
/*     */     //   313: ldc 57343
/*     */     //   315: if_icmpgt -> 347
/*     */     //   318: aload_0
/*     */     //   319: iload #6
/*     */     //   321: putfield mSurrogate : I
/*     */     //   324: iload_2
/*     */     //   325: iload #5
/*     */     //   327: if_icmpne -> 333
/*     */     //   330: goto -> 364
/*     */     //   333: aload_0
/*     */     //   334: aload_1
/*     */     //   335: iload_2
/*     */     //   336: iinc #2, 1
/*     */     //   339: invokevirtual charAt : (I)C
/*     */     //   342: invokevirtual calcSurrogate : (I)I
/*     */     //   345: istore #6
/*     */     //   347: aload_0
/*     */     //   348: iload #6
/*     */     //   350: invokevirtual writeAsEntity : (I)I
/*     */     //   353: pop
/*     */     //   354: aload_1
/*     */     //   355: invokevirtual length : ()I
/*     */     //   358: iload_2
/*     */     //   359: isub
/*     */     //   360: istore_3
/*     */     //   361: goto -> 7
/*     */     //   364: iload_3
/*     */     //   365: iload #4
/*     */     //   367: isub
/*     */     //   368: istore_3
/*     */     //   369: goto -> 7
/*     */     //   372: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #586	-> 0
/*     */     //   #587	-> 2
/*     */     //   #590	-> 7
/*     */     //   #591	-> 11
/*     */     //   #592	-> 23
/*     */     //   #593	-> 29
/*     */     //   #594	-> 33
/*     */     //   #597	-> 40
/*     */     //   #598	-> 47
/*     */     //   #599	-> 57
/*     */     //   #600	-> 65
/*     */     //   #601	-> 72
/*     */     //   #602	-> 75
/*     */     //   #605	-> 78
/*     */     //   #606	-> 84
/*     */     //   #609	-> 87
/*     */     //   #610	-> 99
/*     */     //   #611	-> 109
/*     */     //   #612	-> 116
/*     */     //   #613	-> 130
/*     */     //   #614	-> 149
/*     */     //   #615	-> 152
/*     */     //   #616	-> 159
/*     */     //   #617	-> 166
/*     */     //   #618	-> 185
/*     */     //   #620	-> 188
/*     */     //   #621	-> 200
/*     */     //   #622	-> 207
/*     */     //   #623	-> 215
/*     */     //   #624	-> 234
/*     */     //   #629	-> 237
/*     */     //   #630	-> 244
/*     */     //   #631	-> 258
/*     */     //   #632	-> 282
/*     */     //   #633	-> 301
/*     */     //   #639	-> 304
/*     */     //   #640	-> 318
/*     */     //   #642	-> 324
/*     */     //   #643	-> 330
/*     */     //   #645	-> 333
/*     */     //   #652	-> 347
/*     */     //   #653	-> 354
/*     */     //   #654	-> 361
/*     */     //   #656	-> 364
/*     */     //   #657	-> 369
/*     */     //   #658	-> 372
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   57	21	5	sec	I
/*     */     //   109	255	6	c	I
/*     */     //   93	271	5	inEnd	I
/*     */     //   23	346	4	max	I
/*     */     //   0	373	0	this	Lcom/ctc/wstx/sw/AsciiXmlWriter;
/*     */     //   0	373	1	data	Ljava/lang/String;
/*     */     //   2	371	2	offset	I
/*     */     //   7	366	3	len	I
/*     */   }
/*     */   
/*     */   protected void writeTextContent(char[] cbuf, int offset, int len) throws IOException {
/* 664 */     while (len > 0) {
/* 665 */       int max = this.mOutputBuffer.length - this.mOutputPtr;
/* 666 */       if (max < 1) {
/* 667 */         flushBuffer();
/* 668 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 671 */       if (this.mSurrogate != 0) {
/* 672 */         int sec = cbuf[offset++];
/* 673 */         sec = calcSurrogate(sec);
/* 674 */         writeAsEntity(sec);
/* 675 */         len--;
/*     */         continue;
/*     */       } 
/* 679 */       if (max > len)
/* 680 */         max = len; 
/* 683 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 684 */         int c = cbuf[offset++];
/* 685 */         if (c < 32) {
/* 686 */           if (c == 10 || c == 9) {
/* 687 */             this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 689 */           if (c == 13) {
/* 690 */             if (!this.mEscapeCR) {
/* 691 */               this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */               continue;
/*     */             } 
/* 694 */           } else if ((!this.mXml11 || c == 0) && 
/* 695 */             this.mCheckContent) {
/* 696 */             c = handleInvalidChar(c);
/* 697 */             this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 703 */         } else if (c < 127) {
/* 704 */           if (c != 60 && c != 38)
/* 708 */             if (c != 62 || (offset > 1 && cbuf[offset - 2] != ']')) {
/* 709 */               this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */               continue;
/*     */             }  
/* 716 */         } else if (c >= 55296 && c <= 57343) {
/* 717 */           this.mSurrogate = c;
/* 719 */           if (offset == inEnd)
/*     */             break; 
/* 722 */           c = calcSurrogate(cbuf[offset++]);
/*     */         } 
/* 729 */         writeAsEntity(c);
/* 730 */         max -= inEnd - offset;
/*     */       } 
/* 733 */       len -= max;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void handleInvalidAsciiChar(int c) throws IOException {
/* 747 */     flush();
/* 755 */     throw new IOException("Invalid XML character (0x" + Integer.toHexString(c) + "); can only be output using character entity when using US-ASCII encoding");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\AsciiXmlWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */