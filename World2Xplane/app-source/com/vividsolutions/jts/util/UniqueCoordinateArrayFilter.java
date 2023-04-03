/*    */ package com.vividsolutions.jts.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ public class UniqueCoordinateArrayFilter implements CoordinateFilter {
/*    */   public static Coordinate[] filterCoordinates(Coordinate[] coords) {
/* 60 */     UniqueCoordinateArrayFilter filter = new UniqueCoordinateArrayFilter();
/* 61 */     for (int i = 0; i < coords.length; i++)
/* 62 */       filter.filter(coords[i]); 
/* 64 */     return filter.getCoordinates();
/*    */   }
/*    */   
/* 67 */   TreeSet treeSet = new TreeSet();
/*    */   
/* 68 */   ArrayList list = new ArrayList();
/*    */   
/*    */   public Coordinate[] getCoordinates() {
/* 78 */     Coordinate[] coordinates = new Coordinate[this.list.size()];
/* 79 */     return (Coordinate[])this.list.toArray((Object[])coordinates);
/*    */   }
/*    */   
/*    */   public void filter(Coordinate coord) {
/* 83 */     if (!this.treeSet.contains(coord)) {
/* 84 */       this.list.add(coord);
/* 85 */       this.treeSet.add(coord);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\UniqueCoordinateArrayFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */