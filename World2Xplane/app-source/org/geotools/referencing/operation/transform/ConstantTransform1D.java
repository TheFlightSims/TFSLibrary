/*    */ package org.geotools.referencing.operation.transform;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ final class ConstantTransform1D extends LinearTransform1D {
/*    */   private static final long serialVersionUID = -1583675681650985947L;
/*    */   
/*    */   protected ConstantTransform1D(double offset) {
/* 45 */     super(0.0D, offset);
/*    */   }
/*    */   
/*    */   public double transform(double value) {
/* 53 */     return this.offset;
/*    */   }
/*    */   
/*    */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 63 */     Arrays.fill(dstPts, dstOff, dstOff + numPts, (float)this.offset);
/*    */   }
/*    */   
/*    */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 73 */     Arrays.fill(dstPts, dstOff, dstOff + numPts, this.offset);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ConstantTransform1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */