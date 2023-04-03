/*     */ package ch.qos.logback.core.helpers;
/*     */ 
/*     */ public class Transform {
/*     */   private static final String CDATA_START = "<![CDATA[";
/*     */   
/*     */   private static final String CDATA_END = "]]>";
/*     */   
/*     */   private static final String CDATA_PSEUDO_END = "]]&gt;";
/*     */   
/*     */   private static final String CDATA_EMBEDED_END = "]]>]]&gt;<![CDATA[";
/*     */   
/*  28 */   private static final int CDATA_END_LEN = "]]>".length();
/*     */   
/*     */   public static String escapeTags(String input) {
/*  40 */     if (input == null || input.length() == 0 || (input.indexOf("<") == -1 && input.indexOf(">") == -1))
/*  42 */       return input; 
/*  45 */     StringBuffer buf = new StringBuffer(input);
/*  46 */     return escapeTags(buf);
/*     */   }
/*     */   
/*     */   public static String escapeTags(StringBuffer buf) {
/*  58 */     for (int i = 0; i < buf.length(); i++) {
/*  59 */       char ch = buf.charAt(i);
/*  60 */       if (ch == '<') {
/*  61 */         buf.replace(i, i + 1, "&lt;");
/*  62 */       } else if (ch == '>') {
/*  63 */         buf.replace(i, i + 1, "&gt;");
/*     */       } 
/*     */     } 
/*  66 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static void appendEscapingCDATA(StringBuilder output, String str) {
/*  82 */     if (str == null)
/*     */       return; 
/*  86 */     int end = str.indexOf("]]>");
/*  88 */     if (end < 0) {
/*  89 */       output.append(str);
/*     */       return;
/*     */     } 
/*  94 */     int start = 0;
/*  96 */     while (end > -1) {
/*  97 */       output.append(str.substring(start, end));
/*  98 */       output.append("]]>]]&gt;<![CDATA[");
/*  99 */       start = end + CDATA_END_LEN;
/* 101 */       if (start < str.length()) {
/* 102 */         end = str.indexOf("]]>", start);
/*     */         continue;
/*     */       } 
/*     */       return;
/*     */     } 
/* 108 */     output.append(str.substring(start));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\helpers\Transform.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */