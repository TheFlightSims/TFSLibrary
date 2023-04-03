/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ class LocationIndexOfPoint {
/*     */   private Geometry linearGeom;
/*     */   
/*     */   public static LinearLocation indexOf(Geometry linearGeom, Coordinate inputPt) {
/*  50 */     LocationIndexOfPoint locater = new LocationIndexOfPoint(linearGeom);
/*  51 */     return locater.indexOf(inputPt);
/*     */   }
/*     */   
/*     */   public static LinearLocation indexOfAfter(Geometry linearGeom, Coordinate inputPt, LinearLocation minIndex) {
/*  56 */     LocationIndexOfPoint locater = new LocationIndexOfPoint(linearGeom);
/*  57 */     return locater.indexOfAfter(inputPt, minIndex);
/*     */   }
/*     */   
/*     */   public LocationIndexOfPoint(Geometry linearGeom) {
/*  63 */     this.linearGeom = linearGeom;
/*     */   }
/*     */   
/*     */   public LinearLocation indexOf(Coordinate inputPt) {
/*  74 */     return indexOfFromStart(inputPt, null);
/*     */   }
/*     */   
/*     */   public LinearLocation indexOfAfter(Coordinate inputPt, LinearLocation minIndex) {
/*  94 */     if (minIndex == null)
/*  94 */       return indexOf(inputPt); 
/*  97 */     LinearLocation endLoc = LinearLocation.getEndLocation(this.linearGeom);
/*  98 */     if (endLoc.compareTo(minIndex) <= 0)
/*  99 */       return endLoc; 
/* 101 */     LinearLocation closestAfter = indexOfFromStart(inputPt, minIndex);
/* 106 */     Assert.isTrue((closestAfter.compareTo(minIndex) >= 0), "computed location is before specified minimum location");
/* 108 */     return closestAfter;
/*     */   }
/*     */   
/*     */   private LinearLocation indexOfFromStart(Coordinate inputPt, LinearLocation minIndex) {
/* 113 */     double minDistance = Double.MAX_VALUE;
/* 114 */     int minComponentIndex = 0;
/* 115 */     int minSegmentIndex = 0;
/* 116 */     double minFrac = -1.0D;
/* 118 */     LineSegment seg = new LineSegment();
/* 119 */     LinearIterator it = new LinearIterator(this.linearGeom);
/* 120 */     for (; it.hasNext(); it.next()) {
/* 121 */       if (!it.isEndOfLine()) {
/* 122 */         seg.p0 = it.getSegmentStart();
/* 123 */         seg.p1 = it.getSegmentEnd();
/* 124 */         double segDistance = seg.distance(inputPt);
/* 125 */         double segFrac = seg.segmentFraction(inputPt);
/* 127 */         int candidateComponentIndex = it.getComponentIndex();
/* 128 */         int candidateSegmentIndex = it.getVertexIndex();
/* 129 */         if (segDistance < minDistance)
/* 131 */           if (minIndex == null || minIndex.compareLocationValues(candidateComponentIndex, candidateSegmentIndex, segFrac) < 0) {
/* 137 */             minComponentIndex = candidateComponentIndex;
/* 138 */             minSegmentIndex = candidateSegmentIndex;
/* 139 */             minFrac = segFrac;
/* 140 */             minDistance = segDistance;
/*     */           }  
/*     */       } 
/*     */     } 
/* 145 */     if (minDistance == Double.MAX_VALUE)
/* 147 */       return new LinearLocation(minIndex); 
/* 150 */     LinearLocation loc = new LinearLocation(minComponentIndex, minSegmentIndex, minFrac);
/* 151 */     return loc;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LocationIndexOfPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */