/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class RectangularPolyconicProjection extends Projection {
/*    */   private double phi0;
/*    */   
/*    */   private double phi1;
/*    */   
/*    */   private double fxa;
/*    */   
/*    */   private double fxb;
/*    */   
/*    */   private boolean mode;
/*    */   
/*    */   private static final double EPS = 1.0E-9D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*    */     double fa;
/* 38 */     if (this.mode) {
/* 39 */       fa = Math.tan(lplam * this.fxb) * this.fxa;
/*    */     } else {
/* 41 */       fa = 0.5D * lplam;
/*    */     } 
/* 42 */     if (Math.abs(lpphi) < 1.0E-9D) {
/* 43 */       out.x = fa + fa;
/* 44 */       out.y = -this.phi0;
/*    */     } else {
/* 46 */       out.y = 1.0D / Math.tan(lpphi);
/* 47 */       out.x = Math.sin(fa = 2.0D * Math.atan(fa * Math.sin(lpphi))) * out.y;
/* 48 */       out.y = lpphi - this.phi0 + (1.0D - Math.cos(fa)) * out.y;
/*    */     } 
/* 50 */     return out;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 54 */     super.initialize();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 64 */     return "Rectangular Polyconic";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\RectangularPolyconicProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */