/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class HammerProjection extends PseudoCylindricalProjection {
/* 28 */   private double w = 0.5D;
/*    */   
/* 29 */   private double m = 1.0D;
/*    */   
/*    */   private double rm;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/* 38 */     double cosphi, d = Math.sqrt(2.0D / (1.0D + (cosphi = Math.cos(lpphi)) * Math.cos(lplam *= this.w)));
/* 39 */     xy.x = this.m * d * cosphi * Math.sin(lplam);
/* 40 */     xy.y = this.rm * d * Math.sin(lpphi);
/* 41 */     return xy;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 45 */     super.initialize();
/* 46 */     if ((this.w = Math.abs(this.w)) <= 0.0D)
/* 47 */       throw new ProjectionException("-27"); 
/* 49 */     this.w = 0.5D;
/* 50 */     if ((this.m = Math.abs(this.m)) <= 0.0D)
/* 51 */       throw new ProjectionException("-27"); 
/* 53 */     this.m = 1.0D;
/* 54 */     this.rm = 1.0D / this.m;
/* 55 */     this.m /= this.w;
/* 56 */     this.es = 0.0D;
/*    */   }
/*    */   
/*    */   public boolean isEqualArea() {
/* 63 */     return true;
/*    */   }
/*    */   
/*    */   public void setW(double w) {
/* 68 */     this.w = w;
/*    */   }
/*    */   
/*    */   public double getW() {
/* 72 */     return this.w;
/*    */   }
/*    */   
/*    */   public void setM(double m) {
/* 76 */     this.m = m;
/*    */   }
/*    */   
/*    */   public double getM() {
/* 80 */     return this.m;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 84 */     return "Hammer & Eckert-Greifendorff";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\HammerProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */