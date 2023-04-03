/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.DeadLetter;
/*     */ import akka.actor.InternalActorRef;
/*     */ 
/*     */ public abstract class BoundedQueueBasedMessageQueue$class {
/*     */   public static void $init$(BoundedQueueBasedMessageQueue $this) {}
/*     */   
/*     */   public static void enqueue(BoundedQueueBasedMessageQueue $this, ActorRef receiver, Envelope handle) {
/* 469 */     if ($this.pushTimeOut().length() >= 0L) {
/* 470 */       if (!$this.queue().offer(handle, $this.pushTimeOut().length(), $this.pushTimeOut().unit()))
/* 471 */         ((InternalActorRef)receiver).provider().deadLetters().tell(
/* 472 */             new DeadLetter(handle.message(), handle.sender(), receiver), handle.sender()); 
/*     */     } else {
/* 473 */       $this.queue().put(handle);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Envelope dequeue(BoundedQueueBasedMessageQueue $this) {
/* 475 */     return $this.queue().poll();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedQueueBasedMessageQueue$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */