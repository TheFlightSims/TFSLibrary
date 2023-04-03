/*    */ package com.vividsolutions.jts.linearref;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ 
/*    */ class LocationIndexOfLine {
/*    */   private Geometry linearGeom;
/*    */   
/*    */   public static LinearLocation[] indicesOf(Geometry linearGeom, Geometry subLine) {
/* 56 */     LocationIndexOfLine locater = new LocationIndexOfLine(linearGeom);
/* 57 */     return locater.indicesOf(subLine);
/*    */   }
/*    */   
/*    */   public LocationIndexOfLine(Geometry linearGeom) {
/* 63 */     this.linearGeom = linearGeom;
/*    */   }
/*    */   
/*    */   public LinearLocation[] indicesOf(Geometry subLine) {
/* 68 */     Coordinate startPt = ((LineString)subLine.getGeometryN(0)).getCoordinateN(0);
/* 69 */     LineString lastLine = (LineString)subLine.getGeometryN(subLine.getNumGeometries() - 1);
/* 70 */     Coordinate endPt = lastLine.getCoordinateN(lastLine.getNumPoints() - 1);
/* 72 */     LocationIndexOfPoint locPt = new LocationIndexOfPoint(this.linearGeom);
/* 73 */     LinearLocation[] subLineLoc = new LinearLocation[2];
/* 74 */     subLineLoc[0] = locPt.indexOf(startPt);
/* 77 */     if (subLine.getLength() == 0.0D) {
/* 78 */       subLineLoc[1] = (LinearLocation)subLineLoc[0].clone();
/*    */     } else {
/* 81 */       subLineLoc[1] = locPt.indexOfAfter(endPt, subLineLoc[0]);
/*    */     } 
/* 83 */     return subLineLoc;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LocationIndexOfLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */