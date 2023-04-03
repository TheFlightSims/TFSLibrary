/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class AugustProjection extends Projection {
/*    */   private static final double M = 1.333333333333333D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 32 */     double t = Math.tan(0.5D * lpphi);
/* 33 */     double c1 = Math.sqrt(1.0D - t * t);
/* 34 */     double c = 1.0D + c1 * Math.cos(lplam *= 0.5D);
/* 35 */     double x1 = Math.sin(lplam) * c1 / c;
/* 36 */     double y1 = t / c;
/*    */     double x12, y12;
/* 37 */     out.x = 1.333333333333333D * x1 * (3.0D + (x12 = x1 * x1) - 3.0D * (y12 = y1 * y1));
/* 38 */     out.y = 1.333333333333333D * y1 * (3.0D + 3.0D * x12 - y12);
/* 39 */     return out;
/*    */   }
/*    */   
/*    */   public boolean isConformal() {
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     return "August Epicycloidal";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\AugustProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */