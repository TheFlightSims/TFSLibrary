/*     */ package math.geom2d.conic;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.curve.AbstractSmoothCurve2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class HyperbolaBranchArc2D extends AbstractSmoothCurve2D implements SmoothOrientedCurve2D, Cloneable {
/*     */   public static HyperbolaBranchArc2D create(HyperbolaBranch2D branch, double t0, double t1) {
/*  29 */     return new HyperbolaBranchArc2D(branch, t0, t1);
/*     */   }
/*     */   
/*  37 */   HyperbolaBranch2D branch = null;
/*     */   
/*  42 */   double t0 = 0.0D;
/*     */   
/*  47 */   double t1 = 1.0D;
/*     */   
/*     */   public HyperbolaBranchArc2D(HyperbolaBranch2D branch, double t0, double t1) {
/*  53 */     this.branch = branch;
/*  54 */     this.t0 = t0;
/*  55 */     this.t1 = t1;
/*     */   }
/*     */   
/*     */   public HyperbolaBranch2D getHyperbolaBranch() {
/*  62 */     return this.branch;
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/*  69 */     return this.branch.curvature(t);
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/*  73 */     return this.branch.tangent(t);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D point) {
/*  80 */     return signedDistance(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/*  85 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/*  90 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D pt) {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 102 */     return asPolyline(60).appendPath(path);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 114 */     Collection<Point2D> inters0 = this.branch.intersections(line);
/* 115 */     ArrayList<Point2D> inters = new ArrayList<Point2D>();
/* 116 */     for (Point2D point : inters0) {
/* 117 */       double pos = this.branch.project(point);
/* 118 */       if (pos > this.t0 && pos < this.t1)
/* 119 */         inters.add(point); 
/*     */     } 
/* 122 */     return inters;
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 129 */     if (Double.isInfinite(t))
/* 130 */       throw new UnboundedShape2DException(this); 
/* 131 */     t = Math.min(Math.max(t, this.t0), this.t1);
/* 132 */     return this.branch.point(t);
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 136 */     if (!this.branch.contains(point))
/* 137 */       return Double.NaN; 
/* 138 */     double t = this.branch.position(point);
/* 139 */     if (t - this.t0 < -1.0E-12D)
/* 140 */       return Double.NaN; 
/* 141 */     if (this.t1 - t < 1.0E-12D)
/* 142 */       return Double.NaN; 
/* 143 */     return t;
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 147 */     double t = this.branch.project(point);
/* 148 */     return Math.min(Math.max(t, this.t0), this.t1);
/*     */   }
/*     */   
/*     */   public HyperbolaBranchArc2D reverse() {
/* 152 */     Hyperbola2D hyper = this.branch.hyperbola;
/* 153 */     Hyperbola2D hyper2 = new Hyperbola2D(hyper.xc, hyper.yc, hyper.a, 
/* 154 */         hyper.b, hyper.theta, !hyper.direct);
/* 155 */     return new HyperbolaBranchArc2D(new HyperbolaBranch2D(hyper2, 
/* 156 */           this.branch.positive), -this.t1, -this.t0);
/*     */   }
/*     */   
/*     */   public HyperbolaBranchArc2D subCurve(double t0, double t1) {
/* 165 */     if (t1 < t0)
/* 166 */       return null; 
/* 167 */     t0 = Math.max(this.t0, t0);
/* 168 */     t1 = Math.min(this.t1, t1);
/* 169 */     return new HyperbolaBranchArc2D(this.branch, t0, t1);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 173 */     return this.t0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 181 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 185 */     return this.t1;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 193 */     return t1();
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 200 */     if (!isBounded())
/* 201 */       throw new UnboundedShape2DException(this); 
/* 202 */     return asPolyline(100).boundingBox();
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends HyperbolaBranchArc2D> clip(Box2D box) {
/* 213 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/* 216 */     CurveArray2D<HyperbolaBranchArc2D> result = 
/* 217 */       new CurveArray2D(set.size());
/* 220 */     for (Curve2D curve : set.curves()) {
/* 221 */       if (curve instanceof HyperbolaBranchArc2D)
/* 222 */         result.add(curve); 
/*     */     } 
/* 224 */     return (CurveSet2D<? extends HyperbolaBranchArc2D>)result;
/*     */   }
/*     */   
/*     */   public double distance(Point2D point) {
/* 228 */     Point2D p = point(project(new Point2D(point)));
/* 229 */     return p.distance(point);
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 233 */     Point2D p = point(project(new Point2D(x, y)));
/* 234 */     return p.distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 238 */     if (this.t0 == Double.NEGATIVE_INFINITY)
/* 239 */       return false; 
/* 240 */     if (this.t1 == Double.POSITIVE_INFINITY)
/* 241 */       return false; 
/* 242 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 246 */     return false;
/*     */   }
/*     */   
/*     */   public HyperbolaBranchArc2D transform(AffineTransform2D trans) {
/* 251 */     HyperbolaBranch2D branch2 = this.branch.transform(trans);
/* 254 */     double startPos = Double.isInfinite(this.t0) ? Double.NEGATIVE_INFINITY : 
/* 255 */       branch2.project(firstPoint().transform(trans));
/* 256 */     double endPos = Double.isInfinite(this.t1) ? Double.POSITIVE_INFINITY : 
/* 257 */       branch2.project(lastPoint().transform(trans));
/* 260 */     if (startPos > endPos)
/* 261 */       return new HyperbolaBranchArc2D(branch2.reverse(), 
/* 262 */           endPos, startPos); 
/* 264 */     return new HyperbolaBranchArc2D(branch2, startPos, endPos);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 269 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 273 */     if (!this.branch.contains(x, y))
/* 274 */       return false; 
/* 275 */     double t = this.branch.position(new Point2D(x, y));
/* 276 */     if (t < this.t0)
/* 277 */       return false; 
/* 278 */     if (t > this.t1)
/* 279 */       return false; 
/* 280 */     return true;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 284 */     if (!isBounded())
/* 285 */       throw new UnboundedShape2DException(this); 
/* 286 */     return asPolyline(100).asGeneralPath();
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 297 */     if (this == obj)
/* 298 */       return true; 
/* 300 */     if (!(obj instanceof HyperbolaBranchArc2D))
/* 301 */       return false; 
/* 302 */     HyperbolaBranchArc2D arc = (HyperbolaBranchArc2D)obj;
/* 304 */     if (!this.branch.almostEquals((GeometricObject2D)arc.branch, eps))
/* 304 */       return false; 
/* 305 */     if (Math.abs(this.t0 - arc.t0) > eps)
/* 306 */       return false; 
/* 307 */     if (Math.abs(this.t1 - arc.t1) > eps)
/* 308 */       return false; 
/* 309 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 317 */     if (!(obj instanceof HyperbolaBranchArc2D))
/* 318 */       return false; 
/* 319 */     HyperbolaBranchArc2D that = (HyperbolaBranchArc2D)obj;
/* 321 */     if (!this.branch.equals(that.branch))
/* 321 */       return false; 
/* 322 */     if (!EqualUtils.areEqual(this.t0, that.t0))
/* 323 */       return false; 
/* 324 */     if (!EqualUtils.areEqual(this.t1, that.t1))
/* 325 */       return false; 
/* 326 */     return true;
/*     */   }
/*     */   
/*     */   public HyperbolaBranchArc2D clone() {
/* 331 */     return new HyperbolaBranchArc2D(this.branch.clone(), this.t0, this.t1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\HyperbolaBranchArc2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */