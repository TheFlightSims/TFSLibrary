/*    */ package org.geotools.referencing.operation.transform;
/*    */ 
/*    */ import org.opengis.geometry.DirectPosition;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.TransformException;
/*    */ 
/*    */ class ConcatenatedTransformDirect extends ConcatenatedTransform {
/*    */   private static final long serialVersionUID = -3568975979013908920L;
/*    */   
/*    */   public ConcatenatedTransformDirect(MathTransform transform1, MathTransform transform2) {
/* 46 */     super(transform1, transform2);
/*    */   }
/*    */   
/*    */   boolean isValid() {
/* 54 */     return (super.isValid() && this.transform1.getSourceDimensions() == this.transform1.getTargetDimensions() && this.transform2.getSourceDimensions() == this.transform2.getTargetDimensions());
/*    */   }
/*    */   
/*    */   public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst) throws TransformException {
/* 66 */     assert isValid();
/* 67 */     ptDst = this.transform1.transform(ptSrc, ptDst);
/* 68 */     return this.transform2.transform(ptDst, ptDst);
/*    */   }
/*    */   
/*    */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 79 */     assert isValid();
/* 80 */     this.transform1.transform(srcPts, srcOff, dstPts, dstOff, numPts);
/* 81 */     this.transform2.transform(dstPts, dstOff, dstPts, dstOff, numPts);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ConcatenatedTransformDirect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */