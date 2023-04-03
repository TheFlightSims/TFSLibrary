/*    */ package com.vividsolutions.jts.geom.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PointExtracter implements GeometryFilter {
/*    */   private List pts;
/*    */   
/*    */   public static List getPoints(Geometry geom, List<Geometry> list) {
/* 58 */     if (geom instanceof com.vividsolutions.jts.geom.Point) {
/* 59 */       list.add(geom);
/* 61 */     } else if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 62 */       geom.apply(new PointExtracter(list));
/*    */     } 
/* 66 */     return list;
/*    */   }
/*    */   
/*    */   public static List getPoints(Geometry geom) {
/* 77 */     return getPoints(geom, new ArrayList());
/*    */   }
/*    */   
/*    */   public PointExtracter(List pts) {
/* 86 */     this.pts = pts;
/*    */   }
/*    */   
/*    */   public void filter(Geometry geom) {
/* 91 */     if (geom instanceof com.vividsolutions.jts.geom.Point)
/* 91 */       this.pts.add(geom); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\PointExtracter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */