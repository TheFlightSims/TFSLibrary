/*     */ package akka.japi.pf;
/*     */ 
/*     */ import scala.PartialFunction;
/*     */ 
/*     */ public final class PFBuilder<I, R> extends AbstractPFBuilder<I, R> {
/*     */   public <P> PFBuilder<I, R> match(final Class<P> type, FI.Apply<P, R> paramApply) {
/*  31 */     addStatement(new CaseStatement<I, P, R>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/*  35 */               return type.isInstance(param1Object);
/*     */             }
/*     */           },  paramApply));
/*  38 */     return this;
/*     */   }
/*     */   
/*     */   public <P> PFBuilder<I, R> match(final Class<P> type, final FI.TypedPredicate<P> predicate, FI.Apply<P, R> paramApply) {
/*  52 */     addStatement(new CaseStatement<I, P, R>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/*  56 */               if (!type.isInstance(param1Object))
/*  57 */                 return false; 
/*  60 */               Object object = param1Object;
/*  61 */               return predicate.defined(object);
/*     */             }
/*     */           },  paramApply));
/*  65 */     return this;
/*     */   }
/*     */   
/*     */   public <P> PFBuilder<I, R> matchEquals(final P object, FI.Apply<P, R> paramApply) {
/*  77 */     addStatement(new CaseStatement<I, P, R>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/*  81 */               return object.equals(param1Object);
/*     */             }
/*     */           },  paramApply));
/*  84 */     return this;
/*     */   }
/*     */   
/*     */   public PFBuilder<I, R> matchAny(FI.Apply<Object, R> paramApply) {
/*  93 */     addStatement(new CaseStatement<I, Object, R>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/*  97 */               return true;
/*     */             }
/*     */           },  paramApply));
/* 100 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\PFBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */