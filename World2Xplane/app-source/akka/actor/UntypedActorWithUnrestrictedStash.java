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
/*    */ @ScalaSignature(bytes = "\006\001M1Q!\001\002\002\002\035\021\021%\0268usB,G-Q2u_J<\026\016\0365V]J,7\017\036:jGR,Gm\025;bg\"T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011c\001\001\t\031A\021\021BC\007\002\005%\0211B\001\002\r+:$\030\020]3e\003\016$xN\035\t\003\0235I!A\004\002\003#Us'/Z:ue&\034G/\0323Ti\006\034\b\016C\003\021\001\021\005\021#\001\004=S:LGO\020\013\002%A\021\021\002\001")
/*    */ public abstract class UntypedActorWithUnrestrictedStash extends UntypedActor implements UnrestrictedStash {
/*    */   private Vector<Envelope> akka$actor$StashSupport$$theStash;
/*    */   
/*    */   private final int akka$actor$StashSupport$$capacity;
/*    */   
/*    */   private final DequeBasedMessageQueueSemantics mailbox;
/*    */   
/*    */   public void akka$actor$UnrestrictedStash$$super$preRestart(Throwable reason, Option<Object> message) {
/* 60 */     super.preRestart(reason, message);
/*    */   }
/*    */   
/*    */   public void akka$actor$UnrestrictedStash$$super$postStop() {
/* 60 */     super.postStop();
/*    */   }
/*    */   
/*    */   public void preRestart(Throwable reason, Option message) {
/* 60 */     UnrestrictedStash$class.preRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void postStop() {
/* 60 */     UnrestrictedStash$class.postStop(this);
/*    */   }
/*    */   
/*    */   public Vector<Envelope> akka$actor$StashSupport$$theStash() {
/* 60 */     return this.akka$actor$StashSupport$$theStash;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> x$1) {
/* 60 */     this.akka$actor$StashSupport$$theStash = x$1;
/*    */   }
/*    */   
/*    */   public int akka$actor$StashSupport$$capacity() {
/* 60 */     return this.akka$actor$StashSupport$$capacity;
/*    */   }
/*    */   
/*    */   public DequeBasedMessageQueueSemantics mailbox() {
/* 60 */     return this.mailbox;
/*    */   }
/*    */   
/*    */   public void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int x$1) {
/* 60 */     this.akka$actor$StashSupport$$capacity = x$1;
/*    */   }
/*    */   
/*    */   public void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics x$1) {
/* 60 */     this.mailbox = x$1;
/*    */   }
/*    */   
/*    */   public void stash() {
/* 60 */     StashSupport$class.stash(this);
/*    */   }
/*    */   
/*    */   public void prepend(Seq others) {
/* 60 */     StashSupport$class.prepend(this, others);
/*    */   }
/*    */   
/*    */   public void unstash() {
/* 60 */     StashSupport$class.unstash(this);
/*    */   }
/*    */   
/*    */   public void unstashAll() {
/* 60 */     StashSupport$class.unstashAll(this);
/*    */   }
/*    */   
/*    */   public void unstashAll(Function1 filterPredicate) {
/* 60 */     StashSupport$class.unstashAll(this, filterPredicate);
/*    */   }
/*    */   
/*    */   public Vector<Envelope> clearStash() {
/* 60 */     return StashSupport$class.clearStash(this);
/*    */   }
/*    */   
/*    */   public UntypedActorWithUnrestrictedStash() {
/* 60 */     StashSupport$class.$init$(this);
/* 60 */     UnrestrictedStash$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UntypedActorWithUnrestrictedStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */