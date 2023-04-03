/*     */ package org.geotools.referencing.operation.matrix;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class AffineTransform2D extends AffineTransform implements Matrix {
/*     */   private static final long serialVersionUID = -9104194268576601386L;
/*     */   
/*     */   public static final int SIZE = 3;
/*     */   
/*     */   public AffineTransform2D() {}
/*     */   
/*     */   public AffineTransform2D(AffineTransform transform) {
/*  69 */     super(transform);
/*     */   }
/*     */   
/*     */   public AffineTransform2D(Matrix matrix) {
/*  77 */     if (matrix.getNumRow() != 3 || matrix.getNumCol() != 3)
/*  78 */       throw new IllegalArgumentException(Errors.format(70)); 
/*  80 */     for (int i = 0; i < 3; i++)
/*  81 */       checkLastRow(i, matrix.getElement(2, i)); 
/*  83 */     int c = 0;
/*  84 */     double[] values = new double[6];
/*  85 */     for (int j = 0; j < 2; j++) {
/*  86 */       for (int k = 0; k < 3; k++)
/*  87 */         values[c++] = matrix.getElement(j, k); 
/*     */     } 
/*  90 */     assert c == values.length : c;
/*  92 */     setTransform(values);
/*     */   }
/*     */   
/*     */   private void setTransform(double[] matrix) {
/*  99 */     setTransform(matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5]);
/*     */   }
/*     */   
/*     */   public final int getNumRow() {
/* 107 */     return 3;
/*     */   }
/*     */   
/*     */   public final int getNumCol() {
/* 115 */     return 3;
/*     */   }
/*     */   
/*     */   public double getElement(int row, int column) {
/* 126 */     switch (row) {
/*     */       case 0:
/* 128 */         switch (column) {
/*     */           case 0:
/* 129 */             return getScaleX();
/*     */           case 1:
/* 130 */             return getShearX();
/*     */           case 2:
/* 131 */             return getTranslateX();
/*     */         } 
/* 156 */         throw new IndexOutOfBoundsException(Errors.format(58, "row", Integer.valueOf(row)));
/*     */       case 1:
/*     */         switch (column) {
/*     */           case 0:
/*     */             return getShearY();
/*     */           case 1:
/*     */             return getScaleY();
/*     */           case 2:
/*     */             return getTranslateY();
/*     */         } 
/* 156 */         throw new IndexOutOfBoundsException(Errors.format(58, "row", Integer.valueOf(row)));
/*     */       case 2:
/*     */         switch (column) {
/*     */           case 0:
/*     */           case 1:
/*     */             return 0.0D;
/*     */           case 2:
/*     */             return 1.0D;
/*     */         } 
/* 156 */         throw new IndexOutOfBoundsException(Errors.format(58, "row", Integer.valueOf(row)));
/*     */     } 
/*     */     throw new IndexOutOfBoundsException(Errors.format(58, "column", Integer.valueOf(column)));
/*     */   }
/*     */   
/*     */   public void setElement(int row, int column, double value) {
/* 168 */     if (row < 0 || row >= 3)
/* 169 */       throw new IndexOutOfBoundsException(Errors.format(58, "row", Integer.valueOf(row))); 
/* 172 */     if (column < 0 || column >= 3)
/* 173 */       throw new IndexOutOfBoundsException(Errors.format(58, "column", Integer.valueOf(column))); 
/* 176 */     if (row == 2) {
/* 177 */       checkLastRow(column, value);
/*     */       return;
/*     */     } 
/* 180 */     double[] matrix = new double[6];
/* 181 */     getMatrix(matrix);
/* 182 */     matrix[row * 3 + column] = value;
/* 183 */     setTransform(matrix);
/* 184 */     assert Double.compare(getElement(row, column), value) == 0 : value;
/*     */   }
/*     */   
/*     */   private static void checkLastRow(int column, double value) throws IllegalArgumentException {
/* 195 */     if (value != ((column == 2) ? true : false))
/* 196 */       throw new IllegalArgumentException(Errors.format(58, "matrix[2," + column + ']', Double.valueOf(value))); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 207 */     return GeneralMatrix.toString(this);
/*     */   }
/*     */   
/*     */   public AffineTransform2D clone() {
/* 215 */     return (AffineTransform2D)super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 220 */     if (!(obj instanceof AffineTransform))
/* 221 */       return false; 
/* 224 */     AffineTransform a = (AffineTransform)obj;
/* 226 */     return (Utilities.equals(getScaleX(), a.getScaleX()) && Utilities.equals(getScaleY(), a.getScaleY()) && Utilities.equals(getShearX(), a.getShearY()) && Utilities.equals(getTranslateX(), a.getTranslateX()) && Utilities.equals(getTranslateY(), a.getTranslateY()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\AffineTransform2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */