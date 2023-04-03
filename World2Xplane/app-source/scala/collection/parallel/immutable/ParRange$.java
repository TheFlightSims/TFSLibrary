/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Range;
/*     */ 
/*     */ public final class ParRange$ implements Serializable {
/*     */   public static final ParRange$ MODULE$;
/*     */   
/*     */   private ParRange$() {
/* 113 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 113 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public ParRange apply(int start, int end, int step, boolean inclusive) {
/* 114 */     return new ParRange(
/* 115 */         inclusive ? (Range)new Range.Inclusive(start, end, step) : 
/* 116 */         new Range(start, end, step));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParRange$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */