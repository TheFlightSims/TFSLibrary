/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.measure.converter.LogConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.CompoundUnit;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ class UnitParser implements UnitParserConstants {
/*     */   private SymbolMap _symbols;
/*     */   
/*     */   public UnitParserTokenManager token_source;
/*     */   
/*     */   SimpleCharStream jj_input_stream;
/*     */   
/*     */   public Token token;
/*     */   
/*     */   public Token jj_nt;
/*     */   
/*     */   private int jj_ntk;
/*     */   
/*     */   private Token jj_scanpos;
/*     */   
/*     */   private Token jj_lastpos;
/*     */   
/*     */   private int jj_la;
/*     */   
/*     */   private static class Exponent {
/*     */     public final int pow;
/*     */     
/*     */     public final int root;
/*     */     
/*     */     public Exponent(int pow, int root) {
/*  11 */       this.pow = pow;
/*  12 */       this.root = root;
/*     */     }
/*     */   }
/*     */   
/*     */   public UnitParser(SymbolMap symbols, Reader in) {
/*  19 */     this(in);
/*  20 */     this._symbols = symbols;
/*     */   }
/*     */   
/*     */   public final Unit parseUnit() throws ParseException {
/*  28 */     Unit result = CompoundExpr();
/*  29 */     jj_consume_token(0);
/*  30 */     return result;
/*     */   }
/*     */   
/*     */   public final Unit CompoundExpr() throws ParseException {
/*     */     CompoundUnit compoundUnit;
/*  35 */     Unit result = Unit.ONE;
/*  36 */     Unit temp = Unit.ONE;
/*  37 */     result = AddExpr();
/*     */     while (true) {
/*  40 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 11:
/*     */           break;
/*     */         default:
/*  45 */           this.jj_la1[0] = this.jj_gen;
/*     */           break;
/*     */       } 
/*  48 */       jj_consume_token(11);
/*  49 */       temp = AddExpr();
/*  50 */       compoundUnit = result.compound(temp);
/*     */     } 
/*  52 */     return (Unit)compoundUnit;
/*     */   }
/*     */   
/*     */   public final Unit AddExpr() throws ParseException {
/*  57 */     Unit result = Unit.ONE;
/*  58 */     Number n1 = null;
/*  59 */     Token sign1 = null;
/*  60 */     Number n2 = null;
/*  61 */     Token sign2 = null;
/*  62 */     if (jj_2_1(2147483647)) {
/*  63 */       n1 = NumberExpr();
/*  64 */       sign1 = Sign();
/*     */     } 
/*  68 */     result = MulExpr();
/*  69 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 5:
/*     */       case 6:
/*  72 */         sign2 = Sign();
/*  73 */         n2 = NumberExpr();
/*     */         break;
/*     */       default:
/*  76 */         this.jj_la1[1] = this.jj_gen;
/*     */         break;
/*     */     } 
/*  79 */     if (n1 != null) {
/*  80 */       if (sign1.image.equals("-"))
/*  81 */         result = result.times(-1L); 
/*  83 */       result = result.plus(n1.doubleValue());
/*     */     } 
/*  85 */     if (n2 != null) {
/*  86 */       double offset = n2.doubleValue();
/*  87 */       if (sign2.image.equals("-"))
/*  88 */         offset = -offset; 
/*  90 */       result = result.plus(offset);
/*     */     } 
/*  92 */     return result;
/*     */   }
/*     */   
/*     */   public final Unit MulExpr() throws ParseException {
/*  97 */     Unit result = Unit.ONE;
/*  98 */     Unit temp = Unit.ONE;
/*  99 */     result = ExponentExpr();
/*     */     while (true) {
/* 102 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 7:
/*     */         case 8:
/*     */         case 9:
/*     */           break;
/*     */         default:
/* 109 */           this.jj_la1[2] = this.jj_gen;
/*     */           break;
/*     */       } 
/* 112 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 7:
/*     */         case 8:
/* 115 */           switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */             case 7:
/* 117 */               jj_consume_token(7);
/*     */               break;
/*     */             case 8:
/* 120 */               jj_consume_token(8);
/*     */               break;
/*     */             default:
/* 123 */               this.jj_la1[3] = this.jj_gen;
/* 124 */               jj_consume_token(-1);
/* 125 */               throw new ParseException();
/*     */           } 
/* 127 */           temp = ExponentExpr();
/* 128 */           result = result.times(temp);
/*     */           continue;
/*     */         case 9:
/* 131 */           jj_consume_token(9);
/* 132 */           temp = ExponentExpr();
/* 133 */           result = result.divide(temp);
/*     */           continue;
/*     */       } 
/* 136 */       this.jj_la1[4] = this.jj_gen;
/* 137 */       jj_consume_token(-1);
/* 138 */       throw new ParseException();
/*     */     } 
/* 141 */     return result;
/*     */   }
/*     */   
/*     */   public final Unit ExponentExpr() throws ParseException {
/*     */     double base;
/* 146 */     Unit result = Unit.ONE;
/* 147 */     Unit temp = Unit.ONE;
/* 148 */     Exponent exponent = null;
/* 149 */     Token token = null;
/* 150 */     if (jj_2_2(2147483647)) {
/*     */       double d;
/* 151 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 14:
/* 153 */           token = jj_consume_token(14);
/*     */           break;
/*     */         case 19:
/* 156 */           token = jj_consume_token(19);
/*     */           break;
/*     */         default:
/* 159 */           this.jj_la1[5] = this.jj_gen;
/* 160 */           jj_consume_token(-1);
/* 161 */           throw new ParseException();
/*     */       } 
/* 163 */       jj_consume_token(10);
/* 164 */       result = AtomicExpr();
/* 166 */       if (token.kind == 14) {
/* 167 */         d = Integer.parseInt(token.image);
/*     */       } else {
/* 169 */         d = Math.E;
/*     */       } 
/* 171 */       return result.transform((new LogConverter(d)).inverse());
/*     */     } 
/* 173 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 12:
/*     */       case 14:
/*     */       case 16:
/*     */       case 20:
/* 178 */         result = AtomicExpr();
/* 179 */         switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */           case 10:
/*     */           case 15:
/* 182 */             exponent = Exp();
/*     */             break;
/*     */           default:
/* 185 */             this.jj_la1[6] = this.jj_gen;
/*     */             break;
/*     */         } 
/* 188 */         if (exponent != null) {
/* 189 */           if (exponent.pow != 1)
/* 190 */             result = result.pow(exponent.pow); 
/* 192 */           if (exponent.root != 1)
/* 193 */             result = result.root(exponent.root); 
/*     */         } 
/* 196 */         return result;
/*     */       case 17:
/*     */       case 18:
/* 200 */         switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */           case 17:
/* 202 */             jj_consume_token(17);
/* 203 */             switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */               case 14:
/* 205 */                 token = jj_consume_token(14);
/*     */                 break;
/*     */             } 
/* 208 */             this.jj_la1[7] = this.jj_gen;
/*     */             break;
/*     */           case 18:
/* 213 */             token = jj_consume_token(18);
/*     */             break;
/*     */           default:
/* 216 */             this.jj_la1[8] = this.jj_gen;
/* 217 */             jj_consume_token(-1);
/* 218 */             throw new ParseException();
/*     */         } 
/* 220 */         jj_consume_token(12);
/* 221 */         result = AddExpr();
/* 222 */         jj_consume_token(13);
/* 223 */         base = 10.0D;
/* 224 */         if (token != null)
/* 225 */           if (token.kind == 14) {
/* 226 */             base = Integer.parseInt(token.image);
/* 227 */           } else if (token.kind == 18) {
/* 228 */             base = Math.E;
/*     */           }  
/* 231 */         return result.transform((UnitConverter)new LogConverter(base));
/*     */     } 
/* 234 */     this.jj_la1[9] = this.jj_gen;
/* 235 */     jj_consume_token(-1);
/* 236 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final Unit AtomicExpr() throws ParseException {
/*     */     Unit<?> unit;
/* 243 */     Unit result = Unit.ONE;
/* 244 */     Unit temp = Unit.ONE;
/* 245 */     Number n = null;
/* 246 */     Token token = null;
/* 247 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 14:
/*     */       case 16:
/* 250 */         n = NumberExpr();
/* 251 */         if (n instanceof Integer)
/* 252 */           return result.times(n.intValue()); 
/* 254 */         return result.times(n.doubleValue());
/*     */       case 20:
/* 258 */         token = jj_consume_token(20);
/* 259 */         unit = this._symbols.getUnit(token.image);
/* 260 */         if (unit == null) {
/* 261 */           Prefix prefix = this._symbols.getPrefix(token.image);
/* 262 */           if (prefix != null) {
/* 263 */             String prefixSymbol = this._symbols.getSymbol(prefix);
/* 264 */             unit = this._symbols.getUnit(token.image.substring(prefixSymbol.length()));
/* 265 */             if (unit != null)
/* 266 */               return unit.transform(prefix.getConverter()); 
/*     */           } 
/* 269 */           throw new ParseException();
/*     */         } 
/* 271 */         return unit;
/*     */       case 12:
/* 275 */         jj_consume_token(12);
/* 276 */         result = AddExpr();
/* 277 */         jj_consume_token(13);
/* 278 */         return result;
/*     */     } 
/* 281 */     this.jj_la1[10] = this.jj_gen;
/* 282 */     jj_consume_token(-1);
/* 283 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final Token Sign() throws ParseException {
/* 289 */     Token result = null;
/* 290 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 5:
/* 292 */         result = jj_consume_token(5);
/* 302 */         return result;
/*     */       case 6:
/*     */         result = jj_consume_token(6);
/* 302 */         return result;
/*     */     } 
/*     */     this.jj_la1[11] = this.jj_gen;
/*     */     jj_consume_token(-1);
/*     */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final Number NumberExpr() throws ParseException {
/* 307 */     Token token = null;
/* 308 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 14:
/* 310 */         token = jj_consume_token(14);
/* 311 */         return Long.valueOf(token.image);
/*     */       case 16:
/* 314 */         token = jj_consume_token(16);
/* 315 */         return Double.valueOf(token.image);
/*     */     } 
/* 318 */     this.jj_la1[12] = this.jj_gen;
/* 319 */     jj_consume_token(-1);
/* 320 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final Exponent Exp() throws ParseException {
/*     */     int pow, root, i;
/* 326 */     Token powSign = null;
/* 327 */     Token powToken = null;
/* 328 */     Token rootSign = null;
/* 329 */     Token rootToken = null;
/* 330 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 10:
/* 332 */         jj_consume_token(10);
/* 333 */         switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */           case 5:
/*     */           case 6:
/*     */           case 14:
/* 337 */             switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */               case 5:
/*     */               case 6:
/* 340 */                 powSign = Sign();
/*     */                 break;
/*     */               default:
/* 343 */                 this.jj_la1[13] = this.jj_gen;
/*     */                 break;
/*     */             } 
/* 346 */             powToken = jj_consume_token(14);
/* 347 */             pow = Integer.parseInt(powToken.image);
/* 348 */             if (powSign != null && powSign.image.equals("-"))
/* 349 */               pow = -pow; 
/* 351 */             return new Exponent(pow, 1);
/*     */           case 12:
/* 354 */             jj_consume_token(12);
/* 355 */             switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */               case 5:
/*     */               case 6:
/* 358 */                 powSign = Sign();
/*     */                 break;
/*     */               default:
/* 361 */                 this.jj_la1[14] = this.jj_gen;
/*     */                 break;
/*     */             } 
/* 364 */             powToken = jj_consume_token(14);
/* 365 */             switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */               case 9:
/* 367 */                 jj_consume_token(9);
/* 368 */                 switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */                   case 5:
/*     */                   case 6:
/* 371 */                     rootSign = Sign();
/*     */                     break;
/*     */                   default:
/* 374 */                     this.jj_la1[15] = this.jj_gen;
/*     */                     break;
/*     */                 } 
/* 377 */                 rootToken = jj_consume_token(14);
/*     */                 break;
/*     */               default:
/* 380 */                 this.jj_la1[16] = this.jj_gen;
/*     */                 break;
/*     */             } 
/* 383 */             jj_consume_token(13);
/* 384 */             pow = Integer.parseInt(powToken.image);
/* 385 */             if (powSign != null && powSign.image.equals("-"))
/* 386 */               pow = -pow; 
/* 388 */             root = 1;
/* 389 */             if (rootToken != null) {
/* 390 */               root = Integer.parseInt(rootToken.image);
/* 391 */               if (rootSign != null && rootSign.image.equals("-"))
/* 392 */                 root = -root; 
/*     */             } 
/* 395 */             return new Exponent(pow, root);
/*     */         } 
/* 398 */         this.jj_la1[17] = this.jj_gen;
/* 399 */         jj_consume_token(-1);
/* 400 */         throw new ParseException();
/*     */       case 15:
/* 404 */         powToken = jj_consume_token(15);
/* 405 */         pow = 0;
/* 406 */         for (i = 0; i < powToken.image.length(); i++) {
/* 407 */           pow *= 10;
/* 408 */           switch (powToken.image.charAt(i)) {
/*     */             case '¹':
/* 409 */               pow++;
/*     */               break;
/*     */             case '²':
/* 410 */               pow += 2;
/*     */               break;
/*     */             case '³':
/* 411 */               pow += 3;
/*     */               break;
/*     */             case '⁴':
/* 412 */               pow += 4;
/*     */               break;
/*     */             case '⁵':
/* 413 */               pow += 5;
/*     */               break;
/*     */             case '⁶':
/* 414 */               pow += 6;
/*     */               break;
/*     */             case '⁷':
/* 415 */               pow += 7;
/*     */               break;
/*     */             case '⁸':
/* 416 */               pow += 8;
/*     */               break;
/*     */             case '⁹':
/* 417 */               pow += 9;
/*     */               break;
/*     */           } 
/*     */         } 
/* 420 */         return new Exponent(pow, 1);
/*     */     } 
/* 423 */     this.jj_la1[18] = this.jj_gen;
/* 424 */     jj_consume_token(-1);
/* 425 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   private final boolean jj_2_1(int xla) {
/* 431 */     this.jj_la = xla;
/* 431 */     this.jj_lastpos = this.jj_scanpos = this.token;
/*     */     try {
/* 432 */       return !jj_3_1();
/*     */     } catch (LookaheadSuccess ls) {
/* 433 */       return true;
/*     */     } finally {
/* 434 */       jj_save(0, xla);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean jj_2_2(int xla) {
/* 438 */     this.jj_la = xla;
/* 438 */     this.jj_lastpos = this.jj_scanpos = this.token;
/*     */     try {
/* 439 */       return !jj_3_2();
/*     */     } catch (LookaheadSuccess ls) {
/* 440 */       return true;
/*     */     } finally {
/* 441 */       jj_save(1, xla);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_3() {
/* 446 */     Token xsp = this.jj_scanpos;
/* 447 */     if (jj_3R_5()) {
/* 448 */       this.jj_scanpos = xsp;
/* 449 */       if (jj_3R_6())
/* 449 */         return true; 
/*     */     } 
/* 451 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_6() {
/* 455 */     if (jj_scan_token(16))
/* 455 */       return true; 
/* 456 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3_2() {
/* 461 */     Token xsp = this.jj_scanpos;
/* 462 */     if (jj_scan_token(14)) {
/* 463 */       this.jj_scanpos = xsp;
/* 464 */       if (jj_scan_token(19))
/* 464 */         return true; 
/*     */     } 
/* 466 */     if (jj_scan_token(10))
/* 466 */       return true; 
/* 467 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3_1() {
/* 471 */     if (jj_3R_3())
/* 471 */       return true; 
/* 472 */     if (jj_3R_4())
/* 472 */       return true; 
/* 473 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_4() {
/* 478 */     Token xsp = this.jj_scanpos;
/* 479 */     if (jj_scan_token(5)) {
/* 480 */       this.jj_scanpos = xsp;
/* 481 */       if (jj_scan_token(6))
/* 481 */         return true; 
/*     */     } 
/* 483 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_5() {
/* 487 */     if (jj_scan_token(14))
/* 487 */       return true; 
/* 488 */     return false;
/*     */   }
/*     */   
/*     */   public boolean lookingAhead = false;
/*     */   
/*     */   private boolean jj_semLA;
/*     */   
/*     */   private int jj_gen;
/*     */   
/* 500 */   private final int[] jj_la1 = new int[19];
/*     */   
/*     */   private static int[] jj_la1_0;
/*     */   
/*     */   static {
/* 503 */     jj_la1_0();
/*     */   }
/*     */   
/*     */   private static void jj_la1_0() {
/* 506 */     jj_la1_0 = new int[] { 
/* 506 */         2048, 96, 896, 384, 896, 540672, 33792, 16384, 393216, 1527808, 
/* 506 */         1134592, 96, 81920, 96, 96, 96, 512, 20576, 33792 };
/*     */   }
/*     */   
/* 508 */   private final JJCalls[] jj_2_rtns = new JJCalls[2];
/*     */   
/*     */   private boolean jj_rescan = false;
/*     */   
/* 510 */   private int jj_gc = 0;
/*     */   
/*     */   private final LookaheadSuccess jj_ls;
/*     */   
/*     */   private Vector jj_expentries;
/*     */   
/*     */   private int[] jj_expentry;
/*     */   
/*     */   private int jj_kind;
/*     */   
/*     */   private int[] jj_lasttokens;
/*     */   
/*     */   private int jj_endpos;
/*     */   
/*     */   public UnitParser(InputStream stream) {
/* 513 */     this(stream, (String)null);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream stream) {
/* 526 */     ReInit(stream, null);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream stream, String encoding) {
/*     */     try {
/* 529 */       this.jj_input_stream.ReInit(stream, encoding, 1, 1);
/*     */     } catch (UnsupportedEncodingException e) {
/* 529 */       throw new RuntimeException(e);
/*     */     } 
/* 530 */     this.token_source.ReInit(this.jj_input_stream);
/* 531 */     this.token = new Token();
/* 532 */     this.jj_ntk = -1;
/* 533 */     this.jj_gen = 0;
/*     */     int i;
/* 534 */     for (i = 0; i < 19; ) {
/* 534 */       this.jj_la1[i] = -1;
/* 534 */       i++;
/*     */     } 
/* 535 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 535 */       this.jj_2_rtns[i] = new JJCalls();
/* 535 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ReInit(Reader stream) {
/* 549 */     this.jj_input_stream.ReInit(stream, 1, 1);
/* 550 */     this.token_source.ReInit(this.jj_input_stream);
/* 551 */     this.token = new Token();
/* 552 */     this.jj_ntk = -1;
/* 553 */     this.jj_gen = 0;
/*     */     int i;
/* 554 */     for (i = 0; i < 19; ) {
/* 554 */       this.jj_la1[i] = -1;
/* 554 */       i++;
/*     */     } 
/* 555 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 555 */       this.jj_2_rtns[i] = new JJCalls();
/* 555 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ReInit(UnitParserTokenManager tm) {
/* 568 */     this.token_source = tm;
/* 569 */     this.token = new Token();
/* 570 */     this.jj_ntk = -1;
/* 571 */     this.jj_gen = 0;
/*     */     int i;
/* 572 */     for (i = 0; i < 19; ) {
/* 572 */       this.jj_la1[i] = -1;
/* 572 */       i++;
/*     */     } 
/* 573 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 573 */       this.jj_2_rtns[i] = new JJCalls();
/* 573 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Token jj_consume_token(int kind) throws ParseException {
/*     */     Token oldToken;
/* 578 */     if ((oldToken = this.token).next != null) {
/* 578 */       this.token = this.token.next;
/*     */     } else {
/* 579 */       this.token = this.token.next = this.token_source.getNextToken();
/*     */     } 
/* 580 */     this.jj_ntk = -1;
/* 581 */     if (this.token.kind == kind) {
/* 582 */       this.jj_gen++;
/* 583 */       if (++this.jj_gc > 100) {
/* 584 */         this.jj_gc = 0;
/* 585 */         for (int i = 0; i < this.jj_2_rtns.length; i++) {
/* 586 */           JJCalls c = this.jj_2_rtns[i];
/* 587 */           while (c != null) {
/* 588 */             if (c.gen < this.jj_gen)
/* 588 */               c.first = null; 
/* 589 */             c = c.next;
/*     */           } 
/*     */         } 
/*     */       } 
/* 593 */       return this.token;
/*     */     } 
/* 595 */     this.token = oldToken;
/* 596 */     this.jj_kind = kind;
/* 597 */     throw generateParseException();
/*     */   }
/*     */   
/*     */   private static final class LookaheadSuccess extends Error {
/*     */     private LookaheadSuccess() {}
/*     */   }
/*     */   
/*     */   public UnitParser(InputStream stream, String encoding) {
/* 601 */     this.jj_ls = new LookaheadSuccess();
/* 647 */     this.jj_expentries = new Vector();
/* 649 */     this.jj_kind = -1;
/* 650 */     this.jj_lasttokens = new int[100];
/*     */     try {
/*     */       this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
/*     */     } catch (UnsupportedEncodingException e) {
/*     */       throw new RuntimeException(e);
/*     */     } 
/*     */     this.token_source = new UnitParserTokenManager(this.jj_input_stream);
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 19; ) {
/*     */       this.jj_la1[i] = -1;
/*     */       i++;
/*     */     } 
/*     */     for (i = 0; i < this.jj_2_rtns.length; ) {
/*     */       this.jj_2_rtns[i] = new JJCalls();
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public UnitParser(Reader stream) {
/*     */     this.jj_ls = new LookaheadSuccess();
/*     */     this.jj_expentries = new Vector();
/*     */     this.jj_kind = -1;
/* 650 */     this.jj_lasttokens = new int[100];
/*     */     this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
/*     */     this.token_source = new UnitParserTokenManager(this.jj_input_stream);
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 19; ) {
/*     */       this.jj_la1[i] = -1;
/*     */       i++;
/*     */     } 
/*     */     for (i = 0; i < this.jj_2_rtns.length; ) {
/*     */       this.jj_2_rtns[i] = new JJCalls();
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public UnitParser(UnitParserTokenManager tm) {
/*     */     this.jj_ls = new LookaheadSuccess();
/*     */     this.jj_expentries = new Vector();
/*     */     this.jj_kind = -1;
/* 650 */     this.jj_lasttokens = new int[100];
/*     */     this.token_source = tm;
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 19; ) {
/*     */       this.jj_la1[i] = -1;
/*     */       i++;
/*     */     } 
/*     */     for (i = 0; i < this.jj_2_rtns.length; ) {
/*     */       this.jj_2_rtns[i] = new JJCalls();
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean jj_scan_token(int kind) {
/*     */     if (this.jj_scanpos == this.jj_lastpos) {
/*     */       this.jj_la--;
/*     */       if (this.jj_scanpos.next == null) {
/*     */         this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken();
/*     */       } else {
/*     */         this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next;
/*     */       } 
/*     */     } else {
/*     */       this.jj_scanpos = this.jj_scanpos.next;
/*     */     } 
/*     */     if (this.jj_rescan) {
/*     */       int i = 0;
/*     */       Token tok = this.token;
/*     */       while (tok != null && tok != this.jj_scanpos) {
/*     */         i++;
/*     */         tok = tok.next;
/*     */       } 
/*     */       if (tok != null)
/*     */         jj_add_error_token(kind, i); 
/*     */     } 
/*     */     if (this.jj_scanpos.kind != kind)
/*     */       return true; 
/*     */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos)
/*     */       throw this.jj_ls; 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public final Token getNextToken() {
/*     */     if (this.token.next != null) {
/*     */       this.token = this.token.next;
/*     */     } else {
/*     */       this.token = this.token.next = this.token_source.getNextToken();
/*     */     } 
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen++;
/*     */     return this.token;
/*     */   }
/*     */   
/*     */   public final Token getToken(int index) {
/*     */     Token t = this.lookingAhead ? this.jj_scanpos : this.token;
/*     */     for (int i = 0; i < index; i++) {
/*     */       if (t.next != null) {
/*     */         t = t.next;
/*     */       } else {
/*     */         t = t.next = this.token_source.getNextToken();
/*     */       } 
/*     */     } 
/*     */     return t;
/*     */   }
/*     */   
/*     */   private final int jj_ntk() {
/*     */     if ((this.jj_nt = this.token.next) == null)
/*     */       return this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind; 
/*     */     return this.jj_ntk = this.jj_nt.kind;
/*     */   }
/*     */   
/*     */   private void jj_add_error_token(int kind, int pos) {
/* 654 */     if (pos >= 100)
/*     */       return; 
/* 655 */     if (pos == this.jj_endpos + 1) {
/* 656 */       this.jj_lasttokens[this.jj_endpos++] = kind;
/* 657 */     } else if (this.jj_endpos != 0) {
/* 658 */       this.jj_expentry = new int[this.jj_endpos];
/* 659 */       for (int i = 0; i < this.jj_endpos; i++)
/* 660 */         this.jj_expentry[i] = this.jj_lasttokens[i]; 
/* 662 */       boolean exists = false;
/* 663 */       for (Enumeration<int[]> e = this.jj_expentries.elements(); e.hasMoreElements(); ) {
/* 664 */         int[] oldentry = e.nextElement();
/* 665 */         if (oldentry.length == this.jj_expentry.length) {
/* 666 */           exists = true;
/* 667 */           for (int j = 0; j < this.jj_expentry.length; j++) {
/* 668 */             if (oldentry[j] != this.jj_expentry[j]) {
/* 669 */               exists = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 673 */           if (exists)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 676 */       if (!exists)
/* 676 */         this.jj_expentries.addElement(this.jj_expentry); 
/* 677 */       if (pos != 0)
/* 677 */         this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParseException generateParseException() {
/* 682 */     this.jj_expentries.removeAllElements();
/* 683 */     boolean[] la1tokens = new boolean[21];
/*     */     int i;
/* 684 */     for (i = 0; i < 21; i++)
/* 685 */       la1tokens[i] = false; 
/* 687 */     if (this.jj_kind >= 0) {
/* 688 */       la1tokens[this.jj_kind] = true;
/* 689 */       this.jj_kind = -1;
/*     */     } 
/* 691 */     for (i = 0; i < 19; i++) {
/* 692 */       if (this.jj_la1[i] == this.jj_gen)
/* 693 */         for (int k = 0; k < 32; k++) {
/* 694 */           if ((jj_la1_0[i] & 1 << k) != 0)
/* 695 */             la1tokens[k] = true; 
/*     */         }  
/*     */     } 
/* 700 */     for (i = 0; i < 21; i++) {
/* 701 */       if (la1tokens[i]) {
/* 702 */         this.jj_expentry = new int[1];
/* 703 */         this.jj_expentry[0] = i;
/* 704 */         this.jj_expentries.addElement(this.jj_expentry);
/*     */       } 
/*     */     } 
/* 707 */     this.jj_endpos = 0;
/* 708 */     jj_rescan_token();
/* 709 */     jj_add_error_token(0, 0);
/* 710 */     int[][] exptokseq = new int[this.jj_expentries.size()][];
/* 711 */     for (int j = 0; j < this.jj_expentries.size(); j++)
/* 712 */       exptokseq[j] = this.jj_expentries.elementAt(j); 
/* 714 */     return new ParseException(this.token, exptokseq, tokenImage);
/*     */   }
/*     */   
/*     */   public final void enable_tracing() {}
/*     */   
/*     */   public final void disable_tracing() {}
/*     */   
/*     */   private final void jj_rescan_token() {
/* 724 */     this.jj_rescan = true;
/* 725 */     for (int i = 0; i < 2; i++) {
/*     */       try {
/* 727 */         JJCalls p = this.jj_2_rtns[i];
/*     */         do {
/* 729 */           if (p.gen > this.jj_gen) {
/* 730 */             this.jj_la = p.arg;
/* 730 */             this.jj_lastpos = this.jj_scanpos = p.first;
/* 731 */             switch (i) {
/*     */               case 0:
/* 732 */                 jj_3_1();
/*     */                 break;
/*     */               case 1:
/* 733 */                 jj_3_2();
/*     */                 break;
/*     */             } 
/*     */           } 
/* 736 */           p = p.next;
/* 737 */         } while (p != null);
/* 738 */       } catch (LookaheadSuccess ls) {}
/*     */     } 
/* 740 */     this.jj_rescan = false;
/*     */   }
/*     */   
/*     */   private final void jj_save(int index, int xla) {
/* 744 */     JJCalls p = this.jj_2_rtns[index];
/* 745 */     while (p.gen > this.jj_gen) {
/* 746 */       if (p.next == null) {
/* 746 */         p = p.next = new JJCalls();
/*     */         break;
/*     */       } 
/* 747 */       p = p.next;
/*     */     } 
/* 749 */     p.gen = this.jj_gen + xla - this.jj_la;
/* 749 */     p.first = this.token;
/* 749 */     p.arg = xla;
/*     */   }
/*     */   
/*     */   static final class JJCalls {
/*     */     int gen;
/*     */     
/*     */     Token first;
/*     */     
/*     */     int arg;
/*     */     
/*     */     JJCalls next;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UnitParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */