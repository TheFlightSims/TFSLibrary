/*    */ package com.vividsolutions.jts.operation.distance;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ConnectedElementPointFilter implements GeometryFilter {
/*    */   private List pts;
/*    */   
/*    */   public static List getCoordinates(Geometry geom) {
/* 58 */     List pts = new ArrayList();
/* 59 */     geom.apply(new ConnectedElementPointFilter(pts));
/* 60 */     return pts;
/*    */   }
/*    */   
/*    */   ConnectedElementPointFilter(List pts) {
/* 67 */     this.pts = pts;
/*    */   }
/*    */   
/*    */   public void filter(Geometry geom) {
/* 72 */     if (geom instanceof com.vividsolutions.jts.geom.Point || geom instanceof com.vividsolutions.jts.geom.LineString || geom instanceof com.vividsolutions.jts.geom.Polygon)
/* 75 */       this.pts.add(geom.getCoordinate()); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance\ConnectedElementPointFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */