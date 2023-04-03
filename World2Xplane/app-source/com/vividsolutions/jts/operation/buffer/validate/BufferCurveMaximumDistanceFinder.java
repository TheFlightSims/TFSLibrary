/*     */ package com.vividsolutions.jts.operation.buffer.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class BufferCurveMaximumDistanceFinder {
/*     */   private Geometry inputGeom;
/*     */   
/*  55 */   private PointPairDistance maxPtDist = new PointPairDistance();
/*     */   
/*     */   public BufferCurveMaximumDistanceFinder(Geometry inputGeom) {
/*  59 */     this.inputGeom = inputGeom;
/*     */   }
/*     */   
/*     */   public double findDistance(Geometry bufferCurve) {
/*  64 */     computeMaxVertexDistance(bufferCurve);
/*  65 */     computeMaxMidpointDistance(bufferCurve);
/*  66 */     return this.maxPtDist.getDistance();
/*     */   }
/*     */   
/*     */   public PointPairDistance getDistancePoints() {
/*  71 */     return this.maxPtDist;
/*     */   }
/*     */   
/*     */   private void computeMaxVertexDistance(Geometry curve) {
/*  75 */     MaxPointDistanceFilter distFilter = new MaxPointDistanceFilter(this.inputGeom);
/*  76 */     curve.apply(distFilter);
/*  77 */     this.maxPtDist.setMaximum(distFilter.getMaxPointDistance());
/*     */   }
/*     */   
/*     */   private void computeMaxMidpointDistance(Geometry curve) {
/*  82 */     MaxMidpointDistanceFilter distFilter = new MaxMidpointDistanceFilter(this.inputGeom);
/*  83 */     curve.apply(distFilter);
/*  84 */     this.maxPtDist.setMaximum(distFilter.getMaxPointDistance());
/*     */   }
/*     */   
/*     */   public static class MaxPointDistanceFilter implements CoordinateFilter {
/*  88 */     private PointPairDistance maxPtDist = new PointPairDistance();
/*     */     
/*  89 */     private PointPairDistance minPtDist = new PointPairDistance();
/*     */     
/*     */     private Geometry geom;
/*     */     
/*     */     public MaxPointDistanceFilter(Geometry geom) {
/*  93 */       this.geom = geom;
/*     */     }
/*     */     
/*     */     public void filter(Coordinate pt) {
/*  97 */       this.minPtDist.initialize();
/*  98 */       DistanceToPointFinder.computeDistance(this.geom, pt, this.minPtDist);
/*  99 */       this.maxPtDist.setMaximum(this.minPtDist);
/*     */     }
/*     */     
/*     */     public PointPairDistance getMaxPointDistance() {
/* 103 */       return this.maxPtDist;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MaxMidpointDistanceFilter implements CoordinateSequenceFilter {
/* 110 */     private PointPairDistance maxPtDist = new PointPairDistance();
/*     */     
/* 111 */     private PointPairDistance minPtDist = new PointPairDistance();
/*     */     
/*     */     private Geometry geom;
/*     */     
/*     */     public MaxMidpointDistanceFilter(Geometry geom) {
/* 115 */       this.geom = geom;
/*     */     }
/*     */     
/*     */     public void filter(CoordinateSequence seq, int index) {
/* 120 */       if (index == 0)
/*     */         return; 
/* 123 */       Coordinate p0 = seq.getCoordinate(index - 1);
/* 124 */       Coordinate p1 = seq.getCoordinate(index);
/* 125 */       Coordinate midPt = new Coordinate((p0.x + p1.x) / 2.0D, (p0.y + p1.y) / 2.0D);
/* 129 */       this.minPtDist.initialize();
/* 130 */       DistanceToPointFinder.computeDistance(this.geom, midPt, this.minPtDist);
/* 131 */       this.maxPtDist.setMaximum(this.minPtDist);
/*     */     }
/*     */     
/*     */     public boolean isGeometryChanged() {
/* 134 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 136 */       return false;
/*     */     }
/*     */     
/*     */     public PointPairDistance getMaxPointDistance() {
/* 139 */       return this.maxPtDist;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\validate\BufferCurveMaximumDistanceFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */