/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class BoggsProjection extends PseudoCylindricalProjection {
/*    */   private static final int NITER = 20;
/*    */   
/*    */   private static final double EPS = 1.0E-7D;
/*    */   
/*    */   private static final double ONETOL = 1.000001D;
/*    */   
/*    */   private static final double FXC = 2.00276D;
/*    */   
/*    */   private static final double FXC2 = 1.11072D;
/*    */   
/*    */   private static final double FYC = 0.49931D;
/*    */   
/*    */   private static final double FYC2 = 1.4142135623730951D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 39 */     double theta = lpphi;
/* 40 */     if (Math.abs(Math.abs(lpphi) - 1.5707963267948966D) < 1.0E-7D) {
/* 41 */       out.x = 0.0D;
/*    */     } else {
/* 43 */       double c = Math.sin(theta) * Math.PI;
/* 44 */       int i = 20;
/*    */       double th1;
/* 45 */       theta -= th1 = (theta + Math.sin(theta) - c) / (1.0D + Math.cos(theta));
/* 47 */       for (; i > 0 && Math.abs(th1) >= 1.0E-7D; i--);
/* 49 */       theta *= 0.5D;
/* 50 */       out.x = 2.00276D * lplam / (1.0D / Math.cos(lpphi) + 1.11072D / Math.cos(theta));
/*    */     } 
/* 52 */     out.y = 0.49931D * (lpphi + 1.4142135623730951D * Math.sin(theta));
/* 53 */     return out;
/*    */   }
/*    */   
/*    */   public boolean isEqualArea() {
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 64 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     return "Boggs Eumorphic";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\BoggsProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */