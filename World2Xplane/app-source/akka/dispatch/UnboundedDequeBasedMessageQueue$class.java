/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ 
/*     */ public abstract class UnboundedDequeBasedMessageQueue$class {
/*     */   public static void $init$(UnboundedDequeBasedMessageQueue $this) {}
/*     */   
/*     */   public static void enqueue(UnboundedDequeBasedMessageQueue $this, ActorRef receiver, Envelope handle) {
/* 498 */     $this.queue().add(handle);
/*     */   }
/*     */   
/*     */   public static void enqueueFirst(UnboundedDequeBasedMessageQueue $this, ActorRef receiver, Envelope handle) {
/* 499 */     $this.queue().addFirst(handle);
/*     */   }
/*     */   
/*     */   public static Envelope dequeue(UnboundedDequeBasedMessageQueue $this) {
/* 500 */     return $this.queue().poll();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedDequeBasedMessageQueue$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */