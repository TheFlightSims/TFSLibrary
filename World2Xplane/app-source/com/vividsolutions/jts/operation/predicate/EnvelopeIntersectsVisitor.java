/*     */ package com.vividsolutions.jts.operation.predicate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.util.ShortCircuitedGeometryVisitor;
/*     */ 
/*     */ class EnvelopeIntersectsVisitor extends ShortCircuitedGeometryVisitor {
/*     */   private Envelope rectEnv;
/*     */   
/*     */   private boolean intersects = false;
/*     */   
/*     */   public EnvelopeIntersectsVisitor(Envelope rectEnv) {
/* 145 */     this.rectEnv = rectEnv;
/*     */   }
/*     */   
/*     */   public boolean intersects() {
/* 157 */     return this.intersects;
/*     */   }
/*     */   
/*     */   protected void visit(Geometry element) {
/* 162 */     Envelope elementEnv = element.getEnvelopeInternal();
/* 165 */     if (!this.rectEnv.intersects(elementEnv))
/*     */       return; 
/* 169 */     if (this.rectEnv.contains(elementEnv)) {
/* 170 */       this.intersects = true;
/*     */       return;
/*     */     } 
/* 182 */     if (elementEnv.getMinX() >= this.rectEnv.getMinX() && elementEnv.getMaxX() <= this.rectEnv.getMaxX()) {
/* 184 */       this.intersects = true;
/*     */       return;
/*     */     } 
/* 187 */     if (elementEnv.getMinY() >= this.rectEnv.getMinY() && elementEnv.getMaxY() <= this.rectEnv.getMaxY()) {
/* 189 */       this.intersects = true;
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isDone() {
/* 196 */     return (this.intersects == true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\predicate\EnvelopeIntersectsVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */