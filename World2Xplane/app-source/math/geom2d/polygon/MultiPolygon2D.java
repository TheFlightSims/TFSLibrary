/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContourArray2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class MultiPolygon2D implements Domain2D, Polygon2D {
/*     */   public static MultiPolygon2D create(Collection<LinearRing2D> rings) {
/*  33 */     return new MultiPolygon2D(rings);
/*     */   }
/*     */   
/*     */   public static MultiPolygon2D create(LinearRing2D... rings) {
/*  37 */     return new MultiPolygon2D(rings);
/*     */   }
/*     */   
/*  44 */   ArrayList<LinearRing2D> rings = new ArrayList<LinearRing2D>();
/*     */   
/*     */   public MultiPolygon2D(LinearRing2D ring) {
/*  54 */     this.rings.add(ring);
/*     */   }
/*     */   
/*     */   public MultiPolygon2D(LinearRing2D... rings) {
/*     */     byte b;
/*     */     int i;
/*     */     LinearRing2D[] arrayOfLinearRing2D;
/*  58 */     for (i = (arrayOfLinearRing2D = rings).length, b = 0; b < i; ) {
/*  58 */       LinearRing2D ring = arrayOfLinearRing2D[b];
/*  59 */       this.rings.add(ring);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public MultiPolygon2D(SimplePolygon2D polygon) {
/*  63 */     this.rings.addAll(polygon.boundary().curves());
/*     */   }
/*     */   
/*     */   public MultiPolygon2D(Collection<LinearRing2D> lines) {
/*  67 */     this.rings.addAll(lines);
/*     */   }
/*     */   
/*     */   public void addRing(LinearRing2D ring) {
/*  74 */     this.rings.add(ring);
/*     */   }
/*     */   
/*     */   public void insertRing(int index, LinearRing2D ring) {
/*  78 */     this.rings.add(index, ring);
/*     */   }
/*     */   
/*     */   public void removeRing(LinearRing2D ring) {
/*  82 */     this.rings.remove(ring);
/*     */   }
/*     */   
/*     */   public void clearRings() {
/*  86 */     this.rings.clear();
/*     */   }
/*     */   
/*     */   public LinearRing2D getRing(int index) {
/*  90 */     return this.rings.get(index);
/*     */   }
/*     */   
/*     */   public void setRing(int index, LinearRing2D ring) {
/*  94 */     this.rings.set(index, ring);
/*     */   }
/*     */   
/*     */   public int ringNumber() {
/*  98 */     return this.rings.size();
/*     */   }
/*     */   
/*     */   public double area() {
/* 111 */     return Polygons2D.computeArea(this);
/*     */   }
/*     */   
/*     */   public Point2D centroid() {
/* 120 */     return Polygons2D.computeCentroid(this);
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> edges() {
/* 124 */     int nEdges = edgeNumber();
/* 125 */     ArrayList<LineSegment2D> edges = new ArrayList<LineSegment2D>(nEdges);
/* 126 */     for (LinearRing2D ring : this.rings)
/* 127 */       edges.addAll(ring.edges()); 
/* 128 */     return edges;
/*     */   }
/*     */   
/*     */   public int edgeNumber() {
/* 132 */     int count = 0;
/* 133 */     for (LinearRing2D ring : this.rings)
/* 134 */       count += ring.vertexNumber(); 
/* 135 */     return count;
/*     */   }
/*     */   
/*     */   public Collection<Point2D> vertices() {
/* 139 */     int nv = vertexNumber();
/* 140 */     ArrayList<Point2D> points = new ArrayList<Point2D>(nv);
/* 141 */     for (LinearRing2D ring : this.rings)
/* 142 */       points.addAll(ring.vertices()); 
/* 143 */     return points;
/*     */   }
/*     */   
/*     */   public Point2D vertex(int i) {
/* 152 */     int count = 0;
/* 153 */     LinearRing2D boundary = null;
/* 155 */     for (LinearRing2D ring : this.rings) {
/* 156 */       int nv = ring.vertexNumber();
/* 157 */       if (count + nv > i) {
/* 158 */         boundary = ring;
/*     */         break;
/*     */       } 
/* 161 */       count += nv;
/*     */     } 
/* 164 */     if (boundary == null)
/* 165 */       throw new IndexOutOfBoundsException(); 
/* 167 */     return boundary.vertex(i - count);
/*     */   }
/*     */   
/*     */   public void setVertex(int i, Point2D point) {
/* 176 */     int count = 0;
/* 177 */     LinearRing2D boundary = null;
/* 179 */     for (LinearRing2D ring : this.rings) {
/* 180 */       int nv = ring.vertexNumber();
/* 181 */       if (count + nv > i) {
/* 182 */         boundary = ring;
/*     */         break;
/*     */       } 
/* 185 */       count += nv;
/*     */     } 
/* 188 */     if (boundary == null)
/* 189 */       throw new IndexOutOfBoundsException(); 
/* 191 */     boundary.setVertex(i - count, point);
/*     */   }
/*     */   
/*     */   public void addVertex(Point2D position) {
/* 202 */     if (this.rings.size() == 0)
/* 203 */       throw new RuntimeException("Can not add a vertex to a multipolygon with no ring"); 
/* 205 */     LinearRing2D ring = this.rings.get(this.rings.size() - 1);
/* 206 */     ring.addVertex(position);
/*     */   }
/*     */   
/*     */   public void insertVertex(int index, Point2D point) {
/* 217 */     if (this.rings.size() == 0)
/* 218 */       throw new RuntimeException("Can not add a vertex to a multipolygon with no ring"); 
/* 222 */     int nv = vertexNumber();
/* 223 */     if (nv <= index)
/* 224 */       throw new IllegalArgumentException("Can not insert vertex at position " + 
/* 225 */           index + " (max is " + nv + ")"); 
/* 229 */     int count = 0;
/* 230 */     LinearRing2D boundary = null;
/* 232 */     for (LinearRing2D ring : this.rings) {
/* 233 */       nv = ring.vertexNumber();
/* 234 */       if (count + nv > index) {
/* 235 */         boundary = ring;
/*     */         break;
/*     */       } 
/* 238 */       count += nv;
/*     */     } 
/* 241 */     if (boundary == null)
/* 242 */       throw new IndexOutOfBoundsException(); 
/* 244 */     boundary.insertVertex(index - count, point);
/*     */   }
/*     */   
/*     */   public void removeVertex(int i) {
/* 253 */     int count = 0;
/* 254 */     LinearRing2D boundary = null;
/* 256 */     for (LinearRing2D ring : this.rings) {
/* 257 */       int nv = ring.vertexNumber();
/* 258 */       if (count + nv > i) {
/* 259 */         boundary = ring;
/*     */         break;
/*     */       } 
/* 262 */       count += nv;
/*     */     } 
/* 265 */     if (boundary == null)
/* 266 */       throw new IndexOutOfBoundsException(); 
/* 268 */     boundary.removeVertex(i - count);
/*     */   }
/*     */   
/*     */   public int vertexNumber() {
/* 277 */     int count = 0;
/* 278 */     for (LinearRing2D ring : this.rings)
/* 279 */       count += ring.vertexNumber(); 
/* 280 */     return count;
/*     */   }
/*     */   
/*     */   public int closestVertexIndex(Point2D point) {
/* 287 */     double minDist = Double.POSITIVE_INFINITY;
/* 288 */     int index = -1;
/* 290 */     int i = 0;
/* 291 */     for (LinearRing2D ring : this.rings) {
/* 292 */       for (Point2D vertex : ring.vertices()) {
/* 293 */         double dist = vertex.distance(point);
/* 294 */         if (dist < minDist) {
/* 295 */           index = i;
/* 296 */           minDist = dist;
/*     */         } 
/* 298 */         i++;
/*     */       } 
/*     */     } 
/* 303 */     return index;
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D transform(CircleInversion2D inv) {
/* 314 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D(
/* 315 */         (CirculinearBoundary2D)boundary().transform(inv).reverse());
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 322 */     return Polygons2D.createBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public Polygon2D asPolygon(int n) {
/* 329 */     return this;
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D<LinearRing2D> boundary() {
/* 333 */     return CirculinearContourArray2D.create(this.rings);
/*     */   }
/*     */   
/*     */   public Collection<LinearRing2D> contours() {
/* 340 */     return Collections.unmodifiableList(this.rings);
/*     */   }
/*     */   
/*     */   public Polygon2D complement() {
/* 345 */     ArrayList<LinearRing2D> reverseLines = 
/* 346 */       new ArrayList<LinearRing2D>(this.rings.size());
/* 349 */     for (LinearRing2D ring : this.rings)
/* 350 */       reverseLines.add(ring.reverse()); 
/* 353 */     return new MultiPolygon2D(reverseLines);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 361 */     Box2D box = new Box2D(
/* 362 */         Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 
/* 363 */         Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
/* 366 */     for (LinearRing2D ring : this.rings)
/* 367 */       box = box.union(ring.boundingBox()); 
/* 370 */     return box;
/*     */   }
/*     */   
/*     */   public Polygon2D clip(Box2D box) {
/* 377 */     return Polygons2D.clipPolygon(this, box);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 381 */     return Math.max(boundary().signedDistance(p), 0.0D);
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 385 */     return Math.max(boundary().signedDistance(x, y), 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 390 */     CirculinearContourArray2D<LinearRing2D> circulinearContourArray2D = boundary();
/* 391 */     if (!circulinearContourArray2D.isBounded())
/* 392 */       return false; 
/* 395 */     double area = 0.0D;
/* 396 */     for (LinearRing2D ring : this.rings)
/* 397 */       area += ring.area(); 
/* 400 */     return (area > 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 409 */     for (LinearRing2D ring : this.rings) {
/* 410 */       if (!ring.isEmpty())
/* 411 */         return false; 
/*     */     } 
/* 412 */     return true;
/*     */   }
/*     */   
/*     */   public MultiPolygon2D transform(AffineTransform2D trans) {
/* 417 */     ArrayList<LinearRing2D> transformed = 
/* 418 */       new ArrayList<LinearRing2D>(this.rings.size());
/* 421 */     for (LinearRing2D ring : this.rings)
/* 422 */       transformed.add(ring.transform(trans)); 
/* 425 */     return new MultiPolygon2D(transformed);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 429 */     double angle = 0.0D;
/* 430 */     for (LinearRing2D ring : this.rings)
/* 431 */       angle += ring.windingAngle(point); 
/* 433 */     double area = area();
/* 434 */     if (area > 0.0D)
/* 435 */       return (angle > Math.PI); 
/* 437 */     return (angle > -3.141592653589793D);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 442 */     return contains(new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 446 */     g2.draw(boundary().getGeneralPath());
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g) {
/* 450 */     g.fill(boundary().getGeneralPath());
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 461 */     if (this == obj)
/* 462 */       return true; 
/* 464 */     if (!(obj instanceof MultiPolygon2D))
/* 465 */       return false; 
/* 466 */     MultiPolygon2D polygon = (MultiPolygon2D)obj;
/* 469 */     if (polygon.rings.size() != this.rings.size())
/* 470 */       return false; 
/* 473 */     for (int i = 0; i < this.rings.size(); i++) {
/* 474 */       if (!((LinearRing2D)this.rings.get(i)).almostEquals((GeometricObject2D)polygon.rings.get(i), eps))
/* 475 */         return false; 
/*     */     } 
/* 477 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 485 */     if (this == obj)
/* 486 */       return true; 
/* 488 */     if (!(obj instanceof MultiPolygon2D))
/* 489 */       return false; 
/* 492 */     MultiPolygon2D polygon = (MultiPolygon2D)obj;
/* 493 */     if (polygon.rings.size() != this.rings.size())
/* 494 */       return false; 
/* 497 */     for (int i = 0; i < this.rings.size(); i++) {
/* 498 */       if (!((LinearRing2D)this.rings.get(i)).equals(polygon.rings.get(i)))
/* 499 */         return false; 
/*     */     } 
/* 501 */     return true;
/*     */   }
/*     */   
/*     */   public MultiPolygon2D clone() {
/* 507 */     ArrayList<LinearRing2D> array = new ArrayList<LinearRing2D>(this.rings.size());
/* 510 */     for (LinearRing2D ring : this.rings)
/* 511 */       array.add(ring.clone()); 
/* 514 */     return new MultiPolygon2D(array);
/*     */   }
/*     */   
/*     */   public MultiPolygon2D() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\MultiPolygon2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */