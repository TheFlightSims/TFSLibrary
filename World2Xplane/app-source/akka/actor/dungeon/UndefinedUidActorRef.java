/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefProvider;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.LocalRef;
/*     */ import akka.actor.MinimalActorRef;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\00152Q!\001\002\001\r!\021A#\0268eK\032Lg.\0323VS\022\f5\r^8s%\0264'BA\002\005\003\035!WO\\4f_:T!!\002\004\002\013\005\034Go\034:\013\003\035\tA!Y6lCN\031\001!C\007\021\005)YQ\"\001\003\n\0051!!\001E%oi\026\024h.\0317BGR|'OU3g!\tQa\"\003\002\020\t\tyQ*\0338j[\006d\027i\031;peJ+g\r\003\005\022\001\t\005\t\025!\003\024\003\r\021XMZ\002\001!\tQA#\003\002\026\t\tA\021i\031;peJ+g\rC\003\030\001\021\005\001$\001\004=S:LGO\020\013\0033m\001\"A\007\001\016\003\tAQ!\005\fA\002MAq!\b\001C\002\023\005c$\001\003qCRDW#A\020\021\005)\001\023BA\021\005\005%\t5\r^8s!\006$\b\016\003\004$\001\001\006IaH\001\006a\006$\b\016\t\005\006K\001!\tEJ\001\taJ|g/\0333feV\tq\005\005\002)W5\t\021FC\001+\003\025\0318-\0317b\023\ta\023FA\004O_RD\027N\\4")
/*     */ public class UndefinedUidActorRef extends InternalActorRef implements MinimalActorRef {
/*     */   private final ActorPath path;
/*     */   
/*     */   public InternalActorRef getParent() {
/* 202 */     return MinimalActorRef.class.getParent(this);
/*     */   }
/*     */   
/*     */   public InternalActorRef getChild(Iterator names) {
/* 202 */     return MinimalActorRef.class.getChild(this, names);
/*     */   }
/*     */   
/*     */   public void start() {
/* 202 */     MinimalActorRef.class.start(this);
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 202 */     MinimalActorRef.class.suspend(this);
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 202 */     MinimalActorRef.class.resume(this, causedByFailure);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 202 */     MinimalActorRef.class.stop(this);
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/* 202 */     return MinimalActorRef.class.isTerminated(this);
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/* 202 */     MinimalActorRef.class.$bang(this, message, sender);
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/* 202 */     MinimalActorRef.class.sendSystemMessage(this, message);
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 202 */     MinimalActorRef.class.restart(this, cause);
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 202 */     return MinimalActorRef.class.writeReplace(this);
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/* 202 */     return MinimalActorRef.class.$bang$default$2(this, message);
/*     */   }
/*     */   
/*     */   public final boolean isLocal() {
/* 202 */     return LocalRef.class.isLocal((LocalRef)this);
/*     */   }
/*     */   
/*     */   public UndefinedUidActorRef(ActorRef ref) {
/* 202 */     LocalRef.class.$init$((LocalRef)this);
/* 202 */     MinimalActorRef.class.$init$(this);
/* 203 */     this.path = ref.path().withUid(0);
/*     */   }
/*     */   
/*     */   public ActorPath path() {
/* 203 */     return this.path;
/*     */   }
/*     */   
/*     */   public Nothing$ provider() {
/* 204 */     throw new UnsupportedOperationException("UndefinedUidActorRef does not provide");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\UndefinedUidActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */