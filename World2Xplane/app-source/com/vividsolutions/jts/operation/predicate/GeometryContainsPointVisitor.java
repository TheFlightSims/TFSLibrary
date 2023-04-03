/*     */ package com.vividsolutions.jts.operation.predicate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.locate.SimplePointInAreaLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.util.ShortCircuitedGeometryVisitor;
/*     */ 
/*     */ class GeometryContainsPointVisitor extends ShortCircuitedGeometryVisitor {
/*     */   private CoordinateSequence rectSeq;
/*     */   
/*     */   private Envelope rectEnv;
/*     */   
/*     */   private boolean containsPoint = false;
/*     */   
/*     */   public GeometryContainsPointVisitor(Polygon rectangle) {
/* 218 */     this.rectSeq = rectangle.getExteriorRing().getCoordinateSequence();
/* 219 */     this.rectEnv = rectangle.getEnvelopeInternal();
/*     */   }
/*     */   
/*     */   public boolean containsPoint() {
/* 231 */     return this.containsPoint;
/*     */   }
/*     */   
/*     */   protected void visit(Geometry geom) {
/* 237 */     if (!(geom instanceof Polygon))
/*     */       return; 
/* 241 */     Envelope elementEnv = geom.getEnvelopeInternal();
/* 242 */     if (!this.rectEnv.intersects(elementEnv))
/*     */       return; 
/* 246 */     Coordinate rectPt = new Coordinate();
/* 247 */     for (int i = 0; i < 4; i++) {
/* 248 */       this.rectSeq.getCoordinate(i, rectPt);
/* 249 */       if (elementEnv.contains(rectPt))
/* 253 */         if (SimplePointInAreaLocator.containsPointInPolygon(rectPt, (Polygon)geom)) {
/* 255 */           this.containsPoint = true;
/*     */           return;
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isDone() {
/* 263 */     return (this.containsPoint == true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\predicate\GeometryContainsPointVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */