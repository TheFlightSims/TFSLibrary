/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class RandomGroup$ extends AbstractFunction2<Iterable<String>, String, RandomGroup> implements Serializable {
/*     */   public static final RandomGroup$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 121 */     return "RandomGroup";
/*     */   }
/*     */   
/*     */   public RandomGroup apply(Iterable<String> paths, String routerDispatcher) {
/* 121 */     return new RandomGroup(paths, routerDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Iterable<String>, String>> unapply(RandomGroup x$0) {
/* 121 */     return (x$0 == null) ? (Option<Tuple2<Iterable<String>, String>>)scala.None$.MODULE$ : (Option<Tuple2<Iterable<String>, String>>)new Some(new Tuple2(x$0.paths(), x$0.routerDispatcher()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 121 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private RandomGroup$() {
/* 121 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$2() {
/* 123 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$2() {
/* 123 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RandomGroup$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */