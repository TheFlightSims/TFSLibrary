/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class TCEAProjection extends Projection {
/*    */   private double rk0;
/*    */   
/*    */   public TCEAProjection() {
/* 30 */     initialize();
/*    */   }
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 34 */     out.x = this.rk0 * Math.cos(lpphi) * Math.sin(lplam);
/* 35 */     out.y = this.scaleFactor * (Math.atan2(Math.tan(lpphi), Math.cos(lplam)) - this.projectionLatitude);
/* 36 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 42 */     out.y = xyy * this.rk0 + this.projectionLatitude;
/* 43 */     out.x *= this.scaleFactor;
/* 44 */     double t = Math.sqrt(1.0D - xyx * xyx);
/* 45 */     out.y = Math.asin(t * Math.sin(xyy));
/* 46 */     out.x = Math.atan2(xyx, t * Math.cos(xyy));
/* 47 */     return out;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 51 */     super.initialize();
/* 52 */     this.rk0 = 1.0D / this.scaleFactor;
/*    */   }
/*    */   
/*    */   public boolean isRectilinear() {
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 64 */     return "Transverse Cylindrical Equal Area";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\TCEAProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */