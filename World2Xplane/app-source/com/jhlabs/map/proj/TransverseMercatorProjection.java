/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class TransverseMercatorProjection extends CylindricalProjection {
/*     */   private static final double FC1 = 1.0D;
/*     */   
/*     */   private static final double FC2 = 0.5D;
/*     */   
/*     */   private static final double FC3 = 0.16666666666666666D;
/*     */   
/*     */   private static final double FC4 = 0.08333333333333333D;
/*     */   
/*     */   private static final double FC5 = 0.05D;
/*     */   
/*     */   private static final double FC6 = 0.03333333333333333D;
/*     */   
/*     */   private static final double FC7 = 0.023809523809523808D;
/*     */   
/*     */   private static final double FC8 = 0.017857142857142856D;
/*     */   
/*     */   private double esp;
/*     */   
/*     */   private double ml0;
/*     */   
/*     */   private double[] en;
/*     */   
/*     */   public TransverseMercatorProjection() {
/*  44 */     this.ellipsoid = Ellipsoid.GRS_1980;
/*  45 */     this.projectionLatitude = Math.toRadians(0.0D);
/*  46 */     this.projectionLongitude = Math.toRadians(0.0D);
/*  47 */     this.minLongitude = Math.toRadians(-90.0D);
/*  48 */     this.maxLongitude = Math.toRadians(90.0D);
/*  49 */     initialize();
/*     */   }
/*     */   
/*     */   public TransverseMercatorProjection(Ellipsoid ellipsoid, double lon_0, double lat_0, double k, double x_0, double y_0) {
/*  56 */     setEllipsoid(ellipsoid);
/*  57 */     this.projectionLongitude = lon_0;
/*  58 */     this.projectionLatitude = lat_0;
/*  59 */     this.scaleFactor = k;
/*  60 */     this.falseEasting = x_0;
/*  61 */     this.falseNorthing = y_0;
/*  62 */     initialize();
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  66 */     TransverseMercatorProjection p = (TransverseMercatorProjection)super.clone();
/*  67 */     if (this.en != null)
/*  68 */       p.en = (double[])this.en.clone(); 
/*  69 */     return p;
/*     */   }
/*     */   
/*     */   public boolean isRectilinear() {
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  77 */     super.initialize();
/*  78 */     if (this.spherical) {
/*  79 */       this.esp = this.scaleFactor;
/*  80 */       this.ml0 = 0.5D * this.esp;
/*     */     } else {
/*  82 */       this.en = MapMath.enfn(this.es);
/*  83 */       this.ml0 = MapMath.mlfn(this.projectionLatitude, Math.sin(this.projectionLatitude), Math.cos(this.projectionLatitude), this.en);
/*  84 */       this.esp = this.es / (1.0D - this.es);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getRowFromNearestParallel(double latitude) {
/*  89 */     int degrees = (int)MapMath.radToDeg(MapMath.normalizeLatitude(latitude));
/*  90 */     if (degrees < -80 || degrees > 84)
/*  91 */       return 0; 
/*  92 */     if (degrees > 80)
/*  93 */       return 24; 
/*  94 */     return (degrees + 80) / 8 + 3;
/*     */   }
/*     */   
/*     */   public int getZoneFromNearestMeridian(double longitude) {
/*  98 */     int zone = (int)Math.floor((MapMath.normalizeLongitude(longitude) + Math.PI) * 30.0D / Math.PI) + 1;
/*  99 */     if (zone < 1) {
/* 100 */       zone = 1;
/* 101 */     } else if (zone > 60) {
/* 102 */       zone = 60;
/*     */     } 
/* 103 */     return zone;
/*     */   }
/*     */   
/*     */   public void setUTMZone(int zone) {
/* 107 */     zone--;
/* 108 */     this.projectionLongitude = (zone + 0.5D) * Math.PI / 30.0D - Math.PI;
/* 109 */     this.projectionLatitude = 0.0D;
/* 110 */     this.scaleFactor = 0.9996D;
/* 111 */     this.falseEasting = 500000.0D;
/* 112 */     initialize();
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/* 116 */     if (this.spherical) {
/* 117 */       double cosphi = Math.cos(lpphi);
/* 118 */       double b = cosphi * Math.sin(lplam);
/* 120 */       xy.x = this.ml0 * this.scaleFactor * Math.log((1.0D + b) / (1.0D - b));
/* 121 */       double ty = cosphi * Math.cos(lplam) / Math.sqrt(1.0D - b * b);
/* 122 */       ty = MapMath.acos(ty);
/* 123 */       if (lpphi < 0.0D)
/* 124 */         ty = -ty; 
/* 125 */       xy.y = this.esp * (ty - this.projectionLatitude);
/*     */     } else {
/* 128 */       double sinphi = Math.sin(lpphi);
/* 129 */       double cosphi = Math.cos(lpphi);
/* 130 */       double t = (Math.abs(cosphi) > 1.0E-10D) ? (sinphi / cosphi) : 0.0D;
/* 131 */       t *= t;
/* 132 */       double al = cosphi * lplam;
/* 133 */       double als = al * al;
/* 134 */       al /= Math.sqrt(1.0D - this.es * sinphi * sinphi);
/* 135 */       double n = this.esp * cosphi * cosphi;
/* 136 */       xy.x = this.scaleFactor * al * (1.0D + 0.16666666666666666D * als * (1.0D - t + n + 0.05D * als * (5.0D + t * (t - 18.0D) + n * (14.0D - 58.0D * t) + 0.023809523809523808D * als * (61.0D + t * (t * (179.0D - t) - 479.0D)))));
/* 141 */       xy.y = this.scaleFactor * (MapMath.mlfn(lpphi, sinphi, cosphi, this.en) - this.ml0 + sinphi * al * lplam * 0.5D * (1.0D + 0.08333333333333333D * als * (5.0D - t + n * (9.0D + 4.0D * n) + 0.03333333333333333D * als * (61.0D + t * (t - 58.0D) + n * (270.0D - 330.0D * t) + 0.017857142857142856D * als * (1385.0D + t * (t * (543.0D - t) - 3111.0D))))));
/*     */     } 
/* 148 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double out) {
/* 152 */     if (this.spherical) {
/* 153 */       x = Math.exp(x / this.scaleFactor);
/* 154 */       y = 0.5D * (x - 1.0D / x);
/* 155 */       x = Math.cos(this.projectionLatitude + y / this.scaleFactor);
/* 156 */       out.y = MapMath.asin(Math.sqrt((1.0D - x * x) / (1.0D + y * y)));
/* 157 */       if (y < 0.0D)
/* 158 */         out.y = -out.y; 
/* 159 */       out.x = Math.atan2(y, x);
/*     */     } else {
/* 163 */       out.y = MapMath.inv_mlfn(this.ml0 + y / this.scaleFactor, this.es, this.en);
/* 164 */       if (Math.abs(y) >= 1.5707963267948966D) {
/* 165 */         out.y = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/* 166 */         out.x = 0.0D;
/*     */       } else {
/* 168 */         double sinphi = Math.sin(y);
/* 169 */         double cosphi = Math.cos(y);
/* 170 */         double t = (Math.abs(cosphi) > 1.0E-10D) ? (sinphi / cosphi) : 0.0D;
/* 171 */         double n = this.esp * cosphi * cosphi;
/* 172 */         double con, d = x * Math.sqrt(con = 1.0D - this.es * sinphi * sinphi) / this.scaleFactor;
/* 173 */         con *= t;
/* 174 */         t *= t;
/* 175 */         double ds = d * d;
/* 176 */         out.y -= con * ds / (1.0D - this.es) * 0.5D * (1.0D - ds * 0.08333333333333333D * (5.0D + t * (3.0D - 9.0D * n) + n * (1.0D - 4.0D * n) - ds * 0.03333333333333333D * (61.0D + t * (90.0D - 252.0D * n + 45.0D * t) + 46.0D * n - ds * 0.017857142857142856D * (1385.0D + t * (3633.0D + t * (4095.0D + 1574.0D * t))))));
/* 182 */         out.x = d * (1.0D - ds * 0.16666666666666666D * (1.0D + 2.0D * t + n - ds * 0.05D * (5.0D + t * (28.0D + 24.0D * t + 8.0D * n) + 6.0D * n - ds * 0.023809523809523808D * (61.0D + t * (662.0D + t * (1320.0D + 720.0D * t)))))) / cosphi;
/*     */       } 
/*     */     } 
/* 189 */     return out;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 193 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 197 */     return "Transverse Mercator";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\TransverseMercatorProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */