/*    */ package org.opengis.geometry;
/*    */ 
/*    */ public class MismatchedDimensionException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = 3138484331425225155L;
/*    */   
/*    */   public MismatchedDimensionException() {}
/*    */   
/*    */   public MismatchedDimensionException(String message) {
/* 43 */     super(message);
/*    */   }
/*    */   
/*    */   public MismatchedDimensionException(String message, Throwable cause) {
/* 54 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\MismatchedDimensionException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */