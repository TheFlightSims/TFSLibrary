/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class AitoffProjection extends PseudoCylindricalProjection {
/*    */   protected static final int AITOFF = 0;
/*    */   
/*    */   protected static final int WINKEL = 1;
/*    */   
/*    */   private boolean winkel = false;
/*    */   
/* 32 */   private double cosphi1 = 0.0D;
/*    */   
/*    */   public AitoffProjection() {}
/*    */   
/*    */   public AitoffProjection(int type, double projectionLatitude) {
/* 38 */     this.projectionLatitude = projectionLatitude;
/* 39 */     this.winkel = (type == 1);
/*    */   }
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 43 */     double c = 0.5D * lplam;
/* 44 */     double d = Math.acos(Math.cos(lpphi) * Math.cos(c));
/* 47 */     out.x = 2.0D * d * Math.cos(lpphi) * Math.sin(c) * (out.y = 1.0D / Math.sin(d));
/* 48 */     out.y *= d * Math.sin(lpphi);
/* 50 */     out.x = out.y = 0.0D;
/* 51 */     if (this.winkel) {
/* 52 */       out.x = (out.x + lplam * this.cosphi1) * 0.5D;
/* 53 */       out.y = (out.y + lpphi) * 0.5D;
/*    */     } 
/* 55 */     return out;
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 59 */     super.initialize();
/* 60 */     if (this.winkel)
/* 66 */       this.cosphi1 = 0.6366197723675814D; 
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 75 */     return this.winkel ? "Winkel Tripel" : "Aitoff";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\AitoffProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */