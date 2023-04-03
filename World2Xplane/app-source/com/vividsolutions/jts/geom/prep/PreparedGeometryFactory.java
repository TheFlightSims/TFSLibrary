/*    */ package com.vividsolutions.jts.geom.prep;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.Lineal;
/*    */ import com.vividsolutions.jts.geom.Polygonal;
/*    */ import com.vividsolutions.jts.geom.Puntal;
/*    */ 
/*    */ public class PreparedGeometryFactory {
/*    */   public static PreparedGeometry prepare(Geometry geom) {
/* 59 */     return (new PreparedGeometryFactory()).create(geom);
/*    */   }
/*    */   
/*    */   public PreparedGeometry create(Geometry geom) {
/* 73 */     if (geom instanceof Polygonal)
/* 74 */       return new PreparedPolygon((Polygonal)geom); 
/* 75 */     if (geom instanceof Lineal)
/* 76 */       return new PreparedLineString((Lineal)geom); 
/* 77 */     if (geom instanceof Puntal)
/* 78 */       return new PreparedPoint((Puntal)geom); 
/* 83 */     return new BasicPreparedGeometry(geom);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedGeometryFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */