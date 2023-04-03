/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ 
/*    */ public final class Terminated$ implements Serializable {
/*    */   public static final Terminated$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 96 */     return "Terminated";
/*    */   }
/*    */   
/*    */   public Terminated apply(ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/* 96 */     return new Terminated(actor, existenceConfirmed, addressTerminated);
/*    */   }
/*    */   
/*    */   public Option<ActorRef> unapply(Terminated x$0) {
/* 96 */     return (x$0 == null) ? (Option<ActorRef>)scala.None$.MODULE$ : (Option<ActorRef>)new Some(x$0.actor());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 96 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Terminated$() {
/* 96 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Terminated$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */