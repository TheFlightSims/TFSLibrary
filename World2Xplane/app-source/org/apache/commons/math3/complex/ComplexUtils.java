/*    */ package org.apache.commons.math3.complex;
/*    */ 
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class ComplexUtils {
/*    */   public static Complex polar2Complex(double r, double theta) {
/* 65 */     if (r < 0.0D)
/* 66 */       throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_COMPLEX_MODULE, new Object[] { Double.valueOf(r) }); 
/* 69 */     return new Complex(r * FastMath.cos(theta), r * FastMath.sin(theta));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\complex\ComplexUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */