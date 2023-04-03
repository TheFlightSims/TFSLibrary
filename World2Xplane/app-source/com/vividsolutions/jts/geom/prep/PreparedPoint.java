/*    */ package com.vividsolutions.jts.geom.prep;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.Puntal;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PreparedPoint extends BasicPreparedGeometry {
/*    */   public PreparedPoint(Puntal point) {
/* 50 */     super((Geometry)point);
/*    */   }
/*    */   
/*    */   public boolean intersects(Geometry g) {
/* 61 */     if (!envelopesIntersect(g))
/* 61 */       return false; 
/* 66 */     return isAnyTargetComponentInTest(g);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */