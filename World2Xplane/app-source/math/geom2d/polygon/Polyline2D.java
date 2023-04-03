/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.PolyCirculinearCurve2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class Polyline2D extends LinearCurve2D implements CirculinearContinuousCurve2D, Cloneable {
/*     */   public static Polyline2D create(Collection<? extends Point2D> points) {
/*  61 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public static Polyline2D create(Point2D... points) {
/*  70 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public Polyline2D() {}
/*     */   
/*     */   public Polyline2D(int nVertices) {
/*  87 */     super(nVertices);
/*     */   }
/*     */   
/*     */   public Polyline2D(Point2D initialPoint) {
/*  91 */     this.vertices.add(initialPoint);
/*     */   }
/*     */   
/*     */   public Polyline2D(Point2D... vertices) {
/*  95 */     super(vertices);
/*     */   }
/*     */   
/*     */   public Polyline2D(Collection<? extends Point2D> vertices) {
/*  99 */     super(vertices);
/*     */   }
/*     */   
/*     */   public Polyline2D(double[] xcoords, double[] ycoords) {
/* 103 */     super(xcoords, ycoords);
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> edges() {
/* 117 */     int n = this.vertices.size();
/* 118 */     ArrayList<LineSegment2D> edges = new ArrayList<LineSegment2D>(n);
/* 120 */     if (n < 2)
/* 121 */       return edges; 
/* 123 */     for (int i = 0; i < n - 1; i++)
/* 124 */       edges.add(new LineSegment2D(this.vertices.get(i), this.vertices.get(i + 1))); 
/* 126 */     return edges;
/*     */   }
/*     */   
/*     */   public int edgeNumber() {
/* 130 */     int n = this.vertices.size();
/* 131 */     if (n > 1)
/* 132 */       return n - 1; 
/* 133 */     return 0;
/*     */   }
/*     */   
/*     */   public LineSegment2D edge(int index) {
/* 137 */     return new LineSegment2D(this.vertices.get(index), this.vertices.get(index + 1));
/*     */   }
/*     */   
/*     */   public LineSegment2D lastEdge() {
/* 141 */     int n = this.vertices.size();
/* 142 */     if (n < 2)
/* 143 */       return null; 
/* 144 */     return new LineSegment2D(this.vertices.get(n - 2), this.vertices.get(n - 1));
/*     */   }
/*     */   
/*     */   public CirculinearContinuousCurve2D transform(CircleInversion2D inv) {
/* 156 */     Collection<LineSegment2D> edges = edges();
/* 157 */     ArrayList<CirculinearContinuousCurve2D> arcs = 
/* 158 */       new ArrayList<CirculinearContinuousCurve2D>(edges.size());
/* 161 */     for (LineSegment2D edge : edges)
/* 162 */       arcs.add(edge.transform(inv)); 
/* 166 */     return (CirculinearContinuousCurve2D)new PolyCirculinearCurve2D(arcs);
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 179 */     double angle = 0.0D;
/* 180 */     int n = this.vertices.size();
/* 181 */     for (int i = 0; i < n - 1; i++)
/* 182 */       angle += (new LineSegment2D(this.vertices.get(i), this.vertices.get(i + 1)))
/* 183 */         .windingAngle(point); 
/* 185 */     return angle;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D pt) {
/* 194 */     if ((new LinearRing2D(vertexArray())).isInside(pt))
/* 195 */       return true; 
/* 198 */     if (this.vertices.size() < 3)
/* 199 */       return false; 
/* 202 */     Point2D p0 = firstPoint();
/* 203 */     Point2D q0 = vertex(1);
/* 204 */     if ((new StraightLine2D(q0, p0)).isInside(pt))
/* 205 */       return false; 
/* 208 */     Point2D p1 = lastPoint();
/* 209 */     Point2D q1 = vertex(vertexNumber() - 2);
/* 210 */     if ((new StraightLine2D(p1, q1)).isInside(pt))
/* 211 */       return false; 
/* 214 */     if ((new StraightLine2D(p0, p1)).isInside(pt))
/* 215 */       return true; 
/* 217 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 228 */     return false;
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 242 */     double t0 = t0();
/* 243 */     double t1 = t1();
/* 244 */     t = Math.max(Math.min(t, t1), t0);
/* 247 */     int ind0 = (int)Math.floor(t + 1.0E-12D);
/* 248 */     double tl = t - ind0;
/* 249 */     Point2D p0 = this.vertices.get(ind0);
/* 252 */     if (Math.abs(t - ind0) < 1.0E-12D)
/* 253 */       return new Point2D(p0); 
/* 256 */     int ind1 = ind0 + 1;
/* 257 */     Point2D p1 = this.vertices.get(ind1);
/* 260 */     double x0 = p0.x();
/* 261 */     double y0 = p0.y();
/* 262 */     double dx = p1.x() - x0;
/* 263 */     double dy = p1.y() - y0;
/* 264 */     return new Point2D(x0 + tl * dx, y0 + tl * dy);
/*     */   }
/*     */   
/*     */   public double t1() {
/* 271 */     return (this.vertices.size() - 1);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 279 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 288 */     if (this.vertices.size() == 0)
/* 289 */       return null; 
/* 290 */     return this.vertices.get(this.vertices.size() - 1);
/*     */   }
/*     */   
/*     */   public Polyline2D reverse() {
/* 298 */     Point2D[] points2 = new Point2D[this.vertices.size()];
/* 299 */     int n = this.vertices.size();
/* 300 */     for (int i = 0; i < n; i++)
/* 301 */       points2[i] = this.vertices.get(n - 1 - i); 
/* 302 */     return new Polyline2D(points2);
/*     */   }
/*     */   
/*     */   public Collection<? extends Polyline2D> continuousCurves() {
/* 307 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public Polyline2D subCurve(double t0, double t1) {
/* 318 */     Polyline2D res = new Polyline2D();
/* 320 */     if (t1 < t0)
/* 321 */       return res; 
/* 324 */     int indMax = (int)t1();
/* 327 */     t0 = Math.min(Math.max(t0, 0.0D), indMax);
/* 328 */     t1 = Math.min(Math.max(t1, 0.0D), indMax);
/* 331 */     int ind0 = (int)Math.floor(t0);
/* 332 */     int ind1 = (int)Math.floor(t1);
/* 335 */     if (ind0 == ind1) {
/* 337 */       res.addVertex(point(t0));
/* 338 */       res.addVertex(point(t1));
/* 340 */       return res;
/*     */     } 
/* 344 */     res.addVertex(point(t0));
/* 347 */     for (int n = ind0 + 1; n <= ind1; n++)
/* 348 */       res.addVertex(this.vertices.get(n)); 
/* 351 */     res.addVertex(point(t1));
/* 354 */     return res;
/*     */   }
/*     */   
/*     */   public Polyline2D transform(AffineTransform2D trans) {
/* 366 */     Point2D[] pts = new Point2D[this.vertices.size()];
/* 367 */     for (int i = 0; i < this.vertices.size(); i++)
/* 368 */       pts[i] = trans.transform(this.vertices.get(i)); 
/* 369 */     return new Polyline2D(pts);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 379 */     if (this.vertices.size() < 2)
/* 380 */       return path; 
/* 383 */     Iterator<Point2D> iter = this.vertices.iterator();
/* 386 */     Point2D point = iter.next();
/* 389 */     while (iter.hasNext()) {
/* 390 */       point = iter.next();
/* 391 */       path.lineTo((float)point.x(), (float)point.y());
/*     */     } 
/* 394 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath asGeneralPath() {
/* 401 */     GeneralPath path = new GeneralPath();
/* 402 */     if (this.vertices.size() < 2)
/* 403 */       return path; 
/* 406 */     Iterator<Point2D> iter = this.vertices.iterator();
/* 409 */     Point2D point = iter.next();
/* 410 */     path.moveTo((float)point.x(), (float)point.y());
/* 413 */     while (iter.hasNext()) {
/* 414 */       point = iter.next();
/* 415 */       path.lineTo((float)point.x(), (float)point.y());
/*     */     } 
/* 418 */     return path;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 429 */     if (this == obj)
/* 430 */       return true; 
/* 432 */     if (!(obj instanceof Polyline2D))
/* 433 */       return false; 
/* 434 */     Polyline2D polyline = (Polyline2D)obj;
/* 436 */     if (this.vertices.size() != polyline.vertices.size())
/* 437 */       return false; 
/* 439 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 440 */       if (!((Point2D)this.vertices.get(i)).almostEquals((GeometricObject2D)polyline.vertices.get(i), eps))
/* 441 */         return false; 
/*     */     } 
/* 442 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 450 */     if (this == object)
/* 451 */       return true; 
/* 452 */     if (!(object instanceof Polyline2D))
/* 453 */       return false; 
/* 454 */     Polyline2D polyline = (Polyline2D)object;
/* 456 */     if (this.vertices.size() != polyline.vertices.size())
/* 457 */       return false; 
/* 458 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 459 */       if (!((Point2D)this.vertices.get(i)).equals(polyline.vertices.get(i)))
/* 460 */         return false; 
/*     */     } 
/* 461 */     return true;
/*     */   }
/*     */   
/*     */   public Polyline2D clone() {
/* 466 */     ArrayList<Point2D> array = new ArrayList<Point2D>(this.vertices.size());
/* 467 */     for (Point2D point : this.vertices)
/* 468 */       array.add(point.clone()); 
/* 469 */     return new Polyline2D(array);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\Polyline2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */