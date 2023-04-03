/*    */ package org.geotools.gml;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.Point;
/*    */ 
/*    */ public class SubHandlerPoint extends SubHandler {
/* 37 */   Coordinate coordinate = null;
/*    */   
/*    */   public void addCoordinate(Coordinate coordinate) {
/* 51 */     this.coordinate = coordinate;
/*    */   }
/*    */   
/*    */   public boolean isComplete(String message) {
/* 62 */     if (this.coordinate != null)
/* 63 */       return true; 
/* 65 */     return false;
/*    */   }
/*    */   
/*    */   public Geometry create(GeometryFactory geometryFactory) {
/* 77 */     Point point = geometryFactory.createPoint(this.coordinate);
/* 78 */     point.setUserData(getSRS());
/* 79 */     point.setSRID(getSRID());
/* 80 */     return (Geometry)point;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandlerPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */