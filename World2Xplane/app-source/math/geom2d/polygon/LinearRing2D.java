/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearContour2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.circulinear.CirculinearRing2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearRing2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class LinearRing2D extends LinearCurve2D implements CirculinearRing2D {
/*     */   public static LinearRing2D create(Collection<? extends Point2D> points) {
/*  64 */     return new LinearRing2D(points);
/*     */   }
/*     */   
/*     */   public static LinearRing2D create(Point2D... vertices) {
/*  73 */     return new LinearRing2D(vertices);
/*     */   }
/*     */   
/*     */   public LinearRing2D() {}
/*     */   
/*     */   public LinearRing2D(Point2D initialPoint) {
/*  85 */     super(new Point2D[] { initialPoint });
/*     */   }
/*     */   
/*     */   public LinearRing2D(Point2D... vertices) {
/*  89 */     super(vertices);
/*     */   }
/*     */   
/*     */   public LinearRing2D(double[] xcoords, double[] ycoords) {
/*  93 */     super(xcoords, ycoords);
/*     */   }
/*     */   
/*     */   public LinearRing2D(Collection<? extends Point2D> points) {
/*  97 */     super(points);
/*     */   }
/*     */   
/*     */   public double area() {
/* 114 */     double area = 0.0D;
/* 115 */     Point2D prev = this.vertices.get(this.vertices.size() - 1);
/* 117 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 118 */       Point2D point = this.vertices.get(i);
/* 119 */       area += prev.x() * point.y() - prev.y() * point.x();
/* 120 */       prev = point;
/*     */     } 
/* 122 */     return area /= 2.0D;
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> edges() {
/* 137 */     int n = this.vertices.size();
/* 138 */     ArrayList<LineSegment2D> edges = new ArrayList<LineSegment2D>(n);
/* 141 */     if (n < 2)
/* 142 */       return edges; 
/* 145 */     for (int i = 0; i < n - 1; i++)
/* 146 */       edges.add(new LineSegment2D(this.vertices.get(i), this.vertices.get(i + 1))); 
/* 149 */     Point2D p0 = this.vertices.get(0);
/* 150 */     Point2D pn = this.vertices.get(n - 1);
/* 153 */     if (pn.distance(p0) > 1.0E-12D)
/* 154 */       edges.add(new LineSegment2D(pn, p0)); 
/* 157 */     return edges;
/*     */   }
/*     */   
/*     */   public int edgeNumber() {
/* 161 */     int n = this.vertices.size();
/* 162 */     if (n > 1)
/* 163 */       return n; 
/* 164 */     return 0;
/*     */   }
/*     */   
/*     */   public LineSegment2D edge(int index) {
/* 168 */     int i2 = (index + 1) % this.vertices.size();
/* 169 */     return new LineSegment2D(this.vertices.get(index), this.vertices.get(i2));
/*     */   }
/*     */   
/*     */   public LineSegment2D lastEdge() {
/* 177 */     int n = this.vertices.size();
/* 178 */     if (n < 2)
/* 179 */       return null; 
/* 180 */     return new LineSegment2D(this.vertices.get(n - 1), this.vertices.get(0));
/*     */   }
/*     */   
/*     */   public CirculinearRing2D parallel(double dist) {
/* 187 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 188 */     return (CirculinearRing2D)GenericCirculinearRing2D.create(
/* 189 */         bc.createContinuousParallel(this, dist).smoothPieces());
/*     */   }
/*     */   
/*     */   public CirculinearRing2D transform(CircleInversion2D inv) {
/* 198 */     Collection<LineSegment2D> edges = edges();
/* 199 */     ArrayList<CirculinearElement2D> arcs = 
/* 200 */       new ArrayList<CirculinearElement2D>(edges.size());
/* 203 */     for (LineSegment2D edge : edges)
/* 204 */       arcs.add(edge.transform(inv)); 
/* 208 */     return (CirculinearRing2D)new GenericCirculinearRing2D(arcs);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D domain() {
/* 215 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D((CirculinearBoundary2D)this);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 219 */     g2.fill(asGeneralPath());
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 231 */     int wn = Polygons2D.windingNumber(this.vertices, point);
/* 232 */     return (wn * 2) * Math.PI;
/*     */   }
/*     */   
/*     */   public boolean isInside(double x, double y) {
/* 236 */     return isInside(new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 246 */     if (contains(point))
/* 247 */       return true; 
/* 249 */     double area = area();
/* 250 */     int winding = Polygons2D.windingNumber(this.vertices, point);
/* 251 */     if (area > 0.0D)
/* 252 */       return (winding == 1); 
/* 254 */     return (winding == 0);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 266 */     return true;
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 278 */     double t0 = t0();
/* 279 */     double t1 = t1();
/* 280 */     t = Math.max(Math.min(t, t1), t0);
/* 282 */     int n = this.vertices.size();
/* 285 */     int ind0 = (int)Math.floor(t + 1.0E-12D);
/* 286 */     double tl = t - ind0;
/* 288 */     if (ind0 == n)
/* 289 */       ind0 = 0; 
/* 290 */     Point2D p0 = this.vertices.get(ind0);
/* 293 */     if (Math.abs(t - ind0) < 1.0E-12D)
/* 294 */       return new Point2D(p0); 
/* 297 */     int ind1 = ind0 + 1;
/* 298 */     if (ind1 == n)
/* 299 */       ind1 = 0; 
/* 300 */     Point2D p1 = this.vertices.get(ind1);
/* 303 */     double x0 = p0.x();
/* 304 */     double y0 = p0.y();
/* 305 */     double dx = p1.x() - x0;
/* 306 */     double dy = p1.y() - y0;
/* 308 */     return new Point2D(x0 + tl * dx, y0 + tl * dy);
/*     */   }
/*     */   
/*     */   public double t1() {
/* 315 */     return this.vertices.size();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 323 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 331 */     if (this.vertices.size() == 0)
/* 332 */       return null; 
/* 333 */     return this.vertices.get(0);
/*     */   }
/*     */   
/*     */   public Collection<? extends LinearRing2D> continuousCurves() {
/* 338 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public LinearRing2D reverse() {
/* 347 */     Point2D[] points2 = new Point2D[this.vertices.size()];
/* 348 */     int n = this.vertices.size();
/* 349 */     if (n > 0)
/* 350 */       points2[0] = this.vertices.get(0); 
/* 352 */     for (int i = 1; i < n; i++)
/* 353 */       points2[i] = this.vertices.get(n - i); 
/* 355 */     return new LinearRing2D(points2);
/*     */   }
/*     */   
/*     */   public Polyline2D subCurve(double t0, double t1) {
/* 365 */     Polyline2D res = new Polyline2D();
/* 368 */     int indMax = vertexNumber();
/* 371 */     t0 = Math.min(Math.max(t0, 0.0D), indMax);
/* 372 */     t1 = Math.min(Math.max(t1, 0.0D), indMax);
/* 375 */     int ind0 = (int)Math.floor(t0 + 1.0E-12D);
/* 376 */     int ind1 = (int)Math.floor(t1 + 1.0E-12D);
/* 379 */     if (ind0 == ind1 && t0 < t1) {
/* 381 */       res.addVertex(point(t0));
/* 382 */       res.addVertex(point(t1));
/* 384 */       return res;
/*     */     } 
/* 388 */     res.addVertex(point(t0));
/* 390 */     if (ind1 > ind0) {
/* 392 */       for (int n = ind0 + 1; n <= ind1; n++)
/* 393 */         res.addVertex(this.vertices.get(n)); 
/*     */     } else {
/*     */       int n;
/* 396 */       for (n = ind0 + 1; n < indMax; n++)
/* 397 */         res.addVertex(this.vertices.get(n)); 
/* 400 */       for (n = 0; n <= ind1; n++)
/* 401 */         res.addVertex(this.vertices.get(n)); 
/*     */     } 
/* 405 */     res.addVertex(point(t1));
/* 408 */     return res;
/*     */   }
/*     */   
/*     */   public LinearRing2D transform(AffineTransform2D trans) {
/* 418 */     Point2D[] pts = new Point2D[this.vertices.size()];
/* 419 */     for (int i = 0; i < this.vertices.size(); i++)
/* 420 */       pts[i] = trans.transform(this.vertices.get(i)); 
/* 421 */     return new LinearRing2D(pts);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 431 */     if (this.vertices.size() < 2)
/* 432 */       return path; 
/* 436 */     Point2D p0 = lastPoint();
/* 437 */     path.moveTo((float)p0.x(), (float)p0.y());
/* 440 */     for (Point2D point : this.vertices)
/* 441 */       path.lineTo((float)point.x(), (float)point.y()); 
/* 444 */     path.closePath();
/* 446 */     return path;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 457 */     if (this == obj)
/* 458 */       return true; 
/* 460 */     if (!(obj instanceof LinearRing2D))
/* 461 */       return false; 
/* 462 */     LinearRing2D ring = (LinearRing2D)obj;
/* 464 */     if (this.vertices.size() != ring.vertices.size())
/* 465 */       return false; 
/* 467 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 468 */       if (!((Point2D)this.vertices.get(i)).almostEquals((GeometricObject2D)ring.vertices.get(i), eps))
/* 469 */         return false; 
/*     */     } 
/* 470 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 475 */     if (!(object instanceof LinearRing2D))
/* 476 */       return false; 
/* 477 */     LinearRing2D ring = (LinearRing2D)object;
/* 479 */     if (this.vertices.size() != ring.vertices.size())
/* 480 */       return false; 
/* 481 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 482 */       if (!((Point2D)this.vertices.get(i)).equals(ring.vertices.get(i)))
/* 483 */         return false; 
/*     */     } 
/* 484 */     return true;
/*     */   }
/*     */   
/*     */   public LinearRing2D clone() {
/* 493 */     ArrayList<Point2D> array = new ArrayList<Point2D>(this.vertices.size());
/* 494 */     for (Point2D point : this.vertices)
/* 495 */       array.add(point.clone()); 
/* 496 */     return new LinearRing2D(array);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\LinearRing2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */