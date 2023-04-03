/*     */ package org.apache.commons.configuration.plist;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import org.apache.commons.codec.binary.Hex;
/*     */ import org.apache.commons.configuration.HierarchicalConfiguration;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ class PropertyListParser implements PropertyListParserConstants {
/*     */   public PropertyListParserTokenManager token_source;
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
/*     */   protected String removeQuotes(String s) {
/*  27 */     if (s == null)
/*  29 */       return null; 
/*  32 */     if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2)
/*  34 */       s = s.substring(1, s.length() - 1); 
/*  37 */     return s;
/*     */   }
/*     */   
/*     */   protected String unescapeQuotes(String s) {
/*  42 */     return StringUtils.replace(s, "\\\"", "\"");
/*     */   }
/*     */   
/*     */   protected byte[] filterData(String s) throws ParseException {
/*  51 */     if (s == null)
/*  53 */       return null; 
/*  57 */     if (s.startsWith("<") && s.endsWith(">") && s.length() >= 2)
/*  59 */       s = s.substring(1, s.length() - 1); 
/*  63 */     s = StringUtils.replaceChars(s, " \t\n\r", "");
/*  66 */     if (s.length() % 2 != 0)
/*  68 */       s = "0" + s; 
/*     */     try {
/*  74 */       return Hex.decodeHex(s.toCharArray());
/*  76 */     } catch (Exception e) {
/*  78 */       throw new ParseException("Unable to parse the byte[] : " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Date parseDate(String s) throws ParseException {
/*  87 */     return PropertyListConfiguration.parseDate(s);
/*     */   }
/*     */   
/*     */   public final PropertyListConfiguration parse() throws ParseException {
/*  91 */     PropertyListConfiguration configuration = null;
/*  92 */     configuration = Dictionary();
/*  93 */     jj_consume_token(0);
/*  94 */     return configuration;
/*     */   }
/*     */   
/*     */   public final PropertyListConfiguration Dictionary() throws ParseException {
/*  99 */     PropertyListConfiguration configuration = new PropertyListConfiguration();
/* 100 */     List children = new ArrayList();
/* 101 */     HierarchicalConfiguration.Node child = null;
/* 102 */     jj_consume_token(8);
/*     */     while (true) {
/* 105 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */         case 21:
/*     */         case 22:
/*     */           break;
/*     */         default:
/* 111 */           this.jj_la1[0] = this.jj_gen;
/*     */           break;
/*     */       } 
/* 114 */       child = Property();
/* 115 */       if (child.getValue() instanceof HierarchicalConfiguration) {
/* 118 */         HierarchicalConfiguration conf = (HierarchicalConfiguration)child.getValue();
/* 119 */         HierarchicalConfiguration.Node root = conf.getRoot();
/* 120 */         root.setName(child.getName());
/* 121 */         children.add(root);
/*     */         continue;
/*     */       } 
/* 125 */       children.add(child);
/*     */     } 
/* 128 */     jj_consume_token(9);
/* 129 */     for (int i = 0; i < children.size(); i++) {
/* 131 */       child = children.get(i);
/* 132 */       configuration.getRoot().addChild(child);
/*     */     } 
/* 135 */     return configuration;
/*     */   }
/*     */   
/*     */   public final HierarchicalConfiguration.Node Property() throws ParseException {
/* 140 */     String key = null;
/* 141 */     Object value = null;
/* 142 */     HierarchicalConfiguration.Node node = new HierarchicalConfiguration.Node();
/* 143 */     key = String();
/* 144 */     node.setName(key);
/* 145 */     jj_consume_token(11);
/* 146 */     value = Element();
/* 147 */     node.setValue(value);
/* 148 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 10:
/* 150 */         jj_consume_token(10);
/* 156 */         return node;
/*     */     } 
/*     */     this.jj_la1[1] = this.jj_gen;
/* 156 */     return node;
/*     */   }
/*     */   
/*     */   public final Object Element() throws ParseException {
/* 161 */     Object value = null;
/* 162 */     if (jj_2_1(2)) {
/* 163 */       value = Array();
/* 164 */       return value;
/*     */     } 
/* 166 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 8:
/* 168 */         value = Dictionary();
/* 169 */         return value;
/*     */       case 21:
/*     */       case 22:
/* 173 */         value = String();
/* 174 */         return value;
/*     */       case 19:
/* 177 */         value = Data();
/* 178 */         return value;
/*     */       case 20:
/* 181 */         value = Date();
/* 182 */         return value;
/*     */     } 
/* 185 */     this.jj_la1[2] = this.jj_gen;
/* 186 */     jj_consume_token(-1);
/* 187 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final List Array() throws ParseException {
/* 194 */     List list = new ArrayList();
/* 195 */     Object element = null;
/* 196 */     jj_consume_token(5);
/* 197 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 5:
/*     */       case 8:
/*     */       case 19:
/*     */       case 20:
/*     */       case 21:
/*     */       case 22:
/* 204 */         element = Element();
/* 205 */         list.add(element);
/*     */         while (true) {
/* 208 */           switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */             case 7:
/*     */               break;
/*     */             default:
/* 213 */               this.jj_la1[3] = this.jj_gen;
/*     */               break;
/*     */           } 
/* 216 */           jj_consume_token(7);
/* 217 */           element = Element();
/* 218 */           list.add(element);
/*     */         } 
/* 225 */         jj_consume_token(6);
/* 226 */         return list;
/*     */     } 
/*     */     this.jj_la1[4] = this.jj_gen;
/*     */     jj_consume_token(6);
/* 226 */     return list;
/*     */   }
/*     */   
/*     */   public final String String() throws ParseException {
/* 231 */     Token token = null;
/* 232 */     String value = null;
/* 233 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*     */       case 22:
/* 235 */         token = jj_consume_token(22);
/* 236 */         return unescapeQuotes(removeQuotes(token.image));
/*     */       case 21:
/* 239 */         token = jj_consume_token(21);
/* 240 */         return token.image;
/*     */     } 
/* 243 */     this.jj_la1[5] = this.jj_gen;
/* 244 */     jj_consume_token(-1);
/* 245 */     throw new ParseException();
/*     */   }
/*     */   
/*     */   public final byte[] Data() throws ParseException {
/* 252 */     Token token = jj_consume_token(19);
/* 253 */     return filterData(token.image);
/*     */   }
/*     */   
/*     */   public final Date Date() throws ParseException {
/* 259 */     Token token = jj_consume_token(20);
/* 260 */     return parseDate(token.image);
/*     */   }
/*     */   
/*     */   private final boolean jj_2_1(int xla) {
/* 265 */     this.jj_la = xla;
/* 265 */     this.jj_lastpos = this.jj_scanpos = this.token;
/*     */     try {
/* 266 */       return !jj_3_1();
/*     */     } catch (LookaheadSuccess ls) {
/* 267 */       return true;
/*     */     } finally {
/* 268 */       jj_save(0, xla);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_14() {
/* 272 */     if (jj_scan_token(22))
/* 272 */       return true; 
/* 273 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_11() {
/* 278 */     Token xsp = this.jj_scanpos;
/* 279 */     if (jj_3R_14()) {
/* 280 */       this.jj_scanpos = xsp;
/* 281 */       if (jj_3R_15())
/* 281 */         return true; 
/*     */     } 
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_13() {
/* 287 */     if (jj_scan_token(20))
/* 287 */       return true; 
/* 288 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_10() {
/* 292 */     if (jj_scan_token(8))
/* 292 */       return true; 
/* 293 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_9() {
/* 297 */     if (jj_3R_13())
/* 297 */       return true; 
/* 298 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_8() {
/* 302 */     if (jj_3R_12())
/* 302 */       return true; 
/* 303 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_12() {
/* 307 */     if (jj_scan_token(19))
/* 307 */       return true; 
/* 308 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_7() {
/* 312 */     if (jj_3R_11())
/* 312 */       return true; 
/* 313 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_4() {
/* 317 */     if (jj_3R_5())
/* 317 */       return true; 
/* 318 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_6() {
/* 322 */     if (jj_3R_10())
/* 322 */       return true; 
/* 323 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_15() {
/* 327 */     if (jj_scan_token(21))
/* 327 */       return true; 
/* 328 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_3() {
/* 332 */     if (jj_scan_token(5))
/* 332 */       return true; 
/* 334 */     Token xsp = this.jj_scanpos;
/* 335 */     if (jj_3R_4())
/* 335 */       this.jj_scanpos = xsp; 
/* 336 */     if (jj_scan_token(6))
/* 336 */       return true; 
/* 337 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3_1() {
/* 341 */     if (jj_3R_3())
/* 341 */       return true; 
/* 342 */     return false;
/*     */   }
/*     */   
/*     */   private final boolean jj_3R_5() {
/* 347 */     Token xsp = this.jj_scanpos;
/* 348 */     if (jj_3_1()) {
/* 349 */       this.jj_scanpos = xsp;
/* 350 */       if (jj_3R_6()) {
/* 351 */         this.jj_scanpos = xsp;
/* 352 */         if (jj_3R_7()) {
/* 353 */           this.jj_scanpos = xsp;
/* 354 */           if (jj_3R_8()) {
/* 355 */             this.jj_scanpos = xsp;
/* 356 */             if (jj_3R_9())
/* 356 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 361 */     return false;
/*     */   }
/*     */   
/*     */   public boolean lookingAhead = false;
/*     */   
/*     */   private boolean jj_semLA;
/*     */   
/*     */   private int jj_gen;
/*     */   
/* 373 */   private final int[] jj_la1 = new int[6];
/*     */   
/*     */   private static int[] jj_la1_0;
/*     */   
/*     */   static {
/* 376 */     jj_la1_0();
/*     */   }
/*     */   
/*     */   private static void jj_la1_0() {
/* 379 */     jj_la1_0 = new int[] { 6291456, 1024, 7864576, 128, 7864608, 6291456 };
/*     */   }
/*     */   
/* 381 */   private final JJCalls[] jj_2_rtns = new JJCalls[1];
/*     */   
/*     */   private boolean jj_rescan = false;
/*     */   
/* 383 */   private int jj_gc = 0;
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
/*     */   public void ReInit(InputStream stream) {
/* 396 */     this.jj_input_stream.ReInit(stream, 1, 1);
/* 397 */     this.token_source.ReInit(this.jj_input_stream);
/* 398 */     this.token = new Token();
/* 399 */     this.jj_ntk = -1;
/* 400 */     this.jj_gen = 0;
/*     */     int i;
/* 401 */     for (i = 0; i < 6; ) {
/* 401 */       this.jj_la1[i] = -1;
/* 401 */       i++;
/*     */     } 
/* 402 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 402 */       this.jj_2_rtns[i] = new JJCalls();
/* 402 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ReInit(Reader stream) {
/* 416 */     this.jj_input_stream.ReInit(stream, 1, 1);
/* 417 */     this.token_source.ReInit(this.jj_input_stream);
/* 418 */     this.token = new Token();
/* 419 */     this.jj_ntk = -1;
/* 420 */     this.jj_gen = 0;
/*     */     int i;
/* 421 */     for (i = 0; i < 6; ) {
/* 421 */       this.jj_la1[i] = -1;
/* 421 */       i++;
/*     */     } 
/* 422 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 422 */       this.jj_2_rtns[i] = new JJCalls();
/* 422 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ReInit(PropertyListParserTokenManager tm) {
/* 435 */     this.token_source = tm;
/* 436 */     this.token = new Token();
/* 437 */     this.jj_ntk = -1;
/* 438 */     this.jj_gen = 0;
/*     */     int i;
/* 439 */     for (i = 0; i < 6; ) {
/* 439 */       this.jj_la1[i] = -1;
/* 439 */       i++;
/*     */     } 
/* 440 */     for (i = 0; i < this.jj_2_rtns.length; ) {
/* 440 */       this.jj_2_rtns[i] = new JJCalls();
/* 440 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Token jj_consume_token(int kind) throws ParseException {
/*     */     Token oldToken;
/* 445 */     if ((oldToken = this.token).next != null) {
/* 445 */       this.token = this.token.next;
/*     */     } else {
/* 446 */       this.token = this.token.next = this.token_source.getNextToken();
/*     */     } 
/* 447 */     this.jj_ntk = -1;
/* 448 */     if (this.token.kind == kind) {
/* 449 */       this.jj_gen++;
/* 450 */       if (++this.jj_gc > 100) {
/* 451 */         this.jj_gc = 0;
/* 452 */         for (int i = 0; i < this.jj_2_rtns.length; i++) {
/* 453 */           JJCalls c = this.jj_2_rtns[i];
/* 454 */           while (c != null) {
/* 455 */             if (c.gen < this.jj_gen)
/* 455 */               c.first = null; 
/* 456 */             c = c.next;
/*     */           } 
/*     */         } 
/*     */       } 
/* 460 */       return this.token;
/*     */     } 
/* 462 */     this.token = oldToken;
/* 463 */     this.jj_kind = kind;
/* 464 */     throw generateParseException();
/*     */   }
/*     */   
/*     */   private static final class LookaheadSuccess extends Error {
/*     */     private LookaheadSuccess() {}
/*     */   }
/*     */   
/*     */   public PropertyListParser(InputStream stream) {
/* 468 */     this.jj_ls = new LookaheadSuccess();
/* 514 */     this.jj_expentries = new Vector();
/* 516 */     this.jj_kind = -1;
/* 517 */     this.jj_lasttokens = new int[100];
/*     */     this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
/*     */     this.token_source = new PropertyListParserTokenManager(this.jj_input_stream);
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 6; ) {
/*     */       this.jj_la1[i] = -1;
/*     */       i++;
/*     */     } 
/*     */     for (i = 0; i < this.jj_2_rtns.length; ) {
/*     */       this.jj_2_rtns[i] = new JJCalls();
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PropertyListParser(Reader stream) {
/*     */     this.jj_ls = new LookaheadSuccess();
/*     */     this.jj_expentries = new Vector();
/*     */     this.jj_kind = -1;
/* 517 */     this.jj_lasttokens = new int[100];
/*     */     this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
/*     */     this.token_source = new PropertyListParserTokenManager(this.jj_input_stream);
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 6; ) {
/*     */       this.jj_la1[i] = -1;
/*     */       i++;
/*     */     } 
/*     */     for (i = 0; i < this.jj_2_rtns.length; ) {
/*     */       this.jj_2_rtns[i] = new JJCalls();
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PropertyListParser(PropertyListParserTokenManager tm) {
/*     */     this.jj_ls = new LookaheadSuccess();
/*     */     this.jj_expentries = new Vector();
/*     */     this.jj_kind = -1;
/* 517 */     this.jj_lasttokens = new int[100];
/*     */     this.token_source = tm;
/*     */     this.token = new Token();
/*     */     this.jj_ntk = -1;
/*     */     this.jj_gen = 0;
/*     */     int i;
/*     */     for (i = 0; i < 6; ) {
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
/* 521 */     if (pos >= 100)
/*     */       return; 
/* 522 */     if (pos == this.jj_endpos + 1) {
/* 523 */       this.jj_lasttokens[this.jj_endpos++] = kind;
/* 524 */     } else if (this.jj_endpos != 0) {
/* 525 */       this.jj_expentry = new int[this.jj_endpos];
/* 526 */       for (int i = 0; i < this.jj_endpos; i++)
/* 527 */         this.jj_expentry[i] = this.jj_lasttokens[i]; 
/* 529 */       boolean exists = false;
/* 530 */       for (Enumeration e = this.jj_expentries.elements(); e.hasMoreElements(); ) {
/* 531 */         int[] oldentry = e.nextElement();
/* 532 */         if (oldentry.length == this.jj_expentry.length) {
/* 533 */           exists = true;
/* 534 */           for (int j = 0; j < this.jj_expentry.length; j++) {
/* 535 */             if (oldentry[j] != this.jj_expentry[j]) {
/* 536 */               exists = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 540 */           if (exists)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 543 */       if (!exists)
/* 543 */         this.jj_expentries.addElement(this.jj_expentry); 
/* 544 */       if (pos != 0)
/* 544 */         this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParseException generateParseException() {
/* 549 */     this.jj_expentries.removeAllElements();
/* 550 */     boolean[] la1tokens = new boolean[24];
/*     */     int i;
/* 551 */     for (i = 0; i < 24; i++)
/* 552 */       la1tokens[i] = false; 
/* 554 */     if (this.jj_kind >= 0) {
/* 555 */       la1tokens[this.jj_kind] = true;
/* 556 */       this.jj_kind = -1;
/*     */     } 
/* 558 */     for (i = 0; i < 6; i++) {
/* 559 */       if (this.jj_la1[i] == this.jj_gen)
/* 560 */         for (int k = 0; k < 32; k++) {
/* 561 */           if ((jj_la1_0[i] & 1 << k) != 0)
/* 562 */             la1tokens[k] = true; 
/*     */         }  
/*     */     } 
/* 567 */     for (i = 0; i < 24; i++) {
/* 568 */       if (la1tokens[i]) {
/* 569 */         this.jj_expentry = new int[1];
/* 570 */         this.jj_expentry[0] = i;
/* 571 */         this.jj_expentries.addElement(this.jj_expentry);
/*     */       } 
/*     */     } 
/* 574 */     this.jj_endpos = 0;
/* 575 */     jj_rescan_token();
/* 576 */     jj_add_error_token(0, 0);
/* 577 */     int[][] exptokseq = new int[this.jj_expentries.size()][];
/* 578 */     for (int j = 0; j < this.jj_expentries.size(); j++)
/* 579 */       exptokseq[j] = this.jj_expentries.elementAt(j); 
/* 581 */     return new ParseException(this.token, exptokseq, tokenImage);
/*     */   }
/*     */   
/*     */   public final void enable_tracing() {}
/*     */   
/*     */   public final void disable_tracing() {}
/*     */   
/*     */   private final void jj_rescan_token() {
/* 591 */     this.jj_rescan = true;
/* 592 */     for (int i = 0; i < 1; ) {
/* 593 */       JJCalls p = this.jj_2_rtns[i];
/*     */       while (true) {
/* 595 */         if (p.gen > this.jj_gen) {
/* 596 */           this.jj_la = p.arg;
/* 596 */           this.jj_lastpos = this.jj_scanpos = p.first;
/* 597 */           switch (i) {
/*     */             case 0:
/* 598 */               jj_3_1();
/*     */               break;
/*     */           } 
/*     */         } 
/* 601 */         p = p.next;
/* 602 */         if (p == null)
/*     */           i++; 
/*     */       } 
/*     */     } 
/* 604 */     this.jj_rescan = false;
/*     */   }
/*     */   
/*     */   private final void jj_save(int index, int xla) {
/* 608 */     JJCalls p = this.jj_2_rtns[index];
/* 609 */     while (p.gen > this.jj_gen) {
/* 610 */       if (p.next == null) {
/* 610 */         p = p.next = new JJCalls();
/*     */         break;
/*     */       } 
/* 611 */       p = p.next;
/*     */     } 
/* 613 */     p.gen = this.jj_gen + xla - this.jj_la;
/* 613 */     p.first = this.token;
/* 613 */     p.arg = xla;
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\plist\PropertyListParser.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */