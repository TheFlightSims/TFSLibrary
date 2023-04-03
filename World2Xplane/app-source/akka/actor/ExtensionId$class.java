/*     */ package akka.actor;
/*     */ 
/*     */ public abstract class ExtensionId$class {
/*     */   public static void $init$(ExtensionId $this) {}
/*     */   
/*     */   public static Extension apply(ExtensionId<Extension> $this, ActorSystem system) {
/*  79 */     return system.registerExtension($this);
/*     */   }
/*     */   
/*     */   public static Extension get(ExtensionId<Extension> $this, ActorSystem system) {
/*  91 */     return $this.apply(system);
/*     */   }
/*     */   
/*     */   public static final int hashCode(ExtensionId $this) {
/*  99 */     return System.identityHashCode($this);
/*     */   }
/*     */   
/*     */   public static final boolean equals(ExtensionId $this, Object other) {
/* 100 */     return ($this == other);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ExtensionId$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */