/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class NonSelfAdjointOperatorException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = 1784999305030258247L;
/*    */   
/*    */   public NonSelfAdjointOperatorException() {
/* 45 */     super((Localizable)LocalizedFormats.NON_SELF_ADJOINT_OPERATOR, new Object[0]);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\NonSelfAdjointOperatorException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */