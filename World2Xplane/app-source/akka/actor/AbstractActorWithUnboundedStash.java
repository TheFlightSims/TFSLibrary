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
/*     */ @ScalaSignature(bytes = "\006\001M1Q!\001\002\002\002\035\021q$\0212tiJ\f7\r^!di>\024x+\033;i+:\024w.\0368eK\022\034F/Y:i\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M\031\001\001\003\007\021\005%QQ\"\001\002\n\005-\021!!D!cgR\024\030m\031;BGR|'\017\005\002\n\033%\021aB\001\002\017+:\024w.\0368eK\022\034F/Y:i\021\025\001\002\001\"\001\022\003\031a\024N\\5u}Q\t!\003\005\002\n\001\001")
/*     */ public abstract class AbstractActorWithUnboundedStash extends AbstractActor implements UnboundedStash {
/*     */   private Vector<Envelope> akka$actor$StashSupport$$theStash;
/*     */   
/*     */   private final int akka$actor$StashSupport$$capacity;
/*     */   
/*     */   private final DequeBasedMessageQueueSemantics mailbox;
/*     */   
/*     */   public void akka$actor$UnrestrictedStash$$super$preRestart(Throwable reason, Option message) {
/* 139 */     Actor$class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void akka$actor$UnrestrictedStash$$super$postStop() {
/* 139 */     Actor$class.postStop(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) {
/* 139 */     UnrestrictedStash$class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postStop() {
/* 139 */     UnrestrictedStash$class.postStop(this);
/*     */   }
/*     */   
/*     */   public Vector<Envelope> akka$actor$StashSupport$$theStash() {
/* 139 */     return this.akka$actor$StashSupport$$theStash;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> x$1) {
/* 139 */     this.akka$actor$StashSupport$$theStash = x$1;
/*     */   }
/*     */   
/*     */   public int akka$actor$StashSupport$$capacity() {
/* 139 */     return this.akka$actor$StashSupport$$capacity;
/*     */   }
/*     */   
/*     */   public DequeBasedMessageQueueSemantics mailbox() {
/* 139 */     return this.mailbox;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int x$1) {
/* 139 */     this.akka$actor$StashSupport$$capacity = x$1;
/*     */   }
/*     */   
/*     */   public void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics x$1) {
/* 139 */     this.mailbox = x$1;
/*     */   }
/*     */   
/*     */   public void stash() {
/* 139 */     StashSupport$class.stash(this);
/*     */   }
/*     */   
/*     */   public void prepend(Seq others) {
/* 139 */     StashSupport$class.prepend(this, others);
/*     */   }
/*     */   
/*     */   public void unstash() {
/* 139 */     StashSupport$class.unstash(this);
/*     */   }
/*     */   
/*     */   public void unstashAll() {
/* 139 */     StashSupport$class.unstashAll(this);
/*     */   }
/*     */   
/*     */   public void unstashAll(Function1 filterPredicate) {
/* 139 */     StashSupport$class.unstashAll(this, filterPredicate);
/*     */   }
/*     */   
/*     */   public Vector<Envelope> clearStash() {
/* 139 */     return StashSupport$class.clearStash(this);
/*     */   }
/*     */   
/*     */   public AbstractActorWithUnboundedStash() {
/* 139 */     StashSupport$class.$init$(this);
/* 139 */     UnrestrictedStash$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractActorWithUnboundedStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */