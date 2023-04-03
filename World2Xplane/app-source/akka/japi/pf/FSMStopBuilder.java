/*     */ package akka.japi.pf;
/*     */ 
/*     */ import akka.actor.FSM;
/*     */ import scala.PartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public class FSMStopBuilder<S, D> {
/*  21 */   private UnitPFBuilder<FSM.StopEvent<S, D>> builder = new UnitPFBuilder<FSM.StopEvent<S, D>>();
/*     */   
/*     */   public FSMStopBuilder<S, D> stop(final FSM.Reason reason, final FI.UnitApply2<S, D> apply) {
/*  33 */     this.builder.match(FSM.StopEvent.class, new FI.TypedPredicate<FSM.StopEvent>() {
/*     */           public boolean defined(FSM.StopEvent param1StopEvent) {
/*  37 */             return reason.equals(param1StopEvent.reason());
/*     */           }
/*     */         },  new FI.UnitApply<FSM.StopEvent>() {
/*     */           public void apply(FSM.StopEvent param1StopEvent) throws Exception {
/*  43 */             Object object1 = param1StopEvent.currentState();
/*  45 */             Object object2 = param1StopEvent.stateData();
/*  46 */             apply.apply(object1, object2);
/*     */           }
/*     */         });
/*  51 */     return this;
/*     */   }
/*     */   
/*     */   public <P extends FSM.Reason> FSMStopBuilder<S, D> stop(Class<P> paramClass, FI.UnitApply3<P, S, D> paramUnitApply3) {
/*  64 */     return stop(paramClass, new FI.TypedPredicate<P>() {
/*     */           public boolean defined(P param1P) {
/*  68 */             return true;
/*     */           }
/*     */         },  paramUnitApply3);
/*     */   }
/*     */   
/*     */   public <P extends FSM.Reason> FSMStopBuilder<S, D> stop(final Class<P> reasonType, final FI.TypedPredicate<P> predicate, final FI.UnitApply3<P, S, D> apply) {
/*  85 */     this.builder.match(FSM.StopEvent.class, new FI.TypedPredicate<FSM.StopEvent>() {
/*     */           public boolean defined(FSM.StopEvent param1StopEvent) {
/*  89 */             if (reasonType.isInstance(param1StopEvent.reason())) {
/*  91 */               FSM.Reason reason = param1StopEvent.reason();
/*  92 */               return predicate.defined(reason);
/*     */             } 
/*  94 */             return false;
/*     */           }
/*     */         },  new FI.UnitApply<FSM.StopEvent>() {
/*     */           public void apply(FSM.StopEvent param1StopEvent) throws Exception {
/* 101 */             FSM.Reason reason = param1StopEvent.reason();
/* 103 */             Object object1 = param1StopEvent.currentState();
/* 105 */             Object object2 = param1StopEvent.stateData();
/* 106 */             apply.apply(reason, object1, object2);
/*     */           }
/*     */         });
/* 111 */     return this;
/*     */   }
/*     */   
/*     */   public PartialFunction<FSM.StopEvent<S, D>, BoxedUnit> build() {
/* 121 */     return this.builder.build();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\FSMStopBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */