/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public final class LongRange extends Range implements Serializable {
/*     */   private static final long serialVersionUID = 71849363892720L;
/*     */   
/*     */   private final long min;
/*     */   
/*     */   private final long max;
/*     */   
/*  51 */   private transient Long minObject = null;
/*     */   
/*  55 */   private transient Long maxObject = null;
/*     */   
/*  59 */   private transient int hashCode = 0;
/*     */   
/*  63 */   private transient String toString = null;
/*     */   
/*     */   public LongRange(long number) {
/*  73 */     this.min = number;
/*  74 */     this.max = number;
/*     */   }
/*     */   
/*     */   public LongRange(Number number) {
/*  87 */     if (number == null)
/*  88 */       throw new IllegalArgumentException("The number must not be null"); 
/*  90 */     this.min = number.longValue();
/*  91 */     this.max = number.longValue();
/*  92 */     if (number instanceof Long) {
/*  93 */       this.minObject = (Long)number;
/*  94 */       this.maxObject = (Long)number;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LongRange(long number1, long number2) {
/* 110 */     if (number2 < number1) {
/* 111 */       this.min = number2;
/* 112 */       this.max = number1;
/*     */     } else {
/* 114 */       this.min = number1;
/* 115 */       this.max = number2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LongRange(Number number1, Number number2) {
/* 132 */     if (number1 == null || number2 == null)
/* 133 */       throw new IllegalArgumentException("The numbers must not be null"); 
/* 135 */     long number1val = number1.longValue();
/* 136 */     long number2val = number2.longValue();
/* 137 */     if (number2val < number1val) {
/* 138 */       this.min = number2val;
/* 139 */       this.max = number1val;
/* 140 */       if (number2 instanceof Long)
/* 141 */         this.minObject = (Long)number2; 
/* 143 */       if (number1 instanceof Long)
/* 144 */         this.maxObject = (Long)number1; 
/*     */     } else {
/* 147 */       this.min = number1val;
/* 148 */       this.max = number2val;
/* 149 */       if (number1 instanceof Long)
/* 150 */         this.minObject = (Long)number1; 
/* 152 */       if (number2 instanceof Long)
/* 153 */         this.maxObject = (Long)number2; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number getMinimumNumber() {
/* 167 */     if (this.minObject == null)
/* 168 */       this.minObject = new Long(this.min); 
/* 170 */     return this.minObject;
/*     */   }
/*     */   
/*     */   public long getMinimumLong() {
/* 179 */     return this.min;
/*     */   }
/*     */   
/*     */   public int getMinimumInteger() {
/* 190 */     return (int)this.min;
/*     */   }
/*     */   
/*     */   public double getMinimumDouble() {
/* 201 */     return this.min;
/*     */   }
/*     */   
/*     */   public float getMinimumFloat() {
/* 212 */     return (float)this.min;
/*     */   }
/*     */   
/*     */   public Number getMaximumNumber() {
/* 221 */     if (this.maxObject == null)
/* 222 */       this.maxObject = new Long(this.max); 
/* 224 */     return this.maxObject;
/*     */   }
/*     */   
/*     */   public long getMaximumLong() {
/* 233 */     return this.max;
/*     */   }
/*     */   
/*     */   public int getMaximumInteger() {
/* 244 */     return (int)this.max;
/*     */   }
/*     */   
/*     */   public double getMaximumDouble() {
/* 255 */     return this.max;
/*     */   }
/*     */   
/*     */   public float getMaximumFloat() {
/* 266 */     return (float)this.max;
/*     */   }
/*     */   
/*     */   public boolean containsNumber(Number number) {
/* 282 */     if (number == null)
/* 283 */       return false; 
/* 285 */     return containsLong(number.longValue());
/*     */   }
/*     */   
/*     */   public boolean containsLong(long value) {
/* 300 */     return (value >= this.min && value <= this.max);
/*     */   }
/*     */   
/*     */   public boolean containsRange(Range range) {
/* 317 */     if (range == null)
/* 318 */       return false; 
/* 320 */     return (containsLong(range.getMinimumLong()) && containsLong(range.getMaximumLong()));
/*     */   }
/*     */   
/*     */   public boolean overlapsRange(Range range) {
/* 334 */     if (range == null)
/* 335 */       return false; 
/* 337 */     return (range.containsLong(this.min) || range.containsLong(this.max) || containsLong(range.getMinimumLong()));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 354 */     if (obj == this)
/* 355 */       return true; 
/* 357 */     if (!(obj instanceof LongRange))
/* 358 */       return false; 
/* 360 */     LongRange range = (LongRange)obj;
/* 361 */     return (this.min == range.min && this.max == range.max);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 370 */     if (this.hashCode == 0) {
/* 371 */       this.hashCode = 17;
/* 372 */       this.hashCode = 37 * this.hashCode + getClass().hashCode();
/* 373 */       this.hashCode = 37 * this.hashCode + (int)(this.min ^ this.min >> 32L);
/* 374 */       this.hashCode = 37 * this.hashCode + (int)(this.max ^ this.max >> 32L);
/*     */     } 
/* 376 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 387 */     if (this.toString == null) {
/* 388 */       StrBuilder buf = new StrBuilder(32);
/* 389 */       buf.append("Range[");
/* 390 */       buf.append(this.min);
/* 391 */       buf.append(',');
/* 392 */       buf.append(this.max);
/* 393 */       buf.append(']');
/* 394 */       this.toString = buf.toString();
/*     */     } 
/* 396 */     return this.toString;
/*     */   }
/*     */   
/*     */   public long[] toArray() {
/* 406 */     long[] array = new long[(int)(this.max - this.min + 1L)];
/* 407 */     for (int i = 0; i < array.length; i++)
/* 408 */       array[i] = this.min + i; 
/* 410 */     return array;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\LongRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */