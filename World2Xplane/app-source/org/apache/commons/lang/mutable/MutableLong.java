/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ public class MutableLong extends Number implements Comparable, Mutable {
/*     */   private static final long serialVersionUID = 62986528375L;
/*     */   
/*     */   private long value;
/*     */   
/*     */   public MutableLong() {}
/*     */   
/*     */   public MutableLong(long value) {
/*  53 */     this.value = value;
/*     */   }
/*     */   
/*     */   public MutableLong(Number value) {
/*  64 */     this.value = value.longValue();
/*     */   }
/*     */   
/*     */   public MutableLong(String value) throws NumberFormatException {
/*  76 */     this.value = Long.parseLong(value);
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  86 */     return new Long(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(long value) {
/*  95 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/* 106 */     setValue(((Number)value).longValue());
/*     */   }
/*     */   
/*     */   public void increment() {
/* 116 */     this.value++;
/*     */   }
/*     */   
/*     */   public void decrement() {
/* 125 */     this.value--;
/*     */   }
/*     */   
/*     */   public void add(long operand) {
/* 136 */     this.value += operand;
/*     */   }
/*     */   
/*     */   public void add(Number operand) {
/* 147 */     this.value += operand.longValue();
/*     */   }
/*     */   
/*     */   public void subtract(long operand) {
/* 157 */     this.value -= operand;
/*     */   }
/*     */   
/*     */   public void subtract(Number operand) {
/* 168 */     this.value -= operand.longValue();
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 179 */     return (int)this.value;
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 188 */     return this.value;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 197 */     return (float)this.value;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 206 */     return this.value;
/*     */   }
/*     */   
/*     */   public Long toLong() {
/* 216 */     return new Long(longValue());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 229 */     if (obj instanceof MutableLong)
/* 230 */       return (this.value == ((MutableLong)obj).longValue()); 
/* 232 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 241 */     return (int)(this.value ^ this.value >>> 32L);
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 253 */     MutableLong other = (MutableLong)obj;
/* 254 */     long anotherVal = other.value;
/* 255 */     return (this.value < anotherVal) ? -1 : ((this.value == anotherVal) ? 0 : 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 265 */     return String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableLong.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */