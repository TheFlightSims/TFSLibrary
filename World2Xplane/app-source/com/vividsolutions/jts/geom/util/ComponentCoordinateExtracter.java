/*    */ package com.vividsolutions.jts.geom.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ComponentCoordinateExtracter implements GeometryComponentFilter {
/*    */   private List coords;
/*    */   
/*    */   public static List getCoordinates(Geometry geom) {
/* 60 */     List coords = new ArrayList();
/* 61 */     geom.apply(new ComponentCoordinateExtracter(coords));
/* 62 */     return coords;
/*    */   }
/*    */   
/*    */   public ComponentCoordinateExtracter(List coords) {
/* 72 */     this.coords = coords;
/*    */   }
/*    */   
/*    */   public void filter(Geometry geom) {
/* 78 */     if (geom instanceof com.vividsolutions.jts.geom.LineString || geom instanceof com.vividsolutions.jts.geom.Point)
/* 80 */       this.coords.add(geom.getCoordinate()); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\ComponentCoordinateExtracter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */