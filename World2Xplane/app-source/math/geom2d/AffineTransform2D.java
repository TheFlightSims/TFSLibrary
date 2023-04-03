/*     */ package math.geom2d;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.transform.Bijection2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class AffineTransform2D implements Bijection2D, GeometricObject2D, Cloneable {
/*     */   protected double m00;
/*     */   
/*     */   protected double m01;
/*     */   
/*     */   protected double m02;
/*     */   
/*     */   protected double m10;
/*     */   
/*     */   protected double m11;
/*     */   
/*     */   protected double m12;
/*     */   
/*     */   public static AffineTransform2D createIdentity() {
/*  64 */     return new AffineTransform2D(1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D create(AffineTransform2D trans) {
/*  73 */     return new AffineTransform2D(
/*  74 */         trans.m00, trans.m01, trans.m02, 
/*  75 */         trans.m10, trans.m11, trans.m12);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D create(double[] coefs) {
/*  84 */     if (coefs.length == 4)
/*  85 */       return new AffineTransform2D(
/*  86 */           coefs[0], coefs[1], 0.0D, 
/*  87 */           coefs[2], coefs[3], 0.0D); 
/*  88 */     if (coefs.length == 6)
/*  89 */       return new AffineTransform2D(
/*  90 */           coefs[0], coefs[1], coefs[2], 
/*  91 */           coefs[3], coefs[4], coefs[5]); 
/*  93 */     throw new IllegalArgumentException("Input array must have either 4 or 6 elements");
/*     */   }
/*     */   
/*     */   public static AffineTransform2D create(double xx, double yx, double tx, double xy, double yy, double ty) {
/* 103 */     return new AffineTransform2D(xx, yx, tx, xy, yy, ty);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createGlideReflection(LinearShape2D line, double distance) {
/* 114 */     Vector2D vector = line.direction().normalize();
/* 115 */     Point2D origin = line.origin();
/* 118 */     double dx = vector.x();
/* 119 */     double dy = vector.y();
/* 120 */     double x0 = origin.x();
/* 121 */     double y0 = origin.y();
/* 124 */     double tx = dx * distance;
/* 125 */     double ty = dy * distance;
/* 128 */     double delta = dx * dx + dy * dy;
/* 129 */     double dx2 = dx * dx;
/* 130 */     double dy2 = dy * dy;
/* 131 */     double dxy = dx * dy;
/* 132 */     double dxy0 = dx * y0;
/* 133 */     double dyx0 = dy * x0;
/* 136 */     return new AffineTransform2D((
/* 137 */         dx2 - dy2) / delta, 
/* 138 */         2.0D * dxy / delta, 
/* 139 */         2.0D * dy * (dyx0 - dxy0) / delta + tx, 
/* 140 */         2.0D * dxy / delta, (
/* 141 */         dy2 - dx2) / delta, 
/* 142 */         2.0D * dx * (dxy0 - dyx0) / delta + ty);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static AffineTransform2D createHomothecy(Point2D center, double k) {
/* 150 */     return createScaling(center, k, k);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createLineReflection(LinearShape2D line) {
/* 159 */     Point2D origin = line.origin();
/* 160 */     Vector2D vector = line.direction();
/* 163 */     double dx = vector.x();
/* 164 */     double dy = vector.y();
/* 165 */     double x0 = origin.x();
/* 166 */     double y0 = origin.y();
/* 169 */     double dx2 = dx * dx;
/* 170 */     double dy2 = dy * dy;
/* 171 */     double dxy = dx * dy;
/* 172 */     double delta = dx2 + dy2;
/* 175 */     return new AffineTransform2D((
/* 176 */         dx2 - dy2) / delta, 
/* 177 */         2.0D * dxy / delta, 
/* 178 */         2.0D * (dy2 * x0 - dxy * y0) / delta, 
/* 179 */         2.0D * dxy / delta, (
/* 180 */         dy2 - dx2) / delta, 
/* 181 */         2.0D * (dx2 * y0 - dxy * x0) / delta);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createPointReflection(Point2D center) {
/* 193 */     return createScaling(center, -1.0D, -1.0D);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createQuadrantRotation(int numQuadrant) {
/* 201 */     int n = (numQuadrant % 4 + 4) % 4;
/* 202 */     switch (n) {
/*     */       case 0:
/* 204 */         return new AffineTransform2D(1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D);
/*     */       case 1:
/* 206 */         return new AffineTransform2D(0.0D, -1.0D, 0.0D, 1.0D, 0.0D, 0.0D);
/*     */       case 2:
/* 208 */         return new AffineTransform2D(-1.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D);
/*     */       case 3:
/* 210 */         return new AffineTransform2D(0.0D, 1.0D, 0.0D, -1.0D, 0.0D, 0.0D);
/*     */     } 
/* 212 */     throw new RuntimeException("Error in integer rounding...");
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createQuadrantRotation(Point2D center, int numQuadrant) {
/* 222 */     AffineTransform2D trans = createQuadrantRotation(numQuadrant);
/* 223 */     trans.recenter(center.x(), center.y());
/* 224 */     return trans;
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createQuadrantRotation(double x0, double y0, int numQuadrant) {
/* 233 */     AffineTransform2D trans = createQuadrantRotation(numQuadrant);
/* 234 */     trans.recenter(x0, y0);
/* 235 */     return trans;
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createRotation(double angle) {
/* 242 */     return createRotation(0.0D, 0.0D, angle);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createRotation(Point2D center, double angle) {
/* 249 */     return createRotation(center.x(), center.y(), angle);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createRotation(double cx, double cy, double angle) {
/* 259 */     angle = Angle2D.formatAngle(angle);
/* 262 */     int k = (int)Math.round(angle * 2.0D / Math.PI);
/* 263 */     if (Math.abs(k * Math.PI / 2.0D - angle) < 1.0E-12D)
/* 264 */       return createQuadrantRotation(cx, cy, k); 
/* 268 */     double cot = Math.cos(angle);
/* 269 */     double sit = Math.sin(angle);
/* 272 */     return new AffineTransform2D(
/* 273 */         cot, -sit, (1.0D - cot) * cx + sit * cy, 
/* 274 */         sit, cot, (1.0D - cot) * cy - sit * cx);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createScaling(double sx, double sy) {
/* 281 */     return createScaling(new Point2D(0.0D, 0.0D), sx, sy);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createScaling(Point2D center, double sx, double sy) {
/* 290 */     return new AffineTransform2D(
/* 291 */         sx, 0.0D, (1.0D - sx) * center.x(), 
/* 292 */         0.0D, sy, (1.0D - sy) * center.y());
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createShear(double shx, double shy) {
/* 305 */     return new AffineTransform2D(1.0D, shx, 0.0D, shy, 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createTransform(AffineTransform transform) {
/* 312 */     return new AffineTransform2D(transform);
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createTranslation(Vector2D vect) {
/* 319 */     return new AffineTransform2D(1.0D, 0.0D, vect.x(), 0.0D, 1.0D, vect.y());
/*     */   }
/*     */   
/*     */   public static AffineTransform2D createTranslation(double dx, double dy) {
/* 326 */     return new AffineTransform2D(1.0D, 0.0D, dx, 0.0D, 1.0D, dy);
/*     */   }
/*     */   
/*     */   public static boolean isIdentity(AffineTransform2D trans) {
/* 336 */     if (Math.abs(trans.m00 - 1.0D) > 1.0E-12D)
/* 337 */       return false; 
/* 338 */     if (Math.abs(trans.m01) > 1.0E-12D)
/* 339 */       return false; 
/* 340 */     if (Math.abs(trans.m02) > 1.0E-12D)
/* 341 */       return false; 
/* 342 */     if (Math.abs(trans.m10) > 1.0E-12D)
/* 343 */       return false; 
/* 344 */     if (Math.abs(trans.m11 - 1.0D) > 1.0E-12D)
/* 345 */       return false; 
/* 346 */     if (Math.abs(trans.m12) > 1.0E-12D)
/* 347 */       return false; 
/* 348 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isDirect(AffineTransform2D trans) {
/* 358 */     return (trans.m00 * trans.m11 - trans.m01 * trans.m10 > 0.0D);
/*     */   }
/*     */   
/*     */   public static boolean isIsometry(AffineTransform2D trans) {
/* 370 */     double a = trans.m00;
/* 371 */     double b = trans.m01;
/* 372 */     double c = trans.m10;
/* 373 */     double d = trans.m11;
/* 376 */     if (Math.abs(a * a + b * b - 1.0D) > 1.0E-12D)
/* 377 */       return false; 
/* 378 */     if (Math.abs(c * c + d * d - 1.0D) > 1.0E-12D)
/* 379 */       return false; 
/* 382 */     if (Math.abs(a * b + c * d) > 1.0E-12D)
/* 383 */       return false; 
/* 386 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isMotion(AffineTransform2D trans) {
/* 398 */     return (isIsometry(trans) && isDirect(trans));
/*     */   }
/*     */   
/*     */   public static boolean isSimilarity(AffineTransform2D trans) {
/* 409 */     double a = trans.m00;
/* 410 */     double b = trans.m01;
/* 411 */     double c = trans.m10;
/* 412 */     double d = trans.m11;
/* 415 */     double k2 = Math.abs(a * d - b * c);
/* 418 */     if (Math.abs(a * a + b * b - k2) > 1.0E-12D)
/* 419 */       return false; 
/* 420 */     if (Math.abs(c * c + d * d - k2) > 1.0E-12D)
/* 421 */       return false; 
/* 422 */     if (Math.abs(a * a + c * c - k2) > 1.0E-12D)
/* 423 */       return false; 
/* 424 */     if (Math.abs(b * b + d * d - k2) > 1.0E-12D)
/* 425 */       return false; 
/* 428 */     return true;
/*     */   }
/*     */   
/*     */   public AffineTransform2D() {
/* 439 */     this.m00 = this.m11 = 1.0D;
/* 440 */     this.m01 = this.m10 = 0.0D;
/* 441 */     this.m02 = this.m12 = 0.0D;
/*     */   }
/*     */   
/*     */   public AffineTransform2D(AffineTransform2D trans) {
/* 446 */     this.m00 = trans.m00;
/* 447 */     this.m01 = trans.m01;
/* 448 */     this.m02 = trans.m02;
/* 449 */     this.m10 = trans.m10;
/* 450 */     this.m11 = trans.m11;
/* 451 */     this.m12 = trans.m12;
/*     */   }
/*     */   
/*     */   public AffineTransform2D(AffineTransform transform) {
/* 458 */     double[] coefs = new double[6];
/* 459 */     transform.getMatrix(coefs);
/* 460 */     assignCoefs(coefs);
/*     */   }
/*     */   
/*     */   public AffineTransform2D(double[] coefs) {
/* 464 */     assignCoefs(coefs);
/*     */   }
/*     */   
/*     */   public AffineTransform2D(double xx, double yx, double tx, double xy, double yy, double ty) {
/* 469 */     this.m00 = xx;
/* 470 */     this.m01 = yx;
/* 471 */     this.m02 = tx;
/* 472 */     this.m10 = xy;
/* 473 */     this.m11 = yy;
/* 474 */     this.m12 = ty;
/*     */   }
/*     */   
/*     */   private void assignCoefs(double[] coefs) {
/* 481 */     if (coefs.length == 4) {
/* 482 */       this.m00 = coefs[0];
/* 483 */       this.m01 = coefs[1];
/* 484 */       this.m10 = coefs[2];
/* 485 */       this.m11 = coefs[3];
/*     */     } else {
/* 487 */       this.m00 = coefs[0];
/* 488 */       this.m01 = coefs[1];
/* 489 */       this.m02 = coefs[2];
/* 490 */       this.m10 = coefs[3];
/* 491 */       this.m11 = coefs[4];
/* 492 */       this.m12 = coefs[5];
/*     */     } 
/*     */   }
/*     */   
/*     */   private void recenter(double x0, double y0) {
/* 503 */     this.m02 = (1.0D - this.m00) * x0 - this.m01 * y0;
/* 504 */     this.m12 = (1.0D - this.m11) * y0 - this.m10 * x0;
/*     */   }
/*     */   
/*     */   public double[] coefficients() {
/* 514 */     double[] tab = { this.m00, this.m01, this.m02, this.m10, this.m11, this.m12 };
/* 515 */     return tab;
/*     */   }
/*     */   
/*     */   public double[][] affineMatrix() {
/* 524 */     double[][] tab = { { this.m00, this.m01, this.m02 }, { this.m10, this.m11, this.m12 }, { 0.0D, 0.0D, 1.0D } };
/* 528 */     return tab;
/*     */   }
/*     */   
/*     */   public AffineTransform asAwtTransform() {
/* 535 */     return new AffineTransform(
/* 536 */         this.m00, this.m01, this.m02, 
/* 537 */         this.m10, this.m11, this.m12);
/*     */   }
/*     */   
/*     */   public AffineTransform2D concatenate(AffineTransform2D that) {
/* 552 */     double n00 = this.m00 * that.m00 + this.m01 * that.m10;
/* 553 */     double n01 = this.m00 * that.m01 + this.m01 * that.m11;
/* 554 */     double n02 = this.m00 * that.m02 + this.m01 * that.m12 + this.m02;
/* 555 */     double n10 = this.m10 * that.m00 + this.m11 * that.m10;
/* 556 */     double n11 = this.m10 * that.m01 + this.m11 * that.m11;
/* 557 */     double n12 = this.m10 * that.m02 + this.m11 * that.m12 + this.m12;
/* 558 */     return new AffineTransform2D(n00, n01, n02, n10, n11, n12);
/*     */   }
/*     */   
/*     */   public AffineTransform2D chain(AffineTransform2D that) {
/* 579 */     return new AffineTransform2D(
/* 580 */         that.m00 * this.m00 + that.m01 * this.m10, 
/* 581 */         that.m00 * this.m01 + that.m01 * this.m11, 
/* 582 */         that.m00 * this.m02 + that.m01 * this.m12 + that.m02, 
/* 583 */         that.m10 * this.m00 + that.m11 * this.m10, 
/* 584 */         that.m10 * this.m01 + that.m11 * this.m11, 
/* 585 */         that.m10 * this.m02 + that.m11 * this.m12 + that.m12);
/*     */   }
/*     */   
/*     */   public AffineTransform2D preConcatenate(AffineTransform2D that) {
/* 600 */     return chain(that);
/*     */   }
/*     */   
/*     */   public boolean isSimilarity() {
/* 610 */     return isSimilarity(this);
/*     */   }
/*     */   
/*     */   public boolean isMotion() {
/* 618 */     return isMotion(this);
/*     */   }
/*     */   
/*     */   public boolean isIsometry() {
/* 630 */     return isIsometry(this);
/*     */   }
/*     */   
/*     */   public boolean isDirect() {
/* 639 */     return isDirect(this);
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 647 */     return isIdentity(this);
/*     */   }
/*     */   
/*     */   public AffineTransform2D invert() {
/* 660 */     double det = this.m00 * this.m11 - this.m10 * this.m01;
/* 662 */     if (Math.abs(det) < 1.0E-12D)
/* 663 */       throw new NonInvertibleTransform2DException(this); 
/* 665 */     return new AffineTransform2D(
/* 666 */         this.m11 / det, -this.m01 / det, (this.m01 * this.m12 - this.m02 * this.m11) / det, 
/* 667 */         -this.m10 / det, this.m00 / det, (this.m02 * this.m10 - this.m00 * this.m12) / det);
/*     */   }
/*     */   
/*     */   public Point2D transform(Point2D p) {
/* 677 */     Point2D dst = new Point2D(
/* 678 */         p.x() * this.m00 + p.y() * this.m01 + this.m02, 
/* 679 */         p.x() * this.m10 + p.y() * this.m11 + this.m12);
/* 680 */     return dst;
/*     */   }
/*     */   
/*     */   public Point2D[] transform(Point2D[] src, Point2D[] dst) {
/* 684 */     if (dst == null)
/* 685 */       dst = new Point2D[src.length]; 
/* 688 */     for (int i = 0; i < src.length; i++) {
/* 689 */       double x = src[i].x();
/* 690 */       double y = src[i].y();
/* 691 */       dst[i] = new Point2D(
/* 692 */           x * this.m00 + y * this.m01 + this.m02, 
/* 693 */           x * this.m10 + y * this.m11 + this.m12);
/*     */     } 
/* 695 */     return dst;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 702 */     if (this == obj)
/* 703 */       return true; 
/* 705 */     if (!(obj instanceof AffineTransform2D))
/* 706 */       return false; 
/* 708 */     double[] tab1 = coefficients();
/* 709 */     double[] tab2 = ((AffineTransform2D)obj).coefficients();
/* 711 */     for (int i = 0; i < 6; i++) {
/* 712 */       if (Math.abs(tab1[i] - tab2[i]) > eps)
/* 713 */         return false; 
/*     */     } 
/* 715 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 726 */     return new String("AffineTransform2D(" + this.m00 + "," + this.m01 + "," + this.m02 + 
/* 727 */         "," + this.m10 + "," + this.m11 + "," + this.m12 + ",");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 732 */     if (this == obj)
/* 733 */       return true; 
/* 735 */     if (!(obj instanceof AffineTransform2D))
/* 736 */       return false; 
/* 738 */     AffineTransform2D that = (AffineTransform2D)obj;
/* 740 */     if (!EqualUtils.areEqual(this.m00, that.m00))
/* 741 */       return false; 
/* 742 */     if (!EqualUtils.areEqual(this.m01, that.m01))
/* 743 */       return false; 
/* 744 */     if (!EqualUtils.areEqual(this.m02, that.m02))
/* 745 */       return false; 
/* 746 */     if (!EqualUtils.areEqual(this.m00, that.m00))
/* 747 */       return false; 
/* 748 */     if (!EqualUtils.areEqual(this.m01, that.m01))
/* 749 */       return false; 
/* 750 */     if (!EqualUtils.areEqual(this.m02, that.m02))
/* 751 */       return false; 
/* 753 */     return true;
/*     */   }
/*     */   
/*     */   public AffineTransform2D clone() {
/* 758 */     return new AffineTransform2D(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\AffineTransform2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */