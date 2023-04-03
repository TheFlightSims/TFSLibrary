/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public final class StringUtil {
/*     */   static final char CHAR_SPACE = ' ';
/*     */   
/*     */   private static final char INT_SPACE = ' ';
/*     */   
/*  11 */   static String sLF = null;
/*     */   
/*     */   private static final int EOS = 65536;
/*     */   
/*     */   public static String getLF() {
/*  15 */     String lf = sLF;
/*  16 */     if (lf == null)
/*     */       try {
/*  18 */         lf = System.getProperty("line.separator");
/*  19 */         sLF = (lf == null) ? "\n" : lf;
/*  20 */       } catch (Throwable t) {
/*  22 */         sLF = lf = "\n";
/*     */       }  
/*  25 */     return lf;
/*     */   }
/*     */   
/*     */   public static void appendLF(StringBuffer sb) {
/*  29 */     sb.append(getLF());
/*     */   }
/*     */   
/*     */   public static String concatEntries(Collection coll, String sep, String lastSep) {
/*  33 */     if (lastSep == null)
/*  34 */       lastSep = sep; 
/*  36 */     int len = coll.size();
/*  37 */     StringBuffer sb = new StringBuffer(16 + (len << 3));
/*  38 */     Iterator it = coll.iterator();
/*  39 */     int i = 0;
/*  40 */     while (it.hasNext()) {
/*  41 */       if (i != 0)
/*  43 */         if (i == len - 1) {
/*  44 */           sb.append(lastSep);
/*     */         } else {
/*  46 */           sb.append(sep);
/*     */         }  
/*  48 */       i++;
/*  49 */       sb.append(it.next());
/*     */     } 
/*  51 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String normalizeSpaces(char[] buf, int origStart, int origEnd) {
/*  76 */     origEnd--;
/*  78 */     int start = origStart;
/*  79 */     int end = origEnd;
/*  82 */     while (start <= end && buf[start] == ' ')
/*  83 */       start++; 
/*  86 */     if (start > end)
/*  87 */       return ""; 
/*  93 */     while (end > start && buf[end] == ' ')
/*  94 */       end--; 
/* 101 */     int i = start + 1;
/* 103 */     while (i < end) {
/* 104 */       if (buf[i] == ' ') {
/* 105 */         if (buf[i + 1] == ' ')
/*     */           break; 
/* 109 */         i += 2;
/*     */         continue;
/*     */       } 
/* 111 */       i++;
/*     */     } 
/* 116 */     if (i >= end) {
/* 118 */       if (start == origStart && end == origEnd)
/* 119 */         return null; 
/* 121 */       return new String(buf, start, end - start + 1);
/*     */     } 
/* 127 */     StringBuffer sb = new StringBuffer(end - start);
/* 128 */     sb.append(buf, start, i - start);
/* 130 */     while (i <= end) {
/* 131 */       char c = buf[i++];
/* 132 */       if (c == ' ') {
/* 133 */         sb.append(' ');
/*     */         while (true) {
/* 136 */           c = buf[i++];
/* 137 */           if (c != ' ')
/* 138 */             sb.append(c); 
/*     */         } 
/*     */       } 
/* 143 */       sb.append(c);
/*     */     } 
/* 147 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static boolean isAllWhitespace(String str) {
/* 152 */     for (int i = 0, len = str.length(); i < len; i++) {
/* 153 */       if (str.charAt(i) > ' ')
/* 154 */         return false; 
/*     */     } 
/* 157 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isAllWhitespace(char[] ch, int start, int len) {
/* 162 */     len += start;
/* 163 */     for (; start < len; start++) {
/* 164 */       if (ch[start] > ' ')
/* 165 */         return false; 
/*     */     } 
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equalEncodings(String str1, String str2) {
/* 184 */     int len1 = str1.length();
/* 185 */     int len2 = str2.length();
/* 188 */     for (int i1 = 0, i2 = 0; i1 < len1 || i2 < len2; ) {
/* 189 */       int c1 = (i1 >= len1) ? 65536 : str1.charAt(i1++);
/* 190 */       int c2 = (i2 >= len2) ? 65536 : str2.charAt(i2++);
/* 193 */       if (c1 == c2)
/*     */         continue; 
/* 198 */       while (c1 <= 32 || c1 == 95 || c1 == 45)
/* 199 */         c1 = (i1 >= len1) ? 65536 : str1.charAt(i1++); 
/* 201 */       while (c2 <= 32 || c2 == 95 || c2 == 45)
/* 202 */         c2 = (i2 >= len2) ? 65536 : str2.charAt(i2++); 
/* 205 */       if (c1 != c2) {
/* 207 */         if (c1 == 65536 || c2 == 65536)
/* 208 */           return false; 
/* 210 */         if (c1 < 127) {
/* 211 */           if (c1 <= 90 && c1 >= 65)
/* 212 */             c1 += 32; 
/*     */         } else {
/* 215 */           c1 = Character.toLowerCase((char)c1);
/*     */         } 
/* 217 */         if (c2 < 127) {
/* 218 */           if (c2 <= 90 && c2 >= 65)
/* 219 */             c2 += 32; 
/*     */         } else {
/* 222 */           c2 = Character.toLowerCase((char)c2);
/*     */         } 
/* 224 */         if (c1 != c2)
/* 225 */           return false; 
/*     */       } 
/*     */     } 
/* 231 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean encodingStartsWith(String enc, String prefix) {
/* 236 */     int len1 = enc.length();
/* 237 */     int len2 = prefix.length();
/* 239 */     int i1 = 0, i2 = 0;
/* 242 */     while (i1 < len1 || i2 < len2) {
/* 243 */       int c1 = (i1 >= len1) ? 65536 : enc.charAt(i1++);
/* 244 */       int c2 = (i2 >= len2) ? 65536 : prefix.charAt(i2++);
/* 247 */       if (c1 == c2)
/*     */         continue; 
/* 252 */       while (c1 <= 32 || c1 == 95 || c1 == 45)
/* 253 */         c1 = (i1 >= len1) ? 65536 : enc.charAt(i1++); 
/* 255 */       while (c2 <= 32 || c2 == 95 || c2 == 45)
/* 256 */         c2 = (i2 >= len2) ? 65536 : prefix.charAt(i2++); 
/* 259 */       if (c1 != c2) {
/* 260 */         if (c2 == 65536)
/* 261 */           return true; 
/* 263 */         if (c1 == 65536)
/* 264 */           return false; 
/* 266 */         if (Character.toLowerCase((char)c1) != Character.toLowerCase((char)c2))
/* 267 */           return false; 
/*     */       } 
/*     */     } 
/* 273 */     return true;
/*     */   }
/*     */   
/*     */   public static String trimEncoding(String str, boolean upperCase) {
/* 282 */     int i = 0;
/* 283 */     int len = str.length();
/* 286 */     for (; i < len; i++) {
/* 287 */       char c = str.charAt(i);
/* 288 */       if (c <= ' ' || !Character.isLetterOrDigit(c))
/*     */         break; 
/*     */     } 
/* 293 */     if (i == len)
/* 294 */       return str; 
/* 298 */     StringBuffer sb = new StringBuffer();
/* 299 */     if (i > 0)
/* 300 */       sb.append(str.substring(0, i)); 
/* 302 */     for (; i < len; i++) {
/* 303 */       char c = str.charAt(i);
/* 304 */       if (c > ' ' && Character.isLetterOrDigit(c)) {
/* 305 */         if (upperCase)
/* 306 */           c = Character.toUpperCase(c); 
/* 308 */         sb.append(c);
/*     */       } 
/*     */     } 
/* 312 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static boolean matches(String str, char[] cbuf, int offset, int len) {
/* 317 */     if (str.length() != len)
/* 318 */       return false; 
/* 320 */     for (int i = 0; i < len; i++) {
/* 321 */       if (str.charAt(i) != cbuf[offset + i])
/* 322 */         return false; 
/*     */     } 
/* 325 */     return true;
/*     */   }
/*     */   
/*     */   public static final boolean isSpace(char c) {
/* 337 */     return (c <= ' ');
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\StringUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */