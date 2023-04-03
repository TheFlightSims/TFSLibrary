/*    */ package com.vividsolutions.jts.precision;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*    */ import com.vividsolutions.jts.geom.PrecisionModel;
/*    */ 
/*    */ public class CoordinatePrecisionReducerFilter implements CoordinateSequenceFilter {
/*    */   private PrecisionModel precModel;
/*    */   
/*    */   public CoordinatePrecisionReducerFilter(PrecisionModel precModel) {
/* 61 */     this.precModel = precModel;
/*    */   }
/*    */   
/*    */   public void filter(CoordinateSequence seq, int i) {
/* 69 */     seq.setOrdinate(i, 0, this.precModel.makePrecise(seq.getOrdinate(i, 0)));
/* 70 */     seq.setOrdinate(i, 1, this.precModel.makePrecise(seq.getOrdinate(i, 1)));
/*    */   }
/*    */   
/*    */   public boolean isDone() {
/* 78 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isGeometryChanged() {
/* 85 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\CoordinatePrecisionReducerFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */