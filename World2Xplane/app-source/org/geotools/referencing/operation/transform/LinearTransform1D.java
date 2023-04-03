/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.matrix.Matrix1;
/*     */ import org.geotools.referencing.operation.matrix.Matrix2;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform1D;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class LinearTransform1D extends AbstractMathTransform implements MathTransform1D, LinearTransform, Serializable {
/*     */   private static final long serialVersionUID = -7595037195668813000L;
/*     */   
/*  66 */   public static final LinearTransform1D IDENTITY = IdentityTransform1D.ONE;
/*     */   
/*     */   public final double scale;
/*     */   
/*     */   public final double offset;
/*     */   
/*     */   private transient MathTransform1D inverse;
/*     */   
/*     */   protected LinearTransform1D(double scale, double offset) {
/*  92 */     this.scale = scale;
/*  93 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public static LinearTransform1D create(double scale, double offset) {
/* 103 */     if (scale == 0.0D)
/* 104 */       return new ConstantTransform1D(offset); 
/* 106 */     if (scale == 1.0D && offset == 0.0D)
/* 107 */       return IDENTITY; 
/* 109 */     return new LinearTransform1D(scale, offset);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 117 */     return ProjectiveTransform.ProviderAffine.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 129 */     return ProjectiveTransform.getParameterValues(getMatrix());
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 136 */     return 1;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 143 */     return 1;
/*     */   }
/*     */   
/*     */   public Matrix getMatrix() {
/* 150 */     return (Matrix)new Matrix2(this.scale, this.offset, 0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public MathTransform1D inverse() throws NoninvertibleTransformException {
/* 158 */     if (this.inverse == null)
/* 159 */       if (isIdentity()) {
/* 160 */         this.inverse = this;
/* 161 */       } else if (this.scale != 0.0D) {
/* 163 */         LinearTransform1D inverse = create(1.0D / this.scale, -this.offset / this.scale);
/* 164 */         inverse.inverse = this;
/* 165 */         this.inverse = inverse;
/*     */       } else {
/* 167 */         this.inverse = (MathTransform1D)super.inverse();
/*     */       }  
/* 170 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 178 */     return isIdentity(0.0D);
/*     */   }
/*     */   
/*     */   public boolean isIdentity(double tolerance) {
/* 189 */     tolerance = Math.abs(tolerance);
/* 190 */     return (Math.abs(this.offset) <= tolerance && Math.abs(this.scale - 1.0D) <= tolerance);
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) throws TransformException {
/* 201 */     return (Matrix)new Matrix1(this.scale);
/*     */   }
/*     */   
/*     */   public double derivative(double value) {
/* 208 */     return this.scale;
/*     */   }
/*     */   
/*     */   public double transform(double value) {
/* 215 */     return this.offset + this.scale * value;
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 225 */     if (srcPts != dstPts || srcOff >= dstOff) {
/* 226 */       while (--numPts >= 0)
/* 227 */         dstPts[dstOff++] = (float)(this.offset + this.scale * srcPts[srcOff++]); 
/*     */     } else {
/* 230 */       srcOff += numPts;
/* 231 */       dstOff += numPts;
/* 232 */       while (--numPts >= 0)
/* 233 */         dstPts[--dstOff] = (float)(this.offset + this.scale * srcPts[--srcOff]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 244 */     if (srcPts != dstPts || srcOff >= dstOff) {
/* 245 */       while (--numPts >= 0)
/* 246 */         dstPts[dstOff++] = this.offset + this.scale * srcPts[srcOff++]; 
/*     */     } else {
/* 249 */       srcOff += numPts;
/* 250 */       dstOff += numPts;
/* 251 */       while (--numPts >= 0)
/* 252 */         dstPts[--dstOff] = this.offset + this.scale * srcPts[--srcOff]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 265 */     long code = 675810104L + Double.doubleToRawLongBits(this.offset);
/* 266 */     code = code * 37L + Double.doubleToRawLongBits(this.scale);
/* 267 */     return (int)(code >>> 32L) ^ (int)code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 275 */     if (object == this)
/* 277 */       return true; 
/* 279 */     if (super.equals(object)) {
/* 280 */       LinearTransform1D that = (LinearTransform1D)object;
/* 281 */       return (Double.doubleToRawLongBits(this.scale) == Double.doubleToRawLongBits(that.scale) && Double.doubleToRawLongBits(this.offset) == Double.doubleToRawLongBits(that.offset));
/*     */     } 
/* 292 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\LinearTransform1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */