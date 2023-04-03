/*    */ package org.poly2tri.geometry.polygon;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PolygonSet {
/* 29 */   protected ArrayList<Polygon> _polygons = new ArrayList<>();
/*    */   
/*    */   public PolygonSet() {}
/*    */   
/*    */   public PolygonSet(Polygon poly) {
/* 37 */     this._polygons.add(poly);
/*    */   }
/*    */   
/*    */   public void add(Polygon p) {
/* 42 */     this._polygons.add(p);
/*    */   }
/*    */   
/*    */   public List<Polygon> getPolygons() {
/* 47 */     return this._polygons;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\geometry\polygon\PolygonSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */