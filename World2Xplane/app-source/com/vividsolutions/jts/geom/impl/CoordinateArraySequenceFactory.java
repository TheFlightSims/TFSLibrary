/*    */ package com.vividsolutions.jts.geom.impl;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public final class CoordinateArraySequenceFactory implements CoordinateSequenceFactory, Serializable {
/*    */   private static final long serialVersionUID = -4099577099607551657L;
/*    */   
/* 47 */   private static final CoordinateArraySequenceFactory instanceObject = new CoordinateArraySequenceFactory();
/*    */   
/*    */   private Object readResolve() {
/* 54 */     return instance();
/*    */   }
/*    */   
/*    */   public static CoordinateArraySequenceFactory instance() {
/* 61 */     return instanceObject;
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(Coordinate[] coordinates) {
/* 73 */     return new CoordinateArraySequence(coordinates);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(CoordinateSequence coordSeq) {
/* 80 */     return new CoordinateArraySequence(coordSeq);
/*    */   }
/*    */   
/*    */   public CoordinateSequence create(int size, int dimension) {
/* 90 */     if (dimension > 3)
/* 91 */       dimension = 3; 
/* 94 */     if (dimension < 2)
/* 96 */       return new CoordinateArraySequence(size); 
/* 97 */     return new CoordinateArraySequence(size, dimension);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\impl\CoordinateArraySequenceFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */