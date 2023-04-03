/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class RandomStringUtils {
/*  46 */   private static final Random RANDOM = new Random();
/*     */   
/*     */   public static String random(int count) {
/*  72 */     return random(count, false, false);
/*     */   }
/*     */   
/*     */   public static String randomAscii(int count) {
/*  86 */     return random(count, 32, 127, false, false);
/*     */   }
/*     */   
/*     */   public static String randomAlphabetic(int count) {
/* 100 */     return random(count, true, false);
/*     */   }
/*     */   
/*     */   public static String randomAlphanumeric(int count) {
/* 114 */     return random(count, true, true);
/*     */   }
/*     */   
/*     */   public static String randomNumeric(int count) {
/* 128 */     return random(count, false, true);
/*     */   }
/*     */   
/*     */   public static String random(int count, boolean letters, boolean numbers) {
/* 146 */     return random(count, 0, 0, letters, numbers);
/*     */   }
/*     */   
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers) {
/* 166 */     return random(count, start, end, letters, numbers, null, RANDOM);
/*     */   }
/*     */   
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars) {
/* 190 */     return random(count, start, end, letters, numbers, chars, RANDOM);
/*     */   }
/*     */   
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random) {
/* 228 */     if (count == 0)
/* 229 */       return ""; 
/* 230 */     if (count < 0)
/* 231 */       throw new IllegalArgumentException("Requested random string length " + count + " is less than 0."); 
/* 233 */     if (start == 0 && end == 0) {
/* 234 */       end = 123;
/* 235 */       start = 32;
/* 236 */       if (!letters && !numbers) {
/* 237 */         start = 0;
/* 238 */         end = Integer.MAX_VALUE;
/*     */       } 
/*     */     } 
/* 242 */     char[] buffer = new char[count];
/* 243 */     int gap = end - start;
/* 245 */     while (count-- != 0) {
/*     */       char ch;
/* 247 */       if (chars == null) {
/* 248 */         ch = (char)(random.nextInt(gap) + start);
/*     */       } else {
/* 250 */         ch = chars[random.nextInt(gap) + start];
/*     */       } 
/* 252 */       if ((letters && Character.isLetter(ch)) || (numbers && Character.isDigit(ch)) || (!letters && !numbers)) {
/* 256 */         if (ch >= '?' && ch <= '?') {
/* 257 */           if (count == 0) {
/* 258 */             count++;
/*     */             continue;
/*     */           } 
/* 261 */           buffer[count] = ch;
/* 262 */           count--;
/* 263 */           buffer[count] = (char)(55296 + random.nextInt(128));
/*     */           continue;
/*     */         } 
/* 265 */         if (ch >= '?' && ch <= '?') {
/* 266 */           if (count == 0) {
/* 267 */             count++;
/*     */             continue;
/*     */           } 
/* 270 */           buffer[count] = (char)(56320 + random.nextInt(128));
/* 271 */           count--;
/* 272 */           buffer[count] = ch;
/*     */           continue;
/*     */         } 
/* 274 */         if (ch >= '?' && ch <= '?') {
/* 276 */           count++;
/*     */           continue;
/*     */         } 
/* 278 */         buffer[count] = ch;
/*     */         continue;
/*     */       } 
/* 281 */       count++;
/*     */     } 
/* 284 */     return new String(buffer);
/*     */   }
/*     */   
/*     */   public static String random(int count, String chars) {
/* 301 */     if (chars == null)
/* 302 */       return random(count, 0, 0, false, false, null, RANDOM); 
/* 304 */     return random(count, chars.toCharArray());
/*     */   }
/*     */   
/*     */   public static String random(int count, char[] chars) {
/* 320 */     if (chars == null)
/* 321 */       return random(count, 0, 0, false, false, null, RANDOM); 
/* 323 */     return random(count, 0, chars.length, false, false, chars, RANDOM);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\RandomStringUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */