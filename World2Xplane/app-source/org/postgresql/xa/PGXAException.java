/*    */ package org.postgresql.xa;
/*    */ 
/*    */ import javax.transaction.xa.XAException;
/*    */ 
/*    */ public class PGXAException extends XAException {
/*    */   PGXAException(String message, int errorCode) {
/* 23 */     super(message);
/* 25 */     this.errorCode = errorCode;
/*    */   }
/*    */   
/*    */   PGXAException(String message, Throwable cause, int errorCode) {
/* 29 */     super(message);
/* 31 */     initCause(cause);
/* 32 */     this.errorCode = errorCode;
/*    */   }
/*    */   
/*    */   PGXAException(Throwable cause, int errorCode) {
/* 36 */     super(errorCode);
/* 38 */     initCause(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\xa\PGXAException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */