/*    */ package com.vividsolutions.jtsexample.geom;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.MultiPoint;
/*    */ import com.vividsolutions.jts.geom.Point;
/*    */ 
/*    */ public class ConstructionExample {
/*    */   public static void main(String[] args) throws Exception {
/* 28 */     GeometryFactory fact = new GeometryFactory();
/* 30 */     Point p1 = fact.createPoint(new Coordinate(0.0D, 0.0D));
/* 31 */     System.out.println(p1);
/* 33 */     Point p2 = fact.createPoint(new Coordinate(1.0D, 1.0D));
/* 34 */     System.out.println(p2);
/* 36 */     MultiPoint mpt = fact.createMultiPoint(new Coordinate[] { new Coordinate(0.0D, 0.0D), new Coordinate(1.0D, 1.0D) });
/* 37 */     System.out.println(mpt);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\ConstructionExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */