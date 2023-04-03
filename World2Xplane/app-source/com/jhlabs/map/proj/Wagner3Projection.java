/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Wagner3Projection extends PseudoCylindricalProjection {
/*    */   private static final double TWOTHIRD = 0.6666666666666666D;
/*    */   
/*    */   private double C_x;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/* 32 */     xy.x = this.C_x * lplam * Math.cos(0.6666666666666666D * lpphi);
/* 33 */     xy.y = lpphi;
/* 34 */     return xy;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 38 */     lp.y = y;
/* 39 */     lp.x = x / this.C_x * Math.cos(0.6666666666666666D * lp.y);
/* 40 */     return lp;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 44 */     super.initialize();
/* 45 */     this.C_x = Math.cos(this.trueScaleLatitude) / Math.cos(2.0D * this.trueScaleLatitude / 3.0D);
/* 46 */     this.es = 0.0D;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     return "Wagner III";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Wagner3Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */