/*     */ package org.geotools.referencing.operation.matrix;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.vecmath.SingularMatrixException;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class Matrix1 implements XMatrix, Serializable {
/*     */   private static final long serialVersionUID = -4829171016106097031L;
/*     */   
/*     */   public double m00;
/*     */   
/*     */   public static final int SIZE = 1;
/*     */   
/*     */   public Matrix1() {
/*  51 */     this.m00 = 1.0D;
/*     */   }
/*     */   
/*     */   public Matrix1(double m00) {
/*  58 */     this.m00 = m00;
/*     */   }
/*     */   
/*     */   public Matrix1(Matrix matrix) {
/*  66 */     if (matrix.getNumRow() != 1 || matrix.getNumCol() != 1)
/*  67 */       throw new IllegalArgumentException(Errors.format(70)); 
/*  69 */     this.m00 = matrix.getElement(0, 0);
/*     */   }
/*     */   
/*     */   public final int getNumRow() {
/*  77 */     return 1;
/*     */   }
/*     */   
/*     */   public final int getNumCol() {
/*  85 */     return 1;
/*     */   }
/*     */   
/*     */   public final double getElement(int row, int col) {
/*  92 */     if (row == 0 && col == 0)
/*  93 */       return this.m00; 
/*  95 */     throw new IndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public final void setElement(int row, int col, double value) {
/* 103 */     if (row == 0 && col == 0) {
/* 104 */       this.m00 = value;
/*     */     } else {
/* 106 */       throw new IndexOutOfBoundsException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void setZero() {
/* 114 */     this.m00 = 0.0D;
/*     */   }
/*     */   
/*     */   public final void setIdentity() {
/* 121 */     this.m00 = 1.0D;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity() {
/* 128 */     return (this.m00 == 1.0D);
/*     */   }
/*     */   
/*     */   public final boolean isIdentity(double tolerance) {
/* 135 */     return (Math.abs(this.m00 - 1.0D) <= Math.abs(tolerance));
/*     */   }
/*     */   
/*     */   public final boolean isAffine() {
/* 142 */     return (this.m00 == 1.0D);
/*     */   }
/*     */   
/*     */   public final void negate() {
/* 149 */     this.m00 = -this.m00;
/*     */   }
/*     */   
/*     */   public final void transpose() {}
/*     */   
/*     */   public final void invert() {
/* 163 */     if (this.m00 == 0.0D)
/* 164 */       throw new SingularMatrixException(); 
/* 166 */     this.m00 = 1.0D / this.m00;
/*     */   }
/*     */   
/*     */   public final void multiply(Matrix matrix) {
/* 173 */     if (matrix.getNumRow() != 1 || matrix.getNumCol() != 1)
/* 174 */       throw new IllegalArgumentException(Errors.format(70)); 
/* 176 */     this.m00 *= matrix.getElement(0, 0);
/*     */   }
/*     */   
/*     */   public boolean equals(Matrix matrix, double tolerance) {
/* 183 */     return GeneralMatrix.epsilonEquals(this, matrix, tolerance);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 192 */     if (object != null && object.getClass().equals(getClass())) {
/* 193 */       Matrix1 that = (Matrix1)object;
/* 194 */       return (Double.doubleToLongBits(this.m00) == Double.doubleToLongBits(that.m00));
/*     */     } 
/* 196 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 204 */     return (int)(Double.doubleToLongBits(this.m00) ^ 0xBCFB568C01624679L);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 213 */     return GeneralMatrix.toString(this);
/*     */   }
/*     */   
/*     */   public Matrix1 clone() {
/*     */     try {
/* 222 */       return (Matrix1)super.clone();
/* 223 */     } catch (CloneNotSupportedException e) {
/* 225 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\Matrix1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */