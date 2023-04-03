/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class WithListeners$ extends AbstractFunction1<Function1<ActorRef, BoxedUnit>, WithListeners> implements Serializable {
/*    */   public static final WithListeners$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 13 */     return "WithListeners";
/*    */   }
/*    */   
/*    */   public WithListeners apply(Function1<ActorRef, BoxedUnit> f) {
/* 13 */     return new WithListeners(f);
/*    */   }
/*    */   
/*    */   public Option<Function1<ActorRef, BoxedUnit>> unapply(WithListeners x$0) {
/* 13 */     return (x$0 == null) ? (Option<Function1<ActorRef, BoxedUnit>>)scala.None$.MODULE$ : (Option<Function1<ActorRef, BoxedUnit>>)new Some(x$0.f());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 13 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private WithListeners$() {
/* 13 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\WithListeners$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */