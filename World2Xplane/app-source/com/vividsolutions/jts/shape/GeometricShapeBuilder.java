/*     */ package com.vividsolutions.jts.shape;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ 
/*     */ public abstract class GeometricShapeBuilder {
/*  40 */   protected Envelope extent = new Envelope(0.0D, 1.0D, 0.0D, 1.0D);
/*     */   
/*  41 */   protected int numPts = 0;
/*     */   
/*     */   protected GeometryFactory geomFactory;
/*     */   
/*     */   public GeometricShapeBuilder(GeometryFactory geomFactory) {
/*  46 */     this.geomFactory = geomFactory;
/*     */   }
/*     */   
/*     */   public void setExtent(Envelope extent) {
/*  51 */     this.extent = extent;
/*     */   }
/*     */   
/*     */   public Envelope getExtent() {
/*  56 */     return this.extent;
/*     */   }
/*     */   
/*     */   public Coordinate getCentre() {
/*  61 */     return this.extent.centre();
/*     */   }
/*     */   
/*     */   public double getDiameter() {
/*  66 */     return Math.min(this.extent.getHeight(), this.extent.getWidth());
/*     */   }
/*     */   
/*     */   public double getRadius() {
/*  71 */     return getDiameter() / 2.0D;
/*     */   }
/*     */   
/*     */   public LineSegment getSquareBaseLine() {
/*  76 */     double radius = getRadius();
/*  78 */     Coordinate centre = getCentre();
/*  79 */     Coordinate p0 = new Coordinate(centre.x - radius, centre.y - radius);
/*  80 */     Coordinate p1 = new Coordinate(centre.x + radius, centre.y - radius);
/*  81 */     return new LineSegment(p0, p1);
/*     */   }
/*     */   
/*     */   public Envelope getSquareExtent() {
/*  86 */     double radius = getRadius();
/*  88 */     Coordinate centre = getCentre();
/*  89 */     return new Envelope(centre.x - radius, centre.x + radius, centre.y - radius, centre.y + radius);
/*     */   }
/*     */   
/*     */   public void setNumPoints(int numPts) {
/*  99 */     this.numPts = numPts;
/*     */   }
/*     */   
/*     */   public abstract Geometry getGeometry();
/*     */   
/*     */   protected Coordinate createCoord(double x, double y) {
/* 105 */     Coordinate pt = new Coordinate(x, y);
/* 106 */     this.geomFactory.getPrecisionModel().makePrecise(pt);
/* 107 */     return pt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\shape\GeometricShapeBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */