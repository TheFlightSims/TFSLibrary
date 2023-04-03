/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class Deafen$ extends AbstractFunction1<ActorRef, Deafen> implements Serializable {
/*    */   public static final Deafen$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 12 */     return "Deafen";
/*    */   }
/*    */   
/*    */   public Deafen apply(ActorRef listener) {
/* 12 */     return new Deafen(listener);
/*    */   }
/*    */   
/*    */   public Option<ActorRef> unapply(Deafen x$0) {
/* 12 */     return (x$0 == null) ? (Option<ActorRef>)scala.None$.MODULE$ : (Option<ActorRef>)new Some(x$0.listener());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 12 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Deafen$() {
/* 12 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Deafen$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */