/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class OrthographicAzimuthalProjection extends AzimuthalProjection {
/*     */   public OrthographicAzimuthalProjection() {
/*  32 */     initialize();
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/*  37 */     double sinphi, cosphi = Math.cos(phi);
/*  38 */     double coslam = Math.cos(lam);
/*  42 */     switch (this.mode) {
/*     */       case 3:
/*  46 */         xy.y = Math.sin(phi);
/*     */         break;
/*     */       case 4:
/*  49 */         sinphi = Math.sin(phi);
/*  53 */         xy.y = this.cosphi0 * sinphi - this.sinphi0 * cosphi * coslam;
/*     */         break;
/*     */       case 1:
/*  56 */         coslam = -coslam;
/*     */       case 2:
/*  60 */         xy.y = cosphi * coslam;
/*     */         break;
/*     */     } 
/*  63 */     xy.x = cosphi * Math.sin(lam);
/*  64 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/*     */     double rh, sinc;
/*  70 */     if ((sinc = rh = MapMath.distance(x, y)) > 1.0D) {
/*  71 */       if (sinc - 1.0D > 1.0E-10D)
/*  71 */         throw new ProjectionException(); 
/*  72 */       sinc = 1.0D;
/*     */     } 
/*  74 */     double cosc = Math.sqrt(1.0D - sinc * sinc);
/*  75 */     if (Math.abs(rh) <= 1.0E-10D) {
/*  76 */       lp.y = this.projectionLatitude;
/*     */     } else {
/*  77 */       switch (this.mode) {
/*     */         case 1:
/*  79 */           y = -y;
/*  80 */           lp.y = Math.acos(sinc);
/*     */           break;
/*     */         case 2:
/*  83 */           lp.y = -Math.acos(sinc);
/*     */           break;
/*     */         case 3:
/*  86 */           lp.y = y * sinc / rh;
/*  87 */           x *= sinc;
/*  88 */           y = cosc * rh;
/*  89 */           if (Math.abs(lp.y) >= 1.0D) {
/*  90 */             lp.y = (lp.y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */             break;
/*     */           } 
/*  92 */           lp.y = Math.asin(lp.y);
/*     */           break;
/*     */         case 4:
/*  95 */           lp.y = cosc * this.sinphi0 + y * sinc * this.cosphi0 / rh;
/*  96 */           y = (cosc - this.sinphi0 * lp.y) * rh;
/*  97 */           x *= sinc * this.cosphi0;
/*  98 */           if (Math.abs(lp.y) >= 1.0D) {
/*  99 */             lp.y = (lp.y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */             break;
/*     */           } 
/* 101 */           lp.y = Math.asin(lp.y);
/*     */           break;
/*     */       } 
/*     */     } 
/* 104 */     lp.x = (y == 0.0D && (this.mode == 4 || this.mode == 3)) ? ((x == 0.0D) ? 0.0D : ((x < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D)) : Math.atan2(x, y);
/* 106 */     return lp;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 114 */     return "Orthographic Azimuthal";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\OrthographicAzimuthalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */