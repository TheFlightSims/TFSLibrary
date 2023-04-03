/*    */ package com.vividsolutions.jts.geom.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PolygonExtracter implements GeometryFilter {
/*    */   private List comps;
/*    */   
/*    */   public static List getPolygons(Geometry geom, List<Geometry> list) {
/* 57 */     if (geom instanceof com.vividsolutions.jts.geom.Polygon) {
/* 58 */       list.add(geom);
/* 60 */     } else if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 61 */       geom.apply(new PolygonExtracter(list));
/*    */     } 
/* 65 */     return list;
/*    */   }
/*    */   
/*    */   public static List getPolygons(Geometry geom) {
/* 76 */     return getPolygons(geom, new ArrayList());
/*    */   }
/*    */   
/*    */   public PolygonExtracter(List comps) {
/* 85 */     this.comps = comps;
/*    */   }
/*    */   
/*    */   public void filter(Geometry geom) {
/* 90 */     if (geom instanceof com.vividsolutions.jts.geom.Polygon)
/* 90 */       this.comps.add(geom); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\PolygonExtracter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */