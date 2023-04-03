/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class DimensionFilter {
/*  80 */   public static final Hints.Key INSTANCE = new Hints.Key(DimensionFilter.class);
/*     */   
/*     */   private int[] sourceDimensions;
/*     */   
/*     */   private int[] targetDimensions;
/*     */   
/*     */   private final MathTransformFactory factory;
/*     */   
/*     */   public DimensionFilter() {
/* 108 */     this(ReferencingFactoryFinder.getMathTransformFactory(null));
/*     */   }
/*     */   
/*     */   public DimensionFilter(Hints hints) {
/* 119 */     this(ReferencingFactoryFinder.getMathTransformFactory(hints));
/*     */   }
/*     */   
/*     */   public DimensionFilter(MathTransformFactory factory) {
/* 128 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public static DimensionFilter getInstance(Hints hints) {
/* 144 */     if (hints != null) {
/* 145 */       DimensionFilter candidate = (DimensionFilter)hints.get(INSTANCE);
/* 146 */       if (candidate != null) {
/* 147 */         candidate.clear();
/* 148 */         return candidate;
/*     */       } 
/*     */     } 
/* 151 */     return new DimensionFilter(hints);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 159 */     this.sourceDimensions = null;
/* 160 */     this.targetDimensions = null;
/*     */   }
/*     */   
/*     */   public void addSourceDimension(int dimension) throws IllegalArgumentException {
/* 175 */     this.sourceDimensions = add(this.sourceDimensions, dimension);
/*     */   }
/*     */   
/*     */   public void addSourceDimensions(int[] dimensions) throws IllegalArgumentException {
/* 191 */     this.sourceDimensions = add(this.sourceDimensions, dimensions);
/*     */   }
/*     */   
/*     */   public void addSourceDimensionRange(int lower, int upper) throws IllegalArgumentException {
/* 206 */     this.sourceDimensions = add(this.sourceDimensions, lower, upper);
/*     */   }
/*     */   
/*     */   public int[] getSourceDimensions() throws IllegalStateException {
/* 217 */     if (this.sourceDimensions != null)
/* 218 */       return (int[])this.sourceDimensions.clone(); 
/* 220 */     throw new IllegalStateException();
/*     */   }
/*     */   
/*     */   public void addTargetDimension(int dimension) throws IllegalArgumentException {
/* 235 */     this.targetDimensions = add(this.targetDimensions, dimension);
/*     */   }
/*     */   
/*     */   public void addTargetDimensions(int[] dimensions) throws IllegalArgumentException {
/* 251 */     this.targetDimensions = add(this.targetDimensions, dimensions);
/*     */   }
/*     */   
/*     */   public void addTargetDimensionRange(int lower, int upper) throws IllegalArgumentException {
/* 266 */     this.targetDimensions = add(this.targetDimensions, lower, upper);
/*     */   }
/*     */   
/*     */   public int[] getTargetDimensions() throws IllegalStateException {
/* 285 */     if (this.targetDimensions != null)
/* 286 */       return (int[])this.targetDimensions.clone(); 
/* 288 */     throw new IllegalStateException();
/*     */   }
/*     */   
/*     */   public MathTransform separate(MathTransform transform) throws FactoryException {
/* 312 */     if (this.sourceDimensions == null) {
/* 313 */       this.sourceDimensions = series(0, transform.getSourceDimensions());
/* 314 */       if (this.targetDimensions == null) {
/* 315 */         this.targetDimensions = series(0, transform.getTargetDimensions());
/* 316 */         return transform;
/*     */       } 
/* 318 */       return separateOutput(transform);
/*     */     } 
/* 320 */     int[] target = this.targetDimensions;
/* 321 */     transform = separateInput(transform);
/* 322 */     assert XArray.isStrictlySorted(this.targetDimensions);
/* 323 */     if (target != null) {
/* 324 */       int[] step = this.targetDimensions;
/* 325 */       this.targetDimensions = new int[target.length];
/* 326 */       for (int i = 0; i < target.length; i++) {
/* 327 */         int j = Arrays.binarySearch(step, target[i]);
/* 328 */         if (j < 0)
/* 337 */           throw new FactoryException(Errors.format(81)); 
/* 339 */         this.targetDimensions[i] = j;
/*     */       } 
/* 341 */       transform = separateOutput(transform);
/* 342 */       this.targetDimensions = target;
/*     */     } 
/* 344 */     assert this.sourceDimensions.length == transform.getSourceDimensions() : transform;
/* 345 */     assert this.targetDimensions.length == transform.getTargetDimensions() : transform;
/* 346 */     return transform;
/*     */   }
/*     */   
/*     */   private MathTransform separateInput(MathTransform transform) throws FactoryException {
/* 359 */     int dimSource = transform.getSourceDimensions();
/* 360 */     int dimTarget = transform.getTargetDimensions();
/* 361 */     int dimInput = this.sourceDimensions.length;
/* 362 */     int lower = this.sourceDimensions[0];
/* 363 */     int upper = this.sourceDimensions[dimInput - 1] + 1;
/* 364 */     assert XArray.isStrictlySorted(this.sourceDimensions);
/* 365 */     if (upper > dimSource)
/* 366 */       throw new IllegalArgumentException(Errors.format(58, "sourceDimensions", Integer.valueOf(upper - 1))); 
/* 372 */     if (dimInput == dimSource) {
/* 373 */       assert lower == 0 && upper == dimSource;
/* 374 */       this.targetDimensions = series(0, dimTarget);
/* 375 */       return transform;
/*     */     } 
/* 377 */     if (transform.isIdentity()) {
/* 378 */       this.targetDimensions = this.sourceDimensions;
/* 379 */       return this.factory.createAffineTransform((Matrix)MatrixFactory.create(dimInput + 1));
/*     */     } 
/* 381 */     if (transform instanceof ConcatenatedTransform) {
/* 382 */       ConcatenatedTransform ctr = (ConcatenatedTransform)transform;
/* 383 */       int[] original = this.sourceDimensions;
/* 385 */       MathTransform step1 = separateInput(ctr.transform1);
/* 385 */       this.sourceDimensions = this.targetDimensions;
/* 386 */       MathTransform step2 = separateInput(ctr.transform2);
/* 386 */       this.sourceDimensions = original;
/* 387 */       return this.factory.createConcatenatedTransform(step1, step2);
/*     */     } 
/* 394 */     if (transform instanceof PassThroughTransform) {
/* 395 */       PassThroughTransform passThrough = (PassThroughTransform)transform;
/* 396 */       int dimPass = passThrough.subTransform.getSourceDimensions();
/* 397 */       int dimDiff = passThrough.subTransform.getTargetDimensions() - dimPass;
/* 398 */       int subLower = passThrough.firstAffectedOrdinate;
/* 399 */       int subUpper = subLower + dimPass;
/* 400 */       DimensionFilter subFilter = new DimensionFilter(this.factory);
/* 401 */       for (int i = 0; i < this.sourceDimensions.length; i++) {
/* 402 */         int n = this.sourceDimensions[i];
/* 403 */         if (n >= subLower && n < subUpper) {
/* 405 */           subFilter.addSourceDimension(n - subLower);
/*     */         } else {
/* 409 */           if (n >= subUpper)
/* 410 */             n += dimDiff; 
/* 412 */           this.targetDimensions = add(this.targetDimensions, n);
/*     */         } 
/*     */       } 
/* 415 */       if (subFilter.sourceDimensions == null)
/* 421 */         return this.factory.createAffineTransform((Matrix)MatrixFactory.create(dimInput + 1)); 
/* 430 */       MathTransform subTransform = subFilter.separateInput(passThrough.subTransform);
/* 431 */       for (int j = 0; j < subFilter.targetDimensions.length; j++)
/* 432 */         subFilter.targetDimensions[j] = subFilter.targetDimensions[j] + subLower; 
/* 434 */       this.targetDimensions = add(this.targetDimensions, subFilter.targetDimensions);
/* 442 */       if (containsAll(this.sourceDimensions, lower, subLower) && containsAll(this.sourceDimensions, subUpper, upper)) {
/* 445 */         int firstAffectedOrdinate = Math.max(0, subLower - lower);
/* 446 */         int numTrailingOrdinates = Math.max(0, upper - subUpper);
/* 447 */         return this.factory.createPassThroughTransform(firstAffectedOrdinate, subTransform, numTrailingOrdinates);
/*     */       } 
/* 451 */       this.targetDimensions = null;
/*     */     } 
/* 459 */     if (transform instanceof LinearTransform) {
/* 460 */       int nRows = 0;
/* 461 */       boolean hasLastRow = false;
/* 462 */       Matrix matrix = ((LinearTransform)transform).getMatrix();
/* 464 */       assert dimSource + 1 == matrix.getNumCol() && dimTarget + 1 == matrix.getNumRow() : matrix;
/* 465 */       double[][] rows = new double[dimTarget + 1][];
/*     */       int j;
/* 466 */       label80: for (j = 0; j < rows.length; j++) {
/* 467 */         double[] row = new double[dimInput + 1];
/* 476 */         int nCols = 0, scan = 0;
/* 477 */         for (int i = 0; i < dimSource; i++) {
/* 478 */           double element = matrix.getElement(j, i);
/* 479 */           if (scan < this.sourceDimensions.length && this.sourceDimensions[scan] == i) {
/* 480 */             row[nCols++] = element;
/* 481 */             scan++;
/* 482 */           } else if (element != 0.0D) {
/*     */             continue label80;
/*     */           } 
/*     */         } 
/* 488 */         row[nCols++] = matrix.getElement(j, dimSource);
/* 489 */         assert nCols == row.length : nCols;
/* 490 */         if (j == dimTarget) {
/* 491 */           hasLastRow = true;
/*     */         } else {
/* 493 */           this.targetDimensions = add(this.targetDimensions, j);
/*     */         } 
/* 495 */         rows[nRows++] = row;
/*     */       } 
/* 497 */       rows = (double[][])XArray.resize((Object[])rows, nRows);
/* 498 */       if (hasLastRow)
/* 499 */         return this.factory.createAffineTransform((Matrix)new GeneralMatrix(rows)); 
/*     */     } 
/* 505 */     throw new FactoryException(Errors.format(81));
/*     */   }
/*     */   
/*     */   private MathTransform separateOutput(MathTransform transform) throws FactoryException {
/* 524 */     int dimSource = transform.getSourceDimensions();
/* 525 */     int dimTarget = transform.getTargetDimensions();
/* 526 */     int dimOutput = this.targetDimensions.length;
/* 527 */     int lower = this.targetDimensions[0];
/* 528 */     int upper = this.targetDimensions[dimOutput - 1];
/* 529 */     assert XArray.isStrictlySorted(this.targetDimensions);
/* 530 */     if (upper > dimTarget)
/* 531 */       throw new IllegalArgumentException(Errors.format(58, "targetDimensions", Integer.valueOf(upper))); 
/* 534 */     if (dimOutput == dimTarget) {
/* 535 */       assert lower == 0 && upper == dimTarget;
/* 536 */       return transform;
/*     */     } 
/* 544 */     int dimPass = 0;
/* 545 */     int dimDiff = 0;
/* 546 */     int dimStep = dimTarget;
/* 547 */     if (transform instanceof PassThroughTransform) {
/* 548 */       PassThroughTransform passThrough = (PassThroughTransform)transform;
/* 549 */       int subLower = passThrough.firstAffectedOrdinate;
/* 550 */       int subUpper = subLower + passThrough.subTransform.getTargetDimensions();
/* 551 */       if (!containsAny(this.targetDimensions, subLower, subUpper)) {
/* 552 */         transform = null;
/* 553 */         dimStep = dimSource;
/* 554 */         dimPass = subLower;
/* 555 */         dimDiff = subLower + passThrough.subTransform.getSourceDimensions() - subUpper;
/*     */       } 
/*     */     } 
/* 564 */     XMatrix matrix = MatrixFactory.create(dimOutput + 1, dimStep + 1);
/* 565 */     matrix.setZero();
/* 566 */     for (int j = 0; j < dimOutput; j++) {
/* 567 */       int i = this.targetDimensions[j];
/* 568 */       if (i >= dimPass)
/* 569 */         i += dimDiff; 
/* 571 */       matrix.setElement(j, i, 1.0D);
/*     */     } 
/* 574 */     matrix.setElement(dimOutput, dimStep, 1.0D);
/* 575 */     MathTransform filtered = this.factory.createAffineTransform((Matrix)matrix);
/* 576 */     if (transform != null)
/* 577 */       filtered = this.factory.createConcatenatedTransform(transform, filtered); 
/* 579 */     return filtered;
/*     */   }
/*     */   
/*     */   private static boolean containsAll(int[] sequence, int lower, int upper) {
/* 592 */     if (lower == upper)
/* 593 */       return true; 
/* 595 */     if (sequence != null) {
/* 596 */       assert XArray.isStrictlySorted(sequence);
/* 597 */       int index = Arrays.binarySearch(sequence, lower);
/* 598 */       if (index >= 0) {
/* 599 */         index += --upper - lower;
/* 600 */         if (index >= 0 && index < sequence.length)
/* 601 */           return (sequence[index] == upper); 
/*     */       } 
/*     */     } 
/* 605 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean containsAny(int[] sequence, int lower, int upper) {
/* 617 */     if (upper == lower)
/* 618 */       return true; 
/* 620 */     if (sequence != null) {
/* 621 */       assert XArray.isStrictlySorted(sequence);
/* 622 */       int index = Arrays.binarySearch(sequence, lower);
/* 623 */       if (index >= 0)
/* 624 */         return true; 
/* 626 */       index ^= 0xFFFFFFFF;
/* 627 */       return (index < sequence.length && sequence[index] < upper);
/*     */     } 
/* 629 */     return false;
/*     */   }
/*     */   
/*     */   private static int[] add(int[] sequence, int dimension) throws IllegalArgumentException {
/* 639 */     if (dimension < 0)
/* 640 */       throw new IllegalArgumentException(Errors.format(58, "dimension", Integer.valueOf(dimension))); 
/* 643 */     if (sequence == null)
/* 644 */       return new int[] { dimension }; 
/* 646 */     assert XArray.isStrictlySorted(sequence);
/* 647 */     int i = Arrays.binarySearch(sequence, dimension);
/* 648 */     if (i < 0) {
/* 649 */       i ^= 0xFFFFFFFF;
/* 650 */       sequence = XArray.insert(sequence, i, 1);
/* 651 */       sequence[i] = dimension;
/*     */     } 
/* 653 */     assert Arrays.binarySearch(sequence, dimension) == i;
/* 654 */     return sequence;
/*     */   }
/*     */   
/*     */   private static int[] add(int[] sequence, int[] dimensions) throws IllegalArgumentException {
/* 666 */     if (dimensions.length != 0) {
/* 667 */       ensureValidSeries(dimensions);
/* 668 */       if (sequence == null) {
/* 669 */         sequence = (int[])dimensions.clone();
/*     */       } else {
/* 673 */         for (int i = 0; i < dimensions.length; i++)
/* 674 */           sequence = add(sequence, dimensions[i]); 
/*     */       } 
/*     */     } 
/* 678 */     return sequence;
/*     */   }
/*     */   
/*     */   private static int[] add(int[] sequence, int lower, int upper) throws IllegalArgumentException {
/* 691 */     if (lower < 0 || lower >= upper)
/* 692 */       throw new IllegalArgumentException(Errors.format(58, "lower", Integer.valueOf(lower))); 
/* 695 */     if (sequence == null) {
/* 696 */       sequence = series(lower, upper);
/*     */     } else {
/* 700 */       while (lower < upper)
/* 701 */         sequence = add(sequence, lower++); 
/*     */     } 
/* 704 */     assert containsAll(sequence, lower, upper);
/* 705 */     return sequence;
/*     */   }
/*     */   
/*     */   private static int[] series(int lower, int upper) throws IllegalArgumentException {
/* 712 */     int[] sequence = new int[upper - lower];
/* 713 */     for (int i = 0; i < sequence.length; i++)
/* 714 */       sequence[i] = i + lower; 
/* 716 */     return sequence;
/*     */   }
/*     */   
/*     */   private static void ensureValidSeries(int[] dimensions) throws IllegalArgumentException {
/* 726 */     int last = -1;
/* 727 */     for (int i = 0; i < dimensions.length; i++) {
/* 728 */       int value = dimensions[i];
/* 729 */       if (value <= last)
/* 730 */         throw new IllegalArgumentException(Errors.format(58, "dimensions[" + i + ']', Integer.valueOf(value))); 
/* 733 */       last = value;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\DimensionFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */