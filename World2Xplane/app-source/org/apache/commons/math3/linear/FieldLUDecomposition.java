/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ 
/*     */ public class FieldLUDecomposition<T extends FieldElement<T>> {
/*     */   private final Field<T> field;
/*     */   
/*     */   private T[][] lu;
/*     */   
/*     */   private int[] pivot;
/*     */   
/*     */   private boolean even;
/*     */   
/*     */   private boolean singular;
/*     */   
/*     */   private FieldMatrix<T> cachedL;
/*     */   
/*     */   private FieldMatrix<T> cachedU;
/*     */   
/*     */   private FieldMatrix<T> cachedP;
/*     */   
/*     */   public FieldLUDecomposition(FieldMatrix<T> matrix) {
/*  87 */     if (!matrix.isSquare())
/*  88 */       throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension()); 
/*  92 */     int m = matrix.getColumnDimension();
/*  93 */     this.field = matrix.getField();
/*  94 */     this.lu = matrix.getData();
/*  95 */     this.pivot = new int[m];
/*  96 */     this.cachedL = null;
/*  97 */     this.cachedU = null;
/*  98 */     this.cachedP = null;
/* 101 */     for (int row = 0; row < m; row++)
/* 102 */       this.pivot[row] = row; 
/* 104 */     this.even = true;
/* 105 */     this.singular = false;
/* 108 */     for (int col = 0; col < m; col++) {
/* 110 */       FieldElement fieldElement = (FieldElement)this.field.getZero();
/* 113 */       for (int i = 0; i < col; i++) {
/*     */         FieldElement fieldElement1;
/* 114 */         T[] luRow = this.lu[i];
/* 115 */         T t = luRow[col];
/* 116 */         for (int n = 0; n < i; n++)
/* 117 */           fieldElement1 = (FieldElement)t.subtract(luRow[n].multiply(this.lu[n][col])); 
/* 119 */         luRow[col] = (T)fieldElement1;
/*     */       } 
/* 123 */       int nonZero = col;
/* 124 */       for (int j = col; j < m; j++) {
/*     */         FieldElement fieldElement1;
/* 125 */         T[] luRow = this.lu[j];
/* 126 */         T t = luRow[col];
/* 127 */         for (int n = 0; n < col; n++)
/* 128 */           fieldElement1 = (FieldElement)t.subtract(luRow[n].multiply(this.lu[n][col])); 
/* 130 */         luRow[col] = (T)fieldElement1;
/* 132 */         if (this.lu[nonZero][col].equals(this.field.getZero()))
/* 134 */           nonZero++; 
/*     */       } 
/* 139 */       if (nonZero >= m) {
/* 140 */         this.singular = true;
/*     */         return;
/*     */       } 
/* 145 */       if (nonZero != col) {
/* 146 */         FieldElement fieldElement1 = (FieldElement)this.field.getZero();
/* 147 */         for (int n = 0; n < m; n++) {
/* 148 */           T t = this.lu[nonZero][n];
/* 149 */           this.lu[nonZero][n] = this.lu[col][n];
/* 150 */           this.lu[col][n] = t;
/*     */         } 
/* 152 */         int temp = this.pivot[nonZero];
/* 153 */         this.pivot[nonZero] = this.pivot[col];
/* 154 */         this.pivot[col] = temp;
/* 155 */         this.even = !this.even;
/*     */       } 
/* 159 */       T luDiag = this.lu[col][col];
/* 160 */       for (int k = col + 1; k < m; k++) {
/* 161 */         T[] luRow = this.lu[k];
/* 162 */         luRow[col] = (T)luRow[col].divide(luDiag);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> getL() {
/* 174 */     if (this.cachedL == null && !this.singular) {
/* 175 */       int m = this.pivot.length;
/* 176 */       this.cachedL = new Array2DRowFieldMatrix<T>(this.field, m, m);
/* 177 */       for (int i = 0; i < m; i++) {
/* 178 */         T[] luI = this.lu[i];
/* 179 */         for (int j = 0; j < i; j++)
/* 180 */           this.cachedL.setEntry(i, j, luI[j]); 
/* 182 */         this.cachedL.setEntry(i, i, (T)this.field.getOne());
/*     */       } 
/*     */     } 
/* 185 */     return this.cachedL;
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> getU() {
/* 194 */     if (this.cachedU == null && !this.singular) {
/* 195 */       int m = this.pivot.length;
/* 196 */       this.cachedU = new Array2DRowFieldMatrix<T>(this.field, m, m);
/* 197 */       for (int i = 0; i < m; i++) {
/* 198 */         T[] luI = this.lu[i];
/* 199 */         for (int j = i; j < m; j++)
/* 200 */           this.cachedU.setEntry(i, j, luI[j]); 
/*     */       } 
/*     */     } 
/* 204 */     return this.cachedU;
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> getP() {
/* 217 */     if (this.cachedP == null && !this.singular) {
/* 218 */       int m = this.pivot.length;
/* 219 */       this.cachedP = new Array2DRowFieldMatrix<T>(this.field, m, m);
/* 220 */       for (int i = 0; i < m; i++)
/* 221 */         this.cachedP.setEntry(i, this.pivot[i], (T)this.field.getOne()); 
/*     */     } 
/* 224 */     return this.cachedP;
/*     */   }
/*     */   
/*     */   public int[] getPivot() {
/* 233 */     return (int[])this.pivot.clone();
/*     */   }
/*     */   
/*     */   public T getDeterminant() {
/* 241 */     if (this.singular)
/* 242 */       return (T)this.field.getZero(); 
/* 244 */     int m = this.pivot.length;
/* 245 */     FieldElement fieldElement = this.even ? (FieldElement)this.field.getOne() : (FieldElement)((FieldElement)this.field.getZero()).subtract(this.field.getOne());
/* 246 */     for (int i = 0; i < m; i++)
/* 247 */       fieldElement = (FieldElement)fieldElement.multiply(this.lu[i][i]); 
/* 249 */     return (T)fieldElement;
/*     */   }
/*     */   
/*     */   public FieldDecompositionSolver<T> getSolver() {
/* 258 */     return new Solver<T>(this.field, (FieldElement[][])this.lu, this.pivot, this.singular);
/*     */   }
/*     */   
/*     */   private static class Solver<T extends FieldElement<T>> implements FieldDecompositionSolver<T> {
/*     */     private final Field<T> field;
/*     */     
/*     */     private final T[][] lu;
/*     */     
/*     */     private final int[] pivot;
/*     */     
/*     */     private final boolean singular;
/*     */     
/*     */     private Solver(Field<T> field, T[][] lu, int[] pivot, boolean singular) {
/* 285 */       this.field = field;
/* 286 */       this.lu = lu;
/* 287 */       this.pivot = pivot;
/* 288 */       this.singular = singular;
/*     */     }
/*     */     
/*     */     public boolean isNonSingular() {
/* 293 */       return !this.singular;
/*     */     }
/*     */     
/*     */     public FieldVector<T> solve(FieldVector<T> b) {
/*     */       try {
/* 299 */         return solve((ArrayFieldVector<T>)b);
/* 300 */       } catch (ClassCastException cce) {
/* 302 */         int m = this.pivot.length;
/* 303 */         if (b.getDimension() != m)
/* 304 */           throw new DimensionMismatchException(b.getDimension(), m); 
/* 306 */         if (this.singular)
/* 307 */           throw new SingularMatrixException(); 
/* 311 */         FieldElement[] arrayOfFieldElement = (FieldElement[])Array.newInstance(this.field.getRuntimeClass(), m);
/* 314 */         for (int row = 0; row < m; row++)
/* 315 */           arrayOfFieldElement[row] = (FieldElement)b.getEntry(this.pivot[row]); 
/*     */         int col;
/* 319 */         for (col = 0; col < m; col++) {
/* 320 */           FieldElement fieldElement = arrayOfFieldElement[col];
/* 321 */           for (int i = col + 1; i < m; i++)
/* 322 */             arrayOfFieldElement[i] = (FieldElement)arrayOfFieldElement[i].subtract(fieldElement.multiply(this.lu[i][col])); 
/*     */         } 
/* 327 */         for (col = m - 1; col >= 0; col--) {
/* 328 */           arrayOfFieldElement[col] = (FieldElement)arrayOfFieldElement[col].divide(this.lu[col][col]);
/* 329 */           FieldElement fieldElement = arrayOfFieldElement[col];
/* 330 */           for (int i = 0; i < col; i++)
/* 331 */             arrayOfFieldElement[i] = (FieldElement)arrayOfFieldElement[i].subtract(fieldElement.multiply(this.lu[i][col])); 
/*     */         } 
/* 335 */         return new ArrayFieldVector<T>(this.field, (T[])arrayOfFieldElement, false);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ArrayFieldVector<T> solve(ArrayFieldVector<T> b) {
/* 348 */       int m = this.pivot.length;
/* 349 */       int length = b.getDimension();
/* 350 */       if (length != m)
/* 351 */         throw new DimensionMismatchException(length, m); 
/* 353 */       if (this.singular)
/* 354 */         throw new SingularMatrixException(); 
/* 359 */       FieldElement[] arrayOfFieldElement = (FieldElement[])Array.newInstance(this.field.getRuntimeClass(), m);
/* 363 */       for (int row = 0; row < m; row++)
/* 364 */         arrayOfFieldElement[row] = (FieldElement)b.getEntry(this.pivot[row]); 
/*     */       int col;
/* 368 */       for (col = 0; col < m; col++) {
/* 369 */         FieldElement fieldElement = arrayOfFieldElement[col];
/* 370 */         for (int i = col + 1; i < m; i++)
/* 371 */           arrayOfFieldElement[i] = (FieldElement)arrayOfFieldElement[i].subtract(fieldElement.multiply(this.lu[i][col])); 
/*     */       } 
/* 376 */       for (col = m - 1; col >= 0; col--) {
/* 377 */         arrayOfFieldElement[col] = (FieldElement)arrayOfFieldElement[col].divide(this.lu[col][col]);
/* 378 */         FieldElement fieldElement = arrayOfFieldElement[col];
/* 379 */         for (int i = 0; i < col; i++)
/* 380 */           arrayOfFieldElement[i] = (FieldElement)arrayOfFieldElement[i].subtract(fieldElement.multiply(this.lu[i][col])); 
/*     */       } 
/* 384 */       return new ArrayFieldVector<T>((T[])arrayOfFieldElement, false);
/*     */     }
/*     */     
/*     */     public FieldMatrix<T> solve(FieldMatrix<T> b) {
/* 389 */       int m = this.pivot.length;
/* 390 */       if (b.getRowDimension() != m)
/* 391 */         throw new DimensionMismatchException(b.getRowDimension(), m); 
/* 393 */       if (this.singular)
/* 394 */         throw new SingularMatrixException(); 
/* 397 */       int nColB = b.getColumnDimension();
/* 401 */       FieldElement[][] arrayOfFieldElement = (FieldElement[][])Array.newInstance(this.field.getRuntimeClass(), new int[] { m, nColB });
/* 402 */       for (int row = 0; row < m; row++) {
/* 403 */         FieldElement[] arrayOfFieldElement1 = arrayOfFieldElement[row];
/* 404 */         int pRow = this.pivot[row];
/* 405 */         for (int i = 0; i < nColB; i++)
/* 406 */           arrayOfFieldElement1[i] = (FieldElement)b.getEntry(pRow, i); 
/*     */       } 
/*     */       int col;
/* 411 */       for (col = 0; col < m; col++) {
/* 412 */         FieldElement[] arrayOfFieldElement1 = arrayOfFieldElement[col];
/* 413 */         for (int i = col + 1; i < m; i++) {
/* 414 */           FieldElement[] arrayOfFieldElement2 = arrayOfFieldElement[i];
/* 415 */           T luICol = this.lu[i][col];
/* 416 */           for (int j = 0; j < nColB; j++)
/* 417 */             arrayOfFieldElement2[j] = (FieldElement)arrayOfFieldElement2[j].subtract(arrayOfFieldElement1[j].multiply(luICol)); 
/*     */         } 
/*     */       } 
/* 423 */       for (col = m - 1; col >= 0; col--) {
/* 424 */         FieldElement[] arrayOfFieldElement1 = arrayOfFieldElement[col];
/* 425 */         T luDiag = this.lu[col][col];
/* 426 */         for (int j = 0; j < nColB; j++)
/* 427 */           arrayOfFieldElement1[j] = (FieldElement)arrayOfFieldElement1[j].divide(luDiag); 
/* 429 */         for (int i = 0; i < col; i++) {
/* 430 */           FieldElement[] arrayOfFieldElement2 = arrayOfFieldElement[i];
/* 431 */           T luICol = this.lu[i][col];
/* 432 */           for (int k = 0; k < nColB; k++)
/* 433 */             arrayOfFieldElement2[k] = (FieldElement)arrayOfFieldElement2[k].subtract(arrayOfFieldElement1[k].multiply(luICol)); 
/*     */         } 
/*     */       } 
/* 438 */       return new Array2DRowFieldMatrix<T>(this.field, (T[][])arrayOfFieldElement, false);
/*     */     }
/*     */     
/*     */     public FieldMatrix<T> getInverse() {
/* 444 */       int m = this.pivot.length;
/* 445 */       FieldElement fieldElement = (FieldElement)this.field.getOne();
/* 446 */       FieldMatrix<T> identity = new Array2DRowFieldMatrix<T>(this.field, m, m);
/* 447 */       for (int i = 0; i < m; i++)
/* 448 */         identity.setEntry(i, i, (T)fieldElement); 
/* 450 */       return solve(identity);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\FieldLUDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */