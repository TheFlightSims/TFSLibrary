/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequenceFactory;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ public class LiteShape implements Shape, Cloneable {
/*     */   private Geometry geometry;
/*     */   
/*  58 */   private AffineTransform affineTransform = null;
/*     */   
/*     */   private boolean generalize = false;
/*     */   
/*  60 */   private double maxDistance = 1.0D;
/*     */   
/*  63 */   private LineIterator lineIterator = new LineIterator();
/*     */   
/*  64 */   private GeomCollectionIterator collIterator = new GeomCollectionIterator();
/*     */   
/*     */   private float xScale;
/*     */   
/*     */   private float yScale;
/*     */   
/*     */   private GeometryFactory geomFac;
/*     */   
/*     */   public LiteShape(Geometry geom, AffineTransform at, boolean generalize, double maxDistance) {
/*  83 */     this(geom, at, generalize);
/*  84 */     this.maxDistance = maxDistance;
/*     */   }
/*     */   
/*     */   public LiteShape(Geometry geom, AffineTransform at, boolean generalize) {
/*  97 */     if (geom != null)
/*  98 */       this.geometry = getGeometryFactory().createGeometry(geom); 
/*  99 */     this.affineTransform = at;
/* 100 */     this.generalize = generalize;
/* 101 */     if (at == null) {
/* 102 */       this.yScale = this.xScale = 1.0F;
/*     */       return;
/*     */     } 
/* 105 */     this.xScale = (float)Math.sqrt(at.getScaleX() * at.getScaleX() + at.getShearX() * at.getShearX());
/* 108 */     this.yScale = (float)Math.sqrt(at.getScaleY() * at.getScaleY() + at.getShearY() * at.getShearY());
/*     */   }
/*     */   
/*     */   private GeometryFactory getGeometryFactory() {
/* 114 */     if (this.geomFac == null)
/* 115 */       this.geomFac = new GeometryFactory((CoordinateSequenceFactory)new PackedCoordinateSequenceFactory()); 
/* 118 */     return this.geomFac;
/*     */   }
/*     */   
/*     */   public void setGeometry(Geometry g) {
/* 128 */     this.geometry = (Geometry)g.clone();
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/* 165 */     Geometry rect = rectangleToGeometry(r);
/* 167 */     return this.geometry.contains(rect);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 181 */     Coordinate coord = new Coordinate(p.getX(), p.getY());
/* 182 */     Point point = this.geometry.getFactory().createPoint(coord);
/* 184 */     return this.geometry.contains((Geometry)point);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 198 */     Coordinate coord = new Coordinate(x, y);
/* 199 */     Point point = this.geometry.getFactory().createPoint(coord);
/* 201 */     return this.geometry.contains((Geometry)point);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 247 */     Geometry rect = createRectangle(x, y, w, h);
/* 249 */     return this.geometry.contains(rect);
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 270 */     Coordinate[] coords = this.geometry.getEnvelope().getCoordinates();
/* 287 */     double x2 = (coords[0]).x, x1 = x2;
/* 288 */     double y2 = (coords[0]).y, y1 = y2;
/* 290 */     for (int i = 1; i < 3; i++) {
/* 291 */       double x = (coords[i]).x;
/* 292 */       double y = (coords[i]).y;
/* 294 */       if (x < x1)
/* 295 */         x1 = x; 
/* 298 */       if (x > x2)
/* 299 */         x2 = x; 
/* 302 */       if (y < y1)
/* 303 */         y1 = y; 
/* 306 */       if (y > y2)
/* 307 */         y2 = y; 
/*     */     } 
/* 311 */     x1 = Math.ceil(x1);
/* 312 */     x2 = Math.floor(x2);
/* 313 */     y1 = Math.ceil(y1);
/* 314 */     y2 = Math.floor(y2);
/* 316 */     return new Rectangle((int)x1, (int)y1, (int)(x2 - x1), (int)(y2 - y1));
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 338 */     Envelope env = this.geometry.getEnvelopeInternal();
/* 339 */     return new Rectangle2D.Double(env.getMinX(), env.getMinY(), env.getWidth(), env.getHeight());
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 377 */     AbstractLiteIterator pi = null;
/* 379 */     AffineTransform combined = null;
/* 381 */     if (this.affineTransform == null) {
/* 382 */       combined = at;
/* 383 */     } else if (at == null || at.isIdentity()) {
/* 384 */       combined = this.affineTransform;
/*     */     } else {
/* 386 */       combined = new AffineTransform(this.affineTransform);
/* 387 */       combined.concatenate(at);
/*     */     } 
/* 391 */     if (this.geometry instanceof Point)
/* 392 */       pi = new PointIterator((Point)this.geometry, combined); 
/* 395 */     if (this.geometry instanceof Polygon) {
/* 397 */       pi = new PolygonIterator((Polygon)this.geometry, combined, this.generalize, this.maxDistance);
/* 399 */     } else if (this.geometry instanceof LinearRing) {
/* 400 */       this.lineIterator.init((LineString)this.geometry, combined, this.generalize, (float)this.maxDistance);
/* 402 */       pi = this.lineIterator;
/* 403 */     } else if (this.geometry instanceof LineString) {
/* 408 */       if (combined == this.affineTransform) {
/* 409 */         this.lineIterator.init((LineString)this.geometry, combined, this.generalize, (float)this.maxDistance, this.xScale, this.yScale);
/*     */       } else {
/* 412 */         this.lineIterator.init((LineString)this.geometry, combined, this.generalize, (float)this.maxDistance);
/*     */       } 
/* 414 */       pi = this.lineIterator;
/* 415 */     } else if (this.geometry instanceof GeometryCollection) {
/* 416 */       this.collIterator.init((GeometryCollection)this.geometry, combined, this.generalize, this.maxDistance);
/* 418 */       pi = this.collIterator;
/*     */     } 
/* 421 */     return pi;
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 480 */     return getPathIterator(at);
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 513 */     Geometry rect = rectangleToGeometry(r);
/* 515 */     return this.geometry.intersects(rect);
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/* 558 */     Geometry rect = createRectangle(x, y, w, h);
/* 560 */     return this.geometry.intersects(rect);
/*     */   }
/*     */   
/*     */   private Geometry rectangleToGeometry(Rectangle2D r) {
/* 571 */     return createRectangle(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
/*     */   }
/*     */   
/*     */   private Geometry createRectangle(double x, double y, double w, double h) {
/* 587 */     Coordinate[] coords = { new Coordinate(x, y), new Coordinate(x, y + h), new Coordinate(x + w, y + h), new Coordinate(x + w, y), new Coordinate(x, y) };
/* 592 */     LinearRing lr = this.geometry.getFactory().createLinearRing(coords);
/* 594 */     return (Geometry)this.geometry.getFactory().createPolygon(lr, null);
/*     */   }
/*     */   
/*     */   public AffineTransform getAffineTransform() {
/* 601 */     return this.affineTransform;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/* 605 */     return this.geometry;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\LiteShape.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */