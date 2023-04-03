/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Router$ extends AbstractFunction2<RoutingLogic, IndexedSeq<Routee>, Router> implements Serializable {
/*     */   public static final Router$ MODULE$;
/*     */   
/*     */   public final String toString() {
/*  95 */     return "Router";
/*     */   }
/*     */   
/*     */   public Router apply(RoutingLogic logic, IndexedSeq<Routee> routees) {
/*  95 */     return new Router(logic, routees);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<RoutingLogic, IndexedSeq<Routee>>> unapply(Router x$0) {
/*  95 */     return (x$0 == null) ? (Option<Tuple2<RoutingLogic, IndexedSeq<Routee>>>)scala.None$.MODULE$ : (Option<Tuple2<RoutingLogic, IndexedSeq<Routee>>>)new Some(new Tuple2(x$0.logic(), x$0.routees()));
/*     */   }
/*     */   
/*     */   public IndexedSeq<Routee> $lessinit$greater$default$2() {
/*  95 */     return (IndexedSeq<Routee>)scala.package$.MODULE$.Vector().empty();
/*     */   }
/*     */   
/*     */   public IndexedSeq<Routee> apply$default$2() {
/*  95 */     return (IndexedSeq<Routee>)scala.package$.MODULE$.Vector().empty();
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  95 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Router$() {
/*  95 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class Router$$anonfun$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Routee routee$1;
/*     */     
/*     */     public final boolean apply(Routee x$2) {
/* 156 */       Routee routee = this.routee$1;
/* 156 */       if (x$2 == null) {
/* 156 */         if (routee != null);
/* 156 */       } else if (x$2.equals(routee)) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public Router$$anonfun$1(Router $outer, Routee routee$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Router$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */