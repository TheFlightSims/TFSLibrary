/*    */ package org.opengis.coverage.processing;
/*    */ 
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ 
/*    */ @UML(identifier = "GP_OperationNotFound", specification = Specification.OGC_01004)
/*    */ public class OperationNotFoundException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = 8654574655958181935L;
/*    */   
/*    */   public OperationNotFoundException() {}
/*    */   
/*    */   public OperationNotFoundException(String message) {
/* 59 */     super(message);
/*    */   }
/*    */   
/*    */   public OperationNotFoundException(String message, Throwable cause) {
/* 72 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\processing\OperationNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */