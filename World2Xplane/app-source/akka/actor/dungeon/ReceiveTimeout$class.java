/*    */ package akka.actor.dungeon;
/*    */ 
/*    */ import akka.actor.ActorCell;
/*    */ import akka.actor.ActorCell$;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.Cancellable;
/*    */ import akka.actor.InternalActorRef;
/*    */ import akka.actor.ReceiveTimeout$;
/*    */ import akka.dispatch.MessageDispatcher;
/*    */ import scala.Tuple2;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.duration.Duration;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public abstract class ReceiveTimeout$class {
/*    */   public static void $init$(ActorCell $this) {
/* 23 */     $this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData_$eq(ReceiveTimeout$.MODULE$.emptyReceiveTimeoutData());
/*    */   }
/*    */   
/*    */   public static final Duration receiveTimeout(ActorCell $this) {
/* 25 */     return (Duration)$this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData()._1();
/*    */   }
/*    */   
/*    */   public static final void setReceiveTimeout(ActorCell $this, Duration timeout) {
/* 27 */     Tuple2 qual$1 = $this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData();
/* 27 */     Duration x$1 = timeout;
/* 27 */     Cancellable x$2 = (Cancellable)qual$1.copy$default$2();
/* 27 */     $this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData_$eq(qual$1.copy(x$1, x$2));
/*    */   }
/*    */   
/*    */   public static final void checkReceiveTimeout(ActorCell $this) {
/* 30 */     Tuple2 recvtimeout = $this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData();
/* 32 */     if ($this.mailbox().hasMessages()) {
/* 39 */       $this.cancelReceiveTimeout();
/*    */     } else {
/*    */       Duration duration = (Duration)recvtimeout._1();
/*    */       if (duration instanceof FiniteDuration) {
/*    */         FiniteDuration finiteDuration1 = (FiniteDuration)duration;
/*    */         ((Cancellable)recvtimeout._2()).cancel();
/*    */         FiniteDuration x$3 = finiteDuration1;
/*    */         InternalActorRef x$4 = $this.self();
/*    */         ReceiveTimeout$ x$5 = ReceiveTimeout$.MODULE$;
/*    */         MessageDispatcher x$6 = $this.dispatcher();
/*    */         ActorRef x$7 = $this.system().scheduler().scheduleOnce$default$5(x$3, (ActorRef)x$4, x$5);
/*    */         Cancellable task = $this.system().scheduler().scheduleOnce(x$3, (ActorRef)x$4, x$5, (ExecutionContext)x$6, x$7);
/*    */         $this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData_$eq(new Tuple2(finiteDuration1, task));
/*    */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*    */       } else {
/*    */         $this.cancelReceiveTimeout();
/*    */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static final void cancelReceiveTimeout(ActorCell $this) {
/* 44 */     if ($this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData()._2() != ActorCell$.MODULE$.emptyCancellable()) {
/* 45 */       ((Cancellable)$this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData()._2()).cancel();
/* 46 */       $this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData_$eq(new Tuple2($this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData()._1(), ActorCell$.MODULE$.emptyCancellable()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\ReceiveTimeout$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */