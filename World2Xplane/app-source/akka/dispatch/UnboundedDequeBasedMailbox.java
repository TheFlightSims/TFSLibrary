/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.Deque;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.LinkedBlockingDeque;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005d\001B\001\003\001\036\021!$\0268c_VtG-\0323EKF,XMQ1tK\022l\025-\0337c_bT!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\024\r\001AaBE*\035!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\f\033\006LGNY8y)f\004X\rE\002\020'UI!\001\006\002\003)A\023x\016Z;dKNlUm]:bO\026\fV/Z;f!\t1\"E\004\002\020/\035)\001D\001E\0013\005QRK\0342pk:$W\r\032#fcV,')Y:fI6\013\027\016\0342pqB\021qB\007\004\006\003\tA\taG\n\0045!a\002CA\005\036\023\tq\"B\001\007TKJL\027\r\\5{C\ndW\rC\003!5\021\005\021%\001\004=S:LGO\020\013\0023\031!1E\007\001%\0051iUm]:bO\026\fV/Z;f'\r\021SE\r\t\004M5zS\"A\024\013\005!J\023AC2p]\016,(O]3oi*\021!fK\001\005kRLGNC\001-\003\021Q\027M^1\n\0059:#a\005'j].,GM\0217pG.Lgn\032#fcV,\007CA\b1\023\t\t$A\001\005F]Z,Gn\0349f!\ty1'\003\0025\005\tyRK\0342pk:$W\r\032#fcV,')Y:fI6+7o]1hKF+X-^3\t\013\001\022C\021\001\034\025\003]\002\"\001\017\022\016\003iAqA\017\022C\002\023\0251(A\003rk\026,X-F\0018\021\031i$\005)A\007o\0051\021/^3vK\002Bqa\020\016\002\002\023\005\005)A\003baBd\027\020F\001B!\ty\001\001C\004D5\005\005I\021\021#\002\017Ut\027\r\0359msR\021Q\t\023\t\003\023\031K!a\022\006\003\017\t{w\016\\3b]\"9\021JQA\001\002\004\t\025a\001=%a!91JGA\001\n\023a\025a\003:fC\022\024Vm]8mm\026$\022!\024\t\003\035Fk\021a\024\006\003!.\nA\001\\1oO&\021!k\024\002\007\037\nTWm\031;\021\005%!\026BA+\013\005\035\001&o\0343vGRDQ\001\t\001\005\002\001CQ\001\t\001\005\002a#2!Q-f\021\025Qv\0131\001\\\003!\031X\r\036;j]\036\034\bC\001/c\035\ti\006-D\001_\025\tyF!A\003bGR|'/\003\002b=\006Y\021i\031;peNK8\017^3n\023\t\031GM\001\005TKR$\030N\\4t\025\t\tg\fC\003g/\002\007q-\001\004d_:4\027n\032\t\003Q:l\021!\033\006\003M*T!a\0337\002\021QL\b/Z:bM\026T\021!\\\001\004G>l\027BA8j\005\031\031uN\0344jO\")\021\017\001C#e\00611M]3bi\026$2a];~!\tyA/\003\002$\005!)a\017\035a\001o\006)qn\0368feB\031\021\002\037>\n\005eT!AB(qi&|g\016\005\002^w&\021AP\030\002\t\003\016$xN\035*fM\")a\020\035a\001\00611/_:uK6\004B!\003=\002\002A\031Q,a\001\n\007\005\025aLA\006BGR|'oU=ti\026l\007\002CA\005\001\005\005I\021\001!\002\t\r|\007/\037\005\n\003\033\001\021\021!C!\003\037\tQ\002\035:pIV\034G\017\025:fM&DXCAA\t!\rq\0251C\005\004\003+y%AB*ue&tw\rC\005\002\032\001\t\t\021\"\001\002\034\005a\001O]8ek\016$\030I]5usV\021\021Q\004\t\004\023\005}\021bAA\021\025\t\031\021J\034;\t\023\005\025\002!!A\005\002\005\035\022A\0049s_\022,8\r^#mK6,g\016\036\013\005\003S\ty\003E\002\n\003WI1!!\f\013\005\r\te.\037\005\013\003c\t\031#!AA\002\005u\021a\001=%c!I\021Q\007\001\002\002\023\005\023qG\001\020aJ|G-^2u\023R,'/\031;peV\021\021\021\b\t\007\003w\t\t%!\013\016\005\005u\"bAA \025\005Q1m\0347mK\016$\030n\0348\n\t\005\r\023Q\b\002\t\023R,'/\031;pe\"I\021q\t\001\002\002\023\005\021\021J\001\tG\006tW)];bYR\031Q)a\023\t\025\005E\022QIA\001\002\004\tI\003C\005\002P\001\t\t\021\"\021\002R\005A\001.Y:i\007>$W\r\006\002\002\036!I\021Q\013\001\002\002\023\005\023qK\001\ti>\034FO]5oOR\021\021\021\003\005\n\0037\002\021\021!C!\003;\na!Z9vC2\034HcA#\002`!Q\021\021GA-\003\003\005\r!!\013")
/*     */ public class UnboundedDequeBasedMailbox implements MailboxType, ProducesMessageQueue<UnboundedDequeBasedMailbox.MessageQueue>, Product, Serializable {
/*     */   public UnboundedDequeBasedMailbox copy() {
/* 642 */     return new UnboundedDequeBasedMailbox();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 642 */     return "UnboundedDequeBasedMailbox";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 642 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 642 */     int i = x$1;
/* 642 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 642 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 642 */     return x$1 instanceof UnboundedDequeBasedMailbox;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 642 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 642 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     boolean bool;
/* 642 */     Object object = x$1;
/* 642 */     if (object instanceof UnboundedDequeBasedMailbox) {
/*     */       bool = true;
/*     */     } else {
/*     */       bool = false;
/*     */     } 
/* 642 */     return (bool && ((UnboundedDequeBasedMailbox)x$1).canEqual(this));
/*     */   }
/*     */   
/*     */   public UnboundedDequeBasedMailbox() {
/* 642 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public UnboundedDequeBasedMailbox(ActorSystem.Settings settings, Config config) {
/* 644 */     this();
/*     */   }
/*     */   
/*     */   public final MessageQueue create(Option owner, Option system) {
/* 647 */     return new MessageQueue();
/*     */   }
/*     */   
/*     */   public static class MessageQueue extends LinkedBlockingDeque<Envelope> implements UnboundedDequeBasedMessageQueue {
/*     */     private final MessageQueue queue;
/*     */     
/*     */     public void enqueue(ActorRef receiver, Envelope handle) {
/* 651 */       UnboundedDequeBasedMessageQueue$class.enqueue(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public void enqueueFirst(ActorRef receiver, Envelope handle) {
/* 651 */       UnboundedDequeBasedMessageQueue$class.enqueueFirst(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public Envelope dequeue() {
/* 651 */       return UnboundedDequeBasedMessageQueue$class.dequeue(this);
/*     */     }
/*     */     
/*     */     public int numberOfMessages() {
/* 651 */       return QueueBasedMessageQueue$class.numberOfMessages(this);
/*     */     }
/*     */     
/*     */     public boolean hasMessages() {
/* 651 */       return QueueBasedMessageQueue$class.hasMessages(this);
/*     */     }
/*     */     
/*     */     public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
/* 651 */       QueueBasedMessageQueue$class.cleanUp(this, owner, deadLetters);
/*     */     }
/*     */     
/*     */     public MessageQueue() {
/* 651 */       QueueBasedMessageQueue$class.$init$(this);
/* 651 */       UnboundedDequeBasedMessageQueue$class.$init$(this);
/* 652 */       this.queue = this;
/*     */     }
/*     */     
/*     */     public final MessageQueue queue() {
/* 652 */       return this.queue;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedDequeBasedMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */