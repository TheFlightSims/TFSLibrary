/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class LarriveeProjection extends Projection {
/*    */   private static final double SIXTH = 0.16666666666666666D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 30 */     out.x = 0.5D * lplam * (1.0D + Math.sqrt(Math.cos(lpphi)));
/* 31 */     out.y = lpphi / Math.cos(0.5D * lpphi) * Math.cos(0.16666666666666666D * lplam);
/* 32 */     return out;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     return "Larrivee";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LarriveeProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */