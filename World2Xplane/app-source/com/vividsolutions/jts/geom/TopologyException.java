/*    */ package com.vividsolutions.jts.geom;
/*    */ 
/*    */ public class TopologyException extends RuntimeException {
/*    */   private static String msgWithCoord(String msg, Coordinate pt) {
/* 48 */     if (pt != null)
/* 49 */       return msg + " [ " + pt + " ]"; 
/* 50 */     return msg;
/*    */   }
/*    */   
/* 53 */   private Coordinate pt = null;
/*    */   
/*    */   public TopologyException(String msg) {
/* 57 */     super(msg);
/*    */   }
/*    */   
/*    */   public TopologyException(String msg, Coordinate pt) {
/* 62 */     super(msgWithCoord(msg, pt));
/* 63 */     this.pt = new Coordinate(pt);
/*    */   }
/*    */   
/*    */   public Coordinate getCoordinate() {
/* 66 */     return this.pt;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\TopologyException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */