/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSelection;
/*    */ import akka.util.Timeout;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.concurrent.Promise;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class ScatterGatherFirstCompletedRoutees$ extends AbstractFunction2<IndexedSeq<Routee>, FiniteDuration, ScatterGatherFirstCompletedRoutees> implements Serializable {
/*    */   public static final ScatterGatherFirstCompletedRoutees$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 42 */     return "ScatterGatherFirstCompletedRoutees";
/*    */   }
/*    */   
/*    */   public ScatterGatherFirstCompletedRoutees apply(IndexedSeq<Routee> routees, FiniteDuration within) {
/* 42 */     return new ScatterGatherFirstCompletedRoutees(routees, within);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<IndexedSeq<Routee>, FiniteDuration>> unapply(ScatterGatherFirstCompletedRoutees x$0) {
/* 42 */     return (x$0 == null) ? (Option<Tuple2<IndexedSeq<Routee>, FiniteDuration>>)scala.None$.MODULE$ : (Option<Tuple2<IndexedSeq<Routee>, FiniteDuration>>)new Some(new Tuple2(x$0.routees(), x$0.within()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 42 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ScatterGatherFirstCompletedRoutees$() {
/* 42 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class ScatterGatherFirstCompletedRoutees$$anonfun$send$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object message$1;
/*    */     
/*    */     private final Timeout timeout$1;
/*    */     
/*    */     private final Promise promise$1;
/*    */     
/*    */     public final Object apply(Routee x0$1) {
/*    */       BoxedUnit boxedUnit;
/* 49 */       Routee routee = x0$1;
/* 50 */       if (routee instanceof ActorRefRoutee) {
/* 50 */         ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/* 50 */         ActorRef ref = actorRefRoutee.ref();
/* 51 */         Promise promise = this.promise$1.tryCompleteWith(akka.pattern.AskableActorRef$.MODULE$.ask$extension(akka.pattern.package$.MODULE$.ask(ref), this.message$1, this.timeout$1));
/* 52 */       } else if (routee instanceof ActorSelectionRoutee) {
/* 52 */         ActorSelectionRoutee actorSelectionRoutee = (ActorSelectionRoutee)routee;
/* 52 */         ActorSelection sel = actorSelectionRoutee.selection();
/* 53 */         Promise promise = this.promise$1.tryCompleteWith(akka.pattern.AskableActorSelection$.MODULE$.ask$extension(akka.pattern.package$.MODULE$.ask(sel), this.message$1, this.timeout$1));
/*    */       } else {
/* 54 */         boxedUnit = BoxedUnit.UNIT;
/*    */       } 
/*    */       return boxedUnit;
/*    */     }
/*    */     
/*    */     public ScatterGatherFirstCompletedRoutees$$anonfun$send$1(ScatterGatherFirstCompletedRoutees $outer, Object message$1, Timeout timeout$1, Promise promise$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedRoutees$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */