/*     */ package org.apache.commons.compress.utils;
/*     */ 
/*     */ import java.nio.charset.Charset;
/*     */ 
/*     */ public class Charsets {
/*     */   public static Charset toCharset(Charset charset) {
/*  73 */     return (charset == null) ? Charset.defaultCharset() : charset;
/*     */   }
/*     */   
/*     */   public static Charset toCharset(String charset) {
/*  86 */     return (charset == null) ? Charset.defaultCharset() : Charset.forName(charset);
/*     */   }
/*     */   
/*  97 */   public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
/*     */   
/* 109 */   public static final Charset US_ASCII = Charset.forName("US-ASCII");
/*     */   
/* 122 */   public static final Charset UTF_16 = Charset.forName("UTF-16");
/*     */   
/* 134 */   public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
/*     */   
/* 146 */   public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
/*     */   
/* 158 */   public static final Charset UTF_8 = Charset.forName("UTF-8");
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compres\\utils\Charsets.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */