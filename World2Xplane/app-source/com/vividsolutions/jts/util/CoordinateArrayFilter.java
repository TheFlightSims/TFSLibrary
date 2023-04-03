/*    */ package com.vividsolutions.jts.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*    */ 
/*    */ public class CoordinateArrayFilter implements CoordinateFilter {
/* 46 */   Coordinate[] pts = null;
/*    */   
/* 47 */   int n = 0;
/*    */   
/*    */   public CoordinateArrayFilter(int size) {
/* 56 */     this.pts = new Coordinate[size];
/*    */   }
/*    */   
/*    */   public Coordinate[] getCoordinates() {
/* 65 */     return this.pts;
/*    */   }
/*    */   
/*    */   public void filter(Coordinate coord) {
/* 69 */     this.pts[this.n++] = coord;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\CoordinateArrayFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */