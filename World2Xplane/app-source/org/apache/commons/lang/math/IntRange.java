/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public final class IntRange extends Range implements Serializable {
/*     */   private static final long serialVersionUID = 71849363892730L;
/*     */   
/*     */   private final int min;
/*     */   
/*     */   private final int max;
/*     */   
/*  51 */   private transient Integer minObject = null;
/*     */   
/*  55 */   private transient Integer maxObject = null;
/*     */   
/*  59 */   private transient int hashCode = 0;
/*     */   
/*  63 */   private transient String toString = null;
/*     */   
/*     */   public IntRange(int number) {
/*  73 */     this.min = number;
/*  74 */     this.max = number;
/*     */   }
/*     */   
/*     */   public IntRange(Number number) {
/*  86 */     if (number == null)
/*  87 */       throw new IllegalArgumentException("The number must not be null"); 
/*  89 */     this.min = number.intValue();
/*  90 */     this.max = number.intValue();
/*  91 */     if (number instanceof Integer) {
/*  92 */       this.minObject = (Integer)number;
/*  93 */       this.maxObject = (Integer)number;
/*     */     } 
/*     */   }
/*     */   
/*     */   public IntRange(int number1, int number2) {
/* 109 */     if (number2 < number1) {
/* 110 */       this.min = number2;
/* 111 */       this.max = number1;
/*     */     } else {
/* 113 */       this.min = number1;
/* 114 */       this.max = number2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public IntRange(Number number1, Number number2) {
/* 131 */     if (number1 == null || number2 == null)
/* 132 */       throw new IllegalArgumentException("The numbers must not be null"); 
/* 134 */     int number1val = number1.intValue();
/* 135 */     int number2val = number2.intValue();
/* 136 */     if (number2val < number1val) {
/* 137 */       this.min = number2val;
/* 138 */       this.max = number1val;
/* 139 */       if (number2 instanceof Integer)
/* 140 */         this.minObject = (Integer)number2; 
/* 142 */       if (number1 instanceof Integer)
/* 143 */         this.maxObject = (Integer)number1; 
/*     */     } else {
/* 146 */       this.min = number1val;
/* 147 */       this.max = number2val;
/* 148 */       if (number1 instanceof Integer)
/* 149 */         this.minObject = (Integer)number1; 
/* 151 */       if (number2 instanceof Integer)
/* 152 */         this.maxObject = (Integer)number2; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number getMinimumNumber() {
/* 166 */     if (this.minObject == null)
/* 167 */       this.minObject = new Integer(this.min); 
/* 169 */     return this.minObject;
/*     */   }
/*     */   
/*     */   public long getMinimumLong() {
/* 178 */     return this.min;
/*     */   }
/*     */   
/*     */   public int getMinimumInteger() {
/* 187 */     return this.min;
/*     */   }
/*     */   
/*     */   public double getMinimumDouble() {
/* 196 */     return this.min;
/*     */   }
/*     */   
/*     */   public float getMinimumFloat() {
/* 205 */     return this.min;
/*     */   }
/*     */   
/*     */   public Number getMaximumNumber() {
/* 214 */     if (this.maxObject == null)
/* 215 */       this.maxObject = new Integer(this.max); 
/* 217 */     return this.maxObject;
/*     */   }
/*     */   
/*     */   public long getMaximumLong() {
/* 226 */     return this.max;
/*     */   }
/*     */   
/*     */   public int getMaximumInteger() {
/* 235 */     return this.max;
/*     */   }
/*     */   
/*     */   public double getMaximumDouble() {
/* 244 */     return this.max;
/*     */   }
/*     */   
/*     */   public float getMaximumFloat() {
/* 253 */     return this.max;
/*     */   }
/*     */   
/*     */   public boolean containsNumber(Number number) {
/* 269 */     if (number == null)
/* 270 */       return false; 
/* 272 */     return containsInteger(number.intValue());
/*     */   }
/*     */   
/*     */   public boolean containsInteger(int value) {
/* 287 */     return (value >= this.min && value <= this.max);
/*     */   }
/*     */   
/*     */   public boolean containsRange(Range range) {
/* 304 */     if (range == null)
/* 305 */       return false; 
/* 307 */     return (containsInteger(range.getMinimumInteger()) && containsInteger(range.getMaximumInteger()));
/*     */   }
/*     */   
/*     */   public boolean overlapsRange(Range range) {
/* 321 */     if (range == null)
/* 322 */       return false; 
/* 324 */     return (range.containsInteger(this.min) || range.containsInteger(this.max) || containsInteger(range.getMinimumInteger()));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 341 */     if (obj == this)
/* 342 */       return true; 
/* 344 */     if (!(obj instanceof IntRange))
/* 345 */       return false; 
/* 347 */     IntRange range = (IntRange)obj;
/* 348 */     return (this.min == range.min && this.max == range.max);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 357 */     if (this.hashCode == 0) {
/* 358 */       this.hashCode = 17;
/* 359 */       this.hashCode = 37 * this.hashCode + getClass().hashCode();
/* 360 */       this.hashCode = 37 * this.hashCode + this.min;
/* 361 */       this.hashCode = 37 * this.hashCode + this.max;
/*     */     } 
/* 363 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 374 */     if (this.toString == null) {
/* 375 */       StrBuilder buf = new StrBuilder(32);
/* 376 */       buf.append("Range[");
/* 377 */       buf.append(this.min);
/* 378 */       buf.append(',');
/* 379 */       buf.append(this.max);
/* 380 */       buf.append(']');
/* 381 */       this.toString = buf.toString();
/*     */     } 
/* 383 */     return this.toString;
/*     */   }
/*     */   
/*     */   public int[] toArray() {
/* 393 */     int[] array = new int[this.max - this.min + 1];
/* 394 */     for (int i = 0; i < array.length; i++)
/* 395 */       array[i] = this.min + i; 
/* 398 */     return array;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\IntRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */