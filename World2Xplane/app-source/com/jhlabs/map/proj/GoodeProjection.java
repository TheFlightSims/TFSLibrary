/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class GoodeProjection extends Projection {
/*    */   private static final double Y_COR = 0.0528D;
/*    */   
/*    */   private static final double PHI_LIM = 0.7109307819790236D;
/*    */   
/* 30 */   private SinusoidalProjection sinu = new SinusoidalProjection();
/*    */   
/* 31 */   private MolleweideProjection moll = new MolleweideProjection();
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 34 */     if (Math.abs(lpphi) <= 0.7109307819790236D) {
/* 35 */       out = this.sinu.project(lplam, lpphi, out);
/*    */     } else {
/* 37 */       out = this.moll.project(lplam, lpphi, out);
/* 38 */       out.y -= (lpphi >= 0.0D) ? 0.0528D : -0.0528D;
/*    */     } 
/* 40 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 44 */     if (Math.abs(xyy) <= 0.7109307819790236D) {
/* 45 */       out = this.sinu.projectInverse(xyx, xyy, out);
/*    */     } else {
/* 47 */       xyy += (xyy >= 0.0D) ? 0.0528D : -0.0528D;
/* 48 */       out = this.moll.projectInverse(xyx, xyy, out);
/*    */     } 
/* 50 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return "Goode Homolosine";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\GoodeProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */