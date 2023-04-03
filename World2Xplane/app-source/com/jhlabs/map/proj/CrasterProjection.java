/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class CrasterProjection extends Projection {
/*    */   private static final double XM = 0.9772050238058398D;
/*    */   
/*    */   private static final double RXM = 1.0233267079464885D;
/*    */   
/*    */   private static final double YM = 3.0699801238394655D;
/*    */   
/*    */   private static final double RYM = 0.32573500793527993D;
/*    */   
/*    */   private static final double THIRD = 0.3333333333333333D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 34 */     lpphi *= 0.3333333333333333D;
/* 35 */     out.x = 0.9772050238058398D * lplam * (2.0D * Math.cos(lpphi + lpphi) - 1.0D);
/* 36 */     out.y = 3.0699801238394655D * Math.sin(lpphi);
/* 37 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 41 */     out.y = 3.0D * Math.asin(xyy * 0.32573500793527993D);
/* 42 */     out.x = xyx * 1.0233267079464885D / (2.0D * Math.cos((out.y + out.y) * 0.3333333333333333D) - 1.0D);
/* 43 */     return out;
/*    */   }
/*    */   
/*    */   public boolean isEqualArea() {
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return "Craster Parabolic (Putnins P4)";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\CrasterProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */