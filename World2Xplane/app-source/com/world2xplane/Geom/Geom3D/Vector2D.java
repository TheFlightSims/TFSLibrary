/*    */ package com.world2xplane.Geom.Geom3D;
/*    */ 
/*    */ public class Vector2D {
/*    */   public double x;
/*    */   
/*    */   public double y;
/*    */   
/*    */   public Vector2D(double x, double y) {
/* 22 */     this.x = x;
/* 23 */     this.y = y;
/*    */   }
/*    */   
/*    */   public Vector2D sub(Vector2D v) {
/* 27 */     return new Vector2D(this.x - v.x, this.y - v.y);
/*    */   }
/*    */   
/*    */   public Vector2D add(Vector2D v) {
/* 31 */     return new Vector2D(this.x + v.x, this.y + v.y);
/*    */   }
/*    */   
/*    */   public Vector2D mult(double s) {
/* 35 */     return new Vector2D(this.x * s, this.y * s);
/*    */   }
/*    */   
/*    */   public double dot(Vector2D v) {
/* 39 */     return this.x * v.x + this.y * v.y;
/*    */   }
/*    */   
/*    */   public double mag() {
/* 43 */     return Math.sqrt(this.x * this.x + this.y * this.y);
/*    */   }
/*    */   
/*    */   public double cross(Vector2D v) {
/* 53 */     return this.y * v.x - this.x * v.y;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     return "Vector2D(" + this.x + ", " + this.y + ")";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Geom3D\Vector2D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */