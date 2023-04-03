/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ 
/*     */ public final class ScatterGatherFirstCompletedGroup$ extends AbstractFunction3<Iterable<String>, FiniteDuration, String, ScatterGatherFirstCompletedGroup> implements Serializable {
/*     */   public static final ScatterGatherFirstCompletedGroup$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 162 */     return "ScatterGatherFirstCompletedGroup";
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedGroup apply(Iterable<String> paths, FiniteDuration within, String routerDispatcher) {
/* 162 */     return new ScatterGatherFirstCompletedGroup(paths, within, routerDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<Iterable<String>, FiniteDuration, String>> unapply(ScatterGatherFirstCompletedGroup x$0) {
/* 162 */     return (x$0 == null) ? (Option<Tuple3<Iterable<String>, FiniteDuration, String>>)scala.None$.MODULE$ : (Option<Tuple3<Iterable<String>, FiniteDuration, String>>)new Some(new Tuple3(x$0.paths(), x$0.within(), x$0.routerDispatcher()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 162 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ScatterGatherFirstCompletedGroup$() {
/* 162 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$3() {
/* 165 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$3() {
/* 165 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedGroup$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */