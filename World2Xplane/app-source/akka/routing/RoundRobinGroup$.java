/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class RoundRobinGroup$ extends AbstractFunction2<Iterable<String>, String, RoundRobinGroup> implements Serializable {
/*     */   public static final RoundRobinGroup$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 126 */     return "RoundRobinGroup";
/*     */   }
/*     */   
/*     */   public RoundRobinGroup apply(Iterable<String> paths, String routerDispatcher) {
/* 126 */     return new RoundRobinGroup(paths, routerDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Iterable<String>, String>> unapply(RoundRobinGroup x$0) {
/* 126 */     return (x$0 == null) ? (Option<Tuple2<Iterable<String>, String>>)scala.None$.MODULE$ : (Option<Tuple2<Iterable<String>, String>>)new Some(new Tuple2(x$0.paths(), x$0.routerDispatcher()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 126 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private RoundRobinGroup$() {
/* 126 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$2() {
/* 128 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$2() {
/* 128 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoundRobinGroup$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */