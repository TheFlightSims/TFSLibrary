/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
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
/*     */ @ScalaSignature(bytes = "\006\001\005\035g\001B\001\003\001\036\021aBQ8v]\022,G-T1jY\n|\007P\003\002\004\t\005AA-[:qCR\034\007NC\001\006\003\021\t7n[1\004\001M1\001\001\003\b\023Yr\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005-i\025-\0337c_b$\026\020]3\021\007=\031R#\003\002\025\005\t!\002K]8ek\016,7/T3tg\006<W-U;fk\026\004\"A\006\022\017\005=9r!\002\r\003\021\003I\022A\004\"pk:$W\rZ'bS2\024w\016\037\t\003\037i1Q!\001\002\t\002m\0312A\007\005\035!\tIQ$\003\002\037\025\ta1+\032:jC2L'0\0312mK\")\001E\007C\001C\0051A(\0338jiz\"\022!\007\004\005Gi\001AE\001\007NKN\034\030mZ3Rk\026,XmE\002#KI\0022AJ\0270\033\0059#B\001\025*\003)\031wN\\2veJ,g\016\036\006\003U-\nA!\036;jY*\tA&\001\003kCZ\f\027B\001\030(\005Ma\025N\\6fI\ncwnY6j]\036\fV/Z;f!\ty\001'\003\0022\005\tAQI\034<fY>\004X\r\005\002\020g%\021AG\001\002\036\005>,h\016Z3e#V,W/\032\"bg\026$W*Z:tC\036,\027+^3vK\"AaG\tB\001B\003%q'\001\005dCB\f7-\033;z!\tI\001(\003\002:\025\t\031\021J\034;\t\021m\022#Q1A\005\006q\n1\002];tQRKW.Z(viV\tQ\b\005\002?\0056\tqH\003\002A\003\006AA-\036:bi&|gN\003\002)\025%\0211i\020\002\017\r&t\027\016^3EkJ\fG/[8o\021!)%E!A!\002\033i\024\001\0049vg\"$\026.\\3PkR\004\003\"\002\021#\t\0039Ec\001%K\027B\021\021JI\007\0025!)aG\022a\001o!)1H\022a\001{!)QJ\tC\003\035\006)\021/^3vKV\tq\nE\002'!>J!!U\024\003\033\tcwnY6j]\036\fV/Z;f\021\035\031&$!A\005\002R\013Q!\0319qYf$2!\026,X!\ty\001\001C\0037%\002\007q\007C\003<%\002\007Q\bC\004Z5\005\005I\021\021.\002\017Ut\027\r\0359msR\0211,\031\t\004\023qs\026BA/\013\005\031y\005\017^5p]B!\021bX\034>\023\t\001'B\001\004UkBdWM\r\005\bEb\013\t\0211\001V\003\rAH\005\r\005\bIj\t\t\021\"\003f\003-\021X-\0313SKN|GN^3\025\003\031\004\"a\0326\016\003!T!![\026\002\t1\fgnZ\005\003W\"\024aa\0242kK\016$\bCA\005n\023\tq'BA\004Qe>$Wo\031;\t\021Y\002!Q3A\005\002A,\022a\016\005\te\002\021\t\022)A\005o\005I1-\0319bG&$\030\020\t\005\tw\001\021)\032!C\001y!AQ\t\001B\tB\003%Q\bC\003!\001\021\005a\017F\002VobDQAN;A\002]BQaO;A\002uBQ\001\t\001\005\002i$B!V>\002\020!)A0\037a\001{\006A1/\032;uS:<7\017E\002\003\023q1a`A\003\033\t\t\tAC\002\002\004\021\tQ!Y2u_JLA!a\002\002\002\005Y\021i\031;peNK8\017^3n\023\021\tY!!\004\003\021M+G\017^5oONTA!a\002\002\002!9\021\021C=A\002\005M\021AB2p]\032Lw\r\005\003\002\026\005\005RBAA\f\025\021\t\t\"!\007\013\t\005m\021QD\001\tif\004Xm]1gK*\021\021qD\001\004G>l\027\002BA\022\003/\021aaQ8oM&<\007bBA\024\001\021\025\023\021F\001\007GJ,\027\r^3\025\r\005-\022qFA\036!\ry\021QF\005\003G\tA\001\"!\r\002&\001\007\0211G\001\006_^tWM\035\t\005\023q\013)\004E\002\000\003oIA!!\017\002\002\tA\021i\031;peJ+g\r\003\005\002>\005\025\002\031AA \003\031\031\030p\035;f[B!\021\002XA!!\ry\0301I\005\005\003\013\n\tAA\006BGR|'oU=ti\026l\007\"CA%\001\005\005I\021AA&\003\021\031w\016]=\025\013U\013i%a\024\t\021Y\n9\005%AA\002]B\001bOA$!\003\005\r!\020\005\n\003'\002\021\023!C\001\003+\nabY8qs\022\"WMZ1vYR$\023'\006\002\002X)\032q'!\027,\005\005m\003\003BA/\003Oj!!a\030\013\t\005\005\0241M\001\nk:\034\007.Z2lK\022T1!!\032\013\003)\tgN\\8uCRLwN\\\005\005\003S\nyFA\tv]\016DWmY6fIZ\013'/[1oG\026D\021\"!\034\001#\003%\t!a\034\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\021\021\021\017\026\004{\005e\003\"CA;\001\005\005I\021IA<\0035\001(o\0343vGR\004&/\0324jqV\021\021\021\020\t\004O\006m\024bAA?Q\n11\013\036:j]\036D\001\"!!\001\003\003%\t\001]\001\raJ|G-^2u\003JLG/\037\005\n\003\013\003\021\021!C\001\003\017\013a\002\035:pIV\034G/\0227f[\026tG\017\006\003\002\n\006=\005cA\005\002\f&\031\021Q\022\006\003\007\005s\027\020C\005\002\022\006\r\025\021!a\001o\005\031\001\020J\031\t\023\005U\005!!A\005B\005]\025a\0049s_\022,8\r^%uKJ\fGo\034:\026\005\005e\005CBAN\003C\013I)\004\002\002\036*\031\021q\024\006\002\025\r|G\016\\3di&|g.\003\003\002$\006u%\001C%uKJ\fGo\034:\t\023\005\035\006!!A\005\002\005%\026\001C2b]\026\013X/\0317\025\t\005-\026\021\027\t\004\023\0055\026bAAX\025\t9!i\\8mK\006t\007BCAI\003K\013\t\0211\001\002\n\"I\021Q\027\001\002\002\023\005\023qW\001\tQ\006\034\bnQ8eKR\tq\007C\005\002<\002\t\t\021\"\021\002>\006AAo\\*ue&tw\r\006\002\002z!I\021\021\031\001\002\002\023\005\0231Y\001\007KF,\030\r\\:\025\t\005-\026Q\031\005\013\003#\013y,!AA\002\005%\005")
/*     */ public class BoundedMailbox implements MailboxType, ProducesMessageQueue<BoundedMailbox.MessageQueue>, Product, Serializable {
/*     */   private final int capacity;
/*     */   
/*     */   private final FiniteDuration pushTimeOut;
/*     */   
/*     */   public int capacity() {
/* 579 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public FiniteDuration pushTimeOut() {
/* 579 */     return this.pushTimeOut;
/*     */   }
/*     */   
/*     */   public BoundedMailbox copy(int capacity, FiniteDuration pushTimeOut) {
/* 579 */     return new BoundedMailbox(capacity, pushTimeOut);
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/* 579 */     return capacity();
/*     */   }
/*     */   
/*     */   public FiniteDuration copy$default$2() {
/* 579 */     return pushTimeOut();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 579 */     return "BoundedMailbox";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 579 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 579 */     int i = x$1;
/* 579 */     switch (i) {
/*     */       default:
/* 579 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 579 */     return BoxesRunTime.boxToInteger(capacity());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 579 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 579 */     return x$1 instanceof BoundedMailbox;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 579 */     int i = -889275714;
/* 579 */     i = Statics.mix(i, capacity());
/* 579 */     i = Statics.mix(i, Statics.anyHash(pushTimeOut()));
/* 579 */     return Statics.finalizeHash(i, 2);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 579 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/dispatch/BoundedMailbox
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 96
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/BoundedMailbox
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
/*     */     //   #579	-> 0
/*     */     //   #63	-> 14
/*     */     //   #579	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	98	0	this	Lakka/dispatch/BoundedMailbox;
/*     */     //   0	98	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public BoundedMailbox(int capacity, FiniteDuration pushTimeOut) {
/* 579 */     Product.class.$init$(this);
/* 585 */     if (capacity < 0)
/* 585 */       throw new IllegalArgumentException("The capacity for BoundedMailbox can not be negative"); 
/* 586 */     if (pushTimeOut == null)
/* 586 */       throw new IllegalArgumentException("The push time-out for BoundedMailbox can not be null"); 
/*     */   }
/*     */   
/*     */   public BoundedMailbox(ActorSystem.Settings settings, Config config) {
/*     */     this(config.getInt("mailbox-capacity"), Helpers$ConfigOps$.MODULE$.getNanosDuration$extension(Helpers$.MODULE$.ConfigOps(config), "mailbox-push-timeout-time"));
/*     */   }
/*     */   
/*     */   public final MessageQueue create(Option owner, Option system) {
/* 589 */     return new MessageQueue(capacity(), pushTimeOut());
/*     */   }
/*     */   
/*     */   public static class MessageQueue extends LinkedBlockingQueue<Envelope> implements BoundedQueueBasedMessageQueue {
/*     */     private final FiniteDuration pushTimeOut;
/*     */     
/*     */     public void enqueue(ActorRef receiver, Envelope handle) {
/* 593 */       BoundedQueueBasedMessageQueue$class.enqueue(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public Envelope dequeue() {
/* 593 */       return BoundedQueueBasedMessageQueue$class.dequeue(this);
/*     */     }
/*     */     
/*     */     public int numberOfMessages() {
/* 593 */       return QueueBasedMessageQueue$class.numberOfMessages(this);
/*     */     }
/*     */     
/*     */     public boolean hasMessages() {
/* 593 */       return QueueBasedMessageQueue$class.hasMessages(this);
/*     */     }
/*     */     
/*     */     public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
/* 593 */       QueueBasedMessageQueue$class.cleanUp(this, owner, deadLetters);
/*     */     }
/*     */     
/*     */     public final FiniteDuration pushTimeOut() {
/* 593 */       return this.pushTimeOut;
/*     */     }
/*     */     
/*     */     public MessageQueue(int capacity, FiniteDuration pushTimeOut) {
/* 593 */       super(
/* 594 */           capacity);
/*     */       QueueBasedMessageQueue$class.$init$(this);
/*     */       BoundedQueueBasedMessageQueue$class.$init$(this);
/*     */     }
/*     */     
/*     */     public final BlockingQueue<Envelope> queue() {
/* 595 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */