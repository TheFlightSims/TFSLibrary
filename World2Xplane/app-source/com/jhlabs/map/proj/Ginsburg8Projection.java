/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Ginsburg8Projection extends Projection {
/*    */   private static final double Cl = 9.52426E-4D;
/*    */   
/*    */   private static final double Cp = 0.162388D;
/*    */   
/*    */   private static final double C12 = 0.08333333333333333D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 32 */     double t = lpphi * lpphi;
/* 34 */     out.y = lpphi * (1.0D + t * 0.08333333333333333D);
/* 35 */     out.x = lplam * (1.0D - 0.162388D * t);
/* 36 */     t = lplam * lplam;
/* 37 */     out.x *= 0.87D - 9.52426E-4D * t * t;
/* 38 */     return out;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 42 */     return "Ginsburg VIII (TsNIIGAiK)";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Ginsburg8Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */