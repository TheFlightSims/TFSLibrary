/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class BroadcastGroup$ extends AbstractFunction2<Iterable<String>, String, BroadcastGroup> implements Serializable {
/*     */   public static final BroadcastGroup$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 120 */     return "BroadcastGroup";
/*     */   }
/*     */   
/*     */   public BroadcastGroup apply(Iterable<String> paths, String routerDispatcher) {
/* 120 */     return new BroadcastGroup(paths, routerDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Iterable<String>, String>> unapply(BroadcastGroup x$0) {
/* 120 */     return (x$0 == null) ? (Option<Tuple2<Iterable<String>, String>>)scala.None$.MODULE$ : (Option<Tuple2<Iterable<String>, String>>)new Some(new Tuple2(x$0.paths(), x$0.routerDispatcher()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 120 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private BroadcastGroup$() {
/* 120 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$2() {
/* 122 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$2() {
/* 122 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BroadcastGroup$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */