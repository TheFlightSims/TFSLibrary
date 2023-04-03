/*    */ package com.vividsolutions.jts.geom.util;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryCollection;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GeometryCollectionMapper {
/*    */   public static GeometryCollection map(GeometryCollection gc, GeometryMapper.MapOp op) {
/* 52 */     GeometryCollectionMapper mapper = new GeometryCollectionMapper(op);
/* 53 */     return mapper.map(gc);
/*    */   }
/*    */   
/* 56 */   private GeometryMapper.MapOp mapOp = null;
/*    */   
/*    */   public GeometryCollectionMapper(GeometryMapper.MapOp mapOp) {
/* 59 */     this.mapOp = mapOp;
/*    */   }
/*    */   
/*    */   public GeometryCollection map(GeometryCollection gc) {
/* 64 */     List<Geometry> mapped = new ArrayList();
/* 65 */     for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 66 */       Geometry g = this.mapOp.map(gc.getGeometryN(i));
/* 67 */       if (!g.isEmpty())
/* 68 */         mapped.add(g); 
/*    */     } 
/* 70 */     return gc.getFactory().createGeometryCollection(GeometryFactory.toGeometryArray(mapped));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\GeometryCollectionMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */