/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public abstract class AbstractRealMatrix extends RealLinearOperator implements RealMatrix {
/*     */   protected AbstractRealMatrix() {}
/*     */   
/*     */   protected AbstractRealMatrix(int rowDimension, int columnDimension) {
/*  54 */     if (rowDimension < 1)
/*  55 */       throw new NotStrictlyPositiveException(Integer.valueOf(rowDimension)); 
/*  57 */     if (columnDimension < 1)
/*  58 */       throw new NotStrictlyPositiveException(Integer.valueOf(columnDimension)); 
/*     */   }
/*     */   
/*     */   public abstract RealMatrix createMatrix(int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract RealMatrix copy();
/*     */   
/*     */   public RealMatrix add(RealMatrix m) {
/*  71 */     MatrixUtils.checkAdditionCompatible(this, m);
/*  73 */     int rowCount = getRowDimension();
/*  74 */     int columnCount = getColumnDimension();
/*  75 */     RealMatrix out = createMatrix(rowCount, columnCount);
/*  76 */     for (int row = 0; row < rowCount; row++) {
/*  77 */       for (int col = 0; col < columnCount; col++)
/*  78 */         out.setEntry(row, col, getEntry(row, col) + m.getEntry(row, col)); 
/*     */     } 
/*  82 */     return out;
/*     */   }
/*     */   
/*     */   public RealMatrix subtract(RealMatrix m) {
/*  88 */     MatrixUtils.checkSubtractionCompatible(this, m);
/*  90 */     int rowCount = getRowDimension();
/*  91 */     int columnCount = getColumnDimension();
/*  92 */     RealMatrix out = createMatrix(rowCount, columnCount);
/*  93 */     for (int row = 0; row < rowCount; row++) {
/*  94 */       for (int col = 0; col < columnCount; col++)
/*  95 */         out.setEntry(row, col, getEntry(row, col) - m.getEntry(row, col)); 
/*     */     } 
/*  99 */     return out;
/*     */   }
/*     */   
/*     */   public RealMatrix scalarAdd(double d) {
/* 104 */     int rowCount = getRowDimension();
/* 105 */     int columnCount = getColumnDimension();
/* 106 */     RealMatrix out = createMatrix(rowCount, columnCount);
/* 107 */     for (int row = 0; row < rowCount; row++) {
/* 108 */       for (int col = 0; col < columnCount; col++)
/* 109 */         out.setEntry(row, col, getEntry(row, col) + d); 
/*     */     } 
/* 113 */     return out;
/*     */   }
/*     */   
/*     */   public RealMatrix scalarMultiply(double d) {
/* 118 */     int rowCount = getRowDimension();
/* 119 */     int columnCount = getColumnDimension();
/* 120 */     RealMatrix out = createMatrix(rowCount, columnCount);
/* 121 */     for (int row = 0; row < rowCount; row++) {
/* 122 */       for (int col = 0; col < columnCount; col++)
/* 123 */         out.setEntry(row, col, getEntry(row, col) * d); 
/*     */     } 
/* 127 */     return out;
/*     */   }
/*     */   
/*     */   public RealMatrix multiply(RealMatrix m) {
/* 133 */     MatrixUtils.checkMultiplicationCompatible(this, m);
/* 135 */     int nRows = getRowDimension();
/* 136 */     int nCols = m.getColumnDimension();
/* 137 */     int nSum = getColumnDimension();
/* 138 */     RealMatrix out = createMatrix(nRows, nCols);
/* 139 */     for (int row = 0; row < nRows; row++) {
/* 140 */       for (int col = 0; col < nCols; col++) {
/* 141 */         double sum = 0.0D;
/* 142 */         for (int i = 0; i < nSum; i++)
/* 143 */           sum += getEntry(row, i) * m.getEntry(i, col); 
/* 145 */         out.setEntry(row, col, sum);
/*     */       } 
/*     */     } 
/* 149 */     return out;
/*     */   }
/*     */   
/*     */   public RealMatrix preMultiply(RealMatrix m) {
/* 154 */     return m.multiply(this);
/*     */   }
/*     */   
/*     */   public RealMatrix power(int p) {
/* 159 */     if (p < 0)
/* 160 */       throw new IllegalArgumentException("p must be >= 0"); 
/* 163 */     if (!isSquare())
/* 164 */       throw new NonSquareMatrixException(getRowDimension(), getColumnDimension()); 
/* 167 */     if (p == 0)
/* 168 */       return MatrixUtils.createRealIdentityMatrix(getRowDimension()); 
/* 171 */     if (p == 1)
/* 172 */       return copy(); 
/* 175 */     int power = p - 1;
/* 184 */     char[] binaryRepresentation = Integer.toBinaryString(power).toCharArray();
/* 185 */     ArrayList<Integer> nonZeroPositions = new ArrayList<Integer>();
/* 186 */     int maxI = -1;
/* 188 */     for (int i = 0; i < binaryRepresentation.length; i++) {
/* 189 */       if (binaryRepresentation[i] == '1') {
/* 190 */         int pos = binaryRepresentation.length - i - 1;
/* 191 */         nonZeroPositions.add(Integer.valueOf(pos));
/* 194 */         if (maxI == -1)
/* 195 */           maxI = pos; 
/*     */       } 
/*     */     } 
/* 200 */     RealMatrix[] results = new RealMatrix[maxI + 1];
/* 201 */     results[0] = copy();
/* 203 */     for (int j = 1; j <= maxI; j++)
/* 204 */       results[j] = results[j - 1].multiply(results[j - 1]); 
/* 207 */     RealMatrix result = copy();
/* 209 */     for (Integer integer : nonZeroPositions)
/* 210 */       result = result.multiply(results[integer.intValue()]); 
/* 213 */     return result;
/*     */   }
/*     */   
/*     */   public double[][] getData() {
/* 218 */     double[][] data = new double[getRowDimension()][getColumnDimension()];
/* 220 */     for (int i = 0; i < data.length; i++) {
/* 221 */       double[] dataI = data[i];
/* 222 */       for (int j = 0; j < dataI.length; j++)
/* 223 */         dataI[j] = getEntry(i, j); 
/*     */     } 
/* 227 */     return data;
/*     */   }
/*     */   
/*     */   public double getNorm() {
/* 232 */     return walkInColumnOrder(new RealMatrixPreservingVisitor() {
/*     */           private double endRow;
/*     */           
/*     */           private double columnSum;
/*     */           
/*     */           private double maxColSum;
/*     */           
/*     */           public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
/* 247 */             this.endRow = endRow;
/* 248 */             this.columnSum = 0.0D;
/* 249 */             this.maxColSum = 0.0D;
/*     */           }
/*     */           
/*     */           public void visit(int row, int column, double value) {
/* 254 */             this.columnSum += FastMath.abs(value);
/* 255 */             if (row == this.endRow) {
/* 256 */               this.maxColSum = FastMath.max(this.maxColSum, this.columnSum);
/* 257 */               this.columnSum = 0.0D;
/*     */             } 
/*     */           }
/*     */           
/*     */           public double end() {
/* 263 */             return this.maxColSum;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public double getFrobeniusNorm() {
/* 270 */     return walkInOptimizedOrder(new RealMatrixPreservingVisitor() {
/*     */           private double sum;
/*     */           
/*     */           public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
/* 279 */             this.sum = 0.0D;
/*     */           }
/*     */           
/*     */           public void visit(int row, int column, double value) {
/* 284 */             this.sum += value * value;
/*     */           }
/*     */           
/*     */           public double end() {
/* 289 */             return FastMath.sqrt(this.sum);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public RealMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) {
/* 297 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 299 */     RealMatrix subMatrix = createMatrix(endRow - startRow + 1, endColumn - startColumn + 1);
/* 301 */     for (int i = startRow; i <= endRow; i++) {
/* 302 */       for (int j = startColumn; j <= endColumn; j++)
/* 303 */         subMatrix.setEntry(i - startRow, j - startColumn, getEntry(i, j)); 
/*     */     } 
/* 307 */     return subMatrix;
/*     */   }
/*     */   
/*     */   public RealMatrix getSubMatrix(final int[] selectedRows, final int[] selectedColumns) {
/* 313 */     MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
/* 316 */     RealMatrix subMatrix = createMatrix(selectedRows.length, selectedColumns.length);
/* 318 */     subMatrix.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
/*     */           public double visit(int row, int column, double value) {
/* 323 */             return AbstractRealMatrix.this.getEntry(selectedRows[row], selectedColumns[column]);
/*     */           }
/*     */         });
/* 328 */     return subMatrix;
/*     */   }
/*     */   
/*     */   public void copySubMatrix(int startRow, int endRow, int startColumn, int endColumn, final double[][] destination) {
/* 336 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 337 */     int rowsCount = endRow + 1 - startRow;
/* 338 */     int columnsCount = endColumn + 1 - startColumn;
/* 339 */     if (destination.length < rowsCount || (destination[0]).length < columnsCount)
/* 340 */       throw new MatrixDimensionMismatchException(destination.length, (destination[0]).length, rowsCount, columnsCount); 
/* 345 */     walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() {
/*     */           private int startRow;
/*     */           
/*     */           private int startColumn;
/*     */           
/*     */           public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
/* 358 */             this.startRow = startRow;
/* 359 */             this.startColumn = startColumn;
/*     */           }
/*     */           
/*     */           public void visit(int row, int column, double value) {
/* 365 */             destination[row - this.startRow][column - this.startColumn] = value;
/*     */           }
/*     */         },  startRow, endRow, startColumn, endColumn);
/*     */   }
/*     */   
/*     */   public void copySubMatrix(int[] selectedRows, int[] selectedColumns, double[][] destination) {
/* 374 */     MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
/* 375 */     if (destination.length < selectedRows.length || (destination[0]).length < selectedColumns.length)
/* 377 */       throw new MatrixDimensionMismatchException(destination.length, (destination[0]).length, selectedRows.length, selectedColumns.length); 
/* 382 */     for (int i = 0; i < selectedRows.length; i++) {
/* 383 */       double[] destinationI = destination[i];
/* 384 */       for (int j = 0; j < selectedColumns.length; j++)
/* 385 */         destinationI[j] = getEntry(selectedRows[i], selectedColumns[j]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSubMatrix(double[][] subMatrix, int row, int column) throws NoDataException, DimensionMismatchException, NullArgumentException {
/* 393 */     MathUtils.checkNotNull(subMatrix);
/* 394 */     int nRows = subMatrix.length;
/* 395 */     if (nRows == 0)
/* 396 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/* 399 */     int nCols = (subMatrix[0]).length;
/* 400 */     if (nCols == 0)
/* 401 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/* 404 */     for (int r = 1; r < nRows; r++) {
/* 405 */       if ((subMatrix[r]).length != nCols)
/* 406 */         throw new DimensionMismatchException(nCols, (subMatrix[r]).length); 
/*     */     } 
/* 410 */     MatrixUtils.checkRowIndex(this, row);
/* 411 */     MatrixUtils.checkColumnIndex(this, column);
/* 412 */     MatrixUtils.checkRowIndex(this, nRows + row - 1);
/* 413 */     MatrixUtils.checkColumnIndex(this, nCols + column - 1);
/* 415 */     for (int i = 0; i < nRows; i++) {
/* 416 */       for (int j = 0; j < nCols; j++)
/* 417 */         setEntry(row + i, column + j, subMatrix[i][j]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getRowMatrix(int row) {
/* 424 */     MatrixUtils.checkRowIndex(this, row);
/* 425 */     int nCols = getColumnDimension();
/* 426 */     RealMatrix out = createMatrix(1, nCols);
/* 427 */     for (int i = 0; i < nCols; i++)
/* 428 */       out.setEntry(0, i, getEntry(row, i)); 
/* 431 */     return out;
/*     */   }
/*     */   
/*     */   public void setRowMatrix(int row, RealMatrix matrix) {
/* 436 */     MatrixUtils.checkRowIndex(this, row);
/* 437 */     int nCols = getColumnDimension();
/* 438 */     if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols)
/* 440 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols); 
/* 444 */     for (int i = 0; i < nCols; i++)
/* 445 */       setEntry(row, i, matrix.getEntry(0, i)); 
/*     */   }
/*     */   
/*     */   public RealMatrix getColumnMatrix(int column) {
/* 451 */     MatrixUtils.checkColumnIndex(this, column);
/* 452 */     int nRows = getRowDimension();
/* 453 */     RealMatrix out = createMatrix(nRows, 1);
/* 454 */     for (int i = 0; i < nRows; i++)
/* 455 */       out.setEntry(i, 0, getEntry(i, column)); 
/* 458 */     return out;
/*     */   }
/*     */   
/*     */   public void setColumnMatrix(int column, RealMatrix matrix) {
/* 463 */     MatrixUtils.checkColumnIndex(this, column);
/* 464 */     int nRows = getRowDimension();
/* 465 */     if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1)
/* 467 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1); 
/* 471 */     for (int i = 0; i < nRows; i++)
/* 472 */       setEntry(i, column, matrix.getEntry(i, 0)); 
/*     */   }
/*     */   
/*     */   public RealVector getRowVector(int row) {
/* 478 */     return new ArrayRealVector(getRow(row), false);
/*     */   }
/*     */   
/*     */   public void setRowVector(int row, RealVector vector) {
/* 483 */     MatrixUtils.checkRowIndex(this, row);
/* 484 */     int nCols = getColumnDimension();
/* 485 */     if (vector.getDimension() != nCols)
/* 486 */       throw new MatrixDimensionMismatchException(1, vector.getDimension(), 1, nCols); 
/* 489 */     for (int i = 0; i < nCols; i++)
/* 490 */       setEntry(row, i, vector.getEntry(i)); 
/*     */   }
/*     */   
/*     */   public RealVector getColumnVector(int column) {
/* 496 */     return new ArrayRealVector(getColumn(column), false);
/*     */   }
/*     */   
/*     */   public void setColumnVector(int column, RealVector vector) {
/* 501 */     MatrixUtils.checkColumnIndex(this, column);
/* 502 */     int nRows = getRowDimension();
/* 503 */     if (vector.getDimension() != nRows)
/* 504 */       throw new MatrixDimensionMismatchException(vector.getDimension(), 1, nRows, 1); 
/* 507 */     for (int i = 0; i < nRows; i++)
/* 508 */       setEntry(i, column, vector.getEntry(i)); 
/*     */   }
/*     */   
/*     */   public double[] getRow(int row) {
/* 514 */     MatrixUtils.checkRowIndex(this, row);
/* 515 */     int nCols = getColumnDimension();
/* 516 */     double[] out = new double[nCols];
/* 517 */     for (int i = 0; i < nCols; i++)
/* 518 */       out[i] = getEntry(row, i); 
/* 521 */     return out;
/*     */   }
/*     */   
/*     */   public void setRow(int row, double[] array) {
/* 526 */     MatrixUtils.checkRowIndex(this, row);
/* 527 */     int nCols = getColumnDimension();
/* 528 */     if (array.length != nCols)
/* 529 */       throw new MatrixDimensionMismatchException(1, array.length, 1, nCols); 
/* 531 */     for (int i = 0; i < nCols; i++)
/* 532 */       setEntry(row, i, array[i]); 
/*     */   }
/*     */   
/*     */   public double[] getColumn(int column) {
/* 538 */     MatrixUtils.checkColumnIndex(this, column);
/* 539 */     int nRows = getRowDimension();
/* 540 */     double[] out = new double[nRows];
/* 541 */     for (int i = 0; i < nRows; i++)
/* 542 */       out[i] = getEntry(i, column); 
/* 545 */     return out;
/*     */   }
/*     */   
/*     */   public void setColumn(int column, double[] array) {
/* 550 */     MatrixUtils.checkColumnIndex(this, column);
/* 551 */     int nRows = getRowDimension();
/* 552 */     if (array.length != nRows)
/* 553 */       throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1); 
/* 555 */     for (int i = 0; i < nRows; i++)
/* 556 */       setEntry(i, column, array[i]); 
/*     */   }
/*     */   
/*     */   public abstract double getEntry(int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract void setEntry(int paramInt1, int paramInt2, double paramDouble);
/*     */   
/*     */   public void addToEntry(int row, int column, double increment) {
/* 568 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 569 */     setEntry(row, column, getEntry(row, column) + increment);
/*     */   }
/*     */   
/*     */   public void multiplyEntry(int row, int column, double factor) {
/* 574 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 575 */     setEntry(row, column, getEntry(row, column) * factor);
/*     */   }
/*     */   
/*     */   public RealMatrix transpose() {
/* 580 */     int nRows = getRowDimension();
/* 581 */     int nCols = getColumnDimension();
/* 582 */     final RealMatrix out = createMatrix(nCols, nRows);
/* 583 */     walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() {
/*     */           public void visit(int row, int column, double value) {
/* 588 */             out.setEntry(column, row, value);
/*     */           }
/*     */         });
/* 593 */     return out;
/*     */   }
/*     */   
/*     */   public boolean isSquare() {
/* 598 */     return (getColumnDimension() == getRowDimension());
/*     */   }
/*     */   
/*     */   public abstract int getRowDimension();
/*     */   
/*     */   public abstract int getColumnDimension();
/*     */   
/*     */   public double getTrace() {
/* 621 */     int nRows = getRowDimension();
/* 622 */     int nCols = getColumnDimension();
/* 623 */     if (nRows != nCols)
/* 624 */       throw new NonSquareMatrixException(nRows, nCols); 
/* 626 */     double trace = 0.0D;
/* 627 */     for (int i = 0; i < nRows; i++)
/* 628 */       trace += getEntry(i, i); 
/* 630 */     return trace;
/*     */   }
/*     */   
/*     */   public double[] operate(double[] v) {
/* 635 */     int nRows = getRowDimension();
/* 636 */     int nCols = getColumnDimension();
/* 637 */     if (v.length != nCols)
/* 638 */       throw new DimensionMismatchException(v.length, nCols); 
/* 641 */     double[] out = new double[nRows];
/* 642 */     for (int row = 0; row < nRows; row++) {
/* 643 */       double sum = 0.0D;
/* 644 */       for (int i = 0; i < nCols; i++)
/* 645 */         sum += getEntry(row, i) * v[i]; 
/* 647 */       out[row] = sum;
/*     */     } 
/* 650 */     return out;
/*     */   }
/*     */   
/*     */   public RealVector operate(RealVector v) {
/*     */     try {
/* 657 */       return new ArrayRealVector(operate(((ArrayRealVector)v).getDataRef()), false);
/* 658 */     } catch (ClassCastException cce) {
/* 659 */       int nRows = getRowDimension();
/* 660 */       int nCols = getColumnDimension();
/* 661 */       if (v.getDimension() != nCols)
/* 662 */         throw new DimensionMismatchException(v.getDimension(), nCols); 
/* 665 */       double[] out = new double[nRows];
/* 666 */       for (int row = 0; row < nRows; row++) {
/* 667 */         double sum = 0.0D;
/* 668 */         for (int i = 0; i < nCols; i++)
/* 669 */           sum += getEntry(row, i) * v.getEntry(i); 
/* 671 */         out[row] = sum;
/*     */       } 
/* 674 */       return new ArrayRealVector(out, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] preMultiply(double[] v) {
/* 681 */     int nRows = getRowDimension();
/* 682 */     int nCols = getColumnDimension();
/* 683 */     if (v.length != nRows)
/* 684 */       throw new DimensionMismatchException(v.length, nRows); 
/* 687 */     double[] out = new double[nCols];
/* 688 */     for (int col = 0; col < nCols; col++) {
/* 689 */       double sum = 0.0D;
/* 690 */       for (int i = 0; i < nRows; i++)
/* 691 */         sum += getEntry(i, col) * v[i]; 
/* 693 */       out[col] = sum;
/*     */     } 
/* 696 */     return out;
/*     */   }
/*     */   
/*     */   public RealVector preMultiply(RealVector v) {
/*     */     try {
/* 702 */       return new ArrayRealVector(preMultiply(((ArrayRealVector)v).getDataRef()), false);
/* 703 */     } catch (ClassCastException cce) {
/* 705 */       int nRows = getRowDimension();
/* 706 */       int nCols = getColumnDimension();
/* 707 */       if (v.getDimension() != nRows)
/* 708 */         throw new DimensionMismatchException(v.getDimension(), nRows); 
/* 711 */       double[] out = new double[nCols];
/* 712 */       for (int col = 0; col < nCols; col++) {
/* 713 */         double sum = 0.0D;
/* 714 */         for (int i = 0; i < nRows; i++)
/* 715 */           sum += getEntry(i, col) * v.getEntry(i); 
/* 717 */         out[col] = sum;
/*     */       } 
/* 720 */       return new ArrayRealVector(out, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixChangingVisitor visitor) {
/* 726 */     int rows = getRowDimension();
/* 727 */     int columns = getColumnDimension();
/* 728 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 729 */     for (int row = 0; row < rows; row++) {
/* 730 */       for (int column = 0; column < columns; column++) {
/* 731 */         double oldValue = getEntry(row, column);
/* 732 */         double newValue = visitor.visit(row, column, oldValue);
/* 733 */         setEntry(row, column, newValue);
/*     */       } 
/*     */     } 
/* 736 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixPreservingVisitor visitor) {
/* 741 */     int rows = getRowDimension();
/* 742 */     int columns = getColumnDimension();
/* 743 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 744 */     for (int row = 0; row < rows; row++) {
/* 745 */       for (int column = 0; column < columns; column++)
/* 746 */         visitor.visit(row, column, getEntry(row, column)); 
/*     */     } 
/* 749 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 756 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 757 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 759 */     for (int row = startRow; row <= endRow; row++) {
/* 760 */       for (int column = startColumn; column <= endColumn; column++) {
/* 761 */         double oldValue = getEntry(row, column);
/* 762 */         double newValue = visitor.visit(row, column, oldValue);
/* 763 */         setEntry(row, column, newValue);
/*     */       } 
/*     */     } 
/* 766 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 773 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 774 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 776 */     for (int row = startRow; row <= endRow; row++) {
/* 777 */       for (int column = startColumn; column <= endColumn; column++)
/* 778 */         visitor.visit(row, column, getEntry(row, column)); 
/*     */     } 
/* 781 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixChangingVisitor visitor) {
/* 786 */     int rows = getRowDimension();
/* 787 */     int columns = getColumnDimension();
/* 788 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 789 */     for (int column = 0; column < columns; column++) {
/* 790 */       for (int row = 0; row < rows; row++) {
/* 791 */         double oldValue = getEntry(row, column);
/* 792 */         double newValue = visitor.visit(row, column, oldValue);
/* 793 */         setEntry(row, column, newValue);
/*     */       } 
/*     */     } 
/* 796 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixPreservingVisitor visitor) {
/* 801 */     int rows = getRowDimension();
/* 802 */     int columns = getColumnDimension();
/* 803 */     visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
/* 804 */     for (int column = 0; column < columns; column++) {
/* 805 */       for (int row = 0; row < rows; row++)
/* 806 */         visitor.visit(row, column, getEntry(row, column)); 
/*     */     } 
/* 809 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 816 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 817 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 819 */     for (int column = startColumn; column <= endColumn; column++) {
/* 820 */       for (int row = startRow; row <= endRow; row++) {
/* 821 */         double oldValue = getEntry(row, column);
/* 822 */         double newValue = visitor.visit(row, column, oldValue);
/* 823 */         setEntry(row, column, newValue);
/*     */       } 
/*     */     } 
/* 826 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInColumnOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 833 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 834 */     visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
/* 836 */     for (int column = startColumn; column <= endColumn; column++) {
/* 837 */       for (int row = startRow; row <= endRow; row++)
/* 838 */         visitor.visit(row, column, getEntry(row, column)); 
/*     */     } 
/* 841 */     return visitor.end();
/*     */   }
/*     */   
/*     */   public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor) {
/* 846 */     return walkInRowOrder(visitor);
/*     */   }
/*     */   
/*     */   public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor) {
/* 851 */     return walkInRowOrder(visitor);
/*     */   }
/*     */   
/*     */   public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 858 */     return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
/*     */   }
/*     */   
/*     */   public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 865 */     return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 874 */     int nRows = getRowDimension();
/* 875 */     int nCols = getColumnDimension();
/* 876 */     StringBuffer res = new StringBuffer();
/* 877 */     String fullClassName = getClass().getName();
/* 878 */     String shortClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
/* 879 */     res.append(shortClassName).append("{");
/* 881 */     for (int i = 0; i < nRows; i++) {
/* 882 */       if (i > 0)
/* 883 */         res.append(","); 
/* 885 */       res.append("{");
/* 886 */       for (int j = 0; j < nCols; j++) {
/* 887 */         if (j > 0)
/* 888 */           res.append(","); 
/* 890 */         res.append(getEntry(i, j));
/*     */       } 
/* 892 */       res.append("}");
/*     */     } 
/* 895 */     res.append("}");
/* 896 */     return res.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 909 */     if (object == this)
/* 910 */       return true; 
/* 912 */     if (!(object instanceof RealMatrix))
/* 913 */       return false; 
/* 915 */     RealMatrix m = (RealMatrix)object;
/* 916 */     int nRows = getRowDimension();
/* 917 */     int nCols = getColumnDimension();
/* 918 */     if (m.getColumnDimension() != nCols || m.getRowDimension() != nRows)
/* 919 */       return false; 
/* 921 */     for (int row = 0; row < nRows; row++) {
/* 922 */       for (int col = 0; col < nCols; col++) {
/* 923 */         if (getEntry(row, col) != m.getEntry(row, col))
/* 924 */           return false; 
/*     */       } 
/*     */     } 
/* 928 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 938 */     int ret = 7;
/* 939 */     int nRows = getRowDimension();
/* 940 */     int nCols = getColumnDimension();
/* 941 */     ret = ret * 31 + nRows;
/* 942 */     ret = ret * 31 + nCols;
/* 943 */     for (int row = 0; row < nRows; row++) {
/* 944 */       for (int col = 0; col < nCols; col++)
/* 945 */         ret = ret * 31 + (11 * (row + 1) + 17 * (col + 1)) * MathUtils.hash(getEntry(row, col)); 
/*     */     } 
/* 949 */     return ret;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\AbstractRealMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */