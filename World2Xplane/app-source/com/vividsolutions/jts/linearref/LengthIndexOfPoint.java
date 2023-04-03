/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ class LengthIndexOfPoint {
/*     */   private Geometry linearGeom;
/*     */   
/*     */   public static double indexOf(Geometry linearGeom, Coordinate inputPt) {
/*  50 */     LengthIndexOfPoint locater = new LengthIndexOfPoint(linearGeom);
/*  51 */     return locater.indexOf(inputPt);
/*     */   }
/*     */   
/*     */   public static double indexOfAfter(Geometry linearGeom, Coordinate inputPt, double minIndex) {
/*  56 */     LengthIndexOfPoint locater = new LengthIndexOfPoint(linearGeom);
/*  57 */     return locater.indexOfAfter(inputPt, minIndex);
/*     */   }
/*     */   
/*     */   public LengthIndexOfPoint(Geometry linearGeom) {
/*  63 */     this.linearGeom = linearGeom;
/*     */   }
/*     */   
/*     */   public double indexOf(Coordinate inputPt) {
/*  74 */     return indexOfFromStart(inputPt, -1.0D);
/*     */   }
/*     */   
/*     */   public double indexOfAfter(Coordinate inputPt, double minIndex) {
/*  94 */     if (minIndex < 0.0D)
/*  94 */       return indexOf(inputPt); 
/*  97 */     double endIndex = this.linearGeom.getLength();
/*  98 */     if (endIndex < minIndex)
/*  99 */       return endIndex; 
/* 101 */     double closestAfter = indexOfFromStart(inputPt, minIndex);
/* 105 */     Assert.isTrue((closestAfter >= minIndex), "computed index is before specified minimum index");
/* 107 */     return closestAfter;
/*     */   }
/*     */   
/*     */   private double indexOfFromStart(Coordinate inputPt, double minIndex) {
/* 112 */     double minDistance = Double.MAX_VALUE;
/* 114 */     double ptMeasure = minIndex;
/* 115 */     double segmentStartMeasure = 0.0D;
/* 116 */     LineSegment seg = new LineSegment();
/* 117 */     LinearIterator it = new LinearIterator(this.linearGeom);
/* 118 */     while (it.hasNext()) {
/* 119 */       if (!it.isEndOfLine()) {
/* 120 */         seg.p0 = it.getSegmentStart();
/* 121 */         seg.p1 = it.getSegmentEnd();
/* 122 */         double segDistance = seg.distance(inputPt);
/* 123 */         double segMeasureToPt = segmentNearestMeasure(seg, inputPt, segmentStartMeasure);
/* 124 */         if (segDistance < minDistance && segMeasureToPt > minIndex) {
/* 126 */           ptMeasure = segMeasureToPt;
/* 127 */           minDistance = segDistance;
/*     */         } 
/* 129 */         segmentStartMeasure += seg.getLength();
/*     */       } 
/* 131 */       it.next();
/*     */     } 
/* 133 */     return ptMeasure;
/*     */   }
/*     */   
/*     */   private double segmentNearestMeasure(LineSegment seg, Coordinate inputPt, double segmentStartMeasure) {
/* 140 */     double projFactor = seg.projectionFactor(inputPt);
/* 141 */     if (projFactor <= 0.0D)
/* 142 */       return segmentStartMeasure; 
/* 143 */     if (projFactor <= 1.0D)
/* 144 */       return segmentStartMeasure + projFactor * seg.getLength(); 
/* 146 */     return segmentStartMeasure + seg.getLength();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LengthIndexOfPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */