/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.index.strtree.ItemBoundable;
/*     */ import com.vividsolutions.jts.index.strtree.ItemDistance;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import com.vividsolutions.jts.operation.distance.FacetSequence;
/*     */ import com.vividsolutions.jts.operation.distance.FacetSequenceTreeBuilder;
/*     */ 
/*     */ public class MinimumClearance {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   private double minClearance;
/*     */   
/*     */   private Coordinate[] minClearancePts;
/*     */   
/*     */   public static double getDistance(Geometry g) {
/* 134 */     MinimumClearance rp = new MinimumClearance(g);
/* 135 */     return rp.getDistance();
/*     */   }
/*     */   
/*     */   public static Geometry getLine(Geometry g) {
/* 149 */     MinimumClearance rp = new MinimumClearance(g);
/* 150 */     return (Geometry)rp.getLine();
/*     */   }
/*     */   
/*     */   public MinimumClearance(Geometry geom) {
/* 165 */     this.inputGeom = geom;
/*     */   }
/*     */   
/*     */   public double getDistance() {
/* 180 */     compute();
/* 181 */     return this.minClearance;
/*     */   }
/*     */   
/*     */   public LineString getLine() {
/* 197 */     compute();
/* 199 */     if (this.minClearancePts == null || this.minClearancePts[0] == null)
/* 200 */       return this.inputGeom.getFactory().createLineString((Coordinate[])null); 
/* 201 */     return this.inputGeom.getFactory().createLineString(this.minClearancePts);
/*     */   }
/*     */   
/*     */   private void compute() {
/* 207 */     if (this.minClearancePts != null)
/*     */       return; 
/* 210 */     this.minClearancePts = new Coordinate[2];
/* 211 */     this.minClearance = Double.MAX_VALUE;
/* 214 */     if (this.inputGeom.isEmpty())
/*     */       return; 
/* 218 */     STRtree geomTree = FacetSequenceTreeBuilder.build(this.inputGeom);
/* 220 */     Object[] nearest = geomTree.nearestNeighbour(new MinClearanceDistance());
/* 221 */     MinClearanceDistance mcd = new MinClearanceDistance();
/* 222 */     this.minClearance = mcd.distance((FacetSequence)nearest[0], (FacetSequence)nearest[1]);
/* 225 */     this.minClearancePts = mcd.getCoordinates();
/*     */   }
/*     */   
/*     */   private static class MinClearanceDistance implements ItemDistance {
/* 250 */     private double minDist = Double.MAX_VALUE;
/*     */     
/* 251 */     private Coordinate[] minPts = new Coordinate[2];
/*     */     
/*     */     public Coordinate[] getCoordinates() {
/* 255 */       return this.minPts;
/*     */     }
/*     */     
/*     */     public double distance(ItemBoundable b1, ItemBoundable b2) {
/* 259 */       FacetSequence fs1 = (FacetSequence)b1.getItem();
/* 260 */       FacetSequence fs2 = (FacetSequence)b2.getItem();
/* 261 */       this.minDist = Double.MAX_VALUE;
/* 262 */       return distance(fs1, fs2);
/*     */     }
/*     */     
/*     */     public double distance(FacetSequence fs1, FacetSequence fs2) {
/* 269 */       vertexDistance(fs1, fs2);
/* 270 */       if (fs1.size() == 1 && fs2.size() == 1)
/* 270 */         return this.minDist; 
/* 271 */       if (this.minDist <= 0.0D)
/* 271 */         return this.minDist; 
/* 272 */       segmentDistance(fs1, fs2);
/* 273 */       if (this.minDist <= 0.0D)
/* 273 */         return this.minDist; 
/* 274 */       segmentDistance(fs2, fs1);
/* 275 */       return this.minDist;
/*     */     }
/*     */     
/*     */     private double vertexDistance(FacetSequence fs1, FacetSequence fs2) {
/* 279 */       for (int i1 = 0; i1 < fs1.size(); i1++) {
/* 280 */         for (int i2 = 0; i2 < fs2.size(); i2++) {
/* 281 */           Coordinate p1 = fs1.getCoordinate(i1);
/* 282 */           Coordinate p2 = fs2.getCoordinate(i2);
/* 283 */           if (!p1.equals2D(p2)) {
/* 284 */             double d = p1.distance(p2);
/* 285 */             if (d < this.minDist) {
/* 286 */               this.minDist = d;
/* 287 */               this.minPts[0] = p1;
/* 288 */               this.minPts[1] = p2;
/* 289 */               if (d == 0.0D)
/* 290 */                 return d; 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 295 */       return this.minDist;
/*     */     }
/*     */     
/*     */     private double segmentDistance(FacetSequence fs1, FacetSequence fs2) {
/* 299 */       for (int i1 = 0; i1 < fs1.size(); i1++) {
/* 300 */         for (int i2 = 1; i2 < fs2.size(); i2++) {
/* 302 */           Coordinate p = fs1.getCoordinate(i1);
/* 304 */           Coordinate seg0 = fs2.getCoordinate(i2 - 1);
/* 305 */           Coordinate seg1 = fs2.getCoordinate(i2);
/* 307 */           if (!p.equals2D(seg0) && !p.equals2D(seg1)) {
/* 308 */             double d = CGAlgorithms.distancePointLine(p, seg0, seg1);
/* 309 */             if (d < this.minDist) {
/* 310 */               this.minDist = d;
/* 311 */               updatePts(p, seg0, seg1);
/* 312 */               if (d == 0.0D)
/* 313 */                 return d; 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 318 */       return this.minDist;
/*     */     }
/*     */     
/*     */     private void updatePts(Coordinate p, Coordinate seg0, Coordinate seg1) {
/* 323 */       this.minPts[0] = p;
/* 324 */       LineSegment seg = new LineSegment(seg0, seg1);
/* 325 */       this.minPts[1] = new Coordinate(seg.closestPoint(p));
/*     */     }
/*     */     
/*     */     private MinClearanceDistance() {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\MinimumClearance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */