/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.Envelope$;
/*     */ 
/*     */ public abstract class Cell$class {
/*     */   public static void $init$(Cell $this) {}
/*     */   
/*     */   public static final void sendMessage(Cell $this, Object message, ActorRef sender) {
/* 290 */     $this.sendMessage(Envelope$.MODULE$.apply(message, sender, $this.system()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Cell$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */