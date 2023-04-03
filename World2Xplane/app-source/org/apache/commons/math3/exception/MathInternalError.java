/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class MathInternalError extends MathIllegalStateException {
/*    */   private static final long serialVersionUID = -6276776513966934846L;
/*    */   
/*    */   private static final String REPORT_URL = "https://issues.apache.org/jira/browse/MATH";
/*    */   
/*    */   public MathInternalError() {
/* 38 */     getContext().addMessage((Localizable)LocalizedFormats.INTERNAL_ERROR, new Object[] { "https://issues.apache.org/jira/browse/MATH" });
/*    */   }
/*    */   
/*    */   public MathInternalError(Throwable cause) {
/* 46 */     super(cause, (Localizable)LocalizedFormats.INTERNAL_ERROR, new Object[] { "https://issues.apache.org/jira/browse/MATH" });
/*    */   }
/*    */   
/*    */   public MathInternalError(Localizable pattern, Object... args) {
/* 56 */     super(pattern, args);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\MathInternalError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */