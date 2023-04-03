/*     */ package org.geotools.referencing.operation.matrix;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.vecmath.SingularMatrixException;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class Matrix2 implements XMatrix, Serializable {
/*     */   private static final long serialVersionUID = 7116561372481474290L;
/*     */   
/*     */   public static final int SIZE = 2;
/*     */   
/*     */   public double m00;
/*     */   
/*     */   public double m01;
/*     */   
/*     */   public double m10;
/*     */   
/*     */   public double m11;
/*     */   
/*     */   public Matrix2() {
/*  59 */     this.m00 = this.m11 = 1.0D;
/*     */   }
/*     */   
/*     */   public Matrix2(double m00, double m01, double m10, double m11) {
/*  68 */     this.m00 = m00;
/*  69 */     this.m01 = m01;
/*  70 */     this.m10 = m10;
/*  71 */     this.m11 = m11;
/*     */   }
/*     */   
/*     */   public Matrix2(Matrix matrix) {
/*  79 */     if (matrix.getNumRow() != 2 || matrix.getNumCol() != 2)
/*  80 */       throw new IllegalArgumentException(Errors.format(70)); 
/*  82 */     this.m00 = matrix.getElement(0, 0);
/*  83 */     this.m01 = matrix.getElement(0, 1);
/*  84 */     this.m10 = matrix.getElement(1, 0);
/*  85 */     this.m11 = matrix.getElement(1, 1);
/*     */   }
/*     */   
/*     */   public final int getNumRow() {
/*  93 */     return 2;
/*     */   }
/*     */   
/*     */   public final int getNumCol() {
/* 101 */     return 2;
/*     */   }
/*     */   
/*     */   public final double getElement(int row, int col) {
/* 108 */     switch (row) {
/*     */       case 0:
/* 110 */         switch (col) {
/*     */           case 0:
/* 111 */             return this.m00;
/*     */           case 1:
/* 112 */             return this.m01;
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 117 */         switch (col) {
/*     */           case 0:
/* 118 */             return this.m10;
/*     */           case 1:
/* 119 */             return this.m11;
/*     */         } 
/*     */         break;
/*     */     } 
/* 124 */     throw new IndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public final void setElement(int row, int col, double value) {
/* 131 */     switch (row) {
/*     */       case 0:
/* 133 */         switch (col) {
/*     */           case 0:
/* 134 */             this.m00 = value;
/*     */             return;
/*     */           case 1:
/* 135 */             this.m01 = value;
/*     */             return;
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 140 */         switch (col) {
/*     */           case 0:
/* 141 */             this.m10 = value;
/*     */             return;
/*     */           case 1:
/* 142 */             this.m11 = value;
/*     */             return;
/*     */         } 
/*     */         break;
/*     */     } 
/* 147 */     throw new IndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public final void setZero() {
/* 154 */     this.m00 = this.m01 = this.m10 = this.m11 = 0.0D;
/*     */   }
/*     */   
/*     */   public final void setIdentity() {
/* 161 */     this.m01 = this.m10 = 0.0D;
/* 162 */     this.m00 = this.m11 = 1.0D;
/* 163 */     if (!$assertionsDisabled && !isIdentity())
/* 163 */       throw new AssertionError(); 
/*     */   }
/*     */   
/*     */   public final boolean isIdentity() {
/* 170 */     return (this.m01 == 0.0D && this.m10 == 0.0D && this.m00 == 1.0D && this.m11 == 1.0D);
/*     */   }
/*     */   
/*     */   public final boolean isIdentity(double tolerance) {
/* 177 */     return GeneralMatrix.isIdentity(this, tolerance);
/*     */   }
/*     */   
/*     */   public final boolean isAffine() {
/* 184 */     return (this.m10 == 0.0D && this.m11 == 1.0D);
/*     */   }
/*     */   
/*     */   public final void negate() {
/* 191 */     this.m00 = -this.m00;
/* 192 */     this.m01 = -this.m01;
/* 193 */     this.m10 = -this.m10;
/* 194 */     this.m11 = -this.m11;
/*     */   }
/*     */   
/*     */   public final void transpose() {
/* 201 */     double swap = this.m10;
/* 202 */     this.m10 = this.m01;
/* 203 */     this.m01 = swap;
/*     */   }
/*     */   
/*     */   public final void invert() {
/* 210 */     double det = this.m00 * this.m11 - this.m01 * this.m10;
/* 211 */     if (det == 0.0D)
/* 212 */       throw new SingularMatrixException(); 
/* 214 */     double swap = this.m00;
/* 215 */     this.m00 = this.m11 / det;
/* 216 */     this.m11 = swap / det;
/* 217 */     this.m10 = -this.m10 / det;
/* 218 */     this.m01 = -this.m01 / det;
/*     */   }
/*     */   
/*     */   public final void multiply(Matrix matrix) {
/*     */     Matrix2 k;
/* 226 */     if (matrix instanceof Matrix2) {
/* 227 */       k = (Matrix2)matrix;
/*     */     } else {
/* 229 */       k = new Matrix2(matrix);
/*     */     } 
/* 232 */     double m0 = this.m00, m1 = this.m01;
/* 233 */     this.m00 = m0 * k.m00 + m1 * k.m10;
/* 234 */     this.m01 = m0 * k.m01 + m1 * k.m11;
/* 235 */     m0 = this.m10;
/* 235 */     m1 = this.m11;
/* 236 */     this.m10 = m0 * k.m00 + m1 * k.m10;
/* 237 */     this.m11 = m0 * k.m01 + m1 * k.m11;
/*     */   }
/*     */   
/*     */   public boolean equals(Matrix matrix, double tolerance) {
/* 244 */     return GeneralMatrix.epsilonEquals(this, matrix, tolerance);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 253 */     if (object != null && object.getClass().equals(getClass())) {
/* 254 */       Matrix2 that = (Matrix2)object;
/* 255 */       return (Double.doubleToLongBits(this.m00) == Double.doubleToLongBits(that.m00) && Double.doubleToLongBits(this.m01) == Double.doubleToLongBits(that.m01) && Double.doubleToLongBits(this.m10) == Double.doubleToLongBits(that.m10) && Double.doubleToLongBits(this.m11) == Double.doubleToLongBits(that.m11));
/*     */     } 
/* 260 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 268 */     return (int)(Double.doubleToLongBits(this.m00) + 37L * Double.doubleToLongBits(this.m01) + 37L * Double.doubleToLongBits(this.m10) + 37L * Double.doubleToLongBits(this.m11) ^ 0x62C31ADCF5064EF2L);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 281 */     return GeneralMatrix.toString(this);
/*     */   }
/*     */   
/*     */   public Matrix2 clone() {
/*     */     try {
/* 290 */       return (Matrix2)super.clone();
/* 291 */     } catch (CloneNotSupportedException e) {
/* 293 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\Matrix2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */