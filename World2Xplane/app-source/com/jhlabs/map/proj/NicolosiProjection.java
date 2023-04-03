/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class NicolosiProjection extends Projection {
/*    */   private static final double EPS = 1.0E-10D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 30 */     if (Math.abs(lplam) < 1.0E-10D) {
/* 31 */       out.x = 0.0D;
/* 32 */       out.y = lpphi;
/* 33 */     } else if (Math.abs(lpphi) < 1.0E-10D) {
/* 34 */       out.x = lplam;
/* 35 */       out.y = 0.0D;
/* 36 */     } else if (Math.abs(Math.abs(lplam) - 1.5707963267948966D) < 1.0E-10D) {
/* 37 */       out.x = lplam * Math.cos(lpphi);
/* 38 */       out.y = 1.5707963267948966D * Math.sin(lpphi);
/* 39 */     } else if (Math.abs(Math.abs(lpphi) - 1.5707963267948966D) < 1.0E-10D) {
/* 40 */       out.x = 0.0D;
/* 41 */       out.y = lpphi;
/*    */     } else {
/* 45 */       double tb = 1.5707963267948966D / lplam - lplam / 1.5707963267948966D;
/* 46 */       double c = lpphi / 1.5707963267948966D;
/* 47 */       double sp, d = (1.0D - c * c) / ((sp = Math.sin(lpphi)) - c);
/* 48 */       double r2 = tb / d;
/* 49 */       r2 *= r2;
/* 50 */       double m = (tb * sp / d - 0.5D * tb) / (1.0D + r2);
/* 51 */       double n = (sp / r2 + 0.5D * d) / (1.0D + 1.0D / r2);
/* 52 */       double x = Math.cos(lpphi);
/* 53 */       x = Math.sqrt(m * m + x * x / (1.0D + r2));
/* 54 */       out.x = 1.5707963267948966D * (m + ((lplam < 0.0D) ? -x : x));
/* 55 */       double y = Math.sqrt(n * n - (sp * sp / r2 + d * sp - 1.0D) / (1.0D + 1.0D / r2));
/* 57 */       out.y = 1.5707963267948966D * (n + ((lpphi < 0.0D) ? y : -y));
/*    */     } 
/* 59 */     return out;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 63 */     return "Nicolosi Globular";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\NicolosiProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */