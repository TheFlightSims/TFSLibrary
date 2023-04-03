/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ public class Rational {
/*     */   public long num;
/*     */   
/*     */   public long denom;
/*     */   
/*     */   private static final int MAX_TERMS = 20;
/*     */   
/*     */   public Rational(long num, long denom) {
/*  25 */     this.num = num;
/*  26 */     this.denom = denom;
/*     */   }
/*     */   
/*     */   public Rational(Rational r) {
/*  30 */     this.num = r.num;
/*  31 */     this.denom = r.denom;
/*     */   }
/*     */   
/*     */   public static Rational createFromFrac(long[] terms, int len) {
/*  45 */     Rational r = new Rational(0L, 1L);
/*  46 */     for (int i = len - 1; i >= 0; i--) {
/*  47 */       r.add(terms[i]);
/*  48 */       if (i != 0)
/*  49 */         r.invert(); 
/*     */     } 
/*  53 */     return r;
/*     */   }
/*     */   
/*     */   public static Rational approximate(float f, float tol) {
/*  65 */     float rem = f;
/*  66 */     long[] d = new long[20];
/*  67 */     int index = 0;
/*  68 */     for (int i = 0; i < 20; i++) {
/*  69 */       int k = (int)Math.floor(rem);
/*  70 */       d[index++] = k;
/*  72 */       rem -= k;
/*  73 */       if (rem == 0.0F)
/*     */         break; 
/*  76 */       rem = 1.0F / rem;
/*     */     } 
/*  81 */     Rational r = null;
/*  82 */     for (int j = 1; j <= index; j++) {
/*  83 */       r = createFromFrac(d, j);
/*  84 */       if (Math.abs(r.floatValue() - f) < tol)
/*  85 */         return r; 
/*     */     } 
/*  89 */     return r;
/*     */   }
/*     */   
/*     */   public static Rational approximate(double f, double tol) {
/*  99 */     double rem = f;
/* 100 */     long[] d = new long[20];
/* 101 */     int index = 0;
/* 102 */     for (int i = 0; i < 20; i++) {
/* 103 */       long k = (long)Math.floor(rem);
/* 104 */       d[index++] = k;
/* 106 */       rem -= k;
/* 107 */       if (rem == 0.0D)
/*     */         break; 
/* 110 */       rem = 1.0D / rem;
/*     */     } 
/* 115 */     Rational r = null;
/* 116 */     for (int j = 1; j <= index; j++) {
/* 117 */       r = createFromFrac(d, j);
/* 118 */       if (Math.abs(r.doubleValue() - f) < tol)
/* 119 */         return r; 
/*     */     } 
/* 123 */     return r;
/*     */   }
/*     */   
/*     */   private static long gcd(long m, long n) {
/* 127 */     if (m < 0L)
/* 128 */       m = -m; 
/* 130 */     if (n < 0L)
/* 131 */       n = -n; 
/* 134 */     while (n > 0L) {
/* 135 */       long tmp = m % n;
/* 136 */       m = n;
/* 137 */       n = tmp;
/*     */     } 
/* 139 */     return m;
/*     */   }
/*     */   
/*     */   private void normalize() {
/* 144 */     if (this.denom < 0L) {
/* 145 */       this.num = -this.num;
/* 146 */       this.denom = -this.denom;
/*     */     } 
/* 149 */     long gcd = gcd(this.num, this.denom);
/* 150 */     if (gcd > 1L) {
/* 151 */       this.num /= gcd;
/* 152 */       this.denom /= gcd;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(long i) {
/* 160 */     this.num += i * this.denom;
/* 161 */     normalize();
/*     */   }
/*     */   
/*     */   public void add(Rational r) {
/* 168 */     this.num = this.num * r.denom + r.num * this.denom;
/* 169 */     this.denom *= r.denom;
/* 170 */     normalize();
/*     */   }
/*     */   
/*     */   public void subtract(long i) {
/* 177 */     this.num -= i * this.denom;
/* 178 */     normalize();
/*     */   }
/*     */   
/*     */   public void subtract(Rational r) {
/* 185 */     this.num = this.num * r.denom - r.num * this.denom;
/* 186 */     this.denom *= r.denom;
/* 187 */     normalize();
/*     */   }
/*     */   
/*     */   public void multiply(long i) {
/* 194 */     this.num *= i;
/* 195 */     normalize();
/*     */   }
/*     */   
/*     */   public void multiply(Rational r) {
/* 202 */     this.num *= r.num;
/* 203 */     this.denom *= r.denom;
/* 204 */     normalize();
/*     */   }
/*     */   
/*     */   public void invert() {
/* 211 */     long tmp = this.num;
/* 212 */     this.num = this.denom;
/* 213 */     this.denom = tmp;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 220 */     return (float)this.num / (float)this.denom;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 227 */     return this.num / this.denom;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 234 */     return this.num + "/" + this.denom;
/*     */   }
/*     */   
/*     */   public static int ceil(long num, long denom) {
/* 242 */     int ret = (int)(num / denom);
/* 244 */     if (num > 0L && 
/* 245 */       num % denom != 0L)
/* 246 */       ret++; 
/* 250 */     return ret;
/*     */   }
/*     */   
/*     */   public static int floor(long num, long denom) {
/* 258 */     int ret = (int)(num / denom);
/* 260 */     if (num < 0L && 
/* 261 */       num % denom != 0L)
/* 262 */       ret--; 
/* 266 */     return ret;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 274 */     float f = Float.parseFloat(args[0]);
/* 275 */     for (int i = 1; i < 15; i++) {
/* 276 */       Rational r = approximate(f, (float)Math.pow(10.0D, -i));
/* 277 */       System.out.println(r + " = " + r.floatValue());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\Rational.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */