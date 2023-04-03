/*    */ package org.geotools.gml;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.LinearRing;
/*    */ import com.vividsolutions.jts.geom.TopologyException;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SubHandlerLinearRing extends SubHandler {
/* 40 */   private ArrayList coordinateList = new ArrayList();
/*    */   
/*    */   public void addCoordinate(Coordinate coordinate) {
/* 54 */     this.coordinateList.add(coordinate);
/*    */   }
/*    */   
/*    */   public boolean isComplete(String message) {
/* 66 */     if (this.coordinateList.size() > 1) {
/* 67 */       Coordinate firstCoordinate = this.coordinateList.get(0);
/* 68 */       Coordinate lastCoordinate = this.coordinateList.get(this.coordinateList.size() - 1);
/* 71 */       if (lastCoordinate.equals2D(firstCoordinate))
/* 72 */         return true; 
/* 74 */       return false;
/*    */     } 
/* 77 */     return false;
/*    */   }
/*    */   
/*    */   public Geometry create(GeometryFactory geometryFactory) {
/*    */     try {
/* 90 */       Coordinate[] coords = (Coordinate[])this.coordinateList.toArray((Object[])new Coordinate[this.coordinateList.size()]);
/* 91 */       LinearRing ring = geometryFactory.createLinearRing(coords);
/* 92 */       ring.setUserData(getSRS());
/* 93 */       ring.setSRID(getSRID());
/* 94 */       return (Geometry)ring;
/* 95 */     } catch (TopologyException e) {
/* 96 */       System.err.println("Caught Topology exception in GMLLinearRingHandler");
/* 99 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandlerLinearRing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */