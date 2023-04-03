/*     */ package math.geom2d;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.BoundaryPolyCurve2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.ContourArray2D;
/*     */ import math.geom2d.line.AbstractLine2D;
/*     */ import math.geom2d.line.LineArc2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import math.geom2d.polygon.Polygon2D;
/*     */ import math.geom2d.polygon.Polygons2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class Box2D implements GeometricObject2D, Cloneable {
/*     */   @Deprecated
/*     */   public static Box2D create(double xmin, double xmax, double ymin, double ymax) {
/*  66 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Box2D create(Point2D p1, Point2D p2) {
/*  74 */     return new Box2D(p1, p2);
/*     */   }
/*     */   
/*  82 */   public static final Box2D UNIT_SQUARE_BOX = create(0.0D, 1.0D, 0.0D, 1.0D);
/*     */   
/*  89 */   public static final Box2D INFINITE_BOX = create(
/*  90 */       Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/*  91 */       Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   
/*  96 */   private double xmin = 0.0D;
/*     */   
/*  97 */   private double xmax = 0.0D;
/*     */   
/*  98 */   private double ymin = 0.0D;
/*     */   
/*  99 */   private double ymax = 0.0D;
/*     */   
/*     */   public Box2D() {
/* 106 */     this(0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Box2D(double x0, double x1, double y0, double y1) {
/* 113 */     this.xmin = x0;
/* 114 */     this.xmax = x1;
/* 115 */     this.ymin = y0;
/* 116 */     this.ymax = y1;
/*     */   }
/*     */   
/*     */   public Box2D(Rectangle2D rect) {
/* 122 */     this(rect.getX(), rect.getX() + rect.getWidth(), rect.getY(), rect.getY() + rect.getHeight());
/*     */   }
/*     */   
/*     */   public Box2D(Point2D p1, Point2D p2) {
/* 129 */     double x1 = p1.x();
/* 130 */     double y1 = p1.y();
/* 131 */     double x2 = p2.x();
/* 132 */     double y2 = p2.y();
/* 133 */     this.xmin = Math.min(x1, x2);
/* 134 */     this.xmax = Math.max(x1, x2);
/* 135 */     this.ymin = Math.min(y1, y2);
/* 136 */     this.ymax = Math.max(y1, y2);
/*     */   }
/*     */   
/*     */   public Box2D(Point2D point, double w, double h) {
/* 141 */     this(point.x(), point.x() + w, point.y(), point.y() + h);
/*     */   }
/*     */   
/*     */   public double getMinX() {
/* 148 */     return this.xmin;
/*     */   }
/*     */   
/*     */   public double getMinY() {
/* 152 */     return this.ymin;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/* 156 */     return this.xmax;
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/* 160 */     return this.ymax;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 164 */     return this.xmax - this.xmin;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 168 */     return this.ymax - this.ymin;
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 173 */     if (Double.isInfinite(this.xmin))
/* 174 */       return false; 
/* 175 */     if (Double.isInfinite(this.ymin))
/* 176 */       return false; 
/* 177 */     if (Double.isInfinite(this.xmax))
/* 178 */       return false; 
/* 179 */     if (Double.isInfinite(this.ymax))
/* 180 */       return false; 
/* 181 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 191 */     double x = point.x();
/* 192 */     double y = point.y();
/* 193 */     if (x < this.xmin)
/* 194 */       return false; 
/* 195 */     if (y < this.ymin)
/* 196 */       return false; 
/* 197 */     if (x > this.xmax)
/* 198 */       return false; 
/* 199 */     if (y > this.ymax)
/* 200 */       return false; 
/* 201 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 208 */     if (x < this.xmin)
/* 209 */       return false; 
/* 210 */     if (y < this.ymin)
/* 211 */       return false; 
/* 212 */     if (x > this.xmax)
/* 213 */       return false; 
/* 214 */     if (y > this.ymax)
/* 215 */       return false; 
/* 216 */     return true;
/*     */   }
/*     */   
/*     */   public boolean containsBounds(Shape2D shape) {
/* 227 */     if (!shape.isBounded())
/* 228 */       return false; 
/* 229 */     for (Point2D point : shape.boundingBox().vertices()) {
/* 230 */       if (!contains(point))
/* 231 */         return false; 
/*     */     } 
/* 233 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<StraightLine2D> clippingLines() {
/* 247 */     ArrayList<StraightLine2D> lines = new ArrayList<StraightLine2D>(4);
/* 249 */     if (isFinite(this.ymin))
/* 250 */       lines.add(new StraightLine2D(0.0D, this.ymin, 1.0D, 0.0D)); 
/* 251 */     if (isFinite(this.xmax))
/* 252 */       lines.add(new StraightLine2D(this.xmax, 0.0D, 0.0D, 1.0D)); 
/* 253 */     if (isFinite(this.ymax))
/* 254 */       lines.add(new StraightLine2D(0.0D, this.ymax, -1.0D, 0.0D)); 
/* 255 */     if (isFinite(this.xmin))
/* 256 */       lines.add(new StraightLine2D(this.xmin, 0.0D, 0.0D, -1.0D)); 
/* 257 */     return lines;
/*     */   }
/*     */   
/*     */   public Collection<LinearShape2D> edges() {
/* 265 */     ArrayList<LinearShape2D> edges = new ArrayList<LinearShape2D>(4);
/* 267 */     if (isBounded()) {
/* 268 */       edges.add(new LineSegment2D(this.xmin, this.ymin, this.xmax, this.ymin));
/* 269 */       edges.add(new LineSegment2D(this.xmax, this.ymin, this.xmax, this.ymax));
/* 270 */       edges.add(new LineSegment2D(this.xmax, this.ymax, this.xmin, this.ymax));
/* 271 */       edges.add(new LineSegment2D(this.xmin, this.ymax, this.xmin, this.ymin));
/* 272 */       return edges;
/*     */     } 
/* 275 */     if (!Double.isInfinite(this.ymin))
/* 276 */       if (Double.isInfinite(this.xmin) && Double.isInfinite(this.xmax)) {
/* 277 */         edges.add(new StraightLine2D(0.0D, this.ymin, 1.0D, 0.0D));
/* 278 */       } else if (!Double.isInfinite(this.xmin) && !Double.isInfinite(this.xmax)) {
/* 279 */         edges.add(new LineSegment2D(this.xmin, this.ymin, this.xmax, this.ymin));
/*     */       } else {
/* 281 */         edges.add(new LineArc2D(0.0D, this.ymin, 1.0D, 0.0D, this.xmin, this.xmax));
/*     */       }  
/* 284 */     if (!Double.isInfinite(this.xmax))
/* 285 */       if (Double.isInfinite(this.ymin) && Double.isInfinite(this.ymax)) {
/* 286 */         edges.add(new StraightLine2D(this.xmax, 0.0D, 0.0D, 1.0D));
/* 287 */       } else if (!Double.isInfinite(this.ymin) && !Double.isInfinite(this.ymax)) {
/* 288 */         edges.add(new LineSegment2D(this.xmax, this.ymin, this.xmax, this.ymax));
/*     */       } else {
/* 290 */         edges.add(new LineArc2D(this.xmax, 0.0D, 0.0D, 1.0D, this.ymin, this.ymax));
/*     */       }  
/* 293 */     if (!Double.isInfinite(this.ymax))
/* 294 */       if (Double.isInfinite(this.xmin) && Double.isInfinite(this.xmax)) {
/* 295 */         edges.add(new StraightLine2D(0.0D, this.ymax, 1.0D, 0.0D));
/* 296 */       } else if (!Double.isInfinite(this.xmin) && !Double.isInfinite(this.xmax)) {
/* 297 */         edges.add(new LineSegment2D(this.xmax, this.ymax, this.xmin, this.ymax));
/*     */       } else {
/* 299 */         edges.add((new LineArc2D(0.0D, this.ymin, 1.0D, 0.0D, this.xmin, this.xmax)).reverse());
/*     */       }  
/* 302 */     if (!Double.isInfinite(this.xmin))
/* 303 */       if (Double.isInfinite(this.ymin) && Double.isInfinite(this.ymax)) {
/* 304 */         edges.add(new StraightLine2D(this.xmin, 0.0D, 0.0D, -1.0D));
/* 305 */       } else if (!Double.isInfinite(this.ymin) && !Double.isInfinite(this.ymax)) {
/* 306 */         edges.add(new LineSegment2D(this.xmin, this.ymax, this.xmin, this.ymin));
/*     */       } else {
/* 308 */         edges.add((new LineArc2D(this.xmin, 0.0D, 0.0D, 1.0D, this.ymin, this.ymax)).reverse());
/*     */       }  
/* 311 */     return edges;
/*     */   }
/*     */   
/*     */   public Boundary2D boundary() {
/* 324 */     if (isBounded()) {
/* 325 */       Point2D[] pts = new Point2D[4];
/* 326 */       pts[0] = new Point2D(this.xmin, this.ymin);
/* 327 */       pts[1] = new Point2D(this.xmax, this.ymin);
/* 328 */       pts[2] = new Point2D(this.xmax, this.ymax);
/* 329 */       pts[3] = new Point2D(this.xmin, this.ymax);
/* 330 */       return (Boundary2D)new LinearRing2D(pts);
/*     */     } 
/* 334 */     boolean bx0 = !Double.isInfinite(this.xmin);
/* 335 */     boolean bx1 = !Double.isInfinite(this.xmax);
/* 336 */     boolean by0 = !Double.isInfinite(this.ymin);
/* 337 */     boolean by1 = !Double.isInfinite(this.ymax);
/* 340 */     if (!bx0 && !bx1) {
/* 341 */       if (!by0 && !by1)
/* 342 */         return (Boundary2D)new ContourArray2D(); 
/* 343 */       if (by0 && !by1)
/* 344 */         return (Boundary2D)new StraightLine2D(0.0D, this.ymin, 1.0D, 0.0D); 
/* 345 */       if (!by0 && by1)
/* 346 */         return (Boundary2D)new StraightLine2D(0.0D, this.ymax, -1.0D, 0.0D); 
/* 347 */       return (Boundary2D)new ContourArray2D((Contour2D[])new StraightLine2D[] { new StraightLine2D(0.0D, this.ymin, 1.0D, 0.0D), 
/* 349 */             new StraightLine2D(0.0D, this.ymax, -1.0D, 0.0D) });
/*     */     } 
/* 353 */     if (!by0 && !by1) {
/* 354 */       if (!bx0 && !bx1)
/* 355 */         return (Boundary2D)new ContourArray2D(); 
/* 356 */       if (bx0 && !bx1)
/* 357 */         return (Boundary2D)new StraightLine2D(this.xmin, 0.0D, 0.0D, -1.0D); 
/* 358 */       if (!bx0 && bx1)
/* 359 */         return (Boundary2D)new StraightLine2D(this.xmax, 0.0D, 0.0D, 1.0D); 
/* 360 */       return (Boundary2D)new ContourArray2D((Contour2D[])new StraightLine2D[] { new StraightLine2D(this.xmin, 0.0D, 0.0D, -1.0D), 
/* 362 */             new StraightLine2D(this.xmax, 0.0D, 0.0D, 1.0D) });
/*     */     } 
/* 367 */     if (bx0 && by0)
/* 368 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new LineArc2D[] { new LineArc2D(this.xmin, this.ymin, 0.0D, -1.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 370 */             new LineArc2D(this.xmin, this.ymin, 1.0D, 0.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 372 */     if (bx1 && by0)
/* 373 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new LineArc2D[] { new LineArc2D(this.xmax, this.ymin, 1.0D, 0.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 375 */             new LineArc2D(this.xmax, this.ymin, 0.0D, 1.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 377 */     if (bx1 && by1)
/* 378 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new LineArc2D[] { new LineArc2D(this.xmax, this.ymax, 0.0D, 1.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 380 */             new LineArc2D(this.xmax, this.ymax, -1.0D, 0.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 382 */     if (bx0 && by1)
/* 383 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new LineArc2D[] { new LineArc2D(this.xmin, this.ymax, -1.0D, 0.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 385 */             new LineArc2D(this.xmin, this.ymax, 0.0D, -1.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 389 */     if (bx0)
/* 390 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new AbstractLine2D[] { (AbstractLine2D)new LineArc2D(this.xmin, this.ymax, -1.0D, 0.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 392 */             (AbstractLine2D)new LineSegment2D(this.xmin, this.ymax, this.xmin, this.ymin), 
/* 393 */             (AbstractLine2D)new LineArc2D(this.xmin, this.ymin, 1.0D, 0.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 395 */     if (bx1)
/* 396 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new AbstractLine2D[] { (AbstractLine2D)new LineArc2D(this.xmax, this.ymin, 1.0D, 0.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 398 */             (AbstractLine2D)new LineSegment2D(this.xmax, this.ymin, this.xmax, this.ymax), 
/* 399 */             (AbstractLine2D)new LineArc2D(this.xmax, this.ymax, -1.0D, 0.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 401 */     if (by0)
/* 402 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new AbstractLine2D[] { (AbstractLine2D)new LineArc2D(this.xmin, this.ymin, 0.0D, -1.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 404 */             (AbstractLine2D)new LineSegment2D(this.xmin, this.ymin, this.xmax, this.ymin), 
/* 405 */             (AbstractLine2D)new LineArc2D(this.xmax, this.ymin, 0.0D, 1.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 407 */     if (by1)
/* 408 */       return (Boundary2D)new BoundaryPolyCurve2D((ContinuousOrientedCurve2D[])new AbstractLine2D[] { (AbstractLine2D)new LineArc2D(this.xmax, this.ymax, 0.0D, 1.0D, Double.NEGATIVE_INFINITY, 0.0D), 
/* 410 */             (AbstractLine2D)new LineSegment2D(this.xmax, this.ymax, this.xmin, this.ymax), 
/* 411 */             (AbstractLine2D)new LineArc2D(this.xmin, this.ymax, 0.0D, -1.0D, 0.0D, Double.POSITIVE_INFINITY) }); 
/* 413 */     return null;
/*     */   }
/*     */   
/*     */   public Collection<Point2D> vertices() {
/* 417 */     ArrayList<Point2D> points = new ArrayList<Point2D>(4);
/* 418 */     boolean bx0 = isFinite(this.xmin);
/* 419 */     boolean bx1 = isFinite(this.xmax);
/* 420 */     boolean by0 = isFinite(this.ymin);
/* 421 */     boolean by1 = isFinite(this.ymax);
/* 422 */     if (bx0 && by0)
/* 423 */       points.add(new Point2D(this.xmin, this.ymin)); 
/* 424 */     if (bx1 && by0)
/* 425 */       points.add(new Point2D(this.xmax, this.ymin)); 
/* 426 */     if (bx0 && by1)
/* 427 */       points.add(new Point2D(this.xmin, this.ymax)); 
/* 428 */     if (bx1 && by1)
/* 429 */       points.add(new Point2D(this.xmax, this.ymax)); 
/* 430 */     return points;
/*     */   }
/*     */   
/*     */   private static final boolean isFinite(double value) {
/* 434 */     if (Double.isInfinite(value))
/* 435 */       return false; 
/* 436 */     if (Double.isNaN(value))
/* 437 */       return false; 
/* 438 */     return true;
/*     */   }
/*     */   
/*     */   public int vertexNumber() {
/* 443 */     return vertices().size();
/*     */   }
/*     */   
/*     */   public Box2D union(Box2D box) {
/* 456 */     double xmin = Math.min(this.xmin, box.xmin);
/* 457 */     double xmax = Math.max(this.xmax, box.xmax);
/* 458 */     double ymin = Math.min(this.ymin, box.ymin);
/* 459 */     double ymax = Math.max(this.ymax, box.ymax);
/* 460 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public Box2D intersection(Box2D box) {
/* 471 */     double xmin = Math.max(this.xmin, box.xmin);
/* 472 */     double xmax = Math.min(this.xmax, box.xmax);
/* 473 */     double ymin = Math.max(this.ymin, box.ymin);
/* 474 */     double ymax = Math.min(this.ymax, box.ymax);
/* 475 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public Box2D merge(Box2D box) {
/* 485 */     this.xmin = Math.min(this.xmin, box.xmin);
/* 486 */     this.xmax = Math.max(this.xmax, box.xmax);
/* 487 */     this.ymin = Math.min(this.ymin, box.ymin);
/* 488 */     this.ymax = Math.max(this.ymax, box.ymax);
/* 489 */     return this;
/*     */   }
/*     */   
/*     */   public Box2D clip(Box2D box) {
/* 499 */     this.xmin = Math.max(this.xmin, box.xmin);
/* 500 */     this.xmax = Math.min(this.xmax, box.xmax);
/* 501 */     this.ymin = Math.max(this.ymin, box.ymin);
/* 502 */     this.ymax = Math.min(this.ymax, box.ymax);
/* 503 */     return this;
/*     */   }
/*     */   
/*     */   public Box2D transform(AffineTransform2D trans) {
/* 512 */     if (!isBounded())
/* 513 */       return INFINITE_BOX; 
/* 516 */     double xmin = Double.POSITIVE_INFINITY;
/* 517 */     double xmax = Double.NEGATIVE_INFINITY;
/* 518 */     double ymin = Double.POSITIVE_INFINITY;
/* 519 */     double ymax = Double.NEGATIVE_INFINITY;
/* 522 */     for (Point2D point : vertices()) {
/* 523 */       point = point.transform(trans);
/* 524 */       xmin = Math.min(xmin, point.x());
/* 525 */       ymin = Math.min(ymin, point.y());
/* 526 */       xmax = Math.max(xmax, point.x());
/* 527 */       ymax = Math.max(ymax, point.y());
/*     */     } 
/* 531 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public Rectangle asAwtRectangle() {
/* 543 */     int xr = (int)Math.floor(this.xmin);
/* 544 */     int yr = (int)Math.floor(this.ymin);
/* 545 */     int wr = (int)Math.ceil(this.xmax - xr);
/* 546 */     int hr = (int)Math.ceil(this.ymax - yr);
/* 547 */     return new Rectangle(xr, yr, wr, hr);
/*     */   }
/*     */   
/*     */   public Rectangle2D asAwtRectangle2D() {
/* 557 */     return new Rectangle2D.Double(this.xmin, this.ymin, this.xmax - this.xmin, this.ymax - this.ymin);
/*     */   }
/*     */   
/*     */   public Polygon2D asRectangle() {
/* 566 */     return (Polygon2D)Polygons2D.createRectangle(this.xmin, this.ymin, this.xmax, this.ymax);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 575 */     if (!isBounded())
/* 576 */       throw new UnboundedBox2DException(this); 
/* 577 */     boundary().draw(g2);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 586 */     if (!isBounded())
/* 587 */       throw new UnboundedBox2DException(this); 
/* 588 */     boundary().fill(g2);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Box2D boundingBox() {
/* 596 */     return new Box2D(this.xmin, this.xmax, this.ymin, this.ymax);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 604 */     if (this == obj)
/* 605 */       return true; 
/* 608 */     if (!(obj instanceof Box2D))
/* 609 */       return false; 
/* 610 */     Box2D box = (Box2D)obj;
/* 612 */     if (Math.abs(this.xmin - box.xmin) > eps)
/* 613 */       return false; 
/* 614 */     if (Math.abs(this.xmax - box.xmax) > eps)
/* 615 */       return false; 
/* 616 */     if (Math.abs(this.ymin - box.ymin) > eps)
/* 617 */       return false; 
/* 618 */     if (Math.abs(this.ymax - box.ymax) > eps)
/* 619 */       return false; 
/* 621 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 629 */     return new String("Box2D(" + this.xmin + "," + this.xmax + "," + this.ymin + "," + this.ymax + ")");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 638 */     if (this == obj)
/* 639 */       return true; 
/* 642 */     if (!(obj instanceof Box2D))
/* 643 */       return false; 
/* 644 */     Box2D that = (Box2D)obj;
/* 647 */     if (!EqualUtils.areEqual(this.xmin, that.xmin))
/* 648 */       return false; 
/* 649 */     if (!EqualUtils.areEqual(this.xmax, that.xmax))
/* 650 */       return false; 
/* 651 */     if (!EqualUtils.areEqual(this.ymin, that.ymin))
/* 652 */       return false; 
/* 653 */     if (!EqualUtils.areEqual(this.ymax, that.ymax))
/* 654 */       return false; 
/* 656 */     return true;
/*     */   }
/*     */   
/*     */   public Box2D clone() {
/* 661 */     return new Box2D(this.xmin, this.xmax, this.ymin, this.ymax);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\Box2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */