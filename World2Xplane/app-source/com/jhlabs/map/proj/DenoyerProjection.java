/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class DenoyerProjection extends Projection {
/*    */   public static final double C0 = 0.95D;
/*    */   
/*    */   public static final double C1 = -0.08333333333333333D;
/*    */   
/*    */   public static final double C3 = 0.0016666666666666666D;
/*    */   
/*    */   public static final double D1 = 0.9D;
/*    */   
/*    */   public static final double D5 = 0.03D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 34 */     out.y = lpphi;
/* 35 */     out.x = lplam;
/* 36 */     double aphi = Math.abs(lplam);
/* 37 */     out.x *= Math.cos((0.95D + aphi * (-0.08333333333333333D + aphi * aphi * 0.0016666666666666666D)) * lpphi * (0.9D + 0.03D * lpphi * lpphi * lpphi * lpphi));
/* 39 */     return out;
/*    */   }
/*    */   
/*    */   public boolean parallelsAreParallel() {
/* 43 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 47 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return "Denoyer Semi-elliptical";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\DenoyerProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */