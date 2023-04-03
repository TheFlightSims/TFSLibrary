/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class MillerProjection extends CylindricalProjection {
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 32 */     out.x = lplam;
/* 33 */     out.y = Math.log(Math.tan(0.7853981633974483D + lpphi * 0.4D)) * 1.25D;
/* 34 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 38 */     out.x = xyx;
/* 39 */     out.y = 2.5D * (Math.atan(Math.exp(0.8D * xyy)) - 0.7853981633974483D);
/* 40 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return "Miller Cylindrical";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\MillerProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */