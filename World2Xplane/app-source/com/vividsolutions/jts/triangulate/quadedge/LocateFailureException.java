/*    */ package com.vividsolutions.jts.triangulate.quadedge;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ 
/*    */ public class LocateFailureException extends RuntimeException {
/*    */   private static String msgWithSpatial(String msg, LineSegment seg) {
/* 41 */     if (seg != null)
/* 42 */       return msg + " [ " + seg + " ]"; 
/* 43 */     return msg;
/*    */   }
/*    */   
/* 46 */   private LineSegment seg = null;
/*    */   
/*    */   public LocateFailureException(String msg) {
/* 49 */     super(msg);
/*    */   }
/*    */   
/*    */   public LocateFailureException(String msg, LineSegment seg) {
/* 53 */     super(msgWithSpatial(msg, seg));
/* 54 */     this.seg = new LineSegment(seg);
/*    */   }
/*    */   
/*    */   public LocateFailureException(LineSegment seg) {
/* 58 */     super("Locate failed to converge (at edge: " + seg + ").  Possible causes include invalid Subdivision topology or very close sites");
/* 62 */     this.seg = new LineSegment(seg);
/*    */   }
/*    */   
/*    */   public LineSegment getSegment() {
/* 66 */     return this.seg;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\LocateFailureException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */