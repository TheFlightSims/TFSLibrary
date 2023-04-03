/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class MBTFPQProjection extends Projection {
/*    */   private static final int NITER = 20;
/*    */   
/*    */   private static final double EPS = 1.0E-7D;
/*    */   
/*    */   private static final double ONETOL = 1.000001D;
/*    */   
/*    */   private static final double C = 1.7071067811865475D;
/*    */   
/*    */   private static final double RC = 0.585786437626905D;
/*    */   
/*    */   private static final double FYC = 1.874758284622695D;
/*    */   
/*    */   private static final double RYC = 0.533402096794177D;
/*    */   
/*    */   private static final double FXC = 0.3124597141037825D;
/*    */   
/*    */   private static final double RXC = 3.2004125807650623D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 41 */     double c = 1.7071067811865475D * Math.sin(lpphi);
/* 42 */     int i = 20;
/*    */     double th1;
/* 43 */     out.y -= th1 = (Math.sin(0.5D * lpphi) + Math.sin(lpphi) - c) / (0.5D * Math.cos(0.5D * lpphi) + Math.cos(lpphi));
/* 45 */     for (; i > 0 && Math.abs(th1) >= 1.0E-7D; i--);
/* 47 */     out.x = 0.3124597141037825D * lplam * (1.0D + 2.0D * Math.cos(lpphi) / Math.cos(0.5D * lpphi));
/* 48 */     out.y = 1.874758284622695D * Math.sin(0.5D * lpphi);
/* 49 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 53 */     double t = 0.0D;
/* 55 */     double lpphi = 0.533402096794177D * xyy;
/* 56 */     if (Math.abs(lpphi) > 1.0D) {
/* 57 */       if (Math.abs(lpphi) > 1.000001D)
/* 57 */         throw new ProjectionException("I"); 
/* 58 */       if (lpphi < 0.0D) {
/* 58 */         t = -1.0D;
/* 58 */         lpphi = -3.141592653589793D;
/*    */       } else {
/* 59 */         t = 1.0D;
/* 59 */         lpphi = Math.PI;
/*    */       } 
/*    */     } else {
/* 61 */       lpphi = 2.0D * Math.asin(t = lpphi);
/*    */     } 
/* 62 */     out.x = 3.2004125807650623D * xyx / (1.0D + 2.0D * Math.cos(lpphi) / Math.cos(0.5D * lpphi));
/* 63 */     lpphi = 0.585786437626905D * (t + Math.sin(lpphi));
/* 64 */     if (Math.abs(lpphi) > 1.0D) {
/* 65 */       if (Math.abs(lpphi) > 1.000001D)
/* 66 */         throw new ProjectionException("I"); 
/* 68 */       lpphi = (lpphi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*    */     } else {
/* 70 */       lpphi = Math.asin(lpphi);
/*    */     } 
/* 71 */     out.y = lpphi;
/* 72 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 76 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     return "McBryde-Thomas Flat-Polar Quartic";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\MBTFPQProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */