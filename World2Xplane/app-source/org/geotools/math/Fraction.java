/*     */ package org.geotools.math;
/*     */ 
/*     */ public final class Fraction extends Number implements Comparable<Fraction>, Cloneable {
/*     */   private static final long serialVersionUID = -4501644254763471216L;
/*     */   
/*     */   private int numerator;
/*     */   
/*     */   private int denominator;
/*     */   
/*     */   public Fraction() {}
/*     */   
/*     */   public Fraction(Fraction other) {
/*  67 */     this.numerator = other.numerator;
/*  68 */     this.denominator = other.denominator;
/*     */   }
/*     */   
/*     */   public Fraction(int numerator) {
/*  77 */     this.numerator = numerator;
/*  78 */     this.denominator = 1;
/*     */   }
/*     */   
/*     */   public Fraction(int numerator, int denominator) {
/*  88 */     this.numerator = numerator;
/*  89 */     this.denominator = denominator;
/*  90 */     simplify();
/*     */   }
/*     */   
/*     */   public void set(int numerator, int denominator) {
/* 100 */     this.numerator = numerator;
/* 101 */     this.denominator = denominator;
/* 102 */     simplify();
/*     */   }
/*     */   
/*     */   public void add(Fraction other) {
/* 112 */     this.numerator = this.numerator * other.denominator + other.numerator * this.denominator;
/* 113 */     this.denominator *= other.denominator;
/* 114 */     simplify();
/*     */   }
/*     */   
/*     */   public void subtract(Fraction other) {
/* 124 */     this.numerator = this.numerator * other.denominator - other.numerator * this.denominator;
/* 125 */     this.denominator *= other.denominator;
/* 126 */     simplify();
/*     */   }
/*     */   
/*     */   public void multiply(Fraction other) {
/* 136 */     this.numerator *= other.numerator;
/* 137 */     this.denominator *= other.denominator;
/* 138 */     simplify();
/*     */   }
/*     */   
/*     */   public void divide(Fraction other) {
/* 148 */     this.numerator *= other.denominator;
/* 149 */     this.denominator *= other.numerator;
/* 150 */     simplify();
/*     */   }
/*     */   
/*     */   private void simplify() {
/* 158 */     if (this.numerator == 0) {
/* 159 */       this.denominator = XMath.sgn(this.denominator);
/*     */       return;
/*     */     } 
/* 163 */     if (this.denominator == 0) {
/* 164 */       this.numerator = XMath.sgn(this.numerator);
/*     */       return;
/*     */     } 
/* 168 */     if (this.denominator % this.numerator == 0) {
/* 169 */       this.denominator /= this.numerator;
/* 170 */       if (this.denominator < 0) {
/* 171 */         this.denominator = -this.denominator;
/* 172 */         this.numerator = -1;
/*     */       } else {
/* 174 */         this.numerator = 1;
/*     */       } 
/*     */       return;
/*     */     } 
/* 178 */     int num = Math.abs(this.numerator);
/* 179 */     int den = Math.abs(this.denominator);
/* 180 */     num %= den;
/* 182 */     if (num == 0) {
/* 183 */       this.numerator /= this.denominator;
/* 184 */       this.denominator = 1;
/*     */       return;
/*     */     } 
/* 188 */     int pgcd = 1;
/* 189 */     int remainder = num;
/*     */     while (true) {
/* 191 */       num = den;
/* 192 */       den = remainder;
/* 193 */       pgcd = remainder;
/* 194 */       remainder = num % den;
/* 195 */       if (remainder == 0) {
/* 196 */         this.numerator /= pgcd;
/* 197 */         this.denominator /= pgcd;
/* 198 */         if (this.denominator < 0) {
/* 199 */           this.numerator = -this.numerator;
/* 200 */           this.denominator = -this.denominator;
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int numerator() {
/* 210 */     return this.numerator;
/*     */   }
/*     */   
/*     */   public int denominator() {
/* 219 */     return this.denominator;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 229 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 239 */     return (float)doubleValue();
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 249 */     return intValue();
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 259 */     return round(this.numerator, this.denominator);
/*     */   }
/*     */   
/*     */   public static long round(long numerator, long denominator) {
/* 272 */     long n = numerator / denominator;
/* 273 */     long r = numerator % denominator;
/* 274 */     if (r != 0L) {
/* 275 */       r = Math.abs(r << 1L);
/* 276 */       long d = Math.abs(denominator);
/* 277 */       if (r > d || (r == d && (n & 0x1L) != 0L))
/* 278 */         if ((numerator ^ denominator) >= 0L) {
/* 279 */           n++;
/*     */         } else {
/* 281 */           n--;
/*     */         }  
/*     */     } 
/* 285 */     return n;
/*     */   }
/*     */   
/*     */   public static int round(int numerator, int denominator) {
/* 298 */     int n = numerator / denominator;
/* 299 */     int r = numerator % denominator;
/* 300 */     if (r != 0) {
/* 301 */       r = Math.abs(r << 1);
/* 302 */       int d = Math.abs(denominator);
/* 303 */       if (r > d || (r == d && (n & 0x1) != 0))
/* 304 */         if ((numerator ^ denominator) >= 0) {
/* 305 */           n++;
/*     */         } else {
/* 307 */           n--;
/*     */         }  
/*     */     } 
/* 311 */     return n;
/*     */   }
/*     */   
/*     */   public static int floor(int numerator, int denominator) {
/* 326 */     int n = numerator / denominator;
/* 327 */     if ((numerator ^ denominator) < 0 && numerator % denominator != 0)
/* 328 */       n--; 
/* 330 */     return n;
/*     */   }
/*     */   
/*     */   public static int ceil(int numerator, int denominator) {
/* 342 */     int n = numerator / denominator;
/* 343 */     if ((numerator ^ denominator) >= 0 && numerator % denominator != 0)
/* 344 */       n++; 
/* 346 */     return n;
/*     */   }
/*     */   
/*     */   public int compareTo(Fraction other) {
/* 357 */     return this.numerator * other.denominator - other.numerator * this.denominator;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 369 */     if (other instanceof Fraction) {
/* 370 */       Fraction that = (Fraction)other;
/* 371 */       return (this.numerator == that.numerator && this.denominator == that.denominator);
/*     */     } 
/* 373 */     return false;
/*     */   }
/*     */   
/*     */   public Fraction clone() {
/*     */     try {
/* 384 */       return (Fraction)super.clone();
/* 385 */     } catch (CloneNotSupportedException e) {
/* 386 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 397 */     return String.valueOf(this.numerator) + '/' + this.denominator;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\math\Fraction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */