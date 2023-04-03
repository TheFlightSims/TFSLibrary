/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class RobinsonProjection extends PseudoCylindricalProjection {
/*  28 */   private static final double[] X = new double[] { 
/*  28 */       1.0D, -5.67239E-12D, -7.15511E-5D, 3.11028E-6D, 0.9986D, -4.82241E-4D, -2.4897E-5D, -1.33094E-6D, 0.9954D, -8.31031E-4D, 
/*  28 */       -4.4861E-5D, -9.86588E-7D, 0.99D, -0.00135363D, -5.96598E-5D, 3.67749E-6D, 0.9822D, -0.00167442D, -4.4975E-6D, -5.72394E-6D, 
/*  28 */       0.973D, -0.00214869D, -9.03565E-5D, 1.88767E-8D, 0.96D, -0.00305084D, -9.00732E-5D, 1.64869E-6D, 0.9427D, -0.00382792D, 
/*  28 */       -6.53428E-5D, -2.61493E-6D, 0.9216D, -0.00467747D, -1.04566E-4D, 4.8122E-6D, 0.8962D, -0.00536222D, -3.23834E-5D, -5.43445E-6D, 
/*  28 */       0.8679D, -0.00609364D, -1.139E-4D, 3.32521E-6D, 0.835D, -0.00698325D, -6.40219E-5D, 9.34582E-7D, 0.7986D, -0.00755337D, 
/*  28 */       -5.00038E-5D, 9.35532E-7D, 0.7597D, -0.00798325D, -3.59716E-5D, -2.27604E-6D, 0.7186D, -0.00851366D, -7.0112E-5D, -8.63072E-6D, 
/*  28 */       0.6732D, -0.00986209D, -1.99572E-4D, 1.91978E-5D, 0.6213D, -0.010418D, 8.83948E-5D, 6.24031E-6D, 0.5722D, -0.00906601D, 
/*  28 */       1.81999E-4D, 6.24033E-6D, 0.5322D, 0.0D, 0.0D, 0.0D };
/*     */   
/*  50 */   private static final double[] Y = new double[] { 
/*  50 */       0.0D, 0.0124D, 3.72529E-10D, 1.15484E-9D, 0.062D, 0.0124001D, 1.76951E-8D, -5.92321E-9D, 0.124D, 0.0123998D, 
/*  50 */       -7.09668E-8D, 2.25753E-8D, 0.186D, 0.0124008D, 2.66917E-7D, -8.44523E-8D, 0.248D, 0.0123971D, -9.99682E-7D, 3.15569E-7D, 
/*  50 */       0.31D, 0.0124108D, 3.73349E-6D, -1.1779E-6D, 0.372D, 0.0123598D, -1.3935E-5D, 4.39588E-6D, 0.434D, 0.0125501D, 
/*  50 */       5.20034E-5D, -1.00051E-5D, 0.4968D, 0.0123198D, -9.80735E-5D, 9.22397E-6D, 0.5571D, 0.0120308D, 4.02857E-5D, -5.2901E-6D, 
/*  50 */       0.6176D, 0.0120369D, -3.90662E-5D, 7.36117E-7D, 0.6769D, 0.0117015D, -2.80246E-5D, -8.54283E-7D, 0.7346D, 0.0113572D, 
/*  50 */       -4.08389E-5D, -5.18524E-7D, 0.7903D, 0.0109099D, -4.86169E-5D, -1.0718E-6D, 0.8435D, 0.0103433D, -6.46934E-5D, 5.36384E-9D, 
/*  50 */       0.8936D, 0.00969679D, -6.46129E-5D, -8.54894E-6D, 0.9394D, 0.00840949D, -1.92847E-4D, -4.21023E-6D, 0.9761D, 0.00616525D, 
/*  50 */       -2.56001E-4D, -4.21021E-6D, 1.0D, 0.0D, 0.0D, 0.0D };
/*     */   
/*  72 */   private final int NODES = 18;
/*     */   
/*     */   private static final double FXC = 0.8487D;
/*     */   
/*     */   private static final double FYC = 1.3523D;
/*     */   
/*     */   private static final double C1 = 11.459155902616464D;
/*     */   
/*     */   private static final double RC1 = 0.08726646259971647D;
/*     */   
/*     */   private static final double ONEEPS = 1.000001D;
/*     */   
/*     */   private static final double EPS = 1.0E-8D;
/*     */   
/*     */   private double poly(double[] array, int offset, double z) {
/*  84 */     return array[offset] + z * (array[offset + 1] + z * (array[offset + 2] + z * array[offset + 3]));
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/*  88 */     double phi = Math.abs(lpphi);
/*  89 */     int i = (int)Math.floor(phi * 11.459155902616464D);
/*  90 */     if (i >= 18)
/*  91 */       i = 17; 
/*  92 */     phi = Math.toDegrees(phi - 0.08726646259971647D * i);
/*  93 */     i *= 4;
/*  94 */     xy.x = poly(X, i, phi) * 0.8487D * lplam;
/*  95 */     xy.y = poly(Y, i, phi) * 1.3523D;
/*  96 */     if (lpphi < 0.0D)
/*  97 */       xy.y = -xy.y; 
/*  98 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 105 */     lp.x = x / 0.8487D;
/* 106 */     lp.y = Math.abs(y / 1.3523D);
/* 107 */     if (lp.y >= 1.0D) {
/* 108 */       if (lp.y > 1.000001D)
/* 109 */         throw new ProjectionException(); 
/* 111 */       lp.y = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/* 112 */       lp.x /= X[72];
/*     */     } else {
/*     */       double t1;
/* 115 */       int i = 4 * (int)Math.floor(lp.y * 18.0D);
/*     */       while (true) {
/* 116 */         while (Y[i] > lp.y)
/* 117 */           i -= 4; 
/* 118 */         if (Y[i + 4] <= lp.y) {
/* 119 */           i += 4;
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 123 */       double t = 5.0D * (lp.y - Y[i]) / (Y[i + 4] - Y[i]);
/* 124 */       double Tc0 = Y[i];
/* 125 */       double Tc1 = Y[i + 1];
/* 126 */       double Tc2 = Y[i + 2];
/* 127 */       double Tc3 = Y[i + 3];
/* 128 */       t = 5.0D * (lp.y - Tc0) / (Y[i + 1] - Tc0);
/* 129 */       Tc0 -= lp.y;
/*     */       do {
/* 131 */         t -= t1 = (Tc0 + t * (Tc1 + t * (Tc2 + t * Tc3))) / (Tc1 + t * (Tc2 + Tc2 + t * 3.0D * Tc3));
/* 132 */       } while (Math.abs(t1) >= 1.0E-8D);
/* 135 */       lp.y = Math.toRadians((5 * i) + t);
/* 136 */       if (y < 0.0D)
/* 137 */         lp.y = -lp.y; 
/* 138 */       lp.x /= poly(X, i, t);
/*     */     } 
/* 140 */     return lp;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 144 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 148 */     return "Robinson";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\RobinsonProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */