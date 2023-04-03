/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.Serializable;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class IdentityTransform extends AbstractMathTransform implements LinearTransform, Serializable {
/*     */   private static final long serialVersionUID = -5339040282922138164L;
/*     */   
/*     */   private final int dimension;
/*     */   
/*  64 */   private static final LinearTransform[] POOL = new LinearTransform[8];
/*     */   
/*     */   protected IdentityTransform(int dimension) {
/*  70 */     this.dimension = dimension;
/*     */   }
/*     */   
/*     */   public static synchronized LinearTransform create(int dimension) {
/*     */     LinearTransform candidate;
/*  78 */     if (dimension < POOL.length) {
/*  79 */       LinearTransform linearTransform = POOL[dimension];
/*  80 */       if (linearTransform != null)
/*  81 */         return linearTransform; 
/*     */     } 
/*  84 */     switch (dimension) {
/*     */       case 1:
/*  85 */         candidate = LinearTransform1D.IDENTITY;
/*     */         break;
/*     */       case 2:
/*  86 */         candidate = new AffineTransform2D(new AffineTransform());
/*     */         break;
/*     */       default:
/*  87 */         candidate = new IdentityTransform(dimension);
/*     */         break;
/*     */     } 
/*  89 */     if (dimension < POOL.length)
/*  90 */       POOL[dimension] = candidate; 
/*  92 */     return candidate;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isIdentity(double tolerance) {
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 116 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 123 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 131 */     return ProjectiveTransform.ProviderAffine.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 141 */     return ProjectiveTransform.getParameterValues(getMatrix());
/*     */   }
/*     */   
/*     */   public Matrix getMatrix() {
/* 148 */     return (Matrix)MatrixFactory.create(this.dimension + 1);
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) {
/* 157 */     return (Matrix)MatrixFactory.create(this.dimension);
/*     */   }
/*     */   
/*     */   public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst) {
/* 168 */     if (ptSrc.getDimension() == this.dimension) {
/* 169 */       if (ptDst == null)
/* 170 */         return (DirectPosition)new GeneralDirectPosition(ptSrc); 
/* 172 */       if (ptDst.getDimension() == this.dimension) {
/* 173 */         for (int i = 0; i < this.dimension; i++)
/* 174 */           ptDst.setOrdinate(i, ptSrc.getOrdinate(i)); 
/* 176 */         return ptDst;
/*     */       } 
/*     */     } 
/*     */     try {
/* 181 */       return super.transform(ptSrc, ptDst);
/* 182 */     } catch (TransformException e) {
/* 183 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 194 */     System.arraycopy(srcPts, srcOff, dstPts, dstOff, numPts * this.dimension);
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 203 */     System.arraycopy(srcPts, srcOff, dstPts, dstOff, numPts * this.dimension);
/*     */   }
/*     */   
/*     */   public MathTransform inverse() {
/* 212 */     return this;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 222 */     return 1861010892 + this.dimension;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 231 */     if (object == this)
/* 233 */       return true; 
/* 235 */     if (super.equals(object)) {
/* 236 */       IdentityTransform that = (IdentityTransform)object;
/* 237 */       return (this.dimension == that.dimension);
/*     */     } 
/* 239 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\IdentityTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */