/*    */ package com.vividsolutions.jtsexample.geom;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*    */ 
/*    */ public class ExtendedCoordinateSequenceFactory implements CoordinateSequenceFactory {
/* 47 */   private static ExtendedCoordinateSequenceFactory instance = new ExtendedCoordinateSequenceFactory();
/*    */   
/*    */   public static ExtendedCoordinateSequenceFactory instance() {
/* 56 */     return instance;
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(Coordinate[] coordinates) {
/* 65 */     return (coordinates instanceof ExtendedCoordinate[]) ? new ExtendedCoordinateSequence((ExtendedCoordinate[])coordinates) : new ExtendedCoordinateSequence(coordinates);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(CoordinateSequence coordSeq) {
/* 71 */     return (coordSeq instanceof ExtendedCoordinateSequence) ? new ExtendedCoordinateSequence(coordSeq) : new ExtendedCoordinateSequence(coordSeq);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(int size, int dimension) {
/* 80 */     return new ExtendedCoordinateSequence(size);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\ExtendedCoordinateSequenceFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */