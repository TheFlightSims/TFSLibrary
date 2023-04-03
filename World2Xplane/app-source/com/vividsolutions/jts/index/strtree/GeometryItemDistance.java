/*    */ package com.vividsolutions.jts.index.strtree;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ 
/*    */ public class GeometryItemDistance implements ItemDistance {
/*    */   public double distance(ItemBoundable item1, ItemBoundable item2) {
/* 59 */     Geometry g1 = (Geometry)item1.getItem();
/* 60 */     Geometry g2 = (Geometry)item2.getItem();
/* 61 */     return g1.distance(g2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\GeometryItemDistance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */