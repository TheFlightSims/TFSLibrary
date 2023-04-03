/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.referencing.operation.matrix.Matrix2;
/*     */ import org.geotools.referencing.operation.transform.AbstractMathTransform;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ 
/*     */ final class LocalizationGridTransform2D extends AbstractMathTransform implements MathTransform2D, Serializable {
/*     */   private static final long serialVersionUID = 1067560328828441295L;
/*     */   
/*     */   private static final int MAX_ITER = 40;
/*     */   
/*     */   private static final boolean CONSERVATIVE = true;
/*     */   
/*     */   private static final boolean MASK_NON_CONVERGENCE;
/*     */   
/*     */   static final int X_OFFSET = 0;
/*     */   
/*     */   static final int Y_OFFSET = 1;
/*     */   
/*     */   static final int CP_LENGTH = 2;
/*     */   
/*     */   private final int width;
/*     */   
/*     */   private final int height;
/*     */   
/*     */   private final double[] grid;
/*     */   
/*     */   private final AffineTransform global;
/*     */   
/*     */   private transient MathTransform2D inverse;
/*     */   
/*     */   static {
/*     */     String str;
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/* 105 */       str = System.getProperty("org.geotools.referencing.forceConvergence", "false");
/* 106 */     } catch (SecurityException exception) {
/* 108 */       str = "false";
/*     */     } 
/* 110 */     MASK_NON_CONVERGENCE = str.equalsIgnoreCase("true");
/*     */   }
/*     */   
/*     */   protected LocalizationGridTransform2D(int width, int height, double[] grid, AffineTransform global) {
/* 171 */     this.width = width;
/* 172 */     this.height = height;
/* 173 */     this.grid = grid;
/* 174 */     this.global = global;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 182 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 189 */     return 2;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 196 */     return 2;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 204 */     return false;
/*     */   }
/*     */   
/*     */   public Matrix derivative(Point2D point) {
/* 212 */     AffineTransform tr = new AffineTransform();
/* 213 */     getAffineTransform(point.getX(), point.getY(), tr);
/* 214 */     return (Matrix)new Matrix2(tr.getScaleX(), tr.getShearX(), tr.getShearY(), tr.getScaleY());
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 234 */     transform(srcPts, null, srcOff, dstPts, null, dstOff, numPts);
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 252 */     transform(null, srcPts, srcOff, null, dstPts, dstOff, numPts);
/*     */   }
/*     */   
/*     */   private void transform(float[] srcPts1, double[] srcPts2, int srcOff, float[] dstPts1, double[] dstPts2, int dstOff, int numPts) {
/* 261 */     int minCol = 0;
/* 262 */     int minRow = 0;
/* 263 */     int maxCol = this.width - 2;
/* 264 */     int maxRow = this.height - 2;
/* 265 */     int postIncrement = 0;
/* 266 */     if (srcOff < dstOff && (
/* 267 */       (srcPts2 != null) ? (srcPts2 == dstPts2) : (srcPts1 == dstPts1))) {
/* 268 */       srcOff += (numPts - 1) * 2;
/* 269 */       dstOff += (numPts - 1) * 2;
/* 270 */       postIncrement = -4;
/*     */     } 
/* 273 */     while (--numPts >= 0) {
/*     */       double xi, yi;
/* 275 */       if (srcPts2 != null) {
/* 276 */         xi = srcPts2[srcOff++];
/* 277 */         yi = srcPts2[srcOff++];
/*     */       } else {
/* 279 */         xi = srcPts1[srcOff++];
/* 280 */         yi = srcPts1[srcOff++];
/*     */       } 
/* 282 */       int col = Math.max(Math.min((int)xi, maxCol), 0);
/* 283 */       int row = Math.max(Math.min((int)yi, maxRow), 0);
/* 284 */       int offset00 = (col + row * this.width) * 2;
/* 285 */       int offset01 = offset00 + 2 * this.width;
/* 286 */       int offset10 = offset00 + 2;
/* 287 */       int offset11 = offset01 + 2;
/* 296 */       double x0 = linearInterpolation((col + 0), this.grid[offset00 + 0], (col + 1), this.grid[offset10 + 0], xi);
/* 298 */       double y0 = linearInterpolation((col + 0), this.grid[offset00 + 1], (col + 1), this.grid[offset10 + 1], xi);
/* 300 */       double x1 = linearInterpolation((col + 0), this.grid[offset01 + 0], (col + 1), this.grid[offset11 + 0], xi);
/* 302 */       double y1 = linearInterpolation((col + 0), this.grid[offset01 + 1], (col + 1), this.grid[offset11 + 1], xi);
/* 307 */       double xf = linearInterpolation(row, x0, (row + 1), x1, yi);
/* 308 */       double yf = linearInterpolation(row, y0, (row + 1), y1, yi);
/* 309 */       if (dstPts2 != null) {
/* 310 */         dstPts2[dstOff++] = xf;
/* 311 */         dstPts2[dstOff++] = yf;
/*     */       } else {
/* 313 */         dstPts1[dstOff++] = (float)xf;
/* 314 */         dstPts1[dstOff++] = (float)yf;
/*     */       } 
/* 316 */       srcOff += postIncrement;
/* 317 */       dstOff += postIncrement;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static double linearInterpolation(double x1, double y1, double x2, double y2, double x) {
/* 341 */     return y1 + (y2 - y1) / (x2 - x1) * (x - x1);
/*     */   }
/*     */   
/*     */   private void getAffineTransform(double x, double y, AffineTransform dest) {
/* 352 */     int sgnCol, sgnRow, col = (int)x;
/* 353 */     int row = (int)y;
/* 354 */     if (col > this.width - 2)
/* 354 */       col = this.width - 2; 
/* 355 */     if (row > this.height - 2)
/* 355 */       row = this.height - 2; 
/* 356 */     if (col < 0)
/* 356 */       col = 0; 
/* 357 */     if (row < 0)
/* 357 */       row = 0; 
/* 360 */     if (x - col > 0.5D) {
/* 361 */       sgnCol = -1;
/* 362 */       col++;
/*     */     } else {
/* 363 */       sgnCol = 1;
/*     */     } 
/* 364 */     if (y - row > 0.5D) {
/* 365 */       sgnRow = -1;
/* 366 */       row++;
/*     */     } else {
/* 367 */       sgnRow = 1;
/*     */     } 
/* 374 */     int offset00 = (col + row * this.width) * 2;
/* 375 */     int offset01 = offset00 + sgnRow * 2 * this.width;
/* 376 */     int offset10 = offset00 + sgnCol * 2;
/* 377 */     x = this.grid[offset00 + 0];
/* 378 */     y = this.grid[offset00 + 1];
/* 379 */     double dxCol = (this.grid[offset10 + 0] - x) * sgnCol;
/* 380 */     double dyCol = (this.grid[offset10 + 1] - y) * sgnCol;
/* 381 */     double dxRow = (this.grid[offset01 + 0] - x) * sgnRow;
/* 382 */     double dyRow = (this.grid[offset01 + 1] - y) * sgnRow;
/* 383 */     dest.setTransform(dxCol, dyCol, dxRow, dyRow, x - dxCol * col - dxRow * row, y - dyCol * col - dyRow * row);
/* 392 */     assert distance(new Point(col, row), dest) < 1.0E-5D;
/* 393 */     assert distance(new Point(col + sgnCol, row), dest) < 1.0E-5D;
/* 394 */     assert distance(new Point(col, row + sgnRow), dest) < 1.0E-5D;
/*     */   }
/*     */   
/*     */   private double distance(Point2D index, AffineTransform tr) {
/*     */     try {
/* 408 */       Point2D geoCoord = transform(index, null);
/* 409 */       geoCoord = tr.inverseTransform(geoCoord, geoCoord);
/* 410 */       return geoCoord.distance(index);
/* 411 */     } catch (TransformException exception) {
/* 413 */       throw new AssertionError(exception);
/* 414 */     } catch (NoninvertibleTransformException exception) {
/* 416 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   final void inverseTransform(Point2D source, Point2D.Double target, AffineTransform tr) throws TransformException {
/* 453 */     tr.setTransform(this.global);
/*     */     try {
/* 456 */       tr.inverseTransform(source, target);
/* 457 */       int previousX = (int)target.x;
/* 458 */       int previousY = (int)target.y;
/* 459 */       for (int iter = 0; iter < 40; iter++) {
/* 460 */         getAffineTransform(target.x, target.y, tr);
/* 461 */         tr.inverseTransform(source, target);
/* 462 */         int ix = (int)target.x;
/* 463 */         int iy = (int)target.y;
/* 464 */         if (previousX == ix && previousY == iy) {
/* 466 */           if (target.x >= 0.0D && target.x < this.width && target.y >= 0.0D && target.y < this.height) {
/* 470 */             assert transform(target, null).distanceSq(source) < 0.001D : target;
/*     */           } else {
/* 473 */             inverseTransform(source, target);
/*     */           } 
/*     */           return;
/*     */         } 
/* 477 */         previousX = ix;
/* 478 */         previousY = iy;
/*     */       } 
/* 487 */       int x0 = previousX;
/* 488 */       int y0 = previousY;
/* 489 */       this.global.inverseTransform(source, target);
/* 491 */       double x = target.x, bestX = x;
/* 492 */       double y = target.y, bestY = y;
/* 493 */       double minSq = Double.POSITIVE_INFINITY;
/* 494 */       for (int i = -39; i < 40; i++) {
/* 495 */         previousX = (int)x;
/* 496 */         previousY = (int)y;
/* 497 */         getAffineTransform(x, y, tr);
/* 498 */         tr.inverseTransform(source, target);
/* 499 */         x = target.x;
/* 500 */         y = target.y;
/* 501 */         int ix = (int)x;
/* 502 */         int iy = (int)y;
/* 503 */         if (previousX == ix && previousY == iy) {
/* 505 */           assert i >= 0;
/* 506 */           if (x >= 0.0D && x < this.width && y >= 0.0D && y < this.height) {
/* 508 */             assert transform(target, null).distanceSq(source) < 0.001D : target;
/*     */           } else {
/* 511 */             inverseTransform(source, target);
/*     */           } 
/*     */           return;
/*     */         } 
/* 515 */         if (i == 0) {
/* 516 */           assert x0 == ix && y0 == iy;
/* 517 */         } else if (x0 == ix && y0 == iy) {
/* 519 */           if (bestX >= 0.0D && bestX < this.width && bestY >= 0.0D && bestY < this.height) {
/* 520 */             target.x = bestX;
/* 521 */             target.y = bestY;
/*     */           } else {
/* 523 */             inverseTransform(source, target);
/*     */           } 
/*     */           return;
/*     */         } 
/* 527 */         transform(target, target);
/* 528 */         double distanceSq = target.distanceSq(source);
/* 529 */         if (distanceSq < minSq) {
/* 530 */           minSq = distanceSq;
/* 531 */           bestX = x;
/* 532 */           bestY = y;
/*     */         } 
/*     */       } 
/* 541 */       if (MASK_NON_CONVERGENCE) {
/* 542 */         Logging.getLogger(LocalizationGridTransform2D.class).fine("No convergence");
/* 543 */         if (bestX >= 0.0D && bestX < this.width && bestY >= 0.0D && bestY < this.height) {
/* 544 */           target.x = bestX;
/* 545 */           target.y = bestY;
/*     */         } else {
/* 547 */           inverseTransform(source, target);
/*     */         } 
/*     */         return;
/*     */       } 
/* 551 */     } catch (NoninvertibleTransformException exception) {
/* 553 */       TransformException e = new TransformException(Errors.format(105));
/* 554 */       e.initCause(exception);
/* 555 */       throw e;
/*     */     } 
/* 557 */     throw new TransformException(Errors.format(129));
/*     */   }
/*     */   
/*     */   private void inverseTransform(Point2D source, Point2D.Double target) throws NoninvertibleTransformException {
/* 575 */     if (this.global.inverseTransform(source, target) != target)
/* 576 */       throw new AssertionError(); 
/* 578 */     double x = target.x;
/* 579 */     double y = target.y;
/* 580 */     if (x >= 0.0D && x < this.width && y >= 0.0D && y < this.height) {
/* 582 */       x -= 0.5D * this.width;
/* 583 */       y -= 0.5D * this.height;
/* 584 */       if (Math.abs(x) < Math.abs(y)) {
/* 585 */         target.x = (x > 0.0D) ? this.width : -1.0D;
/*     */       } else {
/* 587 */         target.y = (y > 0.0D) ? this.height : -1.0D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public MathTransform2D inverse() {
/* 597 */     if (this.inverse == null)
/* 598 */       this.inverse = new Inverse(); 
/* 600 */     return this.inverse;
/*     */   }
/*     */   
/*     */   private final class Inverse extends AbstractMathTransform.Inverse implements MathTransform2D, Serializable {
/*     */     private static final long serialVersionUID = 4876426825123740986L;
/*     */     
/*     */     public Inverse() {
/* 621 */       super(LocalizationGridTransform2D.this);
/*     */     }
/*     */     
/*     */     public Point2D transform(Point2D ptSrc, Point2D ptDst) throws TransformException {
/* 629 */       AffineTransform tr = new AffineTransform(LocalizationGridTransform2D.this.global);
/* 630 */       if (ptDst == null) {
/* 631 */         Point2D.Double double_ = new Point2D.Double();
/* 632 */         LocalizationGridTransform2D.this.inverseTransform(ptSrc, double_, tr);
/* 633 */         return double_;
/*     */       } 
/* 635 */       if (ptDst != ptSrc && ptDst instanceof Point2D.Double) {
/* 636 */         LocalizationGridTransform2D.this.inverseTransform(ptSrc, (Point2D.Double)ptDst, tr);
/* 637 */         return ptDst;
/*     */       } 
/* 639 */       Point2D.Double target = new Point2D.Double();
/* 640 */       LocalizationGridTransform2D.this.inverseTransform(ptSrc, target, tr);
/* 641 */       ptDst.setLocation(target);
/* 642 */       return ptDst;
/*     */     }
/*     */     
/*     */     public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
/* 664 */       int postIncrement = 0;
/* 665 */       if (srcPts == dstPts && srcOff < dstOff) {
/* 666 */         srcOff += (numPts - 1) * 2;
/* 667 */         dstOff += (numPts - 1) * 2;
/* 668 */         postIncrement = -4;
/*     */       } 
/* 670 */       Point2D.Double source = new Point2D.Double();
/* 671 */       Point2D.Double target = new Point2D.Double();
/* 672 */       AffineTransform tr = new AffineTransform(LocalizationGridTransform2D.this.global);
/* 673 */       while (--numPts >= 0) {
/* 674 */         source.x = srcPts[srcOff++];
/* 675 */         source.y = srcPts[srcOff++];
/* 676 */         LocalizationGridTransform2D.this.inverseTransform(source, target, tr);
/* 677 */         dstPts[dstOff++] = (float)target.x;
/* 678 */         dstPts[dstOff++] = (float)target.y;
/* 679 */         srcOff += postIncrement;
/* 680 */         dstOff += postIncrement;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 702 */       int postIncrement = 0;
/* 703 */       if (srcPts == dstPts && srcOff < dstOff) {
/* 704 */         srcOff += (numPts - 1) * 2;
/* 705 */         dstOff += (numPts - 1) * 2;
/* 706 */         postIncrement = -4;
/*     */       } 
/* 708 */       Point2D.Double source = new Point2D.Double();
/* 709 */       Point2D.Double target = new Point2D.Double();
/* 710 */       AffineTransform tr = new AffineTransform(LocalizationGridTransform2D.this.global);
/* 711 */       while (--numPts >= 0) {
/* 712 */         source.x = srcPts[srcOff++];
/* 713 */         source.y = srcPts[srcOff++];
/* 714 */         LocalizationGridTransform2D.this.inverseTransform(source, target, tr);
/* 715 */         dstPts[dstOff++] = target.x;
/* 716 */         dstPts[dstOff++] = target.y;
/* 717 */         srcOff += postIncrement;
/* 718 */         dstOff += postIncrement;
/*     */       } 
/*     */     }
/*     */     
/*     */     public MathTransform2D inverse() {
/* 727 */       return (MathTransform2D)super.inverse();
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 734 */       in.defaultReadObject();
/* 735 */       LocalizationGridTransform2D.this.inverse = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 745 */     return 0x801E52CF ^ super.hashCode() ^ this.global.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 753 */     if (super.equals(object)) {
/* 754 */       LocalizationGridTransform2D that = (LocalizationGridTransform2D)object;
/* 755 */       return (this.width == that.width && this.height == that.height && Utilities.equals(this.global, that.global) && Arrays.equals(this.grid, that.grid));
/*     */     } 
/* 759 */     return false;
/*     */   }
/*     */   
/*     */   private static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -8263439392080019340L;
/*     */     
/* 778 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, "WarpPolynomial") }(GeneralParameterDescriptor[])new org.opengis.parameter.ParameterDescriptor[0]);
/*     */     
/*     */     public Provider() {
/* 786 */       super(2, 2, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Transformation> getOperationType() {
/* 794 */       return Transformation.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException, FactoryException {
/* 807 */       throw new FactoryException("Not yet implemented");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\LocalizationGridTransform2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */