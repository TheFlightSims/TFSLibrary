/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class EquidistantAzimuthalProjection extends AzimuthalProjection {
/*     */   private static final double TOL = 1.0E-8D;
/*     */   
/*     */   private int mode;
/*     */   
/*     */   private double[] en;
/*     */   
/*     */   private double M1;
/*     */   
/*     */   private double N1;
/*     */   
/*     */   private double Mp;
/*     */   
/*     */   private double He;
/*     */   
/*     */   private double G;
/*     */   
/*     */   private double sinphi0;
/*     */   
/*     */   private double cosphi0;
/*     */   
/*     */   public EquidistantAzimuthalProjection() {
/*  40 */     this(Math.toRadians(90.0D), Math.toRadians(0.0D));
/*     */   }
/*     */   
/*     */   public EquidistantAzimuthalProjection(double projectionLatitude, double projectionLongitude) {
/*  44 */     super(projectionLatitude, projectionLongitude);
/*  45 */     initialize();
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  49 */     EquidistantAzimuthalProjection p = (EquidistantAzimuthalProjection)super.clone();
/*  50 */     if (this.en != null)
/*  51 */       p.en = (double[])this.en.clone(); 
/*  52 */     return p;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  56 */     super.initialize();
/*  57 */     if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966D) < 1.0E-10D) {
/*  58 */       this.mode = (this.projectionLatitude < 0.0D) ? 2 : 1;
/*  59 */       this.sinphi0 = (this.projectionLatitude < 0.0D) ? -1.0D : 1.0D;
/*  60 */       this.cosphi0 = 0.0D;
/*  61 */     } else if (Math.abs(this.projectionLatitude) < 1.0E-10D) {
/*  62 */       this.mode = 3;
/*  63 */       this.sinphi0 = 0.0D;
/*  64 */       this.cosphi0 = 1.0D;
/*     */     } else {
/*  66 */       this.mode = 4;
/*  67 */       this.sinphi0 = Math.sin(this.projectionLatitude);
/*  68 */       this.cosphi0 = Math.cos(this.projectionLatitude);
/*     */     } 
/*  70 */     if (!this.spherical) {
/*  71 */       this.en = MapMath.enfn(this.es);
/*  72 */       switch (this.mode) {
/*     */         case 1:
/*  74 */           this.Mp = MapMath.mlfn(1.5707963267948966D, 1.0D, 0.0D, this.en);
/*     */           break;
/*     */         case 2:
/*  77 */           this.Mp = MapMath.mlfn(-1.5707963267948966D, -1.0D, 0.0D, this.en);
/*     */           break;
/*     */         case 3:
/*     */         case 4:
/*  81 */           this.N1 = 1.0D / Math.sqrt(1.0D - this.es * this.sinphi0 * this.sinphi0);
/*  82 */           this.G = this.sinphi0 * (this.He = this.e / Math.sqrt(this.one_es));
/*  83 */           this.He *= this.cosphi0;
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/*  90 */     if (this.spherical) {
/*  93 */       double sinphi = Math.sin(phi);
/*  94 */       double cosphi = Math.cos(phi);
/*  95 */       double coslam = Math.cos(lam);
/*  96 */       switch (this.mode) {
/*     */         case 3:
/*     */         case 4:
/*  99 */           if (this.mode == 3) {
/* 100 */             xy.y = cosphi * coslam;
/*     */           } else {
/* 102 */             xy.y = this.sinphi0 * sinphi + this.cosphi0 * cosphi * coslam;
/*     */           } 
/* 103 */           if (Math.abs(Math.abs(xy.y) - 1.0D) < 1.0E-8D) {
/* 104 */             if (xy.y < 0.0D)
/* 105 */               throw new ProjectionException(); 
/* 107 */             xy.x = xy.y = 0.0D;
/*     */           } 
/* 109 */           xy.y = Math.acos(xy.y);
/* 110 */           xy.y /= Math.sin(xy.y);
/* 111 */           xy.x = xy.y * cosphi * Math.sin(lam);
/* 112 */           xy.y *= (this.mode == 3) ? sinphi : (this.cosphi0 * sinphi - this.sinphi0 * cosphi * coslam);
/*     */           break;
/*     */         case 1:
/* 117 */           phi = -phi;
/* 118 */           coslam = -coslam;
/*     */         case 2:
/* 120 */           if (Math.abs(phi - 1.5707963267948966D) < 1.0E-10D)
/* 121 */             throw new ProjectionException(); 
/* 122 */           xy.x = (xy.y = 1.5707963267948966D + phi) * Math.sin(lam);
/* 123 */           xy.y *= coslam;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 129 */       double rho, s, H, H2, c, Az, t, ct, st, cA, sA, coslam = Math.cos(lam);
/* 130 */       double cosphi = Math.cos(phi);
/* 131 */       double sinphi = Math.sin(phi);
/* 132 */       switch (this.mode) {
/*     */         case 1:
/* 134 */           coslam = -coslam;
/*     */         case 2:
/* 136 */           xy.x = (rho = Math.abs(this.Mp - MapMath.mlfn(phi, sinphi, cosphi, this.en))) * Math.sin(lam);
/* 138 */           xy.y = rho * coslam;
/*     */         case 3:
/*     */         case 4:
/* 142 */           if (Math.abs(lam) < 1.0E-10D && Math.abs(phi - this.projectionLatitude) < 1.0E-10D)
/* 143 */             xy.x = xy.y = 0.0D; 
/* 146 */           t = Math.atan2(this.one_es * sinphi + this.es * this.N1 * this.sinphi0 * Math.sqrt(1.0D - this.es * sinphi * sinphi), cosphi);
/* 148 */           ct = Math.cos(t);
/* 148 */           st = Math.sin(t);
/* 149 */           Az = Math.atan2(Math.sin(lam) * ct, this.cosphi0 * st - this.sinphi0 * coslam * ct);
/* 150 */           cA = Math.cos(Az);
/* 150 */           sA = Math.sin(Az);
/* 151 */           s = MapMath.asin((Math.abs(sA) < 1.0E-8D) ? ((this.cosphi0 * st - this.sinphi0 * coslam * ct) / cA) : (Math.sin(lam) * ct / sA));
/* 154 */           H = this.He * cA;
/* 155 */           H2 = H * H;
/* 156 */           c = this.N1 * s * (1.0D + s * s * (-H2 * (1.0D - H2) / 6.0D + s * (this.G * H * (1.0D - 2.0D * H2 * H2) / 8.0D + s * ((H2 * (4.0D - 7.0D * H2) - 3.0D * this.G * this.G * (1.0D - 7.0D * H2)) / 120.0D - s * this.G * H / 48.0D))));
/* 160 */           xy.x = c * sA;
/* 161 */           xy.y = c * cA;
/*     */           break;
/*     */       } 
/*     */     } 
/* 165 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 169 */     if (this.spherical) {
/*     */       double c_rh;
/* 172 */       if ((c_rh = MapMath.distance(x, y)) > Math.PI) {
/* 173 */         if (c_rh - 1.0E-10D > Math.PI)
/* 174 */           throw new ProjectionException(); 
/* 175 */         c_rh = Math.PI;
/* 176 */       } else if (c_rh < 1.0E-10D) {
/* 177 */         lp.y = this.projectionLatitude;
/* 178 */         lp.x = 0.0D;
/* 179 */         return lp;
/*     */       } 
/* 181 */       if (this.mode == 4 || this.mode == 3) {
/* 182 */         double sinc = Math.sin(c_rh);
/* 183 */         double cosc = Math.cos(c_rh);
/* 184 */         if (this.mode == 3) {
/* 185 */           lp.y = MapMath.asin(y * sinc / c_rh);
/* 186 */           x *= sinc;
/* 187 */           y = cosc * c_rh;
/*     */         } else {
/* 189 */           lp.y = MapMath.asin(cosc * this.sinphi0 + y * sinc * this.cosphi0 / c_rh);
/* 191 */           y = (cosc - this.sinphi0 * Math.sin(lp.y)) * c_rh;
/* 192 */           x *= sinc * this.cosphi0;
/*     */         } 
/* 194 */         lp.x = (y == 0.0D) ? 0.0D : Math.atan2(x, y);
/* 195 */       } else if (this.mode == 1) {
/* 196 */         lp.y = 1.5707963267948966D - c_rh;
/* 197 */         lp.x = Math.atan2(x, -y);
/*     */       } else {
/* 199 */         lp.y = c_rh - 1.5707963267948966D;
/* 200 */         lp.x = Math.atan2(x, y);
/*     */       } 
/*     */     } else {
/*     */       double c;
/* 206 */       if ((c = MapMath.distance(x, y)) < 1.0E-10D) {
/* 207 */         lp.y = this.projectionLatitude;
/* 208 */         lp.x = 0.0D;
/* 209 */         return lp;
/*     */       } 
/* 211 */       if (this.mode == 4 || this.mode == 3) {
/* 212 */         double Az, cosAz = Math.cos(Az = Math.atan2(x, y));
/* 213 */         double t = this.cosphi0 * cosAz;
/* 214 */         double B = this.es * t / this.one_es;
/* 215 */         double A = -B * t;
/* 216 */         B *= 3.0D * (1.0D - A) * this.sinphi0;
/* 217 */         double D = c / this.N1;
/* 218 */         double E = D * (1.0D - D * D * (A * (1.0D + A) / 6.0D + B * (1.0D + 3.0D * A) * D / 24.0D));
/* 219 */         double F = 1.0D - E * E * (A / 2.0D + B * E / 6.0D);
/* 220 */         double psi = MapMath.asin(this.sinphi0 * Math.cos(E) + t * Math.sin(E));
/* 221 */         lp.x = MapMath.asin(Math.sin(Az) * Math.sin(E) / Math.cos(psi));
/* 222 */         if ((t = Math.abs(psi)) < 1.0E-10D) {
/* 223 */           lp.y = 0.0D;
/* 224 */         } else if (Math.abs(t - 1.5707963267948966D) < 0.0D) {
/* 225 */           lp.y = 1.5707963267948966D;
/*     */         } else {
/* 227 */           lp.y = Math.atan((1.0D - this.es * F * this.sinphi0 / Math.sin(psi)) * Math.tan(psi) / this.one_es);
/*     */         } 
/*     */       } else {
/* 229 */         lp.y = MapMath.inv_mlfn((this.mode == 1) ? (this.Mp - c) : (this.Mp + c), this.es, this.en);
/* 230 */         lp.x = Math.atan2(x, (this.mode == 1) ? -y : y);
/*     */       } 
/*     */     } 
/* 233 */     return lp;
/*     */   }
/*     */   
/*     */   public Shape getBoundingShape() {
/* 237 */     double r = 1.5707963267948966D * this.a;
/* 238 */     return new Ellipse2D.Double(-r, -r, 2.0D * r, 2.0D * r);
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 242 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 246 */     return "Equidistant Azimuthal";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\EquidistantAzimuthalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */