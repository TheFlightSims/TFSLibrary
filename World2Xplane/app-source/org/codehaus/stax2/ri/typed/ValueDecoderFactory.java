/*      */ package org.codehaus.stax2.ri.typed;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import javax.xml.namespace.NamespaceContext;
/*      */ import javax.xml.namespace.QName;
/*      */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*      */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*      */ 
/*      */ public final class ValueDecoderFactory {
/*   46 */   protected BooleanDecoder mBooleanDecoder = null;
/*      */   
/*   47 */   protected IntDecoder mIntDecoder = null;
/*      */   
/*   48 */   protected LongDecoder mLongDecoder = null;
/*      */   
/*   49 */   protected FloatDecoder mFloatDecoder = null;
/*      */   
/*   50 */   protected DoubleDecoder mDoubleDecoder = null;
/*      */   
/*      */   public BooleanDecoder getBooleanDecoder() {
/*   62 */     if (this.mBooleanDecoder == null)
/*   63 */       this.mBooleanDecoder = new BooleanDecoder(); 
/*   65 */     return this.mBooleanDecoder;
/*      */   }
/*      */   
/*      */   public IntDecoder getIntDecoder() {
/*   70 */     if (this.mIntDecoder == null)
/*   71 */       this.mIntDecoder = new IntDecoder(); 
/*   73 */     return this.mIntDecoder;
/*      */   }
/*      */   
/*      */   public LongDecoder getLongDecoder() {
/*   78 */     if (this.mLongDecoder == null)
/*   79 */       this.mLongDecoder = new LongDecoder(); 
/*   81 */     return this.mLongDecoder;
/*      */   }
/*      */   
/*      */   public FloatDecoder getFloatDecoder() {
/*   86 */     if (this.mFloatDecoder == null)
/*   87 */       this.mFloatDecoder = new FloatDecoder(); 
/*   89 */     return this.mFloatDecoder;
/*      */   }
/*      */   
/*      */   public DoubleDecoder getDoubleDecoder() {
/*   94 */     if (this.mDoubleDecoder == null)
/*   95 */       this.mDoubleDecoder = new DoubleDecoder(); 
/*   97 */     return this.mDoubleDecoder;
/*      */   }
/*      */   
/*      */   public IntegerDecoder getIntegerDecoder() {
/*  102 */     return new IntegerDecoder();
/*      */   }
/*      */   
/*      */   public DecimalDecoder getDecimalDecoder() {
/*  104 */     return new DecimalDecoder();
/*      */   }
/*      */   
/*      */   public QNameDecoder getQNameDecoder(NamespaceContext nsc) {
/*  107 */     return new QNameDecoder(nsc);
/*      */   }
/*      */   
/*      */   public IntArrayDecoder getIntArrayDecoder(int[] result, int offset, int len) {
/*  123 */     return new IntArrayDecoder(result, offset, len, getIntDecoder());
/*      */   }
/*      */   
/*      */   public IntArrayDecoder getIntArrayDecoder() {
/*  133 */     return new IntArrayDecoder(getIntDecoder());
/*      */   }
/*      */   
/*      */   public LongArrayDecoder getLongArrayDecoder(long[] result, int offset, int len) {
/*  138 */     return new LongArrayDecoder(result, offset, len, getLongDecoder());
/*      */   }
/*      */   
/*      */   public LongArrayDecoder getLongArrayDecoder() {
/*  144 */     return new LongArrayDecoder(getLongDecoder());
/*      */   }
/*      */   
/*      */   public FloatArrayDecoder getFloatArrayDecoder(float[] result, int offset, int len) {
/*  149 */     return new FloatArrayDecoder(result, offset, len, getFloatDecoder());
/*      */   }
/*      */   
/*      */   public FloatArrayDecoder getFloatArrayDecoder() {
/*  155 */     return new FloatArrayDecoder(getFloatDecoder());
/*      */   }
/*      */   
/*      */   public DoubleArrayDecoder getDoubleArrayDecoder(double[] result, int offset, int len) {
/*  160 */     return new DoubleArrayDecoder(result, offset, len, getDoubleDecoder());
/*      */   }
/*      */   
/*      */   public DoubleArrayDecoder getDoubleArrayDecoder() {
/*  165 */     return new DoubleArrayDecoder(getDoubleDecoder());
/*      */   }
/*      */   
/*      */   public static abstract class DecoderBase extends TypedValueDecoder {
/*      */     static final long L_BILLION = 1000000000L;
/*      */     
/*      */     static final long L_MAX_INT = 2147483647L;
/*      */     
/*      */     static final long L_MIN_INT = -2147483648L;
/*      */     
/*  187 */     static final BigInteger BD_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
/*      */     
/*  188 */     static final BigInteger BD_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
/*      */     
/*      */     protected int mNextPtr;
/*      */     
/*      */     public abstract String getType();
/*      */     
/*      */     public void handleEmptyValue() {
/*  209 */       throw new IllegalArgumentException("Empty value (all white space) not a valid lexical representation of " + getType());
/*      */     }
/*      */     
/*      */     protected void verifyDigits(String lexical, int start, int end) {
/*  224 */       for (; start < end; start++) {
/*  225 */         char ch = lexical.charAt(start);
/*  226 */         if (ch > '9' || ch < '0')
/*  227 */           throw constructInvalidValue(lexical); 
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void verifyDigits(char[] lexical, int start, int end, int ptr) {
/*  234 */       for (; ptr < end; ptr++) {
/*  235 */         char ch = lexical[ptr];
/*  236 */         if (ch > '9' || ch < '0')
/*  237 */           throw constructInvalidValue(lexical, start, end); 
/*      */       } 
/*      */     }
/*      */     
/*      */     protected int skipSignAndZeroes(String lexical, char ch, boolean hasSign, int end) {
/*      */       int ptr;
/*  250 */       if (hasSign) {
/*  251 */         ptr = 1;
/*  252 */         if (ptr >= end)
/*  253 */           throw constructInvalidValue(lexical); 
/*  255 */         ch = lexical.charAt(ptr++);
/*      */       } else {
/*  257 */         ptr = 1;
/*      */       } 
/*  261 */       int value = ch - 48;
/*  262 */       if (value < 0 || value > 9)
/*  263 */         throw constructInvalidValue(lexical); 
/*  267 */       while (value == 0 && ptr < end) {
/*  268 */         int v2 = lexical.charAt(ptr) - 48;
/*  269 */         if (v2 < 0 || v2 > 9)
/*      */           break; 
/*  272 */         ptr++;
/*  273 */         value = v2;
/*      */       } 
/*  275 */       this.mNextPtr = ptr;
/*  276 */       return value;
/*      */     }
/*      */     
/*      */     protected int skipSignAndZeroes(char[] lexical, char ch, boolean hasSign, int start, int end) {
/*  281 */       int ptr = start + 1;
/*  282 */       if (hasSign) {
/*  283 */         if (ptr >= end)
/*  284 */           throw constructInvalidValue(lexical, start, end); 
/*  286 */         ch = lexical[ptr++];
/*      */       } 
/*  290 */       int value = ch - 48;
/*  291 */       if (value < 0 || value > 9)
/*  292 */         throw constructInvalidValue(lexical, start, end); 
/*  296 */       while (value == 0 && ptr < end) {
/*  297 */         int v2 = lexical[ptr] - 48;
/*  298 */         if (v2 < 0 || v2 > 9)
/*      */           break; 
/*  301 */         ptr++;
/*  302 */         value = v2;
/*      */       } 
/*  304 */       this.mNextPtr = ptr;
/*  305 */       return value;
/*      */     }
/*      */     
/*      */     protected static final int parseInt(char[] digitChars, int start, int end) {
/*  326 */       int num = digitChars[start] - 48;
/*  327 */       if (++start < end) {
/*  328 */         num = num * 10 + digitChars[start] - 48;
/*  329 */         if (++start < end) {
/*  330 */           num = num * 10 + digitChars[start] - 48;
/*  331 */           if (++start < end) {
/*  332 */             num = num * 10 + digitChars[start] - 48;
/*  333 */             if (++start < end) {
/*  334 */               num = num * 10 + digitChars[start] - 48;
/*  335 */               if (++start < end) {
/*  336 */                 num = num * 10 + digitChars[start] - 48;
/*  337 */                 if (++start < end) {
/*  338 */                   num = num * 10 + digitChars[start] - 48;
/*  339 */                   if (++start < end) {
/*  340 */                     num = num * 10 + digitChars[start] - 48;
/*  341 */                     if (++start < end)
/*  342 */                       num = num * 10 + digitChars[start] - 48; 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  351 */       return num;
/*      */     }
/*      */     
/*      */     protected static final int parseInt(int num, char[] digitChars, int start, int end) {
/*  356 */       num = num * 10 + digitChars[start] - 48;
/*  357 */       if (++start < end) {
/*  358 */         num = num * 10 + digitChars[start] - 48;
/*  359 */         if (++start < end) {
/*  360 */           num = num * 10 + digitChars[start] - 48;
/*  361 */           if (++start < end) {
/*  362 */             num = num * 10 + digitChars[start] - 48;
/*  363 */             if (++start < end) {
/*  364 */               num = num * 10 + digitChars[start] - 48;
/*  365 */               if (++start < end) {
/*  366 */                 num = num * 10 + digitChars[start] - 48;
/*  367 */                 if (++start < end) {
/*  368 */                   num = num * 10 + digitChars[start] - 48;
/*  369 */                   if (++start < end)
/*  370 */                     num = num * 10 + digitChars[start] - 48; 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  378 */       return num;
/*      */     }
/*      */     
/*      */     protected static final int parseInt(String digitChars, int start, int end) {
/*  383 */       int num = digitChars.charAt(start) - 48;
/*  384 */       if (++start < end) {
/*  385 */         num = num * 10 + digitChars.charAt(start) - 48;
/*  386 */         if (++start < end) {
/*  387 */           num = num * 10 + digitChars.charAt(start) - 48;
/*  388 */           if (++start < end) {
/*  389 */             num = num * 10 + digitChars.charAt(start) - 48;
/*  390 */             if (++start < end) {
/*  391 */               num = num * 10 + digitChars.charAt(start) - 48;
/*  392 */               if (++start < end) {
/*  393 */                 num = num * 10 + digitChars.charAt(start) - 48;
/*  394 */                 if (++start < end) {
/*  395 */                   num = num * 10 + digitChars.charAt(start) - 48;
/*  396 */                   if (++start < end) {
/*  397 */                     num = num * 10 + digitChars.charAt(start) - 48;
/*  398 */                     if (++start < end)
/*  399 */                       num = num * 10 + digitChars.charAt(start) - 48; 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  408 */       return num;
/*      */     }
/*      */     
/*      */     protected static final int parseInt(int num, String digitChars, int start, int end) {
/*  413 */       num = num * 10 + digitChars.charAt(start) - 48;
/*  414 */       if (++start < end) {
/*  415 */         num = num * 10 + digitChars.charAt(start) - 48;
/*  416 */         if (++start < end) {
/*  417 */           num = num * 10 + digitChars.charAt(start) - 48;
/*  418 */           if (++start < end) {
/*  419 */             num = num * 10 + digitChars.charAt(start) - 48;
/*  420 */             if (++start < end) {
/*  421 */               num = num * 10 + digitChars.charAt(start) - 48;
/*  422 */               if (++start < end) {
/*  423 */                 num = num * 10 + digitChars.charAt(start) - 48;
/*  424 */                 if (++start < end) {
/*  425 */                   num = num * 10 + digitChars.charAt(start) - 48;
/*  426 */                   if (++start < end)
/*  427 */                     num = num * 10 + digitChars.charAt(start) - 48; 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  435 */       return num;
/*      */     }
/*      */     
/*      */     protected static final long parseLong(char[] digitChars, int start, int end) {
/*  441 */       int start2 = end - 9;
/*  442 */       long val = parseInt(digitChars, start, start2) * 1000000000L;
/*  443 */       return val + parseInt(digitChars, start2, end);
/*      */     }
/*      */     
/*      */     protected static final long parseLong(String digitChars, int start, int end) {
/*  449 */       int start2 = end - 9;
/*  450 */       long val = parseInt(digitChars, start, start2) * 1000000000L;
/*  451 */       return val + parseInt(digitChars, start2, end);
/*      */     }
/*      */     
/*      */     protected IllegalArgumentException constructInvalidValue(String lexical) {
/*  463 */       return new IllegalArgumentException("Value \"" + lexical + "\" not a valid lexical representation of " + getType());
/*      */     }
/*      */     
/*      */     protected IllegalArgumentException constructInvalidValue(char[] lexical, int startOffset, int end) {
/*  468 */       return new IllegalArgumentException("Value \"" + lexicalDesc(lexical, startOffset, end) + "\" not a valid lexical representation of " + getType());
/*      */     }
/*      */     
/*      */     protected String lexicalDesc(char[] lexical, int startOffset, int end) {
/*  473 */       return _clean(new String(lexical, startOffset, end - startOffset));
/*      */     }
/*      */     
/*      */     protected String lexicalDesc(String lexical) {
/*  478 */       return _clean(lexical);
/*      */     }
/*      */     
/*      */     protected String _clean(String str) {
/*  484 */       return str.trim();
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class BooleanDecoder extends DecoderBase {
/*      */     protected boolean mValue;
/*      */     
/*      */     public String getType() {
/*  501 */       return "boolean";
/*      */     }
/*      */     
/*      */     public boolean getValue() {
/*  503 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/*  508 */       int len = lexical.length();
/*  509 */       char c = lexical.charAt(0);
/*  510 */       if (c == 't') {
/*  511 */         if (len == 4 && lexical.charAt(1) == 'r' && lexical.charAt(2) == 'u' && lexical.charAt(3) == 'e') {
/*  515 */           this.mValue = true;
/*      */           return;
/*      */         } 
/*  518 */       } else if (c == 'f') {
/*  519 */         if (len == 5 && lexical.charAt(1) == 'a' && lexical.charAt(2) == 'l' && lexical.charAt(3) == 's' && lexical.charAt(4) == 'e') {
/*  524 */           this.mValue = false;
/*      */           return;
/*      */         } 
/*  527 */       } else if (c == '0') {
/*  528 */         if (len == 1) {
/*  529 */           this.mValue = false;
/*      */           return;
/*      */         } 
/*  532 */       } else if (c == '1' && 
/*  533 */         len == 1) {
/*  534 */         this.mValue = true;
/*      */         return;
/*      */       } 
/*  538 */       throw constructInvalidValue(lexical);
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/*  545 */       int len = end - start;
/*  546 */       char c = lexical[start];
/*  547 */       if (c == 't') {
/*  548 */         if (len == 4 && lexical[start + 1] == 'r' && lexical[start + 2] == 'u' && lexical[start + 3] == 'e') {
/*  552 */           this.mValue = true;
/*      */           return;
/*      */         } 
/*  555 */       } else if (c == 'f') {
/*  556 */         if (len == 5 && lexical[start + 1] == 'a' && lexical[start + 2] == 'l' && lexical[start + 3] == 's' && lexical[start + 4] == 'e') {
/*  561 */           this.mValue = false;
/*      */           return;
/*      */         } 
/*  564 */       } else if (c == '0') {
/*  565 */         if (len == 1) {
/*  566 */           this.mValue = false;
/*      */           return;
/*      */         } 
/*  569 */       } else if (c == '1' && 
/*  570 */         len == 1) {
/*  571 */         this.mValue = true;
/*      */         return;
/*      */       } 
/*  575 */       throw constructInvalidValue(lexical, start, end);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class IntDecoder extends DecoderBase {
/*      */     protected int mValue;
/*      */     
/*      */     public String getType() {
/*  586 */       return "int";
/*      */     }
/*      */     
/*      */     public int getValue() {
/*  588 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/*  593 */       int nr, end = lexical.length();
/*  594 */       char ch = lexical.charAt(0);
/*  595 */       boolean neg = (ch == '-');
/*  598 */       if (neg || ch == '+') {
/*  599 */         nr = skipSignAndZeroes(lexical, ch, true, end);
/*      */       } else {
/*  601 */         nr = skipSignAndZeroes(lexical, ch, false, end);
/*      */       } 
/*  603 */       int ptr = this.mNextPtr;
/*  606 */       int charsLeft = end - ptr;
/*  607 */       if (charsLeft == 0) {
/*  608 */         this.mValue = neg ? -nr : nr;
/*      */         return;
/*      */       } 
/*  611 */       verifyDigits(lexical, ptr, end);
/*  613 */       if (charsLeft <= 8) {
/*  614 */         int i = parseInt(nr, lexical, ptr, ptr + charsLeft);
/*  615 */         this.mValue = neg ? -i : i;
/*      */         return;
/*      */       } 
/*  620 */       if (charsLeft == 9 && nr < 3) {
/*  621 */         long base = 1000000000L;
/*  622 */         if (nr == 2)
/*  623 */           base += 1000000000L; 
/*  625 */         int i = parseInt(lexical, ptr, ptr + charsLeft);
/*  626 */         long l = base + i;
/*  627 */         if (neg) {
/*  628 */           l = -l;
/*  629 */           if (l >= -2147483648L) {
/*  630 */             this.mValue = (int)l;
/*      */             return;
/*      */           } 
/*  634 */         } else if (l <= 2147483647L) {
/*  635 */           this.mValue = (int)l;
/*      */           return;
/*      */         } 
/*      */       } 
/*  640 */       throw new IllegalArgumentException("value \"" + lexicalDesc(lexical) + "\" not a valid 32-bit integer: overflow.");
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/*      */       int nr;
/*  647 */       char ch = lexical[start];
/*  648 */       boolean neg = (ch == '-');
/*  651 */       if (neg || ch == '+') {
/*  652 */         nr = skipSignAndZeroes(lexical, ch, true, start, end);
/*      */       } else {
/*  654 */         nr = skipSignAndZeroes(lexical, ch, false, start, end);
/*      */       } 
/*  656 */       int ptr = this.mNextPtr;
/*  659 */       int charsLeft = end - ptr;
/*  660 */       if (charsLeft == 0) {
/*  661 */         this.mValue = neg ? -nr : nr;
/*      */         return;
/*      */       } 
/*  664 */       verifyDigits(lexical, start, end, ptr);
/*  667 */       if (charsLeft <= 8) {
/*  668 */         int i = parseInt(nr, lexical, ptr, ptr + charsLeft);
/*  669 */         this.mValue = neg ? -i : i;
/*      */         return;
/*      */       } 
/*  674 */       if (charsLeft == 9 && nr < 3) {
/*  675 */         long base = 1000000000L;
/*  676 */         if (nr == 2)
/*  677 */           base += 1000000000L; 
/*  679 */         int i = parseInt(lexical, ptr, ptr + charsLeft);
/*  680 */         long l = base + i;
/*  681 */         if (neg) {
/*  682 */           l = -l;
/*  683 */           if (l >= -2147483648L) {
/*  684 */             this.mValue = (int)l;
/*      */             return;
/*      */           } 
/*  688 */         } else if (l <= 2147483647L) {
/*  689 */           this.mValue = (int)l;
/*      */           return;
/*      */         } 
/*      */       } 
/*  694 */       throw new IllegalArgumentException("value \"" + lexicalDesc(lexical, start, end) + "\" not a valid 32-bit integer: overflow.");
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class LongDecoder extends DecoderBase {
/*      */     protected long mValue;
/*      */     
/*      */     public String getType() {
/*  705 */       return "long";
/*      */     }
/*      */     
/*      */     public long getValue() {
/*  707 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/*  712 */       int nr, end = lexical.length();
/*  713 */       char ch = lexical.charAt(0);
/*  714 */       boolean neg = (ch == '-');
/*  717 */       if (neg || ch == '+') {
/*  718 */         nr = skipSignAndZeroes(lexical, ch, true, end);
/*      */       } else {
/*  720 */         nr = skipSignAndZeroes(lexical, ch, false, end);
/*      */       } 
/*  722 */       int ptr = this.mNextPtr;
/*  725 */       int charsLeft = end - ptr;
/*  726 */       if (charsLeft == 0) {
/*  727 */         this.mValue = (neg ? -nr : nr);
/*      */         return;
/*      */       } 
/*  730 */       verifyDigits(lexical, ptr, end);
/*  733 */       if (charsLeft <= 8) {
/*  734 */         int i = parseInt(nr, lexical, ptr, ptr + charsLeft);
/*  735 */         this.mValue = (neg ? -i : i);
/*      */         return;
/*      */       } 
/*  739 */       ptr--;
/*  740 */       charsLeft++;
/*  743 */       if (charsLeft <= 18) {
/*  744 */         long l = parseLong(lexical, ptr, ptr + charsLeft);
/*  745 */         this.mValue = neg ? -l : l;
/*      */         return;
/*      */       } 
/*  752 */       this.mValue = parseUsingBD(lexical.substring(ptr, ptr + charsLeft), neg);
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/*      */       int nr;
/*  758 */       char ch = lexical[start];
/*  759 */       boolean neg = (ch == '-');
/*  762 */       if (neg || ch == '+') {
/*  763 */         nr = skipSignAndZeroes(lexical, ch, true, start, end);
/*      */       } else {
/*  765 */         nr = skipSignAndZeroes(lexical, ch, false, start, end);
/*      */       } 
/*  767 */       int ptr = this.mNextPtr;
/*  770 */       int charsLeft = end - ptr;
/*  771 */       if (charsLeft == 0) {
/*  772 */         this.mValue = (neg ? -nr : nr);
/*      */         return;
/*      */       } 
/*  775 */       verifyDigits(lexical, start, end, ptr);
/*  778 */       if (charsLeft <= 8) {
/*  779 */         int i = parseInt(nr, lexical, ptr, ptr + charsLeft);
/*  780 */         this.mValue = neg ? -i : i;
/*      */         return;
/*      */       } 
/*  784 */       ptr--;
/*  785 */       charsLeft++;
/*  788 */       if (charsLeft <= 18) {
/*  789 */         long l = parseLong(lexical, ptr, ptr + charsLeft);
/*  790 */         this.mValue = neg ? -l : l;
/*      */         return;
/*      */       } 
/*  795 */       this.mValue = parseUsingBD(new String(lexical, ptr, charsLeft), neg);
/*      */     }
/*      */     
/*      */     private long parseUsingBD(String lexical, boolean neg) {
/*  800 */       BigInteger bi = new BigInteger(lexical);
/*  803 */       if (neg) {
/*  804 */         bi = bi.negate();
/*  805 */         if (bi.compareTo(BD_MIN_LONG) >= 0)
/*  806 */           return bi.longValue(); 
/*  809 */       } else if (bi.compareTo(BD_MAX_LONG) <= 0) {
/*  810 */         return bi.longValue();
/*      */       } 
/*  814 */       throw new IllegalArgumentException("value \"" + lexicalDesc(lexical) + "\" not a valid long: overflow.");
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class FloatDecoder extends DecoderBase {
/*      */     protected float mValue;
/*      */     
/*      */     public String getType() {
/*  825 */       return "float";
/*      */     }
/*      */     
/*      */     public float getValue() {
/*  827 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/*  835 */       int len = lexical.length();
/*  836 */       if (len == 3) {
/*  837 */         char c = lexical.charAt(0);
/*  838 */         if (c == 'I') {
/*  839 */           if (lexical.charAt(1) == 'N' && lexical.charAt(2) == 'F') {
/*  840 */             this.mValue = Float.POSITIVE_INFINITY;
/*      */             return;
/*      */           } 
/*  843 */         } else if (c == 'N' && 
/*  844 */           lexical.charAt(1) == 'a' && lexical.charAt(2) == 'N') {
/*  845 */           this.mValue = Float.NaN;
/*      */           return;
/*      */         } 
/*  849 */       } else if (len == 4) {
/*  850 */         char c = lexical.charAt(0);
/*  851 */         if (c == '-' && 
/*  852 */           lexical.charAt(1) == 'I' && lexical.charAt(2) == 'N' && lexical.charAt(3) == 'F') {
/*  855 */           this.mValue = Float.NEGATIVE_INFINITY;
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       try {
/*  862 */         this.mValue = Float.parseFloat(lexical);
/*  863 */       } catch (NumberFormatException nex) {
/*  864 */         throw constructInvalidValue(lexical);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/*  871 */       int len = end - start;
/*  873 */       if (len == 3) {
/*  874 */         char c = lexical[start];
/*  875 */         if (c == 'I') {
/*  876 */           if (lexical[start + 1] == 'N' && lexical[start + 2] == 'F') {
/*  877 */             this.mValue = Float.POSITIVE_INFINITY;
/*      */             return;
/*      */           } 
/*  880 */         } else if (c == 'N' && 
/*  881 */           lexical[start + 1] == 'a' && lexical[start + 2] == 'N') {
/*  882 */           this.mValue = Float.NaN;
/*      */           return;
/*      */         } 
/*  886 */       } else if (len == 4) {
/*  887 */         char c = lexical[start];
/*  888 */         if (c == '-' && 
/*  889 */           lexical[start + 1] == 'I' && lexical[start + 2] == 'N' && lexical[start + 3] == 'F') {
/*  892 */           this.mValue = Float.NEGATIVE_INFINITY;
/*      */           return;
/*      */         } 
/*      */       } 
/*  898 */       String lexicalStr = new String(lexical, start, len);
/*      */       try {
/*  900 */         this.mValue = Float.parseFloat(lexicalStr);
/*  901 */       } catch (NumberFormatException nex) {
/*  902 */         throw constructInvalidValue(lexicalStr);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class DoubleDecoder extends DecoderBase {
/*      */     protected double mValue;
/*      */     
/*      */     public String getType() {
/*  914 */       return "double";
/*      */     }
/*      */     
/*      */     public double getValue() {
/*  916 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/*  924 */       int len = lexical.length();
/*  925 */       if (len == 3) {
/*  926 */         char c = lexical.charAt(0);
/*  927 */         if (c == 'I') {
/*  928 */           if (lexical.charAt(1) == 'N' && lexical.charAt(2) == 'F') {
/*  929 */             this.mValue = Double.POSITIVE_INFINITY;
/*      */             return;
/*      */           } 
/*  932 */         } else if (c == 'N' && 
/*  933 */           lexical.charAt(1) == 'a' && lexical.charAt(2) == 'N') {
/*  934 */           this.mValue = Double.NaN;
/*      */           return;
/*      */         } 
/*  938 */       } else if (len == 4) {
/*  939 */         char c = lexical.charAt(0);
/*  940 */         if (c == '-' && 
/*  941 */           lexical.charAt(1) == 'I' && lexical.charAt(2) == 'N' && lexical.charAt(3) == 'F') {
/*  944 */           this.mValue = Double.NEGATIVE_INFINITY;
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       try {
/*  951 */         this.mValue = Double.parseDouble(lexical);
/*  952 */       } catch (NumberFormatException nex) {
/*  953 */         throw constructInvalidValue(lexical);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/*  960 */       int len = end - start;
/*  962 */       if (len == 3) {
/*  963 */         char c = lexical[start];
/*  964 */         if (c == 'I') {
/*  965 */           if (lexical[start + 1] == 'N' && lexical[start + 2] == 'F') {
/*  966 */             this.mValue = Double.POSITIVE_INFINITY;
/*      */             return;
/*      */           } 
/*  969 */         } else if (c == 'N' && 
/*  970 */           lexical[start + 1] == 'a' && lexical[start + 2] == 'N') {
/*  971 */           this.mValue = Double.NaN;
/*      */           return;
/*      */         } 
/*  975 */       } else if (len == 4) {
/*  976 */         char c = lexical[start];
/*  977 */         if (c == '-' && 
/*  978 */           lexical[start + 1] == 'I' && lexical[start + 2] == 'N' && lexical[start + 3] == 'F') {
/*  981 */           this.mValue = Double.NEGATIVE_INFINITY;
/*      */           return;
/*      */         } 
/*      */       } 
/*  987 */       String lexicalStr = new String(lexical, start, len);
/*      */       try {
/*  989 */         this.mValue = Double.parseDouble(lexicalStr);
/*  990 */       } catch (NumberFormatException nex) {
/*  991 */         throw constructInvalidValue(lexicalStr);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class IntegerDecoder extends DecoderBase {
/*      */     protected BigInteger mValue;
/*      */     
/*      */     public String getType() {
/* 1009 */       return "integer";
/*      */     }
/*      */     
/*      */     public BigInteger getValue() {
/* 1011 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/*      */       try {
/* 1016 */         this.mValue = new BigInteger(lexical);
/* 1017 */       } catch (NumberFormatException nex) {
/* 1018 */         throw constructInvalidValue(lexical);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/* 1024 */       String lexicalStr = new String(lexical, start, end - start);
/*      */       try {
/* 1026 */         this.mValue = new BigInteger(lexicalStr);
/* 1027 */       } catch (NumberFormatException nex) {
/* 1028 */         throw constructInvalidValue(lexicalStr);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class DecimalDecoder extends DecoderBase {
/*      */     protected BigDecimal mValue;
/*      */     
/*      */     public String getType() {
/* 1040 */       return "decimal";
/*      */     }
/*      */     
/*      */     public BigDecimal getValue() {
/* 1042 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/*      */       try {
/* 1047 */         this.mValue = new BigDecimal(lexical);
/* 1048 */       } catch (NumberFormatException nex) {
/* 1049 */         throw constructInvalidValue(lexical);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/* 1055 */       int len = end - start;
/*      */       try {
/* 1062 */         this.mValue = new BigDecimal(new String(lexical, start, len));
/* 1063 */       } catch (NumberFormatException nex) {
/* 1064 */         throw constructInvalidValue(new String(lexical, start, len));
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class QNameDecoder extends DecoderBase {
/*      */     final NamespaceContext mNsCtxt;
/*      */     
/*      */     protected QName mValue;
/*      */     
/*      */     public QNameDecoder(NamespaceContext nsc) {
/* 1077 */       this.mNsCtxt = nsc;
/*      */     }
/*      */     
/*      */     public String getType() {
/* 1080 */       return "QName";
/*      */     }
/*      */     
/*      */     public QName getValue() {
/* 1082 */       return this.mValue;
/*      */     }
/*      */     
/*      */     public void decode(String lexical) throws IllegalArgumentException {
/* 1086 */       int ix = lexical.indexOf(':');
/* 1087 */       if (ix >= 0) {
/* 1088 */         this.mValue = resolveQName(lexical.substring(0, ix), lexical.substring(ix + 1));
/*      */       } else {
/* 1091 */         this.mValue = resolveQName(lexical);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void decode(char[] lexical, int start, int end) throws IllegalArgumentException {
/* 1097 */       int i = start;
/* 1098 */       for (; i < end; i++) {
/* 1099 */         if (lexical[i] == ':') {
/* 1100 */           this.mValue = resolveQName(new String(lexical, start, i - start), new String(lexical, i + 1, end - i - 1));
/*      */           return;
/*      */         } 
/*      */       } 
/* 1105 */       this.mValue = resolveQName(new String(lexical, start, end - start));
/*      */     }
/*      */     
/*      */     protected QName resolveQName(String localName) throws IllegalArgumentException {
/* 1111 */       String uri = this.mNsCtxt.getNamespaceURI("");
/* 1112 */       if (uri == null)
/* 1113 */         uri = ""; 
/* 1115 */       return new QName(uri, localName);
/*      */     }
/*      */     
/*      */     protected QName resolveQName(String prefix, String localName) throws IllegalArgumentException {
/* 1121 */       if (prefix.length() == 0 || localName.length() == 0)
/* 1123 */         throw constructInvalidValue(prefix + ":" + localName); 
/* 1129 */       String uri = this.mNsCtxt.getNamespaceURI(prefix);
/* 1130 */       if (uri == null || uri.length() == 0)
/* 1131 */         throw new IllegalArgumentException("Value \"" + lexicalDesc(prefix + ":" + localName) + "\" not a valid QName: prefix '" + prefix + "' not bound to a namespace"); 
/* 1133 */       return new QName(uri, localName, prefix);
/*      */     }
/*      */   }
/*      */   
/*      */   public static abstract class BaseArrayDecoder extends TypedArrayDecoder {
/*      */     protected static final int INITIAL_RESULT_BUFFER_SIZE = 40;
/*      */     
/*      */     protected static final int SMALL_RESULT_BUFFER_SIZE = 4000;
/*      */     
/*      */     protected int mStart;
/*      */     
/*      */     protected int mEnd;
/*      */     
/* 1169 */     protected int mCount = 0;
/*      */     
/*      */     protected BaseArrayDecoder(int start, int maxCount) {
/* 1173 */       this.mStart = start;
/* 1175 */       if (maxCount < 1)
/* 1176 */         throw new IllegalArgumentException("Number of elements to read can not be less than 1"); 
/* 1178 */       this.mEnd = maxCount;
/*      */     }
/*      */     
/*      */     public final int getCount() {
/* 1181 */       return this.mCount;
/*      */     }
/*      */     
/*      */     public final boolean hasRoom() {
/* 1182 */       return (this.mCount < this.mEnd);
/*      */     }
/*      */     
/*      */     public abstract void expand();
/*      */     
/*      */     protected int calcNewSize(int currSize) {
/* 1193 */       if (currSize < 4000)
/* 1194 */         return currSize << 2; 
/* 1196 */       return currSize + currSize;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class IntArrayDecoder extends BaseArrayDecoder {
/*      */     int[] mResult;
/*      */     
/*      */     final ValueDecoderFactory.IntDecoder mDecoder;
/*      */     
/*      */     public IntArrayDecoder(int[] result, int start, int maxCount, ValueDecoderFactory.IntDecoder intDecoder) {
/* 1214 */       super(start, maxCount);
/* 1215 */       this.mResult = result;
/* 1216 */       this.mDecoder = intDecoder;
/*      */     }
/*      */     
/*      */     public IntArrayDecoder(ValueDecoderFactory.IntDecoder intDecoder) {
/* 1225 */       super(0, 40);
/* 1226 */       this.mResult = new int[40];
/* 1227 */       this.mDecoder = intDecoder;
/*      */     }
/*      */     
/*      */     public void expand() {
/* 1232 */       int[] old = this.mResult;
/* 1233 */       int oldLen = old.length;
/* 1234 */       int newSize = calcNewSize(oldLen);
/* 1235 */       this.mResult = new int[newSize];
/* 1236 */       System.arraycopy(old, this.mStart, this.mResult, 0, oldLen);
/* 1237 */       this.mStart = 0;
/* 1238 */       this.mEnd = newSize;
/*      */     }
/*      */     
/*      */     public int[] getValues() {
/* 1243 */       int[] result = new int[this.mCount];
/* 1245 */       System.arraycopy(this.mResult, this.mStart, result, 0, this.mCount);
/* 1246 */       return result;
/*      */     }
/*      */     
/*      */     public boolean decodeValue(String input) throws IllegalArgumentException {
/* 1251 */       this.mDecoder.decode(input);
/* 1252 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1253 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */     
/*      */     public boolean decodeValue(char[] buffer, int start, int end) throws IllegalArgumentException {
/* 1258 */       this.mDecoder.decode(buffer, start, end);
/* 1259 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1260 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class LongArrayDecoder extends BaseArrayDecoder {
/*      */     long[] mResult;
/*      */     
/*      */     final ValueDecoderFactory.LongDecoder mDecoder;
/*      */     
/*      */     public LongArrayDecoder(long[] result, int start, int maxCount, ValueDecoderFactory.LongDecoder longDecoder) {
/* 1275 */       super(start, maxCount);
/* 1276 */       this.mResult = result;
/* 1277 */       this.mDecoder = longDecoder;
/*      */     }
/*      */     
/*      */     public LongArrayDecoder(ValueDecoderFactory.LongDecoder longDecoder) {
/* 1282 */       super(0, 40);
/* 1283 */       this.mResult = new long[40];
/* 1284 */       this.mDecoder = longDecoder;
/*      */     }
/*      */     
/*      */     public void expand() {
/* 1289 */       long[] old = this.mResult;
/* 1290 */       int oldLen = old.length;
/* 1291 */       int newSize = calcNewSize(oldLen);
/* 1292 */       this.mResult = new long[newSize];
/* 1293 */       System.arraycopy(old, this.mStart, this.mResult, 0, oldLen);
/* 1294 */       this.mStart = 0;
/* 1295 */       this.mEnd = newSize;
/*      */     }
/*      */     
/*      */     public long[] getValues() {
/* 1300 */       long[] result = new long[this.mCount];
/* 1301 */       System.arraycopy(this.mResult, this.mStart, result, 0, this.mCount);
/* 1302 */       return result;
/*      */     }
/*      */     
/*      */     public boolean decodeValue(String input) throws IllegalArgumentException {
/* 1307 */       this.mDecoder.decode(input);
/* 1308 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1309 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */     
/*      */     public boolean decodeValue(char[] buffer, int start, int end) throws IllegalArgumentException {
/* 1314 */       this.mDecoder.decode(buffer, start, end);
/* 1315 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1316 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class FloatArrayDecoder extends BaseArrayDecoder {
/*      */     float[] mResult;
/*      */     
/*      */     final ValueDecoderFactory.FloatDecoder mDecoder;
/*      */     
/*      */     public FloatArrayDecoder(float[] result, int start, int maxCount, ValueDecoderFactory.FloatDecoder floatDecoder) {
/* 1330 */       super(start, maxCount);
/* 1331 */       this.mResult = result;
/* 1332 */       this.mDecoder = floatDecoder;
/*      */     }
/*      */     
/*      */     public FloatArrayDecoder(ValueDecoderFactory.FloatDecoder floatDecoder) {
/* 1337 */       super(0, 40);
/* 1338 */       this.mResult = new float[40];
/* 1339 */       this.mDecoder = floatDecoder;
/*      */     }
/*      */     
/*      */     public void expand() {
/* 1344 */       float[] old = this.mResult;
/* 1345 */       int oldLen = old.length;
/* 1346 */       int newSize = calcNewSize(oldLen);
/* 1347 */       this.mResult = new float[newSize];
/* 1348 */       System.arraycopy(old, this.mStart, this.mResult, 0, oldLen);
/* 1349 */       this.mStart = 0;
/* 1350 */       this.mEnd = newSize;
/*      */     }
/*      */     
/*      */     public float[] getValues() {
/* 1355 */       float[] result = new float[this.mCount];
/* 1356 */       System.arraycopy(this.mResult, this.mStart, result, 0, this.mCount);
/* 1357 */       return result;
/*      */     }
/*      */     
/*      */     public boolean decodeValue(String input) throws IllegalArgumentException {
/* 1362 */       this.mDecoder.decode(input);
/* 1363 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1364 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */     
/*      */     public boolean decodeValue(char[] buffer, int start, int end) throws IllegalArgumentException {
/* 1369 */       this.mDecoder.decode(buffer, start, end);
/* 1370 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1371 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class DoubleArrayDecoder extends BaseArrayDecoder {
/*      */     double[] mResult;
/*      */     
/*      */     final ValueDecoderFactory.DoubleDecoder mDecoder;
/*      */     
/*      */     public DoubleArrayDecoder(double[] result, int start, int maxCount, ValueDecoderFactory.DoubleDecoder doubleDecoder) {
/* 1385 */       super(start, maxCount);
/* 1386 */       this.mResult = result;
/* 1387 */       this.mDecoder = doubleDecoder;
/*      */     }
/*      */     
/*      */     public DoubleArrayDecoder(ValueDecoderFactory.DoubleDecoder doubleDecoder) {
/* 1392 */       super(0, 40);
/* 1393 */       this.mResult = new double[40];
/* 1394 */       this.mDecoder = doubleDecoder;
/*      */     }
/*      */     
/*      */     public void expand() {
/* 1399 */       double[] old = this.mResult;
/* 1400 */       int oldLen = old.length;
/* 1401 */       int newSize = calcNewSize(oldLen);
/* 1402 */       this.mResult = new double[newSize];
/* 1403 */       System.arraycopy(old, this.mStart, this.mResult, 0, oldLen);
/* 1404 */       this.mStart = 0;
/* 1405 */       this.mEnd = newSize;
/*      */     }
/*      */     
/*      */     public double[] getValues() {
/* 1410 */       double[] result = new double[this.mCount];
/* 1411 */       System.arraycopy(this.mResult, this.mStart, result, 0, this.mCount);
/* 1412 */       return result;
/*      */     }
/*      */     
/*      */     public boolean decodeValue(String input) throws IllegalArgumentException {
/* 1417 */       this.mDecoder.decode(input);
/* 1418 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1419 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */     
/*      */     public boolean decodeValue(char[] buffer, int start, int end) throws IllegalArgumentException {
/* 1424 */       this.mDecoder.decode(buffer, start, end);
/* 1425 */       this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
/* 1426 */       return (++this.mCount >= this.mEnd);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\ValueDecoderFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */