/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class URMFPSProjection extends Projection {
/*    */   private static final double C_x = 0.8773826753D;
/*    */   
/*    */   private static final double Cy = 1.139753528477D;
/*    */   
/* 30 */   private double n = 0.8660254037844386D;
/*    */   
/*    */   private double C_y;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 37 */     out.y = MapMath.asin(this.n * Math.sin(lpphi));
/* 38 */     out.x = 0.8773826753D * lplam * Math.cos(lpphi);
/* 39 */     out.y = this.C_y * lpphi;
/* 40 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 44 */     xyy /= this.C_y;
/* 45 */     out.y = MapMath.asin(Math.sin(xyy) / this.n);
/* 46 */     out.x = xyx / 0.8773826753D * Math.cos(xyy);
/* 47 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 51 */     return true;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 55 */     super.initialize();
/* 56 */     if (this.n <= 0.0D || this.n > 1.0D)
/* 57 */       throw new ProjectionException("-40"); 
/* 58 */     this.C_y = 1.139753528477D / this.n;
/*    */   }
/*    */   
/*    */   public void setN(double n) {
/* 63 */     this.n = n;
/*    */   }
/*    */   
/*    */   public double getN() {
/* 67 */     return this.n;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 71 */     return "Urmaev Flat-Polar Sinusoidal";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\URMFPSProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */