/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Range implements Serializable {
/*     */   private static final long serialVersionUID = -906333695431863380L;
/*     */   
/*     */   private double lower;
/*     */   
/*     */   private double upper;
/*     */   
/*     */   public strictfp Range(double lower, double upper) {
/*  84 */     if (lower > upper) {
/*  85 */       String msg = "Range(double, double): require lower (" + lower + ") <= upper (" + upper + ").";
/*  87 */       throw new IllegalArgumentException(msg);
/*     */     } 
/*  89 */     this.lower = lower;
/*  90 */     this.upper = upper;
/*     */   }
/*     */   
/*     */   public strictfp double getLowerBound() {
/*  99 */     return this.lower;
/*     */   }
/*     */   
/*     */   public strictfp double getUpperBound() {
/* 108 */     return this.upper;
/*     */   }
/*     */   
/*     */   public strictfp double getLength() {
/* 117 */     return this.upper - this.lower;
/*     */   }
/*     */   
/*     */   public strictfp double getCentralValue() {
/* 126 */     return this.lower / 2.0D + this.upper / 2.0D;
/*     */   }
/*     */   
/*     */   public strictfp boolean contains(double value) {
/* 138 */     return (value >= this.lower && value <= this.upper);
/*     */   }
/*     */   
/*     */   public strictfp boolean intersects(double b0, double b1) {
/* 151 */     if (b0 <= this.lower)
/* 152 */       return (b1 > this.lower); 
/* 155 */     return (b0 < this.upper && b1 >= b0);
/*     */   }
/*     */   
/*     */   public strictfp double constrain(double value) {
/* 168 */     double result = value;
/* 169 */     if (!contains(value))
/* 170 */       if (value > this.upper) {
/* 171 */         result = this.upper;
/* 173 */       } else if (value < this.lower) {
/* 174 */         result = this.lower;
/*     */       }  
/* 177 */     return result;
/*     */   }
/*     */   
/*     */   public static strictfp Range combine(Range range1, Range range2) {
/* 197 */     if (range1 == null)
/* 198 */       return range2; 
/* 201 */     if (range2 == null)
/* 202 */       return range1; 
/* 205 */     double l = Math.min(range1.getLowerBound(), range2.getLowerBound());
/* 208 */     double u = Math.max(range1.getUpperBound(), range2.getUpperBound());
/* 211 */     return new Range(l, u);
/*     */   }
/*     */   
/*     */   public static strictfp Range expand(Range range, double lowerMargin, double upperMargin) {
/* 229 */     if (range == null)
/* 230 */       throw new IllegalArgumentException("Null 'range' argument."); 
/* 232 */     double length = range.getLength();
/* 233 */     double lower = length * lowerMargin;
/* 234 */     double upper = length * upperMargin;
/* 235 */     return new Range(range.getLowerBound() - lower, range.getUpperBound() + upper);
/*     */   }
/*     */   
/*     */   public static strictfp Range shift(Range base, double delta) {
/* 249 */     return shift(base, delta, false);
/*     */   }
/*     */   
/*     */   public static strictfp Range shift(Range base, double delta, boolean allowZeroCrossing) {
/* 265 */     if (allowZeroCrossing)
/* 266 */       return new Range(base.getLowerBound() + delta, base.getUpperBound() + delta); 
/* 271 */     return new Range(shiftWithNoZeroCrossing(base.getLowerBound(), delta), shiftWithNoZeroCrossing(base.getUpperBound(), delta));
/*     */   }
/*     */   
/*     */   private static strictfp double shiftWithNoZeroCrossing(double value, double delta) {
/* 279 */     if (value > 0.0D)
/* 280 */       return Math.max(value + delta, 0.0D); 
/* 282 */     if (value < 0.0D)
/* 283 */       return Math.min(value + delta, 0.0D); 
/* 286 */     return value + delta;
/*     */   }
/*     */   
/*     */   public strictfp boolean equals(Object obj) {
/* 298 */     if (!(obj instanceof Range))
/* 299 */       return false; 
/* 301 */     Range range = (Range)obj;
/* 302 */     if (this.lower != range.lower)
/* 303 */       return false; 
/* 305 */     if (this.upper != range.upper)
/* 306 */       return false; 
/* 308 */     return true;
/*     */   }
/*     */   
/*     */   public strictfp int hashCode() {
/* 319 */     long temp = Double.doubleToLongBits(this.lower);
/* 320 */     int result = (int)(temp ^ temp >>> 32L);
/* 321 */     temp = Double.doubleToLongBits(this.upper);
/* 322 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 323 */     return result;
/*     */   }
/*     */   
/*     */   public strictfp String toString() {
/* 333 */     return "Range[" + this.lower + "," + this.upper + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\Range.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */