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
/*     */ public final class RandomRouter$ implements Serializable {
/*     */   public static final RandomRouter$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 154 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private RandomRouter$() {
/* 154 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public RandomRouter apply(Iterable routees) {
/* 158 */     Iterable<String> x$36 = (Iterable)routees.map((Function1)new RandomRouter$$anonfun$2(), scala.collection.immutable.Iterable$.MODULE$.canBuildFrom());
/* 158 */     int x$37 = $lessinit$greater$default$1();
/* 158 */     Option<Resizer> x$38 = $lessinit$greater$default$3();
/* 158 */     String x$39 = $lessinit$greater$default$4();
/* 158 */     SupervisorStrategy x$40 = $lessinit$greater$default$5();
/* 158 */     return new RandomRouter(x$37, x$36, x$38, x$39, x$40);
/*     */   }
/*     */   
/*     */   public static class RandomRouter$$anonfun$2 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$2) {
/* 158 */       return x$2.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public RandomRouter create(Iterable routees) {
/* 164 */     return apply((Iterable<ActorRef>)akka.japi.Util$.MODULE$.immutableSeq(routees));
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 196 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> apply$default$2() {
/* 196 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$3() {
/* 196 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public RandomRouter apply(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 196 */     return new RandomRouter(nrOfInstances, routees, resizer, routerDispatcher, supervisorStrategy);
/*     */   }
/*     */   
/*     */   public Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>> unapply(RandomRouter x$0) {
/* 196 */     return (x$0 == null) ? (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)scala.None$.MODULE$ : (Option<Tuple5<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy>>)new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.routees(), x$0.resizer(), x$0.routerDispatcher(), x$0.supervisorStrategy()));
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 196 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> $lessinit$greater$default$2() {
/* 196 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$3() {
/* 196 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String apply$default$4() {
/* 197 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$4() {
/* 197 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$5() {
/* 198 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$5() {
/* 198 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RandomRouter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */