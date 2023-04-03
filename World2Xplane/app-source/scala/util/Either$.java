/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function0;
/*     */ 
/*     */ public final class Either$ {
/*     */   public static final Either$ MODULE$;
/*     */   
/*     */   private Either$() {
/* 205 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> Either.MergeableEither<A> MergeableEither(Either<A, A> x) {
/* 218 */     return new Either.MergeableEither<A>(x);
/*     */   }
/*     */   
/*     */   public <A> Either.MergeableEither<A> either2mergeable(Either<A, A> x) {
/* 225 */     return new Either.MergeableEither<A>(x);
/*     */   }
/*     */   
/*     */   public <A, B> Either<A, B> cond(boolean test, Function0 right, Function0 left) {
/* 593 */     return test ? new Right<A, B>((B)right.apply()) : new Left<A, B>((A)left.apply());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Either$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */