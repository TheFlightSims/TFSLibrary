/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Array2DRowFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
/*     */   private static final long serialVersionUID = 7260756672015356458L;
/*     */   
/*     */   private T[][] data;
/*     */   
/*     */   public Array2DRowFieldMatrix(Field<T> field) {
/*  55 */     super(field);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix(Field<T> field, int rowDimension, int columnDimension) {
/*  70 */     super(field, rowDimension, columnDimension);
/*  71 */     this.data = buildArray(field, rowDimension, columnDimension);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix(T[][] d) {
/*  89 */     this(extractField(d), d);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix(Field<T> field, T[][] d) {
/* 108 */     super(field);
/* 109 */     copyIn(d);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix(T[][] d, boolean copyArray) {
/* 129 */     this(extractField(d), d, copyArray);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix(Field<T> field, T[][] d, boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
/* 150 */     super(field);
/* 151 */     if (copyArray) {
/* 152 */       copyIn(d);
/*     */     } else {
/* 154 */       MathUtils.checkNotNull(d);
/* 155 */       int nRows = d.length;
/* 156 */       if (nRows == 0)
/* 157 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/* 159 */       int nCols = (d[0]).length;
/* 160 */       if (nCols == 0)
/* 161 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/* 163 */       for (int r = 1; r < nRows; r++) {
/* 164 */         if ((d[r]).length != nCols)
/* 165 */           throw new DimensionMismatchException(nCols, (d[r]).length); 
/*     */       } 
/* 168 */       this.data = d;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix(T[] v) {
/* 180 */     this(extractField(v), v);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix(Field<T> field, T[] v) {
/* 192 */     super(field);
/* 193 */     int nRows = v.length;
/* 194 */     this.data = buildArray(getField(), nRows, 1);
/* 195 */     for (int row = 0; row < nRows; row++)
/* 196 */       this.data[row][0] = v[row]; 
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) {
/* 203 */     return new Array2DRowFieldMatrix(getField(), rowDimension, columnDimension);
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> copy() {
/* 209 */     return new Array2DRowFieldMatrix(getField(), copyOut(), false);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix<T> add(Array2DRowFieldMatrix<T> m) {
/* 222 */     checkAdditionCompatible(m);
/* 224 */     int rowCount = getRowDimension();
/* 225 */     int columnCount = getColumnDimension();
/* 226 */     T[][] outData = buildArray(getField(), rowCount, columnCount);
/* 227 */     for (int row = 0; row < rowCount; row++) {
/* 228 */       T[] dataRow = this.data[row];
/* 229 */       T[] mRow = m.data[row];
/* 230 */       T[] outDataRow = outData[row];
/* 231 */       for (int col = 0; col < columnCount; col++)
/* 232 */         outDataRow[col] = (T)dataRow[col].add(mRow[col]); 
/*     */     } 
/* 236 */     return new Array2DRowFieldMatrix(getField(), outData, false);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix<T> subtract(Array2DRowFieldMatrix<T> m) {
/* 249 */     checkSubtractionCompatible(m);
/* 251 */     int rowCount = getRowDimension();
/* 252 */     int columnCount = getColumnDimension();
/* 253 */     T[][] outData = buildArray(getField(), rowCount, columnCount);
/* 254 */     for (int row = 0; row < rowCount; row++) {
/* 255 */       T[] dataRow = this.data[row];
/* 256 */       T[] mRow = m.data[row];
/* 257 */       T[] outDataRow = outData[row];
/* 258 */       for (int col = 0; col < columnCount; col++)
/* 259 */         outDataRow[col] = (T)dataRow[col].subtract(mRow[col]); 
/*     */     } 
/* 263 */     return new Array2DRowFieldMatrix(getField(), outData, false);
/*     */   }
/*     */   
/*     */   public Array2DRowFieldMatrix<T> multiply(Array2DRowFieldMatrix<T> m) {
/* 277 */     checkMultiplicationCompatible(m);
/* 279 */     int nRows = getRowDimension();
/* 280 */     int nCols = m.getColumnDimension();
/* 281 */     int nSum = getColumnDimension();
/* 282 */     T[][] outData = buildArray(getField(), nRows, nCols);
/* 283 */     for (int row = 0; row < nRows; row++) {
/* 284 */       T[] dataRow = this.data[row];
/* 285 */       T[] outDataRow = outData[row];
/* 286 */       for (int col = 0; col < nCols; col++) {
/* 287 */         FieldElement fieldElement = (FieldElement)getField().getZero();
/* 288 */         for (int i = 0; i < nSum; i++)
/* 289 */           fieldElement = (FieldElement)fieldElement.add(dataRow[i].multiply(m.data[i][col])); 
/* 291 */         outDataRow[col] = (T)fieldElement;
/*     */       } 
/*     */     } 
/* 295 */     return new Array2DRowFieldMatrix(getField(), outData, false);
/*     */   }
/*     */   
/*     */   public T[][] getData() {
/* 302 */     return copyOut();
/*     */   }
/*     */   
/*     */   public T[][] getDataRef() {
/* 312 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setSubMatrix(T[][] subMatrix, int row, int column) {
/* 318 */     if (this.data == null) {
/* 319 */       if (row > 0)
/* 320 */         throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, new Object[] { Integer.valueOf(row) }); 
/* 322 */       if (column > 0)
/* 323 */         throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, new Object[] { Integer.valueOf(column) }); 
/* 325 */       int nRows = subMatrix.length;
/* 326 */       if (nRows == 0)
/* 327 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/* 330 */       int nCols = (subMatrix[0]).length;
/* 331 */       if (nCols == 0)
/* 332 */         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/* 334 */       this.data = buildArray(getField(), subMatrix.length, nCols);
/* 335 */       for (int i = 0; i < this.data.length; i++) {
/* 336 */         if ((subMatrix[i]).length != nCols)
/* 337 */           throw new DimensionMismatchException(nCols, (subMatrix[i]).length); 
/* 339 */         System.arraycopy(subMatrix[i], 0, this.data[i + row], column, nCols);
/*     */       } 
/*     */     } else {
/* 342 */       super.setSubMatrix(subMatrix, row, column);
/*     */     } 
/*     */   }
/*     */   
/*     */   public T getEntry(int row, int column) {
/* 350 */     checkRowIndex(row);
/* 351 */     checkColumnIndex(column);
/* 353 */     return this.data[row][column];
/*     */   }
/*     */   
/*     */   public void setEntry(int row, int column, T value) {
/* 359 */     checkRowIndex(row);
/* 360 */     checkColumnIndex(column);
/* 362 */     this.data[row][column] = value;
/*     */   }
/*     */   
/*     */   public void addToEntry(int row, int column, T increment) {
/* 368 */     checkRowIndex(row);
/* 369 */     checkColumnIndex(column);
/* 371 */     this.data[row][column] = (T)this.data[row][column].add(increment);
/*     */   }
/*     */   
/*     */   public void multiplyEntry(int row, int column, T factor) {
/* 377 */     checkRowIndex(row);
/* 378 */     checkColumnIndex(column);
/* 380 */     this.data[row][column] = (T)this.data[row][column].multiply(factor);
/*     */   }
/*     */   
/*     */   public int getRowDimension() {
/* 386 */     return (this.data == null) ? 0 : this.data.length;
/*     */   }
/*     */   
/*     */   public int getColumnDimension() {
/* 392 */     return (this.data == null || this.data[0] == null) ? 0 : (this.data[0]).length;
/*     */   }
/*     */   
/*     */   public T[] operate(T[] v) {
/* 398 */     int nRows = getRowDimension();
/* 399 */     int nCols = getColumnDimension();
/* 400 */     if (v.length != nCols)
/* 401 */       throw new DimensionMismatchException(v.length, nCols); 
/* 403 */     T[] out = buildArray(getField(), nRows);
/* 404 */     for (int row = 0; row < nRows; row++) {
/* 405 */       T[] dataRow = this.data[row];
/* 406 */       FieldElement fieldElement = (FieldElement)getField().getZero();
/* 407 */       for (int i = 0; i < nCols; i++)
/* 408 */         fieldElement = (FieldElement)fieldElement.add(dataRow[i].multiply(v[i])); 
/* 410 */       out[row] = (T)fieldElement;
/*     */     } 
/* 412 */     return out;
/*     */   }
/*     */   
/*     */   public T[] preMultiply(T[] v) {
/* 418 */     int nRows = getRowDimension();
/* 419 */     int nCols = getColumnDimension();
/* 420 */     if (v.length != nRows)
/* 421 */       throw new DimensionMismatchException(v.length, nRows); 
/* 424 */     T[] out = buildArray(getField(), nCols);
/* 425 */     for (int col = 0; col < nCols; col++) {
/* 426 */       FieldElement fieldElement = (FieldElement)getField().getZero();
/* 427 */       for (int i = 0; i < nRows; i++)
/* 428 */         fieldElement = (FieldElement)fieldElement.add(this.data[i][col].multiply(v[i])); 
/* 430 */       out[col] = (T)fieldElement;
/*     */     } 
/* 433 */     return out;
/*     */   }
/*     */   
/*     */   public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor) {
/* 439 */     int rows = getRowDimension();
/* 440 */     int columns = getColumnDimension();
/* 441 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 442 */     for (int i = 0; i < rows; i++) {
/* 443 */       T[] rowI = this.data[i];
/* 444 */       for (int j = 0; j < columns; j++)
/* 445 */         rowI[j] = visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 448 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor) {
/* 454 */     int rows = getRowDimension();
/* 455 */     int columns = getColumnDimension();
/* 456 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 457 */     for (int i = 0; i < rows; i++) {
/* 458 */       T[] rowI = this.data[i];
/* 459 */       for (int j = 0; j < columns; j++)
/* 460 */         visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 463 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 471 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 472 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 474 */     for (int i = startRow; i <= endRow; i++) {
/* 475 */       T[] rowI = this.data[i];
/* 476 */       for (int j = startColumn; j <= endColumn; j++)
/* 477 */         rowI[j] = visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 480 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 488 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 489 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 491 */     for (int i = startRow; i <= endRow; i++) {
/* 492 */       T[] rowI = this.data[i];
/* 493 */       for (int j = startColumn; j <= endColumn; j++)
/* 494 */         visitor.visit(i, j, rowI[j]); 
/*     */     } 
/* 497 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public T walkInColumnOrder(FieldMatrixChangingVisitor<T> visitor) {
/* 503 */     int rows = getRowDimension();
/* 504 */     int columns = getColumnDimension();
/* 505 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 506 */     for (int j = 0; j < columns; j++) {
/* 507 */       for (int i = 0; i < rows; i++) {
/* 508 */         T[] rowI = this.data[i];
/* 509 */         rowI[j] = visitor.visit(i, j, rowI[j]);
/*     */       } 
/*     */     } 
/* 512 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> visitor) {
/* 518 */     int rows = getRowDimension();
/* 519 */     int columns = getColumnDimension();
/* 520 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 521 */     for (int j = 0; j < columns; j++) {
/* 522 */       for (int i = 0; i < rows; i++)
/* 523 */         visitor.visit(i, j, this.data[i][j]); 
/*     */     } 
/* 526 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public T walkInColumnOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 534 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 535 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 537 */     for (int j = startColumn; j <= endColumn; j++) {
/* 538 */       for (int i = startRow; i <= endRow; i++) {
/* 539 */         T[] rowI = this.data[i];
/* 540 */         rowI[j] = visitor.visit(i, j, rowI[j]);
/*     */       } 
/*     */     } 
/* 543 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 551 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 552 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 554 */     for (int j = startColumn; j <= endColumn; j++) {
/* 555 */       for (int i = startRow; i <= endRow; i++)
/* 556 */         visitor.visit(i, j, this.data[i][j]); 
/*     */     } 
/* 559 */     return visitor.end();
/*     */   }
/*     */   
/*     */   private T[][] copyOut() {
/* 568 */     int nRows = getRowDimension();
/* 569 */     T[][] out = buildArray(getField(), nRows, getColumnDimension());
/* 571 */     for (int i = 0; i < nRows; i++)
/* 572 */       System.arraycopy(this.data[i], 0, out[i], 0, (this.data[i]).length); 
/* 574 */     return out;
/*     */   }
/*     */   
/*     */   private void copyIn(T[][] in) {
/* 587 */     setSubMatrix(in, 0, 0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\Array2DRowFieldMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */