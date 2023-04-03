/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.DeadLetter;
/*     */ import akka.actor.InternalActorRef;
/*     */ 
/*     */ public abstract class BoundedDequeBasedMessageQueue$class {
/*     */   public static void $init$(BoundedDequeBasedMessageQueue $this) {}
/*     */   
/*     */   public static void enqueue(BoundedDequeBasedMessageQueue $this, ActorRef receiver, Envelope handle) {
/* 512 */     if ($this.pushTimeOut().length() >= 0L) {
/* 513 */       if (!$this.queue().offer(handle, $this.pushTimeOut().length(), $this.pushTimeOut().unit()))
/* 514 */         ((InternalActorRef)receiver).provider().deadLetters().tell(
/* 515 */             new DeadLetter(handle.message(), handle.sender(), receiver), handle.sender()); 
/*     */     } else {
/* 516 */       $this.queue().put(handle);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void enqueueFirst(BoundedDequeBasedMessageQueue $this, ActorRef receiver, Envelope handle) {
/* 519 */     if ($this.pushTimeOut().length() >= 0L) {
/* 520 */       if (!$this.queue().offerFirst(handle, $this.pushTimeOut().length(), $this.pushTimeOut().unit()))
/* 521 */         ((InternalActorRef)receiver).provider().deadLetters().tell(
/* 522 */             new DeadLetter(handle.message(), handle.sender(), receiver), handle.sender()); 
/*     */     } else {
/* 523 */       $this.queue().putFirst(handle);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Envelope dequeue(BoundedDequeBasedMessageQueue $this) {
/* 525 */     return $this.queue().poll();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedDequeBasedMessageQueue$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */