/*    */ package com.ctc.wstx.api;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public interface InvalidCharHandler {
/*    */   char convertInvalidChar(int paramInt) throws IOException;
/*    */   
/*    */   public static class FailingHandler implements InvalidCharHandler {
/*    */     public static final int SURR1_FIRST = 55296;
/*    */     
/*    */     public static final int SURR1_LAST = 56319;
/*    */     
/*    */     public static final int SURR2_FIRST = 56320;
/*    */     
/*    */     public static final int SURR2_LAST = 57343;
/*    */     
/* 36 */     private static final FailingHandler sInstance = new FailingHandler();
/*    */     
/*    */     public static FailingHandler getInstance() {
/* 40 */       return sInstance;
/*    */     }
/*    */     
/*    */     public char convertInvalidChar(int c) throws IOException {
/* 50 */       if (c == 0)
/* 51 */         throw new IOException("Invalid null character in text to output"); 
/* 53 */       if (c < 32 || (c >= 127 && c <= 159)) {
/* 54 */         String msg = "Invalid white space character (0x" + Integer.toHexString(c) + ") in text to output (in xml 1.1, could output as a character entity)";
/* 55 */         throw new IOException(msg);
/*    */       } 
/* 57 */       if (c > 1114111)
/* 58 */         throw new IOException("Illegal unicode character point (0x" + Integer.toHexString(c) + ") to output; max is 0x10FFFF as per RFC 3629"); 
/* 63 */       if (c >= 55296 && c <= 57343)
/* 64 */         throw new IOException("Illegal surrogate pair -- can only be output via character entities, which are not allowed in this content"); 
/* 66 */       throw new IOException("Invalid XML character (0x" + Integer.toHexString(c) + ") in text to output");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ReplacingHandler implements InvalidCharHandler {
/*    */     final char mReplacementChar;
/*    */     
/*    */     public ReplacingHandler(char c) {
/* 82 */       this.mReplacementChar = c;
/*    */     }
/*    */     
/*    */     public char convertInvalidChar(int c) throws IOException {
/* 87 */       return this.mReplacementChar;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\api\InvalidCharHandler.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */