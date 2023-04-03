/*     */ package org.geotools.resources.geometry;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import org.geotools.resources.Classes;
/*     */ 
/*     */ public class XRectangle2D extends Rectangle2D implements Serializable {
/*     */   private static final double EPS = 1.0E-6D;
/*     */   
/*  59 */   public static final Rectangle2D INFINITY = InfiniteRectangle2D.INFINITY;
/*     */   
/*     */   private static final long serialVersionUID = -1918221103635749436L;
/*     */   
/*     */   protected double xmin;
/*     */   
/*     */   protected double ymin;
/*     */   
/*     */   protected double xmax;
/*     */   
/*     */   protected double ymax;
/*     */   
/*     */   public XRectangle2D() {}
/*     */   
/*     */   public XRectangle2D(double x, double y, double width, double height) {
/*  82 */     this.xmin = x;
/*  83 */     this.ymin = y;
/*  84 */     this.xmax = x + width;
/*  85 */     this.ymax = y + height;
/*     */   }
/*     */   
/*     */   public XRectangle2D(Rectangle2D rect) {
/*  96 */     if (rect != null)
/*  97 */       setRect(rect); 
/*     */   }
/*     */   
/*     */   public static XRectangle2D createFromExtremums(double xmin, double ymin, double xmax, double ymax) {
/* 109 */     XRectangle2D rect = new XRectangle2D();
/* 110 */     rect.xmin = xmin;
/* 111 */     rect.ymin = ymin;
/* 112 */     rect.xmax = xmax;
/* 113 */     rect.ymax = ymax;
/* 114 */     return rect;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 126 */     return (this.xmin >= this.xmax || this.ymin >= this.ymax);
/*     */   }
/*     */   
/*     */   public double getX() {
/* 136 */     return this.xmin;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 146 */     return this.ymin;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 155 */     return this.xmax - this.xmin;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 164 */     return this.ymax - this.ymin;
/*     */   }
/*     */   
/*     */   public double getMinX() {
/* 172 */     return this.xmin;
/*     */   }
/*     */   
/*     */   public double getMinY() {
/* 180 */     return this.ymin;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/* 188 */     return this.xmax;
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/* 196 */     return this.ymax;
/*     */   }
/*     */   
/*     */   public double getCenterX() {
/* 204 */     return (this.xmin + this.xmax) * 0.5D;
/*     */   }
/*     */   
/*     */   public double getCenterY() {
/* 212 */     return (this.ymin + this.ymax) * 0.5D;
/*     */   }
/*     */   
/*     */   public void setRect(double x, double y, double width, double height) {
/* 228 */     this.xmin = x;
/* 229 */     this.ymin = y;
/* 230 */     this.xmax = x + width;
/* 231 */     this.ymax = y + height;
/*     */   }
/*     */   
/*     */   public void setRect(Rectangle2D r) {
/* 242 */     this.xmin = r.getMinX();
/* 243 */     this.ymin = r.getMinY();
/* 244 */     this.xmax = r.getMaxX();
/* 245 */     this.ymax = r.getMaxY();
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double width, double height) {
/* 267 */     if (this.xmin >= this.xmax || this.ymin >= this.ymax || width <= 0.0D || height <= 0.0D)
/* 268 */       return false; 
/* 270 */     return (x < this.xmax && y < this.ymax && x + width > this.xmin && y + height > this.ymin);
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D rect) {
/* 287 */     if (this.xmin >= this.xmax || this.ymin >= this.ymax)
/* 288 */       return false; 
/* 290 */     double xmin2 = rect.getMinX();
/* 291 */     double xmax2 = rect.getMaxX();
/* 291 */     if (xmax2 <= xmin2)
/* 291 */       return false; 
/* 292 */     double ymin2 = rect.getMinY();
/* 293 */     double ymax2 = rect.getMaxY();
/* 293 */     if (ymax2 <= ymin2)
/* 293 */       return false; 
/* 294 */     return (xmin2 < this.xmax && ymin2 < this.ymax && xmax2 > this.xmin && ymax2 > this.ymin);
/*     */   }
/*     */   
/*     */   public static boolean intersectInclusive(Rectangle2D rect1, Rectangle2D rect2) {
/* 323 */     double xmin1 = rect1.getMinX();
/* 324 */     double xmax1 = rect1.getMaxX();
/* 324 */     if (xmax1 < xmin1)
/* 324 */       return false; 
/* 325 */     double ymin1 = rect1.getMinY();
/* 326 */     double ymax1 = rect1.getMaxY();
/* 326 */     if (ymax1 < ymin1)
/* 326 */       return false; 
/* 327 */     double xmin2 = rect2.getMinX();
/* 328 */     double xmax2 = rect2.getMaxX();
/* 328 */     if (xmax2 < xmin2)
/* 328 */       return false; 
/* 329 */     double ymin2 = rect2.getMinY();
/* 330 */     double ymax2 = rect2.getMaxY();
/* 330 */     if (ymax2 < ymin2)
/* 330 */       return false; 
/* 331 */     return (xmax2 >= xmin1 && ymax2 >= ymin1 && xmin2 <= xmax1 && ymin2 <= ymax1);
/*     */   }
/*     */   
/*     */   public static boolean intersectInclusive(Shape shape, Rectangle2D rect) {
/* 357 */     double x = rect.getX();
/* 358 */     double y = rect.getY();
/* 359 */     double width = rect.getWidth();
/* 360 */     double height = rect.getHeight();
/* 361 */     if (width == 0.0D && height == 0.0D) {
/* 362 */       width = 1.0E-6D;
/* 363 */       height = 1.0E-6D;
/* 364 */     } else if (width == 0.0D) {
/* 365 */       width = height * 1.0E-6D;
/* 366 */       x -= 0.5D * width;
/* 367 */     } else if (height == 0.0D) {
/* 368 */       height = width * 1.0E-6D;
/* 369 */       y -= 0.5D * height;
/*     */     } 
/* 371 */     return shape.intersects(x, y, width, height);
/*     */   }
/*     */   
/*     */   public static boolean equalsEpsilon(Rectangle2D rect1, Rectangle2D rect2) {
/* 378 */     double dx = 0.5D * Math.abs(rect1.getWidth() + rect2.getWidth());
/* 379 */     double dy = 0.5D * Math.abs(rect1.getHeight() + rect2.getHeight());
/* 380 */     if (dx > 0.0D) {
/* 380 */       dx *= 1.0E-6D;
/*     */     } else {
/* 380 */       dx = 1.0E-6D;
/*     */     } 
/* 381 */     if (dy > 0.0D) {
/* 381 */       dy *= 1.0E-6D;
/*     */     } else {
/* 381 */       dy = 1.0E-6D;
/*     */     } 
/* 382 */     return (equalsEpsilon(rect1.getMinX(), rect2.getMinX(), dx) && equalsEpsilon(rect1.getMinY(), rect2.getMinY(), dy) && equalsEpsilon(rect1.getMaxX(), rect2.getMaxX(), dx) && equalsEpsilon(rect1.getMaxY(), rect2.getMaxY(), dy));
/*     */   }
/*     */   
/*     */   private static boolean equalsEpsilon(double v1, double v2, double eps) {
/* 392 */     return (Math.abs(v1 - v2) < eps || Double.doubleToLongBits(v1) == Double.doubleToLongBits(v2));
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double width, double height) {
/* 414 */     if (this.xmin >= this.xmax || this.ymin >= this.ymax || width <= 0.0D || height <= 0.0D)
/* 415 */       return false; 
/* 417 */     return (x >= this.xmin && y >= this.ymin && x + width <= this.xmax && y + height <= this.ymax);
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D rect) {
/* 432 */     if (this.xmin >= this.xmax || this.ymin >= this.ymax)
/* 433 */       return false; 
/* 435 */     double xmin2 = rect.getMinX();
/* 436 */     double xmax2 = rect.getMaxX();
/* 436 */     if (xmax2 <= xmin2)
/* 436 */       return false; 
/* 437 */     double ymin2 = rect.getMinY();
/* 438 */     double ymax2 = rect.getMaxY();
/* 438 */     if (ymax2 <= ymin2)
/* 438 */       return false; 
/* 439 */     return (xmin2 >= this.xmin && ymin2 >= this.ymin && xmax2 <= this.xmax && ymax2 <= this.ymax);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 454 */     return (x >= this.xmin && y >= this.ymin && x < this.xmax && y < this.ymax);
/*     */   }
/*     */   
/*     */   public static boolean containsInclusive(Rectangle2D outter, Rectangle2D inner) {
/* 484 */     return (outter.getMinX() <= inner.getMinX() && outter.getMaxX() >= inner.getMaxX() && outter.getMinY() <= inner.getMinY() && outter.getMaxY() >= inner.getMaxY());
/*     */   }
/*     */   
/*     */   public int outcode(double x, double y) {
/* 504 */     int out = 0;
/* 505 */     if (this.xmax <= this.xmin) {
/* 505 */       out |= 0x5;
/* 506 */     } else if (x < this.xmin) {
/* 506 */       out |= 0x1;
/* 507 */     } else if (x > this.xmax) {
/* 507 */       out |= 0x4;
/*     */     } 
/* 509 */     if (this.ymax <= this.ymin) {
/* 509 */       out |= 0xA;
/* 510 */     } else if (y < this.ymin) {
/* 510 */       out |= 0x2;
/* 511 */     } else if (y > this.ymax) {
/* 511 */       out |= 0x8;
/*     */     } 
/* 512 */     return out;
/*     */   }
/*     */   
/*     */   public Rectangle2D createIntersection(Rectangle2D rect) {
/* 525 */     XRectangle2D r = new XRectangle2D();
/* 526 */     r.xmin = Math.max(this.xmin, rect.getMinX());
/* 527 */     r.ymin = Math.max(this.ymin, rect.getMinY());
/* 528 */     r.xmax = Math.min(this.xmax, rect.getMaxX());
/* 529 */     r.ymax = Math.min(this.ymax, rect.getMaxY());
/* 530 */     return r;
/*     */   }
/*     */   
/*     */   public Rectangle2D createUnion(Rectangle2D rect) {
/* 545 */     XRectangle2D r = new XRectangle2D();
/* 546 */     r.xmin = Math.min(this.xmin, rect.getMinX());
/* 547 */     r.ymin = Math.min(this.ymin, rect.getMinY());
/* 548 */     r.xmax = Math.max(this.xmax, rect.getMaxX());
/* 549 */     r.ymax = Math.max(this.ymax, rect.getMaxY());
/* 550 */     return r;
/*     */   }
/*     */   
/*     */   public void add(double x, double y) {
/* 569 */     if (x < this.xmin)
/* 569 */       this.xmin = x; 
/* 570 */     if (x > this.xmax)
/* 570 */       this.xmax = x; 
/* 571 */     if (y < this.ymin)
/* 571 */       this.ymin = y; 
/* 572 */     if (y > this.ymax)
/* 572 */       this.ymax = y; 
/*     */   }
/*     */   
/*     */   public void add(Rectangle2D rect) {
/*     */     double t;
/* 585 */     if ((t = rect.getMinX()) < this.xmin)
/* 585 */       this.xmin = t; 
/* 586 */     if ((t = rect.getMaxX()) > this.xmax)
/* 586 */       this.xmax = t; 
/* 587 */     if ((t = rect.getMinY()) < this.ymin)
/* 587 */       this.ymin = t; 
/* 588 */     if ((t = rect.getMaxY()) > this.ymax)
/* 588 */       this.ymax = t; 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 598 */     StringBuffer buffer = new StringBuffer(Classes.getShortClassName(this));
/* 599 */     NumberFormat format = NumberFormat.getNumberInstance();
/* 600 */     FieldPosition dummy = new FieldPosition(0);
/* 601 */     buffer.append("[xmin=");
/* 601 */     format.format(this.xmin, buffer, dummy);
/* 602 */     buffer.append(" xmax=");
/* 602 */     format.format(this.xmax, buffer, dummy);
/* 603 */     buffer.append(" ymin=");
/* 603 */     format.format(this.ymin, buffer, dummy);
/* 604 */     buffer.append(" ymax=");
/* 604 */     format.format(this.ymax, buffer, dummy);
/* 605 */     buffer.append(']');
/* 606 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\geometry\XRectangle2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */