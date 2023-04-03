/*    */ package akka.actor;
/*    */ 
/*    */ import akka.dispatch.DequeBasedMessageQueueSemantics;
/*    */ import akka.dispatch.Envelope;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.collection.immutable.Seq;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M1Q!\001\002\002\002\035\021Q#\0268usB,G-Q2u_J<\026\016\0365Ti\006\034\bN\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\004\001!a\001CA\005\013\033\005\021\021BA\006\003\0051)f\016^=qK\022\f5\r^8s!\tIQ\"\003\002\017\005\t)1\013^1tQ\")\001\003\001C\001#\0051A(\0338jiz\"\022A\005\t\003\023\001\001")
/*    */ public abstract class UntypedActorWithStash extends UntypedActor implements Stash {
/*    */   private Vector<Envelope> akka$actor$StashSupport$$theStash;
/*    */   
/*    */   private final int akka$actor$StashSupport$$capacity;
/*    */   
/*    */   private final DequeBasedMessageQueueSemantics mailbox;
/*    */   
/*    */   public void akka$actor$UnrestrictedStash$$super$preRestart(Throwable reason, Option<Object> message) {
/* 47 */     super.preRestart(reason, message);
/*    */   }
/*    */   
/*    */   public void akka$actor$UnrestrictedStash$$super$postStop() {
/* 47 */     super.postStop();
/*    */   }
/*    */   
/*    */   public void preRestart(Throwable reason, Option message) {
/* 47 */     UnrestrictedStash$class.preRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void postStop() {
/* 47 */     UnrestrictedStash$class.postStop(this);
/*    */   }
/*    */   
/*    */   public Vector<Envelope> akka$actor$StashSupport$$theStash() {
/* 47 */     return this.akka$actor$StashSupport$$theStash;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> x$1) {
/* 47 */     this.akka$actor$StashSupport$$theStash = x$1;
/*    */   }
/*    */   
/*    */   public int akka$actor$StashSupport$$capacity() {
/* 47 */     return this.akka$actor$StashSupport$$capacity;
/*    */   }
/*    */   
/*    */   public DequeBasedMessageQueueSemantics mailbox() {
/* 47 */     return this.mailbox;
/*    */   }
/*    */   
/*    */   public void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int x$1) {
/* 47 */     this.akka$actor$StashSupport$$capacity = x$1;
/*    */   }
/*    */   
/*    */   public void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics x$1) {
/* 47 */     this.mailbox = x$1;
/*    */   }
/*    */   
/*    */   public void stash() {
/* 47 */     StashSupport$class.stash(this);
/*    */   }
/*    */   
/*    */   public void prepend(Seq others) {
/* 47 */     StashSupport$class.prepend(this, others);
/*    */   }
/*    */   
/*    */   public void unstash() {
/* 47 */     StashSupport$class.unstash(this);
/*    */   }
/*    */   
/*    */   public void unstashAll() {
/* 47 */     StashSupport$class.unstashAll(this);
/*    */   }
/*    */   
/*    */   public void unstashAll(Function1 filterPredicate) {
/* 47 */     StashSupport$class.unstashAll(this, filterPredicate);
/*    */   }
/*    */   
/*    */   public Vector<Envelope> clearStash() {
/* 47 */     return StashSupport$class.clearStash(this);
/*    */   }
/*    */   
/*    */   public UntypedActorWithStash() {
/* 47 */     StashSupport$class.$init$(this);
/* 47 */     UnrestrictedStash$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UntypedActorWithStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */