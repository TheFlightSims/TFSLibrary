/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import javax.vecmath.SingularMatrixException;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.MatrixParameterDescriptors;
/*     */ import org.geotools.parameter.MatrixParameters;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ 
/*     */ public class ProjectiveTransform extends AbstractMathTransform implements LinearTransform, Serializable {
/*     */   private static final long serialVersionUID = -2104496465933824935L;
/*     */   
/*     */   private final int numRow;
/*     */   
/*     */   private final int numCol;
/*     */   
/*     */   private final double[] elt;
/*     */   
/*     */   private transient ProjectiveTransform inverse;
/*     */   
/*     */   protected ProjectiveTransform(Matrix matrix) {
/* 128 */     this.numRow = matrix.getNumRow();
/* 129 */     this.numCol = matrix.getNumCol();
/* 130 */     this.elt = new double[this.numRow * this.numCol];
/* 131 */     int index = 0;
/* 132 */     for (int j = 0; j < this.numRow; j++) {
/* 133 */       for (int i = 0; i < this.numCol; i++)
/* 134 */         this.elt[index++] = matrix.getElement(j, i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static LinearTransform create(Matrix matrix) {
/* 147 */     int dimension = matrix.getNumRow() - 1;
/* 148 */     if (dimension == matrix.getNumCol() - 1) {
/* 149 */       if (matrix.isIdentity())
/* 150 */         return IdentityTransform.create(dimension); 
/* 152 */       GeneralMatrix m = toGMatrix(matrix);
/* 153 */       if (m.isAffine())
/* 154 */         switch (dimension) {
/*     */           case 1:
/* 155 */             return LinearTransform1D.create(m.getElement(0, 0), m.getElement(0, 1));
/*     */           case 2:
/* 156 */             return create(m.toAffineTransform2D());
/*     */         }  
/*     */     } 
/* 160 */     switch (dimension) {
/*     */       case 2:
/* 161 */         return new ProjectiveTransform2D(matrix);
/*     */     } 
/* 162 */     return new ProjectiveTransform(matrix);
/*     */   }
/*     */   
/*     */   public static LinearTransform create(AffineTransform matrix) {
/* 175 */     if (matrix.isIdentity())
/* 176 */       return IdentityTransform.create(2); 
/* 178 */     return new AffineTransform2D(matrix);
/*     */   }
/*     */   
/*     */   public static LinearTransform createScale(int dimension, double scale) {
/* 191 */     if (scale == 1.0D)
/* 192 */       return IdentityTransform.create(dimension); 
/* 194 */     GeneralMatrix generalMatrix = new GeneralMatrix(dimension + 1);
/* 195 */     for (int i = 0; i < dimension; i++)
/* 196 */       generalMatrix.setElement(i, i, scale); 
/* 198 */     return create((Matrix)generalMatrix);
/*     */   }
/*     */   
/*     */   public static LinearTransform createTranslation(int dimension, double offset) {
/* 211 */     if (offset == 0.0D)
/* 212 */       return IdentityTransform.create(dimension); 
/* 214 */     GeneralMatrix generalMatrix = new GeneralMatrix(dimension + 1);
/* 215 */     for (int i = 0; i < dimension; i++)
/* 216 */       generalMatrix.setElement(i, dimension, offset); 
/* 218 */     return create((Matrix)generalMatrix);
/*     */   }
/*     */   
/*     */   public static Matrix createSelectMatrix(int sourceDim, int[] toKeep) throws IndexOutOfBoundsException {
/* 236 */     int targetDim = toKeep.length;
/* 237 */     XMatrix matrix = MatrixFactory.create(targetDim + 1, sourceDim + 1);
/* 238 */     matrix.setZero();
/* 239 */     for (int j = 0; j < targetDim; j++)
/* 240 */       matrix.setElement(j, toKeep[j], 1.0D); 
/* 242 */     matrix.setElement(targetDim, sourceDim, 1.0D);
/* 243 */     return (Matrix)matrix;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 251 */     return ProviderAffine.PARAMETERS;
/*     */   }
/*     */   
/*     */   static ParameterValueGroup getParameterValues(Matrix matrix) {
/* 264 */     MatrixParameters values = (MatrixParameters)ProviderAffine.PARAMETERS.createValue();
/* 265 */     values.setMatrix(matrix);
/* 266 */     return (ParameterValueGroup)values;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 278 */     return getParameterValues(getMatrix());
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 302 */     int inputDimension = this.numCol - 1;
/* 303 */     int outputDimension = this.numRow - 1;
/* 304 */     double[] buffer = new double[this.numRow];
/* 305 */     if (srcPts == dstPts) {
/* 308 */       int upperSrc = srcOff + numPts * inputDimension;
/* 309 */       if (upperSrc > dstOff && (
/* 310 */         (inputDimension >= outputDimension) ? (dstOff > srcOff) : (dstOff + numPts * outputDimension > upperSrc))) {
/* 314 */         srcPts = new float[numPts * inputDimension];
/* 315 */         System.arraycopy(dstPts, srcOff, srcPts, 0, srcPts.length);
/* 316 */         srcOff = 0;
/*     */       } 
/*     */     } 
/* 320 */     while (--numPts >= 0) {
/* 321 */       int mix = 0;
/* 322 */       for (int j = 0; j < this.numRow; j++) {
/* 323 */         double sum = this.elt[mix + inputDimension];
/* 324 */         for (int k = 0; k < inputDimension; k++)
/* 325 */           sum += srcPts[srcOff + k] * this.elt[mix++]; 
/* 327 */         buffer[j] = sum;
/* 328 */         mix++;
/*     */       } 
/* 330 */       double w = buffer[outputDimension];
/* 331 */       for (int i = 0; i < outputDimension; i++)
/* 333 */         dstPts[dstOff++] = (float)(buffer[i] / w); 
/* 335 */       srcOff += inputDimension;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 359 */     int inputDimension = this.numCol - 1;
/* 360 */     int outputDimension = this.numRow - 1;
/* 361 */     double[] buffer = new double[this.numRow];
/* 362 */     if (srcPts == dstPts) {
/* 365 */       int upperSrc = srcOff + numPts * inputDimension;
/* 366 */       if (upperSrc > dstOff && (
/* 367 */         (inputDimension >= outputDimension) ? (dstOff > srcOff) : (dstOff + numPts * outputDimension > upperSrc))) {
/* 371 */         srcPts = new double[numPts * inputDimension];
/* 372 */         System.arraycopy(dstPts, srcOff, srcPts, 0, srcPts.length);
/* 373 */         srcOff = 0;
/*     */       } 
/*     */     } 
/* 377 */     while (--numPts >= 0) {
/* 378 */       int mix = 0;
/* 379 */       for (int j = 0; j < this.numRow; j++) {
/* 380 */         double sum = this.elt[mix + inputDimension];
/* 381 */         for (int k = 0; k < inputDimension; k++)
/* 382 */           sum += srcPts[srcOff + k] * this.elt[mix++]; 
/* 384 */         buffer[j] = sum;
/* 385 */         mix++;
/*     */       } 
/* 387 */       double w = buffer[outputDimension];
/* 388 */       for (int i = 0; i < outputDimension; i++)
/* 390 */         dstPts[dstOff++] = buffer[i] / w; 
/* 392 */       srcOff += inputDimension;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Matrix derivative(Point2D point) {
/* 403 */     return derivative((DirectPosition)null);
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) {
/* 413 */     GeneralMatrix matrix = getGeneralMatrix();
/* 414 */     matrix.setSize(this.numRow - 1, this.numCol - 1);
/* 415 */     return (Matrix)matrix;
/*     */   }
/*     */   
/*     */   public Matrix getMatrix() {
/* 422 */     return (Matrix)getGeneralMatrix();
/*     */   }
/*     */   
/*     */   private GeneralMatrix getGeneralMatrix() {
/* 429 */     return new GeneralMatrix(this.numRow, this.numCol, this.elt);
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 436 */     return this.numCol - 1;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 443 */     return this.numRow - 1;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 451 */     if (this.numRow != this.numCol)
/* 452 */       return false; 
/* 454 */     int index = 0;
/* 455 */     for (int j = 0; j < this.numRow; j++) {
/* 456 */       for (int i = 0; i < this.numCol; i++) {
/* 457 */         if (this.elt[index++] != ((i == j) ? true : false))
/* 458 */           return false; 
/*     */       } 
/*     */     } 
/* 462 */     assert isIdentity(0.0D);
/* 463 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isIdentity(double tolerance) {
/* 474 */     tolerance = Math.abs(tolerance);
/* 475 */     if (this.numRow != this.numCol)
/* 476 */       return false; 
/* 478 */     int index = 0;
/* 479 */     for (int j = 0; j < this.numRow; j++) {
/* 480 */       for (int i = 0; i < this.numCol; i++) {
/* 481 */         double e = this.elt[index++];
/* 482 */         if (i == j)
/* 483 */           e--; 
/* 486 */         if (Math.abs(e) > tolerance)
/* 487 */           return false; 
/*     */       } 
/*     */     } 
/* 491 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized MathTransform inverse() throws NoninvertibleTransformException {
/* 499 */     if (this.inverse == null)
/* 500 */       if (isIdentity()) {
/* 501 */         this.inverse = this;
/*     */       } else {
/* 503 */         GeneralMatrix generalMatrix = getGeneralMatrix();
/*     */         try {
/* 505 */           generalMatrix.invert();
/* 506 */         } catch (SingularMatrixException exception) {
/* 507 */           throw new NoninvertibleTransformException(Errors.format(105), exception);
/* 509 */         } catch (MismatchedSizeException exception) {
/* 511 */           throw new NoninvertibleTransformException(Errors.format(105), exception);
/*     */         } 
/* 514 */         this.inverse = new ProjectiveTransform((Matrix)generalMatrix);
/* 515 */         this.inverse.inverse = this;
/*     */       }  
/* 518 */     return this.inverse;
/*     */   }
/*     */   
/*     */   MathTransform createInverse(Matrix matrix) {
/* 526 */     return new ProjectiveTransform(matrix);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 536 */     long code = -2104496465933824935L;
/* 537 */     for (int i = this.elt.length; --i >= 0;)
/* 538 */       code = code * 37L + Double.doubleToLongBits(this.elt[i]); 
/* 540 */     return (int)(code >>> 32L) ^ (int)code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 549 */     if (object == this)
/* 551 */       return true; 
/* 553 */     if (super.equals(object)) {
/* 554 */       ProjectiveTransform that = (ProjectiveTransform)object;
/* 555 */       return (this.numRow == that.numRow && this.numCol == that.numCol && Arrays.equals(this.elt, that.elt));
/*     */     } 
/* 559 */     return false;
/*     */   }
/*     */   
/*     */   public static final class ProviderAffine extends MathTransformProvider {
/*     */     private static final long serialVersionUID = 649555815622129472L;
/*     */     
/* 582 */     private static final ProviderAffine[] methods = new ProviderAffine[8];
/*     */     
/*     */     static final ParameterDescriptorGroup PARAMETERS;
/*     */     
/*     */     static {
/* 591 */       NamedIdentifier name = new NamedIdentifier(Citations.OGC, "Affine");
/* 592 */       Map<String, Object> properties = new HashMap<String, Object>(4, 0.8F);
/* 593 */       properties.put("name", name);
/* 594 */       properties.put("identifiers", name);
/* 595 */       properties.put("alias", new NamedIdentifier[] { name, new NamedIdentifier(Citations.EPSG, "Affine general parametric transformation"), new NamedIdentifier(Citations.EPSG, "9624"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(2)) });
/* 601 */       PARAMETERS = (ParameterDescriptorGroup)new MatrixParameterDescriptors(properties);
/*     */     }
/*     */     
/*     */     public ProviderAffine() {
/* 608 */       this(2, 2);
/* 610 */       methods[1] = this;
/*     */     }
/*     */     
/*     */     private ProviderAffine(int sourceDimensions, int targetDimensions) {
/* 617 */       super(sourceDimensions, targetDimensions, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Conversion> getOperationType() {
/* 625 */       return Conversion.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/* 639 */       LinearTransform linearTransform = ProjectiveTransform.create(((MatrixParameterDescriptors)getParameters()).getMatrix(values));
/* 640 */       return (MathTransform)new MathTransformProvider.Delegate((MathTransform)linearTransform, (OperationMethod)getProvider(linearTransform.getSourceDimensions(), linearTransform.getTargetDimensions()));
/*     */     }
/*     */     
/*     */     public static ProviderAffine getProvider(int sourceDimensions, int targetDimensions) {
/* 655 */       if (sourceDimensions == targetDimensions) {
/* 656 */         int i = sourceDimensions - 1;
/* 657 */         if (i >= 0 && i < methods.length) {
/* 658 */           ProviderAffine method = methods[i];
/* 659 */           if (method == null)
/* 660 */             methods[i] = method = new ProviderAffine(sourceDimensions, targetDimensions); 
/* 662 */           return method;
/*     */         } 
/*     */       } 
/* 665 */       return new ProviderAffine(sourceDimensions, targetDimensions);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class ProviderLongitudeRotation extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -2104496465933824935L;
/*     */     
/* 684 */     public static final ParameterDescriptor OFFSET = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Longitude offset") }Double.NaN, -180.0D, 180.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 693 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Longitude rotation"), new NamedIdentifier(Citations.EPSG, "9601") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { OFFSET });
/*     */     
/*     */     public ProviderLongitudeRotation() {
/* 704 */       super(2, 2, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Conversion> getOperationType() {
/* 712 */       return Conversion.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/* 725 */       double offset = doubleValue(OFFSET, values);
/* 726 */       return (MathTransform)ProjectiveTransform.create(AffineTransform.getTranslateInstance(offset, 0.0D));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ProjectiveTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */