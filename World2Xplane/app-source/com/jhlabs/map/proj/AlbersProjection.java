/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class AlbersProjection extends Projection {
/*     */   private static final double EPS10 = 1.0E-10D;
/*     */   
/*     */   private static final double TOL7 = 1.0E-7D;
/*     */   
/*     */   private double ec;
/*     */   
/*     */   private double n;
/*     */   
/*     */   private double c;
/*     */   
/*     */   private double dd;
/*     */   
/*     */   private double n2;
/*     */   
/*     */   private double rho0;
/*     */   
/*     */   private double phi1;
/*     */   
/*     */   private double phi2;
/*     */   
/*     */   private double[] en;
/*     */   
/*     */   private static final int N_ITER = 15;
/*     */   
/*     */   private static final double EPSILON = 1.0E-7D;
/*     */   
/*     */   private static final double TOL = 1.0E-10D;
/*     */   
/*  43 */   protected double projectionLatitude1 = MapMath.degToRad(45.5D);
/*     */   
/*  44 */   protected double projectionLatitude2 = MapMath.degToRad(29.5D);
/*     */   
/*     */   public AlbersProjection() {
/*  47 */     this.minLatitude = Math.toRadians(0.0D);
/*  48 */     this.maxLatitude = Math.toRadians(80.0D);
/*  49 */     initialize();
/*     */   }
/*     */   
/*     */   private static double phi1_(double qs, double Te, double Tone_es) {
/*  56 */     double dphi, Phi = Math.asin(0.5D * qs);
/*  57 */     if (Te < 1.0E-7D)
/*  58 */       return Phi; 
/*  59 */     int i = 15;
/*     */     do {
/*  61 */       double sinpi = Math.sin(Phi);
/*  62 */       double cospi = Math.cos(Phi);
/*  63 */       double con = Te * sinpi;
/*  64 */       double com = 1.0D - con * con;
/*  65 */       dphi = 0.5D * com * com / cospi * (qs / Tone_es - sinpi / com + 0.5D / Te * Math.log((1.0D - con) / (1.0D + con)));
/*  68 */       Phi += dphi;
/*  69 */     } while (Math.abs(dphi) > 1.0E-10D && --i != 0);
/*  70 */     return (i != 0) ? Phi : Double.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*     */     double rho;
/*  75 */     if ((rho = this.c - (!this.spherical ? (this.n * MapMath.qsfn(Math.sin(lpphi), this.e, this.one_es)) : (this.n2 * Math.sin(lpphi)))) < 0.0D)
/*  76 */       throw new ProjectionException("F"); 
/*  77 */     rho = this.dd * Math.sqrt(rho);
/*  78 */     out.x = rho * Math.sin(lplam *= this.n);
/*  79 */     out.y = this.rho0 - rho * Math.cos(lplam);
/*  80 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/*     */     double rho;
/*  85 */     if ((rho = MapMath.distance(xyx, xyy = this.rho0 - xyy)) != 0.0D) {
/*  87 */       if (this.n < 0.0D) {
/*  88 */         rho = -rho;
/*  89 */         xyx = -xyx;
/*  90 */         xyy = -xyy;
/*     */       } 
/*  92 */       double lpphi = rho / this.dd;
/*  93 */       if (!this.spherical) {
/*  94 */         lpphi = (this.c - lpphi * lpphi) / this.n;
/*  95 */         if (Math.abs(this.ec - Math.abs(lpphi)) > 1.0E-7D) {
/*  96 */           if ((lpphi = phi1_(lpphi, this.e, this.one_es)) == Double.MAX_VALUE)
/*  97 */             throw new ProjectionException("I"); 
/*     */         } else {
/*  99 */           lpphi = (lpphi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */         } 
/* 100 */       } else if (Math.abs(out.y = (this.c - lpphi * lpphi) / this.n2) <= 1.0D) {
/* 101 */         lpphi = Math.asin(lpphi);
/*     */       } else {
/* 103 */         lpphi = (lpphi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */       } 
/* 104 */       double lplam = Math.atan2(xyx, xyy) / this.n;
/* 105 */       out.x = lplam;
/* 106 */       out.y = lpphi;
/*     */     } else {
/* 108 */       out.x = 0.0D;
/* 109 */       out.y = (this.n > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*     */     } 
/* 111 */     return out;
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 115 */     super.initialize();
/* 119 */     this.phi1 = this.projectionLatitude1;
/* 120 */     this.phi2 = this.projectionLatitude2;
/* 122 */     if (Math.abs(this.phi1 + this.phi2) < 1.0E-10D)
/* 123 */       throw new IllegalArgumentException("-21"); 
/* 124 */     double sinphi = Math.sin(this.phi1);
/* 125 */     double cosphi = Math.cos(this.phi1);
/* 126 */     boolean secant = (Math.abs(this.phi1 - this.phi2) >= 1.0E-10D);
/* 127 */     this.spherical = (this.es > 0.0D);
/* 128 */     if (!this.spherical) {
/* 131 */       if ((this.en = MapMath.enfn(this.es)) == null)
/* 132 */         throw new IllegalArgumentException("0"); 
/* 133 */       double m1 = MapMath.msfn(sinphi, cosphi, this.es);
/* 134 */       double ml1 = MapMath.qsfn(sinphi, this.e, this.one_es);
/* 135 */       if (secant) {
/* 138 */         sinphi = Math.sin(this.phi2);
/* 139 */         cosphi = Math.cos(this.phi2);
/* 140 */         double m2 = MapMath.msfn(sinphi, cosphi, this.es);
/* 141 */         double ml2 = MapMath.qsfn(sinphi, this.e, this.one_es);
/* 142 */         this.n = (m1 * m1 - m2 * m2) / (ml2 - ml1);
/*     */       } 
/* 144 */       this.ec = 1.0D - 0.5D * this.one_es * Math.log((1.0D - this.e) / (1.0D + this.e)) / this.e;
/* 146 */       this.c = m1 * m1 + this.n * ml1;
/* 147 */       this.dd = 1.0D / this.n;
/* 148 */       this.rho0 = this.dd * Math.sqrt(this.c - this.n * MapMath.qsfn(Math.sin(this.projectionLatitude), this.e, this.one_es));
/*     */     } else {
/* 151 */       if (secant)
/* 151 */         this.n = 0.5D * (this.n + Math.sin(this.phi2)); 
/* 152 */       this.n2 = this.n + this.n;
/* 153 */       this.c = cosphi * cosphi + this.n2 * sinphi;
/* 154 */       this.dd = 1.0D / this.n;
/* 155 */       this.rho0 = this.dd * Math.sqrt(this.c - this.n2 * Math.sin(this.projectionLatitude));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEqualArea() {
/* 163 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 167 */     return true;
/*     */   }
/*     */   
/*     */   public int getEPSGCode() {
/* 174 */     return 9822;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 178 */     return "Albers Equal Area";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\AlbersProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */