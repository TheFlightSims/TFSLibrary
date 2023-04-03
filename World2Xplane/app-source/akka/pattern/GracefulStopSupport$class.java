/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.actor.Actor$;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.InternalActorRef;
/*    */ import akka.actor.PoisonPill$;
/*    */ import akka.dispatch.sysmsg.SystemMessage;
/*    */ import akka.dispatch.sysmsg.Watch;
/*    */ import akka.util.Timeout;
/*    */ import scala.Function1;
/*    */ import scala.concurrent.Future;
/*    */ import scala.concurrent.Future$;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class GracefulStopSupport$class {
/*    */   public static void $init$(GracefulStopSupport $this) {}
/*    */   
/*    */   public static Object gracefulStop$default$3(GracefulStopSupport $this) {
/* 48 */     return PoisonPill$.MODULE$;
/*    */   }
/*    */   
/*    */   public static Future gracefulStop(GracefulStopSupport $this, ActorRef target, FiniteDuration timeout, Object stopMessage) {
/* 51 */     InternalActorRef internalTarget = (InternalActorRef)target;
/* 52 */     PromiseActorRef ref = PromiseActorRef$.MODULE$.apply(internalTarget.provider(), new Timeout(timeout), target.toString());
/* 53 */     internalTarget.sendSystemMessage((SystemMessage)new Watch(internalTarget, ref));
/* 54 */     target.tell(stopMessage, Actor$.MODULE$.noSender());
/* 55 */     return target.isTerminated() ? Future$.MODULE$.successful(BoxesRunTime.boxToBoolean(true)) : ref.result().future()
/*    */       
/* 60 */       .transform((Function1)new GracefulStopSupport$$anonfun$gracefulStop$1($this, internalTarget, ref, target), (Function1)new GracefulStopSupport$$anonfun$gracefulStop$2($this, internalTarget, ref, target), ref.internalCallingThreadExecutionContext());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\GracefulStopSupport$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */