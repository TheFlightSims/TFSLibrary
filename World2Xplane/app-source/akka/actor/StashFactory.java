/*    */ package akka.actor;
/*    */ 
/*    */ import akka.dispatch.DequeBasedMessageQueueSemantics;
/*    */ import akka.dispatch.Envelope;
/*    */ import scala.Function1;
/*    */ import scala.collection.immutable.Seq;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00152\021\"\001\002\021\002\007\005AA\002\024\003\031M#\030m\0355GC\016$xN]=\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\n\003\001\035\001\"\001C\006\016\003%Q\021AC\001\006g\016\fG.Y\005\003\031%\021a!\0218z%\0264\007\"\002\b\001\t\003\001\022A\002\023j]&$He\001\001\025\003E\001\"\001\003\n\n\005MI!\001B+oSRDa!\006\001\005\002\0211\022aC2sK\006$Xm\025;bg\"$\022a\006\013\0041q\t\003CA\r\033\033\005\021\021BA\016\003\0051\031F/Y:i'V\004\bo\034:u\021\025iB\003q\001\037\003\r\031G\017\037\t\0033}I!\001\t\002\003\031\005\033Go\034:D_:$X\r\037;\t\013\t\"\0029A\022\002\007I,g\r\005\002\032I%\021QE\001\002\t\003\016$xN\035*fMJ\031q%\013\026\007\t!\002\001A\n\002\ryI,g-\0338f[\026tGO\020\t\0033\001\001\"!G\026\n\0051\022!!B!di>\024\b")
/*    */ public interface StashFactory {
/*    */   StashSupport createStash(ActorContext paramActorContext, ActorRef paramActorRef);
/*    */   
/*    */   public class StashFactory$$anon$1 implements StashSupport {
/*    */     private final ActorContext ctx$1;
/*    */     
/*    */     private final ActorRef ref$1;
/*    */     
/*    */     private Vector<Envelope> akka$actor$StashSupport$$theStash;
/*    */     
/*    */     private final int akka$actor$StashSupport$$capacity;
/*    */     
/*    */     private final DequeBasedMessageQueueSemantics mailbox;
/*    */     
/*    */     public Vector<Envelope> akka$actor$StashSupport$$theStash() {
/* 89 */       return this.akka$actor$StashSupport$$theStash;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void akka$actor$StashSupport$$theStash_$eq(Vector<Envelope> x$1) {
/* 89 */       this.akka$actor$StashSupport$$theStash = x$1;
/*    */     }
/*    */     
/*    */     public int akka$actor$StashSupport$$capacity() {
/* 89 */       return this.akka$actor$StashSupport$$capacity;
/*    */     }
/*    */     
/*    */     public DequeBasedMessageQueueSemantics mailbox() {
/* 89 */       return this.mailbox;
/*    */     }
/*    */     
/*    */     public void akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq(int x$1) {
/* 89 */       this.akka$actor$StashSupport$$capacity = x$1;
/*    */     }
/*    */     
/*    */     public void akka$actor$StashSupport$_setter_$mailbox_$eq(DequeBasedMessageQueueSemantics x$1) {
/* 89 */       this.mailbox = x$1;
/*    */     }
/*    */     
/*    */     public void stash() {
/* 89 */       StashSupport$class.stash(this);
/*    */     }
/*    */     
/*    */     public void prepend(Seq others) {
/* 89 */       StashSupport$class.prepend(this, others);
/*    */     }
/*    */     
/*    */     public void unstash() {
/* 89 */       StashSupport$class.unstash(this);
/*    */     }
/*    */     
/*    */     public void unstashAll() {
/* 89 */       StashSupport$class.unstashAll(this);
/*    */     }
/*    */     
/*    */     public void unstashAll(Function1 filterPredicate) {
/* 89 */       StashSupport$class.unstashAll(this, filterPredicate);
/*    */     }
/*    */     
/*    */     public Vector<Envelope> clearStash() {
/* 89 */       return StashSupport$class.clearStash(this);
/*    */     }
/*    */     
/*    */     public StashFactory$$anon$1(StashFactory $outer, ActorContext ctx$1, ActorRef ref$1) {
/* 89 */       StashSupport$class.$init$(this);
/*    */     }
/*    */     
/*    */     public ActorContext context() {
/* 90 */       return this.ctx$1;
/*    */     }
/*    */     
/*    */     public ActorRef self() {
/* 91 */       return this.ref$1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\StashFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */