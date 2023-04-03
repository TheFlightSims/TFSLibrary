/*     */ package com.vividsolutions.jts.algorithm.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class DiscreteHausdorffDistance {
/*     */   private Geometry g0;
/*     */   
/*     */   private Geometry g1;
/*     */   
/*     */   public static double distance(Geometry g0, Geometry g1) {
/*  78 */     DiscreteHausdorffDistance dist = new DiscreteHausdorffDistance(g0, g1);
/*  79 */     return dist.distance();
/*     */   }
/*     */   
/*     */   public static double distance(Geometry g0, Geometry g1, double densifyFrac) {
/*  84 */     DiscreteHausdorffDistance dist = new DiscreteHausdorffDistance(g0, g1);
/*  85 */     dist.setDensifyFraction(densifyFrac);
/*  86 */     return dist.distance();
/*     */   }
/*     */   
/*  91 */   private PointPairDistance ptDist = new PointPairDistance();
/*     */   
/*  96 */   private double densifyFrac = 0.0D;
/*     */   
/*     */   public DiscreteHausdorffDistance(Geometry g0, Geometry g1) {
/* 100 */     this.g0 = g0;
/* 101 */     this.g1 = g1;
/*     */   }
/*     */   
/*     */   public void setDensifyFraction(double densifyFrac) {
/* 114 */     if (densifyFrac > 1.0D || densifyFrac <= 0.0D)
/* 116 */       throw new IllegalArgumentException("Fraction is not in range (0.0 - 1.0]"); 
/* 118 */     this.densifyFrac = densifyFrac;
/*     */   }
/*     */   
/*     */   public double distance() {
/* 123 */     compute(this.g0, this.g1);
/* 124 */     return this.ptDist.getDistance();
/*     */   }
/*     */   
/*     */   public double orientedDistance() {
/* 129 */     computeOrientedDistance(this.g0, this.g1, this.ptDist);
/* 130 */     return this.ptDist.getDistance();
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 133 */     return this.ptDist.getCoordinates();
/*     */   }
/*     */   
/*     */   private void compute(Geometry g0, Geometry g1) {
/* 137 */     computeOrientedDistance(g0, g1, this.ptDist);
/* 138 */     computeOrientedDistance(g1, g0, this.ptDist);
/*     */   }
/*     */   
/*     */   private void computeOrientedDistance(Geometry discreteGeom, Geometry geom, PointPairDistance ptDist) {
/* 143 */     MaxPointDistanceFilter distFilter = new MaxPointDistanceFilter(geom);
/* 144 */     discreteGeom.apply(distFilter);
/* 145 */     ptDist.setMaximum(distFilter.getMaxPointDistance());
/* 147 */     if (this.densifyFrac > 0.0D) {
/* 148 */       MaxDensifiedByFractionDistanceFilter fracFilter = new MaxDensifiedByFractionDistanceFilter(geom, this.densifyFrac);
/* 149 */       discreteGeom.apply(fracFilter);
/* 150 */       ptDist.setMaximum(fracFilter.getMaxPointDistance());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class MaxPointDistanceFilter implements CoordinateFilter {
/* 158 */     private PointPairDistance maxPtDist = new PointPairDistance();
/*     */     
/* 159 */     private PointPairDistance minPtDist = new PointPairDistance();
/*     */     
/* 160 */     private DistanceToPoint euclideanDist = new DistanceToPoint();
/*     */     
/*     */     private Geometry geom;
/*     */     
/*     */     public MaxPointDistanceFilter(Geometry geom) {
/* 165 */       this.geom = geom;
/*     */     }
/*     */     
/*     */     public void filter(Coordinate pt) {
/* 170 */       this.minPtDist.initialize();
/* 171 */       DistanceToPoint.computeDistance(this.geom, pt, this.minPtDist);
/* 172 */       this.maxPtDist.setMaximum(this.minPtDist);
/*     */     }
/*     */     
/*     */     public PointPairDistance getMaxPointDistance() {
/* 175 */       return this.maxPtDist;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MaxDensifiedByFractionDistanceFilter implements CoordinateSequenceFilter {
/* 181 */     private PointPairDistance maxPtDist = new PointPairDistance();
/*     */     
/* 182 */     private PointPairDistance minPtDist = new PointPairDistance();
/*     */     
/*     */     private Geometry geom;
/*     */     
/* 184 */     private int numSubSegs = 0;
/*     */     
/*     */     public MaxDensifiedByFractionDistanceFilter(Geometry geom, double fraction) {
/* 187 */       this.geom = geom;
/* 188 */       this.numSubSegs = (int)Math.rint(1.0D / fraction);
/*     */     }
/*     */     
/*     */     public void filter(CoordinateSequence seq, int index) {
/* 196 */       if (index == 0)
/*     */         return; 
/* 199 */       Coordinate p0 = seq.getCoordinate(index - 1);
/* 200 */       Coordinate p1 = seq.getCoordinate(index);
/* 202 */       double delx = (p1.x - p0.x) / this.numSubSegs;
/* 203 */       double dely = (p1.y - p0.y) / this.numSubSegs;
/* 205 */       for (int i = 0; i < this.numSubSegs; i++) {
/* 206 */         double x = p0.x + i * delx;
/* 207 */         double y = p0.y + i * dely;
/* 208 */         Coordinate pt = new Coordinate(x, y);
/* 209 */         this.minPtDist.initialize();
/* 210 */         DistanceToPoint.computeDistance(this.geom, pt, this.minPtDist);
/* 211 */         this.maxPtDist.setMaximum(this.minPtDist);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isGeometryChanged() {
/* 217 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 219 */       return false;
/*     */     }
/*     */     
/*     */     public PointPairDistance getMaxPointDistance() {
/* 222 */       return this.maxPtDist;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\distance\DiscreteHausdorffDistance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */