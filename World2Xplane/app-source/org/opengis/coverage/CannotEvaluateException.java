/*    */ package org.opengis.coverage;
/*    */ 
/*    */ public class CannotEvaluateException extends RuntimeException {
/*    */   private static final long serialVersionUID = 506793649975583062L;
/*    */   
/*    */   private Coverage coverage;
/*    */   
/*    */   public CannotEvaluateException() {}
/*    */   
/*    */   public CannotEvaluateException(String message) {
/* 59 */     super(message);
/*    */   }
/*    */   
/*    */   public CannotEvaluateException(String message, Throwable cause) {
/* 71 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public Coverage getCoverage() {
/* 85 */     return this.coverage;
/*    */   }
/*    */   
/*    */   public void setCoverage(Coverage coverage) {
/* 97 */     this.coverage = coverage;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\CannotEvaluateException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */