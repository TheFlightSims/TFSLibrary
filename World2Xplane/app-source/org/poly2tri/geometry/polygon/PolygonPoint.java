/*    */ package org.poly2tri.geometry.polygon;
/*    */ 
/*    */ import org.poly2tri.triangulation.point.TPoint;
/*    */ 
/*    */ public class PolygonPoint extends TPoint {
/*    */   protected PolygonPoint _next;
/*    */   
/*    */   protected PolygonPoint _previous;
/*    */   
/*    */   public PolygonPoint(double x, double y) {
/* 34 */     super(x, y);
/*    */   }
/*    */   
/*    */   public PolygonPoint(double x, double y, double z) {
/* 39 */     super(x, y, z);
/*    */   }
/*    */   
/*    */   public void setPrevious(PolygonPoint p) {
/* 44 */     this._previous = p;
/*    */   }
/*    */   
/*    */   public void setNext(PolygonPoint p) {
/* 49 */     this._next = p;
/*    */   }
/*    */   
/*    */   public PolygonPoint getNext() {
/* 54 */     return this._next;
/*    */   }
/*    */   
/*    */   public PolygonPoint getPrevious() {
/* 59 */     return this._previous;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\geometry\polygon\PolygonPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */