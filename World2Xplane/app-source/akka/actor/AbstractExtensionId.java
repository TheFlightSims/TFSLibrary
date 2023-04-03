/*     */ package akka.actor;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t2Q!\001\002\002\002\035\0211#\0212tiJ\f7\r^#yi\026t7/[8o\023\022T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\001QC\001\005\026'\r\001\021b\004\t\003\0255i\021a\003\006\002\031\005)1oY1mC&\021ab\003\002\007\003:L(+\0324\021\007A\t2#D\001\003\023\t\021\"AA\006FqR,gn]5p]&#\007C\001\013\026\031\001!QA\006\001C\002]\021\021\001V\t\0031m\001\"AC\r\n\005iY!a\002(pi\"Lgn\032\t\003!qI!!\b\002\003\023\025CH/\0328tS>t\007\"B\020\001\t\003\001\023A\002\037j]&$h\bF\001\"!\r\001\002a\005")
/*     */ public abstract class AbstractExtensionId<T extends Extension> implements ExtensionId<T> {
/*     */   public T apply(ActorSystem system) {
/* 106 */     return (T)ExtensionId$class.apply(this, system);
/*     */   }
/*     */   
/*     */   public T get(ActorSystem system) {
/* 106 */     return (T)ExtensionId$class.get(this, system);
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 106 */     return ExtensionId$class.hashCode(this);
/*     */   }
/*     */   
/*     */   public final boolean equals(Object other) {
/* 106 */     return ExtensionId$class.equals(this, other);
/*     */   }
/*     */   
/*     */   public AbstractExtensionId() {
/* 106 */     ExtensionId$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractExtensionId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */