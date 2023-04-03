/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025d\001B\001\003\001\036\021\001#\0268c_VtG-\0323NC&d'm\034=\013\005\r!\021\001\0033jgB\fGo\0315\013\003\025\tA!Y6lC\016\0011C\002\001\t\035I)F\004\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\0211\"T1jY\n|\007\020V=qKB\031qbE\013\n\005Q\021!\001\006)s_\022,8-Z:NKN\034\030mZ3Rk\026,X\r\005\002\027E9\021qbF\004\0061\tA\t!G\001\021+:\024w.\0368eK\022l\025-\0337c_b\004\"a\004\016\007\013\005\021\001\022A\016\024\007iAA\004\005\002\n;%\021aD\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\006Ai!\t!I\001\007y%t\027\016\036 \025\003e1Aa\t\016\001I\taQ*Z:tC\036,\027+^3vKN\031!%\n\032\021\007\031js&D\001(\025\tA\023&\001\006d_:\034WO\035:f]RT!AK\026\002\tU$\030\016\034\006\002Y\005!!.\031<b\023\tqsEA\013D_:\034WO\035:f]Rd\025N\\6fIF+X-^3\021\005=\001\024BA\031\003\005!)eN^3m_B,\007CA\b4\023\t!$AA\020V]\n|WO\0343fIF+X-^3CCN,G-T3tg\006<W-U;fk\026DQ\001\t\022\005\002Y\"\022a\016\t\003q\tj\021A\007\005\006u\t\")aO\001\006cV,W/Z\013\002yA\031QHP\030\016\003%J!aP\025\003\013E+X-^3\t\017\005S\022\021!CA\005\006)\021\r\0359msR\t1\t\005\002\020\001!9QIGA\001\n\0033\025aB;oCB\004H.\037\013\003\017*\003\"!\003%\n\005%S!a\002\"p_2,\027M\034\005\b\027\022\013\t\0211\001D\003\rAH\005\r\005\b\033j\t\t\021\"\003O\003-\021X-\0313SKN|GN^3\025\003=\003\"\001U*\016\003ES!AU\026\002\t1\fgnZ\005\003)F\023aa\0242kK\016$\bCA\005W\023\t9&BA\004Qe>$Wo\031;\t\013\001\002A\021\001\"\t\013\001\002A\021\001.\025\007\r[v\rC\003]3\002\007Q,\001\005tKR$\030N\\4t!\tqFM\004\002`E6\t\001M\003\002b\t\005)\021m\031;pe&\0211\rY\001\f\003\016$xN]*zgR,W.\003\002fM\nA1+\032;uS:<7O\003\002dA\")\001.\027a\001S\00611m\0348gS\036\004\"A\0339\016\003-T!\001\0337\013\0055t\027\001\003;za\026\034\030MZ3\013\003=\f1aY8n\023\t\t8N\001\004D_:4\027n\032\005\006g\002!)\005^\001\007GJ,\027\r^3\025\007U<x\020\005\002\020m&\0211E\001\005\006qJ\004\r!_\001\006_^tWM\035\t\004\023id\030BA>\013\005\031y\005\017^5p]B\021q,`\005\003}\002\024\001\"Q2u_J\024VM\032\005\b\003\003\021\b\031AA\002\003\031\031\030p\035;f[B!\021B_A\003!\ry\026qA\005\004\003\023\001'aC!di>\0248+_:uK6D\001\"!\004\001\003\003%\tAQ\001\005G>\004\030\020C\005\002\022\001\t\t\021\"\021\002\024\005i\001O]8ek\016$\bK]3gSb,\"!!\006\021\007A\0139\"C\002\002\032E\023aa\025;sS:<\007\"CA\017\001\005\005I\021AA\020\0031\001(o\0343vGR\f%/\033;z+\t\t\t\003E\002\n\003GI1!!\n\013\005\rIe\016\036\005\n\003S\001\021\021!C\001\003W\ta\002\035:pIV\034G/\0227f[\026tG\017\006\003\002.\005M\002cA\005\0020%\031\021\021\007\006\003\007\005s\027\020\003\006\0026\005\035\022\021!a\001\003C\t1\001\037\0232\021%\tI\004AA\001\n\003\nY$A\bqe>$Wo\031;Ji\026\024\030\r^8s+\t\ti\004\005\004\002@\005\025\023QF\007\003\003\003R1!a\021\013\003)\031w\016\0347fGRLwN\\\005\005\003\017\n\tE\001\005Ji\026\024\030\r^8s\021%\tY\005AA\001\n\003\ti%\001\005dC:,\025/^1m)\r9\025q\n\005\013\003k\tI%!AA\002\0055\002\"CA*\001\005\005I\021IA+\003!A\027m\0355D_\022,GCAA\021\021%\tI\006AA\001\n\003\nY&\001\005u_N#(/\0338h)\t\t)\002C\005\002`\001\t\t\021\"\021\002b\0051Q-];bYN$2aRA2\021)\t)$!\030\002\002\003\007\021Q\006")
/*     */ public class UnboundedMailbox implements MailboxType, ProducesMessageQueue<UnboundedMailbox.MessageQueue>, Product, Serializable {
/*     */   public UnboundedMailbox copy() {
/* 550 */     return new UnboundedMailbox();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 550 */     return "UnboundedMailbox";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 550 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 550 */     int i = x$1;
/* 550 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 550 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 550 */     return x$1 instanceof UnboundedMailbox;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 550 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 550 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     boolean bool;
/* 550 */     Object object = x$1;
/* 550 */     if (object instanceof UnboundedMailbox) {
/*     */       bool = true;
/*     */     } else {
/*     */       bool = false;
/*     */     } 
/* 550 */     return (bool && ((UnboundedMailbox)x$1).canEqual(this));
/*     */   }
/*     */   
/*     */   public UnboundedMailbox() {
/* 550 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public UnboundedMailbox(ActorSystem.Settings settings, Config config) {
/* 552 */     this();
/*     */   }
/*     */   
/*     */   public final MessageQueue create(Option owner, Option system) {
/* 555 */     return new MessageQueue();
/*     */   }
/*     */   
/*     */   public static class MessageQueue extends ConcurrentLinkedQueue<Envelope> implements UnboundedQueueBasedMessageQueue {
/*     */     public void enqueue(ActorRef receiver, Envelope handle) {
/* 559 */       UnboundedQueueBasedMessageQueue$class.enqueue(this, receiver, handle);
/*     */     }
/*     */     
/*     */     public Envelope dequeue() {
/* 559 */       return UnboundedQueueBasedMessageQueue$class.dequeue(this);
/*     */     }
/*     */     
/*     */     public int numberOfMessages() {
/* 559 */       return QueueBasedMessageQueue$class.numberOfMessages(this);
/*     */     }
/*     */     
/*     */     public boolean hasMessages() {
/* 559 */       return QueueBasedMessageQueue$class.hasMessages(this);
/*     */     }
/*     */     
/*     */     public void cleanUp(ActorRef owner, MessageQueue deadLetters) {
/* 559 */       QueueBasedMessageQueue$class.cleanUp(this, owner, deadLetters);
/*     */     }
/*     */     
/*     */     public MessageQueue() {
/* 559 */       QueueBasedMessageQueue$class.$init$(this);
/* 559 */       UnboundedQueueBasedMessageQueue$class.$init$(this);
/*     */     }
/*     */     
/*     */     public final Queue<Envelope> queue() {
/* 560 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */