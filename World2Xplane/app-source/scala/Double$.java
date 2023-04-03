/*     */ package scala;
/*     */ 
/*     */ public final class Double$ implements AnyValCompanion {
/*     */   public static final Double$ MODULE$;
/*     */   
/*     */   private final double MinPositiveValue;
/*     */   
/*     */   private final double NaN;
/*     */   
/*     */   private final double PositiveInfinity;
/*     */   
/*     */   private final double NegativeInfinity;
/*     */   
/*     */   private final double MinValue;
/*     */   
/*     */   private final double MaxValue;
/*     */   
/*     */   private Double$() {
/* 364 */     MODULE$ = this;
/* 378 */     this.MinValue = -java.lang.Double.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public final double MinPositiveValue() {
/*     */     return java.lang.Double.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final double NaN() {
/*     */     return java.lang.Double.NaN;
/*     */   }
/*     */   
/*     */   public final double PositiveInfinity() {
/*     */     return java.lang.Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public final double NegativeInfinity() {
/*     */     return java.lang.Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public final double MinValue() {
/* 378 */     return this.MinValue;
/*     */   }
/*     */   
/*     */   public final double MaxValue() {
/* 381 */     return java.lang.Double.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public java.lang.Double box(double x) {
/* 388 */     return java.lang.Double.valueOf(x);
/*     */   }
/*     */   
/*     */   public double unbox(Object x) {
/* 398 */     return ((java.lang.Double)x).doubleValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 402 */     return "object scala.Double";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Double$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */