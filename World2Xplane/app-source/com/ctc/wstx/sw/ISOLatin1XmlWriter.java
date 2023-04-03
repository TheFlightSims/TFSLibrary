/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class ISOLatin1XmlWriter extends EncodingXmlWriter {
/*     */   public ISOLatin1XmlWriter(OutputStream out, WriterConfig cfg, boolean autoclose) throws IOException {
/*  39 */     super(out, cfg, "ISO-8859-1", autoclose);
/*     */   }
/*     */   
/*     */   public void writeRaw(char[] cbuf, int offset, int len) throws IOException {
/*  45 */     if (this.mSurrogate != 0)
/*  46 */       throwUnpairedSurrogate(); 
/*  49 */     int ptr = this.mOutputPtr;
/*  50 */     while (len > 0) {
/*  51 */       int max = this.mOutputBuffer.length - ptr;
/*  52 */       if (max < 1) {
/*  53 */         this.mOutputPtr = ptr;
/*  54 */         flushBuffer();
/*  55 */         ptr = 0;
/*  56 */         max = this.mOutputBuffer.length;
/*     */       } 
/*  59 */       if (max > len)
/*  60 */         max = len; 
/*  62 */       if (this.mCheckContent) {
/*  63 */         for (int inEnd = offset + max; offset < inEnd; offset++) {
/*  64 */           int c = cbuf[offset];
/*  65 */           if (c < 32) {
/*  66 */             if (c != 10)
/*  68 */               if (c != 13)
/*  70 */                 if (c != 9) {
/*  71 */                   this.mOutputPtr = ptr;
/*  72 */                   c = handleInvalidChar(c);
/*     */                 }   
/*  74 */           } else if (c > 126) {
/*  75 */             if (c > 255) {
/*  76 */               this.mOutputPtr = ptr;
/*  77 */               handleInvalidLatinChar(c);
/*  78 */             } else if (this.mXml11 && 
/*  79 */               c < 159 && c != 133) {
/*  80 */               this.mOutputPtr = ptr;
/*  81 */               c = handleInvalidChar(c);
/*     */             } 
/*     */           } 
/*  85 */           this.mOutputBuffer[ptr++] = (byte)c;
/*     */         } 
/*     */       } else {
/*  88 */         for (int inEnd = offset + max; offset < inEnd; offset++)
/*  89 */           this.mOutputBuffer[ptr++] = (byte)cbuf[offset]; 
/*     */       } 
/*  92 */       len -= max;
/*     */     } 
/*  94 */     this.mOutputPtr = ptr;
/*     */   }
/*     */   
/*     */   public void writeRaw(String str, int offset, int len) throws IOException {
/* 100 */     if (this.mSurrogate != 0)
/* 101 */       throwUnpairedSurrogate(); 
/* 103 */     int ptr = this.mOutputPtr;
/* 104 */     while (len > 0) {
/* 105 */       int max = this.mOutputBuffer.length - ptr;
/* 106 */       if (max < 1) {
/* 107 */         this.mOutputPtr = ptr;
/* 108 */         flushBuffer();
/* 109 */         ptr = 0;
/* 110 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 113 */       if (max > len)
/* 114 */         max = len; 
/* 116 */       if (this.mCheckContent) {
/* 117 */         for (int inEnd = offset + max; offset < inEnd; offset++) {
/* 118 */           int c = str.charAt(offset);
/* 119 */           if (c < 32) {
/* 120 */             if (c != 10)
/* 122 */               if (c != 13)
/* 124 */                 if (c != 9) {
/* 125 */                   this.mOutputPtr = ptr;
/* 126 */                   c = handleInvalidChar(c);
/*     */                 }   
/* 128 */           } else if (c > 126) {
/* 129 */             if (c > 255) {
/* 130 */               this.mOutputPtr = ptr;
/* 131 */               handleInvalidLatinChar(c);
/* 132 */             } else if (this.mXml11 && 
/* 133 */               c < 159 && c != 133) {
/* 134 */               this.mOutputPtr = ptr;
/* 135 */               c = handleInvalidChar(c);
/*     */             } 
/*     */           } 
/* 139 */           this.mOutputBuffer[ptr++] = (byte)c;
/*     */         } 
/*     */       } else {
/* 142 */         for (int inEnd = offset + max; offset < inEnd; offset++)
/* 143 */           this.mOutputBuffer[ptr++] = (byte)str.charAt(offset); 
/*     */       } 
/* 146 */       len -= max;
/*     */     } 
/* 148 */     this.mOutputPtr = ptr;
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
/*     */     //   14: ifle -> 374
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
/*     */     //   116: if_icmpge -> 366
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
/*     */     //   147: ifne -> 342
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
/*     */     //   170: if_icmpeq -> 342
/*     */     //   173: iload #7
/*     */     //   175: bipush #9
/*     */     //   177: if_icmpeq -> 342
/*     */     //   180: aload_0
/*     */     //   181: getfield mCheckContent : Z
/*     */     //   184: ifeq -> 342
/*     */     //   187: aload_0
/*     */     //   188: getfield mXml11 : Z
/*     */     //   191: ifeq -> 199
/*     */     //   194: iload #7
/*     */     //   196: ifne -> 342
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
/*     */     //   234: if_icmpeq -> 342
/*     */     //   237: iload #7
/*     */     //   239: bipush #38
/*     */     //   241: if_icmpeq -> 342
/*     */     //   244: iload #7
/*     */     //   246: bipush #34
/*     */     //   248: if_icmpeq -> 342
/*     */     //   251: aload_0
/*     */     //   252: getfield mOutputBuffer : [B
/*     */     //   255: iload #4
/*     */     //   257: iinc #4, 1
/*     */     //   260: iload #7
/*     */     //   262: i2b
/*     */     //   263: bastore
/*     */     //   264: goto -> 113
/*     */     //   267: iload #7
/*     */     //   269: sipush #159
/*     */     //   272: if_icmple -> 299
/*     */     //   275: iload #7
/*     */     //   277: sipush #255
/*     */     //   280: if_icmpgt -> 299
/*     */     //   283: aload_0
/*     */     //   284: getfield mOutputBuffer : [B
/*     */     //   287: iload #4
/*     */     //   289: iinc #4, 1
/*     */     //   292: iload #7
/*     */     //   294: i2b
/*     */     //   295: bastore
/*     */     //   296: goto -> 113
/*     */     //   299: iload #7
/*     */     //   301: ldc 55296
/*     */     //   303: if_icmplt -> 342
/*     */     //   306: iload #7
/*     */     //   308: ldc 57343
/*     */     //   310: if_icmpgt -> 342
/*     */     //   313: aload_0
/*     */     //   314: iload #7
/*     */     //   316: putfield mSurrogate : I
/*     */     //   319: iload_2
/*     */     //   320: iload #6
/*     */     //   322: if_icmpne -> 328
/*     */     //   325: goto -> 366
/*     */     //   328: aload_0
/*     */     //   329: aload_1
/*     */     //   330: iload_2
/*     */     //   331: iinc #2, 1
/*     */     //   334: invokevirtual charAt : (I)C
/*     */     //   337: invokevirtual calcSurrogate : (I)I
/*     */     //   340: istore #7
/*     */     //   342: aload_0
/*     */     //   343: iload #4
/*     */     //   345: putfield mOutputPtr : I
/*     */     //   348: aload_0
/*     */     //   349: iload #7
/*     */     //   351: invokevirtual writeAsEntity : (I)I
/*     */     //   354: istore #4
/*     */     //   356: aload_1
/*     */     //   357: invokevirtual length : ()I
/*     */     //   360: iload_2
/*     */     //   361: isub
/*     */     //   362: istore_3
/*     */     //   363: goto -> 13
/*     */     //   366: iload_3
/*     */     //   367: iload #5
/*     */     //   369: isub
/*     */     //   370: istore_3
/*     */     //   371: goto -> 13
/*     */     //   374: aload_0
/*     */     //   375: iload #4
/*     */     //   377: putfield mOutputPtr : I
/*     */     //   380: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #154	-> 0
/*     */     //   #155	-> 2
/*     */     //   #156	-> 7
/*     */     //   #159	-> 13
/*     */     //   #160	-> 17
/*     */     //   #161	-> 27
/*     */     //   #162	-> 33
/*     */     //   #163	-> 39
/*     */     //   #164	-> 43
/*     */     //   #165	-> 46
/*     */     //   #168	-> 53
/*     */     //   #169	-> 60
/*     */     //   #170	-> 70
/*     */     //   #171	-> 78
/*     */     //   #172	-> 84
/*     */     //   #173	-> 92
/*     */     //   #174	-> 95
/*     */     //   #177	-> 98
/*     */     //   #178	-> 104
/*     */     //   #181	-> 107
/*     */     //   #182	-> 119
/*     */     //   #183	-> 129
/*     */     //   #187	-> 136
/*     */     //   #188	-> 143
/*     */     //   #189	-> 150
/*     */     //   #190	-> 163
/*     */     //   #192	-> 166
/*     */     //   #193	-> 180
/*     */     //   #194	-> 187
/*     */     //   #195	-> 199
/*     */     //   #196	-> 207
/*     */     //   #197	-> 220
/*     */     //   #202	-> 223
/*     */     //   #203	-> 230
/*     */     //   #204	-> 251
/*     */     //   #205	-> 264
/*     */     //   #208	-> 267
/*     */     //   #209	-> 283
/*     */     //   #210	-> 296
/*     */     //   #213	-> 299
/*     */     //   #214	-> 313
/*     */     //   #216	-> 319
/*     */     //   #217	-> 325
/*     */     //   #219	-> 328
/*     */     //   #226	-> 342
/*     */     //   #227	-> 348
/*     */     //   #228	-> 356
/*     */     //   #229	-> 363
/*     */     //   #231	-> 366
/*     */     //   #232	-> 371
/*     */     //   #233	-> 374
/*     */     //   #234	-> 380
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   70	28	6	sec	I
/*     */     //   129	237	7	c	I
/*     */     //   113	253	6	inEnd	I
/*     */     //   27	344	5	max	I
/*     */     //   0	381	0	this	Lcom/ctc/wstx/sw/ISOLatin1XmlWriter;
/*     */     //   0	381	1	data	Ljava/lang/String;
/*     */     //   2	379	2	offset	I
/*     */     //   7	374	3	len	I
/*     */     //   13	368	4	ptr	I
/*     */   }
/*     */   
/*     */   protected void writeAttrValue(char[] data, int offset, int len) throws IOException {
/* 239 */     int ptr = this.mOutputPtr;
/* 242 */     while (len > 0) {
/* 243 */       int max = this.mOutputBuffer.length - ptr;
/* 244 */       if (max < 1) {
/* 245 */         this.mOutputPtr = ptr;
/* 246 */         flushBuffer();
/* 247 */         ptr = 0;
/* 248 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 251 */       if (this.mSurrogate != 0) {
/* 252 */         int sec = data[offset++];
/* 253 */         sec = calcSurrogate(sec);
/* 254 */         this.mOutputPtr = ptr;
/* 255 */         ptr = writeAsEntity(sec);
/* 256 */         len--;
/*     */         continue;
/*     */       } 
/* 260 */       if (max > len)
/* 261 */         max = len; 
/* 264 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 265 */         int c = data[offset++];
/* 266 */         if (c < 32) {
/* 270 */           if (c == 13) {
/* 271 */             if (!this.mEscapeCR) {
/* 272 */               this.mOutputBuffer[ptr++] = (byte)c;
/*     */               continue;
/*     */             } 
/* 275 */           } else if (c != 10 && c != 9 && 
/* 276 */             this.mCheckContent && (
/* 277 */             !this.mXml11 || c == 0)) {
/* 278 */             c = handleInvalidChar(c);
/* 279 */             this.mOutputBuffer[ptr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 285 */         } else if (c < 127) {
/* 286 */           if (c != 60 && c != 38 && c != 34) {
/* 287 */             this.mOutputBuffer[ptr++] = (byte)c;
/*     */             continue;
/*     */           } 
/*     */         } else {
/* 291 */           if (c > 159 && c <= 255) {
/* 292 */             this.mOutputBuffer[ptr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 296 */           if (c >= 55296 && c <= 57343) {
/* 297 */             this.mSurrogate = c;
/* 299 */             if (offset == inEnd)
/*     */               break; 
/* 302 */             c = calcSurrogate(data[offset++]);
/*     */           } 
/*     */         } 
/* 309 */         this.mOutputPtr = ptr;
/* 310 */         ptr = writeAsEntity(c);
/* 311 */         max -= inEnd - offset;
/*     */       } 
/* 314 */       len -= max;
/*     */     } 
/* 316 */     this.mOutputPtr = ptr;
/*     */   }
/*     */   
/*     */   protected int writeCDataContent(String data) throws IOException {
/* 324 */     int offset = 0;
/* 325 */     int len = data.length();
/* 326 */     if (!this.mCheckContent) {
/* 327 */       writeRaw(data, offset, len);
/* 328 */       return -1;
/*     */     } 
/* 330 */     int ptr = this.mOutputPtr;
/* 333 */     label46: while (len > 0) {
/* 334 */       int max = this.mOutputBuffer.length - ptr;
/* 335 */       if (max < 1) {
/* 336 */         this.mOutputPtr = ptr;
/* 337 */         flushBuffer();
/* 338 */         ptr = 0;
/* 339 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 342 */       if (max > len)
/* 343 */         max = len; 
/* 345 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 346 */         int c = data.charAt(offset++);
/* 347 */         if (c < 32) {
/* 348 */           if (c != 10)
/* 350 */             if (c != 13)
/* 352 */               if (c != 9) {
/* 353 */                 this.mOutputPtr = ptr;
/* 354 */                 c = handleInvalidChar(c);
/*     */               }   
/* 356 */         } else if (c > 126) {
/* 357 */           if (c > 255) {
/* 358 */             this.mOutputPtr = ptr;
/* 359 */             handleInvalidLatinChar(c);
/* 360 */           } else if (this.mXml11 && 
/* 361 */             c < 159 && c != 133) {
/* 362 */             this.mOutputPtr = ptr;
/* 363 */             c = handleInvalidChar(c);
/*     */           } 
/* 366 */         } else if (c == 62 && 
/* 367 */           offset > 2 && data.charAt(offset - 2) == ']' && data.charAt(offset - 3) == ']') {
/* 369 */           if (!this.mFixContent)
/* 370 */             return offset - 3; 
/* 375 */           this.mOutputPtr = ptr;
/* 376 */           writeCDataEnd();
/* 377 */           writeCDataStart();
/* 378 */           writeAscii((byte)62);
/* 379 */           ptr = this.mOutputPtr;
/* 383 */           len = data.length() - offset;
/*     */           continue label46;
/*     */         } 
/* 387 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 389 */       len -= max;
/*     */     } 
/* 391 */     this.mOutputPtr = ptr;
/* 392 */     return -1;
/*     */   }
/*     */   
/*     */   protected int writeCDataContent(char[] cbuf, int start, int len) throws IOException {
/* 400 */     if (!this.mCheckContent) {
/* 401 */       writeRaw(cbuf, start, len);
/* 402 */       return -1;
/*     */     } 
/* 405 */     int ptr = this.mOutputPtr;
/* 406 */     int offset = start;
/* 408 */     while (len > 0) {
/* 409 */       int max = this.mOutputBuffer.length - ptr;
/* 410 */       if (max < 1) {
/* 411 */         this.mOutputPtr = ptr;
/* 412 */         flushBuffer();
/* 413 */         ptr = 0;
/* 414 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 417 */       if (max > len)
/* 418 */         max = len; 
/* 421 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 422 */         int c = cbuf[offset++];
/* 423 */         if (c < 32) {
/* 424 */           if (c != 10)
/* 426 */             if (c != 13)
/* 428 */               if (c != 9) {
/* 429 */                 this.mOutputPtr = ptr;
/* 430 */                 c = handleInvalidChar(c);
/*     */               }   
/* 432 */         } else if (c > 126) {
/* 433 */           if (c > 255) {
/* 434 */             this.mOutputPtr = ptr;
/* 435 */             handleInvalidLatinChar(c);
/* 436 */           } else if (this.mXml11 && 
/* 437 */             c < 159 && c != 133) {
/* 438 */             this.mOutputPtr = ptr;
/* 439 */             c = handleInvalidChar(c);
/*     */           } 
/* 442 */         } else if (c == 62 && 
/* 443 */           offset >= start + 3 && cbuf[offset - 2] == ']' && cbuf[offset - 3] == ']') {
/* 445 */           if (!this.mFixContent)
/* 446 */             return offset - 3; 
/* 451 */           this.mOutputPtr = ptr;
/* 452 */           writeCDataEnd();
/* 453 */           writeCDataStart();
/* 454 */           writeAscii((byte)62);
/* 455 */           ptr = this.mOutputPtr;
/* 459 */           max -= inEnd - offset;
/*     */           break;
/*     */         } 
/* 463 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 465 */       len -= max;
/*     */     } 
/* 467 */     this.mOutputPtr = ptr;
/* 468 */     return -1;
/*     */   }
/*     */   
/*     */   protected int writeCommentContent(String data) throws IOException {
/* 476 */     int offset = 0;
/* 477 */     int len = data.length();
/* 478 */     if (!this.mCheckContent) {
/* 479 */       writeRaw(data, offset, len);
/* 480 */       return -1;
/*     */     } 
/* 483 */     int ptr = this.mOutputPtr;
/* 485 */     while (len > 0) {
/* 486 */       int max = this.mOutputBuffer.length - ptr;
/* 487 */       if (max < 1) {
/* 488 */         this.mOutputPtr = ptr;
/* 489 */         flushBuffer();
/* 490 */         ptr = 0;
/* 491 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 494 */       if (max > len)
/* 495 */         max = len; 
/* 498 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 499 */         int c = data.charAt(offset++);
/* 500 */         if (c < 32) {
/* 501 */           if (c != 10)
/* 503 */             if (c != 13)
/* 505 */               if (c != 9) {
/* 506 */                 this.mOutputPtr = ptr;
/* 507 */                 c = handleInvalidChar(c);
/*     */               }   
/* 509 */         } else if (c > 126) {
/* 510 */           if (c > 255) {
/* 511 */             this.mOutputPtr = ptr;
/* 512 */             handleInvalidLatinChar(c);
/* 513 */           } else if (this.mXml11 && 
/* 514 */             c < 159 && c != 133) {
/* 515 */             this.mOutputPtr = ptr;
/* 516 */             c = handleInvalidChar(c);
/*     */           } 
/* 519 */         } else if (c == 45 && 
/* 520 */           offset > 1 && data.charAt(offset - 2) == '-') {
/* 521 */           if (!this.mFixContent)
/* 522 */             return offset - 2; 
/* 529 */           this.mOutputBuffer[ptr++] = 32;
/* 530 */           if (ptr >= this.mOutputBuffer.length) {
/* 531 */             this.mOutputPtr = ptr;
/* 532 */             flushBuffer();
/* 533 */             ptr = 0;
/*     */           } 
/* 535 */           this.mOutputBuffer[ptr++] = 45;
/* 540 */           max -= inEnd - offset;
/*     */           break;
/*     */         } 
/* 544 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 546 */       len -= max;
/*     */     } 
/* 548 */     this.mOutputPtr = ptr;
/* 549 */     return -1;
/*     */   }
/*     */   
/*     */   protected int writePIData(String data) throws IOException, XMLStreamException {
/* 557 */     int offset = 0;
/* 558 */     int len = data.length();
/* 559 */     if (!this.mCheckContent) {
/* 560 */       writeRaw(data, offset, len);
/* 561 */       return -1;
/*     */     } 
/* 564 */     int ptr = this.mOutputPtr;
/* 565 */     while (len > 0) {
/* 566 */       int max = this.mOutputBuffer.length - ptr;
/* 567 */       if (max < 1) {
/* 568 */         this.mOutputPtr = ptr;
/* 569 */         flushBuffer();
/* 570 */         ptr = 0;
/* 571 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 574 */       if (max > len)
/* 575 */         max = len; 
/* 577 */       for (int inEnd = offset + max; offset < inEnd; offset++) {
/* 578 */         int c = data.charAt(offset);
/* 579 */         if (c < 32) {
/* 580 */           if (c != 10)
/* 582 */             if (c != 13)
/* 584 */               if (c != 9) {
/* 585 */                 this.mOutputPtr = ptr;
/* 586 */                 c = handleInvalidChar(c);
/*     */               }   
/* 588 */         } else if (c > 126) {
/* 589 */           if (c > 255) {
/* 590 */             this.mOutputPtr = ptr;
/* 591 */             handleInvalidLatinChar(c);
/* 592 */           } else if (this.mXml11 && 
/* 593 */             c < 159 && c != 133) {
/* 594 */             this.mOutputPtr = ptr;
/* 595 */             c = handleInvalidChar(c);
/*     */           } 
/* 598 */         } else if (c == 62 && 
/* 599 */           offset > 0 && data.charAt(offset - 1) == '?') {
/* 600 */           return offset - 2;
/*     */         } 
/* 603 */         this.mOutputBuffer[ptr++] = (byte)c;
/*     */       } 
/* 605 */       len -= max;
/*     */     } 
/* 607 */     this.mOutputPtr = ptr;
/* 608 */     return -1;
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
/*     */     //   8: ifle -> 410
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
/*     */     //   96: if_icmpge -> 402
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
/*     */     //   163: ifne -> 385
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
/*     */     //   197: ifne -> 385
/*     */     //   200: aload_0
/*     */     //   201: getfield mCheckContent : Z
/*     */     //   204: ifeq -> 385
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
/*     */     //   248: if_icmpeq -> 385
/*     */     //   251: iload #6
/*     */     //   253: bipush #38
/*     */     //   255: if_icmpeq -> 385
/*     */     //   258: iload #6
/*     */     //   260: bipush #62
/*     */     //   262: if_icmpne -> 282
/*     */     //   265: iload_2
/*     */     //   266: iconst_1
/*     */     //   267: if_icmple -> 385
/*     */     //   270: aload_1
/*     */     //   271: iload_2
/*     */     //   272: iconst_2
/*     */     //   273: isub
/*     */     //   274: invokevirtual charAt : (I)C
/*     */     //   277: bipush #93
/*     */     //   279: if_icmpeq -> 385
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
/*     */     //   306: sipush #159
/*     */     //   309: if_icmple -> 342
/*     */     //   312: iload #6
/*     */     //   314: sipush #255
/*     */     //   317: if_icmpgt -> 342
/*     */     //   320: aload_0
/*     */     //   321: getfield mOutputBuffer : [B
/*     */     //   324: aload_0
/*     */     //   325: dup
/*     */     //   326: getfield mOutputPtr : I
/*     */     //   329: dup_x1
/*     */     //   330: iconst_1
/*     */     //   331: iadd
/*     */     //   332: putfield mOutputPtr : I
/*     */     //   335: iload #6
/*     */     //   337: i2b
/*     */     //   338: bastore
/*     */     //   339: goto -> 93
/*     */     //   342: iload #6
/*     */     //   344: ldc 55296
/*     */     //   346: if_icmplt -> 385
/*     */     //   349: iload #6
/*     */     //   351: ldc 57343
/*     */     //   353: if_icmpgt -> 385
/*     */     //   356: aload_0
/*     */     //   357: iload #6
/*     */     //   359: putfield mSurrogate : I
/*     */     //   362: iload_2
/*     */     //   363: iload #5
/*     */     //   365: if_icmpne -> 371
/*     */     //   368: goto -> 402
/*     */     //   371: aload_0
/*     */     //   372: aload_1
/*     */     //   373: iload_2
/*     */     //   374: iinc #2, 1
/*     */     //   377: invokevirtual charAt : (I)C
/*     */     //   380: invokevirtual calcSurrogate : (I)I
/*     */     //   383: istore #6
/*     */     //   385: aload_0
/*     */     //   386: iload #6
/*     */     //   388: invokevirtual writeAsEntity : (I)I
/*     */     //   391: pop
/*     */     //   392: aload_1
/*     */     //   393: invokevirtual length : ()I
/*     */     //   396: iload_2
/*     */     //   397: isub
/*     */     //   398: istore_3
/*     */     //   399: goto -> 7
/*     */     //   402: iload_3
/*     */     //   403: iload #4
/*     */     //   405: isub
/*     */     //   406: istore_3
/*     */     //   407: goto -> 7
/*     */     //   410: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #614	-> 0
/*     */     //   #615	-> 2
/*     */     //   #618	-> 7
/*     */     //   #619	-> 11
/*     */     //   #620	-> 23
/*     */     //   #621	-> 29
/*     */     //   #622	-> 33
/*     */     //   #625	-> 40
/*     */     //   #626	-> 47
/*     */     //   #627	-> 57
/*     */     //   #628	-> 65
/*     */     //   #629	-> 72
/*     */     //   #630	-> 75
/*     */     //   #633	-> 78
/*     */     //   #634	-> 84
/*     */     //   #637	-> 87
/*     */     //   #638	-> 99
/*     */     //   #639	-> 109
/*     */     //   #640	-> 116
/*     */     //   #641	-> 130
/*     */     //   #642	-> 149
/*     */     //   #643	-> 152
/*     */     //   #644	-> 159
/*     */     //   #645	-> 166
/*     */     //   #646	-> 185
/*     */     //   #648	-> 188
/*     */     //   #649	-> 200
/*     */     //   #650	-> 207
/*     */     //   #651	-> 215
/*     */     //   #652	-> 234
/*     */     //   #657	-> 237
/*     */     //   #658	-> 244
/*     */     //   #659	-> 258
/*     */     //   #660	-> 282
/*     */     //   #661	-> 301
/*     */     //   #665	-> 304
/*     */     //   #666	-> 320
/*     */     //   #667	-> 339
/*     */     //   #670	-> 342
/*     */     //   #671	-> 356
/*     */     //   #673	-> 362
/*     */     //   #674	-> 368
/*     */     //   #676	-> 371
/*     */     //   #683	-> 385
/*     */     //   #684	-> 392
/*     */     //   #685	-> 399
/*     */     //   #687	-> 402
/*     */     //   #688	-> 407
/*     */     //   #689	-> 410
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   57	21	5	sec	I
/*     */     //   109	293	6	c	I
/*     */     //   93	309	5	inEnd	I
/*     */     //   23	384	4	max	I
/*     */     //   0	411	0	this	Lcom/ctc/wstx/sw/ISOLatin1XmlWriter;
/*     */     //   0	411	1	data	Ljava/lang/String;
/*     */     //   2	409	2	offset	I
/*     */     //   7	404	3	len	I
/*     */   }
/*     */   
/*     */   protected void writeTextContent(char[] cbuf, int offset, int len) throws IOException {
/* 695 */     while (len > 0) {
/* 696 */       int max = this.mOutputBuffer.length - this.mOutputPtr;
/* 697 */       if (max < 1) {
/* 698 */         flushBuffer();
/* 699 */         max = this.mOutputBuffer.length;
/*     */       } 
/* 702 */       if (this.mSurrogate != 0) {
/* 703 */         int sec = cbuf[offset++];
/* 704 */         sec = calcSurrogate(sec);
/* 705 */         writeAsEntity(sec);
/* 706 */         len--;
/*     */         continue;
/*     */       } 
/* 710 */       if (max > len)
/* 711 */         max = len; 
/* 714 */       for (int inEnd = offset + max; offset < inEnd; ) {
/* 715 */         int c = cbuf[offset++];
/* 716 */         if (c < 32) {
/* 717 */           if (c == 10 || c == 9) {
/* 718 */             this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 720 */           if (c == 13) {
/* 721 */             if (!this.mEscapeCR) {
/* 722 */               this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */               continue;
/*     */             } 
/* 725 */           } else if ((!this.mXml11 || c == 0) && 
/* 726 */             this.mCheckContent) {
/* 727 */             c = handleInvalidChar(c);
/* 728 */             this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 734 */         } else if (c < 127) {
/* 735 */           if (c != 60 && c != 38)
/* 739 */             if (c != 62 || (offset > 1 && cbuf[offset - 2] != ']')) {
/* 740 */               this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */               continue;
/*     */             }  
/*     */         } else {
/* 745 */           if (c > 159 && c <= 255) {
/* 746 */             this.mOutputBuffer[this.mOutputPtr++] = (byte)c;
/*     */             continue;
/*     */           } 
/* 750 */           if (c >= 55296 && c <= 57343) {
/* 751 */             this.mSurrogate = c;
/* 753 */             if (offset == inEnd)
/*     */               break; 
/* 756 */             c = calcSurrogate(cbuf[offset++]);
/*     */           } 
/*     */         } 
/* 763 */         writeAsEntity(c);
/* 764 */         max -= inEnd - offset;
/*     */       } 
/* 767 */       len -= max;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void handleInvalidLatinChar(int c) throws IOException {
/* 781 */     flush();
/* 789 */     throw new IOException("Invalid XML character (0x" + Integer.toHexString(c) + "); can only be output using character entity when using ISO-8859-1 encoding");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\ISOLatin1XmlWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */