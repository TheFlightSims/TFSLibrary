/*     */ package org.geotools.referencing.operation.matrix;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RectangularShape;
/*     */ import org.geotools.math.XMath;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class XAffineTransform extends AffineTransform {
/*     */   private static final long serialVersionUID = 5215291166450556451L;
/*     */   
/*     */   public XAffineTransform(AffineTransform tr) {
/*  63 */     super(tr);
/*     */   }
/*     */   
/*     */   public XAffineTransform(double m00, double m10, double m01, double m11, double m02, double m12) {
/*  75 */     super(m00, m10, m01, m11, m02, m12);
/*     */   }
/*     */   
/*     */   protected void checkPermission() throws UnsupportedOperationException {
/*  89 */     throw new UnsupportedOperationException(Errors.format(188));
/*     */   }
/*     */   
/*     */   public void translate(double tx, double ty) {
/*  98 */     checkPermission();
/*  99 */     super.translate(tx, ty);
/*     */   }
/*     */   
/*     */   public void rotate(double theta) {
/* 107 */     checkPermission();
/* 108 */     super.rotate(theta);
/*     */   }
/*     */   
/*     */   public void rotate(double theta, double x, double y) {
/* 116 */     checkPermission();
/* 117 */     super.rotate(theta, x, y);
/*     */   }
/*     */   
/*     */   public void scale(double sx, double sy) {
/* 125 */     checkPermission();
/* 126 */     super.scale(sx, sy);
/*     */   }
/*     */   
/*     */   public void shear(double shx, double shy) {
/* 134 */     checkPermission();
/* 135 */     super.shear(shx, shy);
/*     */   }
/*     */   
/*     */   public void setToIdentity() {
/* 143 */     checkPermission();
/* 144 */     super.setToIdentity();
/*     */   }
/*     */   
/*     */   public void setToTranslation(double tx, double ty) {
/* 152 */     checkPermission();
/* 153 */     super.setToTranslation(tx, ty);
/*     */   }
/*     */   
/*     */   public void setToRotation(double theta) {
/* 161 */     checkPermission();
/* 162 */     super.setToRotation(theta);
/*     */   }
/*     */   
/*     */   public void setToRotation(double theta, double x, double y) {
/* 170 */     checkPermission();
/* 171 */     super.setToRotation(theta, x, y);
/*     */   }
/*     */   
/*     */   public void setToScale(double sx, double sy) {
/* 179 */     checkPermission();
/* 180 */     super.setToScale(sx, sy);
/*     */   }
/*     */   
/*     */   public void setToShear(double shx, double shy) {
/* 188 */     checkPermission();
/* 189 */     super.setToShear(shx, shy);
/*     */   }
/*     */   
/*     */   public void setTransform(AffineTransform Tx) {
/* 197 */     checkPermission();
/* 198 */     super.setTransform(Tx);
/*     */   }
/*     */   
/*     */   public void setTransform(double m00, double m10, double m01, double m11, double m02, double m12) {
/* 208 */     checkPermission();
/* 209 */     super.setTransform(m00, m10, m01, m11, m02, m12);
/*     */   }
/*     */   
/*     */   public void concatenate(AffineTransform Tx) {
/* 217 */     checkPermission();
/* 218 */     super.concatenate(Tx);
/*     */   }
/*     */   
/*     */   public void preConcatenate(AffineTransform Tx) {
/* 226 */     checkPermission();
/* 227 */     super.preConcatenate(Tx);
/*     */   }
/*     */   
/*     */   public boolean isIdentity(double tolerance) {
/* 240 */     return isIdentity(this, tolerance);
/*     */   }
/*     */   
/*     */   public static boolean isIdentity(AffineTransform tr, double tolerance) {
/* 267 */     if (tr.isIdentity())
/* 268 */       return true; 
/* 270 */     tolerance = Math.abs(tolerance);
/* 271 */     return (Math.abs(tr.getScaleX() - 1.0D) <= tolerance && Math.abs(tr.getScaleY() - 1.0D) <= tolerance && Math.abs(tr.getShearX()) <= tolerance && Math.abs(tr.getShearY()) <= tolerance && Math.abs(tr.getTranslateX()) <= tolerance && Math.abs(tr.getTranslateY()) <= tolerance);
/*     */   }
/*     */   
/*     */   public static Shape transform(AffineTransform transform, Shape shape, boolean overwrite) {
/* 303 */     int type = transform.getType();
/* 304 */     if (type == 0)
/* 305 */       return shape; 
/* 309 */     if ((type & 0x30) == 0) {
/* 311 */       if (shape instanceof Rectangle2D) {
/* 312 */         Rectangle2D rect = (Rectangle2D)shape;
/* 313 */         return transform(transform, rect, overwrite ? rect : null);
/*     */       } 
/* 317 */       if ((type & 0x0) == 0 && 
/* 318 */         shape instanceof RectangularShape) {
/* 319 */         RectangularShape rect = (RectangularShape)shape;
/* 320 */         if (!overwrite)
/* 321 */           rect = (RectangularShape)rect.clone(); 
/* 323 */         Rectangle2D frame = rect.getFrame();
/* 324 */         rect.setFrame(transform(transform, frame, frame));
/* 325 */         return rect;
/*     */       } 
/*     */     } 
/* 331 */     if (shape instanceof GeneralPath) {
/* 332 */       GeneralPath path = (GeneralPath)shape;
/* 333 */       if (overwrite) {
/* 334 */         path.transform(transform);
/*     */       } else {
/* 336 */         shape = path.createTransformedShape(transform);
/*     */       } 
/* 338 */     } else if (shape instanceof Area) {
/* 339 */       Area area = (Area)shape;
/* 340 */       if (overwrite) {
/* 341 */         area.transform(transform);
/*     */       } else {
/* 343 */         shape = area.createTransformedArea(transform);
/*     */       } 
/*     */     } else {
/* 346 */       GeneralPath path = new GeneralPath(shape);
/* 347 */       path.transform(transform);
/* 348 */       shape = path;
/*     */     } 
/* 353 */     return shape;
/*     */   }
/*     */   
/*     */   public static Rectangle2D transform(AffineTransform transform, Rectangle2D bounds, Rectangle2D dest) {
/* 380 */     double xmin = Double.POSITIVE_INFINITY;
/* 381 */     double ymin = Double.POSITIVE_INFINITY;
/* 382 */     double xmax = Double.NEGATIVE_INFINITY;
/* 383 */     double ymax = Double.NEGATIVE_INFINITY;
/* 384 */     Point2D.Double point = new Point2D.Double();
/* 385 */     for (int i = 0; i < 4; i++) {
/* 386 */       point.x = ((i & 0x1) == 0) ? bounds.getMinX() : bounds.getMaxX();
/* 387 */       point.y = ((i & 0x2) == 0) ? bounds.getMinY() : bounds.getMaxY();
/* 388 */       transform.transform(point, point);
/* 389 */       if (point.x < xmin)
/* 389 */         xmin = point.x; 
/* 390 */       if (point.x > xmax)
/* 390 */         xmax = point.x; 
/* 391 */       if (point.y < ymin)
/* 391 */         ymin = point.y; 
/* 392 */       if (point.y > ymax)
/* 392 */         ymax = point.y; 
/*     */     } 
/* 394 */     if (dest != null) {
/* 395 */       dest.setRect(xmin, ymin, xmax - xmin, ymax - ymin);
/* 396 */       return dest;
/*     */     } 
/* 398 */     return new Rectangle2D.Double(xmin, ymin, xmax - xmin, ymax - ymin);
/*     */   }
/*     */   
/*     */   public static Rectangle2D inverseTransform(AffineTransform transform, Rectangle2D bounds, Rectangle2D dest) throws NoninvertibleTransformException {
/* 424 */     double xmin = Double.POSITIVE_INFINITY;
/* 425 */     double ymin = Double.POSITIVE_INFINITY;
/* 426 */     double xmax = Double.NEGATIVE_INFINITY;
/* 427 */     double ymax = Double.NEGATIVE_INFINITY;
/* 428 */     Point2D.Double point = new Point2D.Double();
/* 429 */     for (int i = 0; i < 4; i++) {
/* 430 */       point.x = ((i & 0x1) == 0) ? bounds.getMinX() : bounds.getMaxX();
/* 431 */       point.y = ((i & 0x2) == 0) ? bounds.getMinY() : bounds.getMaxY();
/* 432 */       transform.inverseTransform(point, point);
/* 433 */       if (point.x < xmin)
/* 433 */         xmin = point.x; 
/* 434 */       if (point.x > xmax)
/* 434 */         xmax = point.x; 
/* 435 */       if (point.y < ymin)
/* 435 */         ymin = point.y; 
/* 436 */       if (point.y > ymax)
/* 436 */         ymax = point.y; 
/*     */     } 
/* 438 */     if (dest != null) {
/* 439 */       dest.setRect(xmin, ymin, xmax - xmin, ymax - ymin);
/* 440 */       return dest;
/*     */     } 
/* 442 */     return new Rectangle2D.Double(xmin, ymin, xmax - xmin, ymax - ymin);
/*     */   }
/*     */   
/*     */   public static Point2D inverseDeltaTransform(AffineTransform transform, Point2D source, Point2D dest) throws NoninvertibleTransformException {
/* 462 */     double m00 = transform.getScaleX();
/* 463 */     double m11 = transform.getScaleY();
/* 464 */     double m01 = transform.getShearX();
/* 465 */     double m10 = transform.getShearY();
/* 466 */     double det = m00 * m11 - m01 * m10;
/* 467 */     if (Math.abs(det) <= Double.MIN_VALUE)
/* 468 */       return transform.createInverse().deltaTransform(source, dest); 
/* 470 */     double x0 = source.getX();
/* 471 */     double y0 = source.getY();
/* 472 */     double x = (x0 * m11 - y0 * m01) / det;
/* 473 */     double y = (y0 * m00 - x0 * m10) / det;
/* 474 */     if (dest != null) {
/* 475 */       dest.setLocation(x, y);
/* 476 */       return dest;
/*     */     } 
/* 478 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public static int getSwapXY(AffineTransform tr) {
/* 490 */     int flip = getFlip(tr);
/* 491 */     if (flip != 0) {
/* 492 */       double scaleX = getScaleX0(tr);
/* 493 */       double scaleY = getScaleY0(tr) * flip;
/* 494 */       double y = Math.abs(tr.getShearY() / scaleY - tr.getShearX() / scaleX);
/* 495 */       double x = Math.abs(tr.getScaleY() / scaleY + tr.getScaleX() / scaleX);
/* 496 */       if (x > y)
/* 496 */         return 1; 
/* 497 */       if (x < y)
/* 497 */         return -1; 
/*     */     } 
/* 500 */     return 0;
/*     */   }
/*     */   
/*     */   public static double getRotation(AffineTransform tr) {
/* 515 */     int flip = getFlip(tr);
/* 516 */     if (flip != 0) {
/* 517 */       double scaleX = getScaleX0(tr);
/* 518 */       double scaleY = getScaleY0(tr) * flip;
/* 519 */       return Math.atan2(tr.getShearY() / scaleY - tr.getShearX() / scaleX, tr.getScaleY() / scaleY + tr.getScaleX() / scaleX);
/*     */     } 
/* 522 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public static int getFlip(AffineTransform tr) {
/* 554 */     int scaleX = XMath.sgn(tr.getScaleX());
/* 555 */     int scaleY = XMath.sgn(tr.getScaleY());
/* 556 */     int shearX = XMath.sgn(tr.getShearX());
/* 557 */     int shearY = XMath.sgn(tr.getShearY());
/* 558 */     if (scaleX == scaleY && shearX == -shearY)
/* 558 */       return 1; 
/* 559 */     if (scaleX == -scaleY && shearX == shearY)
/* 559 */       return -1; 
/* 560 */     return 0;
/*     */   }
/*     */   
/*     */   public static double getScaleX0(AffineTransform tr) {
/* 569 */     double scale = tr.getScaleX();
/* 570 */     double shear = tr.getShearX();
/* 571 */     if (shear == 0.0D)
/* 571 */       return Math.abs(scale); 
/* 572 */     if (scale == 0.0D)
/* 572 */       return Math.abs(shear); 
/* 573 */     return Math.hypot(scale, shear);
/*     */   }
/*     */   
/*     */   public static double getScaleY0(AffineTransform tr) {
/* 582 */     double scale = tr.getScaleY();
/* 583 */     double shear = tr.getShearY();
/* 584 */     if (shear == 0.0D)
/* 584 */       return Math.abs(scale); 
/* 585 */     if (scale == 0.0D)
/* 585 */       return Math.abs(shear); 
/* 586 */     return Math.hypot(scale, shear);
/*     */   }
/*     */   
/*     */   public static double getScale(AffineTransform tr) {
/* 596 */     return 0.5D * (getScaleX0(tr) + getScaleY0(tr));
/*     */   }
/*     */   
/*     */   public static AffineTransform getScaleInstance(double sx, double sy, double x, double y) {
/* 614 */     return new AffineTransform(sx, 0.0D, 0.0D, sy, (1.0D - sx) * x, (1.0D - sy) * y);
/*     */   }
/*     */   
/*     */   public static void round(AffineTransform tr, double tolerance) {
/*     */     double r;
/*     */     double m00;
/*     */     double m01;
/*     */     double m10;
/*     */     double m11;
/* 633 */     if (Math.abs((m00 = Math.rint(r = tr.getScaleX())) - r) <= tolerance && Math.abs((m01 = Math.rint(r = tr.getShearX())) - r) <= tolerance && Math.abs((m11 = Math.rint(r = tr.getScaleY())) - r) <= tolerance && Math.abs((m10 = Math.rint(r = tr.getShearY())) - r) <= tolerance)
/* 638 */       if ((m00 != 0.0D || m01 != 0.0D) && (m10 != 0.0D || m11 != 0.0D)) {
/* 639 */         double m02 = Math.rint(r = tr.getTranslateX());
/* 639 */         if (Math.abs(m02 - r) > tolerance)
/* 639 */           m02 = r; 
/* 640 */         double m12 = Math.rint(r = tr.getTranslateY());
/* 640 */         if (Math.abs(m12 - r) > tolerance)
/* 640 */           m12 = r; 
/* 641 */         tr.setTransform(m00, m10, m01, m11, m02, m12);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\XAffineTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */