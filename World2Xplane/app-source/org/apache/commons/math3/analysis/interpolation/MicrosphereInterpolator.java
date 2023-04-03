/*    */ package org.apache.commons.math3.analysis.interpolation;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*    */ import org.apache.commons.math3.exception.NotPositiveException;
/*    */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*    */ import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
/*    */ 
/*    */ public class MicrosphereInterpolator implements MultivariateInterpolator {
/*    */   public static final int DEFAULT_MICROSPHERE_ELEMENTS = 2000;
/*    */   
/*    */   public static final int DEFAULT_BRIGHTNESS_EXPONENT = 2;
/*    */   
/*    */   private final int microsphereElements;
/*    */   
/*    */   private final int brightnessExponent;
/*    */   
/*    */   public MicrosphereInterpolator() {
/* 60 */     this(2000, 2);
/*    */   }
/*    */   
/*    */   public MicrosphereInterpolator(int elements, int exponent) {
/* 72 */     if (exponent < 0)
/* 73 */       throw new NotPositiveException(Integer.valueOf(exponent)); 
/* 75 */     if (elements <= 0)
/* 76 */       throw new NotStrictlyPositiveException(Integer.valueOf(elements)); 
/* 79 */     this.microsphereElements = elements;
/* 80 */     this.brightnessExponent = exponent;
/*    */   }
/*    */   
/*    */   public MultivariateFunction interpolate(double[][] xval, double[] yval) {
/* 88 */     UnitSphereRandomVectorGenerator rand = new UnitSphereRandomVectorGenerator((xval[0]).length);
/* 90 */     return new MicrosphereInterpolatingFunction(xval, yval, this.brightnessExponent, this.microsphereElements, rand);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\MicrosphereInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */