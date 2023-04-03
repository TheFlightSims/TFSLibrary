/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class MathTransformProxy implements MathTransform, Serializable {
/*     */   private static final long serialVersionUID = 8844242705205498128L;
/*     */   
/*     */   public final MathTransform transform;
/*     */   
/*     */   protected MathTransformProxy(MathTransform transform) {
/*  68 */     this.transform = transform;
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/*  75 */     return this.transform.getTargetDimensions();
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/*  82 */     return this.transform.getSourceDimensions();
/*     */   }
/*     */   
/*     */   public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst) throws MismatchedDimensionException, TransformException {
/*  95 */     return this.transform.transform(ptSrc, ptDst);
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 105 */     this.transform.transform(srcPts, srcOff, dstPts, dstOff, numPts);
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
/* 115 */     this.transform.transform(srcPts, srcOff, dstPts, dstOff, numPts);
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 128 */     ((AbstractMathTransform)this.transform).transform(srcPts, srcOff, dstPts, dstOff, numPts);
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
/* 141 */     ((AbstractMathTransform)this.transform).transform(srcPts, srcOff, dstPts, dstOff, numPts);
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) throws TransformException {
/* 148 */     return this.transform.derivative(point);
/*     */   }
/*     */   
/*     */   public MathTransform inverse() throws NoninvertibleTransformException {
/* 155 */     return this.transform.inverse();
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 162 */     return this.transform.isIdentity();
/*     */   }
/*     */   
/*     */   public String toWKT() throws UnsupportedOperationException {
/* 171 */     return this.transform.toWKT();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 179 */     return this.transform.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 191 */     if (object != null && object.getClass().equals(getClass())) {
/* 192 */       MathTransformProxy that = (MathTransformProxy)object;
/* 193 */       return Utilities.equals(this.transform, that.transform);
/*     */     } 
/* 195 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 203 */     return this.transform.hashCode() ^ 0xAF1D1110;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\MathTransformProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */