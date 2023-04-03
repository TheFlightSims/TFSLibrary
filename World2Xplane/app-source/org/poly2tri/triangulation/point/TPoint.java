/*    */ package org.poly2tri.triangulation.point;
/*    */ 
/*    */ import org.poly2tri.triangulation.TriangulationPoint;
/*    */ 
/*    */ public class TPoint extends TriangulationPoint {
/*    */   private double _x;
/*    */   
/*    */   private double _y;
/*    */   
/*    */   private double _z;
/*    */   
/*    */   public TPoint(double x, double y) {
/* 34 */     this(x, y, 0.0D);
/*    */   }
/*    */   
/*    */   public TPoint(double x, double y, double z) {
/* 39 */     this._x = x;
/* 40 */     this._y = y;
/* 41 */     this._z = z;
/*    */   }
/*    */   
/*    */   public double getX() {
/* 44 */     return this._x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 45 */     return this._y;
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 46 */     return this._z;
/*    */   }
/*    */   
/*    */   public float getXf() {
/* 48 */     return (float)this._x;
/*    */   }
/*    */   
/*    */   public float getYf() {
/* 49 */     return (float)this._y;
/*    */   }
/*    */   
/*    */   public float getZf() {
/* 50 */     return (float)this._z;
/*    */   }
/*    */   
/*    */   public void set(double x, double y, double z) {
/* 55 */     this._x = x;
/* 56 */     this._y = y;
/* 57 */     this._z = z;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\point\TPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */