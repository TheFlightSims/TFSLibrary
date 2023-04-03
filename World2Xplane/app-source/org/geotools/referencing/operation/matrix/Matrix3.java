/*     */ package org.geotools.referencing.operation.matrix;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import javax.vecmath.Matrix3d;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class Matrix3 extends Matrix3d implements XMatrix {
/*     */   private static final long serialVersionUID = 8902061778871586611L;
/*     */   
/*     */   public static final int SIZE = 3;
/*     */   
/*     */   public Matrix3() {
/*  52 */     setIdentity();
/*     */   }
/*     */   
/*     */   public Matrix3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
/*  62 */     super(m00, m01, m02, m10, m11, m12, m20, m21, m22);
/*     */   }
/*     */   
/*     */   public Matrix3(AffineTransform transform) {
/*  71 */     setMatrix(transform);
/*     */   }
/*     */   
/*     */   public Matrix3(Matrix matrix) {
/*  79 */     if (matrix.getNumRow() != 3 || matrix.getNumCol() != 3)
/*  80 */       throw new IllegalArgumentException(Errors.format(70)); 
/*  82 */     for (int j = 0; j < 3; j++) {
/*  83 */       for (int i = 0; i < 3; i++)
/*  84 */         setElement(j, i, matrix.getElement(j, i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final int getNumRow() {
/*  94 */     return 3;
/*     */   }
/*     */   
/*     */   public final int getNumCol() {
/* 102 */     return 3;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity() {
/* 109 */     for (int j = 0; j < 3; j++) {
/* 110 */       for (int i = 0; i < 3; i++) {
/* 111 */         if (getElement(j, i) != ((i == j) ? true : false))
/* 112 */           return false; 
/*     */       } 
/*     */     } 
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity(double tolerance) {
/* 123 */     return GeneralMatrix.isIdentity(this, tolerance);
/*     */   }
/*     */   
/*     */   public final boolean isAffine() {
/* 130 */     return (this.m20 == 0.0D && this.m21 == 0.0D && this.m22 == 1.0D);
/*     */   }
/*     */   
/*     */   public final boolean isNaN() {
/* 139 */     return (Double.isNaN(this.m00) || Double.isNaN(this.m01) || Double.isNaN(this.m02) || Double.isNaN(this.m10) || Double.isNaN(this.m11) || Double.isNaN(this.m12) || Double.isNaN(this.m20) || Double.isNaN(this.m21) || Double.isNaN(this.m22));
/*     */   }
/*     */   
/*     */   public final void multiply(Matrix matrix) {
/*     */     Matrix3d m;
/* 149 */     if (matrix instanceof Matrix3d) {
/* 150 */       m = (Matrix3d)matrix;
/*     */     } else {
/* 152 */       m = new Matrix3(matrix);
/*     */     } 
/* 154 */     mul(m);
/*     */   }
/*     */   
/*     */   public void setMatrix(AffineTransform transform) {
/* 163 */     this.m00 = transform.getScaleX();
/* 163 */     this.m01 = transform.getShearX();
/* 163 */     this.m02 = transform.getTranslateX();
/* 164 */     this.m10 = transform.getShearY();
/* 164 */     this.m11 = transform.getScaleY();
/* 164 */     this.m12 = transform.getTranslateY();
/* 165 */     this.m20 = 0.0D;
/* 165 */     this.m21 = 0.0D;
/* 165 */     this.m22 = 1.0D;
/*     */   }
/*     */   
/*     */   public boolean equalsAffine(AffineTransform transform) {
/* 174 */     return (this.m00 == transform.getScaleX() && this.m01 == transform.getShearX() && this.m02 == transform.getTranslateX() && this.m10 == transform.getShearY() && this.m11 == transform.getScaleY() && this.m12 == transform.getTranslateY() && this.m20 == 0.0D && this.m21 == 0.0D && this.m22 == 1.0D);
/*     */   }
/*     */   
/*     */   public boolean equals(Matrix matrix, double tolerance) {
/* 183 */     return GeneralMatrix.epsilonEquals(this, matrix, tolerance);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 192 */     return GeneralMatrix.toString(this);
/*     */   }
/*     */   
/*     */   public Matrix3 clone() {
/* 200 */     return (Matrix3)super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\Matrix3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */