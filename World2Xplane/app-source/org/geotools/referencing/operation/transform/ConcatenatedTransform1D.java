/*    */ package org.geotools.referencing.operation.transform;
/*    */ 
/*    */ import org.geotools.geometry.DirectPosition1D;
/*    */ import org.opengis.geometry.DirectPosition;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.MathTransform1D;
/*    */ import org.opengis.referencing.operation.Matrix;
/*    */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*    */ import org.opengis.referencing.operation.TransformException;
/*    */ 
/*    */ final class ConcatenatedTransform1D extends ConcatenatedTransform implements MathTransform1D {
/*    */   private static final long serialVersionUID = 8150427971141078395L;
/*    */   
/*    */   public ConcatenatedTransform1D(MathTransform transform1, MathTransform transform2) {
/* 47 */     super(transform1, transform2);
/*    */   }
/*    */   
/*    */   boolean isValid() {
/* 55 */     return (super.isValid() && getSourceDimensions() == 1 && getTargetDimensions() == 1);
/*    */   }
/*    */   
/*    */   public double transform(double value) throws TransformException {
/* 62 */     double[] values = { value };
/* 63 */     double[] buffer = { this.transform1.getTargetDimensions() };
/* 64 */     this.transform1.transform(values, 0, buffer, 0, 1);
/* 65 */     this.transform2.transform(buffer, 0, values, 0, 1);
/* 66 */     return values[0];
/*    */   }
/*    */   
/*    */   public double derivative(double value) throws TransformException {
/* 73 */     DirectPosition1D p = new DirectPosition1D(value);
/* 74 */     Matrix m = derivative((DirectPosition)p);
/* 75 */     assert m.getNumRow() == 1 && m.getNumCol() == 1;
/* 76 */     return m.getElement(0, 0);
/*    */   }
/*    */   
/*    */   public MathTransform1D inverse() throws NoninvertibleTransformException {
/* 84 */     return (MathTransform1D)super.inverse();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ConcatenatedTransform1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */