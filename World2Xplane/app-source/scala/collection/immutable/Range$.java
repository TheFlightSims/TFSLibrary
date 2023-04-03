/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class Range$ implements Serializable {
/*     */   public static final Range$ MODULE$;
/*     */   
/*     */   private final int MAX_PRINT;
/*     */   
/*     */   private Object readResolve() {
/* 296 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Range$() {
/* 296 */     MODULE$ = this;
/* 297 */     this.MAX_PRINT = 512;
/*     */   }
/*     */   
/*     */   public int MAX_PRINT() {
/* 297 */     return this.MAX_PRINT;
/*     */   }
/*     */   
/*     */   public int count(int start, int end, int step, boolean isInclusive) {
/* 305 */     if (step == 0)
/* 306 */       throw new IllegalArgumentException("step cannot be 0."); 
/* 308 */     boolean isEmpty = 
/* 309 */       (start == end) ? (!isInclusive) : (
/* 310 */       (start < end) ? ((step < 0)) : (
/* 311 */       (step > 0)));
/* 316 */     long gap = end - start;
/* 317 */     long jumps = gap / step;
/* 320 */     boolean hasStub = (isInclusive || gap % step != 0L);
/* 321 */     long result = jumps + (hasStub ? 1L : 0L);
/* 323 */     return isEmpty ? 0 : ((result > 2147483647L) ? -1 : 
/* 324 */       (int)result);
/*     */   }
/*     */   
/*     */   public int count(int start, int end, int step) {
/* 328 */     return count(start, end, step, false);
/*     */   }
/*     */   
/*     */   public Range apply(int start, int end, int step) {
/* 339 */     return new Range(start, end, step);
/*     */   }
/*     */   
/*     */   public Range apply(int start, int end) {
/* 343 */     return new Range(start, end, 1);
/*     */   }
/*     */   
/*     */   public Range.Inclusive inclusive(int start, int end, int step) {
/* 348 */     return new Range.Inclusive(start, end, step);
/*     */   }
/*     */   
/*     */   public Range.Inclusive inclusive(int start, int end) {
/* 352 */     return new Range.Inclusive(start, end, 1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Range$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */