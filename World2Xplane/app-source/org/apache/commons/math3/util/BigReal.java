/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class BigReal implements FieldElement<BigReal>, Comparable<BigReal>, Serializable {
/*  43 */   public static final BigReal ZERO = new BigReal(BigDecimal.ZERO);
/*     */   
/*  46 */   public static final BigReal ONE = new BigReal(BigDecimal.ONE);
/*     */   
/*     */   private static final long serialVersionUID = 4984534880991310382L;
/*     */   
/*     */   private final BigDecimal d;
/*     */   
/*  55 */   private RoundingMode roundingMode = RoundingMode.HALF_UP;
/*     */   
/*  58 */   private int scale = 64;
/*     */   
/*     */   public BigReal(BigDecimal val) {
/*  64 */     this.d = val;
/*     */   }
/*     */   
/*     */   public BigReal(BigInteger val) {
/*  71 */     this.d = new BigDecimal(val);
/*     */   }
/*     */   
/*     */   public BigReal(BigInteger unscaledVal, int scale) {
/*  79 */     this.d = new BigDecimal(unscaledVal, scale);
/*     */   }
/*     */   
/*     */   public BigReal(BigInteger unscaledVal, int scale, MathContext mc) {
/*  88 */     this.d = new BigDecimal(unscaledVal, scale, mc);
/*     */   }
/*     */   
/*     */   public BigReal(BigInteger val, MathContext mc) {
/*  96 */     this.d = new BigDecimal(val, mc);
/*     */   }
/*     */   
/*     */   public BigReal(char[] in) {
/* 103 */     this.d = new BigDecimal(in);
/*     */   }
/*     */   
/*     */   public BigReal(char[] in, int offset, int len) {
/* 112 */     this.d = new BigDecimal(in, offset, len);
/*     */   }
/*     */   
/*     */   public BigReal(char[] in, int offset, int len, MathContext mc) {
/* 122 */     this.d = new BigDecimal(in, offset, len, mc);
/*     */   }
/*     */   
/*     */   public BigReal(char[] in, MathContext mc) {
/* 130 */     this.d = new BigDecimal(in, mc);
/*     */   }
/*     */   
/*     */   public BigReal(double val) {
/* 137 */     this.d = new BigDecimal(val);
/*     */   }
/*     */   
/*     */   public BigReal(double val, MathContext mc) {
/* 145 */     this.d = new BigDecimal(val, mc);
/*     */   }
/*     */   
/*     */   public BigReal(int val) {
/* 152 */     this.d = new BigDecimal(val);
/*     */   }
/*     */   
/*     */   public BigReal(int val, MathContext mc) {
/* 160 */     this.d = new BigDecimal(val, mc);
/*     */   }
/*     */   
/*     */   public BigReal(long val) {
/* 167 */     this.d = new BigDecimal(val);
/*     */   }
/*     */   
/*     */   public BigReal(long val, MathContext mc) {
/* 175 */     this.d = new BigDecimal(val, mc);
/*     */   }
/*     */   
/*     */   public BigReal(String val) {
/* 182 */     this.d = new BigDecimal(val);
/*     */   }
/*     */   
/*     */   public BigReal(String val, MathContext mc) {
/* 190 */     this.d = new BigDecimal(val, mc);
/*     */   }
/*     */   
/*     */   public RoundingMode getRoundingMode() {
/* 200 */     return this.roundingMode;
/*     */   }
/*     */   
/*     */   public void setRoundingMode(RoundingMode roundingMode) {
/* 209 */     this.roundingMode = roundingMode;
/*     */   }
/*     */   
/*     */   public int getScale() {
/* 219 */     return this.scale;
/*     */   }
/*     */   
/*     */   public void setScale(int scale) {
/* 228 */     this.scale = scale;
/*     */   }
/*     */   
/*     */   public BigReal add(BigReal a) {
/* 233 */     return new BigReal(this.d.add(a.d));
/*     */   }
/*     */   
/*     */   public BigReal subtract(BigReal a) {
/* 238 */     return new BigReal(this.d.subtract(a.d));
/*     */   }
/*     */   
/*     */   public BigReal negate() {
/* 243 */     return new BigReal(this.d.negate());
/*     */   }
/*     */   
/*     */   public BigReal divide(BigReal a) {
/*     */     try {
/* 253 */       return new BigReal(this.d.divide(a.d, this.scale, this.roundingMode));
/* 254 */     } catch (ArithmeticException e) {
/* 256 */       throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public BigReal reciprocal() {
/*     */     try {
/* 267 */       return new BigReal(BigDecimal.ONE.divide(this.d, this.scale, this.roundingMode));
/* 268 */     } catch (ArithmeticException e) {
/* 270 */       throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public BigReal multiply(BigReal a) {
/* 276 */     return new BigReal(this.d.multiply(a.d));
/*     */   }
/*     */   
/*     */   public BigReal multiply(int n) {
/* 281 */     return new BigReal(this.d.multiply(new BigDecimal(n)));
/*     */   }
/*     */   
/*     */   public int compareTo(BigReal a) {
/* 286 */     return this.d.compareTo(a.d);
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 293 */     return this.d.doubleValue();
/*     */   }
/*     */   
/*     */   public BigDecimal bigDecimalValue() {
/* 300 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 306 */     if (this == other)
/* 307 */       return true; 
/* 310 */     if (other instanceof BigReal)
/* 311 */       return this.d.equals(((BigReal)other).d); 
/* 313 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 319 */     return this.d.hashCode();
/*     */   }
/*     */   
/*     */   public Field<BigReal> getField() {
/* 324 */     return BigRealField.getInstance();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\BigReal.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */