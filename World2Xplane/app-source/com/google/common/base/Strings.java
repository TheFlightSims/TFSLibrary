/*     */ package com.google.common.base;
/*     */ 
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @GwtCompatible
/*     */ public final class Strings {
/*     */   public static String nullToEmpty(@Nullable String string) {
/*  47 */     return (string == null) ? "" : string;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static String emptyToNull(@Nullable String string) {
/*  58 */     return isNullOrEmpty(string) ? null : string;
/*     */   }
/*     */   
/*     */   public static boolean isNullOrEmpty(@Nullable String string) {
/*  75 */     return (string == null || string.length() == 0);
/*     */   }
/*     */   
/*     */   public static String padStart(String string, int minLength, char padChar) {
/*  98 */     Preconditions.checkNotNull(string);
/*  99 */     if (string.length() >= minLength)
/* 100 */       return string; 
/* 102 */     StringBuilder sb = new StringBuilder(minLength);
/* 103 */     for (int i = string.length(); i < minLength; i++)
/* 104 */       sb.append(padChar); 
/* 106 */     sb.append(string);
/* 107 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String padEnd(String string, int minLength, char padChar) {
/* 130 */     Preconditions.checkNotNull(string);
/* 131 */     if (string.length() >= minLength)
/* 132 */       return string; 
/* 134 */     StringBuilder sb = new StringBuilder(minLength);
/* 135 */     sb.append(string);
/* 136 */     for (int i = string.length(); i < minLength; i++)
/* 137 */       sb.append(padChar); 
/* 139 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String repeat(String string, int count) {
/* 154 */     Preconditions.checkNotNull(string);
/* 156 */     if (count <= 1) {
/* 157 */       Preconditions.checkArgument((count >= 0), "invalid count: %s", new Object[] { Integer.valueOf(count) });
/* 158 */       return (count == 0) ? "" : string;
/*     */     } 
/* 162 */     int len = string.length();
/* 163 */     long longSize = len * count;
/* 164 */     int size = (int)longSize;
/* 165 */     if (size != longSize)
/* 166 */       throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize); 
/* 170 */     char[] array = new char[size];
/* 171 */     string.getChars(0, len, array, 0);
/*     */     int n;
/* 173 */     for (n = len; n < size - n; n <<= 1)
/* 174 */       System.arraycopy(array, 0, array, n, n); 
/* 176 */     System.arraycopy(array, 0, array, n, size - n);
/* 177 */     return new String(array);
/*     */   }
/*     */   
/*     */   public static String commonPrefix(CharSequence a, CharSequence b) {
/* 189 */     Preconditions.checkNotNull(a);
/* 190 */     Preconditions.checkNotNull(b);
/* 192 */     int maxPrefixLength = Math.min(a.length(), b.length());
/* 193 */     int p = 0;
/* 194 */     while (p < maxPrefixLength && a.charAt(p) == b.charAt(p))
/* 195 */       p++; 
/* 197 */     if (validSurrogatePairAt(a, p - 1) || validSurrogatePairAt(b, p - 1))
/* 198 */       p--; 
/* 200 */     return a.subSequence(0, p).toString();
/*     */   }
/*     */   
/*     */   public static String commonSuffix(CharSequence a, CharSequence b) {
/* 212 */     Preconditions.checkNotNull(a);
/* 213 */     Preconditions.checkNotNull(b);
/* 215 */     int maxSuffixLength = Math.min(a.length(), b.length());
/* 216 */     int s = 0;
/* 218 */     while (s < maxSuffixLength && a.charAt(a.length() - s - 1) == b.charAt(b.length() - s - 1))
/* 219 */       s++; 
/* 221 */     if (validSurrogatePairAt(a, a.length() - s - 1) || validSurrogatePairAt(b, b.length() - s - 1))
/* 223 */       s--; 
/* 225 */     return a.subSequence(a.length() - s, a.length()).toString();
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static boolean validSurrogatePairAt(CharSequence string, int index) {
/* 234 */     return (index >= 0 && index <= string.length() - 2 && Character.isHighSurrogate(string.charAt(index)) && Character.isLowSurrogate(string.charAt(index + 1)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\Strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */