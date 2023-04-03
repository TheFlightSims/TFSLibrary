/*     */ package akka.japi.pf;
/*     */ 
/*     */ import scala.PartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class UnitPFBuilder<I> extends AbstractPFBuilder<I, BoxedUnit> {
/*     */   public <P> UnitPFBuilder<I> match(final Class<P> type, FI.UnitApply<P> paramUnitApply) {
/*  35 */     addStatement(new UnitCaseStatement<I, P>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/*  39 */               return type.isInstance(param1Object);
/*     */             }
/*     */           },  paramUnitApply));
/*  42 */     return this;
/*     */   }
/*     */   
/*     */   public <P> UnitPFBuilder<I> match(final Class<P> type, final FI.TypedPredicate<P> predicate, FI.UnitApply<P> paramUnitApply) {
/*  56 */     addStatement(new UnitCaseStatement<I, P>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/*  60 */               if (!type.isInstance(param1Object))
/*  61 */                 return false; 
/*  64 */               Object object = param1Object;
/*  65 */               return predicate.defined(object);
/*     */             }
/*     */           },  paramUnitApply));
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public <P> UnitPFBuilder<I> matchEquals(final P object, FI.UnitApply<P> paramUnitApply) {
/*  81 */     addStatement(new UnitCaseStatement<I, P>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/*  85 */               return object.equals(param1Object);
/*     */             }
/*     */           },  paramUnitApply));
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   public <P> UnitPFBuilder<I> matchEquals(final P object, final FI.TypedPredicate<P> predicate, FI.UnitApply<P> paramUnitApply) {
/* 102 */     addStatement(new UnitCaseStatement<I, P>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/* 106 */               if (!object.equals(param1Object))
/* 107 */                 return false; 
/* 110 */               Object object = param1Object;
/* 111 */               return predicate.defined(object);
/*     */             }
/*     */           },  paramUnitApply));
/* 115 */     return this;
/*     */   }
/*     */   
/*     */   public UnitPFBuilder<I> matchAny(FI.UnitApply<Object> paramUnitApply) {
/* 124 */     addStatement(new UnitCaseStatement<I, Object>(new FI.Predicate() {
/*     */             public boolean defined(Object param1Object) {
/* 128 */               return true;
/*     */             }
/*     */           },  paramUnitApply));
/* 131 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\UnitPFBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */