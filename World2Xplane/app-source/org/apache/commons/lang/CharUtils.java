/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ public class CharUtils {
/*     */   private static final String CHAR_STRING = "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032\033\034\035\036\037 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
/*     */   
/*  51 */   private static final String[] CHAR_STRING_ARRAY = new String[128];
/*     */   
/*  52 */   private static final Character[] CHAR_ARRAY = new Character[128];
/*     */   
/*     */   public static final char LF = '\n';
/*     */   
/*     */   public static final char CR = '\r';
/*     */   
/*     */   static {
/*  74 */     for (int i = 127; i >= 0; i--) {
/*  75 */       CHAR_STRING_ARRAY[i] = "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032\033\034\035\036\037 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".substring(i, i + 1);
/*  76 */       CHAR_ARRAY[i] = new Character((char)i);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Character toCharacterObject(char ch) {
/* 107 */     if (ch < CHAR_ARRAY.length)
/* 108 */       return CHAR_ARRAY[ch]; 
/* 110 */     return new Character(ch);
/*     */   }
/*     */   
/*     */   public static Character toCharacterObject(String str) {
/* 131 */     if (StringUtils.isEmpty(str))
/* 132 */       return null; 
/* 134 */     return toCharacterObject(str.charAt(0));
/*     */   }
/*     */   
/*     */   public static char toChar(Character ch) {
/* 152 */     if (ch == null)
/* 153 */       throw new IllegalArgumentException("The Character must not be null"); 
/* 155 */     return ch.charValue();
/*     */   }
/*     */   
/*     */   public static char toChar(Character ch, char defaultValue) {
/* 172 */     if (ch == null)
/* 173 */       return defaultValue; 
/* 175 */     return ch.charValue();
/*     */   }
/*     */   
/*     */   public static char toChar(String str) {
/* 195 */     if (StringUtils.isEmpty(str))
/* 196 */       throw new IllegalArgumentException("The String must not be empty"); 
/* 198 */     return str.charAt(0);
/*     */   }
/*     */   
/*     */   public static char toChar(String str, char defaultValue) {
/* 217 */     if (StringUtils.isEmpty(str))
/* 218 */       return defaultValue; 
/* 220 */     return str.charAt(0);
/*     */   }
/*     */   
/*     */   public static int toIntValue(char ch) {
/* 240 */     if (!isAsciiNumeric(ch))
/* 241 */       throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'"); 
/* 243 */     return ch - 48;
/*     */   }
/*     */   
/*     */   public static int toIntValue(char ch, int defaultValue) {
/* 262 */     if (!isAsciiNumeric(ch))
/* 263 */       return defaultValue; 
/* 265 */     return ch - 48;
/*     */   }
/*     */   
/*     */   public static int toIntValue(Character ch) {
/* 285 */     if (ch == null)
/* 286 */       throw new IllegalArgumentException("The character must not be null"); 
/* 288 */     return toIntValue(ch.charValue());
/*     */   }
/*     */   
/*     */   public static int toIntValue(Character ch, int defaultValue) {
/* 308 */     if (ch == null)
/* 309 */       return defaultValue; 
/* 311 */     return toIntValue(ch.charValue(), defaultValue);
/*     */   }
/*     */   
/*     */   public static String toString(char ch) {
/* 330 */     if (ch < '')
/* 331 */       return CHAR_STRING_ARRAY[ch]; 
/* 333 */     return new String(new char[] { ch });
/*     */   }
/*     */   
/*     */   public static String toString(Character ch) {
/* 354 */     if (ch == null)
/* 355 */       return null; 
/* 357 */     return toString(ch.charValue());
/*     */   }
/*     */   
/*     */   public static String unicodeEscaped(char ch) {
/* 375 */     if (ch < '\020')
/* 376 */       return "\\u000" + Integer.toHexString(ch); 
/* 377 */     if (ch < 'Ā')
/* 378 */       return "\\u00" + Integer.toHexString(ch); 
/* 379 */     if (ch < 'က')
/* 380 */       return "\\u0" + Integer.toHexString(ch); 
/* 382 */     return "\\u" + Integer.toHexString(ch);
/*     */   }
/*     */   
/*     */   public static String unicodeEscaped(Character ch) {
/* 402 */     if (ch == null)
/* 403 */       return null; 
/* 405 */     return unicodeEscaped(ch.charValue());
/*     */   }
/*     */   
/*     */   public static boolean isAscii(char ch) {
/* 425 */     return (ch < '');
/*     */   }
/*     */   
/*     */   public static boolean isAsciiPrintable(char ch) {
/* 444 */     return (ch >= ' ' && ch < '');
/*     */   }
/*     */   
/*     */   public static boolean isAsciiControl(char ch) {
/* 463 */     return (ch < ' ' || ch == '');
/*     */   }
/*     */   
/*     */   public static boolean isAsciiAlpha(char ch) {
/* 482 */     return ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'));
/*     */   }
/*     */   
/*     */   public static boolean isAsciiAlphaUpper(char ch) {
/* 501 */     return (ch >= 'A' && ch <= 'Z');
/*     */   }
/*     */   
/*     */   public static boolean isAsciiAlphaLower(char ch) {
/* 520 */     return (ch >= 'a' && ch <= 'z');
/*     */   }
/*     */   
/*     */   public static boolean isAsciiNumeric(char ch) {
/* 539 */     return (ch >= '0' && ch <= '9');
/*     */   }
/*     */   
/*     */   public static boolean isAsciiAlphanumeric(char ch) {
/* 558 */     return ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9'));
/*     */   }
/*     */   
/*     */   static boolean isHighSurrogate(char ch) {
/* 573 */     return ('?' <= ch && '?' >= ch);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\CharUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */