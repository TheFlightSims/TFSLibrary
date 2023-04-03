/*     */ package math.geom2d.conic;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.ContourArray2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class Hyperbola2D extends ContourArray2D<HyperbolaBranch2D> implements Conic2D, Cloneable {
/*     */   public static Hyperbola2D create(Point2D center, double a, double b, double theta) {
/*  54 */     return new Hyperbola2D(center.x(), center.y(), a, b, theta, true);
/*     */   }
/*     */   
/*     */   public static Hyperbola2D create(Point2D center, double a, double b, double theta, boolean d) {
/*  59 */     return new Hyperbola2D(center.x(), center.y(), a, b, theta, d);
/*     */   }
/*     */   
/*     */   public static Hyperbola2D reduceCentered(double[] coefs) {
/*  76 */     double theta, r1, r2, A = coefs[0];
/*  77 */     double B = coefs[1];
/*  78 */     double C = coefs[2];
/*  82 */     if (Math.abs(A - C) < 1.0E-12D) {
/*  83 */       theta = 0.7853981633974483D;
/*     */     } else {
/*  85 */       theta = Math.atan2(B, A - C) / 2.0D;
/*  86 */       if (B < 0.0D)
/*  87 */         theta -= Math.PI; 
/*  88 */       theta = Angle2D.formatAngle(theta);
/*     */     } 
/*  92 */     double[] coefs2 = Conics2D.transformCentered(coefs, 
/*  93 */         AffineTransform2D.createRotation(-theta));
/*  96 */     double f = 1.0D;
/*  97 */     if (coefs2.length > 5)
/*  98 */       f = Math.abs(coefs[5]); 
/* 100 */     assert Math.abs(coefs2[1] / f) < 1.0E-12D : 
/* 101 */       "Second conic coefficient should be zero";
/* 103 */     assert coefs2[0] * coefs2[2] < 0.0D : 
/* 104 */       "Transformed conic is not an Hyperbola";
/* 109 */     if (coefs2[0] > 0.0D) {
/* 111 */       r1 = Math.sqrt(f / coefs2[0]);
/* 112 */       r2 = Math.sqrt(-f / coefs2[2]);
/*     */     } else {
/* 115 */       r1 = Math.sqrt(f / coefs2[2]);
/* 116 */       r2 = Math.sqrt(-f / coefs2[0]);
/* 117 */       theta = Angle2D.formatAngle(theta + 1.5707963267948966D);
/* 118 */       theta = Math.min(theta, Angle2D.formatAngle(theta + Math.PI));
/*     */     } 
/* 122 */     return new Hyperbola2D(0.0D, 0.0D, r1, r2, theta, true);
/*     */   }
/*     */   
/*     */   public static Hyperbola2D transformCentered(Hyperbola2D hyper, AffineTransform2D trans) {
/* 136 */     double a = hyper.a;
/* 137 */     double b = hyper.b;
/* 138 */     double theta = hyper.theta;
/* 141 */     double aSq = a * a;
/* 142 */     double bSq = b * b;
/* 143 */     double cot = Math.cos(theta);
/* 144 */     double sit = Math.sin(theta);
/* 145 */     double cotSq = cot * cot;
/* 146 */     double sitSq = sit * sit;
/* 149 */     double A = cotSq / aSq - sitSq / bSq;
/* 150 */     double B = 2.0D * cot * sit * (1.0D / aSq + 1.0D / bSq);
/* 151 */     double C = sitSq / aSq - cotSq / bSq;
/* 152 */     double[] coefs = { A, B, C };
/* 155 */     double[] coefs2 = Conics2D.transformCentered(coefs, trans);
/* 158 */     return reduceCentered(coefs2);
/*     */   }
/*     */   
/* 166 */   protected double xc = 0.0D;
/*     */   
/* 167 */   protected double yc = 0.0D;
/*     */   
/* 170 */   protected double a = 1.0D;
/*     */   
/* 173 */   protected double b = 1.0D;
/*     */   
/* 176 */   protected double theta = 0.0D;
/*     */   
/*     */   protected boolean direct = true;
/*     */   
/* 182 */   protected HyperbolaBranch2D branch1 = null;
/*     */   
/* 185 */   protected HyperbolaBranch2D branch2 = null;
/*     */   
/*     */   public Hyperbola2D() {
/* 195 */     this(0.0D, 0.0D, 1.0D, 1.0D, 0.0D, true);
/*     */   }
/*     */   
/*     */   public Hyperbola2D(Point2D center, double a, double b, double theta) {
/* 199 */     this(center.x(), center.y(), a, b, theta, true);
/*     */   }
/*     */   
/*     */   public Hyperbola2D(Point2D center, double a, double b, double theta, boolean d) {
/* 204 */     this(center.x(), center.y(), a, b, theta, d);
/*     */   }
/*     */   
/*     */   public Hyperbola2D(double xc, double yc, double a, double b, double theta) {
/* 208 */     this(xc, yc, a, b, theta, true);
/*     */   }
/*     */   
/*     */   public Hyperbola2D(double xc, double yc, double a, double b, double theta, boolean d) {
/* 214 */     this.xc = xc;
/* 215 */     this.yc = yc;
/* 216 */     this.a = a;
/* 217 */     this.b = b;
/* 218 */     this.theta = theta;
/* 219 */     this.direct = d;
/* 221 */     this.branch1 = new HyperbolaBranch2D(this, false);
/* 222 */     this.branch2 = new HyperbolaBranch2D(this, true);
/* 223 */     add((Curve2D)this.branch1);
/* 224 */     add((Curve2D)this.branch2);
/*     */   }
/*     */   
/*     */   public Point2D toGlobal(Point2D point) {
/* 236 */     point = point.transform(AffineTransform2D.createScaling(this.a, this.b));
/* 237 */     point = point.transform(AffineTransform2D.createRotation(this.theta));
/* 238 */     point = point.transform(AffineTransform2D.createTranslation(this.xc, this.yc));
/* 239 */     return point;
/*     */   }
/*     */   
/*     */   public Point2D toLocal(Point2D point) {
/* 243 */     point = point.transform(AffineTransform2D.createTranslation(-this.xc, -this.yc));
/* 244 */     point = point.transform(AffineTransform2D.createRotation(-this.theta));
/* 245 */     point = point.transform(AffineTransform2D.createScaling(1.0D / this.a, 1.0D / this.b));
/* 246 */     return point;
/*     */   }
/*     */   
/*     */   private LinearShape2D formatLine(LinearShape2D line) {
/* 257 */     line = line.transform(AffineTransform2D.createTranslation(-this.xc, -this.yc));
/* 258 */     line = line.transform(AffineTransform2D.createRotation(-this.theta));
/* 259 */     line = line.transform(AffineTransform2D.createScaling(1.0D / this.a, 1.0D / this.b));
/* 260 */     return line;
/*     */   }
/*     */   
/*     */   public Point2D getCenter() {
/* 269 */     return new Point2D(this.xc, this.yc);
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 277 */     return this.theta;
/*     */   }
/*     */   
/*     */   public double getLength1() {
/* 282 */     return this.a;
/*     */   }
/*     */   
/*     */   public double getLength2() {
/* 287 */     return this.b;
/*     */   }
/*     */   
/*     */   public boolean isDirect() {
/* 291 */     return this.direct;
/*     */   }
/*     */   
/*     */   public Vector2D getVector1() {
/* 295 */     return new Vector2D(Math.cos(this.theta), Math.sin(this.theta));
/*     */   }
/*     */   
/*     */   public Vector2D getVector2() {
/* 299 */     return new Vector2D(-Math.sin(this.theta), Math.cos(this.theta));
/*     */   }
/*     */   
/*     */   public Point2D getFocus1() {
/* 307 */     double c = Math.hypot(this.a, this.b);
/* 308 */     return new Point2D(this.xc + c * Math.cos(this.theta), this.yc + c * Math.sin(this.theta));
/*     */   }
/*     */   
/*     */   public Point2D getFocus2() {
/* 316 */     double c = Math.hypot(this.a, this.b);
/* 317 */     return new Point2D(this.xc - c * Math.cos(this.theta), this.yc - c * Math.sin(this.theta));
/*     */   }
/*     */   
/*     */   public HyperbolaBranch2D positiveBranch() {
/* 321 */     return this.branch2;
/*     */   }
/*     */   
/*     */   public HyperbolaBranch2D negativeBranch() {
/* 325 */     return this.branch1;
/*     */   }
/*     */   
/*     */   public Collection<HyperbolaBranch2D> branches() {
/* 329 */     ArrayList<HyperbolaBranch2D> array = 
/* 330 */       new ArrayList<HyperbolaBranch2D>(2);
/* 331 */     array.add(this.branch1);
/* 332 */     array.add(this.branch2);
/* 333 */     return array;
/*     */   }
/*     */   
/*     */   public Collection<StraightLine2D> asymptotes() {
/* 341 */     Vector2D v1 = new Vector2D(this.a, this.b);
/* 342 */     Vector2D v2 = new Vector2D(this.a, -this.b);
/* 345 */     AffineTransform2D rot = AffineTransform2D.createRotation(this.theta);
/* 346 */     v1 = v1.transform(rot);
/* 347 */     v2 = v2.transform(rot);
/* 350 */     ArrayList<StraightLine2D> array = new ArrayList<StraightLine2D>(2);
/* 353 */     Point2D center = getCenter();
/* 354 */     array.add(new StraightLine2D(center, v1));
/* 355 */     array.add(new StraightLine2D(center, v2));
/* 358 */     return array;
/*     */   }
/*     */   
/*     */   public double[] conicCoefficients() {
/* 366 */     double aSq = this.a * this.a;
/* 367 */     double bSq = this.b * this.b;
/* 368 */     double aSqInv = 1.0D / aSq;
/* 369 */     double bSqInv = 1.0D / bSq;
/* 372 */     double sint = Math.sin(this.theta);
/* 373 */     double cost = Math.cos(this.theta);
/* 374 */     double sin2t = 2.0D * sint * cost;
/* 375 */     double sintSq = sint * sint;
/* 376 */     double costSq = cost * cost;
/* 379 */     double xcSq = this.xc * this.xc;
/* 380 */     double ycSq = this.yc * this.yc;
/* 387 */     double a = costSq / aSq - sintSq / bSq;
/* 388 */     double b = (bSq + aSq) * sin2t / aSq * bSq;
/* 389 */     double c = sintSq / aSq - costSq / bSq;
/* 390 */     double d = -this.yc * b - 2.0D * this.xc * a;
/* 391 */     double e = -this.xc * b - 2.0D * this.yc * c;
/* 392 */     double f = -1.0D + (xcSq + ycSq) * (aSqInv - bSqInv) / 2.0D + (
/* 393 */       costSq - sintSq) * (xcSq - ycSq) * (aSqInv + bSqInv) / 2.0D + 
/* 394 */       this.xc * this.yc * (aSqInv + bSqInv) * sin2t;
/* 400 */     return new double[] { a, b, c, d, e, f };
/*     */   }
/*     */   
/*     */   public Conic2D.Type conicType() {
/* 404 */     return Conic2D.Type.HYPERBOLA;
/*     */   }
/*     */   
/*     */   public double eccentricity() {
/* 408 */     return Math.hypot(1.0D, this.b * this.b / this.a / this.a);
/*     */   }
/*     */   
/*     */   public Hyperbola2D reverse() {
/* 417 */     return new Hyperbola2D(this.xc, this.yc, this.a, this.b, this.theta, 
/* 418 */         !this.direct);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 424 */     Collection<Point2D> points = new ArrayList<Point2D>();
/* 427 */     LinearShape2D line2 = formatLine(line);
/* 430 */     Point2D origin = line2.origin();
/* 431 */     double dx = line2.direction().x();
/* 432 */     double dy = line2.direction().y();
/* 437 */     if (Math.abs(dx) > Math.abs(dy)) {
/* 441 */       double k = dy / dx;
/* 442 */       double yi = origin.y() - k * origin.x();
/* 445 */       double a = 1.0D - k * k;
/* 446 */       double b = -2.0D * k * yi;
/* 447 */       double c = -yi * yi - 1.0D;
/* 449 */       double delta = b * b - 4.0D * a * c;
/* 450 */       if (delta <= 0.0D) {
/* 451 */         System.out.println("Intersection with horizontal line should alays give positive delta");
/* 452 */         return points;
/*     */       } 
/* 456 */       double x1 = (-b - Math.sqrt(delta)) / 2.0D * a;
/* 457 */       double x2 = (-b + Math.sqrt(delta)) / 2.0D * a;
/* 460 */       StraightLine2D support = line2.supportingLine();
/* 463 */       double pos1 = support.project(new Point2D(x1, k * x1 + yi));
/* 464 */       if (line2.contains(support.point(pos1)))
/* 465 */         points.add(line.point(pos1)); 
/* 468 */       double pos2 = support.project(new Point2D(x2, k * x2 + yi));
/* 469 */       if (line2.contains(support.point(pos2)))
/* 470 */         points.add(line.point(pos2)); 
/*     */     } else {
/* 476 */       double k = dx / dy;
/* 477 */       double xi = origin.x() - k * origin.y();
/* 480 */       double a = k * k - 1.0D;
/* 481 */       double b = 2.0D * k * xi;
/* 482 */       double c = xi * xi - 1.0D;
/* 484 */       double delta = b * b - 4.0D * a * c;
/* 485 */       if (delta <= 0.0D)
/* 487 */         return points; 
/* 491 */       double y1 = (-b - Math.sqrt(delta)) / 2.0D * a;
/* 492 */       double y2 = (-b + Math.sqrt(delta)) / 2.0D * a;
/* 495 */       StraightLine2D support = line2.supportingLine();
/* 498 */       double pos1 = support.project(new Point2D(k * y1 + xi, y1));
/* 499 */       if (line2.contains(support.point(pos1)))
/* 500 */         points.add(line.point(pos1)); 
/* 503 */       double pos2 = support.project(new Point2D(k * y2 + xi, y2));
/* 504 */       if (line2.contains(support.point(pos2)))
/* 505 */         points.add(line.point(pos2)); 
/*     */     } 
/* 508 */     return points;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 516 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 521 */     Point2D point = toLocal(new Point2D(x, y));
/* 522 */     double xa = point.x() / this.a;
/* 523 */     double yb = point.y() / this.b;
/* 524 */     double res = xa * xa - yb * yb - 1.0D;
/* 525 */     return (Math.abs(res) < 1.0E-6D);
/*     */   }
/*     */   
/*     */   public Hyperbola2D transform(AffineTransform2D trans) {
/* 533 */     Hyperbola2D result = transformCentered(this, trans);
/* 534 */     Point2D center = getCenter().transform(trans);
/* 535 */     result.xc = center.x();
/* 536 */     result.yc = center.y();
/* 538 */     this.direct ^= trans.isDirect() ? 0 : 1;
/* 539 */     return result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 545 */     return Box2D.INFINITE_BOX;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g) {
/* 551 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 561 */     if (this == obj)
/* 562 */       return true; 
/* 564 */     if (!(obj instanceof Hyperbola2D))
/* 565 */       return false; 
/* 568 */     Hyperbola2D that = (Hyperbola2D)obj;
/* 571 */     if (Math.abs(that.xc - this.xc) > eps)
/* 572 */       return false; 
/* 573 */     if (Math.abs(that.yc - this.yc) > eps)
/* 574 */       return false; 
/* 575 */     if (Math.abs(that.a - this.a) > eps)
/* 576 */       return false; 
/* 577 */     if (Math.abs(that.b - this.b) > eps)
/* 578 */       return false; 
/* 579 */     if (Math.abs(that.theta - this.theta) > eps)
/* 580 */       return false; 
/* 581 */     if (this.direct != that.direct)
/* 582 */       return false; 
/* 585 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 596 */     if (!(obj instanceof Hyperbola2D))
/* 597 */       return false; 
/* 600 */     Hyperbola2D that = (Hyperbola2D)obj;
/* 603 */     if (!EqualUtils.areEqual(this.xc, that.xc))
/* 604 */       return false; 
/* 605 */     if (!EqualUtils.areEqual(this.yc, that.yc))
/* 606 */       return false; 
/* 607 */     if (!EqualUtils.areEqual(this.a, that.a))
/* 608 */       return false; 
/* 609 */     if (!EqualUtils.areEqual(this.b, that.b))
/* 610 */       return false; 
/* 611 */     if (!EqualUtils.areEqual(this.theta, that.theta))
/* 612 */       return false; 
/* 613 */     if (this.direct != that.direct)
/* 614 */       return false; 
/* 617 */     return true;
/*     */   }
/*     */   
/*     */   public Hyperbola2D clone() {
/* 622 */     return new Hyperbola2D(this.xc, this.yc, this.a, this.b, this.theta, this.direct);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\Hyperbola2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */