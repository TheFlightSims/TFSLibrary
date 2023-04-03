/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple5;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class BroadcastRouter$ implements Serializable {
/*     */   public static final BroadcastRouter$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 352 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private BroadcastRouter$() {
/* 352 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public BroadcastRouter apply(Iterable routees) {
/* 356 */     Iterable<String> x$96 = (Iterable)routees.map((Function1)new BroadcastRouter$$anonfun$4(), scala.collection.immutable.Iterable$.MODULE$.canBuildFrom());
/* 356 */     int x$97 = $lessinit$greater$default$1();
/* 356 */     Option<Resizer> x$98 = $lessinit$greater$default$3();
/* 356 */     String x$99 = $lessinit$greater$default$4();
/* 356 */     SupervisorStrategy x$100 = $lessinit$greater$default$5();
/* 356 */     return new BroadcastRouter(x$97, x$96, x$98, x$99, x$100);
/*     */   }
/*     */   
/*     */   public static class BroadcastRouter$$anonfun$4 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$4) {
/* 356 */       return x$4.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public BroadcastRouter create(Iterable routees) {
/* 362 */     return apply((Iterable<ActorRef>)akka.japi.Util$.MODULE$.immutableSeq(routees));
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 394 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> apply$default$2() {
/* 394 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$3() {
/* 394 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public BroadcastRouter apply(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 394 */     return new BroadcastRouter(nrOfInstances, routees, resizer, routerDispatcher, supervisorStrategy);
/*     */   }
/*     */   
/*     */   public Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>> unapply(BroadcastRouter x$0) {
/* 394 */     return (x$0 == null) ? (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)scala.None$.MODULE$ : (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.routees(), x$0.resizer(), x$0.routerDispatcher(), x$0.supervisorStrategy()));
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 394 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> $lessinit$greater$default$2() {
/* 394 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$3() {
/* 394 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String apply$default$4() {
/* 395 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$4() {
/* 395 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$5() {
/* 396 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$5() {
/* 396 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BroadcastRouter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */