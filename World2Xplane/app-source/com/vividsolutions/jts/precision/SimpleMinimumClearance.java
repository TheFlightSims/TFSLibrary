/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ 
/*     */ public class SimpleMinimumClearance {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   private double minClearance;
/*     */   
/*     */   private Coordinate[] minClearancePts;
/*     */   
/*     */   public static double getDistance(Geometry g) {
/*  64 */     SimpleMinimumClearance rp = new SimpleMinimumClearance(g);
/*  65 */     return rp.getDistance();
/*     */   }
/*     */   
/*     */   public static Geometry getLine(Geometry g) {
/*  70 */     SimpleMinimumClearance rp = new SimpleMinimumClearance(g);
/*  71 */     return (Geometry)rp.getLine();
/*     */   }
/*     */   
/*     */   public SimpleMinimumClearance(Geometry geom) {
/*  80 */     this.inputGeom = geom;
/*     */   }
/*     */   
/*     */   public double getDistance() {
/*  85 */     compute();
/*  86 */     return this.minClearance;
/*     */   }
/*     */   
/*     */   public LineString getLine() {
/*  91 */     compute();
/*  92 */     return this.inputGeom.getFactory().createLineString(this.minClearancePts);
/*     */   }
/*     */   
/*     */   private void compute() {
/*  97 */     if (this.minClearancePts != null)
/*     */       return; 
/*  98 */     this.minClearancePts = new Coordinate[2];
/*  99 */     this.minClearance = Double.MAX_VALUE;
/* 100 */     this.inputGeom.apply(new VertexCoordinateFilter());
/*     */   }
/*     */   
/*     */   private void updateClearance(double candidateValue, Coordinate p0, Coordinate p1) {
/* 105 */     if (candidateValue < this.minClearance) {
/* 106 */       this.minClearance = candidateValue;
/* 107 */       this.minClearancePts[0] = new Coordinate(p0);
/* 108 */       this.minClearancePts[1] = new Coordinate(p1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateClearance(double candidateValue, Coordinate p, Coordinate seg0, Coordinate seg1) {
/* 115 */     if (candidateValue < this.minClearance) {
/* 116 */       this.minClearance = candidateValue;
/* 117 */       this.minClearancePts[0] = new Coordinate(p);
/* 118 */       LineSegment seg = new LineSegment(seg0, seg1);
/* 119 */       this.minClearancePts[1] = new Coordinate(seg.closestPoint(p));
/*     */     } 
/*     */   }
/*     */   
/*     */   private class VertexCoordinateFilter implements CoordinateFilter {
/*     */     public void filter(Coordinate coord) {
/* 132 */       SimpleMinimumClearance.this.inputGeom.apply(new SimpleMinimumClearance.ComputeMCCoordinateSequenceFilter(coord));
/*     */     }
/*     */   }
/*     */   
/*     */   private class ComputeMCCoordinateSequenceFilter implements CoordinateSequenceFilter {
/*     */     private Coordinate queryPt;
/*     */     
/*     */     public ComputeMCCoordinateSequenceFilter(Coordinate queryPt) {
/* 143 */       this.queryPt = queryPt;
/*     */     }
/*     */     
/*     */     public void filter(CoordinateSequence seq, int i) {
/* 147 */       checkVertexDistance(seq.getCoordinate(i));
/* 150 */       if (i > 0)
/* 151 */         checkSegmentDistance(seq.getCoordinate(i - 1), seq.getCoordinate(i)); 
/*     */     }
/*     */     
/*     */     private void checkVertexDistance(Coordinate vertex) {
/* 157 */       double vertexDist = vertex.distance(this.queryPt);
/* 158 */       if (vertexDist > 0.0D)
/* 159 */         SimpleMinimumClearance.this.updateClearance(vertexDist, this.queryPt, vertex); 
/*     */     }
/*     */     
/*     */     private void checkSegmentDistance(Coordinate seg0, Coordinate seg1) {
/* 165 */       if (this.queryPt.equals2D(seg0) || this.queryPt.equals2D(seg1))
/*     */         return; 
/* 167 */       double segDist = CGAlgorithms.distancePointLine(this.queryPt, seg1, seg0);
/* 168 */       if (segDist > 0.0D)
/* 169 */         SimpleMinimumClearance.this.updateClearance(segDist, this.queryPt, seg1, seg0); 
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 173 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isGeometryChanged() {
/* 177 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\SimpleMinimumClearance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */