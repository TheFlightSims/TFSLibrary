/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class BipolarProjection extends Projection {
/*     */   private boolean noskew;
/*     */   
/*     */   private static final double EPS = 1.0E-10D;
/*     */   
/*     */   private static final double EPS10 = 1.0E-10D;
/*     */   
/*     */   private static final double ONEEPS = 1.000000001D;
/*     */   
/*     */   private static final int NITER = 10;
/*     */   
/*     */   private static final double lamB = -0.3489497672625068D;
/*     */   
/*     */   private static final double n = 0.6305584488127469D;
/*     */   
/*     */   private static final double F = 1.8972474256746104D;
/*     */   
/*     */   private static final double Azab = 0.8165004367468637D;
/*     */   
/*     */   private static final double Azba = 1.8226184385618593D;
/*     */   
/*     */   private static final double T = 1.27246578267089D;
/*     */   
/*     */   private static final double rhoc = 1.2070912152156872D;
/*     */   
/*     */   private static final double cAzc = 0.6969152303867837D;
/*     */   
/*     */   private static final double sAzc = 0.7171535133114361D;
/*     */   
/*     */   private static final double C45 = 0.7071067811865476D;
/*     */   
/*     */   private static final double S45 = 0.7071067811865476D;
/*     */   
/*     */   private static final double C20 = 0.9396926207859084D;
/*     */   
/*     */   private static final double S20 = -0.3420201433256687D;
/*     */   
/*     */   private static final double R110 = 1.9198621771937625D;
/*     */   
/*     */   private static final double R104 = 1.8151424220741028D;
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*  61 */     double tphi, Az, z, Av, cphi = Math.cos(lpphi);
/*  62 */     double sphi = Math.sin(lpphi);
/*  63 */     double sdlam, cdlam = Math.cos(sdlam = -0.3489497672625068D - lplam);
/*  64 */     sdlam = Math.sin(sdlam);
/*  65 */     if (Math.abs(Math.abs(lpphi) - 1.5707963267948966D) < 1.0E-10D) {
/*  66 */       Az = (lpphi < 0.0D) ? Math.PI : 0.0D;
/*  67 */       tphi = Double.MAX_VALUE;
/*     */     } else {
/*  69 */       tphi = sphi / cphi;
/*  70 */       Az = Math.atan2(sdlam, 0.7071067811865476D * (tphi - cdlam));
/*     */     } 
/*     */     boolean tag;
/*  72 */     if (tag = (Az > 1.8226184385618593D)) {
/*  73 */       cdlam = Math.cos(sdlam = lplam + 1.9198621771937625D);
/*  74 */       sdlam = Math.sin(sdlam);
/*  75 */       z = -0.3420201433256687D * sphi + 0.9396926207859084D * cphi * cdlam;
/*  76 */       if (Math.abs(z) > 1.0D) {
/*  77 */         if (Math.abs(z) > 1.000000001D)
/*  78 */           throw new ProjectionException("F"); 
/*  79 */         z = (z < 0.0D) ? -1.0D : 1.0D;
/*     */       } else {
/*  81 */         z = Math.acos(z);
/*     */       } 
/*  82 */       if (tphi != Double.MAX_VALUE)
/*  83 */         Az = Math.atan2(sdlam, 0.9396926207859084D * tphi - -0.3420201433256687D * cdlam); 
/*  84 */       Av = 0.8165004367468637D;
/*  85 */       out.y = 1.2070912152156872D;
/*     */     } else {
/*  87 */       z = 0.7071067811865476D * (sphi + cphi * cdlam);
/*  88 */       if (Math.abs(z) > 1.0D) {
/*  89 */         if (Math.abs(z) > 1.000000001D)
/*  90 */           throw new ProjectionException("F"); 
/*  91 */         z = (z < 0.0D) ? -1.0D : 1.0D;
/*     */       } else {
/*  93 */         z = Math.acos(z);
/*     */       } 
/*  94 */       Av = 1.8226184385618593D;
/*  95 */       out.y = -1.2070912152156872D;
/*     */     } 
/*  97 */     if (z < 0.0D)
/*  97 */       throw new ProjectionException("F"); 
/*  98 */     double t, r = 1.8972474256746104D * (t = Math.pow(Math.tan(0.5D * z), 0.6305584488127469D));
/*     */     double al;
/*  99 */     if ((al = 0.5D * (1.8151424220741028D - z)) < 0.0D)
/* 100 */       throw new ProjectionException("F"); 
/* 101 */     al = (t + Math.pow(al, 0.6305584488127469D)) / 1.27246578267089D;
/* 102 */     if (Math.abs(al) > 1.0D) {
/* 103 */       if (Math.abs(al) > 1.000000001D)
/* 104 */         throw new ProjectionException("F"); 
/* 105 */       al = (al < 0.0D) ? -1.0D : 1.0D;
/*     */     } else {
/* 107 */       al = Math.acos(al);
/*     */     } 
/* 108 */     if (Math.abs(t = 0.6305584488127469D * (Av - Az)) < al)
/* 109 */       r /= Math.cos(al + (tag ? t : -t)); 
/* 110 */     out.x = r * Math.sin(t);
/* 111 */     out.y += (tag ? -r : r) * Math.cos(t);
/* 112 */     if (this.noskew) {
/* 113 */       t = out.x;
/* 114 */       out.x = -out.x * 0.6969152303867837D - out.y * 0.7171535133114361D;
/* 115 */       out.y = -out.y * 0.6969152303867837D + t * 0.7171535133114361D;
/*     */     } 
/* 117 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 121 */     double s, c, Av, z = 0.0D;
/* 125 */     if (this.noskew) {
/* 126 */       double t = xyx;
/* 127 */       out.x = -xyx * 0.6969152303867837D + xyy * 0.7171535133114361D;
/* 128 */       out.y = -xyy * 0.6969152303867837D - t * 0.7171535133114361D;
/*     */     } 
/*     */     boolean neg;
/* 130 */     if (neg = (xyx < 0.0D)) {
/* 131 */       out.y = 1.2070912152156872D - xyy;
/* 132 */       s = -0.3420201433256687D;
/* 133 */       c = 0.9396926207859084D;
/* 134 */       Av = 0.8165004367468637D;
/*     */     } else {
/* 136 */       out.y += 1.2070912152156872D;
/* 137 */       s = 0.7071067811865476D;
/* 138 */       c = 0.7071067811865476D;
/* 139 */       Av = 1.8226184385618593D;
/*     */     } 
/* 141 */     double r = MapMath.distance(xyx, xyy), rp = r, rl = rp;
/* 142 */     double Az, fAz = Math.abs(Az = Math.atan2(xyx, xyy));
/*     */     int i;
/* 143 */     for (i = 10; i > 0; i--) {
/* 144 */       z = 2.0D * Math.atan(Math.pow(r / 1.8972474256746104D, 1.585895806935677D));
/* 145 */       double al = Math.acos((Math.pow(Math.tan(0.5D * z), 0.6305584488127469D) + Math.pow(Math.tan(0.5D * (1.8151424220741028D - z)), 0.6305584488127469D)) / 1.27246578267089D);
/* 147 */       if (fAz < al)
/* 148 */         r = rp * Math.cos(al + (neg ? Az : -Az)); 
/* 149 */       if (Math.abs(rl - r) < 1.0E-10D)
/*     */         break; 
/* 151 */       rl = r;
/*     */     } 
/* 153 */     if (i == 0)
/* 153 */       throw new ProjectionException("I"); 
/* 154 */     Az = Av - Az / 0.6305584488127469D;
/* 155 */     out.y = Math.asin(s * Math.cos(z) + c * Math.sin(z) * Math.cos(Az));
/* 156 */     out.x = Math.atan2(Math.sin(Az), c / Math.tan(z) - s * Math.cos(Az));
/* 157 */     if (neg) {
/* 158 */       out.x -= 1.9198621771937625D;
/*     */     } else {
/* 160 */       out.x = -0.3489497672625068D - out.x;
/*     */     } 
/* 161 */     return out;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 165 */     return true;
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 169 */     super.initialize();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 174 */     return "Bipolar Conic of Western Hemisphere";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\BipolarProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */