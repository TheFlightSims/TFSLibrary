/*     */ package org.geotools.referencing.operation.matrix;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import javax.vecmath.GMatrix;
/*     */ import org.geotools.io.ContentFormatException;
/*     */ import org.geotools.io.LineFormat;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class GeneralMatrix extends GMatrix implements XMatrix {
/*     */   private static final long serialVersionUID = 8447482612423035360L;
/*     */   
/*     */   public GeneralMatrix(int size) {
/*  73 */     super(size, size);
/*     */   }
/*     */   
/*     */   public GeneralMatrix(int numRow, int numCol) {
/*  84 */     super(numRow, numCol);
/*     */   }
/*     */   
/*     */   public GeneralMatrix(int numRow, int numCol, double[] matrix) {
/* 100 */     super(numRow, numCol, matrix);
/* 101 */     if (numRow * numCol != matrix.length)
/* 102 */       throw new IllegalArgumentException(String.valueOf(matrix.length)); 
/*     */   }
/*     */   
/*     */   public GeneralMatrix(double[][] matrix) throws IllegalArgumentException {
/* 114 */     super(matrix.length, (matrix.length != 0) ? (matrix[0]).length : 0);
/* 115 */     int numRow = getNumRow();
/* 116 */     int numCol = getNumCol();
/* 117 */     for (int j = 0; j < numRow; j++) {
/* 118 */       if ((matrix[j]).length != numCol)
/* 119 */         throw new IllegalArgumentException(Errors.format(90)); 
/* 121 */       setRow(j, matrix[j]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeneralMatrix(Matrix matrix) {
/* 131 */     this(matrix.getNumRow(), matrix.getNumCol());
/* 132 */     int height = getNumRow();
/* 133 */     int width = getNumCol();
/* 134 */     for (int j = 0; j < height; j++) {
/* 135 */       for (int i = 0; i < width; i++)
/* 136 */         setElement(j, i, matrix.getElement(j, i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeneralMatrix(GMatrix matrix) {
/* 147 */     super(matrix);
/*     */   }
/*     */   
/*     */   public GeneralMatrix(AffineTransform transform) {
/* 156 */     super(3, 3, new double[] { transform.getScaleX(), transform.getShearX(), transform.getTranslateX(), transform.getShearY(), transform.getScaleY(), transform.getTranslateY(), 0.0D, 0.0D, 1.0D });
/* 161 */     assert isAffine() : this;
/*     */   }
/*     */   
/*     */   public GeneralMatrix(Envelope srcRegion, Envelope dstRegion) {
/* 185 */     super(dstRegion.getDimension() + 1, srcRegion.getDimension() + 1);
/* 187 */     int srcDim = srcRegion.getDimension();
/* 188 */     int dstDim = dstRegion.getDimension();
/* 189 */     for (int i = Math.min(srcDim, dstDim); --i >= 0; ) {
/* 190 */       double scale = dstRegion.getSpan(i) / srcRegion.getSpan(i);
/* 191 */       double translate = dstRegion.getMinimum(i) - srcRegion.getMinimum(i) * scale;
/* 192 */       setElement(i, i, scale);
/* 193 */       setElement(i, srcDim, translate);
/*     */     } 
/* 195 */     setElement(dstDim, srcDim, 1.0D);
/* 196 */     assert srcDim != dstDim || isAffine() : this;
/*     */   }
/*     */   
/*     */   public GeneralMatrix(AxisDirection[] srcAxis, AxisDirection[] dstAxis) {
/* 223 */     this((Envelope)null, srcAxis, (Envelope)null, dstAxis, false);
/*     */   }
/*     */   
/*     */   public GeneralMatrix(Envelope srcRegion, AxisDirection[] srcAxis, Envelope dstRegion, AxisDirection[] dstAxis) {
/* 255 */     this(srcRegion, srcAxis, dstRegion, dstAxis, true);
/*     */   }
/*     */   
/*     */   private GeneralMatrix(Envelope srcRegion, AxisDirection[] srcAxis, Envelope dstRegion, AxisDirection[] dstAxis, boolean validRegions) {
/* 269 */     super(dstAxis.length + 1, srcAxis.length + 1);
/* 270 */     if (validRegions) {
/* 271 */       ensureDimensionMatch("srcRegion", srcRegion, srcAxis.length);
/* 272 */       ensureDimensionMatch("dstRegion", dstRegion, dstAxis.length);
/*     */     } 
/* 281 */     setZero();
/* 282 */     for (int dstIndex = 0; dstIndex < dstAxis.length; dstIndex++) {
/* 283 */       boolean hasFound = false;
/* 284 */       AxisDirection dstAxe = dstAxis[dstIndex];
/* 285 */       AxisDirection search = dstAxe.absolute();
/* 286 */       for (int srcIndex = 0; srcIndex < srcAxis.length; srcIndex++) {
/* 287 */         AxisDirection srcAxe = srcAxis[srcIndex];
/* 288 */         if (search.equals(srcAxe.absolute())) {
/* 289 */           if (hasFound)
/* 291 */             throw new IllegalArgumentException(Errors.format(36, srcAxe.name(), dstAxe.name())); 
/* 294 */           hasFound = true;
/* 300 */           boolean normal = srcAxe.equals(dstAxe);
/* 301 */           double scale = normal ? 1.0D : -1.0D;
/* 302 */           double translate = 0.0D;
/* 303 */           if (validRegions) {
/* 304 */             translate = normal ? dstRegion.getMinimum(dstIndex) : dstRegion.getMaximum(dstIndex);
/* 306 */             scale *= dstRegion.getSpan(dstIndex) / srcRegion.getSpan(srcIndex);
/* 308 */             translate -= srcRegion.getMinimum(srcIndex) * scale;
/*     */           } 
/* 310 */           setElement(dstIndex, srcIndex, scale);
/* 311 */           setElement(dstIndex, srcAxis.length, translate);
/*     */         } 
/*     */       } 
/* 314 */       if (!hasFound)
/* 316 */         throw new IllegalArgumentException(Errors.format(136, dstAxis[dstIndex].name())); 
/*     */     } 
/* 320 */     setElement(dstAxis.length, srcAxis.length, 1.0D);
/* 321 */     assert srcAxis.length != dstAxis.length || isAffine() : this;
/*     */   }
/*     */   
/*     */   private static void ensureDimensionMatch(String name, Envelope envelope, int dimension) throws MismatchedDimensionException {
/* 338 */     int dim = envelope.getDimension();
/* 339 */     if (dimension != dim)
/* 340 */       throw new MismatchedDimensionException(Errors.format(94, name, Integer.valueOf(dim), Integer.valueOf(dimension))); 
/*     */   }
/*     */   
/*     */   public static double[][] getElements(Matrix matrix) {
/* 356 */     if (matrix instanceof GeneralMatrix)
/* 357 */       return ((GeneralMatrix)matrix).getElements(); 
/* 359 */     int numCol = matrix.getNumCol();
/* 360 */     double[][] rows = new double[matrix.getNumRow()][];
/* 361 */     for (int j = 0; j < rows.length; j++) {
/* 363 */       double[] row = new double[numCol];
/* 364 */       for (int i = 0; i < row.length; i++)
/* 365 */         row[i] = matrix.getElement(j, i); 
/*     */     } 
/* 368 */     return rows;
/*     */   }
/*     */   
/*     */   public final double[][] getElements() {
/* 381 */     int numCol = getNumCol();
/* 382 */     double[][] rows = new double[getNumRow()][];
/* 383 */     for (int j = 0; j < rows.length; j++)
/* 384 */       getRow(j, rows[j] = new double[numCol]); 
/* 386 */     return rows;
/*     */   }
/*     */   
/*     */   public final boolean isAffine() {
/* 393 */     int dimension = getNumRow();
/* 394 */     if (dimension != getNumCol())
/* 395 */       return false; 
/* 397 */     dimension--;
/* 398 */     for (int i = 0; i <= dimension; i++) {
/* 399 */       if (getElement(dimension, i) != ((i == dimension) ? true : false))
/* 400 */         return false; 
/*     */     } 
/* 403 */     return true;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity() {
/* 410 */     int numRow = getNumRow();
/* 411 */     int numCol = getNumCol();
/* 412 */     if (numRow != numCol)
/* 413 */       return false; 
/* 415 */     for (int j = 0; j < numRow; j++) {
/* 416 */       for (int i = 0; i < numCol; i++) {
/* 417 */         if (getElement(j, i) != ((i == j) ? true : false))
/* 418 */           return false; 
/*     */       } 
/*     */     } 
/* 422 */     assert isAffine() : this;
/* 423 */     assert isIdentity(0.0D) : this;
/* 424 */     return true;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity(double tolerance) {
/* 433 */     return isIdentity(this, tolerance);
/*     */   }
/*     */   
/*     */   static boolean isIdentity(Matrix matrix, double tolerance) {
/* 440 */     tolerance = Math.abs(tolerance);
/* 441 */     int numRow = matrix.getNumRow();
/* 442 */     int numCol = matrix.getNumCol();
/* 443 */     if (numRow != numCol)
/* 444 */       return false; 
/* 446 */     for (int j = 0; j < numRow; j++) {
/* 447 */       for (int i = 0; i < numCol; i++) {
/* 448 */         double e = matrix.getElement(j, i);
/* 449 */         if (i == j)
/* 450 */           e--; 
/* 452 */         if (Math.abs(e) > tolerance)
/* 453 */           return false; 
/*     */       } 
/*     */     } 
/* 458 */     return true;
/*     */   }
/*     */   
/*     */   public final void multiply(Matrix matrix) {
/*     */     GMatrix m;
/* 466 */     if (matrix instanceof GMatrix) {
/* 467 */       m = (GMatrix)matrix;
/*     */     } else {
/* 469 */       m = new GeneralMatrix(matrix);
/*     */     } 
/* 471 */     mul(m);
/*     */   }
/*     */   
/*     */   public boolean equals(Matrix matrix, double tolerance) {
/* 478 */     return epsilonEquals(this, matrix, tolerance);
/*     */   }
/*     */   
/*     */   static boolean epsilonEquals(Matrix m1, Matrix m2, double tolerance) {
/* 485 */     int numRow = m1.getNumRow();
/* 486 */     if (numRow != m2.getNumRow())
/* 487 */       return false; 
/* 489 */     int numCol = m1.getNumCol();
/* 490 */     if (numCol != m2.getNumCol())
/* 491 */       return false; 
/* 493 */     for (int j = 0; j < numRow; j++) {
/* 494 */       for (int i = 0; i < numCol; ) {
/* 495 */         double v1 = m1.getElement(j, i);
/* 496 */         double v2 = m2.getElement(j, i);
/* 497 */         if (Math.abs(v1 - v2) <= tolerance || 
/* 498 */           Double.doubleToLongBits(v1) == Double.doubleToLongBits(v2)) {
/*     */           i++;
/*     */           continue;
/*     */         } 
/* 502 */         return false;
/*     */       } 
/*     */     } 
/* 506 */     return true;
/*     */   }
/*     */   
/*     */   public final AffineTransform toAffineTransform2D() throws IllegalStateException {
/*     */     int check;
/* 519 */     if ((check = getNumRow()) != 3 || (check = getNumCol()) != 3)
/* 520 */       throw new IllegalStateException(Errors.format(127, Integer.valueOf(check - 1))); 
/* 523 */     if (isAffine())
/* 524 */       return new AffineTransform(getElement(0, 0), getElement(1, 0), getElement(0, 1), getElement(1, 1), getElement(0, 2), getElement(1, 2)); 
/* 528 */     throw new IllegalStateException(Errors.format(118));
/*     */   }
/*     */   
/*     */   public static GeneralMatrix load(File file) throws IOException {
/* 541 */     BufferedReader in = new BufferedReader(new FileReader(file));
/*     */     try {
/* 543 */       return load(in, Locale.US);
/*     */     } finally {
/* 545 */       in.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static GeneralMatrix load(BufferedReader in, Locale locale) throws IOException {
/* 562 */     LineFormat parser = new LineFormat(locale);
/* 563 */     double[] data = null;
/* 564 */     double[] row = null;
/* 565 */     int numRow = 0;
/* 566 */     int numData = 0;
/*     */     String line;
/* 568 */     while ((line = in.readLine()) != null) {
/* 569 */       if ((line = line.trim()).length() == 0) {
/* 570 */         if (numRow == 0)
/*     */           continue; 
/*     */         break;
/*     */       } 
/*     */       try {
/* 577 */         parser.setLine(line);
/* 578 */         row = parser.getValues(row);
/* 579 */       } catch (ParseException exception) {
/* 580 */         throw new ContentFormatException(exception.getLocalizedMessage(), exception);
/*     */       } 
/* 582 */       int upper = numData + row.length;
/* 583 */       if (data == null)
/* 585 */         data = new double[numData * numData]; 
/* 587 */       if (upper > data.length)
/* 588 */         data = XArray.resize(data, upper * 2); 
/* 590 */       System.arraycopy(row, 0, data, numData, row.length);
/* 591 */       numData = upper;
/* 592 */       numRow++;
/* 593 */       assert numData % numRow == 0 : numData;
/*     */     } 
/* 595 */     data = (data != null) ? XArray.resize(data, numData) : new double[0];
/* 596 */     return new GeneralMatrix(numRow, numData / numRow, data);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 605 */     return toString(this);
/*     */   }
/*     */   
/*     */   static String toString(Matrix matrix) {
/* 613 */     int numRow = matrix.getNumRow();
/* 614 */     int numCol = matrix.getNumCol();
/* 615 */     StringBuffer buffer = new StringBuffer();
/* 616 */     int columnWidth = 12;
/* 617 */     String lineSeparator = System.getProperty("line.separator", "\n");
/* 618 */     FieldPosition dummy = new FieldPosition(0);
/* 619 */     NumberFormat format = NumberFormat.getNumberInstance();
/* 620 */     format.setGroupingUsed(false);
/* 621 */     format.setMinimumFractionDigits(6);
/* 622 */     format.setMaximumFractionDigits(6);
/* 623 */     for (int j = 0; j < numRow; j++) {
/* 624 */       for (int i = 0; i < numCol; i++) {
/* 625 */         int position = buffer.length();
/* 626 */         buffer = format.format(matrix.getElement(j, i), buffer, dummy);
/* 627 */         int spaces = Math.max(12 - buffer.length() - position, 1);
/* 628 */         buffer.insert(position, Utilities.spaces(spaces));
/*     */       } 
/* 630 */       buffer.append(lineSeparator);
/*     */     } 
/* 632 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public GeneralMatrix clone() {
/* 640 */     return (GeneralMatrix)super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\GeneralMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */