/*     */ package math.geom2d.conic;
/*     */ 
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CircleLine2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearContour2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.ContourArray2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.SmoothContour2D;
/*     */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*     */ import math.geom2d.line.LinearElement2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ 
/*     */ public class Conics2D {
/*     */   public static final Conic2D reduceConic(double[] coefs) {
/*     */     double a1, b1, c1, d1, e1, f1;
/*  25 */     if (coefs.length < 6) {
/*  26 */       System.err.println(
/*  27 */           "Conic2DUtils.reduceConic: must provide 6 coefficients");
/*  28 */       return null;
/*     */     } 
/*  30 */     boolean debug = false;
/*  33 */     double eps = 1.0E-12D;
/*  36 */     double a = coefs[0];
/*  37 */     double b = coefs[1];
/*  38 */     double c = coefs[2];
/*  39 */     double d = coefs[3];
/*  40 */     double e = coefs[4];
/*  41 */     double f = coefs[5];
/*  50 */     double theta0 = 0.0D;
/*  52 */     if (Math.abs(b) < eps) {
/*  54 */       a1 = a;
/*  55 */       b1 = b;
/*  56 */       c1 = c;
/*  57 */       d1 = d;
/*  58 */       e1 = e;
/*  59 */       f1 = f;
/*  60 */       theta0 = 0.0D;
/*     */     } else {
/*  63 */       if (Math.abs(a - c) < eps) {
/*  64 */         theta0 = 0.7853981633974483D;
/*     */       } else {
/*  66 */         theta0 = Angle2D.formatAngle(Math.atan2(b, a - c) / 2.0D);
/*     */       } 
/*  68 */       if (debug)
/*  69 */         System.out.println("conic main angle: " + Math.toDegrees(theta0)); 
/*  72 */       double cot = Math.cos(theta0);
/*  73 */       double sit = Math.sin(theta0);
/*  74 */       double co2t = Math.cos(2.0D * theta0);
/*  75 */       double si2t = Math.sin(2.0D * theta0);
/*  76 */       double cot2 = cot * cot;
/*  77 */       double sit2 = sit * sit;
/*  80 */       a1 = a * cot2 + b * sit * cot + c * sit2;
/*  81 */       b1 = si2t * (c - a) + b * co2t;
/*  82 */       c1 = a * sit2 - b * sit * cot + c * cot2;
/*  83 */       d1 = d * cot + e * sit;
/*  84 */       e1 = -d * sit + e * cot;
/*  85 */       f1 = f;
/*     */     } 
/*  89 */     if (Math.abs(b1) > eps) {
/*  90 */       System.err.println(
/*  91 */           "Conic2DUtils.reduceConic: conic was not correctly transformed");
/*  93 */       return null;
/*     */     } 
/*  97 */     if (Math.abs(a) < eps && Math.abs(c) < eps) {
/*  98 */       if (Math.abs(d) > eps || Math.abs(e) > eps)
/*  99 */         return new ConicStraightLine2D(d, e, f); 
/* 101 */       return null;
/*     */     } 
/* 105 */     if (Math.abs(a1) < eps) {
/* 107 */       if (debug)
/* 108 */         System.out.println("horizontal parabola"); 
/* 111 */       if (Math.abs(d1) < eps) {
/* 112 */         double delta = e1 * e1 - 4.0D * c1 * f1;
/* 113 */         if (delta >= 0.0D) {
/* 115 */           double d2 = -e1 / 2.0D / c1;
/* 116 */           double dist = Math.sqrt(delta) / 2.0D / c1;
/* 117 */           Point2D point2D = (new Point2D(0.0D, d2))
/* 118 */             .transform(AffineTransform2D.createRotation(theta0));
/* 119 */           return new ConicTwoLines2D(point2D, dist, theta0);
/*     */         } 
/* 121 */         return null;
/*     */       } 
/* 125 */       double c2 = -c1 / d1;
/* 126 */       double e2 = -e1 / d1;
/* 127 */       double f2 = -f1 / d1;
/* 130 */       double xs = -(e2 * e2 - 4.0D * c2 * f2) / 4.0D * c2;
/* 131 */       double ys = -e2 * 0.5D / c2;
/* 134 */       return new Parabola2D(xs, ys, c2, theta0 - 1.5707963267948966D);
/*     */     } 
/* 136 */     if (Math.abs(c1) < eps) {
/* 138 */       if (debug)
/* 139 */         System.out.println("vertical parabola"); 
/* 142 */       if (Math.abs(e1) < eps) {
/* 143 */         double delta = d1 * d1 - 4.0D * a1 * f1;
/* 144 */         if (delta >= 0.0D) {
/* 146 */           double d3 = -d1 / 2.0D / a1;
/* 147 */           double dist = Math.sqrt(delta) / 2.0D / a1;
/* 148 */           Point2D point2D = (new Point2D(0.0D, d3))
/* 149 */             .transform(AffineTransform2D.createRotation(theta0));
/* 150 */           return new ConicTwoLines2D(point2D, dist, theta0);
/*     */         } 
/* 152 */         return null;
/*     */       } 
/* 156 */       double a2 = -a1 / e1;
/* 157 */       double d2 = -d1 / e1;
/* 158 */       double f2 = -f1 / e1;
/* 161 */       double xs = -d2 * 0.5D / a2;
/* 162 */       double ys = -(d2 * d2 - 4.0D * a2 * f2) / 4.0D * a2;
/* 165 */       return new Parabola2D(xs, ys, a2, theta0);
/*     */     } 
/* 171 */     Point2D center = new Point2D(-d1 / 2.0D * a1, -e1 / 2.0D * c1);
/* 172 */     center = center.transform(AffineTransform2D.createRotation(theta0));
/* 175 */     double num = (c1 * d1 * d1 + a1 * e1 * e1 - 4.0D * a1 * c1 * f1) / 
/* 176 */       4.0D * a1 * c1;
/* 177 */     double at = num / a1;
/* 178 */     double bt = num / c1;
/* 180 */     if (at < 0.0D && bt < 0.0D) {
/* 181 */       System.err.println("Conic2DUtils.reduceConic(): found A<0 and C<0");
/* 182 */       return null;
/*     */     } 
/* 186 */     if (at > 0.0D && bt > 0.0D) {
/* 187 */       if (debug)
/* 188 */         System.out.println("ellipse"); 
/* 189 */       if (at > bt)
/* 190 */         return new Ellipse2D(center, Math.sqrt(at), Math.sqrt(bt), theta0); 
/* 192 */       return new Ellipse2D(center, Math.sqrt(bt), Math.sqrt(at), 
/* 193 */           Angle2D.formatAngle(theta0 + 1.5707963267948966D));
/*     */     } 
/* 199 */     if (at > 0.0D) {
/* 200 */       if (debug)
/* 201 */         System.out.println("east-west hyperbola"); 
/* 202 */       return new Hyperbola2D(center, Math.sqrt(at), Math.sqrt(-bt), theta0);
/*     */     } 
/* 204 */     if (debug)
/* 205 */       System.out.println("north-south hyperbola"); 
/* 206 */     return new Hyperbola2D(center, Math.sqrt(bt), Math.sqrt(-at), theta0 + 1.5707963267948966D);
/*     */   }
/*     */   
/*     */   public static final double[] transformCentered(double[] coefs, AffineTransform2D trans) {
/* 223 */     double[][] mat = trans.affineMatrix();
/* 224 */     double a = mat[0][0];
/* 225 */     double b = mat[1][0];
/* 226 */     double c = mat[0][1];
/* 227 */     double d = mat[1][1];
/* 230 */     double A = coefs[0];
/* 231 */     double B = coefs[1];
/* 232 */     double C = coefs[2];
/* 235 */     double delta = a * d - b * c;
/* 236 */     delta *= delta;
/* 238 */     double A2 = (A * d * d + C * b * b - B * b * d) / delta;
/* 239 */     double B2 = (B * (a * d + b * c) - 2.0D * (A * c * d + C * a * b)) / delta;
/* 240 */     double C2 = (A * c * c + C * a * a - B * a * c) / delta;
/* 243 */     if (coefs.length == 3)
/* 244 */       return new double[] { A2, B2, C2 }; 
/* 247 */     double D = coefs[3];
/* 248 */     double E = coefs[4];
/* 249 */     double F = coefs[5];
/* 250 */     double D2 = D * d - E * b;
/* 251 */     double E2 = E * a - D * c;
/* 252 */     return new double[] { A2, B2, C2, D2, E2, F };
/*     */   }
/*     */   
/*     */   public static final double[] transform(double[] coefs, AffineTransform2D trans) {
/* 265 */     double[][] mat = trans.invert().affineMatrix();
/* 266 */     double a = mat[0][0];
/* 267 */     double b = mat[1][0];
/* 268 */     double c = mat[0][1];
/* 269 */     double d = mat[1][1];
/* 270 */     double e = mat[0][2];
/* 271 */     double f = mat[1][2];
/* 274 */     double A = coefs[0];
/* 275 */     double B = coefs[1];
/* 276 */     double C = coefs[2];
/* 277 */     double D = coefs[3];
/* 278 */     double E = coefs[4];
/* 279 */     double F = coefs[5];
/* 282 */     double A2 = A * a * a + B * a * b + C * b * b;
/* 283 */     double B2 = 2.0D * (A * a * c + C * b * d) + B * (a * d + b * c);
/* 284 */     double C2 = A * c * c + B * c * d + C * d * d;
/* 285 */     double D2 = 2.0D * (A * a * e + C * b * f) + B * (a * f + b * e) + D * a + E * b;
/* 286 */     double E2 = 2.0D * (A * c * e + C * d * f) + B * (c * f + d * e) + D * c + E * d;
/* 287 */     double F2 = A * e * e + B * e * f + C * f * f + D * e + E * f + F;
/* 290 */     return new double[] { A2, B2, C2, D2, E2, F2 };
/*     */   }
/*     */   
/*     */   static class ConicStraightLine2D extends StraightLine2D implements Conic2D {
/* 298 */     double[] coefs = new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D };
/*     */     
/*     */     public ConicStraightLine2D(StraightLine2D line) {
/* 301 */       super((LinearShape2D)line);
/* 302 */       this.coefs = new double[] { 0.0D, 0.0D, 0.0D, this.dy, -this.dx, this.dx * this.y0 - this.dy * this.x0 };
/*     */     }
/*     */     
/*     */     public ConicStraightLine2D(double a, double b, double c) {
/* 306 */       super((LinearShape2D)StraightLine2D.createCartesian(a, b, c));
/* 307 */       this.coefs = new double[] { 0.0D, 0.0D, 0.0D, a, b, c };
/*     */     }
/*     */     
/*     */     public double[] conicCoefficients() {
/* 311 */       return this.coefs;
/*     */     }
/*     */     
/*     */     public Conic2D.Type conicType() {
/* 315 */       return Conic2D.Type.STRAIGHT_LINE;
/*     */     }
/*     */     
/*     */     public double eccentricity() {
/* 320 */       return Double.NaN;
/*     */     }
/*     */     
/*     */     public ConicStraightLine2D reverse() {
/* 325 */       return new ConicStraightLine2D(super.reverse());
/*     */     }
/*     */     
/*     */     public ConicStraightLine2D transform(AffineTransform2D trans) {
/* 330 */       return new ConicStraightLine2D(super.transform(trans));
/*     */     }
/*     */   }
/*     */   
/*     */   static class ConicTwoLines2D extends ContourArray2D<StraightLine2D> implements Conic2D {
/* 338 */     double xc = 0.0D, yc = 0.0D, d = 1.0D, theta = 0.0D;
/*     */     
/*     */     public ConicTwoLines2D(Point2D point, double d, double theta) {
/* 341 */       this(point.x(), point.y(), d, theta);
/*     */     }
/*     */     
/*     */     public ConicTwoLines2D(double xc, double yc, double d, double theta) {
/* 347 */       this.xc = xc;
/* 348 */       this.yc = yc;
/* 349 */       this.d = d;
/* 350 */       this.theta = theta;
/* 352 */       StraightLine2D baseLine = new StraightLine2D(
/* 353 */           new Point2D(xc, yc), theta);
/* 354 */       add((Curve2D)baseLine.parallel(d));
/* 355 */       add((Curve2D)baseLine.parallel(-d).reverse());
/*     */     }
/*     */     
/*     */     public double[] conicCoefficients() {
/* 359 */       double[] coefs = { 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, -1.0D };
/* 360 */       AffineTransform2D sca = AffineTransform2D.createScaling(0.0D, this.d);
/* 361 */       AffineTransform2D rot = AffineTransform2D.createRotation(this.theta);
/* 362 */       AffineTransform2D tra = AffineTransform2D.createTranslation(this.xc, this.yc);
/* 365 */       AffineTransform2D trans = sca.chain(rot).chain(tra);
/* 366 */       return Conics2D.transform(coefs, trans);
/*     */     }
/*     */     
/*     */     public Conic2D.Type conicType() {
/* 370 */       return Conic2D.Type.TWO_LINES;
/*     */     }
/*     */     
/*     */     public double eccentricity() {
/* 374 */       return Double.NaN;
/*     */     }
/*     */     
/*     */     public ConicTwoLines2D transform(AffineTransform2D trans) {
/* 379 */       Point2D center = (new Point2D(this.xc, this.yc)).transform(trans);
/* 380 */       StraightLine2D line = ((StraightLine2D)firstCurve()).transform(trans);
/* 382 */       double dist = line.distance(center);
/* 383 */       double angle = line.horizontalAngle();
/* 384 */       return new ConicTwoLines2D(center, dist, angle);
/*     */     }
/*     */     
/*     */     public ConicTwoLines2D reverse() {
/* 389 */       return new ConicTwoLines2D(this.xc, this.yc, -this.d, this.theta);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\Conics2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */