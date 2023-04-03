/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class GeometryBuilder {
/*     */   private GeometryFactory geomFact;
/*     */   
/*     */   private CoordinateSequenceFactory csFact;
/*     */   
/*     */   public GeometryBuilder() {
/*  71 */     this(new GeometryFactory());
/*     */   }
/*     */   
/*     */   public GeometryBuilder(GeometryFactory geomFact) {
/*  80 */     this.geomFact = geomFact;
/*  81 */     this.csFact = geomFact.getCoordinateSequenceFactory();
/*     */   }
/*     */   
/*     */   public Point point() {
/*  90 */     return this.geomFact.createPoint(createCS(new double[0], 2));
/*     */   }
/*     */   
/*     */   public Point pointZ() {
/*  99 */     return this.geomFact.createPoint(createCS(new double[0], 3));
/*     */   }
/*     */   
/*     */   public Point point(double x) {
/* 109 */     return this.geomFact.createPoint(createCS(new double[] { x }, 1));
/*     */   }
/*     */   
/*     */   public Point point(double x, double y) {
/* 120 */     return this.geomFact.createPoint(createCS(new double[] { x, y }, 2));
/*     */   }
/*     */   
/*     */   public Point pointZ(double x, double y, double z) {
/* 132 */     return this.geomFact.createPoint(createCS(new double[] { x, y, z }, 3));
/*     */   }
/*     */   
/*     */   public LineString lineString() {
/* 141 */     return this.geomFact.createLineString(createCS(new double[0], 2));
/*     */   }
/*     */   
/*     */   public LineString lineStringZ() {
/* 150 */     return this.geomFact.createLineString(createCS(new double[0], 3));
/*     */   }
/*     */   
/*     */   public LineString lineString(double... ord) {
/* 160 */     return this.geomFact.createLineString(createCS(ord, 2));
/*     */   }
/*     */   
/*     */   public LineString lineStringZ(double... ord) {
/* 170 */     return this.geomFact.createLineString(createCS(ord, 3));
/*     */   }
/*     */   
/*     */   public LinearRing linearRing() {
/* 179 */     return this.geomFact.createLinearRing(createRingCS(new double[0], 2));
/*     */   }
/*     */   
/*     */   public LinearRing linearRingZ() {
/* 188 */     return this.geomFact.createLinearRing(createRingCS(new double[0], 3));
/*     */   }
/*     */   
/*     */   public LinearRing linearRing(double... ord) {
/* 199 */     return this.geomFact.createLinearRing(createRingCS(ord, 2));
/*     */   }
/*     */   
/*     */   public LinearRing linearRingZ(double... ord) {
/* 210 */     return this.geomFact.createLinearRing(createRingCS(ord, 3));
/*     */   }
/*     */   
/*     */   public Polygon polygon() {
/* 219 */     return this.geomFact.createPolygon(linearRing(), null);
/*     */   }
/*     */   
/*     */   public Polygon polygonZ() {
/* 228 */     return this.geomFact.createPolygon(linearRingZ(), null);
/*     */   }
/*     */   
/*     */   public Polygon polygon(double... ord) {
/* 238 */     return this.geomFact.createPolygon(linearRing(ord), null);
/*     */   }
/*     */   
/*     */   public Polygon polygonZ(double... ord) {
/* 248 */     return this.geomFact.createPolygon(linearRingZ(ord), null);
/*     */   }
/*     */   
/*     */   public Polygon polygon(LinearRing shell) {
/* 259 */     return this.geomFact.createPolygon(shell, null);
/*     */   }
/*     */   
/*     */   public Polygon polygon(LinearRing shell, LinearRing hole) {
/* 270 */     return this.geomFact.createPolygon(shell, new LinearRing[] { hole });
/*     */   }
/*     */   
/*     */   public Polygon polygon(Polygon shell, Polygon hole) {
/* 282 */     return this.geomFact.createPolygon((LinearRing)shell.getExteriorRing(), new LinearRing[] { (LinearRing)hole.getExteriorRing() });
/*     */   }
/*     */   
/*     */   public Polygon box(double x1, double y1, double x2, double y2) {
/* 296 */     double[] ord = { x1, y1, x1, y2, x2, y2, x2, y1, x1, y1 };
/* 297 */     return polygon(ord);
/*     */   }
/*     */   
/*     */   public Polygon boxZ(double x1, double y1, double x2, double y2, double z) {
/* 311 */     double[] ord = { 
/* 311 */         x1, y1, z, x1, y2, z, x2, y2, z, x2, 
/* 311 */         y1, z, x1, y1, z };
/* 313 */     return polygonZ(ord);
/*     */   }
/*     */   
/*     */   public Polygon ellipse(double x1, double y1, double x2, double y2, int nsides) {
/* 328 */     double rx = Math.abs(x2 - x1) / 2.0D;
/* 329 */     double ry = Math.abs(y2 - y1) / 2.0D;
/* 330 */     double cx = Math.min(x1, x2) + rx;
/* 331 */     double cy = Math.min(y1, y2) + ry;
/* 333 */     double[] ord = new double[2 * nsides + 2];
/* 334 */     double angInc = 6.283185307179586D / nsides;
/* 336 */     for (int i = 0; i < nsides; i++) {
/* 337 */       double ang = -(i * angInc);
/* 338 */       ord[2 * i] = cx + rx * Math.cos(ang);
/* 339 */       ord[2 * i + 1] = cy + ry * Math.sin(ang);
/*     */     } 
/* 341 */     ord[2 * nsides] = ord[0];
/* 342 */     ord[2 * nsides + 1] = ord[1];
/* 343 */     return polygon(ord);
/*     */   }
/*     */   
/*     */   public Polygon circle(double x, double y, double radius, int nsides) {
/* 356 */     return ellipse(x - radius, y - radius, x + radius, y + radius, nsides);
/*     */   }
/*     */   
/*     */   public MultiPoint multiPoint(double x1, double y1, double x2, double y2) {
/* 369 */     return this.geomFact.createMultiPoint(new Point[] { point(x1, y1), point(x2, y2) });
/*     */   }
/*     */   
/*     */   public MultiPoint multiPointZ(double x1, double y1, double z1, double x2, double y2, double z2) {
/* 386 */     return this.geomFact.createMultiPoint(new Point[] { pointZ(x1, y1, z1), pointZ(x2, y2, z2) });
/*     */   }
/*     */   
/*     */   public MultiLineString multiLineString(LineString... lines) {
/* 397 */     return this.geomFact.createMultiLineString(lines);
/*     */   }
/*     */   
/*     */   public MultiPolygon multiPolygon(Polygon... polys) {
/* 407 */     return this.geomFact.createMultiPolygon(polys);
/*     */   }
/*     */   
/*     */   public GeometryCollection geometryCollection(Geometry... geoms) {
/* 417 */     return this.geomFact.createGeometryCollection(geoms);
/*     */   }
/*     */   
/*     */   private boolean isClosed(double[] ord, int dim) {
/* 429 */     int n = ord.length / dim;
/* 430 */     if (n == 0)
/* 431 */       return true; 
/* 433 */     int lastPos = dim * (n - 1);
/* 434 */     double lastx = ord[lastPos];
/* 435 */     double lasty = ord[lastPos + 1];
/* 436 */     boolean isClosed = (lastx == ord[0] && lasty == ord[1]);
/* 437 */     return isClosed;
/*     */   }
/*     */   
/*     */   private CoordinateSequence createRingCS(double[] ord, int dim) {
/* 446 */     if (isClosed(ord, dim))
/* 447 */       return createCS(ord, dim); 
/* 448 */     double[] ord2 = new double[ord.length + dim];
/* 449 */     System.arraycopy(ord, 0, ord2, 0, ord.length);
/* 451 */     int lastPos = ord.length;
/* 452 */     for (int i = 0; i < dim; i++)
/* 453 */       ord2[lastPos + i] = ord2[i]; 
/* 455 */     return createCS(ord2, dim);
/*     */   }
/*     */   
/*     */   private CoordinateSequence createCS(double[] ord, int dim) {
/* 464 */     if (ord.length % dim != 0)
/* 465 */       throw new IllegalArgumentException("Ordinate array length " + ord.length + " is not a multiple of dimension " + dim); 
/* 467 */     int n = ord.length / dim;
/* 468 */     CoordinateSequence cs = this.csFact.create(n, dim);
/* 469 */     for (int i = 0; i < n; i++) {
/* 470 */       for (int d = 0; d < dim; d++)
/* 471 */         cs.setOrdinate(i, d, ord[dim * i + d]); 
/*     */     } 
/* 473 */     return cs;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\GeometryBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */