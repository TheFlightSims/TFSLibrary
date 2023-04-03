/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class PolyconicProjection extends Projection {
/*     */   private double ml0;
/*     */   
/*     */   private double[] en;
/*     */   
/*     */   private static final double TOL = 1.0E-10D;
/*     */   
/*     */   private static final double CONV = 1.0E-10D;
/*     */   
/*     */   private static final int N_ITER = 10;
/*     */   
/*     */   private static final int I_ITER = 20;
/*     */   
/*     */   private static final double ITOL = 1.0E-12D;
/*     */   
/*     */   public PolyconicProjection() {
/*  37 */     this.minLatitude = MapMath.degToRad(0.0D);
/*  38 */     this.maxLatitude = MapMath.degToRad(80.0D);
/*  39 */     this.minLongitude = MapMath.degToRad(-60.0D);
/*  40 */     this.maxLongitude = MapMath.degToRad(60.0D);
/*  41 */     initialize();
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*  45 */     if (this.spherical) {
/*  48 */       if (Math.abs(lpphi) <= 1.0E-10D) {
/*  49 */         out.x = lplam;
/*  50 */         out.y = this.ml0;
/*     */       } else {
/*  52 */         double cot = 1.0D / Math.tan(lpphi);
/*     */         double E;
/*  53 */         out.x = Math.sin(E = lplam * Math.sin(lpphi)) * cot;
/*  54 */         out.y = lpphi - this.projectionLatitude + cot * (1.0D - Math.cos(E));
/*     */       } 
/*  59 */     } else if (Math.abs(lpphi) <= 1.0E-10D) {
/*  60 */       out.x = lplam;
/*  61 */       out.y = -this.ml0;
/*     */     } else {
/*  63 */       double sp = Math.sin(lpphi);
/*  64 */       double cp, ms = (Math.abs(cp = Math.cos(lpphi)) > 1.0E-10D) ? (MapMath.msfn(sp, cp, this.es) / sp) : 0.0D;
/*  65 */       out.x = ms * Math.sin(out.x *= sp);
/*  66 */       out.y = MapMath.mlfn(lpphi, sp, cp, this.en) - this.ml0 + ms * (1.0D - Math.cos(lplam));
/*     */     } 
/*  69 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/*  74 */     if (this.spherical) {
/*     */       double lpphi;
/*  78 */       if (Math.abs(lpphi = this.projectionLatitude + xyy) <= 1.0E-10D) {
/*  79 */         out.x = xyx;
/*  79 */         out.y = 0.0D;
/*     */       } else {
/*     */         double dphi, tp;
/*  81 */         lpphi = xyy;
/*  82 */         double B = xyx * xyx + xyy * xyy;
/*  83 */         int i = 10;
/*     */         do {
/*  85 */           tp = Math.tan(lpphi);
/*  86 */           out.y -= dphi = (xyy * (lpphi * tp + 1.0D) - lpphi - 0.5D * (lpphi * lpphi + B) * tp) / ((lpphi - xyy) / tp - 1.0D);
/*  89 */         } while (Math.abs(dphi) > 1.0E-10D && --i > 0);
/*  90 */         if (i == 0)
/*  90 */           throw new ProjectionException("I"); 
/*  91 */         out.x = Math.asin(xyx * Math.tan(lpphi)) / Math.sin(lpphi);
/*  92 */         out.y = lpphi;
/*     */       } 
/*     */     } else {
/*  95 */       xyy += this.ml0;
/*  96 */       if (Math.abs(xyy) <= 1.0E-10D) {
/*  96 */         out.x = xyx;
/*  96 */         out.y = 0.0D;
/*     */       } else {
/* 101 */         double r = xyy * xyy + xyx * xyx;
/*     */         double lpphi;
/*     */         int i;
/* 102 */         for (lpphi = xyy, i = 20; i > 0; i--) {
/* 103 */           double sp = Math.sin(lpphi);
/* 104 */           double cp, s2ph = sp * (cp = Math.cos(lpphi));
/* 105 */           if (Math.abs(cp) < 1.0E-12D)
/* 106 */             throw new ProjectionException("I"); 
/* 107 */           double mlp, d1 = sp * (mlp = Math.sqrt(1.0D - this.es * sp * sp)) / cp;
/* 108 */           double ml = MapMath.mlfn(lpphi, sp, cp, this.en);
/* 109 */           double mlb = ml * ml + r;
/* 110 */           mlp = 1.0D / this.es / mlp * mlp * mlp;
/*     */           double dPhi;
/* 111 */           lpphi += dPhi = (ml + ml + d1 * mlb - 2.0D * xyy * (d1 * ml + 1.0D)) / (this.es * s2ph * (mlb - 2.0D * xyy * ml) / d1 + 2.0D * (xyy - ml) * (d1 * mlp - 1.0D / s2ph) - mlp - mlp);
/* 115 */           if (Math.abs(dPhi) <= 1.0E-12D)
/*     */             break; 
/*     */         } 
/* 118 */         if (i == 0)
/* 119 */           throw new ProjectionException("I"); 
/* 120 */         double c = Math.sin(lpphi);
/* 121 */         out.x = Math.asin(xyx * Math.tan(lpphi) * Math.sqrt(1.0D - this.es * c * c)) / Math.sin(lpphi);
/* 122 */         out.y = lpphi;
/*     */       } 
/*     */     } 
/* 125 */     return out;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 133 */     super.initialize();
/* 134 */     this.spherical = true;
/* 135 */     if (!this.spherical) {
/* 136 */       this.en = MapMath.enfn(this.es);
/* 137 */       if (this.en == null)
/* 138 */         throw new ProjectionException("E"); 
/* 139 */       this.ml0 = MapMath.mlfn(this.projectionLatitude, Math.sin(this.projectionLatitude), Math.cos(this.projectionLatitude), this.en);
/*     */     } else {
/* 141 */       this.ml0 = -this.projectionLatitude;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 146 */     return "Polyconic (American)";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\PolyconicProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */