/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class FloatRange extends Range implements Serializable {
/*     */   private static final long serialVersionUID = 71849363892750L;
/*     */   
/*     */   private final float min;
/*     */   
/*     */   private final float max;
/*     */   
/*  49 */   private transient Float minObject = null;
/*     */   
/*  53 */   private transient Float maxObject = null;
/*     */   
/*  57 */   private transient int hashCode = 0;
/*     */   
/*  61 */   private transient String toString = null;
/*     */   
/*     */   public FloatRange(float number) {
/*  72 */     if (Float.isNaN(number))
/*  73 */       throw new IllegalArgumentException("The number must not be NaN"); 
/*  75 */     this.min = number;
/*  76 */     this.max = number;
/*     */   }
/*     */   
/*     */   public FloatRange(Number number) {
/*  90 */     if (number == null)
/*  91 */       throw new IllegalArgumentException("The number must not be null"); 
/*  93 */     this.min = number.floatValue();
/*  94 */     this.max = number.floatValue();
/*  95 */     if (Float.isNaN(this.min) || Float.isNaN(this.max))
/*  96 */       throw new IllegalArgumentException("The number must not be NaN"); 
/*  98 */     if (number instanceof Float) {
/*  99 */       this.minObject = (Float)number;
/* 100 */       this.maxObject = (Float)number;
/*     */     } 
/*     */   }
/*     */   
/*     */   public FloatRange(float number1, float number2) {
/* 117 */     if (Float.isNaN(number1) || Float.isNaN(number2))
/* 118 */       throw new IllegalArgumentException("The numbers must not be NaN"); 
/* 120 */     if (number2 < number1) {
/* 121 */       this.min = number2;
/* 122 */       this.max = number1;
/*     */     } else {
/* 124 */       this.min = number1;
/* 125 */       this.max = number2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public FloatRange(Number number1, Number number2) {
/* 143 */     if (number1 == null || number2 == null)
/* 144 */       throw new IllegalArgumentException("The numbers must not be null"); 
/* 146 */     float number1val = number1.floatValue();
/* 147 */     float number2val = number2.floatValue();
/* 148 */     if (Float.isNaN(number1val) || Float.isNaN(number2val))
/* 149 */       throw new IllegalArgumentException("The numbers must not be NaN"); 
/* 151 */     if (number2val < number1val) {
/* 152 */       this.min = number2val;
/* 153 */       this.max = number1val;
/* 154 */       if (number2 instanceof Float)
/* 155 */         this.minObject = (Float)number2; 
/* 157 */       if (number1 instanceof Float)
/* 158 */         this.maxObject = (Float)number1; 
/*     */     } else {
/* 161 */       this.min = number1val;
/* 162 */       this.max = number2val;
/* 163 */       if (number1 instanceof Float)
/* 164 */         this.minObject = (Float)number1; 
/* 166 */       if (number2 instanceof Float)
/* 167 */         this.maxObject = (Float)number2; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number getMinimumNumber() {
/* 181 */     if (this.minObject == null)
/* 182 */       this.minObject = new Float(this.min); 
/* 184 */     return this.minObject;
/*     */   }
/*     */   
/*     */   public long getMinimumLong() {
/* 195 */     return (long)this.min;
/*     */   }
/*     */   
/*     */   public int getMinimumInteger() {
/* 206 */     return (int)this.min;
/*     */   }
/*     */   
/*     */   public double getMinimumDouble() {
/* 215 */     return this.min;
/*     */   }
/*     */   
/*     */   public float getMinimumFloat() {
/* 224 */     return this.min;
/*     */   }
/*     */   
/*     */   public Number getMaximumNumber() {
/* 233 */     if (this.maxObject == null)
/* 234 */       this.maxObject = new Float(this.max); 
/* 236 */     return this.maxObject;
/*     */   }
/*     */   
/*     */   public long getMaximumLong() {
/* 247 */     return (long)this.max;
/*     */   }
/*     */   
/*     */   public int getMaximumInteger() {
/* 258 */     return (int)this.max;
/*     */   }
/*     */   
/*     */   public double getMaximumDouble() {
/* 267 */     return this.max;
/*     */   }
/*     */   
/*     */   public float getMaximumFloat() {
/* 276 */     return this.max;
/*     */   }
/*     */   
/*     */   public boolean containsNumber(Number number) {
/* 292 */     if (number == null)
/* 293 */       return false; 
/* 295 */     return containsFloat(number.floatValue());
/*     */   }
/*     */   
/*     */   public boolean containsFloat(float value) {
/* 310 */     return (value >= this.min && value <= this.max);
/*     */   }
/*     */   
/*     */   public boolean containsRange(Range range) {
/* 327 */     if (range == null)
/* 328 */       return false; 
/* 330 */     return (containsFloat(range.getMinimumFloat()) && containsFloat(range.getMaximumFloat()));
/*     */   }
/*     */   
/*     */   public boolean overlapsRange(Range range) {
/* 344 */     if (range == null)
/* 345 */       return false; 
/* 347 */     return (range.containsFloat(this.min) || range.containsFloat(this.max) || containsFloat(range.getMinimumFloat()));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 364 */     if (obj == this)
/* 365 */       return true; 
/* 367 */     if (!(obj instanceof FloatRange))
/* 368 */       return false; 
/* 370 */     FloatRange range = (FloatRange)obj;
/* 371 */     return (Float.floatToIntBits(this.min) == Float.floatToIntBits(range.min) && Float.floatToIntBits(this.max) == Float.floatToIntBits(range.max));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 381 */     if (this.hashCode == 0) {
/* 382 */       this.hashCode = 17;
/* 383 */       this.hashCode = 37 * this.hashCode + getClass().hashCode();
/* 384 */       this.hashCode = 37 * this.hashCode + Float.floatToIntBits(this.min);
/* 385 */       this.hashCode = 37 * this.hashCode + Float.floatToIntBits(this.max);
/*     */     } 
/* 387 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 398 */     if (this.toString == null) {
/* 399 */       StringBuffer buf = new StringBuffer(32);
/* 400 */       buf.append("Range[");
/* 401 */       buf.append(this.min);
/* 402 */       buf.append(',');
/* 403 */       buf.append(this.max);
/* 404 */       buf.append(']');
/* 405 */       this.toString = buf.toString();
/*     */     } 
/* 407 */     return this.toString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\FloatRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */