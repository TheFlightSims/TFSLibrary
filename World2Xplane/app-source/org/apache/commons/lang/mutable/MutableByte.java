/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ public class MutableByte extends Number implements Comparable, Mutable {
/*     */   private static final long serialVersionUID = -1585823265L;
/*     */   
/*     */   private byte value;
/*     */   
/*     */   public MutableByte() {}
/*     */   
/*     */   public MutableByte(byte value) {
/*  53 */     this.value = value;
/*     */   }
/*     */   
/*     */   public MutableByte(Number value) {
/*  64 */     this.value = value.byteValue();
/*     */   }
/*     */   
/*     */   public MutableByte(String value) throws NumberFormatException {
/*  76 */     this.value = Byte.parseByte(value);
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  86 */     return new Byte(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(byte value) {
/*  95 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/* 106 */     setValue(((Number)value).byteValue());
/*     */   }
/*     */   
/*     */   public void increment() {
/* 116 */     this.value = (byte)(this.value + 1);
/*     */   }
/*     */   
/*     */   public void decrement() {
/* 125 */     this.value = (byte)(this.value - 1);
/*     */   }
/*     */   
/*     */   public void add(byte operand) {
/* 136 */     this.value = (byte)(this.value + operand);
/*     */   }
/*     */   
/*     */   public void add(Number operand) {
/* 147 */     this.value = (byte)(this.value + operand.byteValue());
/*     */   }
/*     */   
/*     */   public void subtract(byte operand) {
/* 157 */     this.value = (byte)(this.value - operand);
/*     */   }
/*     */   
/*     */   public void subtract(Number operand) {
/* 168 */     this.value = (byte)(this.value - operand.byteValue());
/*     */   }
/*     */   
/*     */   public byte byteValue() {
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
/*     */   public Byte toByte() {
/* 225 */     return new Byte(byteValue());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 238 */     if (obj instanceof MutableByte)
/* 239 */       return (this.value == ((MutableByte)obj).byteValue()); 
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 250 */     return this.value;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 262 */     MutableByte other = (MutableByte)obj;
/* 263 */     byte anotherVal = other.value;
/* 264 */     return (this.value < anotherVal) ? -1 : ((this.value == anotherVal) ? 0 : 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 274 */     return String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableByte.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */