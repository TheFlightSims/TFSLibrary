/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class IllConditionedOperatorException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = -7883263944530490135L;
/*    */   
/*    */   public IllConditionedOperatorException(double cond) {
/* 41 */     super((Localizable)LocalizedFormats.ILL_CONDITIONED_OPERATOR, new Object[] { Double.valueOf(cond) });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\IllConditionedOperatorException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */