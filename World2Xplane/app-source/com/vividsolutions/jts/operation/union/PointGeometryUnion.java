/*    */ package com.vividsolutions.jts.operation.union;
/*    */ 
/*    */ import com.vividsolutions.jts.algorithm.PointLocator;
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.MultiPoint;
/*    */ import com.vividsolutions.jts.geom.Point;
/*    */ import com.vividsolutions.jts.geom.Puntal;
/*    */ import com.vividsolutions.jts.geom.util.GeometryCombiner;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ public class PointGeometryUnion {
/*    */   private Geometry pointGeom;
/*    */   
/*    */   private Geometry otherGeom;
/*    */   
/*    */   private GeometryFactory geomFact;
/*    */   
/*    */   public static Geometry union(Puntal pointGeom, Geometry otherGeom) {
/* 53 */     PointGeometryUnion unioner = new PointGeometryUnion(pointGeom, otherGeom);
/* 54 */     return unioner.union();
/*    */   }
/*    */   
/*    */   public PointGeometryUnion(Puntal pointGeom, Geometry otherGeom) {
/* 63 */     this.pointGeom = (Geometry)pointGeom;
/* 64 */     this.otherGeom = otherGeom;
/* 65 */     this.geomFact = otherGeom.getFactory();
/*    */   }
/*    */   
/*    */   public Geometry union() {
/*    */     MultiPoint multiPoint;
/* 70 */     PointLocator locater = new PointLocator();
/* 72 */     Set<Coordinate> exteriorCoords = new TreeSet();
/* 74 */     for (int i = 0; i < this.pointGeom.getNumGeometries(); i++) {
/* 75 */       Point point = (Point)this.pointGeom.getGeometryN(i);
/* 76 */       Coordinate coord = point.getCoordinate();
/* 77 */       int loc = locater.locate(coord, this.otherGeom);
/* 78 */       if (loc == 2)
/* 79 */         exteriorCoords.add(coord); 
/*    */     } 
/* 83 */     if (exteriorCoords.size() == 0)
/* 84 */       return this.otherGeom; 
/* 87 */     Geometry ptComp = null;
/* 88 */     Coordinate[] coords = CoordinateArrays.toCoordinateArray(exteriorCoords);
/* 89 */     if (coords.length == 1) {
/* 90 */       Point point = this.geomFact.createPoint(coords[0]);
/*    */     } else {
/* 93 */       multiPoint = this.geomFact.createMultiPoint(coords);
/*    */     } 
/* 97 */     return GeometryCombiner.combine((Geometry)multiPoint, this.otherGeom);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operatio\\union\PointGeometryUnion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */