/*     */ package scala;
/*     */ 
/*     */ public final class Int$ implements AnyValCompanion {
/*     */   public static final Int$ MODULE$;
/*     */   
/*     */   private final int MinValue;
/*     */   
/*     */   private final int MaxValue;
/*     */   
/*     */   private Int$() {
/* 598 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final int MinValue() {
/* 601 */     return Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final int MaxValue() {
/* 605 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public Integer box(int x) {
/* 612 */     return Integer.valueOf(x);
/*     */   }
/*     */   
/*     */   public int unbox(Object x) {
/* 622 */     return ((Integer)x).intValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 626 */     return "object scala.Int";
/*     */   }
/*     */   
/*     */   public long int2long(int x) {
/* 630 */     return x;
/*     */   }
/*     */   
/*     */   public float int2float(int x) {
/* 631 */     return x;
/*     */   }
/*     */   
/*     */   public double int2double(int x) {
/* 632 */     return x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Int$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */