/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.DequeBasedMessageQueueSemantics;
/*     */ import akka.dispatch.Envelope;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001M1Q!\001\002\002\002\035\021!%\0212tiJ\f7\r^!di>\024x+\033;i+:\024Xm\035;sS\016$X\rZ*uCND'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001aE\002\001\0211\001\"!\003\006\016\003\tI!a\003\002\003\033\005\0237\017\036:bGR\f5\r^8s!\tIQ\"\003\002\017\005\t\tRK\034:fgR\024\030n\031;fIN#\030m\0355\t\013A\001A\021A\t\002\rqJg.\033;?)\005\021\002CA\005\001\001")
/*     */ public abstract class AbstractActorWithUnrestrictedStash extends AbstractActor implements UnrestrictedStash {
/*     */   private Vector<Envelope> akka$actor$StashSupport$$theStash;
/*     */   
/*     */   private final int akka$actor$StashSupport$$capacity;
/*     */   
/*     */   private final DequeBasedMessageQueueSemantics mailbox;
/*     */   
/*     */   public void akka$actor$UnrestrictedStash$$super$preRestart(Throwable reason, Option message) {
/* 149 */     Actor$class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void akka$actor$UnrestrictedStash$$super$postStop() {
/* 149 */     Actor$class.postStop(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) {
/* 149 */     UnrestrictedStash$class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postStop() {
/* 149 */     UnrestrictedStash$class.postStop(this);
/*     */   }
/*     */   
/*     */   public Vector<Envelope> akka$actor$StashSupport$$theStash() {
/* 149 */     return this.akka$actor$StashSupport$$theStash;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> x$1) {
/* 149 */     this.akka$actor$StashSupport$$theStash = x$1;
/*     */   }
/*     */   
/*     */   public int akka$actor$StashSupport$$capacity() {
/* 149 */     return this.akka$actor$StashSupport$$capacity;
/*     */   }
/*     */   
/*     */   public DequeBasedMessageQueueSemantics mailbox() {
/* 149 */     return this.mailbox;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int x$1) {
/* 149 */     this.akka$actor$StashSupport$$capacity = x$1;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics x$1) {
/* 149 */     this.mailbox = x$1;
/*     */   }
/*     */   
/*     */   public void stash() {
/* 149 */     StashSupport$class.stash(this);
/*     */   }
/*     */   
/*     */   public void prepend(Seq others) {
/* 149 */     StashSupport$class.prepend(this, others);
/*     */   }
/*     */   
/*     */   public void unstash() {
/* 149 */     StashSupport$class.unstash(this);
/*     */   }
/*     */   
/*     */   public void unstashAll() {
/* 149 */     StashSupport$class.unstashAll(this);
/*     */   }
/*     */   
/*     */   public void unstashAll(Function1 filterPredicate) {
/* 149 */     StashSupport$class.unstashAll(this, filterPredicate);
/*     */   }
/*     */   
/*     */   public Vector<Envelope> clearStash() {
/* 149 */     return StashSupport$class.clearStash(this);
/*     */   }
/*     */   
/*     */   public AbstractActorWithUnrestrictedStash() {
/* 149 */     StashSupport$class.$init$(this);
/* 149 */     UnrestrictedStash$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractActorWithUnrestrictedStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */