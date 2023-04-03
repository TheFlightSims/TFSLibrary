/*    */ package com.vividsolutions.jts.operation.distance;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ConnectedElementLocationFilter implements GeometryFilter {
/*    */   private List locations;
/*    */   
/*    */   public static List getLocations(Geometry geom) {
/* 60 */     List locations = new ArrayList();
/* 61 */     geom.apply(new ConnectedElementLocationFilter(locations));
/* 62 */     return locations;
/*    */   }
/*    */   
/*    */   ConnectedElementLocationFilter(List locations) {
/* 69 */     this.locations = locations;
/*    */   }
/*    */   
/*    */   public void filter(Geometry geom) {
/* 74 */     if (geom instanceof com.vividsolutions.jts.geom.Point || geom instanceof com.vividsolutions.jts.geom.LineString || geom instanceof com.vividsolutions.jts.geom.Polygon)
/* 77 */       this.locations.add(new GeometryLocation(geom, 0, geom.getCoordinate())); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance\ConnectedElementLocationFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */