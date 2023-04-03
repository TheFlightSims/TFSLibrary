/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class CylindricalEqualAreaProjection extends Projection {
/*    */   private double qp;
/*    */   
/*    */   private double[] apa;
/*    */   
/*    */   private double trueScaleLatitude;
/*    */   
/*    */   public CylindricalEqualAreaProjection() {
/* 33 */     this(0.0D, 0.0D, 0.0D);
/*    */   }
/*    */   
/*    */   public CylindricalEqualAreaProjection(double projectionLatitude, double projectionLongitude, double trueScaleLatitude) {
/* 37 */     this.projectionLatitude = projectionLatitude;
/* 38 */     this.projectionLongitude = projectionLongitude;
/* 39 */     this.trueScaleLatitude = trueScaleLatitude;
/* 40 */     initialize();
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 44 */     super.initialize();
/* 45 */     double t = this.trueScaleLatitude;
/* 47 */     this.scaleFactor = Math.cos(t);
/* 48 */     if (this.es != 0.0D) {
/* 49 */       t = Math.sin(t);
/* 50 */       this.scaleFactor /= Math.sqrt(1.0D - this.es * t * t);
/* 51 */       this.apa = MapMath.authset(this.es);
/* 52 */       this.qp = MapMath.qsfn(1.0D, this.e, this.one_es);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/* 57 */     if (this.spherical) {
/* 58 */       xy.x = this.scaleFactor * lam;
/* 59 */       xy.y = Math.sin(phi) / this.scaleFactor;
/*    */     } else {
/* 61 */       xy.x = this.scaleFactor * lam;
/* 62 */       xy.y = 0.5D * MapMath.qsfn(Math.sin(phi), this.e, this.one_es) / this.scaleFactor;
/*    */     } 
/* 64 */     return xy;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 68 */     if (this.spherical) {
/*    */       double t;
/* 71 */       if ((t = Math.abs(y *= this.scaleFactor)) - 1.0E-10D <= 1.0D) {
/* 72 */         if (t >= 1.0D) {
/* 73 */           lp.y = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*    */         } else {
/* 75 */           lp.y = Math.asin(y);
/*    */         } 
/* 76 */         lp.x = x / this.scaleFactor;
/*    */       } else {
/* 77 */         throw new ProjectionException();
/*    */       } 
/*    */     } else {
/* 79 */       lp.y = MapMath.authlat(Math.asin(2.0D * y * this.scaleFactor / this.qp), this.apa);
/* 80 */       lp.x = x / this.scaleFactor;
/*    */     } 
/* 82 */     return lp;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 86 */     return true;
/*    */   }
/*    */   
/*    */   public boolean isRectilinear() {
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\CylindricalEqualAreaProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */