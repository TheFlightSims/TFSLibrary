/*    */ package org.geotools.referencing.operation.transform;
/*    */ 
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.MathTransform1D;
/*    */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*    */ import org.opengis.referencing.operation.TransformException;
/*    */ 
/*    */ final class ConcatenatedTransformDirect1D extends ConcatenatedTransformDirect implements MathTransform1D {
/*    */   private static final long serialVersionUID = 1064398659892864966L;
/*    */   
/*    */   private final MathTransform1D transform1;
/*    */   
/*    */   private final MathTransform1D transform2;
/*    */   
/*    */   public ConcatenatedTransformDirect1D(MathTransform1D transform1, MathTransform1D transform2) {
/* 60 */     super((MathTransform)transform1, (MathTransform)transform2);
/* 61 */     this.transform1 = transform1;
/* 62 */     this.transform2 = transform2;
/*    */   }
/*    */   
/*    */   boolean isValid() {
/* 70 */     return (super.isValid() && getSourceDimensions() == 1 && getTargetDimensions() == 1);
/*    */   }
/*    */   
/*    */   public double transform(double value) throws TransformException {
/* 77 */     return this.transform2.transform(this.transform1.transform(value));
/*    */   }
/*    */   
/*    */   public double derivative(double value) throws TransformException {
/* 84 */     double value1 = this.transform1.derivative(value);
/* 85 */     double value2 = this.transform2.derivative(this.transform1.transform(value));
/* 86 */     return value2 * value1;
/*    */   }
/*    */   
/*    */   public MathTransform1D inverse() throws NoninvertibleTransformException {
/* 94 */     return (MathTransform1D)super.inverse();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ConcatenatedTransformDirect1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */