/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class CassiniProjection extends Projection {
/*     */   private double m0;
/*     */   
/*     */   private double n;
/*     */   
/*     */   private double t;
/*     */   
/*     */   private double a1;
/*     */   
/*     */   private double c;
/*     */   
/*     */   private double r;
/*     */   
/*     */   private double dd;
/*     */   
/*     */   private double d2;
/*     */   
/*     */   private double a2;
/*     */   
/*     */   private double tn;
/*     */   
/*     */   private double[] en;
/*     */   
/*     */   private static final double EPS10 = 1.0E-10D;
/*     */   
/*     */   private static final double C1 = 0.16666666666666666D;
/*     */   
/*     */   private static final double C2 = 0.008333333333333333D;
/*     */   
/*     */   private static final double C3 = 0.041666666666666664D;
/*     */   
/*     */   private static final double C4 = 0.3333333333333333D;
/*     */   
/*     */   private static final double C5 = 0.06666666666666667D;
/*     */   
/*     */   public CassiniProjection() {
/*  48 */     this.projectionLatitude = Math.toRadians(0.0D);
/*  49 */     this.projectionLongitude = Math.toRadians(0.0D);
/*  50 */     this.minLongitude = Math.toRadians(-90.0D);
/*  51 */     this.maxLongitude = Math.toRadians(90.0D);
/*  52 */     initialize();
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/*  56 */     if (this.spherical) {
/*  57 */       xy.x = Math.asin(Math.cos(lpphi) * Math.sin(lplam));
/*  58 */       xy.y = Math.atan2(Math.tan(lpphi), Math.cos(lplam)) - this.projectionLatitude;
/*     */     } else {
/*  60 */       xy.y = MapMath.mlfn(lpphi, this.n = Math.sin(lpphi), this.c = Math.cos(lpphi), this.en);
/*  61 */       this.n = 1.0D / Math.sqrt(1.0D - this.es * this.n * this.n);
/*  62 */       this.tn = Math.tan(lpphi);
/*  62 */       this.t = this.tn * this.tn;
/*  63 */       this.a1 = lplam * this.c;
/*  64 */       this.c *= this.es * this.c / (1.0D - this.es);
/*  65 */       this.a2 = this.a1 * this.a1;
/*  66 */       xy.x = this.n * this.a1 * (1.0D - this.a2 * this.t * (0.16666666666666666D - (8.0D - this.t + 8.0D * this.c) * this.a2 * 0.008333333333333333D));
/*  68 */       xy.y -= this.m0 - this.n * this.tn * this.a2 * (0.5D + (5.0D - this.t + 6.0D * this.c) * this.a2 * 0.041666666666666664D);
/*     */     } 
/*  71 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/*  76 */     out.y = Math.asin(Math.sin(this.dd = xyy + this.projectionLatitude) * Math.cos(xyx));
/*  77 */     out.x = Math.atan2(Math.tan(xyx), Math.cos(this.dd));
/*  81 */     double ph1 = MapMath.inv_mlfn(this.m0 + xyy, this.es, this.en);
/*  82 */     this.tn = Math.tan(ph1);
/*  82 */     this.t = this.tn * this.tn;
/*  83 */     this.n = Math.sin(ph1);
/*  84 */     this.r = 1.0D / (1.0D - this.es * this.n * this.n);
/*  85 */     this.n = Math.sqrt(this.r);
/*  86 */     this.r *= (1.0D - this.es) * this.n;
/*  87 */     this.dd = xyx / this.n;
/*  88 */     this.d2 = this.dd * this.dd;
/*  89 */     out.y = ph1 - this.n * this.tn / this.r * this.d2 * (0.5D - (1.0D + 3.0D * this.t) * this.d2 * 0.041666666666666664D);
/*  91 */     out.x = this.dd * (1.0D + this.t * this.d2 * (-0.3333333333333333D + (1.0D + 3.0D * this.t) * this.d2 * 0.06666666666666667D)) / Math.cos(ph1);
/*  94 */     return out;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  98 */     super.initialize();
/*  99 */     if (!this.spherical) {
/* 100 */       if ((this.en = MapMath.enfn(this.es)) == null)
/* 101 */         throw new IllegalArgumentException(); 
/* 102 */       this.m0 = MapMath.mlfn(this.projectionLatitude, Math.sin(this.projectionLatitude), Math.cos(this.projectionLatitude), this.en);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public int getEPSGCode() {
/* 114 */     return 9806;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 118 */     return "Cassini";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\CassiniProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */