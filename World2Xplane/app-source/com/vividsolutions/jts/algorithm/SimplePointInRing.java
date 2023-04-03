/*    */ package com.vividsolutions.jts.algorithm;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.LinearRing;
/*    */ 
/*    */ public class SimplePointInRing implements PointInRing {
/*    */   private Coordinate[] pts;
/*    */   
/*    */   public SimplePointInRing(LinearRing ring) {
/* 52 */     this.pts = ring.getCoordinates();
/*    */   }
/*    */   
/*    */   public boolean isInside(Coordinate pt) {
/* 57 */     return CGAlgorithms.isPointInRing(pt, this.pts);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\SimplePointInRing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */