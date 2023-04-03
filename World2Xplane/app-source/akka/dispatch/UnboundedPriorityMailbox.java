/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import java.util.Comparator;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.PriorityBlockingQueue;
/*     */ import scala.Option;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001=4A!\001\002\001\017\tARK\0342pk:$W\r\032)sS>\024\030\016^=NC&d'm\034=\013\005\r!\021\001\0033jgB\fGo\0315\013\003\025\tA!Y6lC\016\0011\003\002\001\t\035I\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005-i\025-\0337c_b$\026\020]3\021\007=\031R#\003\002\025\005\t!\002K]8ek\016,7/T3tg\006<W-U;fk\026\004\"AF\020\017\005=9r!\002\r\003\021\003I\022\001G+oE>,h\016Z3e!JLwN]5us6\013\027\016\0342pqB\021qB\007\004\006\003\tA\taG\n\0035!AQ!\b\016\005\002y\ta\001P5oSRtD#A\r\007\t\001R\002!\t\002\r\033\026\0348/Y4f#V,W/Z\n\004?\tz\003cA\022+Y5\tAE\003\002&M\005Q1m\0348dkJ\024XM\034;\013\005\035B\023\001B;uS2T\021!K\001\005U\0064\030-\003\002,I\t)\002K]5pe&$\030P\0217pG.LgnZ)vKV,\007CA\b.\023\tq#A\001\005F]Z,Gn\0349f!\ty\001'\003\0022\005\tyRK\0342pk:$W\rZ)vKV,')Y:fI6+7o]1hKF+X-^3\t\021Mz\"\021!Q\001\nQ\nq\"\0338ji&\fGnQ1qC\016LG/\037\t\003\023UJ!A\016\006\003\007%sG\017\003\0059?\t\005\t\025!\003:\003\r\031W\016\035\t\004umbS\"\001\024\n\005q2#AC\"p[B\f'/\031;pe\")Qd\bC\001}Q\031q(\021\"\021\005\001{R\"\001\016\t\013Mj\004\031\001\033\t\013aj\004\031A\035\t\013\021{BQA#\002\013E,X-^3\026\003\031\0032AO$-\023\tAeEA\003Rk\026,X\r\003\0059\001\t\025\r\021\"\001K+\005I\004\002\003'\001\005\003\005\013\021B\035\002\t\rl\007\017\t\005\tg\001\021)\031!C\001\035V\tA\007\003\005Q\001\t\005\t\025!\0035\003AIg.\033;jC2\034\025\r]1dSRL\b\005C\003\036\001\021\005!\013F\002T)V\003\"a\004\001\t\013a\n\006\031A\035\t\013M\n\006\031\001\033\t\013u\001A\021A,\025\005MC\006\"\002\035W\001\004I\004\"\002.\001\t\013Z\026AB2sK\006$X\rF\002]=&\004\"aD/\n\005\001\022\001\"B0Z\001\004\001\027!B8x]\026\024\bcA\005bG&\021!M\003\002\007\037B$\030n\0348\021\005\021<W\"A3\013\005\031$\021!B1di>\024\030B\0015f\005!\t5\r^8s%\0264\007\"\0026Z\001\004Y\027AB:zgR,W\016E\002\nC2\004\"\001Z7\n\0059,'aC!di>\0248+_:uK6\004")
/*     */ public class UnboundedPriorityMailbox implements MailboxType, ProducesMessageQueue<UnboundedPriorityMailbox.MessageQueue> {
/*     */   private final Comparator<Envelope> cmp;
/*     */   
/*     */   private final int initialCapacity;
/*     */   
/*     */   public Comparator<Envelope> cmp() {
/* 603 */     return this.cmp;
/*     */   }
/*     */   
/*     */   public int initialCapacity() {
/* 603 */     return this.initialCapacity;
/*     */   }
/*     */   
/*     */   public UnboundedPriorityMailbox(Comparator<Envelope> cmp, int initialCapacity) {}
/*     */   
/*     */   public UnboundedPriorityMailbox(Comparator<Envelope> cmp) {
/* 605 */     this(cmp, 11);
/*     */   }
/*     */   
/*     */   public final MessageQueue create(Option owner, Option system) {
/* 607 */     return new MessageQueue(initialCapacity(), cmp());
/*     */   }
/*     */   
/*     */   public static class MessageQueue extends PriorityBlockingQueue<Envelope> implements UnboundedQueueBasedMessageQueue {
/*     */     public void enqueue(ActorRef receiver, Envelope handle) {
/* 611 */       UnboundedQueueBasedMessageQueue$class.enqueue(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public Envelope dequeue() {
/* 611 */       return UnboundedQueueBasedMessageQueue$class.dequeue(this);
/*     */     }
/*     */     
/*     */     public int numberOfMessages() {
/* 611 */       return QueueBasedMessageQueue$class.numberOfMessages(this);
/*     */     }
/*     */     
/*     */     public boolean hasMessages() {
/* 611 */       return QueueBasedMessageQueue$class.hasMessages(this);
/*     */     }
/*     */     
/*     */     public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
/* 611 */       QueueBasedMessageQueue$class.cleanUp(this, owner, deadLetters);
/*     */     }
/*     */     
/*     */     public MessageQueue(int initialCapacity, Comparator<? super Envelope> cmp) {
/* 611 */       super(
/* 612 */           initialCapacity, cmp);
/*     */       QueueBasedMessageQueue$class.$init$(this);
/*     */       UnboundedQueueBasedMessageQueue$class.$init$(this);
/*     */     }
/*     */     
/*     */     public final Queue<Envelope> queue() {
/* 613 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedPriorityMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */