/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class NellProjection extends Projection {
/*    */   private static final int MAX_ITER = 10;
/*    */   
/*    */   private static final double LOOP_TOL = 1.0E-7D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 34 */     double k = 2.0D * Math.sin(lpphi);
/* 35 */     double V = lpphi * lpphi;
/* 36 */     out.y *= 1.00371D + V * (-0.0935382D + V * -0.011412D);
/* 37 */     int i = 10;
/* 38 */     out.y -= V = (lpphi + Math.sin(lpphi) - k) / (1.0D + Math.cos(lpphi));
/* 40 */     for (; i > 0 && Math.abs(V) >= 1.0E-7D; i--);
/* 43 */     out.x = 0.5D * lplam * (1.0D + Math.cos(lpphi));
/* 44 */     out.y = lpphi;
/* 45 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 51 */     out.x = 2.0D * xyx / (1.0D + Math.cos(xyy));
/* 52 */     out.y = MapMath.asin(0.5D * (xyy + Math.sin(xyy)));
/* 53 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 61 */     return "Nell";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\NellProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */