/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class GnomonicAzimuthalProjection extends AzimuthalProjection {
/*     */   public GnomonicAzimuthalProjection() {
/*  28 */     this(Math.toRadians(90.0D), Math.toRadians(0.0D));
/*     */   }
/*     */   
/*     */   public GnomonicAzimuthalProjection(double projectionLatitude, double projectionLongitude) {
/*  32 */     super(projectionLatitude, projectionLongitude);
/*  33 */     this.minLatitude = Math.toRadians(0.0D);
/*  34 */     this.maxLatitude = Math.toRadians(90.0D);
/*  35 */     initialize();
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  39 */     super.initialize();
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/*  43 */     double sinphi = Math.sin(phi);
/*  44 */     double cosphi = Math.cos(phi);
/*  45 */     double coslam = Math.cos(lam);
/*  47 */     switch (this.mode) {
/*     */       case 3:
/*  49 */         xy.y = cosphi * coslam;
/*     */         break;
/*     */       case 4:
/*  52 */         xy.y = this.sinphi0 * sinphi + this.cosphi0 * cosphi * coslam;
/*     */         break;
/*     */       case 2:
/*  55 */         xy.y = -sinphi;
/*     */         break;
/*     */       case 1:
/*  58 */         xy.y = sinphi;
/*     */         break;
/*     */     } 
/*  61 */     if (Math.abs(xy.y) <= 1.0E-10D)
/*  62 */       throw new ProjectionException(); 
/*  63 */     xy.x = (xy.y = 1.0D / xy.y) * cosphi * Math.sin(lam);
/*  64 */     switch (this.mode) {
/*     */       case 3:
/*  66 */         xy.y *= sinphi;
/*     */         break;
/*     */       case 4:
/*  69 */         xy.y *= this.cosphi0 * sinphi - this.sinphi0 * cosphi * coslam;
/*     */         break;
/*     */       case 1:
/*  72 */         coslam = -coslam;
/*     */       case 2:
/*  74 */         xy.y *= cosphi * coslam;
/*     */         break;
/*     */     } 
/*  77 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/*  83 */     double rh = MapMath.distance(x, y);
/*  84 */     double sinz = Math.sin(lp.y = Math.atan(rh));
/*  85 */     double cosz = Math.sqrt(1.0D - sinz * sinz);
/*  86 */     if (Math.abs(rh) <= 1.0E-10D) {
/*  87 */       lp.y = this.projectionLatitude;
/*  88 */       lp.x = 0.0D;
/*     */     } else {
/*  90 */       switch (this.mode) {
/*     */         case 4:
/*  92 */           lp.y = cosz * this.sinphi0 + y * sinz * this.cosphi0 / rh;
/*  93 */           if (Math.abs(lp.y) >= 1.0D) {
/*  94 */             lp.y = (lp.y > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*     */           } else {
/*  96 */             lp.y = Math.asin(lp.y);
/*     */           } 
/*  97 */           y = (cosz - this.sinphi0 * Math.sin(lp.y)) * rh;
/*  98 */           x *= sinz * this.cosphi0;
/*     */           break;
/*     */         case 3:
/* 101 */           lp.y = y * sinz / rh;
/* 102 */           if (Math.abs(lp.y) >= 1.0D) {
/* 103 */             lp.y = (lp.y > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*     */           } else {
/* 105 */             lp.y = Math.asin(lp.y);
/*     */           } 
/* 106 */           y = cosz * rh;
/* 107 */           x *= sinz;
/*     */           break;
/*     */         case 2:
/* 110 */           lp.y -= 1.5707963267948966D;
/*     */           break;
/*     */         case 1:
/* 113 */           lp.y = 1.5707963267948966D - lp.y;
/* 114 */           y = -y;
/*     */           break;
/*     */       } 
/* 117 */       lp.x = Math.atan2(x, y);
/*     */     } 
/* 119 */     return lp;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 127 */     return "Gnomonic Azimuthal";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\GnomonicAzimuthalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */