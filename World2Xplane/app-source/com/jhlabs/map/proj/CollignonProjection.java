/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class CollignonProjection extends Projection {
/*    */   private static final double FXC = 1.1283791670955126D;
/*    */   
/*    */   private static final double FYC = 1.772453850905516D;
/*    */   
/*    */   private static final double ONEEPS = 1.0000001D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 32 */     if ((out.y = 1.0D - Math.sin(lpphi)) <= 0.0D) {
/* 33 */       out.y = 0.0D;
/*    */     } else {
/* 35 */       out.y = Math.sqrt(out.y);
/*    */     } 
/* 36 */     out.x = 1.1283791670955126D * lplam * out.y;
/* 37 */     out.y = 1.772453850905516D * (1.0D - out.y);
/* 38 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 42 */     double lpphi = xyy / 1.772453850905516D - 1.0D;
/* 43 */     if (Math.abs(out.y = 1.0D - lpphi * lpphi) < 1.0D) {
/* 44 */       out.y = Math.asin(lpphi);
/*    */     } else {
/* 45 */       if (Math.abs(lpphi) > 1.0000001D)
/* 45 */         throw new ProjectionException("I"); 
/* 46 */       out.y = (lpphi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*    */     } 
/* 47 */     if ((out.x = 1.0D - Math.sin(lpphi)) <= 0.0D) {
/* 48 */       out.x = 0.0D;
/*    */     } else {
/* 50 */       out.x = xyx / 1.1283791670955126D * Math.sqrt(out.x);
/*    */     } 
/* 51 */     out.y = lpphi;
/* 52 */     return out;
/*    */   }
/*    */   
/*    */   public boolean isEqualArea() {
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 63 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 67 */     return "Collignon";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\CollignonProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */