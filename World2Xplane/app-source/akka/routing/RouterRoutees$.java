/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class RouterRoutees$ extends AbstractFunction1<IndexedSeq<ActorRef>, RouterRoutees> implements Serializable {
/*    */   public static final RouterRoutees$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 45 */     return "RouterRoutees";
/*    */   }
/*    */   
/*    */   public RouterRoutees apply(IndexedSeq<ActorRef> routees) {
/* 45 */     return new RouterRoutees(routees);
/*    */   }
/*    */   
/*    */   public Option<IndexedSeq<ActorRef>> unapply(RouterRoutees x$0) {
/* 45 */     return (x$0 == null) ? (Option<IndexedSeq<ActorRef>>)scala.None$.MODULE$ : (Option<IndexedSeq<ActorRef>>)new Some(x$0.routees());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 45 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private RouterRoutees$() {
/* 45 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RouterRoutees$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */