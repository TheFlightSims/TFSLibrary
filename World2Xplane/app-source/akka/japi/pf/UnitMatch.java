/*     */ package akka.japi.pf;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.PartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public class UnitMatch<I> extends AbstractMatch<I, BoxedUnit> {
/*     */   public static final <F, P> UnitPFBuilder<F> match(Class<P> paramClass, FI.UnitApply<P> paramUnitApply) {
/*  33 */     return (new UnitPFBuilder<F>()).match(paramClass, paramUnitApply);
/*     */   }
/*     */   
/*     */   public static <F, P> UnitPFBuilder<F> match(Class<P> paramClass, FI.TypedPredicate<P> paramTypedPredicate, FI.UnitApply<P> paramUnitApply) {
/*  49 */     return (new UnitPFBuilder<F>()).match(paramClass, paramTypedPredicate, paramUnitApply);
/*     */   }
/*     */   
/*     */   public static <F, P> UnitPFBuilder<F> matchEquals(P paramP, FI.UnitApply<P> paramUnitApply) {
/*  63 */     return (new UnitPFBuilder<F>()).matchEquals(paramP, paramUnitApply);
/*     */   }
/*     */   
/*     */   public static <F, P> UnitPFBuilder<F> matchEquals(P paramP, FI.TypedPredicate<P> paramTypedPredicate, FI.UnitApply<P> paramUnitApply) {
/*  79 */     return (new UnitPFBuilder<F>()).matchEquals(paramP, paramTypedPredicate, paramUnitApply);
/*     */   }
/*     */   
/*     */   public static <F> UnitPFBuilder<F> matchAny(FI.UnitApply<Object> paramUnitApply) {
/*  91 */     return (new UnitPFBuilder<F>()).matchAny(paramUnitApply);
/*     */   }
/*     */   
/*     */   public static <F> UnitMatch<F> create(UnitPFBuilder<F> paramUnitPFBuilder) {
/* 101 */     return new UnitMatch<F>(paramUnitPFBuilder.build());
/*     */   }
/*     */   
/*     */   private UnitMatch(PartialFunction<I, BoxedUnit> paramPartialFunction) {
/* 105 */     super(paramPartialFunction);
/*     */   }
/*     */   
/*     */   public void match(I paramI) throws MatchError {
/* 121 */     this.statements.apply(paramI);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\UnitMatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */