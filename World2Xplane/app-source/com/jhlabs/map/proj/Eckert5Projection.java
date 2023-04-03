/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Eckert5Projection extends Projection {
/*    */   private static final double XF = 0.4410127717245515D;
/*    */   
/*    */   private static final double RXF = 2.267508027238226D;
/*    */   
/*    */   private static final double YF = 0.882025543449103D;
/*    */   
/*    */   private static final double RYF = 1.133754013619113D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 33 */     out.x = 0.4410127717245515D * (1.0D + Math.cos(lpphi)) * lplam;
/* 34 */     out.y = 0.882025543449103D * lpphi;
/* 35 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 39 */     out.x = 2.267508027238226D * xyx / (1.0D + Math.cos(out.y = 1.133754013619113D * xyy));
/* 40 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return "Eckert V";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Eckert5Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */