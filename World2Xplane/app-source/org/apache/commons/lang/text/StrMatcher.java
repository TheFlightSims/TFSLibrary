/*     */ package org.apache.commons.lang.text;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public abstract class StrMatcher {
/*  37 */   private static final StrMatcher COMMA_MATCHER = new CharMatcher(',');
/*     */   
/*  41 */   private static final StrMatcher TAB_MATCHER = new CharMatcher('\t');
/*     */   
/*  45 */   private static final StrMatcher SPACE_MATCHER = new CharMatcher(' ');
/*     */   
/*  50 */   private static final StrMatcher SPLIT_MATCHER = new CharSetMatcher(" \t\n\r\f".toCharArray());
/*     */   
/*  54 */   private static final StrMatcher TRIM_MATCHER = new TrimMatcher();
/*     */   
/*  58 */   private static final StrMatcher SINGLE_QUOTE_MATCHER = new CharMatcher('\'');
/*     */   
/*  62 */   private static final StrMatcher DOUBLE_QUOTE_MATCHER = new CharMatcher('"');
/*     */   
/*  66 */   private static final StrMatcher QUOTE_MATCHER = new CharSetMatcher("'\"".toCharArray());
/*     */   
/*  70 */   private static final StrMatcher NONE_MATCHER = new NoMatcher();
/*     */   
/*     */   public static StrMatcher commaMatcher() {
/*  80 */     return COMMA_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher tabMatcher() {
/*  89 */     return TAB_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher spaceMatcher() {
/*  98 */     return SPACE_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher splitMatcher() {
/* 108 */     return SPLIT_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher trimMatcher() {
/* 117 */     return TRIM_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher singleQuoteMatcher() {
/* 126 */     return SINGLE_QUOTE_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher doubleQuoteMatcher() {
/* 135 */     return DOUBLE_QUOTE_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher quoteMatcher() {
/* 144 */     return QUOTE_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher noneMatcher() {
/* 153 */     return NONE_MATCHER;
/*     */   }
/*     */   
/*     */   public static StrMatcher charMatcher(char ch) {
/* 163 */     return new CharMatcher(ch);
/*     */   }
/*     */   
/*     */   public static StrMatcher charSetMatcher(char[] chars) {
/* 173 */     if (chars == null || chars.length == 0)
/* 174 */       return NONE_MATCHER; 
/* 176 */     if (chars.length == 1)
/* 177 */       return new CharMatcher(chars[0]); 
/* 179 */     return new CharSetMatcher(chars);
/*     */   }
/*     */   
/*     */   public static StrMatcher charSetMatcher(String chars) {
/* 189 */     if (chars == null || chars.length() == 0)
/* 190 */       return NONE_MATCHER; 
/* 192 */     if (chars.length() == 1)
/* 193 */       return new CharMatcher(chars.charAt(0)); 
/* 195 */     return new CharSetMatcher(chars.toCharArray());
/*     */   }
/*     */   
/*     */   public static StrMatcher stringMatcher(String str) {
/* 205 */     if (str == null || str.length() == 0)
/* 206 */       return NONE_MATCHER; 
/* 208 */     return new StringMatcher(str);
/*     */   }
/*     */   
/*     */   public abstract int isMatch(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public int isMatch(char[] buffer, int pos) {
/* 267 */     return isMatch(buffer, pos, 0, buffer.length);
/*     */   }
/*     */   
/*     */   static final class CharSetMatcher extends StrMatcher {
/*     */     private final char[] chars;
/*     */     
/*     */     CharSetMatcher(char[] chars) {
/* 285 */       this.chars = (char[])chars.clone();
/* 286 */       Arrays.sort(this.chars);
/*     */     }
/*     */     
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
/* 299 */       return (Arrays.binarySearch(this.chars, buffer[pos]) >= 0) ? 1 : 0;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class CharMatcher extends StrMatcher {
/*     */     private final char ch;
/*     */     
/*     */     CharMatcher(char ch) {
/* 318 */       this.ch = ch;
/*     */     }
/*     */     
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
/* 331 */       return (this.ch == buffer[pos]) ? 1 : 0;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class StringMatcher extends StrMatcher {
/*     */     private final char[] chars;
/*     */     
/*     */     StringMatcher(String str) {
/* 350 */       this.chars = str.toCharArray();
/*     */     }
/*     */     
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
/* 363 */       int len = this.chars.length;
/* 364 */       if (pos + len > bufferEnd)
/* 365 */         return 0; 
/* 367 */       for (int i = 0; i < this.chars.length; i++, pos++) {
/* 368 */         if (this.chars[i] != buffer[pos])
/* 369 */           return 0; 
/*     */       } 
/* 372 */       return len;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class NoMatcher extends StrMatcher {
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
/* 399 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class TrimMatcher extends StrMatcher {
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
/* 426 */       return (buffer[pos] <= ' ') ? 1 : 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\text\StrMatcher.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */