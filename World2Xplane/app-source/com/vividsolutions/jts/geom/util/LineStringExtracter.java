/*    */ package com.vividsolutions.jts.geom.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class LineStringExtracter implements GeometryFilter {
/*    */   private List comps;
/*    */   
/*    */   public static List getLines(Geometry geom, List<Geometry> lines) {
/* 57 */     if (geom instanceof com.vividsolutions.jts.geom.LineString) {
/* 58 */       lines.add(geom);
/* 60 */     } else if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 61 */       geom.apply(new LineStringExtracter(lines));
/*    */     } 
/* 65 */     return lines;
/*    */   }
/*    */   
/*    */   public static List getLines(Geometry geom) {
/* 76 */     return getLines(geom, new ArrayList());
/*    */   }
/*    */   
/*    */   public LineStringExtracter(List comps) {
/* 86 */     this.comps = comps;
/*    */   }
/*    */   
/*    */   public void filter(Geometry geom) {
/* 91 */     if (geom instanceof com.vividsolutions.jts.geom.LineString)
/* 91 */       this.comps.add(geom); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\LineStringExtracter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */