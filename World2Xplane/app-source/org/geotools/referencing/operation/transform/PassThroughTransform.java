/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.vecmath.GMatrix;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class PassThroughTransform extends AbstractMathTransform implements Serializable {
/*     */   private static final long serialVersionUID = -1673997634240223449L;
/*     */   
/*     */   protected final int firstAffectedOrdinate;
/*     */   
/*     */   protected final int numTrailingOrdinates;
/*     */   
/*     */   protected final MathTransform subTransform;
/*     */   
/*     */   private PassThroughTransform inverse;
/*     */   
/*     */   protected PassThroughTransform(int firstAffectedOrdinate, MathTransform subTransform, int numTrailingOrdinates) {
/*  96 */     if (firstAffectedOrdinate < 0)
/*  97 */       throw new IllegalArgumentException(Errors.format(58, "firstAffectedOrdinate", Integer.valueOf(firstAffectedOrdinate))); 
/* 100 */     if (numTrailingOrdinates < 0)
/* 101 */       throw new IllegalArgumentException(Errors.format(58, "numTrailingOrdinates", Integer.valueOf(numTrailingOrdinates))); 
/* 104 */     if (subTransform instanceof PassThroughTransform) {
/* 105 */       PassThroughTransform passThrough = (PassThroughTransform)subTransform;
/* 106 */       passThrough.firstAffectedOrdinate += firstAffectedOrdinate;
/* 107 */       passThrough.numTrailingOrdinates += numTrailingOrdinates;
/* 108 */       this.subTransform = passThrough.subTransform;
/*     */     } else {
/* 110 */       this.firstAffectedOrdinate = firstAffectedOrdinate;
/* 111 */       this.numTrailingOrdinates = numTrailingOrdinates;
/* 112 */       this.subTransform = subTransform;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static MathTransform create(int firstAffectedOrdinate, MathTransform subTransform, int numTrailingOrdinates) {
/* 137 */     if (firstAffectedOrdinate < 0)
/* 138 */       throw new IllegalArgumentException(Errors.format(58, "firstAffectedOrdinate", Integer.valueOf(firstAffectedOrdinate))); 
/* 141 */     if (numTrailingOrdinates < 0)
/* 142 */       throw new IllegalArgumentException(Errors.format(58, "numTrailingOrdinates", Integer.valueOf(numTrailingOrdinates))); 
/* 145 */     if (firstAffectedOrdinate == 0 && numTrailingOrdinates == 0)
/* 146 */       return subTransform; 
/* 151 */     if (subTransform.isIdentity()) {
/* 152 */       int dimension = subTransform.getSourceDimensions();
/* 153 */       if (dimension == subTransform.getTargetDimensions())
/* 154 */         return (MathTransform)IdentityTransform.create(firstAffectedOrdinate + dimension + numTrailingOrdinates); 
/*     */     } 
/* 162 */     if (subTransform instanceof LinearTransform) {
/* 163 */       GeneralMatrix matrix = toGMatrix(((LinearTransform)subTransform).getMatrix());
/* 164 */       matrix = expand(matrix, firstAffectedOrdinate, numTrailingOrdinates, 1);
/* 165 */       return (MathTransform)ProjectiveTransform.create((Matrix)matrix);
/*     */     } 
/* 171 */     return new PassThroughTransform(firstAffectedOrdinate, subTransform, numTrailingOrdinates);
/*     */   }
/*     */   
/*     */   public MathTransform getSubTransform() {
/* 182 */     return this.subTransform;
/*     */   }
/*     */   
/*     */   public int[] getModifiedCoordinates() {
/* 193 */     int[] index = new int[this.subTransform.getSourceDimensions()];
/* 194 */     for (int i = 0; i < index.length; i++)
/* 195 */       index[i] = i + this.firstAffectedOrdinate; 
/* 197 */     return index;
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 204 */     return this.firstAffectedOrdinate + this.subTransform.getSourceDimensions() + this.numTrailingOrdinates;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 211 */     return this.firstAffectedOrdinate + this.subTransform.getTargetDimensions() + this.numTrailingOrdinates;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 219 */     return this.subTransform.isIdentity();
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
/* 230 */     int subDimSource = this.subTransform.getSourceDimensions();
/* 231 */     int subDimTarget = this.subTransform.getTargetDimensions();
/* 232 */     int srcStep = this.numTrailingOrdinates;
/* 233 */     int dstStep = this.numTrailingOrdinates;
/* 234 */     if (srcPts == dstPts && srcOff < dstOff) {
/* 235 */       int dimSource = getSourceDimensions();
/* 236 */       int dimTarget = getTargetDimensions();
/* 237 */       srcOff += numPts * dimSource;
/* 238 */       dstOff += numPts * dimTarget;
/* 239 */       srcStep -= 2 * dimSource;
/* 240 */       dstStep -= 2 * dimTarget;
/*     */     } 
/* 242 */     while (--numPts >= 0) {
/* 243 */       System.arraycopy(srcPts, srcOff, dstPts, dstOff, this.firstAffectedOrdinate);
/* 244 */       this.subTransform.transform(srcPts, srcOff += this.firstAffectedOrdinate, dstPts, dstOff += this.firstAffectedOrdinate, 1);
/* 245 */       System.arraycopy(srcPts, srcOff += subDimSource, dstPts, dstOff += subDimTarget, this.numTrailingOrdinates);
/* 246 */       srcOff += srcStep;
/* 247 */       dstOff += dstStep;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 258 */     int subDimSource = this.subTransform.getSourceDimensions();
/* 259 */     int subDimTarget = this.subTransform.getTargetDimensions();
/* 260 */     int srcStep = this.numTrailingOrdinates;
/* 261 */     int dstStep = this.numTrailingOrdinates;
/* 262 */     if (srcPts == dstPts && srcOff < dstOff) {
/* 263 */       int dimSource = getSourceDimensions();
/* 264 */       int dimTarget = getTargetDimensions();
/* 265 */       srcOff += numPts * dimSource;
/* 266 */       dstOff += numPts * dimTarget;
/* 267 */       srcStep -= 2 * dimSource;
/* 268 */       dstStep -= 2 * dimTarget;
/*     */     } 
/* 270 */     while (--numPts >= 0) {
/* 271 */       System.arraycopy(srcPts, srcOff, dstPts, dstOff, this.firstAffectedOrdinate);
/* 272 */       this.subTransform.transform(srcPts, srcOff += this.firstAffectedOrdinate, dstPts, dstOff += this.firstAffectedOrdinate, 1);
/* 273 */       System.arraycopy(srcPts, srcOff += subDimSource, dstPts, dstOff += subDimTarget, this.numTrailingOrdinates);
/* 274 */       srcOff += srcStep;
/* 275 */       dstOff += dstStep;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) throws TransformException {
/* 284 */     int nSkipped = this.firstAffectedOrdinate + this.numTrailingOrdinates;
/* 285 */     int transDim = this.subTransform.getSourceDimensions();
/* 286 */     int pointDim = point.getDimension();
/* 287 */     if (pointDim != transDim + nSkipped)
/* 288 */       throw new MismatchedDimensionException(Errors.format(94, "point", Integer.valueOf(pointDim), Integer.valueOf(transDim + nSkipped))); 
/* 291 */     GeneralDirectPosition subPoint = new GeneralDirectPosition(transDim);
/* 292 */     for (int i = 0; i < transDim; i++)
/* 293 */       subPoint.ordinates[i] = point.getOrdinate(i + this.firstAffectedOrdinate); 
/* 295 */     return (Matrix)expand(toGMatrix(this.subTransform.derivative((DirectPosition)subPoint)), this.firstAffectedOrdinate, this.numTrailingOrdinates, 0);
/*     */   }
/*     */   
/*     */   private static GeneralMatrix expand(GeneralMatrix subMatrix, int firstAffectedOrdinate, int numTrailingOrdinates, int affine) {
/* 315 */     int nSkipped = firstAffectedOrdinate + numTrailingOrdinates;
/* 316 */     int numRow = subMatrix.getNumRow() - affine;
/* 317 */     int numCol = subMatrix.getNumCol() - affine;
/* 318 */     GeneralMatrix matrix = new GeneralMatrix(numRow + nSkipped + affine, numCol + nSkipped + affine);
/* 320 */     matrix.setZero();
/* 327 */     for (int j = 0; j < firstAffectedOrdinate; j++)
/* 328 */       matrix.setElement(j, j, 1.0D); 
/* 335 */     subMatrix.copySubMatrix(0, 0, numRow, numCol, firstAffectedOrdinate, firstAffectedOrdinate, (GMatrix)matrix);
/* 343 */     int offset = numCol - numRow;
/* 344 */     int numRowOut = numRow + nSkipped;
/* 345 */     for (int i = numRowOut - numTrailingOrdinates; i < numRowOut; i++)
/* 346 */       matrix.setElement(i, i + offset, 1.0D); 
/* 348 */     if (affine != 0) {
/* 350 */       subMatrix.copySubMatrix(0, numCol, numRow, affine, firstAffectedOrdinate, numCol + nSkipped, (GMatrix)matrix);
/* 353 */       subMatrix.copySubMatrix(numRow, 0, affine, numCol, numRow + nSkipped, firstAffectedOrdinate, (GMatrix)matrix);
/* 356 */       subMatrix.copySubMatrix(numRow, numCol, affine, affine, numRow + nSkipped, numCol + nSkipped, (GMatrix)matrix);
/*     */     } 
/* 359 */     return matrix;
/*     */   }
/*     */   
/*     */   public synchronized MathTransform inverse() throws NoninvertibleTransformException {
/* 367 */     if (this.inverse == null) {
/* 368 */       this.inverse = new PassThroughTransform(this.firstAffectedOrdinate, this.subTransform.inverse(), this.numTrailingOrdinates);
/* 370 */       this.inverse.inverse = this;
/*     */     } 
/* 372 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 382 */     int code = 1480877863 + this.firstAffectedOrdinate + 37 * this.numTrailingOrdinates;
/* 383 */     if (this.subTransform != null)
/* 384 */       code ^= this.subTransform.hashCode(); 
/* 386 */     return code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 394 */     if (object == this)
/* 395 */       return true; 
/* 397 */     if (super.equals(object)) {
/* 398 */       PassThroughTransform that = (PassThroughTransform)object;
/* 399 */       return (this.firstAffectedOrdinate == that.firstAffectedOrdinate && this.numTrailingOrdinates == that.numTrailingOrdinates && Utilities.equals(this.subTransform, that.subTransform));
/*     */     } 
/* 403 */     return false;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 420 */     formatter.append(this.firstAffectedOrdinate);
/* 421 */     if (this.numTrailingOrdinates != 0) {
/* 422 */       formatter.append(this.numTrailingOrdinates);
/* 423 */       formatter.setInvalidWKT(PassThroughTransform.class);
/*     */     } 
/* 425 */     formatter.append(this.subTransform);
/* 426 */     return "PASSTHROUGH_MT";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\PassThroughTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */