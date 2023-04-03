/*    */ package com.vividsolutions.jts.geom.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GeometryMapper {
/*    */   public static Geometry map(Geometry geom, MapOp op) {
/* 62 */     List<Geometry> mapped = new ArrayList();
/* 63 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 64 */       Geometry g = op.map(geom.getGeometryN(i));
/* 65 */       if (g != null)
/* 66 */         mapped.add(g); 
/*    */     } 
/* 68 */     return geom.getFactory().buildGeometry(mapped);
/*    */   }
/*    */   
/*    */   public static Collection map(Collection geoms, MapOp op) {
/* 73 */     List<Geometry> mapped = new ArrayList();
/* 74 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/* 75 */       Geometry g = i.next();
/* 76 */       Geometry gr = op.map(g);
/* 77 */       if (gr != null)
/* 78 */         mapped.add(gr); 
/*    */     } 
/* 80 */     return mapped;
/*    */   }
/*    */   
/*    */   public static interface MapOp {
/*    */     Geometry map(Geometry param1Geometry);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\GeometryMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */