/*     */ package com.vividsolutions.jts.operation.overlay.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import java.util.List;
/*     */ 
/*     */ public class FuzzyPointLocator {
/*     */   private Geometry g;
/*     */   
/*     */   private double boundaryDistanceTolerance;
/*     */   
/*     */   private MultiLineString linework;
/*     */   
/*  57 */   private PointLocator ptLocator = new PointLocator();
/*     */   
/*  58 */   private LineSegment seg = new LineSegment();
/*     */   
/*     */   public FuzzyPointLocator(Geometry g, double boundaryDistanceTolerance) {
/*  62 */     this.g = g;
/*  63 */     this.boundaryDistanceTolerance = boundaryDistanceTolerance;
/*  64 */     this.linework = extractLinework(g);
/*     */   }
/*     */   
/*     */   public int getLocation(Coordinate pt) {
/*  69 */     if (isWithinToleranceOfBoundary(pt))
/*  70 */       return 1; 
/*  80 */     return this.ptLocator.locate(pt, this.g);
/*     */   }
/*     */   
/*     */   private MultiLineString extractLinework(Geometry g) {
/*  91 */     PolygonalLineworkExtracter extracter = new PolygonalLineworkExtracter();
/*  92 */     g.apply(extracter);
/*  93 */     List linework = extracter.getLinework();
/*  94 */     LineString[] lines = GeometryFactory.toLineStringArray(linework);
/*  95 */     return g.getFactory().createMultiLineString(lines);
/*     */   }
/*     */   
/*     */   private boolean isWithinToleranceOfBoundary(Coordinate pt) {
/* 100 */     for (int i = 0; i < this.linework.getNumGeometries(); i++) {
/* 101 */       LineString line = (LineString)this.linework.getGeometryN(i);
/* 102 */       CoordinateSequence seq = line.getCoordinateSequence();
/* 103 */       for (int j = 0; j < seq.size() - 1; j++) {
/* 104 */         seq.getCoordinate(j, this.seg.p0);
/* 105 */         seq.getCoordinate(j + 1, this.seg.p1);
/* 106 */         double dist = this.seg.distance(pt);
/* 107 */         if (dist <= this.boundaryDistanceTolerance)
/* 108 */           return true; 
/*     */       } 
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\validate\FuzzyPointLocator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */