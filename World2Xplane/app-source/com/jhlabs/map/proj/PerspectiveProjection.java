/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class PerspectiveProjection extends Projection {
/*     */   private double height;
/*     */   
/*     */   private double psinph0;
/*     */   
/*     */   private double pcosph0;
/*     */   
/*     */   private double p;
/*     */   
/*     */   private double rp;
/*     */   
/*     */   private double pn1;
/*     */   
/*     */   private double pfact;
/*     */   
/*     */   private double h;
/*     */   
/*     */   private double cg;
/*     */   
/*     */   private double sg;
/*     */   
/*     */   private double sw;
/*     */   
/*     */   private double cw;
/*     */   
/*     */   private int mode;
/*     */   
/*     */   private int tilt;
/*     */   
/*     */   private static final double EPS10 = 1.0E-10D;
/*     */   
/*     */   private static final int N_POLE = 0;
/*     */   
/*     */   private static final int S_POLE = 1;
/*     */   
/*     */   private static final int EQUIT = 2;
/*     */   
/*     */   private static final int OBLIQ = 3;
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/*  51 */     double sinphi = Math.sin(lpphi);
/*  52 */     double cosphi = Math.cos(lpphi);
/*  53 */     double coslam = Math.cos(lplam);
/*  54 */     switch (this.mode) {
/*     */       case 3:
/*  56 */         xy.y = this.psinph0 * sinphi + this.pcosph0 * cosphi * coslam;
/*     */         break;
/*     */       case 2:
/*  59 */         xy.y = cosphi * coslam;
/*     */         break;
/*     */       case 1:
/*  62 */         xy.y = -sinphi;
/*     */         break;
/*     */       case 0:
/*  65 */         xy.y = sinphi;
/*     */         break;
/*     */     } 
/*  70 */     xy.y = this.pn1 / (this.p - xy.y);
/*  71 */     xy.x = xy.y * cosphi * Math.sin(lplam);
/*  72 */     switch (this.mode) {
/*     */       case 3:
/*  74 */         xy.y *= this.pcosph0 * sinphi - this.psinph0 * cosphi * coslam;
/*     */         break;
/*     */       case 2:
/*  78 */         xy.y *= sinphi;
/*     */         break;
/*     */       case 0:
/*  81 */         coslam = -coslam;
/*     */       case 1:
/*  83 */         xy.y *= cosphi * coslam;
/*     */         break;
/*     */     } 
/*  86 */     if (this.tilt != 0) {
/*  89 */       double yt = xy.y * this.cg + xy.x * this.sg;
/*  90 */       double ba = 1.0D / (yt * this.sw * this.h + this.cw);
/*  91 */       xy.x = (xy.x * this.cg - xy.y * this.sg) * this.cw * ba;
/*  92 */       xy.y = yt * ba;
/*     */     } 
/*  94 */     return xy;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 148 */     super.initialize();
/* 149 */     this.mode = 2;
/* 150 */     this.height = this.a;
/* 151 */     this.tilt = 0;
/* 164 */     this.pn1 = this.height / this.a;
/* 165 */     this.p = 1.0D + this.pn1;
/* 166 */     this.rp = 1.0D / this.p;
/* 167 */     this.h = 1.0D / this.pn1;
/* 168 */     this.pfact = (this.p + 1.0D) * this.h;
/* 169 */     this.es = 0.0D;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 187 */     return "Perspective";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\PerspectiveProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */