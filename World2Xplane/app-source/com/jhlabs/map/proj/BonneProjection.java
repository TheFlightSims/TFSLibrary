/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class BonneProjection extends Projection {
/*     */   private double phi1;
/*     */   
/*     */   private double cphi1;
/*     */   
/*     */   private double am1;
/*     */   
/*     */   private double m1;
/*     */   
/*     */   private double[] en;
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*  36 */     double d2 = this.cphi1 + this.phi1 - lpphi;
/*     */     double E;
/*  38 */     out.x = d2 * Math.sin(E = lplam * Math.cos(lpphi) / d2);
/*  39 */     out.y = this.cphi1 - d2 * Math.cos(E);
/*  41 */     out.x = out.y = 0.0D;
/*  45 */     double d1, c, rh = this.am1 + this.m1 - MapMath.mlfn(lpphi, d1 = Math.sin(lpphi), c = Math.cos(lpphi), this.en);
/*  46 */     d1 = c * lplam / rh * Math.sqrt(1.0D - this.es * d1 * d1);
/*  47 */     out.x = rh * Math.sin(d1);
/*  48 */     out.y = this.am1 - rh * Math.cos(d1);
/*  50 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/*  54 */     if (this.spherical) {
/*  57 */       double rh = MapMath.distance(xyx, out.y = this.cphi1 - xyy);
/*  58 */       out.y = this.cphi1 + this.phi1 - rh;
/*  59 */       if (Math.abs(out.y) > 1.5707963267948966D)
/*  59 */         throw new ProjectionException("I"); 
/*  60 */       if (Math.abs(Math.abs(out.y) - 1.5707963267948966D) <= 1.0E-10D) {
/*  61 */         out.x = 0.0D;
/*     */       } else {
/*  63 */         out.x = rh * Math.atan2(xyx, xyy) / Math.cos(out.y);
/*     */       } 
/*     */     } else {
/*  67 */       double rh = MapMath.distance(xyx, out.y = this.am1 - xyy);
/*  68 */       out.y = MapMath.inv_mlfn(this.am1 + this.m1 - rh, this.es, this.en);
/*     */       double s;
/*  69 */       if ((s = Math.abs(out.y)) < 1.5707963267948966D) {
/*  70 */         s = Math.sin(out.y);
/*  71 */         out.x = rh * Math.atan2(xyx, xyy) * Math.sqrt(1.0D - this.es * s * s) / Math.cos(out.y);
/*  73 */       } else if (Math.abs(s - 1.5707963267948966D) <= 1.0E-10D) {
/*  74 */         out.x = 0.0D;
/*     */       } else {
/*  75 */         throw new ProjectionException("I");
/*     */       } 
/*     */     } 
/*  77 */     return out;
/*     */   }
/*     */   
/*     */   public boolean isEqualArea() {
/*  84 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  92 */     super.initialize();
/*  97 */     this.phi1 = 1.5707963267948966D;
/*  98 */     if (Math.abs(this.phi1) < 1.0E-10D)
/*  99 */       throw new ProjectionException("-23"); 
/* 101 */     this.en = MapMath.enfn(this.es);
/*     */     double c;
/* 102 */     this.m1 = MapMath.mlfn(this.phi1, this.am1 = Math.sin(this.phi1), c = Math.cos(this.phi1), this.en);
/* 104 */     this.am1 = c / Math.sqrt(1.0D - this.es * this.am1 * this.am1) * this.am1;
/* 106 */     if (Math.abs(this.phi1) + 1.0E-10D >= 1.5707963267948966D) {
/* 107 */       this.cphi1 = 0.0D;
/*     */     } else {
/* 109 */       this.cphi1 = 1.0D / Math.tan(this.phi1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 114 */     return "Bonne";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\BonneProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */