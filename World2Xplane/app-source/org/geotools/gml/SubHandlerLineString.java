/*    */ package org.geotools.gml;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SubHandlerLineString extends SubHandler {
/* 39 */   private ArrayList coordinateList = new ArrayList();
/*    */   
/*    */   public void addCoordinate(Coordinate coordinate) {
/* 53 */     this.coordinateList.add(coordinate);
/*    */   }
/*    */   
/*    */   public boolean isComplete(String message) {
/* 64 */     if (this.coordinateList.size() > 1)
/* 65 */       return true; 
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public Geometry create(GeometryFactory geometryFactory) {
/* 79 */     Coordinate[] coords = (Coordinate[])this.coordinateList.toArray((Object[])new Coordinate[this.coordinateList.size()]);
/* 80 */     LineString lineString = geometryFactory.createLineString(coords);
/* 81 */     lineString.setUserData(getSRS());
/* 82 */     lineString.setSRID(getSRID());
/* 83 */     return (Geometry)lineString;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandlerLineString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */