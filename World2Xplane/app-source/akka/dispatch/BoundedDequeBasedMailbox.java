/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.Deque;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.BlockingDeque;
/*     */ import java.util.concurrent.LinkedBlockingDeque;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025g\001B\001\003\001\036\021\001DQ8v]\022,G\rR3rk\026\024\025m]3e\033\006LGNY8y\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001a\005\004\001\0219\0212\016\b\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!aC'bS2\024w\016\037+za\026\0042aD\n\026\023\t!\"A\001\013Qe>$WoY3t\033\026\0348/Y4f#V,W/\032\t\003-\tr!aD\f\b\013a\021\001\022A\r\0021\t{WO\0343fI\022+\027/^3CCN,G-T1jY\n|\007\020\005\002\0205\031)\021A\001E\0017M\031!\004\003\017\021\005%i\022B\001\020\013\0051\031VM]5bY&T\030M\0317f\021\025\001#\004\"\001\"\003\031a\024N\\5u}Q\t\021D\002\003$5\001!#\001D'fgN\fw-Z)vKV,7c\001\022&eA\031a%L\030\016\003\035R!\001K\025\002\025\r|gnY;se\026tGO\003\002+W\005!Q\017^5m\025\005a\023\001\0026bm\006L!AL\024\003'1Kgn[3e\0052|7m[5oO\022+\027/^3\021\005=\001\024BA\031\003\005!)eN^3m_B,\007CA\b4\023\t!$AA\017C_VtG-\0323EKF,XMQ1tK\022lUm]:bO\026\fV/Z;f\021!1$E!A!\002\0239\024\001C2ba\006\034\027\016^=\021\005%A\024BA\035\013\005\rIe\016\036\005\tw\t\022)\031!C\001y\005Y\001/^:i)&lWmT;u+\005i\004C\001 C\033\005y$B\001!B\003!!WO]1uS>t'B\001\025\013\023\t\031uH\001\bGS:LG/\032#ve\006$\030n\0348\t\021\025\023#\021!Q\001\nu\nA\002];tQRKW.Z(vi\002BQ\001\t\022\005\002\035#2\001\023&L!\tI%%D\001\033\021\0251d\t1\0018\021\025Yd\t1\001>\021\035i%E1A\005\0069\013Q!];fk\026,\022\001\023\005\007!\n\002\013Q\002%\002\rE,X-^3!\021\035\021&$!A\005\002N\013Q!\0319qYf$2\001V+W!\ty\001\001C\0037#\002\007q\007C\003<#\002\007Q\bC\004Y5\005\005I\021Q-\002\017Ut\027\r\0359msR\021!\f\031\t\004\023mk\026B\001/\013\005\031y\005\017^5p]B!\021BX\034>\023\ty&B\001\004UkBdWM\r\005\bC^\013\t\0211\001U\003\rAH\005\r\005\bGj\t\t\021\"\003e\003-\021X-\0313SKN|GN^3\025\003\025\004\"AZ5\016\003\035T!\001[\026\002\t1\fgnZ\005\003U\036\024aa\0242kK\016$\bCA\005m\023\ti'BA\004Qe>$Wo\031;\t\021Y\002!Q3A\005\006=,\022a\016\005\tc\002\021\t\022)A\007o\005I1-\0319bG&$\030\020\t\005\tw\001\021)\032!C\003y!AQ\t\001B\tB\0035Q\bC\003!\001\021\005Q\017F\002Um^DQA\016;A\002]BQa\017;A\002uBQ\001\t\001\005\002e$B\001\026>\002\016!)1\020\037a\001y\006A1/\032;uS:<7\017E\002~\003\017q1A`A\002\033\005y(bAA\001\t\005)\021m\031;pe&\031\021QA@\002\027\005\033Go\034:TsN$X-\\\005\005\003\023\tYA\001\005TKR$\030N\\4t\025\r\t)a \005\b\003\037A\b\031AA\t\003\031\031wN\0344jOB!\0211CA\020\033\t\t)B\003\003\002\020\005]!\002BA\r\0037\t\001\002^=qKN\fg-\032\006\003\003;\t1aY8n\023\021\t\t#!\006\003\r\r{gNZ5h\021\035\t)\003\001C#\003O\taa\031:fCR,GCBA\025\003[\tI\004E\002\020\003WI!a\t\002\t\021\005=\0221\005a\001\003c\tQa\\<oKJ\004B!C.\0024A\031a0!\016\n\007\005]rP\001\005BGR|'OU3g\021!\tY$a\tA\002\005u\022AB:zgR,W\016\005\003\n7\006}\002c\001@\002B%\031\0211I@\003\027\005\033Go\034:TsN$X-\034\005\n\003\017\002\021\021!C\001\003\023\nAaY8qsR)A+a\023\002N!Aa'!\022\021\002\003\007q\007\003\005<\003\013\002\n\0211\001>\021%\t\t\006AI\001\n\003\t\031&\001\bd_BLH\005Z3gCVdG\017J\031\026\005\005U#fA\034\002X-\022\021\021\f\t\005\0037\n)'\004\002\002^)!\021qLA1\003%)hn\0315fG.,GMC\002\002d)\t!\"\0318o_R\fG/[8o\023\021\t9'!\030\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\005\002l\001\t\n\021\"\001\002n\005q1m\0349zI\021,g-Y;mi\022\022TCAA8U\ri\024q\013\005\n\003g\002\021\021!C!\003k\nQ\002\035:pIV\034G\017\025:fM&DXCAA<!\r1\027\021P\005\004\003w:'AB*ue&tw\r\003\005\002\000\001\t\t\021\"\001p\0031\001(o\0343vGR\f%/\033;z\021%\t\031\tAA\001\n\003\t))\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005\035\025Q\022\t\004\023\005%\025bAAF\025\t\031\021I\\=\t\023\005=\025\021QA\001\002\0049\024a\001=%c!I\0211\023\001\002\002\023\005\023QS\001\020aJ|G-^2u\023R,'/\031;peV\021\021q\023\t\007\0033\013y*a\"\016\005\005m%bAAO\025\005Q1m\0347mK\016$\030n\0348\n\t\005\005\0261\024\002\t\023R,'/\031;pe\"I\021Q\025\001\002\002\023\005\021qU\001\tG\006tW)];bYR!\021\021VAX!\rI\0211V\005\004\003[S!a\002\"p_2,\027M\034\005\013\003\037\013\031+!AA\002\005\035\005\"CAZ\001\005\005I\021IA[\003!A\027m\0355D_\022,G#A\034\t\023\005e\006!!A\005B\005m\026\001\003;p'R\024\030N\\4\025\005\005]\004\"CA`\001\005\005I\021IAa\003\031)\027/^1mgR!\021\021VAb\021)\ty)!0\002\002\003\007\021q\021")
/*     */ public class BoundedDequeBasedMailbox implements MailboxType, ProducesMessageQueue<BoundedDequeBasedMailbox.MessageQueue>, Product, Serializable {
/*     */   private final int capacity;
/*     */   
/*     */   private final FiniteDuration pushTimeOut;
/*     */   
/*     */   public final int capacity() {
/* 659 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public final FiniteDuration pushTimeOut() {
/* 659 */     return this.pushTimeOut;
/*     */   }
/*     */   
/*     */   public BoundedDequeBasedMailbox copy(int capacity, FiniteDuration pushTimeOut) {
/* 659 */     return new BoundedDequeBasedMailbox(capacity, pushTimeOut);
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/* 659 */     return capacity();
/*     */   }
/*     */   
/*     */   public FiniteDuration copy$default$2() {
/* 659 */     return pushTimeOut();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 659 */     return "BoundedDequeBasedMailbox";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 659 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 659 */     int i = x$1;
/* 659 */     switch (i) {
/*     */       default:
/* 659 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 659 */     return BoxesRunTime.boxToInteger(capacity());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 659 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 659 */     return x$1 instanceof BoundedDequeBasedMailbox;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 659 */     int i = -889275714;
/* 659 */     i = Statics.mix(i, capacity());
/* 659 */     i = Statics.mix(i, Statics.anyHash(pushTimeOut()));
/* 659 */     return Statics.finalizeHash(i, 2);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 659 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 92
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/BoundedDequeBasedMailbox
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 96
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/BoundedDequeBasedMailbox
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual capacity : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual capacity : ()I
/*     */     //   40: if_icmpne -> 88
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual pushTimeOut : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual pushTimeOut : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   52: astore #5
/*     */     //   54: dup
/*     */     //   55: ifnonnull -> 67
/*     */     //   58: pop
/*     */     //   59: aload #5
/*     */     //   61: ifnull -> 75
/*     */     //   64: goto -> 88
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 88
/*     */     //   75: aload #4
/*     */     //   77: aload_0
/*     */     //   78: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   81: ifeq -> 88
/*     */     //   84: iconst_1
/*     */     //   85: goto -> 89
/*     */     //   88: iconst_0
/*     */     //   89: ifeq -> 96
/*     */     //   92: iconst_1
/*     */     //   93: goto -> 97
/*     */     //   96: iconst_0
/*     */     //   97: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #659	-> 0
/*     */     //   #63	-> 14
/*     */     //   #659	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	98	0	this	Lakka/dispatch/BoundedDequeBasedMailbox;
/*     */     //   0	98	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public BoundedDequeBasedMailbox(int capacity, FiniteDuration pushTimeOut) {
/* 659 */     Product.class.$init$(this);
/* 665 */     if (capacity < 0)
/* 665 */       throw new IllegalArgumentException("The capacity for BoundedDequeBasedMailbox can not be negative"); 
/* 666 */     if (pushTimeOut == null)
/* 666 */       throw new IllegalArgumentException("The push time-out for BoundedDequeBasedMailbox can not be null"); 
/*     */   }
/*     */   
/*     */   public BoundedDequeBasedMailbox(ActorSystem.Settings settings, Config config) {
/*     */     this(config.getInt("mailbox-capacity"), Helpers$ConfigOps$.MODULE$.getNanosDuration$extension(Helpers$.MODULE$.ConfigOps(config), "mailbox-push-timeout-time"));
/*     */   }
/*     */   
/*     */   public final MessageQueue create(Option owner, Option system) {
/* 669 */     return new MessageQueue(capacity(), pushTimeOut());
/*     */   }
/*     */   
/*     */   public static class MessageQueue extends LinkedBlockingDeque<Envelope> implements BoundedDequeBasedMessageQueue {
/*     */     private final FiniteDuration pushTimeOut;
/*     */     
/*     */     private final MessageQueue queue;
/*     */     
/*     */     public void enqueue(ActorRef receiver, Envelope handle) {
/* 673 */       BoundedDequeBasedMessageQueue$class.enqueue(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public void enqueueFirst(ActorRef receiver, Envelope handle) {
/* 673 */       BoundedDequeBasedMessageQueue$class.enqueueFirst(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public Envelope dequeue() {
/* 673 */       return BoundedDequeBasedMessageQueue$class.dequeue(this);
/*     */     }
/*     */     
/*     */     public int numberOfMessages() {
/* 673 */       return QueueBasedMessageQueue$class.numberOfMessages(this);
/*     */     }
/*     */     
/*     */     public boolean hasMessages() {
/* 673 */       return QueueBasedMessageQueue$class.hasMessages(this);
/*     */     }
/*     */     
/*     */     public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
/* 673 */       QueueBasedMessageQueue$class.cleanUp(this, owner, deadLetters);
/*     */     }
/*     */     
/*     */     public FiniteDuration pushTimeOut() {
/* 673 */       return this.pushTimeOut;
/*     */     }
/*     */     
/*     */     public MessageQueue(int capacity, FiniteDuration pushTimeOut) {
/* 673 */       super(
/* 674 */           capacity);
/*     */       QueueBasedMessageQueue$class.$init$(this);
/*     */       BoundedDequeBasedMessageQueue$class.$init$(this);
/* 675 */       this.queue = this;
/*     */     }
/*     */     
/*     */     public final MessageQueue queue() {
/* 675 */       return this.queue;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedDequeBasedMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */