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
/*    */ @ScalaSignature(bytes = "\006\001M1Q!\001\002\002\002\035\021a$\0268usB,G-Q2u_J<\026\016\0365V]\n|WO\0343fIN#\030m\0355\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\002\001'\r\001\001\002\004\t\003\023)i\021AA\005\003\027\t\021A\"\0268usB,G-Q2u_J\004\"!C\007\n\0059\021!AD+oE>,h\016Z3e'R\f7\017\033\005\006!\001!\t!E\001\007y%t\027\016\036 \025\003I\001\"!\003\001")
/*    */ public abstract class UntypedActorWithUnboundedStash extends UntypedActor implements UnboundedStash {
/*    */   private Vector<Envelope> akka$actor$StashSupport$$theStash;
/*    */   
/*    */   private final int akka$actor$StashSupport$$capacity;
/*    */   
/*    */   private final DequeBasedMessageQueueSemantics mailbox;
/*    */   
/*    */   public void akka$actor$UnrestrictedStash$$super$preRestart(Throwable reason, Option<Object> message) {
/* 54 */     super.preRestart(reason, message);
/*    */   }
/*    */   
/*    */   public void akka$actor$UnrestrictedStash$$super$postStop() {
/* 54 */     super.postStop();
/*    */   }
/*    */   
/*    */   public void preRestart(Throwable reason, Option message) {
/* 54 */     UnrestrictedStash$class.preRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void postStop() {
/* 54 */     UnrestrictedStash$class.postStop(this);
/*    */   }
/*    */   
/*    */   public Vector<Envelope> akka$actor$StashSupport$$theStash() {
/* 54 */     return this.akka$actor$StashSupport$$theStash;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> x$1) {
/* 54 */     this.akka$actor$StashSupport$$theStash = x$1;
/*    */   }
/*    */   
/*    */   public int akka$actor$StashSupport$$capacity() {
/* 54 */     return this.akka$actor$StashSupport$$capacity;
/*    */   }
/*    */   
/*    */   public DequeBasedMessageQueueSemantics mailbox() {
/* 54 */     return this.mailbox;
/*    */   }
/*    */   
/*    */   public void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int x$1) {
/* 54 */     this.akka$actor$StashSupport$$capacity = x$1;
/*    */   }
/*    */   
/*    */   public void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics x$1) {
/* 54 */     this.mailbox = x$1;
/*    */   }
/*    */   
/*    */   public void stash() {
/* 54 */     StashSupport$class.stash(this);
/*    */   }
/*    */   
/*    */   public void prepend(Seq others) {
/* 54 */     StashSupport$class.prepend(this, others);
/*    */   }
/*    */   
/*    */   public void unstash() {
/* 54 */     StashSupport$class.unstash(this);
/*    */   }
/*    */   
/*    */   public void unstashAll() {
/* 54 */     StashSupport$class.unstashAll(this);
/*    */   }
/*    */   
/*    */   public void unstashAll(Function1 filterPredicate) {
/* 54 */     StashSupport$class.unstashAll(this, filterPredicate);
/*    */   }
/*    */   
/*    */   public Vector<Envelope> clearStash() {
/* 54 */     return StashSupport$class.clearStash(this);
/*    */   }
/*    */   
/*    */   public UntypedActorWithUnboundedStash() {
/* 54 */     StashSupport$class.$init$(this);
/* 54 */     UnrestrictedStash$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UntypedActorWithUnboundedStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */