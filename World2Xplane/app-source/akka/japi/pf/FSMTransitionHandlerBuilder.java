/*    */ package akka.japi.pf;
/*    */ 
/*    */ import scala.PartialFunction;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public class FSMTransitionHandlerBuilder<S> {
/* 20 */   private UnitPFBuilder<Tuple2<S, S>> builder = new UnitPFBuilder<Tuple2<S, S>>();
/*    */   
/*    */   public FSMTransitionHandlerBuilder<S> state(final S fromState, final S toState, final FI.UnitApplyVoid apply) {
/* 34 */     this.builder.match(Tuple2.class, new FI.TypedPredicate<Tuple2>() {
/*    */           public boolean defined(Tuple2 param1Tuple2) {
/* 38 */             return ((fromState == null || fromState.equals(param1Tuple2._1())) && (toState == null || toState.equals(param1Tuple2._2())));
/*    */           }
/*    */         },  new FI.UnitApply<Tuple2>() {
/*    */           public void apply(Tuple2 param1Tuple2) throws Exception {
/* 45 */             apply.apply();
/*    */           }
/*    */         });
/* 49 */     return this;
/*    */   }
/*    */   
/*    */   public FSMTransitionHandlerBuilder<S> state(final S fromState, final S toState, final FI.UnitApply2<S, S> apply) {
/* 63 */     this.builder.match(Tuple2.class, new FI.TypedPredicate<Tuple2>() {
/*    */           public boolean defined(Tuple2 param1Tuple2) {
/* 67 */             return ((fromState == null || fromState.equals(param1Tuple2._1())) && (toState == null || toState.equals(param1Tuple2._2())));
/*    */           }
/*    */         },  new FI.UnitApply<Tuple2>() {
/*    */           public void apply(Tuple2 param1Tuple2) throws Exception {
/* 75 */             Object object1 = param1Tuple2._1();
/* 77 */             Object object2 = param1Tuple2._2();
/* 78 */             apply.apply(object1, object2);
/*    */           }
/*    */         });
/* 82 */     return this;
/*    */   }
/*    */   
/*    */   public PartialFunction<Tuple2<S, S>, BoxedUnit> build() {
/* 92 */     return this.builder.build();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\FSMTransitionHandlerBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */