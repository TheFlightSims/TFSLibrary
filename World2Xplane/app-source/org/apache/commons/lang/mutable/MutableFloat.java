/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ 
/*     */ public class MutableFloat extends Number implements Comparable, Mutable {
/*     */   private static final long serialVersionUID = 5787169186L;
/*     */   
/*     */   private float value;
/*     */   
/*     */   public MutableFloat() {}
/*     */   
/*     */   public MutableFloat(float value) {
/*  55 */     this.value = value;
/*     */   }
/*     */   
/*     */   public MutableFloat(Number value) {
/*  66 */     this.value = value.floatValue();
/*     */   }
/*     */   
/*     */   public MutableFloat(String value) throws NumberFormatException {
/*  78 */     this.value = Float.parseFloat(value);
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  88 */     return new Float(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(float value) {
/*  97 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/* 108 */     setValue(((Number)value).floatValue());
/*     */   }
/*     */   
/*     */   public boolean isNaN() {
/* 118 */     return Float.isNaN(this.value);
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 127 */     return Float.isInfinite(this.value);
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
/*     */   public void add(float operand) {
/* 157 */     this.value += operand;
/*     */   }
/*     */   
/*     */   public void add(Number operand) {
/* 168 */     this.value += operand.floatValue();
/*     */   }
/*     */   
/*     */   public void subtract(float operand) {
/* 178 */     this.value -= operand;
/*     */   }
/*     */   
/*     */   public void subtract(Number operand) {
/* 189 */     this.value -= operand.floatValue();
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
/* 218 */     return this.value;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 227 */     return this.value;
/*     */   }
/*     */   
/*     */   public Float toFloat() {
/* 237 */     return new Float(floatValue());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 273 */     return (obj instanceof MutableFloat && Float.floatToIntBits(((MutableFloat)obj).value) == Float.floatToIntBits(this.value));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 283 */     return Float.floatToIntBits(this.value);
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 294 */     MutableFloat other = (MutableFloat)obj;
/* 295 */     float anotherVal = other.value;
/* 296 */     return NumberUtils.compare(this.value, anotherVal);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 306 */     return String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableFloat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */