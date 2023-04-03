/*    */ package com.google.protobuf;
/*    */ 
/*    */ public class ServiceException extends Exception {
/*    */   private static final long serialVersionUID = -1219262335729891920L;
/*    */   
/*    */   public ServiceException(String message) {
/* 42 */     super(message);
/*    */   }
/*    */   
/*    */   public ServiceException(Throwable cause) {
/* 46 */     super(cause);
/*    */   }
/*    */   
/*    */   public ServiceException(String message, Throwable cause) {
/* 50 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\ServiceException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */