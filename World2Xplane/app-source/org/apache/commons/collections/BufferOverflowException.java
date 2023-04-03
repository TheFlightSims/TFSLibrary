/*    */ package org.apache.commons.collections;
/*    */ 
/*    */ public class BufferOverflowException extends RuntimeException {
/*    */   private final Throwable throwable;
/*    */   
/*    */   public BufferOverflowException() {
/* 42 */     this.throwable = null;
/*    */   }
/*    */   
/*    */   public BufferOverflowException(String message) {
/* 51 */     this(message, null);
/*    */   }
/*    */   
/*    */   public BufferOverflowException(String message, Throwable exception) {
/* 61 */     super(message);
/* 62 */     this.throwable = exception;
/*    */   }
/*    */   
/*    */   public final Throwable getCause() {
/* 71 */     return this.throwable;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\BufferOverflowException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */