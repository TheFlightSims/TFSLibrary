/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class DeadLetterActorRef$ implements Serializable {
/*     */   public static final DeadLetterActorRef$ MODULE$;
/*     */   
/*     */   private final DeadLetterActorRef.SerializedDeadLetterActorRef serialized;
/*     */   
/*     */   private DeadLetterActorRef$() {
/* 459 */     MODULE$ = this;
/* 466 */     this.serialized = new DeadLetterActorRef.SerializedDeadLetterActorRef();
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*     */     return MODULE$;
/*     */   }
/*     */   
/*     */   public DeadLetterActorRef.SerializedDeadLetterActorRef serialized() {
/* 466 */     return this.serialized;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DeadLetterActorRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */