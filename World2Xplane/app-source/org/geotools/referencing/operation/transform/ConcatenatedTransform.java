/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.Serializable;
/*     */ import javax.vecmath.GMatrix;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.matrix.Matrix3;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform1D;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ConcatenatedTransform extends AbstractMathTransform implements Serializable {
/*     */   private static final long serialVersionUID = 5772066656987558634L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-10D;
/*     */   
/*     */   private static final int TEMPORARY_ARRAY_LENGTH = 256;
/*     */   
/*     */   public final MathTransform transform1;
/*     */   
/*     */   public final MathTransform transform2;
/*     */   
/*     */   private ConcatenatedTransform inverse;
/*     */   
/*     */   protected ConcatenatedTransform(MathTransform transform1, MathTransform transform2) {
/* 101 */     this.transform1 = transform1;
/* 102 */     this.transform2 = transform2;
/* 103 */     if (!isValid())
/* 104 */       throw new IllegalArgumentException(Errors.format(20, getName(transform1), getName(transform2))); 
/*     */   }
/*     */   
/*     */   private static XMatrix getMatrix(MathTransform transform) {
/* 114 */     if (transform instanceof LinearTransform)
/* 115 */       return toXMatrix(((LinearTransform)transform).getMatrix()); 
/* 117 */     if (transform instanceof AffineTransform)
/* 118 */       return (XMatrix)new Matrix3((AffineTransform)transform); 
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean areInverse(MathTransform tr1, MathTransform tr2) {
/* 134 */     if (tr2 instanceof AbstractMathTransform.Inverse)
/* 135 */       return tr1.equals(((AbstractMathTransform.Inverse)tr2).inverse()); 
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public static MathTransform create(MathTransform tr1, MathTransform tr2) {
/* 159 */     int dim1 = tr1.getTargetDimensions();
/* 160 */     int dim2 = tr2.getSourceDimensions();
/* 161 */     if (dim1 != dim2)
/* 162 */       throw new IllegalArgumentException(Errors.format(20, getName(tr1), getName(tr2)) + ' ' + Errors.format(93, Integer.valueOf(dim1), Integer.valueOf(dim2))); 
/* 167 */     MathTransform mt = createOptimized(tr1, tr2);
/* 168 */     if (mt != null)
/* 169 */       return mt; 
/* 189 */     int stepCount = getStepCount(tr1) + getStepCount(tr2);
/* 190 */     boolean tryAgain = true;
/* 191 */     for (int k = 0;; k++) {
/* 192 */       MathTransform c1 = tr1;
/* 193 */       MathTransform c2 = tr2;
/* 194 */       boolean first = ((k & 0x1) == 0);
/* 195 */       MathTransform candidate = first ? c1 : c2;
/* 196 */       while (candidate instanceof ConcatenatedTransform) {
/* 197 */         ConcatenatedTransform ctr = (ConcatenatedTransform)candidate;
/* 198 */         if (first) {
/* 199 */           c1 = candidate = ctr.transform1;
/* 200 */           c2 = create(ctr.transform2, c2);
/*     */         } else {
/* 202 */           c1 = create(c1, ctr.transform1);
/* 203 */           c2 = candidate = ctr.transform2;
/*     */         } 
/* 205 */         int c = getStepCount(c1) + getStepCount(c2);
/* 206 */         if (c < stepCount) {
/* 207 */           tr1 = c1;
/* 208 */           tr2 = c2;
/* 209 */           stepCount = c;
/* 210 */           tryAgain = true;
/*     */         } 
/*     */       } 
/* 213 */       if (!tryAgain)
/*     */         break; 
/* 214 */       tryAgain = false;
/*     */     } 
/* 220 */     mt = createOptimized(tr1, tr2);
/* 221 */     if (mt != null)
/* 222 */       return mt; 
/* 228 */     return createConcatenatedTransform(tr1, tr2);
/*     */   }
/*     */   
/*     */   private static MathTransform createOptimized(MathTransform tr1, MathTransform tr2) {
/* 240 */     if (tr1.isIdentity())
/* 240 */       return tr2; 
/* 241 */     if (tr2.isIdentity())
/* 241 */       return tr1; 
/* 246 */     XMatrix matrix1 = getMatrix(tr1);
/* 247 */     if (matrix1 != null) {
/* 248 */       XMatrix matrix2 = getMatrix(tr2);
/* 249 */       if (matrix2 != null) {
/*     */         GeneralMatrix generalMatrix;
/* 252 */         int numRow = matrix2.getNumRow();
/* 253 */         int numCol = matrix1.getNumCol();
/* 255 */         if (numCol == matrix2.getNumCol()) {
/* 256 */           XMatrix matrix = matrix2;
/* 257 */           matrix2.multiply((Matrix)matrix1);
/*     */         } else {
/* 259 */           GeneralMatrix m = new GeneralMatrix(numRow, numCol);
/* 260 */           m.mul((GMatrix)toGMatrix((Matrix)matrix2), (GMatrix)toGMatrix((Matrix)matrix1));
/* 261 */           generalMatrix = m;
/*     */         } 
/* 263 */         if (generalMatrix.isIdentity(1.0E-10D))
/* 264 */           generalMatrix.setIdentity(); 
/* 269 */         return (MathTransform)ProjectiveTransform.create((Matrix)generalMatrix);
/*     */       } 
/*     */     } 
/* 276 */     if (areInverse(tr1, tr2) || areInverse(tr2, tr1)) {
/* 277 */       assert tr1.getSourceDimensions() == tr2.getTargetDimensions();
/* 278 */       assert tr1.getTargetDimensions() == tr2.getSourceDimensions();
/* 279 */       return (MathTransform)IdentityTransform.create(tr1.getSourceDimensions());
/*     */     } 
/* 285 */     if (tr1 instanceof AbstractMathTransform) {
/* 286 */       MathTransform optimized = ((AbstractMathTransform)tr1).concatenate(tr2, false);
/* 287 */       if (optimized != null)
/* 288 */         return optimized; 
/*     */     } 
/* 291 */     if (tr2 instanceof AbstractMathTransform) {
/* 292 */       MathTransform optimized = ((AbstractMathTransform)tr2).concatenate(tr1, true);
/* 293 */       if (optimized != null)
/* 294 */         return optimized; 
/*     */     } 
/* 298 */     return null;
/*     */   }
/*     */   
/*     */   static ConcatenatedTransform createConcatenatedTransform(MathTransform tr1, MathTransform tr2) {
/* 308 */     int dimSource = tr1.getSourceDimensions();
/* 309 */     int dimTarget = tr2.getTargetDimensions();
/* 313 */     if (dimSource == 1 && dimTarget == 1) {
/* 314 */       if (tr1 instanceof MathTransform1D && tr2 instanceof MathTransform1D)
/* 315 */         return new ConcatenatedTransformDirect1D((MathTransform1D)tr1, (MathTransform1D)tr2); 
/* 318 */       return new ConcatenatedTransform1D(tr1, tr2);
/*     */     } 
/* 324 */     if (dimSource == 2 && dimTarget == 2) {
/* 325 */       if (tr1 instanceof MathTransform2D && tr2 instanceof MathTransform2D)
/* 326 */         return new ConcatenatedTransformDirect2D((MathTransform2D)tr1, (MathTransform2D)tr2); 
/* 329 */       return new ConcatenatedTransform2D(tr1, tr2);
/*     */     } 
/* 335 */     if (dimSource == tr1.getTargetDimensions() && tr2.getSourceDimensions() == dimTarget)
/* 336 */       return new ConcatenatedTransformDirect(tr1, tr2); 
/* 338 */     return new ConcatenatedTransform(tr1, tr2);
/*     */   }
/*     */   
/*     */   private static final String getName(MathTransform transform) {
/* 346 */     if (transform instanceof AbstractMathTransform) {
/* 347 */       ParameterValueGroup params = ((AbstractMathTransform)transform).getParameterValues();
/* 348 */       if (params != null) {
/* 349 */         String name = params.getDescriptor().getName().getCode();
/* 350 */         if (name != null && (name = name.trim()).length() != 0)
/* 351 */           return name; 
/*     */       } 
/*     */     } 
/* 355 */     return Classes.getShortClassName(transform);
/*     */   }
/*     */   
/*     */   boolean isValid() {
/* 363 */     return (this.transform1.getTargetDimensions() == this.transform2.getSourceDimensions());
/*     */   }
/*     */   
/*     */   public final int getSourceDimensions() {
/* 370 */     return this.transform1.getSourceDimensions();
/*     */   }
/*     */   
/*     */   public final int getTargetDimensions() {
/* 377 */     return this.transform2.getTargetDimensions();
/*     */   }
/*     */   
/*     */   public final int getStepCount() {
/* 389 */     return getStepCount(this.transform1) + getStepCount(this.transform2);
/*     */   }
/*     */   
/*     */   private static int getStepCount(MathTransform transform) {
/* 398 */     if (transform.isIdentity())
/* 399 */       return 0; 
/* 401 */     if (!(transform instanceof ConcatenatedTransform))
/* 402 */       return 1; 
/* 404 */     return ((ConcatenatedTransform)transform).getStepCount();
/*     */   }
/*     */   
/*     */   public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst) throws TransformException {
/* 414 */     assert isValid();
/* 418 */     return this.transform2.transform(this.transform1.transform(ptSrc, null), ptDst);
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 431 */     assert isValid();
/* 432 */     int intermDim = this.transform1.getTargetDimensions();
/* 433 */     int targetDim = getTargetDimensions();
/* 439 */     if (intermDim <= targetDim) {
/* 440 */       this.transform1.transform(srcPts, srcOff, dstPts, dstOff, numPts);
/* 441 */       this.transform2.transform(dstPts, dstOff, dstPts, dstOff, numPts);
/*     */       return;
/*     */     } 
/* 444 */     if (numPts <= 0)
/*     */       return; 
/* 452 */     int numTmp = numPts;
/* 453 */     int length = numTmp * intermDim;
/* 454 */     if (length > 256) {
/* 455 */       numTmp = Math.max(1, 256 / intermDim);
/* 456 */       length = numTmp * intermDim;
/*     */     } 
/* 458 */     double[] tmp = new double[length];
/* 459 */     int sourceDim = getSourceDimensions();
/*     */     do {
/* 461 */       if (numTmp > numPts)
/* 462 */         numTmp = numPts; 
/* 464 */       this.transform1.transform(srcPts, srcOff, tmp, 0, numTmp);
/* 465 */       this.transform2.transform(tmp, 0, dstPts, dstOff, numTmp);
/* 466 */       srcOff += numTmp * sourceDim;
/* 467 */       dstOff += numTmp * targetDim;
/* 468 */       numPts -= numTmp;
/* 469 */     } while (numPts != 0);
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
/* 484 */     assert isValid();
/* 485 */     if (numPts <= 0)
/*     */       return; 
/* 488 */     int sourceDim = getSourceDimensions();
/* 489 */     int targetDim = getTargetDimensions();
/* 490 */     int intermDim = this.transform1.getTargetDimensions();
/* 491 */     int dimension = Math.max(Math.max(sourceDim, targetDim), intermDim);
/* 492 */     int numTmp = numPts;
/* 493 */     int length = numTmp * dimension;
/* 494 */     if (length > 256) {
/* 495 */       numTmp = Math.max(1, 256 / dimension);
/* 496 */       length = numTmp * dimension;
/*     */     } 
/* 498 */     double[] tmp = new double[length];
/*     */     do {
/* 500 */       if (numTmp > numPts)
/* 501 */         numTmp = numPts; 
/* 503 */       length = numTmp * sourceDim;
/*     */       int i;
/* 504 */       for (i = 0; i < length; i++)
/* 505 */         tmp[i] = srcPts[srcOff++]; 
/* 507 */       this.transform1.transform(tmp, 0, tmp, 0, numTmp);
/* 508 */       this.transform2.transform(tmp, 0, tmp, 0, numTmp);
/* 509 */       length = numTmp * targetDim;
/* 510 */       for (i = 0; i < length; i++)
/* 511 */         dstPts[dstOff++] = (float)tmp[i]; 
/* 513 */       numPts -= numTmp;
/* 514 */     } while (numPts != 0);
/*     */   }
/*     */   
/*     */   public synchronized MathTransform inverse() throws NoninvertibleTransformException {
/* 522 */     assert isValid();
/* 523 */     if (this.inverse == null) {
/* 524 */       this.inverse = createConcatenatedTransform(this.transform2.inverse(), this.transform1.inverse());
/* 525 */       this.inverse.inverse = this;
/*     */     } 
/* 527 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public Matrix derivative(Point2D point) throws TransformException {
/* 542 */     return derivative((DirectPosition)new GeneralDirectPosition(point));
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) throws TransformException {
/*     */     GeneralMatrix generalMatrix;
/* 554 */     Matrix matrix1 = this.transform1.derivative(point);
/* 555 */     Matrix matrix2 = this.transform2.derivative(this.transform1.transform(point, null));
/* 558 */     int numRow = matrix2.getNumRow();
/* 559 */     int numCol = matrix1.getNumCol();
/* 561 */     if (numCol == matrix2.getNumCol()) {
/* 562 */       XMatrix matrix = toXMatrix(matrix2);
/* 563 */       matrix.multiply(matrix1);
/*     */     } else {
/* 565 */       GeneralMatrix m = new GeneralMatrix(numRow, numCol);
/* 566 */       m.mul((GMatrix)toGMatrix(matrix2), (GMatrix)toGMatrix(matrix1));
/* 567 */       generalMatrix = m;
/*     */     } 
/* 569 */     return (Matrix)generalMatrix;
/*     */   }
/*     */   
/*     */   public final boolean isIdentity() {
/* 579 */     return (this.transform1.isIdentity() && this.transform2.isIdentity());
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 587 */     return this.transform1.hashCode() + 37 * this.transform2.hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals(Object object) {
/* 595 */     if (object == this)
/* 597 */       return true; 
/* 599 */     if (super.equals(object)) {
/* 600 */       ConcatenatedTransform that = (ConcatenatedTransform)object;
/* 601 */       return (Utilities.equals(this.transform1, that.transform1) && Utilities.equals(this.transform2, that.transform2));
/*     */     } 
/* 604 */     return false;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 617 */     addWKT(formatter, this.transform1);
/* 618 */     addWKT(formatter, this.transform2);
/* 619 */     return "CONCAT_MT";
/*     */   }
/*     */   
/*     */   private static void addWKT(Formatter formatter, MathTransform transform) {
/* 628 */     if (transform instanceof ConcatenatedTransform) {
/* 629 */       ConcatenatedTransform concat = (ConcatenatedTransform)transform;
/* 630 */       addWKT(formatter, concat.transform1);
/* 631 */       addWKT(formatter, concat.transform2);
/*     */     } else {
/* 633 */       formatter.append(transform);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ConcatenatedTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */