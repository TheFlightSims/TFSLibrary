/*    */ package org.geotools.geometry.jts;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*    */ 
/*    */ public class LiteCoordinateSequenceFactory implements CoordinateSequenceFactory {
/*    */   public CoordinateSequence create(Coordinate[] coordinates) {
/* 38 */     return (CoordinateSequence)new LiteCoordinateSequence(coordinates);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(CoordinateSequence coordSeq) {
/* 49 */     if (coordSeq instanceof LiteCoordinateSequence)
/* 50 */       return (CoordinateSequence)new LiteCoordinateSequence((LiteCoordinateSequence)coordSeq); 
/* 51 */     return (CoordinateSequence)new LiteCoordinateSequence(coordSeq.toCoordinateArray());
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(int size, int dimension) {
/* 58 */     return (CoordinateSequence)new LiteCoordinateSequence(size, dimension);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(double[] points) {
/* 65 */     return (CoordinateSequence)new LiteCoordinateSequence(points);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(double[] points, int dimension) {
/* 72 */     return (CoordinateSequence)new LiteCoordinateSequence(points, dimension);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\LiteCoordinateSequenceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */