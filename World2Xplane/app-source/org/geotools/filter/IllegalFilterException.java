/*    */ package org.geotools.filter;
/*    */ 
/*    */ public class IllegalFilterException extends RuntimeException {
/*    */   private static final long serialVersionUID = 6991878877158220201L;
/*    */   
/*    */   public IllegalFilterException(String message) {
/* 40 */     super(message);
/*    */   }
/*    */   
/*    */   public IllegalFilterException(Exception cause) {
/* 50 */     super(cause);
/*    */   }
/*    */   
/*    */   public IllegalFilterException(String msg, Exception cause) {
/* 61 */     super(msg, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IllegalFilterException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */