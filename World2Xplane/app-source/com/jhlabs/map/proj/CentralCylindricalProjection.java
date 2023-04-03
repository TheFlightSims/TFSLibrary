/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class CentralCylindricalProjection extends CylindricalProjection {
/*    */   private double ap;
/*    */   
/*    */   private static final double EPS10 = 1.0E-10D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 37 */     if (Math.abs(Math.abs(lpphi) - 1.5707963267948966D) <= 1.0E-10D)
/* 37 */       throw new ProjectionException("F"); 
/* 38 */     out.x = lplam;
/* 39 */     out.y = Math.tan(lpphi);
/* 40 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 44 */     out.y = Math.atan(xyy);
/* 45 */     out.x = xyx;
/* 46 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     return "Central Cylindrical";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\CentralCylindricalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */