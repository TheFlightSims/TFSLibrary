/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.SupervisorStrategy;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.runtime.AbstractFunction3;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class BalancingPool$ extends AbstractFunction3<Object, SupervisorStrategy, String, BalancingPool> implements Serializable {
/*    */   public static final BalancingPool$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 68 */     return "BalancingPool";
/*    */   }
/*    */   
/*    */   public BalancingPool apply(int nrOfInstances, SupervisorStrategy supervisorStrategy, String routerDispatcher) {
/* 68 */     return new BalancingPool(nrOfInstances, supervisorStrategy, routerDispatcher);
/*    */   }
/*    */   
/*    */   public Option<Tuple3<Object, SupervisorStrategy, String>> unapply(BalancingPool x$0) {
/* 68 */     return (x$0 == null) ? (Option<Tuple3<Object, SupervisorStrategy, String>>)scala.None$.MODULE$ : (Option<Tuple3<Object, SupervisorStrategy, String>>)new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.supervisorStrategy(), x$0.routerDispatcher()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 68 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private BalancingPool$() {
/* 68 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public SupervisorStrategy $lessinit$greater$default$2() {
/* 70 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*    */   }
/*    */   
/*    */   public SupervisorStrategy apply$default$2() {
/* 70 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*    */   }
/*    */   
/*    */   public String $lessinit$greater$default$3() {
/* 71 */     return "akka.actor.default-dispatcher";
/*    */   }
/*    */   
/*    */   public String apply$default$3() {
/* 71 */     return "akka.actor.default-dispatcher";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BalancingPool$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */