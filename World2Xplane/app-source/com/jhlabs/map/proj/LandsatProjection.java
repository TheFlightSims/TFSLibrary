/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class LandsatProjection extends Projection {
/*     */   private double a2;
/*     */   
/*     */   private double a4;
/*     */   
/*     */   private double b;
/*     */   
/*     */   private double c1;
/*     */   
/*     */   private double c3;
/*     */   
/*     */   private double q;
/*     */   
/*     */   private double t;
/*     */   
/*     */   private double u;
/*     */   
/*     */   private double w;
/*     */   
/*     */   private double p22;
/*     */   
/*     */   private double sa;
/*     */   
/*     */   private double ca;
/*     */   
/*     */   private double xj;
/*     */   
/*     */   private double rlm;
/*     */   
/*     */   private double rlm2;
/*     */   
/*     */   private static final double TOL = 1.0E-7D;
/*     */   
/*     */   private static final double PI_HALFPI = 4.71238898038469D;
/*     */   
/*     */   private static final double TWOPI_HALFPI = 7.853981633974483D;
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/*     */     int l;
/*  36 */     double lamt = 0.0D, lamdp = 0.0D;
/*  39 */     if (lpphi > 1.5707963267948966D) {
/*  40 */       lpphi = 1.5707963267948966D;
/*  41 */     } else if (lpphi < -1.5707963267948966D) {
/*  42 */       lpphi = -1.5707963267948966D;
/*     */     } 
/*  43 */     double lampp = (lpphi >= 0.0D) ? 1.5707963267948966D : 4.71238898038469D;
/*  44 */     double tanphi = Math.tan(lpphi);
/*  45 */     int nn = 0;
/*     */     while (true) {
/*  46 */       double sav = lampp;
/*  47 */       double lamtp = lplam + this.p22 * lampp;
/*  48 */       double cl = Math.cos(lamtp);
/*  49 */       if (Math.abs(cl) < 1.0E-7D)
/*  50 */         lamtp -= 1.0E-7D; 
/*  51 */       double fac = lampp - Math.sin(lampp) * ((cl < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D);
/*  52 */       for (l = 50; l > 0; l--) {
/*  53 */         lamt = lplam + this.p22 * sav;
/*     */         double c;
/*  54 */         if (Math.abs(c = Math.cos(lamt)) < 1.0E-7D)
/*  55 */           lamt -= 1.0E-7D; 
/*  56 */         double xlam = (this.one_es * tanphi * this.sa + Math.sin(lamt) * this.ca) / c;
/*  57 */         lamdp = Math.atan(xlam) + fac;
/*  58 */         if (Math.abs(Math.abs(sav) - Math.abs(lamdp)) < 1.0E-7D)
/*     */           break; 
/*  60 */         sav = lamdp;
/*     */       } 
/*  62 */       if (l == 0 || ++nn >= 3 || (lamdp > this.rlm && lamdp < this.rlm2))
/*     */         break; 
/*  64 */       if (lamdp <= this.rlm) {
/*  65 */         lampp = 7.853981633974483D;
/*     */         continue;
/*     */       } 
/*  66 */       if (lamdp >= this.rlm2)
/*  67 */         lampp = 1.5707963267948966D; 
/*     */     } 
/*  69 */     if (l != 0) {
/*  70 */       double sp = Math.sin(lpphi);
/*  71 */       double phidp = MapMath.asin((this.one_es * this.ca * sp - this.sa * Math.cos(lpphi) * Math.sin(lamt)) / Math.sqrt(1.0D - this.es * sp * sp));
/*  73 */       double tanph = Math.log(Math.tan(0.7853981633974483D + 0.5D * phidp));
/*  74 */       double sd = Math.sin(lamdp);
/*  75 */       double sdsq = sd * sd;
/*  76 */       double s = this.p22 * this.sa * Math.cos(lamdp) * Math.sqrt((1.0D + this.t * sdsq) / (1.0D + this.w * sdsq) * (1.0D + this.q * sdsq));
/*  78 */       double d = Math.sqrt(this.xj * this.xj + s * s);
/*  79 */       xy.x = this.b * lamdp + this.a2 * Math.sin(2.0D * lamdp) + this.a4 * Math.sin(lamdp * 4.0D) - tanph * s / d;
/*  81 */       xy.y = this.c1 * sd + this.c3 * Math.sin(lamdp * 3.0D) + tanph * this.xj / d;
/*     */     } else {
/*  83 */       xy.x = xy.y = Double.POSITIVE_INFINITY;
/*     */     } 
/*  84 */     return xy;
/*     */   }
/*     */   
/*     */   private void seraz0(double lam, double mult) {
/* 134 */     lam *= 0.017453292519943295D;
/* 135 */     double sd = Math.sin(lam);
/* 136 */     double sdsq = sd * sd;
/* 137 */     double s = this.p22 * this.sa * Math.cos(lam) * Math.sqrt((1.0D + this.t * sdsq) / (1.0D + this.w * sdsq) * (1.0D + this.q * sdsq));
/* 139 */     double d__1 = 1.0D + this.q * sdsq;
/* 140 */     double h = Math.sqrt((1.0D + this.q * sdsq) / (1.0D + this.w * sdsq)) * ((1.0D + this.w * sdsq) / d__1 * d__1 - this.p22 * this.ca);
/* 142 */     double sq = Math.sqrt(this.xj * this.xj + s * s);
/*     */     double fc;
/* 143 */     this.b += fc = mult * (h * this.xj - s * s) / sq;
/* 144 */     this.a2 += fc * Math.cos(lam + lam);
/* 145 */     this.a4 += fc * Math.cos(lam * 4.0D);
/* 146 */     fc = mult * s * (h + this.xj) / sq;
/* 147 */     this.c1 += fc * Math.cos(lam);
/* 148 */     this.c3 += fc * Math.cos(lam * 3.0D);
/*     */   }
/*     */   
/*     */   public void initialize() {
/*     */     double alf;
/* 152 */     super.initialize();
/* 157 */     int land = 1;
/* 158 */     if (land <= 0 || land > 5)
/* 159 */       throw new ProjectionException("-28"); 
/* 161 */     int path = 120;
/* 162 */     if (path <= 0 || path > ((land <= 3) ? 251 : 233))
/* 163 */       throw new ProjectionException("-29"); 
/* 164 */     if (land <= 3) {
/* 165 */       this.projectionLongitude = 2.2492058070450924D - 0.025032610785576042D * path;
/* 166 */       this.p22 = 103.2669323D;
/* 167 */       alf = 1.729481662386221D;
/*     */     } else {
/* 169 */       this.projectionLongitude = 2.2567107228286685D - 0.026966460545835135D * path;
/* 170 */       this.p22 = 98.8841202D;
/* 171 */       alf = 1.7139133254584316D;
/*     */     } 
/* 173 */     this.p22 /= 1440.0D;
/* 174 */     this.sa = Math.sin(alf);
/* 175 */     this.ca = Math.cos(alf);
/* 176 */     if (Math.abs(this.ca) < 1.0E-9D)
/* 177 */       this.ca = 1.0E-9D; 
/* 178 */     double esc = this.es * this.ca * this.ca;
/* 179 */     double ess = this.es * this.sa * this.sa;
/* 180 */     this.w = (1.0D - esc) * this.rone_es;
/* 181 */     this.w = this.w * this.w - 1.0D;
/* 182 */     this.q = ess * this.rone_es;
/* 183 */     this.t = ess * (2.0D - this.es) * this.rone_es * this.rone_es;
/* 184 */     this.u = esc * this.rone_es;
/* 185 */     this.xj = this.one_es * this.one_es * this.one_es;
/* 186 */     this.rlm = 1.6341348883592068D;
/* 187 */     this.rlm2 = this.rlm + 6.283185307179586D;
/* 188 */     this.a2 = this.a4 = this.b = this.c1 = this.c3 = 0.0D;
/* 189 */     seraz0(0.0D, 1.0D);
/*     */     double lam;
/* 190 */     for (lam = 9.0D; lam <= 81.0001D; lam += 18.0D)
/* 191 */       seraz0(lam, 4.0D); 
/* 192 */     for (lam = 18.0D; lam <= 72.0001D; lam += 18.0D)
/* 193 */       seraz0(lam, 2.0D); 
/* 194 */     seraz0(90.0D, 1.0D);
/* 195 */     this.a2 /= 30.0D;
/* 196 */     this.a4 /= 60.0D;
/* 197 */     this.b /= 30.0D;
/* 198 */     this.c1 /= 15.0D;
/* 199 */     this.c3 /= 45.0D;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 203 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 207 */     return "Landsat";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\LandsatProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */