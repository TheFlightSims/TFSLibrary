/*    */ package com.vividsolutions.jts.geom.prep;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ 
/*    */ class PreparedPolygonCovers extends AbstractPreparedPolygonContains {
/*    */   public static boolean covers(PreparedPolygon prep, Geometry geom) {
/* 62 */     PreparedPolygonCovers polyInt = new PreparedPolygonCovers(prep);
/* 63 */     return polyInt.covers(geom);
/*    */   }
/*    */   
/*    */   public PreparedPolygonCovers(PreparedPolygon prepPoly) {
/* 73 */     super(prepPoly);
/* 74 */     this.requireSomePointInInterior = false;
/*    */   }
/*    */   
/*    */   public boolean covers(Geometry geom) {
/* 85 */     return eval(geom);
/*    */   }
/*    */   
/*    */   protected boolean fullTopologicalPredicate(Geometry geom) {
/* 97 */     boolean result = this.prepPoly.getGeometry().covers(geom);
/* 98 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedPolygonCovers.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */