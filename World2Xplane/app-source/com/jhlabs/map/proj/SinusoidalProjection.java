/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class SinusoidalProjection extends PseudoCylindricalProjection {
/*    */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/* 29 */     xy.x = lam * Math.cos(phi);
/* 30 */     xy.y = phi;
/* 31 */     return xy;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 35 */     lp.x = x / Math.cos(y);
/* 36 */     lp.y = y;
/* 37 */     return lp;
/*    */   }
/*    */   
/*    */   public double getWidth(double y) {
/* 41 */     return MapMath.normalizeLongitude(Math.PI) * Math.cos(y);
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 49 */     return "Sinusoidal";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\SinusoidalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */