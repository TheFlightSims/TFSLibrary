/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.fraction.BigFraction;
/*     */ import org.apache.commons.math3.fraction.Fraction;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class MatrixUtils {
/*     */   public static RealMatrix createRealMatrix(int rows, int columns) {
/*  70 */     return (rows * columns <= 4096) ? new Array2DRowRealMatrix(rows, columns) : new BlockRealMatrix(rows, columns);
/*     */   }
/*     */   
/*     */   public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(Field<T> field, int rows, int columns) {
/*  92 */     return (rows * columns <= 4096) ? new Array2DRowFieldMatrix<T>(field, rows, columns) : new BlockFieldMatrix<T>(field, rows, columns);
/*     */   }
/*     */   
/*     */   public static RealMatrix createRealMatrix(double[][] data) {
/* 116 */     if (data == null || data[0] == null)
/* 118 */       throw new NullArgumentException(); 
/* 120 */     return (data.length * (data[0]).length <= 4096) ? new Array2DRowRealMatrix(data) : new BlockRealMatrix(data);
/*     */   }
/*     */   
/*     */   public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(T[][] data) {
/* 144 */     if (data == null || data[0] == null)
/* 146 */       throw new NullArgumentException(); 
/* 148 */     return (data.length * (data[0]).length <= 4096) ? new Array2DRowFieldMatrix<T>(data) : new BlockFieldMatrix<T>(data);
/*     */   }
/*     */   
/*     */   public static RealMatrix createRealIdentityMatrix(int dimension) {
/* 161 */     RealMatrix m = createRealMatrix(dimension, dimension);
/* 162 */     for (int i = 0; i < dimension; i++)
/* 163 */       m.setEntry(i, i, 1.0D); 
/* 165 */     return m;
/*     */   }
/*     */   
/*     */   public static <T extends FieldElement<T>> FieldMatrix<T> createFieldIdentityMatrix(Field<T> field, int dimension) {
/* 180 */     FieldElement fieldElement1 = (FieldElement)field.getZero();
/* 181 */     FieldElement fieldElement2 = (FieldElement)field.getOne();
/* 183 */     FieldElement[][] arrayOfFieldElement = (FieldElement[][])Array.newInstance(field.getRuntimeClass(), new int[] { dimension, dimension });
/* 184 */     for (int row = 0; row < dimension; row++) {
/* 185 */       FieldElement[] arrayOfFieldElement1 = arrayOfFieldElement[row];
/* 186 */       Arrays.fill((Object[])arrayOfFieldElement1, fieldElement1);
/* 187 */       arrayOfFieldElement1[row] = fieldElement2;
/*     */     } 
/* 189 */     return new Array2DRowFieldMatrix<T>(field, (T[][])arrayOfFieldElement, false);
/*     */   }
/*     */   
/*     */   public static RealMatrix createRealDiagonalMatrix(double[] diagonal) {
/* 201 */     RealMatrix m = createRealMatrix(diagonal.length, diagonal.length);
/* 202 */     for (int i = 0; i < diagonal.length; i++)
/* 203 */       m.setEntry(i, i, diagonal[i]); 
/* 205 */     return m;
/*     */   }
/*     */   
/*     */   public static <T extends FieldElement<T>> FieldMatrix<T> createFieldDiagonalMatrix(T[] diagonal) {
/* 219 */     FieldMatrix<T> m = createFieldMatrix(diagonal[0].getField(), diagonal.length, diagonal.length);
/* 221 */     for (int i = 0; i < diagonal.length; i++)
/* 222 */       m.setEntry(i, i, diagonal[i]); 
/* 224 */     return m;
/*     */   }
/*     */   
/*     */   public static RealVector createRealVector(double[] data) {
/* 236 */     if (data == null)
/* 237 */       throw new NullArgumentException(); 
/* 239 */     return new ArrayRealVector(data, true);
/*     */   }
/*     */   
/*     */   public static <T extends FieldElement<T>> FieldVector<T> createFieldVector(T[] data) {
/* 253 */     if (data == null)
/* 254 */       throw new NullArgumentException(); 
/* 256 */     if (data.length == 0)
/* 257 */       throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]); 
/* 259 */     return new ArrayFieldVector<T>(data[0].getField(), data, true);
/*     */   }
/*     */   
/*     */   public static RealMatrix createRowRealMatrix(double[] rowData) {
/* 272 */     if (rowData == null)
/* 273 */       throw new NullArgumentException(); 
/* 275 */     int nCols = rowData.length;
/* 276 */     RealMatrix m = createRealMatrix(1, nCols);
/* 277 */     for (int i = 0; i < nCols; i++)
/* 278 */       m.setEntry(0, i, rowData[i]); 
/* 280 */     return m;
/*     */   }
/*     */   
/*     */   public static <T extends FieldElement<T>> FieldMatrix<T> createRowFieldMatrix(T[] rowData) {
/* 295 */     if (rowData == null)
/* 296 */       throw new NullArgumentException(); 
/* 298 */     int nCols = rowData.length;
/* 299 */     if (nCols == 0)
/* 300 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/* 302 */     FieldMatrix<T> m = createFieldMatrix(rowData[0].getField(), 1, nCols);
/* 303 */     for (int i = 0; i < nCols; i++)
/* 304 */       m.setEntry(0, i, rowData[i]); 
/* 306 */     return m;
/*     */   }
/*     */   
/*     */   public static RealMatrix createColumnRealMatrix(double[] columnData) {
/* 319 */     if (columnData == null)
/* 320 */       throw new NullArgumentException(); 
/* 322 */     int nRows = columnData.length;
/* 323 */     RealMatrix m = createRealMatrix(nRows, 1);
/* 324 */     for (int i = 0; i < nRows; i++)
/* 325 */       m.setEntry(i, 0, columnData[i]); 
/* 327 */     return m;
/*     */   }
/*     */   
/*     */   public static <T extends FieldElement<T>> FieldMatrix<T> createColumnFieldMatrix(T[] columnData) {
/* 342 */     if (columnData == null)
/* 343 */       throw new NullArgumentException(); 
/* 345 */     int nRows = columnData.length;
/* 346 */     if (nRows == 0)
/* 347 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW); 
/* 349 */     FieldMatrix<T> m = createFieldMatrix(columnData[0].getField(), nRows, 1);
/* 350 */     for (int i = 0; i < nRows; i++)
/* 351 */       m.setEntry(i, 0, columnData[i]); 
/* 353 */     return m;
/*     */   }
/*     */   
/*     */   public static void checkMatrixIndex(AnyMatrix m, int row, int column) {
/* 367 */     checkRowIndex(m, row);
/* 368 */     checkColumnIndex(m, column);
/*     */   }
/*     */   
/*     */   public static void checkRowIndex(AnyMatrix m, int row) {
/* 379 */     if (row < 0 || row >= m.getRowDimension())
/* 381 */       throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, Integer.valueOf(row), Integer.valueOf(0), Integer.valueOf(m.getRowDimension() - 1)); 
/*     */   }
/*     */   
/*     */   public static void checkColumnIndex(AnyMatrix m, int column) {
/* 394 */     if (column < 0 || column >= m.getColumnDimension())
/* 395 */       throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, Integer.valueOf(column), Integer.valueOf(0), Integer.valueOf(m.getColumnDimension() - 1)); 
/*     */   }
/*     */   
/*     */   public static void checkSubMatrixIndex(AnyMatrix m, int startRow, int endRow, int startColumn, int endColumn) {
/* 416 */     checkRowIndex(m, startRow);
/* 417 */     checkRowIndex(m, endRow);
/* 418 */     if (endRow < startRow)
/* 419 */       throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(endRow), Integer.valueOf(startRow), false); 
/* 423 */     checkColumnIndex(m, startColumn);
/* 424 */     checkColumnIndex(m, endColumn);
/* 425 */     if (endColumn < startColumn)
/* 426 */       throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, Integer.valueOf(endColumn), Integer.valueOf(startColumn), false); 
/*     */   }
/*     */   
/*     */   public static void checkSubMatrixIndex(AnyMatrix m, int[] selectedRows, int[] selectedColumns) {
/* 449 */     if (selectedRows == null)
/* 450 */       throw new NullArgumentException(); 
/* 452 */     if (selectedColumns == null)
/* 453 */       throw new NullArgumentException(); 
/* 455 */     if (selectedRows.length == 0)
/* 456 */       throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_ROW_INDEX_ARRAY); 
/* 458 */     if (selectedColumns.length == 0)
/* 459 */       throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_COLUMN_INDEX_ARRAY); 
/* 462 */     for (int row : selectedRows)
/* 463 */       checkRowIndex(m, row); 
/* 465 */     for (int column : selectedColumns)
/* 466 */       checkColumnIndex(m, column); 
/*     */   }
/*     */   
/*     */   public static void checkAdditionCompatible(AnyMatrix left, AnyMatrix right) {
/* 478 */     if (left.getRowDimension() != right.getRowDimension() || left.getColumnDimension() != right.getColumnDimension())
/* 480 */       throw new MatrixDimensionMismatchException(left.getRowDimension(), left.getColumnDimension(), right.getRowDimension(), right.getColumnDimension()); 
/*     */   }
/*     */   
/*     */   public static void checkSubtractionCompatible(AnyMatrix left, AnyMatrix right) {
/* 493 */     if (left.getRowDimension() != right.getRowDimension() || left.getColumnDimension() != right.getColumnDimension())
/* 495 */       throw new MatrixDimensionMismatchException(left.getRowDimension(), left.getColumnDimension(), right.getRowDimension(), right.getColumnDimension()); 
/*     */   }
/*     */   
/*     */   public static void checkMultiplicationCompatible(AnyMatrix left, AnyMatrix right) {
/* 508 */     if (left.getColumnDimension() != right.getRowDimension())
/* 509 */       throw new DimensionMismatchException(left.getColumnDimension(), right.getRowDimension()); 
/*     */   }
/*     */   
/*     */   public static Array2DRowRealMatrix fractionMatrixToRealMatrix(FieldMatrix<Fraction> m) {
/* 520 */     FractionMatrixConverter converter = new FractionMatrixConverter();
/* 521 */     m.walkInOptimizedOrder(converter);
/* 522 */     return converter.getConvertedMatrix();
/*     */   }
/*     */   
/*     */   private static class FractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<Fraction> {
/*     */     private double[][] data;
/*     */     
/*     */     public FractionMatrixConverter() {
/* 531 */       super(Fraction.ZERO);
/*     */     }
/*     */     
/*     */     public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
/* 538 */       this.data = new double[rows][columns];
/*     */     }
/*     */     
/*     */     public void visit(int row, int column, Fraction value) {
/* 544 */       this.data[row][column] = value.doubleValue();
/*     */     }
/*     */     
/*     */     Array2DRowRealMatrix getConvertedMatrix() {
/* 553 */       return new Array2DRowRealMatrix(this.data, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Array2DRowRealMatrix bigFractionMatrixToRealMatrix(FieldMatrix<BigFraction> m) {
/* 565 */     BigFractionMatrixConverter converter = new BigFractionMatrixConverter();
/* 566 */     m.walkInOptimizedOrder(converter);
/* 567 */     return converter.getConvertedMatrix();
/*     */   }
/*     */   
/*     */   private static class BigFractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<BigFraction> {
/*     */     private double[][] data;
/*     */     
/*     */     public BigFractionMatrixConverter() {
/* 576 */       super(BigFraction.ZERO);
/*     */     }
/*     */     
/*     */     public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
/* 583 */       this.data = new double[rows][columns];
/*     */     }
/*     */     
/*     */     public void visit(int row, int column, BigFraction value) {
/* 589 */       this.data[row][column] = value.doubleValue();
/*     */     }
/*     */     
/*     */     Array2DRowRealMatrix getConvertedMatrix() {
/* 598 */       return new Array2DRowRealMatrix(this.data, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void serializeRealVector(RealVector vector, ObjectOutputStream oos) throws IOException {
/* 645 */     int n = vector.getDimension();
/* 646 */     oos.writeInt(n);
/* 647 */     for (int i = 0; i < n; i++)
/* 648 */       oos.writeDouble(vector.getEntry(i)); 
/*     */   }
/*     */   
/*     */   public static void deserializeRealVector(Object instance, String fieldName, ObjectInputStream ois) throws ClassNotFoundException, IOException {
/*     */     try {
/* 676 */       int n = ois.readInt();
/* 677 */       double[] data = new double[n];
/* 678 */       for (int i = 0; i < n; i++)
/* 679 */         data[i] = ois.readDouble(); 
/* 683 */       RealVector vector = new ArrayRealVector(data, false);
/* 686 */       Field f = instance.getClass().getDeclaredField(fieldName);
/* 688 */       f.setAccessible(true);
/* 689 */       f.set(instance, vector);
/* 691 */     } catch (NoSuchFieldException nsfe) {
/* 692 */       IOException ioe = new IOException();
/* 693 */       ioe.initCause(nsfe);
/* 694 */       throw ioe;
/* 695 */     } catch (IllegalAccessException iae) {
/* 696 */       IOException ioe = new IOException();
/* 697 */       ioe.initCause(iae);
/* 698 */       throw ioe;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void serializeRealMatrix(RealMatrix matrix, ObjectOutputStream oos) throws IOException {
/* 746 */     int n = matrix.getRowDimension();
/* 747 */     int m = matrix.getColumnDimension();
/* 748 */     oos.writeInt(n);
/* 749 */     oos.writeInt(m);
/* 750 */     for (int i = 0; i < n; i++) {
/* 751 */       for (int j = 0; j < m; j++)
/* 752 */         oos.writeDouble(matrix.getEntry(i, j)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void deserializeRealMatrix(Object instance, String fieldName, ObjectInputStream ois) throws ClassNotFoundException, IOException {
/*     */     try {
/* 781 */       int n = ois.readInt();
/* 782 */       int m = ois.readInt();
/* 783 */       double[][] data = new double[n][m];
/* 784 */       for (int i = 0; i < n; i++) {
/* 785 */         double[] dataI = data[i];
/* 786 */         for (int j = 0; j < m; j++)
/* 787 */           dataI[j] = ois.readDouble(); 
/*     */       } 
/* 792 */       RealMatrix matrix = new Array2DRowRealMatrix(data, false);
/* 795 */       Field f = instance.getClass().getDeclaredField(fieldName);
/* 797 */       f.setAccessible(true);
/* 798 */       f.set(instance, matrix);
/* 800 */     } catch (NoSuchFieldException nsfe) {
/* 801 */       IOException ioe = new IOException();
/* 802 */       ioe.initCause(nsfe);
/* 803 */       throw ioe;
/* 804 */     } catch (IllegalAccessException iae) {
/* 805 */       IOException ioe = new IOException();
/* 806 */       ioe.initCause(iae);
/* 807 */       throw ioe;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void solveLowerTriangularSystem(RealMatrix rm, RealVector b) {
/* 827 */     if (rm == null || b == null || rm.getRowDimension() != b.getDimension())
/* 828 */       throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, new Object[] { Integer.valueOf((rm == null) ? 0 : rm.getRowDimension()), Integer.valueOf((b == null) ? 0 : b.getDimension()) }); 
/* 832 */     if (rm.getColumnDimension() != rm.getRowDimension())
/* 833 */       throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, new Object[] { Integer.valueOf(rm.getRowDimension()), Integer.valueOf(rm.getRowDimension()), Integer.valueOf(rm.getRowDimension()), Integer.valueOf(rm.getColumnDimension()) }); 
/* 837 */     int rows = rm.getRowDimension();
/* 838 */     for (int i = 0; i < rows; i++) {
/* 839 */       double diag = rm.getEntry(i, i);
/* 840 */       if (FastMath.abs(diag) < 2.2250738585072014E-308D)
/* 841 */         throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]); 
/* 843 */       double bi = b.getEntry(i) / diag;
/* 844 */       b.setEntry(i, bi);
/* 845 */       for (int j = i + 1; j < rows; j++)
/* 846 */         b.setEntry(j, b.getEntry(j) - bi * rm.getEntry(j, i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void solveUpperTriangularSystem(RealMatrix rm, RealVector b) {
/* 867 */     if (rm == null || b == null || rm.getRowDimension() != b.getDimension())
/* 868 */       throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, new Object[] { Integer.valueOf((rm == null) ? 0 : rm.getRowDimension()), Integer.valueOf((b == null) ? 0 : b.getDimension()) }); 
/* 872 */     if (rm.getColumnDimension() != rm.getRowDimension())
/* 873 */       throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, new Object[] { Integer.valueOf(rm.getRowDimension()), Integer.valueOf(rm.getRowDimension()), Integer.valueOf(rm.getRowDimension()), Integer.valueOf(rm.getColumnDimension()) }); 
/* 877 */     int rows = rm.getRowDimension();
/* 878 */     for (int i = rows - 1; i > -1; i--) {
/* 879 */       double diag = rm.getEntry(i, i);
/* 880 */       if (FastMath.abs(diag) < 2.2250738585072014E-308D)
/* 881 */         throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]); 
/* 883 */       double bi = b.getEntry(i) / diag;
/* 884 */       b.setEntry(i, bi);
/* 885 */       for (int j = i - 1; j > -1; j--)
/* 886 */         b.setEntry(j, b.getEntry(j) - bi * rm.getEntry(j, i)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\MatrixUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */