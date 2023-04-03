/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class LengthIndexedLine {
/*     */   private Geometry linearGeom;
/*     */   
/*     */   public LengthIndexedLine(Geometry linearGeom) {
/*  59 */     this.linearGeom = linearGeom;
/*     */   }
/*     */   
/*     */   public Coordinate extractPoint(double index) {
/*  75 */     LinearLocation loc = LengthLocationMap.getLocation(this.linearGeom, index);
/*  76 */     return loc.getCoordinate(this.linearGeom);
/*     */   }
/*     */   
/*     */   public Coordinate extractPoint(double index, double offsetDistance) {
/*  97 */     LinearLocation loc = LengthLocationMap.getLocation(this.linearGeom, index);
/*  98 */     LinearLocation locLow = loc.toLowest(this.linearGeom);
/*  99 */     return locLow.getSegment(this.linearGeom).pointAlongOffset(locLow.getSegmentFraction(), offsetDistance);
/*     */   }
/*     */   
/*     */   public Geometry extractLine(double startIndex, double endIndex) {
/* 114 */     LocationIndexedLine lil = new LocationIndexedLine(this.linearGeom);
/* 115 */     double startIndex2 = clampIndex(startIndex);
/* 116 */     double endIndex2 = clampIndex(endIndex);
/* 118 */     boolean resolveStartLower = (startIndex2 == endIndex2);
/* 119 */     LinearLocation startLoc = locationOf(startIndex2, resolveStartLower);
/* 122 */     LinearLocation endLoc = locationOf(endIndex2);
/* 123 */     return ExtractLineByLocation.extract(this.linearGeom, startLoc, endLoc);
/*     */   }
/*     */   
/*     */   private LinearLocation locationOf(double index) {
/* 128 */     return LengthLocationMap.getLocation(this.linearGeom, index);
/*     */   }
/*     */   
/*     */   private LinearLocation locationOf(double index, boolean resolveLower) {
/* 133 */     return LengthLocationMap.getLocation(this.linearGeom, index, resolveLower);
/*     */   }
/*     */   
/*     */   public double indexOf(Coordinate pt) {
/* 155 */     return LengthIndexOfPoint.indexOf(this.linearGeom, pt);
/*     */   }
/*     */   
/*     */   public double indexOfAfter(Coordinate pt, double minIndex) {
/* 182 */     return LengthIndexOfPoint.indexOfAfter(this.linearGeom, pt, minIndex);
/*     */   }
/*     */   
/*     */   public double[] indicesOf(Geometry subLine) {
/* 196 */     LinearLocation[] locIndex = LocationIndexOfLine.indicesOf(this.linearGeom, subLine);
/* 197 */     double[] index = { LengthLocationMap.getLength(this.linearGeom, locIndex[0]), LengthLocationMap.getLength(this.linearGeom, locIndex[1]) };
/* 201 */     return index;
/*     */   }
/*     */   
/*     */   public double project(Coordinate pt) {
/* 216 */     return LengthIndexOfPoint.indexOf(this.linearGeom, pt);
/*     */   }
/*     */   
/*     */   public double getStartIndex() {
/* 225 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getEndIndex() {
/* 234 */     return this.linearGeom.getLength();
/*     */   }
/*     */   
/*     */   public boolean isValidIndex(double index) {
/* 245 */     return (index >= getStartIndex() && index <= getEndIndex());
/*     */   }
/*     */   
/*     */   public double clampIndex(double index) {
/* 257 */     double posIndex = positiveIndex(index);
/* 258 */     double startIndex = getStartIndex();
/* 259 */     if (posIndex < startIndex)
/* 259 */       return startIndex; 
/* 261 */     double endIndex = getEndIndex();
/* 262 */     if (posIndex > endIndex)
/* 262 */       return endIndex; 
/* 264 */     return posIndex;
/*     */   }
/*     */   
/*     */   private double positiveIndex(double index) {
/* 269 */     if (index >= 0.0D)
/* 269 */       return index; 
/* 270 */     return this.linearGeom.getLength() + index;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LengthIndexedLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */