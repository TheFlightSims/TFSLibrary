/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class ZeroException extends MathIllegalNumberException {
/*    */   private static final long serialVersionUID = -1960874856936000015L;
/*    */   
/*    */   public ZeroException() {
/* 37 */     this((Localizable)LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
/*    */   }
/*    */   
/*    */   public ZeroException(Localizable specific, Object... arguments) {
/* 47 */     super(specific, Integer.valueOf(0), arguments);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\ZeroException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */