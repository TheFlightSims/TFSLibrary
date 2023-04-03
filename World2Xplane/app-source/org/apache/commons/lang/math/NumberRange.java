/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public final class NumberRange extends Range implements Serializable {
/*     */   private static final long serialVersionUID = 71849363892710L;
/*     */   
/*     */   private final Number min;
/*     */   
/*     */   private final Number max;
/*     */   
/*  53 */   private transient int hashCode = 0;
/*     */   
/*  57 */   private transient String toString = null;
/*     */   
/*     */   public NumberRange(Number num) {
/*  69 */     if (num == null)
/*  70 */       throw new IllegalArgumentException("The number must not be null"); 
/*  72 */     if (!(num instanceof Comparable))
/*  73 */       throw new IllegalArgumentException("The number must implement Comparable"); 
/*  75 */     if (num instanceof Double && ((Double)num).isNaN())
/*  76 */       throw new IllegalArgumentException("The number must not be NaN"); 
/*  78 */     if (num instanceof Float && ((Float)num).isNaN())
/*  79 */       throw new IllegalArgumentException("The number must not be NaN"); 
/*  82 */     this.min = num;
/*  83 */     this.max = num;
/*     */   }
/*     */   
/*     */   public NumberRange(Number num1, Number num2) {
/* 105 */     if (num1 == null || num2 == null)
/* 106 */       throw new IllegalArgumentException("The numbers must not be null"); 
/* 108 */     if (num1.getClass() != num2.getClass())
/* 109 */       throw new IllegalArgumentException("The numbers must be of the same type"); 
/* 111 */     if (!(num1 instanceof Comparable))
/* 112 */       throw new IllegalArgumentException("The numbers must implement Comparable"); 
/* 114 */     if (num1 instanceof Double) {
/* 115 */       if (((Double)num1).isNaN() || ((Double)num2).isNaN())
/* 116 */         throw new IllegalArgumentException("The number must not be NaN"); 
/* 118 */     } else if (num1 instanceof Float && ((
/* 119 */       (Float)num1).isNaN() || ((Float)num2).isNaN())) {
/* 120 */       throw new IllegalArgumentException("The number must not be NaN");
/*     */     } 
/* 124 */     int compare = ((Comparable)num1).compareTo(num2);
/* 125 */     if (compare == 0) {
/* 126 */       this.min = num1;
/* 127 */       this.max = num1;
/* 128 */     } else if (compare > 0) {
/* 129 */       this.min = num2;
/* 130 */       this.max = num1;
/*     */     } else {
/* 132 */       this.min = num1;
/* 133 */       this.max = num2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number getMinimumNumber() {
/* 146 */     return this.min;
/*     */   }
/*     */   
/*     */   public Number getMaximumNumber() {
/* 155 */     return this.max;
/*     */   }
/*     */   
/*     */   public boolean containsNumber(Number number) {
/* 172 */     if (number == null)
/* 173 */       return false; 
/* 175 */     if (number.getClass() != this.min.getClass())
/* 176 */       throw new IllegalArgumentException("The number must be of the same type as the range numbers"); 
/* 178 */     int compareMin = ((Comparable)this.min).compareTo(number);
/* 179 */     int compareMax = ((Comparable)this.max).compareTo(number);
/* 180 */     return (compareMin <= 0 && compareMax >= 0);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 199 */     if (obj == this)
/* 200 */       return true; 
/* 202 */     if (!(obj instanceof NumberRange))
/* 203 */       return false; 
/* 205 */     NumberRange range = (NumberRange)obj;
/* 206 */     return (this.min.equals(range.min) && this.max.equals(range.max));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 215 */     if (this.hashCode == 0) {
/* 216 */       this.hashCode = 17;
/* 217 */       this.hashCode = 37 * this.hashCode + getClass().hashCode();
/* 218 */       this.hashCode = 37 * this.hashCode + this.min.hashCode();
/* 219 */       this.hashCode = 37 * this.hashCode + this.max.hashCode();
/*     */     } 
/* 221 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 232 */     if (this.toString == null) {
/* 233 */       StrBuilder buf = new StrBuilder(32);
/* 234 */       buf.append("Range[");
/* 235 */       buf.append(this.min);
/* 236 */       buf.append(',');
/* 237 */       buf.append(this.max);
/* 238 */       buf.append(']');
/* 239 */       this.toString = buf.toString();
/*     */     } 
/* 241 */     return this.toString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\NumberRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */