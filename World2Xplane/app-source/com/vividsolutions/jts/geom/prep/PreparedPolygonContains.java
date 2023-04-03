/*    */ package com.vividsolutions.jts.geom.prep;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ 
/*    */ class PreparedPolygonContains extends AbstractPreparedPolygonContains {
/*    */   public static boolean contains(PreparedPolygon prep, Geometry geom) {
/* 63 */     PreparedPolygonContains polyInt = new PreparedPolygonContains(prep);
/* 64 */     return polyInt.contains(geom);
/*    */   }
/*    */   
/*    */   public PreparedPolygonContains(PreparedPolygon prepPoly) {
/* 74 */     super(prepPoly);
/*    */   }
/*    */   
/*    */   public boolean contains(Geometry geom) {
/* 85 */     return eval(geom);
/*    */   }
/*    */   
/*    */   protected boolean fullTopologicalPredicate(Geometry geom) {
/* 97 */     boolean isContained = this.prepPoly.getGeometry().contains(geom);
/* 98 */     return isContained;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedPolygonContains.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */