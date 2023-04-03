/*    */ package com.vividsolutions.jts.precision;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateList;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.PrecisionModel;
/*    */ import com.vividsolutions.jts.geom.util.GeometryEditor;
/*    */ 
/*    */ public class PrecisionReducerCoordinateOperation extends GeometryEditor.CoordinateOperation {
/*    */   private PrecisionModel targetPM;
/*    */   
/*    */   private boolean removeCollapsed = true;
/*    */   
/*    */   public PrecisionReducerCoordinateOperation(PrecisionModel targetPM, boolean removeCollapsed) {
/* 51 */     this.targetPM = targetPM;
/* 52 */     this.removeCollapsed = removeCollapsed;
/*    */   }
/*    */   
/*    */   public Coordinate[] edit(Coordinate[] coordinates, Geometry geom) {
/* 56 */     if (coordinates.length == 0)
/* 57 */       return null; 
/* 59 */     Coordinate[] reducedCoords = new Coordinate[coordinates.length];
/* 61 */     for (int i = 0; i < coordinates.length; i++) {
/* 62 */       Coordinate coord = new Coordinate(coordinates[i]);
/* 63 */       this.targetPM.makePrecise(coord);
/* 64 */       reducedCoords[i] = coord;
/*    */     } 
/* 67 */     CoordinateList noRepeatedCoordList = new CoordinateList(reducedCoords, false);
/* 69 */     Coordinate[] noRepeatedCoords = noRepeatedCoordList.toCoordinateArray();
/* 80 */     int minLength = 0;
/* 81 */     if (geom instanceof com.vividsolutions.jts.geom.LineString)
/* 82 */       minLength = 2; 
/* 83 */     if (geom instanceof com.vividsolutions.jts.geom.LinearRing)
/* 84 */       minLength = 4; 
/* 86 */     Coordinate[] collapsedCoords = reducedCoords;
/* 87 */     if (this.removeCollapsed)
/* 88 */       collapsedCoords = null; 
/* 91 */     if (noRepeatedCoords.length < minLength)
/* 92 */       return collapsedCoords; 
/* 96 */     return noRepeatedCoords;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\PrecisionReducerCoordinateOperation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */