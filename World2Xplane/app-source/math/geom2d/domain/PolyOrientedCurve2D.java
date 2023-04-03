/*     */ package math.geom2d.domain;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.curve.PolyCurve2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ 
/*     */ public class PolyOrientedCurve2D<T extends ContinuousOrientedCurve2D> extends PolyCurve2D<T> implements ContinuousOrientedCurve2D {
/*     */   public static <T extends ContinuousOrientedCurve2D> PolyOrientedCurve2D<T> create(Collection<T> curves) {
/*  70 */     return new PolyOrientedCurve2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousOrientedCurve2D> PolyOrientedCurve2D<T> create(ContinuousOrientedCurve2D... curves) {
/*  80 */     return new PolyOrientedCurve2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousOrientedCurve2D> PolyOrientedCurve2D<T> createClosed(ContinuousOrientedCurve2D... curves) {
/*  90 */     return new PolyOrientedCurve2D<T>((T[])curves, true);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousOrientedCurve2D> PolyOrientedCurve2D<T> create(Collection<T> curves, boolean closed) {
/* 100 */     return new PolyOrientedCurve2D<T>(curves, closed);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousOrientedCurve2D> PolyOrientedCurve2D<T> create(ContinuousOrientedCurve2D[] curves, boolean closed) {
/* 110 */     return new PolyOrientedCurve2D<T>((T[])curves, closed);
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D() {}
/*     */   
/*     */   public PolyOrientedCurve2D(int size) {
/* 122 */     super(size);
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D(ContinuousOrientedCurve2D... curves) {
/* 126 */     super((ContinuousCurve2D[])curves);
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D(ContinuousOrientedCurve2D[] curves, boolean closed) {
/* 130 */     super((ContinuousCurve2D[])curves, closed);
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D(Collection<? extends T> curves) {
/* 134 */     super(curves);
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D(Collection<? extends T> curves, boolean closed) {
/* 138 */     super(curves, closed);
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 146 */     double angle = 0.0D;
/* 147 */     for (OrientedCurve2D curve : this.curves)
/* 148 */       angle += curve.windingAngle(point); 
/* 149 */     return angle;
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 153 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 162 */     double dist = distance(x, y);
/* 164 */     if (isInside(new Point2D(x, y)))
/* 165 */       dist = -dist; 
/* 167 */     return dist;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 175 */     double pos = project(point);
/* 177 */     if (!isSingular(pos))
/* 179 */       return ((ContinuousOrientedCurve2D)childCurve(pos)).isInside(point); 
/* 183 */     int n = size();
/* 186 */     int i = curveIndex(pos);
/* 187 */     if (pos / 2.0D - i > 0.25D)
/* 188 */       i++; 
/* 191 */     if (Math.round(pos) == (2 * n - 1)) {
/* 192 */       pos = 0.0D;
/* 193 */       i = 0;
/*     */     } 
/* 196 */     Point2D vertex = point(pos);
/* 199 */     int iPrev = (i > 0) ? (i - 1) : (n - 1);
/* 200 */     int iNext = i;
/* 203 */     ContinuousOrientedCurve2D continuousOrientedCurve2D1 = this.curves.get(iPrev);
/* 204 */     ContinuousOrientedCurve2D continuousOrientedCurve2D2 = this.curves.get(iNext);
/* 207 */     Vector2D v1 = computeTangent(continuousOrientedCurve2D1, continuousOrientedCurve2D1.t1());
/* 208 */     Vector2D v2 = computeTangent(continuousOrientedCurve2D2, continuousOrientedCurve2D2.t0());
/* 211 */     boolean in1 = (new StraightLine2D(vertex, v1)).isInside(point);
/* 212 */     boolean in2 = (new StraightLine2D(vertex, v2)).isInside(point);
/* 215 */     double diff = Angle2D.angle(v1, v2);
/* 216 */     double eps = 1.0E-12D;
/* 217 */     if (diff < Math.PI - eps)
/* 219 */       return (in1 && in2); 
/* 222 */     if (diff > Math.PI + eps)
/* 224 */       return !(!in1 && !in2); 
/* 228 */     SmoothCurve2D smoothPrev = Curves2D.getLastSmoothCurve(continuousOrientedCurve2D1);
/* 229 */     SmoothCurve2D smoothNext = Curves2D.getFirstSmoothCurve(continuousOrientedCurve2D2);
/* 230 */     double kappaPrev = smoothPrev.curvature(smoothPrev.t1());
/* 231 */     double kappaNext = smoothNext.curvature(smoothNext.t0());
/* 234 */     double sp = Math.signum(kappaPrev);
/* 235 */     double sn = Math.signum(kappaNext);
/* 239 */     if (sn * sp > 0.0D)
/* 240 */       return (kappaPrev > 0.0D && kappaNext > 0.0D); 
/* 244 */     if (sn * sp == 0.0D) {
/* 245 */       if (sn == 0.0D && sp == 0.0D)
/* 246 */         throw new IllegalArgumentException("colinear lines..."); 
/* 249 */       if (sp == 0.0D)
/* 250 */         return (kappaNext > 0.0D); 
/* 252 */       return (kappaPrev > 0.0D);
/*     */     } 
/* 257 */     if (kappaPrev > 0.0D && kappaNext < 0.0D)
/* 258 */       return (Math.abs(kappaPrev) > Math.abs(kappaNext)); 
/* 260 */     return (Math.abs(kappaPrev) < Math.abs(kappaNext));
/*     */   }
/*     */   
/*     */   private static Vector2D computeTangent(ContinuousCurve2D curve, double pos) {
/* 269 */     if (curve instanceof SmoothCurve2D)
/* 270 */       return ((SmoothCurve2D)curve).tangent(pos); 
/* 273 */     if (curve instanceof CurveSet2D) {
/* 274 */       CurveSet2D<?> curveSet = (CurveSet2D)curve;
/* 275 */       double pos2 = curveSet.localPosition(pos);
/* 276 */       Curve2D subCurve = curveSet.childCurve(pos);
/* 277 */       return computeTangent((ContinuousCurve2D)subCurve, pos2);
/*     */     } 
/* 280 */     throw new IllegalArgumentException(
/* 281 */         "Unknown type of curve: should be either continuous or curveset");
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D<? extends ContinuousOrientedCurve2D> reverse() {
/* 286 */     ContinuousOrientedCurve2D[] curves2 = 
/* 287 */       new ContinuousOrientedCurve2D[this.curves.size()];
/* 288 */     int n = this.curves.size();
/* 289 */     for (int i = 0; i < n; i++)
/* 290 */       curves2[i] = ((ContinuousOrientedCurve2D)this.curves.get(n - 1 - i)).reverse(); 
/* 291 */     return new PolyOrientedCurve2D((T[])curves2);
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D<? extends ContinuousOrientedCurve2D> subCurve(double t0, double t1) {
/* 300 */     PolyCurve2D<?> set = super.subCurve(t0, t1);
/* 301 */     PolyOrientedCurve2D<ContinuousOrientedCurve2D> subCurve = 
/* 302 */       new PolyOrientedCurve2D();
/* 303 */     subCurve.setClosed(false);
/* 306 */     for (Curve2D curve : set.curves())
/* 307 */       subCurve.add(curve); 
/* 309 */     return subCurve;
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends ContinuousOrientedCurve2D> clip(Box2D box) {
/* 322 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve(this, box);
/* 325 */     int n = set.size();
/* 326 */     CurveArray2D<ContinuousOrientedCurve2D> result = 
/* 327 */       new CurveArray2D(n);
/* 330 */     for (Curve2D curve : set.curves()) {
/* 331 */       if (curve instanceof ContinuousOrientedCurve2D)
/* 332 */         result.add(curve); 
/*     */     } 
/* 334 */     return (CurveSet2D<? extends ContinuousOrientedCurve2D>)result;
/*     */   }
/*     */   
/*     */   public PolyOrientedCurve2D<?> transform(AffineTransform2D trans) {
/* 339 */     PolyOrientedCurve2D<ContinuousOrientedCurve2D> result = 
/* 340 */       new PolyOrientedCurve2D();
/* 341 */     for (ContinuousOrientedCurve2D curve : this.curves)
/* 342 */       result.add(curve.transform(trans)); 
/* 343 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 349 */     if (!(obj instanceof CurveSet2D))
/* 350 */       return false; 
/* 352 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\PolyOrientedCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */