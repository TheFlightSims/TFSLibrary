/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class LambertConformalConicProjection extends ConicProjection {
/*     */   private double standardLatitude1;
/*     */   
/*     */   private double standardLatitude2;
/*     */   
/*     */   private double n;
/*     */   
/*     */   private double rho0;
/*     */   
/*     */   private double c;
/*     */   
/*     */   public LambertConformalConicProjection() {
/*  33 */     this.minLatitude = Math.toRadians(0.0D);
/*  34 */     this.maxLatitude = Math.toRadians(80.0D);
/*  35 */     this.standardLatitude1 = 0.7853981633974483D;
/*  36 */     this.standardLatitude2 = 0.7853981633974483D;
/*  37 */     initialize();
/*     */   }
/*     */   
/*     */   public LambertConformalConicProjection(Ellipsoid ellipsoid, double lon_0, double lat_1, double lat_2, double lat_0, double x_0, double y_0) {
/*  44 */     setEllipsoid(ellipsoid);
/*  45 */     this.projectionLongitude = lon_0;
/*  46 */     this.projectionLatitude = lat_0;
/*  47 */     this.scaleFactor = 1.0D;
/*  48 */     this.falseEasting = x_0;
/*  49 */     this.falseNorthing = y_0;
/*  50 */     this.standardLatitude1 = lat_1;
/*  51 */     this.standardLatitude2 = lat_2;
/*  52 */     initialize();
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double x, double y, Point2D.Double out) {
/*     */     double rho;
/*  57 */     if (Math.abs(Math.abs(y) - 1.5707963267948966D) < 1.0E-10D) {
/*  58 */       rho = 0.0D;
/*     */     } else {
/*  60 */       rho = this.c * (this.spherical ? Math.pow(Math.tan(0.7853981633974483D + 0.5D * y), -this.n) : Math.pow(MapMath.tsfn(y, Math.sin(y), this.e), this.n));
/*     */     } 
/*  63 */     out.x = this.scaleFactor * rho * Math.sin(x *= this.n);
/*  64 */     out.y = this.scaleFactor * (this.rho0 - rho * Math.cos(x));
/*  65 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double out) {
/*  69 */     double rho = MapMath.distance(x, y = this.rho0 - y);
/*  70 */     x /= this.scaleFactor;
/*  71 */     y /= this.scaleFactor;
/*  72 */     if (rho != 0.0D) {
/*  73 */       if (this.n < 0.0D) {
/*  74 */         rho = -rho;
/*  75 */         x = -x;
/*  76 */         y = -y;
/*     */       } 
/*  78 */       if (this.spherical) {
/*  79 */         out.y = 2.0D * Math.atan(Math.pow(this.c / rho, 1.0D / this.n)) - 1.5707963267948966D;
/*     */       } else {
/*  81 */         out.y = MapMath.phi2(Math.pow(rho / this.c, 1.0D / this.n), this.e);
/*     */       } 
/*  82 */       out.x = Math.atan2(x, y) / this.n;
/*     */     } else {
/*  84 */       out.x = 0.0D;
/*  85 */       out.y = (this.n > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*     */     } 
/*  87 */     return out;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  91 */     super.initialize();
/*  95 */     if (Math.abs(this.standardLatitude1 + this.standardLatitude2) < 1.0E-10D)
/*  96 */       throw new ProjectionException(); 
/*  97 */     double sinphi = Math.sin(this.standardLatitude1);
/*  98 */     double cosphi = Math.cos(this.standardLatitude1);
/*  99 */     boolean secant = (Math.abs(this.standardLatitude1 - this.standardLatitude2) >= 1.0E-10D);
/* 100 */     this.spherical = (this.es == 0.0D);
/* 101 */     if (!this.spherical) {
/* 104 */       double m1 = MapMath.msfn(sinphi, cosphi, this.es);
/* 105 */       double ml1 = MapMath.tsfn(this.standardLatitude1, sinphi, this.e);
/* 106 */       if (secant) {
/* 107 */         this.n = Math.log(m1 / MapMath.msfn(sinphi = Math.sin(this.standardLatitude2), Math.cos(this.standardLatitude2), this.es));
/* 109 */         this.n /= Math.log(ml1 / MapMath.tsfn(this.standardLatitude2, sinphi, this.e));
/*     */       } 
/* 111 */       this.c = this.rho0 = m1 * Math.pow(ml1, -this.n) / this.n;
/* 112 */       this.rho0 *= (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966D) < 1.0E-10D) ? 0.0D : Math.pow(MapMath.tsfn(this.projectionLatitude, Math.sin(this.projectionLatitude), this.e), this.n);
/*     */     } else {
/* 115 */       if (secant)
/* 116 */         this.n = Math.log(cosphi / Math.cos(this.standardLatitude2)) / Math.log(Math.tan(0.7853981633974483D + 0.5D * this.standardLatitude2) / Math.tan(0.7853981633974483D + 0.5D * this.standardLatitude1)); 
/* 119 */       this.c = cosphi * Math.pow(Math.tan(0.7853981633974483D + 0.5D * this.standardLatitude1), this.n) / this.n;
/* 120 */       this.rho0 = (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966D) < 1.0E-10D) ? 0.0D : (this.c * Math.pow(Math.tan(0.7853981633974483D + 0.5D * this.projectionLatitude), -this.n));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setStandardLatitude1(double standardLatitude1) {
/* 126 */     this.standardLatitude1 = standardLatitude1;
/*     */   }
/*     */   
/*     */   public double getStandardLatitude1() {
/* 130 */     return this.standardLatitude1;
/*     */   }
/*     */   
/*     */   public void setStandardLatitude2(double standardLatitude2) {
/* 134 */     this.standardLatitude2 = standardLatitude2;
/*     */   }
/*     */   
/*     */   public double getStandardLatitude2() {
/* 138 */     return this.standardLatitude2;
/*     */   }
/*     */   
/*     */   public boolean isConformal() {
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 149 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 153 */     return "Lambert Conformal Conic";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LambertConformalConicProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */