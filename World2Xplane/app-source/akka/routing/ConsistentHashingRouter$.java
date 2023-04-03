/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple7;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ConsistentHashingRouter$ implements Serializable {
/*     */   public static final ConsistentHashingRouter$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  23 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ConsistentHashingRouter$() {
/*  23 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> hashMappingAdapter(ConsistentHashingRouter.ConsistentHashMapper mapper) {
/*  96 */     return (PartialFunction<Object, Object>)new ConsistentHashingRouter$$anonfun$hashMappingAdapter$1(mapper);
/*     */   }
/*     */   
/*     */   public static class ConsistentHashingRouter$$anonfun$hashMappingAdapter$1 extends AbstractPartialFunction<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ConsistentHashingRouter.ConsistentHashMapper mapper$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*  96 */       Object object2, object1 = x1;
/*  97 */       if (this.mapper$1.hashKey(object1) != null) {
/*  98 */         object2 = this.mapper$1.hashKey(object1);
/*     */       } else {
/*     */         object2 = default.apply(x1);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       boolean bool;
/*     */       Object object = x1;
/*     */       if (this.mapper$1.hashKey(object) != null) {
/*  98 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public ConsistentHashingRouter$$anonfun$hashMappingAdapter$1(ConsistentHashingRouter.ConsistentHashMapper mapper$1) {}
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter apply(Iterable routees) {
/* 106 */     Iterable<String> x$4 = (Iterable)routees.map((Function1)new ConsistentHashingRouter$$anonfun$1(), scala.collection.immutable.Iterable$.MODULE$.canBuildFrom());
/* 106 */     int x$5 = $lessinit$greater$default$1();
/* 106 */     Option<Resizer> x$6 = $lessinit$greater$default$3();
/* 106 */     String x$7 = $lessinit$greater$default$4();
/* 106 */     SupervisorStrategy x$8 = $lessinit$greater$default$5();
/* 106 */     int x$9 = $lessinit$greater$default$6();
/* 106 */     PartialFunction<Object, Object> x$10 = $lessinit$greater$default$7();
/* 106 */     return new ConsistentHashingRouter(x$5, x$4, x$6, x$7, x$8, x$9, x$10);
/*     */   }
/*     */   
/*     */   public static class ConsistentHashingRouter$$anonfun$1 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$1) {
/* 106 */       return x$1.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter create(Iterable routees) {
/* 112 */     return apply((Iterable<ActorRef>)akka.japi.Util$.MODULE$.immutableSeq(routees));
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter apply(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping) {
/* 484 */     return new ConsistentHashingRouter(nrOfInstances, routees, resizer, routerDispatcher, supervisorStrategy, virtualNodesFactor, hashMapping);
/*     */   }
/*     */   
/*     */   public Option<Tuple7<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy, Object, PartialFunction<Object, Object>>> unapply(ConsistentHashingRouter x$0) {
/* 484 */     return (x$0 == null) ? (Option<Tuple7<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy, Object, PartialFunction<Object, Object>>>)scala.None$.MODULE$ : (Option<Tuple7<Object, Iterable<String>, Option<Resizer>, String, SupervisorStrategy, Object, PartialFunction<Object, Object>>>)new Some(new Tuple7(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.routees(), x$0.resizer(), x$0.routerDispatcher(), x$0.supervisorStrategy(), BoxesRunTime.boxToInteger(x$0.virtualNodesFactor()), x$0.hashMapping()));
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 485 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> apply$default$2() {
/* 485 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$3() {
/* 485 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 485 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> $lessinit$greater$default$2() {
/* 485 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$3() {
/* 485 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String apply$default$4() {
/* 486 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$4() {
/* 486 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$5() {
/* 487 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$5() {
/* 487 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public int apply$default$6() {
/* 488 */     return 0;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$6() {
/* 488 */     return 0;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> apply$default$7() {
/* 489 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> $lessinit$greater$default$7() {
/* 489 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingRouter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */