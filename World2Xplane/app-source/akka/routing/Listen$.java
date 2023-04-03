/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class Listen$ extends AbstractFunction1<ActorRef, Listen> implements Serializable {
/*    */   public static final Listen$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 11 */     return "Listen";
/*    */   }
/*    */   
/*    */   public Listen apply(ActorRef listener) {
/* 11 */     return new Listen(listener);
/*    */   }
/*    */   
/*    */   public Option<ActorRef> unapply(Listen x$0) {
/* 11 */     return (x$0 == null) ? (Option<ActorRef>)scala.None$.MODULE$ : (Option<ActorRef>)new Some(x$0.listener());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 11 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Listen$() {
/* 11 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Listen$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */