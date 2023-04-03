/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public final class DoubleRange extends Range implements Serializable {
/*     */   private static final long serialVersionUID = 71849363892740L;
/*     */   
/*     */   private final double min;
/*     */   
/*     */   private final double max;
/*     */   
/*  51 */   private transient Double minObject = null;
/*     */   
/*  55 */   private transient Double maxObject = null;
/*     */   
/*  59 */   private transient int hashCode = 0;
/*     */   
/*  63 */   private transient String toString = null;
/*     */   
/*     */   public DoubleRange(double number) {
/*  74 */     if (Double.isNaN(number))
/*  75 */       throw new IllegalArgumentException("The number must not be NaN"); 
/*  77 */     this.min = number;
/*  78 */     this.max = number;
/*     */   }
/*     */   
/*     */   public DoubleRange(Number number) {
/*  92 */     if (number == null)
/*  93 */       throw new IllegalArgumentException("The number must not be null"); 
/*  95 */     this.min = number.doubleValue();
/*  96 */     this.max = number.doubleValue();
/*  97 */     if (Double.isNaN(this.min) || Double.isNaN(this.max))
/*  98 */       throw new IllegalArgumentException("The number must not be NaN"); 
/* 100 */     if (number instanceof Double) {
/* 101 */       this.minObject = (Double)number;
/* 102 */       this.maxObject = (Double)number;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DoubleRange(double number1, double number2) {
/* 119 */     if (Double.isNaN(number1) || Double.isNaN(number2))
/* 120 */       throw new IllegalArgumentException("The numbers must not be NaN"); 
/* 122 */     if (number2 < number1) {
/* 123 */       this.min = number2;
/* 124 */       this.max = number1;
/*     */     } else {
/* 126 */       this.min = number1;
/* 127 */       this.max = number2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DoubleRange(Number number1, Number number2) {
/* 145 */     if (number1 == null || number2 == null)
/* 146 */       throw new IllegalArgumentException("The numbers must not be null"); 
/* 148 */     double number1val = number1.doubleValue();
/* 149 */     double number2val = number2.doubleValue();
/* 150 */     if (Double.isNaN(number1val) || Double.isNaN(number2val))
/* 151 */       throw new IllegalArgumentException("The numbers must not be NaN"); 
/* 153 */     if (number2val < number1val) {
/* 154 */       this.min = number2val;
/* 155 */       this.max = number1val;
/* 156 */       if (number2 instanceof Double)
/* 157 */         this.minObject = (Double)number2; 
/* 159 */       if (number1 instanceof Double)
/* 160 */         this.maxObject = (Double)number1; 
/*     */     } else {
/* 163 */       this.min = number1val;
/* 164 */       this.max = number2val;
/* 165 */       if (number1 instanceof Double)
/* 166 */         this.minObject = (Double)number1; 
/* 168 */       if (number2 instanceof Double)
/* 169 */         this.maxObject = (Double)number2; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number getMinimumNumber() {
/* 183 */     if (this.minObject == null)
/* 184 */       this.minObject = new Double(this.min); 
/* 186 */     return this.minObject;
/*     */   }
/*     */   
/*     */   public long getMinimumLong() {
/* 197 */     return (long)this.min;
/*     */   }
/*     */   
/*     */   public int getMinimumInteger() {
/* 208 */     return (int)this.min;
/*     */   }
/*     */   
/*     */   public double getMinimumDouble() {
/* 217 */     return this.min;
/*     */   }
/*     */   
/*     */   public float getMinimumFloat() {
/* 228 */     return (float)this.min;
/*     */   }
/*     */   
/*     */   public Number getMaximumNumber() {
/* 237 */     if (this.maxObject == null)
/* 238 */       this.maxObject = new Double(this.max); 
/* 240 */     return this.maxObject;
/*     */   }
/*     */   
/*     */   public long getMaximumLong() {
/* 251 */     return (long)this.max;
/*     */   }
/*     */   
/*     */   public int getMaximumInteger() {
/* 262 */     return (int)this.max;
/*     */   }
/*     */   
/*     */   public double getMaximumDouble() {
/* 271 */     return this.max;
/*     */   }
/*     */   
/*     */   public float getMaximumFloat() {
/* 282 */     return (float)this.max;
/*     */   }
/*     */   
/*     */   public boolean containsNumber(Number number) {
/* 298 */     if (number == null)
/* 299 */       return false; 
/* 301 */     return containsDouble(number.doubleValue());
/*     */   }
/*     */   
/*     */   public boolean containsDouble(double value) {
/* 316 */     return (value >= this.min && value <= this.max);
/*     */   }
/*     */   
/*     */   public boolean containsRange(Range range) {
/* 333 */     if (range == null)
/* 334 */       return false; 
/* 336 */     return (containsDouble(range.getMinimumDouble()) && containsDouble(range.getMaximumDouble()));
/*     */   }
/*     */   
/*     */   public boolean overlapsRange(Range range) {
/* 350 */     if (range == null)
/* 351 */       return false; 
/* 353 */     return (range.containsDouble(this.min) || range.containsDouble(this.max) || containsDouble(range.getMinimumDouble()));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 370 */     if (obj == this)
/* 371 */       return true; 
/* 373 */     if (!(obj instanceof DoubleRange))
/* 374 */       return false; 
/* 376 */     DoubleRange range = (DoubleRange)obj;
/* 377 */     return (Double.doubleToLongBits(this.min) == Double.doubleToLongBits(range.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(range.max));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 387 */     if (this.hashCode == 0) {
/* 388 */       this.hashCode = 17;
/* 389 */       this.hashCode = 37 * this.hashCode + getClass().hashCode();
/* 390 */       long lng = Double.doubleToLongBits(this.min);
/* 391 */       this.hashCode = 37 * this.hashCode + (int)(lng ^ lng >> 32L);
/* 392 */       lng = Double.doubleToLongBits(this.max);
/* 393 */       this.hashCode = 37 * this.hashCode + (int)(lng ^ lng >> 32L);
/*     */     } 
/* 395 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 406 */     if (this.toString == null) {
/* 407 */       StrBuilder buf = new StrBuilder(32);
/* 408 */       buf.append("Range[");
/* 409 */       buf.append(this.min);
/* 410 */       buf.append(',');
/* 411 */       buf.append(this.max);
/* 412 */       buf.append(']');
/* 413 */       this.toString = buf.toString();
/*     */     } 
/* 415 */     return this.toString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\DoubleRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */