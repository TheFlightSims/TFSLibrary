/*    */ package com.vividsolutions.jts.triangulate;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.io.WKTWriter;
/*    */ 
/*    */ public class ConstraintEnforcementException extends RuntimeException {
/*    */   private static final long serialVersionUID = 386496846550080140L;
/*    */   
/*    */   private static String msgWithCoord(String msg, Coordinate pt) {
/* 50 */     if (pt != null)
/* 51 */       return msg + " [ " + WKTWriter.toPoint(pt) + " ]"; 
/* 52 */     return msg;
/*    */   }
/*    */   
/* 55 */   private Coordinate pt = null;
/*    */   
/*    */   public ConstraintEnforcementException(String msg) {
/* 63 */     super(msg);
/*    */   }
/*    */   
/*    */   public ConstraintEnforcementException(String msg, Coordinate pt) {
/* 73 */     super(msgWithCoord(msg, pt));
/* 74 */     this.pt = new Coordinate(pt);
/*    */   }
/*    */   
/*    */   public Coordinate getCoordinate() {
/* 83 */     return this.pt;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\ConstraintEnforcementException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */