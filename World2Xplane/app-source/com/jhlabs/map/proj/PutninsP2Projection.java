/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class PutninsP2Projection extends Projection {
/*    */   private static final double C_x = 1.8949D;
/*    */   
/*    */   private static final double C_y = 1.71848D;
/*    */   
/*    */   private static final double C_p = 0.6141848493043784D;
/*    */   
/*    */   private static final double EPS = 1.0E-10D;
/*    */   
/*    */   private static final int NITER = 10;
/*    */   
/*    */   private static final double PI_DIV_3 = 1.0471975511965976D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 38 */     double p = 0.6141848493043784D * Math.sin(lpphi);
/* 39 */     double s = lpphi * lpphi;
/* 40 */     out.y *= 0.615709D + s * (0.00909953D + s * 0.0046292D);
/* 41 */     int i = 10;
/* 42 */     double c = Math.cos(lpphi);
/* 43 */     s = Math.sin(lpphi);
/*    */     double V;
/* 44 */     out.y -= V = (lpphi + s * (c - 1.0D) - p) / (1.0D + c * (c - 1.0D) - s * s);
/* 46 */     for (; i > 0 && Math.abs(V) >= 1.0E-10D; i--);
/* 49 */     if (i == 0)
/* 50 */       out.y = (lpphi < 0.0D) ? -1.0471975511965976D : 1.0471975511965976D; 
/* 51 */     out.x = 1.8949D * lplam * (Math.cos(lpphi) - 0.5D);
/* 52 */     out.y = 1.71848D * Math.sin(lpphi);
/* 53 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 59 */     out.y = MapMath.asin(xyy / 1.71848D);
/*    */     double c;
/* 60 */     out.x = xyx / 1.8949D * ((c = Math.cos(out.y)) - 0.5D);
/* 61 */     out.y = MapMath.asin((out.y + Math.sin(out.y) * (c - 1.0D)) / 0.6141848493043784D);
/* 62 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 66 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 70 */     return "Putnins P2";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\PutninsP2Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */