/*    */ package org.apache.commons.math3.optimization.linear;
/*    */ 
/*    */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class UnboundedSolutionException extends MathIllegalStateException {
/*    */   private static final long serialVersionUID = 940539497277290619L;
/*    */   
/*    */   public UnboundedSolutionException() {
/* 38 */     super((Localizable)LocalizedFormats.UNBOUNDED_SOLUTION, new Object[0]);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\UnboundedSolutionException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */