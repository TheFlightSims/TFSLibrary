/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ public class MutableInt extends Number implements Comparable, Mutable {
/*     */   private static final long serialVersionUID = 512176391864L;
/*     */   
/*     */   private int value;
/*     */   
/*     */   public MutableInt() {}
/*     */   
/*     */   public MutableInt(int value) {
/*  53 */     this.value = value;
/*     */   }
/*     */   
/*     */   public MutableInt(Number value) {
/*  64 */     this.value = value.intValue();
/*     */   }
/*     */   
/*     */   public MutableInt(String value) throws NumberFormatException {
/*  76 */     this.value = Integer.parseInt(value);
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  86 */     return new Integer(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(int value) {
/*  95 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/* 106 */     setValue(((Number)value).intValue());
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
/*     */   public void add(int operand) {
/* 136 */     this.value += operand;
/*     */   }
/*     */   
/*     */   public void add(Number operand) {
/* 147 */     this.value += operand.intValue();
/*     */   }
/*     */   
/*     */   public void subtract(int operand) {
/* 157 */     this.value -= operand;
/*     */   }
/*     */   
/*     */   public void subtract(Number operand) {
/* 168 */     this.value -= operand.intValue();
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 179 */     return this.value;
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 188 */     return this.value;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 197 */     return this.value;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 206 */     return this.value;
/*     */   }
/*     */   
/*     */   public Integer toInteger() {
/* 216 */     return new Integer(intValue());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 229 */     if (obj instanceof MutableInt)
/* 230 */       return (this.value == ((MutableInt)obj).intValue()); 
/* 232 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 241 */     return this.value;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 253 */     MutableInt other = (MutableInt)obj;
/* 254 */     int anotherVal = other.value;
/* 255 */     return (this.value < anotherVal) ? -1 : ((this.value == anotherVal) ? 0 : 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 265 */     return String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableInt.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */