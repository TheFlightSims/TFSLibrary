/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class LagrangeProjection extends Projection {
/*    */   private double hrw;
/*    */   
/* 29 */   private double rw = 1.4D;
/*    */   
/*    */   private double a1;
/*    */   
/*    */   private double phi1;
/*    */   
/*    */   private static final double TOL = 1.0E-10D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/* 38 */     if (Math.abs(Math.abs(lpphi) - 1.5707963267948966D) < 1.0E-10D) {
/* 39 */       xy.x = 0.0D;
/* 40 */       xy.y = (lpphi < 0.0D) ? -2.0D : 2.0D;
/*    */     } else {
/* 42 */       lpphi = Math.sin(lpphi);
/* 43 */       double v = this.a1 * Math.pow((1.0D + lpphi) / (1.0D - lpphi), this.hrw);
/*    */       double c;
/* 44 */       if ((c = 0.5D * (v + 1.0D / v) + Math.cos(lplam *= this.rw)) < 1.0E-10D)
/* 45 */         throw new ProjectionException(); 
/* 46 */       xy.x = 2.0D * Math.sin(lplam) / c;
/* 47 */       xy.y = (v - 1.0D / v) / c;
/*    */     } 
/* 49 */     return xy;
/*    */   }
/*    */   
/*    */   public void setW(double w) {
/* 53 */     this.rw = w;
/*    */   }
/*    */   
/*    */   public double getW() {
/* 57 */     return this.rw;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 61 */     super.initialize();
/* 62 */     if (this.rw <= 0.0D)
/* 63 */       throw new ProjectionException("-27"); 
/* 64 */     this.hrw = 0.5D * (this.rw = 1.0D / this.rw);
/* 65 */     this.phi1 = this.standardParallel1;
/* 66 */     if (Math.abs(Math.abs(this.phi1 = Math.sin(this.phi1)) - 1.0D) < 1.0E-10D)
/* 67 */       throw new ProjectionException("-22"); 
/* 68 */     this.a1 = Math.pow((1.0D - this.phi1) / (1.0D + this.phi1), this.hrw);
/*    */   }
/*    */   
/*    */   public boolean isConformal() {
/* 75 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 79 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 83 */     return "Lagrange";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LagrangeProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */