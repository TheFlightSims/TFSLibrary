/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.dispatch.sysmsg.EarliestFirstSystemMessageList$;
/*     */ import akka.dispatch.sysmsg.NoMessage$;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.SystemMessageList$;
/*     */ import akka.event.Logging;
/*     */ import akka.util.Unsafe;
/*     */ import scala.Option;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tEsAB\001\003\021\003!a!A\004NC&d'm\034=\013\005\r!\021\001\0033jgB\fGo\0315\013\003\025\tA!Y6lCB\021q\001C\007\002\005\0311\021B\001E\001\t)\021q!T1jY\n|\007p\005\002\t\027A\021AbD\007\002\033)\ta\"A\003tG\006d\027-\003\002\021\033\t1\021I\\=SK\032DQA\005\005\005\002Q\ta\001P5oSRt4\001\001\013\002\r\025!a\003\003\001\030\005\031\031F/\031;vgB\021A\002G\005\00335\0211!\0238u\021\035Y\002B1A\005\006q\tAa\0249f]V\tQdD\001\037;\005\001\001B\002\021\tA\0035Q$A\003Pa\026t\007\005C\004#\021\t\007IQA\022\002\r\rcwn]3e+\005!s\"A\023\036\003\005Aaa\n\005!\002\033!\023aB\"m_N,G\r\t\005\bS!\021\r\021\"\002+\003%\0316\r[3ek2,G-F\001,\037\005aS$\001\002\t\r9B\001\025!\004,\003)\0316\r[3ek2,G\r\t\005\ba!\021\r\021\"\0022\003I\031\bn\\;mIN\033\007.\0323vY\026l\025m]6\026\003Iz\021aM\017\002\007!1Q\007\003Q\001\016I\n1c\0355pk2$7k\0315fIVdW-T1tW\002Bqa\016\005C\002\023\025\001(\001\013tQ>,H\016\032(piB\023xnY3tg6\0137o[\013\002/!1!\b\003Q\001\016]\tQc\0355pk2$gj\034;Qe>\034Wm]:NCN\\\007\005C\004=\021\t\007IQ\001\035\002\027M,8\017]3oI6\0137o\033\005\007}!\001\013QB\f\002\031M,8\017]3oI6\0137o\033\021\t\017\001C!\031!C\003\003\006Y1/^:qK:$WK\\5u+\005\021u\"A\"\036\003\021Aa!\022\005!\002\033\021\025\001D:vgB,g\016Z+oSR\004\003bB$\t\005\004%)\001S\001\006I\026\024WoZ\013\002\023>\t!*G\001\001\021\031a\005\002)A\007\023\0061A-\0322vO\0022a!\003\002\002\002\021q5\003B'\f\037J\003\"a\002)\n\005E\023!AE*zgR,W.T3tg\006<W-U;fk\026\004\"a\025-\016\003QS!!\026,\002\t1\fgn\032\006\002/\006!!.\031<b\023\tIFK\001\005Sk:t\027M\0317f\021!YVJ!b\001\n\003a\026\001D7fgN\fw-Z)vKV,W#A/\021\005\035q\026BA0\003\0051iUm]:bO\026\fV/Z;f\021!\tWJ!A!\002\023i\026!D7fgN\fw-Z)vKV,\007\005C\003\023\033\022\0051\r\006\002eKB\021q!\024\005\0067\n\004\r!\030\005\nO6\003\r\0211A\005\002!\fQ!Y2u_J,\022!\033\t\003U2l\021a\033\006\003O\022I!!\\6\003\023\005\033Go\034:DK2d\007\"C8N\001\004\005\r\021\"\001q\003%\t7\r^8s?\022*\027\017\006\002riB\021AB]\005\003g6\021A!\0268ji\"9QO\\A\001\002\004I\027a\001=%c!1q/\024Q!\n%\fa!Y2u_J\004\003F\001<z!\ta!0\003\002|\033\tAao\0347bi&dW\rC\003~\033\022\005a0\001\005tKR\f5\r^8s)\t\tx\020\003\004\002\002q\004\r![\001\005G\026dG\016C\004\002\0065#\t!a\002\002\025\021L7\017]1uG\",'/\006\002\002\nA\031q!a\003\n\007\0055!AA\tNKN\034\030mZ3ESN\004\030\r^2iKJDq!!\005N\t\003\t\031\"A\004f]F,X-^3\025\013E\f)\"a\b\t\021\005]\021q\002a\001\0033\t\001B]3dK&4XM\035\t\004U\006m\021bAA\017W\nA\021i\031;peJ+g\r\003\005\002\"\005=\001\031AA\022\003\ri7o\032\t\004\017\005\025\022bAA\024\005\tAQI\034<fY>\004X\rC\004\002,5#\t!!\f\002\017\021,\027/^3vKR\021\0211\005\005\b\003ciE\021AA\032\003-A\027m]'fgN\fw-Z:\026\005\005U\002c\001\007\0028%\031\021\021H\007\003\017\t{w\016\\3b]\"1\021QH'\005\002a\n\001C\\;nE\026\024xJZ'fgN\fw-Z:\t\027\005\005S\n1AA\002\023E\0211I\001\033?N$\030\r^;t\t>tu\016^\"bY2lU\rR5sK\016$H._\013\003\003\013\0022!a\022\026\035\t9\001\001C\006\002L5\003\r\0211A\005\022\0055\023AH0ti\006$Xo\035#p\035>$8)\0317m\033\026$\025N]3di2Lx\fJ3r)\r\t\030q\n\005\nk\006%\023\021!a\001\003\013B\001\"a\025NA\003&\021QI\001\034?N$\030\r^;t\t>tu\016^\"bY2lU\rR5sK\016$H.\037\021)\007\005E\023\020C\006\002Z5\003\r\0211A\005\022\005m\023aH0tsN$X-\\)vKV,Gi\034(pi\016\013G\016\\'f\t&\024Xm\031;msV\021\021Q\f\t\005\003?\n)'\004\002\002b)\031\0211\r\002\002\rML8/\\:h\023\021\t9'!\031\003\033MK8\017^3n\033\026\0348/Y4f\021-\tY'\024a\001\002\004%\t\"!\034\002G}\033\030p\035;f[F+X-^3E_:{GoQ1mY6+G)\033:fGRd\027p\030\023fcR\031\021/a\034\t\023U\fI'!AA\002\005u\003\002CA:\033\002\006K!!\030\002A}\033\030p\035;f[F+X-^3E_:{GoQ1mY6+G)\033:fGRd\027\020\t\025\004\003cJ\bbBA=\033\022\025\0211I\001\007gR\fG/^:)\t\005]\024Q\020\t\004\031\005}\024bAAA\033\t1\021N\0347j]\026Dq!!\"N\t\013\t\031$\001\013tQ>,H\016\032)s_\016,7o]'fgN\fw-\032\025\005\003\007\013i\b\003\004\002\f6#)\001O\001\rgV\034\b/\0328e\007>,h\016\036\025\005\003\023\013i\bC\004\002\0226#)!a\r\002\027%\0348+^:qK:$W\r\032\025\005\003\037\013i\bC\004\002\0306#)!a\r\002\021%\0348\t\\8tK\022DC!!&\002~!9\021QT'\005\006\005M\022aC5t'\016DW\rZ;mK\022DC!a'\002~!9\0211U'\005\026\005\025\026\001D;qI\006$Xm\025;biV\034HCBA\033\003O\013Y\013\003\005\002*\006\005\006\031AA#\003%yG\016Z*uCR,8\017\003\005\002.\006\005\006\031AA#\003%qWm^*uCR,8\017\013\003\002\"\006u\004bBAZ\033\022U\021QW\001\ng\026$8\013^1ukN$2!]A\\\021!\ti+!-A\002\005\025\003\006BAY\003{Bq!!0N\t\013\ty,\001\004sKN,X.\032\013\003\003kAC!a/\002DB!\021QYAf\033\t\t9MC\002\002J6\t!\"\0318o_R\fG/[8o\023\021\ti-a2\003\017Q\f\027\016\034:fG\"9\021\021['\005\006\005}\026aB:vgB,g\016\032\025\005\003\037\f\031\rC\004\002X6#)!a0\002\031\t,7m\\7f\0072|7/\0323)\t\005U\0271\031\005\b\003;lEQAA`\0039\031X\r^!t'\016DW\rZ;mK\022DC!a7\002D\"9\0211]'\005\006\005}\026!C:fi\006\033\030\n\0327fQ\021\t\t/a1\t\017\005%X\n\"\006\002l\006q1/_:uK6\fV/Z;f\017\026$XCAAw!\021\ty&a<\n\t\005E\030\021\r\002\035\031\006$Xm\035;GSJ\034HoU=ti\026lW*Z:tC\036,G*[:u\021\035\t)0\024C\013\003o\fab]=ti\026l\027+^3vKB+H\017\006\004\0026\005e\030Q \005\t\003w\f\031\0201\001\002n\006!ql\0347e\021!\ty0a=A\002\0055\030\001B0oK^DqAa\001N\t\013\021)!\001\016dC:\024UmU2iK\022,H.\0323G_J,\0050Z2vi&|g\016\006\004\0026\t\035!1\002\005\t\005\023\021\t\0011\001\0026\005q\001.Y:NKN\034\030mZ3IS:$\b\002\003B\007\005\003\001\r!!\016\002)!\f7oU=ti\026lW*Z:tC\036,\007*\0338u\021\035\021\t\"\024C\003\005'\t1A];o)\005\t\bb\002B\f\033\0225!\021D\001\017aJ|7-Z:t\033\006LGNY8y)\025\t(1\004B\020\021%\021iB!\006\021\002\003\007q#\001\003mK\032$\bB\003B\021\005+\001\n\0211\001\003$\005QA-Z1eY&tWMT:\021\0071\021)#C\002\003(5\021A\001T8oO\"\"!QCAb\021\035\021i#\024C\003\005'\t\001\004\035:pG\026\0348/\0217m'f\034H/Z7NKN\034\030mZ3t\021!\021\t$\024C\t\005\tM\021aB2mK\006tW\013\035\005\n\005ki\025\023!C\007\005o\t\001\004\035:pG\026\0348/T1jY\n|\007\020\n3fM\006,H\016\036\0232+\t\021IDK\002\030\005wY#A!\020\021\t\t}\"QI\007\003\005\003RAAa\021\002H\006IQO\\2iK\016\\W\rZ\005\005\005\017\022\tEA\tv]\016DWmY6fIZ\013'/[1oG\026D\021Ba\023N#\003%iA!\024\0021A\024xnY3tg6\013\027\016\0342pq\022\"WMZ1vYR$#'\006\002\003P)\"!1\005B\036\001")
/*     */ public abstract class Mailbox implements SystemMessageQueue, Runnable {
/*     */   private final MessageQueue messageQueue;
/*     */   
/*     */   private volatile ActorCell actor;
/*     */   
/*     */   private volatile int _statusDoNotCallMeDirectly;
/*     */   
/*     */   private volatile SystemMessage _systemQueueDoNotCallMeDirectly;
/*     */   
/*     */   public MessageQueue messageQueue() {
/*  52 */     return this.messageQueue;
/*     */   }
/*     */   
/*     */   public Mailbox(MessageQueue messageQueue) {}
/*     */   
/*     */   public ActorCell actor() {
/*  73 */     return this.actor;
/*     */   }
/*     */   
/*     */   public void actor_$eq(ActorCell x$1) {
/*  73 */     this.actor = x$1;
/*     */   }
/*     */   
/*     */   public void setActor(ActorCell cell) {
/*  74 */     actor_$eq(cell);
/*     */   }
/*     */   
/*     */   public MessageDispatcher dispatcher() {
/*  76 */     return actor().dispatcher();
/*     */   }
/*     */   
/*     */   public void enqueue(ActorRef receiver, Envelope msg) {
/*  81 */     messageQueue().enqueue(receiver, msg);
/*     */   }
/*     */   
/*     */   public Envelope dequeue() {
/*  86 */     return messageQueue().dequeue();
/*     */   }
/*     */   
/*     */   public boolean hasMessages() {
/*  91 */     return messageQueue().hasMessages();
/*     */   }
/*     */   
/*     */   public int numberOfMessages() {
/*  98 */     return messageQueue().numberOfMessages();
/*     */   }
/*     */   
/*     */   public int _statusDoNotCallMeDirectly() {
/* 101 */     return this._statusDoNotCallMeDirectly;
/*     */   }
/*     */   
/*     */   public void _statusDoNotCallMeDirectly_$eq(int x$1) {
/* 101 */     this._statusDoNotCallMeDirectly = x$1;
/*     */   }
/*     */   
/*     */   public SystemMessage _systemQueueDoNotCallMeDirectly() {
/* 104 */     return this._systemQueueDoNotCallMeDirectly;
/*     */   }
/*     */   
/*     */   public void _systemQueueDoNotCallMeDirectly_$eq(SystemMessage x$1) {
/* 104 */     this._systemQueueDoNotCallMeDirectly = x$1;
/*     */   }
/*     */   
/*     */   public final int status() {
/* 107 */     return Unsafe.instance.getIntVolatile(this, AbstractMailbox.mailboxStatusOffset);
/*     */   }
/*     */   
/*     */   public final boolean shouldProcessMessage() {
/* 110 */     return ((status() & Mailbox$.MODULE$.shouldNotProcessMask()) == 0);
/*     */   }
/*     */   
/*     */   public final int suspendCount() {
/* 113 */     return status() / 4;
/*     */   }
/*     */   
/*     */   public final boolean isSuspended() {
/* 116 */     return ((status() & Mailbox$.MODULE$.suspendMask()) != 0);
/*     */   }
/*     */   
/*     */   public final boolean isClosed() {
/* 119 */     return (status() == 1);
/*     */   }
/*     */   
/*     */   public final boolean isScheduled() {
/* 122 */     return ((status() & 0x2) != 0);
/*     */   }
/*     */   
/*     */   public final boolean updateStatus(int oldStatus, int newStatus) {
/* 126 */     return Unsafe.instance.compareAndSwapInt(this, AbstractMailbox.mailboxStatusOffset, oldStatus, newStatus);
/*     */   }
/*     */   
/*     */   public final void setStatus(int newStatus) {
/* 130 */     Unsafe.instance.putIntVolatile(this, AbstractMailbox.mailboxStatusOffset, newStatus);
/*     */   }
/*     */   
/*     */   public final boolean resume() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual status : ()I
/*     */     //   4: istore_2
/*     */     //   5: iload_2
/*     */     //   6: tableswitch default -> 24, 1 -> 59
/*     */     //   24: iload_2
/*     */     //   25: iconst_4
/*     */     //   26: if_icmpge -> 33
/*     */     //   29: iload_2
/*     */     //   30: goto -> 36
/*     */     //   33: iload_2
/*     */     //   34: iconst_4
/*     */     //   35: isub
/*     */     //   36: istore_3
/*     */     //   37: aload_0
/*     */     //   38: iload_2
/*     */     //   39: iload_3
/*     */     //   40: invokevirtual updateStatus : (II)Z
/*     */     //   43: ifeq -> 0
/*     */     //   46: iload_3
/*     */     //   47: iconst_4
/*     */     //   48: if_icmpge -> 55
/*     */     //   51: iconst_1
/*     */     //   52: goto -> 65
/*     */     //   55: iconst_0
/*     */     //   56: goto -> 65
/*     */     //   59: aload_0
/*     */     //   60: iconst_1
/*     */     //   61: invokevirtual setStatus : (I)V
/*     */     //   64: iconst_0
/*     */     //   65: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #139	-> 0
/*     */     //   #143	-> 24
/*     */     //   #144	-> 37
/*     */     //   #141	-> 59
/*     */     //   #139	-> 65
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	66	0	this	Lakka/dispatch/Mailbox;
/*     */     //   37	29	3	next	I
/*     */   }
/*     */   
/*     */   public final boolean suspend() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual status : ()I
/*     */     //   4: istore_2
/*     */     //   5: iload_2
/*     */     //   6: tableswitch default -> 24, 1 -> 48
/*     */     //   24: aload_0
/*     */     //   25: iload_2
/*     */     //   26: iload_2
/*     */     //   27: iconst_4
/*     */     //   28: iadd
/*     */     //   29: invokevirtual updateStatus : (II)Z
/*     */     //   32: ifeq -> 0
/*     */     //   35: iload_2
/*     */     //   36: iconst_4
/*     */     //   37: if_icmpge -> 44
/*     */     //   40: iconst_1
/*     */     //   41: goto -> 54
/*     */     //   44: iconst_0
/*     */     //   45: goto -> 54
/*     */     //   48: aload_0
/*     */     //   49: iconst_1
/*     */     //   50: invokevirtual setStatus : (I)V
/*     */     //   53: iconst_0
/*     */     //   54: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #155	-> 0
/*     */     //   #159	-> 24
/*     */     //   #157	-> 48
/*     */     //   #155	-> 54
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	55	0	this	Lakka/dispatch/Mailbox;
/*     */   }
/*     */   
/*     */   public final boolean becomeClosed() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual status : ()I
/*     */     //   4: istore_2
/*     */     //   5: iload_2
/*     */     //   6: tableswitch default -> 24, 1 -> 37
/*     */     //   24: aload_0
/*     */     //   25: iload_2
/*     */     //   26: iconst_1
/*     */     //   27: invokevirtual updateStatus : (II)Z
/*     */     //   30: ifeq -> 0
/*     */     //   33: iconst_1
/*     */     //   34: goto -> 43
/*     */     //   37: aload_0
/*     */     //   38: iconst_1
/*     */     //   39: invokevirtual setStatus : (I)V
/*     */     //   42: iconst_0
/*     */     //   43: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #168	-> 0
/*     */     //   #171	-> 24
/*     */     //   #170	-> 37
/*     */     //   #168	-> 43
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	44	0	this	Lakka/dispatch/Mailbox;
/*     */   }
/*     */   
/*     */   public final boolean setAsScheduled() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual status : ()I
/*     */     //   4: istore_2
/*     */     //   5: iload_2
/*     */     //   6: iconst_3
/*     */     //   7: iand
/*     */     //   8: iconst_0
/*     */     //   9: if_icmpeq -> 16
/*     */     //   12: iconst_0
/*     */     //   13: goto -> 28
/*     */     //   16: aload_0
/*     */     //   17: iload_2
/*     */     //   18: iload_2
/*     */     //   19: iconst_2
/*     */     //   20: ior
/*     */     //   21: invokevirtual updateStatus : (II)Z
/*     */     //   24: ifeq -> 0
/*     */     //   27: iconst_1
/*     */     //   28: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #179	-> 0
/*     */     //   #184	-> 5
/*     */     //   #185	-> 16
/*     */     //   #178	-> 28
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	29	0	this	Lakka/dispatch/Mailbox;
/*     */     //   5	24	2	s	I
/*     */   }
/*     */   
/*     */   public final boolean setAsIdle() {
/*     */     while (true) {
/* 193 */       int s = status();
/* 194 */       if (updateStatus(s, s & (0x2 ^ 0xFFFFFFFF)))
/*     */         return true; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final SystemMessage systemQueueGet() {
/* 202 */     return (SystemMessage)Unsafe.instance.getObjectVolatile(this, AbstractMailbox.systemMessageOffset);
/*     */   }
/*     */   
/*     */   public final boolean systemQueuePut(SystemMessage _old, SystemMessage _new) {
/* 208 */     return Unsafe.instance.compareAndSwapObject(this, AbstractMailbox.systemMessageOffset, _old, _new);
/*     */   }
/*     */   
/*     */   public final boolean canBeScheduledForExecution(boolean hasMessageHint, boolean hasSystemMessageHint) {
/* 210 */     int i = status();
/* 210 */     switch (i) {
/*     */       default:
/* 210 */         return 
/*     */           
/* 213 */           (hasSystemMessageHint || hasSystemMessages());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */       case 2:
/*     */         break;
/*     */     } 
/*     */     return (hasMessageHint || hasSystemMessageHint || hasSystemMessages() || hasMessages());
/*     */   }
/*     */   
/*     */   public final void run() {
/*     */     try {
/* 218 */       if (!isClosed()) {
/* 219 */         processAllSystemMessages();
/* 220 */         processMailbox(processMailbox$default$1(), processMailbox$default$2());
/*     */       } 
/*     */       return;
/*     */     } finally {
/* 223 */       setAsIdle();
/* 224 */       dispatcher().registerForExecution(this, false, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final int processMailbox$default$1() {
/* 232 */     return Math.max(dispatcher().throughput(), 1);
/*     */   }
/*     */   
/*     */   private final long processMailbox$default$2() {
/* 233 */     return (dispatcher().isThroughputDeadlineTimeDefined() == true) ? (System.nanoTime() + dispatcher().throughputDeadlineTime().toNanos()) : 0L;
/*     */   }
/*     */   
/*     */   private final void processMailbox(int left, long deadlineNs) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual shouldProcessMessage : ()Z
/*     */     //   4: ifeq -> 94
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual dequeue : ()Lakka/dispatch/Envelope;
/*     */     //   11: astore #5
/*     */     //   13: aload #5
/*     */     //   15: ifnull -> 88
/*     */     //   18: aload_0
/*     */     //   19: invokevirtual actor : ()Lakka/actor/ActorCell;
/*     */     //   22: aload #5
/*     */     //   24: invokevirtual invoke : (Lakka/dispatch/Envelope;)V
/*     */     //   27: invokestatic interrupted : ()Z
/*     */     //   30: ifeq -> 43
/*     */     //   33: new java/lang/InterruptedException
/*     */     //   36: dup
/*     */     //   37: ldc 'Interrupted while processing actor messages'
/*     */     //   39: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   42: athrow
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual processAllSystemMessages : ()V
/*     */     //   47: iload_1
/*     */     //   48: iconst_1
/*     */     //   49: if_icmple -> 82
/*     */     //   52: aload_0
/*     */     //   53: invokevirtual dispatcher : ()Lakka/dispatch/MessageDispatcher;
/*     */     //   56: invokevirtual isThroughputDeadlineTimeDefined : ()Z
/*     */     //   59: iconst_0
/*     */     //   60: if_icmpeq -> 73
/*     */     //   63: invokestatic nanoTime : ()J
/*     */     //   66: lload_2
/*     */     //   67: lsub
/*     */     //   68: lconst_0
/*     */     //   69: lcmp
/*     */     //   70: ifge -> 82
/*     */     //   73: iload_1
/*     */     //   74: iconst_1
/*     */     //   75: isub
/*     */     //   76: lload_2
/*     */     //   77: lstore_2
/*     */     //   78: istore_1
/*     */     //   79: goto -> 0
/*     */     //   82: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   85: goto -> 97
/*     */     //   88: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   91: goto -> 97
/*     */     //   94: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   97: pop
/*     */     //   98: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #234	-> 0
/*     */     //   #235	-> 7
/*     */     //   #236	-> 13
/*     */     //   #238	-> 18
/*     */     //   #239	-> 27
/*     */     //   #240	-> 33
/*     */     //   #241	-> 43
/*     */     //   #242	-> 47
/*     */     //   #243	-> 73
/*     */     //   #242	-> 82
/*     */     //   #236	-> 88
/*     */     //   #234	-> 94
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	99	0	this	Lakka/dispatch/Mailbox;
/*     */     //   0	99	1	left	I
/*     */     //   0	99	2	deadlineNs	J
/*     */     //   13	86	5	next	Lakka/dispatch/Envelope;
/*     */   }
/*     */   
/*     */   public final void processAllSystemMessages() {
/* 255 */     null;
/* 255 */     Throwable interruption = null;
/* 256 */     SystemMessage messageList = systemDrain(SystemMessageList$.MODULE$.LNil());
/* 257 */     while (EarliestFirstSystemMessageList$.MODULE$.nonEmpty$extension(messageList) && !isClosed()) {
/* 258 */       SystemMessage msg = messageList;
/* 259 */       messageList = EarliestFirstSystemMessageList$.MODULE$.tail$extension(messageList);
/* 260 */       msg.unlink();
/* 263 */       actor().systemInvoke(msg);
/* 264 */       if (Thread.interrupted())
/* 265 */         interruption = new InterruptedException("Interrupted while processing system messages"); 
/* 265 */       if (EarliestFirstSystemMessageList$.MODULE$
/*     */         
/* 267 */         .isEmpty$extension(messageList) && !isClosed())
/* 267 */         messageList = systemDrain(SystemMessageList$.MODULE$.LNil()); 
/*     */     } 
/* 273 */     Mailbox dlm = actor().dispatcher().mailboxes().deadLetterMailbox();
/* 281 */     while (EarliestFirstSystemMessageList$.MODULE$.nonEmpty$extension(messageList)) {
/*     */       SystemMessage msg = messageList;
/*     */       messageList = EarliestFirstSystemMessageList$.MODULE$.tail$extension(messageList);
/*     */       msg.unlink();
/*     */       try {
/*     */       
/*     */       } finally {
/*     */         BoxedUnit boxedUnit;
/*     */         Exception exception1 = null, exception2 = exception1;
/*     */         if (exception2 instanceof InterruptedException) {
/*     */           InterruptedException interruptedException = (InterruptedException)exception2;
/*     */           BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */           continue;
/*     */         } 
/* 281 */         Option option = NonFatal$.MODULE$.unapply(exception2);
/* 281 */         if (option.isEmpty())
/*     */           throw exception1; 
/* 281 */         Throwable e = (Throwable)option.get();
/* 281 */         actor().system().eventStream().publish(
/* 282 */             new Logging.Error(e, actor().self().path().toString(), getClass(), (new StringBuilder()).append("error while enqueuing ").append(msg).append(" to deadLetters: ").append(e.getMessage()).toString()));
/*     */       } 
/*     */     } 
/* 286 */     if (interruption != null) {
/* 287 */       Thread.interrupted();
/* 288 */       throw interruption;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cleanUp() {
/* 298 */     if (actor() != null) {
/* 299 */       Mailbox dlm = actor().dispatcher().mailboxes().deadLetterMailbox();
/* 300 */       SystemMessage messageList = systemDrain((SystemMessage)NoMessage$.MODULE$);
/* 301 */       while (EarliestFirstSystemMessageList$.MODULE$.nonEmpty$extension(messageList)) {
/* 303 */         SystemMessage msg = messageList;
/* 304 */         messageList = EarliestFirstSystemMessageList$.MODULE$.tail$extension(messageList);
/* 305 */         msg.unlink();
/* 306 */         dlm.systemEnqueue((ActorRef)actor().self(), msg);
/*     */       } 
/* 309 */       if (messageQueue() != null)
/* 310 */         messageQueue().cleanUp((ActorRef)actor().self(), actor().dispatcher().mailboxes().deadLetterMailbox().messageQueue()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean debug() {
/*     */     return Mailbox$.MODULE$.debug();
/*     */   }
/*     */   
/*     */   public static int suspendUnit() {
/*     */     return Mailbox$.MODULE$.suspendUnit();
/*     */   }
/*     */   
/*     */   public static int suspendMask() {
/*     */     return Mailbox$.MODULE$.suspendMask();
/*     */   }
/*     */   
/*     */   public static int shouldNotProcessMask() {
/*     */     return Mailbox$.MODULE$.shouldNotProcessMask();
/*     */   }
/*     */   
/*     */   public static int shouldScheduleMask() {
/*     */     return Mailbox$.MODULE$.shouldScheduleMask();
/*     */   }
/*     */   
/*     */   public static int Scheduled() {
/*     */     return Mailbox$.MODULE$.Scheduled();
/*     */   }
/*     */   
/*     */   public static int Closed() {
/*     */     return Mailbox$.MODULE$.Closed();
/*     */   }
/*     */   
/*     */   public static int Open() {
/*     */     return Mailbox$.MODULE$.Open();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Mailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */