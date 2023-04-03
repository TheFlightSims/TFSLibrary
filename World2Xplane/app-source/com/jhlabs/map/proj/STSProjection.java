/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ class STSProjection extends ConicProjection {
/*    */   private double C_x;
/*    */   
/*    */   private double C_y;
/*    */   
/*    */   private double C_p;
/*    */   
/*    */   private boolean tan_mode;
/*    */   
/*    */   protected STSProjection(double p, double q, boolean mode) {
/* 32 */     this.es = 0.0D;
/* 33 */     this.C_x = q / p;
/* 34 */     this.C_y = p;
/* 35 */     this.C_p = 1.0D / q;
/* 36 */     this.tan_mode = mode;
/* 37 */     initialize();
/*    */   }
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/* 43 */     xy.x = this.C_x * lplam * Math.cos(lpphi);
/* 44 */     xy.y = this.C_y;
/* 45 */     lpphi *= this.C_p;
/* 46 */     double c = Math.cos(lpphi);
/* 47 */     if (this.tan_mode) {
/* 48 */       xy.x *= c * c;
/* 49 */       xy.y *= Math.tan(lpphi);
/*    */     } else {
/* 51 */       xy.x /= c;
/* 52 */       xy.y *= Math.sin(lpphi);
/*    */     } 
/* 54 */     return xy;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double lp) {
/* 60 */     xyy /= this.C_y;
/* 61 */     double c = Math.cos(lp.y = this.tan_mode ? Math.atan(xyy) : MapMath.asin(xyy));
/* 62 */     lp.y /= this.C_p;
/* 63 */     lp.x = xyx / this.C_x * Math.cos(lp.y /= this.C_p);
/* 64 */     if (this.tan_mode) {
/* 65 */       lp.x /= c * c;
/*    */     } else {
/* 67 */       lp.x *= c;
/*    */     } 
/* 68 */     return lp;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 72 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\STSProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */