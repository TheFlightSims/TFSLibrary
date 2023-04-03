/*     */ package org.geotools.referencing.operation.matrix;
/*     */ 
/*     */ import javax.vecmath.Matrix4d;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class Matrix4 extends Matrix4d implements XMatrix {
/*     */   private static final long serialVersionUID = 5685762518066856310L;
/*     */   
/*     */   public static final int SIZE = 4;
/*     */   
/*     */   public Matrix4() {
/*  52 */     setIdentity();
/*     */   }
/*     */   
/*     */   public Matrix4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) {
/*  63 */     super(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
/*     */   }
/*     */   
/*     */   public Matrix4(Matrix matrix) {
/*  74 */     if (matrix.getNumRow() != 4 || matrix.getNumCol() != 4)
/*  75 */       throw new IllegalArgumentException(Errors.format(70)); 
/*  77 */     for (int j = 0; j < 4; j++) {
/*  78 */       for (int i = 0; i < 4; i++)
/*  79 */         setElement(j, i, matrix.getElement(j, i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final int getNumRow() {
/*  89 */     return 4;
/*     */   }
/*     */   
/*     */   public final int getNumCol() {
/*  97 */     return 4;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity() {
/* 104 */     for (int j = 0; j < 4; j++) {
/* 105 */       for (int i = 0; i < 4; i++) {
/* 106 */         if (getElement(j, i) != ((i == j) ? true : false))
/* 107 */           return false; 
/*     */       } 
/*     */     } 
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity(double tolerance) {
/* 118 */     return GeneralMatrix.isIdentity(this, tolerance);
/*     */   }
/*     */   
/*     */   public final boolean isAffine() {
/* 125 */     return (this.m30 == 0.0D && this.m31 == 0.0D && this.m32 == 0.0D && this.m33 == 1.0D);
/*     */   }
/*     */   
/*     */   public final void multiply(Matrix matrix) {
/*     */     Matrix4d m;
/* 133 */     if (matrix instanceof Matrix4d) {
/* 134 */       m = (Matrix4d)matrix;
/*     */     } else {
/* 136 */       m = new Matrix4(matrix);
/*     */     } 
/* 138 */     mul(m);
/*     */   }
/*     */   
/*     */   public boolean equals(Matrix matrix, double tolerance) {
/* 145 */     return GeneralMatrix.epsilonEquals(this, matrix, tolerance);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 154 */     return GeneralMatrix.toString(this);
/*     */   }
/*     */   
/*     */   public Matrix4 clone() {
/* 162 */     return (Matrix4)super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\Matrix4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */