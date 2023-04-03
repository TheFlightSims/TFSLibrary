/*      */ package org.apache.commons.math3.linear;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import org.apache.commons.math3.Field;
/*      */ import org.apache.commons.math3.FieldElement;
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.NoDataException;
/*      */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*      */ import org.apache.commons.math3.exception.NullArgumentException;
/*      */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*      */ import org.apache.commons.math3.exception.OutOfRangeException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ 
/*      */ public abstract class AbstractFieldMatrix<T extends FieldElement<T>> implements FieldMatrix<T> {
/*      */   private final Field<T> field;
/*      */   
/*      */   protected AbstractFieldMatrix() {
/*   53 */     this.field = null;
/*      */   }
/*      */   
/*      */   protected AbstractFieldMatrix(Field<T> field) {
/*   61 */     this.field = field;
/*      */   }
/*      */   
/*      */   protected AbstractFieldMatrix(Field<T> field, int rowDimension, int columnDimension) {
/*   76 */     if (rowDimension <= 0)
/*   77 */       throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(rowDimension)); 
/*   80 */     if (columnDimension <= 0)
/*   81 */       throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(columnDimension)); 
/*   84 */     this.field = field;
/*      */   }
/*      */   
/*      */   protected static <T extends FieldElement<T>> Field<T> extractField(T[][] d) {
/*   97 */     if (d == null)
/*   98 */       throw new NullArgumentException(); 
/*  100 */     if (d.length == 0)
/*  101 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/*  103 */     if ((d[0]).length == 0)
/*  104 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/*  106 */     return d[0][0].getField();
/*      */   }
/*      */   
/*      */   protected static <T extends FieldElement<T>> Field<T> extractField(T[] d) {
/*  118 */     if (d.length == 0)
/*  119 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/*  121 */     return d[0].getField();
/*      */   }
/*      */   
/*      */   protected static <T extends FieldElement<T>> T[][] buildArray(Field<T> field, int rows, int columns) {
/*  139 */     if (columns < 0) {
/*  140 */       FieldElement[] arrayOfFieldElement1 = (FieldElement[])Array.newInstance(field.getRuntimeClass(), 0);
/*  141 */       return (T[][])Array.newInstance(arrayOfFieldElement1.getClass(), rows);
/*      */     } 
/*  143 */     FieldElement[][] arrayOfFieldElement = (FieldElement[][])Array.newInstance(field.getRuntimeClass(), new int[] { rows, columns });
/*  145 */     for (int i = 0; i < arrayOfFieldElement.length; i++)
/*  146 */       Arrays.fill((Object[])arrayOfFieldElement[i], field.getZero()); 
/*  148 */     return (T[][])arrayOfFieldElement;
/*      */   }
/*      */   
/*      */   protected static <T extends FieldElement<T>> T[] buildArray(Field<T> field, int length) {
/*  163 */     FieldElement[] arrayOfFieldElement = (FieldElement[])Array.newInstance(field.getRuntimeClass(), length);
/*  164 */     Arrays.fill((Object[])arrayOfFieldElement, field.getZero());
/*  165 */     return (T[])arrayOfFieldElement;
/*      */   }
/*      */   
/*      */   public Field<T> getField() {
/*  170 */     return this.field;
/*      */   }
/*      */   
/*      */   public abstract FieldMatrix<T> createMatrix(int paramInt1, int paramInt2);
/*      */   
/*      */   public abstract FieldMatrix<T> copy();
/*      */   
/*      */   public FieldMatrix<T> add(FieldMatrix<T> m) {
/*  182 */     checkAdditionCompatible(m);
/*  184 */     int rowCount = getRowDimension();
/*  185 */     int columnCount = getColumnDimension();
/*  186 */     FieldMatrix<T> out = createMatrix(rowCount, columnCount);
/*  187 */     for (int row = 0; row < rowCount; row++) {
/*  188 */       for (int col = 0; col < columnCount; col++)
/*  189 */         out.setEntry(row, col, (T)getEntry(row, col).add(m.getEntry(row, col))); 
/*      */     } 
/*  193 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> subtract(FieldMatrix<T> m) {
/*  199 */     checkSubtractionCompatible(m);
/*  201 */     int rowCount = getRowDimension();
/*  202 */     int columnCount = getColumnDimension();
/*  203 */     FieldMatrix<T> out = createMatrix(rowCount, columnCount);
/*  204 */     for (int row = 0; row < rowCount; row++) {
/*  205 */       for (int col = 0; col < columnCount; col++)
/*  206 */         out.setEntry(row, col, (T)getEntry(row, col).subtract(m.getEntry(row, col))); 
/*      */     } 
/*  210 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> scalarAdd(T d) {
/*  216 */     int rowCount = getRowDimension();
/*  217 */     int columnCount = getColumnDimension();
/*  218 */     FieldMatrix<T> out = createMatrix(rowCount, columnCount);
/*  219 */     for (int row = 0; row < rowCount; row++) {
/*  220 */       for (int col = 0; col < columnCount; col++)
/*  221 */         out.setEntry(row, col, (T)getEntry(row, col).add(d)); 
/*      */     } 
/*  225 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> scalarMultiply(T d) {
/*  230 */     int rowCount = getRowDimension();
/*  231 */     int columnCount = getColumnDimension();
/*  232 */     FieldMatrix<T> out = createMatrix(rowCount, columnCount);
/*  233 */     for (int row = 0; row < rowCount; row++) {
/*  234 */       for (int col = 0; col < columnCount; col++)
/*  235 */         out.setEntry(row, col, (T)getEntry(row, col).multiply(d)); 
/*      */     } 
/*  239 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> multiply(FieldMatrix<T> m) {
/*  245 */     checkMultiplicationCompatible(m);
/*  247 */     int nRows = getRowDimension();
/*  248 */     int nCols = m.getColumnDimension();
/*  249 */     int nSum = getColumnDimension();
/*  250 */     FieldMatrix<T> out = createMatrix(nRows, nCols);
/*  251 */     for (int row = 0; row < nRows; row++) {
/*  252 */       for (int col = 0; col < nCols; col++) {
/*  253 */         FieldElement fieldElement = (FieldElement)this.field.getZero();
/*  254 */         for (int i = 0; i < nSum; i++)
/*  255 */           fieldElement = (FieldElement)fieldElement.add(getEntry(row, i).multiply(m.getEntry(i, col))); 
/*  257 */         out.setEntry(row, col, (T)fieldElement);
/*      */       } 
/*      */     } 
/*  261 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> preMultiply(FieldMatrix<T> m) {
/*  266 */     return m.multiply(this);
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> power(int p) {
/*  271 */     if (p < 0)
/*  272 */       throw new IllegalArgumentException("p must be >= 0"); 
/*  275 */     if (!isSquare())
/*  276 */       throw new NonSquareMatrixException(getRowDimension(), getColumnDimension()); 
/*  279 */     if (p == 0)
/*  280 */       return MatrixUtils.createFieldIdentityMatrix(getField(), getRowDimension()); 
/*  283 */     if (p == 1)
/*  284 */       return copy(); 
/*  287 */     int power = p - 1;
/*  296 */     char[] binaryRepresentation = Integer.toBinaryString(power).toCharArray();
/*  298 */     ArrayList<Integer> nonZeroPositions = new ArrayList<Integer>();
/*  300 */     for (int i = 0; i < binaryRepresentation.length; i++) {
/*  301 */       if (binaryRepresentation[i] == '1') {
/*  302 */         int pos = binaryRepresentation.length - i - 1;
/*  303 */         nonZeroPositions.add(Integer.valueOf(pos));
/*      */       } 
/*      */     } 
/*  307 */     ArrayList<FieldMatrix<T>> results = new ArrayList<FieldMatrix<T>>(binaryRepresentation.length);
/*  310 */     results.add(0, copy());
/*  312 */     for (int j = 1; j < binaryRepresentation.length; j++) {
/*  313 */       FieldMatrix<T> s = results.get(j - 1);
/*  314 */       FieldMatrix<T> r = s.multiply(s);
/*  315 */       results.add(j, r);
/*      */     } 
/*  318 */     FieldMatrix<T> result = copy();
/*  320 */     for (Integer integer : nonZeroPositions)
/*  321 */       result = result.multiply(results.get(integer.intValue())); 
/*  324 */     return result;
/*      */   }
/*      */   
/*      */   public T[][] getData() {
/*  329 */     T[][] data = buildArray(this.field, getRowDimension(), getColumnDimension());
/*  331 */     for (int i = 0; i < data.length; i++) {
/*  332 */       T[] dataI = data[i];
/*  333 */       for (int j = 0; j < dataI.length; j++)
/*  334 */         dataI[j] = getEntry(i, j); 
/*      */     } 
/*  338 */     return data;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) {
/*  344 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/*  346 */     FieldMatrix<T> subMatrix = createMatrix(endRow - startRow + 1, endColumn - startColumn + 1);
/*  348 */     for (int i = startRow; i <= endRow; i++) {
/*  349 */       for (int j = startColumn; j <= endColumn; j++)
/*  350 */         subMatrix.setEntry(i - startRow, j - startColumn, getEntry(i, j)); 
/*      */     } 
/*  354 */     return subMatrix;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> getSubMatrix(final int[] selectedRows, final int[] selectedColumns) {
/*  363 */     checkSubMatrixIndex(selectedRows, selectedColumns);
/*  366 */     FieldMatrix<T> subMatrix = createMatrix(selectedRows.length, selectedColumns.length);
/*  368 */     subMatrix.walkInOptimizedOrder(new DefaultFieldMatrixChangingVisitor<T>((FieldElement)this.field.getZero()) {
/*      */           public T visit(int row, int column, T value) {
/*  373 */             return AbstractFieldMatrix.this.getEntry(selectedRows[row], selectedColumns[column]);
/*      */           }
/*      */         });
/*  378 */     return subMatrix;
/*      */   }
/*      */   
/*      */   public void copySubMatrix(int startRow, int endRow, int startColumn, int endColumn, final T[][] destination) {
/*  387 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/*  388 */     int rowsCount = endRow + 1 - startRow;
/*  389 */     int columnsCount = endColumn + 1 - startColumn;
/*  390 */     if (destination.length < rowsCount || (destination[0]).length < columnsCount)
/*  391 */       throw new MatrixDimensionMismatchException(destination.length, (destination[0]).length, rowsCount, columnsCount); 
/*  398 */     walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>((FieldElement)this.field.getZero()) {
/*      */           private int startRow;
/*      */           
/*      */           private int startColumn;
/*      */           
/*      */           public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
/*  411 */             this.startRow = startRow;
/*  412 */             this.startColumn = startColumn;
/*      */           }
/*      */           
/*      */           public void visit(int row, int column, T value) {
/*  418 */             destination[row - this.startRow][column - this.startColumn] = (FieldElement)value;
/*      */           }
/*      */         }startRow, endRow, startColumn, endColumn);
/*      */   }
/*      */   
/*      */   public void copySubMatrix(int[] selectedRows, int[] selectedColumns, T[][] destination) {
/*  428 */     checkSubMatrixIndex(selectedRows, selectedColumns);
/*  429 */     if (destination.length < selectedRows.length || (destination[0]).length < selectedColumns.length)
/*  431 */       throw new MatrixDimensionMismatchException(destination.length, (destination[0]).length, selectedRows.length, selectedColumns.length); 
/*  438 */     for (int i = 0; i < selectedRows.length; i++) {
/*  439 */       T[] destinationI = destination[i];
/*  440 */       for (int j = 0; j < selectedColumns.length; j++)
/*  441 */         destinationI[j] = getEntry(selectedRows[i], selectedColumns[j]); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setSubMatrix(T[][] subMatrix, int row, int column) {
/*  449 */     if (subMatrix == null)
/*  450 */       throw new NullArgumentException(); 
/*  452 */     int nRows = subMatrix.length;
/*  453 */     if (nRows == 0)
/*  454 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/*  457 */     int nCols = (subMatrix[0]).length;
/*  458 */     if (nCols == 0)
/*  459 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/*  462 */     for (int r = 1; r < nRows; r++) {
/*  463 */       if ((subMatrix[r]).length != nCols)
/*  464 */         throw new DimensionMismatchException(nCols, (subMatrix[r]).length); 
/*      */     } 
/*  468 */     checkRowIndex(row);
/*  469 */     checkColumnIndex(column);
/*  470 */     checkRowIndex(nRows + row - 1);
/*  471 */     checkColumnIndex(nCols + column - 1);
/*  473 */     for (int i = 0; i < nRows; i++) {
/*  474 */       for (int j = 0; j < nCols; j++)
/*  475 */         setEntry(row + i, column + j, subMatrix[i][j]); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> getRowMatrix(int row) {
/*  482 */     checkRowIndex(row);
/*  483 */     int nCols = getColumnDimension();
/*  484 */     FieldMatrix<T> out = createMatrix(1, nCols);
/*  485 */     for (int i = 0; i < nCols; i++)
/*  486 */       out.setEntry(0, i, getEntry(row, i)); 
/*  489 */     return out;
/*      */   }
/*      */   
/*      */   public void setRowMatrix(int row, FieldMatrix<T> matrix) {
/*  495 */     checkRowIndex(row);
/*  496 */     int nCols = getColumnDimension();
/*  497 */     if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols)
/*  499 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols); 
/*  503 */     for (int i = 0; i < nCols; i++)
/*  504 */       setEntry(row, i, matrix.getEntry(0, i)); 
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> getColumnMatrix(int column) {
/*  512 */     checkColumnIndex(column);
/*  513 */     int nRows = getRowDimension();
/*  514 */     FieldMatrix<T> out = createMatrix(nRows, 1);
/*  515 */     for (int i = 0; i < nRows; i++)
/*  516 */       out.setEntry(i, 0, getEntry(i, column)); 
/*  519 */     return out;
/*      */   }
/*      */   
/*      */   public void setColumnMatrix(int column, FieldMatrix<T> matrix) {
/*  525 */     checkColumnIndex(column);
/*  526 */     int nRows = getRowDimension();
/*  527 */     if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1)
/*  529 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1); 
/*  533 */     for (int i = 0; i < nRows; i++)
/*  534 */       setEntry(i, column, matrix.getEntry(i, 0)); 
/*      */   }
/*      */   
/*      */   public FieldVector<T> getRowVector(int row) {
/*  541 */     return new ArrayFieldVector<T>(this.field, getRow(row), false);
/*      */   }
/*      */   
/*      */   public void setRowVector(int row, FieldVector<T> vector) {
/*  546 */     checkRowIndex(row);
/*  547 */     int nCols = getColumnDimension();
/*  548 */     if (vector.getDimension() != nCols)
/*  549 */       throw new MatrixDimensionMismatchException(1, vector.getDimension(), 1, nCols); 
/*  552 */     for (int i = 0; i < nCols; i++)
/*  553 */       setEntry(row, i, vector.getEntry(i)); 
/*      */   }
/*      */   
/*      */   public FieldVector<T> getColumnVector(int column) {
/*  560 */     return new ArrayFieldVector<T>(this.field, getColumn(column), false);
/*      */   }
/*      */   
/*      */   public void setColumnVector(int column, FieldVector<T> vector) {
/*  565 */     checkColumnIndex(column);
/*  566 */     int nRows = getRowDimension();
/*  567 */     if (vector.getDimension() != nRows)
/*  568 */       throw new MatrixDimensionMismatchException(vector.getDimension(), 1, nRows, 1); 
/*  571 */     for (int i = 0; i < nRows; i++)
/*  572 */       setEntry(i, column, vector.getEntry(i)); 
/*      */   }
/*      */   
/*      */   public T[] getRow(int row) {
/*  579 */     checkRowIndex(row);
/*  580 */     int nCols = getColumnDimension();
/*  581 */     T[] out = buildArray(this.field, nCols);
/*  582 */     for (int i = 0; i < nCols; i++)
/*  583 */       out[i] = getEntry(row, i); 
/*  586 */     return out;
/*      */   }
/*      */   
/*      */   public void setRow(int row, T[] array) {
/*  592 */     checkRowIndex(row);
/*  593 */     int nCols = getColumnDimension();
/*  594 */     if (array.length != nCols)
/*  595 */       throw new MatrixDimensionMismatchException(1, array.length, 1, nCols); 
/*  597 */     for (int i = 0; i < nCols; i++)
/*  598 */       setEntry(row, i, array[i]); 
/*      */   }
/*      */   
/*      */   public T[] getColumn(int column) {
/*  605 */     checkColumnIndex(column);
/*  606 */     int nRows = getRowDimension();
/*  607 */     T[] out = buildArray(this.field, nRows);
/*  608 */     for (int i = 0; i < nRows; i++)
/*  609 */       out[i] = getEntry(i, column); 
/*  612 */     return out;
/*      */   }
/*      */   
/*      */   public void setColumn(int column, T[] array) {
/*  618 */     checkColumnIndex(column);
/*  619 */     int nRows = getRowDimension();
/*  620 */     if (array.length != nRows)
/*  621 */       throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1); 
/*  623 */     for (int i = 0; i < nRows; i++)
/*  624 */       setEntry(i, column, array[i]); 
/*      */   }
/*      */   
/*      */   public abstract T getEntry(int paramInt1, int paramInt2);
/*      */   
/*      */   public abstract void setEntry(int paramInt1, int paramInt2, T paramT);
/*      */   
/*      */   public abstract void addToEntry(int paramInt1, int paramInt2, T paramT);
/*      */   
/*      */   public abstract void multiplyEntry(int paramInt1, int paramInt2, T paramT);
/*      */   
/*      */   public FieldMatrix<T> transpose() {
/*  642 */     int nRows = getRowDimension();
/*  643 */     int nCols = getColumnDimension();
/*  644 */     final FieldMatrix<T> out = createMatrix(nCols, nRows);
/*  645 */     walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>((FieldElement)this.field.getZero()) {
/*      */           public void visit(int row, int column, T value) {
/*  649 */             out.setEntry(column, row, value);
/*      */           }
/*      */         });
/*  653 */     return out;
/*      */   }
/*      */   
/*      */   public boolean isSquare() {
/*  658 */     return (getColumnDimension() == getRowDimension());
/*      */   }
/*      */   
/*      */   public abstract int getRowDimension();
/*      */   
/*      */   public abstract int getColumnDimension();
/*      */   
/*      */   public T getTrace() {
/*  669 */     int nRows = getRowDimension();
/*  670 */     int nCols = getColumnDimension();
/*  671 */     if (nRows != nCols)
/*  672 */       throw new NonSquareMatrixException(nRows, nCols); 
/*  674 */     FieldElement fieldElement = (FieldElement)this.field.getZero();
/*  675 */     for (int i = 0; i < nRows; i++)
/*  676 */       fieldElement = (FieldElement)fieldElement.add(getEntry(i, i)); 
/*  678 */     return (T)fieldElement;
/*      */   }
/*      */   
/*      */   public T[] operate(T[] v) {
/*  684 */     int nRows = getRowDimension();
/*  685 */     int nCols = getColumnDimension();
/*  686 */     if (v.length != nCols)
/*  687 */       throw new DimensionMismatchException(v.length, nCols); 
/*  690 */     T[] out = buildArray(this.field, nRows);
/*  691 */     for (int row = 0; row < nRows; row++) {
/*  692 */       FieldElement fieldElement = (FieldElement)this.field.getZero();
/*  693 */       for (int i = 0; i < nCols; i++)
/*  694 */         fieldElement = (FieldElement)fieldElement.add(getEntry(row, i).multiply(v[i])); 
/*  696 */       out[row] = (T)fieldElement;
/*      */     } 
/*  699 */     return out;
/*      */   }
/*      */   
/*      */   public FieldVector<T> operate(FieldVector<T> v) {
/*      */     try {
/*  705 */       return new ArrayFieldVector<T>(this.field, operate(((ArrayFieldVector<T>)v).getDataRef()), false);
/*  706 */     } catch (ClassCastException cce) {
/*  707 */       int nRows = getRowDimension();
/*  708 */       int nCols = getColumnDimension();
/*  709 */       if (v.getDimension() != nCols)
/*  710 */         throw new DimensionMismatchException(v.getDimension(), nCols); 
/*  713 */       T[] out = buildArray(this.field, nRows);
/*  714 */       for (int row = 0; row < nRows; row++) {
/*  715 */         FieldElement fieldElement = (FieldElement)this.field.getZero();
/*  716 */         for (int i = 0; i < nCols; i++)
/*  717 */           fieldElement = (FieldElement)fieldElement.add(getEntry(row, i).multiply(v.getEntry(i))); 
/*  719 */         out[row] = (T)fieldElement;
/*      */       } 
/*  722 */       return new ArrayFieldVector<T>(this.field, out, false);
/*      */     } 
/*      */   }
/*      */   
/*      */   public T[] preMultiply(T[] v) {
/*  729 */     int nRows = getRowDimension();
/*  730 */     int nCols = getColumnDimension();
/*  731 */     if (v.length != nRows)
/*  732 */       throw new DimensionMismatchException(v.length, nRows); 
/*  735 */     T[] out = buildArray(this.field, nCols);
/*  736 */     for (int col = 0; col < nCols; col++) {
/*  737 */       FieldElement fieldElement = (FieldElement)this.field.getZero();
/*  738 */       for (int i = 0; i < nRows; i++)
/*  739 */         fieldElement = (FieldElement)fieldElement.add(getEntry(i, col).multiply(v[i])); 
/*  741 */       out[col] = (T)fieldElement;
/*      */     } 
/*  744 */     return out;
/*      */   }
/*      */   
/*      */   public FieldVector<T> preMultiply(FieldVector<T> v) {
/*      */     try {
/*  750 */       return new ArrayFieldVector<T>(this.field, preMultiply(((ArrayFieldVector<T>)v).getDataRef()), false);
/*  751 */     } catch (ClassCastException cce) {
/*  752 */       int nRows = getRowDimension();
/*  753 */       int nCols = getColumnDimension();
/*  754 */       if (v.getDimension() != nRows)
/*  755 */         throw new DimensionMismatchException(v.getDimension(), nRows); 
/*  758 */       T[] out = buildArray(this.field, nCols);
/*  759 */       for (int col = 0; col < nCols; col++) {
/*  760 */         FieldElement fieldElement = (FieldElement)this.field.getZero();
/*  761 */         for (int i = 0; i < nRows; i++)
/*  762 */           fieldElement = (FieldElement)fieldElement.add(getEntry(i, col).multiply(v.getEntry(i))); 
/*  764 */         out[col] = (T)fieldElement;
/*      */       } 
/*  767 */       return new ArrayFieldVector<T>(this.field, out, false);
/*      */     } 
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor) {
/*  773 */     int rows = getRowDimension();
/*  774 */     int columns = getColumnDimension();
/*  775 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/*  776 */     for (int row = 0; row < rows; row++) {
/*  777 */       for (int column = 0; column < columns; column++) {
/*  778 */         T oldValue = getEntry(row, column);
/*  779 */         T newValue = visitor.visit(row, column, oldValue);
/*  780 */         setEntry(row, column, newValue);
/*      */       } 
/*      */     } 
/*  783 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor) {
/*  788 */     int rows = getRowDimension();
/*  789 */     int columns = getColumnDimension();
/*  790 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/*  791 */     for (int row = 0; row < rows; row++) {
/*  792 */       for (int column = 0; column < columns; column++)
/*  793 */         visitor.visit(row, column, getEntry(row, column)); 
/*      */     } 
/*  796 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/*  803 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/*  804 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/*  806 */     for (int row = startRow; row <= endRow; row++) {
/*  807 */       for (int column = startColumn; column <= endColumn; column++) {
/*  808 */         T oldValue = getEntry(row, column);
/*  809 */         T newValue = visitor.visit(row, column, oldValue);
/*  810 */         setEntry(row, column, newValue);
/*      */       } 
/*      */     } 
/*  813 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/*  820 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/*  821 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/*  823 */     for (int row = startRow; row <= endRow; row++) {
/*  824 */       for (int column = startColumn; column <= endColumn; column++)
/*  825 */         visitor.visit(row, column, getEntry(row, column)); 
/*      */     } 
/*  828 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInColumnOrder(FieldMatrixChangingVisitor<T> visitor) {
/*  833 */     int rows = getRowDimension();
/*  834 */     int columns = getColumnDimension();
/*  835 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/*  836 */     for (int column = 0; column < columns; column++) {
/*  837 */       for (int row = 0; row < rows; row++) {
/*  838 */         T oldValue = getEntry(row, column);
/*  839 */         T newValue = visitor.visit(row, column, oldValue);
/*  840 */         setEntry(row, column, newValue);
/*      */       } 
/*      */     } 
/*  843 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> visitor) {
/*  848 */     int rows = getRowDimension();
/*  849 */     int columns = getColumnDimension();
/*  850 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/*  851 */     for (int column = 0; column < columns; column++) {
/*  852 */       for (int row = 0; row < rows; row++)
/*  853 */         visitor.visit(row, column, getEntry(row, column)); 
/*      */     } 
/*  856 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInColumnOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/*  863 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/*  864 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/*  866 */     for (int column = startColumn; column <= endColumn; column++) {
/*  867 */       for (int row = startRow; row <= endRow; row++) {
/*  868 */         T oldValue = getEntry(row, column);
/*  869 */         T newValue = visitor.visit(row, column, oldValue);
/*  870 */         setEntry(row, column, newValue);
/*      */       } 
/*      */     } 
/*  873 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/*  880 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/*  881 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/*  883 */     for (int column = startColumn; column <= endColumn; column++) {
/*  884 */       for (int row = startRow; row <= endRow; row++)
/*  885 */         visitor.visit(row, column, getEntry(row, column)); 
/*      */     } 
/*  888 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor) {
/*  893 */     return walkInRowOrder(visitor);
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor) {
/*  898 */     return walkInRowOrder(visitor);
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/*  905 */     return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/*  912 */     return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
/*      */   }
/*      */   
/*      */   public String toString() {
/*  921 */     int nRows = getRowDimension();
/*  922 */     int nCols = getColumnDimension();
/*  923 */     StringBuffer res = new StringBuffer();
/*  924 */     String fullClassName = getClass().getName();
/*  925 */     String shortClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
/*  926 */     res.append(shortClassName).append("{");
/*  928 */     for (int i = 0; i < nRows; i++) {
/*  929 */       if (i > 0)
/*  930 */         res.append(","); 
/*  932 */       res.append("{");
/*  933 */       for (int j = 0; j < nCols; j++) {
/*  934 */         if (j > 0)
/*  935 */           res.append(","); 
/*  937 */         res.append(getEntry(i, j));
/*      */       } 
/*  939 */       res.append("}");
/*      */     } 
/*  942 */     res.append("}");
/*  943 */     return res.toString();
/*      */   }
/*      */   
/*      */   public boolean equals(Object object) {
/*  956 */     if (object == this)
/*  957 */       return true; 
/*  959 */     if (!(object instanceof FieldMatrix))
/*  960 */       return false; 
/*  962 */     FieldMatrix<?> m = (FieldMatrix)object;
/*  963 */     int nRows = getRowDimension();
/*  964 */     int nCols = getColumnDimension();
/*  965 */     if (m.getColumnDimension() != nCols || m.getRowDimension() != nRows)
/*  966 */       return false; 
/*  968 */     for (int row = 0; row < nRows; row++) {
/*  969 */       for (int col = 0; col < nCols; col++) {
/*  970 */         if (!getEntry(row, col).equals(m.getEntry(row, col)))
/*  971 */           return false; 
/*      */       } 
/*      */     } 
/*  975 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  985 */     int ret = 322562;
/*  986 */     int nRows = getRowDimension();
/*  987 */     int nCols = getColumnDimension();
/*  988 */     ret = ret * 31 + nRows;
/*  989 */     ret = ret * 31 + nCols;
/*  990 */     for (int row = 0; row < nRows; row++) {
/*  991 */       for (int col = 0; col < nCols; col++)
/*  992 */         ret = ret * 31 + (11 * (row + 1) + 17 * (col + 1)) * getEntry(row, col).hashCode(); 
/*      */     } 
/*  995 */     return ret;
/*      */   }
/*      */   
/*      */   protected void checkRowIndex(int row) {
/* 1005 */     if (row < 0 || row >= getRowDimension())
/* 1006 */       throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, Integer.valueOf(row), Integer.valueOf(0), Integer.valueOf(getRowDimension() - 1)); 
/*      */   }
/*      */   
/*      */   protected void checkColumnIndex(int column) {
/* 1018 */     if (column < 0 || column >= getColumnDimension())
/* 1019 */       throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, Integer.valueOf(column), Integer.valueOf(0), Integer.valueOf(getColumnDimension() - 1)); 
/*      */   }
/*      */   
/*      */   protected void checkSubMatrixIndex(int startRow, int endRow, int startColumn, int endColumn) {
/* 1038 */     checkRowIndex(startRow);
/* 1039 */     checkRowIndex(endRow);
/* 1040 */     if (endRow < startRow)
/* 1041 */       throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(endRow), Integer.valueOf(startRow), true); 
/* 1045 */     checkColumnIndex(startColumn);
/* 1046 */     checkColumnIndex(endColumn);
/* 1047 */     if (endColumn < startColumn)
/* 1048 */       throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, Integer.valueOf(endColumn), Integer.valueOf(startColumn), true); 
/*      */   }
/*      */   
/*      */   protected void checkSubMatrixIndex(int[] selectedRows, int[] selectedColumns) {
/* 1064 */     if (selectedRows == null || selectedColumns == null)
/* 1066 */       throw new NullArgumentException(); 
/* 1068 */     if (selectedRows.length == 0 || selectedColumns.length == 0)
/* 1070 */       throw new NoDataException(); 
/* 1073 */     for (int row : selectedRows)
/* 1074 */       checkRowIndex(row); 
/* 1076 */     for (int column : selectedColumns)
/* 1077 */       checkColumnIndex(column); 
/*      */   }
/*      */   
/*      */   protected void checkAdditionCompatible(FieldMatrix<T> m) {
/* 1089 */     if (getRowDimension() != m.getRowDimension() || getColumnDimension() != m.getColumnDimension())
/* 1091 */       throw new MatrixDimensionMismatchException(m.getRowDimension(), m.getColumnDimension(), getRowDimension(), getColumnDimension()); 
/*      */   }
/*      */   
/*      */   protected void checkSubtractionCompatible(FieldMatrix<T> m) {
/* 1104 */     if (getRowDimension() != m.getRowDimension() || getColumnDimension() != m.getColumnDimension())
/* 1106 */       throw new MatrixDimensionMismatchException(m.getRowDimension(), m.getColumnDimension(), getRowDimension(), getColumnDimension()); 
/*      */   }
/*      */   
/*      */   protected void checkMultiplicationCompatible(FieldMatrix<T> m) {
/* 1119 */     if (getColumnDimension() != m.getRowDimension())
/* 1120 */       throw new DimensionMismatchException(m.getRowDimension(), getColumnDimension()); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\AbstractFieldMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */