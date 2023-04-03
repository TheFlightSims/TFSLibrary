/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class FoucautSinusoidalProjection extends Projection {
/*    */   private double n;
/*    */   
/*    */   private double n1;
/*    */   
/*    */   private static final int MAX_ITER = 10;
/*    */   
/*    */   private static final double LOOP_TOL = 1.0E-7D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 34 */     double t = Math.cos(lpphi);
/* 35 */     out.x = lplam * t / (this.n + this.n1 * t);
/* 36 */     out.y = this.n * lpphi + this.n1 * Math.sin(lpphi);
/* 37 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 44 */     if (this.n != 0.0D) {
/* 45 */       out.y = xyy;
/* 46 */       int i = 10;
/*    */       double d;
/* 47 */       out.y -= d = (this.n * out.y + this.n1 * Math.sin(out.y) - xyy) / (this.n + this.n1 * Math.cos(out.y));
/* 49 */       for (; i > 0 && Math.abs(d) >= 1.0E-7D; i--);
/* 52 */       if (i == 0)
/* 53 */         out.y = (xyy < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D; 
/*    */     } else {
/* 55 */       out.y = MapMath.asin(xyy);
/*    */     } 
/* 56 */     double V = Math.cos(out.y);
/* 57 */     out.x = xyx * (this.n + this.n1 * V) / V;
/* 58 */     return out;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 62 */     super.initialize();
/* 64 */     if (this.n < 0.0D || this.n > 1.0D)
/* 65 */       throw new ProjectionException("-99"); 
/* 66 */     this.n1 = 1.0D - this.n;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 70 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 74 */     return "Foucaut Sinusoidal";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\FoucautSinusoidalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */