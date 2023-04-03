/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ 
/*     */ public class MutableDouble extends Number implements Comparable, Mutable {
/*     */   private static final long serialVersionUID = 1587163916L;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public MutableDouble() {}
/*     */   
/*     */   public MutableDouble(double value) {
/*  55 */     this.value = value;
/*     */   }
/*     */   
/*     */   public MutableDouble(Number value) {
/*  66 */     this.value = value.doubleValue();
/*     */   }
/*     */   
/*     */   public MutableDouble(String value) throws NumberFormatException {
/*  78 */     this.value = Double.parseDouble(value);
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  88 */     return new Double(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(double value) {
/*  97 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/* 108 */     setValue(((Number)value).doubleValue());
/*     */   }
/*     */   
/*     */   public boolean isNaN() {
/* 118 */     return Double.isNaN(this.value);
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 127 */     return Double.isInfinite(this.value);
/*     */   }
/*     */   
/*     */   public void increment() {
/* 137 */     this.value++;
/*     */   }
/*     */   
/*     */   public void decrement() {
/* 146 */     this.value--;
/*     */   }
/*     */   
/*     */   public void add(double operand) {
/* 157 */     this.value += operand;
/*     */   }
/*     */   
/*     */   public void add(Number operand) {
/* 168 */     this.value += operand.doubleValue();
/*     */   }
/*     */   
/*     */   public void subtract(double operand) {
/* 178 */     this.value -= operand;
/*     */   }
/*     */   
/*     */   public void subtract(Number operand) {
/* 189 */     this.value -= operand.doubleValue();
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 200 */     return (int)this.value;
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 209 */     return (long)this.value;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 218 */     return (float)this.value;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 227 */     return this.value;
/*     */   }
/*     */   
/*     */   public Double toDouble() {
/* 237 */     return new Double(doubleValue());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 271 */     return (obj instanceof MutableDouble && Double.doubleToLongBits(((MutableDouble)obj).value) == Double.doubleToLongBits(this.value));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 281 */     long bits = Double.doubleToLongBits(this.value);
/* 282 */     return (int)(bits ^ bits >>> 32L);
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 294 */     MutableDouble other = (MutableDouble)obj;
/* 295 */     double anotherVal = other.value;
/* 296 */     return NumberUtils.compare(this.value, anotherVal);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 306 */     return String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableDouble.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */