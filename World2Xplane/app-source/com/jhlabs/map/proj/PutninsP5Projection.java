/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class PutninsP5Projection extends Projection {
/* 34 */   protected double A = 2.0D;
/*    */   
/* 35 */   protected double B = 1.0D;
/*    */   
/*    */   private static final double C = 1.01346D;
/*    */   
/*    */   private static final double D = 1.2158542D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/* 39 */     xy.x = 1.01346D * lplam * (this.A - this.B * Math.sqrt(1.0D + 1.2158542D * lpphi * lpphi));
/* 40 */     xy.y = 1.01346D * lpphi;
/* 41 */     return xy;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double lp) {
/* 45 */     lp.y = xyy / 1.01346D;
/* 46 */     lp.x = xyx / 1.01346D * (this.A - this.B * Math.sqrt(1.0D + 1.2158542D * lp.y * lp.y));
/* 47 */     return lp;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 51 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 55 */     return "Putnins P5";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\PutninsP5Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */