/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ConsistentHashingGroup$ extends AbstractFunction4<Iterable<String>, Object, PartialFunction<Object, Object>, String, ConsistentHashingGroup> implements Serializable {
/*     */   public static final ConsistentHashingGroup$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 361 */     return "ConsistentHashingGroup";
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup apply(Iterable<String> paths, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping, String routerDispatcher) {
/* 361 */     return new ConsistentHashingGroup(paths, virtualNodesFactor, hashMapping, routerDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple4<Iterable<String>, Object, PartialFunction<Object, Object>, String>> unapply(ConsistentHashingGroup x$0) {
/* 361 */     return (x$0 == null) ? (Option<Tuple4<Iterable<String>, Object, PartialFunction<Object, Object>, String>>)scala.None$.MODULE$ : (Option<Tuple4<Iterable<String>, Object, PartialFunction<Object, Object>, String>>)new Some(new Tuple4(x$0.paths(), BoxesRunTime.boxToInteger(x$0.virtualNodesFactor()), x$0.hashMapping(), x$0.routerDispatcher()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 361 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ConsistentHashingGroup$() {
/* 361 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$2() {
/* 363 */     return 0;
/*     */   }
/*     */   
/*     */   public int apply$default$2() {
/* 363 */     return 0;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> $lessinit$greater$default$3() {
/* 364 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> apply$default$3() {
/* 364 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$4() {
/* 365 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$4() {
/* 365 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingGroup$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */