/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ 
/*     */ public abstract class UnboundedQueueBasedMessageQueue$class {
/*     */   public static void $init$(UnboundedQueueBasedMessageQueue $this) {}
/*     */   
/*     */   public static void enqueue(UnboundedQueueBasedMessageQueue $this, ActorRef receiver, Envelope handle) {
/* 453 */     $this.queue().add(handle);
/*     */   }
/*     */   
/*     */   public static Envelope dequeue(UnboundedQueueBasedMessageQueue $this) {
/* 454 */     return $this.queue().poll();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedQueueBasedMessageQueue$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */