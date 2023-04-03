/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Wagner2Projection extends Projection {
/*    */   private static final double C_x = 0.92483D;
/*    */   
/*    */   private static final double C_y = 1.38725D;
/*    */   
/*    */   private static final double C_p1 = 0.88022D;
/*    */   
/*    */   private static final double C_p2 = 0.8855D;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 33 */     out.y = MapMath.asin(0.88022D * Math.sin(0.8855D * lpphi));
/* 34 */     out.x = 0.92483D * lplam * Math.cos(lpphi);
/* 35 */     out.y = 1.38725D * lpphi;
/* 36 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 40 */     out.y = xyy / 1.38725D;
/* 41 */     out.x = xyx / 0.92483D * Math.cos(out.y);
/* 42 */     out.y = MapMath.asin(Math.sin(out.y) / 0.88022D) / 0.8855D;
/* 43 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 47 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return "Wagner II";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Wagner2Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */