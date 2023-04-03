/*    */ package org.geotools.referencing.operation.transform;
/*    */ 
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.MathTransform2D;
/*    */ import org.opengis.referencing.operation.Matrix;
/*    */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*    */ 
/*    */ final class ProjectiveTransform2D extends ProjectiveTransform implements MathTransform2D {
/*    */   private static final long serialVersionUID = -3101392684596817045L;
/*    */   
/*    */   public ProjectiveTransform2D(Matrix matrix) {
/* 41 */     super(matrix);
/*    */   }
/*    */   
/*    */   public MathTransform2D inverse() throws NoninvertibleTransformException {
/* 49 */     return (MathTransform2D)super.inverse();
/*    */   }
/*    */   
/*    */   MathTransform2D createInverse(Matrix matrix) {
/* 57 */     return new ProjectiveTransform2D(matrix);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ProjectiveTransform2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */