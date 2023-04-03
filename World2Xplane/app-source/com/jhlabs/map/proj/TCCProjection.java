/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class TCCProjection extends CylindricalProjection {
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 35 */     double b = Math.cos(lpphi) * Math.sin(lplam);
/*    */     double bt;
/* 36 */     if ((bt = 1.0D - b * b) < 1.0E-10D)
/* 37 */       throw new ProjectionException("F"); 
/* 38 */     out.x = b / Math.sqrt(bt);
/* 39 */     out.y = Math.atan2(Math.tan(lpphi), Math.cos(lplam));
/* 40 */     return out;
/*    */   }
/*    */   
/*    */   public boolean isRectilinear() {
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return "Transverse Central Cylindrical";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\TCCProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */