/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class VanDerGrintenProjection extends Projection {
/*     */   private static final double TOL = 1.0E-10D;
/*     */   
/*     */   private static final double THIRD = 0.3333333333333333D;
/*     */   
/*     */   private static final double TWO_THRD = 0.6666666666666666D;
/*     */   
/*     */   private static final double C2_27 = 0.07407407407407407D;
/*     */   
/*     */   private static final double PI4_3 = 4.188790204786391D;
/*     */   
/*     */   private static final double PISQ = 9.869604401089358D;
/*     */   
/*     */   private static final double TPISQ = 19.739208802178716D;
/*     */   
/*     */   private static final double HPISQ = 4.934802200544679D;
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*  39 */     double p2 = Math.abs(lpphi / 1.5707963267948966D);
/*  40 */     if (p2 - 1.0E-10D > 1.0D)
/*  40 */       throw new ProjectionException("F"); 
/*  41 */     if (p2 > 1.0D)
/*  42 */       p2 = 1.0D; 
/*  43 */     if (Math.abs(lpphi) <= 1.0E-10D) {
/*  44 */       out.x = lplam;
/*  45 */       out.y = 0.0D;
/*  46 */     } else if (Math.abs(lplam) <= 1.0E-10D || Math.abs(p2 - 1.0D) < 1.0E-10D) {
/*  47 */       out.x = 0.0D;
/*  48 */       out.y = Math.PI * Math.tan(0.5D * Math.asin(p2));
/*  49 */       if (lpphi < 0.0D)
/*  49 */         out.y = -out.y; 
/*     */     } else {
/*  51 */       double al = 0.5D * Math.abs(Math.PI / lplam - lplam / Math.PI);
/*  52 */       double al2 = al * al;
/*  53 */       double g = Math.sqrt(1.0D - p2 * p2);
/*  54 */       g /= p2 + g - 1.0D;
/*  55 */       double g2 = g * g;
/*  56 */       p2 = g * (2.0D / p2 - 1.0D);
/*  57 */       p2 *= p2;
/*  58 */       out.x = g - p2;
/*  58 */       g = p2 + al2;
/*  59 */       out.x = Math.PI * (al * out.x + Math.sqrt(al2 * out.x * out.x - g * (g2 - p2))) / g;
/*  60 */       if (lplam < 0.0D)
/*  60 */         out.x = -out.x; 
/*  61 */       out.y = Math.abs(out.x / Math.PI);
/*  62 */       out.y = 1.0D - out.y * (out.y + 2.0D * al);
/*  63 */       if (out.y < -1.0E-10D)
/*  63 */         throw new ProjectionException("F"); 
/*  64 */       if (out.y < 0.0D) {
/*  65 */         out.y = 0.0D;
/*     */       } else {
/*  67 */         out.y = Math.sqrt(out.y) * ((lpphi < 0.0D) ? -3.141592653589793D : Math.PI);
/*     */       } 
/*     */     } 
/*  69 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/*  75 */     double x2 = xyx * xyx;
/*     */     double ay;
/*  76 */     if ((ay = Math.abs(xyy)) < 1.0E-10D) {
/*  77 */       out.y = 0.0D;
/*  78 */       double d1 = x2 * x2 + 19.739208802178716D * (x2 + 4.934802200544679D);
/*  79 */       out.x = (Math.abs(xyx) <= 1.0E-10D) ? 0.0D : (0.5D * (x2 - 9.869604401089358D + Math.sqrt(d1)) / xyx);
/*  81 */       return out;
/*     */     } 
/*  83 */     double y2 = xyy * xyy;
/*  84 */     double r = x2 + y2, r2 = r * r;
/*  85 */     double c1 = -3.141592653589793D * ay * (r + 9.869604401089358D);
/*  86 */     double c3 = r2 + 6.283185307179586D * (ay * r + Math.PI * (y2 + Math.PI * (ay + 1.5707963267948966D)));
/*  87 */     double c2 = c1 + 9.869604401089358D * (r - 3.0D * y2);
/*  88 */     double c0 = Math.PI * ay;
/*  89 */     c2 /= c3;
/*  90 */     double al = c1 / c3 - 0.3333333333333333D * c2 * c2;
/*  91 */     double m = 2.0D * Math.sqrt(-0.3333333333333333D * al);
/*  92 */     double d = 0.07407407407407407D * c2 * c2 * c2 + (c0 * c0 - 0.3333333333333333D * c2 * c1) / c3;
/*     */     double t;
/*  93 */     if ((t = Math.abs(d = 3.0D * d / al * m)) - 1.0E-10D <= 1.0D) {
/*  94 */       d = (t > 1.0D) ? ((d > 0.0D) ? 0.0D : Math.PI) : Math.acos(d);
/*  95 */       out.y = Math.PI * (m * Math.cos(d * 0.3333333333333333D + 4.188790204786391D) - 0.3333333333333333D * c2);
/*  96 */       if (xyy < 0.0D)
/*  96 */         out.y = -out.y; 
/*  97 */       t = r2 + 19.739208802178716D * (x2 - y2 + 4.934802200544679D);
/*  98 */       out.x = (Math.abs(xyx) <= 1.0E-10D) ? 0.0D : (0.5D * (r - 9.869604401089358D + ((t <= 0.0D) ? 0.0D : Math.sqrt(t))) / xyx);
/*     */     } else {
/* 101 */       throw new ProjectionException("I");
/*     */     } 
/* 102 */     return out;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 110 */     return "van der Grinten (I)";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\VanDerGrintenProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */