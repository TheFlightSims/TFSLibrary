/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class MBTFPSProjection extends Projection {
/*    */   private static final int MAX_ITER = 10;
/*    */   
/*    */   private static final double LOOP_TOL = 1.0E-7D;
/*    */   
/*    */   private static final double C1 = 0.45503D;
/*    */   
/*    */   private static final double C2 = 1.36509D;
/*    */   
/*    */   private static final double C3 = 1.41546D;
/*    */   
/*    */   private static final double C_x = 0.22248D;
/*    */   
/*    */   private static final double C_y = 1.44492D;
/*    */   
/*    */   private static final double C1_2 = 0.3333333333333333D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 40 */     double k = 1.41546D * Math.sin(lpphi);
/* 41 */     int i = 10;
/* 42 */     double t = lpphi / 1.36509D;
/*    */     double V;
/* 43 */     out.y -= V = (0.45503D * Math.sin(t) + Math.sin(lpphi) - k) / (0.3333333333333333D * Math.cos(t) + Math.cos(lpphi));
/* 45 */     for (; i > 0 && Math.abs(V) >= 1.0E-7D; i--);
/* 48 */     t = lpphi / 1.36509D;
/* 49 */     out.x = 0.22248D * lplam * (1.0D + 3.0D * Math.cos(lpphi) / Math.cos(t));
/* 50 */     out.y = 1.44492D * Math.sin(t);
/* 51 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/*    */     double t;
/* 57 */     out.y = 1.36509D * (t = MapMath.asin(xyy / 1.44492D));
/* 58 */     out.x = xyx / 0.22248D * (1.0D + 3.0D * Math.cos(out.y) / Math.cos(t));
/* 59 */     out.y = MapMath.asin((0.45503D * Math.sin(t) + Math.sin(out.y)) / 1.41546D);
/* 60 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     return "McBryde-Thomas Flat-Pole Sine (No. 2)";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\MBTFPSProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */