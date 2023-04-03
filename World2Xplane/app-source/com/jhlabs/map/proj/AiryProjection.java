/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class AiryProjection extends Projection {
/*     */   private double p_halfpi;
/*     */   
/*     */   private double sinph0;
/*     */   
/*     */   private double cosph0;
/*     */   
/*     */   private double Cb;
/*     */   
/*     */   private int mode;
/*     */   
/*     */   private boolean no_cut = true;
/*     */   
/*     */   private static final double EPS = 1.0E-10D;
/*     */   
/*     */   private static final int N_POLE = 0;
/*     */   
/*     */   private static final int S_POLE = 1;
/*     */   
/*     */   private static final int EQUIT = 2;
/*     */   
/*     */   private static final int OBLIQ = 3;
/*     */   
/*     */   public AiryProjection() {
/*  41 */     this.minLatitude = Math.toRadians(-60.0D);
/*  42 */     this.maxLatitude = Math.toRadians(60.0D);
/*  43 */     this.minLongitude = Math.toRadians(-90.0D);
/*  44 */     this.maxLongitude = Math.toRadians(90.0D);
/*  45 */     initialize();
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*  51 */     double cosphi, sinphi, s, Krho, cosz, sinlam = Math.sin(lplam);
/*  52 */     double coslam = Math.cos(lplam);
/*  53 */     switch (this.mode) {
/*     */       case 2:
/*     */       case 3:
/*  56 */         sinphi = Math.sin(lpphi);
/*  57 */         cosphi = Math.cos(lpphi);
/*  58 */         cosz = cosphi * coslam;
/*  59 */         if (this.mode == 3)
/*  60 */           cosz = this.sinph0 * sinphi + this.cosph0 * cosz; 
/*  61 */         if (!this.no_cut && cosz < -1.0E-10D)
/*  62 */           throw new ProjectionException("F"); 
/*  63 */         s = 1.0D - cosz;
/*  64 */         if (Math.abs(s) > 1.0E-10D) {
/*  65 */           double t = 0.5D * (1.0D + cosz);
/*  66 */           Krho = -Math.log(t) / s - this.Cb / t;
/*     */         } else {
/*  68 */           Krho = 0.5D - this.Cb;
/*     */         } 
/*  69 */         out.x = Krho * cosphi * sinlam;
/*  70 */         if (this.mode == 3) {
/*  71 */           out.y = Krho * (this.cosph0 * sinphi - this.sinph0 * cosphi * coslam);
/*     */           break;
/*     */         } 
/*  74 */         out.y = Krho * sinphi;
/*     */         break;
/*     */       case 0:
/*     */       case 1:
/*  78 */         out.y = Math.abs(this.p_halfpi - lpphi);
/*  79 */         if (!this.no_cut && lpphi - 1.0E-10D > 1.5707963267948966D)
/*  80 */           throw new ProjectionException("F"); 
/*  81 */         if ((out.y *= 0.5D) > 1.0E-10D) {
/*  82 */           double t = Math.tan(lpphi);
/*  83 */           Krho = -2.0D * (Math.log(Math.cos(lpphi)) / t + t * this.Cb);
/*  84 */           out.x = Krho * sinlam;
/*  85 */           out.y = Krho * coslam;
/*  86 */           if (this.mode == 0)
/*  87 */             out.y = -out.y; 
/*     */           break;
/*     */         } 
/*  89 */         out.x = out.y = 0.0D;
/*     */         break;
/*     */     } 
/*  91 */     return out;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  95 */     super.initialize();
/* 101 */     this.no_cut = false;
/* 102 */     double beta = 0.7853981633974483D;
/* 103 */     if (Math.abs(beta) < 1.0E-10D) {
/* 104 */       this.Cb = -0.5D;
/*     */     } else {
/* 106 */       this.Cb = 1.0D / Math.tan(beta);
/* 107 */       this.Cb *= this.Cb * Math.log(Math.cos(beta));
/*     */     } 
/* 109 */     if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966D) < 1.0E-10D) {
/* 110 */       if (this.projectionLatitude < 0.0D) {
/* 111 */         this.p_halfpi = -1.5707963267948966D;
/* 112 */         this.mode = 1;
/*     */       } else {
/* 114 */         this.p_halfpi = 1.5707963267948966D;
/* 115 */         this.mode = 0;
/*     */       } 
/* 118 */     } else if (Math.abs(this.projectionLatitude) < 1.0E-10D) {
/* 119 */       this.mode = 2;
/*     */     } else {
/* 121 */       this.mode = 3;
/* 122 */       this.sinph0 = Math.sin(this.projectionLatitude);
/* 123 */       this.cosph0 = Math.cos(this.projectionLatitude);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 129 */     return "Airy";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\AiryProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */