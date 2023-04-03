/*    */ package org.opengis.geometry;
/*    */ 
/*    */ public class MismatchedReferenceSystemException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = 6222334569692693273L;
/*    */   
/*    */   public MismatchedReferenceSystemException() {}
/*    */   
/*    */   public MismatchedReferenceSystemException(String message) {
/* 44 */     super(message);
/*    */   }
/*    */   
/*    */   public MismatchedReferenceSystemException(String message, Throwable cause) {
/* 55 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\MismatchedReferenceSystemException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */