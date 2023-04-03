/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Array2DRowRealMatrix extends AbstractRealMatrix implements Serializable {
/*     */   private static final long serialVersionUID = -1067294169172445528L;
/*     */   
/*     */   private double[][] data;
/*     */   
/*     */   public Array2DRowRealMatrix() {}
/*     */   
/*     */   public Array2DRowRealMatrix(int rowDimension, int columnDimension) {
/*  56 */     super(rowDimension, columnDimension);
/*  57 */     this.data = new double[rowDimension][columnDimension];
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix(double[][] d) throws DimensionMismatchException, NoDataException, NullArgumentException {
/*  75 */     copyIn(d);
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix(double[][] d, boolean copyArray) {
/*  96 */     if (copyArray) {
/*  97 */       copyIn(d);
/*     */     } else {
/*  99 */       if (d == null)
/* 100 */         throw new NullArgumentException(); 
/* 102 */       int nRows = d.length;
/* 103 */       if (nRows == 0)
/* 104 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/* 106 */       int nCols = (d[0]).length;
/* 107 */       if (nCols == 0)
/* 108 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/* 110 */       for (int r = 1; r < nRows; r++) {
/* 111 */         if ((d[r]).length != nCols)
/* 112 */           throw new DimensionMismatchException((d[r]).length, nCols); 
/*     */       } 
/* 115 */       this.data = d;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix(double[] v) {
/* 127 */     int nRows = v.length;
/* 128 */     this.data = new double[nRows][1];
/* 129 */     for (int row = 0; row < nRows; row++)
/* 130 */       this.data[row][0] = v[row]; 
/*     */   }
/*     */   
/*     */   public RealMatrix createMatrix(int rowDimension, int columnDimension) {
/* 138 */     return new Array2DRowRealMatrix(rowDimension, columnDimension);
/*     */   }
/*     */   
/*     */   public RealMatrix copy() {
/* 144 */     return new Array2DRowRealMatrix(copyOut(), false);
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix add(Array2DRowRealMatrix m) {
/* 157 */     MatrixUtils.checkAdditionCompatible(this, m);
/* 159 */     int rowCount = getRowDimension();
/* 160 */     int columnCount = getColumnDimension();
/* 161 */     double[][] outData = new double[rowCount][columnCount];
/* 162 */     for (int row = 0; row < rowCount; row++) {
/* 163 */       double[] dataRow = this.data[row];
/* 164 */       double[] mRow = m.data[row];
/* 165 */       double[] outDataRow = outData[row];
/* 166 */       for (int col = 0; col < columnCount; col++)
/* 167 */         outDataRow[col] = dataRow[col] + mRow[col]; 
/*     */     } 
/* 171 */     return new Array2DRowRealMatrix(outData, false);
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix subtract(Array2DRowRealMatrix m) {
/* 184 */     MatrixUtils.checkSubtractionCompatible(this, m);
/* 186 */     int rowCount = getRowDimension();
/* 187 */     int columnCount = getColumnDimension();
/* 188 */     double[][] outData = new double[rowCount][columnCount];
/* 189 */     for (int row = 0; row < rowCount; row++) {
/* 190 */       double[] dataRow = this.data[row];
/* 191 */       double[] mRow = m.data[row];
/* 192 */       double[] outDataRow = outData[row];
/* 193 */       for (int col = 0; col < columnCount; col++)
/* 194 */         outDataRow[col] = dataRow[col] - mRow[col]; 
/*     */     } 
/* 198 */     return new Array2DRowRealMatrix(outData, false);
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix multiply(Array2DRowRealMatrix m) {
/* 211 */     MatrixUtils.checkMultiplicationCompatible(this, m);
/* 213 */     int nRows = getRowDimension();
/* 214 */     int nCols = m.getColumnDimension();
/* 215 */     int nSum = getColumnDimension();
/* 217 */     double[][] outData = new double[nRows][nCols];
/* 219 */     double[] mCol = new double[nSum];
/* 220 */     double[][] mData = m.data;
/* 223 */     for (int col = 0; col < nCols; col++) {
/* 226 */       for (int mRow = 0; mRow < nSum; mRow++)
/* 227 */         mCol[mRow] = mData[mRow][col]; 
/* 230 */       for (int row = 0; row < nRows; row++) {
/* 231 */         double[] dataRow = this.data[row];
/* 232 */         double sum = 0.0D;
/* 233 */         for (int i = 0; i < nSum; i++)
/* 234 */           sum += dataRow[i] * mCol[i]; 
/* 236 */         outData[row][col] = sum;
/*     */       } 
/*     */     } 
/* 240 */     return new Array2DRowRealMatrix(outData, false);
/*     */   }
/*     */   
/*     */   public double[][] getData() {
/* 246 */     return copyOut();
/*     */   }
/*     */   
/*     */   public double[][] getDataRef() {
/* 255 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setSubMatrix(double[][] subMatrix, int row, int column) {
/* 262 */     if (this.data == null) {
/* 263 */       if (row > 0)
/* 264 */         throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, new Object[] { Integer.valueOf(row) }); 
/* 266 */       if (column > 0)
/* 267 */         throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, new Object[] { Integer.valueOf(column) }); 
/* 269 */       MathUtils.checkNotNull(subMatrix);
/* 270 */       int nRows = subMatrix.length;
/* 271 */       if (nRows == 0)
/* 272 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/* 275 */       int nCols = (subMatrix[0]).length;
/* 276 */       if (nCols == 0)
/* 277 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/* 279 */       this.data = new double[subMatrix.length][nCols];
/* 280 */       for (int i = 0; i < this.data.length; i++) {
/* 281 */         if ((subMatrix[i]).length != nCols)
/* 282 */           throw new DimensionMismatchException((subMatrix[i]).length, nCols); 
/* 284 */         System.arraycopy(subMatrix[i], 0, this.data[i + row], column, nCols);
/*     */       } 
/*     */     } else {
/* 287 */       super.setSubMatrix(subMatrix, row, column);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getEntry(int row, int column) {
/* 295 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 296 */     return this.data[row][column];
/*     */   }
/*     */   
/*     */   public void setEntry(int row, int column, double value) {
/* 302 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 303 */     this.data[row][column] = value;
/*     */   }
/*     */   
/*     */   public void addToEntry(int row, int column, double increment) {
/* 309 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 310 */     this.data[row][column] = this.data[row][column] + increment;
/*     */   }
/*     */   
/*     */   public void multiplyEntry(int row, int column, double factor) {
/* 316 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 317 */     this.data[row][column] = this.data[row][column] * factor;
/*     */   }
/*     */   
/*     */   public int getRowDimension() {
/* 323 */     return (this.data == null) ? 0 : this.data.length;
/*     */   }
/*     */   
/*     */   public int getColumnDimension() {
/* 329 */     return (this.data == null || this.data[0] == null) ? 0 : (this.data[0]).length;
/*     */   }
/*     */   
/*     */   public double[] operate(double[] v) {
/* 335 */     int nRows = getRowDimension();
/* 336 */     int nCols = getColumnDimension();
/* 337 */     if (v.length != nCols)
/* 338 */       throw new DimensionMismatchException(v.length, nCols); 
/* 340 */     double[] out = new double[nRows];
/* 341 */     for (int row = 0; row < nRows; row++) {
/* 342 */       double[] dataRow = this.data[row];
/* 343 */       double sum = 0.0D;
/* 344 */       for (int i = 0; i < nCols; i++)
/* 345 */         sum += dataRow[i] * v[i]; 
/* 347 */       out[row] = sum;
/*     */     } 
/* 349 */     return out;
/*     */   }
/*     */   
/*     */   public double[] preMultiply(double[] v) {
/* 355 */     int nRows = getRowDimension();
/* 356 */     int nCols = getColumnDimension();
/* 357 */     if (v.length != nRows)
/* 358 */       throw new DimensionMismatchException(v.length, nRows); 
/* 361 */     double[] out = new double[nCols];
/* 362 */     for (int col = 0; col < nCols; col++) {
/* 363 */       double sum = 0.0D;
/* 364 */       for (int i = 0; i < nRows; i++)
/* 365 */         sum += this.data[i][col] * v[i]; 
/* 367 */       out[col] = sum;
/*     */     } 
/* 370 */     return out;
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixChangingVisitor visitor) {
/* 377 */     int rows = getRowDimension();
/* 378 */     int columns = getColumnDimension();
/* 379 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 380 */     for (int i = 0; i < rows; i++) {
/* 381 */       double[] rowI = this.data[i];
/* 382 */       for (int j = 0; j < columns; j++)
/* 383 */         rowI[j] = visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 386 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixPreservingVisitor visitor) {
/* 392 */     int rows = getRowDimension();
/* 393 */     int columns = getColumnDimension();
/* 394 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 395 */     for (int i = 0; i < rows; i++) {
/* 396 */       double[] rowI = this.data[i];
/* 397 */       for (int j = 0; j < columns; j++)
/* 398 */         visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 401 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 409 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 410 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 412 */     for (int i = startRow; i <= endRow; i++) {
/* 413 */       double[] rowI = this.data[i];
/* 414 */       for (int j = startColumn; j <= endColumn; j++)
/* 415 */         rowI[j] = visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 418 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 426 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 427 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 429 */     for (int i = startRow; i <= endRow; i++) {
/* 430 */       double[] rowI = this.data[i];
/* 431 */       for (int j = startColumn; j <= endColumn; j++)
/* 432 */         visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 435 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixChangingVisitor visitor) {
/* 441 */     int rows = getRowDimension();
/* 442 */     int columns = getColumnDimension();
/* 443 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 444 */     for (int j = 0; j < columns; j++) {
/* 445 */       for (int i = 0; i < rows; i++) {
/* 446 */         double[] rowI = this.data[i];
/* 447 */         rowI[j] = visitor.visit(i, j, rowI[j]);
/*     */       } 
/*     */     } 
/* 450 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixPreservingVisitor visitor) {
/* 456 */     int rows = getRowDimension();
/* 457 */     int columns = getColumnDimension();
/* 458 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 459 */     for (int j = 0; j < columns; j++) {
/* 460 */       for (int i = 0; i < rows; i++)
/* 461 */         visitor.visit(i, j, this.data[i][j]); 
/*     */     } 
/* 464 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 472 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 473 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 475 */     for (int j = startColumn; j <= endColumn; j++) {
/* 476 */       for (int i = startRow; i <= endRow; i++) {
/* 477 */         double[] rowI = this.data[i];
/* 478 */         rowI[j] = visitor.visit(i, j, rowI[j]);
/*     */       } 
/*     */     } 
/* 481 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 489 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 490 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 492 */     for (int j = startColumn; j <= endColumn; j++) {
/* 493 */       for (int i = startRow; i <= endRow; i++)
/* 494 */         visitor.visit(i, j, this.data[i][j]); 
/*     */     } 
/* 497 */     return visitor.end();
/*     */   }
/*     */   
/*     */   private double[][] copyOut() {
/* 506 */     int nRows = getRowDimension();
/* 507 */     double[][] out = new double[nRows][getColumnDimension()];
/* 509 */     for (int i = 0; i < nRows; i++)
/* 510 */       System.arraycopy(this.data[i], 0, out[i], 0, (this.data[i]).length); 
/* 512 */     return out;
/*     */   }
/*     */   
/*     */   private void copyIn(double[][] in) throws DimensionMismatchException, NoDataException, NullArgumentException {
/* 526 */     setSubMatrix(in, 0, 0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\Array2DRowRealMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */