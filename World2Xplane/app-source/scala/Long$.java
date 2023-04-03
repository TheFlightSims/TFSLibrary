/*     */ package scala;
/*     */ 
/*     */ public final class Long$ implements AnyValCompanion {
/*     */   public static final Long$ MODULE$;
/*     */   
/*     */   private final long MinValue;
/*     */   
/*     */   private final long MaxValue;
/*     */   
/*     */   private Long$() {
/* 598 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final long MinValue() {
/* 601 */     return java.lang.Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final long MaxValue() {
/* 605 */     return java.lang.Long.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public java.lang.Long box(long x) {
/* 612 */     return java.lang.Long.valueOf(x);
/*     */   }
/*     */   
/*     */   public long unbox(Object x) {
/* 622 */     return ((java.lang.Long)x).longValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 626 */     return "object scala.Long";
/*     */   }
/*     */   
/*     */   public float long2float(long x) {
/* 630 */     return (float)x;
/*     */   }
/*     */   
/*     */   public double long2double(long x) {
/* 631 */     return x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Long$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */