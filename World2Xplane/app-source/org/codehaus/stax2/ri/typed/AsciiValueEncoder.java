/*    */ package org.codehaus.stax2.ri.typed;
/*    */ 
/*    */ public abstract class AsciiValueEncoder {
/*    */   protected static final int MIN_CHARS_WITHOUT_FLUSH = 64;
/*    */   
/*    */   public final boolean bufferNeedsFlush(int freeChars) {
/* 99 */     return (freeChars < 64);
/*    */   }
/*    */   
/*    */   public abstract boolean isCompleted();
/*    */   
/*    */   public abstract int encodeMore(char[] paramArrayOfchar, int paramInt1, int paramInt2);
/*    */   
/*    */   public abstract int encodeMore(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\AsciiValueEncoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */