/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public final class NumberRange {
/*     */   private final Number min;
/*     */   
/*     */   private final Number max;
/*     */   
/*     */   public NumberRange(Number num) {
/*  55 */     if (num == null)
/*  56 */       throw new NullPointerException("The number must not be null"); 
/*  59 */     this.min = num;
/*  60 */     this.max = num;
/*     */   }
/*     */   
/*     */   public NumberRange(Number min, Number max) {
/*  76 */     if (min == null)
/*  77 */       throw new NullPointerException("The minimum value must not be null"); 
/*  78 */     if (max == null)
/*  79 */       throw new NullPointerException("The maximum value must not be null"); 
/*  82 */     if (max.doubleValue() < min.doubleValue()) {
/*  83 */       this.min = this.max = min;
/*     */     } else {
/*  85 */       this.min = min;
/*  86 */       this.max = max;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number getMinimum() {
/*  96 */     return this.min;
/*     */   }
/*     */   
/*     */   public Number getMaximum() {
/* 105 */     return this.max;
/*     */   }
/*     */   
/*     */   public boolean includesNumber(Number number) {
/* 117 */     if (number == null)
/* 118 */       return false; 
/* 120 */     return (this.min.doubleValue() <= number.doubleValue() && this.max.doubleValue() >= number.doubleValue());
/*     */   }
/*     */   
/*     */   public boolean includesRange(NumberRange range) {
/* 134 */     if (range == null)
/* 135 */       return false; 
/* 137 */     return (includesNumber(range.min) && includesNumber(range.max));
/*     */   }
/*     */   
/*     */   public boolean overlaps(NumberRange range) {
/* 150 */     if (range == null)
/* 151 */       return false; 
/* 153 */     return (range.includesNumber(this.min) || range.includesNumber(this.max) || includesRange(range));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 167 */     if (obj == this)
/* 168 */       return true; 
/* 169 */     if (!(obj instanceof NumberRange))
/* 170 */       return false; 
/* 172 */     NumberRange range = (NumberRange)obj;
/* 173 */     return (this.min.equals(range.min) && this.max.equals(range.max));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 183 */     int result = 17;
/* 184 */     result = 37 * result + this.min.hashCode();
/* 185 */     result = 37 * result + this.max.hashCode();
/* 186 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 199 */     StrBuilder sb = new StrBuilder();
/* 201 */     if (this.min.doubleValue() < 0.0D) {
/* 202 */       sb.append('(').append(this.min).append(')');
/*     */     } else {
/* 206 */       sb.append(this.min);
/*     */     } 
/* 209 */     sb.append('-');
/* 211 */     if (this.max.doubleValue() < 0.0D) {
/* 212 */       sb.append('(').append(this.max).append(')');
/*     */     } else {
/* 216 */       sb.append(this.max);
/*     */     } 
/* 219 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\NumberRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */