/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public class CharSetUtils {
/*     */   public static CharSet evaluateSet(String[] set) {
/*  73 */     if (set == null)
/*  74 */       return null; 
/*  76 */     return new CharSet(set);
/*     */   }
/*     */   
/*     */   public static String squeeze(String str, String set) {
/* 100 */     if (StringUtils.isEmpty(str) || StringUtils.isEmpty(set))
/* 101 */       return str; 
/* 103 */     String[] strs = new String[1];
/* 104 */     strs[0] = set;
/* 105 */     return squeeze(str, strs);
/*     */   }
/*     */   
/*     */   public static String squeeze(String str, String[] set) {
/* 123 */     if (StringUtils.isEmpty(str) || ArrayUtils.isEmpty((Object[])set))
/* 124 */       return str; 
/* 126 */     CharSet chars = CharSet.getInstance(set);
/* 127 */     StrBuilder buffer = new StrBuilder(str.length());
/* 128 */     char[] chrs = str.toCharArray();
/* 129 */     int sz = chrs.length;
/* 130 */     char lastChar = ' ';
/* 131 */     char ch = ' ';
/* 132 */     for (int i = 0; i < sz; i++) {
/* 133 */       ch = chrs[i];
/* 134 */       if (!chars.contains(ch) || 
/* 135 */         ch != lastChar || i == 0) {
/* 139 */         buffer.append(ch);
/* 140 */         lastChar = ch;
/*     */       } 
/*     */     } 
/* 142 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static int count(String str, String set) {
/* 166 */     if (StringUtils.isEmpty(str) || StringUtils.isEmpty(set))
/* 167 */       return 0; 
/* 169 */     String[] strs = new String[1];
/* 170 */     strs[0] = set;
/* 171 */     return count(str, strs);
/*     */   }
/*     */   
/*     */   public static int count(String str, String[] set) {
/* 189 */     if (StringUtils.isEmpty(str) || ArrayUtils.isEmpty((Object[])set))
/* 190 */       return 0; 
/* 192 */     CharSet chars = CharSet.getInstance(set);
/* 193 */     int count = 0;
/* 194 */     char[] chrs = str.toCharArray();
/* 195 */     int sz = chrs.length;
/* 196 */     for (int i = 0; i < sz; i++) {
/* 197 */       if (chars.contains(chrs[i]))
/* 198 */         count++; 
/*     */     } 
/* 201 */     return count;
/*     */   }
/*     */   
/*     */   public static String keep(String str, String set) {
/* 226 */     if (str == null)
/* 227 */       return null; 
/* 229 */     if (str.length() == 0 || StringUtils.isEmpty(set))
/* 230 */       return ""; 
/* 232 */     String[] strs = new String[1];
/* 233 */     strs[0] = set;
/* 234 */     return keep(str, strs);
/*     */   }
/*     */   
/*     */   public static String keep(String str, String[] set) {
/* 254 */     if (str == null)
/* 255 */       return null; 
/* 257 */     if (str.length() == 0 || ArrayUtils.isEmpty((Object[])set))
/* 258 */       return ""; 
/* 260 */     return modify(str, set, true);
/*     */   }
/*     */   
/*     */   public static String delete(String str, String set) {
/* 284 */     if (StringUtils.isEmpty(str) || StringUtils.isEmpty(set))
/* 285 */       return str; 
/* 287 */     String[] strs = new String[1];
/* 288 */     strs[0] = set;
/* 289 */     return delete(str, strs);
/*     */   }
/*     */   
/*     */   public static String delete(String str, String[] set) {
/* 308 */     if (StringUtils.isEmpty(str) || ArrayUtils.isEmpty((Object[])set))
/* 309 */       return str; 
/* 311 */     return modify(str, set, false);
/*     */   }
/*     */   
/*     */   private static String modify(String str, String[] set, boolean expect) {
/* 324 */     CharSet chars = CharSet.getInstance(set);
/* 325 */     StrBuilder buffer = new StrBuilder(str.length());
/* 326 */     char[] chrs = str.toCharArray();
/* 327 */     int sz = chrs.length;
/* 328 */     for (int i = 0; i < sz; i++) {
/* 329 */       if (chars.contains(chrs[i]) == expect)
/* 330 */         buffer.append(chrs[i]); 
/*     */     } 
/* 333 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static String translate(String str, String searchChars, String replaceChars) {
/* 371 */     if (StringUtils.isEmpty(str))
/* 372 */       return str; 
/* 374 */     StrBuilder buffer = new StrBuilder(str.length());
/* 375 */     char[] chrs = str.toCharArray();
/* 376 */     char[] withChrs = replaceChars.toCharArray();
/* 377 */     int sz = chrs.length;
/* 378 */     int withMax = replaceChars.length() - 1;
/* 379 */     for (int i = 0; i < sz; i++) {
/* 380 */       int idx = searchChars.indexOf(chrs[i]);
/* 381 */       if (idx != -1) {
/* 382 */         if (idx > withMax)
/* 383 */           idx = withMax; 
/* 385 */         buffer.append(withChrs[idx]);
/*     */       } else {
/* 387 */         buffer.append(chrs[i]);
/*     */       } 
/*     */     } 
/* 390 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\CharSetUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */