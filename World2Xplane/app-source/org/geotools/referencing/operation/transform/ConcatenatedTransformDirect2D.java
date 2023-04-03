/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ final class ConcatenatedTransformDirect2D extends ConcatenatedTransformDirect implements MathTransform2D {
/*     */   private static final long serialVersionUID = 6009454091075588885L;
/*     */   
/*     */   private final MathTransform2D transform1;
/*     */   
/*     */   private final MathTransform2D transform2;
/*     */   
/*     */   public ConcatenatedTransformDirect2D(MathTransform2D transform1, MathTransform2D transform2) {
/*  66 */     super((MathTransform)transform1, (MathTransform)transform2);
/*  67 */     this.transform1 = transform1;
/*  68 */     this.transform2 = transform2;
/*     */   }
/*     */   
/*     */   boolean isValid() {
/*  76 */     return (super.isValid() && getSourceDimensions() == 2 && getTargetDimensions() == 2);
/*     */   }
/*     */   
/*     */   public Point2D transform(Point2D ptSrc, Point2D ptDst) throws TransformException {
/*  85 */     assert isValid();
/*  86 */     ptDst = this.transform1.transform(ptSrc, ptDst);
/*  87 */     return this.transform2.transform(ptDst, ptDst);
/*     */   }
/*     */   
/*     */   public Shape createTransformedShape(Shape shape) throws TransformException {
/*  95 */     assert isValid();
/*  96 */     return this.transform2.createTransformedShape(this.transform1.createTransformedShape(shape));
/*     */   }
/*     */   
/*     */   public Matrix derivative(Point2D point) throws TransformException {
/* 108 */     XMatrix matrix1 = toXMatrix(this.transform1.derivative(point));
/* 109 */     XMatrix matrix2 = toXMatrix(this.transform2.derivative(this.transform1.transform(point, null)));
/* 110 */     matrix2.multiply((Matrix)matrix1);
/* 111 */     return (Matrix)matrix2;
/*     */   }
/*     */   
/*     */   public MathTransform2D inverse() throws NoninvertibleTransformException {
/* 119 */     return (MathTransform2D)super.inverse();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ConcatenatedTransformDirect2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */