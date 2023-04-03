/*    */ package org.apache.commons.lang;
/*    */ 
/*    */ import org.apache.commons.lang.exception.NestableRuntimeException;
/*    */ 
/*    */ public class SerializationException extends NestableRuntimeException {
/*    */   private static final long serialVersionUID = 4029025366392702726L;
/*    */   
/*    */   public SerializationException() {}
/*    */   
/*    */   public SerializationException(String msg) {
/* 54 */     super(msg);
/*    */   }
/*    */   
/*    */   public SerializationException(Throwable cause) {
/* 65 */     super(cause);
/*    */   }
/*    */   
/*    */   public SerializationException(String msg, Throwable cause) {
/* 77 */     super(msg, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\SerializationException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */