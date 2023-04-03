/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ 
/*     */ public abstract class QueueBasedMessageQueue$class {
/*     */   public static void $init$(QueueBasedMessageQueue $this) {}
/*     */   
/*     */   public static int numberOfMessages(QueueBasedMessageQueue $this) {
/* 433 */     return $this.queue().size();
/*     */   }
/*     */   
/*     */   public static boolean hasMessages(QueueBasedMessageQueue $this) {
/* 434 */     return !$this.queue().isEmpty();
/*     */   }
/*     */   
/*     */   public static void cleanUp(QueueBasedMessageQueue $this, ActorRef owner, MessageQueue deadLetters) {
/* 436 */     if ($this.hasMessages()) {
/* 437 */       Envelope envelope = $this.dequeue();
/* 438 */       while (envelope != null) {
/* 439 */         deadLetters.enqueue(owner, envelope);
/* 440 */         envelope = $this.dequeue();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\QueueBasedMessageQueue$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */