/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ public class CharEncoding {
/*     */   public static final String ISO_8859_1 = "ISO-8859-1";
/*     */   
/*     */   public static final String US_ASCII = "US-ASCII";
/*     */   
/*     */   public static final String UTF_16 = "UTF-16";
/*     */   
/*     */   public static final String UTF_16BE = "UTF-16BE";
/*     */   
/*     */   public static final String UTF_16LE = "UTF-16LE";
/*     */   
/*     */   public static final String UTF_8 = "UTF-8";
/*     */   
/*     */   public static boolean isSupported(String name) {
/* 142 */     if (name == null)
/* 143 */       return false; 
/*     */     try {
/* 146 */       new String(ArrayUtils.EMPTY_BYTE_ARRAY, name);
/* 147 */     } catch (UnsupportedEncodingException e) {
/* 148 */       return false;
/*     */     } 
/* 150 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\CharEncoding.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */