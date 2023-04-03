/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class ActorRefRoutee$ extends AbstractFunction1<ActorRef, ActorRefRoutee> implements Serializable {
/*    */   public static final ActorRefRoutee$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 43 */     return "ActorRefRoutee";
/*    */   }
/*    */   
/*    */   public ActorRefRoutee apply(ActorRef ref) {
/* 43 */     return new ActorRefRoutee(ref);
/*    */   }
/*    */   
/*    */   public Option<ActorRef> unapply(ActorRefRoutee x$0) {
/* 43 */     return (x$0 == null) ? (Option<ActorRef>)scala.None$.MODULE$ : (Option<ActorRef>)new Some(x$0.ref());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 43 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ActorRefRoutee$() {
/* 43 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ActorRefRoutee$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */