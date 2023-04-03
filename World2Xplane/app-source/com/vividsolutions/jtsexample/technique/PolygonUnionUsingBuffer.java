/*    */ package com.vividsolutions.jtsexample.technique;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryCollection;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.io.WKTReader;
/*    */ 
/*    */ public class PolygonUnionUsingBuffer {
/*    */   public static void main(String[] args) throws Exception {
/* 30 */     WKTReader rdr = new WKTReader();
/* 32 */     Geometry[] geom = new Geometry[3];
/* 33 */     geom[0] = rdr.read("POLYGON (( 100 180, 100 260, 180 260, 180 180, 100 180 ))");
/* 34 */     geom[1] = rdr.read("POLYGON (( 80 140, 80 200, 200 200, 200 140, 80 140 ))");
/* 35 */     geom[2] = rdr.read("POLYGON (( 160 160, 160 240, 240 240, 240 160, 160 160 ))");
/* 36 */     unionUsingBuffer(geom);
/*    */   }
/*    */   
/*    */   public static void unionUsingBuffer(Geometry[] geom) {
/* 42 */     GeometryFactory fact = geom[0].getFactory();
/* 43 */     GeometryCollection geometryCollection = fact.createGeometryCollection(geom);
/* 44 */     Geometry union = geometryCollection.buffer(0.0D);
/* 45 */     System.out.println(union);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\technique\PolygonUnionUsingBuffer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */