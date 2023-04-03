/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class StereographicAzimuthalProjection extends AzimuthalProjection {
/*     */   private static final double TOL = 1.0E-8D;
/*     */   
/*     */   private double akm1;
/*     */   
/*     */   public StereographicAzimuthalProjection() {
/*  33 */     this(Math.toRadians(90.0D), Math.toRadians(0.0D));
/*     */   }
/*     */   
/*     */   public StereographicAzimuthalProjection(double projectionLatitude, double projectionLongitude) {
/*  37 */     super(projectionLatitude, projectionLongitude);
/*  38 */     initialize();
/*     */   }
/*     */   
/*     */   public void setupUPS(int pole) {
/*  42 */     this.projectionLatitude = (pole == 2) ? -1.5707963267948966D : 1.5707963267948966D;
/*  43 */     this.projectionLongitude = 0.0D;
/*  44 */     this.scaleFactor = 0.994D;
/*  45 */     this.falseEasting = 2000000.0D;
/*  46 */     this.falseNorthing = 2000000.0D;
/*  47 */     this.trueScaleLatitude = 1.5707963267948966D;
/*  48 */     initialize();
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  54 */     super.initialize();
/*     */     double t;
/*  55 */     if (Math.abs((t = Math.abs(this.projectionLatitude)) - 1.5707963267948966D) < 1.0E-10D) {
/*  56 */       this.mode = (this.projectionLatitude < 0.0D) ? 2 : 1;
/*     */     } else {
/*  58 */       this.mode = (t > 1.0E-10D) ? 4 : 3;
/*     */     } 
/*  59 */     this.trueScaleLatitude = Math.abs(this.trueScaleLatitude);
/*  60 */     if (this.spherical) {
/*     */       double X;
/*  63 */       switch (this.mode) {
/*     */         case 1:
/*     */         case 2:
/*  66 */           if (Math.abs(this.trueScaleLatitude - 1.5707963267948966D) < 1.0E-10D) {
/*  67 */             this.akm1 = 2.0D * this.scaleFactor / Math.sqrt(Math.pow(1.0D + this.e, 1.0D + this.e) * Math.pow(1.0D - this.e, 1.0D - this.e));
/*     */             break;
/*     */           } 
/*  70 */           this.akm1 = Math.cos(this.trueScaleLatitude) / MapMath.tsfn(this.trueScaleLatitude, t = Math.sin(this.trueScaleLatitude), this.e);
/*  72 */           t *= this.e;
/*  73 */           this.akm1 /= Math.sqrt(1.0D - t * t);
/*     */         case 3:
/*  77 */           this.akm1 = 2.0D * this.scaleFactor;
/*     */           break;
/*     */         case 4:
/*  80 */           t = Math.sin(this.projectionLatitude);
/*  81 */           X = 2.0D * Math.atan(ssfn(this.projectionLatitude, t, this.e)) - 1.5707963267948966D;
/*  82 */           t *= this.e;
/*  83 */           this.akm1 = 2.0D * this.scaleFactor * Math.cos(this.projectionLatitude) / Math.sqrt(1.0D - t * t);
/*  84 */           this.sinphi0 = Math.sin(X);
/*  85 */           this.cosphi0 = Math.cos(X);
/*     */           break;
/*     */       } 
/*     */     } else {
/*  89 */       switch (this.mode) {
/*     */         case 4:
/*  91 */           this.sinphi0 = Math.sin(this.projectionLatitude);
/*  92 */           this.cosphi0 = Math.cos(this.projectionLatitude);
/*     */         case 3:
/*  94 */           this.akm1 = 2.0D * this.scaleFactor;
/*     */           break;
/*     */         case 1:
/*     */         case 2:
/*  98 */           this.akm1 = (Math.abs(this.trueScaleLatitude - 1.5707963267948966D) >= 1.0E-10D) ? (Math.cos(this.trueScaleLatitude) / Math.tan(0.7853981633974483D - 0.5D * this.trueScaleLatitude)) : (2.0D * this.scaleFactor);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/* 107 */     double coslam = Math.cos(lam);
/* 108 */     double sinlam = Math.sin(lam);
/* 109 */     double sinphi = Math.sin(phi);
/* 111 */     if (this.spherical) {
/* 112 */       double cosphi = Math.cos(phi);
/* 114 */       switch (this.mode) {
/*     */         case 3:
/* 116 */           xy.y = 1.0D + cosphi * coslam;
/* 117 */           if (xy.y <= 1.0E-10D)
/* 118 */             throw new ProjectionException(); 
/* 119 */           xy.x = (xy.y = this.akm1 / xy.y) * cosphi * sinlam;
/* 120 */           xy.y *= sinphi;
/*     */         case 4:
/* 123 */           xy.y = 1.0D + this.sinphi0 * sinphi + this.cosphi0 * cosphi * coslam;
/* 124 */           if (xy.y <= 1.0E-10D)
/* 125 */             throw new ProjectionException(); 
/* 126 */           xy.x = (xy.y = this.akm1 / xy.y) * cosphi * sinlam;
/* 127 */           xy.y *= this.cosphi0 * sinphi - this.sinphi0 * cosphi * coslam;
/*     */         case 1:
/* 130 */           coslam = -coslam;
/* 131 */           phi = -phi;
/*     */         case 2:
/* 133 */           if (Math.abs(phi - 1.5707963267948966D) < 1.0E-8D)
/* 134 */             throw new ProjectionException(); 
/* 135 */           xy.x = sinlam * (xy.y = this.akm1 * Math.tan(0.7853981633974483D + 0.5D * phi));
/* 136 */           xy.y *= coslam;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 140 */       double A, sinX = 0.0D, cosX = 0.0D;
/* 142 */       if (this.mode == 4 || this.mode == 3) {
/*     */         double X;
/* 143 */         sinX = Math.sin(X = 2.0D * Math.atan(ssfn(phi, sinphi, this.e)) - 1.5707963267948966D);
/* 144 */         cosX = Math.cos(X);
/*     */       } 
/* 146 */       switch (this.mode) {
/*     */         case 4:
/* 148 */           A = this.akm1 / this.cosphi0 * (1.0D + this.sinphi0 * sinX + this.cosphi0 * cosX * coslam);
/* 149 */           xy.y = A * (this.cosphi0 * sinX - this.sinphi0 * cosX * coslam);
/* 150 */           xy.x = A * cosX;
/*     */           break;
/*     */         case 3:
/* 153 */           A = 2.0D * this.akm1 / (1.0D + cosX * coslam);
/* 154 */           xy.y = A * sinX;
/* 155 */           xy.x = A * cosX;
/*     */           break;
/*     */         case 2:
/* 158 */           phi = -phi;
/* 159 */           coslam = -coslam;
/* 160 */           sinphi = -sinphi;
/*     */         case 1:
/* 162 */           xy.x = this.akm1 * MapMath.tsfn(phi, sinphi, this.e);
/* 163 */           xy.y = -xy.x * coslam;
/*     */           break;
/*     */       } 
/* 166 */       xy.x *= sinlam;
/*     */     } 
/* 168 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 172 */     if (this.spherical) {
/* 175 */       double c, rh, sinc = Math.sin(c = 2.0D * Math.atan((rh = MapMath.distance(x, y)) / this.akm1));
/* 176 */       double cosc = Math.cos(c);
/* 177 */       lp.x = 0.0D;
/* 178 */       switch (this.mode) {
/*     */         case 3:
/* 180 */           if (Math.abs(rh) <= 1.0E-10D) {
/* 181 */             lp.y = 0.0D;
/*     */           } else {
/* 183 */             lp.y = Math.asin(y * sinc / rh);
/*     */           } 
/* 184 */           if (cosc != 0.0D || x != 0.0D)
/* 185 */             lp.x = Math.atan2(x * sinc, cosc * rh); 
/*     */           break;
/*     */         case 4:
/* 188 */           if (Math.abs(rh) <= 1.0E-10D) {
/* 189 */             lp.y = this.projectionLatitude;
/*     */           } else {
/* 191 */             lp.y = Math.asin(cosc * this.sinphi0 + y * sinc * this.cosphi0 / rh);
/*     */           } 
/* 192 */           if ((c = cosc - this.sinphi0 * Math.sin(lp.y)) != 0.0D || x != 0.0D)
/* 193 */             lp.x = Math.atan2(x * sinc * this.cosphi0, c * rh); 
/*     */           break;
/*     */         case 1:
/* 196 */           y = -y;
/*     */         case 2:
/* 198 */           if (Math.abs(rh) <= 1.0E-10D) {
/* 199 */             lp.y = this.projectionLatitude;
/*     */           } else {
/* 201 */             lp.y = Math.asin((this.mode == 2) ? -cosc : cosc);
/*     */           } 
/* 202 */           lp.x = (x == 0.0D && y == 0.0D) ? 0.0D : Math.atan2(x, y);
/*     */           break;
/*     */       } 
/*     */     } else {
/* 208 */       double cosphi, sinphi, tp, halfe, halfpi, rho = MapMath.distance(x, y);
/* 209 */       switch (this.mode) {
/*     */         default:
/* 213 */           cosphi = Math.cos(tp = 2.0D * Math.atan2(rho * this.cosphi0, this.akm1));
/* 214 */           sinphi = Math.sin(tp);
/* 215 */           phi_l = Math.asin(cosphi * this.sinphi0 + y * sinphi * this.cosphi0 / rho);
/* 216 */           tp = Math.tan(0.5D * (1.5707963267948966D + phi_l));
/* 217 */           x *= sinphi;
/* 218 */           y = rho * this.cosphi0 * cosphi - y * this.sinphi0 * sinphi;
/* 219 */           halfpi = 1.5707963267948966D;
/* 220 */           halfe = 0.5D * this.e;
/*     */         case 1:
/* 223 */           y = -y;
/*     */         case 2:
/* 225 */           phi_l = 1.5707963267948966D - 2.0D * Math.atan(tp = -rho / this.akm1);
/* 226 */           halfpi = -1.5707963267948966D;
/* 227 */           halfe = -0.5D * this.e;
/*     */           break;
/*     */       } 
/*     */       double phi_l;
/* 230 */       for (int i = 8; i-- != 0; phi_l = lp.y) {
/* 231 */         sinphi = this.e * Math.sin(phi_l);
/* 232 */         lp.y = 2.0D * Math.atan(tp * Math.pow((1.0D + sinphi) / (1.0D - sinphi), halfe)) - halfpi;
/* 233 */         if (Math.abs(phi_l - lp.y) < 1.0E-10D) {
/* 234 */           if (this.mode == 2)
/* 235 */             lp.y = -lp.y; 
/* 236 */           lp.x = (x == 0.0D && y == 0.0D) ? 0.0D : Math.atan2(x, y);
/* 237 */           return lp;
/*     */         } 
/*     */       } 
/* 240 */       throw new RuntimeException("Iteration didn't converge");
/*     */     } 
/* 242 */     return lp;
/*     */   }
/*     */   
/*     */   public boolean isConformal() {
/* 249 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   private double ssfn(double phit, double sinphi, double eccen) {
/* 257 */     sinphi *= eccen;
/* 258 */     return Math.tan(0.5D * (1.5707963267948966D + phit)) * Math.pow((1.0D - sinphi) / (1.0D + sinphi), 0.5D * eccen);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 263 */     return "Stereographic Azimuthal";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\StereographicAzimuthalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */