/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ 
/*     */ public final class UnhandledMessage$ extends AbstractFunction3<Object, ActorRef, ActorRef, UnhandledMessage> implements Serializable {
/*     */   public static final UnhandledMessage$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 245 */     return "UnhandledMessage";
/*     */   }
/*     */   
/*     */   public UnhandledMessage apply(Object message, ActorRef sender, ActorRef recipient) {
/* 245 */     return new UnhandledMessage(message, sender, recipient);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<Object, ActorRef, ActorRef>> unapply(UnhandledMessage x$0) {
/* 245 */     return (x$0 == null) ? (Option<Tuple3<Object, ActorRef, ActorRef>>)scala.None$.MODULE$ : (Option<Tuple3<Object, ActorRef, ActorRef>>)new Some(new Tuple3(x$0.message(), x$0.sender(), x$0.recipient()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 245 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private UnhandledMessage$() {
/* 245 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UnhandledMessage$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */