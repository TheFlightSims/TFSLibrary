/*    */ package org.geotools.referencing.operation.transform;
/*    */ 
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.MathTransform2D;
/*    */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*    */ 
/*    */ final class ConcatenatedTransform2D extends ConcatenatedTransform implements MathTransform2D {
/*    */   private static final long serialVersionUID = -7307709788564866500L;
/*    */   
/*    */   public ConcatenatedTransform2D(MathTransform transform1, MathTransform transform2) {
/* 44 */     super(transform1, transform2);
/*    */   }
/*    */   
/*    */   boolean isValid() {
/* 52 */     return (super.isValid() && getSourceDimensions() == 2 && getTargetDimensions() == 2);
/*    */   }
/*    */   
/*    */   public MathTransform2D inverse() throws NoninvertibleTransformException {
/* 60 */     return (MathTransform2D)super.inverse();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ConcatenatedTransform2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */