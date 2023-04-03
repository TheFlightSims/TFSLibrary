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
/*     */ public final class SmallestMailboxRouter$ implements Serializable {
/*     */   public static final SmallestMailboxRouter$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 248 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private SmallestMailboxRouter$() {
/* 248 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter apply(Iterable routees) {
/* 253 */     Iterable<String> x$66 = (Iterable)routees.map((Function1)new SmallestMailboxRouter$$anonfun$3(), scala.collection.immutable.Iterable$.MODULE$.canBuildFrom());
/* 253 */     int x$67 = $lessinit$greater$default$1();
/* 253 */     Option<Resizer> x$68 = $lessinit$greater$default$3();
/* 253 */     String x$69 = $lessinit$greater$default$4();
/* 253 */     SupervisorStrategy x$70 = $lessinit$greater$default$5();
/* 253 */     return new SmallestMailboxRouter(x$67, x$66, x$68, x$69, x$70);
/*     */   }
/*     */   
/*     */   public static class SmallestMailboxRouter$$anonfun$3 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$3) {
/* 253 */       return x$3.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter create(Iterable routees) {
/* 259 */     return apply((Iterable<ActorRef>)akka.japi.Util$.MODULE$.immutableSeq(routees));
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 300 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> apply$default$2() {
/* 300 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$3() {
/* 300 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter apply(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 300 */     return new SmallestMailboxRouter(nrOfInstances, routees, resizer, routerDispatcher, supervisorStrategy);
/*     */   }
/*     */   
/*     */   public Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>> unapply(SmallestMailboxRouter x$0) {
/* 300 */     return (x$0 == null) ? (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)scala.None$.MODULE$ : (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.routees(), x$0.resizer(), x$0.routerDispatcher(), x$0.supervisorStrategy()));
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 300 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> $lessinit$greater$default$2() {
/* 300 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$3() {
/* 300 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String apply$default$4() {
/* 301 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$4() {
/* 301 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$5() {
/* 302 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$5() {
/* 302 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\SmallestMailboxRouter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */