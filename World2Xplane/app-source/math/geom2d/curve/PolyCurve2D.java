/*     */ package math.geom2d.curve;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ 
/*     */ public class PolyCurve2D<T extends ContinuousCurve2D> extends CurveArray2D<T> implements ContinuousCurve2D {
/*     */   public static <T extends ContinuousCurve2D> PolyCurve2D<T> create(Collection<T> curves) {
/*  61 */     return new PolyCurve2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousCurve2D> PolyCurve2D<T> create(ContinuousCurve2D... curves) {
/*  71 */     return new PolyCurve2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousCurve2D> PolyCurve2D<T> createClosed(ContinuousCurve2D... curves) {
/*  81 */     return new PolyCurve2D<T>((T[])curves, true);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousCurve2D> PolyCurve2D<T> create(Collection<T> curves, boolean closed) {
/*  91 */     return new PolyCurve2D<T>(curves, closed);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousCurve2D> PolyCurve2D<T> create(ContinuousCurve2D[] curves, boolean closed) {
/* 101 */     return new PolyCurve2D<T>((T[])curves, closed);
/*     */   }
/*     */   
/*     */   protected static <T extends ContinuousCurve2D> Collection<T> wrapCurve(T curve) {
/* 109 */     ArrayList<T> list = new ArrayList<T>(1);
/* 110 */     list.add(curve);
/* 111 */     return list;
/*     */   }
/*     */   
/*     */   protected boolean closed = false;
/*     */   
/*     */   public PolyCurve2D(int n) {
/* 134 */     super(n);
/*     */   }
/*     */   
/*     */   public PolyCurve2D(ContinuousCurve2D... curves) {
/* 142 */     super((T[])curves);
/*     */   }
/*     */   
/*     */   public PolyCurve2D(ContinuousCurve2D[] curves, boolean closed) {
/* 150 */     super((T[])curves);
/* 151 */     this.closed = closed;
/*     */   }
/*     */   
/*     */   public PolyCurve2D(Collection<? extends T> curves) {
/* 159 */     super(curves);
/*     */   }
/*     */   
/*     */   public PolyCurve2D(Collection<? extends T> curves, boolean closed) {
/* 167 */     super(curves);
/* 168 */     this.closed = closed;
/*     */   }
/*     */   
/*     */   public void setClosed(boolean b) {
/* 179 */     this.closed = b;
/*     */   }
/*     */   
/*     */   public Vector2D leftTangent(double t) {
/* 190 */     return ((ContinuousCurve2D)childCurve(t)).leftTangent(localPosition(t));
/*     */   }
/*     */   
/*     */   public Vector2D rightTangent(double t) {
/* 197 */     return ((ContinuousCurve2D)childCurve(t)).rightTangent(localPosition(t));
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 204 */     return ((ContinuousCurve2D)childCurve(t)).curvature(localPosition(t));
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 211 */     return this.closed;
/*     */   }
/*     */   
/*     */   public Polyline2D asPolyline(int n) {
/* 220 */     Point2D[] points = new Point2D[n + 1];
/* 221 */     double t0 = t0();
/* 222 */     double t1 = t1();
/* 223 */     double dt = (t1 - t0) / n;
/* 224 */     for (int i = 0; i < n; i++)
/* 225 */       points[i] = point(i * dt + t0); 
/* 226 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public Collection<? extends SmoothCurve2D> smoothPieces() {
/* 235 */     ArrayList<SmoothCurve2D> list = new ArrayList<SmoothCurve2D>();
/* 236 */     for (Curve2D curve : this.curves)
/* 237 */       list.addAll(getSmoothCurves(curve)); 
/* 238 */     return list;
/*     */   }
/*     */   
/*     */   private static Collection<SmoothCurve2D> getSmoothCurves(Curve2D curve) {
/* 249 */     ArrayList<SmoothCurve2D> array = new ArrayList<SmoothCurve2D>();
/* 252 */     if (curve instanceof SmoothCurve2D) {
/* 253 */       array.add((SmoothCurve2D)curve);
/* 254 */       return array;
/*     */     } 
/* 258 */     if (curve instanceof CurveSet2D) {
/* 259 */       for (Curve2D curve2 : ((CurveSet2D)curve).curves())
/* 260 */         array.addAll(getSmoothCurves(curve2)); 
/* 261 */       return array;
/*     */     } 
/* 264 */     if (curve == null)
/* 265 */       return array; 
/* 267 */     System.err.println("could not find smooth parts of curve with class " + 
/* 268 */         curve.getClass().getName());
/* 269 */     return array;
/*     */   }
/*     */   
/*     */   public Collection<? extends PolyCurve2D<?>> continuousCurves() {
/* 280 */     return wrapCurve(this);
/*     */   }
/*     */   
/*     */   public PolyCurve2D<? extends ContinuousCurve2D> reverse() {
/* 289 */     int n = this.curves.size();
/* 290 */     ContinuousCurve2D[] curves2 = new ContinuousCurve2D[n];
/* 293 */     for (int i = 0; i < n; i++)
/* 294 */       curves2[i] = ((ContinuousCurve2D)this.curves.get(n - 1 - i)).reverse(); 
/* 297 */     return new PolyCurve2D((T[])curves2, this.closed);
/*     */   }
/*     */   
/*     */   public PolyCurve2D<? extends ContinuousCurve2D> subCurve(double t0, double t1) {
/* 308 */     if ((((t1 < t0) ? 1 : 0) & (isClosed() ? 0 : 1)) != 0)
/* 309 */       return new PolyCurve2D(); 
/* 312 */     CurveSet2D<?> set = super.subCurve(t0, t1);
/* 315 */     PolyCurve2D<ContinuousCurve2D> subCurve = 
/* 316 */       new PolyCurve2D(set.size());
/* 319 */     subCurve.setClosed(false);
/* 322 */     for (Curve2D curve : set.curves())
/* 323 */       subCurve.add((ContinuousCurve2D)curve); 
/* 326 */     return subCurve;
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends ContinuousCurve2D> clip(Box2D box) {
/* 338 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve(this, box);
/* 341 */     CurveArray2D<ContinuousCurve2D> result = 
/* 342 */       new CurveArray2D<ContinuousCurve2D>(set.size());
/* 345 */     for (Curve2D curve : set.curves()) {
/* 346 */       if (curve instanceof ContinuousCurve2D)
/* 347 */         result.add((ContinuousCurve2D)curve); 
/*     */     } 
/* 349 */     return result;
/*     */   }
/*     */   
/*     */   public PolyCurve2D<? extends ContinuousCurve2D> transform(AffineTransform2D trans) {
/* 359 */     PolyCurve2D<ContinuousCurve2D> result = new PolyCurve2D();
/* 360 */     for (ContinuousCurve2D curve : this.curves)
/* 361 */       result.add(curve.transform(trans)); 
/* 362 */     result.setClosed(isClosed());
/* 363 */     return result;
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 368 */     for (ContinuousCurve2D curve : curves()) {
/* 369 */       Point2D point = curve.point(curve.t0());
/* 370 */       path.lineTo((float)point.x(), (float)point.y());
/* 371 */       curve.appendPath(path);
/*     */     } 
/* 375 */     if (this.closed) {
/* 376 */       Point2D point = firstPoint();
/* 377 */       path.lineTo((float)point.x(), (float)point.y());
/*     */     } 
/* 380 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 389 */     GeneralPath path = new GeneralPath();
/* 392 */     if (this.curves.size() == 0)
/* 393 */       return path; 
/* 397 */     Point2D start = firstPoint();
/* 398 */     path.moveTo((float)start.x(), (float)start.y());
/* 399 */     Point2D current = start;
/* 402 */     for (ContinuousCurve2D curve : this.curves) {
/* 403 */       start = curve.firstPoint();
/* 404 */       if (start.distance(current) > 1.0E-12D)
/* 405 */         path.lineTo((float)start.x(), (float)start.y()); 
/* 406 */       path = curve.appendPath(path);
/* 407 */       current = start;
/*     */     } 
/* 411 */     if (this.closed)
/* 412 */       path.closePath(); 
/* 416 */     return path;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 424 */     g2.draw(getGeneralPath());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 430 */     if (!(obj instanceof CurveSet2D))
/* 431 */       return false; 
/* 432 */     PolyCurve2D<?> curveSet = (PolyCurve2D)obj;
/* 435 */     if (size() != curveSet.size())
/* 436 */       return false; 
/* 439 */     for (int i = 0; i < this.curves.size(); i++) {
/* 440 */       if (!((ContinuousCurve2D)this.curves.get(i)).equals(curveSet.curves.get(i)))
/* 441 */         return false; 
/*     */     } 
/* 444 */     return true;
/*     */   }
/*     */   
/*     */   public PolyCurve2D() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\PolyCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */