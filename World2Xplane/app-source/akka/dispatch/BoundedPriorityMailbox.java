/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.util.BoundedBlockingQueue;
/*     */ import java.util.Comparator;
/*     */ import java.util.PriorityQueue;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import scala.Option;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005a\001B\001\003\001\035\021aCQ8v]\022,G\r\025:j_JLG/_'bS2\024w\016\037\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\031B\001\001\005\017%A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\0275\013\027\016\0342pqRK\b/\032\t\004\037M)\022B\001\013\003\005Q\001&o\0343vG\026\034X*Z:tC\036,\027+^3vKB\021ac\b\b\003\037]9Q\001\007\002\t\002e\taCQ8v]\022,G\r\025:j_JLG/_'bS2\024w\016\037\t\003\037i1Q!\001\002\t\002m\031\"A\007\005\t\013uQB\021\001\020\002\rqJg.\033;?)\005Ib\001\002\021\033\001\005\022A\"T3tg\006<W-U;fk\026\0342a\b\022,!\r\031c\005K\007\002I)\021Q\005B\001\005kRLG.\003\002(I\t!\"i\\;oI\026$'\t\\8dW&tw-U;fk\026\004\"aD\025\n\005)\022!\001C#om\026dw\016]3\021\005=a\023BA\027\003\005u\021u.\0368eK\022\fV/Z;f\005\006\034X\rZ'fgN\fw-Z)vKV,\007\"C\030 \005\003\005\013\021\002\0314\003!\031\027\r]1dSRL\bCA\0052\023\t\021$BA\002J]RL!\001\016\024\002\0275\f\007pQ1qC\016LG/\037\005\tm}\021\t\021)A\005o\005\0311-\0349\021\007ab\004&D\001:\025\t)#HC\001<\003\021Q\027M^1\n\005uJ$AC\"p[B\f'/\031;pe\"Aqh\bBC\002\023\005\001)A\006qkNDG+[7f\037V$X#A!\021\005\t;U\"A\"\013\005\021+\025\001\0033ve\006$\030n\0348\013\005\031S\021AC2p]\016,(O]3oi&\021\001j\021\002\t\tV\024\030\r^5p]\"A!j\bB\001B\003%\021)\001\007qkNDG+[7f\037V$\b\005C\003\036?\021\005A\n\006\003N\037B\013\006C\001( \033\005Q\002\"B\030L\001\004\001\004\"\002\034L\001\0049\004\"B L\001\004\t\005\"B* \t\013!\026!B9vKV,W#A+\021\007YC\006&D\001X\025\t1\025(\003\002Z/\ni!\t\\8dW&tw-U;fk\026D\001B\016\001\003\006\004%)aW\013\002o!AQ\f\001B\001B\0035q'\001\003d[B\004\003\002C\030\001\005\013\007IQA0\026\003AB\001\"\031\001\003\002\003\006i\001M\001\nG\006\004\030mY5us\002B\001b\020\001\003\006\004%)\001\021\005\t\025\002\021\t\021)A\007\003\")Q\004\001C\001KR!am\0325j!\ty\001\001C\0037I\002\007q\007C\0030I\002\007\001\007C\003@I\002\007\021\tC\003l\001\021\025C.\001\004de\026\fG/\032\013\004[>T\bCA\bo\023\t\001#\001C\003qU\002\007\021/A\003po:,'\017E\002\neRL!a\035\006\003\r=\003H/[8o!\t)\b0D\001w\025\t9H!A\003bGR|'/\003\002zm\nA\021i\031;peJ+g\rC\003|U\002\007A0\001\004tsN$X-\034\t\004\023Il\bCA;\023\tyhOA\006BGR|'oU=ti\026l\007")
/*     */ public class BoundedPriorityMailbox implements MailboxType, ProducesMessageQueue<BoundedPriorityMailbox.MessageQueue> {
/*     */   private final Comparator<Envelope> cmp;
/*     */   
/*     */   private final int capacity;
/*     */   
/*     */   private final Duration pushTimeOut;
/*     */   
/*     */   public final Comparator<Envelope> cmp() {
/* 621 */     return this.cmp;
/*     */   }
/*     */   
/*     */   public final int capacity() {
/* 621 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public final Duration pushTimeOut() {
/* 621 */     return this.pushTimeOut;
/*     */   }
/*     */   
/*     */   public BoundedPriorityMailbox(Comparator<Envelope> cmp, int capacity, Duration pushTimeOut) {
/* 624 */     if (capacity < 0)
/* 624 */       throw new IllegalArgumentException("The capacity for BoundedMailbox can not be negative"); 
/* 625 */     if (pushTimeOut == null)
/* 625 */       throw new IllegalArgumentException("The push time-out for BoundedMailbox can not be null"); 
/*     */   }
/*     */   
/*     */   public final MessageQueue create(Option owner, Option system) {
/* 628 */     return new MessageQueue(capacity(), cmp(), pushTimeOut());
/*     */   }
/*     */   
/*     */   public static class MessageQueue extends BoundedBlockingQueue<Envelope> implements BoundedQueueBasedMessageQueue {
/*     */     private final Duration pushTimeOut;
/*     */     
/*     */     public void enqueue(ActorRef receiver, Envelope handle) {
/* 632 */       BoundedQueueBasedMessageQueue$class.enqueue(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public Envelope dequeue() {
/* 632 */       return BoundedQueueBasedMessageQueue$class.dequeue(this);
/*     */     }
/*     */     
/*     */     public int numberOfMessages() {
/* 632 */       return QueueBasedMessageQueue$class.numberOfMessages(this);
/*     */     }
/*     */     
/*     */     public boolean hasMessages() {
/* 632 */       return QueueBasedMessageQueue$class.hasMessages(this);
/*     */     }
/*     */     
/*     */     public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
/* 632 */       QueueBasedMessageQueue$class.cleanUp(this, owner, deadLetters);
/*     */     }
/*     */     
/*     */     public Duration pushTimeOut() {
/* 632 */       return this.pushTimeOut;
/*     */     }
/*     */     
/*     */     public MessageQueue(int capacity, Comparator<?> cmp, Duration pushTimeOut) {
/* 632 */       super(
/* 633 */           capacity, new PriorityQueue(11, cmp));
/*     */       QueueBasedMessageQueue$class.$init$(this);
/*     */       BoundedQueueBasedMessageQueue$class.$init$(this);
/*     */     }
/*     */     
/*     */     public final BlockingQueue<Envelope> queue() {
/* 635 */       return (BlockingQueue<Envelope>)this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedPriorityMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */