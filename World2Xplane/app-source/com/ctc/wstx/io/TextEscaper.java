/*    */ package com.ctc.wstx.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ 
/*    */ public final class TextEscaper {
/*    */   public static void writeEscapedAttrValue(Writer w, String value) throws IOException {
/* 18 */     int i = 0;
/* 19 */     int len = value.length();
/*    */     do {
/* 21 */       int start = i;
/* 22 */       char c = Character.MIN_VALUE;
/* 24 */       for (; i < len; i++) {
/* 25 */         c = value.charAt(i);
/* 26 */         if (c == '<' || c == '&' || c == '"')
/*    */           break; 
/*    */       } 
/* 30 */       int outLen = i - start;
/* 31 */       if (outLen > 0)
/* 32 */         w.write(value, start, outLen); 
/* 34 */       if (i >= len)
/*    */         continue; 
/* 35 */       if (c == '<') {
/* 36 */         w.write("&lt;");
/* 37 */       } else if (c == '&') {
/* 38 */         w.write("&amp;");
/* 39 */       } else if (c == '"') {
/* 40 */         w.write("&quot;");
/*    */       } 
/* 44 */     } while (++i < len);
/*    */   }
/*    */   
/*    */   public static void outputDTDText(Writer w, char[] ch, int offset, int len) throws IOException {
/* 55 */     int i = offset;
/* 56 */     len += offset;
/*    */     do {
/* 58 */       int start = i;
/* 59 */       char c = Character.MIN_VALUE;
/* 61 */       for (; i < len; i++) {
/* 62 */         c = ch[i];
/* 63 */         if (c == '&' || c == '%' || c == '"')
/*    */           break; 
/*    */       } 
/* 67 */       int outLen = i - start;
/* 68 */       if (outLen > 0)
/* 69 */         w.write(ch, start, outLen); 
/* 71 */       if (i >= len)
/*    */         continue; 
/* 72 */       if (c == '&') {
/* 76 */         w.write("&amp;");
/* 77 */       } else if (c == '%') {
/* 79 */         w.write("&#37;");
/* 80 */       } else if (c == '"') {
/* 82 */         w.write("&#34;");
/*    */       } 
/* 85 */     } while (++i < len);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\TextEscaper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */