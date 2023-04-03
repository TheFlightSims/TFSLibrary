/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Eckert1Projection extends Projection {
/*    */   private static final double FC = 0.9213177319235613D;
/*    */   
/*    */   private static final double RP = 0.3183098861837907D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 31 */     out.x = 0.9213177319235613D * lplam * (1.0D - 0.3183098861837907D * Math.abs(lpphi));
/* 32 */     out.y = 0.9213177319235613D * lpphi;
/* 33 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 37 */     out.y = xyy / 0.9213177319235613D;
/* 38 */     out.x = xyx / 0.9213177319235613D * (1.0D - 0.3183098861837907D * Math.abs(out.y));
/* 39 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 43 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     return "Eckert I";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Eckert1Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */