/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Eckert2Projection extends Projection {
/*    */   private static final double FXC = 0.46065886596178063D;
/*    */   
/*    */   private static final double FYC = 1.4472025091165353D;
/*    */   
/*    */   private static final double C13 = 0.3333333333333333D;
/*    */   
/*    */   private static final double ONEEPS = 1.0000001D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 33 */     out.x = 0.46065886596178063D * lplam * (out.y = Math.sqrt(4.0D - 3.0D * Math.sin(Math.abs(lpphi))));
/* 34 */     out.y = 1.4472025091165353D * (2.0D - out.y);
/* 35 */     if (lpphi < 0.0D)
/* 35 */       out.y = -out.y; 
/* 36 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 40 */     out.x = xyx / 0.46065886596178063D * (out.y = 2.0D - Math.abs(xyy) / 1.4472025091165353D);
/* 41 */     out.y = (4.0D - out.y * out.y) * 0.3333333333333333D;
/* 42 */     if (Math.abs(out.y) >= 1.0D) {
/* 43 */       if (Math.abs(out.y) > 1.0000001D)
/* 43 */         throw new ProjectionException("I"); 
/* 45 */       out.y = (out.y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*    */     } else {
/* 47 */       out.y = Math.asin(out.y);
/*    */     } 
/* 48 */     if (xyy < 0.0D)
/* 49 */       out.y = -out.y; 
/* 50 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return "Eckert II";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Eckert2Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */