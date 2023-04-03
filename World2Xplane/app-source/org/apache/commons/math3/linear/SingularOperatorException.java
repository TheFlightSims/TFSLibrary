/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class SingularOperatorException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = -476049978595245033L;
/*    */   
/*    */   public SingularOperatorException() {
/* 37 */     super((Localizable)LocalizedFormats.SINGULAR_OPERATOR, new Object[0]);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\SingularOperatorException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */