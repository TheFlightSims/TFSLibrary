/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class HatanoProjection extends Projection {
/*    */   private static final int NITER = 20;
/*    */   
/*    */   private static final double EPS = 1.0E-7D;
/*    */   
/*    */   private static final double ONETOL = 1.000001D;
/*    */   
/*    */   private static final double CN = 2.67595D;
/*    */   
/*    */   private static final double CS = 2.43763D;
/*    */   
/*    */   private static final double RCN = 0.3736990601468637D;
/*    */   
/*    */   private static final double RCS = 0.4102345310814193D;
/*    */   
/*    */   private static final double FYCN = 1.75859D;
/*    */   
/*    */   private static final double FYCS = 1.93052D;
/*    */   
/*    */   private static final double RYCN = 0.5686373742600607D;
/*    */   
/*    */   private static final double RYCS = 0.5179951515653813D;
/*    */   
/*    */   private static final double FXC = 0.85D;
/*    */   
/*    */   private static final double RXC = 1.1764705882352942D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 45 */     double c = Math.sin(lpphi) * ((lpphi < 0.0D) ? 2.43763D : 2.67595D);
/* 46 */     int i = 20;
/*    */     double th1;
/* 47 */     lpphi -= th1 = (lpphi + Math.sin(lpphi) - c) / (1.0D + Math.cos(lpphi));
/* 48 */     for (; i > 0 && Math.abs(th1) >= 1.0E-7D; i--);
/* 50 */     out.x = 0.85D * lplam * Math.cos(lpphi *= 0.5D);
/* 51 */     out.y = Math.sin(lpphi) * ((lpphi < 0.0D) ? 1.93052D : 1.75859D);
/* 52 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 58 */     double th = xyy * ((xyy < 0.0D) ? 0.5179951515653813D : 0.5686373742600607D);
/* 59 */     if (Math.abs(th) > 1.0D) {
/* 60 */       if (Math.abs(th) > 1.000001D)
/* 60 */         throw new ProjectionException("I"); 
/* 61 */       th = (th > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*    */     } else {
/* 63 */       th = Math.asin(th);
/*    */     } 
/* 64 */     out.x = 1.1764705882352942D * xyx / Math.cos(th);
/* 65 */     th += th;
/* 66 */     out.y = (th + Math.sin(th)) * ((xyy < 0.0D) ? 0.4102345310814193D : 0.3736990601468637D);
/* 67 */     if (Math.abs(out.y) > 1.0D) {
/* 68 */       if (Math.abs(out.y) > 1.000001D)
/* 68 */         throw new ProjectionException("I"); 
/* 69 */       out.y = (out.y > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*    */     } else {
/* 71 */       out.y = Math.asin(out.y);
/*    */     } 
/* 72 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 76 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     return "Hatano Asymmetrical Equal Area";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\HatanoProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */