/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class LoximuthalProjection extends PseudoCylindricalProjection {
/*    */   private static final double FC = 0.9213177319235613D;
/*    */   
/*    */   private static final double RP = 0.3183098861837907D;
/*    */   
/*    */   private static final double EPS = 1.0E-8D;
/*    */   
/* 36 */   private double phi1 = Math.toRadians(40.0D);
/*    */   
/* 37 */   private double cosphi1 = Math.cos(this.phi1);
/*    */   
/* 38 */   private double tanphi1 = Math.tan(0.7853981633974483D + 0.5D * this.phi1);
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 43 */     double x, y = lpphi - this.phi1;
/* 44 */     if (y < 1.0E-8D) {
/* 45 */       x = lplam * this.cosphi1;
/*    */     } else {
/* 47 */       x = 0.7853981633974483D + 0.5D * lpphi;
/* 48 */       if (Math.abs(x) < 1.0E-8D || Math.abs(Math.abs(x) - 1.5707963267948966D) < 1.0E-8D) {
/* 49 */         x = 0.0D;
/*    */       } else {
/* 51 */         x = lplam * y / Math.log(Math.tan(x) / this.tanphi1);
/*    */       } 
/*    */     } 
/* 53 */     out.x = x;
/* 54 */     out.y = y;
/* 55 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 59 */     double longitude, latitude = xyy + this.phi1;
/* 61 */     if (Math.abs(xyy) < 1.0E-8D) {
/* 62 */       longitude = xyx / this.cosphi1;
/* 63 */     } else if (Math.abs(longitude = 0.7853981633974483D + 0.5D * xyy) < 1.0E-8D || Math.abs(Math.abs(xyx) - 1.5707963267948966D) < 1.0E-8D) {
/* 65 */       longitude = 0.0D;
/*    */     } else {
/* 67 */       longitude = xyx * Math.log(Math.tan(longitude) / this.tanphi1) / xyy;
/*    */     } 
/* 69 */     out.x = longitude;
/* 70 */     out.y = latitude;
/* 71 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 75 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 79 */     return "Loximuthal";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LoximuthalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */