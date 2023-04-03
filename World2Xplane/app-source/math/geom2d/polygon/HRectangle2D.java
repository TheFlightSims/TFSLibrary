/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContour2D;
/*     */ import math.geom2d.circulinear.CirculinearContourArray2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ @Deprecated
/*     */ public class HRectangle2D extends Rectangle2D.Double implements Polygon2D {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public HRectangle2D(double x0, double y0, double w, double h) {
/*  70 */     super(x0, y0, w, h);
/*     */   }
/*     */   
/*     */   public HRectangle2D() {
/*  75 */     super(0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public HRectangle2D(Rectangle2D rect) {
/*  80 */     super(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/*     */   }
/*     */   
/*     */   public HRectangle2D(Point2D point, double w, double h) {
/*  85 */     super(point.getX(), point.getY(), w, h);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> vertices() {
/*  93 */     ArrayList<Point2D> points = new ArrayList<Point2D>(4);
/*  94 */     points.add(new Point2D(this.x, this.y));
/*  95 */     points.add(new Point2D(this.x + this.width, this.y));
/*  96 */     points.add(new Point2D(this.x + this.width, this.y + this.height));
/*  97 */     points.add(new Point2D(this.x, this.y + this.height));
/*  98 */     return points;
/*     */   }
/*     */   
/*     */   public Point2D vertex(int i) {
/* 107 */     switch (i) {
/*     */       case 0:
/* 109 */         return new Point2D(this.x, this.y);
/*     */       case 1:
/* 111 */         return new Point2D(this.x + this.width, this.y);
/*     */       case 2:
/* 113 */         return new Point2D(this.x + this.width, this.y + this.height);
/*     */       case 3:
/* 115 */         return new Point2D(this.x, this.y + this.height);
/*     */     } 
/* 117 */     throw new IndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public void setVertex(int i, Point2D point) {
/* 122 */     throw new UnsupportedOperationException("Vertices of HRectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public void addVertex(Point2D point) {
/* 126 */     throw new UnsupportedOperationException("Vertices of HRectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public void insertVertex(int i, Point2D point) {
/* 130 */     throw new UnsupportedOperationException("Vertices of HRectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public void removeVertex(int i) {
/* 134 */     throw new UnsupportedOperationException("Vertices of HRectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public int vertexNumber() {
/* 143 */     return 4;
/*     */   }
/*     */   
/*     */   public int closestVertexIndex(Point2D point) {
/* 150 */     double minDist = Double.POSITIVE_INFINITY;
/* 151 */     int index = -1;
/* 153 */     int i = 0;
/* 154 */     for (Point2D vertex : vertices()) {
/* 155 */       double dist = vertex.distance(point);
/* 156 */       if (dist < minDist) {
/* 157 */         index = i;
/* 158 */         minDist = dist;
/*     */       } 
/* 160 */       i++;
/*     */     } 
/* 163 */     return index;
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> edges() {
/* 167 */     ArrayList<LineSegment2D> edges = new ArrayList<LineSegment2D>(4);
/* 168 */     edges.add(new LineSegment2D(this.x, this.y, this.x + this.width, this.y));
/* 169 */     edges.add(new LineSegment2D(this.x + this.width, this.y, this.x + this.width, this.y + this.height));
/* 170 */     edges.add(new LineSegment2D(this.x + this.width, this.y + this.height, this.x, this.y + this.height));
/* 171 */     edges.add(new LineSegment2D(this.x, this.y + this.height, this.x, this.y));
/* 172 */     return edges;
/*     */   }
/*     */   
/*     */   public int edgeNumber() {
/* 176 */     return 4;
/*     */   }
/*     */   
/*     */   public double area() {
/* 185 */     return Polygons2D.computeArea(this);
/*     */   }
/*     */   
/*     */   public Point2D centroid() {
/* 194 */     return Polygons2D.computeCentroid(this);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D transform(CircleInversion2D inv) {
/* 205 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D(
/* 206 */         (CirculinearBoundary2D)boundary().transform(inv));
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 213 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 214 */     return bc.computeBuffer((CirculinearCurve2D)boundary(), dist);
/*     */   }
/*     */   
/*     */   public Polygon2D asPolygon(int n) {
/* 224 */     return this;
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D<LinearRing2D> boundary() {
/* 228 */     return new CirculinearContourArray2D((CirculinearContour2D)asRing());
/*     */   }
/*     */   
/*     */   public Collection<LinearRing2D> contours() {
/* 235 */     ArrayList<LinearRing2D> rings = new ArrayList<LinearRing2D>(1);
/* 236 */     rings.add(asRing());
/* 237 */     return rings;
/*     */   }
/*     */   
/*     */   private LinearRing2D asRing() {
/* 241 */     Point2D[] pts = new Point2D[4];
/* 242 */     pts[0] = new Point2D(this.x, this.y);
/* 243 */     pts[1] = new Point2D(this.width + this.x, this.y);
/* 244 */     pts[2] = new Point2D(this.width + this.x, this.y + this.height);
/* 245 */     pts[3] = new Point2D(this.x, this.y + this.height);
/* 247 */     return new LinearRing2D(pts);
/*     */   }
/*     */   
/*     */   public Polygon2D complement() {
/* 251 */     Point2D[] pts = new Point2D[4];
/* 252 */     pts[0] = new Point2D(this.x, this.y);
/* 253 */     pts[1] = new Point2D(this.x, this.y + this.height);
/* 254 */     pts[2] = new Point2D(this.width + this.x, this.y + this.height);
/* 255 */     pts[3] = new Point2D(this.width + this.x, this.y);
/* 256 */     return new SimplePolygon2D(pts);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 268 */     return distance(p.getX(), p.getY());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 277 */     double dist = boundary().signedDistance(x, y);
/* 278 */     return Math.max(dist, 0.0D);
/*     */   }
/*     */   
/*     */   public HRectangle2D clip(Box2D box) {
/* 287 */     double xmin = Math.max(getMinX(), box.getMinX());
/* 288 */     double xmax = Math.min(getMaxX(), box.getMaxX());
/* 289 */     double ymin = Math.max(getMinY(), box.getMinY());
/* 290 */     double ymax = Math.min(getMaxY(), box.getMaxY());
/* 291 */     if (xmin > xmax || ymin > ymax)
/* 292 */       return new HRectangle2D(xmin, ymin, 0.0D, 0.0D); 
/* 294 */     return new HRectangle2D(xmin, xmax, xmax - xmin, ymax - ymin);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 299 */     return true;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 303 */     return new Box2D(getMinX(), getMaxX(), getMinY(), 
/* 304 */         getMaxY());
/*     */   }
/*     */   
/*     */   public SimplePolygon2D transform(AffineTransform2D trans) {
/* 311 */     int nPoints = 4;
/* 312 */     Point2D[] array = new Point2D[nPoints];
/* 313 */     Point2D[] res = new Point2D[nPoints];
/* 314 */     Iterator<Point2D> iter = vertices().iterator();
/* 315 */     for (int i = 0; i < nPoints; i++) {
/* 316 */       array[i] = iter.next();
/* 317 */       res[i] = new Point2D();
/*     */     } 
/* 320 */     trans.transform(array, res);
/* 321 */     return new SimplePolygon2D(res);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 325 */     g2.draw(boundary().getGeneralPath());
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 329 */     g2.fill(boundary().getGeneralPath());
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 341 */     if (this == obj)
/* 342 */       return true; 
/* 345 */     if (!(obj instanceof HRectangle2D))
/* 346 */       return false; 
/* 347 */     HRectangle2D rect = (HRectangle2D)obj;
/* 351 */     for (Point2D point : vertices()) {
/* 352 */       boolean ok = false;
/* 355 */       for (Point2D point2 : rect.vertices()) {
/* 356 */         if (point.almostEquals((GeometricObject2D)point2, eps))
/* 357 */           ok = true; 
/*     */       } 
/* 362 */       if (!ok)
/* 363 */         return false; 
/*     */     } 
/* 367 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 382 */     if (!(obj instanceof HRectangle2D))
/* 383 */       return false; 
/* 384 */     HRectangle2D rect = (HRectangle2D)obj;
/* 388 */     for (Point2D point : vertices()) {
/* 389 */       boolean ok = false;
/* 392 */       for (Point2D point2 : rect.vertices()) {
/* 393 */         if (point.equals(point2))
/* 394 */           ok = true; 
/*     */       } 
/* 399 */       if (!ok)
/* 400 */         return false; 
/*     */     } 
/* 404 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 408 */     return contains(p.getX(), p.getY());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\HRectangle2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */