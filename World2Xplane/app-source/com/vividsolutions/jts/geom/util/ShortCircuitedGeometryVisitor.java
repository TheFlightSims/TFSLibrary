/*    */ package com.vividsolutions.jts.geom.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ 
/*    */ public abstract class ShortCircuitedGeometryVisitor {
/*    */   private boolean isDone = false;
/*    */   
/*    */   public void applyTo(Geometry geom) {
/* 52 */     for (int i = 0; i < geom.getNumGeometries() && !this.isDone; i++) {
/* 53 */       Geometry element = geom.getGeometryN(i);
/* 54 */       if (!(element instanceof com.vividsolutions.jts.geom.GeometryCollection)) {
/* 55 */         visit(element);
/* 56 */         if (isDone()) {
/* 57 */           this.isDone = true;
/*    */           return;
/*    */         } 
/*    */       } else {
/* 62 */         applyTo(element);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract void visit(Geometry paramGeometry);
/*    */   
/*    */   protected abstract boolean isDone();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\ShortCircuitedGeometryVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */