/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.IllegalPathStateException;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.Serializable;
/*     */ import javax.vecmath.SingularMatrixException;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.matrix.Matrix1;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.wkt.Formattable;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.geometry.ShapeUtilities;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform1D;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public abstract class AbstractMathTransform extends Formattable implements MathTransform {
/*     */   public String getName() {
/*  98 */     ParameterDescriptorGroup descriptor = getParameterDescriptors();
/*  99 */     if (descriptor != null) {
/* 100 */       ReferenceIdentifier referenceIdentifier = descriptor.getName();
/* 101 */       if (referenceIdentifier != null) {
/* 102 */         String code = referenceIdentifier.getCode();
/* 103 */         if (code != null)
/* 104 */           return code; 
/*     */       } 
/*     */     } 
/* 108 */     return Classes.getShortClassName(this);
/*     */   }
/*     */   
/*     */   public abstract int getSourceDimensions();
/*     */   
/*     */   public abstract int getTargetDimensions();
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 147 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   private static String constructMessage(String argument, int dimension, int expected) {
/* 169 */     return Errors.format(94, argument, Integer.valueOf(dimension), Integer.valueOf(expected));
/*     */   }
/*     */   
/*     */   public Point2D transform(Point2D ptSrc, Point2D ptDst) throws TransformException {
/*     */     int dim;
/* 190 */     if ((dim = getSourceDimensions()) != 2)
/* 191 */       throw new MismatchedDimensionException(constructMessage("ptSrc", 2, dim)); 
/* 193 */     if ((dim = getTargetDimensions()) != 2)
/* 194 */       throw new MismatchedDimensionException(constructMessage("ptDst", 2, dim)); 
/* 196 */     double[] ord = { ptSrc.getX(), ptSrc.getY() };
/* 197 */     transform(ord, 0, ord, 0, 1);
/* 198 */     if (ptDst != null) {
/* 199 */       ptDst.setLocation(ord[0], ord[1]);
/* 200 */       return ptDst;
/*     */     } 
/* 202 */     return new Point2D.Double(ord[0], ord[1]);
/*     */   }
/*     */   
/*     */   public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst) throws TransformException {
/*     */     GeneralDirectPosition generalDirectPosition;
/* 213 */     int dimPoint = ptSrc.getDimension();
/* 214 */     int dimSource = getSourceDimensions();
/* 215 */     int dimTarget = getTargetDimensions();
/* 216 */     if (dimPoint != dimSource)
/* 217 */       throw new MismatchedDimensionException(constructMessage("ptSrc", dimPoint, dimSource)); 
/* 219 */     if (ptDst != null) {
/*     */       double[] array;
/* 220 */       dimPoint = ptDst.getDimension();
/* 221 */       if (dimPoint != dimTarget)
/* 222 */         throw new MismatchedDimensionException(constructMessage("ptDst", dimPoint, dimTarget)); 
/* 229 */       if (dimSource >= dimTarget) {
/* 230 */         array = ptSrc.getCoordinate();
/*     */       } else {
/* 232 */         array = new double[dimTarget];
/* 233 */         for (int j = dimSource; --j >= 0;)
/* 234 */           array[j] = ptSrc.getOrdinate(j); 
/*     */       } 
/* 237 */       transform(array, 0, array, 0, 1);
/* 238 */       for (int i = dimTarget; --i >= 0;)
/* 239 */         ptDst.setOrdinate(i, array[i]); 
/*     */     } else {
/*     */       double[] source;
/* 248 */       GeneralDirectPosition destination = new GeneralDirectPosition(dimTarget);
/* 250 */       if (dimSource <= dimTarget) {
/* 251 */         source = destination.ordinates;
/* 252 */         for (int i = dimSource; --i >= 0;)
/* 253 */           source[i] = ptSrc.getOrdinate(i); 
/*     */       } else {
/* 256 */         source = ptSrc.getCoordinate();
/*     */       } 
/* 258 */       transform(source, 0, destination.ordinates, 0, 1);
/*     */     } 
/* 260 */     return (DirectPosition)generalDirectPosition;
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
/* 272 */     int dimSource = getSourceDimensions();
/* 273 */     int dimTarget = getTargetDimensions();
/* 274 */     double[] tmpPts = new double[numPts * Math.max(dimSource, dimTarget)];
/*     */     int i;
/* 275 */     for (i = numPts * dimSource; --i >= 0;)
/* 276 */       tmpPts[i] = srcPts[srcOff + i]; 
/* 278 */     transform(tmpPts, 0, tmpPts, 0, numPts);
/* 279 */     for (i = numPts * dimTarget; --i >= 0;)
/* 280 */       dstPts[dstOff + i] = (float)tmpPts[i]; 
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
/* 295 */     int dimSource = getSourceDimensions();
/* 296 */     int dimTarget = getTargetDimensions();
/* 297 */     double[] tmpPts = new double[numPts * Math.max(dimSource, dimTarget)];
/* 298 */     System.arraycopy(srcPts, srcOff, tmpPts, 0, numPts * dimSource);
/* 299 */     transform(tmpPts, 0, tmpPts, 0, numPts);
/* 300 */     for (int i = numPts * dimTarget; --i >= 0;)
/* 301 */       dstPts[dstOff + i] = (float)tmpPts[i]; 
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 315 */     int dimSource = getSourceDimensions();
/* 316 */     int dimTarget = getTargetDimensions();
/* 317 */     if (dimSource == dimTarget) {
/* 318 */       int n = numPts * dimSource;
/* 319 */       for (int i = 0; i < n; i++)
/* 320 */         dstPts[dstOff + i] = srcPts[srcOff + i]; 
/* 322 */       transform(dstPts, dstOff, dstPts, dstOff, numPts);
/*     */     } else {
/* 324 */       double[] tmpPts = new double[numPts * dimSource];
/* 325 */       for (int i = tmpPts.length; --i >= 0;)
/* 326 */         tmpPts[i] = srcPts[srcOff + i]; 
/* 328 */       transform(tmpPts, 0, dstPts, 0, numPts);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Shape createTransformedShape(Shape shape) throws TransformException {
/* 344 */     return isIdentity() ? shape : createTransformedShape(shape, null, null, 0);
/*     */   }
/*     */   
/*     */   final Shape createTransformedShape(Shape shape, AffineTransform preTransform, AffineTransform postTransform, int orientation) throws TransformException {
/*     */     int dim;
/* 373 */     if ((dim = getSourceDimensions()) != 2 || (dim = getTargetDimensions()) != 2)
/* 374 */       throw new MismatchedDimensionException(constructMessage("shape", 2, dim)); 
/* 376 */     PathIterator it = shape.getPathIterator(preTransform);
/* 377 */     GeneralPath path = new GeneralPath(it.getWindingRule());
/* 378 */     Point2D.Float ctrl = new Point2D.Float();
/* 379 */     double[] buffer = new double[6];
/* 381 */     double ax = 0.0D, ay = 0.0D;
/* 382 */     double px = 0.0D, py = 0.0D;
/* 383 */     for (; !it.isDone(); it.next()) {
/*     */       Point2D ctrlPoint;
/* 384 */       switch (it.currentSegment(buffer)) {
/*     */         default:
/* 386 */           throw new IllegalPathStateException();
/*     */         case 4:
/* 395 */           path.closePath();
/*     */           break;
/*     */         case 0:
/* 405 */           ax = buffer[0];
/* 406 */           ay = buffer[1];
/* 407 */           transform(buffer, 0, buffer, 0, 1);
/* 408 */           px = buffer[0];
/* 409 */           py = buffer[1];
/* 410 */           path.moveTo((float)px, (float)py);
/*     */           break;
/*     */         case 1:
/* 424 */           buffer[0] = 0.5D * (ax + (ax = buffer[0]));
/* 425 */           buffer[1] = 0.5D * (ay + (ay = buffer[1]));
/* 426 */           buffer[2] = ax;
/* 427 */           buffer[3] = ay;
/*     */         case 2:
/* 442 */           buffer[0] = 0.5D * (buffer[0] + 0.5D * (ax + (ax = buffer[2])));
/* 443 */           buffer[1] = 0.5D * (buffer[1] + 0.5D * (ay + (ay = buffer[3])));
/*     */         case 3:
/* 469 */           buffer[0] = 0.25D * (1.5D * (buffer[0] + buffer[2]) + 0.5D * (ax + (ax = buffer[4])));
/* 470 */           buffer[1] = 0.25D * (1.5D * (buffer[1] + buffer[3]) + 0.5D * (ay + (ay = buffer[5])));
/* 471 */           buffer[2] = ax;
/* 472 */           buffer[3] = ay;
/* 481 */           transform(buffer, 0, buffer, 0, 2);
/* 482 */           ctrlPoint = ShapeUtilities.parabolicControlPoint(px, py, buffer[0], buffer[1], buffer[2], buffer[3], orientation, ctrl);
/* 486 */           px = buffer[2];
/* 487 */           py = buffer[3];
/* 488 */           if (ctrlPoint != null) {
/* 489 */             assert ctrl == ctrlPoint;
/* 490 */             path.quadTo(ctrl.x, ctrl.y, (float)px, (float)py);
/*     */             break;
/*     */           } 
/* 492 */           path.lineTo((float)px, (float)py);
/*     */           break;
/*     */       } 
/*     */     } 
/* 500 */     if (postTransform != null)
/* 501 */       path.transform(postTransform); 
/* 503 */     return ShapeUtilities.toPrimitive(path);
/*     */   }
/*     */   
/*     */   public Matrix derivative(Point2D point) throws TransformException {
/* 520 */     int dimSource = getSourceDimensions();
/* 521 */     if (dimSource != 2)
/* 522 */       throw new MismatchedDimensionException(constructMessage("point", 2, dimSource)); 
/* 524 */     throw new TransformException(Errors.format(19));
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) throws TransformException {
/* 552 */     int dimSource = getSourceDimensions();
/* 553 */     if (point == null) {
/* 554 */       if (dimSource == 2)
/* 555 */         return derivative((Point2D)null); 
/*     */     } else {
/* 558 */       int dimPoint = point.getDimension();
/* 559 */       if (dimPoint != dimSource)
/* 560 */         throw new MismatchedDimensionException(constructMessage("point", dimPoint, dimSource)); 
/* 562 */       if (dimSource == 2) {
/* 563 */         if (point instanceof Point2D)
/* 564 */           return derivative((Point2D)point); 
/* 566 */         return derivative(new Point2D.Double(point.getOrdinate(0), point.getOrdinate(1)));
/*     */       } 
/* 568 */       if (this instanceof MathTransform1D)
/* 569 */         return (Matrix)new Matrix1(((MathTransform1D)this).derivative(point.getOrdinate(0))); 
/*     */     } 
/* 572 */     throw new TransformException(Errors.format(19));
/*     */   }
/*     */   
/*     */   public MathTransform inverse() throws NoninvertibleTransformException {
/* 582 */     if (isIdentity())
/* 583 */       return this; 
/* 585 */     throw new NoninvertibleTransformException(Errors.format(105));
/*     */   }
/*     */   
/*     */   MathTransform concatenate(MathTransform other, boolean applyOtherFirst) {
/* 621 */     return null;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 629 */     return getSourceDimensions() + 37 * getTargetDimensions();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 648 */     if (object != null && getClass().equals(object.getClass())) {
/* 649 */       AbstractMathTransform that = (AbstractMathTransform)object;
/* 650 */       return Utilities.equals(getParameterDescriptors(), that.getParameterDescriptors());
/*     */     } 
/* 653 */     return false;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 668 */     ParameterValueGroup parameters = getParameterValues();
/* 669 */     if (parameters != null) {
/* 670 */       formatter.append(formatter.getName((IdentifiedObject)parameters.getDescriptor()));
/* 671 */       formatter.append((GeneralParameterValue)parameters);
/*     */     } 
/* 673 */     return "PARAM_MT";
/*     */   }
/*     */   
/*     */   protected static void ensureNonNull(String name, Object object) throws InvalidParameterValueException {
/* 687 */     if (object == null)
/* 688 */       throw new InvalidParameterValueException(Errors.format(143, name), name, object); 
/*     */   }
/*     */   
/*     */   protected static boolean needCopy(int srcOff, int dimSource, int dstOff, int dimTarget, int numPts) {
/* 736 */     if (numPts <= 1 || (srcOff >= dstOff && dimSource >= dimTarget))
/* 742 */       return false; 
/* 744 */     return (srcOff < dstOff + numPts * dimTarget && dstOff < srcOff + numPts * dimSource);
/*     */   }
/*     */   
/*     */   protected static double rollLongitude(double x) {
/* 757 */     return x - 6.283185307179586D * Math.floor(x / 6.283185307179586D + 0.5D);
/*     */   }
/*     */   
/*     */   static XMatrix toXMatrix(Matrix matrix) {
/* 766 */     if (matrix instanceof XMatrix)
/* 767 */       return (XMatrix)matrix; 
/* 769 */     return MatrixFactory.create(matrix);
/*     */   }
/*     */   
/*     */   static GeneralMatrix toGMatrix(Matrix matrix) {
/* 781 */     if (matrix instanceof GeneralMatrix)
/* 782 */       return (GeneralMatrix)matrix; 
/* 784 */     return new GeneralMatrix(matrix);
/*     */   }
/*     */   
/*     */   static Matrix invert(Matrix matrix) throws NoninvertibleTransformException {
/*     */     try {
/* 795 */       XMatrix m = toXMatrix(matrix);
/* 796 */       m.invert();
/* 797 */       return (Matrix)m;
/* 798 */     } catch (SingularMatrixException exception) {
/* 799 */       NoninvertibleTransformException e = new NoninvertibleTransformException(Errors.format(105));
/* 801 */       e.initCause((Throwable)exception);
/* 802 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract class Inverse extends AbstractMathTransform implements Serializable {
/*     */     private static final long serialVersionUID = 3528274816628012283L;
/*     */     
/*     */     public String getName() {
/* 844 */       return AbstractMathTransform.this.getName() + " (" + Vocabulary.format(114) + ')';
/*     */     }
/*     */     
/*     */     public int getSourceDimensions() {
/* 854 */       return AbstractMathTransform.this.getTargetDimensions();
/*     */     }
/*     */     
/*     */     public int getTargetDimensions() {
/* 863 */       return AbstractMathTransform.this.getSourceDimensions();
/*     */     }
/*     */     
/*     */     public Matrix derivative(Point2D point) throws TransformException {
/* 873 */       return invert(AbstractMathTransform.this.derivative(transform(point, (Point2D)null)));
/*     */     }
/*     */     
/*     */     public Matrix derivative(DirectPosition point) throws TransformException {
/* 883 */       return invert(AbstractMathTransform.this.derivative(transform(point, (DirectPosition)null)));
/*     */     }
/*     */     
/*     */     public MathTransform inverse() {
/* 893 */       return AbstractMathTransform.this;
/*     */     }
/*     */     
/*     */     public boolean isIdentity() {
/* 903 */       return AbstractMathTransform.this.isIdentity();
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 911 */       return AbstractMathTransform.this.hashCode() ^ 0xFFFFFFFF;
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 921 */       if (object == this)
/* 923 */         return true; 
/* 925 */       if (object instanceof Inverse) {
/* 926 */         Inverse that = (Inverse)object;
/* 927 */         return Utilities.equals(inverse(), that.inverse());
/*     */       } 
/* 929 */       return false;
/*     */     }
/*     */     
/*     */     protected String formatWKT(Formatter formatter) {
/* 947 */       ParameterValueGroup parameters = getParameterValues();
/* 948 */       if (parameters != null) {
/* 949 */         formatter.append(formatter.getName((IdentifiedObject)parameters.getDescriptor()));
/* 950 */         formatter.append((GeneralParameterValue)parameters);
/* 951 */         return "PARAM_MT";
/*     */       } 
/* 953 */       formatter.append(AbstractMathTransform.this);
/* 954 */       return "INVERSE_MT";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\AbstractMathTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */