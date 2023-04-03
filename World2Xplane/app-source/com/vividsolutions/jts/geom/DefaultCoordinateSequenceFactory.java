/*    */ package com.vividsolutions.jts.geom;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DefaultCoordinateSequenceFactory implements CoordinateSequenceFactory, Serializable {
/*    */   private static final long serialVersionUID = -4099577099607551657L;
/*    */   
/* 49 */   private static final DefaultCoordinateSequenceFactory instanceObject = new DefaultCoordinateSequenceFactory();
/*    */   
/*    */   private Object readResolve() {
/* 56 */     return instance();
/*    */   }
/*    */   
/*    */   public static DefaultCoordinateSequenceFactory instance() {
/* 63 */     return instanceObject;
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(Coordinate[] coordinates) {
/* 77 */     return new DefaultCoordinateSequence(coordinates);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(CoordinateSequence coordSeq) {
/* 84 */     return new DefaultCoordinateSequence(coordSeq);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(int size, int dimension) {
/* 91 */     return new DefaultCoordinateSequence(size);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\DefaultCoordinateSequenceFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */