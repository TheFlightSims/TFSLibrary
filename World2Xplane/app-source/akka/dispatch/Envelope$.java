/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.InvalidMessageException;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ 
/*    */ public final class Envelope$ implements Serializable {
/*    */   public static final Envelope$ MODULE$;
/*    */   
/*    */   public Envelope apply(Object message, ActorRef sender) {
/* 24 */     return new Envelope(message, sender);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, ActorRef>> unapply(Envelope x$0) {
/* 24 */     return (x$0 == null) ? (Option<Tuple2<Object, ActorRef>>)scala.None$.MODULE$ : (Option<Tuple2<Object, ActorRef>>)new Some(new Tuple2(x$0.message(), x$0.sender()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 26 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Envelope$() {
/* 26 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Envelope apply(Object message, ActorRef sender, ActorSystem system) {
/* 28 */     if (message == null)
/* 28 */       throw new InvalidMessageException("Message is null"); 
/* 29 */     return new Envelope(message, (sender != akka.actor.Actor$.MODULE$.noSender()) ? sender : system.deadLetters());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Envelope$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */