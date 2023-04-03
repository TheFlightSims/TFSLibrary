/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class GallProjection extends Projection {
/*    */   private static final double YF = 1.7071067811865475D;
/*    */   
/*    */   private static final double XF = 0.7071067811865476D;
/*    */   
/*    */   private static final double RYF = 0.585786437626905D;
/*    */   
/*    */   private static final double RXF = 1.4142135623730951D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 33 */     out.x = 0.7071067811865476D * lplam;
/* 34 */     out.y = 1.7071067811865475D * Math.tan(0.5D * lpphi);
/* 35 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 39 */     out.x = 1.4142135623730951D * xyx;
/* 40 */     out.y = 2.0D * Math.atan(xyy * 0.585786437626905D);
/* 41 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 49 */     return "Gall (Gall Stereographic)";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\GallProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */