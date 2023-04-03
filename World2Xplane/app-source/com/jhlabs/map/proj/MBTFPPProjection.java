/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class MBTFPPProjection extends Projection {
/*    */   private static final double CS = 0.9525793444156804D;
/*    */   
/*    */   private static final double FXC = 0.9258200997725514D;
/*    */   
/*    */   private static final double FYC = 3.401680257083045D;
/*    */   
/*    */   private static final double C23 = 0.6666666666666666D;
/*    */   
/*    */   private static final double C13 = 0.3333333333333333D;
/*    */   
/*    */   private static final double ONEEPS = 1.0000001D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 35 */     out.y = Math.asin(0.9525793444156804D * Math.sin(lpphi));
/* 36 */     out.x = 0.9258200997725514D * lplam * (2.0D * Math.cos(0.6666666666666666D * lpphi) - 1.0D);
/* 37 */     out.y = 3.401680257083045D * Math.sin(0.3333333333333333D * lpphi);
/* 38 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 42 */     out.y = xyy / 3.401680257083045D;
/* 43 */     if (Math.abs(out.y) >= 1.0D) {
/* 44 */       if (Math.abs(out.y) > 1.0000001D)
/* 44 */         throw new ProjectionException("I"); 
/* 45 */       out.y = (out.y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*    */     } else {
/* 47 */       out.y = Math.asin(out.y);
/*    */     } 
/* 48 */     out.x = xyx / 0.9258200997725514D * (2.0D * Math.cos(0.6666666666666666D * (out.y *= 3.0D)) - 1.0D);
/* 49 */     if (Math.abs(out.y = Math.sin(out.y) / 0.9525793444156804D) >= 1.0D) {
/* 50 */       if (Math.abs(out.y) > 1.0000001D)
/* 50 */         throw new ProjectionException("I"); 
/* 51 */       out.y = (out.y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*    */     } else {
/* 53 */       out.y = Math.asin(out.y);
/*    */     } 
/* 54 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 58 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 62 */     return "McBride-Thomas Flat-Polar Parabolic";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\MBTFPPProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */