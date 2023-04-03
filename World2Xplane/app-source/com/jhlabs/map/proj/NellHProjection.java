/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class NellHProjection extends Projection {
/*    */   private static final int NITER = 9;
/*    */   
/*    */   private static final double EPS = 1.0E-7D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 31 */     out.x = 0.5D * lplam * (1.0D + Math.cos(lpphi));
/* 32 */     out.y = 2.0D * (lpphi - Math.tan(0.5D * lpphi));
/* 33 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 40 */     double p = 0.5D * xyy;
/* 41 */     int i = 9;
/* 42 */     double c = Math.cos(0.5D * xyy);
/*    */     double V;
/* 43 */     out.y -= V = (xyy - Math.tan(xyy / 2.0D) - p) / (1.0D - 0.5D / c * c);
/* 44 */     for (; i > 0 && Math.abs(V) >= 1.0E-7D; i--);
/* 47 */     if (i == 0) {
/* 48 */       out.y = (p < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/* 49 */       out.x = 2.0D * xyx;
/*    */     } else {
/* 51 */       out.x = 2.0D * xyx / (1.0D + Math.cos(xyy));
/*    */     } 
/* 52 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 60 */     return "Nell-Hammer";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\NellHProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */