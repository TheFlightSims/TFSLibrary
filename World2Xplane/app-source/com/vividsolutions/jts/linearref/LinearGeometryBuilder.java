/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class LinearGeometryBuilder {
/*     */   private GeometryFactory geomFact;
/*     */   
/*  48 */   private List lines = new ArrayList();
/*     */   
/*  49 */   private CoordinateList coordList = null;
/*     */   
/*     */   private boolean ignoreInvalidLines = false;
/*     */   
/*     */   private boolean fixInvalidLines = false;
/*     */   
/*  54 */   private Coordinate lastPt = null;
/*     */   
/*     */   public LinearGeometryBuilder(GeometryFactory geomFact) {
/*  57 */     this.geomFact = geomFact;
/*     */   }
/*     */   
/*     */   public void setIgnoreInvalidLines(boolean ignoreInvalidLines) {
/*  68 */     this.ignoreInvalidLines = ignoreInvalidLines;
/*     */   }
/*     */   
/*     */   public void setFixInvalidLines(boolean fixInvalidLines) {
/*  79 */     this.fixInvalidLines = fixInvalidLines;
/*     */   }
/*     */   
/*     */   public void add(Coordinate pt) {
/*  89 */     add(pt, true);
/*     */   }
/*     */   
/*     */   public void add(Coordinate pt, boolean allowRepeatedPoints) {
/*  99 */     if (this.coordList == null)
/* 100 */       this.coordList = new CoordinateList(); 
/* 101 */     this.coordList.add(pt, allowRepeatedPoints);
/* 102 */     this.lastPt = pt;
/*     */   }
/*     */   
/*     */   public Coordinate getLastCoordinate() {
/* 105 */     return this.lastPt;
/*     */   }
/*     */   
/*     */   public void endLine() {
/* 112 */     if (this.coordList == null)
/*     */       return; 
/* 115 */     if (this.ignoreInvalidLines && this.coordList.size() < 2) {
/* 116 */       this.coordList = null;
/*     */       return;
/*     */     } 
/* 119 */     Coordinate[] rawPts = this.coordList.toCoordinateArray();
/* 120 */     Coordinate[] pts = rawPts;
/* 121 */     if (this.fixInvalidLines)
/* 122 */       pts = validCoordinateSequence(rawPts); 
/* 124 */     this.coordList = null;
/* 125 */     LineString line = null;
/*     */     try {
/* 127 */       line = this.geomFact.createLineString(pts);
/* 129 */     } catch (IllegalArgumentException ex) {
/* 132 */       if (!this.ignoreInvalidLines)
/* 133 */         throw ex; 
/*     */     } 
/* 136 */     if (line != null)
/* 136 */       this.lines.add(line); 
/*     */   }
/*     */   
/*     */   private Coordinate[] validCoordinateSequence(Coordinate[] pts) {
/* 141 */     if (pts.length >= 2)
/* 141 */       return pts; 
/* 142 */     Coordinate[] validPts = { pts[0], pts[0] };
/* 143 */     return validPts;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/* 149 */     endLine();
/* 150 */     return this.geomFact.buildGeometry(this.lines);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LinearGeometryBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */