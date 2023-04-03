/*     */ package org.geotools.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.geotools.resources.Classes;
/*     */ 
/*     */ public class Polynom implements Serializable {
/*     */   private static final long serialVersionUID = 6825019711186108990L;
/*     */   
/*  53 */   private static final double[] NO_REAL_ROOT = new double[0];
/*     */   
/*     */   private final double[] c;
/*     */   
/*     */   private transient double[] roots;
/*     */   
/*     */   public Polynom(double[] c) {
/*  71 */     int n = c.length;
/*  72 */     while (n != 0 && c[--n] == 0.0D);
/*  75 */     if (n == 0) {
/*  76 */       this.c = NO_REAL_ROOT;
/*     */     } else {
/*  78 */       this.c = new double[n];
/*  79 */       System.arraycopy(c, 0, this.c, 0, n);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final double y(double x) {
/*  93 */     double sum = 0.0D;
/*  94 */     for (int i = this.c.length; --i >= 0;)
/*  95 */       sum = sum * x + this.c[i]; 
/*  97 */     return sum;
/*     */   }
/*     */   
/*     */   private static double[] quadraticRoots(double c0, double c1, double c2) {
/* 113 */     double d = c1 * c1 - 4.0D * c2 * c0;
/* 114 */     if (d > 0.0D) {
/* 116 */       d = Math.sqrt(d);
/* 117 */       if (c1 < 0.0D)
/* 118 */         d = -d; 
/* 120 */       double q = 0.5D * (d - c1);
/* 121 */       return new double[] { q / c2, (q != 0.0D) ? (c0 / q) : (-0.5D * (d + c1) / c2) };
/*     */     } 
/* 125 */     if (d == 0.0D)
/* 127 */       return new double[] { -c1 / 2.0D * c2 }; 
/* 132 */     return NO_REAL_ROOT;
/*     */   }
/*     */   
/*     */   private static double[] cubicRoots(double c0, double c1, double c2, double c3) {
/* 150 */     c2 /= c3;
/* 151 */     c1 /= c3;
/* 152 */     c0 /= c3;
/* 153 */     double Q = (c2 * c2 - 3.0D * c1) / 9.0D;
/* 154 */     double R = (2.0D * c2 * c2 * c2 - 9.0D * c2 * c1 + 27.0D * c0) / 54.0D;
/* 155 */     double Qcubed = Q * Q * Q;
/* 156 */     double d = Qcubed - R * R;
/* 158 */     c2 /= 3.0D;
/* 159 */     if (d >= 0.0D) {
/* 160 */       double theta = Math.acos(R / Math.sqrt(Qcubed)) / 3.0D;
/* 161 */       double scale = -2.0D * Math.sqrt(Q);
/* 162 */       double[] roots = { scale * Math.cos(theta) - c2, scale * Math.cos(theta + 2.0943951023931953D) - c2, scale * Math.cos(theta + 4.1887902047863905D) - c2 };
/* 167 */       assert Math.abs(roots[0] * roots[1] * roots[2] + c0) < 1.0E-6D;
/* 168 */       assert Math.abs(roots[0] + roots[1] + roots[2] + c2 * 3.0D) < 1.0E-6D;
/* 169 */       assert Math.abs(roots[0] * roots[1] + roots[0] * roots[2] + roots[1] * roots[2] - c1) < 1.0E-6D;
/* 172 */       return roots;
/*     */     } 
/* 174 */     double e = Math.cbrt(Math.sqrt(-d) + Math.abs(R));
/* 175 */     if (R > 0.0D)
/* 176 */       e = -e; 
/* 178 */     return new double[] { e + Q / e - c2 };
/*     */   }
/*     */   
/*     */   public double[] roots() {
/* 190 */     if (this.roots == null)
/* 191 */       this.roots = roots(this.c); 
/* 193 */     return (double[])this.roots.clone();
/*     */   }
/*     */   
/*     */   public static double[] roots(double[] c) {
/* 217 */     int n = c.length;
/* 218 */     while (n != 0 && c[--n] == 0.0D);
/* 221 */     switch (n) {
/*     */       case 0:
/* 222 */         return NO_REAL_ROOT;
/*     */       case 1:
/* 223 */         return new double[] { -c[0] / c[1] };
/*     */       case 2:
/* 224 */         return quadraticRoots(c[0], c[1], c[2]);
/*     */       case 3:
/* 225 */         return cubicRoots(c[0], c[1], c[2], c[3]);
/*     */     } 
/* 226 */     throw new UnsupportedOperationException(String.valueOf(n));
/*     */   }
/*     */   
/*     */   public static void main(String[] c) {
/* 248 */     double[] r = new double[c.length];
/* 249 */     for (int i = 0; i < c.length; i++)
/* 250 */       r[i] = Double.parseDouble(c[i]); 
/* 252 */     double[] roots = roots(r);
/* 253 */     for (int j = 0; j < roots.length; j++)
/* 254 */       System.out.println(roots[j]); 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 263 */     long code = this.c.length;
/* 264 */     for (int i = this.c.length; --i >= 0;)
/* 265 */       code = code * 37L + Double.doubleToLongBits(this.c[i]); 
/* 267 */     return (int)code ^ (int)(code >>> 32L);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 275 */     if (object != null && object.getClass().equals(getClass())) {
/* 276 */       Polynom that = (Polynom)object;
/* 277 */       return Arrays.equals(this.c, that.c);
/*     */     } 
/* 279 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 287 */     StringBuilder buffer = new StringBuilder(Classes.getShortClassName(this));
/* 288 */     buffer.append('[');
/* 289 */     for (int i = 0; i < this.c.length; i++) {
/* 290 */       if (i != 0)
/* 291 */         buffer.append(", "); 
/* 293 */       buffer.append(this.c[i]);
/*     */     } 
/* 295 */     buffer.append(']');
/* 296 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\math\Polynom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */