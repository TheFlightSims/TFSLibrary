/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class PutninsP4Projection extends Projection {
/* 31 */   protected double C_x = 0.874038744D;
/*    */   
/* 32 */   protected double C_y = 3.883251825D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/* 36 */     lpphi = MapMath.asin(0.883883476D * Math.sin(lpphi));
/* 37 */     xy.x = this.C_x * lplam * Math.cos(lpphi);
/* 38 */     xy.x /= Math.cos(lpphi *= 0.333333333333333D);
/* 39 */     xy.y = this.C_y * Math.sin(lpphi);
/* 40 */     return xy;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double lp) {
/* 44 */     lp.y = MapMath.asin(xyy / this.C_y);
/* 45 */     lp.x = xyx * Math.cos(lp.y) / this.C_x;
/* 46 */     lp.y *= 3.0D;
/* 47 */     lp.x /= Math.cos(lp.y);
/* 48 */     lp.y = MapMath.asin(1.13137085D * Math.sin(lp.y));
/* 49 */     return lp;
/*    */   }
/*    */   
/*    */   public boolean isEqualArea() {
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 64 */     return "Putnins P4";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\PutninsP4Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */