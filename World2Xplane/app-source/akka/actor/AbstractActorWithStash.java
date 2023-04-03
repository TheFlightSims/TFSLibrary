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
/*     */ @ScalaSignature(bytes = "\006\001M1Q!\001\002\002\002\035\021a#\0212tiJ\f7\r^!di>\024x+\033;i'R\f7\017\033\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\001\001\024\007\001AA\002\005\002\n\0255\t!!\003\002\f\005\ti\021IY:ue\006\034G/Q2u_J\004\"!C\007\n\0059\021!!B*uCND\007\"\002\t\001\t\003\t\022A\002\037j]&$h\bF\001\023!\tI\001\001")
/*     */ public abstract class AbstractActorWithStash extends AbstractActor implements Stash {
/*     */   private Vector<Envelope> akka$actor$StashSupport$$theStash;
/*     */   
/*     */   private final int akka$actor$StashSupport$$capacity;
/*     */   
/*     */   private final DequeBasedMessageQueueSemantics mailbox;
/*     */   
/*     */   public void akka$actor$UnrestrictedStash$$super$preRestart(Throwable reason, Option message) {
/* 128 */     Actor$class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void akka$actor$UnrestrictedStash$$super$postStop() {
/* 128 */     Actor$class.postStop(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) {
/* 128 */     UnrestrictedStash$class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postStop() {
/* 128 */     UnrestrictedStash$class.postStop(this);
/*     */   }
/*     */   
/*     */   public Vector<Envelope> akka$actor$StashSupport$$theStash() {
/* 128 */     return this.akka$actor$StashSupport$$theStash;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> x$1) {
/* 128 */     this.akka$actor$StashSupport$$theStash = x$1;
/*     */   }
/*     */   
/*     */   public int akka$actor$StashSupport$$capacity() {
/* 128 */     return this.akka$actor$StashSupport$$capacity;
/*     */   }
/*     */   
/*     */   public DequeBasedMessageQueueSemantics mailbox() {
/* 128 */     return this.mailbox;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int x$1) {
/* 128 */     this.akka$actor$StashSupport$$capacity = x$1;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics x$1) {
/* 128 */     this.mailbox = x$1;
/*     */   }
/*     */   
/*     */   public void stash() {
/* 128 */     StashSupport$class.stash(this);
/*     */   }
/*     */   
/*     */   public void prepend(Seq others) {
/* 128 */     StashSupport$class.prepend(this, others);
/*     */   }
/*     */   
/*     */   public void unstash() {
/* 128 */     StashSupport$class.unstash(this);
/*     */   }
/*     */   
/*     */   public void unstashAll() {
/* 128 */     StashSupport$class.unstashAll(this);
/*     */   }
/*     */   
/*     */   public void unstashAll(Function1 filterPredicate) {
/* 128 */     StashSupport$class.unstashAll(this, filterPredicate);
/*     */   }
/*     */   
/*     */   public Vector<Envelope> clearStash() {
/* 128 */     return StashSupport$class.clearStash(this);
/*     */   }
/*     */   
/*     */   public AbstractActorWithStash() {
/* 128 */     StashSupport$class.$init$(this);
/* 128 */     UnrestrictedStash$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractActorWithStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */