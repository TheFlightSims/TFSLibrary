/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSelection;
/*    */ import akka.actor.Scheduler;
/*    */ import akka.util.Timeout;
/*    */ import scala.Function0;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.Future;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ 
/*    */ public final class package$ implements PipeToSupport, AskSupport, GracefulStopSupport, FutureTimeoutSupport {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   public <T> Future<T> after(FiniteDuration duration, Scheduler using, Function0 value, ExecutionContext ec) {
/* 43 */     return FutureTimeoutSupport$class.after(this, duration, using, value, ec);
/*    */   }
/*    */   
/*    */   public Future<Object> gracefulStop(ActorRef target, FiniteDuration timeout, Object stopMessage) {
/* 43 */     return GracefulStopSupport$class.gracefulStop(this, target, timeout, stopMessage);
/*    */   }
/*    */   
/*    */   public Object gracefulStop$default$3() {
/* 43 */     return GracefulStopSupport$class.gracefulStop$default$3(this);
/*    */   }
/*    */   
/*    */   public ActorRef ask(ActorRef actorRef) {
/* 43 */     return AskSupport$class.ask(this, actorRef);
/*    */   }
/*    */   
/*    */   public Future<Object> ask(ActorRef actorRef, Object message, Timeout timeout) {
/* 43 */     return AskSupport$class.ask(this, actorRef, message, timeout);
/*    */   }
/*    */   
/*    */   public ActorSelection ask(ActorSelection actorSelection) {
/* 43 */     return AskSupport$class.ask(this, actorSelection);
/*    */   }
/*    */   
/*    */   public Future<Object> ask(ActorSelection actorSelection, Object message, Timeout timeout) {
/* 43 */     return AskSupport$class.ask(this, actorSelection, message, timeout);
/*    */   }
/*    */   
/*    */   public <T> PipeToSupport.PipeableFuture<T> pipe(Future future, ExecutionContext executionContext) {
/* 43 */     return PipeToSupport$class.pipe(this, future, executionContext);
/*    */   }
/*    */   
/*    */   private package$() {
/* 43 */     MODULE$ = this;
/* 43 */     PipeToSupport$class.$init$(this);
/* 43 */     AskSupport$class.$init$(this);
/* 43 */     GracefulStopSupport$class.$init$(this);
/* 43 */     FutureTimeoutSupport$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */