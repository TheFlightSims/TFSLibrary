/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.measure.unit.AnnotatedUnit;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ class UCUMParser implements UCUMParserConstants {
/*     */   private SymbolMap _symbols;
/*     */   
/*     */   public UCUMParserTokenManager token_source;
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
/*     */   public UCUMParser(SymbolMap symbols, InputStream in) {
/*  21 */     this(in);
/*  22 */     this._symbols = symbols;
/*     */   }
/*     */   
/*     */   public final Unit parseUnit() throws ParseException {
/*  30 */     Unit u = Term();
/*  31 */     jj_consume_token(0);
/*  32 */     return u;
/*     */   }
/*     */   
/*     */   public final Unit Term() throws ParseException {
/*  37 */     Unit result = Unit.ONE;
/*  38 */     Unit temp = Unit.ONE;
/*  39 */     result = Component();
/*     */     while (true) {
/*  42 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 11:
/*     */         case 12:
/*     */           break;
/*     */         default:
/*  48 */           this.jj_la1[0] = this.jj_gen;
/*     */           break;
/*     */       } 
/*  51 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 11:
/*  53 */           jj_consume_token(11);
/*  54 */           temp = Component();
/*  55 */           result = result.times(temp);
/*     */           continue;
/*     */         case 12:
/*  58 */           jj_consume_token(12);
/*  59 */           temp = Component();
/*  60 */           result = result.divide(temp);
/*     */           continue;
/*     */       } 
/*  63 */       this.jj_la1[1] = this.jj_gen;
/*  64 */       jj_consume_token(-1);
/*  65 */       throw new ParseException();
/*     */     } 
/*  68 */     return result;
/*     */   }
/*     */   
/*     */   public final Unit Component() throws ParseException {
/*     */     long factor;
/*  73 */     Unit result = Unit.ONE;
/*  74 */     Token token = null;
/*  75 */     if (jj_2_1(2147483647)) {
/*  76 */       result = Annotatable();
/*  77 */       token = jj_consume_token(8);
/*  78 */       return (Unit)new AnnotatedUnit(result, token.image.substring(1, token.image.length() - 1));
/*     */     } 
/*  80 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 13:
/*  82 */         result = Annotatable();
/*  83 */         return result;
/*     */       case 8:
/*  86 */         token = jj_consume_token(8);
/*  87 */         return (Unit)new AnnotatedUnit(result, token.image.substring(1, token.image.length() - 1));
/*     */       case 9:
/*  90 */         token = jj_consume_token(9);
/*  91 */         factor = Long.parseLong(token.image);
/*  92 */         return result.times(factor);
/*     */       case 12:
/*  95 */         jj_consume_token(12);
/*  96 */         result = Component();
/*  97 */         return Unit.ONE.divide(result);
/*     */       case 14:
/* 100 */         jj_consume_token(14);
/* 101 */         result = Term();
/* 102 */         jj_consume_token(15);
/* 103 */         return result;
/*     */     } 
/* 106 */     this.jj_la1[2] = this.jj_gen;
/* 107 */     jj_consume_token(-1);
/* 108 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final Unit Annotatable() throws ParseException {
/* 115 */     Unit result = Unit.ONE;
/* 116 */     Token token1 = null;
/* 117 */     Token token2 = null;
/* 118 */     if (jj_2_2(2147483647)) {
/* 119 */       result = SimpleUnit();
/* 120 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 10:
/* 122 */           token1 = jj_consume_token(10);
/*     */           break;
/*     */         default:
/* 125 */           this.jj_la1[3] = this.jj_gen;
/*     */           break;
/*     */       } 
/* 128 */       token2 = jj_consume_token(9);
/* 129 */       int exponent = Integer.parseInt(token2.image);
/* 130 */       if (token1 != null && token1.image.equals("-"))
/* 131 */         return result.pow(-exponent); 
/* 133 */       return result.pow(exponent);
/*     */     } 
/* 136 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 13:
/* 138 */         result = SimpleUnit();
/* 139 */         return result;
/*     */     } 
/* 142 */     this.jj_la1[4] = this.jj_gen;
/* 143 */     jj_consume_token(-1);
/* 144 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final Unit SimpleUnit() throws ParseException {
/* 151 */     Token token = null;
/* 152 */     token = jj_consume_token(13);
/* 153 */     Unit<?> unit = this._symbols.getUnit(token.image);
/* 154 */     if (unit == null) {
/* 155 */       Prefix prefix = this._symbols.getPrefix(token.image);
/* 156 */       if (prefix != null) {
/* 157 */         String prefixSymbol = this._symbols.getSymbol(prefix);
/* 158 */         unit = this._symbols.getUnit(token.image.substring(prefixSymbol.length()));
/* 159 */         if (unit != null)
/* 160 */           return unit.transform(prefix.getConverter()); 
/*     */       } 
/* 163 */       throw new ParseException();
/*     */     } 
/* 165 */     return unit;
/*     */   }
/*     */   
/*     */   private final boolean jj_2_1(int xla) {
/* 171 */     this.jj_la = xla;
/* 171 */     this.jj_lastpos = this.jj_scanpos = this.token;
/*     */     try {
/* 172 */       return !jj_3_1();
/*     */     } catch (LookaheadSuccess ls) {
/* 173 */       return true;
/*     */     } finally {
/* 174 */       jj_save(0, xla);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean jj_2_2(int xla) {
/* 178 */     this.jj_la = xla;
/* 178 */     this.jj_lastpos = this.jj_scanpos = this.token;
/*     */     try {
/* 179 */       return !jj_3_2();
/*     */     } catch (LookaheadSuccess ls) {
/* 180 */       return true;
/*     */     } finally {
/* 181 */       jj_save(1, xla);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_3() {
/* 185 */     if (jj_scan_token(13))
/* 185 */       return true; 
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3_1() {
/* 190 */     if (jj_3R_2())
/* 190 */       return true; 
/* 191 */     if (jj_scan_token(8))
/* 191 */       return true; 
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_2() {
/* 197 */     Token xsp = this.jj_scanpos;
/* 198 */     if (jj_3R_4()) {
/* 199 */       this.jj_scanpos = xsp;
/* 200 */       if (jj_3R_5())
/* 200 */         return true; 
/*     */     } 
/* 202 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_5() {
/* 206 */     if (jj_3R_3())
/* 206 */       return true; 
/* 207 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_4() {
/* 211 */     if (jj_3R_3())
/* 211 */       return true; 
/* 213 */     Token xsp = this.jj_scanpos;
/* 214 */     if (jj_scan_token(10))
/* 214 */       this.jj_scanpos = xsp; 
/* 215 */     if (jj_scan_token(9))
/* 215 */       return true; 
/* 216 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3_2() {
/* 220 */     if (jj_3R_3())
/* 220 */       return true; 
/* 222 */     Token xsp = this.jj_scanpos;
/* 223 */     if (jj_scan_token(10))
/* 223 */       this.jj_scanpos = xsp; 
/* 224 */     if (jj_scan_token(9))
/* 224 */       return true; 
/* 225 */     return false;
/*     */   }
/*     */   
/*     */   public boolean lookingAhead = false;
/*     */   
/*     */   private boolean jj_semLA;
/*     */   
/*     */   private int jj_gen;
/*     */   
/* 237 */   private final int[] jj_la1 = new int[5];
/*     */   
/*     */   private static int[] jj_la1_0;
/*     */   
/*     */   static {
/* 240 */     jj_la1_0();
/*     */   }
/*     */   
/*     */   private static void jj_la1_0() {
/* 243 */     jj_la1_0 = new int[] { 6144, 6144, 29440, 1024, 8192 };
/*     */   }
/*     */   
/* 245 */   private final JJCalls[] jj_2_rtns = new JJCalls[2];
/*     */   
/*     */   private boolean jj_rescan = false;
/*     */   
/* 247 */   private int jj_gc = 0;
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
/*     */   public UCUMParser(InputStream stream) {
/* 250 */     this(stream, (String)null);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream stream) {
/* 263 */     ReInit(stream, null);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream stream, String encoding) {
/*     */     try {
/* 266 */       this.jj_input_stream.ReInit(stream, encoding, 1, 1);
/*     */     } catch (UnsupportedEncodingException e) {
/* 266 */       throw new RuntimeException(e);
/*     */     } 
/* 267 */     this.token_source.ReInit(this.jj_input_stream);
/* 268 */     this.token = new Token();
/* 269 */     this.jj_ntk = -1;
/* 270 */     this.jj_gen = 0;
/*     */     int i;
/* 271 */     for (i = 0; i < 5; ) {
/* 271 */       this.jj_la1[i] = -1;
/* 271 */       i++;
/*     */     } 
/* 272 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 272 */       this.jj_2_rtns[i] = new JJCalls();
/* 272 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ReInit(Reader stream) {
/* 286 */     this.jj_input_stream.ReInit(stream, 1, 1);
/* 287 */     this.token_source.ReInit(this.jj_input_stream);
/* 288 */     this.token = new Token();
/* 289 */     this.jj_ntk = -1;
/* 290 */     this.jj_gen = 0;
/*     */     int i;
/* 291 */     for (i = 0; i < 5; ) {
/* 291 */       this.jj_la1[i] = -1;
/* 291 */       i++;
/*     */     } 
/* 292 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 292 */       this.jj_2_rtns[i] = new JJCalls();
/* 292 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ReInit(UCUMParserTokenManager tm) {
/* 305 */     this.token_source = tm;
/* 306 */     this.token = new Token();
/* 307 */     this.jj_ntk = -1;
/* 308 */     this.jj_gen = 0;
/*     */     int i;
/* 309 */     for (i = 0; i < 5; ) {
/* 309 */       this.jj_la1[i] = -1;
/* 309 */       i++;
/*     */     } 
/* 310 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 310 */       this.jj_2_rtns[i] = new JJCalls();
/* 310 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Token jj_consume_token(int kind) throws ParseException {
/*     */     Token oldToken;
/* 315 */     if ((oldToken = this.token).next != null) {
/* 315 */       this.token = this.token.next;
/*     */     } else {
/* 316 */       this.token = this.token.next = this.token_source.getNextToken();
/*     */     } 
/* 317 */     this.jj_ntk = -1;
/* 318 */     if (this.token.kind == kind) {
/* 319 */       this.jj_gen++;
/* 320 */       if (++this.jj_gc > 100) {
/* 321 */         this.jj_gc = 0;
/* 322 */         for (int i = 0; i < this.jj_2_rtns.length; i++) {
/* 323 */           JJCalls c = this.jj_2_rtns[i];
/* 324 */           while (c != null) {
/* 325 */             if (c.gen < this.jj_gen)
/* 325 */               c.first = null; 
/* 326 */             c = c.next;
/*     */           } 
/*     */         } 
/*     */       } 
/* 330 */       return this.token;
/*     */     } 
/* 332 */     this.token = oldToken;
/* 333 */     this.jj_kind = kind;
/* 334 */     throw generateParseException();
/*     */   }
/*     */   
/*     */   private static final class LookaheadSuccess extends Error {
/*     */     private LookaheadSuccess() {}
/*     */   }
/*     */   
/*     */   public UCUMParser(InputStream stream, String encoding) {
/* 338 */     this.jj_ls = new LookaheadSuccess();
/* 384 */     this.jj_expentries = new Vector();
/* 386 */     this.jj_kind = -1;
/* 387 */     this.jj_lasttokens = new int[100];
/*     */     try {
/*     */       this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
/*     */     } catch (UnsupportedEncodingException e) {
/*     */       throw new RuntimeException(e);
/*     */     } 
/*     */     this.token_source = new UCUMParserTokenManager(this.jj_input_stream);
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 5; ) {
/*     */       this.jj_la1[i] = -1;
/*     */       i++;
/*     */     } 
/*     */     for (i = 0; i < this.jj_2_rtns.length; ) {
/*     */       this.jj_2_rtns[i] = new JJCalls();
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public UCUMParser(Reader stream) {
/*     */     this.jj_ls = new LookaheadSuccess();
/*     */     this.jj_expentries = new Vector();
/*     */     this.jj_kind = -1;
/* 387 */     this.jj_lasttokens = new int[100];
/*     */     this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
/*     */     this.token_source = new UCUMParserTokenManager(this.jj_input_stream);
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 5; ) {
/*     */       this.jj_la1[i] = -1;
/*     */       i++;
/*     */     } 
/*     */     for (i = 0; i < this.jj_2_rtns.length; ) {
/*     */       this.jj_2_rtns[i] = new JJCalls();
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public UCUMParser(UCUMParserTokenManager tm) {
/*     */     this.jj_ls = new LookaheadSuccess();
/*     */     this.jj_expentries = new Vector();
/*     */     this.jj_kind = -1;
/* 387 */     this.jj_lasttokens = new int[100];
/*     */     this.token_source = tm;
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 5; ) {
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
/* 391 */     if (pos >= 100)
/*     */       return; 
/* 392 */     if (pos == this.jj_endpos + 1) {
/* 393 */       this.jj_lasttokens[this.jj_endpos++] = kind;
/* 394 */     } else if (this.jj_endpos != 0) {
/* 395 */       this.jj_expentry = new int[this.jj_endpos];
/* 396 */       for (int i = 0; i < this.jj_endpos; i++)
/* 397 */         this.jj_expentry[i] = this.jj_lasttokens[i]; 
/* 399 */       boolean exists = false;
/* 400 */       for (Enumeration<int[]> e = this.jj_expentries.elements(); e.hasMoreElements(); ) {
/* 401 */         int[] oldentry = e.nextElement();
/* 402 */         if (oldentry.length == this.jj_expentry.length) {
/* 403 */           exists = true;
/* 404 */           for (int j = 0; j < this.jj_expentry.length; j++) {
/* 405 */             if (oldentry[j] != this.jj_expentry[j]) {
/* 406 */               exists = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 410 */           if (exists)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 413 */       if (!exists)
/* 413 */         this.jj_expentries.addElement(this.jj_expentry); 
/* 414 */       if (pos != 0)
/* 414 */         this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParseException generateParseException() {
/* 419 */     this.jj_expentries.removeAllElements();
/* 420 */     boolean[] la1tokens = new boolean[16];
/*     */     int i;
/* 421 */     for (i = 0; i < 16; i++)
/* 422 */       la1tokens[i] = false; 
/* 424 */     if (this.jj_kind >= 0) {
/* 425 */       la1tokens[this.jj_kind] = true;
/* 426 */       this.jj_kind = -1;
/*     */     } 
/* 428 */     for (i = 0; i < 5; i++) {
/* 429 */       if (this.jj_la1[i] == this.jj_gen)
/* 430 */         for (int k = 0; k < 32; k++) {
/* 431 */           if ((jj_la1_0[i] & 1 << k) != 0)
/* 432 */             la1tokens[k] = true; 
/*     */         }  
/*     */     } 
/* 437 */     for (i = 0; i < 16; i++) {
/* 438 */       if (la1tokens[i]) {
/* 439 */         this.jj_expentry = new int[1];
/* 440 */         this.jj_expentry[0] = i;
/* 441 */         this.jj_expentries.addElement(this.jj_expentry);
/*     */       } 
/*     */     } 
/* 444 */     this.jj_endpos = 0;
/* 445 */     jj_rescan_token();
/* 446 */     jj_add_error_token(0, 0);
/* 447 */     int[][] exptokseq = new int[this.jj_expentries.size()][];
/* 448 */     for (int j = 0; j < this.jj_expentries.size(); j++)
/* 449 */       exptokseq[j] = this.jj_expentries.elementAt(j); 
/* 451 */     return new ParseException(this.token, exptokseq, tokenImage);
/*     */   }
/*     */   
/*     */   public final void enable_tracing() {}
/*     */   
/*     */   public final void disable_tracing() {}
/*     */   
/*     */   private final void jj_rescan_token() {
/* 461 */     this.jj_rescan = true;
/* 462 */     for (int i = 0; i < 2; i++) {
/*     */       try {
/* 464 */         JJCalls p = this.jj_2_rtns[i];
/*     */         do {
/* 466 */           if (p.gen > this.jj_gen) {
/* 467 */             this.jj_la = p.arg;
/* 467 */             this.jj_lastpos = this.jj_scanpos = p.first;
/* 468 */             switch (i) {
/*     */               case 0:
/* 469 */                 jj_3_1();
/*     */                 break;
/*     */               case 1:
/* 470 */                 jj_3_2();
/*     */                 break;
/*     */             } 
/*     */           } 
/* 473 */           p = p.next;
/* 474 */         } while (p != null);
/* 475 */       } catch (LookaheadSuccess ls) {}
/*     */     } 
/* 477 */     this.jj_rescan = false;
/*     */   }
/*     */   
/*     */   private final void jj_save(int index, int xla) {
/* 481 */     JJCalls p = this.jj_2_rtns[index];
/* 482 */     while (p.gen > this.jj_gen) {
/* 483 */       if (p.next == null) {
/* 483 */         p = p.next = new JJCalls();
/*     */         break;
/*     */       } 
/* 484 */       p = p.next;
/*     */     } 
/* 486 */     p.gen = this.jj_gen + xla - this.jj_la;
/* 486 */     p.first = this.token;
/* 486 */     p.arg = xla;
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UCUMParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */