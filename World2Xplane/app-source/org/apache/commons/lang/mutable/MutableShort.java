/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ public class MutableShort extends Number implements Comparable, Mutable {
/*     */   private static final long serialVersionUID = -2135791679L;
/*     */   
/*     */   private short value;
/*     */   
/*     */   public MutableShort() {}
/*     */   
/*     */   public MutableShort(short value) {
/*  53 */     this.value = value;
/*     */   }
/*     */   
/*     */   public MutableShort(Number value) {
/*  64 */     this.value = value.shortValue();
/*     */   }
/*     */   
/*     */   public MutableShort(String value) throws NumberFormatException {
/*  76 */     this.value = Short.parseShort(value);
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  86 */     return new Short(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(short value) {
/*  95 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/* 106 */     setValue(((Number)value).shortValue());
/*     */   }
/*     */   
/*     */   public void increment() {
/* 116 */     this.value = (short)(this.value + 1);
/*     */   }
/*     */   
/*     */   public void decrement() {
/* 125 */     this.value = (short)(this.value - 1);
/*     */   }
/*     */   
/*     */   public void add(short operand) {
/* 136 */     this.value = (short)(this.value + operand);
/*     */   }
/*     */   
/*     */   public void add(Number operand) {
/* 147 */     this.value = (short)(this.value + operand.shortValue());
/*     */   }
/*     */   
/*     */   public void subtract(short operand) {
/* 157 */     this.value = (short)(this.value - operand);
/*     */   }
/*     */   
/*     */   public void subtract(Number operand) {
/* 168 */     this.value = (short)(this.value - operand.shortValue());
/*     */   }
/*     */   
/*     */   public short shortValue() {
/* 179 */     return this.value;
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 188 */     return this.value;
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 197 */     return this.value;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 206 */     return this.value;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 215 */     return this.value;
/*     */   }
/*     */   
/*     */   public Short toShort() {
/* 225 */     return new Short(shortValue());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 238 */     if (obj instanceof MutableShort)
/* 239 */       return (this.value == ((MutableShort)obj).shortValue()); 
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 250 */     return this.value;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 262 */     MutableShort other = (MutableShort)obj;
/* 263 */     short anotherVal = other.value;
/* 264 */     return (this.value < anotherVal) ? -1 : ((this.value == anotherVal) ? 0 : 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 274 */     return String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableShort.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */