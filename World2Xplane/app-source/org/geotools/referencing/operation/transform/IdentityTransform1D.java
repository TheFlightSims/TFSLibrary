/*    */ package org.geotools.referencing.operation.transform;
/*    */ 
/*    */ final class IdentityTransform1D extends LinearTransform1D {
/*    */   private static final long serialVersionUID = -7378774584053573789L;
/*    */   
/* 38 */   public static final LinearTransform1D ONE = new IdentityTransform1D();
/*    */   
/*    */   private IdentityTransform1D() {
/* 44 */     super(1.0D, 0.0D);
/*    */   }
/*    */   
/*    */   public double transform(double value) {
/* 52 */     return value;
/*    */   }
/*    */   
/*    */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 62 */     System.arraycopy(srcPts, srcOff, dstPts, dstOff, numPts);
/*    */   }
/*    */   
/*    */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 72 */     System.arraycopy(srcPts, srcOff, dstPts, dstOff, numPts);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\IdentityTransform1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */