/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class LocationIndexedLine {
/*     */   private Geometry linearGeom;
/*     */   
/*     */   public LocationIndexedLine(Geometry linearGeom) {
/*  55 */     this.linearGeom = linearGeom;
/*  56 */     checkGeometryType();
/*     */   }
/*     */   
/*     */   private void checkGeometryType() {
/*  61 */     if (!(this.linearGeom instanceof com.vividsolutions.jts.geom.LineString) && !(this.linearGeom instanceof com.vividsolutions.jts.geom.MultiLineString))
/*  62 */       throw new IllegalArgumentException("Input geometry must be linear"); 
/*     */   }
/*     */   
/*     */   public Coordinate extractPoint(LinearLocation index) {
/*  77 */     return index.getCoordinate(this.linearGeom);
/*     */   }
/*     */   
/*     */   public Coordinate extractPoint(LinearLocation index, double offsetDistance) {
/*  98 */     LinearLocation indexLow = index.toLowest(this.linearGeom);
/*  99 */     return indexLow.getSegment(this.linearGeom).pointAlongOffset(indexLow.getSegmentFraction(), offsetDistance);
/*     */   }
/*     */   
/*     */   public Geometry extractLine(LinearLocation startIndex, LinearLocation endIndex) {
/* 112 */     return ExtractLineByLocation.extract(this.linearGeom, startIndex, endIndex);
/*     */   }
/*     */   
/*     */   public LinearLocation indexOf(Coordinate pt) {
/* 130 */     return LocationIndexOfPoint.indexOf(this.linearGeom, pt);
/*     */   }
/*     */   
/*     */   public LinearLocation indexOfAfter(Coordinate pt, LinearLocation minIndex) {
/* 157 */     return LocationIndexOfPoint.indexOfAfter(this.linearGeom, pt, minIndex);
/*     */   }
/*     */   
/*     */   public LinearLocation[] indicesOf(Geometry subLine) {
/* 172 */     return LocationIndexOfLine.indicesOf(this.linearGeom, subLine);
/*     */   }
/*     */   
/*     */   public LinearLocation project(Coordinate pt) {
/* 186 */     return LocationIndexOfPoint.indexOf(this.linearGeom, pt);
/*     */   }
/*     */   
/*     */   public LinearLocation getStartIndex() {
/* 195 */     return new LinearLocation();
/*     */   }
/*     */   
/*     */   public LinearLocation getEndIndex() {
/* 204 */     return LinearLocation.getEndLocation(this.linearGeom);
/*     */   }
/*     */   
/*     */   public boolean isValidIndex(LinearLocation index) {
/* 215 */     return index.isValid(this.linearGeom);
/*     */   }
/*     */   
/*     */   public LinearLocation clampIndex(LinearLocation index) {
/* 226 */     LinearLocation loc = (LinearLocation)index.clone();
/* 227 */     loc.clamp(this.linearGeom);
/* 228 */     return loc;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LocationIndexedLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */