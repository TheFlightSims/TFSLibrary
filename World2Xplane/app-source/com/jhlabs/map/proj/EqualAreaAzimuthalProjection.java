/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class EqualAreaAzimuthalProjection extends AzimuthalProjection {
/*     */   private double sinb1;
/*     */   
/*     */   private double cosb1;
/*     */   
/*     */   private double xmf;
/*     */   
/*     */   private double ymf;
/*     */   
/*     */   private double mmf;
/*     */   
/*     */   private double qp;
/*     */   
/*     */   private double dd;
/*     */   
/*     */   private double rq;
/*     */   
/*     */   private double[] apa;
/*     */   
/*     */   public EqualAreaAzimuthalProjection() {
/*  39 */     initialize();
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  43 */     EqualAreaAzimuthalProjection p = (EqualAreaAzimuthalProjection)super.clone();
/*  44 */     if (this.apa != null)
/*  45 */       p.apa = (double[])this.apa.clone(); 
/*  46 */     return p;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  50 */     super.initialize();
/*  51 */     if (this.spherical) {
/*  52 */       if (this.mode == 4) {
/*  53 */         this.sinphi0 = Math.sin(this.projectionLatitude);
/*  54 */         this.cosphi0 = Math.cos(this.projectionLatitude);
/*     */       } 
/*     */     } else {
/*     */       double sinphi;
/*  59 */       this.qp = MapMath.qsfn(1.0D, this.e, this.one_es);
/*  60 */       this.mmf = 0.5D / (1.0D - this.es);
/*  61 */       this.apa = MapMath.authset(this.es);
/*  62 */       switch (this.mode) {
/*     */         case 1:
/*     */         case 2:
/*  65 */           this.dd = 1.0D;
/*     */           break;
/*     */         case 3:
/*  68 */           this.dd = 1.0D / (this.rq = Math.sqrt(0.5D * this.qp));
/*  69 */           this.xmf = 1.0D;
/*  70 */           this.ymf = 0.5D * this.qp;
/*     */         case 4:
/*  73 */           this.rq = Math.sqrt(0.5D * this.qp);
/*  74 */           sinphi = Math.sin(this.projectionLatitude);
/*  75 */           this.sinb1 = MapMath.qsfn(sinphi, this.e, this.one_es) / this.qp;
/*  76 */           this.cosb1 = Math.sqrt(1.0D - this.sinb1 * this.sinb1);
/*  77 */           this.dd = Math.cos(this.projectionLatitude) / Math.sqrt(1.0D - this.es * sinphi * sinphi) * this.rq * this.cosb1;
/*  79 */           this.ymf = (this.xmf = this.rq) / this.dd;
/*  80 */           this.xmf *= this.dd;
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lam, double phi, Point2D.Double xy) {
/*  87 */     if (this.spherical) {
/*  90 */       double sinphi = Math.sin(phi);
/*  91 */       double cosphi = Math.cos(phi);
/*  92 */       double coslam = Math.cos(lam);
/*  93 */       switch (this.mode) {
/*     */         case 3:
/*  95 */           xy.y = 1.0D + cosphi * coslam;
/*  96 */           if (xy.y <= 1.0E-10D)
/*  96 */             throw new ProjectionException(); 
/*  97 */           xy.x = (xy.y = Math.sqrt(2.0D / xy.y)) * cosphi * Math.sin(lam);
/*  98 */           xy.y *= (this.mode == 3) ? sinphi : (this.cosphi0 * sinphi - this.sinphi0 * cosphi * coslam);
/*     */           break;
/*     */         case 4:
/* 102 */           xy.y = 1.0D + this.sinphi0 * sinphi + this.cosphi0 * cosphi * coslam;
/* 103 */           if (xy.y <= 1.0E-10D)
/* 103 */             throw new ProjectionException(); 
/* 104 */           xy.x = (xy.y = Math.sqrt(2.0D / xy.y)) * cosphi * Math.sin(lam);
/* 105 */           xy.y *= (this.mode == 3) ? sinphi : (this.cosphi0 * sinphi - this.sinphi0 * cosphi * coslam);
/*     */           break;
/*     */         case 1:
/* 109 */           coslam = -coslam;
/*     */         case 2:
/* 111 */           if (Math.abs(phi + this.projectionLatitude) < 1.0E-10D)
/* 111 */             throw new ProjectionException(); 
/* 112 */           xy.y = 0.7853981633974483D - phi * 0.5D;
/* 113 */           xy.y = 2.0D * ((this.mode == 2) ? Math.cos(xy.y) : Math.sin(xy.y));
/* 114 */           xy.x = xy.y * Math.sin(lam);
/* 115 */           xy.y *= coslam;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 119 */       double sinb = 0.0D, cosb = 0.0D, b = 0.0D;
/* 121 */       double coslam = Math.cos(lam);
/* 122 */       double sinlam = Math.sin(lam);
/* 123 */       double sinphi = Math.sin(phi);
/* 124 */       double q = MapMath.qsfn(sinphi, this.e, this.one_es);
/* 125 */       if (this.mode == 4 || this.mode == 3) {
/* 126 */         sinb = q / this.qp;
/* 127 */         cosb = Math.sqrt(1.0D - sinb * sinb);
/*     */       } 
/* 129 */       switch (this.mode) {
/*     */         case 4:
/* 131 */           b = 1.0D + this.sinb1 * sinb + this.cosb1 * cosb * coslam;
/*     */           break;
/*     */         case 3:
/* 134 */           b = 1.0D + cosb * coslam;
/*     */           break;
/*     */         case 1:
/* 137 */           b = 1.5707963267948966D + phi;
/* 138 */           q = this.qp - q;
/*     */           break;
/*     */         case 2:
/* 141 */           b = phi - 1.5707963267948966D;
/* 142 */           q = this.qp + q;
/*     */           break;
/*     */       } 
/* 145 */       if (Math.abs(b) < 1.0E-10D)
/* 145 */         throw new ProjectionException(); 
/* 146 */       switch (this.mode) {
/*     */         case 4:
/* 148 */           xy.y = this.ymf * (b = Math.sqrt(2.0D / b)) * (this.cosb1 * sinb - this.sinb1 * cosb * coslam);
/* 150 */           xy.x = this.xmf * b * cosb * sinlam;
/*     */         case 3:
/* 153 */           xy.y = (b = Math.sqrt(2.0D / (1.0D + cosb * coslam))) * sinb * this.ymf;
/* 154 */           xy.x = this.xmf * b * cosb * sinlam;
/*     */         case 1:
/*     */         case 2:
/* 158 */           if (q >= 0.0D) {
/* 159 */             xy.x = (b = Math.sqrt(q)) * sinlam;
/* 160 */             xy.y = coslam * ((this.mode == 2) ? b : -b);
/*     */             break;
/*     */           } 
/* 162 */           xy.x = xy.y = 0.0D;
/*     */           break;
/*     */       } 
/*     */     } 
/* 166 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 170 */     if (this.spherical) {
/* 171 */       double cosz = 0.0D, sinz = 0.0D;
/* 173 */       double rh = MapMath.distance(x, y);
/* 174 */       if ((lp.y = rh * 0.5D) > 1.0D)
/* 174 */         throw new ProjectionException(); 
/* 175 */       lp.y = 2.0D * Math.asin(lp.y);
/* 176 */       if (this.mode == 4 || this.mode == 3) {
/* 177 */         sinz = Math.sin(lp.y);
/* 178 */         cosz = Math.cos(lp.y);
/*     */       } 
/* 180 */       switch (this.mode) {
/*     */         case 3:
/* 182 */           lp.y = (Math.abs(rh) <= 1.0E-10D) ? 0.0D : Math.asin(y * sinz / rh);
/* 183 */           x *= sinz;
/* 184 */           y = cosz * rh;
/*     */           break;
/*     */         case 4:
/* 187 */           lp.y = (Math.abs(rh) <= 1.0E-10D) ? this.projectionLatitude : Math.asin(cosz * this.sinphi0 + y * sinz * this.cosphi0 / rh);
/* 189 */           x *= sinz * this.cosphi0;
/* 190 */           y = (cosz - Math.sin(lp.y) * this.sinphi0) * rh;
/*     */           break;
/*     */         case 1:
/* 193 */           y = -y;
/* 194 */           lp.y = 1.5707963267948966D - lp.y;
/*     */           break;
/*     */         case 2:
/* 197 */           lp.y -= 1.5707963267948966D;
/*     */           break;
/*     */       } 
/* 200 */       lp.x = (y == 0.0D && (this.mode == 3 || this.mode == 4)) ? 0.0D : Math.atan2(x, y);
/*     */     } else {
/* 203 */       double cCe, sCe, q, rho, ab = 0.0D;
/* 205 */       switch (this.mode) {
/*     */         case 3:
/*     */         case 4:
/* 208 */           if ((rho = MapMath.distance(x /= this.dd, y *= this.dd)) < 1.0E-10D) {
/* 209 */             lp.x = 0.0D;
/* 210 */             lp.y = this.projectionLatitude;
/* 211 */             return lp;
/*     */           } 
/* 213 */           cCe = Math.cos(sCe = 2.0D * Math.asin(0.5D * rho / this.rq));
/* 214 */           x *= sCe = Math.sin(sCe);
/* 215 */           if (this.mode == 4) {
/* 216 */             double d = this.qp * (ab = cCe * this.sinb1 + y * sCe * this.cosb1 / rho);
/* 217 */             y = rho * this.cosb1 * cCe - y * this.sinb1 * sCe;
/*     */           } 
/* 219 */           q = this.qp * (ab = y * sCe / rho);
/* 220 */           y = rho * cCe;
/*     */         case 1:
/* 224 */           y = -y;
/*     */         case 2:
/* 226 */           if ((q = x * x + y * y) == 0.0D) {
/* 227 */             lp.x = 0.0D;
/* 228 */             lp.y = this.projectionLatitude;
/* 229 */             return lp;
/*     */           } 
/* 231 */           ab = 1.0D - q / this.qp;
/* 232 */           if (this.mode == 2)
/* 233 */             ab = -ab; 
/*     */           break;
/*     */       } 
/* 236 */       lp.x = Math.atan2(x, y);
/* 237 */       lp.y = MapMath.authlat(Math.asin(ab), this.apa);
/*     */     } 
/* 239 */     return lp;
/*     */   }
/*     */   
/*     */   public Shape getBoundingShape() {
/* 243 */     double r = 1.414D * this.a;
/* 244 */     return new Ellipse2D.Double(-r, -r, 2.0D * r, 2.0D * r);
/*     */   }
/*     */   
/*     */   public boolean isEqualArea() {
/* 251 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 255 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 259 */     return "Lambert Equal Area Azimuthal";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\EqualAreaAzimuthalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */