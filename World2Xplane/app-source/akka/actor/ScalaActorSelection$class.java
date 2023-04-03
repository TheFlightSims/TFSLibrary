/*     */ package akka.actor;
/*     */ 
/*     */ public abstract class ScalaActorSelection$class {
/*     */   public static void $init$(ActorSelection $this) {}
/*     */   
/*     */   public static ActorRef $bang$default$2(ActorSelection $this, Object msg) {
/* 232 */     return Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public static void $bang(ActorSelection $this, Object msg, ActorRef sender) {
/* 232 */     $this.tell(msg, sender);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ScalaActorSelection$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */