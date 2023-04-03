/*     */ package com.vividsolutions.jts.operation.predicate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.RectangleLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import com.vividsolutions.jts.geom.util.ShortCircuitedGeometryVisitor;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class RectangleIntersectsSegmentVisitor extends ShortCircuitedGeometryVisitor {
/*     */   private Envelope rectEnv;
/*     */   
/*     */   private RectangleLineIntersector rectIntersector;
/*     */   
/*     */   private boolean hasIntersection = false;
/*     */   
/* 281 */   private Coordinate p0 = new Coordinate();
/*     */   
/* 282 */   private Coordinate p1 = new Coordinate();
/*     */   
/*     */   public RectangleIntersectsSegmentVisitor(Polygon rectangle) {
/* 292 */     this.rectEnv = rectangle.getEnvelopeInternal();
/* 293 */     this.rectIntersector = new RectangleLineIntersector(this.rectEnv);
/*     */   }
/*     */   
/*     */   public boolean intersects() {
/* 304 */     return this.hasIntersection;
/*     */   }
/*     */   
/*     */   protected void visit(Geometry geom) {
/* 314 */     Envelope elementEnv = geom.getEnvelopeInternal();
/* 315 */     if (!this.rectEnv.intersects(elementEnv))
/*     */       return; 
/* 321 */     List lines = LinearComponentExtracter.getLines(geom);
/* 322 */     checkIntersectionWithLineStrings(lines);
/*     */   }
/*     */   
/*     */   private void checkIntersectionWithLineStrings(List lines) {
/* 327 */     for (Iterator<LineString> i = lines.iterator(); i.hasNext(); ) {
/* 328 */       LineString testLine = i.next();
/* 329 */       checkIntersectionWithSegments(testLine);
/* 330 */       if (this.hasIntersection)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkIntersectionWithSegments(LineString testLine) {
/* 337 */     CoordinateSequence seq1 = testLine.getCoordinateSequence();
/* 338 */     for (int j = 1; j < seq1.size(); j++) {
/* 339 */       seq1.getCoordinate(j - 1, this.p0);
/* 340 */       seq1.getCoordinate(j, this.p1);
/* 342 */       if (this.rectIntersector.intersects(this.p0, this.p1)) {
/* 343 */         this.hasIntersection = true;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isDone() {
/* 351 */     return (this.hasIntersection == true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\predicate\RectangleIntersectsSegmentVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */