/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ 
/*    */ public class MathIllegalNumberException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = -7447085893598031110L;
/*    */   
/*    */   private final Number argument;
/*    */   
/*    */   protected MathIllegalNumberException(Localizable pattern, Number wrong, Object... arguments) {
/* 46 */     super(pattern, new Object[] { wrong, arguments });
/* 47 */     this.argument = wrong;
/*    */   }
/*    */   
/*    */   public Number getArgument() {
/* 54 */     return this.argument;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\MathIllegalNumberException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */