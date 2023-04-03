/*    */ package akka.actor.dungeon;
/*    */ 
/*    */ import akka.actor.Cancellable;
/*    */ import scala.Tuple2;
/*    */ import scala.concurrent.duration.Duration;
/*    */ 
/*    */ public final class ReceiveTimeout$ {
/*    */   public static final ReceiveTimeout$ MODULE$;
/*    */   
/*    */   private final Tuple2<Duration, Cancellable> emptyReceiveTimeoutData;
/*    */   
/*    */   private ReceiveTimeout$() {
/* 14 */     MODULE$ = this;
/* 15 */     this.emptyReceiveTimeoutData = new Tuple2(scala.concurrent.duration.Duration$.MODULE$.Undefined(), akka.actor.ActorCell$.MODULE$.emptyCancellable());
/*    */   }
/*    */   
/*    */   public final Tuple2<Duration, Cancellable> emptyReceiveTimeoutData() {
/* 15 */     return this.emptyReceiveTimeoutData;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\ReceiveTimeout$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */