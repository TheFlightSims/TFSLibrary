/*      */ package math.geom2d.conic;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import math.geom2d.AffineTransform2D;
/*      */ import math.geom2d.Angle2D;
/*      */ import math.geom2d.Box2D;
/*      */ import math.geom2d.GeometricObject2D;
/*      */ import math.geom2d.Point2D;
/*      */ import math.geom2d.Shape2D;
/*      */ import math.geom2d.Vector2D;
/*      */ import math.geom2d.curve.AbstractSmoothCurve2D;
/*      */ import math.geom2d.curve.ContinuousCurve2D;
/*      */ import math.geom2d.curve.Curve2D;
/*      */ import math.geom2d.curve.CurveArray2D;
/*      */ import math.geom2d.curve.CurveSet2D;
/*      */ import math.geom2d.curve.Curves2D;
/*      */ import math.geom2d.curve.SmoothCurve2D;
/*      */ import math.geom2d.domain.Boundary2D;
/*      */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*      */ import math.geom2d.domain.Contour2D;
/*      */ import math.geom2d.domain.Domain2D;
/*      */ import math.geom2d.domain.GenericDomain2D;
/*      */ import math.geom2d.domain.OrientedCurve2D;
/*      */ import math.geom2d.domain.SmoothContour2D;
/*      */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*      */ import math.geom2d.line.LinearShape2D;
/*      */ import math.geom2d.polygon.LinearCurve2D;
/*      */ import math.geom2d.polygon.LinearRing2D;
/*      */ import math.utils.EqualUtils;
/*      */ 
/*      */ public class Ellipse2D extends AbstractSmoothCurve2D implements EllipseShape2D, Cloneable {
/*      */   protected double xc;
/*      */   
/*      */   protected double yc;
/*      */   
/*      */   protected double r1;
/*      */   
/*      */   protected double r2;
/*      */   
/*      */   public static Ellipse2D create(Point2D focus1, Point2D focus2, double chord) {
/*   69 */     double x1 = focus1.x();
/*   70 */     double y1 = focus1.y();
/*   71 */     double x2 = focus2.x();
/*   72 */     double y2 = focus2.y();
/*   74 */     double xc = (x1 + x2) / 2.0D;
/*   75 */     double yc = (y1 + y2) / 2.0D;
/*   76 */     double theta = Angle2D.horizontalAngle(x1, y1, x2, y2);
/*   78 */     double dist = focus1.distance(focus2);
/*   82 */     double r1 = chord / 2.0D;
/*   83 */     double r2 = Math.sqrt(chord * chord - dist * dist) / 2.0D;
/*   85 */     return new Ellipse2D(xc, yc, r1, r2, theta);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static Ellipse2D create(Point2D center, double l1, double l2) {
/*   93 */     return new Ellipse2D(center.x(), center.y(), l1, l2, 0.0D, true);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static Ellipse2D create(Point2D center, double l1, double l2, double theta) {
/*  104 */     return new Ellipse2D(center.x(), center.y(), l1, l2, theta, true);
/*      */   }
/*      */   
/*      */   public static Ellipse2D create(Point2D center, double l1, double l2, double theta, boolean direct) {
/*  113 */     return new Ellipse2D(center.x(), center.y(), l1, l2, theta, direct);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static Ellipse2D create(java.awt.geom.Ellipse2D ellipse) {
/*  122 */     return new Ellipse2D(
/*  123 */         new Point2D(ellipse.getCenterX(), ellipse.getCenterY()), 
/*  124 */         ellipse.getWidth() / 2.0D, ellipse.getHeight() / 2.0D);
/*      */   }
/*      */   
/*      */   public static Ellipse2D reduceCentered(double[] coefs) {
/*  140 */     double theta, r1, r2, A = coefs[0];
/*  141 */     double B = coefs[1];
/*  142 */     double C = coefs[2];
/*  146 */     if (Math.abs(A - C) < 1.0E-12D) {
/*  147 */       theta = 0.7853981633974483D;
/*      */     } else {
/*  149 */       theta = Math.atan2(B, A - C) / 2.0D;
/*  150 */       if (B < 0.0D)
/*  151 */         theta -= Math.PI; 
/*  152 */       theta = Angle2D.formatAngle(theta);
/*      */     } 
/*  156 */     double[] coefs2 = Conics2D.transformCentered(coefs, 
/*  157 */         AffineTransform2D.createRotation(-theta));
/*  160 */     double f = 1.0D;
/*  161 */     if (coefs2.length > 5)
/*  162 */       f = Math.abs(coefs[5]); 
/*  164 */     assert Math.abs(coefs2[1] / f) < 1.0E-12D : 
/*  165 */       "Second conic coefficient should be zero";
/*  169 */     if (coefs2[0] < coefs2[2]) {
/*  170 */       r1 = Math.sqrt(f / coefs2[0]);
/*  171 */       r2 = Math.sqrt(f / coefs2[2]);
/*      */     } else {
/*  173 */       r1 = Math.sqrt(f / coefs2[2]);
/*  174 */       r2 = Math.sqrt(f / coefs2[0]);
/*  175 */       theta = Angle2D.formatAngle(theta + 1.5707963267948966D);
/*  176 */       theta = Math.min(theta, Angle2D.formatAngle(theta + Math.PI));
/*      */     } 
/*  184 */     return new Ellipse2D(0.0D, 0.0D, r1, r2, theta);
/*      */   }
/*      */   
/*      */   public static Ellipse2D transformCentered(Ellipse2D ellipse, AffineTransform2D trans) {
/*  198 */     double r1 = ellipse.r1;
/*  199 */     double r2 = ellipse.r2;
/*  200 */     double theta = ellipse.theta;
/*  203 */     double r1Sq = r1 * r1;
/*  204 */     double r2Sq = r2 * r2;
/*  205 */     double cot = Math.cos(theta);
/*  206 */     double sit = Math.sin(theta);
/*  207 */     double cotSq = cot * cot;
/*  208 */     double sitSq = sit * sit;
/*  211 */     double A = cotSq / r1Sq + sitSq / r2Sq;
/*  212 */     double B = 2.0D * cot * sit * (1.0D / r1Sq - 1.0D / r2Sq);
/*  213 */     double C = cotSq / r2Sq + sitSq / r1Sq;
/*  214 */     double[] coefs = { A, B, C };
/*  217 */     double[] coefs2 = Conics2D.transformCentered(coefs, trans);
/*  220 */     return reduceCentered(coefs2);
/*      */   }
/*      */   
/*  238 */   protected double theta = 0.0D;
/*      */   
/*      */   protected boolean direct = true;
/*      */   
/*      */   public Ellipse2D() {
/*  251 */     this(0.0D, 0.0D, 1.0D, 1.0D, 0.0D, true);
/*      */   }
/*      */   
/*      */   public Ellipse2D(Point2D center, double l1, double l2) {
/*  256 */     this(center.x(), center.y(), l1, l2, 0.0D, true);
/*      */   }
/*      */   
/*      */   public Ellipse2D(double xc, double yc, double l1, double l2) {
/*  261 */     this(xc, yc, l1, l2, 0.0D, true);
/*      */   }
/*      */   
/*      */   public Ellipse2D(Point2D center, double l1, double l2, double theta) {
/*  269 */     this(center.x(), center.y(), l1, l2, theta, true);
/*      */   }
/*      */   
/*      */   public Ellipse2D(double xc, double yc, double l1, double l2, double theta) {
/*  277 */     this(xc, yc, l1, l2, theta, true);
/*      */   }
/*      */   
/*      */   public Ellipse2D(double xc, double yc, double l1, double l2, double theta, boolean direct) {
/*  286 */     this.xc = xc;
/*  287 */     this.yc = yc;
/*  289 */     this.r1 = l1;
/*  290 */     this.r2 = l2;
/*  292 */     this.theta = theta;
/*  293 */     this.direct = direct;
/*      */   }
/*      */   
/*      */   public Ellipse2D(java.awt.geom.Ellipse2D ellipse) {
/*  301 */     this(new Point2D(ellipse.getCenterX(), ellipse.getCenterY()), ellipse.getWidth() / 2.0D, ellipse.getHeight() / 2.0D);
/*      */   }
/*      */   
/*      */   public double getRho(double angle) {
/*  316 */     double cot = Math.cos(angle - this.theta);
/*  317 */     double sit = Math.cos(angle - this.theta);
/*  318 */     return this.r1 * this.r2 / Math.hypot(this.r2 * cot, this.r1 * sit);
/*      */   }
/*      */   
/*      */   public Point2D projectedPoint(Point2D point) {
/*  322 */     Vector2D polar = projectedVector(point, 1.0E-12D);
/*  323 */     return new Point2D(point.x() + polar.x(), point.y() + polar.y());
/*      */   }
/*      */   
/*      */   public Vector2D projectedVector(Point2D point, double eMax) {
/*  341 */     double la, lb, theta, ot = 0.3333333333333333D;
/*  344 */     double x = point.x() - this.xc;
/*  345 */     double y = point.y() - this.yc;
/*  348 */     if (this.r1 >= this.r2) {
/*  349 */       la = this.r1;
/*  350 */       lb = this.r2;
/*  351 */       theta = this.theta;
/*      */     } else {
/*  353 */       la = this.r2;
/*  354 */       lb = this.r1;
/*  355 */       theta = this.theta + 1.5707963267948966D;
/*  356 */       double tmp = x;
/*  357 */       x = -y;
/*  358 */       y = tmp;
/*      */     } 
/*  361 */     double cot = Math.cos(theta);
/*  362 */     double sit = Math.sin(theta);
/*  363 */     double tmpx = x, tmpy = y;
/*  364 */     x = tmpx * cot - tmpy * sit;
/*  365 */     y = tmpx * sit + tmpy * cot;
/*  367 */     double ae = la;
/*  368 */     double f = 1.0D - lb / la;
/*  369 */     double e2 = f * (2.0D - f);
/*  370 */     double g = 1.0D - f;
/*  371 */     double g2 = g * g;
/*  372 */     double ae2 = ae * ae;
/*  375 */     double z = y;
/*  376 */     double z2 = y * y;
/*  377 */     double r = x;
/*  378 */     double r2 = x * x;
/*  379 */     double g2r2ma2 = g2 * (r2 - ae2);
/*  380 */     double g2r2ma2pz2 = g2r2ma2 + z2;
/*  381 */     double dist = Math.sqrt(r2 + z2);
/*  382 */     boolean inside = (g2r2ma2pz2 <= 0.0D);
/*  385 */     if (dist < 1.0E-10D * ae) {
/*  386 */       System.out.println("point at the center");
/*  387 */       return Vector2D.createPolar(r, 0.0D);
/*      */     } 
/*  390 */     double cz = r / dist;
/*  391 */     double sz = z / dist;
/*  392 */     double t = z / (dist + r);
/*  397 */     double a = 1.0D - e2 * cz * cz;
/*  398 */     double b = g2 * r * cz + z * sz;
/*  399 */     double c = g2r2ma2pz2;
/*  400 */     double b2 = b * b;
/*  401 */     double ac = a * c;
/*  402 */     double k = c / (b + Math.sqrt(b2 - ac));
/*  404 */     double phi = Math.atan2(z - k * sz, g2 * (r - k * cz));
/*  407 */     if (Math.abs(k) < 1.0E-10D * dist)
/*  409 */       return Vector2D.createPolar(k, phi); 
/*  412 */     for (int iterations = 0; iterations < 100; iterations++) {
/*      */       double tildePhi;
/*  418 */       a = g2r2ma2pz2 + g2 * (2.0D * r + k) * k;
/*  419 */       b = -4.0D * k * z / a;
/*  420 */       c = 2.0D * (g2r2ma2pz2 + (1.0D + e2) * k * k) / a;
/*  421 */       double d = b;
/*  426 */       b += t;
/*  427 */       c += t * b;
/*  428 */       d += t * c;
/*  431 */       b2 = b * b;
/*  432 */       double Q = (3.0D * c - b2) / 9.0D;
/*  433 */       double R = (b * (9.0D * c - 2.0D * b2) - 27.0D * d) / 54.0D;
/*  434 */       double D = Q * Q * Q + R * R;
/*  436 */       if (D >= 0.0D) {
/*  437 */         double rootD = Math.sqrt(D);
/*  438 */         double rMr = R - rootD;
/*  439 */         double rPr = R + rootD;
/*  440 */         double tildeT = ((rPr > 0.0D) ? Math.pow(rPr, ot) : -Math.pow(-rPr, ot)) + (
/*  441 */           (rMr > 0.0D) ? Math.pow(rMr, ot) : -Math.pow(-rMr, ot)) - 
/*  442 */           b * ot;
/*  443 */         double tildeT2 = tildeT * tildeT;
/*  444 */         double tildeT2P1 = 1.0D + tildeT2;
/*  445 */         tildePhi = Math.atan2(z * tildeT2P1 - 2.0D * k * tildeT, 
/*  446 */             g2 * (r * tildeT2P1 - k * (1.0D - tildeT2)));
/*      */       } else {
/*  448 */         Q = -Q;
/*  449 */         double qRoot = Math.sqrt(Q);
/*  450 */         double alpha = Math.acos(R / Q * qRoot);
/*  451 */         double tildeT = 2.0D * qRoot * Math.cos(alpha * ot) - b * ot;
/*  452 */         double tildeT2 = tildeT * tildeT;
/*  453 */         double tildeT2P1 = 1.0D + tildeT2;
/*  454 */         tildePhi = Math.atan2(z * tildeT2P1 - 2.0D * k * tildeT, 
/*  455 */             g2 * (r * tildeT2P1 - k * (1.0D - tildeT2)));
/*  456 */         if (tildePhi * phi < 0.0D) {
/*  457 */           tildeT = 2.0D * qRoot * Math.cos((alpha + 6.283185307179586D) * ot) - b * ot;
/*  458 */           tildeT2 = tildeT * tildeT;
/*  459 */           tildeT2P1 = 1.0D + tildeT2;
/*  460 */           tildePhi = Math.atan2(z * tildeT2P1 - 2.0D * k * tildeT, g2 * (
/*  461 */               r * tildeT2P1 - k * (1.0D - tildeT2)));
/*  462 */           if (tildePhi * phi < 0.0D) {
/*  463 */             tildeT = 2.0D * qRoot * Math.cos((alpha + 12.566370614359172D) * ot) - b * ot;
/*  464 */             tildeT2 = tildeT * tildeT;
/*  465 */             tildeT2P1 = 1.0D + tildeT2;
/*  466 */             tildePhi = Math.atan2(z * tildeT2P1 - 2.0D * k * tildeT, g2 * (
/*  467 */                 r * tildeT2P1 - k * (1.0D - tildeT2)));
/*      */           } 
/*      */         } 
/*      */       } 
/*  473 */       double dPhi = Math.abs(0.5D * (tildePhi - phi));
/*  474 */       phi = 0.5D * (phi + tildePhi);
/*  475 */       double cPhi = Math.cos(phi);
/*  476 */       double sPhi = Math.sin(phi);
/*  477 */       double coeff = Math.sqrt(1.0D - e2 * sPhi * sPhi);
/*  483 */       b = ae / coeff;
/*  484 */       double dR = r - cPhi * b;
/*  485 */       double dZ = z - sPhi * b * g2;
/*  486 */       k = Math.hypot(dR, dZ);
/*  487 */       if (inside)
/*  488 */         k = -k; 
/*  490 */       t = dZ / (k + dR);
/*  492 */       if (dPhi < 1.0E-14D) {
/*  493 */         if (this.r1 >= this.r2)
/*  494 */           return Vector2D.createPolar(-k, phi + theta); 
/*  496 */         return Vector2D.createPolar(-k, phi + theta - 1.5707963267948966D);
/*      */       } 
/*      */     } 
/*  500 */     System.out.println("Ellipse.getProjectedVector() did not converge");
/*  501 */     return Vector2D.createPolar(k, phi);
/*      */   }
/*      */   
/*      */   public Ellipse2D parallel(double d) {
/*  510 */     return new Ellipse2D(this.xc, this.yc, Math.abs(this.r1 + d), Math.abs(this.r2 + d), this.theta, this.direct);
/*      */   }
/*      */   
/*      */   public boolean isDirect() {
/*  521 */     return this.direct;
/*      */   }
/*      */   
/*      */   public boolean isCircle() {
/*  529 */     return (Math.abs(this.r1 - this.r2) < 1.0E-12D);
/*      */   }
/*      */   
/*      */   public double semiMajorAxisLength() {
/*  536 */     return this.r1;
/*      */   }
/*      */   
/*      */   public double semiMinorAxisLength() {
/*  543 */     return this.r2;
/*      */   }
/*      */   
/*      */   public Point2D center() {
/*  550 */     return new Point2D(this.xc, this.yc);
/*      */   }
/*      */   
/*      */   public Point2D focus1() {
/*      */     double a;
/*      */     double b;
/*      */     double theta;
/*  559 */     if (this.r1 > this.r2) {
/*  560 */       a = this.r1;
/*  561 */       b = this.r2;
/*  562 */       theta = this.theta;
/*      */     } else {
/*  564 */       a = this.r2;
/*  565 */       b = this.r1;
/*  566 */       theta = this.theta + 1.5707963267948966D;
/*      */     } 
/*  568 */     return Point2D.createPolar(this.xc, this.yc, Math.sqrt(a * a - b * b), theta + Math.PI);
/*      */   }
/*      */   
/*      */   public Point2D focus2() {
/*      */     double a;
/*      */     double b;
/*      */     double theta;
/*  577 */     if (this.r1 > this.r2) {
/*  578 */       a = this.r1;
/*  579 */       b = this.r2;
/*  580 */       theta = this.theta;
/*      */     } else {
/*  582 */       a = this.r2;
/*  583 */       b = this.r1;
/*  584 */       theta = this.theta + 1.5707963267948966D;
/*      */     } 
/*  586 */     return Point2D.createPolar(this.xc, this.yc, Math.sqrt(a * a - b * b), theta);
/*      */   }
/*      */   
/*      */   public Vector2D vector1() {
/*  594 */     return new Vector2D(Math.cos(this.theta), Math.sin(this.theta));
/*      */   }
/*      */   
/*      */   public Vector2D vector2() {
/*  602 */     if (this.direct)
/*  603 */       return new Vector2D(-Math.sin(this.theta), Math.cos(this.theta)); 
/*  605 */     return new Vector2D(Math.sin(this.theta), -Math.cos(this.theta));
/*      */   }
/*      */   
/*      */   public double angle() {
/*  612 */     return this.theta;
/*      */   }
/*      */   
/*      */   public Conic2D.Type conicType() {
/*  619 */     if (Math.abs(this.r1 - this.r2) < 1.0E-12D)
/*  620 */       return Conic2D.Type.CIRCLE; 
/*  621 */     return Conic2D.Type.ELLIPSE;
/*      */   }
/*      */   
/*      */   public double[] conicCoefficients() {
/*  631 */     double r1Sq = this.r1 * this.r1;
/*  632 */     double r2Sq = this.r2 * this.r2;
/*  635 */     double sint = Math.sin(this.theta);
/*  636 */     double cost = Math.cos(this.theta);
/*  637 */     double sin2t = 2.0D * sint * cost;
/*  638 */     double sintSq = sint * sint;
/*  639 */     double costSq = cost * cost;
/*  642 */     double xcSq = this.xc * this.xc;
/*  643 */     double ycSq = this.yc * this.yc;
/*  644 */     double r1SqInv = 1.0D / r1Sq;
/*  645 */     double r2SqInv = 1.0D / r2Sq;
/*  652 */     double a = costSq / r1Sq + sintSq / r2Sq;
/*  653 */     double b = (r2Sq - r1Sq) * sin2t / r1Sq * r2Sq;
/*  654 */     double c = costSq / r2Sq + sintSq / r1Sq;
/*  655 */     double d = -this.yc * b - 2.0D * this.xc * a;
/*  656 */     double e = -this.xc * b - 2.0D * this.yc * c;
/*  657 */     double f = -1.0D + (xcSq + ycSq) * (r1SqInv + r2SqInv) / 2.0D + (
/*  658 */       costSq - sintSq) * (xcSq - ycSq) * (r1SqInv - r2SqInv) / 2.0D + 
/*  659 */       this.xc * this.yc * (r1SqInv - r2SqInv) * sin2t;
/*  662 */     return new double[] { a, b, c, d, e, f };
/*      */   }
/*      */   
/*      */   public double eccentricity() {
/*  671 */     double a = Math.max(this.r1, this.r2);
/*  672 */     double b = Math.min(this.r1, this.r2);
/*  673 */     double r = b / a;
/*  674 */     return Math.sqrt(1.0D - r * r);
/*      */   }
/*      */   
/*      */   public Domain2D domain() {
/*  682 */     return (Domain2D)new GenericDomain2D(this);
/*      */   }
/*      */   
/*      */   public void fill(Graphics2D g2) {
/*  687 */     java.awt.geom.Ellipse2D.Double ellipse = new java.awt.geom.Ellipse2D.Double(
/*  688 */         this.xc - this.r1, this.yc - this.r2, 2.0D * this.r1, 2.0D * this.r2);
/*  691 */     AffineTransform trans = 
/*  692 */       AffineTransform.getRotateInstance(this.theta, this.xc, this.yc);
/*  693 */     Shape shape = trans.createTransformedShape(ellipse);
/*  696 */     g2.fill(shape);
/*      */   }
/*      */   
/*      */   public double windingAngle(Point2D point) {
/*  707 */     if (signedDistance(point) > 0.0D)
/*  708 */       return 0.0D; 
/*  710 */     return this.direct ? 6.283185307179586D : -6.283185307179586D;
/*      */   }
/*      */   
/*      */   public boolean isInside(Point2D point) {
/*  719 */     AffineTransform2D rot = AffineTransform2D.createRotation(this.xc, 
/*  720 */         this.yc, -this.theta);
/*  721 */     Point2D pt = rot.transform(point);
/*  722 */     double xp = (pt.x() - this.xc) / this.r1;
/*  723 */     double yp = (pt.y() - this.yc) / this.r2;
/*  724 */     return ((xp * xp + yp * yp < 1.0D)) ^ (!this.direct);
/*      */   }
/*      */   
/*      */   public double signedDistance(Point2D point) {
/*  732 */     double dist = asPolyline(180).distance(point);
/*  733 */     return isInside(point) ? -dist : dist;
/*      */   }
/*      */   
/*      */   public double signedDistance(double x, double y) {
/*  737 */     return signedDistance(new Point2D(x, y));
/*      */   }
/*      */   
/*      */   public Vector2D tangent(double t) {
/*  744 */     if (!this.direct)
/*  745 */       t = -t; 
/*  746 */     double cot = Math.cos(this.theta);
/*  747 */     double sit = Math.sin(this.theta);
/*  749 */     if (this.direct)
/*  750 */       return new Vector2D(
/*  751 */           -this.r1 * Math.sin(t) * cot - this.r2 * Math.cos(t) * sit, 
/*  752 */           -this.r1 * Math.sin(t) * sit + this.r2 * Math.cos(t) * cot); 
/*  754 */     return new Vector2D(
/*  755 */         this.r1 * Math.sin(t) * cot + this.r2 * Math.cos(t) * sit, 
/*  756 */         this.r1 * Math.sin(t) * sit - this.r2 * Math.cos(t) * cot);
/*      */   }
/*      */   
/*      */   public double curvature(double t) {
/*  763 */     if (!this.direct)
/*  764 */       t = -t; 
/*  765 */     double cot = Math.cos(t);
/*  766 */     double sit = Math.sin(t);
/*  767 */     double k = this.r1 * this.r2 / Math.pow(Math.hypot(this.r2 * cot, this.r1 * sit), 3.0D);
/*  768 */     return this.direct ? k : -k;
/*      */   }
/*      */   
/*      */   public boolean isClosed() {
/*  778 */     return true;
/*      */   }
/*      */   
/*      */   public LinearRing2D asPolyline(int n) {
/*  785 */     return asPolylineClosed(n);
/*      */   }
/*      */   
/*      */   public boolean isBounded() {
/*  791 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  796 */     return false;
/*      */   }
/*      */   
/*      */   public double t0() {
/*  803 */     return 0.0D;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public double getT0() {
/*  811 */     return t0();
/*      */   }
/*      */   
/*      */   public double t1() {
/*  818 */     return 6.283185307179586D;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public double getT1() {
/*  826 */     return t1();
/*      */   }
/*      */   
/*      */   public Point2D point(double t) {
/*  835 */     if (!this.direct)
/*  836 */       t = -t; 
/*  837 */     double cot = Math.cos(this.theta);
/*  838 */     double sit = Math.sin(this.theta);
/*  839 */     return new Point2D(
/*  840 */         this.xc + this.r1 * Math.cos(t) * cot - this.r2 * Math.sin(t) * sit, 
/*  841 */         this.yc + this.r1 * Math.cos(t) * sit + this.r2 * Math.sin(t) * cot);
/*      */   }
/*      */   
/*      */   public Point2D firstPoint() {
/*  852 */     return new Point2D(this.xc + this.r1 * Math.cos(this.theta), this.yc + this.r1 * Math.sin(this.theta));
/*      */   }
/*      */   
/*      */   public Point2D lastPoint() {
/*  863 */     return new Point2D(this.xc + this.r1 * Math.cos(this.theta), this.yc + this.r1 * Math.sin(this.theta));
/*      */   }
/*      */   
/*      */   private Point2D toUnitCircle(Point2D point) {
/*  874 */     double xp = point.x();
/*  875 */     double yp = point.y();
/*  878 */     xp -= this.xc;
/*  879 */     yp -= this.yc;
/*  882 */     double cot = Math.cos(this.theta);
/*  883 */     double sit = Math.sin(this.theta);
/*  884 */     double xp1 = xp * cot + yp * sit;
/*  885 */     double yp1 = -xp * sit + yp * cot;
/*  886 */     xp = xp1;
/*  887 */     yp = yp1;
/*  890 */     xp /= this.r1;
/*  891 */     yp /= this.r2;
/*  894 */     if (!this.direct)
/*  895 */       yp = -yp; 
/*  897 */     return new Point2D(xp, yp);
/*      */   }
/*      */   
/*      */   public double position(Point2D point) {
/*  901 */     Point2D p2 = toUnitCircle(point);
/*  902 */     double xp = p2.x();
/*  903 */     double yp = p2.y();
/*  906 */     double angle = Angle2D.horizontalAngle(xp, yp);
/*  908 */     if (Math.abs(Math.hypot(xp, yp) - 1.0D) < 1.0E-12D)
/*  909 */       return angle; 
/*  911 */     return Double.NaN;
/*      */   }
/*      */   
/*      */   public double project(Point2D point) {
/*  920 */     Point2D p2 = toUnitCircle(point);
/*  921 */     double xp = p2.x();
/*  922 */     double yp = p2.y();
/*  925 */     double angle = Angle2D.horizontalAngle(xp, yp);
/*  927 */     return angle;
/*      */   }
/*      */   
/*      */   public Ellipse2D reverse() {
/*  935 */     return new Ellipse2D(this.xc, this.yc, this.r1, this.r2, this.theta, !this.direct);
/*      */   }
/*      */   
/*      */   public Collection<? extends Ellipse2D> continuousCurves() {
/*  940 */     return wrapCurve((ContinuousCurve2D)this);
/*      */   }
/*      */   
/*      */   public EllipseArc2D subCurve(double t0, double t1) {
/*      */     double startAngle;
/*      */     double extent;
/*  948 */     if (this.direct) {
/*  949 */       startAngle = t0;
/*  950 */       extent = Angle2D.formatAngle(t1 - t0);
/*      */     } else {
/*  952 */       extent = -Angle2D.formatAngle(t1 - t0);
/*  953 */       startAngle = Angle2D.formatAngle(-t0);
/*      */     } 
/*  955 */     return new EllipseArc2D(this, startAngle, extent);
/*      */   }
/*      */   
/*      */   public double distance(Point2D point) {
/*  968 */     return asPolyline(180).distance(point);
/*      */   }
/*      */   
/*      */   public double distance(double x, double y) {
/*  972 */     return distance(new Point2D(x, y));
/*      */   }
/*      */   
/*      */   public CurveSet2D<? extends SmoothOrientedCurve2D> clip(Box2D box) {
/*  983 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/*  986 */     CurveArray2D<SmoothOrientedCurve2D> result = 
/*  987 */       new CurveArray2D(set.size());
/*  990 */     for (Curve2D curve : set.curves()) {
/*  991 */       if (curve instanceof EllipseArc2D)
/*  992 */         result.add(curve); 
/*  993 */       if (curve instanceof Ellipse2D)
/*  994 */         result.add(curve); 
/*      */     } 
/*  996 */     return (CurveSet2D<? extends SmoothOrientedCurve2D>)result;
/*      */   }
/*      */   
/*      */   public Box2D boundingBox() {
/* 1010 */     double cot = Math.cos(this.theta);
/* 1011 */     double sit = Math.sin(this.theta);
/* 1012 */     double xm = Math.hypot(this.r1 * cot, this.r2 * sit);
/* 1013 */     double ym = Math.hypot(this.r1 * sit, this.r2 * cot);
/* 1014 */     return new Box2D(this.xc - xm, this.xc + xm, this.yc - ym, this.yc + ym);
/*      */   }
/*      */   
/*      */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 1028 */     AffineTransform2D sca = AffineTransform2D.createScaling(this.r1, this.r2);
/* 1029 */     AffineTransform2D rot = AffineTransform2D.createRotation(this.theta);
/* 1030 */     AffineTransform2D tra = AffineTransform2D.createTranslation(this.xc, this.yc);
/* 1031 */     AffineTransform2D toUnit = sca.chain(rot).chain(tra).invert();
/* 1034 */     LinearShape2D line2 = line.transform(toUnit);
/* 1040 */     Circle2D circle = new Circle2D(0.0D, 0.0D, 1.0D);
/* 1041 */     Collection<Point2D> points = circle.intersections(line2);
/* 1042 */     if (points.size() == 0)
/* 1043 */       return points; 
/* 1046 */     ArrayList<Point2D> res = new ArrayList<Point2D>(points.size());
/* 1047 */     for (Point2D point : points)
/* 1048 */       res.add(point(circle.position(point))); 
/* 1051 */     return res;
/*      */   }
/*      */   
/*      */   public Ellipse2D transform(AffineTransform2D trans) {
/* 1061 */     Ellipse2D result = transformCentered(this, trans);
/* 1062 */     Point2D center = center().transform(trans);
/* 1063 */     result.xc = center.x();
/* 1064 */     result.yc = center.y();
/* 1065 */     result.direct = !((this.direct ^ trans.isDirect()) != 0);
/* 1066 */     return result;
/*      */   }
/*      */   
/*      */   public boolean contains(Point2D p) {
/* 1077 */     return contains(p.x(), p.y());
/*      */   }
/*      */   
/*      */   public boolean contains(double x, double y) {
/* 1085 */     return (distance(x, y) < 1.0E-12D);
/*      */   }
/*      */   
/*      */   public GeneralPath getGeneralPath() {
/* 1090 */     double cot = Math.cos(this.theta);
/* 1091 */     double sit = Math.sin(this.theta);
/* 1094 */     GeneralPath path = new GeneralPath();
/* 1097 */     path.moveTo((float)(this.xc + this.r1 * cot), (float)(this.yc + this.r1 * sit));
/* 1100 */     return appendPath(path);
/*      */   }
/*      */   
/*      */   public GeneralPath appendPath(GeneralPath path) {
/* 1110 */     double cot = Math.cos(this.theta);
/* 1111 */     double sit = Math.sin(this.theta);
/* 1114 */     if (this.direct) {
/* 1115 */       for (double t = 0.1D; t <= 6.283185307179586D; t += 0.1D)
/* 1116 */         path.lineTo(
/* 1117 */             (float)(this.xc + this.r1 * Math.cos(t) * cot - this.r2 * Math.sin(t) * sit), 
/* 1118 */             (float)(this.yc + this.r2 * Math.sin(t) * cot + this.r1 * Math.cos(t) * sit)); 
/*      */     } else {
/* 1120 */       for (double t = 0.1D; t <= 6.283185307179586D; t += 0.1D)
/* 1121 */         path.lineTo(
/* 1122 */             (float)(this.xc + this.r1 * Math.cos(t) * cot + this.r2 * Math.sin(t) * sit), 
/* 1123 */             (float)(this.yc - this.r2 * Math.sin(t) * cot + this.r1 * Math.cos(t) * sit)); 
/*      */     } 
/* 1126 */     path.lineTo((float)(this.xc + this.r1 * cot), (float)(this.yc + this.r1 * sit));
/* 1128 */     return path;
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2) {
/* 1133 */     java.awt.geom.Ellipse2D.Double ellipse = new java.awt.geom.Ellipse2D.Double(
/* 1134 */         this.xc - this.r1, this.yc - this.r2, 2.0D * this.r1, 2.0D * this.r2);
/* 1135 */     AffineTransform trans = 
/* 1136 */       AffineTransform.getRotateInstance(this.theta, this.xc, this.yc);
/* 1137 */     g2.draw(trans.createTransformedShape(ellipse));
/*      */   }
/*      */   
/*      */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 1148 */     if (this == obj)
/* 1149 */       return true; 
/* 1151 */     if (!(obj instanceof Ellipse2D))
/* 1152 */       return false; 
/* 1154 */     Ellipse2D ell = (Ellipse2D)obj;
/* 1156 */     if (!ell.center().almostEquals((GeometricObject2D)center(), eps))
/* 1157 */       return false; 
/* 1158 */     if (Math.abs(ell.r1 - this.r1) > eps)
/* 1159 */       return false; 
/* 1160 */     if (Math.abs(ell.r2 - this.r2) > eps)
/* 1161 */       return false; 
/* 1162 */     if (!Angle2D.almostEquals(ell.angle(), angle(), eps))
/* 1163 */       return false; 
/* 1164 */     if (ell.isDirect() != isDirect())
/* 1165 */       return false; 
/* 1166 */     return true;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1175 */     if (this == obj)
/* 1176 */       return true; 
/* 1178 */     if (!(obj instanceof Ellipse2D))
/* 1179 */       return false; 
/* 1181 */     Ellipse2D that = (Ellipse2D)obj;
/* 1184 */     if (!EqualUtils.areEqual(this.xc, that.xc))
/* 1185 */       return false; 
/* 1186 */     if (!EqualUtils.areEqual(this.yc, that.yc))
/* 1187 */       return false; 
/* 1188 */     if (!EqualUtils.areEqual(this.r1, that.r1))
/* 1189 */       return false; 
/* 1190 */     if (!EqualUtils.areEqual(this.r2, that.r2))
/* 1191 */       return false; 
/* 1192 */     if (!EqualUtils.areEqual(this.theta, that.theta))
/* 1193 */       return false; 
/* 1194 */     if (this.direct != that.direct)
/* 1195 */       return false; 
/* 1197 */     return true;
/*      */   }
/*      */   
/*      */   public Ellipse2D clone() {
/* 1202 */     return new Ellipse2D(this.xc, this.yc, this.r1, this.r2, this.theta, this.direct);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1207 */     return String.format("Ellipse2D(%f,%f,%f,%f,%f,%s)", new Object[] { Double.valueOf(this.xc), Double.valueOf(this.yc), Double.valueOf(this.r1), Double.valueOf(this.r2), Double.valueOf(this.theta), this.direct ? "true" : "false" });
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\Ellipse2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */