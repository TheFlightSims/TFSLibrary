/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.Function1;
/*     */ import scala.collection.Iterator;
/*     */ 
/*     */ public abstract class MinimalActorRef$class {
/*     */   public static void $init$(MinimalActorRef $this) {}
/*     */   
/*     */   public static InternalActorRef getParent(MinimalActorRef $this) {
/* 431 */     return Nobody$.MODULE$;
/*     */   }
/*     */   
/*     */   public static InternalActorRef getChild(MinimalActorRef $this, Iterator names) {
/* 432 */     return names.forall((Function1)new MinimalActorRef$$anonfun$getChild$1($this)) ? (InternalActorRef)$this : Nobody$.MODULE$;
/*     */   }
/*     */   
/*     */   public static void start(MinimalActorRef $this) {}
/*     */   
/*     */   public static void suspend(MinimalActorRef $this) {}
/*     */   
/*     */   public static void resume(MinimalActorRef $this, Throwable causedByFailure) {}
/*     */   
/*     */   public static void stop(MinimalActorRef $this) {}
/*     */   
/*     */   public static boolean isTerminated(MinimalActorRef $this) {
/* 438 */     return false;
/*     */   }
/*     */   
/*     */   public static void $bang(MinimalActorRef $this, Object message, ActorRef sender) {}
/*     */   
/*     */   public static ActorRef $bang$default$2(MinimalActorRef $this, Object message) {
/* 440 */     return Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public static void sendSystemMessage(MinimalActorRef $this, SystemMessage message) {}
/*     */   
/*     */   public static void restart(MinimalActorRef $this, Throwable cause) {}
/*     */   
/*     */   public static Object writeReplace(MinimalActorRef $this) throws ObjectStreamException {
/* 446 */     return SerializedActorRef$.MODULE$.apply((ActorRef)$this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\MinimalActorRef$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */