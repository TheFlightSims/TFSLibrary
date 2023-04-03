/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
/*     */ 
/*     */ public class OpenMapRealMatrix extends AbstractRealMatrix implements SparseRealMatrix, Serializable {
/*     */   private static final long serialVersionUID = -5962461716457143437L;
/*     */   
/*     */   private final int rows;
/*     */   
/*     */   private final int columns;
/*     */   
/*     */   private final OpenIntToDoubleHashMap entries;
/*     */   
/*     */   public OpenMapRealMatrix(int rowDimension, int columnDimension) {
/*  49 */     super(rowDimension, columnDimension);
/*  50 */     long lRow = rowDimension;
/*  51 */     long lCol = columnDimension;
/*  52 */     if (lRow * lCol >= 2147483647L)
/*  53 */       throw new NumberIsTooLargeException(Long.valueOf(lRow * lCol), Integer.valueOf(2147483647), false); 
/*  55 */     this.rows = rowDimension;
/*  56 */     this.columns = columnDimension;
/*  57 */     this.entries = new OpenIntToDoubleHashMap(0.0D);
/*     */   }
/*     */   
/*     */   public OpenMapRealMatrix(OpenMapRealMatrix matrix) {
/*  66 */     this.rows = matrix.rows;
/*  67 */     this.columns = matrix.columns;
/*  68 */     this.entries = new OpenIntToDoubleHashMap(matrix.entries);
/*     */   }
/*     */   
/*     */   public OpenMapRealMatrix copy() {
/*  74 */     return new OpenMapRealMatrix(this);
/*     */   }
/*     */   
/*     */   public OpenMapRealMatrix createMatrix(int rowDimension, int columnDimension) {
/*  80 */     return new OpenMapRealMatrix(rowDimension, columnDimension);
/*     */   }
/*     */   
/*     */   public int getColumnDimension() {
/*  86 */     return this.columns;
/*     */   }
/*     */   
/*     */   public OpenMapRealMatrix add(OpenMapRealMatrix m) {
/* 100 */     MatrixUtils.checkAdditionCompatible(this, m);
/* 102 */     OpenMapRealMatrix out = new OpenMapRealMatrix(this);
/* 103 */     for (OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator(); iterator.hasNext(); ) {
/* 104 */       iterator.advance();
/* 105 */       int row = iterator.key() / this.columns;
/* 106 */       int col = iterator.key() - row * this.columns;
/* 107 */       out.setEntry(row, col, getEntry(row, col) + iterator.value());
/*     */     } 
/* 110 */     return out;
/*     */   }
/*     */   
/*     */   public OpenMapRealMatrix subtract(RealMatrix m) {
/*     */     try {
/* 118 */       return subtract((OpenMapRealMatrix)m);
/* 119 */     } catch (ClassCastException cce) {
/* 120 */       return (OpenMapRealMatrix)super.subtract(m);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OpenMapRealMatrix subtract(OpenMapRealMatrix m) {
/* 134 */     MatrixUtils.checkAdditionCompatible(this, m);
/* 136 */     OpenMapRealMatrix out = new OpenMapRealMatrix(this);
/* 137 */     for (OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator(); iterator.hasNext(); ) {
/* 138 */       iterator.advance();
/* 139 */       int row = iterator.key() / this.columns;
/* 140 */       int col = iterator.key() - row * this.columns;
/* 141 */       out.setEntry(row, col, getEntry(row, col) - iterator.value());
/*     */     } 
/* 144 */     return out;
/*     */   }
/*     */   
/*     */   public RealMatrix multiply(RealMatrix m) {
/*     */     try {
/* 151 */       return multiply((OpenMapRealMatrix)m);
/* 152 */     } catch (ClassCastException cce) {
/* 155 */       MatrixUtils.checkMultiplicationCompatible(this, m);
/* 157 */       int outCols = m.getColumnDimension();
/* 158 */       BlockRealMatrix out = new BlockRealMatrix(this.rows, outCols);
/* 159 */       for (OpenIntToDoubleHashMap.Iterator iterator = this.entries.iterator(); iterator.hasNext(); ) {
/* 160 */         iterator.advance();
/* 161 */         double value = iterator.value();
/* 162 */         int key = iterator.key();
/* 163 */         int i = key / this.columns;
/* 164 */         int k = key % this.columns;
/* 165 */         for (int j = 0; j < outCols; j++)
/* 166 */           out.addToEntry(i, j, value * m.getEntry(k, j)); 
/*     */       } 
/* 170 */       return out;
/*     */     } 
/*     */   }
/*     */   
/*     */   public OpenMapRealMatrix multiply(OpenMapRealMatrix m) {
/* 185 */     MatrixUtils.checkMultiplicationCompatible(this, m);
/* 187 */     int outCols = m.getColumnDimension();
/* 188 */     OpenMapRealMatrix out = new OpenMapRealMatrix(this.rows, outCols);
/* 189 */     for (OpenIntToDoubleHashMap.Iterator iterator = this.entries.iterator(); iterator.hasNext(); ) {
/* 190 */       iterator.advance();
/* 191 */       double value = iterator.value();
/* 192 */       int key = iterator.key();
/* 193 */       int i = key / this.columns;
/* 194 */       int k = key % this.columns;
/* 195 */       for (int j = 0; j < outCols; j++) {
/* 196 */         int rightKey = m.computeKey(k, j);
/* 197 */         if (m.entries.containsKey(rightKey)) {
/* 198 */           int outKey = out.computeKey(i, j);
/* 199 */           double outValue = out.entries.get(outKey) + value * m.entries.get(rightKey);
/* 201 */           if (outValue == 0.0D) {
/* 202 */             out.entries.remove(outKey);
/*     */           } else {
/* 204 */             out.entries.put(outKey, outValue);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 210 */     return out;
/*     */   }
/*     */   
/*     */   public double getEntry(int row, int column) {
/* 216 */     MatrixUtils.checkRowIndex(this, row);
/* 217 */     MatrixUtils.checkColumnIndex(this, column);
/* 218 */     return this.entries.get(computeKey(row, column));
/*     */   }
/*     */   
/*     */   public int getRowDimension() {
/* 224 */     return this.rows;
/*     */   }
/*     */   
/*     */   public void setEntry(int row, int column, double value) {
/* 230 */     MatrixUtils.checkRowIndex(this, row);
/* 231 */     MatrixUtils.checkColumnIndex(this, column);
/* 232 */     if (value == 0.0D) {
/* 233 */       this.entries.remove(computeKey(row, column));
/*     */     } else {
/* 235 */       this.entries.put(computeKey(row, column), value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addToEntry(int row, int column, double increment) {
/* 242 */     MatrixUtils.checkRowIndex(this, row);
/* 243 */     MatrixUtils.checkColumnIndex(this, column);
/* 244 */     int key = computeKey(row, column);
/* 245 */     double value = this.entries.get(key) + increment;
/* 246 */     if (value == 0.0D) {
/* 247 */       this.entries.remove(key);
/*     */     } else {
/* 249 */       this.entries.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void multiplyEntry(int row, int column, double factor) {
/* 256 */     MatrixUtils.checkRowIndex(this, row);
/* 257 */     MatrixUtils.checkColumnIndex(this, column);
/* 258 */     int key = computeKey(row, column);
/* 259 */     double value = this.entries.get(key) * factor;
/* 260 */     if (value == 0.0D) {
/* 261 */       this.entries.remove(key);
/*     */     } else {
/* 263 */       this.entries.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int computeKey(int row, int column) {
/* 274 */     return row * this.columns + column;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\OpenMapRealMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */