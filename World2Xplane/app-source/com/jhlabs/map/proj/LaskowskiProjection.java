/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class LaskowskiProjection extends Projection {
/*    */   private static final double a10 = 0.975534D;
/*    */   
/*    */   private static final double a12 = -0.119161D;
/*    */   
/*    */   private static final double a32 = -0.0143059D;
/*    */   
/*    */   private static final double a14 = -0.0547009D;
/*    */   
/*    */   private static final double b01 = 1.00384D;
/*    */   
/*    */   private static final double b21 = 0.0802894D;
/*    */   
/*    */   private static final double b03 = 0.0998909D;
/*    */   
/*    */   private static final double b41 = 1.99025E-4D;
/*    */   
/*    */   private static final double b23 = -0.02855D;
/*    */   
/*    */   private static final double b05 = -0.0491032D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 42 */     double l2 = lplam * lplam;
/* 43 */     double p2 = lpphi * lpphi;
/* 44 */     out.x = lplam * (0.975534D + p2 * (-0.119161D + l2 * -0.0143059D + p2 * -0.0547009D));
/* 45 */     out.y = lpphi * (1.00384D + l2 * (0.0802894D + p2 * -0.02855D + l2 * 1.99025E-4D) + p2 * (0.0998909D + p2 * -0.0491032D));
/* 47 */     return out;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return "Laskowski";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LaskowskiProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */