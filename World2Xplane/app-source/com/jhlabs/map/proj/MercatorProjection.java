/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class MercatorProjection extends CylindricalProjection {
/*    */   public Point2D.Double project(double lam, double phi, Point2D.Double out) {
/* 34 */     if (this.spherical) {
/* 35 */       out.x = this.scaleFactor * lam;
/* 36 */       out.y = this.scaleFactor * Math.log(Math.tan(0.7853981633974483D + 0.5D * phi));
/*    */     } else {
/* 38 */       out.x = this.scaleFactor * lam;
/* 39 */       out.y = -this.scaleFactor * Math.log(MapMath.tsfn(phi, Math.sin(phi), this.e));
/*    */     } 
/* 41 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double x, double y, Point2D.Double out) {
/* 45 */     if (this.spherical) {
/* 46 */       out.y = 1.5707963267948966D - 2.0D * Math.atan(Math.exp(-y / this.scaleFactor));
/* 47 */       out.x = x / this.scaleFactor;
/*    */     } else {
/* 49 */       out.y = MapMath.phi2(Math.exp(-y / this.scaleFactor), this.e);
/* 50 */       out.x = x / this.scaleFactor;
/*    */     } 
/* 52 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public boolean isRectilinear() {
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public int getEPSGCode() {
/* 67 */     return 9804;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 71 */     return "Mercator";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\MercatorProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */