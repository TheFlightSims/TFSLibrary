/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class ObliqueMercatorProjection extends Projection {
/*     */   private static final double TOL = 1.0E-7D;
/*     */   
/*     */   private double alpha;
/*     */   
/*     */   private double lamc;
/*     */   
/*     */   private double lam1;
/*     */   
/*     */   private double phi1;
/*     */   
/*     */   private double lam2;
/*     */   
/*     */   private double phi2;
/*     */   
/*     */   private double Gamma;
/*     */   
/*     */   private double al;
/*     */   
/*     */   private double bl;
/*     */   
/*     */   private double el;
/*     */   
/*     */   private double singam;
/*     */   
/*     */   private double cosgam;
/*     */   
/*     */   private double sinrot;
/*     */   
/*     */   private double cosrot;
/*     */   
/*     */   private double u_0;
/*     */   
/*     */   private boolean ellips;
/*     */   
/*     */   private boolean rot;
/*     */   
/*     */   public ObliqueMercatorProjection() {
/*  37 */     this.ellipsoid = Ellipsoid.WGS_1984;
/*  38 */     this.projectionLatitude = Math.toRadians(0.0D);
/*  39 */     this.projectionLongitude = Math.toRadians(0.0D);
/*  40 */     this.minLongitude = Math.toRadians(-60.0D);
/*  41 */     this.maxLongitude = Math.toRadians(60.0D);
/*  42 */     this.minLatitude = Math.toRadians(-80.0D);
/*  43 */     this.maxLatitude = Math.toRadians(80.0D);
/*  44 */     this.alpha = Math.toRadians(-45.0D);
/*  45 */     initialize();
/*     */   }
/*     */   
/*     */   public ObliqueMercatorProjection(Ellipsoid ellipsoid, double lon_0, double lat_0, double alpha, double k, double x_0, double y_0) {
/*  52 */     setEllipsoid(ellipsoid);
/*  53 */     this.lamc = lon_0;
/*  54 */     this.projectionLatitude = lat_0;
/*  55 */     this.alpha = alpha;
/*  56 */     this.scaleFactor = k;
/*  57 */     this.falseEasting = x_0;
/*  58 */     this.falseNorthing = y_0;
/*  59 */     initialize();
/*     */   }
/*     */   
/*     */   public void initialize() {
/*     */     double d;
/*  63 */     super.initialize();
/*  65 */     int azi = 1;
/*  68 */     this.rot = true;
/*  70 */     if (azi != 0) {
/*  71 */       if (Math.abs(this.alpha) <= 1.0E-7D || Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966D) <= 1.0E-7D || Math.abs(Math.abs(this.alpha) - 1.5707963267948966D) <= 1.0E-7D)
/*  74 */         throw new ProjectionException("Obl 1"); 
/*     */     } else {
/*     */       double con;
/*  76 */       if (Math.abs(this.phi1 - this.phi2) <= 1.0E-7D || (con = Math.abs(this.phi1)) <= 1.0E-7D || Math.abs(con - 1.5707963267948966D) <= 1.0E-7D || Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966D) <= 1.0E-7D || Math.abs(Math.abs(this.phi2) - 1.5707963267948966D) <= 1.0E-7D)
/*  80 */         throw new ProjectionException("Obl 2"); 
/*     */     } 
/*  82 */     double com = (this.spherical = (this.es == 0.0D)) ? 1.0D : Math.sqrt(this.one_es);
/*  83 */     if (Math.abs(this.projectionLatitude) > 1.0E-10D) {
/*  84 */       double sinphi0 = Math.sin(this.projectionLatitude);
/*  85 */       double cosphi0 = Math.cos(this.projectionLatitude);
/*  86 */       if (!this.spherical) {
/*  87 */         double con = 1.0D - this.es * sinphi0 * sinphi0;
/*  88 */         this.bl = cosphi0 * cosphi0;
/*  89 */         this.bl = Math.sqrt(1.0D + this.es * this.bl * this.bl / this.one_es);
/*  90 */         this.al = this.bl * this.scaleFactor * com / con;
/*  91 */         d = this.bl * com / cosphi0 * Math.sqrt(con);
/*     */       } else {
/*  93 */         this.bl = 1.0D;
/*  94 */         this.al = this.scaleFactor;
/*  95 */         d = 1.0D / cosphi0;
/*     */       } 
/*  97 */       if ((f = d * d - 1.0D) <= 0.0D) {
/*  98 */         f = 0.0D;
/*     */       } else {
/* 100 */         f = Math.sqrt(f);
/* 101 */         if (this.projectionLatitude < 0.0D)
/* 102 */           f = -f; 
/*     */       } 
/* 104 */       this.el = f += d;
/* 105 */       if (!this.spherical) {
/* 106 */         this.el *= Math.pow(MapMath.tsfn(this.projectionLatitude, sinphi0, this.e), this.bl);
/*     */       } else {
/* 108 */         this.el *= Math.tan(0.5D * (1.5707963267948966D - this.projectionLatitude));
/*     */       } 
/*     */     } else {
/* 110 */       this.bl = 1.0D / com;
/* 111 */       this.al = this.scaleFactor;
/* 112 */       this.el = d = f = 1.0D;
/*     */     } 
/* 114 */     if (azi != 0) {
/* 115 */       this.Gamma = Math.asin(Math.sin(this.alpha) / d);
/* 116 */       this.projectionLongitude = this.lamc - Math.asin(0.5D * (f - 1.0D / f) * Math.tan(this.Gamma)) / this.bl;
/*     */     } else {
/*     */       double h, l;
/* 119 */       if (!this.spherical) {
/* 120 */         h = Math.pow(MapMath.tsfn(this.phi1, Math.sin(this.phi1), this.e), this.bl);
/* 121 */         l = Math.pow(MapMath.tsfn(this.phi2, Math.sin(this.phi2), this.e), this.bl);
/*     */       } else {
/* 123 */         h = Math.tan(0.5D * (1.5707963267948966D - this.phi1));
/* 124 */         l = Math.tan(0.5D * (1.5707963267948966D - this.phi2));
/*     */       } 
/* 126 */       f = this.el / h;
/* 127 */       double p = (l - h) / (l + h);
/* 128 */       double j = this.el * this.el;
/* 129 */       j = (j - l * h) / (j + l * h);
/*     */       double con;
/* 130 */       if ((con = this.lam1 - this.lam2) < -3.141592653589793D) {
/* 131 */         this.lam2 -= 6.283185307179586D;
/* 132 */       } else if (con > Math.PI) {
/* 133 */         this.lam2 += 6.283185307179586D;
/*     */       } 
/* 134 */       this.projectionLongitude = MapMath.normalizeLongitude(0.5D * (this.lam1 + this.lam2) - Math.atan(j * Math.tan(0.5D * this.bl * (this.lam1 - this.lam2)) / p) / this.bl);
/* 136 */       this.Gamma = Math.atan(2.0D * Math.sin(this.bl * MapMath.normalizeLongitude(this.lam1 - this.projectionLongitude)) / (f - 1.0D / f));
/* 138 */       this.alpha = Math.asin(d * Math.sin(this.Gamma));
/*     */     } 
/* 140 */     this.singam = Math.sin(this.Gamma);
/* 141 */     this.cosgam = Math.cos(this.Gamma);
/* 143 */     double f = this.alpha;
/* 144 */     this.sinrot = Math.sin(f);
/* 145 */     this.cosrot = Math.cos(f);
/* 147 */     this.u_0 = Math.abs(this.al * Math.atan(Math.sqrt(d * d - 1.0D) / this.cosrot) / this.bl);
/* 149 */     if (this.projectionLatitude < 0.0D)
/* 150 */       this.u_0 = -this.u_0; 
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/* 156 */     double ul, us, vl = Math.sin(this.bl * lam);
/* 157 */     if (Math.abs(Math.abs(phi) - 1.5707963267948966D) <= 1.0E-10D) {
/* 158 */       ul = (phi < 0.0D) ? -this.singam : this.singam;
/* 159 */       us = this.al * phi / this.bl;
/*     */     } else {
/* 161 */       double q = this.el / (!this.spherical ? Math.pow(MapMath.tsfn(phi, Math.sin(phi), this.e), this.bl) : Math.tan(0.5D * (1.5707963267948966D - phi)));
/* 163 */       double s = 0.5D * (q - 1.0D / q);
/* 164 */       ul = 2.0D * (s * this.singam - vl * this.cosgam) / (q + 1.0D / q);
/* 165 */       double con = Math.cos(this.bl * lam);
/* 166 */       if (Math.abs(con) >= 1.0E-7D) {
/* 167 */         us = this.al * Math.atan((s * this.cosgam + vl * this.singam) / con) / this.bl;
/* 168 */         if (con < 0.0D)
/* 169 */           us += Math.PI * this.al / this.bl; 
/*     */       } else {
/* 171 */         us = this.al * this.bl * lam;
/*     */       } 
/*     */     } 
/* 173 */     if (Math.abs(Math.abs(ul) - 1.0D) <= 1.0E-10D)
/* 173 */       throw new ProjectionException("Obl 3"); 
/* 174 */     double vs = 0.5D * this.al * Math.log((1.0D - ul) / (1.0D + ul)) / this.bl;
/* 175 */     us -= this.u_0;
/* 176 */     if (!this.rot) {
/* 177 */       xy.x = us;
/* 178 */       xy.y = vs;
/*     */     } else {
/* 180 */       xy.x = vs * this.cosrot + us * this.sinrot;
/* 181 */       xy.y = us * this.cosrot - vs * this.sinrot;
/*     */     } 
/* 183 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/*     */     double us, vs;
/* 189 */     if (!this.rot) {
/* 190 */       us = x;
/* 191 */       vs = y;
/*     */     } else {
/* 193 */       vs = x * this.cosrot - y * this.sinrot;
/* 194 */       us = y * this.cosrot + x * this.sinrot;
/*     */     } 
/* 196 */     us += this.u_0;
/* 197 */     double q = Math.exp(-this.bl * vs / this.al);
/* 198 */     double s = 0.5D * (q - 1.0D / q);
/* 199 */     double vl = Math.sin(this.bl * us / this.al);
/* 200 */     double ul = 2.0D * (vl * this.cosgam + s * this.singam) / (q + 1.0D / q);
/* 201 */     if (Math.abs(Math.abs(ul) - 1.0D) < 1.0E-10D) {
/* 202 */       lp.x = 0.0D;
/* 203 */       lp.y = (ul < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */     } else {
/* 205 */       lp.y = this.el / Math.sqrt((1.0D + ul) / (1.0D - ul));
/* 206 */       if (!this.spherical) {
/* 207 */         lp.y = MapMath.phi2(Math.pow(lp.y, 1.0D / this.bl), this.e);
/*     */       } else {
/* 209 */         lp.y = 1.5707963267948966D - 2.0D * Math.atan(lp.y);
/*     */       } 
/* 210 */       lp.x = -Math.atan2(s * this.cosgam - vl * this.singam, Math.cos(this.bl * us / this.al)) / this.bl;
/*     */     } 
/* 213 */     return lp;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 217 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 221 */     return "Oblique Mercator";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\ObliqueMercatorProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */