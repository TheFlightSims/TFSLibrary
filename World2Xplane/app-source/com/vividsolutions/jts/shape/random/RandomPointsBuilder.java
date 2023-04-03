/*     */ package com.vividsolutions.jts.shape.random;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.locate.IndexedPointInAreaLocator;
/*     */ import com.vividsolutions.jts.algorithm.locate.PointOnGeometryLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.shape.GeometricShapeBuilder;
/*     */ 
/*     */ public class RandomPointsBuilder extends GeometricShapeBuilder {
/*  51 */   protected Geometry maskPoly = null;
/*     */   
/*     */   private PointOnGeometryLocator extentLocator;
/*     */   
/*     */   public RandomPointsBuilder() {
/*  60 */     super(new GeometryFactory());
/*     */   }
/*     */   
/*     */   public RandomPointsBuilder(GeometryFactory geomFact) {
/*  71 */     super(geomFact);
/*     */   }
/*     */   
/*     */   public void setExtent(Geometry mask) {
/*  82 */     if (!(mask instanceof com.vividsolutions.jts.geom.Polygonal))
/*  83 */       throw new IllegalArgumentException("Only polygonal extents are supported"); 
/*  84 */     this.maskPoly = mask;
/*  85 */     setExtent(mask.getEnvelopeInternal());
/*  86 */     this.extentLocator = (PointOnGeometryLocator)new IndexedPointInAreaLocator(mask);
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/*  91 */     Coordinate[] pts = new Coordinate[this.numPts];
/*  92 */     int i = 0;
/*  93 */     while (i < this.numPts) {
/*  94 */       Coordinate p = createRandomCoord(getExtent());
/*  95 */       if (this.extentLocator != null && !isInExtent(p))
/*     */         continue; 
/*  97 */       pts[i++] = p;
/*     */     } 
/*  99 */     return (Geometry)this.geomFactory.createMultiPoint(pts);
/*     */   }
/*     */   
/*     */   protected boolean isInExtent(Coordinate p) {
/* 104 */     if (this.extentLocator != null)
/* 105 */       return (this.extentLocator.locate(p) != 2); 
/* 106 */     return getExtent().contains(p);
/*     */   }
/*     */   
/*     */   protected Coordinate createCoord(double x, double y) {
/* 111 */     Coordinate pt = new Coordinate(x, y);
/* 112 */     this.geomFactory.getPrecisionModel().makePrecise(pt);
/* 113 */     return pt;
/*     */   }
/*     */   
/*     */   protected Coordinate createRandomCoord(Envelope env) {
/* 118 */     double x = env.getMinX() + env.getWidth() * Math.random();
/* 119 */     double y = env.getMinY() + env.getHeight() * Math.random();
/* 120 */     return createCoord(x, y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\shape\random\RandomPointsBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */