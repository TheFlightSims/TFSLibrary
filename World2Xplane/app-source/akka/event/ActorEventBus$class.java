/*    */ package akka.event;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ 
/*    */ public abstract class ActorEventBus$class {
/*    */   public static void $init$(ActorEventBus $this) {}
/*    */   
/*    */   public static int compareSubscribers(ActorEventBus $this, ActorRef a, ActorRef b) {
/* 58 */     return a.compareTo(b);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\ActorEventBus$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */