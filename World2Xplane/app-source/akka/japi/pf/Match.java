/*     */ package akka.japi.pf;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.PartialFunction;
/*     */ 
/*     */ public class Match<I, R> extends AbstractMatch<I, R> {
/*     */   public static final <F, T, P> PFBuilder<F, T> match(Class<P> paramClass, FI.Apply<P, T> paramApply) {
/*  32 */     return (new PFBuilder<F, T>()).match(paramClass, paramApply);
/*     */   }
/*     */   
/*     */   public static <F, T, P> PFBuilder<F, T> match(Class<P> paramClass, FI.TypedPredicate<P> paramTypedPredicate, FI.Apply<P, T> paramApply) {
/*  48 */     return (new PFBuilder<F, T>()).match(paramClass, paramTypedPredicate, paramApply);
/*     */   }
/*     */   
/*     */   public static <F, T, P> PFBuilder<F, T> matchEquals(P paramP, FI.Apply<P, T> paramApply) {
/*  62 */     return (new PFBuilder<F, T>()).matchEquals(paramP, paramApply);
/*     */   }
/*     */   
/*     */   public static <F, T> PFBuilder<F, T> matchAny(FI.Apply<Object, T> paramApply) {
/*  74 */     return (new PFBuilder<F, T>()).matchAny(paramApply);
/*     */   }
/*     */   
/*     */   public static final <F, T> Match<F, T> create(PFBuilder<F, T> paramPFBuilder) {
/*  84 */     return new Match<F, T>(paramPFBuilder.build());
/*     */   }
/*     */   
/*     */   Match(PartialFunction<I, R> paramPartialFunction) {
/*  88 */     super(paramPartialFunction);
/*     */   }
/*     */   
/*     */   public R match(I paramI) throws MatchError {
/* 105 */     return (R)this.statements.apply(paramI);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\Match.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */