/*    */ package com.vividsolutions.jts.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*    */ 
/*    */ public class CoordinateCountFilter implements CoordinateFilter {
/* 46 */   private int n = 0;
/*    */   
/*    */   public int getCount() {
/* 56 */     return this.n;
/*    */   }
/*    */   
/*    */   public void filter(Coordinate coord) {
/* 60 */     this.n++;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\CoordinateCountFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */