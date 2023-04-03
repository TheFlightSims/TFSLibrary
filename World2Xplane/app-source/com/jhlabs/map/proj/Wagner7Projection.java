/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Wagner7Projection extends Projection {
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 30 */     double theta = Math.asin(out.y = 0.9063077870366499D * Math.sin(lpphi));
/*    */     double ct;
/* 31 */     out.x = 2.66723D * (ct = Math.cos(theta)) * Math.sin(lplam /= 3.0D);
/*    */     double D;
/* 32 */     out.y *= 1.24104D * (D = 1.0D / Math.sqrt(0.5D * (1.0D + ct * Math.cos(lplam))));
/* 33 */     out.x *= D;
/* 34 */     return out;
/*    */   }
/*    */   
/*    */   public boolean isEqualArea() {
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 45 */     return "Wagner VII";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Wagner7Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */