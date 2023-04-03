/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class FaheyProjection extends Projection {
/*    */   private static final double TOL = 1.0E-6D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 30 */     out.y = 1.819152D * (out.x = Math.tan(0.5D * lpphi));
/* 31 */     out.x = 0.819152D * lplam * asqrt(1.0D - out.x * out.x);
/* 32 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 36 */     out.y = 2.0D * Math.atan(out.y /= 1.819152D);
/* 37 */     out.x = (Math.abs(out.y = 1.0D - xyy * xyy) < 1.0E-6D) ? 0.0D : (xyx / 0.819152D * Math.sqrt(xyy));
/* 39 */     return out;
/*    */   }
/*    */   
/*    */   private double asqrt(double v) {
/* 43 */     return (v <= 0.0D) ? 0.0D : Math.sqrt(v);
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 47 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return "Fahey";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\FaheyProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */