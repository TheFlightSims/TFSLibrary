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
/*     */ public final class RoundRobinRouter$ implements Serializable {
/*     */   public static final RoundRobinRouter$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  59 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private RoundRobinRouter$() {
/*  59 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public RoundRobinRouter apply(Iterable routees) {
/*  64 */     Iterable<String> x$6 = (Iterable)routees.map((Function1)new RoundRobinRouter$$anonfun$1(), scala.collection.immutable.Iterable$.MODULE$.canBuildFrom());
/*  64 */     int x$7 = $lessinit$greater$default$1();
/*  64 */     Option<Resizer> x$8 = $lessinit$greater$default$3();
/*  64 */     String x$9 = $lessinit$greater$default$4();
/*  64 */     SupervisorStrategy x$10 = $lessinit$greater$default$5();
/*  64 */     return new RoundRobinRouter(x$7, x$6, x$8, x$9, x$10);
/*     */   }
/*     */   
/*     */   public static class RoundRobinRouter$$anonfun$1 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$1) {
/*  64 */       return x$1.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public RoundRobinRouter create(Iterable routees) {
/*  70 */     return apply((Iterable<ActorRef>)akka.japi.Util$.MODULE$.immutableSeq(routees));
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 102 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> apply$default$2() {
/* 102 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$3() {
/* 102 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public RoundRobinRouter apply(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 102 */     return new RoundRobinRouter(nrOfInstances, routees, resizer, routerDispatcher, supervisorStrategy);
/*     */   }
/*     */   
/*     */   public Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>> unapply(RoundRobinRouter x$0) {
/* 102 */     return (x$0 == null) ? (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)scala.None$.MODULE$ : (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.routees(), x$0.resizer(), x$0.routerDispatcher(), x$0.supervisorStrategy()));
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 102 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> $lessinit$greater$default$2() {
/* 102 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$3() {
/* 102 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String apply$default$4() {
/* 103 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$4() {
/* 103 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$5() {
/* 104 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$5() {
/* 104 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoundRobinRouter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */