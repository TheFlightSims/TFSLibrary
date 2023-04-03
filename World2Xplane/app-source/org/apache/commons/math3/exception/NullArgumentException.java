/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class NullArgumentException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = -6024911025449780478L;
/*    */   
/*    */   public NullArgumentException() {
/* 41 */     this((Localizable)LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
/*    */   }
/*    */   
/*    */   public NullArgumentException(Localizable pattern, Object... arguments) {
/* 50 */     super(pattern, arguments);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\NullArgumentException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */