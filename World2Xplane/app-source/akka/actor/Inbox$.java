/*     */ package akka.actor;
/*     */ 
/*     */ public final class Inbox$ {
/*     */   public static final Inbox$ MODULE$;
/*     */   
/*     */   private Inbox$() {
/* 147 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Inbox create(ActorSystem system) {
/* 151 */     return (Inbox)ActorDSL$.MODULE$.inbox(system);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Inbox$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */