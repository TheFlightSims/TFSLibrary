/*     */ package scala;
/*     */ 
/*     */ public final class Float$ implements AnyValCompanion {
/*     */   public static final Float$ MODULE$;
/*     */   
/*     */   private final float MinPositiveValue;
/*     */   
/*     */   private final float NaN;
/*     */   
/*     */   private final float PositiveInfinity;
/*     */   
/*     */   private final float NegativeInfinity;
/*     */   
/*     */   private final float MinValue;
/*     */   
/*     */   private final float MaxValue;
/*     */   
/*     */   private Float$() {
/* 364 */     MODULE$ = this;
/* 378 */     this.MinValue = -java.lang.Float.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public final float MinPositiveValue() {
/*     */     return java.lang.Float.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final float NaN() {
/*     */     return java.lang.Float.NaN;
/*     */   }
/*     */   
/*     */   public final float PositiveInfinity() {
/*     */     return java.lang.Float.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public final float NegativeInfinity() {
/*     */     return java.lang.Float.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public final float MinValue() {
/* 378 */     return this.MinValue;
/*     */   }
/*     */   
/*     */   public final float MaxValue() {
/* 381 */     return java.lang.Float.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public java.lang.Float box(float x) {
/* 388 */     return java.lang.Float.valueOf(x);
/*     */   }
/*     */   
/*     */   public float unbox(Object x) {
/* 398 */     return ((java.lang.Float)x).floatValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 402 */     return "object scala.Float";
/*     */   }
/*     */   
/*     */   public double float2double(float x) {
/* 406 */     return x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Float$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */