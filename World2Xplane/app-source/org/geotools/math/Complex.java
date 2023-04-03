/*     */ package org.geotools.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public final class Complex implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -8143196508298758583L;
/*     */   
/*     */   public double real;
/*     */   
/*     */   public double imag;
/*     */   
/*     */   public Complex() {}
/*     */   
/*     */   public Complex(Complex c) {
/*  68 */     this.real = c.real;
/*  69 */     this.imag = c.imag;
/*     */   }
/*     */   
/*     */   public Complex(double real, double imag) {
/*  76 */     this.real = real;
/*  77 */     this.imag = imag;
/*     */   }
/*     */   
/*     */   public void copy(Complex c) {
/*  89 */     this.real = c.real;
/*  90 */     this.imag = c.imag;
/*     */   }
/*     */   
/*     */   public void multiply(Complex c, double s) {
/* 102 */     c.real *= s;
/* 103 */     c.imag *= s;
/*     */   }
/*     */   
/*     */   public void multiply(Complex c1, Complex c2) {
/* 115 */     double x1 = c1.real;
/* 116 */     double y1 = c1.imag;
/* 117 */     double x2 = c2.real;
/* 118 */     double y2 = c2.imag;
/* 119 */     this.real = x1 * x2 - y1 * y2;
/* 120 */     this.imag = y1 * x2 + x1 * y2;
/*     */   }
/*     */   
/*     */   public void divide(Complex c1, Complex c2) {
/* 132 */     double x1 = c1.real;
/* 133 */     double y1 = c1.imag;
/* 134 */     double x2 = c2.real;
/* 135 */     double y2 = c2.imag;
/* 136 */     double denom = x2 * x2 + y2 * y2;
/* 137 */     this.real = (x1 * x2 + y1 * y2) / denom;
/* 138 */     this.imag = (y1 * x2 - x1 * y2) / denom;
/*     */   }
/*     */   
/*     */   public void add(Complex c1, Complex c2) {
/* 150 */     c1.real += c2.real;
/* 151 */     c1.imag += c2.imag;
/*     */   }
/*     */   
/*     */   public void addMultiply(Complex c0, Complex c1, Complex c2) {
/* 163 */     double x1 = c1.real;
/* 164 */     double y1 = c1.imag;
/* 165 */     double x2 = c2.real;
/* 166 */     double y2 = c2.imag;
/* 167 */     c0.real += x1 * x2 - y1 * y2;
/* 168 */     c0.imag += y1 * x2 + x1 * y2;
/*     */   }
/*     */   
/*     */   public void power(Complex c, int power) {
/* 180 */     double x = c.real;
/* 181 */     double y = c.imag;
/* 182 */     switch (power) {
/*     */       case 0:
/* 184 */         this.real = 1.0D;
/* 185 */         this.imag = 0.0D;
/*     */         return;
/*     */       case 1:
/* 189 */         this.real = x;
/* 190 */         this.imag = y;
/*     */         return;
/*     */       case 2:
/* 194 */         this.real = x * x - y * y;
/* 195 */         this.imag = 2.0D * x * y;
/*     */         return;
/*     */       case 3:
/* 199 */         this.real = x * x * x - 3.0D * x * y * y;
/* 200 */         this.imag = 3.0D * x * x * y - y * y * y;
/*     */         return;
/*     */       case 4:
/* 204 */         this.real = x * x * x * x - 6.0D * x * x * y * y + y * y * y * y;
/* 205 */         this.imag = 4.0D * x * x * x * y - 4.0D * x * y * y * y;
/*     */         return;
/*     */       case 5:
/* 209 */         this.real = x * x * x * x * x - 10.0D * x * x * x * y * y + 5.0D * x * y * y * y * y;
/* 210 */         this.imag = 5.0D * x * x * x * x * y - 10.0D * x * x * y * y * y + y * y * y * y * y;
/*     */         return;
/*     */       case 6:
/* 214 */         this.real = x * x * x * x * x * x - 15.0D * x * x * x * x * y * y + 15.0D * x * x * y * y * y * y - y * y * y * y * y * y;
/* 216 */         this.imag = 6.0D * x * x * x * x * x * y - 20.0D * x * x * x * y * y * y + 6.0D * x * y * y * y * y * y;
/*     */         return;
/*     */     } 
/* 221 */     throw new IllegalArgumentException(String.valueOf(power));
/*     */   }
/*     */   
/*     */   public Complex clone() {
/* 231 */     return new Complex(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Complex c) {
/* 238 */     return (Double.doubleToLongBits(this.real) == Double.doubleToLongBits(c.real) && Double.doubleToLongBits(this.imag) == Double.doubleToLongBits(c.imag));
/*     */   }
/*     */   
/*     */   public boolean equals(Object c) {
/* 247 */     return (c instanceof Complex && equals((Complex)c));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 255 */     long code = Double.doubleToLongBits(this.real) + 37L * Double.doubleToLongBits(this.imag);
/* 257 */     return (int)code ^ (int)(code >>> 32L);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 265 */     return "Complex[" + this.real + ", " + this.imag + ']';
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\math\Complex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */